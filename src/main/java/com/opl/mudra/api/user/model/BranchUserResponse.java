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
	private String email;
	private String mobile;
	
	private String firstName;
	private String lastName;
	

	@Override
	public String toString() {
		return "BranchUserResponse [userId=" + userId + ", userName=" + userName + ", isActive=" + isActive
				+ ", userRole=" + userRole + ", email=" + email + ", mobile=" + mobile + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}