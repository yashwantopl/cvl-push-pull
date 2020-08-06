package com.opl.mudra.api.gst.exception;

public class GstException extends Exception{

	private static final long serialVersionUID = 1L;

	private static final String CALL = ":: Call ";
	private static final String EXCEPTION = " :: Exception while calling :: ";
	public GstException() {
		super();
	}

	public GstException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public GstException(String message, Throwable cause) {
		super(message, cause);
	}

	public GstException(String message) {
		super(message);
	}

	public GstException(Throwable cause) {
		super(cause);
	}
	
	public static String startCallingMethodLog(String methodName) {
		return CALL +methodName +"() Started ::";
	}

	public static String startCallingMethodLog(String methodName,String param) {
		return CALL +methodName +"() Started :: " + param + " ::";
	}
	
	public static String startCallingMethodLog(String methodName,Long applicationId,Long userId,String gstin) {
		return CALL + methodName + "() Started :: applicationId:" + applicationId + " | gstin:" + gstin + " | userId:" + userId + " ::";
	}
	
	public static String startCallingMethodLog(String methodName,Long fpUserId,Long fsUserId) {		
		return CALL + methodName + "() Started :: fpUserId:" + fpUserId + " | fs User Id :" + fsUserId  + " ::";
	}
	
	public static String startCallingMethodLog(String methodName,String gstin,Long fpUserId,Long fsUserId) {
		return CALL +methodName +"() Started :: gstin:" + gstin + " | fpUserId:" + fpUserId + " | fsUserId :" + fsUserId + " ::";		
	}
	
	public static String endCallingMethodLog(String methodName) {
		return CALL +methodName +"() Ended ::";
	}
	
	public static String errorLog(String methodName) {
		return EXCEPTION +methodName +"() ::";
	}
	
	public static String errorLog(String methodName,Long applicationId,Long userId,String gstin) {
		return EXCEPTION +methodName +"() :: applicationId:" + applicationId + " | gstin:" + gstin + " | userId:" + userId + " ::";
	}
	
	public static String errorLog(String methodName,Long fpUserId,Long fsUserId) {
		return EXCEPTION +methodName +"() :: fpUserId:" + fpUserId + " | fsUserId:" + fsUserId + " ::";
	}
	
	public static String errorLog(String methodName,String gstin,Long fpUserId,Long fsUserId) {
		return EXCEPTION +methodName +"() :: gstin:" + gstin + " | fpUserId:" + fpUserId + " | fsUserId:" + fsUserId + " ::";
	}
	
	public static String errorLog(String methodName,String param) {
		return EXCEPTION +methodName +"() :: " + param + " ::";
	}

}
