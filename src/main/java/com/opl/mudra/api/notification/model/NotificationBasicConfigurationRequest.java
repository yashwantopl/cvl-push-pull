package com.opl.mudra.api.notification.model;

import java.util.Date;

/**
 * @author maaz.shaikh
 *
 */
public class NotificationBasicConfigurationRequest {
	
	private Long id;
	private String propName;
	private Object propValue;
	private Date createdDate;
	private Boolean isForRetail;
	private Boolean isActive;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public Object getPropValue() {
		return propValue;
	}
	public void setPropValue(Object propValue) {
		this.propValue = propValue;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsForRetail() {
		return isForRetail;
	}
	public void setIsForRetail(Boolean isForRetail) {
		this.isForRetail = isForRetail;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "NotificationBasicConfigurationRequest [id=" + id + ", propName=" + propName + ", propValue=" + propValue
				+ ", createdDate=" + createdDate + ", isForRetail=" + isForRetail + ", isActive=" + isActive + "]";
	}

}
