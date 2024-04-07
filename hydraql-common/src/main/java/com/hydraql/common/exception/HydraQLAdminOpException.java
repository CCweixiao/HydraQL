package com.hydraql.common.exception;

/**
 * @author leojie@apache.org 2024/4/7 20:49
 */
public class HydraQLAdminOpException extends HydraQLBaseException{
    private static final long serialVersionUID = -5014694505768401570L;

    public HydraQLAdminOpException(String message) {
        super(message);
    }

    public HydraQLAdminOpException(Throwable cause) {
        super(cause);
    }

    public HydraQLAdminOpException(String message, Throwable cause) {
        super(message, cause);
    }
}
