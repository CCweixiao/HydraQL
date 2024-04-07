package com.hydraql.common.exception;

/**
 * @author leojie@apache.org 2024/4/7 20:49
 */
public class HydraQLTableOpException extends HydraQLBaseException {
    private static final long serialVersionUID = -7488847238592258252L;

    public HydraQLTableOpException(String message) {
        super(message);
    }

    public HydraQLTableOpException(Throwable cause) {
        super(cause);
    }

    public HydraQLTableOpException(String message, Throwable cause) {
        super(message, cause);
    }
}
