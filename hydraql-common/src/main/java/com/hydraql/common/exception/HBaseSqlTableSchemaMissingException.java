package com.hydraql.common.exception;

/**
 * @author leojie 2022/12/3 00:32
 */
public class HBaseSqlTableSchemaMissingException extends HBaseSdkException {
    private static final long serialVersionUID = 1L;

    public HBaseSqlTableSchemaMissingException(String message) {
        super(message);
    }

    public HBaseSqlTableSchemaMissingException(Throwable cause) {
        super(cause);
    }

    public HBaseSqlTableSchemaMissingException(String message, Throwable cause) {
        super(message, cause);
    }
}
