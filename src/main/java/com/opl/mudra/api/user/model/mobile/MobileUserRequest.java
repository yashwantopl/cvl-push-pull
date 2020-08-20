package com.opl.mudra.api.user.model.mobile;

import java.io.Serializable;
import java.util.Date;

public class MobileUserRequest implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long userId;
	private Long userType;
	private Integer productId;
	private Date fromDate;
	private Date toDate;
	private String email;
	
	
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
	
	public MobileUserRequest(Long userType,Date fromDate,Date toDate) {
		super();
		this.userType = userType;
		this.fromDate = fromDate;
		this.toDate = toDate;
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

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
