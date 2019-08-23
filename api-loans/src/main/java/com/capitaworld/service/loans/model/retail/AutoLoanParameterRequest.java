package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.LoanPurposeAmountMappingRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoLoanParameterRequest extends RetailProduct implements Serializable {
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
	private Boolean isOnRoadPrice;
	private Boolean isExShowroomPrice;
	private Integer ltvForEligibility;

	// private Double currency;

	// Net Monthly Income Range (Rs.)
	private Double minNetMonthlyIncomeRange;
	private Double maxNetMonthlyIncomeRange;
	private Boolean isNetMonthlyIncomeRangeDisplay = false;
	private Boolean isNetMonthlyIncomeRangeMandatory = false;

	// Gross Monthly Income Range (Rs.)
	private Double minGrossMonthlyIncomeRange;
	private Double maxGrossMonthlyIncomeRange;
	private Boolean isGrossMonthlyIncomeRangeDisplay = false;
	private Boolean isGrossMonthlyIncomeRangeMandatory = false;

	// Minimum Net Take Home Salary (Rs.):
	private Double minNetTakeHomeSalary;
	private Boolean isMinNetTakeHomeSalaryDisplay = false;
	private Boolean isMinNetTakeHomeSalaryMandatory = false;

	// Purpose Loan
	private Boolean isPurposeLoanDisplay = false;
	private Boolean isPurposeLoanMandatory = false;
	
	
	// Vehicle Age (Years)
	private Double minVehicleAge;
	private Double maxVehicleAge;
	private Boolean isVehicleAgeDisplay = false;
	private Boolean isVehicleAgeMandatory = false;

	private Integer maxDpds;
	private Boolean isDpdsDisplay = false;
	private Boolean isDpdsMandatory = false;

	private Double minRiskScoreModelCoApp;
	private Boolean isRiskScoreRetailCoAppDisplay = false;
	private Boolean isRiskScoreRetailCoAppMandatory = false;
	
	private Double minEmiMi;
	private Double maxEmiMi;
	private Boolean isEmiMiDisplay = false;
	private Boolean isEmiMiMandatory = false;
	
	private Boolean isCollateralSecurity;
	private Boolean isCollateralSecurityDisplay = false;
	private Boolean isCollateralSecurityMandatory = false;
	
	private Double minNmtlr;
	private Double maxNmtlr;
	private Boolean isNmtlrDisplay = false;
	private Boolean isNmtlrMandatory = false;
	
	private Double minDscr;
	private Double maxDscr;
	private Boolean isDscrDisplay = false;
	private Boolean isDscrMandatory = false;

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
	private Boolean isLoanPurposeDisplay = false;
	private Boolean isLoanPurposeMandatory = false;

	// Residential Status
	private List<Integer> residentialStatusIds = Collections.emptyList();
	private Boolean isResidentialStatusDisplay = false;
	private Boolean isResidentialStatusMandatory = false;

	// Borrower Type
	private List<Integer> borrowerTypeIds = Collections.emptyList();
	private Boolean isBorrowerTypeDisplay = false;
	private Boolean isBorrowerTypeMandatory = false;

	// Borrower Salary Account From
	private List<Integer> borrSalAccIds = Collections.emptyList();
	private Boolean isBorrSalAccDisplay = false;
	private Boolean isBorrSalAccMandatory = false;

	// Borrower Salary Account From
	private List<Integer> employmentWithIds = Collections.emptyList();
	private Boolean isEmploymentWithDisplay = false;
	private Boolean isEmploymentWithMandatory = false;

	private List<Integer> selfEmployedWithIds = Collections.emptyList();
	private Boolean isSelfEmployedWithDisplay = false;
	private Boolean isSelfEmployedWithMandatory = false;
	
	// Residential Status
	private List<Integer> repaymentModeIds = Collections.emptyList();
	private Boolean isRepaymentModeDisplay = false;
	private Boolean isRepaymentModeMandatory = false;

	// Loan to Value (LTV) %
	private Boolean isLtvDisplay = false;
	private Boolean isLtvMandatory = false;

	private Integer salaryAcType;
	
	List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequests = Collections.emptyList();

	private Object workflowData;

	public AutoLoanParameterRequest() {
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

	public List<Integer> getEmploymentWithIds() {
		return employmentWithIds;
	}

	public void setEmploymentWithIds(List<Integer> employmentWithIds) {
		this.employmentWithIds = employmentWithIds;
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

	public List<Integer> getSelfEmployedWithIds() {
		return selfEmployedWithIds;
	}

	public void setSelfEmployedWithIds(List<Integer> selfEmployedWithIds) {
		this.selfEmployedWithIds = selfEmployedWithIds;
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

	public Integer getSalaryAcType() {
		return salaryAcType;
	}

	public void setSalaryAcType(Integer salaryAcType) {
		this.salaryAcType = salaryAcType;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}

	public List<LoanPurposeAmountMappingRequest> getLoanPurposeAmountMappingRequests() {
		return loanPurposeAmountMappingRequests;
	}

	public void setLoanPurposeAmountMappingRequests(
			List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequests) {
		this.loanPurposeAmountMappingRequests = loanPurposeAmountMappingRequests;
	}

	public List<Integer> getRepaymentModeIds() {
		return repaymentModeIds;
	}

	public void setRepaymentModeIds(List<Integer> repaymentModeIds) {
		this.repaymentModeIds = repaymentModeIds;
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
	
}