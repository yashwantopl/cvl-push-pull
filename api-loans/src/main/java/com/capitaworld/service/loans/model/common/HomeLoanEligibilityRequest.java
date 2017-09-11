package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeLoanEligibilityRequest extends LoanEligibilility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer employmentType;
	private Long stampValue;
	private Long marketValue;

	public HomeLoanEligibilityRequest() {
		super();
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Long getStampValue() {
		return stampValue;
	}

	public void setStampValue(Long stampValue) {
		this.stampValue = stampValue;
	}

	public Long getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Long marketValue) {
		this.marketValue = marketValue;
	}

	@Override
	public String toString() {
		return "HomeLoanEligibilityRequest [employmentType=" + employmentType + ", stampValue=" + stampValue
				+ ", marketValue=" + marketValue + ", getIncome()=" + getIncome() + ", getObligation()="
				+ getObligation() + ", getDateOfBirth()=" + getDateOfBirth() + ", getTenure()=" + getTenure() + "]";
	}
	

}
