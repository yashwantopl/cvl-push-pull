package com.capitaworld.service.loans.model;

import java.util.Date;

public class OfflineProcessedApplicationRequest {

	private Long applicationId;
	private Long fpProductId;
	private Long proposalId;
	private Long userId;
	private Double loanAmount;
	private String organisationName;
	private String branchName;
	private String isCampaignUser;
	private String campaignCode;
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
	private Integer status;
	
	//DISBURSED APPLICATION LIST
	private Double disbursedAmount;
	private Date disbursedDate;
	private String accountNo;
	private String transactionNo;
	private Integer paymentMode;
	private String email;
	private String mobile;
	private Long userOrgId;
	
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
	public String getCampaignCode() {
		return campaignCode;
	}
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public Double getDisbursedAmount() {
		return disbursedAmount;
	}
	public void setDisbursedAmount(Double disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}
	public Date getDisbursedDate() {
		return disbursedDate;
	}
	public void setDisbursedDate(Date disbursedDate) {
		this.disbursedDate = disbursedDate;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public Integer getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	
	@Override
	public String toString() {
		return "OfflineProcessedApplicationRequest [applicationId=" + applicationId + ", fpProductId=" + fpProductId
				+ ", proposalId=" + proposalId + ", userId=" + userId + ", loanAmount=" + loanAmount
				+ ", organisationName=" + organisationName + ", branchName=" + branchName + ", isCampaignUser="
				+ isCampaignUser + ", campaignCode=" + campaignCode + ", isSanctioned=" + isSanctioned
				+ ", isDisbursed=" + isDisbursed + ", branchId=" + branchId + ", pan=" + pan + ", gstin=" + gstin
				+ ", branchCode=" + branchCode + ", branchAddress=" + branchAddress + ", locationData=" + locationData
				+ ", sanctionedAmount=" + sanctionedAmount + ", sanctionDate=" + sanctionDate + ", tenure=" + tenure
				+ ", remark=" + remark + ", roi=" + roi + ", processingFee=" + processingFee + ", isSanctionedFrom="
				+ isSanctionedFrom + ", isPartiallyDisbursedOffline=" + isPartiallyDisbursedOffline + ", reason="
				+ reason + ", modifiedDate=" + modifiedDate + ", status=" + status + ", disbursedAmount="
				+ disbursedAmount + ", disbursedDate=" + disbursedDate + ", accountNo=" + accountNo + ", transactionNo="
				+ transactionNo + ", paymentMode=" + paymentMode + "]";
	}
}
