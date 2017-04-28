package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fp_loan_against_share_details database table.
 * 
 */
public class LasParameterRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long fpProductId;

	private Integer currency;

	private Boolean isAgeDisplay;

	private Boolean isAgeMandatory;

	private Boolean isAssetValueDisplay;

	private Boolean isAssetValueMandatory;

	private Boolean isGeographicalDisplay;

	private Boolean isGeographicalMandatory;

	private Boolean isLoanAmountDisplay;

	private Boolean isLoanAmountMandatory;

	private Boolean isTenureDisplay;

	private Boolean isTenureMandatory;

	private Boolean isYearlyIncomeRangeDisplay;

	private Boolean isYearlyIncomeRangeMandatory;

	private Double maxAge;

	private Double maxAssetValue;

	private Double maxLoanAmount;

	private Double maxYearlyIncomeRange;

	private Double minAge;

	private Double minAssetValue;

	private Double minLoanAmount;

	private Double minYearlyIncomeRange;

	private Integer tenureMonth;

	private Integer tenureYear;

	public LasParameterRequest() {
	}

	public Long getFpProductId() {
		return this.fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}


	public Integer getCurrency() {
		return this.currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Boolean getIsAgeDisplay() {
		return this.isAgeDisplay;
	}

	public void setIsAgeDisplay(Boolean isAgeDisplay) {
		this.isAgeDisplay = isAgeDisplay;
	}

	public Boolean getIsAgeMandatory() {
		return this.isAgeMandatory;
	}

	public void setIsAgeMandatory(Boolean isAgeMandatory) {
		this.isAgeMandatory = isAgeMandatory;
	}

	public Boolean getIsAssetValueDisplay() {
		return this.isAssetValueDisplay;
	}

	public void setIsAssetValueDisplay(Boolean isAssetValueDisplay) {
		this.isAssetValueDisplay = isAssetValueDisplay;
	}

	public Boolean getIsAssetValueMandatory() {
		return this.isAssetValueMandatory;
	}

	public void setIsAssetValueMandatory(Boolean isAssetValueMandatory) {
		this.isAssetValueMandatory = isAssetValueMandatory;
	}

	public Boolean getIsGeographicalDisplay() {
		return this.isGeographicalDisplay;
	}

	public void setIsGeographicalDisplay(Boolean isGeographicalDisplay) {
		this.isGeographicalDisplay = isGeographicalDisplay;
	}

	public Boolean getIsGeographicalMandatory() {
		return this.isGeographicalMandatory;
	}

	public void setIsGeographicalMandatory(Boolean isGeographicalMandatory) {
		this.isGeographicalMandatory = isGeographicalMandatory;
	}

	public Boolean getIsLoanAmountDisplay() {
		return this.isLoanAmountDisplay;
	}

	public void setIsLoanAmountDisplay(Boolean isLoanAmountDisplay) {
		this.isLoanAmountDisplay = isLoanAmountDisplay;
	}

	public Boolean getIsLoanAmountMandatory() {
		return this.isLoanAmountMandatory;
	}

	public void setIsLoanAmountMandatory(Boolean isLoanAmountMandatory) {
		this.isLoanAmountMandatory = isLoanAmountMandatory;
	}

	public Boolean getIsTenureDisplay() {
		return this.isTenureDisplay;
	}

	public void setIsTenureDisplay(Boolean isTenureDisplay) {
		this.isTenureDisplay = isTenureDisplay;
	}

	public Boolean getIsTenureMandatory() {
		return this.isTenureMandatory;
	}

	public void setIsTenureMandatory(Boolean isTenureMandatory) {
		this.isTenureMandatory = isTenureMandatory;
	}

	public Boolean getIsYearlyIncomeRangeDisplay() {
		return this.isYearlyIncomeRangeDisplay;
	}

	public void setIsYearlyIncomeRangeDisplay(Boolean isYearlyIncomeRangeDisplay) {
		this.isYearlyIncomeRangeDisplay = isYearlyIncomeRangeDisplay;
	}

	public Boolean getIsYearlyIncomeRangeMandatory() {
		return this.isYearlyIncomeRangeMandatory;
	}

	public void setIsYearlyIncomeRangeMandatory(Boolean isYearlyIncomeRangeMandatory) {
		this.isYearlyIncomeRangeMandatory = isYearlyIncomeRangeMandatory;
	}

	public Double getMaxAge() {
		return this.maxAge;
	}

	public void setMaxAge(Double maxAge) {
		this.maxAge = maxAge;
	}

	public Double getMaxAssetValue() {
		return this.maxAssetValue;
	}

	public void setMaxAssetValue(Double maxAssetValue) {
		this.maxAssetValue = maxAssetValue;
	}

	public Double getMaxLoanAmount() {
		return this.maxLoanAmount;
	}

	public void setMaxLoanAmount(Double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public Double getMaxYearlyIncomeRange() {
		return this.maxYearlyIncomeRange;
	}

	public void setMaxYearlyIncomeRange(Double maxYearlyIncomeRange) {
		this.maxYearlyIncomeRange = maxYearlyIncomeRange;
	}

	public Double getMinAge() {
		return this.minAge;
	}

	public void setMinAge(Double minAge) {
		this.minAge = minAge;
	}

	public Double getMinAssetValue() {
		return this.minAssetValue;
	}

	public void setMinAssetValue(Double minAssetValue) {
		this.minAssetValue = minAssetValue;
	}

	public Double getMinLoanAmount() {
		return this.minLoanAmount;
	}

	public void setMinLoanAmount(Double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public Double getMinYearlyIncomeRange() {
		return this.minYearlyIncomeRange;
	}

	public void setMinYearlyIncomeRange(Double minYearlyIncomeRange) {
		this.minYearlyIncomeRange = minYearlyIncomeRange;
	}

	public Integer getTenureMonth() {
		return this.tenureMonth;
	}

	public void setTenureMonth(Integer tenureMonth) {
		this.tenureMonth = tenureMonth;
	}

	public Integer getTenureYear() {
		return this.tenureYear;
	}

	public void setTenureYear(Integer tenureYear) {
		this.tenureYear = tenureYear;
	}

}