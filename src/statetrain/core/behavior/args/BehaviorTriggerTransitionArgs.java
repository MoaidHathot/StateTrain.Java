package statetrain.core.behavior.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;

public class BehaviorTriggerTransitionArgs<TTrigger, TState> {
    private final TTrigger trigger;
    private final TransitionArgs<TTrigger, TState> transitionArgs;
    private final State<TTrigger, TState> attachedState;
    private final StateMachineContext<TTrigger, TState> context;

    public BehaviorTriggerTransitionArgs(TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs, State<TTrigger, TState> attachedState, StateMachineContext<TTrigger, TState> context) {
        this.trigger = trigger;
        this.transitionArgs = transitionArgs;
        this.attachedState = attachedState;
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

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "BehaviorTriggerTransitionArgs{" +
                "trigger=" + trigger +
                ", transitionArgs=" + transitionArgs +
                ", attachedState=" + attachedState +
                ", context=" + context +
                '}';
    }
}
