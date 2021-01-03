package statetrain.builder.exceptions;

import statetrain.exceptions.StateMachineException;

public class StateMachineBuilderException extends StateMachineException {

    private final StateMachineBuilderExceptionReason reason;

    public StateMachineBuilderException(StateMachineBuilderExceptionReason reason) {
        this.reason = reason;
    }

    public StateMachineBuilderException(StateMachineBuilderExceptionReason reason, String message) {
        super(message);
        this.reason = reason;
    }

    public StateMachineBuilderException(StateMachineBuilderExceptionReason reason, String message, Throwable cause) {
        super(message, cause);
        this.reason = reason;
    }

    public StateMachineBuilderException(StateMachineBuilderExceptionReason reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public StateMachineBuilderException(StateMachineBuilderExceptionReason reason, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.reason = reason;
    }
}
