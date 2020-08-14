package com.opl.mudra.api.user.model;

import java.io.Serializable;

public class BranchUserResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String isActive;
	private String userRole;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}