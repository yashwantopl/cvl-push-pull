package com.opl.mudra.api.auth.model;

public class AuthClientResponse {

	private Long userId;
	private String message;
	private Integer status;
	private boolean isAuthenticate;
	private Long userType;
	private Long userOrgId;

	public AuthClientResponse() {

	}
	
	public AuthClientResponse(Integer status,String message) {
		this.status = status;
		this.message = message;
	}

	public AuthClientResponse(Long userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isAuthenticate() {
		return isAuthenticate;
	}

	public void setAuthenticate(boolean isAuthenticate) {
		this.isAuthenticate = isAuthenticate;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	
	

}
