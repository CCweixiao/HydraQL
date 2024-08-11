package com.hydraql.reflectasm.invoker;

import com.hydraql.reflectasm.MethodAccess;

import java.lang.reflect.Method;

/**
 * @author leojie@apache.org 2024/8/11 14:41
 */
public class MethodInvoker implements Invoker {
    private final Class<?> type;
    private final MethodAccess methodAccess;
    private final int methodIndex;

    public MethodInvoker(MethodAccess methodAccess, int methodIndex) {
        this.methodAccess = methodAccess;
        this.methodIndex = methodIndex;
        Method method = methodAccess.getMethod(methodIndex);
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        } else {
            type = method.getReturnType();
        }
    }

    @Override
    public Object invoke(Object target, Object[] args) {
        return methodAccess.invoke(target, methodIndex, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
