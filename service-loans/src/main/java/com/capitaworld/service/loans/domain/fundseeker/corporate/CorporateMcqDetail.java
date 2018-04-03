package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_corporate_final_mcq_details")
public class CorporateMcqDetail implements Serializable {
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

    @Column(name = "succession_planning")
    private Integer successionPlanning;

    @Column(name = "financial_support")
    private Integer financialSupport;

    @Column(name = "financial_strength")
    private Integer financialStrength;

    @Column(name = "ability_to_raise_funds")
    private Integer abilityToRaiseFunds;

    @Column(name = "intra_company")
    private Integer intraCompany;

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
    private Integer numberOfTimesDp;

    @Column(name = "cumulative_no_of_days_dp")
    private Integer cumulativeNoOfDaysDp;

    @Column(name = "compliance_with_sanctioned")
    private Integer complianceWithSanctioned;

    @Column(name = "progress_reports")
    private Integer progressReports;

    @Column(name = "delay_in_receipt")
    private Integer delayInReceipt;

    @Column(name = "delay_in_submission")
    private Integer delayInSubmission;

    @Column(name = "number_of_lc")
    private Integer numberOfLc;

    @Column(name = "unhedged_foreign_currency")
    private Integer unhedgedForeignCurrency;

    @Column(name = "projected_debt_service")
    private Integer projectedDebtService;

    @Column(name = "internal_rate_return")
    private Integer internalRateReturn;

    @Column(name = "sensititivity_analysis")
    private Integer sensititivityAnalysis;

    @Column(name = "variance_in_projected_sales")
    private Integer varianceInProjectedSales;


    public CorporateMcqDetail() {
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDependsMajorlyOnGovernment() {
        return isDependsMajorlyOnGovernment;
    }

    public void setDependsMajorlyOnGovernment(Boolean dependsMajorlyOnGovernment) {
        isDependsMajorlyOnGovernment = dependsMajorlyOnGovernment;
    }

    public Boolean getIsoCertified() {
        return isIsoCertified;
    }

    public void setIsoCertified(Boolean isoCertified) {
        isIsoCertified = isoCertified;
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

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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

    public Integer getFinancialSupport() {
        return financialSupport;
    }

    public void setFinancialSupport(Integer financialSupport) {
        this.financialSupport = financialSupport;
    }

    public Integer getAbilityToRaiseFunds() {
        return abilityToRaiseFunds;
    }

    public void setAbilityToRaiseFunds(Integer abilityToRaiseFunds) {
        this.abilityToRaiseFunds = abilityToRaiseFunds;
    }

    public Integer getIntraCompany() {
        return intraCompany;
    }

    public void setIntraCompany(Integer intraCompany) {
        this.intraCompany = intraCompany;
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

    public Integer getNumberOfTimesDp() {
        return numberOfTimesDp;
    }

    public void setNumberOfTimesDp(Integer numberOfTimesDp) {
        this.numberOfTimesDp = numberOfTimesDp;
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

    public Integer getProgressReports() {
        return progressReports;
    }

    public void setProgressReports(Integer progressReports) {
        this.progressReports = progressReports;
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
