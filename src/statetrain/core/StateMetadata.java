package statetrain.core;

import java.util.HashMap;
import java.util.Map;

public class StateMetadata {
    private Map<String, Object> systemMetadata = new HashMap<>();
    private Map<String, Object> applicationMetadata = new HashMap<>();

    public Map<String, Object> getSystemMetadata() {
        return systemMetadata;
    }

    public Map<String, Object> getApplicationMetadata() {
        return applicationMetadata;
    }

    @Override
    public String toString() {
        return "StateMetadata{" +
                "systemMetadata=" + systemMetadata +
                ", applicationMetadata=" + applicationMetadata +
                '}';
    }
}
