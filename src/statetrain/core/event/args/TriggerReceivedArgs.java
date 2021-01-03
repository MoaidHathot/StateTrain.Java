package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

public class TriggerReceivedArgs<TTrigger, TState> {
    private final TTrigger trigger;
    private final State<TTrigger, TState> currentState;
    private final StateMachineContext<TTrigger, TState> context;

    public TriggerReceivedArgs(TTrigger trigger, State<TTrigger, TState> currentState, StateMachineContext<TTrigger, TState> context) {
        this.trigger = trigger;
        this.currentState = currentState;
        this.context = context;
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
                ", context=" + context +
                '}';
    }
}
