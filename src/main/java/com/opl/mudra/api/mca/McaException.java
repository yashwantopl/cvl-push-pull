package com.opl.mudra.api.mca;

public class McaException extends Exception {

    private static final long serialVersionUID = 1L;

    private Integer statusCode;
    
    public McaException() {
        super();
    }

    public McaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public McaException(String message, Throwable cause) {
        super(message, cause);
    }

    public McaException(String message) {
        super(message);
    }
    public McaException(String message,Integer status) {
    	super(message);
    	this.statusCode = status;
    }

    public McaException(Throwable cause) {
        super(cause);
    }
    
    public McaException(Throwable cause,Integer statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
    
    
}
