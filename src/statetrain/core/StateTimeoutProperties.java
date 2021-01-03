package statetrain.core;

import java.time.Duration;

public class StateTimeoutProperties<TState> {
    private Duration timeout;
    private TState stateAfterTimeout;

    public StateTimeoutProperties(Duration timeout, TState stateAfterTimeout) {
        this.timeout = timeout;
        this.stateAfterTimeout = stateAfterTimeout;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

    public TState getStateAfterTimeout() {
        return stateAfterTimeout;
    }

    public void setStateAfterTimeout(TState stateAfterTimeout) {
        this.stateAfterTimeout = stateAfterTimeout;
    }

    @Override
    public String toString() {
        return "StateTimeoutProperties{" +
                "timeout=" + timeout +
                ", stateAfterTimeout=" + stateAfterTimeout +
                '}';
    }
}
