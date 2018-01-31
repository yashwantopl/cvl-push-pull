package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalWorkingCapitalLoanRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long clientId;
	private Long applicationId;
	private Integer accountingSystemsId;
	private Integer brandAmbassadorId;
	private Integer competenceId;
	private Integer distributionAndMarketingTieUpsId;
	private Integer environmentCertificationId;
	private Integer existingShareHoldersId;
	private Integer indiaDistributionNetworkId;
	private Integer internalAuditId;
	private Boolean isDependsMajorlyOnGovernment;
	private Boolean isIsoCertified;
	private Integer marketPositionId;
	private Integer marketPositioningTopId;
	private Integer marketShareTurnoverId;
	private Integer marketingPositioningId;
	private Integer productServicesPerseId;
	private Integer technologyPatentedId;
	private Integer technologyRequiresUpgradationId;
	private Integer technologyTypeId;
	private Boolean whetherTechnologyIsTied;
	private String finalFilledCount;
	//new fields for nhbs
	private Integer technologyRiskId;
	private Integer customerQuality;
	private Integer supplierQuality;
	private Integer sustainabilityProduct;
	private Integer orderBookPosition;
	private Integer employeeRelations;
	private Integer productSeasonality;
	private Integer impactOnOperatingMargins;
	private Integer environmentalImpact;
	private Integer accountingQuality;
	private Integer financialRestructuringHistory;
	private Integer integrity;
	private Integer businessCommitment;
	private Integer managementCompetence;
	private Integer businessExperience;
	private Integer successionPlanning;
	private Integer financialStrength;
	private Integer abilityToRaiseFunds;
	private Integer IntraCompany;
	private Integer internalControl;
	private Integer creditTrackRecord;
	private Integer statusOfProjectClearances;
	private Integer statusOfFinancialClosure;
	private Integer infrastructureAvailability;
	private Integer constructionContract;
	private Integer numberOfCheques;
	private Integer numberOfTimes_Dp;
	private Integer cumulativeNoOfDaysDp;
	private Integer complianceWithSanctioned;
	private Integer delayInReceipt;
	private Integer delayInSubmission;
	private Integer numberOfLc;
	
	private Integer unhedgedForeignCurrency;
	private Integer projectedDebtService;
	private Integer internalRateReturn;
	private Integer sensititivityAnalysis;
	private Integer varianceInProjectedSales;
	private Long userId;
	private List<Integer> overseasNetworkIds = Collections.emptyList();
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	private Boolean isFinalMcqFilled;

	public FinalWorkingCapitalLoanRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getAccountingSystemsId() {
		return accountingSystemsId;
	}

	public void setAccountingSystemsId(Integer accountingSystemsId) {
		this.accountingSystemsId = accountingSystemsId;
	}

	public Integer getBrandAmbassadorId() {
		return brandAmbassadorId;
	}

	public void setBrandAmbassadorId(Integer brandAmbassadorId) {
		this.brandAmbassadorId = brandAmbassadorId;
	}

	public Integer getCompetenceId() {
		return competenceId;
	}

	public void setCompetenceId(Integer competenceId) {
		this.competenceId = competenceId;
	}

	public Integer getDistributionAndMarketingTieUpsId() {
		return distributionAndMarketingTieUpsId;
	}

	public void setDistributionAndMarketingTieUpsId(Integer distributionAndMarketingTieUpsId) {
		this.distributionAndMarketingTieUpsId = distributionAndMarketingTieUpsId;
	}

	public Integer getEnvironmentCertificationId() {
		return environmentCertificationId;
	}

	public void setEnvironmentCertificationId(Integer environmentCertificationId) {
		this.environmentCertificationId = environmentCertificationId;
	}

	public Integer getExistingShareHoldersId() {
		return existingShareHoldersId;
	}

	public void setExistingShareHoldersId(Integer existingShareHoldersId) {
		this.existingShareHoldersId = existingShareHoldersId;
	}

	public Integer getIndiaDistributionNetworkId() {
		return indiaDistributionNetworkId;
	}

	public void setIndiaDistributionNetworkId(Integer indiaDistributionNetworkId) {
		this.indiaDistributionNetworkId = indiaDistributionNetworkId;
	}

	public Integer getInternalAuditId() {
		return internalAuditId;
	}

	public void setInternalAuditId(Integer internalAuditId) {
		this.internalAuditId = internalAuditId;
	}

	public Boolean getIsDependsMajorlyOnGovernment() {
		return isDependsMajorlyOnGovernment;
	}

	public void setIsDependsMajorlyOnGovernment(Boolean isDependsMajorlyOnGovernment) {
		this.isDependsMajorlyOnGovernment = isDependsMajorlyOnGovernment;
	}

	public Boolean getIsIsoCertified() {
		return isIsoCertified;
	}

	public void setIsIsoCertified(Boolean isIsoCertified) {
		this.isIsoCertified = isIsoCertified;
	}

	public Integer getMarketPositionId() {
		return marketPositionId;
	}

	public void setMarketPositionId(Integer marketPositionId) {
		this.marketPositionId = marketPositionId;
	}

	public Integer getMarketPositioningTopId() {
		return marketPositioningTopId;
	}

	public void setMarketPositioningTopId(Integer marketPositioningTopId) {
		this.marketPositioningTopId = marketPositioningTopId;
	}

	public Integer getMarketShareTurnoverId() {
		return marketShareTurnoverId;
	}

	public void setMarketShareTurnoverId(Integer marketShareTurnoverId) {
		this.marketShareTurnoverId = marketShareTurnoverId;
	}

	public Integer getMarketingPositioningId() {
		return marketingPositioningId;
	}

	public void setMarketingPositioningId(Integer marketingPositioningId) {
		this.marketingPositioningId = marketingPositioningId;
	}

	public Integer getProductServicesPerseId() {
		return productServicesPerseId;
	}

	public void setProductServicesPerseId(Integer productServicesPerseId) {
		this.productServicesPerseId = productServicesPerseId;
	}

	public Integer getTechnologyPatentedId() {
		return technologyPatentedId;
	}

	public void setTechnologyPatentedId(Integer technologyPatentedId) {
		this.technologyPatentedId = technologyPatentedId;
	}

	public Integer getTechnologyRequiresUpgradationId() {
		return technologyRequiresUpgradationId;
	}

	public void setTechnologyRequiresUpgradationId(Integer technologyRequiresUpgradationId) {
		this.technologyRequiresUpgradationId = technologyRequiresUpgradationId;
	}

	public Integer getTechnologyTypeId() {
		return technologyTypeId;
	}

	public void setTechnologyTypeId(Integer technologyTypeId) {
		this.technologyTypeId = technologyTypeId;
	}

	public Boolean getWhetherTechnologyIsTied() {
		return whetherTechnologyIsTied;
	}

	public void setWhetherTechnologyIsTied(Boolean whetherTechnologyIsTied) {
		this.whetherTechnologyIsTied = whetherTechnologyIsTied;
	}

	public List<Integer> getOverseasNetworkIds() {
		return overseasNetworkIds;
	}

	public void setOverseasNetworkIds(List<Integer> overseasNetworkIds) {
		this.overseasNetworkIds = overseasNetworkIds;
	}

	public Boolean getIsFinalMcqFilled() {
		return isFinalMcqFilled;
	}

	public void setIsFinalMcqFilled(Boolean isFinalMcqFilled) {
		this.isFinalMcqFilled = isFinalMcqFilled;
	}

	public String getFinalFilledCount() {
		return finalFilledCount;
	}

	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}

	public Integer getTechnologyRiskId() {
		return technologyRiskId;
	}

	public void setTechnologyRiskId(Integer technologyRiskId) {
		this.technologyRiskId = technologyRiskId;
	}

	public Integer getCustomerQuality() {
		return customerQuality;
	}

	public void setCustomerQuality(Integer customerQuality) {
		this.customerQuality = customerQuality;
	}

	public Integer getSupplierQuality() {
		return supplierQuality;
	}

	public void setSupplierQuality(Integer supplierQuality) {
		this.supplierQuality = supplierQuality;
	}

	public Integer getSustainabilityProduct() {
		return sustainabilityProduct;
	}

	public void setSustainabilityProduct(Integer sustainabilityProduct) {
		this.sustainabilityProduct = sustainabilityProduct;
	}

	public Integer getOrderBookPosition() {
		return orderBookPosition;
	}

	public void setOrderBookPosition(Integer orderBookPosition) {
		this.orderBookPosition = orderBookPosition;
	}

	public Integer getEmployeeRelations() {
		return employeeRelations;
	}

	public void setEmployeeRelations(Integer employeeRelations) {
		this.employeeRelations = employeeRelations;
	}

	public Integer getProductSeasonality() {
		return productSeasonality;
	}

	public void setProductSeasonality(Integer productSeasonality) {
		this.productSeasonality = productSeasonality;
	}

	public Integer getImpactOnOperatingMargins() {
		return impactOnOperatingMargins;
	}

	public void setImpactOnOperatingMargins(Integer impactOnOperatingMargins) {
		this.impactOnOperatingMargins = impactOnOperatingMargins;
	}

	public Integer getEnvironmentalImpact() {
		return environmentalImpact;
	}

	public void setEnvironmentalImpact(Integer environmentalImpact) {
		this.environmentalImpact = environmentalImpact;
	}

	public Integer getAccountingQuality() {
		return accountingQuality;
	}

	public void setAccountingQuality(Integer accountingQuality) {
		this.accountingQuality = accountingQuality;
	}

	public Integer getFinancialRestructuringHistory() {
		return financialRestructuringHistory;
	}

	public void setFinancialRestructuringHistory(Integer financialRestructuringHistory) {
		this.financialRestructuringHistory = financialRestructuringHistory;
	}

	public Integer getIntegrity() {
		return integrity;
	}

	public void setIntegrity(Integer integrity) {
		this.integrity = integrity;
	}

	public Integer getBusinessCommitment() {
		return businessCommitment;
	}

	public void setBusinessCommitment(Integer businessCommitment) {
		this.businessCommitment = businessCommitment;
	}

	public Integer getManagementCompetence() {
		return managementCompetence;
	}

	public void setManagementCompetence(Integer managementCompetence) {
		this.managementCompetence = managementCompetence;
	}

	public Integer getBusinessExperience() {
		return businessExperience;
	}

	public void setBusinessExperience(Integer businessExperience) {
		this.businessExperience = businessExperience;
	}

	public Integer getSuccessionPlanning() {
		return successionPlanning;
	}

	public void setSuccessionPlanning(Integer successionPlanning) {
		this.successionPlanning = successionPlanning;
	}

	public Integer getFinancialStrength() {
		return financialStrength;
	}

	public void setFinancialStrength(Integer financialStrength) {
		this.financialStrength = financialStrength;
	}

	public Integer getAbilityToRaiseFunds() {
		return abilityToRaiseFunds;
	}

	public void setAbilityToRaiseFunds(Integer abilityToRaiseFunds) {
		this.abilityToRaiseFunds = abilityToRaiseFunds;
	}

	public Integer getIntraCompany() {
		return IntraCompany;
	}

	public void setIntraCompany(Integer intraCompany) {
		IntraCompany = intraCompany;
	}

	public Integer getInternalControl() {
		return internalControl;
	}

	public void setInternalControl(Integer internalControl) {
		this.internalControl = internalControl;
	}

	public Integer getCreditTrackRecord() {
		return creditTrackRecord;
	}

	public void setCreditTrackRecord(Integer creditTrackRecord) {
		this.creditTrackRecord = creditTrackRecord;
	}

	public Integer getStatusOfProjectClearances() {
		return statusOfProjectClearances;
	}

	public void setStatusOfProjectClearances(Integer statusOfProjectClearances) {
		this.statusOfProjectClearances = statusOfProjectClearances;
	}

	public Integer getStatusOfFinancialClosure() {
		return statusOfFinancialClosure;
	}

	public void setStatusOfFinancialClosure(Integer statusOfFinancialClosure) {
		this.statusOfFinancialClosure = statusOfFinancialClosure;
	}

	public Integer getInfrastructureAvailability() {
		return infrastructureAvailability;
	}

	public void setInfrastructureAvailability(Integer infrastructureAvailability) {
		this.infrastructureAvailability = infrastructureAvailability;
	}

	public Integer getConstructionContract() {
		return constructionContract;
	}

	public void setConstructionContract(Integer constructionContract) {
		this.constructionContract = constructionContract;
	}

	public Integer getNumberOfCheques() {
		return numberOfCheques;
	}

	public void setNumberOfCheques(Integer numberOfCheques) {
		this.numberOfCheques = numberOfCheques;
	}

	public Integer getNumberOfTimes_Dp() {
		return numberOfTimes_Dp;
	}

	public void setNumberOfTimes_Dp(Integer numberOfTimes_Dp) {
		this.numberOfTimes_Dp = numberOfTimes_Dp;
	}

	public Integer getCumulativeNoOfDaysDp() {
		return cumulativeNoOfDaysDp;
	}

	public void setCumulativeNoOfDaysDp(Integer cumulativeNoOfDaysDp) {
		this.cumulativeNoOfDaysDp = cumulativeNoOfDaysDp;
	}

	public Integer getComplianceWithSanctioned() {
		return complianceWithSanctioned;
	}

	public void setComplianceWithSanctioned(Integer complianceWithSanctioned) {
		this.complianceWithSanctioned = complianceWithSanctioned;
	}

	public Integer getDelayInReceipt() {
		return delayInReceipt;
	}

	public void setDelayInReceipt(Integer delayInReceipt) {
		this.delayInReceipt = delayInReceipt;
	}

	public Integer getDelayInSubmission() {
		return delayInSubmission;
	}

	public void setDelayInSubmission(Integer delayInSubmission) {
		this.delayInSubmission = delayInSubmission;
	}

	public Integer getNumberOfLc() {
		return numberOfLc;
	}

	public void setNumberOfLc(Integer numberOfLc) {
		this.numberOfLc = numberOfLc;
	}

	public Integer getUnhedgedForeignCurrency() {
		return unhedgedForeignCurrency;
	}

	public void setUnhedgedForeignCurrency(Integer unhedgedForeignCurrency) {
		this.unhedgedForeignCurrency = unhedgedForeignCurrency;
	}

	public Integer getProjectedDebtService() {
		return projectedDebtService;
	}

	public void setProjectedDebtService(Integer projectedDebtService) {
		this.projectedDebtService = projectedDebtService;
	}

	public Integer getInternalRateReturn() {
		return internalRateReturn;
	}

	public void setInternalRateReturn(Integer internalRateReturn) {
		this.internalRateReturn = internalRateReturn;
	}

	public Integer getSensititivityAnalysis() {
		return sensititivityAnalysis;
	}

	public void setSensititivityAnalysis(Integer sensititivityAnalysis) {
		this.sensititivityAnalysis = sensititivityAnalysis;
	}

	public Integer getVarianceInProjectedSales() {
		return varianceInProjectedSales;
	}

	public void setVarianceInProjectedSales(Integer varianceInProjectedSales) {
		this.varianceInProjectedSales = varianceInProjectedSales;
	}

	
	
	
	

}