package com.opl.mudra.api.notification.model;

import java.sql.Timestamp;

/**
 * @author Sanket
 *
 */
public class SysNotifyResponse {

	private Long id;
	private String value;
	private Long productId;
	private Long applicationId;
	private Long userId;
	private Timestamp appCreateDate;
	
	
	
	public Timestamp getAppCreateDate() {
		return appCreateDate;
	}
	public void setAppCreateDate(Timestamp appCreateDate) {
		this.appCreateDate = appCreateDate;
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
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
