package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.behavior.IBehavior;
import statetrain.core.behavior.TransitionResult;

public class StoppedTransitionArgs<TTrigger, TState> {
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionResult<TState> transitionResult;
    private final IBehavior<TTrigger, TState> stopCauser;
    private final boolean transitionStateSelected;

    public StoppedTransitionArgs(StateMachineContext<TTrigger, TState> context, TransitionResult<TState> transitionResult, IBehavior<TTrigger, TState> stopCauser, boolean transitionStateSelected) {
        this.context = context;
        this.transitionResult = transitionResult;
        this.stopCauser = stopCauser;
        this.transitionStateSelected = transitionStateSelected;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    public IBehavior<TTrigger, TState> getStopCauser() {
        return stopCauser;
    }

    @Override
    public String toString() {
        return "StoppedTransitionArgs{" +
                "context=" + context +
                ", transitionSelected=" + transitionStateSelected +
                ", transitionResult=" + transitionResult +
                ", stopCauser=" + stopCauser +
                '}';
    }
}
