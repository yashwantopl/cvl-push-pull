package com.capitaworld.service.loans.exceptions;

public class LoansException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoansException() {
		super();
	}

	public LoansException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoansException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoansException(String message) {
		super(message);
	}

	public LoansException(Throwable cause) {
		super(cause);
	}

	
	
}
