package com.capitaworld.service.loans.model;

import java.io.Serializable;

public class NhbsApplicationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationStatusId;
	
	private Long userId;
	
	private Long userRoleId;

	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	
}
