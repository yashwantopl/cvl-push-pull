package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * The persistent class for the fs_corporate_primary_wc_loan_details database
 * table.
 * 
 */
@Entity
@Table(name = "fs_corporate_primary_wc_loan_details")
@NamedQuery(name = "PrimaryWorkingCapitalLoanDetail.findAll", query = "SELECT f FROM PrimaryWorkingCapitalLoanDetail f")
public class PrimaryWorkingCapitalLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name = "credit_rating_id")
	private Integer creditRatingId;

	@Lob
	@Column(name = "enhancement_of_limit")
	private String enhancementOfLimit;

	@Column(name = "have_existing_limit")
	private Boolean haveExistingLimit;

	@Lob
	@Column(name = "project_brief")
	private String projectBrief;

	@Column(name = "collateral_security_amt_total")
	private Double totalCollateralDetails;
	
	@Column(name = "share_price_face_value")
	private Double sharePriceFace;
	
	@Column(name = "share_price_market_value")
	private Double sharePriceMarket;

	public PrimaryWorkingCapitalLoanDetail() {
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

	public String getEnhancementOfLimit() {
		return this.enhancementOfLimit;
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
		return this.projectBrief;
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