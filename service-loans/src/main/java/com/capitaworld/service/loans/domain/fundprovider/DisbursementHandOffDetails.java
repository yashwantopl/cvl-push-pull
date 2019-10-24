package com.capitaworld.service.loans.domain.fundprovider;

/**
 * Date :- 24 OCT 2019
 * Pr :- Co-Origination
 * Ds :- Post Disbursment Functionality
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="disbursement_handoff_details")
public class DisbursementHandOffDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "disbursement_date")
	private Date disbursementDate;
	
	@Column(name = "transaction_no")
	private String transactionNo;
	
	private Integer tenure;
	
	@Column(name="interest_rate")
	private Double interestRate;
	
	@Column(name="disburse_amount")
	private Double disburseAmount;
	
	@Column(name="repayment_frequency")
	private Integer repaymentFrequency;
	
	@Column(name="installment_amount")
	private Double installmentAmount;
	
	@Column(name="installment_start_date")
	private Date installmentStartDate;
	
	@Column(name="installment_end_date")
	private Date installmentEndDate;
	
	@Column(name="installment_number")
	private Integer installmentNumber;
	
	@Column(name="is_first_disbursement")
	private Boolean isFirstDisbursement;
	
	@Column(name = "remark")
	private String remark;
	
	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
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
	

	
	
}
