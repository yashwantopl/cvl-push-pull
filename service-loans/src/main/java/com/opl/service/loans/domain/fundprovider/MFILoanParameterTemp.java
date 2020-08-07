package com.opl.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the fp_personal_loan_details database table.
 *
 */
@Entity
@Table(name="mfi_loan_details_temp")
public class MFILoanParameterTemp extends ProductMasterTemp implements Serializable {
    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name="fp_product_id")
    private ProductMasterTemp fpProductId;

    private Integer currency;

    // loan amount

    @Column(name="is_loan_amount_display")
    private Boolean isLoanAmountDisplay=false;

    @Column(name="is_loan_amount_mandatory")
    private Boolean isLoanAmountMandatory=false;

    @Column(name="min_loan_amount")
    private Double minLoanAmount;

    @Column(name="max_loan_amount")
    private Double maxLoanAmount;

    // yearly income

    @Column(name="is_yearly_income_range_display")
    private Boolean isYearlyIncomeRangeDisplay=false;

    @Column(name="is_yearly_income_range_mandatory")
    private Boolean isYearlyIncomeRangeMandatory=false;

    @Column(name="min_yearly_income_range")
    private Double minYearlyIncomeRange;

    @Column(name="max_yearly_income_range")
    private Double maxYearlyIncomeRange;

    // age of applicant

    @Column(name="is_age_applicant_display")
    private Boolean isAgeApplicantDisplay=false;

    @Column(name="is_age_applicant_mandatory")
    private Boolean isAgeApplicantMandatory=false;

    @Column(name="min_applicant_age")
    private Double minApplicantAge;

    @Column(name="max_applicant_age")
    private Double maxApplicantAge;

    // bureau score

    @Column(name="is_bureau_display")
    private Boolean isBureauScoreDisplay=false;

    @Column(name="is_bureau_mandatory")
    private Boolean isBureauScoreMandatory=false;

    @Column(name="min_bureau")
    private Double minBureauScore;

    //Less Than 6 month of Credit History
    @Column(name = "min_bureau_score_less_than6_month")
    private Integer minBureauScoreLessThan6Month;

    //No Credit History
    @Column(name = "no_bureau_credit_history")
    private Boolean noBureauCreditHistory = true;


    // activity experience

    @Column(name="is_experience_display")
    private Boolean isActivityExperienceDisplay=false;

    @Column(name="is_experience_mandatory")
    private Boolean isActivityExperienceMandatory=false;

    @Column(name="min_activity_experience")
    private Double minActivityExperience;

    @Column(name="max_activity_experience")
    private Double maxActivityExperience;


    // risk model score

    @Column(name="is_risk_model_score_display")
    private Boolean isRiskModelScoreDisplay = false;

    @Column(name="is_risk_model_score_mandatory")
    private Boolean isRiskModelScoreMandatory = false;

    @Column(name="min_risk_model_score")
    private Integer minRiskModelScore;

    @Column(name="max_risk_model_score")
    private Integer maxRiskModelScore;


    // eligiblity method id

    @Column(name="assessment_method_id")
    private Integer assessmentMethodId;


    // geo graphical location

    @Column(name="is_geographical_display_mfi")
    private Boolean isGeographicalDisplayMFI=false;

    @Column(name="is_geographical_mandatory_mfi")
    private Boolean isGeographicalMandatoryMFI=false;


    public Boolean getIsGeographicalDisplayMFI() {
        return isGeographicalDisplayMFI;
    }

    public void setIsGeographicalDisplayMFI(Boolean geographicalDisplayMFI) {
        isGeographicalDisplayMFI = geographicalDisplayMFI;
    }

    public Boolean getIsGeographicalMandatoryMFI() {
        return isGeographicalMandatoryMFI;
    }

    public void setIsGeographicalMandatoryMFI(Boolean geographicalMandatoryMFI) {
        isGeographicalMandatoryMFI = geographicalMandatoryMFI;
    }


    public MFILoanParameterTemp() {
        // Do nothing because of X and Y.
    }

    public Integer getMinBureauScoreLessThan6Month() {
        return minBureauScoreLessThan6Month;
    }

    public void setMinBureauScoreLessThan6Month(Integer minBureauScoreLessThan6Month) {
        this.minBureauScoreLessThan6Month = minBureauScoreLessThan6Month;
    }

    public Boolean getNoBureauCreditHistory() {
        return noBureauCreditHistory;
    }

    public void setNoBureauCreditHistory(Boolean noBureauCreditHistory) {
        this.noBureauCreditHistory = noBureauCreditHistory;
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

    public Integer getAssessmentMethodId() {
        return assessmentMethodId;
    }

    public void setAssessmentMethodId(Integer assessmentMethodId) {
        this.assessmentMethodId = assessmentMethodId;
    }

    public ProductMasterTemp getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(ProductMasterTemp fpProductId) {
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