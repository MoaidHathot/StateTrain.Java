package statetrain.core.behavior.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;

public class BehaviorDeactivatingArgs<TTrigger, TState> {
    private final TTrigger trigger;
    private final TransitionArgs<TTrigger, TState> transitionArgs;
    private final State<TTrigger, TState> attachedState;
    private final State<TTrigger, TState> newState;
    private final StateMachineContext<TTrigger, TState> context;

    public BehaviorDeactivatingArgs(TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs, State<TTrigger, TState> attachedState, State<TTrigger, TState> newState, StateMachineContext<TTrigger, TState> context) {
        this.trigger = trigger;
        this.transitionArgs = transitionArgs;
        this.attachedState = attachedState;
        this.newState = newState;
        this.context = context;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    public TransitionArgs<TTrigger, TState> getTransitionArgs() {
        return transitionArgs;
    }

    public State<TTrigger, TState> getAttachedState() {
        return attachedState;
    }

    public State<TTrigger, TState> getNewState() {
        return newState;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "BehaviorDeactivatingArgs{" +
                "trigger=" + trigger +
                ", transitionArgs=" + transitionArgs +
                ", attachedState=" + attachedState +
                ", newState=" + newState +
                ", context=" + context +
                '}';
    }
}
