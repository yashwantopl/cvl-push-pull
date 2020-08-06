package com.opl.mudra.api.gst.model;

import java.util.Date;

public class GstProfileMappingResponse {
	
	
	private Long gstId;
	
	private String pan;
	
	private  Long profileId;
	
	private Long userId;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Boolean isActive;
	
	private Boolean isProcessComp;

	public Long getGstId() {
		return gstId;
	}

	public void setGstId(Long gstId) {
		this.gstId = gstId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsProcessComp() {
		return isProcessComp;
	}

	public void setIsProcessComp(Boolean isProcessComp) {
		this.isProcessComp = isProcessComp;
	}
}
