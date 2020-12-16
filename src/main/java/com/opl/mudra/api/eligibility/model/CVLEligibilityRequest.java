package com.opl.mudra.api.eligibility.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CVLEligibilityRequest extends EligibililityRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	private EligibililityRequest eligibililityRequest;
	// Vehicle & operator detail
	private Double agreedPurchasePrice;
    private Double otherCost;
    private Double fullyBuiltCost;
    private Double chassisCost;
    private Double bodyBuildingCost;
    private Double totalCostOfProposedVehicle;
    
    // proposed vehicle incoem expense detail
    private Integer noOfDaysVehicleOperated;
    private Integer kmCoveredByVehicleInDay;
    private Double ratePerKm;
    private Double otherMonthlyIncome;
    private Double avgKmsPerLtr;
    private Double incomeTaxPaid;
    
    // Calculation data
    private Double eligibleProjectCost;
    private Double avgMonthlyIncome;
    private Double totalMonthlyIncome;
    private Double fuelCostPerMonth;
    private Double totalMonthlyExp;
    private Double surplusAmt;
    private Double netSurplusAmt;
    private Double netCashAccruals;
    private Double monthlyCashAccruals;
    private Double perLakhEmi;
    private Double termLoanCashFlow;
    private Double netEligibleTermLoan;
    private Double eligibleLoanAmt;
    private Double eligibleLoanEmi;
    private Double dscr;
    
	public Double getAgreedPurchasePrice() {
		return agreedPurchasePrice;
	}
	public void setAgreedPurchasePrice(Double agreedPurchasePrice) {
		this.agreedPurchasePrice = agreedPurchasePrice;
	}
	public Double getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(Double otherCost) {
		this.otherCost = otherCost;
	}
	public Double getFullyBuiltCost() {
		return fullyBuiltCost;
	}
	public void setFullyBuiltCost(Double fullyBuiltCost) {
		this.fullyBuiltCost = fullyBuiltCost;
	}
	public Double getChassisCost() {
		return chassisCost;
	}
	public void setChassisCost(Double chassisCost) {
		this.chassisCost = chassisCost;
	}
	public Double getBodyBuildingCost() {
		return bodyBuildingCost;
	}
	public void setBodyBuildingCost(Double bodyBuildingCost) {
		this.bodyBuildingCost = bodyBuildingCost;
	}
	public Double getTotalCostOfProposedVehicle() {
		return totalCostOfProposedVehicle;
	}
	public void setTotalCostOfProposedVehicle(Double totalCostOfProposedVehicle) {
		this.totalCostOfProposedVehicle = totalCostOfProposedVehicle;
	}
	public Integer getNoOfDaysVehicleOperated() {
		return noOfDaysVehicleOperated;
	}
	public void setNoOfDaysVehicleOperated(Integer noOfDaysVehicleOperated) {
		this.noOfDaysVehicleOperated = noOfDaysVehicleOperated;
	}
	public Integer getKmCoveredByVehicleInDay() {
		return kmCoveredByVehicleInDay;
	}
	public void setKmCoveredByVehicleInDay(Integer kmCoveredByVehicleInDay) {
		this.kmCoveredByVehicleInDay = kmCoveredByVehicleInDay;
	}
	public Double getRatePerKm() {
		return ratePerKm;
	}
	public void setRatePerKm(Double ratePerKm) {
		this.ratePerKm = ratePerKm;
	}
	public Double getOtherMonthlyIncome() {
		return otherMonthlyIncome;
	}
	public void setOtherMonthlyIncome(Double otherMonthlyIncome) {
		this.otherMonthlyIncome = otherMonthlyIncome;
	}
	public Double getAvgKmsPerLtr() {
		return avgKmsPerLtr;
	}
	public void setAvgKmsPerLtr(Double avgKmsPerLtr) {
		this.avgKmsPerLtr = avgKmsPerLtr;
	}
	public Double getIncomeTaxPaid() {
		return incomeTaxPaid;
	}
	public void setIncomeTaxPaid(Double incomeTaxPaid) {
		this.incomeTaxPaid = incomeTaxPaid;
	}
	public Double getEligibleProjectCost() {
		return eligibleProjectCost;
	}
	public void setEligibleProjectCost(Double eligibleProjectCost) {
		this.eligibleProjectCost = eligibleProjectCost;
	}
	public Double getAvgMonthlyIncome() {
		return avgMonthlyIncome;
	}
	public void setAvgMonthlyIncome(Double avgMonthlyIncome) {
		this.avgMonthlyIncome = avgMonthlyIncome;
	}
	public Double getTotalMonthlyIncome() {
		return totalMonthlyIncome;
	}
	public void setTotalMonthlyIncome(Double totalMonthlyIncome) {
		this.totalMonthlyIncome = totalMonthlyIncome;
	}
	public Double getFuelCostPerMonth() {
		return fuelCostPerMonth;
	}
	public void setFuelCostPerMonth(Double fuelCostPerMonth) {
		this.fuelCostPerMonth = fuelCostPerMonth;
	}
	public Double getTotalMonthlyExp() {
		return totalMonthlyExp;
	}
	public void setTotalMonthlyExp(Double totalMonthlyExp) {
		this.totalMonthlyExp = totalMonthlyExp;
	}
	public Double getNetSurplusAmt() {
		return netSurplusAmt;
	}
	public void setNetSurplusAmt(Double netSurplusAmt) {
		this.netSurplusAmt = netSurplusAmt;
	}
	public Double getNetCashAccruals() {
		return netCashAccruals;
	}
	public void setNetCashAccruals(Double netCashAccruals) {
		this.netCashAccruals = netCashAccruals;
	}
	public Double getMonthlyCashAccruals() {
		return monthlyCashAccruals;
	}
	public void setMonthlyCashAccruals(Double monthlyCashAccruals) {
		this.monthlyCashAccruals = monthlyCashAccruals;
	}
	public Double getPerLakhEmi() {
		return perLakhEmi;
	}
	public void setPerLakhEmi(Double perLakhEmi) {
		this.perLakhEmi = perLakhEmi;
	}
	public Double getTermLoanCashFlow() {
		return termLoanCashFlow;
	}
	public void setTermLoanCashFlow(Double termLoanCashFlow) {
		this.termLoanCashFlow = termLoanCashFlow;
	}
	public Double getNetEligibleTermLoan() {
		return netEligibleTermLoan;
	}
	public void setNetEligibleTermLoan(Double netEligibleTermLoan) {
		this.netEligibleTermLoan = netEligibleTermLoan;
	}
	public Double getEligibleLoanAmt() {
		return eligibleLoanAmt;
	}
	public void setEligibleLoanAmt(Double eligibleLoanAmt) {
		this.eligibleLoanAmt = eligibleLoanAmt;
	}
	public Double getEligibleLoanEmi() {
		return eligibleLoanEmi;
	}
	public void setEligibleLoanEmi(Double eligibleLoanEmi) {
		this.eligibleLoanEmi = eligibleLoanEmi;
	}
	public Double getDscr() {
		return dscr;
	}
	public void setDscr(Double dscr) {
		this.dscr = dscr;
	}

//	public EligibililityRequest getEligibililityRequest() {
//		return eligibililityRequest;
//	}
//
//	public void setEligibililityRequest(EligibililityRequest eligibililityRequest) {
//		this.eligibililityRequest = eligibililityRequest;
//	}

	public Double getSurplusAmt() {
		return surplusAmt;
	}

	public void setSurplusAmt(Double surplusAmt) {
		this.surplusAmt = surplusAmt;
	}
    
    

}
