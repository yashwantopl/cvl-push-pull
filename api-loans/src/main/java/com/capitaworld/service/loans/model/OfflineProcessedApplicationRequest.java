package com.capitaworld.service.loans.model;

import java.util.Date;

public class OfflineProcessedApplicationRequest {

	private Long applicationId;
	private Long userId;
	private Double loanAmount;
	private String organisationName;
	private String branchName;
	private String isCampaignUser;
	private Boolean isSanctioned;
	private Boolean isDisbursed;
	private Long branchId;
	private String pan;
	private String gstin;
	private String branchCode;
	private String branchAddress;
	private Object locationData;
	//SANCTIONED APPLICATION LIST
	private Double sanctionedAmount;
	private Date sanctionDate;
	private Double tenure;
	private String remark;
	private Double roi;
	private Double processingFee;
	private Long isSanctionedFrom;
	private Boolean isPartiallyDisbursedOffline;
	private String reason;
	private Date modifiedDate;
	
	
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
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIsCampaignUser() {
		return isCampaignUser;
	}
	public void setIsCampaignUser(String isCampaignUser) {
		this.isCampaignUser = isCampaignUser;
	}
	public Boolean getIsSanctioned() {
		return isSanctioned;
	}
	public void setIsSanctioned(Boolean isSanctioned) {
		this.isSanctioned = isSanctioned;
	}
	public Boolean getIsDisbursed() {
		return isDisbursed;
	}
	public void setIsDisbursed(Boolean isDisbursed) {
		this.isDisbursed = isDisbursed;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Double getSanctionedAmount() {
		return sanctionedAmount;
	}
	public void setSanctionedAmount(Double sanctionedAmount) {
		this.sanctionedAmount = sanctionedAmount;
	}
	public Date getSanctionDate() {
		return sanctionDate;
	}
	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}
	public Double getTenure() {
		return tenure;
	}
	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getRoi() {
		return roi;
	}
	public void setRoi(Double roi) {
		this.roi = roi;
	}
	public Double getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}
	public Long getIsSanctionedFrom() {
		return isSanctionedFrom;
	}
	public void setIsSanctionedFrom(Long isSanctionedFrom) {
		this.isSanctionedFrom = isSanctionedFrom;
	}
	public Boolean getIsPartiallyDisbursedOffline() {
		return isPartiallyDisbursedOffline;
	}
	public void setIsPartiallyDisbursedOffline(Boolean isPartiallyDisbursedOffline) {
		this.isPartiallyDisbursedOffline = isPartiallyDisbursedOffline;
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
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public Object getLocationData() {
		return locationData;
	}
	public void setLocationData(Object locationData) {
		this.locationData = locationData;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
