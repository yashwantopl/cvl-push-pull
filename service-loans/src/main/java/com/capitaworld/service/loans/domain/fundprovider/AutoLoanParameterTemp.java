package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the fp_auto_loan_details_temp database table.
 * 
 */
@Entity
@Table(name="fp_auto_loan_details_temp")
public class AutoLoanParameterTemp extends ProductMasterTemp implements Serializable {
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

	
	// Gross Monthly Income Range (Rs.)
	@Column(name = "min_gross_monthly_income_range")
	private Double minGrossMonthlyIncomeRange;
	@Column(name = "max_gross_monthly_income_range")
	private Double maxGrossMonthlyIncomeRange;
	@Column(name = "is_gross_monthly_income_range_display")
	private Boolean isGrossMonthlyIncomeRangeDisplay = false;
	@Column(name = "is_gross_monthly_income_range_mandatory")
	private Boolean isGrossMonthlyIncomeRangeMandatory = false;
	

	// Minimum Net Take Home Salary (Rs.): 
	@Column(name = "min_net_take_home_salary")
	private Double minNetTakeHomeSalary;
	@Column(name = "is_min_net_take_home_salary_display")
	private Boolean isMinNetTakeHomeSalaryDisplay = false;
	@Column(name = "is_min_net_take_home_salary_mandatory")
	private Boolean isMinNetTakeHomeSalaryMandatory = false;
	
	// Age of Applicant (Yrs.)
	@Column(name = "min_age")
	private Double minAge;
	@Column(name = "max_age")
	private Double maxAge;
	@Column(name = "is_age_display")
	private Boolean isAgeDisplay = false;
	@Column(name = "is_age_mandatory")
	private Boolean isAgeMandatory = false;
	
	// Age of Vehicle (Yrs.)
	@Column(name = "min_vehicle_age")
	private Double minVehicleAge;
	@Column(name = "max_vehicle_age")
	private Double maxVehicleAge;
	@Column(name = "is_vehicle_age_display")
	private Boolean isVehicleAgeDisplay = false;
	@Column(name = "is_vehicle_age_mandatory")
	private Boolean isVehicleAgeMandatory = false;

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
	private Double minBureauScore;
	@Column(name = "max_bureau_score")
	private Double maxBureauScore;
	@Column(name = "is_bureau_score_display")
	private Boolean isBureauScoreDisplay = false;
	@Column(name = "is_bureau_score_mandatory")
	private Boolean isBureauScoreMandatory = false;
	
	//Less Than 6 month of Credit History
	@Column(name = "min_bureau_score_less_than6_month")
	private Integer minBureauScoreLessThan6Month;
	
	//No Credit History
	@Column(name = "no_bureau_credit_history")
	private Boolean noBureauCreditHistory = true;
	
	

	// Maximum DPDs (Satisfactory with No Default: DPD) (Days) (Past 12 Months)
	@Column(name = "max_dpds")
	private Integer maxDpds;
	@Column(name = "is_dpds_display")
	private Boolean isDpdsDisplay = false;
	@Column(name = "is_dpds_mandatory")
	private Boolean isDpdsMandatory = false;
	
	//Risk Model for Salaried
	@Column(name="min_risk_score_model")
	private Double minRiskScoreRetail;
	@Column(name="is_risk_score_model_display")
	private Boolean isRiskScoreRetailDisplay=true;
	@Column(name="is_risk_score_model_mandatory")
	private Boolean isRiskScoreRetailMandatory=true;
	
	@Column(name="min_risk_score_model_co_app")
	private Double minRiskScoreModelCoApp;
	@Column(name="is_risk_score_model_coapp_display")
	private Boolean isRiskScoreRetailCoAppDisplay=true;
	@Column(name="is_risk_score_model_coapp_mandatory")
	private Boolean isRiskScoreRetailCoAppMandatory=true;
	
	
	//Risk Model For Other than Salaried
	@Column(name="min_risk_score_model_oth_thn_sal")
	private Double minRiskScoreRetailOthThnSal;
	@Column(name="is_risk_score_model_oth_thn_sal_display")
	private Boolean isRiskScoreRetailOthThnSalDisplay=true;
	@Column(name="is_risk_score_model_oth_thn_sal_mandatory")
	private Boolean isRiskScoreRetailOthThnSalMandatory=true;
	
	@Column(name="min_risk_score_model_co_app_oth_thn_sal")
	private Double minRiskScoreModelCoAppOthThnSal;
	@Column(name="is_risk_score_model_coapp_oth_thn_sal_display")
	private Boolean isRiskScoreRetailCoAppOthThnSalDisplay=true;
	@Column(name="is_risk_score_model_coapp_oth_thn_sal_mandatory")
	private Boolean isRiskScoreRetailCoAppOthThnSalMandatory=true;


	// Total Job Experience (Yrs.)
	@Column(name = "min_total_job_experience")
	private Double minTotalJobExp;
	@Column(name = "max_total_job_experience")
	private Double maxTotalJobExp;
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
	@Column(name = "is_loan_purpose_display")
	private Boolean isLoanPurposeDisplay = false;
	@Column(name = "is_loan_purpose_mandatory")
	private Boolean isLoanPurposeMandatory = false;

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
	private Double minBankRelation;
	@Column(name = "max_bank_relationship")
	private Double maxBankRelation;
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
	
	// Eligibile Professional From
	@Column(name = "is_self_employed_with_display")
	private Boolean isSelfEmployedWithDisplay = false;
	@Column(name = "is_self_employed_with_mandatory")
	private Boolean isSelfEmployedWithMandatory = false;

	// Tangible Net Worth to Loan Ratio (For Other than Salaried Employees) %
	
	@Column(name = "min_nmtlr")
	private Double minNmtlr;
	@Column(name = "max_nmtlr")
	private Double maxNmtlr;
	@Column(name = "is_nmtlr_display")
	private Boolean isNmtlrDisplay = false;
	@Column(name = "is_nmtlr_mandatory")
	private Boolean isNmtlrMandatory = false;
	
//	DSCR (only for proprietorship/partnership firms, corporate entities etc. except HUF.)
	@Column(name = "min_dscr")
	private Double minDscr;
	@Column(name = "max_dscr")
	private Double maxDscr;
	@Column(name = "is_dscr_display")
	private Boolean isDscrDisplay = false;
	@Column(name = "is_dscr_mandatory")
	private Boolean isDscrMandatory = false;
	
//	EMI/ MI (Net or Gross)
	
	@Column(name = "min_emi_mi")
	private Double minEmiMi;
	@Column(name = "max_emi_mi")
	private Double maxEmiMi;
	@Column(name = "is_emi_mi_display")
	private Boolean isEmiMiDisplay = false;
	@Column(name = "is_emi_mi_mandatory")
	private Boolean isEmiMiMandatory = false;
	
//	Collateral Security (Hypothecation of vehicle purchased out of Bank Finance)
	
	@Column(name = "is_collateral_security")
	private Boolean isCollateralSecurity;
	@Column(name = "is_collateral_security_display")
	private Boolean isCollateralSecurityDisplay = false;
	@Column(name = "is_collateral_security_mandatory")
	private Boolean isCollateralSecurityMandatory = false;
	
	@Column(name = "is_repayment_mode_display")
	private Boolean isRepaymentModeDisplay = false;
	@Column(name = "is_repayment_mode_mandatory")
	private Boolean isRepaymentModeMandatory = false;

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
	
	@Column(name = "is_on_road_price")
	private Boolean isOnRoadPrice;
	
	@Column(name = "is_ex_showroom_price")
	private Boolean isExShowroomPrice;
	
	@Column(name = "is_agreed_purchase_price")
	private Boolean isAgreedPurchasePrice;
	
	@Column(name = "ltv_for_eligibility")
	private Integer ltvForEligibility;
	
	@Column(name="salary_ac_type")
	private Integer salaryAcType;

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

	public Double getMinGrossMonthlyIncomeRange() {
		return minGrossMonthlyIncomeRange;
	}

	public void setMinGrossMonthlyIncomeRange(Double minGrossMonthlyIncomeRange) {
		this.minGrossMonthlyIncomeRange = minGrossMonthlyIncomeRange;
	}

	public Double getMaxGrossMonthlyIncomeRange() {
		return maxGrossMonthlyIncomeRange;
	}

	public void setMaxGrossMonthlyIncomeRange(Double maxGrossMonthlyIncomeRange) {
		this.maxGrossMonthlyIncomeRange = maxGrossMonthlyIncomeRange;
	}

	public Boolean getIsGrossMonthlyIncomeRangeDisplay() {
		return isGrossMonthlyIncomeRangeDisplay;
	}

	public void setIsGrossMonthlyIncomeRangeDisplay(Boolean isGrossMonthlyIncomeRangeDisplay) {
		this.isGrossMonthlyIncomeRangeDisplay = isGrossMonthlyIncomeRangeDisplay;
	}

	public Boolean getIsGrossMonthlyIncomeRangeMandatory() {
		return isGrossMonthlyIncomeRangeMandatory;
	}

	public void setIsGrossMonthlyIncomeRangeMandatory(Boolean isGrossMonthlyIncomeRangeMandatory) {
		this.isGrossMonthlyIncomeRangeMandatory = isGrossMonthlyIncomeRangeMandatory;
	}

	public Double getMinNetTakeHomeSalary() {
		return minNetTakeHomeSalary;
	}

	public void setMinNetTakeHomeSalary(Double minNetTakeHomeSalary) {
		this.minNetTakeHomeSalary = minNetTakeHomeSalary;
	}

	public Boolean getIsMinNetTakeHomeSalaryDisplay() {
		return isMinNetTakeHomeSalaryDisplay;
	}

	public void setIsMinNetTakeHomeSalaryDisplay(Boolean isMinNetTakeHomeSalaryDisplay) {
		this.isMinNetTakeHomeSalaryDisplay = isMinNetTakeHomeSalaryDisplay;
	}

	public Boolean getIsMinNetTakeHomeSalaryMandatory() {
		return isMinNetTakeHomeSalaryMandatory;
	}

	public void setIsMinNetTakeHomeSalaryMandatory(Boolean isMinNetTakeHomeSalaryMandatory) {
		this.isMinNetTakeHomeSalaryMandatory = isMinNetTakeHomeSalaryMandatory;
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

	public Double getMinRiskScoreRetailOthThnSal() {
		return minRiskScoreRetailOthThnSal;
	}

	public void setMinRiskScoreRetailOthThnSal(Double minRiskScoreRetailOthThnSal) {
		this.minRiskScoreRetailOthThnSal = minRiskScoreRetailOthThnSal;
	}

	public Boolean getIsRiskScoreRetailOthThnSalDisplay() {
		return isRiskScoreRetailOthThnSalDisplay;
	}

	public void setIsRiskScoreRetailOthThnSalDisplay(Boolean isRiskScoreRetailOthThnSalDisplay) {
		this.isRiskScoreRetailOthThnSalDisplay = isRiskScoreRetailOthThnSalDisplay;
	}

	public Boolean getIsRiskScoreRetailOthThnSalMandatory() {
		return isRiskScoreRetailOthThnSalMandatory;
	}

	public void setIsRiskScoreRetailOthThnSalMandatory(Boolean isRiskScoreRetailOthThnSalMandatory) {
		this.isRiskScoreRetailOthThnSalMandatory = isRiskScoreRetailOthThnSalMandatory;
	}

	public Double getMinRiskScoreModelCoAppOthThnSal() {
		return minRiskScoreModelCoAppOthThnSal;
	}

	public void setMinRiskScoreModelCoAppOthThnSal(Double minRiskScoreModelCoAppOthThnSal) {
		this.minRiskScoreModelCoAppOthThnSal = minRiskScoreModelCoAppOthThnSal;
	}

	public Boolean getIsRiskScoreRetailCoAppOthThnSalDisplay() {
		return isRiskScoreRetailCoAppOthThnSalDisplay;
	}

	public void setIsRiskScoreRetailCoAppOthThnSalDisplay(Boolean isRiskScoreRetailCoAppOthThnSalDisplay) {
		this.isRiskScoreRetailCoAppOthThnSalDisplay = isRiskScoreRetailCoAppOthThnSalDisplay;
	}

	public Boolean getIsRiskScoreRetailCoAppOthThnSalMandatory() {
		return isRiskScoreRetailCoAppOthThnSalMandatory;
	}

	public void setIsRiskScoreRetailCoAppOthThnSalMandatory(Boolean isRiskScoreRetailCoAppOthThnSalMandatory) {
		this.isRiskScoreRetailCoAppOthThnSalMandatory = isRiskScoreRetailCoAppOthThnSalMandatory;
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

	public Boolean getIsSelfEmployedWithDisplay() {
		return isSelfEmployedWithDisplay;
	}

	public void setIsSelfEmployedWithDisplay(Boolean isSelfEmployedWithDisplay) {
		this.isSelfEmployedWithDisplay = isSelfEmployedWithDisplay;
	}

	public Boolean getIsSelfEmployedWithMandatory() {
		return isSelfEmployedWithMandatory;
	}

	public void setIsSelfEmployedWithMandatory(Boolean isSelfEmployedWithMandatory) {
		this.isSelfEmployedWithMandatory = isSelfEmployedWithMandatory;
	}

	public Double getMinNmtlr() {
		return minNmtlr;
	}

	public void setMinNmtlr(Double minNmtlr) {
		this.minNmtlr = minNmtlr;
	}

	public Double getMaxNmtlr() {
		return maxNmtlr;
	}

	public void setMaxNmtlr(Double maxNmtlr) {
		this.maxNmtlr = maxNmtlr;
	}

	public Boolean getIsNmtlrDisplay() {
		return isNmtlrDisplay;
	}

	public void setIsNmtlrDisplay(Boolean isNmtlrDisplay) {
		this.isNmtlrDisplay = isNmtlrDisplay;
	}

	public Boolean getIsNmtlrMandatory() {
		return isNmtlrMandatory;
	}

	public void setIsNmtlrMandatory(Boolean isNmtlrMandatory) {
		this.isNmtlrMandatory = isNmtlrMandatory;
	}

	public Double getMinEmiMi() {
		return minEmiMi;
	}

	public void setMinEmiMi(Double minEmiMi) {
		this.minEmiMi = minEmiMi;
	}

	public Double getMaxEmiMi() {
		return maxEmiMi;
	}

	public void setMaxEmiMi(Double maxEmiMi) {
		this.maxEmiMi = maxEmiMi;
	}

	public Boolean getIsEmiMiDisplay() {
		return isEmiMiDisplay;
	}

	public void setIsEmiMiDisplay(Boolean isEmiMiDisplay) {
		this.isEmiMiDisplay = isEmiMiDisplay;
	}

	public Boolean getIsEmiMiMandatory() {
		return isEmiMiMandatory;
	}

	public void setIsEmiMiMandatory(Boolean isEmiMiMandatory) {
		this.isEmiMiMandatory = isEmiMiMandatory;
	}

	public Boolean getIsCollateralSecurity() {
		return isCollateralSecurity;
	}

	public void setIsCollateralSecurity(Boolean isCollateralSecurity) {
		this.isCollateralSecurity = isCollateralSecurity;
	}

	public Boolean getIsCollateralSecurityDisplay() {
		return isCollateralSecurityDisplay;
	}

	public void setIsCollateralSecurityDisplay(Boolean isCollateralSecurityDisplay) {
		this.isCollateralSecurityDisplay = isCollateralSecurityDisplay;
	}

	public Boolean getIsCollateralSecurityMandatory() {
		return isCollateralSecurityMandatory;
	}

	public void setIsCollateralSecurityMandatory(Boolean isCollateralSecurityMandatory) {
		this.isCollateralSecurityMandatory = isCollateralSecurityMandatory;
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

	public Boolean getIsOnRoadPrice() {
		return isOnRoadPrice;
	}

	public void setIsOnRoadPrice(Boolean isOnRoadPrice) {
		this.isOnRoadPrice = isOnRoadPrice;
	}

	public Boolean getIsExShowroomPrice() {
		return isExShowroomPrice;
	}

	public void setIsExShowroomPrice(Boolean isExShowroomPrice) {
		this.isExShowroomPrice = isExShowroomPrice;
	}

	public Integer getLtvForEligibility() {
		return ltvForEligibility;
	}

	public void setLtvForEligibility(Integer ltvForEligibility) {
		this.ltvForEligibility = ltvForEligibility;
	}

	public Integer getSalaryAcType() {
		return salaryAcType;
	}

	public void setSalaryAcType(Integer salaryAcType) {
		this.salaryAcType = salaryAcType;
	}

	public Boolean getIsRepaymentModeDisplay() {
		return isRepaymentModeDisplay;
	}

	public void setIsRepaymentModeDisplay(Boolean isRepaymentModeDisplay) {
		this.isRepaymentModeDisplay = isRepaymentModeDisplay;
	}

	public Boolean getIsRepaymentModeMandatory() {
		return isRepaymentModeMandatory;
	}

	public void setIsRepaymentModeMandatory(Boolean isRepaymentModeMandatory) {
		this.isRepaymentModeMandatory = isRepaymentModeMandatory;
	}

	public Double getMinVehicleAge() {
		return minVehicleAge;
	}

	public void setMinVehicleAge(Double minVehicleAge) {
		this.minVehicleAge = minVehicleAge;
	}

	public Double getMaxVehicleAge() {
		return maxVehicleAge;
	}

	public void setMaxVehicleAge(Double maxVehicleAge) {
		this.maxVehicleAge = maxVehicleAge;
	}

	public Boolean getIsVehicleAgeDisplay() {
		return isVehicleAgeDisplay;
	}

	public void setIsVehicleAgeDisplay(Boolean isVehicleAgeDisplay) {
		this.isVehicleAgeDisplay = isVehicleAgeDisplay;
	}

	public Boolean getIsVehicleAgeMandatory() {
		return isVehicleAgeMandatory;
	}

	public void setIsVehicleAgeMandatory(Boolean isVehicleAgeMandatory) {
		this.isVehicleAgeMandatory = isVehicleAgeMandatory;
	}

	public Double getMinDscr() {
		return minDscr;
	}

	public void setMinDscr(Double minDscr) {
		this.minDscr = minDscr;
	}

	public Double getMaxDscr() {
		return maxDscr;
	}

	public void setMaxDscr(Double maxDscr) {
		this.maxDscr = maxDscr;
	}

	public Boolean getIsDscrDisplay() {
		return isDscrDisplay;
	}

	public void setIsDscrDisplay(Boolean isDscrDisplay) {
		this.isDscrDisplay = isDscrDisplay;
	}

	public Boolean getIsDscrMandatory() {
		return isDscrMandatory;
	}

	public void setIsDscrMandatory(Boolean isDscrMandatory) {
		this.isDscrMandatory = isDscrMandatory;
	}

	public Boolean getIsAgreedPurchasePrice() {
		return isAgreedPurchasePrice;
	}

	public void setIsAgreedPurchasePrice(Boolean isAgreedPurchasePrice) {
		this.isAgreedPurchasePrice = isAgreedPurchasePrice;
	}
}