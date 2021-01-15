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
public class EligibililityRequest implements Serializable{

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
	private Double wcRequired;
	private Double totalRequirement;
	private Double shishuPromotorContribution;
	private Double kishorPromotorContribution;
	private Double tarunPromotorContribution;
	private Double womenPromotorContribution;
	private Double minorityCommunityPromotorContribution;
	private Double existingLimit;
	private Double fpLoanAmount;
	private Double result;
	private Double bankBureauEmi;
	private Double bankBureauEmiAllDir;
	private Double bankBureauAmount;
	private Double bankBureauCollateralAmount;
	private Double tenure;
	private Double tenureReq;
	private Double roi;
	private Double circularLoanAmount;
	private Double emi;

	private Double manufacturing; //for SHISHU
	private Double service;//for KISHOR
	private Double trading;//for TARUN

	// fp Provide max amount

	private Double foir;
	private Double proposedLoanAmount;
	private Boolean collateralSecurity;
	private Double amountofCollateral;
	private Boolean isActive;

	// CHANGES FOR NEW 22-9-18
	private Boolean isAverage;
	private Integer wcRenewalStatus;
	
	// For promoter contribution %
	private String castCategory;
	private Boolean mainDirectorIsWomen;


	// FOR CVL Expences %

	private Double cvlEliMotorTaxPct;

	private Double cvlEliInsurancePremiumPct;

	private Double cvlEliGarageRentPct;

	private Double cvlEliDepreciationPct;

	private Double cvlEliRepairExpPct;

	private Double cvlEliCostOilPct;

	private Double cvlEliStaffSalPct;

	private Double cvlEliDrawingExpPct;

	private Double cvlEliUnloadingChargesPct;

	private Double cvlEliIntOnBorrowingPct;

	private Double cvlEliOthersPct;

	// FOR CVL MARGIN

	private Double cvlCostOfFullyBuildModel;

	private Double cvlCostOfChasis;

	private Double cvlCostOfBody;

	private Double cvlOtherMarginExp;

	private Double cvl2ndHandVehicle;

	// For CVL Fuel Price

	private Double fuelCost;
	
	private String loanType;
	
	private Double monthlyLoanObligation;

	private String fuelType;

	private Double consideredPromotorContribution;
	private Double consideredPromotorContributionAmount;
	private Double valueAfterExistingLoanAmountDiduction;
	private Double netEligibileBankFinance;
	
	
	public EligibililityRequest(){
		
	}
	
	public EligibililityRequest(Long applicationId, Long productId,Long fpProuctId) {
		super();
		this.applicationId = applicationId;
		this.productId = productId;
		this.fpProductId = fpProuctId;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Double getConsideredPromotorContribution() {
		return consideredPromotorContribution;
	}

	public void setConsideredPromotorContribution(Double consideredPromotorContribution) {
		this.consideredPromotorContribution = consideredPromotorContribution;
	}

	public Double getConsideredPromotorContributionAmount() {
		return consideredPromotorContributionAmount;
	}

	public void setConsideredPromotorContributionAmount(Double consideredPromotorContributionAmount) {
		this.consideredPromotorContributionAmount = consideredPromotorContributionAmount;
	}

	public Double getValueAfterExistingLoanAmountDiduction() {
		return valueAfterExistingLoanAmountDiduction;
	}

	public void setValueAfterExistingLoanAmountDiduction(Double valueAfterExistingLoanAmountDiduction) {
		this.valueAfterExistingLoanAmountDiduction = valueAfterExistingLoanAmountDiduction;
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

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
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

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
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

	public Double getTotalRequirement() {
		return totalRequirement;
	}

	public void setTotalRequirement(Double totalRequirement) {
		this.totalRequirement = totalRequirement;
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

	public Double getExistingLimit() {
		return existingLimit;
	}

	public void setExistingLimit(Double existingLimit) {
		this.existingLimit = existingLimit;
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

	public Double getBankBureauEmi() {
		return bankBureauEmi;
	}

	public void setBankBureauEmi(Double bankBureauEmi) {
		this.bankBureauEmi = bankBureauEmi;
	}

	public Double getBankBureauEmiAllDir() {
		return bankBureauEmiAllDir;
	}

	public void setBankBureauEmiAllDir(Double bankBureauEmiAllDir) {
		this.bankBureauEmiAllDir = bankBureauEmiAllDir;
	}

	public Double getBankBureauAmount() {
		return bankBureauAmount;
	}

	public void setBankBureauAmount(Double bankBureauAmount) {
		this.bankBureauAmount = bankBureauAmount;
	}

	public Double getBankBureauCollateralAmount() {
		return bankBureauCollateralAmount;
	}

	public void setBankBureauCollateralAmount(Double bankBureauCollateralAmount) {
		this.bankBureauCollateralAmount = bankBureauCollateralAmount;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Double getTenureReq() {
		return tenureReq;
	}

	public void setTenureReq(Double tenureReq) {
		this.tenureReq = tenureReq;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getCircularLoanAmount() {
		return circularLoanAmount;
	}

	public void setCircularLoanAmount(Double circularLoanAmount) {
		this.circularLoanAmount = circularLoanAmount;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(Double manufacturing) {
		this.manufacturing = manufacturing;
	}

	public Double getService() {
		return service;
	}

	public void setService(Double service) {
		this.service = service;
	}

	public Double getTrading() {
		return trading;
	}

	public void setTrading(Double trading) {
		this.trading = trading;
	}

	public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public Double getProposedLoanAmount() {
		return proposedLoanAmount;
	}

	public void setProposedLoanAmount(Double proposedLoanAmount) {
		this.proposedLoanAmount = proposedLoanAmount;
	}

	public Boolean getCollateralSecurity() {
		return collateralSecurity;
	}

	public void setCollateralSecurity(Boolean collateralSecurity) {
		this.collateralSecurity = collateralSecurity;
	}

	public Double getAmountofCollateral() {
		return amountofCollateral;
	}

	public void setAmountofCollateral(Double amountofCollateral) {
		this.amountofCollateral = amountofCollateral;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsAverage() {
		return isAverage;
	}

	public void setIsAverage(Boolean isAverage) {
		this.isAverage = isAverage;
	}

	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
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

	public Double getCvlEliMotorTaxPct() {
		return cvlEliMotorTaxPct;
	}

	public void setCvlEliMotorTaxPct(Double cvlEliMotorTaxPct) {
		this.cvlEliMotorTaxPct = cvlEliMotorTaxPct;
	}

	public Double getCvlEliInsurancePremiumPct() {
		return cvlEliInsurancePremiumPct;
	}

	public void setCvlEliInsurancePremiumPct(Double cvlEliInsurancePremiumPct) {
		this.cvlEliInsurancePremiumPct = cvlEliInsurancePremiumPct;
	}

	public Double getCvlEliGarageRentPct() {
		return cvlEliGarageRentPct;
	}

	public void setCvlEliGarageRentPct(Double cvlEliGarageRentPct) {
		this.cvlEliGarageRentPct = cvlEliGarageRentPct;
	}

	public Double getCvlEliDepreciationPct() {
		return cvlEliDepreciationPct;
	}

	public void setCvlEliDepreciationPct(Double cvlEliDepreciationPct) {
		this.cvlEliDepreciationPct = cvlEliDepreciationPct;
	}

	public Double getCvlEliRepairExpPct() {
		return cvlEliRepairExpPct;
	}

	public void setCvlEliRepairExpPct(Double cvlEliRepairExpPct) {
		this.cvlEliRepairExpPct = cvlEliRepairExpPct;
	}

	public Double getCvlEliCostOilPct() {
		return cvlEliCostOilPct;
	}

	public void setCvlEliCostOilPct(Double cvlEliCostOilPct) {
		this.cvlEliCostOilPct = cvlEliCostOilPct;
	}

	public Double getCvlEliStaffSalPct() {
		return cvlEliStaffSalPct;
	}

	public void setCvlEliStaffSalPct(Double cvlEliStaffSalPct) {
		this.cvlEliStaffSalPct = cvlEliStaffSalPct;
	}

	public Double getCvlEliDrawingExpPct() {
		return cvlEliDrawingExpPct;
	}

	public void setCvlEliDrawingExpPct(Double cvlEliDrawingExpPct) {
		this.cvlEliDrawingExpPct = cvlEliDrawingExpPct;
	}

	public Double getCvlEliUnloadingChargesPct() {
		return cvlEliUnloadingChargesPct;
	}

	public void setCvlEliUnloadingChargesPct(Double cvlEliUnloadingChargesPct) {
		this.cvlEliUnloadingChargesPct = cvlEliUnloadingChargesPct;
	}

	public Double getCvlEliIntOnBorrowingPct() {
		return cvlEliIntOnBorrowingPct;
	}

	public void setCvlEliIntOnBorrowingPct(Double cvlEliIntOnBorrowingPct) {
		this.cvlEliIntOnBorrowingPct = cvlEliIntOnBorrowingPct;
	}

	public Double getCvlEliOthersPct() {
		return cvlEliOthersPct;
	}

	public void setCvlEliOthersPct(Double cvlEliOthersPct) {
		this.cvlEliOthersPct = cvlEliOthersPct;
	}

	public Double getCvlCostOfFullyBuildModel() {
		return cvlCostOfFullyBuildModel;
	}

	public void setCvlCostOfFullyBuildModel(Double cvlCostOfFullyBuildModel) {
		this.cvlCostOfFullyBuildModel = cvlCostOfFullyBuildModel;
	}

	public Double getCvlCostOfChasis() {
		return cvlCostOfChasis;
	}

	public void setCvlCostOfChasis(Double cvlCostOfChasis) {
		this.cvlCostOfChasis = cvlCostOfChasis;
	}

	public Double getCvlCostOfBody() {
		return cvlCostOfBody;
	}

	public void setCvlCostOfBody(Double cvlCostOfBody) {
		this.cvlCostOfBody = cvlCostOfBody;
	}

	public Double getCvlOtherMarginExp() {
		return cvlOtherMarginExp;
	}

	public void setCvlOtherMarginExp(Double cvlOtherMarginExp) {
		this.cvlOtherMarginExp = cvlOtherMarginExp;
	}

	public Double getCvl2ndHandVehicle() {
		return cvl2ndHandVehicle;
	}

	public void setCvl2ndHandVehicle(Double cvl2ndHandVehicle) {
		this.cvl2ndHandVehicle = cvl2ndHandVehicle;
	}

	public Double getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public Double getMonthlyLoanObligation() {
		return monthlyLoanObligation;
	}

	public void setMonthlyLoanObligation(Double monthlyLoanObligation) {
		this.monthlyLoanObligation = monthlyLoanObligation;
	}

	public Double getNetEligibileBankFinance() {
		return netEligibileBankFinance;
	}

	public void setNetEligibileBankFinance(Double netEligibileBankFinance) {
		this.netEligibileBankFinance = netEligibileBankFinance;
	}
}
