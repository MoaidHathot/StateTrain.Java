package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;

public interface IBehavior<TTrigger, TState> extends AutoCloseable {

    String getName();

    void activating(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);
    void activated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);
    void deactivated(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger, State<TTrigger, TState> newState);
    TransitionResult<TState> triggerTransition(StateMachineContext<TTrigger, TState> context, State<TTrigger, TState> attachedState, TTrigger trigger);
}
