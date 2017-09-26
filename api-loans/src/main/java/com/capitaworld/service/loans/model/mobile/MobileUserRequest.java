package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;

public class MobileUserRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long userId;
	private Long userType;
	private Integer productId;
	
	public MobileUserRequest() {
		
	}
	
	public MobileUserRequest(Long userId, Long userType) {
		super();
		this.userId = userId;
		this.userType = userType;
	}
	public MobileUserRequest(Long applicationId, Long userId, Long userType) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.userType = userType;
	}
	public MobileUserRequest(Long applicationId, Long userId, Long userType,Integer productId) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.userType = userType;
		this.productId = productId;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserType() {
		return userType;
	}
	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
	
}
