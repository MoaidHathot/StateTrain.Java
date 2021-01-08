package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.*;

import java.util.Optional;

public class RegisteredTriggerTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {

    public RegisteredTriggerTransitionBehavior(TState attachedState) {
        super(attachedState);
    }

    @Override
    public void activating(BehaviorActivatingArgs<TTrigger, TState> args) {
    }

    @Override
    public void activated(BehaviorActivatedArgs<TTrigger, TState> args) {
    }

    @Override
    public void deactivating(BehaviorDeactivatingArgs<TTrigger, TState> args) {

    }

    @Override
    public void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args) {
    }

    @Override
    public TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args) {
        return Optional
            .of(args.getTrigger())
            .map(t -> args.getContext().getCurrentState().getStateMap().getOrDefault(t, null))
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
