package com.hydraql.common.exception;

/**
 * @author leojie 2021/2/9 10:50 下午
 */
public class HydraQLConnectionException extends HydraQLBaseException {
    private static final long serialVersionUID = -6323264432916489904L;

    public HydraQLConnectionException(String message) {
        super(message);
    }

    public HydraQLConnectionException(Throwable cause) {
        super(cause);
    }

    public HydraQLConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
