package com.opl.mudra.api.user.model;

import java.util.Date;

public class ReportUserLinkGenResponse {
	
	private Long id;
	private String mobile;
    private String email;
    private String pan;
    private Boolean isEmailSent;
    private Date emailSentDate;
    private String borrowerName;
    private String remarks;
    private Boolean isLinkExpired;
    private Long uploadedBy;
    private String uploadType;
    private Boolean isDataRetrieved;
    private Boolean isGstDataRetrieved;
    private Boolean isItrDataRetrived;
    private Boolean isBsDataRetrived;
    private Boolean isActive;
    private Long orgId;
    private Boolean isMcaDataRetrived;
    private String cin;
    private String companyName;
    private String fpName;
    private String optOut;
    private String orgName;
    
    
    
    

	public String getFpName() {
		return fpName;
	}
	public void setFpName(String fpName) {
		this.fpName = fpName;
	}
	public String getOptOut() {
		return optOut;
	}
	public void setOptOut(String optOut) {
		this.optOut = optOut;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Boolean getIsMcaDataRetrived() {
		return isMcaDataRetrived;
	}
	public void setIsMcaDataRetrived(Boolean isMcaDataRetrived) {
		this.isMcaDataRetrived = isMcaDataRetrived;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Boolean getIsEmailSent() {
		return isEmailSent;
	}
	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
	public Date getEmailSentDate() {
		return emailSentDate;
	}
	public void setEmailSentDate(Date emailSentDate) {
		this.emailSentDate = emailSentDate;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Boolean getIsLinkExpired() {
		return isLinkExpired;
	}
	public void setIsLinkExpired(Boolean isLinkExpired) {
		this.isLinkExpired = isLinkExpired;
	}
	public Long getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(Long uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public Boolean getIsItrDataRetrived() {
		return isItrDataRetrived;
	}
	public void setIsItrDataRetrived(Boolean isItrDataRetrived) {
		this.isItrDataRetrived = isItrDataRetrived;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsBsDataRetrived() {
		return isBsDataRetrived;
	}
	public void setIsBsDataRetrived(Boolean isBsDataRetrived) {
		this.isBsDataRetrived = isBsDataRetrived;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public Boolean getIsGstDataRetrieved() {
		return isGstDataRetrieved;
	}
	public void setIsGstDataRetrieved(Boolean isGstDataRetrieved) {
		this.isGstDataRetrieved = isGstDataRetrieved;
	}
	
	public Boolean getIsDataRetrieved() {
		return isDataRetrieved;
	}
	public void setIsDataRetrieved(Boolean isDataRetrieved) {
		this.isDataRetrieved = isDataRetrieved;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Override
	public String toString() {
		return "ReportUserLinkGenResponse [id=" + id + ", mobile=" + mobile + ", email=" + email + ", pan=" + pan
				+ ", isEmailSent=" + isEmailSent + ", emailSentDate=" + emailSentDate + ", borrowerName=" + borrowerName
				+ ", remarks=" + remarks + ", isLinkExpired=" + isLinkExpired + ", uploadedBy=" + uploadedBy
				+ ", uploadType=" + uploadType + ", isDataRetrieved=" + isDataRetrieved + ", isGstDataRetrieved="
				+ isGstDataRetrieved + ", isItrDataRetrived=" + isItrDataRetrived + ", isBsDataRetrived="
				+ isBsDataRetrived + ", isActive=" + isActive + ", orgId=" + orgId + ", isMcaDataRetrived="
				+ isMcaDataRetrived + ", cin=" + cin + ", companyName=" + companyName + "]";
	}

}
