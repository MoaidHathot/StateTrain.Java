package statetrain.core.behavior;

import statetrain.core.StateMachineContext;
import statetrain.core.State;
import statetrain.core.behavior.args.BehaviorActivatedArgs;
import statetrain.core.behavior.args.BehaviorActivatingArgs;
import statetrain.core.behavior.args.BehaviorDeactivatedArgs;
import statetrain.core.behavior.args.BehaviorTriggerTransitionArgs;
import statetrain.utils.timing.schedule.TaskContext;
import statetrain.utils.timing.schedule.TimerScheduler;

import java.time.Duration;
import java.util.Optional;

public class TimeoutTriggerTransitionBehavior<TTrigger, TState> extends BaseBehavior<TTrigger, TState> {
    private final Duration timeout;
    private final TTrigger timeoutTrigger;
    private final TimerScheduler timerScheduler;

    private TaskContext currentTimeoutContext;

    private final Object lock = new Object();

    public TimeoutTriggerTransitionBehavior(TState attachedState, Duration timeout, TTrigger timeoutTrigger, TimerScheduler timerScheduler) {
        super(attachedState);
        this.timeout = timeout;
        this.timeoutTrigger = timeoutTrigger;
        this.timerScheduler = timerScheduler;
    }

    @Override
    public void activating(BehaviorActivatingArgs<TTrigger, TState> args) {
    }

    @Override
    public void activated(BehaviorActivatedArgs<TTrigger, TState> args) {
        synchronized (lock){
            cancelTimeout();

            currentTimeoutContext = timerScheduler.schedule(() -> {
                boolean shouldTransition;
                synchronized (lock) {
                    shouldTransition = Optional.ofNullable(currentTimeoutContext).map(c -> !c.isCancelled()).orElse(false);
                    currentTimeoutContext = null;
                }

                if (shouldTransition) {
                    args.getContext().triggerTransition(timeoutTrigger);
                }
            }, timeout);
        }
    }

    @Override
    public void deactivated(BehaviorDeactivatedArgs<TTrigger, TState> args) {
        synchronized (lock){
            cancelTimeout();
        }
    }

    private void cancelTimeout(){
        if(null != currentTimeoutContext){
            currentTimeoutContext.cancel();
        }
    }

    @Override
    public TransitionResult<TState> triggerTransition(BehaviorTriggerTransitionArgs<TTrigger, TState> args) {
        synchronized (lock){
            cancelTimeout();
            return TransitionResult.noActionResult();
        }
    }

    @Override
    public void close() throws Exception {
        synchronized (lock){
            cancelTimeout();
        }
    }

    public Duration getTimeout() {
        return timeout;
    }

    public TTrigger getTimeoutTrigger() {
        return timeoutTrigger;
    }

    @Override
    public String toString() {
        return "TimeoutTransitionBehavior{" +
                "timeout=" + timeout +
                ", timeoutTrigger=" + timeoutTrigger +
                ", timerScheduler=" + timerScheduler +
                ", currentTimeoutContext=" + currentTimeoutContext +
                '}';
    }
}
