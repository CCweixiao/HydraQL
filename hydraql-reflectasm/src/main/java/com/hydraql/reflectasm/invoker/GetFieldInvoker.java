package com.hydraql.reflectasm.invoker;

import com.hydraql.reflectasm.Reflector;
import com.hydraql.reflectasm.exceptions.ReflectionException;

import java.lang.reflect.Field;

/**
 * @author leojie@apache.org 2024/8/11 18:13
 */
public class GetFieldInvoker implements Invoker {
    private final Field field;

    public GetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            if (Reflector.canControlMemberAccessible()) {
                field.setAccessible(true);
                try {
                    return field.get(target);
                } catch (IllegalAccessException ex) {
                    throw new ReflectionException(ex);
                }
            }
            throw new ReflectionException(e);
        }
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
