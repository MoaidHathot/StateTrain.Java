package statetrain.core.event;

import statetrain.core.event.args.*;

import java.util.List;

public class CompositeStateEventNotifier<TTrigger, TState> implements IStateEventNotifier<TTrigger, TState> {

    private final List<IStateEventNotifier<TTrigger, TState>> notifierList;

    public CompositeStateEventNotifier(List<IStateEventNotifier<TTrigger, TState>> notifierList) {

        if(null == notifierList){
            throw new IllegalArgumentException("State event notifier list can't be null");
        }

        this.notifierList = notifierList;
    }

    @Override
    public void onInitialTransition(InitialTransitionArgs args) {
        notifierList.forEach(n -> n.onInitialTransition(args));
    }

    @Override
    public void onErrorTransition(ErrorTransitionArgs args) {
        notifierList.forEach(n -> n.onErrorTransition(args));
    }

    @Override
    public void onTriggerReceived(TriggerReceivedArgs args) {
        notifierList.forEach(n -> n.onTriggerReceived(args));
    }

    @Override
    public void onStateTransitioned(StateTransitionedArgs args) {
        notifierList.forEach(n -> n.onStateTransitioned(args));
    }

    @Override
    public void onActivatingBehavior(ActivatingBehaviorArgs args) {
        notifierList.forEach(n -> n.onActivatingBehavior(args));
    }

    @Override
    public void onActivatedBehavior(ActivatedBehaviorArgs args) {
        notifierList.forEach(n -> n.onActivatedBehavior(args));
    }

    @Override
    public void onDeactivatedBehavior(DeactivatedBehaviorArgs args) {
        notifierList.forEach(n -> n.onDeactivatedBehavior(args));
    }

    @Override
    public void onException(ExceptionArgs args) {
        notifierList.forEach(n -> n.onException(args));
    }

    @Override
    public void onStoppedTransition(StoppedTransitionArgs<TTrigger, TState> args) {
        notifierList.forEach(n -> n.onStoppedTransition(args));
    }
}
