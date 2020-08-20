package com.opl.mudra.api.user.model.loans;

import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the fs_loan_application_master database table.
 * 
 */
public class LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private String categoryCode;

	private Long createdBy;

	private Date createdDate;

	private Boolean isActive = true;

	private Long modifiedBy;

	private Date modifiedDate;

	private String name;

	private String applicationCode;

	private Integer productId;

	private Double tenure;

	private Long userId;

	private Integer currencyId;

	private Integer denominationId;

	private Boolean isApplicantDetailsFilled;

	private Boolean isApplicantPrimaryFilled;

	private Boolean isApplicantFinalFilled;

	// CoApps Fields
	private Boolean isCoApp1DetailsFilled;

	private Boolean isCoApp1FinalFilled;

	private Boolean isCoApp2DetailsFilled;

	private Boolean isCoApp2FinalFilled;

	// Guarantor Fields
	private Boolean isGuarantor1DetailsFilled;

	private Boolean isGuarantor1FinalFilled;

	private Boolean isGuarantor2DetailsFilled;

	private Boolean isGuarantor2FinalFilled;

	// Upload Fields
	private Boolean isPrimaryUploadFilled;

	private Boolean isFinalDprUploadFilled;

	private Boolean isFinalUploadFilled;

	private Boolean isFinalMcqFilled;

	// Locking Fields

	private Boolean isPrimaryLocked;

	private Boolean isFinalLocked;

	// Filled Time

	private Boolean detailsFilledTime;

	private Boolean primaryFilledTime;

	private Boolean finalFilledTime;

	// Filled Count

	private String detailsFilledCount;

	private String primaryFilledCount;

	private String finalFilledCount;

	private String mcaCompanyId;

	private Boolean isMca;

	private Boolean isMsmeScoreRequired;

	private String campaignCode;

	private Double eligibleAmnt;

	private Long npUserId;

	private Long npOrgId;

	private Long npAssigneeId;

	private Long fpMakerId;

	private Long ddrStatusId;

	// payment related fields

	private String typeOfPayment;

	private Date appointmentDate;

	private String appointmentTime;

	private Double paymentAmount;
	
	private Boolean isAcceptConsent;
	
	private Date approvedDate;
	
	private String paymentStatus;
	
	private Integer businessTypeId;
	
	private String companyCinNumber;
	
	private Integer wcRenewalStatus;
	
	private Boolean isMcqSkipped;
	private Long proposalId;


	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Long getFpMakerId() {
		return fpMakerId;
	}

	public void setFpMakerId(Long fpMakerId) {
		this.fpMakerId = fpMakerId;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getNpOrgId() {
		return npOrgId;
	}

	public void setNpOrgId(Long npOrgId) {
		this.npOrgId = npOrgId;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Long getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(Long ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	public Boolean getIsMsmeScoreRequired() {
		return isMsmeScoreRequired;
	}

	public void setIsMsmeScoreRequired(Boolean isMsmeScoreRequired) {
		this.isMsmeScoreRequired = isMsmeScoreRequired;
	}

	public Boolean getIsMca() {
		return isMca;
	}

	public void setIsMca(Boolean isMca) {
		this.isMca = isMca;
	}

	public String getMcaCompanyId() {
		return mcaCompanyId;
	}

	public void setMcaCompanyId(String mcaCompanyId) {
		this.mcaCompanyId = mcaCompanyId;
	}

	// bi-directional many-to-one association to ApplicationStatusMaster
	private ApplicationStatusMaster applicationStatusMaster;

	public LoanApplicationMaster() {
		// Do nothing because of X and Y.
	}

	public LoanApplicationMaster(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ApplicationStatusMaster getApplicationStatusMaster() {
		return this.applicationStatusMaster;
	}

	public void setApplicationStatusMaster(ApplicationStatusMaster applicationStatusMaster) {
		this.applicationStatusMaster = applicationStatusMaster;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
	}

	public Boolean getIsApplicantDetailsFilled() {
		return isApplicantDetailsFilled;
	}

	public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
		this.isApplicantDetailsFilled = isApplicantDetailsFilled;
	}

	public Boolean getIsApplicantPrimaryFilled() {
		return isApplicantPrimaryFilled;
	}

	public void setIsApplicantPrimaryFilled(Boolean isApplicantPrimaryFilled) {
		this.isApplicantPrimaryFilled = isApplicantPrimaryFilled;
	}

	public Boolean getIsApplicantFinalFilled() {
		return isApplicantFinalFilled;
	}

	public void setIsApplicantFinalFilled(Boolean isApplicantFinalFilled) {
		this.isApplicantFinalFilled = isApplicantFinalFilled;
	}

	public Boolean getIsCoApp1DetailsFilled() {
		return isCoApp1DetailsFilled;
	}

	public void setIsCoApp1DetailsFilled(Boolean isCoApp1DetailsFilled) {
		this.isCoApp1DetailsFilled = isCoApp1DetailsFilled;
	}

	public Boolean getIsCoApp1FinalFilled() {
		return isCoApp1FinalFilled;
	}

	public void setIsCoApp1FinalFilled(Boolean isCoApp1FinalFilled) {
		this.isCoApp1FinalFilled = isCoApp1FinalFilled;
	}

	public Boolean getIsCoApp2DetailsFilled() {
		return isCoApp2DetailsFilled;
	}

	public void setIsCoApp2DetailsFilled(Boolean isCoApp2DetailsFilled) {
		this.isCoApp2DetailsFilled = isCoApp2DetailsFilled;
	}

	public Boolean getIsCoApp2FinalFilled() {
		return isCoApp2FinalFilled;
	}

	public void setIsCoApp2FinalFilled(Boolean isCoApp2FinalFilled) {
		this.isCoApp2FinalFilled = isCoApp2FinalFilled;
	}

	public Boolean getIsGuarantor1DetailsFilled() {
		return isGuarantor1DetailsFilled;
	}

	public void setIsGuarantor1DetailsFilled(Boolean isGuarantor1DetailsFilled) {
		this.isGuarantor1DetailsFilled = isGuarantor1DetailsFilled;
	}

	public Boolean getIsGuarantor1FinalFilled() {
		return isGuarantor1FinalFilled;
	}

	public void setIsGuarantor1FinalFilled(Boolean isGuarantor1FinalFilled) {
		this.isGuarantor1FinalFilled = isGuarantor1FinalFilled;
	}

	public Boolean getIsGuarantor2DetailsFilled() {
		return isGuarantor2DetailsFilled;
	}

	public void setIsGuarantor2DetailsFilled(Boolean isGuarantor2DetailsFilled) {
		this.isGuarantor2DetailsFilled = isGuarantor2DetailsFilled;
	}

	public Boolean getIsGuarantor2FinalFilled() {
		return isGuarantor2FinalFilled;
	}

	public void setIsGuarantor2FinalFilled(Boolean isGuarantor2FinalFilled) {
		this.isGuarantor2FinalFilled = isGuarantor2FinalFilled;
	}

	public Boolean getIsPrimaryUploadFilled() {
		return isPrimaryUploadFilled;
	}

	public void setIsPrimaryUploadFilled(Boolean isPrimaryUploadFilled) {
		this.isPrimaryUploadFilled = isPrimaryUploadFilled;
	}

	public Boolean getIsFinalDprUploadFilled() {
		return isFinalDprUploadFilled;
	}

	public void setIsFinalDprUploadFilled(Boolean isFinalDprUploadFilled) {
		this.isFinalDprUploadFilled = isFinalDprUploadFilled;
	}

	public Boolean getIsFinalUploadFilled() {
		return isFinalUploadFilled;
	}

	public void setIsFinalUploadFilled(Boolean isFinalUploadFilled) {
		this.isFinalUploadFilled = isFinalUploadFilled;
	}

	public Boolean getIsFinalMcqFilled() {
		return isFinalMcqFilled;
	}

	public void setIsFinalMcqFilled(Boolean isFinalMcqFilled) {
		this.isFinalMcqFilled = isFinalMcqFilled;
	}

	public Boolean getIsPrimaryLocked() {
		return isPrimaryLocked;
	}

	public void setIsPrimaryLocked(Boolean isPrimaryLocked) {
		this.isPrimaryLocked = isPrimaryLocked;
	}

	public Boolean getIsFinalLocked() {
		return isFinalLocked;
	}

	public void setIsFinalLocked(Boolean isFinalLocked) {
		this.isFinalLocked = isFinalLocked;
	}

	public Boolean getDetailsFilledTime() {
		return detailsFilledTime;
	}

	public void setDetailsFilledTime(Boolean detailsFilledTime) {
		this.detailsFilledTime = detailsFilledTime;
	}

	public Boolean getPrimaryFilledTime() {
		return primaryFilledTime;
	}

	public void setPrimaryFilledTime(Boolean primaryFilledTime) {
		this.primaryFilledTime = primaryFilledTime;
	}

	public Boolean getFinalFilledTime() {
		return finalFilledTime;
	}

	public void setFinalFilledTime(Boolean finalFilledTime) {
		this.finalFilledTime = finalFilledTime;
	}

	public String getPrimaryFilledCount() {
		return primaryFilledCount;
	}

	public void setPrimaryFilledCount(String primaryFilledCount) {
		this.primaryFilledCount = primaryFilledCount;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
	}

	public String getFinalFilledCount() {
		return finalFilledCount;
	}

	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public Double getEligibleAmnt() {
		return eligibleAmnt;
	}

	public void setEligibleAmnt(Double eligibleAmnt) {
		this.eligibleAmnt = eligibleAmnt;
	}

	public Long getNpAssigneeId() {
		return npAssigneeId;
	}

	public void setNpAssigneeId(Long npAssigneeId) {
		this.npAssigneeId = npAssigneeId;
	}

	public Long getNpUserId() {
		return npUserId;
	}

	public void setNpUserId(Long npUserId) {
		this.npUserId = npUserId;
	}
	
	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public Boolean getIsAcceptConsent() {
		return isAcceptConsent;
	}

	public void setIsAcceptConsent(Boolean isAcceptConsent) {
		this.isAcceptConsent = isAcceptConsent;
	}
	
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
	

	/**
	 * @return the companyCinNumber
	 */
	public String getCompanyCinNumber() {
		return companyCinNumber;
	}

	/**
	 * @param companyCinNumber the companyCinNumber to set
	 */
	public void setCompanyCinNumber(String companyCinNumber) {
		this.companyCinNumber = companyCinNumber;
	}

	
	
	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
	}

	public Boolean getIsMcqSkipped() {
		return isMcqSkipped;
	}

	public void setIsMcqSkipped(Boolean isMcqSkipped) {
		this.isMcqSkipped = isMcqSkipped;
	}
	
	@Override
	public String toString() {
		return "LoanApplicationMaster [id=" + id + ", amount=" + amount + ", categoryCode=" + categoryCode
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", isActive=" + isActive
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", name=" + name
				+ ", applicationCode=" + applicationCode + ", productId=" + productId + ", tenure=" + tenure
				+ ", userId=" + userId + ", currencyId=" + currencyId + ", denominationId=" + denominationId
				+ ", isApplicantDetailsFilled=" + isApplicantDetailsFilled + ", isApplicantPrimaryFilled="
				+ isApplicantPrimaryFilled + ", isApplicantFinalFilled=" + isApplicantFinalFilled
				+ ", isCoApp1DetailsFilled=" + isCoApp1DetailsFilled + ", isCoApp1FinalFilled=" + isCoApp1FinalFilled
				+ ", isCoApp2DetailsFilled=" + isCoApp2DetailsFilled + ", isCoApp2FinalFilled=" + isCoApp2FinalFilled
				+ ", isGuarantor1DetailsFilled=" + isGuarantor1DetailsFilled + ", isGuarantor1FinalFilled="
				+ isGuarantor1FinalFilled + ", isGuarantor2DetailsFilled=" + isGuarantor2DetailsFilled
				+ ", isGuarantor2FinalFilled=" + isGuarantor2FinalFilled + ", isPrimaryUploadFilled="
				+ isPrimaryUploadFilled + ", isFinalDprUploadFilled=" + isFinalDprUploadFilled
				+ ", isFinalUploadFilled=" + isFinalUploadFilled + ", isFinalMcqFilled=" + isFinalMcqFilled
				+ ", isPrimaryLocked=" + isPrimaryLocked + ", isFinalLocked=" + isFinalLocked + ", detailsFilledTime="
				+ detailsFilledTime + ", primaryFilledTime=" + primaryFilledTime + ", finalFilledTime="
				+ finalFilledTime + ", detailsFilledCount=" + detailsFilledCount + ", primaryFilledCount="
				+ primaryFilledCount + ", finalFilledCount=" + finalFilledCount + ", mcaCompanyId=" + mcaCompanyId
				+ ", isMca=" + isMca + ", isMsmeScoreRequired=" + isMsmeScoreRequired + ", campaignCode=" + campaignCode
				+ ", eligibleAmnt=" + eligibleAmnt + ", npUserId=" + npUserId + ", npOrgId=" + npOrgId
				+ ", npAssigneeId=" + npAssigneeId + ", fpMakerId=" + fpMakerId + ", ddrStatusId=" + ddrStatusId
				+ ", typeOfPayment=" + typeOfPayment + ", appointmentDate=" + appointmentDate + ", appointmentTime="
				+ appointmentTime + ", paymentAmount=" + paymentAmount + ", isAcceptConsent=" + isAcceptConsent
				+ ", approvedDate=" + approvedDate + ", paymentStatus=" + paymentStatus + ", businessTypeId="
				+ businessTypeId + ", companyCinNumber=" + companyCinNumber + ", wcRenewalStatus=" + wcRenewalStatus
				+ ", isMcqSkipped=" + isMcqSkipped + ", proposalId=" + proposalId + ", applicationStatusMaster="
				+ applicationStatusMaster + "]";
	}

}