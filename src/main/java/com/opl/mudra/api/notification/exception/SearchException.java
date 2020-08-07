package com.opl.mudra.api.notification.exception;
/**
 * @author Sanket
 *
 */
public class SearchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchException() {
	}

	public SearchException(String message) {
		super(message);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}

	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
