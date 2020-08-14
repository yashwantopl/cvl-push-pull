package com.opl.mudra.api.user.model;

public class FpProfileBasicDetailRequest {

	private Long userId;
	
	private String organizationName;
	
	

	public FpProfileBasicDetailRequest(Long userId, String organizationName) {
		super();
		this.userId = userId;
		this.organizationName = organizationName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	
}
