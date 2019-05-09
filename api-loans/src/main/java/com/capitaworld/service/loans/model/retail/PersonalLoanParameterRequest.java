package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;

import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fp_personal_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalLoanParameterRequest extends ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long fpProductId;

	private Integer currency;

	private Boolean isAgeDisplay = false;

	private Boolean isAgeMandatory = false;

	private Boolean isGeographicalDisplay = false;

	private Boolean isGeographicalMandatory = false;

	private Boolean isLoanAmountDisplay = false;

	private Boolean isLoanAmountMandatory = false;

	private Boolean isTenureDisplay = false;

	private Boolean isTenureMandatory = false;

	private Boolean isYearlyIncomeRangeDisplay = false;

	private Boolean isYearlyIncomeRangeMandatory = false;

	private Double maxAge;

	private Double maxLoanAmount;

	private Double maxTenure;

	private Double maxYearlyIncomeRange;

	private Double minAge;

	private Double minLoanAmount;

	private Double minTenure;

	private Double minYearlyIncomeRange;

	private List<DataRequest> countryList = Collections.emptyList();

	private List<DataRequest> stateList = Collections.emptyList();

	private List<DataRequest> cityList = Collections.emptyList();

	private Double minMonthlyIncomeRange;

	private Double maxMonthlyIncomeRange;

	private Boolean isMonthlyIncomeRangeDisplay= false;

	private Boolean isMonthlyIncomeRangeMandatory= false;

	private Double minBureauScore;

	private Double maxBureauScore;

	private Boolean isBureauScoreDisplay= false;

	private Boolean isBureauScoreMandatory= false;

	private Double minBureauScoreDays;

	private Double maxBureauScoreDays;

	private Boolean isBureauScoreDaysDisplay= false;

	private Boolean isBureauScoreDaysMandatory= false;

	private Double minRiskScoreRetail;

	private Double maxRiskScoreRetail;

	private Boolean isRiskScoreRetailDisplay= false;

	private Boolean isRiskScoreRetailMandatory= false;

	private List<Integer> empWithIds;

	private Boolean isEmpWithDisplay= false;

	private Boolean isEmpWithMandatory= false;

	private Boolean isEmpRatingDisplay= false;

	private Boolean isEmpRatingMandatory= false;

	private Double minTotalJobExp;

	private Double maxTotalJobExp;

	private Boolean isTotalJobExpDisplay= false;

	private Boolean isTotalJobExpMandatory= false;

	private Double minTotalCurrentJobExp;

	private Double maxTotalCurrentJobExp;

	private Boolean isTotalCurrentJobExpDisplay= false;

	private Boolean isTotalCurrentJobExpMandatory= false;

	private List<Integer> empStatusIds;

	private Boolean isEmpStatusDisplay= false;

	private Boolean isEmpStatusMandatory= false;

	private List<CreditRatingPlParameter> creditRatingSelectedList;

	private Object workflowData;

	private Long userOrgId;

	private Integer userCreditRating;

	private Boolean isBankingRelationMandatory= false;

	private Boolean isBankingRelationDisplay= false;

	private Double minBankRelation;

	private Double maxBankRelation;

	private List<Integer> salaryModeIds;

	private Boolean isSalaryModeDisplay= false;

	private Boolean isSalaryModeMandatory= false;

	private Boolean isSalaryAcTypeDisplay= false;

	private Boolean isSalaryAcTypeMandatory= false;

	private Integer salaryAcType;

	private Integer assessmentMethodId;

	private Boolean isBasedOnIncome = false;

	private Boolean isMultiplierOfIncomeCheck = false;

	private Integer multiplierOfIncomeCheck;

	private Boolean isEmiNmiCheck = false;

	private BigDecimal emiNmiCheck;
	
	private Boolean isGrossIncomeRangeDisplay= false;

	private Boolean isGrossIncomeRangeMandatory= false;
	
	private Double maxGrossIncomeRange;

	private Double minGrossIncomeRange;

	/*
	 * //FP NEW REQUIREMENT private Integer employmentId;
	 * 
	 * private Integer employmentPlaceId;
	 * 
	 * private Integer homeLoanTypeId;
	 * 
	 * private Integer mincurrentJobExperience;
	 * 
	 * private Integer maxCurrentJobExperience;
	 * 
	 * private Integer minTotalJobExperience;
	 * 
	 * private Integer maxTotalJobExperience;
	 * 
	 * private Double foir;
	 * 
	 * private Boolean isEmploymentTypeDisplay=false;
	 * 
	 * private Boolean isEmploymentTypeMandatory=false;
	 * 
	 * private Boolean isEmploymentPlaceDisplay=false;
	 * 
	 * private Boolean isEmploymentPlaceMandatory=false;
	 * 
	 * private Boolean isCurrentJobExperienceDisplay=false;
	 * 
	 * private Boolean isCurrentJobExperienceMandatory=false;
	 * 
	 * private Boolean isTotalJobExperienceDisplay=false;
	 * 
	 * private Boolean isTotalJobExperienceMandatory=false;
	 */

	public PersonalLoanParameterRequest() {
		// Do nothing because of X and Y.
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

	public List<DataRequest> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<DataRequest> countryList) {
		this.countryList = countryList;
	}

	public List<DataRequest> getStateList() {
		return stateList;
	}

	public void setStateList(List<DataRequest> stateList) {
		this.stateList = stateList;
	}

	public List<DataRequest> getCityList() {
		return cityList;
	}

	public void setCityList(List<DataRequest> cityList) {
		this.cityList = cityList;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
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

	public List<Integer> getEmpWithIds() {
		return empWithIds;
	}

	public void setEmpWithIds(List<Integer> empWithIds) {
		this.empWithIds = empWithIds;
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

	public List<Integer> getEmpStatusIds() {
		return empStatusIds;
	}

	public void setEmpStatusIds(List<Integer> empStatusIds) {
		this.empStatusIds = empStatusIds;
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

	public List<CreditRatingPlParameter> getCreditRatingSelectedList() {
		return creditRatingSelectedList;
	}

	public void setCreditRatingSelectedList(List<CreditRatingPlParameter> creditRatingSelectedList) {
		this.creditRatingSelectedList = creditRatingSelectedList;
	}

	public Boolean getIsBureauScoreMandatory() {
		return isBureauScoreMandatory;
	}

	public void setIsBureauScoreMandatory(Boolean isBureauScoreMandatory) {
		this.isBureauScoreMandatory = isBureauScoreMandatory;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
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

	public List<Integer> getSalaryModeIds() {
		return salaryModeIds;
	}

	public void setSalaryModeIds(List<Integer> salaryModeIds) {
		this.salaryModeIds = salaryModeIds;
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

	public Integer getAssessmentMethodId() {
		return assessmentMethodId;
	}

	public void setAssessmentMethodId(Integer assessmentMethodId) {
		this.assessmentMethodId = assessmentMethodId;
	}

	public Boolean getIsBasedOnIncome() {
		return isBasedOnIncome;
	}

	public void setIsBasedOnIncome(Boolean isBasedOnIncome) {
		this.isBasedOnIncome = isBasedOnIncome;
	}

	public Boolean getIsMultiplierOfIncomeCheck() {
		return isMultiplierOfIncomeCheck;
	}

	public void setIsMultiplierOfIncomeCheck(Boolean isMultiplierOfIncomeCheck) {
		this.isMultiplierOfIncomeCheck = isMultiplierOfIncomeCheck;
	}

	public Integer getMultiplierOfIncomeCheck() {
		return multiplierOfIncomeCheck;
	}

	public void setMultiplierOfIncomeCheck(Integer multiplierOfIncomeCheck) {
		this.multiplierOfIncomeCheck = multiplierOfIncomeCheck;
	}

	public Boolean getIsEmiNmiCheck() {
		return isEmiNmiCheck;
	}

	public void setIsEmiNmiCheck(Boolean isEmiNmiCheck) {
		this.isEmiNmiCheck = isEmiNmiCheck;
	}

	public BigDecimal getEmiNmiCheck() {
		return emiNmiCheck;
	}

	public void setEmiNmiCheck(BigDecimal emiNmiCheck) {
		this.emiNmiCheck = emiNmiCheck;
	}

	public Boolean getIsGrossIncomeRangeDisplay() {
		return isGrossIncomeRangeDisplay;
	}

	public void setIsGrossIncomeRangeDisplay(Boolean isGrossIncomeRangeDisplay) {
		this.isGrossIncomeRangeDisplay = isGrossIncomeRangeDisplay;
	}

	public Boolean getIsGrossIncomeRangeMandatory() {
		return isGrossIncomeRangeMandatory;
	}

	public void setIsGrossIncomeRangeMandatory(Boolean isGrossIncomeRangeMandatory) {
		this.isGrossIncomeRangeMandatory = isGrossIncomeRangeMandatory;
	}

	public Double getMaxGrossIncomeRange() {
		return maxGrossIncomeRange;
	}

	public void setMaxGrossIncomeRange(Double maxGrossIncomeRange) {
		this.maxGrossIncomeRange = maxGrossIncomeRange;
	}

	public Double getMinGrossIncomeRange() {
		return minGrossIncomeRange;
	}

	public void setMinGrossIncomeRange(Double minGrossIncomeRange) {
		this.minGrossIncomeRange = minGrossIncomeRange;
	}

	/*
	 * public Integer getEmploymentId() { return employmentId; }
	 * 
	 * public void setEmploymentId(Integer employmentId) { this.employmentId =
	 * employmentId; }
	 * 
	 * public Integer getEmploymentPlaceId() { return employmentPlaceId; }
	 * 
	 * public void setEmploymentPlaceId(Integer employmentPlaceId) {
	 * this.employmentPlaceId = employmentPlaceId; }
	 * 
	 * public Integer getHomeLoanTypeId() { return homeLoanTypeId; }
	 * 
	 * public void setHomeLoanTypeId(Integer homeLoanTypeId) {
	 * this.homeLoanTypeId = homeLoanTypeId; }
	 * 
	 * public Integer getMincurrentJobExperience() { return
	 * mincurrentJobExperience; }
	 * 
	 * public void setMincurrentJobExperience(Integer mincurrentJobExperience) {
	 * this.mincurrentJobExperience = mincurrentJobExperience; }
	 * 
	 * public Integer getMaxCurrentJobExperience() { return
	 * maxCurrentJobExperience; }
	 * 
	 * public void setMaxCurrentJobExperience(Integer maxCurrentJobExperience) {
	 * this.maxCurrentJobExperience = maxCurrentJobExperience; }
	 * 
	 * public Integer getMinTotalJobExperience() { return minTotalJobExperience;
	 * }
	 * 
	 * public void setMinTotalJobExperience(Integer minTotalJobExperience) {
	 * this.minTotalJobExperience = minTotalJobExperience; }
	 * 
	 * public Integer getMaxTotalJobExperience() { return maxTotalJobExperience;
	 * }
	 * 
	 * public void setMaxTotalJobExperience(Integer maxTotalJobExperience) {
	 * this.maxTotalJobExperience = maxTotalJobExperience; }
	 * 
	 * public Double getFoir() { return foir; }
	 * 
	 * public void setFoir(Double foir) { this.foir = foir; }
	 * 
	 * public Boolean getIsEmploymentTypeDisplay() { return
	 * isEmploymentTypeDisplay; }
	 * 
	 * public void setIsEmploymentTypeDisplay(Boolean isEmploymentTypeDisplay) {
	 * this.isEmploymentTypeDisplay = isEmploymentTypeDisplay; }
	 * 
	 * public Boolean getIsEmploymentTypeMandatory() { return
	 * isEmploymentTypeMandatory; }
	 * 
	 * public void setIsEmploymentTypeMandatory(Boolean
	 * isEmploymentTypeMandatory) { this.isEmploymentTypeMandatory =
	 * isEmploymentTypeMandatory; }
	 * 
	 * public Boolean getIsEmploymentPlaceDisplay() { return
	 * isEmploymentPlaceDisplay; }
	 * 
	 * public void setIsEmploymentPlaceDisplay(Boolean isEmploymentPlaceDisplay)
	 * { this.isEmploymentPlaceDisplay = isEmploymentPlaceDisplay; }
	 * 
	 * public Boolean getIsEmploymentPlaceMandatory() { return
	 * isEmploymentPlaceMandatory; }
	 * 
	 * public void setIsEmploymentPlaceMandatory(Boolean
	 * isEmploymentPlaceMandatory) { this.isEmploymentPlaceMandatory =
	 * isEmploymentPlaceMandatory; }
	 * 
	 * public Boolean getIsCurrentJobExperienceDisplay() { return
	 * isCurrentJobExperienceDisplay; }
	 * 
	 * public void setIsCurrentJobExperienceDisplay(Boolean
	 * isCurrentJobExperienceDisplay) { this.isCurrentJobExperienceDisplay =
	 * isCurrentJobExperienceDisplay; }
	 * 
	 * public Boolean getIsCurrentJobExperienceMandatory() { return
	 * isCurrentJobExperienceMandatory; }
	 * 
	 * public void setIsCurrentJobExperienceMandatory(Boolean
	 * isCurrentJobExperienceMandatory) { this.isCurrentJobExperienceMandatory =
	 * isCurrentJobExperienceMandatory; }
	 * 
	 * public Boolean getIsTotalJobExperienceDisplay() { return
	 * isTotalJobExperienceDisplay; }
	 * 
	 * public void setIsTotalJobExperienceDisplay(Boolean
	 * isTotalJobExperienceDisplay) { this.isTotalJobExperienceDisplay =
	 * isTotalJobExperienceDisplay; }
	 * 
	 * public Boolean getIsTotalJobExperienceMandatory() { return
	 * isTotalJobExperienceMandatory; }
	 * 
	 * public void setIsTotalJobExperienceMandatory(Boolean
	 * isTotalJobExperienceMandatory) { this.isTotalJobExperienceMandatory =
	 * isTotalJobExperienceMandatory; }
	 */
	
	
	
	

}