package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the fp_working_capital_details database table.
 * 
 */
@Entity
@Table(name = "fp_uniform_product_details_audit")
public class UniformProductParamterAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "fp_product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "min_amount")
	private Double minAmount;
	
	@Column(name = "min_amount_audit")
	private String minAmountAudit;
	
	@Column(name = "max_amount")
	private Double maxAmount;
	
	@Column(name = "max_amount_audit")
	private String maxAmountAudit;
	
	private Double roi;
	
	@Column(name = "roi_audit")
	private String roiAudit;
	
	private Double pf;
	
	@Column(name = "pf_audit")
	private String pfAudit;
	
	@Column(name = "min_cibil_score")
	private Double minCibilScore;
	
	@Column(name = "min_cibil_score_audit")
	private String minCibilScoreAudit;
	
	
	@Column(name = "min_cmr")
	private Integer minCmr;
	
	@Column(name = "min_cmr_audit")
	private String minCmrAudit;
	
	@Column(name = "max_cmr")
	private Integer maxCmr;
	
	@Column(name = "max_cmr_audit")
	private String maxCmrAudit;
	
	private Integer currency;
	
	@Column(name = "currency_audit")
	private String currencyAudit;

	private Integer denomination;
	
	@Column(name = "denomination_audit")
	private String denominationAudit;
	
	@Column(name = "assessment_method")
	private Integer assessmentMethod;
	
	@Column(name = "assessment_method_audit")
	private String assessmentMethodAudit;
	
	@Column(name = "promotor_contribution")
	private Double promotorContribution;
	
	@Column(name = "promotor_contribution_audit")
	private String promotorContributionAudit;
	
	@Column(name = "wc_requirement")
	private Double wcRequirement;
	
	@Column(name = "wc_requirement_audit")
	private String wcRequirementAudit;
	
	@Column(name = "max_growth")
	private Double maxGrowth;
	
	@Column(name = "max_growth_audit")
	private String maxGrowthAudit;
	
	private Integer version;

	@Column(name = "version_audit")
	private String versionAudit;
	
	
	@Column(name = "fp_product_id")
	private Long fpProductId;

	
	@Column(name = "org_id")
	private Long orgId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	public UniformProductParamterAudit(){
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public String getMinAmountAudit() {
		return minAmountAudit;
	}

	public void setMinAmountAudit(String minAmountAudit) {
		this.minAmountAudit = minAmountAudit;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getMaxAmountAudit() {
		return maxAmountAudit;
	}

	public void setMaxAmountAudit(String maxAmountAudit) {
		this.maxAmountAudit = maxAmountAudit;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public String getRoiAudit() {
		return roiAudit;
	}

	public void setRoiAudit(String roiAudit) {
		this.roiAudit = roiAudit;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public String getPfAudit() {
		return pfAudit;
	}

	public void setPfAudit(String pfAudit) {
		this.pfAudit = pfAudit;
	}

	public Double getMinCibilScore() {
		return minCibilScore;
	}

	public void setMinCibilScore(Double minCibilScore) {
		this.minCibilScore = minCibilScore;
	}

	public String getMinCibilScoreAudit() {
		return minCibilScoreAudit;
	}

	public void setMinCibilScoreAudit(String minCibilScoreAudit) {
		this.minCibilScoreAudit = minCibilScoreAudit;
	}

	public Integer getMinCmr() {
		return minCmr;
	}

	public void setMinCmr(Integer minCmr) {
		this.minCmr = minCmr;
	}

	public String getMinCmrAudit() {
		return minCmrAudit;
	}

	public void setMinCmrAudit(String minCmrAudit) {
		this.minCmrAudit = minCmrAudit;
	}

	public Integer getMaxCmr() {
		return maxCmr;
	}

	public void setMaxCmr(Integer maxCmr) {
		this.maxCmr = maxCmr;
	}

	public String getMaxCmrAudit() {
		return maxCmrAudit;
	}

	public void setMaxCmrAudit(String maxCmrAudit) {
		this.maxCmrAudit = maxCmrAudit;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public String getCurrencyAudit() {
		return currencyAudit;
	}

	public void setCurrencyAudit(String currencyAudit) {
		this.currencyAudit = currencyAudit;
	}

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public String getDenominationAudit() {
		return denominationAudit;
	}

	public void setDenominationAudit(String denominationAudit) {
		this.denominationAudit = denominationAudit;
	}

	public Integer getAssessmentMethod() {
		return assessmentMethod;
	}

	public void setAssessmentMethod(Integer assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	public String getAssessmentMethodAudit() {
		return assessmentMethodAudit;
	}

	public void setAssessmentMethodAudit(String assessmentMethodAudit) {
		this.assessmentMethodAudit = assessmentMethodAudit;
	}

	public Double getPromotorContribution() {
		return promotorContribution;
	}

	public void setPromotorContribution(Double promotorContribution) {
		this.promotorContribution = promotorContribution;
	}

	public String getPromotorContributionAudit() {
		return promotorContributionAudit;
	}

	public void setPromotorContributionAudit(String promotorContributionAudit) {
		this.promotorContributionAudit = promotorContributionAudit;
	}

	public Double getWcRequirement() {
		return wcRequirement;
	}

	public void setWcRequirement(Double wcRequirement) {
		this.wcRequirement = wcRequirement;
	}

	public String getWcRequirementAudit() {
		return wcRequirementAudit;
	}

	public void setWcRequirementAudit(String wcRequirementAudit) {
		this.wcRequirementAudit = wcRequirementAudit;
	}

	public Double getMaxGrowth() {
		return maxGrowth;
	}

	public void setMaxGrowth(Double maxGrowth) {
		this.maxGrowth = maxGrowth;
	}

	public String getMaxGrowthAudit() {
		return maxGrowthAudit;
	}

	public void setMaxGrowthAudit(String maxGrowthAudit) {
		this.maxGrowthAudit = maxGrowthAudit;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getVersionAudit() {
		return versionAudit;
	}

	public void setVersionAudit(String versionAudit) {
		this.versionAudit = versionAudit;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "UniformProductParamterAudit [id=" + id + ", minAmount=" + minAmount + ", minAmountAudit="
				+ minAmountAudit + ", maxAmount=" + maxAmount + ", maxAmountAudit=" + maxAmountAudit + ", roi=" + roi
				+ ", roiAudit=" + roiAudit + ", pf=" + pf + ", pfAudit=" + pfAudit + ", minCibilScore=" + minCibilScore
				+ ", minCibilScoreAudit=" + minCibilScoreAudit + ", minCmr=" + minCmr + ", minCmrAudit=" + minCmrAudit
				+ ", maxCmr=" + maxCmr + ", maxCmrAudit=" + maxCmrAudit + ", currency=" + currency + ", currencyAudit="
				+ currencyAudit + ", denomination=" + denomination + ", denominationAudit=" + denominationAudit
				+ ", assessmentMethod=" + assessmentMethod + ", assessmentMethodAudit=" + assessmentMethodAudit
				+ ", promotorContribution=" + promotorContribution + ", promotorContributionAudit="
				+ promotorContributionAudit + ", wcRequirement=" + wcRequirement + ", wcRequirementAudit="
				+ wcRequirementAudit + ", maxGrowth=" + maxGrowth + ", maxGrowthAudit=" + maxGrowthAudit + ", version="
				+ version + ", versionAudit=" + versionAudit + ", fpProductId=" + fpProductId + ", orgId=" + orgId
				+ ", createdDate=" + createdDate + "]";
	}
}