package com.opl.mudra.api.gst.model;

import java.sql.Timestamp;

public class ApplicationMasterResponse {
	
	private Long id;
	private Long applicationId;
	private Long userId;
	private String pan;
	private String gstin;
	private Boolean isActive;
	private Boolean isGstNotApplicable;
	private Boolean isGstDataRetrieved;
	private Boolean isPanLinked;
	private Timestamp createdDate;
	private Timestamp modifiedDate;
	private String stateName;
	private String orgName;
	private Boolean isPrimary;
	private String gstStatus;
	private String gstType;
	private Boolean isManualGst;
	private Boolean isGstDataUpdated;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsGstNotApplicable() {
		return isGstNotApplicable;
	}
	public void setIsGstNotApplicable(Boolean isGstNotApplicable) {
		this.isGstNotApplicable = isGstNotApplicable;
	}
	public Boolean getIsGstDataRetrieved() {
		return isGstDataRetrieved;
	}
	public void setIsGstDataRetrieved(Boolean isGstDataRetrieved) {
		this.isGstDataRetrieved = isGstDataRetrieved;
	}
	public Boolean getIsPanLinked() {
		return isPanLinked;
	}
	public void setIsPanLinked(Boolean isPanLinked) {
		this.isPanLinked = isPanLinked;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Boolean getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	public String getGstStatus() {
		return gstStatus;
	}
	public void setGstStatus(String gstStatus) {
		this.gstStatus = gstStatus;
	}
	public String getGstType() {
		return gstType;
	}
	public void setGstType(String gstType) {
		this.gstType = gstType;
	}
	public Boolean getIsManualGst() {
		return isManualGst;
	}
	public void setIsManualGst(Boolean isManualGst) {
		this.isManualGst = isManualGst;
	}
	public Boolean getIsGstDataUpdated() {
		return isGstDataUpdated;
	}
	public void setIsGstDataUpdated(Boolean isGstDataUpdated) {
		this.isGstDataUpdated = isGstDataUpdated;
	}
}
