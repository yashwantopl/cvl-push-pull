package com.opl.mudra.api.loans.model.v4;

import java.util.List;
import java.util.Map;

import com.opl.mudra.api.loans.model.score.ScoreParameterNTBRequest;
import com.opl.mudra.api.loans.model.score.ScoreParameterRetailRequest;
import com.opl.mudra.api.scoring.model.FundSeekerInputRequest;
import com.opl.mudra.api.scoring.model.ScoreParameterMFIRequest;
import com.opl.mudra.api.scoring.model.ScoringParameterRequest;

public class ScoringMsmeRequest {

	private List<FundSeekerInputRequest> dataList;
	
	private Long scoringModelId;
	
	private Long fsDigit;

	private Long fpProductId;

	private Long applicationId;
	
	private Long coAppId;
	
	private Long loanPurposeModelId;

	private Long directorId;

	private Integer financialTypeId;

	private Long userId;

	private Integer versionId;

	private Double eligibleLoanAmountCircular;
	
	private Map<String, Object> map = null;

	private Double eligibleTenure;

	private Double emi;

	private Double foir;

	private ScoringParameterRequest scoringParameterRequest;

	private ScoreParameterNTBRequest scoreParameterNTBRequest;

	private ScoreParameterRetailRequest scoreParameterRetailRequest;

	private ScoreParameterMFIRequest scoreParameterMFIRequest ;

	private Boolean isTestingApiCall=false;

	private Boolean isMatchesCall=false;

	Integer minBankRelationshipInMonths;

	private Integer accountType;
	
	
	//LOGIC FOR CONCESSION RATE OF INTEREST
    private Boolean isBorrowersHavingAccounts;
    private  Boolean isBorrowersAvailingLoans;
    private Boolean isBorrowersHavingSalaryAccounts;
    private Boolean isBorrowersAvailingCreaditCards;
    private Boolean isWomenApplicant;
    private Boolean isBorrowersAvailingSpecificLoans;

    // ENDS HERE

    // LOGIC FOR CHECK OFF RELATED ISSUE
    private Boolean isCheckOffDirectPayEmi;
    private Boolean isCheckOffAgreetoPayOutstanding;
    private Boolean isCheckOffShiftSalAcc;
    private Boolean isCheckOffPayOutstndAmount;
    private Boolean isCheckOffNotChangeSalAcc;
    // ENDS HERE CHECK OFF
    
	// CIBIL BASED CONCESSION RATE  OF INTEREST
	private Boolean isCreaditHisotryGreaterSixMonths;  
	private Boolean isCreaditHisotryLessThenSixMonths;
	private Boolean isNoCreaditHistory;
	private Double cibilActualScore;
	private Double cibilActualScoreVersion2;
	// ENDS HERE 						
	
	private List<String> loanTypeList;
	
	private String cmrScore;
	
	private Double assetCoverage;

	public List<FundSeekerInputRequest> getDataList() {
		return dataList;
	}

	public void setDataList(List<FundSeekerInputRequest> dataList) {
		this.dataList = dataList;
	}

	public Long getScoringModelId() {
		return scoringModelId;
	}

	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}

	public Long getFsDigit() {
		return fsDigit;
	}

	public void setFsDigit(Long fsDigit) {
		this.fsDigit = fsDigit;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCoAppId() {
		return coAppId;
	}

	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}

	public Long getLoanPurposeModelId() {
		return loanPurposeModelId;
	}

	public void setLoanPurposeModelId(Long loanPurposeModelId) {
		this.loanPurposeModelId = loanPurposeModelId;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public Integer getFinancialTypeId() {
		return financialTypeId;
	}

	public void setFinancialTypeId(Integer financialTypeId) {
		this.financialTypeId = financialTypeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Double getEligibleLoanAmountCircular() {
		return eligibleLoanAmountCircular;
	}

	public void setEligibleLoanAmountCircular(Double eligibleLoanAmountCircular) {
		this.eligibleLoanAmountCircular = eligibleLoanAmountCircular;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Double getEligibleTenure() {
		return eligibleTenure;
	}

	public void setEligibleTenure(Double eligibleTenure) {
		this.eligibleTenure = eligibleTenure;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public ScoringParameterRequest getScoringParameterRequest() {
		return scoringParameterRequest;
	}

	public void setScoringParameterRequest(ScoringParameterRequest scoringParameterRequest) {
		this.scoringParameterRequest = scoringParameterRequest;
	}

	public ScoreParameterNTBRequest getScoreParameterNTBRequest() {
		return scoreParameterNTBRequest;
	}

	public void setScoreParameterNTBRequest(ScoreParameterNTBRequest scoreParameterNTBRequest) {
		this.scoreParameterNTBRequest = scoreParameterNTBRequest;
	}

	public ScoreParameterRetailRequest getScoreParameterRetailRequest() {
		return scoreParameterRetailRequest;
	}

	public void setScoreParameterRetailRequest(ScoreParameterRetailRequest scoreParameterRetailRequest) {
		this.scoreParameterRetailRequest = scoreParameterRetailRequest;
	}

	public ScoreParameterMFIRequest getScoreParameterMFIRequest() {
		return scoreParameterMFIRequest;
	}

	public void setScoreParameterMFIRequest(ScoreParameterMFIRequest scoreParameterMFIRequest) {
		this.scoreParameterMFIRequest = scoreParameterMFIRequest;
	}

	public Boolean getIsTestingApiCall() {
		return isTestingApiCall;
	}

	public void setIsTestingApiCall(Boolean isTestingApiCall) {
		this.isTestingApiCall = isTestingApiCall;
	}

	public Boolean getIsMatchesCall() {
		return isMatchesCall;
	}

	public void setIsMatchesCall(Boolean isMatchesCall) {
		this.isMatchesCall = isMatchesCall;
	}

	public Integer getMinBankRelationshipInMonths() {
		return minBankRelationshipInMonths;
	}

	public void setMinBankRelationshipInMonths(Integer minBankRelationshipInMonths) {
		this.minBankRelationshipInMonths = minBankRelationshipInMonths;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Boolean getIsBorrowersHavingAccounts() {
		return isBorrowersHavingAccounts;
	}

	public void setIsBorrowersHavingAccounts(Boolean isBorrowersHavingAccounts) {
		this.isBorrowersHavingAccounts = isBorrowersHavingAccounts;
	}

	public Boolean getIsBorrowersAvailingLoans() {
		return isBorrowersAvailingLoans;
	}

	public void setIsBorrowersAvailingLoans(Boolean isBorrowersAvailingLoans) {
		this.isBorrowersAvailingLoans = isBorrowersAvailingLoans;
	}

	public Boolean getIsBorrowersHavingSalaryAccounts() {
		return isBorrowersHavingSalaryAccounts;
	}

	public void setIsBorrowersHavingSalaryAccounts(Boolean isBorrowersHavingSalaryAccounts) {
		this.isBorrowersHavingSalaryAccounts = isBorrowersHavingSalaryAccounts;
	}

	public Boolean getIsBorrowersAvailingCreaditCards() {
		return isBorrowersAvailingCreaditCards;
	}

	public void setIsBorrowersAvailingCreaditCards(Boolean isBorrowersAvailingCreaditCards) {
		this.isBorrowersAvailingCreaditCards = isBorrowersAvailingCreaditCards;
	}

	public Boolean getIsWomenApplicant() {
		return isWomenApplicant;
	}

	public void setIsWomenApplicant(Boolean isWomenApplicant) {
		this.isWomenApplicant = isWomenApplicant;
	}

	public Boolean getIsBorrowersAvailingSpecificLoans() {
		return isBorrowersAvailingSpecificLoans;
	}

	public void setIsBorrowersAvailingSpecificLoans(Boolean isBorrowersAvailingSpecificLoans) {
		this.isBorrowersAvailingSpecificLoans = isBorrowersAvailingSpecificLoans;
	}

	public Boolean getIsCheckOffDirectPayEmi() {
		return isCheckOffDirectPayEmi;
	}

	public void setIsCheckOffDirectPayEmi(Boolean isCheckOffDirectPayEmi) {
		this.isCheckOffDirectPayEmi = isCheckOffDirectPayEmi;
	}

	public Boolean getIsCheckOffAgreetoPayOutstanding() {
		return isCheckOffAgreetoPayOutstanding;
	}

	public void setIsCheckOffAgreetoPayOutstanding(Boolean isCheckOffAgreetoPayOutstanding) {
		this.isCheckOffAgreetoPayOutstanding = isCheckOffAgreetoPayOutstanding;
	}

	public Boolean getIsCheckOffShiftSalAcc() {
		return isCheckOffShiftSalAcc;
	}

	public void setIsCheckOffShiftSalAcc(Boolean isCheckOffShiftSalAcc) {
		this.isCheckOffShiftSalAcc = isCheckOffShiftSalAcc;
	}

	public Boolean getIsCheckOffPayOutstndAmount() {
		return isCheckOffPayOutstndAmount;
	}

	public void setIsCheckOffPayOutstndAmount(Boolean isCheckOffPayOutstndAmount) {
		this.isCheckOffPayOutstndAmount = isCheckOffPayOutstndAmount;
	}

	public Boolean getIsCheckOffNotChangeSalAcc() {
		return isCheckOffNotChangeSalAcc;
	}

	public void setIsCheckOffNotChangeSalAcc(Boolean isCheckOffNotChangeSalAcc) {
		this.isCheckOffNotChangeSalAcc = isCheckOffNotChangeSalAcc;
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

	public Double getCibilActualScore() {
		return cibilActualScore;
	}

	public void setCibilActualScore(Double cibilActualScore) {
		this.cibilActualScore = cibilActualScore;
	}

	public Double getCibilActualScoreVersion2() {
		return cibilActualScoreVersion2;
	}

	public void setCibilActualScoreVersion2(Double cibilActualScoreVersion2) {
		this.cibilActualScoreVersion2 = cibilActualScoreVersion2;
	}

	public List<String> getLoanTypeList() {
		return loanTypeList;
	}

	public void setLoanTypeList(List<String> loanTypeList) {
		this.loanTypeList = loanTypeList;
	}

	public String getCmrScore() {
		return cmrScore;
	}

	public void setCmrScore(String cmrScore) {
		this.cmrScore = cmrScore;
	}

	public Double getAssetCoverage() {
		return assetCoverage;
	}

	public void setAssetCoverage(Double assetCoverage) {
		this.assetCoverage = assetCoverage;
	}
	
	
}
