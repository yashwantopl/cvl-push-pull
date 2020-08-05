package com.opl.mudra.api.loans.model.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalLoanEligibilityRequest extends LoanEligibilility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer constitution;
	private Integer receiptMode;

	public PersonalLoanEligibilityRequest() {
		super();
	}

	public Integer getConstitution() {
		return constitution;
	}

	public void setConstitution(Integer constitution) {
		this.constitution = constitution;
	}

	public Integer getReceiptMode() {
		return receiptMode;
	}

	public void setReceiptMode(Integer receiptMode) {
		this.receiptMode = receiptMode;
	}

	@Override
	public String toString() {
		return "PersonalLoanEligibilityRequest [constitution=" + constitution + ", receiptMode=" + receiptMode
				+ ", getIncome()=" + getIncome() + ", getObligation()=" + getObligation() + ", getDateOfBirth()="
				+ getDateOfBirth() + ", getTenure()=" + getTenure() + "]";
	}
	

}
