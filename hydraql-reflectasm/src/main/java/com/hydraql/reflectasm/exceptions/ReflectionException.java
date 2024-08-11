package com.hydraql.reflectasm.exceptions;

/**
 * @author leojie@apache.org 2024/8/11 14:59
 */
public class ReflectionException extends RuntimeException {
    private static final long serialVersionUID = 3566074081936582135L;

    public ReflectionException() {
    }

    public ReflectionException(String message) {
        super(message);
    }

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionException(Throwable cause) {
        super(cause);
    }
}
