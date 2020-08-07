package com.opl.mudra.api.thirdparty.exception;

public class ThirdpartyException extends Exception {
    private static final long serialVersionUID = 1L;

    public ThirdpartyException() {
        super();
    }

    public ThirdpartyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ThirdpartyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ThirdpartyException(String message) {
        super(message);
    }

    public ThirdpartyException(Throwable cause) {
        super(cause);
    }
}
