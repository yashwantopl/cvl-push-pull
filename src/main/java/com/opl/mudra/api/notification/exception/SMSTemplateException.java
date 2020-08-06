package com.opl.mudra.api.notification.exception;

/**
 * @author Sanket
 *
 */
public class SMSTemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SMSTemplateException() {
	}

	public SMSTemplateException(String message) {
		super(message);
	}

	public SMSTemplateException(Throwable cause) {
		super(cause);
	}

	public SMSTemplateException(String message, Throwable cause) {
		super(message, cause);
	}

	public SMSTemplateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
