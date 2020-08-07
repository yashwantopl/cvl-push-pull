package com.opl.mudra.api.analyzer.model.yodlee;

public class Token {
private String cobrandSessionToken;
	
	private String userSessionToken;
	
	private String token;
	
	private ErrorInfo errorInfo;

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getCobrandSessionToken() {
		return cobrandSessionToken;
	}

	public void setCobrandSessionToken(String cobrandSessionToken) {
		this.cobrandSessionToken = cobrandSessionToken;
	}

	public String getUserSessionToken() {
		return userSessionToken;
	}

	public void setUserSessionToken(String userSessionToken) {
		this.userSessionToken = userSessionToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
