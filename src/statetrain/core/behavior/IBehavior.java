package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.*;

public interface IBehavior<TTrigger, TState> extends AutoCloseable {

    String getName();

    void activating(BehaviorActivatingArgs<TTrigger, TState> args);
    void activated(BehaviorActivatedArgs<TTrigger, TState> args);
    void deactivating(BehaviorDeactivatingArgs<TTrigger, TState> args);
    void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args);
    TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args);
}
