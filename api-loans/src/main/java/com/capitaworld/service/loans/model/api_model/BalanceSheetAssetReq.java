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

	private Double otherNcaOtherCapitalWorkInprogress;

	private Double intangibleAssets;

	private Double othersPreOperativeExpensesPending;

	private Double othersAssetsInTransit;

	private Double investmentsInSubsidiary;

	private Double otherInvestments;

	private Double advanceToSuppliersCapitalGoods;

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

	private Date createdDate;

	private Boolean isActive;

	private Double otherAssets;

	private Double currentRatioAsPerCma;

	public BalanceSheetAssetReq() {
		super();
		this.grossBlock = 0.0;
		this.depreciationToDate = 0.0;
		this.impairmentAsset = 0.0;
		this.netBlock = 0.0;
		this.otherNcaOtherCapitalWorkInprogress = 0.0;
		this.intangibleAssets = 0.0;
		this.othersPreOperativeExpensesPending = 0.0;
		this.othersAssetsInTransit = 0.0;
		this.investmentsInSubsidiary = 0.0;
		this.otherInvestments = 0.0;
		this.advanceToSuppliersCapitalGoods = 0.0;
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

	public Double getGrossBlock() {
		return grossBlock;
	}

	/**
	 * @param grossBlock the grossBlock to set
	 */
	public void setGrossBlock(Double grossBlock) {
		this.grossBlock = grossBlock;
	}

	public Double getDepreciationToDate() {
		return depreciationToDate;
	}

	/**
	 * @param depreciationToDate the depreciationToDate to set
	 */
	public void setDepreciationToDate(Double depreciationToDate) {
		this.depreciationToDate = depreciationToDate;
	}

	public Double getImpairmentAsset() {
		return impairmentAsset;
	}

	/**
	 * @param impairmentAsset the impairmentAsset to set
	 */
	public void setImpairmentAsset(Double impairmentAsset) {
		this.impairmentAsset = impairmentAsset;
	}

	public Double getNetBlock() {
		return netBlock;
	}

	/**
	 * @param netBlock the netBlock to set
	 */
	public void setNetBlock(Double netBlock) {
		this.netBlock = netBlock;
	}

	public Double getOtherNcaOtherCapitalWorkInprogress() {
		return otherNcaOtherCapitalWorkInprogress;
	}

	/**
	 * @param otherNcaOtherCapitalWorkInprogress the
	 *                                           otherNcaOtherCapitalWorkInprogress
	 *                                           to set
	 */
	public void setOtherNcaOtherCapitalWorkInprogress(Double otherNcaOtherCapitalWorkInprogress) {
		this.otherNcaOtherCapitalWorkInprogress = otherNcaOtherCapitalWorkInprogress;
	}

	public Double getIntangibleAssets() {
		return intangibleAssets;
	}

	/**
	 * @param intangibleAssets the intangibleAssets to set
	 */
	public void setIntangibleAssets(Double intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public Double getOthersPreOperativeExpensesPending() {
		return othersPreOperativeExpensesPending;
	}

	/**
	 * @param othersPreOperativeExpensesPending the
	 *                                          othersPreOperativeExpensesPending to
	 *                                          set
	 */
	public void setOthersPreOperativeExpensesPending(Double othersPreOperativeExpensesPending) {
		this.othersPreOperativeExpensesPending = othersPreOperativeExpensesPending;
	}

	public Double getOthersAssetsInTransit() {
		return othersAssetsInTransit;
	}

	/**
	 * @param othersAssetsInTransit the othersAssetsInTransit to set
	 */
	public void setOthersAssetsInTransit(Double othersAssetsInTransit) {
		this.othersAssetsInTransit = othersAssetsInTransit;
	}

	public Double getInvestmentsInSubsidiary() {
		return investmentsInSubsidiary;
	}

	/**
	 * @param investmentsInSubsidiary the investmentsInSubsidiary to set
	 */
	public void setInvestmentsInSubsidiary(Double investmentsInSubsidiary) {
		this.investmentsInSubsidiary = investmentsInSubsidiary;
	}

	public Double getOtherInvestments() {
		return otherInvestments;
	}

	/**
	 * @param otherInvestments the otherInvestments to set
	 */
	public void setOtherInvestments(Double otherInvestments) {
		this.otherInvestments = otherInvestments;
	}

	public Double getAdvanceToSuppliersCapitalGoods() {
		return advanceToSuppliersCapitalGoods;
	}

	/**
	 * @param advanceToSuppliersCapitalGoods the advanceToSuppliersCapitalGoods to
	 *                                       set
	 */
	public void setAdvanceToSuppliersCapitalGoods(Double advanceToSuppliersCapitalGoods) {
		this.advanceToSuppliersCapitalGoods = advanceToSuppliersCapitalGoods;
	}

	public Double getOtherNonCurrentAssets() {
		return otherNonCurrentAssets;
	}

	/**
	 * @param otherNonCurrentAssets the otherNonCurrentAssets to set
	 */
	public void setOtherNonCurrentAssets(Double otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}

	public Double getTotalNonCurrentAssets() {
		return totalNonCurrentAssets;
	}

	/**
	 * @param totalNonCurrentAssets the totalNonCurrentAssets to set
	 */
	public void setTotalNonCurrentAssets(Double totalNonCurrentAssets) {
		this.totalNonCurrentAssets = totalNonCurrentAssets;
	}

	public Double getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	public Double getSundryDebtors() {
		return sundryDebtors;
	}

	/**
	 * @param sundryDebtors the sundryDebtors to set
	 */
	public void setSundryDebtors(Double sundryDebtors) {
		this.sundryDebtors = sundryDebtors;
	}

	public Double getCashAndBankBalance() {
		return cashAndBankBalance;
	}

	/**
	 * @param cashAndBankBalance the cashAndBankBalance to set
	 */
	public void setCashAndBankBalance(Double cashAndBankBalance) {
		this.cashAndBankBalance = cashAndBankBalance;
	}

	public Double getOtherCurrentAssets() {
		return otherCurrentAssets;
	}

	/**
	 * @param otherCurrentAssets the otherCurrentAssets to set
	 */
	public void setOtherCurrentAssets(Double otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	public Double getShortTermLoansandAdvances() {
		return shortTermLoansandAdvances;
	}

	/**
	 * @param shortTermLoansandAdvances the shortTermLoansandAdvances to set
	 */
	public void setShortTermLoansandAdvances(Double shortTermLoansandAdvances) {
		this.shortTermLoansandAdvances = shortTermLoansandAdvances;
	}

	public Double getTotalCurrentAssets() {
		return totalCurrentAssets;
	}

	/**
	 * @param totalCurrentAssets the totalCurrentAssets to set
	 */
	public void setTotalCurrentAssets(Double totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}

	public Double getTotalAssets() {
		return totalAssets;
	}

	/**
	 * @param totalAssets the totalAssets to set
	 */
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}

	public Double getContingentLiabilities() {
		return contingentLiabilities;
	}

	/**
	 * @param contingentLiabilities the contingentLiabilities to set
	 */
	public void setContingentLiabilities(Double contingentLiabilities) {
		this.contingentLiabilities = contingentLiabilities;
	}

	public Double getBookValue() {
		return bookValue;
	}

	/**
	 * @param bookValue the bookValue to set
	 */
	public void setBookValue(Double bookValue) {
		this.bookValue = bookValue;
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

	@Override
	public String toString() {
		return "BalanceSheetAssetReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId + ", currency="
				+ currency + ", grossBlock=" + grossBlock + ", depreciationToDate=" + depreciationToDate
				+ ", impairmentAsset=" + impairmentAsset + ", netBlock=" + netBlock
				+ ", otherNcaOtherCapitalWorkInprogress=" + otherNcaOtherCapitalWorkInprogress + ", intangibleAssets="
				+ intangibleAssets + ", othersPreOperativeExpensesPending=" + othersPreOperativeExpensesPending
				+ ", othersAssetsInTransit=" + othersAssetsInTransit + ", investmentsInSubsidiary="
				+ investmentsInSubsidiary + ", otherInvestments=" + otherInvestments
				+ ", advanceToSuppliersCapitalGoods=" + advanceToSuppliersCapitalGoods + ", otherNonCurrentAssets="
				+ otherNonCurrentAssets + ", totalNonCurrentAssets=" + totalNonCurrentAssets + ", inventory="
				+ inventory + ", sundryDebtors=" + sundryDebtors + ", cashAndBankBalance=" + cashAndBankBalance
				+ ", otherCurrentAssets=" + otherCurrentAssets + ", shortTermLoansandAdvances="
				+ shortTermLoansandAdvances + ", totalCurrentAssets=" + totalCurrentAssets + ", totalAssets="
				+ totalAssets + ", contingentLiabilities=" + contingentLiabilities + ", bookValue=" + bookValue
				+ ", createdDate=" + createdDate + ", isActive=" + isActive + ", otherAssets=" + otherAssets + "]";
	}
}
