package com.hydraql.reflectasm.invoker;

/**
 * @author leojie@apache.org 2024/8/11 14:14
 */
public interface Invoker {
    Object invoke(Object target, Object[] args);

    Class<?> getType();
}
