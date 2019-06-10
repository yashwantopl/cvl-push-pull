package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitAndLossStmntReq  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;

    private String year;

    private Long applicationId;
    
    private String currency;

    private Double subTotalOfIncome;

    private Double totalGrossSales;

    private Double lessExciseDuty;

    private Double netSales;

    private Double inDecStock;

    private Double rawMaterials;

    private Double powerAndFuel;

    private Double otherMfgExpenses;

    private Double generalAdminExp;

    private Double sellingAndDistributionExpenses;

    private Double miscellaneousExpenses;

    private Double expensesAmortised;

    private Double totalExpenditure;

    private Double operatingProfitOI;

    private Double addOtherRevenueIncome;

    private Double operatingProfitEBITDA;

    private Double interest;

    private Double pbdt;

    private Double depreciation;

    private Double profitBeforeTaxAndExpItems;

    private Double exceptionalIncomeExp;

    private Double profitBeforeTax;

    private Double provisionForTaxes;

    private Double profitAfterTax;

    private Double dividendRate;

    private Double equityDividend;

    private Double earningsPerShare;
    
    private Double employeeCost;

    private Date createdDate;

    private Boolean isActive;
    
    private Double otherIncome;
    
	
	public ProfitAndLossStmntReq() {
		super();
		this.subTotalOfIncome = 0.0;
		this.totalGrossSales = 0.0;
		this.lessExciseDuty = 0.0;
		this.netSales = 0.0;
		this.inDecStock = 0.0;
		this.rawMaterials = 0.0;
		this.powerAndFuel = 0.0;
		this.otherMfgExpenses = 0.0;
		this.generalAdminExp = 0.0;
		this.sellingAndDistributionExpenses = 0.0;
		this.miscellaneousExpenses = 0.0;
		this.expensesAmortised = 0.0;
		this.totalExpenditure = 0.0;
		this.operatingProfitOI = 0.0;
		this.addOtherRevenueIncome = 0.0;
		this.operatingProfitEBITDA = 0.0;
		this.interest = 0.0;
		this.pbdt = 0.0;
		this.depreciation = 0.0;
		this.profitBeforeTaxAndExpItems = 0.0;
		this.exceptionalIncomeExp = 0.0;
		this.profitBeforeTax = 0.0;
		this.provisionForTaxes = 0.0;
		this.profitAfterTax = 0.0;
		this.dividendRate = 0.0;
		this.equityDividend = 0.0;
		this.earningsPerShare = 0.0;
		this.employeeCost = 0.0;
		this.otherIncome = 0.0;
	}




	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getSubTotalOfIncome() {
		return subTotalOfIncome;
	}

	/**
	 * @param subTotalOfIncome the subTotalOfIncome to set
	 */
	public void setSubTotalOfIncome(Double subTotalOfIncome) {
		this.subTotalOfIncome = subTotalOfIncome;
	}

	public Double getTotalGrossSales() {
		return totalGrossSales;
	}

	/**
	 * @param totalGrossSales the totalGrossSales to set
	 */
	public void setTotalGrossSales(Double totalGrossSales) {
		this.totalGrossSales = totalGrossSales;
	}

	public Double getLessExciseDuty() {
		return lessExciseDuty;
	}

	/**
	 * @param lessExciseDuty the lessExciseDuty to set
	 */
	public void setLessExciseDuty(Double lessExciseDuty) {
		this.lessExciseDuty = lessExciseDuty;
	}

	public Double getNetSales() {
		return netSales;
	}

	/**
	 * @param netSales the netSales to set
	 */
	public void setNetSales(Double netSales) {
		this.netSales = netSales;
	}

	public Double getInDecStock() {
		return inDecStock;
	}

	/**
	 * @param inDecStock the inDecStock to set
	 */
	public void setInDecStock(Double inDecStock) {
		this.inDecStock = inDecStock;
	}

	public Double getRawMaterials() {
		return rawMaterials;
	}

	/**
	 * @param rawMaterials the rawMaterials to set
	 */
	public void setRawMaterials(Double rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

	public Double getPowerAndFuel() {
		return powerAndFuel;
	}

	/**
	 * @param powerAndFuel the powerAndFuel to set
	 */
	public void setPowerAndFuel(Double powerAndFuel) {
		this.powerAndFuel = powerAndFuel;
	}

	public Double getOtherMfgExpenses() {
		return otherMfgExpenses;
	}

	/**
	 * @param otherMfgExpenses the otherMfgExpenses to set
	 */
	public void setOtherMfgExpenses(Double otherMfgExpenses) {
		this.otherMfgExpenses = otherMfgExpenses;
	}

	public Double getGeneralAdminExp() {
		return generalAdminExp;
	}

	/**
	 * @param generalAdminExp the generalAdminExp to set
	 */
	public void setGeneralAdminExp(Double generalAdminExp) {
		this.generalAdminExp = generalAdminExp;
	}

	public Double getSellingAndDistributionExpenses() {
		return sellingAndDistributionExpenses;
	}

	/**
	 * @param sellingAndDistributionExpenses the sellingAndDistributionExpenses to set
	 */
	public void setSellingAndDistributionExpenses(Double sellingAndDistributionExpenses) {
		this.sellingAndDistributionExpenses = sellingAndDistributionExpenses;
	}

	public Double getMiscellaneousExpenses() {
		return miscellaneousExpenses;
	}

	/**
	 * @param miscellaneousExpenses the miscellaneousExpenses to set
	 */
	public void setMiscellaneousExpenses(Double miscellaneousExpenses) {
		this.miscellaneousExpenses = miscellaneousExpenses;
	}

	public Double getExpensesAmortised() {
		return expensesAmortised;
	}

	/**
	 * @param expensesAmortised the expensesAmortised to set
	 */
	public void setExpensesAmortised(Double expensesAmortised) {
		this.expensesAmortised = expensesAmortised;
	}

	public Double getTotalExpenditure() {
		return totalExpenditure;
	}

	/**
	 * @param totalExpenditure the totalExpenditure to set
	 */
	public void setTotalExpenditure(Double totalExpenditure) {
		this.totalExpenditure = totalExpenditure;
	}

	public Double getOperatingProfitOI() {
		return operatingProfitOI;
	}

	/**
	 * @param operatingProfitOI the operatingProfitOI to set
	 */
	public void setOperatingProfitOI(Double operatingProfitOI) {
		this.operatingProfitOI = operatingProfitOI;
	}

	public Double getAddOtherRevenueIncome() {
		return addOtherRevenueIncome;
	}

	/**
	 * @param addOtherRevenueIncome the addOtherRevenueIncome to set
	 */
	public void setAddOtherRevenueIncome(Double addOtherRevenueIncome) {
		this.addOtherRevenueIncome = addOtherRevenueIncome;
	}

	public Double getOperatingProfitEBITDA() {
		return operatingProfitEBITDA;
	}

	/**
	 * @param operatingProfitEBITDA the operatingProfitEBITDA to set
	 */
	public void setOperatingProfitEBITDA(Double operatingProfitEBITDA) {
		this.operatingProfitEBITDA = operatingProfitEBITDA;
	}


	public Double getInterest() {
		return interest;
	}

	/**
	 * @param interest the interest to set
	 */
	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Double getPbdt() {
		return pbdt;
	}

	/**
	 * @param pbdt the pbdt to set
	 */
	public void setPbdt(Double pbdt) {
		this.pbdt = pbdt;
	}

	public Double getDepreciation() {
		return depreciation;
	}

	/**
	 * @param depreciation the depreciation to set
	 */
	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}

	public Double getProfitBeforeTaxAndExpItems() {
		return profitBeforeTaxAndExpItems;
	}

	/**
	 * @param profitBeforeTaxAndExpItems the profitBeforeTaxAndExpItems to set
	 */
	public void setProfitBeforeTaxAndExpItems(Double profitBeforeTaxAndExpItems) {
		this.profitBeforeTaxAndExpItems = profitBeforeTaxAndExpItems;
	}

	public Double getExceptionalIncomeExp() {
		return exceptionalIncomeExp;
	}

	/**
	 * @param exceptionalIncomeExp the exceptionalIncomeExp to set
	 */
	public void setExceptionalIncomeExp(Double exceptionalIncomeExp) {
		this.exceptionalIncomeExp = exceptionalIncomeExp;
	}

	public Double getProfitBeforeTax() {
		return profitBeforeTax;
	}

	/**
	 * @param profitBeforeTax the profitBeforeTax to set
	 */
	public void setProfitBeforeTax(Double profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}

	public Double getProvisionForTaxes() {
		return provisionForTaxes;
	}

	/**
	 * @param provisionForTaxes the provisionForTaxes to set
	 */
	public void setProvisionForTaxes(Double provisionForTaxes) {
		this.provisionForTaxes = provisionForTaxes;
	}

	public Double getProfitAfterTax() {
		return profitAfterTax;
	}

	/**
	 * @param profitAfterTax the profitAfterTax to set
	 */
	public void setProfitAfterTax(Double profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}

	public Double getDividendRate() {
		return dividendRate;
	}

	/**
	 * @param dividendRate the dividendRate to set
	 */
	public void setDividendRate(Double dividendRate) {
		this.dividendRate = dividendRate;
	}

	public Double getEquityDividend() {
		return equityDividend;
	}

	/**
	 * @param equityDividend the equityDividend to set
	 */
	public void setEquityDividend(Double equityDividend) {
		this.equityDividend = equityDividend;
	}

	public Double getEarningsPerShare() {
		return earningsPerShare;
	}

	/**
	 * @param earningsPerShare the earningsPerShare to set
	 */
	public void setEarningsPerShare(Double earningsPerShare) {
		this.earningsPerShare = earningsPerShare;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	

	public Double getEmployeeCost() {
		return employeeCost;
	}

	/**
	 * @param employeeCost the employeeCost to set
	 */
	public void setEmployeeCost(Double employeeCost) {
		this.employeeCost = employeeCost;
	}


	public Double getOtherIncome() {
		return otherIncome;
	}


	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	@Override
	public String toString() {
		return "ProfitAndLossStmntReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId + ", currency="
				+ currency + ", subTotalOfIncome=" + subTotalOfIncome + ", totalGrossSales=" + totalGrossSales
				+ ", lessExciseDuty=" + lessExciseDuty + ", netSales=" + netSales + ", inDecStock=" + inDecStock
				+ ", rawMaterials=" + rawMaterials + ", powerAndFuel=" + powerAndFuel + ", otherMfgExpenses="
				+ otherMfgExpenses + ", generalAdminExp=" + generalAdminExp + ", sellingAndDistributionExpenses="
				+ sellingAndDistributionExpenses + ", miscellaneousExpenses=" + miscellaneousExpenses
				+ ", expensesAmortised=" + expensesAmortised + ", totalExpenditure=" + totalExpenditure
				+ ", operatingProfitOI=" + operatingProfitOI + ", addOtherRevenueIncome=" + addOtherRevenueIncome
				+ ", operatingProfitEBITDA=" + operatingProfitEBITDA + ", interest=" + interest + ", pbdt=" + pbdt
				+ ", depreciation=" + depreciation + ", profitBeforeTaxAndExpItems=" + profitBeforeTaxAndExpItems
				+ ", exceptionalIncomeExp=" + exceptionalIncomeExp + ", profitBeforeTax=" + profitBeforeTax
				+ ", provisionForTaxes=" + provisionForTaxes + ", profitAfterTax=" + profitAfterTax + ", dividendRate="
				+ dividendRate + ", equityDividend=" + equityDividend + ", earningsPerShare=" + earningsPerShare
				+ ", employeeCost=" + employeeCost + ", createdDate=" + createdDate + ", isActive=" + isActive
				+ ", otherIncome=" + otherIncome + "]";
	}
}
