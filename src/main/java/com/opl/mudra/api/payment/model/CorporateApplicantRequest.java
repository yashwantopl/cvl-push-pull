package com.opl.mudra.api.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long clientId;
	private Long applicationId;
	private String organisationName;
	private Address firstAddress;
	private String websiteAddress;
	private Long userId;
 	private Double loanAmount;
 	private Integer purposeOfLoanId;
 	private Boolean isAdditionalLoanAmount;
 	private Double additionlLoanAmount;
 	private Boolean isAllowSwitchExistingLender;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public Address getFirstAddress() {
		return firstAddress;
	}
	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
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
	public Integer getPurposeOfLoanId() {
		return purposeOfLoanId;
	}
	public void setPurposeOfLoanId(Integer purposeOfLoanId) {
		this.purposeOfLoanId = purposeOfLoanId;
	}
	public Boolean getIsAdditionalLoanAmount() {
		return isAdditionalLoanAmount;
	}
	public void setIsAdditionalLoanAmount(Boolean isAdditionalLoanAmount) {
		this.isAdditionalLoanAmount = isAdditionalLoanAmount;
	}
	public Double getAdditionlLoanAmount() {
		return additionlLoanAmount;
	}
	public void setAdditionlLoanAmount(Double additionlLoanAmount) {
		this.additionlLoanAmount = additionlLoanAmount;
	}
	public Boolean getIsAllowSwitchExistingLender() {
		return isAllowSwitchExistingLender;
	}
	public void setIsAllowSwitchExistingLender(
			Boolean isAllowSwitchExistingLender) {
		this.isAllowSwitchExistingLender = isAllowSwitchExistingLender;
	}
	@Override
	public String toString() {
		return "CorporateApplicantRequest [id=" + id + ", clientId=" + clientId
				+ ", applicationId=" + applicationId + ", organisationName="
				+ organisationName + ", firstAddress=" + firstAddress
				+ ", websiteAddress=" + websiteAddress + ", userId=" + userId
				+ ", loanAmount=" + loanAmount + ", purposeOfLoanId="
				+ purposeOfLoanId + ", isAdditionalLoanAmount="
				+ isAdditionalLoanAmount + ", additionlLoanAmount="
				+ additionlLoanAmount + ", isAllowSwitchExistingLender="
				+ isAllowSwitchExistingLender + "]";
	}
 	

 	
}