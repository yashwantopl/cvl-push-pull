package com.capitaworld.service.loans.model;

import java.util.Date;

public class DisbursementDetailsModel {
	private Long id;
	
	private Date disbursementDate;
	
	private String transactionNo;
	
	private Integer mode;
	
	private Double disburseAmount;
	
	private Integer tenure;
	
	private Double interestRate;
	
	private String remark;

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

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Double getDisburseAmount() {
		return disburseAmount;
	}

	public void setDisburseAmount(Double disburseAmount) {
		this.disburseAmount = disburseAmount;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
