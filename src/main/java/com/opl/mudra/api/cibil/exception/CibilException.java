package com.opl.mudra.api.cibil.exception;

public class CibilException extends Exception {
    private static final long serialVersionUID = 1L;

    public CibilException() {
        super();
    }

    public CibilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CibilException(String message, Throwable cause) {
        super(message, cause);
    }

    public CibilException(String message) {
        super(message);
    }

    public CibilException(Throwable cause) {
        super(cause);
    }
}
