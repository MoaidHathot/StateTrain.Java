package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

import java.util.Optional;

public class ErrorTransitionArgs<TTrigger, TState> {

    private final State<TTrigger, TState> currentState;
    private final State<TTrigger, TState> errorState;
    private final Throwable error;
    private final StateMachineContext<TTrigger, TState> context;

    public ErrorTransitionArgs(State<TTrigger, TState> currentState, State<TTrigger, TState> errorState, StateMachineContext<TTrigger, TState> context, Throwable error) {
        this.currentState = currentState;
        this.errorState = errorState;
        this.context = context;
        this.error = error;
    }

    public State<TTrigger, TState> getCurrentState() {
        return currentState;
    }

    public State<TTrigger, TState> getErrorState() {
        return errorState;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ErrorTransitionArgs{" +
                "currentState=" + currentState +
                ", errorState=" + errorState +
                ", error=" + Optional.ofNullable(error).map(Throwable::getMessage).orElse(null) +
                ", context=" + context +
                '}';
    }
}
