package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

public class InitialTransitionArgs<TTrigger, TState> {
    private final State<TTrigger, TState> initialState;
    private final StateMachineContext<TTrigger, TState> context;

    public InitialTransitionArgs(State<TTrigger, TState> initialState, StateMachineContext<TTrigger, TState> context) {
        this.initialState = initialState;
        this.context = context;
    }

    public State<TTrigger, TState> getInitialState() {
        return initialState;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "InitialTransitionArgs{" +
                "initialState=" + initialState +
                ", context=" + context +
                '}';
    }
}
