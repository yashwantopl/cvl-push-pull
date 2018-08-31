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
 * The persistent class for the fp_personal_loan_details database table.
 * 
 */
@Entity
@Table(name="fp_personal_loan_details_temp")
public class PersonalLoanParameterTemp extends ProductMasterTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name="fp_product_id")
	private ProductMasterTemp fpProductId;

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

	@Column(name="max_loan_amount")
	private Double maxLoanAmount;

	@Column(name="max_tenure")
	private Double maxTenure;

	@Column(name="max_yearly_income_range")
	private Double maxYearlyIncomeRange;

	@Column(name="min_age")
	private Double minAge;

	@Column(name="min_loan_amount")
	private Double minLoanAmount;

	@Column(name="min_tenure")
	private Double minTenure;
	
	@Column(name="min_bureau")
	private Double minBureauScore;
	
	@Column(name="max_bureau")
	private Double maxBureauScore;
	
	@Column(name="is_bureau_display")
	private Boolean isBureauScoreDisplay=false;

	@Column(name="is_bureau_mandatory")
	private Boolean isBureauScoreMandatory=false;
	
	@Column(name="min_bureau_days")
	private Double minBureauScoreDays;
	
	@Column(name="max_bureau_days")
	private Double maxBureauScoreDays;
	
	@Column(name="is_bureau_display_days")
	private Boolean isBureauScoreDisplayDays=false;

	@Column(name="is_bureau_mandatory_days")
	private Boolean isBureauScoreMandatoryDays=false;
	
	
	@Column(name="min_risk_score_model")
	private Double minRiskScore;
	
	@Column(name="max_risk_score_model")
	private Double maxRiskScore;
	
	@Column(name="is_risk_score_model_display")
	private Boolean isRiskScoreDisplay=false;

	@Column(name="is_risk_score_model_mandatory")
	private Boolean isRiskScoreMandatory=false;
	

	@Column(name="min_yearly_income_range")
	private Double minYearlyIncomeRange;
	
	@Column(name="min_monthly_income_range")
	private Double minMonthlyIncomeRange;
	
	@Column(name="max_monthly_income_range")
	private Double maxMonthlyIncomeRange;
	
	@Column(name="is_monthly_income_range_display")
	private Boolean isMonthlyIncomeRangeDisplay=false;

	@Column(name="is_monthly_income_range_mandatory")
	private Boolean isMonthlyIncomeRangeMandatory=false;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	
	@Column(name="min_current_job_experience")
	private Integer mincurrentJobExperience;
	
	@Column(name="max_current_job_experience")
	private Integer maxCurrentJobExperience;
	
	@Column(name="min_total_job_experience")
	private Integer minTotalJobExperience;
	
	@Column(name="max_total_job_experience")
	private Integer maxTotalJobExperience;
	
	@Column(name="is_employment_type_display")
	private Boolean isEmploymentTypeDisplay=false;

	@Column(name="is_employment_type_mandatory")
	private Boolean isEmploymentTypeMandatory=false;
	
	@Column(name="is_employment_status_display")
	private Boolean isEmploymentStatusDisplay=false;

	@Column(name="is_employment_status_mandatory")
	private Boolean isEmploymentStatusMandatory=false;
	
	@Column(name="is_credit_rating_display")
	private Boolean isCreditRatingDisplay=false;

	@Column(name="is_credit_rating_mandatory")
	private Boolean isCreditRatingMandatory=false;
	
	
	
	/*//FP NEW REQUIREMENT
	@Column(name="employment_id")
	private Integer employmentId;
	
	@Column(name="employment_place_id")
	private Integer employmentPlaceId;
	
	
	
	@Column(name="foir")
	private Double foir;
	
	
	
	@Column(name="is_employment_place_display")
	private Boolean isEmploymentPlaceDisplay=false;

	@Column(name="is_employment_place_mandatory")
	private Boolean isEmploymentPlaceMandatory=false;
	*/
	
	@Column(name="is_current_job_experience_display")
	private Boolean isCurrentJobExperienceDisplay=false;

	@Column(name="is_current_job_experience_mandatory")
	private Boolean isCurrentJobExperienceMandatory=false;
	
	@Column(name="is_total_job_experience_display")
	private Boolean isTotalJobExperienceDisplay=false;

	@Column(name="is_total_job_experience_mandatory")
	private Boolean isTotalJobExperienceMandatory=false;

	public PersonalLoanParameterTemp() {
	}

	

	public ProductMasterTemp getFpProductId() {
		return fpProductId;
	}



	public void setFpProductId(ProductMasterTemp fpProductId) {
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

	public Double getMinBureauScore() {
		return minBureauScore;
	}

	public void setMinBureauScore(Double minBureauScore) {
		this.minBureauScore = minBureauScore;
	}

	public Double getMaxBureauScore() {
		return maxBureauScore;
	}

	public void setMaxBureauScore(Double maxBureauScore) {
		this.maxBureauScore = maxBureauScore;
	}

	public Boolean getIsBureauScoreDisplay() {
		return isBureauScoreDisplay;
	}

	public void setIsBureauScoreDisplay(Boolean isBureauScoreDisplay) {
		this.isBureauScoreDisplay = isBureauScoreDisplay;
	}

	public Boolean getIsBureauScoreMandatory() {
		return isBureauScoreMandatory;
	}

	public void setIsBureauScoreMandatory(Boolean isBureauScoreMandatory) {
		this.isBureauScoreMandatory = isBureauScoreMandatory;
	}

	public Double getMinBureauScoreDays() {
		return minBureauScoreDays;
	}

	public void setMinBureauScoreDays(Double minBureauScoreDays) {
		this.minBureauScoreDays = minBureauScoreDays;
	}

	public Double getMaxBureauScoreDays() {
		return maxBureauScoreDays;
	}

	public void setMaxBureauScoreDays(Double maxBureauScoreDays) {
		this.maxBureauScoreDays = maxBureauScoreDays;
	}

	public Boolean getIsBureauScoreDisplayDays() {
		return isBureauScoreDisplayDays;
	}

	public void setIsBureauScoreDisplayDays(Boolean isBureauScoreDisplayDays) {
		this.isBureauScoreDisplayDays = isBureauScoreDisplayDays;
	}

	public Boolean getIsBureauScoreMandatoryDays() {
		return isBureauScoreMandatoryDays;
	}

	public void setIsBureauScoreMandatoryDays(Boolean isBureauScoreMandatoryDays) {
		this.isBureauScoreMandatoryDays = isBureauScoreMandatoryDays;
	}

	public Double getMinRiskScore() {
		return minRiskScore;
	}

	public void setMinRiskScore(Double minRiskScore) {
		this.minRiskScore = minRiskScore;
	}

	public Double getMaxRiskScore() {
		return maxRiskScore;
	}

	public void setMaxRiskScore(Double maxRiskScore) {
		this.maxRiskScore = maxRiskScore;
	}

	public Boolean getIsRiskScoreDisplay() {
		return isRiskScoreDisplay;
	}

	public void setIsRiskScoreDisplay(Boolean isRiskScoreDisplay) {
		this.isRiskScoreDisplay = isRiskScoreDisplay;
	}

	public Boolean getIsRiskScoreMandatory() {
		return isRiskScoreMandatory;
	}

	public void setIsRiskScoreMandatory(Boolean isRiskScoreMandatory) {
		this.isRiskScoreMandatory = isRiskScoreMandatory;
	}

	public Double getMinMonthlyIncomeRange() {
		return minMonthlyIncomeRange;
	}

	public void setMinMonthlyIncomeRange(Double minMonthlyIncomeRange) {
		this.minMonthlyIncomeRange = minMonthlyIncomeRange;
	}

	public Double getMaxMonthlyIncomeRange() {
		return maxMonthlyIncomeRange;
	}

	public void setMaxMonthlyIncomeRange(Double maxMonthlyIncomeRange) {
		this.maxMonthlyIncomeRange = maxMonthlyIncomeRange;
	}

	public Boolean getIsMonthlyIncomeRangeDisplay() {
		return isMonthlyIncomeRangeDisplay;
	}

	public void setIsMonthlyIncomeRangeDisplay(Boolean isMonthlyIncomeRangeDisplay) {
		this.isMonthlyIncomeRangeDisplay = isMonthlyIncomeRangeDisplay;
	}

	public Boolean getIsMonthlyIncomeRangeMandatory() {
		return isMonthlyIncomeRangeMandatory;
	}

	public void setIsMonthlyIncomeRangeMandatory(Boolean isMonthlyIncomeRangeMandatory) {
		this.isMonthlyIncomeRangeMandatory = isMonthlyIncomeRangeMandatory;
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

	public Boolean getIsCreditRatingDisplay() {
		return isCreditRatingDisplay;
	}

	public void setIsCreditRatingDisplay(Boolean isCreditRatingDisplay) {
		this.isCreditRatingDisplay = isCreditRatingDisplay;
	}

	public Boolean getIsCreditRatingMandatory() {
		return isCreditRatingMandatory;
	}

	public void setIsCreditRatingMandatory(Boolean isCreditRatingMandatory) {
		this.isCreditRatingMandatory = isCreditRatingMandatory;
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

	public Boolean getIsEmploymentStatusDisplay() {
		return isEmploymentStatusDisplay;
	}

	public void setIsEmploymentStatusDisplay(Boolean isEmploymentStatusDisplay) {
		this.isEmploymentStatusDisplay = isEmploymentStatusDisplay;
	}

	public Boolean getIsEmploymentStatusMandatory() {
		return isEmploymentStatusMandatory;
	}

	public void setIsEmploymentStatusMandatory(Boolean isEmploymentStatusMandatory) {
		this.isEmploymentStatusMandatory = isEmploymentStatusMandatory;
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
	}*/
	
	
	

	
}