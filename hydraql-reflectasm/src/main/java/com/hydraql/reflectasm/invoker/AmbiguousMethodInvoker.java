package com.hydraql.reflectasm.invoker;

import com.hydraql.reflectasm.MethodAccess;
import com.hydraql.reflectasm.exceptions.ReflectionException;

/**
 * @author leojie@apache.org 2024/8/11 14:56
 */
public class AmbiguousMethodInvoker extends MethodInvoker {
    private final String exceptionMessage;
    public AmbiguousMethodInvoker(MethodAccess methodAccess, int methodIndex, String exceptionMessage) {
        super(methodAccess, methodIndex);
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public Object invoke(Object target, Object[] args) {
        throw new ReflectionException(exceptionMessage);
    }
}
