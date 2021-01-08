package statetrain.core.event;

import statetrain.core.event.args.*;

public interface IStateEventNotifier<TTrigger, TState> {

    void onInitialTransition(InitialTransitionArgs<TTrigger, TState> args);
    void onErrorTransition(ErrorTransitionArgs<TTrigger, TState> args);
    void onTriggerReceived(TriggerReceivedArgs<TTrigger, TState> args);
    void onStateTransitioned(StateTransitionedArgs<TTrigger, TState> args);
    void onActivatingBehavior(ActivatingBehaviorArgs<TTrigger, TState> args);
    void onActivatedBehavior(ActivatedBehaviorArgs<TTrigger, TState> args);
    void onDeactivatingBehavior(DeactivatingBehaviorArgs<TTrigger, TState> args);
    void onDeactivatedBehavior(DeactivatedBehaviorArgs<TTrigger, TState> args);
    void onException(ExceptionArgs<TTrigger, TState> args);
    void onStoppedTransition(StoppedTransitionArgs<TTrigger, TState> args);
}
