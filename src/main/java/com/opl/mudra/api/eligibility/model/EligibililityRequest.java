package com.opl.mudra.api.eligibility.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Column;

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

	private Integer cvlEliMotorTax;

	private Integer cvlEliInsurancePremium;

	private Integer cvlEliGarageRent;

	private Integer cvlEliDepreciation;

	private Integer cvlEliRepairExp;

	private Integer cvlEliCostOil;

	private Integer cvlEliStaffSal;

	private Integer cvlEliDrawingExp;

	private Integer cvlEliUnloadingCharges;

	private Integer cvlEliIntOnBorrowing;

	private Integer cvlEliOthers;

	// FOR CVL MARGIN

	private Integer cvlCostOfFullyBuildModel;

	private Integer cvlCostOfChasis;

	private Integer cvlCostOfBody;

	private Integer cvlOtherMarginExp;

	private Integer cvl2ndHandVehicle;

	// For CVL Fuel Price

	private Double fuelCost;
	
	private String loanType;
	
	
	public EligibililityRequest(){
		
	}
	
	public EligibililityRequest(Long applicationId, Long productId,Long fpProuctId) {
		super();
		this.applicationId = applicationId;
		this.productId = productId;
		this.fpProductId = fpProuctId;
	}


	public Double getFuelCost() {
		return fuelCost;
	}

	public void setFuelCost(Double fuelCost) {
		this.fuelCost = fuelCost;
	}

	public Integer getCvlEliMotorTax() {
		return cvlEliMotorTax;
	}

	public void setCvlEliMotorTax(Integer cvlEliMotorTax) {
		this.cvlEliMotorTax = cvlEliMotorTax;
	}

	public Integer getCvlEliInsurancePremium() {
		return cvlEliInsurancePremium;
	}

	public void setCvlEliInsurancePremium(Integer cvlEliInsurancePremium) {
		this.cvlEliInsurancePremium = cvlEliInsurancePremium;
	}

	public Integer getCvlEliGarageRent() {
		return cvlEliGarageRent;
	}

	public void setCvlEliGarageRent(Integer cvlEliGarageRent) {
		this.cvlEliGarageRent = cvlEliGarageRent;
	}

	public Integer getCvlEliDepreciation() {
		return cvlEliDepreciation;
	}

	public void setCvlEliDepreciation(Integer cvlEliDepreciation) {
		this.cvlEliDepreciation = cvlEliDepreciation;
	}

	public Integer getCvlEliRepairExp() {
		return cvlEliRepairExp;
	}

	public void setCvlEliRepairExp(Integer cvlEliRepairExp) {
		this.cvlEliRepairExp = cvlEliRepairExp;
	}

	public Integer getCvlEliCostOil() {
		return cvlEliCostOil;
	}

	public void setCvlEliCostOil(Integer cvlEliCostOil) {
		this.cvlEliCostOil = cvlEliCostOil;
	}

	public Integer getCvlEliStaffSal() {
		return cvlEliStaffSal;
	}

	public void setCvlEliStaffSal(Integer cvlEliStaffSal) {
		this.cvlEliStaffSal = cvlEliStaffSal;
	}

	public Integer getCvlEliDrawingExp() {
		return cvlEliDrawingExp;
	}

	public void setCvlEliDrawingExp(Integer cvlEliDrawingExp) {
		this.cvlEliDrawingExp = cvlEliDrawingExp;
	}

	public Integer getCvlEliUnloadingCharges() {
		return cvlEliUnloadingCharges;
	}

	public void setCvlEliUnloadingCharges(Integer cvlEliUnloadingCharges) {
		this.cvlEliUnloadingCharges = cvlEliUnloadingCharges;
	}

	public Integer getCvlEliIntOnBorrowing() {
		return cvlEliIntOnBorrowing;
	}

	public void setCvlEliIntOnBorrowing(Integer cvlEliIntOnBorrowing) {
		this.cvlEliIntOnBorrowing = cvlEliIntOnBorrowing;
	}

	public Integer getCvlEliOthers() {
		return cvlEliOthers;
	}

	public void setCvlEliOthers(Integer cvlEliOthers) {
		this.cvlEliOthers = cvlEliOthers;
	}

	public Integer getCvlCostOfFullyBuildModel() {
		return cvlCostOfFullyBuildModel;
	}

	public void setCvlCostOfFullyBuildModel(Integer cvlCostOfFullyBuildModel) {
		this.cvlCostOfFullyBuildModel = cvlCostOfFullyBuildModel;
	}

	public Integer getCvlCostOfChasis() {
		return cvlCostOfChasis;
	}

	public void setCvlCostOfChasis(Integer cvlCostOfChasis) {
		this.cvlCostOfChasis = cvlCostOfChasis;
	}

	public Integer getCvlCostOfBody() {
		return cvlCostOfBody;
	}

	public void setCvlCostOfBody(Integer cvlCostOfBody) {
		this.cvlCostOfBody = cvlCostOfBody;
	}

	public Integer getCvlOtherMarginExp() {
		return cvlOtherMarginExp;
	}

	public void setCvlOtherMarginExp(Integer cvlOtherMarginExp) {
		this.cvlOtherMarginExp = cvlOtherMarginExp;
	}

	public Integer getCvl2ndHandVehicle() {
		return cvl2ndHandVehicle;
	}

	public void setCvl2ndHandVehicle(Integer cvl2ndHandVehicle) {
		this.cvl2ndHandVehicle = cvl2ndHandVehicle;
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

	public void setFpProductId(Long fpProuctId) {
		this.fpProductId = fpProuctId;
	}

	public Integer getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Integer assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Double getTotalRequirement() {
		return totalRequirement;
	}

	public void setTotalRequirement(Double totalRequirement) {
		this.totalRequirement = totalRequirement;
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

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
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

	public Double getCircularLoanAmount() {
		return circularLoanAmount;
	}

	public void setCircularLoanAmount(Double circularLoanAmount) {
		this.circularLoanAmount = circularLoanAmount;
	}

	public Double getTenureReq() {
		return tenureReq;
	}

	public void setTenureReq(Double tenureReq) {
		this.tenureReq = tenureReq;
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

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
	

	
}
