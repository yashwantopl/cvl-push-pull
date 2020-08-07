/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FinancialRatioHistories {
	
	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	@JsonProperty("revenue-growth")
	private String revenueGrowth;

	@JsonProperty("ebitda-margins-percent")
	private String ebitdaMarginsPercent;

	@JsonProperty("ebt-margins-percent")
	private String ebtMarginsPercentage;

	@JsonProperty("pat-margins-percent")
	private String patMarginsPercenage;

	@JsonProperty("employee-cost-percent-of-sales")
	private String employeeCostPercentOfSales;

	@JsonProperty("other-expenses-percent-of-sales")
	private String otherExpensePercentageOfSales;

	@JsonProperty("finance-cost-percent-of-sales")
	private String financeCostPercentageOfSales;

	@JsonProperty("raw-material-consumption")
	private String rawMaterialsConsuption;

	@JsonProperty("return-on-equity")
	private String returnOnEquity;

	@JsonProperty("current-ratio")
	private String curruntRatio;

	@JsonProperty("fixed-assets-turnover")
	private String fixedAssetsTurnover;

	@JsonProperty("working-capital-turnover")
	private String workingCapitalTurnover;

	@JsonProperty("receivables-days")
	private String receivablesDays;

	@JsonProperty("inventory-days")
	private String inventoryDays;

	@JsonProperty("long-term-debt-or-equity")
	private String longTermDebtOrEquity;

	@JsonProperty("interest-coverage")
	private String interestCoverage;

	@JsonProperty("total-assets-or-equity")
	private String totalAssetsOrQuity;

	@JsonProperty("total-debt-or-total-assets")
	private String totalDebtOrTotalAssets;

	@JsonProperty("quick-ratio")
	private String quickRatio;

	@JsonProperty("operating-margin")
	private String operatingMargins;

	@JsonProperty("return-on-fixed-assets")
	private String returnOnFixedAssets;

	@JsonProperty("return-on-capital-employed")
	private String returnOnCapitalEmployed;

	@JsonProperty("total-assets-turnover")
	private String totalAssetsTurnover;

	@JsonProperty("revenue-growth-str")
	private String revenueGrowthStr;

	@JsonProperty("ebitda-margins-percent-str")
	private String ebitdaMarginsPercentStr;

	@JsonProperty("ebt-margins-percent-str")
	private String ebtMarginsPercentStr;

	@JsonProperty("pat-margins-percent-str")
	private String patMarginsPercentStr;

	@JsonProperty("employee-cost-percent-of-sales-str")
	private String employeeCostPercentOfSalesStr;

	@JsonProperty("other-expenses-percent-of-sales-str")
	private String otherExpensesPercentOfSalesStr;

	@JsonProperty("finance-cost-percent-of-sales-str")
	private String financeCostPercentOfSalesStr;

	@JsonProperty("raw-material-consumption-str")
	private String rawMaterialConsumptionstr;

	@JsonProperty("return-on-equity-str")
	private String returnOnEquityStr;

	@JsonProperty("current-ratio-str")
	private String curruntRatioStr;

	@JsonProperty("fixed-assets-turnover-str")
	private String fixedAssetsTurnoverStr;

	@JsonProperty("working-capital-turnover-str")
	private String workingCapitalTurnoverStr;

	@JsonProperty("receivables-days-str")
	private String receivablesDaysStr;

	@JsonProperty("inventory-days-str")
	private String inventoryDaysStr;

	@JsonProperty("long-term-debt-or-equity-str")
	private String longTermDebtOrEquityStr;

	@JsonProperty("interest-coverage-str")
	private String interestCoverageStr;

	@JsonProperty("total-assets-or-equity-str")
	private String totalAssetsOrEquityStr;

	@JsonProperty("total-debt-or-total-assets-str")
	private String totalDebtOrTotalAssetsStr;

	@JsonProperty("quick-ratio-str")
	private String quickRatioStr;

	@JsonProperty("operating-margin-str")
	private String operatingMarginStr;

	@JsonProperty("return-on-fixed-assets-str")
	private String returnOnFixedAssetsStr;

	@JsonProperty("return-on-capital-employed-str")
	private String returnOnCapitalEmployedStr;

	@JsonProperty("total-assets-turnover-str")
	private String totalAssetsTurnoverStr;

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	/**
	 * @return the financialYear
	 */
	public String getFinancialYear() {
		return financialYear;
	}

	/**
	 * @param financialYear
	 *            the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return the revenueGrowth
	 */
	public String getRevenueGrowth() {
		return revenueGrowth;
	}

	/**
	 * @param revenueGrowth
	 *            the revenueGrowth to set
	 */
	public void setRevenueGrowth(String revenueGrowth) {
		this.revenueGrowth = revenueGrowth;
	}

	/**
	 * @return the ebitdaMarginsPercent
	 */
	public String getEbitdaMarginsPercent() {
		return ebitdaMarginsPercent;
	}

	/**
	 * @param ebitdaMarginsPercent
	 *            the ebitdaMarginsPercent to set
	 */
	public void setEbitdaMarginsPercent(String ebitdaMarginsPercent) {
		this.ebitdaMarginsPercent = ebitdaMarginsPercent;
	}

	/**
	 * @return the ebtMarginsPercentage
	 */
	public String getEbtMarginsPercentage() {
		return ebtMarginsPercentage;
	}

	/**
	 * @param ebtMarginsPercentage
	 *            the ebtMarginsPercentage to set
	 */
	public void setEbtMarginsPercentage(String ebtMarginsPercentage) {
		this.ebtMarginsPercentage = ebtMarginsPercentage;
	}

	/**
	 * @return the patMarginsPercenage
	 */
	public String getPatMarginsPercenage() {
		return patMarginsPercenage;
	}

	/**
	 * @param patMarginsPercenage
	 *            the patMarginsPercenage to set
	 */
	public void setPatMarginsPercenage(String patMarginsPercenage) {
		this.patMarginsPercenage = patMarginsPercenage;
	}

	/**
	 * @return the employeeCostPercentOfSales
	 */
	public String getEmployeeCostPercentOfSales() {
		return employeeCostPercentOfSales;
	}

	/**
	 * @param employeeCostPercentOfSales
	 *            the employeeCostPercentOfSales to set
	 */
	public void setEmployeeCostPercentOfSales(String employeeCostPercentOfSales) {
		this.employeeCostPercentOfSales = employeeCostPercentOfSales;
	}

	/**
	 * @return the otherExpensePercentageOfSales
	 */
	public String getOtherExpensePercentageOfSales() {
		return otherExpensePercentageOfSales;
	}

	/**
	 * @param otherExpensePercentageOfSales
	 *            the otherExpensePercentageOfSales to set
	 */
	public void setOtherExpensePercentageOfSales(String otherExpensePercentageOfSales) {
		this.otherExpensePercentageOfSales = otherExpensePercentageOfSales;
	}

	/**
	 * @return the financeCostPercentageOfSales
	 */
	public String getFinanceCostPercentageOfSales() {
		return financeCostPercentageOfSales;
	}

	/**
	 * @param financeCostPercentageOfSales
	 *            the financeCostPercentageOfSales to set
	 */
	public void setFinanceCostPercentageOfSales(String financeCostPercentageOfSales) {
		this.financeCostPercentageOfSales = financeCostPercentageOfSales;
	}

	/**
	 * @return the rawMaterialsConsuption
	 */
	public String getRawMaterialsConsuption() {
		return rawMaterialsConsuption;
	}

	/**
	 * @param rawMaterialsConsuption
	 *            the rawMaterialsConsuption to set
	 */
	public void setRawMaterialsConsuption(String rawMaterialsConsuption) {
		this.rawMaterialsConsuption = rawMaterialsConsuption;
	}

	/**
	 * @return the returnOnEquity
	 */
	public String getReturnOnEquity() {
		return returnOnEquity;
	}

	/**
	 * @param returnOnEquity
	 *            the returnOnEquity to set
	 */
	public void setReturnOnEquity(String returnOnEquity) {
		this.returnOnEquity = returnOnEquity;
	}

	/**
	 * @return the curruntRatio
	 */
	public String getCurruntRatio() {
		return curruntRatio;
	}

	/**
	 * @param curruntRatio
	 *            the curruntRatio to set
	 */
	public void setCurruntRatio(String curruntRatio) {
		this.curruntRatio = curruntRatio;
	}

	/**
	 * @return the fixedAssetsTurnover
	 */
	public String getFixedAssetsTurnover() {
		return fixedAssetsTurnover;
	}

	/**
	 * @param fixedAssetsTurnover
	 *            the fixedAssetsTurnover to set
	 */
	public void setFixedAssetsTurnover(String fixedAssetsTurnover) {
		this.fixedAssetsTurnover = fixedAssetsTurnover;
	}

	/**
	 * @return the workingCapitalTurnover
	 */
	public String getWorkingCapitalTurnover() {
		return workingCapitalTurnover;
	}

	/**
	 * @param workingCapitalTurnover
	 *            the workingCapitalTurnover to set
	 */
	public void setWorkingCapitalTurnover(String workingCapitalTurnover) {
		this.workingCapitalTurnover = workingCapitalTurnover;
	}

	/**
	 * @return the receivablesDays
	 */
	public String getReceivablesDays() {
		return receivablesDays;
	}

	/**
	 * @param receivablesDays
	 *            the receivablesDays to set
	 */
	public void setReceivablesDays(String receivablesDays) {
		this.receivablesDays = receivablesDays;
	}

	/**
	 * @return the inventoryDays
	 */
	public String getInventoryDays() {
		return inventoryDays;
	}

	/**
	 * @param inventoryDays
	 *            the inventoryDays to set
	 */
	public void setInventoryDays(String inventoryDays) {
		this.inventoryDays = inventoryDays;
	}

	/**
	 * @return the longTermDebtOrEquity
	 */
	public String getLongTermDebtOrEquity() {
		return longTermDebtOrEquity;
	}

	/**
	 * @param longTermDebtOrEquity
	 *            the longTermDebtOrEquity to set
	 */
	public void setLongTermDebtOrEquity(String longTermDebtOrEquity) {
		this.longTermDebtOrEquity = longTermDebtOrEquity;
	}

	/**
	 * @return the interestCoverage
	 */
	public String getInterestCoverage() {
		return interestCoverage;
	}

	/**
	 * @param interestCoverage
	 *            the interestCoverage to set
	 */
	public void setInterestCoverage(String interestCoverage) {
		this.interestCoverage = interestCoverage;
	}

	/**
	 * @return the totalAssetsOrQuity
	 */
	public String getTotalAssetsOrQuity() {
		return totalAssetsOrQuity;
	}

	/**
	 * @param totalAssetsOrQuity
	 *            the totalAssetsOrQuity to set
	 */
	public void setTotalAssetsOrQuity(String totalAssetsOrQuity) {
		this.totalAssetsOrQuity = totalAssetsOrQuity;
	}

	/**
	 * @return the totalDebtOrTotalAssets
	 */
	public String getTotalDebtOrTotalAssets() {
		return totalDebtOrTotalAssets;
	}

	/**
	 * @param totalDebtOrTotalAssets
	 *            the totalDebtOrTotalAssets to set
	 */
	public void setTotalDebtOrTotalAssets(String totalDebtOrTotalAssets) {
		this.totalDebtOrTotalAssets = totalDebtOrTotalAssets;
	}

	/**
	 * @return the quickRatio
	 */
	public String getQuickRatio() {
		return quickRatio;
	}

	/**
	 * @param quickRatio
	 *            the quickRatio to set
	 */
	public void setQuickRatio(String quickRatio) {
		this.quickRatio = quickRatio;
	}

	/**
	 * @return the operatingMargins
	 */
	public String getOperatingMargins() {
		return operatingMargins;
	}

	/**
	 * @param operatingMargins
	 *            the operatingMargins to set
	 */
	public void setOperatingMargins(String operatingMargins) {
		this.operatingMargins = operatingMargins;
	}

	/**
	 * @return the returnOnFixedAssets
	 */
	public String getReturnOnFixedAssets() {
		return returnOnFixedAssets;
	}

	/**
	 * @param returnOnFixedAssets
	 *            the returnOnFixedAssets to set
	 */
	public void setReturnOnFixedAssets(String returnOnFixedAssets) {
		this.returnOnFixedAssets = returnOnFixedAssets;
	}

	/**
	 * @return the returnOnCapitalEmployed
	 */
	public String getReturnOnCapitalEmployed() {
		return returnOnCapitalEmployed;
	}

	/**
	 * @param returnOnCapitalEmployed
	 *            the returnOnCapitalEmployed to set
	 */
	public void setReturnOnCapitalEmployed(String returnOnCapitalEmployed) {
		this.returnOnCapitalEmployed = returnOnCapitalEmployed;
	}

	/**
	 * @return the totalAssetsTurnover
	 */
	public String getTotalAssetsTurnover() {
		return totalAssetsTurnover;
	}

	/**
	 * @param totalAssetsTurnover
	 *            the totalAssetsTurnover to set
	 */
	public void setTotalAssetsTurnover(String totalAssetsTurnover) {
		this.totalAssetsTurnover = totalAssetsTurnover;
	}

	/**
	 * @return the revenueGrowthStr
	 */
	public String getRevenueGrowthStr() {
		return revenueGrowthStr;
	}

	/**
	 * @param revenueGrowthStr
	 *            the revenueGrowthStr to set
	 */
	public void setRevenueGrowthStr(String revenueGrowthStr) {
		this.revenueGrowthStr = revenueGrowthStr;
	}

	/**
	 * @return the ebitdaMarginsPercentStr
	 */
	public String getEbitdaMarginsPercentStr() {
		return ebitdaMarginsPercentStr;
	}

	/**
	 * @param ebitdaMarginsPercentStr
	 *            the ebitdaMarginsPercentStr to set
	 */
	public void setEbitdaMarginsPercentStr(String ebitdaMarginsPercentStr) {
		this.ebitdaMarginsPercentStr = ebitdaMarginsPercentStr;
	}

	/**
	 * @return the ebtMarginsPercentStr
	 */
	public String getEbtMarginsPercentStr() {
		return ebtMarginsPercentStr;
	}

	/**
	 * @param ebtMarginsPercentStr
	 *            the ebtMarginsPercentStr to set
	 */
	public void setEbtMarginsPercentStr(String ebtMarginsPercentStr) {
		this.ebtMarginsPercentStr = ebtMarginsPercentStr;
	}

	/**
	 * @return the patMarginsPercentStr
	 */
	public String getPatMarginsPercentStr() {
		return patMarginsPercentStr;
	}

	/**
	 * @param patMarginsPercentStr
	 *            the patMarginsPercentStr to set
	 */
	public void setPatMarginsPercentStr(String patMarginsPercentStr) {
		this.patMarginsPercentStr = patMarginsPercentStr;
	}

	/**
	 * @return the employeeCostPercentOfSalesStr
	 */
	public String getEmployeeCostPercentOfSalesStr() {
		return employeeCostPercentOfSalesStr;
	}

	/**
	 * @param employeeCostPercentOfSalesStr
	 *            the employeeCostPercentOfSalesStr to set
	 */
	public void setEmployeeCostPercentOfSalesStr(String employeeCostPercentOfSalesStr) {
		this.employeeCostPercentOfSalesStr = employeeCostPercentOfSalesStr;
	}

	/**
	 * @return the otherExpensesPercentOfSalesStr
	 */
	public String getOtherExpensesPercentOfSalesStr() {
		return otherExpensesPercentOfSalesStr;
	}

	/**
	 * @param otherExpensesPercentOfSalesStr
	 *            the otherExpensesPercentOfSalesStr to set
	 */
	public void setOtherExpensesPercentOfSalesStr(String otherExpensesPercentOfSalesStr) {
		this.otherExpensesPercentOfSalesStr = otherExpensesPercentOfSalesStr;
	}

	/**
	 * @return the financeCostPercentOfSalesStr
	 */
	public String getFinanceCostPercentOfSalesStr() {
		return financeCostPercentOfSalesStr;
	}

	/**
	 * @param financeCostPercentOfSalesStr
	 *            the financeCostPercentOfSalesStr to set
	 */
	public void setFinanceCostPercentOfSalesStr(String financeCostPercentOfSalesStr) {
		this.financeCostPercentOfSalesStr = financeCostPercentOfSalesStr;
	}

	/**
	 * @return the rawMaterialConsumptionstr
	 */
	public String getRawMaterialConsumptionstr() {
		return rawMaterialConsumptionstr;
	}

	/**
	 * @param rawMaterialConsumptionstr
	 *            the rawMaterialConsumptionstr to set
	 */
	public void setRawMaterialConsumptionstr(String rawMaterialConsumptionstr) {
		this.rawMaterialConsumptionstr = rawMaterialConsumptionstr;
	}

	/**
	 * @return the returnOnEquityStr
	 */
	public String getReturnOnEquityStr() {
		return returnOnEquityStr;
	}

	/**
	 * @param returnOnEquityStr
	 *            the returnOnEquityStr to set
	 */
	public void setReturnOnEquityStr(String returnOnEquityStr) {
		this.returnOnEquityStr = returnOnEquityStr;
	}

	/**
	 * @return the curruntRatioStr
	 */
	public String getCurruntRatioStr() {
		return curruntRatioStr;
	}

	/**
	 * @param curruntRatioStr
	 *            the curruntRatioStr to set
	 */
	public void setCurruntRatioStr(String curruntRatioStr) {
		this.curruntRatioStr = curruntRatioStr;
	}

	/**
	 * @return the fixedAssetsTurnoverStr
	 */
	public String getFixedAssetsTurnoverStr() {
		return fixedAssetsTurnoverStr;
	}

	/**
	 * @param fixedAssetsTurnoverStr
	 *            the fixedAssetsTurnoverStr to set
	 */
	public void setFixedAssetsTurnoverStr(String fixedAssetsTurnoverStr) {
		this.fixedAssetsTurnoverStr = fixedAssetsTurnoverStr;
	}

	/**
	 * @return the workingCapitalTurnoverStr
	 */
	public String getWorkingCapitalTurnoverStr() {
		return workingCapitalTurnoverStr;
	}

	/**
	 * @param workingCapitalTurnoverStr
	 *            the workingCapitalTurnoverStr to set
	 */
	public void setWorkingCapitalTurnoverStr(String workingCapitalTurnoverStr) {
		this.workingCapitalTurnoverStr = workingCapitalTurnoverStr;
	}

	/**
	 * @return the receivablesDaysStr
	 */
	public String getReceivablesDaysStr() {
		return receivablesDaysStr;
	}

	/**
	 * @param receivablesDaysStr
	 *            the receivablesDaysStr to set
	 */
	public void setReceivablesDaysStr(String receivablesDaysStr) {
		this.receivablesDaysStr = receivablesDaysStr;
	}

	/**
	 * @return the inventoryDaysStr
	 */
	public String getInventoryDaysStr() {
		return inventoryDaysStr;
	}

	/**
	 * @param inventoryDaysStr
	 *            the inventoryDaysStr to set
	 */
	public void setInventoryDaysStr(String inventoryDaysStr) {
		this.inventoryDaysStr = inventoryDaysStr;
	}

	/**
	 * @return the longTermDebtOrEquityStr
	 */
	public String getLongTermDebtOrEquityStr() {
		return longTermDebtOrEquityStr;
	}

	/**
	 * @param longTermDebtOrEquityStr
	 *            the longTermDebtOrEquityStr to set
	 */
	public void setLongTermDebtOrEquityStr(String longTermDebtOrEquityStr) {
		this.longTermDebtOrEquityStr = longTermDebtOrEquityStr;
	}

	/**
	 * @return the interestCoverageStr
	 */
	public String getInterestCoverageStr() {
		return interestCoverageStr;
	}

	/**
	 * @param interestCoverageStr
	 *            the interestCoverageStr to set
	 */
	public void setInterestCoverageStr(String interestCoverageStr) {
		this.interestCoverageStr = interestCoverageStr;
	}

	/**
	 * @return the totalAssetsOrEquityStr
	 */
	public String getTotalAssetsOrEquityStr() {
		return totalAssetsOrEquityStr;
	}

	/**
	 * @param totalAssetsOrEquityStr
	 *            the totalAssetsOrEquityStr to set
	 */
	public void setTotalAssetsOrEquityStr(String totalAssetsOrEquityStr) {
		this.totalAssetsOrEquityStr = totalAssetsOrEquityStr;
	}

	/**
	 * @return the totalDebtOrTotalAssetsStr
	 */
	public String getTotalDebtOrTotalAssetsStr() {
		return totalDebtOrTotalAssetsStr;
	}

	/**
	 * @param totalDebtOrTotalAssetsStr
	 *            the totalDebtOrTotalAssetsStr to set
	 */
	public void setTotalDebtOrTotalAssetsStr(String totalDebtOrTotalAssetsStr) {
		this.totalDebtOrTotalAssetsStr = totalDebtOrTotalAssetsStr;
	}

	/**
	 * @return the quickRatioStr
	 */
	public String getQuickRatioStr() {
		return quickRatioStr;
	}

	/**
	 * @param quickRatioStr
	 *            the quickRatioStr to set
	 */
	public void setQuickRatioStr(String quickRatioStr) {
		this.quickRatioStr = quickRatioStr;
	}

	/**
	 * @return the operatingMarginStr
	 */
	public String getOperatingMarginStr() {
		return operatingMarginStr;
	}

	/**
	 * @param operatingMarginStr
	 *            the operatingMarginStr to set
	 */
	public void setOperatingMarginStr(String operatingMarginStr) {
		this.operatingMarginStr = operatingMarginStr;
	}

	/**
	 * @return the returnOnFixedAssetsStr
	 */
	public String getReturnOnFixedAssetsStr() {
		return returnOnFixedAssetsStr;
	}

	/**
	 * @param returnOnFixedAssetsStr
	 *            the returnOnFixedAssetsStr to set
	 */
	public void setReturnOnFixedAssetsStr(String returnOnFixedAssetsStr) {
		this.returnOnFixedAssetsStr = returnOnFixedAssetsStr;
	}

	/**
	 * @return the returnOnCapitalEmployedStr
	 */
	public String getReturnOnCapitalEmployedStr() {
		return returnOnCapitalEmployedStr;
	}

	/**
	 * @param returnOnCapitalEmployedStr
	 *            the returnOnCapitalEmployedStr to set
	 */
	public void setReturnOnCapitalEmployedStr(String returnOnCapitalEmployedStr) {
		this.returnOnCapitalEmployedStr = returnOnCapitalEmployedStr;
	}

	/**
	 * @return the totalAssetsTurnoverStr
	 */
	public String getTotalAssetsTurnoverStr() {
		return totalAssetsTurnoverStr;
	}

	/**
	 * @param totalAssetsTurnoverStr
	 *            the totalAssetsTurnoverStr to set
	 */
	public void setTotalAssetsTurnoverStr(String totalAssetsTurnoverStr) {
		this.totalAssetsTurnoverStr = totalAssetsTurnoverStr;
	}

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
