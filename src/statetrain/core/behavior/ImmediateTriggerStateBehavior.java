package statetrain.core.behavior;

import statetrain.core.State;
import statetrain.core.StateMachineContext;

public class ImmediateTriggerStateBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {
    private final TTrigger immediateTrigger;

    public ImmediateTriggerStateBehavior(TState attachedState, TTrigger immediateTrigger) {
        super(attachedState);
        this.immediateTrigger = immediateTrigger;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void activating(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
    }

    @Override
    public void activated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
        context.registerResolveAction(c -> c.triggerTransition(immediateTrigger));
    }

    @Override
    public void deactivated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger tTrigger, State<TTrigger, TState> newState) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger) {
        return TransitionResult.noActionResult();
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public String toString() {
        return "ImmediateTriggerStateBehavior{" +
                "immediateTrigger=" + immediateTrigger +
                '}';
    }
}
