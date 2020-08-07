/**
 * 
 */
package com.opl.mudra.api.mca;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay.darji
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class McaFinancialCalculationRequest {
	
	@JsonProperty("as-on-date")
    private String asOnDate;
  
    @JsonProperty("consolidated-long-term-borrowings")
    private String consolidatedLongTermBorrowings;
    
    @JsonProperty("financial-year")
    private String financialYear;
    @JsonProperty("reporting-period")
    private String reportingPeriod;
    @JsonProperty("share-capital")
    private String shareCapital;
    @JsonProperty("reserves-and-surplus")
    private String reservesAndSurplus;
    @JsonProperty("money-received-against-share-warrants")
    private String moneyReceivedAgainstShareWarrants;
    @JsonProperty("share-application-money-pending-allotment")
    private String shareApplicationMoneyPendingAllotment;
    @JsonProperty("networth")
    private String networth;
    @JsonProperty("long-term-borrowings")
    private String longTermBorrowings;
    @JsonProperty("net-dtl")
    private String netDtl;
    @JsonProperty("other-long-term-liabilities")
    private String otherLongTermLiabilities;
    @JsonProperty("long-term-provisions")
    private String longTermProvisions;
    @JsonProperty("total-non-current-liability")
    private String totalNonCurrentLiability;
    @JsonProperty("short-term-borrowings")
    private String shortTermBorrowings;
    @JsonProperty("trade-payables")
    private String tradePayables;
    @JsonProperty("other-current-liabilities")
    private String otherCurrentLiabilities;
    @JsonProperty("short-term-provisions")
    private String shortTermProvisions;
    @JsonProperty("total-current-liabilities")
    private String totalCurrentLiabilities;
    @JsonProperty("total-equity-and-liabilities")
    private String totalEquityAndLiabilities;
    @JsonProperty("tangible-assets")
    private String tangibleAssets;
    @JsonProperty("intangible-assets")
    private String intangibleAssets;
    @JsonProperty("capital-wip")
    private String capitalWip;
    @JsonProperty("intangible-assets-under-development")
    private String intangibleAssetsUnderDevelopment;
    @JsonProperty("total-fixed-assets")
    private String totalFixedAssets;
    @JsonProperty("non-current-investments")
    private String nonCurrentInvestments;
    @JsonProperty("net-dta")
    private String netDta;
    @JsonProperty("long-term-loans-and-advances")
    private String longTermLoansAndAdvances;
    @JsonProperty("other-non-current-assets")
    private String otherNonCurrentAssets;
    @JsonProperty("total-non-current-assets")
    private String totalNonCurrentAssets;
    @JsonProperty("current-investments")
    private String currentInvestments;
    @JsonProperty("inventories")
    private String inventories;
    @JsonProperty("trade-receivables")
    private String tradeReceivables;
    @JsonProperty("cash-and-cash-equivalents")
    private String cashAndCashEquivalents;
    @JsonProperty("short-term-loans-and-advances")
    private String shortTermLoansAndAdvances;
    @JsonProperty("other-current-assets")
    private String otherCurrentAssets;
    @JsonProperty("total-current-assets")
    private String totalCurrentAssets;
    @JsonProperty("total-assets")
    private String totalAssets;
    @JsonProperty("financial-year-from")
    private String financialYearFrom;
    @JsonProperty("financial-year-to")
    private String financialYearTo;
    @JsonProperty("revenue-from-sale-of-products")
    private String revenueFromSaleOfProducts;
    @JsonProperty("revenue-from-sale-of-services")
    private String revenueFromSaleOfServices;
    @JsonProperty("total-revenue-from-operations-other-than-finance-company")
    private String totalRevenueFromOperationsOtherThanFinanceCompany;
    @JsonProperty("total-revenue-from-operations")
    private String totalRevenueFromOperations;
    @JsonProperty("other-income")
    private String otherIncome;
    @JsonProperty("total-revenue")
    private String totalRevenue;
    @JsonProperty("cost-of-material-consumed")
    private String costOfMaterialConsumed;
    @JsonProperty("purchases-of-stock-in-trade")
    private String purchasesOfStockInTrade;
    @JsonProperty("changes-in-inventories-of-finished-goods-and-work-in-progress-and-stock-in-trade")
    private String goodsAndWorkInProgressAndStockInTrade;
    @JsonProperty("employee-benefit-expenses")
    private String employeeBenefitExpenses;
    @JsonProperty("finance-cost")
    private String financeCost;
    @JsonProperty("depreciation-and-amortization-expense")
    private String depreciationAndAmortizationExpense;
    @JsonProperty("other-expenses")
    private String otherExpenses;
    @JsonProperty("total-expenses")
    private String totalExpenses;
    @JsonProperty("profit-before-exceptional-and-extraordinary-items-and-tax")
    private String exceptionalAndExtraordinaryItemsAndTax;
    @JsonProperty("exceptional-items-before-tax")
    private String exceptionalItemsBeforeTax;
    @JsonProperty("profit-before-extraordinary-items-and-tax")
    private String profitBeforeExtraordinaryItemsAndTax;
    @JsonProperty("extraordinary-items-before-tax")
    private String extraordinaryItemsBeforeTax;
    @JsonProperty("profit-before-tax")
    private String profitBeforeTax;
    @JsonProperty("current-tax")
    private String currentTax;
    @JsonProperty("deferred-tax")
    private String deferredTax;
    @JsonProperty("total-tax-expenses")
    private String totalTaxExpenses;
    @JsonProperty("profit-or-(loss)-for-the-period-from-continuing-operations")
    private String plForThePeriodFromContinuingOperations;
    @JsonProperty("profit-or-(loss)-from-discontinuing-operations-before-tax")
    private String plFromDiscontinuingOperationsBeforeTax;
    @JsonProperty("tax-expense-of-discontinuing-operations")
    private String taxExpenseOfDiscontinuingOperations;
    @JsonProperty("profit-or-(loss)-from-discontinuing-operations-after-tax")
    private String plFromDiscontinuingOperationsAfterTax;
    @JsonProperty("total-profit-(loss)-for-period-before-minority-interest")
    private String totalProfitLossForPeriodBeforeMinorityInterest;
    @JsonProperty("total-profit-or-loss-for-period")
    private String totalProfitOrLossForPeriod;
    @JsonProperty("ebitda")
    private String ebitda;
    @JsonProperty("ebitda-percentage")
    private String ebitdaPercentage;
    @JsonProperty("deffered-government-grants")
    private String defferedGovernmentGrants;
    @JsonProperty("minority-interest")
    private String minorityInterest;
    @JsonProperty("other-operating-revenues")
    private String otherOperatingRevenues;
    @JsonProperty("duties")
    private String duties;
    @JsonProperty("prior-period-items-before-tax")
    private String priorPeriodItemsBeforeTax;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    private Number osTotal;
    
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	public String getConsolidatedLongTermBorrowings() {
		return consolidatedLongTermBorrowings;
	}
	public void setConsolidatedLongTermBorrowings(String consolidatedLongTermBorrowings) {
		this.consolidatedLongTermBorrowings = consolidatedLongTermBorrowings;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public String getReportingPeriod() {
		return reportingPeriod;
	}
	public void setReportingPeriod(String reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}
	public String getShareCapital() {
		return shareCapital;
	}
	public void setShareCapital(String shareCapital) {
		this.shareCapital = shareCapital;
	}
	public String getReservesAndSurplus() {
		return reservesAndSurplus;
	}
	public void setReservesAndSurplus(String reservesAndSurplus) {
		this.reservesAndSurplus = reservesAndSurplus;
	}
	public String getMoneyReceivedAgainstShareWarrants() {
		return moneyReceivedAgainstShareWarrants;
	}
	public void setMoneyReceivedAgainstShareWarrants(String moneyReceivedAgainstShareWarrants) {
		this.moneyReceivedAgainstShareWarrants = moneyReceivedAgainstShareWarrants;
	}
	public String getShareApplicationMoneyPendingAllotment() {
		return shareApplicationMoneyPendingAllotment;
	}
	public void setShareApplicationMoneyPendingAllotment(String shareApplicationMoneyPendingAllotment) {
		this.shareApplicationMoneyPendingAllotment = shareApplicationMoneyPendingAllotment;
	}
	public String getNetworth() {
		return networth;
	}
	public void setNetworth(String networth) {
		this.networth = networth;
	}
	public String getLongTermBorrowings() {
		return longTermBorrowings;
	}
	public void setLongTermBorrowings(String longTermBorrowings) {
		this.longTermBorrowings = longTermBorrowings;
	}
	public String getNetDtl() {
		return netDtl;
	}
	public void setNetDtl(String netDtl) {
		this.netDtl = netDtl;
	}
	public String getOtherLongTermLiabilities() {
		return otherLongTermLiabilities;
	}
	public void setOtherLongTermLiabilities(String otherLongTermLiabilities) {
		this.otherLongTermLiabilities = otherLongTermLiabilities;
	}
	public String getLongTermProvisions() {
		return longTermProvisions;
	}
	public void setLongTermProvisions(String longTermProvisions) {
		this.longTermProvisions = longTermProvisions;
	}
	public String getTotalNonCurrentLiability() {
		return totalNonCurrentLiability;
	}
	public void setTotalNonCurrentLiability(String totalNonCurrentLiability) {
		this.totalNonCurrentLiability = totalNonCurrentLiability;
	}
	public String getShortTermBorrowings() {
		return shortTermBorrowings;
	}
	public void setShortTermBorrowings(String shortTermBorrowings) {
		this.shortTermBorrowings = shortTermBorrowings;
	}
	public String getTradePayables() {
		return tradePayables;
	}
	public void setTradePayables(String tradePayables) {
		this.tradePayables = tradePayables;
	}
	public String getOtherCurrentLiabilities() {
		return otherCurrentLiabilities;
	}
	public void setOtherCurrentLiabilities(String otherCurrentLiabilities) {
		this.otherCurrentLiabilities = otherCurrentLiabilities;
	}
	public String getShortTermProvisions() {
		return shortTermProvisions;
	}
	public void setShortTermProvisions(String shortTermProvisions) {
		this.shortTermProvisions = shortTermProvisions;
	}
	public String getTotalCurrentLiabilities() {
		return totalCurrentLiabilities;
	}
	public void setTotalCurrentLiabilities(String totalCurrentLiabilities) {
		this.totalCurrentLiabilities = totalCurrentLiabilities;
	}
	public String getTotalEquityAndLiabilities() {
		return totalEquityAndLiabilities;
	}
	public void setTotalEquityAndLiabilities(String totalEquityAndLiabilities) {
		this.totalEquityAndLiabilities = totalEquityAndLiabilities;
	}
	public String getTangibleAssets() {
		return tangibleAssets;
	}
	public void setTangibleAssets(String tangibleAssets) {
		this.tangibleAssets = tangibleAssets;
	}
	public String getIntangibleAssets() {
		return intangibleAssets;
	}
	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}
	public String getCapitalWip() {
		return capitalWip;
	}
	public void setCapitalWip(String capitalWip) {
		this.capitalWip = capitalWip;
	}
	public String getIntangibleAssetsUnderDevelopment() {
		return intangibleAssetsUnderDevelopment;
	}
	public void setIntangibleAssetsUnderDevelopment(String intangibleAssetsUnderDevelopment) {
		this.intangibleAssetsUnderDevelopment = intangibleAssetsUnderDevelopment;
	}
	public String getTotalFixedAssets() {
		return totalFixedAssets;
	}
	public void setTotalFixedAssets(String totalFixedAssets) {
		this.totalFixedAssets = totalFixedAssets;
	}
	public String getNonCurrentInvestments() {
		return nonCurrentInvestments;
	}
	public void setNonCurrentInvestments(String nonCurrentInvestments) {
		this.nonCurrentInvestments = nonCurrentInvestments;
	}
	public String getNetDta() {
		return netDta;
	}
	public void setNetDta(String netDta) {
		this.netDta = netDta;
	}
	public String getLongTermLoansAndAdvances() {
		return longTermLoansAndAdvances;
	}
	public void setLongTermLoansAndAdvances(String longTermLoansAndAdvances) {
		this.longTermLoansAndAdvances = longTermLoansAndAdvances;
	}
	public String getOtherNonCurrentAssets() {
		return otherNonCurrentAssets;
	}
	public void setOtherNonCurrentAssets(String otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}
	public String getTotalNonCurrentAssets() {
		return totalNonCurrentAssets;
	}
	public void setTotalNonCurrentAssets(String totalNonCurrentAssets) {
		this.totalNonCurrentAssets = totalNonCurrentAssets;
	}
	public String getCurrentInvestments() {
		return currentInvestments;
	}
	public void setCurrentInvestments(String currentInvestments) {
		this.currentInvestments = currentInvestments;
	}
	public String getInventories() {
		return inventories;
	}
	public void setInventories(String inventories) {
		this.inventories = inventories;
	}
	public String getTradeReceivables() {
		return tradeReceivables;
	}
	public void setTradeReceivables(String tradeReceivables) {
		this.tradeReceivables = tradeReceivables;
	}
	public String getCashAndCashEquivalents() {
		return cashAndCashEquivalents;
	}
	public void setCashAndCashEquivalents(String cashAndCashEquivalents) {
		this.cashAndCashEquivalents = cashAndCashEquivalents;
	}
	public String getShortTermLoansAndAdvances() {
		return shortTermLoansAndAdvances;
	}
	public void setShortTermLoansAndAdvances(String shortTermLoansAndAdvances) {
		this.shortTermLoansAndAdvances = shortTermLoansAndAdvances;
	}
	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}
	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}
	public String getTotalCurrentAssets() {
		return totalCurrentAssets;
	}
	public void setTotalCurrentAssets(String totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}
	public String getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}
	public String getFinancialYearFrom() {
		return financialYearFrom;
	}
	public void setFinancialYearFrom(String financialYearFrom) {
		this.financialYearFrom = financialYearFrom;
	}
	public String getFinancialYearTo() {
		return financialYearTo;
	}
	public void setFinancialYearTo(String financialYearTo) {
		this.financialYearTo = financialYearTo;
	}
	public String getRevenueFromSaleOfProducts() {
		return revenueFromSaleOfProducts;
	}
	public void setRevenueFromSaleOfProducts(String revenueFromSaleOfProducts) {
		this.revenueFromSaleOfProducts = revenueFromSaleOfProducts;
	}
	public String getRevenueFromSaleOfServices() {
		return revenueFromSaleOfServices;
	}
	public void setRevenueFromSaleOfServices(String revenueFromSaleOfServices) {
		this.revenueFromSaleOfServices = revenueFromSaleOfServices;
	}
	public String getTotalRevenueFromOperationsOtherThanFinanceCompany() {
		return totalRevenueFromOperationsOtherThanFinanceCompany;
	}
	public void setTotalRevenueFromOperationsOtherThanFinanceCompany(
			String totalRevenueFromOperationsOtherThanFinanceCompany) {
		this.totalRevenueFromOperationsOtherThanFinanceCompany = totalRevenueFromOperationsOtherThanFinanceCompany;
	}
	public String getTotalRevenueFromOperations() {
		return totalRevenueFromOperations;
	}
	public void setTotalRevenueFromOperations(String totalRevenueFromOperations) {
		this.totalRevenueFromOperations = totalRevenueFromOperations;
	}
	public String getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public String getCostOfMaterialConsumed() {
		return costOfMaterialConsumed;
	}
	public void setCostOfMaterialConsumed(String costOfMaterialConsumed) {
		this.costOfMaterialConsumed = costOfMaterialConsumed;
	}
	public String getPurchasesOfStockInTrade() {
		return purchasesOfStockInTrade;
	}
	public void setPurchasesOfStockInTrade(String purchasesOfStockInTrade) {
		this.purchasesOfStockInTrade = purchasesOfStockInTrade;
	}
	public String getGoodsAndWorkInProgressAndStockInTrade() {
		return goodsAndWorkInProgressAndStockInTrade;
	}
	public void setGoodsAndWorkInProgressAndStockInTrade(String goodsAndWorkInProgressAndStockInTrade) {
		this.goodsAndWorkInProgressAndStockInTrade = goodsAndWorkInProgressAndStockInTrade;
	}
	public String getEmployeeBenefitExpenses() {
		return employeeBenefitExpenses;
	}
	public void setEmployeeBenefitExpenses(String employeeBenefitExpenses) {
		this.employeeBenefitExpenses = employeeBenefitExpenses;
	}
	public String getFinanceCost() {
		return financeCost;
	}
	public void setFinanceCost(String financeCost) {
		this.financeCost = financeCost;
	}
	public String getDepreciationAndAmortizationExpense() {
		return depreciationAndAmortizationExpense;
	}
	public void setDepreciationAndAmortizationExpense(String depreciationAndAmortizationExpense) {
		this.depreciationAndAmortizationExpense = depreciationAndAmortizationExpense;
	}
	public String getOtherExpenses() {
		return otherExpenses;
	}
	public void setOtherExpenses(String otherExpenses) {
		this.otherExpenses = otherExpenses;
	}
	public String getTotalExpenses() {
		return totalExpenses;
	}
	public void setTotalExpenses(String totalExpenses) {
		this.totalExpenses = totalExpenses;
	}
	public String getExceptionalAndExtraordinaryItemsAndTax() {
		return exceptionalAndExtraordinaryItemsAndTax;
	}
	public void setExceptionalAndExtraordinaryItemsAndTax(String exceptionalAndExtraordinaryItemsAndTax) {
		this.exceptionalAndExtraordinaryItemsAndTax = exceptionalAndExtraordinaryItemsAndTax;
	}
	public String getExceptionalItemsBeforeTax() {
		return exceptionalItemsBeforeTax;
	}
	public void setExceptionalItemsBeforeTax(String exceptionalItemsBeforeTax) {
		this.exceptionalItemsBeforeTax = exceptionalItemsBeforeTax;
	}
	public String getProfitBeforeExtraordinaryItemsAndTax() {
		return profitBeforeExtraordinaryItemsAndTax;
	}
	public void setProfitBeforeExtraordinaryItemsAndTax(String profitBeforeExtraordinaryItemsAndTax) {
		this.profitBeforeExtraordinaryItemsAndTax = profitBeforeExtraordinaryItemsAndTax;
	}
	public String getExtraordinaryItemsBeforeTax() {
		return extraordinaryItemsBeforeTax;
	}
	public void setExtraordinaryItemsBeforeTax(String extraordinaryItemsBeforeTax) {
		this.extraordinaryItemsBeforeTax = extraordinaryItemsBeforeTax;
	}
	public String getProfitBeforeTax() {
		return profitBeforeTax;
	}
	public void setProfitBeforeTax(String profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}
	public String getCurrentTax() {
		return currentTax;
	}
	public void setCurrentTax(String currentTax) {
		this.currentTax = currentTax;
	}
	public String getDeferredTax() {
		return deferredTax;
	}
	public void setDeferredTax(String deferredTax) {
		this.deferredTax = deferredTax;
	}
	public String getTotalTaxExpenses() {
		return totalTaxExpenses;
	}
	public void setTotalTaxExpenses(String totalTaxExpenses) {
		this.totalTaxExpenses = totalTaxExpenses;
	}
	public String getPlForThePeriodFromContinuingOperations() {
		return plForThePeriodFromContinuingOperations;
	}
	public void setPlForThePeriodFromContinuingOperations(String plForThePeriodFromContinuingOperations) {
		this.plForThePeriodFromContinuingOperations = plForThePeriodFromContinuingOperations;
	}
	public String getPlFromDiscontinuingOperationsBeforeTax() {
		return plFromDiscontinuingOperationsBeforeTax;
	}
	public void setPlFromDiscontinuingOperationsBeforeTax(String plFromDiscontinuingOperationsBeforeTax) {
		this.plFromDiscontinuingOperationsBeforeTax = plFromDiscontinuingOperationsBeforeTax;
	}
	public String getTaxExpenseOfDiscontinuingOperations() {
		return taxExpenseOfDiscontinuingOperations;
	}
	public void setTaxExpenseOfDiscontinuingOperations(String taxExpenseOfDiscontinuingOperations) {
		this.taxExpenseOfDiscontinuingOperations = taxExpenseOfDiscontinuingOperations;
	}
	public String getPlFromDiscontinuingOperationsAfterTax() {
		return plFromDiscontinuingOperationsAfterTax;
	}
	public void setPlFromDiscontinuingOperationsAfterTax(String plFromDiscontinuingOperationsAfterTax) {
		this.plFromDiscontinuingOperationsAfterTax = plFromDiscontinuingOperationsAfterTax;
	}
	public String getTotalProfitLossForPeriodBeforeMinorityInterest() {
		return totalProfitLossForPeriodBeforeMinorityInterest;
	}
	public void setTotalProfitLossForPeriodBeforeMinorityInterest(String totalProfitLossForPeriodBeforeMinorityInterest) {
		this.totalProfitLossForPeriodBeforeMinorityInterest = totalProfitLossForPeriodBeforeMinorityInterest;
	}
	public String getTotalProfitOrLossForPeriod() {
		return totalProfitOrLossForPeriod;
	}
	public void setTotalProfitOrLossForPeriod(String totalProfitOrLossForPeriod) {
		this.totalProfitOrLossForPeriod = totalProfitOrLossForPeriod;
	}
	public String getEbitda() {
		return ebitda;
	}
	public void setEbitda(String ebitda) {
		this.ebitda = ebitda;
	}
	public String getEbitdaPercentage() {
		return ebitdaPercentage;
	}
	public void setEbitdaPercentage(String ebitdaPercentage) {
		this.ebitdaPercentage = ebitdaPercentage;
	}
	public String getDefferedGovernmentGrants() {
		return defferedGovernmentGrants;
	}
	public void setDefferedGovernmentGrants(String defferedGovernmentGrants) {
		this.defferedGovernmentGrants = defferedGovernmentGrants;
	}
	public String getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(String minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	public String getOtherOperatingRevenues() {
		return otherOperatingRevenues;
	}
	public void setOtherOperatingRevenues(String otherOperatingRevenues) {
		this.otherOperatingRevenues = otherOperatingRevenues;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getPriorPeriodItemsBeforeTax() {
		return priorPeriodItemsBeforeTax;
	}
	public void setPriorPeriodItemsBeforeTax(String priorPeriodItemsBeforeTax) {
		this.priorPeriodItemsBeforeTax = priorPeriodItemsBeforeTax;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	public Number getOsTotal() {
		return osTotal;
	}
	public void setOsTotal(Number osTotal) {
		this.osTotal = osTotal;
	}

    
    

}
