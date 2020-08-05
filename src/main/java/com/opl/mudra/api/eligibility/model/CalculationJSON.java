package com.opl.mudra.api.eligibility.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CalculationJSON implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long applicationId;
	private Long directorId;
	private Long fpProductId;
	private Long productId;
	private Long userId;
	private Integer assessmentId;
	private Double estSales12Months;
	private Double totalRequirement;
	private Double consideredPromotorContribution;
	private Double consideredPromotorContributionAmount;
	private Double valueAfterExistingLoanAmountDiduction;
	private Double shishuPromotorContribution;
	private Double kishorPromotorContribution;
	private Double tarunPromotorContribution;
	private Double totalBankFinance;
	private Double netEligibileBankFinance;
	private Double wcRequired;
	private Integer wcRenewalStatus;
	private Double existingLimit;
	private Double proposedLoanAmount;
	private Double circularLoanAmount;
	private Double fpLoanAmount;
	private Double result;
	private Double emi;
	private Double roi;
	private Double tenure;
	private Double tenureReq;
	private Double finalTenure;
	private Boolean isAverage;
	private String castCategory;
	private Boolean mainDirectorIsWomen;
	private Double womenPromotorContribution;
	private Double minorityCommunityPromotorContribution;
	
	public CalculationJSON(){
		
	}
	
	public CalculationJSON(Long applicationId, Long productId,Long fpProuctId) {
		super();
		this.applicationId = applicationId;
		this.productId = productId;
		this.fpProductId = fpProuctId;
	}


	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getEstSales12Months() {
		return estSales12Months;
	}
	public void setEstSales12Months(Double estSales12Months) {
		this.estSales12Months = estSales12Months;
	}
	public Double getWcRequired() {
		return wcRequired;
	}
	public void setWcRequired(Double wcRequired) {
		this.wcRequired = wcRequired;
	}
	public Double getExistingLimit() {
		return existingLimit;
	}
	public void setExistingLimit(Double existingLimit) {
		this.existingLimit = existingLimit;
	}
	public Double getProposedLoanAmount() {
		return proposedLoanAmount;
	}
	public void setProposedLoanAmount(Double proposedLoanAmount) {
		this.proposedLoanAmount = proposedLoanAmount;
	}
	public Double getFpLoanAmount() {
		return fpLoanAmount;
	}
	public void setFpLoanAmount(Double fpLoanAmount) {
		this.fpLoanAmount = fpLoanAmount;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Double getTotalBankFinance() {
		return totalBankFinance;
	}

	public void setTotalBankFinance(Double totalBankFinance) {
		this.totalBankFinance = totalBankFinance;
	}

	public Double getNetEligibileBankFinance() {
		return netEligibileBankFinance;
	}

	public void setNetEligibileBankFinance(Double netEligibileBankFinance) {
		this.netEligibileBankFinance = netEligibileBankFinance;
	}

	public Double getTotalRequirement() {
		return totalRequirement;
	}

	public void setTotalRequirement(Double totalRequirement) {
		this.totalRequirement = totalRequirement;
	}

	public Double getConsideredPromotorContribution() {
		return consideredPromotorContribution;
	}

	public void setConsideredPromotorContribution(Double promotorContribution) {
		this.consideredPromotorContribution = promotorContribution;
	}

	public Double getShishuPromotorContribution() {
		return shishuPromotorContribution;
	}

	public void setShishuPromotorContribution(Double shishuPromotorContribution) {
		this.shishuPromotorContribution = shishuPromotorContribution;
	}

	public Double getKishorPromotorContribution() {
		return kishorPromotorContribution;
	}

	public void setKishorPromotorContribution(Double kishorPromotorContribution) {
		this.kishorPromotorContribution = kishorPromotorContribution;
	}

	public Double getTarunPromotorContribution() {
		return tarunPromotorContribution;
	}

	public void setTarunPromotorContribution(Double tarunPromotorContribution) {
		this.tarunPromotorContribution = tarunPromotorContribution;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getCircularLoanAmount() {
		return circularLoanAmount;
	}

	public void setCircularLoanAmount(Double circularLoanAmount) {
		this.circularLoanAmount = circularLoanAmount;
	}

	public Double getConsideredPromotorContributionAmount() {
		return consideredPromotorContributionAmount;
	}

	public void setConsideredPromotorContributionAmount(Double consideredPromotorContributionAmount) {
		this.consideredPromotorContributionAmount = consideredPromotorContributionAmount;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Boolean getIsAverage() {
		return isAverage;
	}

	public void setIsAverage(Boolean isAverage) {
		this.isAverage = isAverage;
	}

	public Double getValueAfterExistingLoanAmountDiduction() {
		return valueAfterExistingLoanAmountDiduction;
	}

	public void setValueAfterExistingLoanAmountDiduction(Double valueAfterExistingLoanAmountDiduction) {
		this.valueAfterExistingLoanAmountDiduction = valueAfterExistingLoanAmountDiduction;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
	}

	public Double getTenureReq() {
		return tenureReq;
	}

	public void setTenureReq(Double tenureReq) {
		this.tenureReq = tenureReq;
	}

	public Double getFinalTenure() {
		return finalTenure;
	}

	public void setFinalTenure(Double finalTenure) {
		this.finalTenure = finalTenure;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public Boolean getMainDirectorIsWomen() {
		return mainDirectorIsWomen;
	}

	public void setMainDirectorIsWomen(Boolean mainDirectorIsWomen) {
		this.mainDirectorIsWomen = mainDirectorIsWomen;
	}

	public Double getWomenPromotorContribution() {
		return womenPromotorContribution;
	}

	public void setWomenPromotorContribution(Double womenPromotorContribution) {
		this.womenPromotorContribution = womenPromotorContribution;
	}

	public Double getMinorityCommunityPromotorContribution() {
		return minorityCommunityPromotorContribution;
	}

	public void setMinorityCommunityPromotorContribution(Double minorityCommunityPromotorContribution) {
		this.minorityCommunityPromotorContribution = minorityCommunityPromotorContribution;
	}

	
}
