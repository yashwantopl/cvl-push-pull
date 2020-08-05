package com.opl.mudra.api.matchengine.model;

import java.util.Date;

public class DisbursementDetailsModel {
	private Long id;
	
	private Long userId;
	
	private Long proposalId;
	
	private Date disbursementDate;
	
	private String transactionNo;
	
	private Integer mode;
	
	private Double senctionedAmount;
	
	private Double amount;
	
	private Integer tenure;
	
	private Double roi;
	
	private String remark;
	
	private Long applicationId;
	
	private Integer proposalStatus;
	
	private Long fpProductId;
	
	private Long orgId;

	private String reason;
	
	private Long isDisbursedFrom;

	private Integer nbfcFlow;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
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

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Double getSenctionedAmount() {
		return senctionedAmount;
	}

	public void setSenctionedAmount(Double senctionedAmount) {
		this.senctionedAmount = senctionedAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(Integer proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getIsDisbursedFrom() {
		return isDisbursedFrom;
	}

	public void setIsDisbursedFrom(Long isDisbursedFrom) {
		this.isDisbursedFrom = isDisbursedFrom;
	}

	public Integer getNbfcFlow() {
		return nbfcFlow;
	}

	public void setNbfcFlow(Integer nbfcFlow) {
		this.nbfcFlow = nbfcFlow;
	}

	@Override
	public String toString() {
		return "DisbursementDetailsModel [id=" + id + ", userId=" + userId + ", proposalId=" + proposalId
				+ ", disbursementDate=" + disbursementDate + ", transactionNo=" + transactionNo + ", mode=" + mode
				+ ", senctionedAmount=" + senctionedAmount + ", amount=" + amount + ", tenure=" + tenure + ", roi="
				+ roi + ", remark=" + remark + ", applicationId=" + applicationId + ", proposalStatus=" + proposalStatus
				+ ", fpProductId=" + fpProductId + ", orgId=" + orgId + ", reason=" + reason + ", isDisbursedFrom="
				+ isDisbursedFrom + "]";
	}
}
