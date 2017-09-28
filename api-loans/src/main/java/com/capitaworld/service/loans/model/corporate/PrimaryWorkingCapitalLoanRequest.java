package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
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

	private Boolean haveExistingLimit;

	private String projectBrief;

	private Double totalCollateralDetails;
	
	private List<Long> negativeList=Collections.emptyList();

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

	public Boolean getHaveExistingLimit() {
		return haveExistingLimit;
	}

	public void setHaveExistingLimit(Boolean haveExistingLimit) {
		this.haveExistingLimit = haveExistingLimit;
	}

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public Double getTotalCollateralDetails() {
		return totalCollateralDetails;
	}

	public void setTotalCollateralDetails(Double totalCollateralDetails) {
		this.totalCollateralDetails = totalCollateralDetails;
	}

	public List<Long> getNegativeList() {
		return negativeList;
	}

	public void setNegativeList(List<Long> negativeList) {
		this.negativeList = negativeList;
	}

	
}