package statetrain.core;

import java.util.Map;

public class TransitionArgs<TTrigger, TState> {
    private final Map<Object, Object> properties;

    public TransitionArgs(Map<Object, Object> properties) {
        this.properties = properties;
    }

    public Map<Object, Object> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return "TransitionMetadata{" +
                "properties=" + properties +
                '}';
    }
}
