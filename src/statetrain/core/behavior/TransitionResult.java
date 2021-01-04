package statetrain.core.behavior;

public class TransitionResult<TState> {

    private final TState result;
    private final boolean stopTransition;

    public TransitionResult(TState result, boolean stopTransition) {
        this.result = result;
        this.stopTransition = stopTransition;
    }

    public boolean stopTransition() {
        return stopTransition;
    }

    public boolean actionable(){
        return null != result;
    }

    public TState getResult() {
        return result;
    }

    public static <TState> TransitionResult<TState> noActionResult(){
        return new TransitionResult<>(null, false);
    }

    public static <TState> TransitionResult<TState> stateTransitionResult(TState state){
        return new TransitionResult<>(state, true);
    }

    public static <TState> TransitionResult<TState> stopTransitioningResult(){
        return new TransitionResult<>(null, true);
    }

    @Override
    public String toString() {
        return "TransitionResult{" +
                "result=" + result +
                ", stopTransition=" + stopTransition +
                '}';
    }
}
