package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;

public class DeactivatingBehaviorArgs<TTrigger, TState> {
    private final IBehavior<TTrigger, TState> behavior;
    private final State<TTrigger, TState> oldState;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionArgs<TTrigger, TState> transitionArgs;

    public DeactivatingBehaviorArgs(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> oldState, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs) {
        this.behavior = behavior;
        this.oldState = oldState;
        this.newState = newState;
        this.trigger = trigger;
        this.context = context;
        this.transitionArgs = transitionArgs;
    }

    public IBehavior<TTrigger, TState> getBehavior() {
        return behavior;
    }

    public State<TTrigger, TState> getOldState() {
        return oldState;
    }

    public State<TTrigger, TState> getNewState() {
        return newState;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    public TransitionArgs<TTrigger, TState> getTransitionArgs() {
        return transitionArgs;
    }

    @Override
    public String toString() {
        return "DeactivatingBehaviorArgs{" +
                "behavior=" + behavior +
                ", oldState=" + oldState +
                ", newState=" + newState +
                ", trigger=" + trigger +
                ", context=" + context +
                ", transitionArgs=" + transitionArgs +
                '}';
    }
}
