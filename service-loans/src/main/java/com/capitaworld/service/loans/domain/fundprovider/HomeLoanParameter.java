package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@Entity
@Table(name = "fp_home_loan_details")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class HomeLoanParameter extends ProductMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer currency;
	
	// Net Monthly Income Range (Rs.)
	@Column(name = "min_net_monthly_income_range")
	private Double minNetMonthlyIncomeRange;
	@Column(name = "max_net_monthly_income_range")
	private Double maxNetMonthlyIncomeRange;
	@Column(name = "is_net_monthly_income_range_display")
	private Boolean isNetMonthlyIncomeRangeDisplay = false;
	@Column(name = "is_net_monthly_income_range_mandatory")
	private Boolean isNetMonthlyIncomeRangeMandatory = false;

	// Age of Applicant (Yrs.)
	@Column(name = "min_age")
	private Double minAge;
	@Column(name = "max_age")
	private Double maxAge;
	@Column(name = "is_age_display")
	private Boolean isAgeDisplay = false;
	@Column(name = "is_age_mandatory")
	private Boolean isAgeMandatory = false;

	// Tenure of Loan (Yrs.)
	@Column(name = "min_tenure")
	private Double minTenure;
	@Column(name = "max_tenure")
	private Double maxTenure;
	@Column(name = "is_tenure_display")
	private Boolean isTenureDisplay = false;
	@Column(name = "is_tenure_mandatory")
	private Boolean isTenureMandatory = false;

	//Purpose Loan
	@Column(name = "is_purpose_loan_display")
	private Boolean isPurposeLoanDisplay = false;
	@Column(name = "is_purpose_loan_mandatory")
	private Boolean isPurposeLoanMandatory = false;
	
	// Geographical Market Focus
	@Column(name = "is_geographical_display")
	private Boolean isGeographicalDisplay = false;
	@Column(name = "is_geographical_mandatory")
	private Boolean isGeographicalMandatory = false;

	// Minimum Bureau Score
	@Column(name = "min_bureau_score")
	private Integer minBureauScore;
	@Column(name = "max_bureau_score")
	private Integer maxBureauScore;
	@Column(name = "is_bureau_score_display")
	private Boolean isBureauScoreDisplay = false;
	@Column(name = "is_bureau_score_mandatory")
	private Boolean isBureauScoreMandatory = false;

	// Maximum DPDs (Satisfactory with No Default: DPD) (Days) (Past 12 Months)
	@Column(name = "max_dpds")
	private Integer maxDpds;
	@Column(name = "is_dpds_display")
	private Boolean isDpdsDisplay = false;
	@Column(name = "is_dpds_mandatory")
	private Boolean isDpdsMandatory = false;
	
	//Risk Model
	@Column(name="min_risk_score_model")
	private Double minRiskScoreRetail;
	@Column(name="is_risk_score_model_display")
	private Boolean isRiskScoreRetailDisplay=false;
	@Column(name="is_risk_score_model_mandatory")
	private Boolean isRiskScoreRetailMandatory=false;
	
	@Column(name="min_risk_score_model_co_app")
	private Double minRiskScoreModelCoApp;
	@Column(name="is_risk_score_model_coapp_display")
	private Boolean isRiskScoreRetailCoAppDisplay=false;
	@Column(name="is_risk_score_model_coapp_mandatory")
	private Boolean isRiskScoreRetailCoAppMandatory=false;


	// Total Job Experience (Yrs.)
	@Column(name = "min_total_job_experience")
	private Integer minTotalJobExp;
	@Column(name = "max_total_job_experience")
	private Integer maxTotalJobExp;
	@Column(name = "is_total_job_experience_display")
	private Boolean isTotalJobExpDisplay = false;
	@Column(name = "is_total_job_experience_mandatory")
	private Boolean isTotalJobExpMandatory = false;

	// Total Current Job Experience (Yrs.)
	@Column(name = "min_current_job_experience")
	private Integer minCurrentJobExperience;
	@Column(name = "max_current_job_experience")
	private Integer maxCurrentJobExperience;
	@Column(name = "is_current_job_experience_display")
	private Boolean isCurrentJobExperienceDisplay = false;
	@Column(name = "is_current_job_experience_mandatory")
	private Boolean isCurrentJobExperienceMandatory = false;

	// Current Employment Status
	@Column(name = "is_current_employment_status_display")
	private Boolean isCurrentEmploymentStatusDisplay = false;
	@Column(name = "is_current_employment_status_mandatory")
	private Boolean isCurrentEmploymentStatusMandatory = false;

	// Purpose of Loan
	@Column(name = "loan_purpose")
	private Integer loanPurpose;
	@Column(name = "is_loan_purpose_display")
	private Boolean isLoanPurposeDisplay = false;
	@Column(name = "is_loan_purpose_mandatory")
	private Boolean isLoanPurposeMandatory = false;

	// Loan Amount (Rs.)
	@Column(name = "min_loan_amount")
	private Double minLoanAmount;
	@Column(name = "max_loan_amount")
	private Double maxLoanAmount;
	@Column(name = "is_loan_amount_display")
	private Boolean isLoanAmountDisplay = false;
	@Column(name = "is_loan_amount_mandatory")
	private Boolean isLoanAmountMandatory = false;

	// Residential Status
	@Column(name = "is_residential_status_display")
	private Boolean isResidentialStatusDisplay = false;
	@Column(name = "is_residential_status_mandatory")
	private Boolean isResidentialStatusMandatory = false;

	// Borrower Type
	@Column(name = "is_borrower_type_display")
	private Boolean isBorrowerTypeDisplay = false;
	@Column(name = "is_borrower_type_mandatory")
	private Boolean isBorrowerTypeMandatory = false;

	// Minimum Banking Relationship (Months)
	@Column(name = "min_bank_relationship")
	private Integer minBankRelation;
	@Column(name = "max_bank_relationship")
	private Integer maxBankRelation;
	@Column(name = "is_bank_relationship_display")
	private Boolean isBankingRelationDisplay = false;
	@Column(name = "is_bank_relationship_mandatory")
	private Boolean isBankingRelationMandatory = false;

	// Mode of Salary
	@Column(name = "is_salary_mode_display")
	private Boolean isSalaryModeDisplay = false;
	@Column(name = "is_salary_mode_mandatory")
	private Boolean isSalaryModeMandatory = false;

	// Borrower Salary Account From
	@Column(name = "is_borr_sal_acc_display")
	private Boolean isBorrSalAccDisplay = false;
	@Column(name = "is_borr_sal_acc_mandatory")
	private Boolean isBorrSalAccMandatory = false;
	
	
	// Eligibile Employer From
	@Column(name = "is_employment_with_display")
	private Boolean isEmploymentWithDisplay = false;
	@Column(name = "is_employment_with_mandatory")
	private Boolean isEmploymentWithMandatory = false;

	// Loan to Value (LTV) %
	
	@Column(name = "min_ltv")
	private Double minLtv;
	@Column(name = "max_ltv")
	private Double maxLtv;
	@Column(name = "is_ltv_display")
	private Boolean isLtvDisplay = false;
	@Column(name = "is_ltv_mandatory")
	private Boolean isLtvMandatory = false;

	// Minimum % of Gross Monthly Income as Take Home Pay
//	@Column(name = "min_grss_mon_income_as_home_pay_sal_indiv")
//	private Double minGrssMonIncomeAsHomePaySalIndiv;
//	@Column(name = "is_grss_mon_income_as_home_pay_sal_indiv_display")
//	private Boolean isGrssMonIncomeAsHomePaySalIndivDisplay = false;
//	@Column(name = "is_grss_mon_income_as_home_pay_sal_indiv_mandatory")
//	private Boolean isGrssMonIncomeAsHomePaySalIndivMandatory = false;
//
//	// Maximum % of Net Income as Permissible EMI
//	@Column(name = "max_net_income_permiss_emi_sal_indiv")
//	private Double maxNetIncomePermissEMISalIndiv;
//	@Column(name = "is_net_income_permiss_emi_sal_indiv_display")
//	private Boolean isNetIncomePermissEMISalIndivDisplay = false;
//	@Column(name = "is_net_income_permiss_emi_sal_indiv_mandatory")
//	private Boolean isNetIncomePermissEMISalIndivMandatory = false;
//
//	// Maximum Number of Times of Gross Monthly Income to be considered 
//	@Column(name = "max_time_consi_month_grss_income_sal_indiv")
//	private Double maxTimeConsiMonthGrssIncomeSalIndiv;
//	@Column(name = "is_time_consi_month_grss_income_sal_indiv_display")
//	private Boolean isTimeConsiMonthGrssIncomeSalIndivDisplay = false;
//	@Column(name = "is_time_consi_month_grss_income_sal_indiv_mandatory")
//	private Boolean isTimeConsiMonthGrssIncomeSalIndivMandatory = false;
//
//	// Minimum % of Gross Monthly Income as Take Home Pay
//	@Column(name = "max_grss_mon_income_as_home_pay_oth_thn_sal_indi")
//	private Double maxGrssMonIncomeAsHomePayOthThnSalIndi;
//	@Column(name = "is_grss_mon_income_as_home_pay_oth_thn_sal_indi_display")
//	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiDisplay = false;
//	@Column(name = "is_grss_mon_income_as_home_pay_oth_thn_sal_indi_mandatory")
//	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory = false;
//
//	// Maximum % of Net Income as Permissible EMI
//	@Column(name = "max_net_income_permiss_emi_oth_thn_sal_indiv")
//	private Double maxNetIncomePermissEMIOthThnSalIndi;
//	@Column(name = "is_net_income_permiss_emi_oth_thn_sal_indiv_display")
//	private Boolean isNetIncomePermissEMIOthThnSalIndiDisplay = false;
//	@Column(name = "is_net_income_permiss_emi_oth_thn_sal_indiv_mandatory")
//	private Boolean isNetIncomePermissEMIOthThnSalIndiMandatory = false;
//
//	// Maximum Number of Times of Gross Monthly Income to be considered 
//	@Column(name = "max_time_consi_month_grss_income_oth_thn_sal_indi")
//	private Double maxTimeConsiMonthGrssIncomeOthThnSalIndi;
//	@Column(name = "is_time_consi_month_grss_income_oth_thn_sal_indi_display")
//	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay = false;
//	@Column(name = "is_time_consi_month_grss_income_oth_thn_sal_indi_mandatory")
//	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory = false;
	
	//No. of Co-Applicant
	@Column(name = "no_of_co_app_or_gua")
	private Integer noOfCoAppOrGua;

	// Do you wish to consider the Income of Co-Applicant for Assessment of Loan Amount? Yes Or No
	@Column(name = "is_consider_income_of_co_app")
	private Boolean isConsiderIncomeOfCoApp;
	
//	Maximum Age allowed
	@Column(name = "max_age_allowed")
	private Integer maxAgeAllowed;
	
	
	@Column(name = "assessment_method_id")
	private Integer assessmentMethodId;
	
	@Column(name = "is_gross_net_income")
	private Boolean isGrossNetIncome;
	
	@Column(name = "mon_income_type")
	private Integer monIncomeType;
	
	private Double foir;
	
	@Column(name = "is_times_multiplier_income")
	private Boolean isTimesMultiplierIncome;
	
	@Column(name = "mon_income_multiplier_type")
	private Integer monIncomeMultiplierType;
	
	@Column(name = "times_multiplier")
	private Integer timesMultiplier;
	
	
	@Column(name = "is_ltv")
	private Boolean isLtv;
	
	@Column(name = "is_pur_ren_cons_exp_rep_cost")
	private Boolean isPurRenConsExpRepCost;
	
	@Column(name = "is_market_value")
	private Boolean isMarketValue;
	
	@Column(name = "ltv_for_eligibility")
	private Integer ltvForEligibility;

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Double getMinNetMonthlyIncomeRange() {
		return minNetMonthlyIncomeRange;
	}

	public void setMinNetMonthlyIncomeRange(Double minNetMonthlyIncomeRange) {
		this.minNetMonthlyIncomeRange = minNetMonthlyIncomeRange;
	}

	public Double getMaxNetMonthlyIncomeRange() {
		return maxNetMonthlyIncomeRange;
	}

	public void setMaxNetMonthlyIncomeRange(Double maxNetMonthlyIncomeRange) {
		this.maxNetMonthlyIncomeRange = maxNetMonthlyIncomeRange;
	}

	public Boolean getIsNetMonthlyIncomeRangeDisplay() {
		return isNetMonthlyIncomeRangeDisplay;
	}

	public void setIsNetMonthlyIncomeRangeDisplay(Boolean isNetMonthlyIncomeRangeDisplay) {
		this.isNetMonthlyIncomeRangeDisplay = isNetMonthlyIncomeRangeDisplay;
	}

	public Boolean getIsNetMonthlyIncomeRangeMandatory() {
		return isNetMonthlyIncomeRangeMandatory;
	}

	public void setIsNetMonthlyIncomeRangeMandatory(Boolean isNetMonthlyIncomeRangeMandatory) {
		this.isNetMonthlyIncomeRangeMandatory = isNetMonthlyIncomeRangeMandatory;
	}

	public Double getMinAge() {
		return minAge;
	}

	public void setMinAge(Double minAge) {
		this.minAge = minAge;
	}

	public Double getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Double maxAge) {
		this.maxAge = maxAge;
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

	public Double getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
	}

	public Double getMaxTenure() {
		return maxTenure;
	}

	public void setMaxTenure(Double maxTenure) {
		this.maxTenure = maxTenure;
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

	public Boolean getIsPurposeLoanDisplay() {
		return isPurposeLoanDisplay;
	}

	public void setIsPurposeLoanDisplay(Boolean isPurposeLoanDisplay) {
		this.isPurposeLoanDisplay = isPurposeLoanDisplay;
	}

	public Boolean getIsPurposeLoanMandatory() {
		return isPurposeLoanMandatory;
	}

	public void setIsPurposeLoanMandatory(Boolean isPurposeLoanMandatory) {
		this.isPurposeLoanMandatory = isPurposeLoanMandatory;
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

	public Integer getMinBureauScore() {
		return minBureauScore;
	}

	public void setMinBureauScore(Integer minBureauScore) {
		this.minBureauScore = minBureauScore;
	}

	public Integer getMaxBureauScore() {
		return maxBureauScore;
	}

	public void setMaxBureauScore(Integer maxBureauScore) {
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

	public Integer getMaxDpds() {
		return maxDpds;
	}

	public void setMaxDpds(Integer maxDpds) {
		this.maxDpds = maxDpds;
	}

	public Boolean getIsDpdsDisplay() {
		return isDpdsDisplay;
	}

	public void setIsDpdsDisplay(Boolean isDpdsDisplay) {
		this.isDpdsDisplay = isDpdsDisplay;
	}

	public Boolean getIsDpdsMandatory() {
		return isDpdsMandatory;
	}

	public void setIsDpdsMandatory(Boolean isDpdsMandatory) {
		this.isDpdsMandatory = isDpdsMandatory;
	}

	public Double getMinRiskScoreRetail() {
		return minRiskScoreRetail;
	}

	public void setMinRiskScoreRetail(Double minRiskScoreRetail) {
		this.minRiskScoreRetail = minRiskScoreRetail;
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

	public Double getMinRiskScoreModelCoApp() {
		return minRiskScoreModelCoApp;
	}

	public void setMinRiskScoreModelCoApp(Double minRiskScoreModelCoApp) {
		this.minRiskScoreModelCoApp = minRiskScoreModelCoApp;
	}

	public Boolean getIsRiskScoreRetailCoAppDisplay() {
		return isRiskScoreRetailCoAppDisplay;
	}

	public void setIsRiskScoreRetailCoAppDisplay(Boolean isRiskScoreRetailCoAppDisplay) {
		this.isRiskScoreRetailCoAppDisplay = isRiskScoreRetailCoAppDisplay;
	}

	public Boolean getIsRiskScoreRetailCoAppMandatory() {
		return isRiskScoreRetailCoAppMandatory;
	}

	public void setIsRiskScoreRetailCoAppMandatory(Boolean isRiskScoreRetailCoAppMandatory) {
		this.isRiskScoreRetailCoAppMandatory = isRiskScoreRetailCoAppMandatory;
	}

	public Integer getMinTotalJobExp() {
		return minTotalJobExp;
	}

	public void setMinTotalJobExp(Integer minTotalJobExp) {
		this.minTotalJobExp = minTotalJobExp;
	}

	public Integer getMaxTotalJobExp() {
		return maxTotalJobExp;
	}

	public void setMaxTotalJobExp(Integer maxTotalJobExp) {
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

	public Integer getMinCurrentJobExperience() {
		return minCurrentJobExperience;
	}

	public void setMinCurrentJobExperience(Integer minCurrentJobExperience) {
		this.minCurrentJobExperience = minCurrentJobExperience;
	}

	public Integer getMaxCurrentJobExperience() {
		return maxCurrentJobExperience;
	}

	public void setMaxCurrentJobExperience(Integer maxCurrentJobExperience) {
		this.maxCurrentJobExperience = maxCurrentJobExperience;
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

	public Boolean getIsCurrentEmploymentStatusDisplay() {
		return isCurrentEmploymentStatusDisplay;
	}

	public void setIsCurrentEmploymentStatusDisplay(Boolean isCurrentEmploymentStatusDisplay) {
		this.isCurrentEmploymentStatusDisplay = isCurrentEmploymentStatusDisplay;
	}

	public Boolean getIsCurrentEmploymentStatusMandatory() {
		return isCurrentEmploymentStatusMandatory;
	}

	public void setIsCurrentEmploymentStatusMandatory(Boolean isCurrentEmploymentStatusMandatory) {
		this.isCurrentEmploymentStatusMandatory = isCurrentEmploymentStatusMandatory;
	}

	public Integer getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Boolean getIsLoanPurposeDisplay() {
		return isLoanPurposeDisplay;
	}

	public void setIsLoanPurposeDisplay(Boolean isLoanPurposeDisplay) {
		this.isLoanPurposeDisplay = isLoanPurposeDisplay;
	}

	public Boolean getIsLoanPurposeMandatory() {
		return isLoanPurposeMandatory;
	}

	public void setIsLoanPurposeMandatory(Boolean isLoanPurposeMandatory) {
		this.isLoanPurposeMandatory = isLoanPurposeMandatory;
	}

	public Double getMaxLoanAmount() {
		return maxLoanAmount;
	}

	public void setMaxLoanAmount(Double maxLoanAmount) {
		this.maxLoanAmount = maxLoanAmount;
	}

	public Double getMinLoanAmount() {
		return minLoanAmount;
	}

	public void setMinLoanAmount(Double minLoanAmount) {
		this.minLoanAmount = minLoanAmount;
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

	public Boolean getIsResidentialStatusDisplay() {
		return isResidentialStatusDisplay;
	}

	public void setIsResidentialStatusDisplay(Boolean isResidentialStatusDisplay) {
		this.isResidentialStatusDisplay = isResidentialStatusDisplay;
	}

	public Boolean getIsResidentialStatusMandatory() {
		return isResidentialStatusMandatory;
	}

	public void setIsResidentialStatusMandatory(Boolean isResidentialStatusMandatory) {
		this.isResidentialStatusMandatory = isResidentialStatusMandatory;
	}

	public Boolean getIsBorrowerTypeDisplay() {
		return isBorrowerTypeDisplay;
	}

	public void setIsBorrowerTypeDisplay(Boolean isBorrowerTypeDisplay) {
		this.isBorrowerTypeDisplay = isBorrowerTypeDisplay;
	}

	public Boolean getIsBorrowerTypeMandatory() {
		return isBorrowerTypeMandatory;
	}

	public void setIsBorrowerTypeMandatory(Boolean isBorrowerTypeMandatory) {
		this.isBorrowerTypeMandatory = isBorrowerTypeMandatory;
	}

	public Integer getMinBankRelation() {
		return minBankRelation;
	}

	public void setMinBankRelation(Integer minBankRelation) {
		this.minBankRelation = minBankRelation;
	}

	public Integer getMaxBankRelation() {
		return maxBankRelation;
	}

	public void setMaxBankRelation(Integer maxBankRelation) {
		this.maxBankRelation = maxBankRelation;
	}

	public Boolean getIsBankingRelationDisplay() {
		return isBankingRelationDisplay;
	}

	public void setIsBankingRelationDisplay(Boolean isBankingRelationDisplay) {
		this.isBankingRelationDisplay = isBankingRelationDisplay;
	}

	public Boolean getIsBankingRelationMandatory() {
		return isBankingRelationMandatory;
	}

	public void setIsBankingRelationMandatory(Boolean isBankingRelationMandatory) {
		this.isBankingRelationMandatory = isBankingRelationMandatory;
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

	public Boolean getIsBorrSalAccDisplay() {
		return isBorrSalAccDisplay;
	}

	public void setIsBorrSalAccDisplay(Boolean isBorrSalAccDisplay) {
		this.isBorrSalAccDisplay = isBorrSalAccDisplay;
	}

	public Boolean getIsBorrSalAccMandatory() {
		return isBorrSalAccMandatory;
	}

	public void setIsBorrSalAccMandatory(Boolean isBorrSalAccMandatory) {
		this.isBorrSalAccMandatory = isBorrSalAccMandatory;
	}

	public Double getMinLtv() {
		return minLtv;
	}

	public void setMinLtv(Double minLtv) {
		this.minLtv = minLtv;
	}

	public Double getMaxLtv() {
		return maxLtv;
	}

	public void setMaxLtv(Double maxLtv) {
		this.maxLtv = maxLtv;
	}

	public Boolean getIsLtvDisplay() {
		return isLtvDisplay;
	}

	public void setIsLtvDisplay(Boolean isLtvDisplay) {
		this.isLtvDisplay = isLtvDisplay;
	}

	public Boolean getIsLtvMandatory() {
		return isLtvMandatory;
	}

	public void setIsLtvMandatory(Boolean isLtvMandatory) {
		this.isLtvMandatory = isLtvMandatory;
	}

	public Integer getNoOfCoAppOrGua() {
		return noOfCoAppOrGua;
	}

	public void setNoOfCoAppOrGua(Integer noOfCoAppOrGua) {
		this.noOfCoAppOrGua = noOfCoAppOrGua;
	}

	public Boolean getIsConsiderIncomeOfCoApp() {
		return isConsiderIncomeOfCoApp;
	}

	public void setIsConsiderIncomeOfCoApp(Boolean isConsiderIncomeOfCoApp) {
		this.isConsiderIncomeOfCoApp = isConsiderIncomeOfCoApp;
	}

	public Integer getMaxAgeAllowed() {
		return maxAgeAllowed;
	}

	public void setMaxAgeAllowed(Integer maxAgeAllowed) {
		this.maxAgeAllowed = maxAgeAllowed;
	}

	public Integer getAssessmentMethodId() {
		return assessmentMethodId;
	}

	public void setAssessmentMethodId(Integer assessmentMethodId) {
		this.assessmentMethodId = assessmentMethodId;
	}

	public Boolean getIsGrossNetIncome() {
		return isGrossNetIncome;
	}

	public void setIsGrossNetIncome(Boolean isGrossNetIncome) {
		this.isGrossNetIncome = isGrossNetIncome;
	}

	public Integer getMonIncomeType() {
		return monIncomeType;
	}

	public void setMonIncomeType(Integer monIncomeType) {
		this.monIncomeType = monIncomeType;
	}

	public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public Boolean getIsTimesMultiplierIncome() {
		return isTimesMultiplierIncome;
	}

	public void setIsTimesMultiplierIncome(Boolean isTimesMultiplierIncome) {
		this.isTimesMultiplierIncome = isTimesMultiplierIncome;
	}

	public Integer getMonIncomeMultiplierType() {
		return monIncomeMultiplierType;
	}

	public void setMonIncomeMultiplierType(Integer monIncomeMultiplierType) {
		this.monIncomeMultiplierType = monIncomeMultiplierType;
	}

	public Integer getTimesMultiplier() {
		return timesMultiplier;
	}

	public void setTimesMultiplier(Integer timesMultiplier) {
		this.timesMultiplier = timesMultiplier;
	}

	public Boolean getIsLtv() {
		return isLtv;
	}

	public void setIsLtv(Boolean isLtv) {
		this.isLtv = isLtv;
	}

	public Boolean getIsPurRenConsExpRepCost() {
		return isPurRenConsExpRepCost;
	}

	public void setIsPurRenConsExpRepCost(Boolean isPurRenConsExpRepCost) {
		this.isPurRenConsExpRepCost = isPurRenConsExpRepCost;
	}

	public Boolean getIsMarketValue() {
		return isMarketValue;
	}

	public void setIsMarketValue(Boolean isMarketValue) {
		this.isMarketValue = isMarketValue;
	}

	public Integer getLtvForEligibility() {
		return ltvForEligibility;
	}

	public void setLtvForEligibility(Integer ltvForEligibility) {
		this.ltvForEligibility = ltvForEligibility;
	}

	public Boolean getIsEmploymentWithDisplay() {
		return isEmploymentWithDisplay;
	}

	public void setIsEmploymentWithDisplay(Boolean isEmploymentWithDisplay) {
		this.isEmploymentWithDisplay = isEmploymentWithDisplay;
	}

	public Boolean getIsEmploymentWithMandatory() {
		return isEmploymentWithMandatory;
	}

	public void setIsEmploymentWithMandatory(Boolean isEmploymentWithMandatory) {
		this.isEmploymentWithMandatory = isEmploymentWithMandatory;
	}
	
}