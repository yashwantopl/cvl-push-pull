package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeLoanParameterRequest extends RetailProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	// Number of Co-Applicant?
	private Integer noOfCoAppOrGua;

	// Do you wish to consider the Income of Co-Applicant for Assessment of Loan
	// Amount? Yes Or No
	private Boolean isConsiderIncomeOfCoApp;

	// Maximum Age allowed
	private Integer maxAgeAllowed;

	// private Integer assessmentMethodId;

	private Boolean isGrossNetIncome;
	private Integer monIncomeType;
	private Double foir;

	private Boolean isTimesMultiplierIncome;
	private Integer monIncomeMultiplierType;
	private Integer timesMultiplier;

	private Boolean isLtv;
	private Boolean isPurRenConsExpRepCost;
	private Boolean isMarketValue;
	private Integer ltvForEligibility;

	// private Double currency;

	// Net Monthly Income Range (Rs.)
	private Double minNetMonthlyIncomeRange;
	private Double maxNetMonthlyIncomeRange;
	private Boolean isNetMonthlyIncomeRangeDisplay = false;
	private Boolean isNetMonthlyIncomeRangeMandatory = false;

	// Age of Applicant (Yrs.)
	// private Double minAge;
	// private Double maxAge;
	// private Boolean isAgeDisplay = false;
	// private Boolean isAgeMandatory = false;

	// Tenure of Loan (Yrs.)
	// private Double minTenure;
	// private Double maxTenure;
	// private Boolean isTenureDisplay = false;
	// private Boolean isTenureMandatory = false;

	// Purpose Loan
	private Boolean isPurposeLoanDisplay = false;
	private Boolean isPurposeLoanMandatory = false;

	// Geographical Market Focus
	// private Boolean isGeographicalDisplay = false;
	// private Boolean isGeographicalMandatory = false;
	// private List<DataRequest> countryList = Collections.emptyList();
	// private List<DataRequest> stateList = Collections.emptyList();
	// private List<DataRequest> cityList = Collections.emptyList();

	// Minimum Bureau Score
	// private Integer minBureauScore;
	// private Integer maxBureauScore;
	// private Boolean isBureauScoreDisplay = false;
	// private Boolean isBureauScoreMandatory = false;

	// Maximum DPDs (Satisfactory with No Default: DPD) (Days) (Past 12 Months)
	private Integer maxDpds;
	private Boolean isDpdsDisplay = false;
	private Boolean isDpdsMandatory = false;

	// Risk Model
//	private Double minRiskScoreRetail;
	// private Boolean isRiskScoreRetailDisplay=false;
	// private Boolean isRiskScoreRetailMandatory=false;
	private Double minRiskScoreModelCoApp;
	private Boolean isRiskScoreRetailCoAppDisplay = false;
	private Boolean isRiskScoreRetailCoAppMandatory = false;

	// Total Job Experience (Yrs.)
	// private Integer minTotalJobExperience;
	// private Integer maxTotalJobExperience;
	// private Boolean isTotalJobExperienceDisplay = false;
	// private Boolean isTotalJobExperienceMandatory = false;

	// Total Current Job Experience (Yrs.)
	private Integer minCurrentJobExperience;
	private Integer maxCurrentJobExperience;
	private Boolean isCurrentJobExperienceDisplay = false;
	private Boolean isCurrentJobExperienceMandatory = false;

	// Current Employment Status
	private List<Integer> currentEmploymentStatusIds = Collections.emptyList();
	private Boolean isCurrentEmploymentStatusDisplay = false;
	private Boolean isCurrentEmploymentStatusMandatory = false;

	// Purpose of Loan
	private Integer loanPurpose;
	private Boolean isLoanPurposeDisplay = false;
	private Boolean isLoanPurposeMandatory = false;

	// Loan Amount (Rs.)
	// private Double maxLoanAmount;
	// private Double minLoanAmount;
	// private Boolean isLoanAmountDisplay = false;
	// private Boolean isLoanAmountMandatory = false;

	// Residential Status
	private List<Integer> residentialStatusIds = Collections.emptyList();
	private Boolean isResidentialStatusDisplay = false;
	private Boolean isResidentialStatusMandatory = false;

	// Borrower Type
	private List<Integer> borrowerTypeIds = Collections.emptyList();
	private Boolean isBorrowerTypeDisplay = false;
	private Boolean isBorrowerTypeMandatory = false;

	// Minimum Banking Relationship (Months)
	// private Integer minBankRelationship;
	// private Boolean isBankRelationshipDisplay = false;
	// private Boolean isBankRelationshipMandatory = false;

	// Mode of Salary
	// private List<Integer> salaryModeIds = Collections.emptyList();
	// private Boolean isSalaryModeDisplay = false;
	// private Boolean isSalaryModeMandatory = false;

	// Borrower Salary Account From
	private List<Integer> borrSalAccIds = Collections.emptyList();
	private Boolean isBorrSalAccDisplay = false;
	private Boolean isBorrSalAccMandatory = false;

	// Loan to Value (LTV) %
	private Double minLtv;
	private Double maxLtv;
	private Boolean isLtvDisplay = false;
	private Boolean isLtvMandatory = false;

	// Minimum % of Gross Monthly Income as Take Home Pay
//	private Double minGrssMonIncomeAsHomePaySalIndiv;
//	private Boolean isGrssMonIncomeAsHomePaySalIndivDisplay = false;
//	private Boolean isGrssMonIncomeAsHomePaySalIndivMandatory = false;
//
//	// Maximum % of Net Income as Permissible EMI
//	private Double maxNetIncomePermissEMISalIndiv;
//	private Boolean isNetIncomePermissEMISalIndivDisplay = false;
//	private Boolean isNetIncomePermissEMISalIndivMandatory = false;
//
//	// Maximum Number of Times of Gross Monthly Income to be considered 
//	private Double maxTimeConsiMonthGrssIncomeSalIndiv;
//	private Boolean isTimeConsiMonthGrssIncomeSalIndivDisplay = false;
//	private Boolean isTimeConsiMonthGrssIncomeSalIndivMandatory = false;
//
//	// Minimum % of Gross Monthly Income as Take Home Pay
//	private Double maxGrssMonIncomeAsHomePayOthThnSalIndi;
//	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiDisplay = false;
//	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory = false;
//
//	// Maximum % of Net Income as Permissible EMI
//	private Double maxNetIncomePermissEMIOthThnSalIndi;
//	private Boolean isNetIncomePermissEMIOthThnSalIndiDisplay = false;
//	private Boolean isNetIncomePermissEMIOthThnSalIndiMandatory = false;
//
//	// Maximum Number of Times of Gross Monthly Income to be considered 
//	private Double maxTimeConsiMonthGrssIncomeOthThnSalIndi;
//	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay = false;
//	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory = false;

	private Object workflowData;
	
	public HomeLoanParameterRequest() {
		// Do nothing because of X and Y.
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

	public List<Integer> getCurrentEmploymentStatusIds() {
		return currentEmploymentStatusIds;
	}

	public void setCurrentEmploymentStatusIds(List<Integer> currentEmploymentStatusIds) {
		this.currentEmploymentStatusIds = currentEmploymentStatusIds;
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

	public List<Integer> getResidentialStatusIds() {
		return residentialStatusIds;
	}

	public void setResidentialStatusIds(List<Integer> residentialStatusIds) {
		this.residentialStatusIds = residentialStatusIds;
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

	public List<Integer> getBorrowerTypeIds() {
		return borrowerTypeIds;
	}

	public void setBorrowerTypeIds(List<Integer> borrowerTypeIds) {
		this.borrowerTypeIds = borrowerTypeIds;
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

	public List<Integer> getBorrSalAccIds() {
		return borrSalAccIds;
	}

	public void setBorrSalAccIds(List<Integer> borrSalAccIds) {
		this.borrSalAccIds = borrSalAccIds;
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

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}
}