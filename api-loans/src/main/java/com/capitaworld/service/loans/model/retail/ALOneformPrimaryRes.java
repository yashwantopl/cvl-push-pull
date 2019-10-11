package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ALOneformPrimaryRes implements Serializable {

	
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
	private Integer repaymentMode;
	private Integer employmentType;
	private Long borrowerContribution;
	private Double monthlyIncome;
	private Double grossMonthlyIncome;
	private Long clientId;  
	
	// Vehicle details
	private Integer vehicleType; 
	private Integer vehicleCategory; 
	private Integer vehicleSegment; 
	private Integer vehicleAge; 
	private Integer vehicleEngineVolume; 
	private Integer vehicleUse; 
	private Long vehicleExShowRoomPrice;
	private Long vehicleOnRoadPrice; 
	private Long vehicleAgreedPurchasePrice;
	private Boolean isVehicleHypothecation;
	
	// Declaration
	private Boolean isCheckOffDirectPayEmi;
	private Boolean isCheckOffAgreeToPayOutstanding;
	private Boolean isCheckOffShiftSalAcc;
	private Boolean isCheckOffPayOutstndAmount;
	private Boolean isCheckOffNotChangeSalAcc;
	
	private String currencyValue;

	//additional Parameters
	private Long manufacturerId;
	private Long assetModelId;
	private Integer assetMake;
	private Long supplierStateId;
	private Long supplierCityId;
	private Long supplierId;
	
	
	public ALOneformPrimaryRes() {
		// TODO Auto-generated constructor stub
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(Integer repayment) {
		this.repaymentMode = repayment;
	}

	public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(Integer vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public Integer getVehicleSegment() {
		return vehicleSegment;
	}

	public void setVehicleSegment(Integer vehicleSegment) {
		this.vehicleSegment = vehicleSegment;
	}

	public Integer getVehicleAge() {
		return vehicleAge;
	}

	public void setVehicleAge(Integer vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

	public Integer getVehicleEngineVolume() {
		return vehicleEngineVolume;
	}

	public void setVehicleEngineVolume(Integer vehicleEngineVolume) {
		this.vehicleEngineVolume = vehicleEngineVolume;
	}

	public Integer getVehicleUse() {
		return vehicleUse;
	}

	public void setVehicleUse(Integer vechicleUse) {
		this.vehicleUse = vechicleUse;
	}

	public Long getVehicleExShowRoomPrice() {
		return vehicleExShowRoomPrice;
	}

	public void setVehicleExShowRoomPrice(Long vehicleExShowRoomPrice) {
		this.vehicleExShowRoomPrice = vehicleExShowRoomPrice;
	}

	public Long getVehicleOnRoadPrice() {
		return vehicleOnRoadPrice;
	}

	public void setVehicleOnRoadPrice(Long vehicleOnRoadPrice) {
		this.vehicleOnRoadPrice = vehicleOnRoadPrice;
	}

	public Long getVehicleAgreedPurchasePrice() {
		return vehicleAgreedPurchasePrice;
	}

	public void setVehicleAgreedPurchasePrice(Long vehicleAgreedPurchasePrice) {
		this.vehicleAgreedPurchasePrice = vehicleAgreedPurchasePrice;
	}

	public Boolean getIsVehicleHypothecation() {
		return isVehicleHypothecation;
	}

	public void setIsVehicleHypothecation(Boolean isVehicleHypothecation) {
		this.isVehicleHypothecation = isVehicleHypothecation;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Long getBorrowerContribution() {
		return borrowerContribution;
	}

	public void setBorrowerContribution(Long borrowerContribution) {
		this.borrowerContribution = borrowerContribution;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	public Boolean getIsCheckOffDirectPayEmi() {
		return isCheckOffDirectPayEmi;
	}

	public void setIsCheckOffDirectPayEmi(Boolean isCheckOffDirectPayEmi) {
		this.isCheckOffDirectPayEmi = isCheckOffDirectPayEmi;
	}

	public Boolean getIsCheckOffAgreeToPayOutstanding() {
		return isCheckOffAgreeToPayOutstanding;
	}

	public void setIsCheckOffAgreeToPayOutstanding(Boolean isCheckOffAgreeToPayOutstanding) {
		this.isCheckOffAgreeToPayOutstanding = isCheckOffAgreeToPayOutstanding;
	}

	public Boolean getIsCheckOffShiftSalAcc() {
		return isCheckOffShiftSalAcc;
	}

	public void setIsCheckOffShiftSalAcc(Boolean isCheckOffShiftSalAcc) {
		this.isCheckOffShiftSalAcc = isCheckOffShiftSalAcc;
	}

	public Boolean getIsCheckOffPayOutstndAmount() {
		return isCheckOffPayOutstndAmount;
	}

	public void setIsCheckOffPayOutstndAmount(Boolean isCheckOffPayOutstndAmount) {
		this.isCheckOffPayOutstndAmount = isCheckOffPayOutstndAmount;
	}

	public Boolean getIsCheckOffNotChangeSalAcc() {
		return isCheckOffNotChangeSalAcc;
	}

	public void setIsCheckOffNotChangeSalAcc(Boolean isCheckOffNotChangeSalAcc) {
		this.isCheckOffNotChangeSalAcc = isCheckOffNotChangeSalAcc;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getAssetModelId() {
		return assetModelId;
	}

	public void setAssetModelId(Long assetModelId) {
		this.assetModelId = assetModelId;
	}

	public Integer getAssetMake() {
		return assetMake;
	}

	public void setAssetMake(Integer assetMake) {
		this.assetMake = assetMake;
	}

	public Long getSupplierStateId() {
		return supplierStateId;
	}

	public void setSupplierStateId(Long supplierStateId) {
		this.supplierStateId = supplierStateId;
	}

	public Long getSupplierCityId() {
		return supplierCityId;
	}

	public void setSupplierCityId(Long supplierCityId) {
		this.supplierCityId = supplierCityId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
}
