package com.opl.mudra.api.oneform.exception;

public class OneFormException extends Exception {
    private static final long serialVersionUID = 1L;

    public OneFormException() {
        super();
    }

    public OneFormException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OneFormException(String message, Throwable cause) {
        super(message, cause);
    }

    public OneFormException(String message) {
        super(message);
    }

    public OneFormException(Throwable cause) {
        super(cause);
    }

}
