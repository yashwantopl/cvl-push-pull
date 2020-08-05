package com.opl.mudra.api.connect.exception;

/**
 * Created by pooja.patel on 28-12-2018.
 */

public class ConnectException extends Exception{

    private static final long serialVersionUID = 1L;

    public ConnectException() {
        super();
    }

    public ConnectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }


}
