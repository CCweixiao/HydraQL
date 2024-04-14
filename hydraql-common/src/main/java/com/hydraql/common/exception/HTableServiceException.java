package com.hydraql.common.exception;

/**
 * @author leojie@apache.org 2024/4/7 20:49
 */
public class HTableServiceException extends HydraQLBaseException {
    private static final long serialVersionUID = -267537227887868439L;

    public HTableServiceException(String message) {
        super(message);
    }

    public HTableServiceException(Throwable cause) {
        super(cause);
    }

    public HTableServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
