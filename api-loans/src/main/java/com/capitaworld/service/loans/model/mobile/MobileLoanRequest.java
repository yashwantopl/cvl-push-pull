package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;
import java.util.Date;

public class MobileLoanRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long userId;
	private Long userType;
	private Long id;
	private Integer productId;
	private Date fromDate;
	private Date toDate;
	private Integer businessTypeId;
	
	
	public MobileLoanRequest() {
		
	}
	
	public MobileLoanRequest(Long userId, Long userType) {
		super();
		this.userId = userId;
		this.userType = userType;
	}
	public MobileLoanRequest(Long applicationId, Long userId, Long userType) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.userType = userType;
	}
	public MobileLoanRequest(Long applicationId, Long userId, Long userType,Integer productId) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.userType = userType;
		this.productId = productId;
	}
	
	public MobileLoanRequest(Long applicationId, Long userId, Long userType,Integer productId,Integer businessTypeId) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.userType = userType;
		this.productId = productId;
		this.businessTypeId = businessTypeId;
	}
	
	public MobileLoanRequest(Long userType,Date fromDate,Date toDate) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
}
