package com.hydraql.common.exception;

/**
 * @author leojie@apache.org 2024/4/3 22:11
 */
public class InvalidTableModelClassException extends RuntimeException {
    private static final long serialVersionUID = 7349010571856930073L;

    public InvalidTableModelClassException() {
    }

    public InvalidTableModelClassException(String message) {
        super(message);
    }

    public InvalidTableModelClassException(Throwable cause) {
        super(cause);
    }

    public InvalidTableModelClassException(String message, Throwable cause) {
        super(message, cause);
    }
}
