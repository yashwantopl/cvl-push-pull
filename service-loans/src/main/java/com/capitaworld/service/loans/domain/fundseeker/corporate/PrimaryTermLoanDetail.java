package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

@Entity
@Table(name = "fs_corporate_primary_term_loan_details")
@NamedQuery(name = "PrimaryTermLoanDetail.findAll", query = "SELECT p FROM PrimaryTermLoanDetail p")
public class PrimaryTermLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;
	
	@Column(name = "credit_rating_id")
	private Integer creditRatingId;

	@Lob
	@Column(name = "project_brief")
	private String projectBrief;

	@Column(name = "total_cost_of_estimate")
	private Double totalCostOfEstimate;

	@Column(name = "total_means_of_finance")
	private Double totalMeansOfFinance;

	@Column(name = "collateral_security_amt_total")
	private Double totalCollateralDetails;
	
	@Column(name = "share_price_face_value")
	private Double sharePriceFace;
	
	@Column(name = "share_price_market_value")
	private Double sharePriceMarket;

	public PrimaryTermLoanDetail() {
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
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

	public Double getSharePriceFace() {
		return sharePriceFace;
	}

	public void setSharePriceFace(Double sharePriceFace) {
		this.sharePriceFace = sharePriceFace;
	}

	public Double getSharePriceMarket() {
		return sharePriceMarket;
	}

	public void setSharePriceMarket(Double sharePriceMarket) {
		this.sharePriceMarket = sharePriceMarket;
	}
	
	
}