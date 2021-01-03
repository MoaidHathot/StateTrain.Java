package statetrain.builder;

import statetrain.core.behavior.IBehavior;
import statetrain.core.event.CompositeStateEventNotifier;
import statetrain.core.event.IStateEventNotifier;
import statetrain.core.event.NullStateEventNotifier;
import statetrain.exceptions.StateMachineException;
import statetrain.builder.exceptions.StateMachineBuilderException;
import statetrain.builder.exceptions.StateMachineBuilderExceptionReason;
import statetrain.core.State;
import statetrain.core.StateMachine;

import java.util.*;
import java.util.function.Function;

public class StateMachineBuilder<TTrigger, TState> {

    private final List<IStateBuilder<TTrigger, TState>> stateBuilders = new ArrayList<>();

    private TState initialState;
    private TState errorState;

    private List<IStateEventNotifier<TTrigger, TState>> stateEventNotifiers = new ArrayList<>();

    private Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> defaultBehavior;

    public StateMachineBuilder(){

    }

    public StateMachineBuilder(TState initialState, TState errorState){
        this.initialState = initialState;
        this.errorState = errorState;
    }

    public NewStateStateBuilder<TTrigger, TState> configureState(TState state){
        var builder = new NewStateStateBuilder<TTrigger, TState>(state);
        registerState(builder);

        return builder;
    }

    public StateMachineBuilder<TTrigger, TState> setInitialState(TState initialState){
        this.initialState = initialState;
        return this;
    }

    public StateMachineBuilder<TTrigger, TState> setErrorState(TState errorState){
        this.errorState = errorState;
        return this;
    }

    public StateMachineBuilder<TTrigger, TState> setDefaultStateBehavior(Function<IStateBuilder<TTrigger, TState>, IBehavior<TTrigger, TState>> defaultBehavior){
        this.defaultBehavior = defaultBehavior;
        return this;
    }

    public StateMachineBuilder<TTrigger, TState> registerState(IStateBuilder<TTrigger, TState> stateBuilder){
        stateBuilders.add(stateBuilder);
        return this;
    }

    public StateMachine<TTrigger, TState> build() throws StateMachineException {
        return build(true, true);
    }

    public StateMachine<TTrigger, TState> build(boolean uniqueStates, boolean uniqueStateNames) throws StateMachineException {

        final var nameSet = new HashSet<String>();
        final var states = new HashMap<TState, State<TTrigger, TState>>();

        registerDefaultBehaviorToEmptyStates();

        for(final var builder : stateBuilders){
            enforceRule(nameSet, states, builder.getState(), builder.getName(), uniqueStates, uniqueStateNames);
            updateCache(nameSet, states, builder.build());
        }

        IStateEventNotifier<TTrigger, TState> notifier = switch (stateEventNotifiers.size()){
            case 0 -> new NullStateEventNotifier<>();
            case 1 -> stateEventNotifiers.get(0);
            default -> new CompositeStateEventNotifier<>(stateEventNotifiers);
        };

        return new StateMachine<>(states, initialState, errorState, notifier);
    }

    public StateMachineBuilder<TTrigger, TState> addNotifier(IStateEventNotifier<TTrigger, TState> notifier){
        stateEventNotifiers.add(notifier);
        return this;
    }

    private void updateCache(Set<String> nameSet, Map<TState, State<TTrigger, TState>> states, State<TTrigger, TState> state){
        nameSet.add(state.getName());
        states.put(state.getState(), state);
    }

    private void enforceRule(Set<String> nameSet, Map<TState, State<TTrigger, TState>> states, TState state, String name, boolean uniqueStates, boolean uniqueNames) throws StateMachineBuilderException {
        if(uniqueNames){
           if(nameSet.contains(name)){
               throw new StateMachineBuilderException(StateMachineBuilderExceptionReason.StateNameAlreadyExist, String.format("State with name: '%s' is already registered", name));
           }
        }

        if(uniqueStates){
            if(states.containsKey(state)){
                throw new StateMachineBuilderException(StateMachineBuilderExceptionReason.StateAlreadyExist, String.format("State: '%s' is already registered", state));
            }
        }
    }

    private void registerDefaultBehaviorToEmptyStates() throws StateMachineBuilderException {
        for(final var builder : stateBuilders){
            if(builder.getBehaviors().isEmpty()){
                if(null == defaultBehavior){
                    throw new StateMachineBuilderException(StateMachineBuilderExceptionReason.StateWithoutBehaviors, String.format("State: '%s' does not have registered behaviors", builder.getState()));
                }

                builder.addBehavior(defaultBehavior);
            }
        }
    }
}
