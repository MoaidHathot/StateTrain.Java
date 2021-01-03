package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.behavior.IBehavior;

public class ActivatedBehaviorArgs<TTrigger, TState> {
    private final IBehavior<TTrigger, TState> behavior;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final StateMachineContext<TTrigger, TState> context;

    public ActivatedBehaviorArgs(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> newState, TTrigger trigger) {
        this.behavior = behavior;
        this.context = context;
        this.newState = newState;
        this.trigger = trigger;
    }

    public IBehavior<TTrigger, TState> getBehavior() {
        return behavior;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    public State<TTrigger, TState> getNewState() {
        return newState;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "ActivatedBehaviorArgs{" +
                "behavior=" + behavior +
                ", newState=" + newState +
                ", trigger=" + trigger +
                ", context=" + context +
                '}';
    }
}
