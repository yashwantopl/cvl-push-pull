package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfitAndLossStmntReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String year;

    private Long applicationId;

    private String currency;

    private Double subTotalOfIncome;
    private String subTotalOfIncomeStr;

    private Double totalGrossSales;
    private String totalGrossSalesStr;

    private Double lessExciseDuty;
    private String lessExciseDutyStr;

    private Double netSales;
    private String netSalesStr;

    private Double inDecStock;
    private String inDecStockStr;

    private Double rawMaterials;
    private String rawMaterialsStr;

    private Double powerAndFuel;
    private String powerAndFuelStr;

    private Double otherMfgExpenses;
    private String otherMfgExpensesStr;

    private Double generalAdminExp;
    private String generalAdminExpStr;

    private Double sellingAndDistributionExpenses;
    private String sellingAndDistributionExpensesStr;

    private Double miscellaneousExpenses;
    private String miscellaneousExpensesStr;

    private Double expensesAmortised;
    private String expensesAmortisedStr;

    private Double totalExpenditure;
    private String totalExpenditureStr;

    private Double operatingProfitOI;
    private String operatingProfitOIStr;

    private Double addOtherRevenueIncome;
    private String addOtherRevenueIncomeStr;

    private Double operatingProfitEBITDA;
    private String operatingProfitEBITDAStr;

    private Double interest;
    private String interestStr;

    private Double pbdt;
    private String pbdtStr;

    private Double depreciation;
    private String depreciationStr;

    private Double profitBeforeTaxAndExpItems;
    private String profitBeforeTaxAndExpItemsStr;

    private Double exceptionalIncomeExp;
    private String exceptionalIncomeExpStr;

    private Double profitBeforeTax;
    private String profitBeforeTaxStr;

    private Double provisionForTaxes;
    private String provisionForTaxesStr;

    private Double profitAfterTax;
    private String profitAfterTaxStr;

    private Double dividendRate;
    private String dividendRateStr;

    private Double equityDividend;
    private String equityDividendStr;

    private Double earningsPerShare;
    private String earningsPerShareStr;

    private Double employeeCost;
    private String employeeCostStr;

    private Date createdDate;

    private Boolean isActive;

    private Double otherIncome;
    private String otherIncomeStr;

    private Double otherIncomeNeedTocCheckOp;
    private String otherIncomeNeedTocCheckOpStr;



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
        this.otherIncomeNeedTocCheckOp = 0.0;
        this.subTotalOfIncomeStr = "0.0";
        this.totalGrossSalesStr = "0.0";
        this.lessExciseDutyStr = "0.0";
        this.netSalesStr = "0.0";
        this.inDecStockStr = "0.0";
        this.rawMaterialsStr = "0.0";
        this.powerAndFuelStr = "0.0";
        this.otherMfgExpensesStr = "0.0";
        this.generalAdminExpStr = "0.0";
        this.sellingAndDistributionExpensesStr = "0.0";
        this.miscellaneousExpensesStr = "0.0";
        this.expensesAmortisedStr = "0.0";
        this.totalExpenditureStr = "0.0";
        this.operatingProfitOIStr = "0.0";
        this.addOtherRevenueIncomeStr = "0.0";
        this.operatingProfitEBITDAStr = "0.0";
        this.interestStr = "0.0";
        this.depreciationStr = "0.0";
        this.profitBeforeTaxAndExpItemsStr = "0.0";
        this.exceptionalIncomeExpStr = "0.0";
        this.profitBeforeTaxStr = "0.0";
        this.provisionForTaxesStr = "0.0";
        this.profitAfterTaxStr = "0.0";
        this.dividendRateStr = "0.0";
        this.equityDividendStr = "0.0";
        this.earningsPerShareStr = "0.0";
        this.employeeCostStr = "0.0";
        this.otherIncomeStr = "0.0";
        this.otherIncomeNeedTocCheckOpStr = "0.0";

    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public Long getApplicationId() {
        return applicationId;
    }


    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Double getSubTotalOfIncome() {
        return subTotalOfIncome;
    }


    public void setSubTotalOfIncome(Double subTotalOfIncome) {
        this.subTotalOfIncome = subTotalOfIncome;
    }


    public Double getTotalGrossSales() {
        return totalGrossSales;
    }


    public void setTotalGrossSales(Double totalGrossSales) {
        this.totalGrossSales = totalGrossSales;
    }


    public Double getLessExciseDuty() {
        return lessExciseDuty;
    }


    public void setLessExciseDuty(Double lessExciseDuty) {
        this.lessExciseDuty = lessExciseDuty;
    }


    public Double getNetSales() {
        return netSales;
    }


    public void setNetSales(Double netSales) {
        this.netSales = netSales;
    }


    public Double getInDecStock() {
        return inDecStock;
    }


    public void setInDecStock(Double inDecStock) {
        this.inDecStock = inDecStock;
    }


    public Double getRawMaterials() {
        return rawMaterials;
    }


    public void setRawMaterials(Double rawMaterials) {
        this.rawMaterials = rawMaterials;
    }


    public Double getPowerAndFuel() {
        return powerAndFuel;
    }


    public void setPowerAndFuel(Double powerAndFuel) {
        this.powerAndFuel = powerAndFuel;
    }


    public Double getOtherMfgExpenses() {
        return otherMfgExpenses;
    }


    public void setOtherMfgExpenses(Double otherMfgExpenses) {
        this.otherMfgExpenses = otherMfgExpenses;
    }


    public Double getGeneralAdminExp() {
        return generalAdminExp;
    }


    public void setGeneralAdminExp(Double generalAdminExp) {
        this.generalAdminExp = generalAdminExp;
    }


    public Double getSellingAndDistributionExpenses() {
        return sellingAndDistributionExpenses;
    }


    public void setSellingAndDistributionExpenses(Double sellingAndDistributionExpenses) {
        this.sellingAndDistributionExpenses = sellingAndDistributionExpenses;
    }


    public Double getMiscellaneousExpenses() {
        return miscellaneousExpenses;
    }


    public void setMiscellaneousExpenses(Double miscellaneousExpenses) {
        this.miscellaneousExpenses = miscellaneousExpenses;
    }


    public Double getExpensesAmortised() {
        return expensesAmortised;
    }


    public void setExpensesAmortised(Double expensesAmortised) {
        this.expensesAmortised = expensesAmortised;
    }


    public Double getTotalExpenditure() {
        return totalExpenditure;
    }


    public void setTotalExpenditure(Double totalExpenditure) {
        this.totalExpenditure = totalExpenditure;
    }


    public Double getOperatingProfitOI() {
        return operatingProfitOI;
    }


    public void setOperatingProfitOI(Double operatingProfitOI) {
        this.operatingProfitOI = operatingProfitOI;
    }


    public Double getAddOtherRevenueIncome() {
        return addOtherRevenueIncome;
    }


    public void setAddOtherRevenueIncome(Double addOtherRevenueIncome) {
        this.addOtherRevenueIncome = addOtherRevenueIncome;
    }


    public Double getOperatingProfitEBITDA() {
        return operatingProfitEBITDA;
    }


    public void setOperatingProfitEBITDA(Double operatingProfitEBITDA) {
        this.operatingProfitEBITDA = operatingProfitEBITDA;
    }


    public Double getInterest() {
        return interest;
    }


    public void setInterest(Double interest) {
        this.interest = interest;
    }


    public Double getPbdt() {
        return pbdt;
    }


    public void setPbdt(Double pbdt) {
        this.pbdt = pbdt;
    }


    public Double getDepreciation() {
        return depreciation;
    }


    public void setDepreciation(Double depreciation) {
        this.depreciation = depreciation;
    }


    public Double getProfitBeforeTaxAndExpItems() {
        return profitBeforeTaxAndExpItems;
    }


    public void setProfitBeforeTaxAndExpItems(Double profitBeforeTaxAndExpItems) {
        this.profitBeforeTaxAndExpItems = profitBeforeTaxAndExpItems;
    }


    public Double getExceptionalIncomeExp() {
        return exceptionalIncomeExp;
    }


    public void setExceptionalIncomeExp(Double exceptionalIncomeExp) {
        this.exceptionalIncomeExp = exceptionalIncomeExp;
    }


    public Double getProfitBeforeTax() {
        return profitBeforeTax;
    }


    public void setProfitBeforeTax(Double profitBeforeTax) {
        this.profitBeforeTax = profitBeforeTax;
    }


    public Double getProvisionForTaxes() {
        return provisionForTaxes;
    }


    public void setProvisionForTaxes(Double provisionForTaxes) {
        this.provisionForTaxes = provisionForTaxes;
    }


    public Double getProfitAfterTax() {
        return profitAfterTax;
    }


    public void setProfitAfterTax(Double profitAfterTax) {
        this.profitAfterTax = profitAfterTax;
    }


    public Double getDividendRate() {
        return dividendRate;
    }


    public void setDividendRate(Double dividendRate) {
        this.dividendRate = dividendRate;
    }


    public Double getEquityDividend() {
        return equityDividend;
    }


    public void setEquityDividend(Double equityDividend) {
        this.equityDividend = equityDividend;
    }


    public Double getEarningsPerShare() {
        return earningsPerShare;
    }


    public void setEarningsPerShare(Double earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }


    public Double getEmployeeCost() {
        return employeeCost;
    }


    public void setEmployeeCost(Double employeeCost) {
        this.employeeCost = employeeCost;
    }


    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Boolean getIsActive() {
        return isActive;
    }


    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public Double getOtherIncome() {
        return otherIncome;
    }


    public void setOtherIncome(Double otherIncome) {
        this.otherIncome = otherIncome;
    }


    public Double getOtherIncomeNeedTocCheckOp() {
        return otherIncomeNeedTocCheckOp;
    }


    public void setOtherIncomeNeedTocCheckOp(Double otherIncomeNeedTocCheckOp) {
        this.otherIncomeNeedTocCheckOp = otherIncomeNeedTocCheckOp;
    }
    public String getSubTotalOfIncomeStr() {
        return subTotalOfIncomeStr;
    }

    public void setSubTotalOfIncomeStr(String subTotalOfIncomeStr) {
        this.subTotalOfIncomeStr = subTotalOfIncomeStr;
    }

    public String getTotalGrossSalesStr() {
        return totalGrossSalesStr;
    }

    public void setTotalGrossSalesStr(String totalGrossSalesStr) {
        this.totalGrossSalesStr = totalGrossSalesStr;
    }

    public String getLessExciseDutyStr() {
        return lessExciseDutyStr;
    }

    public void setLessExciseDutystr(String lessExciseDutystr) {
        this.lessExciseDutyStr = lessExciseDutystr;
    }

    public String getNetSalesStr() {
        return netSalesStr;
    }

    public void setNetSalesStr(String netSalesStr) {
        this.netSalesStr = netSalesStr;
    }

    public String getInDecStockStr() {
        return inDecStockStr;
    }

    public void setInDecStockStr(String inDecStockStr) {
        this.inDecStockStr = inDecStockStr;
    }

    public String getRawMaterialsStr() {
        return rawMaterialsStr;
    }

    public void setRawMaterialsStr(String rawMaterialsStr) {
        this.rawMaterialsStr = rawMaterialsStr;
    }

    public String getPowerAndFuelStr() {
        return powerAndFuelStr;
    }

    public void setPowerAndFuelStr(String powerAndFuelStr) {
        this.powerAndFuelStr = powerAndFuelStr;
    }

    public String getOtherMfgExpensesStr() {
        return otherMfgExpensesStr;
    }

    public void setOtherMfgExpensesStr(String otherMfgExpensesStr) {
        this.otherMfgExpensesStr = otherMfgExpensesStr;
    }

    public String getGeneralAdminExpStr() {
        return generalAdminExpStr;
    }

    public void setGeneralAdminExpStr(String generalAdminExpStr) {
        this.generalAdminExpStr = generalAdminExpStr;
    }

    public String getSellingAndDistributionExpensesStr() {
        return sellingAndDistributionExpensesStr;
    }

    public void setSellingAndDistributionExpensesStr(String sellingAndDistributionExpensesStr) {
        this.sellingAndDistributionExpensesStr = sellingAndDistributionExpensesStr;
    }

    public String getMiscellaneousExpensesStr() {
        return miscellaneousExpensesStr;
    }

    public void setMiscellaneousExpensesStr(String miscellaneousExpensesStr) {
        this.miscellaneousExpensesStr = miscellaneousExpensesStr;
    }

    public String getExpensesAmortisedStr() {
        return expensesAmortisedStr;
    }

    public void setExpensesAmortisedStr(String expensesAmortisedStr) {
        this.expensesAmortisedStr = expensesAmortisedStr;
    }

    public String getTotalExpenditureStr() {
        return totalExpenditureStr;
    }

    public void setTotalExpenditureStr(String totalExpenditureStr) {
        this.totalExpenditureStr = totalExpenditureStr;
    }

    public String getOperatingProfitOIStr() {
        return operatingProfitOIStr;
    }

    public void setOperatingProfitOIStr(String operatingProfitOIStr) {
        this.operatingProfitOIStr = operatingProfitOIStr;
    }

    public String getAddOtherRevenueIncomeStr() {
        return addOtherRevenueIncomeStr;
    }

    public void setAddOtherRevenueIncomeStr(String addOtherRevenueIncomeStr) {
        this.addOtherRevenueIncomeStr = addOtherRevenueIncomeStr;
    }

    public String getOperatingProfitEBITDAStr() {
        return operatingProfitEBITDAStr;
    }

    public void setOperatingProfitEBITDAStr(String operatingProfitEBITDAStr) {
        this.operatingProfitEBITDAStr = operatingProfitEBITDAStr;
    }

    public String getInterestStr() {
        return interestStr;
    }

    public void setInterestStr(String interestStr) {
        this.interestStr = interestStr;
    }

    public String getPbdtStr() {
        return pbdtStr;
    }

    public void setPbdtStr(String pbdtStr) {
        this.pbdtStr = pbdtStr;
    }

    public String getDepreciationStr() {
        return depreciationStr;
    }

    public void setDepreciationStr(String depreciationStr) {
        this.depreciationStr = depreciationStr;
    }

    public String getProfitBeforeTaxAndExpItemsStr() {
        return profitBeforeTaxAndExpItemsStr;
    }

    public void setProfitBeforeTaxAndExpItemsStr(String profitBeforeTaxAndExpItemsStr) {
        this.profitBeforeTaxAndExpItemsStr = profitBeforeTaxAndExpItemsStr;
    }

    public String getExceptionalIncomeExpStr() {
        return exceptionalIncomeExpStr;
    }

    public void setExceptionalIncomeExpStr(String exceptionalIncomeExpStr) {
        this.exceptionalIncomeExpStr = exceptionalIncomeExpStr;
    }

    public String getProfitBeforeTaxStr() {
        return profitBeforeTaxStr;
    }

    public void setProfitBeforeTaxStr(String profitBeforeTaxStr) {
        this.profitBeforeTaxStr = profitBeforeTaxStr;
    }

    public String getProvisionForTaxesStr() {
        return provisionForTaxesStr;
    }

    public void setProvisionForTaxesStr(String provisionForTaxesStr) {
        this.provisionForTaxesStr = provisionForTaxesStr;
    }

    public String getProfitAfterTaxStr() {
        return profitAfterTaxStr;
    }

    public void setProfitAfterTaxStr(String profitAfterTaxStr) {
        this.profitAfterTaxStr = profitAfterTaxStr;
    }

    public String getDividendRateStr() {
        return dividendRateStr;
    }

    public void setDividendRateStr(String dividendRateStr) {
        this.dividendRateStr = dividendRateStr;
    }

    public String getEquityDividendStr() {
        return equityDividendStr;
    }

    public void setEquityDividendStr(String equityDividendStr) {
        this.equityDividendStr = equityDividendStr;
    }

    public String getEarningsPerShareStr() {
        return earningsPerShareStr;
    }

    public void setEarningsPerShareStr(String earningsPerShareStr) {
        this.earningsPerShareStr = earningsPerShareStr;
    }

    public String getEmployeeCostStr() {
        return employeeCostStr;
    }

    public void setEmployeeCostStr(String employeeCostStr) {
        this.employeeCostStr = employeeCostStr;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getOtherIncomeStr() {
        return otherIncomeStr;
    }

    public void setOtherIncomeStr(String otherIncomeStr) {
        this.otherIncomeStr = otherIncomeStr;
    }

    public String getOtherIncomeNeedTocCheckOpStr() {
        return otherIncomeNeedTocCheckOpStr;
    }

    public void setOtherIncomeNeedTocCheckOpStr(String otherIncomeNeedTocCheckOpStr) {
        this.otherIncomeNeedTocCheckOpStr = otherIncomeNeedTocCheckOpStr;
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
                + ", otherIncome=" + otherIncome + ", otherIncomeNeedTocCheckOp=" + otherIncomeNeedTocCheckOp + "]";
    }


}
