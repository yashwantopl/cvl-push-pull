package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class ExistingLoanDetailRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;

	private Long applicantId;

	private String bankOrFinancerName;

	private Double emiAmount;

	private Boolean isActive = true;

	private Integer loanTenure;

	private String loanType;

	private Integer noOfEmiPaid;

	private Double outstandingBalance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getBankOrFinancerName() {
		return bankOrFinancerName;
	}

	public void setBankOrFinancerName(String bankOrFinancerName) {
		this.bankOrFinancerName = bankOrFinancerName;
	}


	public Double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Integer getNoOfEmiPaid() {
		return noOfEmiPaid;
	}

	public void setNoOfEmiPaid(Integer noOfEmiPaid) {
		this.noOfEmiPaid = noOfEmiPaid;
	}

	public Double getOutstandingBalance() {
		return outstandingBalance;
	}

	public void setOutstandingBalance(Double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	
	

}
