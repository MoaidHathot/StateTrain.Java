package statetrain.core.behavior;

public class TransitionResult<TState> {

    private final TState result;

    private TransitionResult(TState result) {
        this.result = result;
    }

    public boolean actionable(){
        return null != result;
    }

    public TState getResult() {
        return result;
    }

    public static <TState> TransitionResult<TState> noActionResult(){
        return new TransitionResult<>(null);
    }

    public static <TState> TransitionResult<TState> stateTransitionResult(TState state){
        return new TransitionResult<TState>(state);
    }

    @Override
    public String toString() {
        return "TransitionResult{" +
                ", result=" + result +
                '}';
    }
}
