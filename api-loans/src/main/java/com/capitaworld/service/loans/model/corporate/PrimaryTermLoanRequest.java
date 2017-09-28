package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_corporate_primary_term_loan_details database
 * table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryTermLoanRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer creditRatingId;
	private boolean isActive;
	private String projectBrief;
	private Double totalCostOfEstimate;
	private Double totalMeansOfFinance;
	private Double totalCollateralDetails;
	private List<Long> negativeList=Collections.emptyList();
	
	public PrimaryTermLoanRequest() {
	}

	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public Double getTotalCostOfEstimate() {
		return totalCostOfEstimate;
	}

	public void setTotalCostOfEstimate(Double totalCostOfEstimate) {
		this.totalCostOfEstimate = totalCostOfEstimate;
	}

	public Double getTotalMeansOfFinance() {
		return totalMeansOfFinance;
	}

	public void setTotalMeansOfFinance(Double totalMeansOfFinance) {
		this.totalMeansOfFinance = totalMeansOfFinance;
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