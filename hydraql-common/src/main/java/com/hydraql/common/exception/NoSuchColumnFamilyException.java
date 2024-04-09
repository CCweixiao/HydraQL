package com.hydraql.common.exception;

/**
 * @author leojie 2022/12/3 00:32
 */
public class NoSuchColumnFamilyException extends RuntimeException {

    private static final long serialVersionUID = -3616379064813648963L;

    public NoSuchColumnFamilyException(String message) {
        super(message);
    }

    public NoSuchColumnFamilyException(Throwable cause) {
        super(cause);
    }

    public NoSuchColumnFamilyException(String message, Throwable cause) {
        super(message, cause);
    }
}
