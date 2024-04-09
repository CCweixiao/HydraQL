package com.hydraql.adapter.schema;

/**
 * @author leojie@apache.org 2024/4/9 15:33
 */
public interface HTableSchemaInterceptor {

    default void verifyKey(String key) {

    }

    default void verifyConfiguration(String key, String value) {
        if (configurationDisable()) {
            throw new RuntimeException("Configuration is not supported in the current version");
        }
    }

    default boolean configurationDisable() {
        return false;
    }
}
