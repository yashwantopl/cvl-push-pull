package com.opl.mudra.api.itr;

/**
 * Created by pooja.patel on 26-12-2018.
 */
public class ITRException extends Exception{

    private static final long serialVersionUID = 1L;

    public ITRException() {
        super();
    }

    public ITRException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ITRException(String message, Throwable cause) {
        super(message, cause);
    }

    public ITRException(String message) {
        super(message);
    }

    public ITRException(Throwable cause) {
        super(cause);
    }
}
