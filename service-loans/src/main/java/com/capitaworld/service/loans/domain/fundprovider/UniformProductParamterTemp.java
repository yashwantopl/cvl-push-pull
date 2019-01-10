package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The persistent class for the fp_working_capital_details database table.
 * 
 */
@Entity
@Table(name = "fp_uniform_product_details_temp")
@PrimaryKeyJoinColumn(referencedColumnName = "fp_product_id")
public class UniformProductParamterTemp extends ProductMasterTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "min_amount")
	private Double minAmount;
	@Column(name = "max_amount")
	private Double maxAmount;
	private Double roi;
	private Double pf;
	@Column(name = "min_cibil_score")
	private Double minCibilScore;
	@Column(name = "min_cmr")
	private Integer minCmr;
	
	@Column(name = "max_cmr")
	private Integer maxCmr;

	private Integer currency;
	
	private Integer dpd;

	private Integer denomination;

	@Column(name = "assessment_method")
	private Integer assessmentMethod;

	@Column(name = "promotor_contribution")
	private Double promotorContribution;

	@Column(name = "wc_requirement")
	private Double wcRequirement;

	@Column(name = "max_growth")
	private Double maxGrowth;
	
	@Column(name = "is_edited")
	private Boolean isEdited;

	public UniformProductParamterTemp() {
		super();
	}

	public UniformProductParamterTemp(Long id) {
		super();
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Double getMinCibilScore() {
		return minCibilScore;
	}

	public void setMinCibilScore(Double minCibilScore) {
		this.minCibilScore = minCibilScore;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Integer getAssessmentMethod() {
		return assessmentMethod;
	}

	public void setAssessmentMethod(Integer assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	public Double getPromotorContribution() {
		return promotorContribution;
	}

	public void setPromotorContribution(Double promotorContribution) {
		this.promotorContribution = promotorContribution;
	}

	public Double getWcRequirement() {
		return wcRequirement;
	}

	public void setWcRequirement(Double wcRequirement) {
		this.wcRequirement = wcRequirement;
	}

	public Double getMaxGrowth() {
		return maxGrowth;
	}

	public void setMaxGrowth(Double maxGrowth) {
		this.maxGrowth = maxGrowth;
	}

	public Integer getMinCmr() {
		return minCmr;
	}

	public void setMinCmr(Integer minCmr) {
		this.minCmr = minCmr;
	}

	public Integer getMaxCmr() {
		return maxCmr;
	}

	public void setMaxCmr(Integer maxCmr) {
		this.maxCmr = maxCmr;
	}

	public Integer getDpd() {
		return dpd;
	}

	public void setDpd(Integer dpd) {
		this.dpd = dpd;
	}

	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}

	@Override
	public String toString() {
		return "UniformProductParamterTemp [minAmount=" + minAmount + ", maxAmount=" + maxAmount + ", roi=" + roi
				+ ", pf=" + pf + ", minCibilScore=" + minCibilScore + ", minCmr=" + minCmr + ", maxCmr=" + maxCmr
				+ ", currency=" + currency + ", dpd=" + dpd + ", denomination=" + denomination + ", assessmentMethod="
				+ assessmentMethod + ", promotorContribution=" + promotorContribution + ", wcRequirement="
				+ wcRequirement + ", maxGrowth=" + maxGrowth + ", isEdited=" + isEdited + "]";
	}
}