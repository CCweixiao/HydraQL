package com.hydraql.common.exception;

/**
 * @author leojie 2021/2/9 10:50 下午
 */
public class HBaseQueryParamsException extends HBaseSdkException {

    private static final long serialVersionUID = 1L;

    public HBaseQueryParamsException(String message) {
        super(message);
    }

    public HBaseQueryParamsException(Throwable cause) {
        super(cause);
    }

    public HBaseQueryParamsException(String message, Throwable cause) {
        super(message, cause);
    }
}
