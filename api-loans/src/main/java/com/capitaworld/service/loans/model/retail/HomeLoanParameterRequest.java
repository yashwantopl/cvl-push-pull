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

//  Co-Applicant/Guarantor
//	private Integer coAppOrGuarantor;
//	private Boolean isCoAppOrGuarantorDisplay = false;
//	private Boolean isCoAppOrGuarantorMandatory = false;

	// Number of Co-Applicant?

//	private Integer minNoOfCoAppOrGua;
//	private Integer maxNoOfCoAppOrGua;
//	private Boolean isNoOfCoAppOrGuaDisplay = false;
//	private Boolean isNoOfCoAppOrGuaMandatory = false;

	// Do you wish to consider the Income of Co-Applicant for Assessment of Loan
	// Amount? Yes Or No
//	private Boolean isConsiderIncomeOfCoApp;
//	private Boolean isIsConsiderIncomeOfCoAppDisplay = false;
//	private Boolean isIsConsiderIncomeOfCoAppMandatory = false;

	
	//Net Monthly Income Range (Rs.)
	private Double minNetMonthlyIncomeRange;
	private Double maxNetMonthlyIncomeRange;
	private Boolean isNetMonthlyIncomeRangeDisplay = false;
	private Boolean isNetMonthlyIncomeRangeMandatory = false;
	
	
	// Age of Applicant (Yrs.)
	private Double minAge;
	private Double maxAge;
	private Boolean isAgeDisplay = false;
	private Boolean isAgeMandatory = false;

	// Tenure of Loan (Yrs.)
	private Double minTenure;
	private Double maxTenure;
	private Boolean isTenureDisplay = false;
	private Boolean isTenureMandatory = false;

	// Geographical Market Focus
	private Boolean isGeographicalDisplay = false;
	private Boolean isGeographicalMandatory = false;
	private List<DataRequest> countryList = Collections.emptyList();
	private List<DataRequest> stateList = Collections.emptyList();
	private List<DataRequest> cityList = Collections.emptyList();

	// Minimum Bureau Score
	private Integer minCibilScore;
	private Integer maxCibilScore;
	private Boolean isCibilScoreDisplay = false;
	private Boolean isCibilScoreMandatory = false;
	
	
//	Maximum DPDs (Satisfactory with No Default: DPD) (Days) (Past 12 Months)
	private Integer maxDpds;
	private Boolean isDpdsDisplay = false;
	private Boolean isDpdsMandatory = false;


	//Total Job Experience (Yrs.)
	private Integer minTotalJobExperience;
	private Integer maxTotalJobExperience;
	private Boolean isTotalJobExperienceDisplay = false;
	private Boolean isTotalJobExperienceMandatory = false;
	
	//Total Current Job Experience (Yrs.)
	private Integer minCurrentJobExperience;
	private Integer maxCurrentJobExperience;
	private Boolean isCurrentJobExperienceDisplay = false;
	private Boolean isCurrentJobExperienceMandatory = false;
	
	//Current Employment Status
	private Integer currentEmploymentStatus;
	private Boolean isCurrentEmploymentStatusDisplay = false;
	private Boolean isCurrentEmploymentStatusMandatory = false;
	
	//Purpose of Loan
	private Integer loanPurpose;
	private Boolean isLoanPurposeDisplay = false;
	private Boolean isLoanPurposeMandatory = false;
	
	// Loan Amount (Rs.)
	private Double maxLoanAmount;
	private Double minLoanAmount;
	private Boolean isLoanAmountDisplay = false;
	private Boolean isLoanAmountMandatory = false;
	
//	Residential Status
	private Integer residentialStatus;
	private Boolean isResidentialStatusDisplay = false;
	private Boolean isResidentialStatusMandatory = false;
	
	//Borrower Type
	private Integer borrowerType;
	private Boolean isBorrowerTypeDisplay = false;
	private Boolean isBorrowerTypeMandatory = false;
	
	//Minimum Banking Relationship (Months)
	private Integer minBankRelationship; 
	private Boolean isBankRelationshipDisplay = false;
	private Boolean isBankRelationshipMandatory = false;
	
//	Mode of Salary
	private Integer salaryMode;
	private Boolean isSalaryModeDisplay = false;
	private Boolean isSalaryModeMandatory = false;
	
	//Borrower Salary Account From
	private Integer borrSalAcc;
	private Boolean isBorrSalAccDisplay = false;
	private Boolean isBorrSalAccMandatory = false;
	
//	Loan to Value (LTV) %
	private Double ltv;
	private Boolean isLtvDisplay = false;
	private Boolean isLtvMandatory = false;
	
	//Minimum % of Gross Monthly Income as Take Home Pay
	private Double minGrssMonIncomeAsHomePaySalIndiv;
	private Boolean isGrssMonIncomeAsHomePaySalIndivDisplay = false;
	private Boolean isGrssMonIncomeAsHomePaySalIndivMandatory = false;
	
	//Maximum % of Net Income as Permissible EMI
	private Double maxNetIncomePermissEMISalIndiv;
	private Boolean isNetIncomePermissEMISalIndivDisplay = false;
	private Boolean isNetIncomePermissEMISalIndivMandatory = false;
	
	//Maximum Number of Times of Gross Monthly Income to be considered 
	private Double maxTimeConsiMonthGrssIncomeSalIndiv;
	private Boolean isTimeConsiMonthGrssIncomeSalIndivDisplay = false;
	private Boolean isTimeConsiMonthGrssIncomeSalIndivMandatory = false;
	
	//Minimum % of Gross Monthly Income as Take Home Pay
	private Double maxGrssMonIncomeAsHomePayOthThnSalIndi;
	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiDisplay = false;
	private Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory = false;
	
//	Maximum % of Net Income as Permissible EMI
	private Double maxNetIncomePermissEMIOthThnSalIndi;
	private Boolean isNetIncomePermissEMIOthThnSalIndiDisplay = false;
	private Boolean isNetIncomePermissEMIOthThnSalIndiMandatory = false;
	
//	Maximum Number of Times of Gross Monthly Income to be considered 
	private Double maxTimeConsiMonthGrssIncomeOthThnSalIndi;
	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay = false;
	private Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiMandatory = false;
	
	public HomeLoanParameterRequest() {
		// Do nothing because of X and Y.
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

	public void setIsGrssMonIncomeAsHomePayOthThnSalIndiMandatory(Boolean isGrssMonIncomeAsHomePayOthThnSalIndiMandatory) {
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

	public void setIsTimeConsiMonthGrssIncomeOthThnSalIndiDisplay(Boolean isTimeConsiMonthGrssIncomeOthThnSalIndiDisplay) {
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