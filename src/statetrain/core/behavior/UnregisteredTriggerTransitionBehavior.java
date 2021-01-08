package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.*;

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

        if(!args.getContext().getCurrentState().getStateMap().containsKey(args.getTrigger())){
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
