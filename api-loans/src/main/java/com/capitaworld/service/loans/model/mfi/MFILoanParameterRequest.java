package com.capitaworld.service.loans.model.mfi;

import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * The persistent class for the fp_personal_loan_details database table.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MFILoanParameterRequest extends ProductMasterRequest implements Serializable {

    private Long fpProductId;

    private Integer currency;

    // loan amount

    private Boolean isLoanAmountDisplay=false;

    private Boolean isLoanAmountMandatory=false;

    private Double minLoanAmount;

    private Double maxLoanAmount;

    // yearly income

    private Boolean isYearlyIncomeRangeDisplay=false;

    private Boolean isYearlyIncomeRangeMandatory=false;

    private Double minYearlyIncomeRange;

    private Double maxYearlyIncomeRange;

    // age of applicant

    private Boolean isAgeApplicantDisplay=false;

    private Boolean isAgeApplicantMandatory=false;

    private Double minApplicantAge;

    private Double maxApplicantAge;

    // bureau score

    private Boolean isBureauScoreDisplay=false;

    private Boolean isBureauScoreMandatory=false;

    private Double minBureauScore;


    // activity experience

    private Boolean isActivityExperienceDisplay=false;

    private Boolean isActivityExperienceMandatory=false;

    private Double minActivityExperience;

    private Double maxActivityExperience;

    // risk model score

    private Boolean isRiskModelScoreDisplay = false;

    private Boolean isRiskModelScoreMandatory = false;


    private Integer minRiskModelScore;

    private Integer maxRiskModelScore;

    //

    private Object workflowData;

    public Object getWorkflowData() {
        return workflowData;
    }

    public void setWorkflowData(Object workflowData) {
        this.workflowData = workflowData;
    }

    public Boolean getIsRiskModelScoreDisplay() {
        return isRiskModelScoreDisplay;
    }

    public void setIsRiskModelScoreDisplay(Boolean riskModelScoreDisplay) {
        isRiskModelScoreDisplay = riskModelScoreDisplay;
    }

    public Boolean getIsRiskModelScoreMandatory() {
        return isRiskModelScoreMandatory;
    }

    public void setIsRiskModelScoreMandatory(Boolean riskModelScoreMandatory) {
        isRiskModelScoreMandatory = riskModelScoreMandatory;
    }

    public Integer getMinRiskModelScore() {
        return minRiskModelScore;
    }

    public void setMinRiskModelScore(Integer minRiskModelScore) {
        this.minRiskModelScore = minRiskModelScore;
    }

    public Integer getMaxRiskModelScore() {
        return maxRiskModelScore;
    }

    public void setMaxRiskModelScore(Integer maxRiskModelScore) {
        this.maxRiskModelScore = maxRiskModelScore;
    }

    private List<DataRequest> countryList = Collections.emptyList();

    private List<DataRequest> stateList = Collections.emptyList();

    private List<DataRequest> cityList = Collections.emptyList();

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

    public Long getFpProductId() {
        return fpProductId;
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

    public Boolean getIsLoanAmountDisplay() {
        return isLoanAmountDisplay;
    }

    public void setIsLoanAmountDisplay(Boolean loanAmountDisplay) {
        isLoanAmountDisplay = loanAmountDisplay;
    }

    public Boolean getIsLoanAmountMandatory() {
        return isLoanAmountMandatory;
    }

    public void setIsLoanAmountMandatory(Boolean loanAmountMandatory) {
        isLoanAmountMandatory = loanAmountMandatory;
    }

    public Double getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(Double minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public Double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(Double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public Boolean getIsYearlyIncomeRangeDisplay() {
        return isYearlyIncomeRangeDisplay;
    }

    public void setIsYearlyIncomeRangeDisplay(Boolean yearlyIncomeRangeDisplay) {
        isYearlyIncomeRangeDisplay = yearlyIncomeRangeDisplay;
    }

    public Boolean getIsYearlyIncomeRangeMandatory() {
        return isYearlyIncomeRangeMandatory;
    }

    public void setIsYearlyIncomeRangeMandatory(Boolean yearlyIncomeRangeMandatory) {
        isYearlyIncomeRangeMandatory = yearlyIncomeRangeMandatory;
    }

    public Double getMinYearlyIncomeRange() {
        return minYearlyIncomeRange;
    }

    public void setMinYearlyIncomeRange(Double minYearlyIncomeRange) {
        this.minYearlyIncomeRange = minYearlyIncomeRange;
    }

    public Double getMaxYearlyIncomeRange() {
        return maxYearlyIncomeRange;
    }

    public void setMaxYearlyIncomeRange(Double maxYearlyIncomeRange) {
        this.maxYearlyIncomeRange = maxYearlyIncomeRange;
    }

    public Boolean getIsAgeApplicantDisplay() {
        return isAgeApplicantDisplay;
    }

    public void setIsAgeApplicantDisplay(Boolean ageApplicantDisplay) {
        isAgeApplicantDisplay = ageApplicantDisplay;
    }

    public Boolean getIsAgeApplicantMandatory() {
        return isAgeApplicantMandatory;
    }

    public void setIsAgeApplicantMandatory(Boolean ageApplicantMandatory) {
        isAgeApplicantMandatory = ageApplicantMandatory;
    }

    public Double getMinApplicantAge() {
        return minApplicantAge;
    }

    public void setMinApplicantAge(Double minApplicantAge) {
        this.minApplicantAge = minApplicantAge;
    }

    public Double getMaxApplicantAge() {
        return maxApplicantAge;
    }

    public void setMaxApplicantAge(Double maxApplicantAge) {
        this.maxApplicantAge = maxApplicantAge;
    }

    public Boolean getIsBureauScoreDisplay() {
        return isBureauScoreDisplay;
    }

    public void setIsBureauScoreDisplay(Boolean bureauScoreDisplay) {
        isBureauScoreDisplay = bureauScoreDisplay;
    }

    public Boolean getIsBureauScoreMandatory() {
        return isBureauScoreMandatory;
    }

    public void setIsBureauScoreMandatory(Boolean bureauScoreMandatory) {
        isBureauScoreMandatory = bureauScoreMandatory;
    }

    public Double getMinBureauScore() {
        return minBureauScore;
    }

    public void setMinBureauScore(Double minBureauScore) {
        this.minBureauScore = minBureauScore;
    }

    public Boolean getIsActivityExperienceDisplay() {
        return isActivityExperienceDisplay;
    }

    public void setIsActivityExperienceDisplay(Boolean activityExperienceDisplay) {
        isActivityExperienceDisplay = activityExperienceDisplay;
    }

    public Boolean getIsActivityExperienceMandatory() {
        return isActivityExperienceMandatory;
    }

    public void setIsActivityExperienceMandatory(Boolean activityExperienceMandatory) {
        isActivityExperienceMandatory = activityExperienceMandatory;
    }

    public Double getMinActivityExperience() {
        return minActivityExperience;
    }

    public void setMinActivityExperience(Double minActivityExperience) {
        this.minActivityExperience = minActivityExperience;
    }

    public Double getMaxActivityExperience() {
        return maxActivityExperience;
    }

    public void setMaxActivityExperience(Double maxActivityExperience) {
        this.maxActivityExperience = maxActivityExperience;
    }
}