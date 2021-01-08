package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.*;

public class ImmediateStateTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    private final TTrigger immediateTrigger;
    private final TState immediateState;

    public ImmediateStateTransitionBehavior(TState attachedState, TTrigger immediateTrigger, TState immediateState) {
        super(attachedState);
        this.immediateTrigger = immediateTrigger;
        this.immediateState = immediateState;
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
        return args.getTrigger() == immediateTrigger ? TransitionResult.stateTransitionResult(immediateState) : TransitionResult.noActionResult();
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
