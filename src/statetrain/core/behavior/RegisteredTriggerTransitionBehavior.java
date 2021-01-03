package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

import java.util.Optional;

public class RegisteredTriggerTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    public RegisteredTriggerTransitionBehavior(TState attachedState) {
        super(attachedState);
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
        return Optional
            .of(trigger)
            .map(t -> context.getCurrentState().getStateMap().getOrDefault(t, null))
            .map(TransitionResult::stateTransitionResult)
            .orElse(TransitionResult.noActionResult());
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public String toString() {
        return "RegisteredTriggerTransitionBehavior{}";
    }
}
