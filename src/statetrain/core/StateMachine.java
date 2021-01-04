package statetrain.core;

import statetrain.core.behavior.IBehavior;
import statetrain.core.behavior.args.BehaviorActivatedArgs;
import statetrain.core.behavior.args.BehaviorActivatingArgs;
import statetrain.core.behavior.args.BehaviorDeactivatedArgs;
import statetrain.core.behavior.args.BehaviorTriggerTransitionArgs;
import statetrain.core.event.IStateEventNotifier;
import statetrain.core.event.NullStateEventNotifier;
import statetrain.core.event.args.*;
import statetrain.core.exceptions.StateMachineStateException;
import statetrain.core.exceptions.StateMachineStateExceptionReason;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class StateMachine<TTrigger, TState> implements AutoCloseable {

    private final Map<TState, State<TTrigger, TState>> states;
    private final IStateEventNotifier<TTrigger, TState> stateEventNotifier;

    private State<TTrigger, TState> currentState;
    private final State<TTrigger, TState>  initialState;
    private final State<TTrigger, TState> onErrorState;

    public StateMachine(Map<TState, State<TTrigger, TState>> states, State<TTrigger, TState> initialState, State<TTrigger, TState> onErrorState, IStateEventNotifier<TTrigger, TState> stateEventNotifier) throws StateMachineStateException {

        if(null == initialState){
            throw new StateMachineStateException(StateMachineStateExceptionReason.InitialStateNotProvided);
        }

        if(null == states){
            throw new IllegalArgumentException("State map can't be null");
        }

        if(states.isEmpty()){
            throw new StateMachineStateException(StateMachineStateExceptionReason.EmptyStateMap, "State map can't be empty");
        }

        this.stateEventNotifier = Optional.ofNullable(stateEventNotifier).orElseGet(NullStateEventNotifier::new);

        this.states = states;
        this.initialState = initialState;
        this.onErrorState = Optional.ofNullable(onErrorState).orElse(initialState);

        transitionToInitialState();
    }

    public StateMachine(Map<TState, State<TTrigger, TState>> states, TState initialState, TState onErrorState, IStateEventNotifier<TTrigger, TState> stateEventNotifier) throws StateMachineStateException {
        this(states, states.get(initialState), states.get(onErrorState), stateEventNotifier);
    }

    private void transitionToInitialState(){
        final var context = new StateMachineContext<>(this);
        stateEventNotifier.onInitialTransition(new InitialTransitionArgs<>(initialState, context));
        performTransition(null, initialState, context, null, null, new TransitionArgs<>(new HashMap<>()));
        context.commit();
    }

    private void transitionToErrorState(Throwable error){
        final var context = new StateMachineContext<>(this);
        stateEventNotifier.onErrorTransition(new ErrorTransitionArgs<>(currentState, onErrorState, context, error));
        performTransition(currentState, onErrorState, new StateMachineContext<>(this), null, null, new TransitionArgs<>(new HashMap<>()));
        context.commit();
    }

    public State<TTrigger, TState> triggerTransition(TTrigger trigger){
        return triggerTransition(trigger, null);
    }

    public synchronized State<TTrigger, TState> triggerTransition(TTrigger trigger, TransitionArgs args){

        args = Optional.ofNullable(args).orElse(new TransitionArgs(new HashMap<>()));

        final var context = new StateMachineContext<>(this);
        stateEventNotifier.onTriggerReceived(new TriggerReceivedArgs<>(trigger, currentState, args, context));
        final var result = handleTransitionTrigger(trigger, context, args);
        context.commit();

        return result;
    }

    private synchronized State<TTrigger, TState> handleTransitionTrigger(TTrigger trigger, StateMachineContext<TTrigger, TState> context, TransitionArgs<TTrigger, TState> args){
        final var behaviors = currentState.getBehaviors();
//todo - create objects args instead of arguments to behavior methods for future compatibility
        State<TTrigger, TState> transitionToState = null;
        IBehavior<TTrigger, TState> transitionDueToBehavior = null;

        for(final var behavior : behaviors){
            final var result = behavior.triggerTransition(new BehaviorTriggerTransitionArgs<>(trigger, args, currentState, context));

            final var resultState = result.getResult();

            if(null != resultState){
                transitionDueToBehavior = behavior;
                transitionToState = getSafely(() -> selectStateToTransition(currentState, trigger, resultState), this::transitionToErrorState);
            }

            if(result.stopTransition()){
                stateEventNotifier.onStoppedTransition(new StoppedTransitionArgs<>(context, result, transitionDueToBehavior, null != transitionToState));
                break;
            }
        }

        if(null != transitionToState){
            performTransition(currentState, transitionToState, context, trigger, transitionDueToBehavior, args);
        }

        return currentState;
    }

    private void performTransition(State<TTrigger, TState> oldState, State<TTrigger, TState> newState, StateMachineContext<TTrigger, TState> context, TTrigger trigger, IBehavior<TTrigger, TState> transitionCauser, TransitionArgs<TTrigger, TState> args){

        if(null != oldState){
            for(final var behavior : oldState.getBehaviors()){
                callBehaviorDeactivated(behavior, context, oldState, newState, trigger, args);
            }
        }

        for(final var behavior : newState.getBehaviors()){
            callBehaviorActivating(behavior, context, newState, trigger, args);
        }

        currentState = newState;
        stateEventNotifier.onStateTransitioned(new StateTransitionedArgs<>(oldState, newState, context, trigger, transitionCauser));

        for(final var behavior : newState.getBehaviors()){
            callBehaviorActivated(behavior, context, newState, trigger, args);
        }
    }

    private void callBehaviorActivated(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> args){
        runSafely(() ->  behavior.activated(new BehaviorActivatedArgs<>(trigger, args, newState, context)), this::notifyError);
        stateEventNotifier.onActivatedBehavior(new ActivatedBehaviorArgs<>(behavior, context, newState, trigger));
    }

    private void callBehaviorActivating(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> args){
        runSafely(() ->  behavior.activating(new BehaviorActivatingArgs<>(trigger, args, newState, context)), this::notifyError);
        stateEventNotifier.onActivatingBehavior(new ActivatingBehaviorArgs<>(behavior, context, newState, trigger));
    }

    private void callBehaviorDeactivated(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> oldState, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> args){
        runSafely(() ->  behavior.deactivated(new BehaviorDeactivatedArgs<>(trigger, args, oldState, newState, context)), this::notifyError);
        stateEventNotifier.onDeactivatedBehavior(new DeactivatedBehaviorArgs<>(behavior, context, oldState, newState, trigger));
    }

    private State<TTrigger, TState> selectStateToTransition(State<TTrigger, TState> currentState, TTrigger event, TState foundEventToTransition){
        return Optional.ofNullable(foundEventToTransition).map(p -> states.getOrDefault(p, null)).orElse(onErrorState);
    }

    public synchronized State<TTrigger, TState> getCurrentState() {
        return currentState;
    }

    public Map<TState, State<TTrigger, TState>> getStates() {
        return states;
    }

    private boolean runSafely(Runnable runnable, Consumer<Exception> onError){
        try{
            runnable.run();
            return true;
        } catch (Exception ex){
            onError.accept(ex);
            return false;
        }
    }

    private <T> T getSafely(Supplier<T> func, Consumer<Exception> onError){
        try{
            return func.get();
        } catch (Exception ex){
            onError.accept(ex);
            return null;
        }
    }

    @Override
    public String toString() {
        return "StateMachine{" +
                "states=" + states +
                ", currentState=" + currentState +
                ", onErrorState=" + onErrorState +
                '}';
    }

    private void notifyError(Throwable error){
        if (null != stateEventNotifier) {
            final var context = new StateMachineContext<>(this);
            stateEventNotifier.onException(new ExceptionArgs(currentState, error, context));
            context.commit();
        }
    }

    @Override
    public synchronized void close() throws Exception {
        currentState.close();
        states.values().forEach(s -> {
            try {
                s.close();
            } catch (Exception ex) {
                notifyError(ex);
            }
        });
    }
}
