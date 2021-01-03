package statetrain.utils.timing.schedule;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class TimerScheduler implements ITaskScheduler {

    private final Timer timer;

    public TimerScheduler(String name){
        timer = new Timer(name, true);
    }

    @Override
    public TaskContext schedule(Runnable runnable, long milliseconds) {
        final var context = new TaskContext();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                context.completeAndCall(runnable, true);
            }
        }, milliseconds);

        return context;
    }

    @Override
    public TaskContext schedule(Runnable runnable, Duration duration) {
        return schedule(runnable, duration.toMillis());
    }

    @Override
    public void close() throws Exception {
        timer.cancel();
    }

    @Override
    public String toString() {
        return "TimerScheduler{" +
                "timer=" + timer +
                '}';
    }
}
