package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

public class UnregisteredTriggerTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    private final TState defaultState;

    public UnregisteredTriggerTransitionBehavior(TState attachedState, TState defaultState) {
        super(attachedState);
        this.defaultState = defaultState;
    }

    public TState getDefaultState() {
        return defaultState;
    }

    @Override
    public void activating(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
    }

    @Override
    public void activated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
    }

    @Override
    public void deactivated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger tTrigger, State<TTrigger, TState> newState) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {

        if(!context.getCurrentState().getStateMap().containsKey(trigger)){
            return TransitionResult.stateTransitionResult(defaultState);
        }

        return TransitionResult.noActionResult();
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public String toString() {
        return "UnknownTriggerTransitionBehavior{" +
                "defaultState=" + defaultState +
                '}';
    }
}
