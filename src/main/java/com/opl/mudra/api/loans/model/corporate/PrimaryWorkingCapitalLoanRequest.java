package com.opl.mudra.api.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.model.LoanApplicationRequest;

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
	
	private Double sharePriceFace;
	
	private Double sharePriceMarket;
	
	private List<Long> negativeList=Collections.emptyList();

	public PrimaryWorkingCapitalLoanRequest() {
		// Do nothing because of X and Y.
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