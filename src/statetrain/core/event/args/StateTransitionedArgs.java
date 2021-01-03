package statetrain.core.event.args;

import statetrain.core.State;
import statetrain.core.StateMachineContext;
import statetrain.core.behavior.IBehavior;

public class StateTransitionedArgs<TTrigger, TState> {
    private final State<TTrigger, TState> oldState;
    private final State<TTrigger, TState> newState;
    private final TTrigger trigger;
    private final IBehavior<TTrigger, TState> transitionCauser;
    private final StateMachineContext<TTrigger, TState> context;

    public StateTransitionedArgs(State<TTrigger, TState> oldState, State<TTrigger, TState> newState, StateMachineContext<TTrigger, TState> context, TTrigger trigger, IBehavior<TTrigger, TState> transitionCauser) {
        this.oldState = oldState;
        this.newState = newState;
        this.context = context;
        this.trigger = trigger;
        this.transitionCauser = transitionCauser;
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
                ", transitionCauser=" + transitionCauser +
                ", context=" + context +
                '}';
    }
}
