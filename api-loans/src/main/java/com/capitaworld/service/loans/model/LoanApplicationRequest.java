package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_loan_application_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long clientId;

	private Double amount;

	private String categoryCode;

	private String name;

	private Integer productId;

	private Double tenure;
	
	private String typeOfLoan;

	private Double loanAmount;
	
	private Double interestRate;
	
	private Double emiAmount;
	
	private String nameOfEntity;
	
	private String fundProvider;
	
	private Long userId;
	
	private Long fpProductId;

	private Integer currencyId;

	private Integer denominationId;

	private Date createdDate;

	private boolean hasAlreadyApplied;

	private String loanTypeMain;

	private String loanTypeSub;

	private String currencyValue;

	private Boolean isApplicantDetailsFilled;

	private Boolean isApplicantPrimaryFilled;

	private Boolean isApplicantFinalFilled;

	private String applicationCode;

	private Integer status;

	private String primaryFilledCount;

	private Boolean profilePrimaryLocked;

	private Boolean finalLocked;

	private String mcaCompanyId;

	private Boolean isMca;

	private Long npUserId;

	private Long npAssigneeId;

	private Long fpMakerId;

	private String email;

	private String mobile;

	private String userName;

	private String providerName;

	private String paymentStatus;

	private String typeOfPayment;

	private Date appointmentDate;

	private String appointmentTime;
	
	private String address;
	
	private Boolean isMailSent;
	
	private Boolean isNhbsApplication;
	
	private Integer ddrStatusId;

	private String gstIn;

	private Long npOrgId;
	
	private Boolean onlinePaymentSuccess;

	private String fsNameForPayment;

	private String amountForPayment;
	private String roiForPayment;
	private String tenureForPayment;
	private String emiForPaymentayment;
	private String  feesForPayment;
    private String  fpNameForPayment;
    
    private Integer businessTypeId;

	public Long getFpMakerId() {
		return fpMakerId;
	}

	public void setFpMakerId(Long fpMakerId) {
		this.fpMakerId = fpMakerId;
	}

	public Long getNpOrgId() {
		return npOrgId;
	}

	public void setNpOrgId(Long npOrgId) {
		this.npOrgId = npOrgId;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public Integer getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(Integer ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	public Boolean getIsNhbsApplication() {
		return isNhbsApplication;
	}

	public void setIsNhbsApplication(Boolean isNhbsApplication) {
		this.isNhbsApplication = isNhbsApplication;
	}

	public LoanApplicationRequest() {
	}

	public LoanApplicationRequest(Long id) {
		this.id = id;
	}

	public LoanApplicationRequest(Long id, Integer productId) {
		super();
		this.id = id;
		this.productId = productId;
	}

	public LoanApplicationRequest(Long id, Long userId) {
		super();
		this.id = id;
		this.userId = userId;
	}

	public String getTypeOfPayment() {
		return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		this.typeOfPayment = typeOfPayment;
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
		return this.tenure;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isHasAlreadyApplied() {
		return hasAlreadyApplied;
	}

	public void setHasAlreadyApplied(boolean hasAlreadyApplied) {
		this.hasAlreadyApplied = hasAlreadyApplied;
	}

	public String getLoanTypeMain() {
		return loanTypeMain;
	}

	public void setLoanTypeMain(String loanTypeMain) {
		this.loanTypeMain = loanTypeMain;
	}

	public String getLoanTypeSub() {
		return loanTypeSub;
	}

	public void setLoanTypeSub(String loanTypeSub) {
		this.loanTypeSub = loanTypeSub;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Boolean getIsApplicantPrimaryFilled() {
		return isApplicantPrimaryFilled;
	}

	public void setIsApplicantPrimaryFilled(Boolean isApplicantPrimaryFilled) {
		this.isApplicantPrimaryFilled = isApplicantPrimaryFilled;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrimaryFilledCount() {
		return primaryFilledCount;
	}

	public void setPrimaryFilledCount(String primaryFilledCount) {
		this.primaryFilledCount = primaryFilledCount;
	}

	public Boolean getProfilePrimaryLocked() {
		return profilePrimaryLocked;
	}

	public void setProfilePrimaryLocked(Boolean profilePrimaryLocked) {
		this.profilePrimaryLocked = profilePrimaryLocked;
	}

	public Boolean getFinalLocked() {
		return finalLocked;
	}

	public void setFinalLocked(Boolean finalLocked) {
		this.finalLocked = finalLocked;
	}

	public Boolean getIsApplicantDetailsFilled() {
		return isApplicantDetailsFilled;
	}

	public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
		this.isApplicantDetailsFilled = isApplicantDetailsFilled;
	}

	public Boolean getIsApplicantFinalFilled() {
		return isApplicantFinalFilled;
	}

	public void setIsApplicantFinalFilled(Boolean isApplicantFinalFilled) {
		this.isApplicantFinalFilled = isApplicantFinalFilled;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsMailSent() {
		return isMailSent;
	}

	public void setIsMailSent(Boolean isMailSent) {
		this.isMailSent = isMailSent;
	}

	public String getTypeOfLoan() {
		return typeOfLoan;
	}

	public void setTypeOfLoan(String typeOfLoan) {
		this.typeOfLoan = typeOfLoan;
	}

	

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	
	

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public String getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}

	public String getFundProvider() {
		return fundProvider;
	}

	public void setFundProvider(String fundProvider) {
		this.fundProvider = fundProvider;
	}

	public Boolean getOnlinePaymentSuccess() {
		return onlinePaymentSuccess;
	}

	public void setOnlinePaymentSuccess(Boolean onlinePaymentSuccess) {
		this.onlinePaymentSuccess = onlinePaymentSuccess;
	}

	public String getFsNameForPayment() {
		return fsNameForPayment;
	}

	public void setFsNameForPayment(String fsNameForPayment) {
		this.fsNameForPayment = fsNameForPayment;
	}

	public String getAmountForPayment() {
		return amountForPayment;
	}

	public void setAmountForPayment(String amountForPayment) {
		this.amountForPayment = amountForPayment;
	}

	public String getRoiForPayment() {
		return roiForPayment;
	}

	public void setRoiForPayment(String roiForPayment) {
		this.roiForPayment = roiForPayment;
	}

	public String getTenureForPayment() {
		return tenureForPayment;
	}

	public void setTenureForPayment(String tenureForPayment) {
		this.tenureForPayment = tenureForPayment;
	}

	public String getEmiForPaymentayment() {
		return emiForPaymentayment;
	}

	public void setEmiForPaymentayment(String emiForPaymentayment) {
		this.emiForPaymentayment = emiForPaymentayment;
	}

	public String getFeesForPayment() {
		return feesForPayment;
	}

	public void setFeesForPayment(String feesForPayment) {
		this.feesForPayment = feesForPayment;
	}

    public String getFpNameForPayment() {
        return fpNameForPayment;
    }

    public void setFpNameForPayment(String fpNameForPayment) {
        this.fpNameForPayment = fpNameForPayment;
    }

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoanApplicationRequest [id=" + id + ", clientId=" + clientId + ", amount=" + amount + ", categoryCode="
				+ categoryCode + ", name=" + name + ", productId=" + productId + ", tenure=" + tenure + ", typeOfLoan="
				+ typeOfLoan + ", loanAmount=" + loanAmount + ", interestRate=" + interestRate + ", emiAmount="
				+ emiAmount + ", nameOfEntity=" + nameOfEntity + ", fundProvider=" + fundProvider + ", userId=" + userId
				+ ", fpProductId=" + fpProductId + ", currencyId=" + currencyId + ", denominationId=" + denominationId
				+ ", createdDate=" + createdDate + ", hasAlreadyApplied=" + hasAlreadyApplied + ", loanTypeMain="
				+ loanTypeMain + ", loanTypeSub=" + loanTypeSub + ", currencyValue=" + currencyValue
				+ ", isApplicantDetailsFilled=" + isApplicantDetailsFilled + ", isApplicantPrimaryFilled="
				+ isApplicantPrimaryFilled + ", isApplicantFinalFilled=" + isApplicantFinalFilled + ", applicationCode="
				+ applicationCode + ", status=" + status + ", primaryFilledCount=" + primaryFilledCount
				+ ", profilePrimaryLocked=" + profilePrimaryLocked + ", finalLocked=" + finalLocked + ", mcaCompanyId="
				+ mcaCompanyId + ", isMca=" + isMca + ", npUserId=" + npUserId + ", npAssigneeId=" + npAssigneeId
				+ ", fpMakerId=" + fpMakerId + ", email=" + email + ", mobile=" + mobile + ", userName=" + userName
				+ ", providerName=" + providerName + ", paymentStatus=" + paymentStatus + ", typeOfPayment="
				+ typeOfPayment + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime
				+ ", address=" + address + ", isMailSent=" + isMailSent + ", isNhbsApplication=" + isNhbsApplication
				+ ", ddrStatusId=" + ddrStatusId + ", gstIn=" + gstIn + ", npOrgId=" + npOrgId
				+ ", onlinePaymentSuccess=" + onlinePaymentSuccess + ", fsNameForPayment=" + fsNameForPayment
				+ ", amountForPayment=" + amountForPayment + ", roiForPayment=" + roiForPayment + ", tenureForPayment="
				+ tenureForPayment + ", emiForPaymentayment=" + emiForPaymentayment + ", feesForPayment="
				+ feesForPayment + ", fpNameForPayment=" + fpNameForPayment + ", businessTypeId=" + businessTypeId
				+ "]";
	}
	
	
}