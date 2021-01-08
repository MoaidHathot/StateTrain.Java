package statetrain.core.behavior;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.behavior.args.*;

public class ImmediateTriggerTransitionStateBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {
    private final TTrigger immediateTrigger;

    public ImmediateTriggerTransitionStateBehavior(TState attachedState, TTrigger immediateTrigger) {
        super(attachedState);
        this.immediateTrigger = immediateTrigger;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void activating(BehaviorActivatingArgs<TTrigger, TState> args) {
    }

    @Override
    public void activated(BehaviorActivatedArgs<TTrigger, TState> args) {
        args.getContext().registerResolveAction(c -> c.triggerTransition(immediateTrigger));
    }

    @Override
    public void deactivating(BehaviorDeactivatingArgs<TTrigger, TState> args) {

    }

    @Override
    public void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args) {
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
