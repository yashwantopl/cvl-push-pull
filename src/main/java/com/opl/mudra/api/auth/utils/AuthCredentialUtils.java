package com.opl.mudra.api.auth.utils;

import java.util.Collection;
import java.util.UUID;

public class AuthCredentialUtils {

	public static final String GRANT_TYPE = "password";
	public static final String CLIENT_ID = "cw";
	public static final String CLIENT_SECRET = "cw";
//	public static final String OAUTH_SERVER_URI = "http://192.168.1.112:8052/capitaworld";
	public static final String OAUTH_TOKEN_URI = "/oauth/token";
	public static final String OAUTH_CHECK_TOKEN_URI = "/oauth/check_token";
	
	public static final String REQUEST_HEADER_ACCESS_TOKEN = "tk_ac";
	public static final String REQUEST_HEADER_USERNAME = "ur_cu";
	public static final String REQUEST_HEADER_REFRESH_TOKEN = "tk_rc";
	public static final String REQUEST_HEADER_LOGIN_TOKEN = "tk_lg";
	public static final String REQUEST_HEADER_AUTHENTICATE = "req_auth";
	public static final String MOBILE_REQUEST = "appreq";
	public static final String TOKEN_REQ_KEY = "AHDU32422KNDKWE90987";
	public static final String LOGIN_KEY = "5b2d175a8e627284fda9f6d5ff9d732c";
	
	public static final int OTP_REQUEST_LOGIN_TYPE = 2;
	
	public static String generateRandomToken() {
        return UUID.randomUUID().toString();
    }
	
	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}
	
	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}
	

	public final class AppRequestHeader
	{
		private AppRequestHeader(){
			//Nothing to do for X and Y
		}
		public static final String MOBILE_NO = "mobile";
		public static final String MODEL_NO = "model";
		public static final String MOBILE_OS = "os";
		public static final String OS_VERSION = "osvrsn";
		public static final String IMEI_NO = "imei";
		public static final String REQUEST_NO = "req";
		public static final String AUTH_TOKEN = "tk";
	}
	
}
