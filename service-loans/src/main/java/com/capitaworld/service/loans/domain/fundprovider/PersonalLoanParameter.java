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
@Table(name="fp_personal_loan_details")
public class PersonalLoanParameter extends ProductMaster implements Serializable {
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
	private Boolean isBureauScoreDaysDisplay=false;

	@Column(name="is_bureau_mandatory_days")
	private Boolean isBureauScoreDaysMandatory=false;
	
	
	@Column(name="min_risk_score_model")
	private Double minRiskScoreRetail;
	
	@Column(name="max_risk_score_model")
	private Double maxRiskScoreRetail;
	
	@Column(name="is_risk_score_model_display")
	private Boolean isRiskScoreRetailDisplay=false;

	@Column(name="is_risk_score_model_mandatory")
	private Boolean isRiskScoreRetailMandatory=false;
	

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
	private Double minTotalCurrentJobExp;
	
	@Column(name="max_current_job_experience")
	private Double maxTotalCurrentJobExp;
	
	@Column(name="min_total_job_experience")
	private Double minTotalJobExp;
	
	@Column(name="max_total_job_experience")
	private Double maxTotalJobExp;
	
	@Column(name="is_employment_type_display")
	private Boolean isEmpWithDisplay=false;

	@Column(name="is_employment_type_mandatory")
	private Boolean isEmpWithMandatory=false;
	
	@Column(name="is_employment_status_display")
	private Boolean isEmpStatusDisplay=false;

	@Column(name="is_employment_status_mandatory")
	private Boolean isEmpStatusMandatory=false;
	
	@Column(name="is_credit_rating_display")
	private Boolean isEmpRatingDisplay=false;

	@Column(name="is_credit_rating_mandatory")
	private Boolean isEmpRatingMandatory=false;
	
	@Column(name="user_credit_rating")
	private Integer userCreditRating;
	
	
	
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
	private Boolean isTotalCurrentJobExpDisplay=false;

	@Column(name="is_current_job_experience_mandatory")
	private Boolean isTotalCurrentJobExpMandatory=false;
	
	@Column(name="is_total_job_experience_display")
	private Boolean isTotalJobExpDisplay=false;

	@Column(name="is_total_job_experience_mandatory")
	private Boolean isTotalJobExpMandatory=false;
	 
	@Column(name ="job_id")
	private Long jobId;
	
	@Column(name="is_banking_relation_mandatory")
	private Boolean isBankingRelationMandatory;

	@Column(name="is_banking_relation_display")
	private Boolean isBankingRelationDisplay;
	
	@Column(name="min_bank_relation")
	private Double minBankRelation;

	@Column(name="max_bank_relation")
	private Double maxBankRelation;
	
	@Column(name="is_salary_mode_display")
	private Boolean isSalaryModeDisplay;

	@Column(name="is_salary_mode_mandatory")
	private Boolean isSalaryModeMandatory;
	
	@Column(name="is_salary_ac_type_display")
	private Boolean isSalaryAcTypeDisplay;

	@Column(name="is_salary_ac_type_mandatory")
	private Boolean isSalaryAcTypeMandatory;
	
	@Column(name="salary_ac_type")
	private Integer salaryAcType;

	public ProductMaster getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(ProductMaster fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsAgeDisplay() {
		return isAgeDisplay;
	}

	public void setIsAgeDisplay(Boolean isAgeDisplay) {
		this.isAgeDisplay = isAgeDisplay;
	}

	public Boolean getIsAgeMandatory() {
		return isAgeMandatory;
	}

	public void setIsAgeMandatory(Boolean isAgeMandatory) {
		this.isAgeMandatory = isAgeMandatory;
	}

	public Boolean getIsGeographicalDisplay() {
		return isGeographicalDisplay;
	}

	public void setIsGeographicalDisplay(Boolean isGeographicalDisplay) {
		this.isGeographicalDisplay = isGeographicalDisplay;
	}

	public Boolean getIsGeographicalMandatory() {
		return isGeographicalMandatory;
	}

	public void setIsGeographicalMandatory(Boolean isGeographicalMandatory) {
		this.isGeographicalMandatory = isGeographicalMandatory;
	}

	public Boolean getIsLoanAmountDisplay() {
		return isLoanAmountDisplay;
	}

	public void setIsLoanAmountDisplay(Boolean isLoanAmountDisplay) {
		this.isLoanAmountDisplay = isLoanAmountDisplay;
	}

	public Boolean getIsLoanAmountMandatory() {
		return isLoanAmountMandatory;
	}

	public void setIsLoanAmountMandatory(Boolean isLoanAmountMandatory) {
		this.isLoanAmountMandatory = isLoanAmountMandatory;
	}

	public Boolean getIsTenureDisplay() {
		return isTenureDisplay;
	}

	public void setIsTenureDisplay(Boolean isTenureDisplay) {
		this.isTenureDisplay = isTenureDisplay;
	}

	public Boolean getIsTenureMandatory() {
		return isTenureMandatory;
	}

	public void setIsTenureMandatory(Boolean isTenureMandatory) {
		this.isTenureMandatory = isTenureMandatory;
	}

	public Boolean getIsYearlyIncomeRangeDisplay() {
		return isYearlyIncomeRangeDisplay;
	}

	public void setIsYearlyIncomeRangeDisplay(Boolean isYearlyIncomeRangeDisplay) {
		this.isYearlyIncomeRangeDisplay = isYearlyIncomeRangeDisplay;
	}

	public Boolean getIsYearlyIncomeRangeMandatory() {
		return isYearlyIncomeRangeMandatory;
	}

	public void setIsYearlyIncomeRangeMandatory(Boolean isYearlyIncomeRangeMandatory) {
		this.isYearlyIncomeRangeMandatory = isYearlyIncomeRangeMandatory;
	}

	public Double getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Double maxAge) {
		this.maxAge = maxAge;
	}

	public Double getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(Double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public Double getMaxTenure() {
		return maxTenure;
	}

	public void setMaxTenure(Double maxTenure) {
		this.maxTenure = maxTenure;
	}

	public Double getMaxYearlyIncomeRange() {
		return maxYearlyIncomeRange;
	}

	public void setMaxYearlyIncomeRange(Double maxYearlyIncomeRange) {
		this.maxYearlyIncomeRange = maxYearlyIncomeRange;
	}

	public Double getMinAge() {
		return minAge;
	}

	public void setMinAge(Double minAge) {
		this.minAge = minAge;
	}

	public Double getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(Double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
	}

	public Double getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
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

	public Boolean getIsBureauScoreDaysDisplay() {
		return isBureauScoreDaysDisplay;
	}

	public void setIsBureauScoreDaysDisplay(Boolean isBureauScoreDaysDisplay) {
		this.isBureauScoreDaysDisplay = isBureauScoreDaysDisplay;
	}

	public Boolean getIsBureauScoreDaysMandatory() {
		return isBureauScoreDaysMandatory;
	}

	public void setIsBureauScoreDaysMandatory(Boolean isBureauScoreDaysMandatory) {
		this.isBureauScoreDaysMandatory = isBureauScoreDaysMandatory;
	}

	public Double getMinRiskScoreRetail() {
		return minRiskScoreRetail;
	}

	public void setMinRiskScoreRetail(Double minRiskScoreRetail) {
		this.minRiskScoreRetail = minRiskScoreRetail;
	}

	public Double getMaxRiskScoreRetail() {
		return maxRiskScoreRetail;
	}

	public void setMaxRiskScoreRetail(Double maxRiskScoreRetail) {
		this.maxRiskScoreRetail = maxRiskScoreRetail;
	}

	public Boolean getIsRiskScoreRetailDisplay() {
		return isRiskScoreRetailDisplay;
	}

	public void setIsRiskScoreRetailDisplay(Boolean isRiskScoreRetailDisplay) {
		this.isRiskScoreRetailDisplay = isRiskScoreRetailDisplay;
	}

	public Boolean getIsRiskScoreRetailMandatory() {
		return isRiskScoreRetailMandatory;
	}

	public void setIsRiskScoreRetailMandatory(Boolean isRiskScoreRetailMandatory) {
		this.isRiskScoreRetailMandatory = isRiskScoreRetailMandatory;
	}

	public Double getMinYearlyIncomeRange() {
		return minYearlyIncomeRange;
	}

	public void setMinYearlyIncomeRange(Double minYearlyIncomeRange) {
		this.minYearlyIncomeRange = minYearlyIncomeRange;
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

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getMinTotalCurrentJobExp() {
		return minTotalCurrentJobExp;
	}

	public void setMinTotalCurrentJobExp(Double minTotalCurrentJobExp) {
		this.minTotalCurrentJobExp = minTotalCurrentJobExp;
	}

	public Double getMaxTotalCurrentJobExp() {
		return maxTotalCurrentJobExp;
	}

	public void setMaxTotalCurrentJobExp(Double maxTotalCurrentJobExp) {
		this.maxTotalCurrentJobExp = maxTotalCurrentJobExp;
	}

	public Double getMinTotalJobExp() {
		return minTotalJobExp;
	}

	public void setMinTotalJobExp(Double minTotalJobExp) {
		this.minTotalJobExp = minTotalJobExp;
	}

	public Double getMaxTotalJobExp() {
		return maxTotalJobExp;
	}

	public void setMaxTotalJobExp(Double maxTotalJobExp) {
		this.maxTotalJobExp = maxTotalJobExp;
	}

	public Boolean getIsEmpWithDisplay() {
		return isEmpWithDisplay;
	}

	public void setIsEmpWithDisplay(Boolean isEmpWithDisplay) {
		this.isEmpWithDisplay = isEmpWithDisplay;
	}

	public Boolean getIsEmpWithMandatory() {
		return isEmpWithMandatory;
	}

	public void setIsEmpWithMandatory(Boolean isEmpWithMandatory) {
		this.isEmpWithMandatory = isEmpWithMandatory;
	}

	public Boolean getIsEmpStatusDisplay() {
		return isEmpStatusDisplay;
	}

	public void setIsEmpStatusDisplay(Boolean isEmpStatusDisplay) {
		this.isEmpStatusDisplay = isEmpStatusDisplay;
	}

	public Boolean getIsEmpStatusMandatory() {
		return isEmpStatusMandatory;
	}

	public void setIsEmpStatusMandatory(Boolean isEmpStatusMandatory) {
		this.isEmpStatusMandatory = isEmpStatusMandatory;
	}

	public Boolean getIsEmpRatingDisplay() {
		return isEmpRatingDisplay;
	}

	public void setIsEmpRatingDisplay(Boolean isEmpRatingDisplay) {
		this.isEmpRatingDisplay = isEmpRatingDisplay;
	}

	public Boolean getIsEmpRatingMandatory() {
		return isEmpRatingMandatory;
	}

	public void setIsEmpRatingMandatory(Boolean isEmpRatingMandatory) {
		this.isEmpRatingMandatory = isEmpRatingMandatory;
	}

	public Boolean getIsTotalCurrentJobExpDisplay() {
		return isTotalCurrentJobExpDisplay;
	}

	public void setIsTotalCurrentJobExpDisplay(Boolean isTotalCurrentJobExpDisplay) {
		this.isTotalCurrentJobExpDisplay = isTotalCurrentJobExpDisplay;
	}

	public Boolean getIsTotalCurrentJobExpMandatory() {
		return isTotalCurrentJobExpMandatory;
	}

	public void setIsTotalCurrentJobExpMandatory(Boolean isTotalCurrentJobExpMandatory) {
		this.isTotalCurrentJobExpMandatory = isTotalCurrentJobExpMandatory;
	}

	public Boolean getIsTotalJobExpDisplay() {
		return isTotalJobExpDisplay;
	}

	public void setIsTotalJobExpDisplay(Boolean isTotalJobExpDisplay) {
		this.isTotalJobExpDisplay = isTotalJobExpDisplay;
	}

	public Boolean getIsTotalJobExpMandatory() {
		return isTotalJobExpMandatory;
	}

	public void setIsTotalJobExpMandatory(Boolean isTotalJobExpMandatory) {
		this.isTotalJobExpMandatory = isTotalJobExpMandatory;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Integer getUserCreditRating() {
		return userCreditRating;
	}

	public void setUserCreditRating(Integer userCreditRating) {
		this.userCreditRating = userCreditRating;
	}

	public Boolean getIsBankingRelationMandatory() {
		return isBankingRelationMandatory;
	}

	public void setIsBankingRelationMandatory(Boolean isBankingRelationMandatory) {
		this.isBankingRelationMandatory = isBankingRelationMandatory;
	}

	public Boolean getIsBankingRelationDisplay() {
		return isBankingRelationDisplay;
	}

	public void setIsBankingRelationDisplay(Boolean isBankingRelationDisplay) {
		this.isBankingRelationDisplay = isBankingRelationDisplay;
	}

	public Double getMinBankRelation() {
		return minBankRelation;
	}

	public void setMinBankRelation(Double minBankRelation) {
		this.minBankRelation = minBankRelation;
	}

	public Double getMaxBankRelation() {
		return maxBankRelation;
	}

	public void setMaxBankRelation(Double maxBankRelation) {
		this.maxBankRelation = maxBankRelation;
	}

	public Boolean getIsSalaryModeDisplay() {
		return isSalaryModeDisplay;
	}

	public void setIsSalaryModeDisplay(Boolean isSalaryModeDisplay) {
		this.isSalaryModeDisplay = isSalaryModeDisplay;
	}

	public Boolean getIsSalaryModeMandatory() {
		return isSalaryModeMandatory;
	}

	public void setIsSalaryModeMandatory(Boolean isSalaryModeMandatory) {
		this.isSalaryModeMandatory = isSalaryModeMandatory;
	}

	public Boolean getIsSalaryAcTypeDisplay() {
		return isSalaryAcTypeDisplay;
	}

	public void setIsSalaryAcTypeDisplay(Boolean isSalaryAcTypeDisplay) {
		this.isSalaryAcTypeDisplay = isSalaryAcTypeDisplay;
	}

	public Boolean getIsSalaryAcTypeMandatory() {
		return isSalaryAcTypeMandatory;
	}

	public void setIsSalaryAcTypeMandatory(Boolean isSalaryAcTypeMandatory) {
		this.isSalaryAcTypeMandatory = isSalaryAcTypeMandatory;
	}

	public Integer getSalaryAcType() {
		return salaryAcType;
	}

	public void setSalaryAcType(Integer salaryAcType) {
		this.salaryAcType = salaryAcType;
	}


	
	

	
	

	
	

	
}