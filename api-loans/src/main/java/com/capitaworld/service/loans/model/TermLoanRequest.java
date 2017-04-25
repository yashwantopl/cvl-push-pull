package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * The persistent class for the fs_corporate_term_loan_details database table.
 * 
 */
public class TermLoanRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	private Integer accountingSystemsId;
	private Integer brandAmbassadorId;
	private Integer competenceId;
	private Long creditRatingId;
	private Integer currencyId;
	private Integer denominationId;
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
	private String projectBrief;
	private Long technologyPatentedId;
	private Integer technologyRequiresUpgradationId;
	private Integer technologyTypeId;
	private boolean whetherTechnologyIsTied;
	private String name;
	private String categoryCode;
	private Double amount;
	private Long productId;
	private int tenure;
	private Long userId;
	private Double totalCostOfEstimate;
	private Double totalMeansOfFinance;

	public TermLoanRequest() {
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

	public Long getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Long creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
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

	public String getProjectBrief() {
		return projectBrief;
	}

	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}

	public Long getTechnologyPatentedId() {
		return technologyPatentedId;
	}

	public void setTechnologyPatentedId(Long technologyPatentedId) {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getTotalCostOfEstimate() {
		return totalCostOfEstimate;
	}

	public void setTotalCostOfEstimate(Double totalCostOfEstimate) {
		this.totalCostOfEstimate = totalCostOfEstimate;
	}

	public Double getTotalMeansOfFinance() {
		return totalMeansOfFinance;
	}

	public void setTotalMeansOfFinance(Double totalMeansOfFinance) {
		this.totalMeansOfFinance = totalMeansOfFinance;
	}

}