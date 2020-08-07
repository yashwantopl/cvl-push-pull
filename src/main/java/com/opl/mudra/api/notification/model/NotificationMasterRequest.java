package com.opl.mudra.api.notification.model;

import java.util.Date;

public class NotificationMasterRequest {

	private Long masterId;
	
	private String notificationName;
	
	private String notificationDesc;
	
	private Boolean isEditable;
	
	private Boolean isActive;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Long modifiedBy;
	
	private Long createdBy;

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public String getNotificationDesc() {
		return notificationDesc;
	}

	public void setNotificationDesc(String notificationDesc) {
		this.notificationDesc = notificationDesc;
	}

	public Boolean getIsEditable() {
		return isEditable;
	}

	public void setIsEditable(Boolean isEditable) {
		this.isEditable = isEditable;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "NotificationMasterRequest [masterId=" + masterId + ", notificationName=" + notificationName
				+ ", notificationDesc=" + notificationDesc + ", isEditable=" + isEditable + ", isActive=" + isActive
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy
				+ ", createdBy=" + createdBy + "]";
	}
}
