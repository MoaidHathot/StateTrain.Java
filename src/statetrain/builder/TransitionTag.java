package statetrain.builder;

import statetrain.core.behavior.TransitionResult;

public class TransitionTag {
    private final Object key;
    private final Object value;

    public TransitionTag(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public TransitionTag(Object key){
        this(key, key);
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TransitionTag{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
