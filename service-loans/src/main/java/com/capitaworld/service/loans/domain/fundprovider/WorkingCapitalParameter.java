package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fp_working_capital_details database table.
 * 
 */
@Entity
@Table(name="fp_working_capital_details")
@PrimaryKeyJoinColumn(name="fp_product_id",referencedColumnName="fp_product_id")
public class WorkingCapitalParameter extends ProductMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@OneToOne
	@JoinColumn(name="fp_product_id")
	private ProductMaster fpProductId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private Integer currency;

	private Integer denomination;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="is_collateral_display")
	private Boolean isCollateralDisplay=false;

	@Column(name="is_collateral_mandatory")
	private Boolean isCollateralMandatory=false;

	@Column(name="is_credit_rating_display")
	private Boolean isCreditRatingDisplay=false;

	@Column(name="is_credit_rating_mandatory")
	private Boolean isCreditRatingMandatory=false;

	@Column(name="is_debt_equity_display")
	private Boolean isDebtEquityDisplay=false;

	@Column(name="is_debt_equity_mandatory")
	private Boolean isDebtEquityMandatory=false;

	@Column(name="is_establishment_display")
	private Boolean isEstablishmentDisplay=false;

	@Column(name="is_establishment_mandatory")
	private Boolean isEstablishmentMandatory=false;

	@Column(name="is_geographical_display")
	private Boolean isGeographicalDisplay=false;

	@Column(name="is_geographical_mandatory")
	private Boolean isGeographicalMandatory=false;

	@Column(name="is_industry_sector_display")
	private Boolean isIndustrySectorDisplay=false;

	@Column(name="is_industry_sector_mandatory")
	private Boolean isIndustrySectorMandatory=false;

	@Column(name="is_investment_size_display")
	private Boolean isInvestmentSizeDisplay=false;

	@Column(name="is_investment_size_mandatory")
	private Boolean isInvestmentSizeMandatory=false;

	@Column(name="is_networth_display")
	private Boolean isNetworthDisplay=false;

	@Column(name="is_networth_mandatory")
	private Boolean isNetworthMandatory=false;

	@Column(name="is_past_year_turnover_display")
	private Boolean isPastYearTurnoverDisplay=false;

	@Column(name="is_past_year_turnover_mandatory")
	private Boolean isPastYearTurnoverMandatory=false;

	@Column(name="is_profitability_history_display")
	private Boolean isProfitabilityHistoryDisplay=false;

	@Column(name="is_profitability_history_mandatory")
	private Boolean isProfitabilityHistoryMandatory=false;

	@Column(name="is_tenure_display")
	private Boolean isTenureDisplay=false;

	@Column(name="is_tenure_mandatory")
	private Boolean isTenureMandatory=false;

	
	@Column(name="long_term_credit_rating")
	private Integer longTermCreditRating;

	@Column(name="max_age_establishment")
	private Integer maxAgeEstablishment;

	@Column(name="max_collateral")
	private Double maxCollateral;

	@Column(name="max_debt_equity")
	private Double maxDebtEquity;

	@Column(name="max_invest_size")
	private Double maxInvestSize;

	@Column(name="max_networth")
	private Double maxNetworth;

	@Column(name="max_past_turnover")
	private Double maxPastTurnover;

	@Column(name="max_tenure")
	private Integer maxTenure;

	@Column(name="min_age_establishment")
	private Integer minAgeEstablishment;

	@Column(name="min_collateral")
	private Double minCollateral;

	@Column(name="min_debt_equity")
	private Double minDebtEquity;

	@Column(name="min_invest_size")
	private Double minInvestSize;

	@Column(name="min_networth")
	private Double minNetworth;

	@Column(name="min_past_turnover")
	private Double minPastTurnover;

	@Column(name="min_tenure")
	private Integer minTenure;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="profitability_history")
	private String profitabilityHistory;

	@Column(name="short_term_credit_rating")
	private Integer shortTermCreditRating;

	@Column(name="uninterested_industry")
	private Long uninterestedIndustry;

	public WorkingCapitalParameter() {
	}

	public ProductMaster getFpProductId() {
		return this.fpProductId;
	}

	public void setFpProductId(ProductMaster fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
		return this.longTermCreditRating;
	}

	public void setLongTermCreditRating(Integer longTermCreditRating) {
		this.longTermCreditRating = longTermCreditRating;
	}

	public Integer getMaxAgeEstablishment() {
		return this.maxAgeEstablishment;
	}

	public void setMaxAgeEstablishment(Integer maxAgeEstablishment) {
		this.maxAgeEstablishment = maxAgeEstablishment;
	}

	public Double getMaxCollateral() {
		return this.maxCollateral;
	}

	public void setMaxCollateral(Double maxCollateral) {
		this.maxCollateral = maxCollateral;
	}

	public Double getMaxDebtEquity() {
		return this.maxDebtEquity;
	}

	public void setMaxDebtEquity(Double maxDebtEquity) {
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

	public Integer getMaxTenure() {
		return this.maxTenure;
	}

	public void setMaxTenure(Integer maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Integer getMinAgeEstablishment() {
		return this.minAgeEstablishment;
	}

	public void setMinAgeEstablishment(Integer minAgeEstablishment) {
		this.minAgeEstablishment = minAgeEstablishment;
	}

	public Double getMinCollateral() {
		return this.minCollateral;
	}

	public void setMinCollateral(Double minCollateral) {
		this.minCollateral = minCollateral;
	}

	public Double getMinDebtEquity() {
		return this.minDebtEquity;
	}

	public void setMinDebtEquity(Double minDebtEquity) {
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

	public Integer getMinTenure() {
		return this.minTenure;
	}

	public void setMinTenure(Integer minTenure) {
		this.minTenure = minTenure;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getProfitabilityHistory() {
		return this.profitabilityHistory;
	}

	public void setProfitabilityHistory(String profitabilityHistory) {
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