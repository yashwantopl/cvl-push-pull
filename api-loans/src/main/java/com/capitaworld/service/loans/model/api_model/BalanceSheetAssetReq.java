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
	private Double depreciationToDate;
	private Double impairmentAsset;
	private Double netBlock;
	private Double capitalWorkInprogress;
	private Double intangibleAssets;
	private Double preOperativeExpensesPending;
	private Double assetsInTransit;
	private Double investmentsInSubsidiary;
	private Double otherInvestments;
	private Double longTermLoansAndAdvances;
	private Double otherNonCurrentAssets;
	private Double totalNonCurrentAssets;
	private Double inventory;
	private Double sundryDebtors;
	private Double cashAndBankBalance;
	private Double otherCurrentAssets;
	private Double shortTermLoansandAdvances;
	private Double totalCurrentAssets;
	private Double totalAssets;
	private Double contingentLiabilities;
	private Double bookValue;
	private Double otherAssets;
	private Double currentRatioAsPerCma;
	private Double otherIncomeNeedTocCheckAsset;

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
