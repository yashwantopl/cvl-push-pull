package com.opl.mudra.api.gst.model;

import java.io.Serializable;

public class GstinDetailsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String gstin;
	
	private Long applicationId;
	
	private String pan;
	
	private String orgName;
	
	private String stateName;
	
	private Boolean isGstNotApplicable;
	
	private Boolean isGstDataRetrieved;

	private Boolean isPrimary;
	private Long fpUserId;
	private Long fsUserId;
	private String gstType;
	private Integer amountOfLoan;
	
	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
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

	/**
	 * @return the isPrimary
	 */
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	/**
	 * @param isPrimary the isPrimary to set
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Long getFpUserId() {
		return fpUserId;
	}

	public void setFpUserId(Long fpUserId) {
		this.fpUserId = fpUserId;
	}

	public Long getFsUserId() {
		return fsUserId;
	}

	public void setFsUserId(Long fsUserId) {
		this.fsUserId = fsUserId;
	}
	public String getGstType() {
		return gstType;
	}

	public void setGstType(String gstType) {
		this.gstType = gstType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}

	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}
}
