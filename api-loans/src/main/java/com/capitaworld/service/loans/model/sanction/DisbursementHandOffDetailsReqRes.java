package com.capitaworld.service.loans.model.sanction;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Date :- 24 OCT 2019
 * Pr :- Co-Origination
 * Ds :- Post Disbursment Functionality
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisbursementHandOffDetailsReqRes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long applicationId;
	
	private String applicationCode;
	
	private Date disbursementDate;
	
	private String transactionNo;
	
	private Integer tenure;
	
	private Double interestRate;
	
	private Double disburseAmount;
	
	private Integer repaymentFrequency;
	
	private Double installmentAmount;
	
	private Date installmentStartDate;
	
	private Date installmentEndDate;
	
	private Integer installmentNumber;
	
	private Boolean isFirstDisbursement;
	
	private String remark;
	
	private Long createdBy;

	private Date createdDate;
	
	private Boolean isActive;

	private Long modifiedBy;

	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getDisburseAmount() {
		return disburseAmount;
	}

	public void setDisburseAmount(Double disburseAmount) {
		this.disburseAmount = disburseAmount;
	}

	public Integer getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(Integer repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public Double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(Double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public Date getInstallmentStartDate() {
		return installmentStartDate;
	}

	public void setInstallmentStartDate(Date installmentStartDate) {
		this.installmentStartDate = installmentStartDate;
	}

	public Date getInstallmentEndDate() {
		return installmentEndDate;
	}

	public void setInstallmentEndDate(Date installmentEndDate) {
		this.installmentEndDate = installmentEndDate;
	}

	public Integer getInstallmentNumber() {
		return installmentNumber;
	}

	public void setInstallmentNumber(Integer installmentNumber) {
		this.installmentNumber = installmentNumber;
	}

	public Boolean getIsFirstDisbursement() {
		return isFirstDisbursement;
	}

	public void setIsFirstDisbursement(Boolean isFirstDisbursement) {
		this.isFirstDisbursement = isFirstDisbursement;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	
	

}
