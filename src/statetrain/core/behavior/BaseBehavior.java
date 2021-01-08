package statetrain.core.behavior;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.behavior.args.*;

public abstract class BaseBehavior<TTrigger, TState> implements IBehavior<TTrigger, TState> {

    private final String name;
    private final TState attachedStateType;

    public BaseBehavior(TState attachedState){
        this.name = getClass().getName();
        this.attachedStateType = attachedState;
    }

    @Override
    public String getName() {
        return name;
    }

    public TState getAttachedStateType() {
        return attachedStateType;
    }

    @Override
    public abstract void activating(BehaviorActivatingArgs<TTrigger, TState> args);

    @Override
    public abstract void activated(BehaviorActivatedArgs<TTrigger, TState> args);

    @Override
    public abstract void deactivating(BehaviorDeactivatingArgs<TTrigger, TState> args);

    @Override
    public abstract void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args);

    @Override
    public abstract TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args);

    @Override
    public String toString() {
        return "BaseBehavior{" +
                "name='" + name + '\'' +
                ", attachedStateType=" + attachedStateType +
                '}';
    }
}