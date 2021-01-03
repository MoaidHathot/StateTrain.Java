package statetrain.core;

import statetrain.core.behavior.IBehavior;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class State<TTrigger, TState> implements AutoCloseable {

    private final TState state;
    private final String name;
    private final Map<TTrigger, TState> stateMap;
    private final StateMetadata stateMetadata;
    private List<IBehavior<TTrigger, TState>> behaviors;

    public State(TState state, String name, StateMetadata stateMetadata, Map<TTrigger, TState> stateMap, List<IBehavior<TTrigger, TState>> behaviors) {

        this.state = state;
        this.name = name;
        this.stateMetadata = stateMetadata;
        this.stateMap = stateMap;
        this.behaviors = behaviors;
    }

    public State(TState state, StateMetadata stateMetadata, Map<TTrigger, TState> stateMap, List<IBehavior<TTrigger, TState>> behaviors) {
        this(state, state.toString(), stateMetadata, stateMap, behaviors);
    }

    public TState getState() {
        return state;
    }

    public StateMetadata getStateMetadata() {
        return stateMetadata;
    }

    public String getName() {
        return name;
    }

    public Map<TTrigger, TState> getStateMap() {
        return stateMap;
    }

    public List<IBehavior<TTrigger, TState>> getBehaviors() {
        return behaviors;
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + state +
                ", name='" + name + '\'' +
                ", states=" + stateMap +
                ", stateMetadata=" + stateMetadata +
                ", behaviors=" + behaviors +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof State)) {
            return false;
        }
        State<?, ?> other = (State<?, ?>) o;
        return Objects.equals(state, other.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public void close() throws Exception {
        if(null != behaviors){
            behaviors.forEach(b -> {
                try {
                    b.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
