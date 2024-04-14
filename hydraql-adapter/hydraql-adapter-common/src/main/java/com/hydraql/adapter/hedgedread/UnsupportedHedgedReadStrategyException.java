package com.hydraql.adapter.hedgedread;

/**
 * @author leojie@apache.org 2024/4/14 21:05
 */
public class UnsupportedHedgedReadStrategyException extends RuntimeException {

    private static final long serialVersionUID = 1607940322689035520L;

    public UnsupportedHedgedReadStrategyException(String message) {
        super(message);
    }

    public UnsupportedHedgedReadStrategyException(Throwable cause) {
        super(cause);
    }

    public UnsupportedHedgedReadStrategyException(String message, Throwable cause) {
        super(message, cause);
    }
}
