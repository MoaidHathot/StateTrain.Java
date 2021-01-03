package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

import java.util.Optional;

public class ExceptionArgs<TTrigger, TState> {
    private final State<TTrigger, TState> currentState;
    private final Throwable error;
    private final StateMachineContext<TTrigger, TState> context;

    public ExceptionArgs(State<TTrigger, TState> currentState, Throwable error, StateMachineContext<TTrigger, TState> context) {
        this.currentState = currentState;
        this.error = error;
        this.context = context;
    }

    public State<TTrigger, TState> getCurrentState() {
        return currentState;
    }

    public Throwable getError() {
        return error;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "ExceptionArgs{" +
                "currentState=" + currentState +
                ", error=" + Optional.ofNullable(error).map(Throwable::getMessage).orElse(null) +
                ", context=" + context +
                '}';
    }
}
