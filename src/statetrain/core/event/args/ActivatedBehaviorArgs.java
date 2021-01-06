package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;

public class ActivatedBehaviorArgs<TTrigger, TState> {
    private final IBehavior<TTrigger, TState> behavior;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionArgs<TTrigger, TState> transitionArgs;

    public ActivatedBehaviorArgs(IBehavior<TTrigger, TState> behavior, StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs) {
        this.behavior = behavior;
        this.context = context;
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

    public State<TTrigger, TState> getNewState() {
        return newState;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "ActivatedBehaviorArgs{" +
                "newState=" + newState +
                ", trigger=" + trigger +
                ", transitionArgs=" + transitionArgs +
                ", context=" + context +
                ", behavior=" + behavior +
                '}';
    }
}
