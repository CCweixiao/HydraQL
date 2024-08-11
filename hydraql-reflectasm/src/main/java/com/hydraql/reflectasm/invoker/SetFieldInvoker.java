package com.hydraql.reflectasm.invoker;

import com.hydraql.reflectasm.Reflector;
import com.hydraql.reflectasm.exceptions.ReflectionException;

import java.lang.reflect.Field;

/**
 * @author leojie@apache.org 2024/8/11 18:07
 */
public class SetFieldInvoker implements Invoker {
    private final Field field;

    public SetFieldInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) {
        try {
            field.set(target, args[0]);
        } catch (IllegalAccessException e) {
            if (!Reflector.canControlMemberAccessible()) {
                throw new ReflectionException(e);
            }
            field.setAccessible(true);
            try {
                field.set(target, args[0]);
            } catch (IllegalAccessException ex) {
                throw new ReflectionException(ex);
            }
        }
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
