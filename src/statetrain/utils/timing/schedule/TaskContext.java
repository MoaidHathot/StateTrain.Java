package statetrain.utils.timing.schedule;

import java.util.Date;

public class TaskContext {

    private boolean isCancelled;
    private boolean isRanToCompletion;
    private Date startedDate;

    public TaskContext(){
        startedDate = new Date();
    }

    public synchronized boolean cancel(){
        if(isResolved()){
            return false;
        }

        if(!isRanToCompletion()){
            isCancelled = true;
        }

        return isCancelled;
    }

    synchronized boolean complete(){
        if(isResolved()){
            return false;
        }

        if(!isCancelled()){
            isRanToCompletion = true;
        }

        return isRanToCompletion;
    }

    synchronized boolean callIfNotResolved(Runnable runnable){
        if(!isResolved()){
            runnable.run();
            return true;
        }

        return false;
    }

    synchronized boolean completeAndCall(Runnable runnable, boolean callOnlyIfCanComplete){

        if(complete() || (isRanToCompletion() && !callOnlyIfCanComplete)){
            runnable.run();
            return true;
        }

        return false;
    }

    public synchronized boolean isResolved(){
        return isCancelled || isRanToCompletion;
    }

    public synchronized boolean isCancelled() {
        return isCancelled;
    }

    public synchronized boolean isRanToCompletion() {
        return isRanToCompletion;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    @Override
    public String toString() {
        return "TaskContext{" +
                "isCancelled=" + isCancelled() +
                ", isRanToCompletion=" + isRanToCompletion() +
                ", startedDate=" + getStartedDate() +
                '}';
    }
}
