package statetrain.core.event;

import statetrain.core.event.args.*;

public class NullStateEventNotifier<TTrigger, TState> implements IStateEventNotifier<TTrigger, TState> {
    @Override
    public void onInitialTransition(InitialTransitionArgs<TTrigger, TState> args) {
    }

    @Override
    public void onErrorTransition(ErrorTransitionArgs<TTrigger, TState> args) {
    }

    @Override
    public void onTriggerReceived(TriggerReceivedArgs<TTrigger, TState> args) {
    }

    @Override
    public void onStateTransitioned(StateTransitionedArgs<TTrigger, TState> args) {
    }

    @Override
    public void onActivatingBehavior(ActivatingBehaviorArgs<TTrigger, TState> args) {
    }

    @Override
    public void onActivatedBehavior(ActivatedBehaviorArgs<TTrigger, TState> args) {
    }

    @Override
    public void onDeactivatedBehavior(DeactivatedBehaviorArgs<TTrigger, TState> args) {
    }

    @Override
    public void onException(ExceptionArgs<TTrigger, TState> args) {
    }

    @Override
    public void onStoppedTransition(StoppedTransitionArgs<TTrigger, TState> args) {
    }
}
