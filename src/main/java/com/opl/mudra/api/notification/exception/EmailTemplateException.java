package com.opl.mudra.api.notification.exception;

/**
 * @author Sanket
 *
 */
public class EmailTemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailTemplateException() {
	}

	public EmailTemplateException(String message) {
		super(message);
	}

	public EmailTemplateException(Throwable cause) {
		super(cause);
	}

	public EmailTemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailTemplateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
