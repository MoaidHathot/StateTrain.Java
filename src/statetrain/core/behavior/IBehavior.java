package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.BehaviorActivatedArgs;
import statetrain.core.behavior.args.BehaviorActivatingArgs;
import statetrain.core.behavior.args.BehaviorDeactivatedArgs;
import statetrain.core.behavior.args.BehaviorTriggerTransitionArgs;

public interface IBehavior<TTrigger, TState> extends AutoCloseable {

    String getName();

    void activating(BehaviorActivatingArgs<TTrigger, TState> args);
    void activated(BehaviorActivatedArgs<TTrigger, TState> args);
    void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args);
    TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args);
}
