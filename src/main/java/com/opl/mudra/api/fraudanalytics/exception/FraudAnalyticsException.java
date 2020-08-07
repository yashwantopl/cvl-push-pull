package com.opl.mudra.api.fraudanalytics.exception;

public class FraudAnalyticsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FraudAnalyticsException() {
		super();
	}

	public FraudAnalyticsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FraudAnalyticsException(String message, Throwable cause) {
		super(message, cause);
	}

	public FraudAnalyticsException(String message) {
		super(message);
	}

	public FraudAnalyticsException(Throwable cause) {
		super(cause);
	}

	
	
}
