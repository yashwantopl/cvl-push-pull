package com.opl.mudra.api.gst.model;

import java.util.Date;

public class KeyPersonDetailsResponse {
	
	private Long id;
	
	private Long gstId;
	
	private Long profileId;
	
	private Long userId;
	
	private String pan;
	
	private String entityName;
	
	private String gstin;
	
	private Date dob;
	
	private Long constitutionId;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGstId() {
		return gstId;
	}

	public void setGstId(Long gstId) {
		this.gstId = gstId;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Long constitutionId) {
		this.constitutionId = constitutionId;
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

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
}
