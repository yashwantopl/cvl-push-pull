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
public class PrimaryUnsecureLoanRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer creditRatingId;
	private boolean isActive;
	private String purposeOfLoan;
	private Double sharePriceFace;
	private Double sharePriceMarket;
	private List<Long> negativeList=Collections.emptyList();
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
	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}
	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
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