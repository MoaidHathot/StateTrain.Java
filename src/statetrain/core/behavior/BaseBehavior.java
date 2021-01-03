package statetrain.core.behavior;

import statetrain.core.State;
import statetrain.core.StateMachineContext;

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
    public abstract void activating(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);

    @Override
    public abstract void activated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);

    @Override
    public abstract void deactivated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger, State<TTrigger, TState> newState);

    @Override
    public abstract TransitionResult<TState> triggerTransition(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);

    @Override
    public String toString() {
        return "BaseBehavior{" +
                "name='" + name + '\'' +
                ", attachedStateType=" + attachedStateType +
                '}';
    }
}