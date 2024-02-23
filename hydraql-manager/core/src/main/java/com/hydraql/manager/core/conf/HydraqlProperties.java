package com.hydraql.manager.core.conf;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author leojie 2024/2/23 13:38
 */
public class HydraqlProperties {
    private final ConcurrentHashMap<PropertyKey, Optional<Object>> mUserProps = new ConcurrentHashMap<>();

    public HydraqlProperties() {
    }

    public HydraqlProperties(HydraqlProperties hydraqlProperties) {
        mUserProps.putAll(hydraqlProperties.mUserProps);
    }

    /**
     * @param key the key to query
     * @return the value, or null if the key has no value set
     */
    public Object get(PropertyKey key) {
        if (mUserProps.containsKey(key)) {
            return mUserProps.get(key).orElse(null);
        }
        // In case key is not the reference to the original key
        return PropertyKey.fromString(key.toString()).getDefaultValue();
    }

    public void set(PropertyKey key, Object value) {
        mUserProps.putIfAbsent(key, Optional.ofNullable(value));
    }

    public boolean isSet(PropertyKey key) {
        if (mUserProps.containsKey(key)) {
            Optional<Object> val = mUserProps.get(key);
            if (val.isPresent()) {
                return true;
            }
        }
        return PropertyKey.fromString(key.toString()).getDefaultValue() != null;
    }

    public Set<PropertyKey> keySet() {
        Set<PropertyKey> keySet = new HashSet<>(PropertyKey.defaultKeys());
        keySet.addAll(mUserProps.keySet());
        return Collections.unmodifiableSet(keySet);
    }
}
