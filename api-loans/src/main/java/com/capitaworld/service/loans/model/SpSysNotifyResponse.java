package com.capitaworld.service.loans.model;

import java.sql.Timestamp;

/**
 * @author sanket
 *
 */
public class SpSysNotifyResponse {
	

	private Long id;
	private String value;
	private Long productId;
	private Long applicationId;
	private Long userId;
	private Timestamp appCreateDate;
	public Long getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	public Long getProductId() {
		return productId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public Long getUserId() {
		return userId;
	}
	public Timestamp getAppCreateDate() {
		return appCreateDate;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setAppCreateDate(Timestamp appCreateDate) {
		this.appCreateDate = appCreateDate;
	}
	
	

}
