package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

public class ImmediateStateTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    private final TTrigger immediateTrigger;
    private final TState immediateState;

    public ImmediateStateTransitionBehavior(TState attachedState, TTrigger immediateTrigger, TState immediateState) {
        super(attachedState);
        this.immediateTrigger = immediateTrigger;
        this.immediateState = immediateState;
    }

    @Override
    public void activating(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
    }

    @Override
    public void activated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
        context.registerResolveAction(c -> c.triggerTransition(immediateTrigger));
    }

    @Override
    public void deactivated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger, State<TTrigger, TState> newState) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
        return trigger == immediateTrigger ? TransitionResult.stateTransitionResult(immediateState) : TransitionResult.noActionResult();
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public String toString() {
        return "ImmediateStateTransitionBehavior{" +
                "immediateTrigger=" + immediateTrigger +
                ", immediateState=" + immediateState +
                '}';
    }
}
