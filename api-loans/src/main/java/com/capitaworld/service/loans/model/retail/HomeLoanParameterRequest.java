package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeLoanParameterRequest extends ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long fpProductId;

	private Integer currency;

	private Boolean isAgeDisplay=false;

	private Boolean isAgeMandatory=false;

	private Boolean isAssetValueDisplay=false;

	private Boolean isAssetValueMandatory=false;

	private Boolean isGeographicalDisplay=false;

	private Boolean isGeographicalMandatory=false;

	private Boolean isLoanAmountDisplay=false;

	private Boolean isLoanAmountMandatory=false;

	private Boolean isTenureDisplay=false;

	private Boolean isTenureMandatory=false;

	private Boolean isYearlyIncomeRangeDisplay=false;

	private Boolean isYearlyIncomeRangeMandatory=false;

	private Double maxAge;

	private Double maxAssetValue;

	private Double maxLoanAmount;

	private Double maxTenure;

	private Double maxYearlyIncomeRange;

	private Double minAge;

	private Double minAssetValue;

	private Double minLoanAmount;

	private Double minTenure;

	private Double minYearlyIncomeRange;

	private List<DataRequest> countryList = Collections.emptyList();

	private List<DataRequest> stateList = Collections.emptyList();

	private List<DataRequest> cityList = Collections.emptyList();
	
	//FP NEW REQUIREMENT
	private Integer employmentId;
	
	private Integer employmentPlaceId;
	
	private Integer homeLoanTypeId;
	
	private Integer mincurrentJobExperience;
	
	private Integer maxCurrentJobExperience;
	
	private Integer minTotalJobExperience;
	
	private Integer maxTotalJobExperience;
	
	private Double foir;
	
	private Integer minTotalBusinessExperience;
	
	private Integer maxTotalBusinessExperience;
	
	private Integer minCashProfitYearly;
	
	private Integer maxCashProfitYearly;
	
	private Double patPreviousYear;
	
	private Double patCurrentYear;
	
	private Boolean isPatPreviousYearSelected = false;
	
	private Boolean isPatCurrentYearSelected = false;
	
	private Boolean isPatAverageSelected = false;
	
	private Double depreciationPreviousYear;
	
	private Double depreciationCurrentYear;
	
	private Boolean isDepreciationPreviousYearSelected = false;
	
	private Boolean isDepreciationCurrentYearSelected = false;
	
	private Double remunerationPreviousYear;
	
	private Double remunerationCurrentYear;
	
	private Boolean isRemunerationPreviousYearSelected = false;
	
	private Boolean isRemunerationCurrentYearSelected = false;
	
	private Boolean isEmploymentTypeDisplay=false;

	private Boolean isEmploymentTypeMandatory=false;
	
	private Boolean isEmploymentPlaceDisplay=false;

	private Boolean isEmploymentPlaceMandatory=false;
	
	private Boolean isHomeLoanTypeDisplay=false;

	private Boolean isHomeLoanTypeMandatory=false;
	
	private Boolean isCurrentJobExperienceDisplay=false;

	private Boolean isCurrentJobExperienceMandatory=false;
	
	private Boolean isTotalJobExperienceDisplay=false;

	private Boolean isTotalJobExperienceMandatory=false;
	
	private Boolean isTotalBusinessExperienceDisplay=false;

	private Boolean isTotalBusinessExperienceMandatory=false;
	
	private Boolean isCashProfitDisplay=false;
	
	private Boolean isCashProfitMandatory=false;

	
	public HomeLoanParameterRequest() {
	}

	public Long getFpProductId() {
		return this.fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Integer getCurrency() {
		return currency;
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

	public Integer getEmploymentId() {
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

	public Integer getHomeLoanTypeId() {
		return homeLoanTypeId;
	}

	public void setHomeLoanTypeId(Integer homeLoanTypeId) {
		this.homeLoanTypeId = homeLoanTypeId;
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

	public Boolean getIsHomeLoanTypeDisplay() {
		return isHomeLoanTypeDisplay;
	}

	public void setIsHomeLoanTypeDisplay(Boolean isHomeLoanTypeDisplay) {
		this.isHomeLoanTypeDisplay = isHomeLoanTypeDisplay;
	}

	public Boolean getIsHomeLoanTypeMandatory() {
		return isHomeLoanTypeMandatory;
	}

	public void setIsHomeLoanTypeMandatory(Boolean isHomeLoanTypeMandatory) {
		this.isHomeLoanTypeMandatory = isHomeLoanTypeMandatory;
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
	}
	
}