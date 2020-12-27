package com.github.CCweixiao.exception;

/**
 * @author leojie 2020/12/27 2:41 下午
 */
public class HBaseThriftConnectionException extends HBaseThriftException {

    private static final long serialVersionUID = 156113249897345550L;

    public HBaseThriftConnectionException(String message) {
        super(message);
    }

    public HBaseThriftConnectionException(Throwable cause) {
        super(cause);
    }

    public HBaseThriftConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
