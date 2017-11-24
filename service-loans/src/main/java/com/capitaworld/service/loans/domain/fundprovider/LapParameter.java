package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the fp_loan_against_property_details database table.
 * 
 */
@Entity
@Table(name="fp_loan_against_property_details")
public class LapParameter extends ProductMaster implements Serializable {
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

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="is_age_display")
	private Boolean isAgeDisplay=false;

	@Column(name="is_age_mandatory")
	private Boolean isAgeMandatory=false;

	@Column(name="is_asset_value_display")
	private Boolean isAssetValueDisplay=false;

	@Column(name="is_asset_value_mandatory")
	private Boolean isAssetValueMandatory=false;

	@Column(name="is_geographical_display")
	private Boolean isGeographicalDisplay=false;

	@Column(name="is_geographical_mandatory")
	private Boolean isGeographicalMandatory=false;

	@Column(name="is_loan_amount_display")
	private Boolean isLoanAmountDisplay=false;

	@Column(name="is_loan_amount_mandatory")
	private Boolean isLoanAmountMandatory=false;

	@Column(name="is_tenure_display")
	private Boolean isTenureDisplay=false;

	@Column(name="is_tenure_mandatory")
	private Boolean isTenureMandatory=false;

	@Column(name="is_yearly_income_range_display")
	private Boolean isYearlyIncomeRangeDisplay=false;

	@Column(name="is_yearly_income_range_mandatory")
	private Boolean isYearlyIncomeRangeMandatory=false;

	@Column(name="max_age")
	private Double maxAge;

	@Column(name="max_asset_value")
	private Double maxAssetValue;

	@Column(name="max_loan_amount")
	private Double maxLoanAmount;

	@Column(name="max_tenure")
	private Double maxTenure;

	@Column(name="max_yearly_income_range")
	private Double maxYearlyIncomeRange;

	@Column(name="min_age")
	private Double minAge;

	@Column(name="min_asset_value")
	private Double minAssetValue;

	@Column(name="min_loan_amount")
	private Double minLoanAmount;

	@Column(name="min_tenure")
	private Double minTenure;

	@Column(name="min_yearly_income_range")
	private Double minYearlyIncomeRange;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	/*//FP NEW REQUIREMENT
	@Column(name="employment_id")
	private Integer employmentId;
	
	@Column(name="employment_place_id")
	private Integer employmentPlaceId;
	
	@Column(name="property_type_id")
	private Integer propertyTypeId;
	
	@Column(name="min_current_job_experience")
	private Integer mincurrentJobExperience;
	
	@Column(name="max_current_job_experience")
	private Integer maxCurrentJobExperience;
	
	@Column(name="min_total_job_experience")
	private Integer minTotalJobExperience;
	
	@Column(name="max_total_job_experience")
	private Integer maxTotalJobExperience;
	
	@Column(name="foir")
	private Double foir;
	
	@Column(name="min_total_business_experience")
	private Integer minTotalBusinessExperience;
	
	@Column(name="max_total_business_experience")
	private Integer maxTotalBusinessExperience;
	
	@Column(name="min_cash_profit_yearly")
	private Integer minCashProfitYearly;
	
	@Column(name="max_cash_profit_yearly")
	private Integer maxCashProfitYearly;
	
	@Column(name="pat_previous_year")
	private Double patPreviousYear;
	
	@Column(name="pat_current_year")
	private Double patCurrentYear;
	
	@Column(name="is_pat_previous_year_selected")
	private Boolean isPatPreviousYearSelected = false;
	
	@Column(name="is_pat_current_year_selected")
	private Boolean isPatCurrentYearSelected = false;
	
	@Column(name="is_pat_average_selected")
	private Boolean isPatAverageSelected = false;
	
	@Column(name="depreciation_previous_year")
	private Double depreciationPreviousYear;
	
	@Column(name="depreciation_current_year")
	private Double depreciationCurrentYear;
	
	@Column(name="is_depreciation_previous_year_selected")
	private Boolean isDepreciationPreviousYearSelected = false;
	
	@Column(name="is_depreciation_current_year_selected")
	private Boolean isDepreciationCurrentYearSelected = false;
	
	@Column(name="remuneration_previous_year")
	private Double remunerationPreviousYear;
	
	@Column(name="remuneration_current_year")
	private Double remunerationCurrentYear;
	
	@Column(name="is_remuneration_previous_year_selected")
	private Boolean isRemunerationPreviousYearSelected = false;
	
	@Column(name="is_remuneration_current_year_selected")
	private Boolean isRemunerationCurrentYearSelected = false;
	
	@Column(name="is_employment_type_display")
	private Boolean isEmploymentTypeDisplay=false;

	@Column(name="is_employment_type_mandatory")
	private Boolean isEmploymentTypeMandatory=false;
	
	@Column(name="is_employment_place_display")
	private Boolean isEmploymentPlaceDisplay=false;

	@Column(name="is_employment_place_mandatory")
	private Boolean isEmploymentPlaceMandatory=false;
	
	@Column(name="is_property_type_display")
	private Boolean isPropertyTypeDisplay=false;

	@Column(name="is_property_type_mandatory")
	private Boolean isPropertyTypeMandatory=false;
	
	@Column(name="is_current_job_experience_display")
	private Boolean isCurrentJobExperienceDisplay=false;

	@Column(name="is_current_job_experience_mandatory")
	private Boolean isCurrentJobExperienceMandatory=false;
	
	@Column(name="is_total_job_experience_display")
	private Boolean isTotalJobExperienceDisplay=false;

	@Column(name="is_total_job_experience_mandatory")
	private Boolean isTotalJobExperienceMandatory=false;
	
	@Column(name="is_total_business_experience_display")
	private Boolean isTotalBusinessExperienceDisplay=false;

	@Column(name="is_total_business_experience_mandatory")
	private Boolean isTotalBusinessExperienceMandatory=false;
	
	@Column(name="is_cash_profit_display")
	private Boolean isCashProfitDisplay=false;
	
	@Column(name="is_cash_profit_mandatory")
	private Boolean isCashProfitMandatory=false;*/

	public LapParameter() {
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public Double getMaxTenure() {
		return this.maxTenure;
	}

	public void setMaxTenure(Double maxTenure) {
		this.maxTenure = maxTenure;
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

	public Double getMinTenure() {
		return this.minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
	}

	public Double getMinYearlyIncomeRange() {
		return this.minYearlyIncomeRange;
	}

	public void setMinYearlyIncomeRange(Double minYearlyIncomeRange) {
		this.minYearlyIncomeRange = minYearlyIncomeRange;
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

	/*public Integer getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Integer employmentId) {
		this.employmentId = employmentId;
	}

	public Integer getEmploymentPlaceId() {
		return employmentPlaceId;
	}

	public void setEmploymentPlaceId(Integer employmentPlaceId) {
		this.employmentPlaceId = employmentPlaceId;
	}

	public Integer getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(Integer propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public Integer getMincurrentJobExperience() {
		return mincurrentJobExperience;
	}

	public void setMincurrentJobExperience(Integer mincurrentJobExperience) {
		this.mincurrentJobExperience = mincurrentJobExperience;
	}

	public Integer getMaxCurrentJobExperience() {
		return maxCurrentJobExperience;
	}

	public void setMaxCurrentJobExperience(Integer maxCurrentJobExperience) {
		this.maxCurrentJobExperience = maxCurrentJobExperience;
	}

	public Integer getMinTotalJobExperience() {
		return minTotalJobExperience;
	}

	public void setMinTotalJobExperience(Integer minTotalJobExperience) {
		this.minTotalJobExperience = minTotalJobExperience;
	}

	public Integer getMaxTotalJobExperience() {
		return maxTotalJobExperience;
	}

	public void setMaxTotalJobExperience(Integer maxTotalJobExperience) {
		this.maxTotalJobExperience = maxTotalJobExperience;
	}

	public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public Integer getMinTotalBusinessExperience() {
		return minTotalBusinessExperience;
	}

	public void setMinTotalBusinessExperience(Integer minTotalBusinessExperience) {
		this.minTotalBusinessExperience = minTotalBusinessExperience;
	}

	public Integer getMaxTotalBusinessExperience() {
		return maxTotalBusinessExperience;
	}

	public void setMaxTotalBusinessExperience(Integer maxTotalBusinessExperience) {
		this.maxTotalBusinessExperience = maxTotalBusinessExperience;
	}

	public Integer getMinCashProfitYearly() {
		return minCashProfitYearly;
	}

	public void setMinCashProfitYearly(Integer minCashProfitYearly) {
		this.minCashProfitYearly = minCashProfitYearly;
	}

	public Integer getMaxCashProfitYearly() {
		return maxCashProfitYearly;
	}

	public void setMaxCashProfitYearly(Integer maxCashProfitYearly) {
		this.maxCashProfitYearly = maxCashProfitYearly;
	}

	public Double getPatPreviousYear() {
		return patPreviousYear;
	}

	public void setPatPreviousYear(Double patPreviousYear) {
		this.patPreviousYear = patPreviousYear;
	}

	public Double getPatCurrentYear() {
		return patCurrentYear;
	}

	public void setPatCurrentYear(Double patCurrentYear) {
		this.patCurrentYear = patCurrentYear;
	}

	public Boolean getIsPatPreviousYearSelected() {
		return isPatPreviousYearSelected;
	}

	public void setIsPatPreviousYearSelected(Boolean isPatPreviousYearSelected) {
		this.isPatPreviousYearSelected = isPatPreviousYearSelected;
	}

	public Boolean getIsPatCurrentYearSelected() {
		return isPatCurrentYearSelected;
	}

	public void setIsPatCurrentYearSelected(Boolean isPatCurrentYearSelected) {
		this.isPatCurrentYearSelected = isPatCurrentYearSelected;
	}

	public Boolean getIsPatAverageSelected() {
		return isPatAverageSelected;
	}

	public void setIsPatAverageSelected(Boolean isPatAverageSelected) {
		this.isPatAverageSelected = isPatAverageSelected;
	}

	public Double getDepreciationPreviousYear() {
		return depreciationPreviousYear;
	}

	public void setDepreciationPreviousYear(Double depreciationPreviousYear) {
		this.depreciationPreviousYear = depreciationPreviousYear;
	}

	public Double getDepreciationCurrentYear() {
		return depreciationCurrentYear;
	}

	public void setDepreciationCurrentYear(Double depreciationCurrentYear) {
		this.depreciationCurrentYear = depreciationCurrentYear;
	}

	public Boolean getIsDepreciationPreviousYearSelected() {
		return isDepreciationPreviousYearSelected;
	}

	public void setIsDepreciationPreviousYearSelected(Boolean isDepreciationPreviousYearSelected) {
		this.isDepreciationPreviousYearSelected = isDepreciationPreviousYearSelected;
	}

	public Boolean getIsDepreciationCurrentYearSelected() {
		return isDepreciationCurrentYearSelected;
	}

	public void setIsDepreciationCurrentYearSelected(Boolean isDepreciationCurrentYearSelected) {
		this.isDepreciationCurrentYearSelected = isDepreciationCurrentYearSelected;
	}

	public Double getRemunerationPreviousYear() {
		return remunerationPreviousYear;
	}

	public void setRemunerationPreviousYear(Double remunerationPreviousYear) {
		this.remunerationPreviousYear = remunerationPreviousYear;
	}

	public Double getRemunerationCurrentYear() {
		return remunerationCurrentYear;
	}

	public void setRemunerationCurrentYear(Double remunerationCurrentYear) {
		this.remunerationCurrentYear = remunerationCurrentYear;
	}

	public Boolean getIsRemunerationPreviousYearSelected() {
		return isRemunerationPreviousYearSelected;
	}

	public void setIsRemunerationPreviousYearSelected(Boolean isRemunerationPreviousYearSelected) {
		this.isRemunerationPreviousYearSelected = isRemunerationPreviousYearSelected;
	}

	public Boolean getIsRemunerationCurrentYearSelected() {
		return isRemunerationCurrentYearSelected;
	}

	public void setIsRemunerationCurrentYearSelected(Boolean isRemunerationCurrentYearSelected) {
		this.isRemunerationCurrentYearSelected = isRemunerationCurrentYearSelected;
	}

	public Boolean getIsEmploymentTypeDisplay() {
		return isEmploymentTypeDisplay;
	}

	public void setIsEmploymentTypeDisplay(Boolean isEmploymentTypeDisplay) {
		this.isEmploymentTypeDisplay = isEmploymentTypeDisplay;
	}

	public Boolean getIsEmploymentTypeMandatory() {
		return isEmploymentTypeMandatory;
	}

	public void setIsEmploymentTypeMandatory(Boolean isEmploymentTypeMandatory) {
		this.isEmploymentTypeMandatory = isEmploymentTypeMandatory;
	}

	public Boolean getIsEmploymentPlaceDisplay() {
		return isEmploymentPlaceDisplay;
	}

	public void setIsEmploymentPlaceDisplay(Boolean isEmploymentPlaceDisplay) {
		this.isEmploymentPlaceDisplay = isEmploymentPlaceDisplay;
	}

	public Boolean getIsEmploymentPlaceMandatory() {
		return isEmploymentPlaceMandatory;
	}

	public void setIsEmploymentPlaceMandatory(Boolean isEmploymentPlaceMandatory) {
		this.isEmploymentPlaceMandatory = isEmploymentPlaceMandatory;
	}

	public Boolean getIsPropertyTypeDisplay() {
		return isPropertyTypeDisplay;
	}

	public void setIsPropertyTypeDisplay(Boolean isPropertyTypeDisplay) {
		this.isPropertyTypeDisplay = isPropertyTypeDisplay;
	}

	public Boolean getIsPropertyTypeMandatory() {
		return isPropertyTypeMandatory;
	}

	public void setIsPropertyTypeMandatory(Boolean isPropertyTypeMandatory) {
		this.isPropertyTypeMandatory = isPropertyTypeMandatory;
	}

	public Boolean getIsCurrentJobExperienceDisplay() {
		return isCurrentJobExperienceDisplay;
	}

	public void setIsCurrentJobExperienceDisplay(Boolean isCurrentJobExperienceDisplay) {
		this.isCurrentJobExperienceDisplay = isCurrentJobExperienceDisplay;
	}

	public Boolean getIsCurrentJobExperienceMandatory() {
		return isCurrentJobExperienceMandatory;
	}

	public void setIsCurrentJobExperienceMandatory(Boolean isCurrentJobExperienceMandatory) {
		this.isCurrentJobExperienceMandatory = isCurrentJobExperienceMandatory;
	}

	public Boolean getIsTotalJobExperienceDisplay() {
		return isTotalJobExperienceDisplay;
	}

	public void setIsTotalJobExperienceDisplay(Boolean isTotalJobExperienceDisplay) {
		this.isTotalJobExperienceDisplay = isTotalJobExperienceDisplay;
	}

	public Boolean getIsTotalJobExperienceMandatory() {
		return isTotalJobExperienceMandatory;
	}

	public void setIsTotalJobExperienceMandatory(Boolean isTotalJobExperienceMandatory) {
		this.isTotalJobExperienceMandatory = isTotalJobExperienceMandatory;
	}

	public Boolean getIsTotalBusinessExperienceDisplay() {
		return isTotalBusinessExperienceDisplay;
	}

	public void setIsTotalBusinessExperienceDisplay(Boolean isTotalBusinessExperienceDisplay) {
		this.isTotalBusinessExperienceDisplay = isTotalBusinessExperienceDisplay;
	}

	public Boolean getIsTotalBusinessExperienceMandatory() {
		return isTotalBusinessExperienceMandatory;
	}

	public void setIsTotalBusinessExperienceMandatory(Boolean isTotalBusinessExperienceMandatory) {
		this.isTotalBusinessExperienceMandatory = isTotalBusinessExperienceMandatory;
	}

	public Boolean getIsCashProfitDisplay() {
		return isCashProfitDisplay;
	}

	public void setIsCashProfitDisplay(Boolean isCashProfitDisplay) {
		this.isCashProfitDisplay = isCashProfitDisplay;
	}

	public Boolean getIsCashProfitMandatory() {
		return isCashProfitMandatory;
	}

	public void setIsCashProfitMandatory(Boolean isCashProfitMandatory) {
		this.isCashProfitMandatory = isCashProfitMandatory;
	}*/

	
}