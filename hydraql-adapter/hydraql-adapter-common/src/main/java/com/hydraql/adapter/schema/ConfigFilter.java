package com.hydraql.adapter.schema;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author leojie@apache.org 2024/7/12 22:38
 */
public interface ConfigFilter {
    default boolean intercept(String key, Object value) {
        return intercept(key, value, true, false);
    }

    default boolean intercept(String key, Object value, boolean checkConfig) {
        return intercept(key, value, false, checkConfig);
    }

    default boolean intercept(String key, Object value, boolean checkValue, boolean checkConfig) {
        if (StringUtils.isBlank(key) || value == null) {
            return true;
        }

        if (checkConfig) {
            if (this.ignoreConfig(key)) {
                return true;
            }
        }

        if (checkValue) {
            if (this.ignoreValue(key)) {
                return true;
            }
        }

        if (value instanceof String && StringUtils.isBlank((String) value)) {
            return true;
        }

        String newVal;
        if (value instanceof String) {
            newVal = value.toString();
        } else if (value instanceof Integer) {
            newVal = value.toString();
        } else if (value instanceof Boolean) {
            newVal = value.toString();
        } else if (value instanceof Long) {
            newVal = value.toString();
        } else if (value instanceof Float) {
            newVal = value.toString();
        } else if (value instanceof Double) {
            newVal = value.toString();
        } else if (value instanceof Short) {
            newVal = value.toString();
        } else {
            throw new IllegalStateException("Illegal value type " + value.getClass());
        }
        String defaultValue = this.defaultValues().get(key);
        return defaultValue != null && defaultValue.equals(newVal);
    }

    /**
     * Unsupported config or not
     *
     * @param key config key
     * @return true or false
     */
    default boolean ignoreConfig(String key) {
        return false;
    }

    /**
     * Unsupported values or not
     *
     * @param key config key
     * @return true or false
     */
    default boolean ignoreValue(String key) {
        return false;
    }

    Map<String, String> defaultValues();
}
