package com.capitaworld.service.loans.model;

import java.io.Serializable;

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
	private Double totalOfCollatralDetail;

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

	public Double getTotalOfCollatralDetail() {
		return totalOfCollatralDetail;
	}

	public void setTotalOfCollatralDetail(Double totalOfCollatralDetail) {
		this.totalOfCollatralDetail = totalOfCollatralDetail;
	}

}