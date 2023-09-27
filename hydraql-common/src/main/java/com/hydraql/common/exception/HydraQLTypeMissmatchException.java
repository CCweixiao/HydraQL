package com.hydraql.common.exception;

/**
 * @author leojie 2023/9/27 14:06
 */
public class HydraQLTypeMissmatchException extends HydraQLBaseException {
    private static final long serialVersionUID = 1L;
    public HydraQLTypeMissmatchException(String message) {
        super(message);
    }

    public HydraQLTypeMissmatchException(Throwable cause) {
        super(cause);
    }

    public HydraQLTypeMissmatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
