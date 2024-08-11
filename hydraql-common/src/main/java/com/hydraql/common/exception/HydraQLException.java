package com.hydraql.common.exception;

/**
 * @author leojie@apache.org 2024/8/11 15:00
 */
public class HydraQLException extends RuntimeException {
    private static final long serialVersionUID = 6234561671799204669L;

    public HydraQLException() {
    }

    public HydraQLException(String message) {
        super(message);
    }

    public HydraQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public HydraQLException(Throwable cause) {
        super(cause);
    }
}
