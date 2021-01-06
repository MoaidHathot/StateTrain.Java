package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.TransitionArgs;
import statetrain.core.behavior.IBehavior;

public class StateTransitionedArgs<TTrigger, TState> {
    private final State<TTrigger, TState> oldState;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final IBehavior<TTrigger, TState> transitionCauser;
    private final StateMachineContext<TTrigger, TState> context;
    private final TransitionArgs<TTrigger, TState> transitionArgs;

    public StateTransitionedArgs(State<TTrigger, TState> oldState, State<TTrigger, TState> newState, TTrigger trigger, TransitionArgs<TTrigger, TState> transitionArgs, StateMachineContext<TTrigger, TState> context, IBehavior<TTrigger, TState> transitionCauser) {
        this.oldState = oldState;
        this.newState = newState;
        this.trigger = trigger;
        this.transitionCauser = transitionCauser;
        this.context = context;
        this.transitionArgs = transitionArgs;
    }

    public TransitionArgs<TTrigger, TState> getTransitionArgs() {
        return transitionArgs;
    }

    public State<TTrigger, TState> getOldState() {
        return oldState;
    }

    public State<TTrigger, TState> getNewState() {
        return newState;
    }

    public StateMachineContext<TTrigger, TState> getContext() {
        return context;
    }

    public TTrigger getTrigger() {
        return trigger;
    }

    public IBehavior<TTrigger, TState> getTransitionCauser() {
        return transitionCauser;
    }

    @Override
    public String toString() {
        return "StateTransitionedArgs{" +
                "oldState=" + oldState +
                ", newState=" + newState +
                ", trigger=" + trigger +
                ", transitionArgs=" + transitionArgs +
                ", transitionCauser=" + transitionCauser +
                ", context=" + context +
                '}';
    }
}
