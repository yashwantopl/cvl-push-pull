package com.capitaworld.service.loans.domain.sanction;

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
@Table(name = "disbursement_detail")
public class LoanDisbursementDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "application_id")
	private Long applicationId;

	@Column(name = "reference_no")
	private String referenceNo;

	@Column(name = "disbursement_date")
	private Date disbursementDate;

	@Column(name = "account_no")
	private String accountNo;

	@Column(name = "disbursed_amount")
	private Double disbursedAmount;

	@Column(name = "payment_mode")
	private Long paymentMode;

	@Column(name = "disbursement_authority")
	private String disbursementAuthority;

	@Column(name = "transaction_no")
	private String transactionNo;

	private String remark;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "bank_disbursement_pk")
	private Long bankDisbursementPrimaryKey;
	
	@Column(name = "is_disbursed_from")
	private Long isDisbursedFrom;
	
	@Column(name = "org_id")
	private Long orgId;

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

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Date getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getDisbursedAmount() {
		return disbursedAmount;
	}

	public void setDisbursedAmount(Double disbursedAmount) {
		this.disbursedAmount = disbursedAmount;
	}

	public Long getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Long paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getDisbursementAuthority() {
		return disbursementAuthority;
	}

	public void setDisbursementAuthority(String disbursementAuthority) {
		this.disbursementAuthority = disbursementAuthority;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getBankDisbursementPrimaryKey() {
		return bankDisbursementPrimaryKey;
	}

	public void setBankDisbursementPrimaryKey(Long bankDisbursementPrimaryKey) {
		this.bankDisbursementPrimaryKey = bankDisbursementPrimaryKey;
	}
	
	public Long getIsDisbursedFrom() {
		return isDisbursedFrom;
	}

	public void setIsDisbursedFrom(Long isDisbursedFrom) {
		this.isDisbursedFrom = isDisbursedFrom;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Override
	public String toString() {
		return "LoanDisbursementDomain [id=" + id + ", applicationId=" + applicationId + ", referenceNo=" + referenceNo
				+ ", disbursementDate=" + disbursementDate + ", accountNo=" + accountNo + ", disbursedAmount="
				+ disbursedAmount + ", paymentMode=" + paymentMode + ", disbursementAuthority=" + disbursementAuthority
				+ ", transactionNo=" + transactionNo + ", remark=" + remark + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", isActive=" + isActive + ", bankDisbursementPrimaryKey=" + bankDisbursementPrimaryKey
				+ ", isDisbursedFrom=" + isDisbursedFrom + ", orgId=" + orgId + "]";
	}

	
}
