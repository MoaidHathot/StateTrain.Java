package statetrain.core.exceptions;

import statetrain.exceptions.StateMachineException;

public class StateMachineStateException extends StateMachineException {
    private final StateMachineStateExceptionReason reason;

    public StateMachineStateException(StateMachineStateExceptionReason reason) {
        this.reason = reason;
    }

    public StateMachineStateException(StateMachineStateExceptionReason reason, String message) {
        super(message);
        this.reason = reason;
    }

    public StateMachineStateException(StateMachineStateExceptionReason reason, String message, Throwable cause) {
        super(message, cause);
        this.reason = reason;
    }

    public StateMachineStateException(StateMachineStateExceptionReason reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public StateMachineStateException(StateMachineStateExceptionReason reason, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reason = reason;
    }
}
