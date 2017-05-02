package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_corporate_primary_wc_loan_details database
 * table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryWorkingCapitalLoanRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer creditRatingId;

	private String enhancementOfLimit;

	private boolean haveExistingLimit;

	private String projectBrief;

	private Double totalCollteralDetails;

	private Integer currencyId;

	private Integer denominationId;

	public PrimaryWorkingCapitalLoanRequest() {
	}

	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public String getEnhancementOfLimit() {
		return enhancementOfLimit;
	}

	public void setEnhancementOfLimit(String enhancementOfLimit) {
		this.enhancementOfLimit = enhancementOfLimit;
	}

	public boolean isHaveExistingLimit() {
		return haveExistingLimit;
	}

	public void setHaveExistingLimit(boolean haveExistingLimit) {
		this.haveExistingLimit = haveExistingLimit;
	}

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public Double getTotalCollteralDetails() {
		return totalCollteralDetails;
	}

	public void setTotalCollteralDetails(Double totalCollteralDetails) {
		this.totalCollteralDetails = totalCollteralDetails;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
	}

}