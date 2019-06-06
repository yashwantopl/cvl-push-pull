package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HLOneformPrimaryRes implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	// RETAIL APPLICANT DETAILS FIELDS
	private Long applicationId;
	private Long userId;
	private Double loanAmountRequired;
	private Integer loanPurpose;
	private Integer loanPurposeQueType;
	private String loanPurposeQueValue;
	private String loanPurposeOther;
	private Integer tenureRequired;
	private Integer repayment;
	
	// RETAIL PRIMARY DETAILS FIELDS
	private String propPremiseName;
	private String propStreetName;
	private String propLandmark;
	private Long propCountry;
	private Long propState;
	private Long propCity;
	private Long propPincode;
	private Long propdistrictMappingId;	
	
	private Double marketValProp;
	private Integer oldPropMonth;
	private Integer oldPropYear;
	
	private  Map<Long, String> coAppFullNameAndCoAppId;	
	//Existing Loan Details
	List<FinancialArrangementsDetailRequest> finArrangementsDetailList;
	
	//BANKING RELATIONSHIP
	List<BankRelationshipRequest> bankRelationshipList;
	
	private Integer salaryMode;
    private String salaryBankName;
    private Integer salaryBankMonth;
    private Integer salaryBankYear;
    private Boolean isOtherSalaryBank;
    private Boolean isOneformPrimaryComplete;
	private String applicantName;
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Double getLoanAmountRequired() {
		return loanAmountRequired;
	}
	public void setLoanAmountRequired(Double loanAmountRequired) {
		this.loanAmountRequired = loanAmountRequired;
	}
	public Integer getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}
	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}
	public Integer getTenureRequired() {
		return tenureRequired;
	}
	public void setTenureRequired(Integer tenureRequired) {
		this.tenureRequired = tenureRequired;
	}
	public Integer getRepayment() {
		return repayment;
	}
	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}
	public String getPropPremiseName() {
		return propPremiseName;
	}
	public void setPropPremiseName(String propPremiseName) {
		this.propPremiseName = propPremiseName;
	}
	public String getPropStreetName() {
		return propStreetName;
	}
	public void setPropStreetName(String propStreetName) {
		this.propStreetName = propStreetName;
	}
	public String getPropLandmark() {
		return propLandmark;
	}
	public void setPropLandmark(String propLandmark) {
		this.propLandmark = propLandmark;
	}
	public Long getPropCountry() {
		return propCountry;
	}
	public void setPropCountry(Long propCountry) {
		this.propCountry = propCountry;
	}
	public Long getPropState() {
		return propState;
	}
	public void setPropState(Long propState) {
		this.propState = propState;
	}
	public Long getPropCity() {
		return propCity;
	}
	public void setPropCity(Long propCity) {
		this.propCity = propCity;
	}
	public Long getPropPincode() {
		return propPincode;
	}
	public void setPropPincode(Long propPincode) {
		this.propPincode = propPincode;
	}
	public Long getPropdistrictMappingId() {
		return propdistrictMappingId;
	}
	public void setPropdistrictMappingId(Long propdistrictMappingId) {
		this.propdistrictMappingId = propdistrictMappingId;
	}
	public Double getMarketValProp() {
		return marketValProp;
	}
	public void setMarketValProp(Double marketValProp) {
		this.marketValProp = marketValProp;
	}
	public Integer getOldPropMonth() {
		return oldPropMonth;
	}
	public void setOldPropMonth(Integer oldPropMonth) {
		this.oldPropMonth = oldPropMonth;
	}
	public Integer getOldPropYear() {
		return oldPropYear;
	}
	public void setOldPropYear(Integer oldPropYear) {
		this.oldPropYear = oldPropYear;
	}
	public List<FinancialArrangementsDetailRequest> getFinArrangementsDetailList() {
		return finArrangementsDetailList;
	}
	public void setFinArrangementsDetailList(List<FinancialArrangementsDetailRequest> finArrangementsDetailList) {
		this.finArrangementsDetailList = finArrangementsDetailList;
	}
	public List<BankRelationshipRequest> getBankRelationshipList() {
		return bankRelationshipList;
	}
	public void setBankRelationshipList(List<BankRelationshipRequest> bankRelationshipList) {
		this.bankRelationshipList = bankRelationshipList;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getSalaryMode() {
		return salaryMode;
	}
	public void setSalaryMode(Integer salaryMode) {
		this.salaryMode = salaryMode;
	}
	public String getSalaryBankName() {
		return salaryBankName;
	}
	public void setSalaryBankName(String salaryBankName) {
		this.salaryBankName = salaryBankName;
	}
	public Integer getSalaryBankMonth() {
		return salaryBankMonth;
	}
	public void setSalaryBankMonth(Integer salaryBankMonth) {
		this.salaryBankMonth = salaryBankMonth;
	}
	public Integer getSalaryBankYear() {
		return salaryBankYear;
	}
	public void setSalaryBankYear(Integer salaryBankYear) {
		this.salaryBankYear = salaryBankYear;
	}
	public Boolean getIsOtherSalaryBank() {
		return isOtherSalaryBank;
	}
	public void setIsOtherSalaryBank(Boolean isOtherSalaryBank) {
		this.isOtherSalaryBank = isOtherSalaryBank;
	}
	public Boolean getIsOneformPrimaryComplete() {
		return isOneformPrimaryComplete;
	}
	public void setIsOneformPrimaryComplete(Boolean isOneformPrimaryComplete) {
		this.isOneformPrimaryComplete = isOneformPrimaryComplete;
	}
	public Integer getLoanPurposeQueType() {
		return loanPurposeQueType;
	}
	public void setLoanPurposeQueType(Integer loanPurposeQueType) {
		this.loanPurposeQueType = loanPurposeQueType;
	}
	public String getLoanPurposeQueValue() {
		return loanPurposeQueValue;
	}
	public void setLoanPurposeQueValue(String loanPurposeQueValue) {
		this.loanPurposeQueValue = loanPurposeQueValue;
	}
	public Map<Long, String> getCoAppFullNameAndCoAppId() {
		return coAppFullNameAndCoAppId;
	}
	public void setCoAppFullNameAndCoAppId(Map<Long, String> coAppFullNameAndCoAppId) {
		this.coAppFullNameAndCoAppId = coAppFullNameAndCoAppId;
	}
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	
	
	
	
}
