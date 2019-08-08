package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceSheetAssetReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String year;
    private Long applicationId;
    private String currency;

    private Double grossBlock;
    private String grossBlockStr;

    private Double depreciationToDate;
    private String depreciationToDateStr;

    private Double impairmentAsset;
    private String impairmentAssetStr;

    private Double netBlock;
    private String netBlockStr;

    private Double capitalWorkInprogress;
    private String capitalWorkInprogressStr;

    private Double intangibleAssets;
    private String intangibleAssetsStr;

    private Double preOperativeExpensesPending;
    private String preOperativeExpensesPendingStr;

    private Double assetsInTransit;
    private String assetsInTransitStr;

    private Double investmentsInSubsidiary;
    private String investmentsInSubsidiaryStr;

    private Double otherInvestments;
    private String otherInvestmentsStr;

    private Double longTermLoansAndAdvances;
    private String longTermLoansAndAdvancesStr;

    private Double otherNonCurrentAssets;
    private String otherNonCurrentAssetsStr;

    private Double totalNonCurrentAssets;
    private String totalNonCurrentAssetsStr;

    private Double inventory;
    private String inventoryStr;

    private Double sundryDebtors;
    private String sundryDebtorsStr;

    private Double cashAndBankBalance;
    private String cashAndBankBalanceStr;

    private Double otherCurrentAssets;
    private String otherCurrentAssetsStr;

    private Double shortTermLoansandAdvances;
    private String shortTermLoansandAdvancesStr;

    private Double totalCurrentAssets;
    private String totalCurrentAssetsStr;

    private Double totalAssets;
    private String totalAssetsStr;

    private Double contingentLiabilities;
    private String contingentLiabilitiesStr;

    private Double bookValue;
    private String bookValueStr;

    private Double otherAssets;
    private String otherAssetsStr;

    private Double currentRatioAsPerCma;
    private String currentRatioAsPerCmaStr;

    private Double otherIncomeNeedTocCheckAsset;
    private String otherIncomeNeedTocCheckAssetStr;

    private Date createdDate;
    private Boolean isActive;


    public BalanceSheetAssetReq() {
        super();
        this.grossBlock = 0.0;
        this.depreciationToDate = 0.0;
        this.impairmentAsset = 0.0;
        this.netBlock = 0.0;
        this.capitalWorkInprogress = 0.0;
        this.intangibleAssets = 0.0;
        this.preOperativeExpensesPending = 0.0;
        this.assetsInTransit = 0.0;
        this.investmentsInSubsidiary = 0.0;
        this.otherInvestments = 0.0;
        this.longTermLoansAndAdvances = 0.0;
        this.otherNonCurrentAssets = 0.0;
        this.totalNonCurrentAssets = 0.0;
        this.inventory = 0.0;
        this.sundryDebtors = 0.0;
        this.cashAndBankBalance = 0.0;
        this.otherCurrentAssets = 0.0;
        this.shortTermLoansandAdvances = 0.0;
        this.totalCurrentAssets = 0.0;
        this.totalAssets = 0.0;
        this.contingentLiabilities = 0.0;
        this.bookValue = 0.0;
        this.otherAssets = 0.0;
        this.currentRatioAsPerCma = 0.0;
        this.otherIncomeNeedTocCheckAsset = 0.0;
        this.grossBlockStr = "0.0";
        this.depreciationToDateStr = "0.0";
        this.impairmentAssetStr = "0.0";
        this.netBlockStr = "0.0";
        this.capitalWorkInprogressStr = "0.0";
        this.intangibleAssetsStr = "0.0";
        this.preOperativeExpensesPendingStr = "0.0";
        this.assetsInTransitStr = "0.0";
        this.investmentsInSubsidiaryStr = "0.0";
        this.otherInvestmentsStr = "0.0";
        this.longTermLoansAndAdvancesStr = "0.0";
        this.otherNonCurrentAssetsStr = "0.0";
        this.totalNonCurrentAssetsStr = "0.0";
        this.inventoryStr = "0.0";
        this.sundryDebtorsStr = "0.0";
        this.cashAndBankBalanceStr = "0.0";
        this.otherCurrentAssetsStr = "0.0";
        this.shortTermLoansandAdvancesStr = "0.0";
        this.totalCurrentAssetsStr = "0.0";
        this.totalAssetsStr = "0.0";
        this.contingentLiabilitiesStr = "0.0";
        this.bookValueStr = "0.0";
        this.otherAssetsStr = "0.0";
        this.currentRatioAsPerCmaStr = "0.0";
        this.otherIncomeNeedTocCheckAssetStr = "0.0";
    }


    public String getGrossBlockStr() {
        return grossBlockStr;
    }

    public void setGrossBlockStr(String grossBlockStr) {
        this.grossBlockStr = grossBlockStr;
    }

    public String getDepreciationToDateStr() {
        return depreciationToDateStr;
    }

    public void setDepreciationToDateStr(String depreciationToDateStr) {
        this.depreciationToDateStr = depreciationToDateStr;
    }

    public String getImpairmentAssetStr() {
        return impairmentAssetStr;
    }

    public void setImpairmentAssetStr(String impairmentAssetStr) {
        this.impairmentAssetStr = impairmentAssetStr;
    }

    public String getNetBlockStr() {
        return netBlockStr;
    }

    public void setNetBlockStr(String netBlockStr) {
        this.netBlockStr = netBlockStr;
    }

    public String getCapitalWorkInprogressStr() {
        return capitalWorkInprogressStr;
    }

    public void setCapitalWorkInprogressStr(String capitalWorkInprogressStr) {
        this.capitalWorkInprogressStr = capitalWorkInprogressStr;
    }

    public String getIntangibleAssetsStr() {
        return intangibleAssetsStr;
    }

    public void setIntangibleAssetsStr(String intangibleAssetsStr) {
        this.intangibleAssetsStr = intangibleAssetsStr;
    }

    public String getPreOperativeExpensesPendingStr() {
        return preOperativeExpensesPendingStr;
    }

    public void setPreOperativeExpensesPendingStr(String preOperativeExpensesPendingStr) {
        this.preOperativeExpensesPendingStr = preOperativeExpensesPendingStr;
    }

    public String getAssetsInTransitStr() {
        return assetsInTransitStr;
    }

    public void setAssetsInTransitStr(String assetsInTransitStr) {
        this.assetsInTransitStr = assetsInTransitStr;
    }

    public String getInvestmentsInSubsidiaryStr() {
        return investmentsInSubsidiaryStr;
    }

    public void setInvestmentsInSubsidiaryStr(String investmentsInSubsidiaryStr) {
        this.investmentsInSubsidiaryStr = investmentsInSubsidiaryStr;
    }

    public String getOtherInvestmentsStr() {
        return otherInvestmentsStr;
    }

    public void setOtherInvestmentsStr(String otherInvestmentsStr) {
        this.otherInvestmentsStr = otherInvestmentsStr;
    }

    public String getLongTermLoansAndAdvancesStr() {
        return longTermLoansAndAdvancesStr;
    }

    public void setLongTermLoansAndAdvancesStr(String longTermLoansAndAdvancesStr) {
        this.longTermLoansAndAdvancesStr = longTermLoansAndAdvancesStr;
    }

    public String getOtherNonCurrentAssetsStr() {
        return otherNonCurrentAssetsStr;
    }

    public void setOtherNonCurrentAssetsStr(String otherNonCurrentAssetsStr) {
        this.otherNonCurrentAssetsStr = otherNonCurrentAssetsStr;
    }

    public String getTotalNonCurrentAssetsStr() {
        return totalNonCurrentAssetsStr;
    }

    public void setTotalNonCurrentAssetsStr(String totalNonCurrentAssetsStr) {
        this.totalNonCurrentAssetsStr = totalNonCurrentAssetsStr;
    }

    public String getInventoryStr() {
        return inventoryStr;
    }

    public void setInventoryStr(String inventoryStr) {
        this.inventoryStr = inventoryStr;
    }

    public String getSundryDebtorsStr() {
        return sundryDebtorsStr;
    }

    public void setSundryDebtorsStr(String sundryDebtorsStr) {
        this.sundryDebtorsStr = sundryDebtorsStr;
    }

    public String getCashAndBankBalanceStr() {
        return cashAndBankBalanceStr;
    }

    public void setCashAndBankBalanceStr(String cashAndBankBalanceStr) {
        this.cashAndBankBalanceStr = cashAndBankBalanceStr;
    }

    public String getOtherCurrentAssetsStr() {
        return otherCurrentAssetsStr;
    }

    public void setOtherCurrentAssetsStr(String otherCurrentAssetsStr) {
        this.otherCurrentAssetsStr = otherCurrentAssetsStr;
    }

    public String getShortTermLoansandAdvancesStr() {
        return shortTermLoansandAdvancesStr;
    }

    public void setShortTermLoansandAdvancesStr(String shortTermLoansandAdvancesStr) {
        this.shortTermLoansandAdvancesStr = shortTermLoansandAdvancesStr;
    }

    public String getTotalCurrentAssetsStr() {
        return totalCurrentAssetsStr;
    }

    public void setTotalCurrentAssetsStr(String totalCurrentAssetsStr) {
        this.totalCurrentAssetsStr = totalCurrentAssetsStr;
    }

    public String getTotalAssetsStr() {
        return totalAssetsStr;
    }

    public void setTotalAssetsStr(String totalAssetsStr) {
        this.totalAssetsStr = totalAssetsStr;
    }

    public String getContingentLiabilitiesStr() {
        return contingentLiabilitiesStr;
    }

    public void setContingentLiabilitiesStr(String contingentLiabilitiesStr) {
        this.contingentLiabilitiesStr = contingentLiabilitiesStr;
    }

    public String getBookValueStr() {
        return bookValueStr;
    }

    public void setBookValueStr(String bookValueStr) {
        this.bookValueStr = bookValueStr;
    }

    public String getOtherAssetsStr() {
        return otherAssetsStr;
    }

    public void setOtherAssetsStr(String otherAssetsStr) {
        this.otherAssetsStr = otherAssetsStr;
    }

    public String getCurrentRatioAsPerCmaStr() {
        return currentRatioAsPerCmaStr;
    }

    public void setCurrentRatioAsPerCmaStr(String currentRatioAsPerCmaStr) {
        this.currentRatioAsPerCmaStr = currentRatioAsPerCmaStr;
    }

    public String getOtherIncomeNeedTocCheckAssetStr() {
        return otherIncomeNeedTocCheckAssetStr;
    }

    public void setOtherIncomeNeedTocCheckAssetStr(String otherIncomeNeedTocCheckAssetStr) {
        this.otherIncomeNeedTocCheckAssetStr = otherIncomeNeedTocCheckAssetStr;
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

    public Double getGrossBlock() {
        return grossBlock;
    }

    public void setGrossBlock(Double grossBlock) {
        this.grossBlock = grossBlock;
    }

    public Double getDepreciationToDate() {
        return depreciationToDate;
    }

    public void setDepreciationToDate(Double depreciationToDate) {
        this.depreciationToDate = depreciationToDate;
    }

    public Double getImpairmentAsset() {
        return impairmentAsset;
    }

    public void setImpairmentAsset(Double impairmentAsset) {
        this.impairmentAsset = impairmentAsset;
    }

    public Double getNetBlock() {
        return netBlock;
    }

    public void setNetBlock(Double netBlock) {
        this.netBlock = netBlock;
    }

    public Double getCapitalWorkInprogress() {
        return capitalWorkInprogress;
    }

    public void setCapitalWorkInprogress(Double capitalWorkInprogress) {
        this.capitalWorkInprogress = capitalWorkInprogress;
    }

    public Double getIntangibleAssets() {
        return intangibleAssets;
    }

    public void setIntangibleAssets(Double intangibleAssets) {
        this.intangibleAssets = intangibleAssets;
    }

    public Double getPreOperativeExpensesPending() {
        return preOperativeExpensesPending;
    }

    public void setPreOperativeExpensesPending(Double preOperativeExpensesPending) {
        this.preOperativeExpensesPending = preOperativeExpensesPending;
    }

    public Double getAssetsInTransit() {
        return assetsInTransit;
    }

    public void setAssetsInTransit(Double assetsInTransit) {
        this.assetsInTransit = assetsInTransit;
    }

    public Double getInvestmentsInSubsidiary() {
        return investmentsInSubsidiary;
    }

    public void setInvestmentsInSubsidiary(Double investmentsInSubsidiary) {
        this.investmentsInSubsidiary = investmentsInSubsidiary;
    }

    public Double getOtherInvestments() {
        return otherInvestments;
    }

    public void setOtherInvestments(Double otherInvestments) {
        this.otherInvestments = otherInvestments;
    }

    public Double getLongTermLoansAndAdvances() {
        return longTermLoansAndAdvances;
    }

    public void setLongTermLoansAndAdvances(Double longTermLoansAndAdvances) {
        this.longTermLoansAndAdvances = longTermLoansAndAdvances;
    }

    public Double getOtherNonCurrentAssets() {
        return otherNonCurrentAssets;
    }

    public void setOtherNonCurrentAssets(Double otherNonCurrentAssets) {
        this.otherNonCurrentAssets = otherNonCurrentAssets;
    }

    public Double getTotalNonCurrentAssets() {
        return totalNonCurrentAssets;
    }

    public void setTotalNonCurrentAssets(Double totalNonCurrentAssets) {
        this.totalNonCurrentAssets = totalNonCurrentAssets;
    }

    public Double getInventory() {
        return inventory;
    }

    public void setInventory(Double inventory) {
        this.inventory = inventory;
    }

    public Double getSundryDebtors() {
        return sundryDebtors;
    }

    public void setSundryDebtors(Double sundryDebtors) {
        this.sundryDebtors = sundryDebtors;
    }

    public Double getCashAndBankBalance() {
        return cashAndBankBalance;
    }

    public void setCashAndBankBalance(Double cashAndBankBalance) {
        this.cashAndBankBalance = cashAndBankBalance;
    }

    public Double getOtherCurrentAssets() {
        return otherCurrentAssets;
    }

    public void setOtherCurrentAssets(Double otherCurrentAssets) {
        this.otherCurrentAssets = otherCurrentAssets;
    }

    public Double getShortTermLoansandAdvances() {
        return shortTermLoansandAdvances;
    }

    public void setShortTermLoansandAdvances(Double shortTermLoansandAdvances) {
        this.shortTermLoansandAdvances = shortTermLoansandAdvances;
    }

    public Double getTotalCurrentAssets() {
        return totalCurrentAssets;
    }

    public void setTotalCurrentAssets(Double totalCurrentAssets) {
        this.totalCurrentAssets = totalCurrentAssets;
    }

    public Double getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    public Double getContingentLiabilities() {
        return contingentLiabilities;
    }

    public void setContingentLiabilities(Double contingentLiabilities) {
        this.contingentLiabilities = contingentLiabilities;
    }

    public Double getBookValue() {
        return bookValue;
    }

    public void setBookValue(Double bookValue) {
        this.bookValue = bookValue;
    }

    public Double getOtherAssets() {
        return otherAssets;
    }

    public void setOtherAssets(Double otherAssets) {
        this.otherAssets = otherAssets;
    }

    public Double getCurrentRatioAsPerCma() {
        return currentRatioAsPerCma;
    }

    public void setCurrentRatioAsPerCma(Double currentRatioAsPerCma) {
        this.currentRatioAsPerCma = currentRatioAsPerCma;
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

    public Double getOtherIncomeNeedTocCheckAsset() {
        return otherIncomeNeedTocCheckAsset;
    }

    public void setOtherIncomeNeedTocCheckAsset(Double otherIncomeNeedTocCheckAsset) {
        this.otherIncomeNeedTocCheckAsset = otherIncomeNeedTocCheckAsset;
    }

    @Override
    public String toString() {
        return "BalanceSheetAssetReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId + ", currency="
                + currency + ", grossBlock=" + grossBlock + ", depreciationToDate=" + depreciationToDate
                + ", impairmentAsset=" + impairmentAsset + ", netBlock=" + netBlock
                + ", otherNcaOtherCapitalWorkInprogress=" + capitalWorkInprogress + ", intangibleAssets="
                + intangibleAssets + ", othersPreOperativeExpensesPending=" + preOperativeExpensesPending
                + ", othersAssetsInTransit=" + assetsInTransit + ", investmentsInSubsidiary=" + investmentsInSubsidiary
                + ", otherInvestments=" + otherInvestments + ", advanceToSuppliersCapitalGoods="
                + longTermLoansAndAdvances + ", otherNonCurrentAssets=" + otherNonCurrentAssets
                + ", totalNonCurrentAssets=" + totalNonCurrentAssets + ", inventory=" + inventory + ", sundryDebtors="
                + sundryDebtors + ", cashAndBankBalance=" + cashAndBankBalance + ", otherCurrentAssets="
                + otherCurrentAssets + ", shortTermLoansandAdvances=" + shortTermLoansandAdvances
                + ", totalCurrentAssets=" + totalCurrentAssets + ", totalAssets=" + totalAssets
                + ", contingentLiabilities=" + contingentLiabilities + ", bookValue=" + bookValue + ", createdDate="
                + createdDate + ", isActive=" + isActive + ", otherAssets=" + otherAssets + "]";
    }
}
