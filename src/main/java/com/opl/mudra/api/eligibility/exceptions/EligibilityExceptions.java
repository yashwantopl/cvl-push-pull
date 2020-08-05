package com.opl.mudra.api.eligibility.exceptions;

public class EligibilityExceptions extends Exception{
	
	private static final long serialVersionUID = 1L;

	public EligibilityExceptions() {
		super();
	}

	public EligibilityExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EligibilityExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	public EligibilityExceptions(String message) {
		super(message);
	}

	public EligibilityExceptions(Throwable cause) {
		super(cause);
	}

}
