package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_personal_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryPersonalLoanRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Long clientId;
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

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