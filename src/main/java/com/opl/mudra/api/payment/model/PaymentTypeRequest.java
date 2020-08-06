package com.opl.mudra.api.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public class PaymentTypeRequest implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long applicationId;
	private String txnId;

	private String modeOfPayment;
	private String accountHolder;
	private String bank;
	private String branch;
	private String accType;
	private Long accountNumber;
	private Double amountPayable;
	private Double amount;
	private Date depositDate;
	private String bankName;
	private Date chequeDate;   // cheque related param
	private String chequeNo;       // cheque related param
	private String gstNumber;
	private String refNumber;
	private String depositedBranch;
	private Long branchCountry;
	private Long branchState;
	private Long branchCity;
	private Date submittedDate;
	private String receipt;
	private Long userId;

	private Date createdDate;
	private Long createdBy;
	private Date modifiedDate;
	private Long modifiedBy;
	private Boolean isActive;
	
	private Boolean isPaymentValidated;
	private Boolean isPaymentSubmitted;
	
	private Boolean isCash;
	private Boolean isCheque;
	
	private Date paymentSubmissionDate;
	
	private Long paymentAccountMasterId;

	private String clientName;

	private Date applicationDate;

	private String applicationType;

	private Date creditedDate;

	private String remarks;

	private Date validationDate;

	private String listType;
	
	private String purposeCode;

	private String status;
	
	private Long stateId;
	
	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public Date getValidationDate() {
		return validationDate;
	}

	public void setValidationDate(Date validationDate) {
		this.validationDate = validationDate;
	}

	public Date getCreditedDate() {
		return creditedDate;
	}

	public void setCreditedDate(Date creditedDate) {
		this.creditedDate = creditedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

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

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(Double amountPayable) {
		this.amountPayable = amountPayable;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getDepositedBranch() {
		return depositedBranch;
	}

	public void setDepositedBranch(String depositedBranch) {
		this.depositedBranch = depositedBranch;
	}

	
	public Long getBranchCountry() {
		return branchCountry;
	}

	public void setBranchCountry(Long branchCountry) {
		this.branchCountry = branchCountry;
	}

	public Long getBranchState() {
		return branchState;
	}

	public void setBranchState(Long branchState) {
		this.branchState = branchState;
	}

	public Long getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(Long branchCity) {
		this.branchCity = branchCity;
	}

	public Date getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Date submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date date) {
		this.modifiedDate = date;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsPaymentValidated() {
		return isPaymentValidated;
	}

	public void setIsPaymentValidated(Boolean isPaymentValidated) {
		this.isPaymentValidated = isPaymentValidated;
	}

	public Boolean getIsPaymentSubmitted() {
		return isPaymentSubmitted;
	}

	public void setIsPaymentSubmitted(Boolean isPaymentSubmitted) {
		this.isPaymentSubmitted = isPaymentSubmitted;
	}

	public Boolean getIsCash() {
		return isCash;
	}

	public void setIsCash(Boolean isCash) {
		this.isCash = isCash;
	}

	public Boolean getIsCheque() {
		return isCheque;
	}

	public void setIsCheque(Boolean isCheque) {
		this.isCheque = isCheque;
	}

	public Date getPaymentSubmissionDate() {
		return paymentSubmissionDate;
	}

	public void setPaymentSubmissionDate(Date paymentSubmissionDate) {
		this.paymentSubmissionDate = paymentSubmissionDate;
	}
	
	

	public Long getPaymentAccountMasterId() {
		return paymentAccountMasterId;
	}

	public void setPaymentAccountMasterId(Long paymentAccountMasterId) {
		this.paymentAccountMasterId = paymentAccountMasterId;
	}
	

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

	public String getPurposeCode() {
		return purposeCode;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentTypeRequest [id=" + id + ", applicationId=" + applicationId + ", txnId=" + txnId
				+ ", modeOfPayment=" + modeOfPayment + ", accountHolder=" + accountHolder + ", bank=" + bank
				+ ", branch=" + branch + ", accType=" + accType + ", accountNumber=" + accountNumber
				+ ", amountPayable=" + amountPayable + ", depositDate=" + depositDate + ", bankName=" + bankName
				+ ", chequeDate=" + chequeDate + ", chequeNo=" + chequeNo + ", gstNumber=" + gstNumber + ", refNumber="
				+ refNumber + ", depositedBranch=" + depositedBranch + ", branchCountry=" + branchCountry
				+ ", branchState=" + branchState + ", branchCity=" + branchCity + ", submittedDate=" + submittedDate
				+ ", receipt=" + receipt + ", userId=" + userId + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy + ", isActive=" + isActive
				+ ", isPaymentValidated=" + isPaymentValidated + ", isPaymentSubmitted=" + isPaymentSubmitted
				+ ", isCash=" + isCash + ", isCheque=" + isCheque + ", paymentSubmissionDate=" + paymentSubmissionDate
				+ ", paymentAccountMasterId=" + paymentAccountMasterId + ", clientName=" + clientName
				+ ", applicationDate=" + applicationDate + ", applicationType=" + applicationType + ", creditedDate="
				+ creditedDate + ", remarks=" + remarks + ", validationDate=" + validationDate + ", listType="
				+ listType + ", purposeCode=" + purposeCode + "]";
	}

	

	
	
}
