package statetrain.core.event.args;

import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;
import statetrain.core.behavior.TransitionResult;

public class StoppedTransitionArgs<TTrigger, TState> {
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionResult<TState> transitionResult;
    private final IBehavior<TTrigger, TState> stopCauser;
    private final boolean transitionStateSelected;
    private TransitionArgs<TTrigger, TState> transitionArgs;

    public StoppedTransitionArgs(StateMachineContext<TTrigger, TState> context, TransitionResult<TState> transitionResult, IBehavior<TTrigger, TState> stopCauser, boolean transitionStateSelected, TransitionArgs<TTrigger, TState> transitionArgs) {
        this.context = context;
        this.transitionResult = transitionResult;
        this.stopCauser = stopCauser;
        this.transitionStateSelected = transitionStateSelected;
        this.transitionArgs = transitionArgs;
    }

    public TransitionResult<TState> getTransitionResult() {
        return transitionResult;
    }

    public boolean isTransitionStateSelected() {
        return transitionStateSelected;
    }

    public TransitionArgs<TTrigger, TState> getTransitionArgs() {
        return transitionArgs;
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
                ", transitionArgs=" + transitionArgs +
                ", stopCauser=" + stopCauser +
                '}';
    }
}
