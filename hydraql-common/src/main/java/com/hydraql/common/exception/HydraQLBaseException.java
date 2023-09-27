package com.hydraql.common.exception;

/**
 * @author leo.jie (leojie1314@gmail.com)
 */
public class HydraQLBaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public HydraQLBaseException(String message) {
        super(message);
    }

    public HydraQLBaseException(Throwable cause) {
        super(cause);
    }

    public HydraQLBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
