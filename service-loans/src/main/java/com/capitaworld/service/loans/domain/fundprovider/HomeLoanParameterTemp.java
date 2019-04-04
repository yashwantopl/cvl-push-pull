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
@Table(name = "fp_home_loan_details_temp")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class HomeLoanParameterTemp extends ProductMasterTemp implements Serializable {
	private static final long serialVersionUID = 1L;

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

	// Geographical Market Focus
	@Column(name = "is_geographical_display")
	private Boolean isGeographicalDisplay = false;
	@Column(name = "is_geographical_mandatory")
	private Boolean isGeographicalMandatory = false;

	// Minimum Bureau Score
	@Column(name = "min_cibil_score")
	private Integer minCibilScore;
	@Column(name = "max_cibil_score")
	private Integer maxCibilScore;
	@Column(name = "is_cibil_score_display")
	private Boolean isCibilScoreDisplay = false;
	@Column(name = "is_cibil_score_mandatory")
	private Boolean isCibilScoreMandatory = false;

	// Maximum DPDs (Satisfactory with No Default: DPD) (Days) (Past 12 Months)
	@Column(name = "max_dpds")
	private Integer maxDpds;
	@Column(name = "is_dpds_display")
	private Boolean isDpdsDisplay = false;
	@Column(name = "is_dpds_mandatory")
	private Boolean isDpdsMandatory = false;


	// Total Job Experience (Yrs.)
	@Column(name = "min_total_job_experience")
	private Integer minTotalJobExperience;
	@Column(name = "max_total_job_experience")
	private Integer maxTotalJobExperience;
	@Column(name = "is_total_job_experience_display")
	private Boolean isTotalJobExperienceDisplay = false;
	@Column(name = "is_total_job_experience_mandatory")
	private Boolean isTotalJobExperienceMandatory = false;

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
	@Column(name = "current_employment_status")
	private Integer currentEmploymentStatus;
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
	private Double maxLoanAmount;
	@Column(name = "max_loan_amount")
	private Double minLoanAmount;
	@Column(name = "is_loan_amount_display")
	private Boolean isLoanAmountDisplay = false;
	@Column(name = "is_loan_amount_mandatory")
	private Boolean isLoanAmountMandatory = false;

	// Residential Status
	@Column(name = "residential_status")
	private Integer residentialStatus;
	@Column(name = "is_residential_status_display")
	private Boolean isResidentialStatusDisplay = false;
	@Column(name = "is_residential_status_mandatory")
	private Boolean isResidentialStatusMandatory = false;

	// Borrower Type
	@Column(name = "borrower_type")
	private Integer borrowerType;
	@Column(name = "is_borrower_type_display")
	private Boolean isBorrowerTypeDisplay = false;
	@Column(name = "is_borrower_type_mandatory")
	private Boolean isBorrowerTypeMandatory = false;

	// Minimum Banking Relationship (Months)
	@Column(name = "min_bank_relationship")
	private Integer minBankRelationship;
	@Column(name = "max_bank_relationship")
	private Integer maxBankRelationship;
	@Column(name = "is_bank_relationship_display")
	private Boolean isBankRelationshipDisplay = false;
	@Column(name = "is_bank_relationship_mandatory")
	private Boolean isBankRelationshipMandatory = false;

	// Mode of Salary
	@Column(name = "salary_mode")
	private Integer salaryMode;
	@Column(name = "is_salary_mode_display")
	private Boolean isSalaryModeDisplay = false;
	@Column(name = "is_salary_mode_mandatory")
	private Boolean isSalaryModeMandatory = false;

	// Borrower Salary Account From
	@Column(name = "borr_sal_acc")
	private Integer borrSalAcc;
	@Column(name = "is_borr_sal_acc_display")
	private Boolean isBorrSalAccDisplay = false;
	@Column(name = "is_borr_sal_acc_mandatory")
	private Boolean isBorrSalAccMandatory = false;

	// Loan to Value (LTV) %
	@Column(name = "ltv")
	private Double ltv;
	@Column(name = "is_ltv_display")
	private Boolean isLtvDisplay = false;
	@Column(name = "is_ltv_mandatory")
	private Boolean isLtvMandatory = false;

	// Minimum % of Gross Monthly Income as Take Home Pay
	@Column(name = "min_grss_mon_income_as_home_pay_sal_indiv")
	private Double minGrssMonIncomeAsHomePaySalIndiv;
	@Column(name = "is_grss_mon_income_as_home_pay_sal_indiv_display")
	private Boolean isGrssMonIncomeAsHomePaySalIndivDisplay = false;
	@Column(name = "is_grss_mon_income_as_home_pay_sal_indiv_mandatory")
	private Boolean isGrssMonIncomeAsHomePaySalIndivMandatory = false;

	// Maximum % of Net Income as Permissible EMI
	@Column(name = "max_net_income_permiss_emi_sal_indiv")
	private Double maxNetIncomePermissEMISalIndiv;
	@Column(name = "is_net_income_permiss_emi_sal_indiv_display")
	private Boolean isNetIncomePermissEMISalIndivDisplay = false;
	@Column(name = "is_net_income_permiss_emi_sal_indiv_mandatory")
	private Boolean isNetIncomePermissEMISalIndivMandatory = false;

	// Maximum Number of Times of Gross Monthly Income to be considered 
	@Column(name = "max_time_consi_month_grss_income_sal_indiv")
	private Double maxTimeConsiMonthGrssIncomeSalIndiv;
	@Column(name = "is_time_consi_month_grss_income_sal_indiv_display")
	private Boolean isTimeConsiMonthGrssIncomeSalIndivDisplay = false;
	@Column(name = "is_time_consi_month_grss_income_sal_indiv_mandatory")
	private Boolean isTimeConsiMonthGrssIncomeSalIndivMandatory = false;

	// Minimum % of Gross Monthly Income as Take Home Pay
	@Column(name = "max_grss_mon_income_as_home_pay_oth_thn_sal_indi")
	private Double maxGrssMonIncomeAsHomePayOthThnSalIndi;
	@Column(name = "is_grss_mon_income_as_home_pay_oth_thn_sal_indi_display")
	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiDisplay = false;
	@Column(name = "is_grss_mon_income_as_home_pay_oth_thn_sal_indi_mandatory")
	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory = false;

	// Maximum % of Net Income as Permissible EMI
	@Column(name = "max_net_income_permiss_emi_oth_thn_sal_indiv")
	private Double maxNetIncomePermissEMIOthThnSalIndi;
	@Column(name = "is_net_income_permiss_emi_oth_thn_sal_indiv_display")
	private Boolean isNetIncomePermissEMIOthThnSalIndiDisplay = false;
	@Column(name = "is_net_income_permiss_emi_oth_thn_sal_indiv_mandatory")
	private Boolean isNetIncomePermissEMIOthThnSalIndiMandatory = false;

	// Maximum Number of Times of Gross Monthly Income to be considered 
	@Column(name = "max_time_consi_month_grss_income_oth_thn_sal_indi")
	private Double maxTimeConsiMonthGrssIncomeOthThnSalIndi;
	@Column(name = "is_time_consi_month_grss_income_oth_thn_sal_indi_display")
	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay = false;
	@Column(name = "is_time_consi_month_grss_income_oth_thn_sal_indi_mandatory")
	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory = false;

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

	public Integer getMinCibilScore() {
		return minCibilScore;
	}

	public void setMinCibilScore(Integer minCibilScore) {
		this.minCibilScore = minCibilScore;
	}

	public Integer getMaxCibilScore() {
		return maxCibilScore;
	}

	public void setMaxCibilScore(Integer maxCibilScore) {
		this.maxCibilScore = maxCibilScore;
	}

	public Boolean getIsCibilScoreDisplay() {
		return isCibilScoreDisplay;
	}

	public void setIsCibilScoreDisplay(Boolean isCibilScoreDisplay) {
		this.isCibilScoreDisplay = isCibilScoreDisplay;
	}

	public Boolean getIsCibilScoreMandatory() {
		return isCibilScoreMandatory;
	}

	public void setIsCibilScoreMandatory(Boolean isCibilScoreMandatory) {
		this.isCibilScoreMandatory = isCibilScoreMandatory;
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

	public Integer getCurrentEmploymentStatus() {
		return currentEmploymentStatus;
	}

	public void setCurrentEmploymentStatus(Integer currentEmploymentStatus) {
		this.currentEmploymentStatus = currentEmploymentStatus;
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

	public Integer getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(Integer residentialStatus) {
		this.residentialStatus = residentialStatus;
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

	public Integer getBorrowerType() {
		return borrowerType;
	}

	public void setBorrowerType(Integer borrowerType) {
		this.borrowerType = borrowerType;
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

	public Integer getMinBankRelationship() {
		return minBankRelationship;
	}

	public void setMinBankRelationship(Integer minBankRelationship) {
		this.minBankRelationship = minBankRelationship;
	}

	public Integer getMaxBankRelationship() {
		return maxBankRelationship;
	}

	public void setMaxBankRelationship(Integer maxBankRelationship) {
		this.maxBankRelationship = maxBankRelationship;
	}

	public Boolean getIsBankRelationshipDisplay() {
		return isBankRelationshipDisplay;
	}

	public void setIsBankRelationshipDisplay(Boolean isBankRelationshipDisplay) {
		this.isBankRelationshipDisplay = isBankRelationshipDisplay;
	}

	public Boolean getIsBankRelationshipMandatory() {
		return isBankRelationshipMandatory;
	}

	public void setIsBankRelationshipMandatory(Boolean isBankRelationshipMandatory) {
		this.isBankRelationshipMandatory = isBankRelationshipMandatory;
	}

	public Integer getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(Integer salaryMode) {
		this.salaryMode = salaryMode;
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

	public Integer getBorrSalAcc() {
		return borrSalAcc;
	}

	public void setBorrSalAcc(Integer borrSalAcc) {
		this.borrSalAcc = borrSalAcc;
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

	public Double getLtv() {
		return ltv;
	}

	public void setLtv(Double ltv) {
		this.ltv = ltv;
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

	public Double getMinGrssMonIncomeAsHomePaySalIndiv() {
		return minGrssMonIncomeAsHomePaySalIndiv;
	}

	public void setMinGrssMonIncomeAsHomePaySalIndiv(Double minGrssMonIncomeAsHomePaySalIndiv) {
		this.minGrssMonIncomeAsHomePaySalIndiv = minGrssMonIncomeAsHomePaySalIndiv;
	}

	public Boolean getIsGrssMonIncomeAsHomePaySalIndivDisplay() {
		return isGrssMonIncomeAsHomePaySalIndivDisplay;
	}

	public void setIsGrssMonIncomeAsHomePaySalIndivDisplay(Boolean isGrssMonIncomeAsHomePaySalIndivDisplay) {
		this.isGrssMonIncomeAsHomePaySalIndivDisplay = isGrssMonIncomeAsHomePaySalIndivDisplay;
	}

	public Boolean getIsGrssMonIncomeAsHomePaySalIndivMandatory() {
		return isGrssMonIncomeAsHomePaySalIndivMandatory;
	}

	public void setIsGrssMonIncomeAsHomePaySalIndivMandatory(Boolean isGrssMonIncomeAsHomePaySalIndivMandatory) {
		this.isGrssMonIncomeAsHomePaySalIndivMandatory = isGrssMonIncomeAsHomePaySalIndivMandatory;
	}

	public Double getMaxNetIncomePermissEMISalIndiv() {
		return maxNetIncomePermissEMISalIndiv;
	}

	public void setMaxNetIncomePermissEMISalIndiv(Double maxNetIncomePermissEMISalIndiv) {
		this.maxNetIncomePermissEMISalIndiv = maxNetIncomePermissEMISalIndiv;
	}

	public Boolean getIsNetIncomePermissEMISalIndivDisplay() {
		return isNetIncomePermissEMISalIndivDisplay;
	}

	public void setIsNetIncomePermissEMISalIndivDisplay(Boolean isNetIncomePermissEMISalIndivDisplay) {
		this.isNetIncomePermissEMISalIndivDisplay = isNetIncomePermissEMISalIndivDisplay;
	}

	public Boolean getIsNetIncomePermissEMISalIndivMandatory() {
		return isNetIncomePermissEMISalIndivMandatory;
	}

	public void setIsNetIncomePermissEMISalIndivMandatory(Boolean isNetIncomePermissEMISalIndivMandatory) {
		this.isNetIncomePermissEMISalIndivMandatory = isNetIncomePermissEMISalIndivMandatory;
	}

	public Double getMaxTimeConsiMonthGrssIncomeSalIndiv() {
		return maxTimeConsiMonthGrssIncomeSalIndiv;
	}

	public void setMaxTimeConsiMonthGrssIncomeSalIndiv(Double maxTimeConsiMonthGrssIncomeSalIndiv) {
		this.maxTimeConsiMonthGrssIncomeSalIndiv = maxTimeConsiMonthGrssIncomeSalIndiv;
	}

	public Boolean getIsTimeConsiMonthGrssIncomeSalIndivDisplay() {
		return isTimeConsiMonthGrssIncomeSalIndivDisplay;
	}

	public void setIsTimeConsiMonthGrssIncomeSalIndivDisplay(Boolean isTimeConsiMonthGrssIncomeSalIndivDisplay) {
		this.isTimeConsiMonthGrssIncomeSalIndivDisplay = isTimeConsiMonthGrssIncomeSalIndivDisplay;
	}

	public Boolean getIsTimeConsiMonthGrssIncomeSalIndivMandatory() {
		return isTimeConsiMonthGrssIncomeSalIndivMandatory;
	}

	public void setIsTimeConsiMonthGrssIncomeSalIndivMandatory(Boolean isTimeConsiMonthGrssIncomeSalIndivMandatory) {
		this.isTimeConsiMonthGrssIncomeSalIndivMandatory = isTimeConsiMonthGrssIncomeSalIndivMandatory;
	}

	public Double getMaxGrssMonIncomeAsHomePayOthThnSalIndi() {
		return maxGrssMonIncomeAsHomePayOthThnSalIndi;
	}

	public void setMaxGrssMonIncomeAsHomePayOthThnSalIndi(Double maxGrssMonIncomeAsHomePayOthThnSalIndi) {
		this.maxGrssMonIncomeAsHomePayOthThnSalIndi = maxGrssMonIncomeAsHomePayOthThnSalIndi;
	}

	public Boolean getIsGrssMonIncomeAsHomePayOthThnSalIndiDisplay() {
		return isGrssMonIncomeAsHomePayOthThnSalIndiDisplay;
	}

	public void setIsGrssMonIncomeAsHomePayOthThnSalIndiDisplay(Boolean isGrssMonIncomeAsHomePayOthThnSalIndiDisplay) {
		this.isGrssMonIncomeAsHomePayOthThnSalIndiDisplay = isGrssMonIncomeAsHomePayOthThnSalIndiDisplay;
	}

	public Boolean getIsGrssMonIncomeAsHomePayOthThnSalIndiMandatory() {
		return isGrssMonIncomeAsHomePayOthThnSalIndiMandatory;
	}

	public void setIsGrssMonIncomeAsHomePayOthThnSalIndiMandatory(
			Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory) {
		this.isGrssMonIncomeAsHomePayOthThnSalIndiMandatory = isGrssMonIncomeAsHomePayOthThnSalIndiMandatory;
	}

	public Double getMaxNetIncomePermissEMIOthThnSalIndi() {
		return maxNetIncomePermissEMIOthThnSalIndi;
	}

	public void setMaxNetIncomePermissEMIOthThnSalIndi(Double maxNetIncomePermissEMIOthThnSalIndi) {
		this.maxNetIncomePermissEMIOthThnSalIndi = maxNetIncomePermissEMIOthThnSalIndi;
	}

	public Boolean getIsNetIncomePermissEMIOthThnSalIndiDisplay() {
		return isNetIncomePermissEMIOthThnSalIndiDisplay;
	}

	public void setIsNetIncomePermissEMIOthThnSalIndiDisplay(Boolean isNetIncomePermissEMIOthThnSalIndiDisplay) {
		this.isNetIncomePermissEMIOthThnSalIndiDisplay = isNetIncomePermissEMIOthThnSalIndiDisplay;
	}

	public Boolean getIsNetIncomePermissEMIOthThnSalIndiMandatory() {
		return isNetIncomePermissEMIOthThnSalIndiMandatory;
	}

	public void setIsNetIncomePermissEMIOthThnSalIndiMandatory(Boolean isNetIncomePermissEMIOthThnSalIndiMandatory) {
		this.isNetIncomePermissEMIOthThnSalIndiMandatory = isNetIncomePermissEMIOthThnSalIndiMandatory;
	}

	public Double getMaxTimeConsiMonthGrssIncomeOthThnSalIndi() {
		return maxTimeConsiMonthGrssIncomeOthThnSalIndi;
	}

	public void setMaxTimeConsiMonthGrssIncomeOthThnSalIndi(Double maxTimeConsiMonthGrssIncomeOthThnSalIndi) {
		this.maxTimeConsiMonthGrssIncomeOthThnSalIndi = maxTimeConsiMonthGrssIncomeOthThnSalIndi;
	}

	public Boolean getIsTimeConsiMonthGrssIncomeOthThnSalIndiDisplay() {
		return isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay;
	}

	public void setIsTimeConsiMonthGrssIncomeOthThnSalIndiDisplay(
			Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay) {
		this.isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay = isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay;
	}

	public Boolean getIsTimeConsiMonthGrssIncomeOthThnSalIndiMandatory() {
		return isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory;
	}

	public void setIsTimeConsiMonthGrssIncomeOthThnSalIndiMandatory(
			Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory) {
		this.isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory = isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory;
	}

}