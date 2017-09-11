package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LAPEligibilityRequest extends LoanEligibilility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer employmentType;
	private Integer propertyType;
	private Long marketValue;

	public LAPEligibilityRequest() {
		super();
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public Long getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Long marketValue) {
		this.marketValue = marketValue;
	}

	@Override
	public String toString() {
		return "LAPEligibilityRequest [employmentType=" + employmentType + ", propertyType=" + propertyType
				+ ", marketValue=" + marketValue + ", getIncome()=" + getIncome() + ", getObligation()="
				+ getObligation() + ", getDateOfBirth()=" + getDateOfBirth() + "]";
	}

}
