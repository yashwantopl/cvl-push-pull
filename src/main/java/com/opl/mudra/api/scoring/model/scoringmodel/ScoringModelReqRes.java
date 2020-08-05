package com.opl.mudra.api.scoring.model.scoringmodel;

import java.util.List;

import com.opl.mudra.api.scoring.InactiveResponse;
import com.opl.mudra.api.scoring.model.GenericCheckerReqRes;


public class ScoringModelReqRes {

	private Long id;

	private Integer status;

	private String message;

	/////////

	private Long userId;

	private Long orgId;

	private Long fpProductId;
	
	private Long applicationId;

	private Integer scoringModelBasedOn;

	////////

	private Long  scoringModelMasterId ;
	
	private Long scoringModelId;
	
	private Long copyScoringModelId;
	
	private Long copyEmploymentTypeId;

	private String scoringModelName;

	private Long businessTypeId;
	
	private Long loanPurposeModelId;
	
	private Integer  employmentTypeId;

	private Integer financialTypeId;
	
	private Double totalScore;
	
	private Double totalCoAppScore;

	private Double managementRiskWeight;

	private Double financialRiskWeight;

	private Double businessRiskWeight;

	private Boolean isWeightConsider;

	private Boolean isGSTRegularHolder;

	private Boolean isGSTCompositeSchemeHolder;

	private Boolean isGSTNotApplicable;

	private Double proportionateScore;

	private Boolean isProportionateScoreConsider;

	private Integer scoringType;

	/////////////////

	private List<RiskGradingReqRes> riskGradingReqResList;
	
	private List<RiskGradingReqRes> riskCoAppGradingReqResList;
	
	private List<RiskGradingReqRes> riskLoanAmountGradingReqResList;
	
	private List<RiskGradingReqRes> riskTenureGradingReqResList;
	
	private List<RiskGradingReqRes> riskPropertyValueGradingReqResList;
	
	private List<RiskGradingReqRes> riskLTVGradingReqResList;

	///////////
	private List<RiskGradingReqRes> riskcreditHistoryGreaterListGradingReqResList; // 
	private List<RiskGradingReqRes> riskcreditHistoryLessListGradingReqResList; // 
	private List<RiskGradingReqRes> riskNocreditHistoryReqResList;
	private List<RiskGradingReqRes> riskExShowRoomPriceReqResList;
	private List<RiskGradingReqRes> riskOnRoadPriceReqResList;
	private List<RiskGradingReqRes> riskAgeVehicleReqResList;
	private List<RiskGradingReqRes> riskAgreedPurchaseValueIDVReqResList;
	private List<RiskGradingReqRes> riskMclrReqResList;
	private List<RiskGradingReqRes> riskVehicleTypeReqResList;
	private List<RiskGradingReqRes> riskRoiReqResList;
	private List<RiskGradingReqRes> riskCibilTenureReqResList;
	

	private List<FieldMappingReqRes> fieldMappingReqResList;

	////////

	private ScoringModelResponse scoringModelResponse;

	private List<ScoringModelResponse> scoringModelResponseList;

	private Object workFlowData;

	private List<Long> rolesList;
	////////

	private Boolean isEdit;

	private Long jobId;

	//not in use till now
	private Integer requestType;

	private Double minMargin;

	private Double maxMargin;

	private List<Long> scoringModelIdList;

	private Boolean isInTemp=true;

	private Double avgROI;

	private Double avgTenure;

	private Double avgPF;

	private Double avgFOIR;
	
	private Double avgLTV;

	private Double minRoi;

	private Double minTenure;
	
	private List<RiskGradingReqRes> grossIncomeGradingReqResList;

	private List<RiskGradingReqRes> netIncomeGradingReqResList;

	private List<RiskGradingReqRes> cibilGradingReqResList;
	
	private Integer marginScaling;
	
	private Integer roiScaling;
	
	private Integer tenureScaling;
	
	private Integer pfScaling;
	
	private Integer ltvScaling;
	
	private Integer  cibilGreterThenConcessionScaling;
	private Integer  cibilLessThenConcessionScaling;
	private Integer  cibilNoCreditConcessionScaling;
	
	
	// STARTS HERE CONCESSION RATE OF INTEREST
	private Boolean isRateOfInterest;
	
	private Double newBorrowersHavingAccounts;
	private Boolean isBorrowersHavingAccounts;
	
	private Double newBorrowersAvailingLoans;
	private  Boolean isBorrowersAvailingLoans;
	
	private Double newBorrowersHavingSalaryAccounts;	
	private Boolean isBorrowersHavingSalaryAccounts;
	
	private Double newBorrowersAvailingCreaditCards;	
	private Boolean isBorrowersAvailingCreaditCards;
	
	
	// STARTS HERE FOR LOGIC FOR CHECK OF CONCESSION RELATED 
	private Boolean isConcessionCheckOff; 

	private Boolean isPatiallyCheckOff;
	private Double newPartiallyCheckOff;
	
	private Boolean isFullyCheckOff;
	private Double newFullyCheckOff;
	// ENDS HERE CHECK OFF RELATED 

	private Boolean isWomenApplicant;
	private Double newWomenApplicant;
	// ENDS HERE CONCESSION RATE OF INTEREST
	
	// CIBIL BASED CONCESSION RATE  OF INTEREST
	private Boolean isConcessionBasedOnBureau;
	private Boolean isCreaditHisotryGreaterSixMonths;
	private Boolean isCreaditHisotryLessThenSixMonths;
	private Boolean isNoCreaditHistory;
	// ENDS HERE 
	
	private Double finalMaxFpConcessionRateOfInterest;
	private Double cibilFpConcessionRoi;
	private Double mclrRoi;
	

	// Get For Average Call
	private Double cibilScoreActual;
	private Double cibilScoreActualVersion2;
	private Double grossAnualIncome;
	private Double netAnnualIncome;
	private Double propertyValue;
	private Integer vehicleTypeId;
	private Integer loanPurpose;
	private Double onRoadPrice;
	private Double exShowroomPrice;
	private Double ageOfVehicle;
	private Double agreedPurchasePriceAndIdv;
	
	private Integer cibilBureauVersionConcession;
	
	private Integer cibilBureauGradVersion;
	
	private Object workFlowDataForRemove;
	
	private Long removeJobId;
	
	private List<GenericCheckerReqRes> workflowRemove;
	
	private InactiveResponse  inactiveResponse;
	
private Boolean isAllowForRemove;
	
	private Integer reqType;
	
	private Long scoringId;

	
	public Double getMclrRoi() {
		return mclrRoi;
	}

	public void setMclrRoi(Double mclrRoi) {
		this.mclrRoi = mclrRoi;
	}

	public Double getCibilFpConcessionRoi() {
		return cibilFpConcessionRoi;
	}

	public void setCibilFpConcessionRoi(Double cibilFpConcessionRoi) {
		this.cibilFpConcessionRoi = cibilFpConcessionRoi;
	}

	public Double getFinalMaxFpConcessionRateOfInterest() {
		return finalMaxFpConcessionRateOfInterest;
	}

	public void setFinalMaxFpConcessionRateOfInterest(Double finalMaxFpConcessionRateOfInterest) {
		this.finalMaxFpConcessionRateOfInterest = finalMaxFpConcessionRateOfInterest;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public Double getCibilScoreActual() {
		return cibilScoreActual;
	}

	public void setCibilScoreActual(Double cibilScoreActual) {
		this.cibilScoreActual = cibilScoreActual;
	}

	public Double getGrossAnualIncome() {
		return grossAnualIncome;
	}

	public void setGrossAnualIncome(Double grossAnualIncome) {
		this.grossAnualIncome = grossAnualIncome;
	}

	public Double getNetAnnualIncome() {
		return netAnnualIncome;
	}

	public void setNetAnnualIncome(Double netAnnualIncome) {
		this.netAnnualIncome = netAnnualIncome;
	}

	public Boolean getIsConcessionBasedOnBureau() {
		return isConcessionBasedOnBureau;
	}

	public void setIsConcessionBasedOnBureau(Boolean isConcessionBasedOnBureau) {
		this.isConcessionBasedOnBureau = isConcessionBasedOnBureau;
	}

	public Boolean getIsCreaditHisotryGreaterSixMonths() {
		return isCreaditHisotryGreaterSixMonths;
	}

	public void setIsCreaditHisotryGreaterSixMonths(Boolean isCreaditHisotryGreaterSixMonths) {
		this.isCreaditHisotryGreaterSixMonths = isCreaditHisotryGreaterSixMonths;
	}

	public Boolean getIsCreaditHisotryLessThenSixMonths() {
		return isCreaditHisotryLessThenSixMonths;
	}

	public void setIsCreaditHisotryLessThenSixMonths(Boolean isCreaditHisotryLessThenSixMonths) {
		this.isCreaditHisotryLessThenSixMonths = isCreaditHisotryLessThenSixMonths;
	}

	public Boolean getIsNoCreaditHistory() {
		return isNoCreaditHistory;
	}

	public void setIsNoCreaditHistory(Boolean isNoCreaditHistory) {
		this.isNoCreaditHistory = isNoCreaditHistory;
	}

	public Boolean getIsRateOfInterest() {
		return isRateOfInterest;
	}
	public Integer getCibilGreterThenConcessionScaling() {
		return cibilGreterThenConcessionScaling;
	}

	public void setCibilGreterThenConcessionScaling(Integer cibilGreterThenConcessionScaling) {
		this.cibilGreterThenConcessionScaling = cibilGreterThenConcessionScaling;
	}

	public Integer getCibilLessThenConcessionScaling() {
		return cibilLessThenConcessionScaling;
	}

	public void setCibilLessThenConcessionScaling(Integer cibilLessThenConcessionScaling) {
		this.cibilLessThenConcessionScaling = cibilLessThenConcessionScaling;
	}

	public Integer getCibilNoCreditConcessionScaling() {
		return cibilNoCreditConcessionScaling;
	}

	public void setCibilNoCreditConcessionScaling(Integer cibilNoCreditConcessionScaling) {
		this.cibilNoCreditConcessionScaling = cibilNoCreditConcessionScaling;
	}

	public Boolean getIsPatiallyCheckOff() {
		return isPatiallyCheckOff;
	}

	public void setIsPatiallyCheckOff(Boolean isPatiallyCheckOff) {
		this.isPatiallyCheckOff = isPatiallyCheckOff;
	}

	public Double getNewPartiallyCheckOff() {
		return newPartiallyCheckOff;
	}

	public void setNewPartiallyCheckOff(Double newPartiallyCheckOff) {
		this.newPartiallyCheckOff = newPartiallyCheckOff;
	}

	public Boolean getIsFullyCheckOff() {
		return isFullyCheckOff;
	}

	public void setIsFullyCheckOff(Boolean isFullyCheckOff) {
		this.isFullyCheckOff = isFullyCheckOff;
	}

	public Double getNewFullyCheckOff() {
		return newFullyCheckOff;
	}

	public void setNewFullyCheckOff(Double newFullyCheckOff) {
		this.newFullyCheckOff = newFullyCheckOff;
	}

	public void setIsRateOfInterest(Boolean isRateOfInterest) {
		this.isRateOfInterest = isRateOfInterest;
	}

	public Double getNewBorrowersHavingAccounts() {
		return newBorrowersHavingAccounts;
	}

	public void setNewBorrowersHavingAccounts(Double newBorrowersHavingAccounts) {
		this.newBorrowersHavingAccounts = newBorrowersHavingAccounts;
	}

	public Boolean getIsBorrowersHavingAccounts() {
		return isBorrowersHavingAccounts;
	}

	public void setIsBorrowersHavingAccounts(Boolean isBorrowersHavingAccounts) {
		this.isBorrowersHavingAccounts = isBorrowersHavingAccounts;
	}

	public Double getNewBorrowersAvailingLoans() {
		return newBorrowersAvailingLoans;
	}

	public void setNewBorrowersAvailingLoans(Double newBorrowersAvailingLoans) {
		this.newBorrowersAvailingLoans = newBorrowersAvailingLoans;
	}

	public Boolean getIsBorrowersAvailingLoans() {
		return isBorrowersAvailingLoans;
	}

	public void setIsBorrowersAvailingLoans(Boolean isBorrowersAvailingLoans) {
		this.isBorrowersAvailingLoans = isBorrowersAvailingLoans;
	}

	public Double getNewBorrowersHavingSalaryAccounts() {
		return newBorrowersHavingSalaryAccounts;
	}

	public void setNewBorrowersHavingSalaryAccounts(Double newBorrowersHavingSalaryAccounts) {
		this.newBorrowersHavingSalaryAccounts = newBorrowersHavingSalaryAccounts;
	}

	public Boolean getIsBorrowersHavingSalaryAccounts() {
		return isBorrowersHavingSalaryAccounts;
	}

	public void setIsBorrowersHavingSalaryAccounts(Boolean isBorrowersHavingSalaryAccounts) {
		this.isBorrowersHavingSalaryAccounts = isBorrowersHavingSalaryAccounts;
	}

	public Double getNewBorrowersAvailingCreaditCards() {
		return newBorrowersAvailingCreaditCards;
	}

	public void setNewBorrowersAvailingCreaditCards(Double newBorrowersAvailingCreaditCards) {
		this.newBorrowersAvailingCreaditCards = newBorrowersAvailingCreaditCards;
	}

	public Boolean getIsBorrowersAvailingCreaditCards() {
		return isBorrowersAvailingCreaditCards;
	}

	public void setIsBorrowersAvailingCreaditCards(Boolean isBorrowersAvailingCreaditCards) {
		this.isBorrowersAvailingCreaditCards = isBorrowersAvailingCreaditCards;
	}

	public Double getMinRoi() {
		return minRoi;
	}

	public void setMinRoi(Double minRoi) {
		this.minRoi = minRoi;
	}

	public Double getMinTenure() {
		return minTenure;
	}

	public void setMinTenure(Double minTenure) {
		this.minTenure = minTenure;
	}

	public Integer getFinancialTypeId() {
		return financialTypeId;
	}

	public void setFinancialTypeId(Integer financialTypeId) {
		this.financialTypeId = financialTypeId;
	}

	public Double getAvgROI() {
		return avgROI;
	}

	public void setAvgROI(Double avgROI) {
		this.avgROI = avgROI;
	}

	public Double getAvgTenure() {
		return avgTenure;
	}

	public void setAvgTenure(Double avgTenure) {
		this.avgTenure = avgTenure;
	}

	public Double getAvgPF() {
		return avgPF;
	}

	public void setAvgPF(Double avgPF) {
		this.avgPF = avgPF;
	}

	public Double getAvgFOIR() {
		return avgFOIR;
	}

	public void setAvgFOIR(Double avgFOIR) {
		this.avgFOIR = avgFOIR;
	}
	
	public Double getAvgLTV() {
		return avgLTV;
	}

	public void setAvgLTV(Double avgLTV) {
		this.avgLTV = avgLTV;
	}

	public Boolean getIsProportionateScoreConsider() {
		return isProportionateScoreConsider;
	}

	public void setIsProportionateScoreConsider(Boolean proportionateScoreConsider) {
		isProportionateScoreConsider = proportionateScoreConsider;
	}

	public List<Long> getScoringModelIdList() {
		return scoringModelIdList;
	}

	public void setScoringModelIdList(List<Long> scoringModelIdList) {
		this.scoringModelIdList = scoringModelIdList;
	}

	public Double getMinMargin() {
		return minMargin;
	}

	public void setMinMargin(Double minMargin) {
		this.minMargin = minMargin;
	}

	public Double getMaxMargin() {
		return maxMargin;
	}

	public void setMaxMargin(Double maxMargin) {
		this.maxMargin = maxMargin;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Double getManagementRiskWeight() {
		return managementRiskWeight;
	}

	public void setManagementRiskWeight(Double managementRiskWeight) {
		this.managementRiskWeight = managementRiskWeight;
	}

	public Double getFinancialRiskWeight() {
		return financialRiskWeight;
	}

	public void setFinancialRiskWeight(Double financialRiskWeight) {
		this.financialRiskWeight = financialRiskWeight;
	}

	public Double getBusinessRiskWeight() {
		return businessRiskWeight;
	}

	public void setBusinessRiskWeight(Double businessRiskWeight) {
		this.businessRiskWeight = businessRiskWeight;
	}

	public List<RiskGradingReqRes> getRiskGradingReqResList() {
		return riskGradingReqResList;
	}

	public void setRiskGradingReqResList(List<RiskGradingReqRes> riskGradingReqResList) {
		this.riskGradingReqResList = riskGradingReqResList;
	}

	public List<ScoringModelResponse> getScoringModelResponseList() {
		return scoringModelResponseList;
	}

	public void setScoringModelResponseList(List<ScoringModelResponse> scoringModelResponseList) {
		this.scoringModelResponseList = scoringModelResponseList;
	}


	public Double getProportionateScore() {
		return proportionateScore;
	}

	public void setProportionateScore(Double proportionateScore) {
		this.proportionateScore = proportionateScore;
	}

	public ScoringModelReqRes() {

	}

	public ScoringModelReqRes(String message, Integer status) {
		super();
		this.status = status;
		this.message = message;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ScoringModelResponse getScoringModelResponse() {
		return scoringModelResponse;
	}

	public void setScoringModelResponse(ScoringModelResponse scoringModelResponse) {
		this.scoringModelResponse = scoringModelResponse;
	}

	public Boolean getWeightConsider() {
		return isWeightConsider;
	}

	public void setWeightConsider(Boolean weightConsider) {
		isWeightConsider = weightConsider;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getScoringModelName() {
		return scoringModelName;
	}

	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}

	public Long getScoringModelId() {
		return scoringModelId;
	}

	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}

	public List<FieldMappingReqRes> getFieldMappingReqResList() {
		return fieldMappingReqResList;
	}

	public void setFieldMappingReqResList(List<FieldMappingReqRes> fieldMappingReqResList) {
		this.fieldMappingReqResList = fieldMappingReqResList;
	}

	public Object getWorkFlowData() {
		return workFlowData;
	}

	public void setWorkFlowData(Object workFlowData) {
		this.workFlowData = workFlowData;
	}

	public List<Long> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Long> rolesList) {
		this.rolesList = rolesList;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Integer getRequestType() {
		return requestType;
	}

	public void setRequestType(Integer requestType) {
		this.requestType = requestType;
	}

	public Boolean getIsInTemp() {
		return isInTemp;
	}

	public void setIsInTemp(Boolean isInTemp) {
		this.isInTemp = isInTemp;
	}
	
	

	public Boolean getIsWeightConsider() {
		return isWeightConsider;
	}

	public List<RiskGradingReqRes> getCibilGradingReqResList() {
		return cibilGradingReqResList;
	}

	public Integer getMarginScaling() {
		return marginScaling;
	}

	public Integer getRoiScaling() {
		return roiScaling;
	}

	public Integer getTenureScaling() {
		return tenureScaling;
	}

	public Integer getPfScaling() {
		return pfScaling;
	}

	public void setIsWeightConsider(Boolean isWeightConsider) {
		this.isWeightConsider = isWeightConsider;
	}

	public void setCibilGradingReqResList(List<RiskGradingReqRes> cibilGradingReqResList) {
		this.cibilGradingReqResList = cibilGradingReqResList;
	}

	public void setMarginScaling(Integer marginScaling) {
		this.marginScaling = marginScaling;
	}

	public void setRoiScaling(Integer roiScaling) {
		this.roiScaling = roiScaling;
	}

	public void setTenureScaling(Integer tenureScaling) {
		this.tenureScaling = tenureScaling;
	}

	public void setPfScaling(Integer pfScaling) {
		this.pfScaling = pfScaling;
	}


	public List<RiskGradingReqRes> getGrossIncomeGradingReqResList() {
		return grossIncomeGradingReqResList;
	}

	public void setGrossIncomeGradingReqResList(List<RiskGradingReqRes> grossIncomeGradingReqResList) {
		this.grossIncomeGradingReqResList = grossIncomeGradingReqResList;
	}

	public List<RiskGradingReqRes> getNetIncomeGradingReqResList() {
		return netIncomeGradingReqResList;
	}

	public void setNetIncomeGradingReqResList(List<RiskGradingReqRes> netIncomeGradingReqResList) {
		this.netIncomeGradingReqResList = netIncomeGradingReqResList;
	}
	
	public Boolean getIsGSTRegularHolder() {
		return isGSTRegularHolder;
	}

	public void setIsGSTRegularHolder(Boolean GSTRegularHolder) {
		isGSTRegularHolder = GSTRegularHolder;
	}

	public Boolean getIsGSTCompositeSchemeHolder() {
		return isGSTCompositeSchemeHolder;
	}

	public void setIsGSTCompositeSchemeHolder(Boolean GSTCompositeSchemeHolder) {
		isGSTCompositeSchemeHolder = GSTCompositeSchemeHolder;
	}

	public Boolean getIsGSTNotApplicable() {
		return isGSTNotApplicable;
	}

	public void setIsGSTNotApplicable(Boolean GSTNotApplicable) {
		isGSTNotApplicable = GSTNotApplicable;
	}
	

	public List<RiskGradingReqRes> getRiskCoAppGradingReqResList() {
		return riskCoAppGradingReqResList;
	}

	public void setRiskCoAppGradingReqResList(List<RiskGradingReqRes> riskCoAppGradingReqResList) {
		this.riskCoAppGradingReqResList = riskCoAppGradingReqResList;
	}

	public List<RiskGradingReqRes> getRiskLoanAmountGradingReqResList() {
		return riskLoanAmountGradingReqResList;
	}

	public void setRiskLoanAmountGradingReqResList(List<RiskGradingReqRes> riskLoanAmountGradingReqResList) {
		this.riskLoanAmountGradingReqResList = riskLoanAmountGradingReqResList;
	}

	public List<RiskGradingReqRes> getRiskTenureGradingReqResList() {
		return riskTenureGradingReqResList;
	}

	public void setRiskTenureGradingReqResList(List<RiskGradingReqRes> riskTenureGradingReqResList) {
		this.riskTenureGradingReqResList = riskTenureGradingReqResList;
	}

	public List<RiskGradingReqRes> getRiskPropertyValueGradingReqResList() {
		return riskPropertyValueGradingReqResList;
	}

	public void setRiskPropertyValueGradingReqResList(List<RiskGradingReqRes> riskPropertyValueGradingReqResList) {
		this.riskPropertyValueGradingReqResList = riskPropertyValueGradingReqResList;
	}
	
	public Integer getLtvScaling() {
		return ltvScaling;
	}

	public void setLtvScaling(Integer ltvScaling) {
		this.ltvScaling = ltvScaling;
	}

	public Double getTotalCoAppScore() {
		return totalCoAppScore;
	}

	public void setTotalCoAppScore(Double totalCoAppScore) {
		this.totalCoAppScore = totalCoAppScore;
	}

	public Long getLoanPurposeModelId() {
		return loanPurposeModelId;
	}

	public void setLoanPurposeModelId(Long loanPurposeModelId) {
		this.loanPurposeModelId = loanPurposeModelId;
	}


	public Boolean getIsWomenApplicant() {
		return isWomenApplicant;
	}

	public void setIsWomenApplicant(Boolean isWomenApplicant) {
		this.isWomenApplicant = isWomenApplicant;
	}

	public Double getNewWomenApplicant() {
		return newWomenApplicant;
	}

	public void setNewWomenApplicant(Double newWomenApplicant) {
		this.newWomenApplicant = newWomenApplicant;
	}

	public List<RiskGradingReqRes> getRiskLTVGradingReqResList() {
		return riskLTVGradingReqResList;
	}

	public void setRiskLTVGradingReqResList(List<RiskGradingReqRes> riskLTVGradingReqResList) {
		this.riskLTVGradingReqResList = riskLTVGradingReqResList;
	}

	public Long getScoringModelMasterId() {
		return scoringModelMasterId;
	}

	public void setScoringModelMasterId(Long scoringModelMasterId) {
		this.scoringModelMasterId = scoringModelMasterId;
	}

	public Boolean getIsConcessionCheckOff() {
		return isConcessionCheckOff;
	}

	public void setIsConcessionCheckOff(Boolean isConcessionCheckOff) {
		this.isConcessionCheckOff = isConcessionCheckOff;
	}
	

	public Long getCopyScoringModelId() {
		return copyScoringModelId;
	}

	public void setCopyScoringModelId(Long copyScoringModelId) {
		this.copyScoringModelId = copyScoringModelId;
	}

	
	public Integer getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(Integer employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}


	public List<RiskGradingReqRes> getRiskcreditHistoryGreaterListGradingReqResList() {
		return riskcreditHistoryGreaterListGradingReqResList;
	}

	public void setRiskcreditHistoryGreaterListGradingReqResList(List<RiskGradingReqRes> riskcreditHistoryGreaterListGradingReqResList) {
		this.riskcreditHistoryGreaterListGradingReqResList = riskcreditHistoryGreaterListGradingReqResList;
	}

	public List<RiskGradingReqRes> getRiskcreditHistoryLessListGradingReqResList() {
		return riskcreditHistoryLessListGradingReqResList;
	}

	public void setRiskcreditHistoryLessListGradingReqResList(List<RiskGradingReqRes> riskcreditHistoryLessListGradingReqResList) {
		this.riskcreditHistoryLessListGradingReqResList = riskcreditHistoryLessListGradingReqResList;
	}

	public List<RiskGradingReqRes> getRiskNocreditHistoryReqResList() {
		return riskNocreditHistoryReqResList;
	}

	public void setRiskNocreditHistoryReqResList(List<RiskGradingReqRes> riskNocreditHistoryReqResList) {
		this.riskNocreditHistoryReqResList = riskNocreditHistoryReqResList;
	}


	public Long getCopyEmploymentTypeId() {
		return copyEmploymentTypeId;
	}

	public void setCopyEmploymentTypeId(Long copyEmploymentTypeId) {
		this.copyEmploymentTypeId = copyEmploymentTypeId;
	}

	public List<RiskGradingReqRes> getRiskExShowRoomPriceReqResList() {
		return riskExShowRoomPriceReqResList;
	}

	public void setRiskExShowRoomPriceReqResList(List<RiskGradingReqRes> riskExShowRoomPriceReqResList) {
		this.riskExShowRoomPriceReqResList = riskExShowRoomPriceReqResList;
	}

	public List<RiskGradingReqRes> getRiskOnRoadPriceReqResList() {
		return riskOnRoadPriceReqResList;
	}

	public void setRiskOnRoadPriceReqResList(List<RiskGradingReqRes> riskOnRoadPriceReqResList) {
		this.riskOnRoadPriceReqResList = riskOnRoadPriceReqResList;
	}

	public List<RiskGradingReqRes> getRiskAgeVehicleReqResList() {
		return riskAgeVehicleReqResList;
	}

	public void setRiskAgeVehicleReqResList(List<RiskGradingReqRes> riskAgeVehicleReqResList) {
		this.riskAgeVehicleReqResList = riskAgeVehicleReqResList;
	}

	public List<RiskGradingReqRes> getRiskAgreedPurchaseValueIDVReqResList() {
		return riskAgreedPurchaseValueIDVReqResList;
	}

	public void setRiskAgreedPurchaseValueIDVReqResList(List<RiskGradingReqRes> riskAgreedPurchaseValueIDVReqResList) {
		this.riskAgreedPurchaseValueIDVReqResList = riskAgreedPurchaseValueIDVReqResList;
	}

	public List<RiskGradingReqRes> getRiskMclrReqResList() {
		return riskMclrReqResList;
	}

	public void setRiskMclrReqResList(List<RiskGradingReqRes> riskMclrReqResList) {
		this.riskMclrReqResList = riskMclrReqResList;
	}

	public List<RiskGradingReqRes> getRiskVehicleTypeReqResList() {
		return riskVehicleTypeReqResList;
	}

	public void setRiskVehicleTypeReqResList(List<RiskGradingReqRes> riskVehicleTypeReqResList) {
		this.riskVehicleTypeReqResList = riskVehicleTypeReqResList;
	}
	
	public Integer getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Integer vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(Double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	public Double getExShowroomPrice() {
		return exShowroomPrice;
	}

	public void setExShowroomPrice(Double exShowroomPrice) {
		this.exShowroomPrice = exShowroomPrice;
	}

	public Double getAgreedPurchasePriceAndIdv() {
		return agreedPurchasePriceAndIdv;
	}

	public void setAgreedPurchasePriceAndIdv(Double agreedPurchasePriceAndIdv) {
		this.agreedPurchasePriceAndIdv = agreedPurchasePriceAndIdv;
	}

	public List<RiskGradingReqRes> getRiskRoiReqResList() {
		return riskRoiReqResList;
	}

	public void setRiskRoiReqResList(List<RiskGradingReqRes> riskRoiReqResList) {
		this.riskRoiReqResList = riskRoiReqResList;
	}
	
	public Double getAgeOfVehicle() {
		return ageOfVehicle;
	}

	public void setAgeOfVehicle(Double ageOfVehicle) {
		this.ageOfVehicle = ageOfVehicle;
	}

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}
	
	public Integer getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}


	public Integer getScoringType() {
		return scoringType;
	}

	public void setScoringType(Integer scoringType) {
		this.scoringType = scoringType;
	}

	public List<RiskGradingReqRes> getRiskCibilTenureReqResList() {
		return riskCibilTenureReqResList;
	}

	public void setRiskCibilTenureReqResList(List<RiskGradingReqRes> riskCibilTenureReqResList) {
		this.riskCibilTenureReqResList = riskCibilTenureReqResList;
	}

	public Integer getCibilBureauVersionConcession() {
		return cibilBureauVersionConcession;
	}

	public void setCibilBureauVersionConcession(Integer cibilBureauVersionConcession) {
		this.cibilBureauVersionConcession = cibilBureauVersionConcession;
	}

	public Integer getCibilBureauGradVersion() {
		return cibilBureauGradVersion;
	}

	public void setCibilBureauGradVersion(Integer cibilBureauGradVersion) {
		this.cibilBureauGradVersion = cibilBureauGradVersion;
	}

	public Double getCibilScoreActualVersion2() {
		return cibilScoreActualVersion2;
	}

	public void setCibilScoreActualVersion2(Double cibilScoreActualVersion2) {
		this.cibilScoreActualVersion2 = cibilScoreActualVersion2;
	}
	
	

	public Object getWorkFlowDataForRemove() {
		return workFlowDataForRemove;
	}

	public void setWorkFlowDataForRemove(Object workFlowDataForRemove) {
		this.workFlowDataForRemove = workFlowDataForRemove;
	}

	public Long getRemoveJobId() {
		return removeJobId;
	}

	public void setRemoveJobId(Long removeJobId) {
		this.removeJobId = removeJobId;
	}

	public List<GenericCheckerReqRes> getWorkflowRemove() {
		return workflowRemove;
	}

	public void setWorkflowRemove(List<GenericCheckerReqRes> workflowRemove) {
		this.workflowRemove = workflowRemove;
	}

	public InactiveResponse getInactiveResponse() {
		return inactiveResponse;
	}

	public void setInactiveResponse(InactiveResponse inactiveResponse) {
		this.inactiveResponse = inactiveResponse;
	}

	public Boolean getIsAllowForRemove() {
		return isAllowForRemove;
	}

	public void setIsAllowForRemove(Boolean isAllowForRemove) {
		this.isAllowForRemove = isAllowForRemove;
	}

	public Integer getReqType() {
		return reqType;
	}

	public void setReqType(Integer reqType) {
		this.reqType = reqType;
	}

	public Long getScoringId() {
		return scoringId;
	}

	public void setScoringId(Long scoringId) {
		this.scoringId = scoringId;
	}

	@Override
	public String toString() {
		return "ScoringModelReqRes [id=" + id + ", status=" + status + ", message=" + message + ", userId=" + userId
				+ ", orgId=" + orgId + ", fpProductId=" + fpProductId + ", applicationId=" + applicationId
				+ ", scoringModelMasterId=" + scoringModelMasterId + ", scoringModelId=" + scoringModelId
				+ ", copyScoringModelId=" + copyScoringModelId + ", copyEmploymentTypeId=" + copyEmploymentTypeId
				+ ", scoringModelName=" + scoringModelName + ", businessTypeId=" + businessTypeId
				+ ", loanPurposeModelId=" + loanPurposeModelId + ", employmentTypeId=" + employmentTypeId
				+ ", financialTypeId=" + financialTypeId + ", totalScore=" + totalScore + ", totalCoAppScore="
				+ totalCoAppScore + ", managementRiskWeight=" + managementRiskWeight + ", financialRiskWeight="
				+ financialRiskWeight + ", businessRiskWeight=" + businessRiskWeight + ", isWeightConsider="
				+ isWeightConsider + ", isGSTRegularHolder=" + isGSTRegularHolder + ", isGSTCompositeSchemeHolder="
				+ isGSTCompositeSchemeHolder + ", isGSTNotApplicable=" + isGSTNotApplicable + ", proportionateScore="
				+ proportionateScore + ", isProportionateScoreConsider=" + isProportionateScoreConsider
				+ ", riskGradingReqResList=" + riskGradingReqResList + ", riskCoAppGradingReqResList="
				+ riskCoAppGradingReqResList + ", riskLoanAmountGradingReqResList=" + riskLoanAmountGradingReqResList
				+ ", riskTenureGradingReqResList=" + riskTenureGradingReqResList
				+ ", riskPropertyValueGradingReqResList=" + riskPropertyValueGradingReqResList
				+ ", riskLTVGradingReqResList=" + riskLTVGradingReqResList
				+ ", riskcreditHistoryGreaterListGradingReqResList=" + riskcreditHistoryGreaterListGradingReqResList
				+ ", riskcreditHistoryLessListGradingReqResList=" + riskcreditHistoryLessListGradingReqResList
				+ ", riskNocreditHistoryReqResList=" + riskNocreditHistoryReqResList + ", fieldMappingReqResList="
				+ fieldMappingReqResList + ", scoringModelResponse=" + scoringModelResponse
				+ ", scoringModelResponseList=" + scoringModelResponseList + ", workFlowData=" + workFlowData
				+ ", rolesList=" + rolesList + ", isEdit=" + isEdit + ", jobId=" + jobId + ", requestType="
				+ requestType + ", minMargin=" + minMargin + ", maxMargin=" + maxMargin + ", scoringModelIdList="
				+ scoringModelIdList + ", isInTemp=" + isInTemp + ", avgROI=" + avgROI + ", avgTenure=" + avgTenure
				+ ", avgPF=" + avgPF + ", avgFOIR=" + avgFOIR + ", avgLTV=" + avgLTV + ", minRoi=" + minRoi
				+ ", minTenure=" + minTenure + ", grossIncomeGradingReqResList=" + grossIncomeGradingReqResList
				+ ", netIncomeGradingReqResList=" + netIncomeGradingReqResList + ", cibilGradingReqResList="
				+ cibilGradingReqResList + ", marginScaling=" + marginScaling + ", roiScaling=" + roiScaling
				+ ", tenureScaling=" + tenureScaling + ", pfScaling=" + pfScaling + ", ltvScaling=" + ltvScaling
				+ ", cibilGreterThenConcessionScaling=" + cibilGreterThenConcessionScaling
				+ ", cibilLessThenConcessionScaling=" + cibilLessThenConcessionScaling
				+ ", cibilNoCreditConcessionScaling=" + cibilNoCreditConcessionScaling + ", isRateOfInterest="
				+ isRateOfInterest + ", newBorrowersHavingAccounts=" + newBorrowersHavingAccounts
				+ ", isBorrowersHavingAccounts=" + isBorrowersHavingAccounts + ", newBorrowersAvailingLoans="
				+ newBorrowersAvailingLoans + ", isBorrowersAvailingLoans=" + isBorrowersAvailingLoans
				+ ", newBorrowersHavingSalaryAccounts=" + newBorrowersHavingSalaryAccounts
				+ ", isBorrowersHavingSalaryAccounts=" + isBorrowersHavingSalaryAccounts
				+ ", newBorrowersAvailingCreaditCards=" + newBorrowersAvailingCreaditCards
				+ ", isBorrowersAvailingCreaditCards=" + isBorrowersAvailingCreaditCards + ", isConcessionCheckOff="
				+ isConcessionCheckOff + ", isPatiallyCheckOff=" + isPatiallyCheckOff + ", newPartiallyCheckOff="
				+ newPartiallyCheckOff + ", isFullyCheckOff=" + isFullyCheckOff + ", newFullyCheckOff="
				+ newFullyCheckOff + ", isWomenApplicant=" + isWomenApplicant + ", newWomenApplicant="
				+ newWomenApplicant + ", isConcessionBasedOnBureau=" + isConcessionBasedOnBureau
				+ ", isCreaditHisotryGreaterSixMonths=" + isCreaditHisotryGreaterSixMonths
				+ ", isCreaditHisotryLessThenSixMonths=" + isCreaditHisotryLessThenSixMonths + ", isNoCreaditHistory="
				+ isNoCreaditHistory + ", finalMaxFpConcessionRateOfInterest=" + finalMaxFpConcessionRateOfInterest
				+ ", cibilFpConcessionRoi=" + cibilFpConcessionRoi + ", mclrRoi=" + mclrRoi + ", cibilScoreActual="
				+ cibilScoreActual + ", grossAnualIncome=" + grossAnualIncome + ", netAnnualIncome=" + netAnnualIncome
				+ ", propertyValue=" + propertyValue + ", scoringModelBasedOn=" + scoringModelBasedOn + "]";
	}
}
