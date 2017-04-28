package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * The persistent class for the fp_term_loan_details database table.
 * 
 */
public class TermLoanParameterRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7147132253130949658L;
	private Long fpProductId;

	private Integer currency;

	private Integer denomination;

	private Boolean isActive;

	private Boolean isCollateralDisplay;

	private Boolean isCollateralMandatory;

	private Boolean isCreditRatingDisplay;

	private Boolean isCreditRatingMandatory;

	private Boolean isDebtEquityDisplay;

	private Boolean isDebtEquityMandatory;

	private Boolean isEstablishmentDisplay;

	private Boolean isEstablishmentMandatory;

	private Boolean isGeographicalDisplay;

	private Boolean isGeographicalMandatory;

	private Boolean isIndustrySectorDisplay;

	private Boolean isIndustrySectorMandatory;

	private Boolean isInvestmentSizeDisplay;

	private Boolean isInvestmentSizeMandatory;

	private Boolean isNetworthDisplay;

	private Boolean isNetworthMandatory;

	private Boolean isPastYearTurnoverDisplay;

	private Boolean isPastYearTurnoverMandatory;

	private Boolean isProfitabilityHistoryDisplay;

	private Boolean isProfitabilityHistoryMandatory;

	private Boolean isTenureDisplay;

	private Boolean isTenureMandatory;

	private Integer LongTermCreditRating;

	private Integer maxAgeEstablishment;

	private Integer maxCollateral;

	private Integer maxDebtEquity;

	private Double maxInvestSize;

	private Double maxNetworth;

	private Double maxPastTurnover;

	private Double maxTenure;

	private Integer minAgeEstablishment;

	private Integer minCollateral;

	private Integer minDebtEquity;

	private Double minInvestSize;

	private Double minNetworth;

	private Double minPastTurnover;

	private Double minTenure;

	private Integer profitabilityHistory;

	private Integer shortTermCreditRating;

	private Long uninterestedIndustry;

	public TermLoanParameterRequest() {
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

	public Integer getDenomination() {
		return this.denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsCollateralDisplay() {
		return this.isCollateralDisplay;
	}

	public void setIsCollateralDisplay(Boolean isCollateralDisplay) {
		this.isCollateralDisplay = isCollateralDisplay;
	}

	public Boolean getIsCollateralMandatory() {
		return this.isCollateralMandatory;
	}

	public void setIsCollateralMandatory(Boolean isCollateralMandatory) {
		this.isCollateralMandatory = isCollateralMandatory;
	}

	public Boolean getIsCreditRatingDisplay() {
		return this.isCreditRatingDisplay;
	}

	public void setIsCreditRatingDisplay(Boolean isCreditRatingDisplay) {
		this.isCreditRatingDisplay = isCreditRatingDisplay;
	}

	public Boolean getIsCreditRatingMandatory() {
		return this.isCreditRatingMandatory;
	}

	public void setIsCreditRatingMandatory(Boolean isCreditRatingMandatory) {
		this.isCreditRatingMandatory = isCreditRatingMandatory;
	}

	public Boolean getIsDebtEquityDisplay() {
		return this.isDebtEquityDisplay;
	}

	public void setIsDebtEquityDisplay(Boolean isDebtEquityDisplay) {
		this.isDebtEquityDisplay = isDebtEquityDisplay;
	}

	public Boolean getIsDebtEquityMandatory() {
		return this.isDebtEquityMandatory;
	}

	public void setIsDebtEquityMandatory(Boolean isDebtEquityMandatory) {
		this.isDebtEquityMandatory = isDebtEquityMandatory;
	}

	public Boolean getIsEstablishmentDisplay() {
		return this.isEstablishmentDisplay;
	}

	public void setIsEstablishmentDisplay(Boolean isEstablishmentDisplay) {
		this.isEstablishmentDisplay = isEstablishmentDisplay;
	}

	public Boolean getIsEstablishmentMandatory() {
		return this.isEstablishmentMandatory;
	}

	public void setIsEstablishmentMandatory(Boolean isEstablishmentMandatory) {
		this.isEstablishmentMandatory = isEstablishmentMandatory;
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

	public Boolean getIsIndustrySectorDisplay() {
		return this.isIndustrySectorDisplay;
	}

	public void setIsIndustrySectorDisplay(Boolean isIndustrySectorDisplay) {
		this.isIndustrySectorDisplay = isIndustrySectorDisplay;
	}

	public Boolean getIsIndustrySectorMandatory() {
		return this.isIndustrySectorMandatory;
	}

	public void setIsIndustrySectorMandatory(Boolean isIndustrySectorMandatory) {
		this.isIndustrySectorMandatory = isIndustrySectorMandatory;
	}

	public Boolean getIsInvestmentSizeDisplay() {
		return this.isInvestmentSizeDisplay;
	}

	public void setIsInvestmentSizeDisplay(Boolean isInvestmentSizeDisplay) {
		this.isInvestmentSizeDisplay = isInvestmentSizeDisplay;
	}

	public Boolean getIsInvestmentSizeMandatory() {
		return this.isInvestmentSizeMandatory;
	}

	public void setIsInvestmentSizeMandatory(Boolean isInvestmentSizeMandatory) {
		this.isInvestmentSizeMandatory = isInvestmentSizeMandatory;
	}

	public Boolean getIsNetworthDisplay() {
		return this.isNetworthDisplay;
	}

	public void setIsNetworthDisplay(Boolean isNetworthDisplay) {
		this.isNetworthDisplay = isNetworthDisplay;
	}

	public Boolean getIsNetworthMandatory() {
		return this.isNetworthMandatory;
	}

	public void setIsNetworthMandatory(Boolean isNetworthMandatory) {
		this.isNetworthMandatory = isNetworthMandatory;
	}

	public Boolean getIsPastYearTurnoverDisplay() {
		return this.isPastYearTurnoverDisplay;
	}

	public void setIsPastYearTurnoverDisplay(Boolean isPastYearTurnoverDisplay) {
		this.isPastYearTurnoverDisplay = isPastYearTurnoverDisplay;
	}

	public Boolean getIsPastYearTurnoverMandatory() {
		return this.isPastYearTurnoverMandatory;
	}

	public void setIsPastYearTurnoverMandatory(Boolean isPastYearTurnoverMandatory) {
		this.isPastYearTurnoverMandatory = isPastYearTurnoverMandatory;
	}

	public Boolean getIsProfitabilityHistoryDisplay() {
		return this.isProfitabilityHistoryDisplay;
	}

	public void setIsProfitabilityHistoryDisplay(Boolean isProfitabilityHistoryDisplay) {
		this.isProfitabilityHistoryDisplay = isProfitabilityHistoryDisplay;
	}

	public Boolean getIsProfitabilityHistoryMandatory() {
		return this.isProfitabilityHistoryMandatory;
	}

	public void setIsProfitabilityHistoryMandatory(Boolean isProfitabilityHistoryMandatory) {
		this.isProfitabilityHistoryMandatory = isProfitabilityHistoryMandatory;
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

	public Integer getLongTermCreditRating() {
		return this.LongTermCreditRating;
	}

	public void setLongTermCreditRating(Integer LongTermCreditRating) {
		this.LongTermCreditRating = LongTermCreditRating;
	}

	public Integer getMaxAgeEstablishment() {
		return this.maxAgeEstablishment;
	}

	public void setMaxAgeEstablishment(Integer maxAgeEstablishment) {
		this.maxAgeEstablishment = maxAgeEstablishment;
	}

	public Integer getMaxCollateral() {
		return this.maxCollateral;
	}

	public void setMaxCollateral(Integer maxCollateral) {
		this.maxCollateral = maxCollateral;
	}

	public Integer getMaxDebtEquity() {
		return this.maxDebtEquity;
	}

	public void setMaxDebtEquity(Integer maxDebtEquity) {
		this.maxDebtEquity = maxDebtEquity;
	}

	public Double getMaxInvestSize() {
		return this.maxInvestSize;
	}

	public void setMaxInvestSize(Double maxInvestSize) {
		this.maxInvestSize = maxInvestSize;
	}

	public Double getMaxNetworth() {
		return this.maxNetworth;
	}

	public void setMaxNetworth(Double maxNetworth) {
		this.maxNetworth = maxNetworth;
	}

	public Double getMaxPastTurnover() {
		return this.maxPastTurnover;
	}

	public void setMaxPastTurnover(Double maxPastTurnover) {
		this.maxPastTurnover = maxPastTurnover;
	}

	public Double getMaxTenure() {
		return this.maxTenure;
	}

	public void setMaxTenure(Double maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Integer getMinAgeEstablishment() {
		return this.minAgeEstablishment;
	}

	public void setMinAgeEstablishment(Integer minAgeEstablishment) {
		this.minAgeEstablishment = minAgeEstablishment;
	}

	public Integer getMinCollateral() {
		return this.minCollateral;
	}

	public void setMinCollateral(Integer minCollateral) {
		this.minCollateral = minCollateral;
	}

	public Integer getMinDebtEquity() {
		return this.minDebtEquity;
	}

	public void setMinDebtEquity(Integer minDebtEquity) {
		this.minDebtEquity = minDebtEquity;
	}

	public Double getMinInvestSize() {
		return this.minInvestSize;
	}

	public void setMinInvestSize(Double minInvestSize) {
		this.minInvestSize = minInvestSize;
	}

	public Double getMinNetworth() {
		return this.minNetworth;
	}

	public void setMinNetworth(Double minNetworth) {
		this.minNetworth = minNetworth;
	}

	public Double getMinPastTurnover() {
		return this.minPastTurnover;
	}

	public void setMinPastTurnover(Double minPastTurnover) {
		this.minPastTurnover = minPastTurnover;
	}

	public Double getMinTenure() {
		return this.minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
	}

	public Integer getProfitabilityHistory() {
		return this.profitabilityHistory;
	}

	public void setProfitabilityHistory(Integer profitabilityHistory) {
		this.profitabilityHistory = profitabilityHistory;
	}

	public Integer getShortTermCreditRating() {
		return this.shortTermCreditRating;
	}

	public void setShortTermCreditRating(Integer shortTermCreditRating) {
		this.shortTermCreditRating = shortTermCreditRating;
	}

	public Long getUninterestedIndustry() {
		return this.uninterestedIndustry;
	}

	public void setUninterestedIndustry(Long uninterestedIndustry) {
		this.uninterestedIndustry = uninterestedIndustry;
	}

}