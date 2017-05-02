package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_retail_personal_loan_details database table.
 * 
 */
public class PrimaryPersonalLoanRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	
	
	private Long loanPurpose;

	private String loanPurposeOther;

	public Long getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Long loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}

	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}

	





}