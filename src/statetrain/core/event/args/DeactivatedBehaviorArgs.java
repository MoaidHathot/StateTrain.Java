package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;

public class DeactivatedBehaviorArgs<TTrigger, TState> {
    private final IBehavior<TTrigger, TState> behavior;
    private final State<TTrigger, TState> oldState;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionArgs<TTrigger, TState> transitionArgs;

    public DeactivatedBehaviorArgs(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> oldState, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs) {
        this.behavior = behavior;
        this.context = context;
        this.oldState = oldState;
        this.newState = newState;
        this.trigger = trigger;
        this.transitionArgs = transitionArgs;
    }

    public TransitionArgs<TTrigger, TState> getTransitionArgs() {
        return transitionArgs;
    }

    public IBehavior<TTrigger, TState> getBehavior() {
        return behavior;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
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

    @Override
    public String toString() {
        return "DeactivatedBehaviorArgs{" +
                "oldState=" + oldState +
                ", newState=" + newState +
                ", trigger=" + trigger +
                ", transitionArgs=" + transitionArgs +
                ", behavior=" + behavior +
                ", context=" + context +
                '}';
    }
}
