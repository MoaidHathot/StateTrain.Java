package statetrain.core.event;

import statetrain.core.behavior.ImmediateStateTransitionBehavior;
import statetrain.core.event.args.*;

import java.util.Optional;
import java.util.function.Consumer;

public class SafeStateEventNotifier<TTrigger, TState> implements IStateEventNotifier<TTrigger, TState> {

    private final IStateEventNotifier<TTrigger, TState> innerNotifier;
    private final Consumer<Throwable> handleError;

    public SafeStateEventNotifier(IStateEventNotifier<TTrigger, TState> innerNotifier, Consumer<Throwable> handleError) {
        this.innerNotifier = Optional.ofNullable(innerNotifier).orElse(new NullStateEventNotifier<>());
        this.handleError = Optional.ofNullable(handleError).orElse(t -> {});
    }

    public SafeStateEventNotifier(IStateEventNotifier<TTrigger, TState> notifier){
        this(notifier, null);
    }

    @Override
    public void onInitialTransition(InitialTransitionArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onInitialTransition(args));
    }

    @Override
    public void onErrorTransition(ErrorTransitionArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onErrorTransition(args));
    }

    @Override
    public void onTriggerReceived(TriggerReceivedArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onTriggerReceived(args));
    }

    @Override
    public void onStateTransitioned(StateTransitionedArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onStateTransitioned(args));
    }

    @Override
    public void onActivatingBehavior(ActivatingBehaviorArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onActivatingBehavior(args));
    }

    @Override
    public void onActivatedBehavior(ActivatedBehaviorArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onActivatedBehavior(args));
    }

    @Override
    public void onDeactivatedBehavior(DeactivatedBehaviorArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onDeactivatedBehavior(args));
    }

    @Override
    public void onException(ExceptionArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onException(args));
    }

    @Override
    public void onStoppedTransition(StoppedTransitionArgs<TTrigger, TState> args) {
        callSafely(() -> innerNotifier.onStoppedTransition(args));
    }

    private void callSafely(Runnable runnable){
        try{
            runnable.run();
        }catch (Exception ex){
            this.handleError.accept(ex);
        }
    }
}
