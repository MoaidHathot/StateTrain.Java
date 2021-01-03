package statetrain.utils.timing.schedule;

import java.time.Duration;

public interface ITaskScheduler extends AutoCloseable {
    TaskContext schedule(Runnable runnable, long milliseconds);
    TaskContext schedule(Runnable runnable, Duration duration);
}
