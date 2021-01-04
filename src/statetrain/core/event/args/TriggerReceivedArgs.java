package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.TransitionArgs;

public class TriggerReceivedArgs<TTrigger, TState> {
    private final TTrigger trigger;
    private final State<TTrigger, TState> currentState;
    private final TransitionArgs transitionArgs;
    private final StateMachineContext<TTrigger, TState> context;

    public TriggerReceivedArgs(TTrigger trigger, State<TTrigger, TState> currentState, TransitionArgs transitionArgs, StateMachineContext<TTrigger, TState> context) {
        this.trigger = trigger;
        this.currentState = currentState;
        this.transitionArgs = transitionArgs;
        this.context = context;
    }

    public TransitionArgs getTransitionArgs() {
        return transitionArgs;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    public State<TTrigger, TState> getCurrentState() {
        return currentState;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "TriggerReceivedArgs{" +
                "trigger=" + trigger +
                ", currentState=" + currentState +
                ", transitionArgs=" + transitionArgs +
                ", context=" + context +
                '}';
    }
}
