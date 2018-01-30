package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * The persistent class for the fs_corporate_term_loan_details database table.
 * 
 */
@Entity
@Table(name = "fs_corporate_final_term_loan_details")
public class FinalTermLoanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name = "accounting_systems_id")
	private Integer accountingSystemsId;

	@Column(name = "brand_ambassador_id")
	private Integer brandAmbassadorId;

	@Column(name = "competence_id")
	private Integer competenceId;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	// @Column(name = "credit_rating_id")
	// private Long creditRatingId;

	// @Column(name = "currency_id")
	// private Integer currencyId;
	//
	// @Column(name = "denomination_id")
	// private Integer denominationId;

	@Column(name = "distribution_and_marketing_tie_ups_id")
	private Integer distributionAndMarketingTieUpsId;

	@Column(name = "environment_certification_id")
	private Integer environmentCertificationId;

	@Column(name = "existing_share_holders_id")
	private Integer existingShareHoldersId;

	@Column(name = "india_distribution_network_id")
	private Integer indiaDistributionNetworkId;

	@Column(name = "internal_audit_id")
	private Integer internalAuditId;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_depends_majorly_on_government")
	private Boolean isDependsMajorlyOnGovernment;

	@Column(name = "is_iso_certified")
	private Boolean isIsoCertified;

	@Column(name = "market_position_id")
	private Integer marketPositionId;

	@Column(name = "market_positioning_top_id")
	private Integer marketPositioningTopId;

	@Column(name = "market_share_turnover_id")
	private Integer marketShareTurnoverId;

	@Column(name = "marketing_positioning_id")
	private Integer marketingPositioningId;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "product_services_perse_id")
	private Integer productServicesPerseId;

	@Column(name = "technology_patented_id")
	private Integer technologyPatentedId;

	@Column(name = "technology_requires_upgradation_id")
	private Integer technologyRequiresUpgradationId;

	@Column(name = "technology_type_id")
	private Integer technologyTypeId;

	@Column(name = "is_technology_tied")
	private boolean whetherTechnologyIsTied;

	@Column(name = "technology_risk_id")
	private Integer technologyRiskId;
	

	@Column(name = "customer_quality")
	private Integer customerQuality;
	
	@Column(name = "supplier_quality")
	private Integer supplierQuality;
	
	@Column(name = "sustainability_product")
	private Integer sustainabilityProduct;
	
	@Column(name = "order_book_position")
	private Integer orderBookPosition;
	
	@Column(name = "employee_relations")
	private Integer employeeRelations;
	
	@Column(name = "product_seasonality")
	private Integer productSeasonality;
	
	@Column(name = "impact_on_operating_margins")
	private Integer impactOnOperatingMargins;
	
	@Column(name = "environmental_impact")
	private Integer environmentalImpact;
	
	@Column(name = "accounting_quality")
	private Integer accountingQuality;
	
	@Column(name = "financial_restructuring_history ")
	private Integer financialRestructuringHistory;
	
	@Column(name = "integrity")
	private Integer integrity;
	
	@Column(name = "business_commitment")
	private Integer businessCommitment;
	
	@Column(name = "management_competence")
	private Integer managementCompetence;
	
	@Column(name = "business_experience")
	private Integer businessExperience;
	
	@Column(name = "succession_	planning")
	private Integer successionPlanning;
	
	@Column(name = "financial_strength")
	private Integer financialStrength;
	
	@Column(name = "ability_to_raise_funds")
	private Integer abilityToRaiseFunds;
	
	@Column(name = "intra_company")
	private Integer IntraCompany;
	
	@Column(name = "internal_control")
	private Integer internalControl;
	
	@Column(name = "credittrack_record")
	private Integer creditTrackRecord;
	
	@Column(name = "status_of_project_clearances")
	private Integer statusOfProjectClearances;
	
	
	@Column(name = "status_of_financial_closure")
	private Integer statusOfFinancialClosure;
	
	@Column(name = "infrastructure_availability")
	private Integer infrastructureAvailability;
	
	@Column(name = "construction_contract")
	private Integer constructionContract;
	
	@Column(name = "number_of_cheques")
	private Integer numberOfCheques;
	
	@Column(name = "number_of_times_dp")
	private Integer numberOfTimes_Dp;
	
	@Column(name = "cumulative_no_of_days_dp")
	private Integer cumulativeNoOfDaysDp;
	
	@Column(name = "compliance_with_sanctioned")
	private Integer complianceWithSanctioned;
	
	@Column(name = "delay_in_receipt")
	private Integer delayInReceipt;
	
	@Column(name = "delay_in_submission")
	private Integer delayInSubmission;
	
	@Column(name = "number_of_lc")
	private Integer numberOfLc;


	public FinalTermLoanDetail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getAccountingSystemsId() {
		return this.accountingSystemsId;
	}

	public void setAccountingSystemsId(Integer accountingSystemsId) {
		this.accountingSystemsId = accountingSystemsId;
	}

	public Integer getBrandAmbassadorId() {
		return this.brandAmbassadorId;
	}

	public void setBrandAmbassadorId(Integer brandAmbassadorId) {
		this.brandAmbassadorId = brandAmbassadorId;
	}

	public Integer getCompetenceId() {
		return this.competenceId;
	}

	public void setCompetenceId(Integer competenceId) {
		this.competenceId = competenceId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDistributionAndMarketingTieUpsId() {
		return this.distributionAndMarketingTieUpsId;
	}

	public void setDistributionAndMarketingTieUpsId(Integer distributionAndMarketingTieUpsId) {
		this.distributionAndMarketingTieUpsId = distributionAndMarketingTieUpsId;
	}

	public Integer getEnvironmentCertificationId() {
		return this.environmentCertificationId;
	}

	public void setEnvironmentCertificationId(Integer environmentCertificationId) {
		this.environmentCertificationId = environmentCertificationId;
	}

	public Integer getExistingShareHoldersId() {
		return this.existingShareHoldersId;
	}

	public void setExistingShareHoldersId(Integer existingShareHoldersId) {
		this.existingShareHoldersId = existingShareHoldersId;
	}

	public Integer getIndiaDistributionNetworkId() {
		return this.indiaDistributionNetworkId;
	}

	public void setIndiaDistributionNetworkId(Integer indiaDistributionNetworkId) {
		this.indiaDistributionNetworkId = indiaDistributionNetworkId;
	}

	public Integer getInternalAuditId() {
		return this.internalAuditId;
	}

	public void setInternalAuditId(Integer internalAuditId) {
		this.internalAuditId = internalAuditId;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDependsMajorlyOnGovernment() {
		return this.isDependsMajorlyOnGovernment;
	}

	public void setIsDependsMajorlyOnGovernment(Boolean isDependsMajorlyOnGovernment) {
		this.isDependsMajorlyOnGovernment = isDependsMajorlyOnGovernment;
	}

	public Boolean getIsIsoCertified() {
		return this.isIsoCertified;
	}

	public void setIsIsoCertified(Boolean isIsoCertified) {
		this.isIsoCertified = isIsoCertified;
	}

	public Integer getMarketPositionId() {
		return this.marketPositionId;
	}

	public void setMarketPositionId(Integer marketPositionId) {
		this.marketPositionId = marketPositionId;
	}

	public Integer getMarketPositioningTopId() {
		return this.marketPositioningTopId;
	}

	public void setMarketPositioningTopId(Integer marketPositioningTopId) {
		this.marketPositioningTopId = marketPositioningTopId;
	}

	public Integer getMarketShareTurnoverId() {
		return this.marketShareTurnoverId;
	}

	public void setMarketShareTurnoverId(Integer marketShareTurnoverId) {
		this.marketShareTurnoverId = marketShareTurnoverId;
	}

	public Integer getMarketingPositioningId() {
		return this.marketingPositioningId;
	}

	public void setMarketingPositioningId(Integer marketingPositioningId) {
		this.marketingPositioningId = marketingPositioningId;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getProductServicesPerseId() {
		return this.productServicesPerseId;
	}

	public void setProductServicesPerseId(Integer productServicesPerseId) {
		this.productServicesPerseId = productServicesPerseId;
	}

	public Integer getTechnologyTypeId() {
		return technologyTypeId;
	}

	public void setTechnologyTypeId(Integer technologyTypeId) {
		this.technologyTypeId = technologyTypeId;
	}

	public Integer getTechnologyPatentedId() {
		return this.technologyPatentedId;
	}

	public void setTechnologyPatentedId(Integer technologyPatentedId) {
		this.technologyPatentedId = technologyPatentedId;
	}

	public Integer getTechnologyRequiresUpgradationId() {
		return this.technologyRequiresUpgradationId;
	}

	public void setTechnologyRequiresUpgradationId(Integer technologyRequiresUpgradationId) {
		this.technologyRequiresUpgradationId = technologyRequiresUpgradationId;
	}

	public boolean isWhetherTechnologyIsTied() {
		return whetherTechnologyIsTied;
	}

	public void setWhetherTechnologyIsTied(boolean whetherTechnologyIsTied) {
		this.whetherTechnologyIsTied = whetherTechnologyIsTied;
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
	
	
}