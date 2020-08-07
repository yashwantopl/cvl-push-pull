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
/**
 * @author nilay.darji
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class McaFinancialCalculationResponse {
	
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
    
    private String osTotal;
    private String osCostOfsalesRawMaterials;
    private String osAddOtherNonInc;
    
    private String liaOtherCurrentLiability;
    private String liaShareWarrants;
    private String liaDefferedTax;
    
    private String assetReceiveOtherDef;
    private String assetOtherCurruntAsset;
    private String assetOtherIntangibleAssets;
    private String osLessExciseDuty;
    private String osDeductOtherItems;
    private String osExportSales;
    private String osNetSales;
    private String osRaisOrFall;
    private String osCostofSales;
    private String osCostOfsalesRawMaterialsImport;
    private String osCostOfsalesRawMaterialsIndi;
    private String osOtherSpares;
    private String osOtherSparesImported;
    private String osOtherSparesIndi;
    private String osPowerFuel;
    private String osMfgExp;
    private String osSubTotalDep;
    private String osSubTotalAddStocks;
    private String osAddOpStockes;
    private String osCostOfProduction;
    private String osDiductStockes;
    private String osSubTotalAddOpStockesFp;
    private String osSubTotalCostOfSales;
    private String osDeductClStockOfFg;
    private String osSubtotalGenAndAdmn;
    private String osSellingAndDistributionEx;
    private String osSubtotalIncome;
    private String osSubTotalExpenses;
    private String osNetOfIncExp;
    private String osDeductotherOp;
    private String osProfitBeforeTaxLoss;
    private String osNetProfitLoss;
    private String osProfitBeforeInterest;
    private String osProfitAfterInterest;
    private String osExpenseAmortised;
    private String osOtherImcomeNeedToCheck;
    private String osRetainProfit;
    private String osEquitydivident;
    private String osDividentRate;
    private String osRetainProfitPercentage;
    
    private String liaShortTermBorrowingsFromBanks;
    private String liaFromApplicant;
    private String liaFromOther;
    private String liaOfWhichBd;
    private String liaSubTotalA;
    private String liaShortTermBorrowings;
    private String liaAdvancePaymentsCust;
    private String liaProvisionTaxes;
    private String liaDividantPay;
    private String liaOtherStatutory;
    private String liaDepositeOftermLoan;
    private String liaSubTotalB;
    private String liaTotalCurrLiability;
    private String liaDebenturs;
    private String liaPrefrenceShares;
    private String liaTermSecured;
    private String liaTermUnSecured;
    private String liaDefferedPay;
    private String liaOtherTermLia;
    private String liaTermLia;
    private String liaNonCurLia;
    private String liaUnsecLoansPromoters;
    private String liaUnsecureLoansOther;
    private String liaOtherloans;
    private String liaTotalOutsideLia;
    private String liaMinorityInterest;
    private String liaGeneralRes;
    private String liaRevalution;
    private String liaSurPlus;
    private String liaOtherSpecify;
    private String liaNetworth;
    private String liaOtherIncomeNeedToCheck;
    private String liaTotalLiabilities;
    
    private String assetInvestments;
    private String assetInvestmentsGov;
    private String assetInvestmentsFixed;
    private String assetExportRec;
    private String assetInstalmentsDef;
    private String assetRawMaterials;
    private String assetRawMaterialsImp;
    private String assetRawMaterialIndi;
    private String assetStockesInPro;
    private String assetFinishedGoods;
    private String assetOtherConsumSp;
    private String assetOtherConsumSpIm;
    private String assetOtherConsumSpIn;
    private String assetAdvanceToSupp;
    private String assetAdvancePayTax;
    private String assetTotalCurAssets;
    private String assetGrossblockWIP;
    private String assetGrossblockWIPBuild;
    private String assetGrossblockWIPPlant;
    private String assetDepriciationToDate;
    private String assetImpairmentOfAsset;
    private String assetInvestment;
    private String assetInvestmentInSubd;
    private String assetInvestmentOthers;
    private String assetAdvSupp;
    private String assetDefferedRec;
    private String assetOthersIV;
    private String assetPreOp;
    private String assetInTransist;
    private String assetothersIVothers;
    private String assetNonConsumable;
    private String assetTotalOtherNonCurAssets;
    private String assetpatent;
    private String assetGoddWill;
    private String assetPriliminaryExp;
    private String assetDoubtFulExp;
    private String assetothersIntangible;
    private String assetTotalAssets;
    private String assetTangibleNetworth;
    private String assetNetWorkingCapital;
    private String assetCurrRatio;
    private String assetTotalOutSideLia;
    private String assetTotalTermLia;
    private String assetOtherIncomeNeedToCheck;
    
    
    
    public McaFinancialCalculationResponse() {
    	super();
    	this.osTotal="0";
        this.osCostOfsalesRawMaterials="0";
        this.osAddOtherNonInc="0";
        this.liaOtherCurrentLiability="0";
        this.liaShareWarrants="0";
        this.liaDefferedTax="0";
        this.assetReceiveOtherDef="0";
        this.assetOtherCurruntAsset="0";
        this.assetOtherIntangibleAssets="0";
        this.osLessExciseDuty="0";
        this.osDeductOtherItems="0";
        this.osExportSales="0";
        this.osNetSales="0";
        this.osRaisOrFall="0";
        this.osCostofSales="0";
        this.osCostOfsalesRawMaterialsImport="0";
        this.osCostOfsalesRawMaterialsIndi="0";
        this.osOtherSpares="0";
        this.osOtherSparesImported="0";
        this.osOtherSparesIndi="0";
        this.osPowerFuel="0";
        this.osMfgExp="0";
        this.osSubTotalDep="0";
        this.osSubTotalAddStocks="0";
        this.osAddOpStockes="0";
        this.osCostOfProduction="0";
        this.osDiductStockes="0";
        this.osSubTotalAddOpStockesFp="0";
        this.osSubTotalCostOfSales="0";
        this.osDeductClStockOfFg="0";
        this.osSubtotalGenAndAdmn="0";
        this.osSellingAndDistributionEx="0";
        this.osSubtotalIncome="0";
        this.osSubTotalExpenses="0";
        this.osNetOfIncExp="0";
        this.osDeductotherOp="0";
        this.osProfitBeforeTaxLoss="0";
        this.osNetProfitLoss="0";
        this.osProfitBeforeInterest="0";
        this.osProfitAfterInterest="0";
        this.osExpenseAmortised="0";
        this.osOtherImcomeNeedToCheck="0";
        this.osRetainProfit="0";
        this.osEquitydivident="0";
        this.osDividentRate="0";
        this.osRetainProfitPercentage="0";
        this.liaShortTermBorrowingsFromBanks="0";
        this.liaFromApplicant="0";
        this.liaFromOther="0";
        this.liaOfWhichBd="0";
        this.liaSubTotalA="0";
        this.liaShortTermBorrowings="0";
        this.liaAdvancePaymentsCust="0";
        this.liaProvisionTaxes="0";
        this.liaDividantPay="0";
        this.liaOtherStatutory="0";
        this.liaDepositeOftermLoan="0";
        this.liaSubTotalB="0";
        this.liaTotalCurrLiability="0";
        this.liaDebenturs="0";
        this.liaPrefrenceShares="0";
        this.liaTermSecured="0";
        this.liaTermUnSecured="0";
        this.liaDefferedPay="0";
        this.liaOtherTermLia="0";
        this.liaTermLia="0";
        this.liaNonCurLia="0";
        this.liaUnsecLoansPromoters="0";
        this.liaUnsecureLoansOther="0";
        this.liaOtherloans="0";
        this.liaTotalOutsideLia="0";
        this.liaMinorityInterest="0";
        this.liaGeneralRes="0";
        this.liaRevalution="0";
        this.liaSurPlus="0";
        this.liaOtherSpecify="0";
        this.liaNetworth="0";
        this.liaOtherIncomeNeedToCheck="0";
        this.liaTotalLiabilities="0"; 
        this.assetAdvanceToSupp="0";
        this.assetInvestments="0";
        this.assetInvestmentsGov="0";
        this.assetInvestmentsFixed="0";
        this.assetExportRec="0";
        this.assetInstalmentsDef="0";
        this.assetRawMaterials="0";
        this.assetRawMaterialsImp="0";
        this.assetRawMaterialIndi="0";
        this.assetStockesInPro="0";
        this.assetFinishedGoods="0";
        this.assetOtherConsumSp="0";
        this.assetOtherConsumSpIm="0";
        this.assetOtherConsumSpIn="0";
        this.assetAdvancePayTax="0";
        this.assetTotalCurAssets="0";
        this.assetGrossblockWIP="0";
        this.assetGrossblockWIPBuild="0";
        this.assetGrossblockWIPPlant="0";
        this.assetDepriciationToDate="0";
        this.assetImpairmentOfAsset="0";
        this.assetInvestment="0";
        this.assetInvestmentInSubd="0";
        this.assetInvestmentOthers="0";
        this.assetAdvSupp="0";
        this.assetDefferedRec="0";
        this.assetOthersIV="0";
        this.assetPreOp="0";
        this.assetInTransist="0";
        this.assetothersIVothers="0";
        this.assetNonConsumable="0";
        this.assetTotalOtherNonCurAssets="0";
        this.assetpatent="0";
        this.assetGoddWill="0";
        this.assetPriliminaryExp="0";
        this.assetDoubtFulExp="0";
        this.assetothersIntangible="0";
        this.assetTotalAssets="0";
        this.assetTangibleNetworth="0";
        this.assetNetWorkingCapital="0";
        this.assetCurrRatio="0.0";
        this.assetTotalOutSideLia="0.0";
        this.assetTotalTermLia="0.0";
        this.assetOtherIncomeNeedToCheck="0";
    }



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



	public String getOsTotal() {
		return osTotal;
	}



	public void setOsTotal(String osTotal) {
		this.osTotal = osTotal;
	}



	public String getOsCostOfsalesRawMaterials() {
		return osCostOfsalesRawMaterials;
	}



	public void setOsCostOfsalesRawMaterials(String osCostOfsalesRawMaterials) {
		this.osCostOfsalesRawMaterials = osCostOfsalesRawMaterials;
	}



	public String getOsAddOtherNonInc() {
		return osAddOtherNonInc;
	}



	public void setOsAddOtherNonInc(String osAddOtherNonInc) {
		this.osAddOtherNonInc = osAddOtherNonInc;
	}



	public String getLiaOtherCurrentLiability() {
		return liaOtherCurrentLiability;
	}



	public void setLiaOtherCurrentLiability(String liaOtherCurrentLiability) {
		this.liaOtherCurrentLiability = liaOtherCurrentLiability;
	}



	public String getLiaShareWarrants() {
		return liaShareWarrants;
	}



	public void setLiaShareWarrants(String liaShareWarrants) {
		this.liaShareWarrants = liaShareWarrants;
	}



	public String getLiaDefferedTax() {
		return liaDefferedTax;
	}



	public void setLiaDefferedTax(String liaDefferedTax) {
		this.liaDefferedTax = liaDefferedTax;
	}



	public String getAssetReceiveOtherDef() {
		return assetReceiveOtherDef;
	}



	public void setAssetReceiveOtherDef(String assetReceiveOtherDef) {
		this.assetReceiveOtherDef = assetReceiveOtherDef;
	}



	public String getAssetOtherCurruntAsset() {
		return assetOtherCurruntAsset;
	}



	public void setAssetOtherCurruntAsset(String assetOtherCurruntAsset) {
		this.assetOtherCurruntAsset = assetOtherCurruntAsset;
	}



	public String getAssetOtherIntangibleAssets() {
		return assetOtherIntangibleAssets;
	}



	public void setAssetOtherIntangibleAssets(String assetOtherIntangibleAssets) {
		this.assetOtherIntangibleAssets = assetOtherIntangibleAssets;
	}



	public String getOsLessExciseDuty() {
		return osLessExciseDuty;
	}



	public void setOsLessExciseDuty(String osLessExciseDuty) {
		this.osLessExciseDuty = osLessExciseDuty;
	}



	public String getOsDeductOtherItems() {
		return osDeductOtherItems;
	}



	public void setOsDeductOtherItems(String osDeductOtherItems) {
		this.osDeductOtherItems = osDeductOtherItems;
	}



	public String getOsExportSales() {
		return osExportSales;
	}



	public void setOsExportSales(String osExportSales) {
		this.osExportSales = osExportSales;
	}



	public String getOsNetSales() {
		return osNetSales;
	}



	public void setOsNetSales(String osNetSales) {
		this.osNetSales = osNetSales;
	}



	public String getOsRaisOrFall() {
		return osRaisOrFall;
	}



	public void setOsRaisOrFall(String osRaisOrFall) {
		this.osRaisOrFall = osRaisOrFall;
	}



	public String getOsCostofSales() {
		return osCostofSales;
	}



	public void setOsCostofSales(String osCostofSales) {
		this.osCostofSales = osCostofSales;
	}



	public String getOsCostOfsalesRawMaterialsImport() {
		return osCostOfsalesRawMaterialsImport;
	}



	public void setOsCostOfsalesRawMaterialsImport(String osCostOfsalesRawMaterialsImport) {
		this.osCostOfsalesRawMaterialsImport = osCostOfsalesRawMaterialsImport;
	}



	public String getOsCostOfsalesRawMaterialsIndi() {
		return osCostOfsalesRawMaterialsIndi;
	}



	public void setOsCostOfsalesRawMaterialsIndi(String osCostOfsalesRawMaterialsIndi) {
		this.osCostOfsalesRawMaterialsIndi = osCostOfsalesRawMaterialsIndi;
	}



	public String getOsOtherSpares() {
		return osOtherSpares;
	}



	public void setOsOtherSpares(String osOtherSpares) {
		this.osOtherSpares = osOtherSpares;
	}



	public String getOsOtherSparesImported() {
		return osOtherSparesImported;
	}



	public void setOsOtherSparesImported(String osOtherSparesImported) {
		this.osOtherSparesImported = osOtherSparesImported;
	}



	public String getOsOtherSparesIndi() {
		return osOtherSparesIndi;
	}



	public void setOsOtherSparesIndi(String osOtherSparesIndi) {
		this.osOtherSparesIndi = osOtherSparesIndi;
	}



	public String getOsPowerFuel() {
		return osPowerFuel;
	}



	public void setOsPowerFuel(String osPowerFuel) {
		this.osPowerFuel = osPowerFuel;
	}



	public String getOsMfgExp() {
		return osMfgExp;
	}



	public void setOsMfgExp(String osMfgExp) {
		this.osMfgExp = osMfgExp;
	}



	public String getOsSubTotalDep() {
		return osSubTotalDep;
	}



	public void setOsSubTotalDep(String osSubTotalDep) {
		this.osSubTotalDep = osSubTotalDep;
	}



	public String getOsSubTotalAddStocks() {
		return osSubTotalAddStocks;
	}



	public void setOsSubTotalAddStocks(String osSubTotalAddStocks) {
		this.osSubTotalAddStocks = osSubTotalAddStocks;
	}



	public String getOsAddOpStockes() {
		return osAddOpStockes;
	}



	public void setOsAddOpStockes(String osAddOpStockes) {
		this.osAddOpStockes = osAddOpStockes;
	}



	public String getOsCostOfProduction() {
		return osCostOfProduction;
	}



	public void setOsCostOfProduction(String osCostOfProduction) {
		this.osCostOfProduction = osCostOfProduction;
	}



	public String getOsDiductStockes() {
		return osDiductStockes;
	}



	public void setOsDiductStockes(String osDiductStockes) {
		this.osDiductStockes = osDiductStockes;
	}



	public String getOsSubTotalAddOpStockesFp() {
		return osSubTotalAddOpStockesFp;
	}



	public void setOsSubTotalAddOpStockesFp(String osSubTotalAddOpStockesFp) {
		this.osSubTotalAddOpStockesFp = osSubTotalAddOpStockesFp;
	}



	public String getOsSubTotalCostOfSales() {
		return osSubTotalCostOfSales;
	}



	public void setOsSubTotalCostOfSales(String osSubTotalCostOfSales) {
		this.osSubTotalCostOfSales = osSubTotalCostOfSales;
	}



	public String getOsDeductClStockOfFg() {
		return osDeductClStockOfFg;
	}



	public void setOsDeductClStockOfFg(String osDeductClStockOfFg) {
		this.osDeductClStockOfFg = osDeductClStockOfFg;
	}



	public String getOsSubtotalGenAndAdmn() {
		return osSubtotalGenAndAdmn;
	}



	public void setOsSubtotalGenAndAdmn(String osSubtotalGenAndAdmn) {
		this.osSubtotalGenAndAdmn = osSubtotalGenAndAdmn;
	}



	public String getOsSellingAndDistributionEx() {
		return osSellingAndDistributionEx;
	}



	public void setOsSellingAndDistributionEx(String osSellingAndDistributionEx) {
		this.osSellingAndDistributionEx = osSellingAndDistributionEx;
	}



	public String getOsSubtotalIncome() {
		return osSubtotalIncome;
	}



	public void setOsSubtotalIncome(String osSubtotalIncome) {
		this.osSubtotalIncome = osSubtotalIncome;
	}



	public String getOsSubTotalExpenses() {
		return osSubTotalExpenses;
	}



	public void setOsSubTotalExpenses(String osSubTotalExpenses) {
		this.osSubTotalExpenses = osSubTotalExpenses;
	}



	public String getOsNetOfIncExp() {
		return osNetOfIncExp;
	}



	public void setOsNetOfIncExp(String osNetOfIncExp) {
		this.osNetOfIncExp = osNetOfIncExp;
	}



	public String getOsDeductotherOp() {
		return osDeductotherOp;
	}



	public void setOsDeductotherOp(String osDeductotherOp) {
		this.osDeductotherOp = osDeductotherOp;
	}



	public String getOsProfitBeforeTaxLoss() {
		return osProfitBeforeTaxLoss;
	}



	public void setOsProfitBeforeTaxLoss(String osProfitBeforeTaxLoss) {
		this.osProfitBeforeTaxLoss = osProfitBeforeTaxLoss;
	}



	public String getOsNetProfitLoss() {
		return osNetProfitLoss;
	}



	public void setOsNetProfitLoss(String osNetProfitLoss) {
		this.osNetProfitLoss = osNetProfitLoss;
	}



	public String getOsProfitBeforeInterest() {
		return osProfitBeforeInterest;
	}



	public void setOsProfitBeforeInterest(String osProfitBeforeInterest) {
		this.osProfitBeforeInterest = osProfitBeforeInterest;
	}



	public String getOsProfitAfterInterest() {
		return osProfitAfterInterest;
	}



	public void setOsProfitAfterInterest(String osProfitAfterInterest) {
		this.osProfitAfterInterest = osProfitAfterInterest;
	}



	public String getOsExpenseAmortised() {
		return osExpenseAmortised;
	}



	public void setOsExpenseAmortised(String osExpenseAmortised) {
		this.osExpenseAmortised = osExpenseAmortised;
	}



	public String getOsOtherImcomeNeedToCheck() {
		return osOtherImcomeNeedToCheck;
	}



	public void setOsOtherImcomeNeedToCheck(String osOtherImcomeNeedToCheck) {
		this.osOtherImcomeNeedToCheck = osOtherImcomeNeedToCheck;
	}



	public String getOsRetainProfit() {
		return osRetainProfit;
	}



	public void setOsRetainProfit(String osRetainProfit) {
		this.osRetainProfit = osRetainProfit;
	}



	public String getOsEquitydivident() {
		return osEquitydivident;
	}



	public void setOsEquitydivident(String osEquitydivident) {
		this.osEquitydivident = osEquitydivident;
	}



	public String getOsRetainProfitPercentage() {
		return osRetainProfitPercentage;
	}



	public void setOsRetainProfitPercentage(String osRetainProfitPercentage) {
		this.osRetainProfitPercentage = osRetainProfitPercentage;
	}



	public String getLiaFromApplicant() {
		return liaFromApplicant;
	}



	public void setLiaFromApplicant(String liaFromApplicant) {
		this.liaFromApplicant = liaFromApplicant;
	}



	public String getLiaFromOther() {
		return liaFromOther;
	}



	public void setLiaFromOther(String liaFromOther) {
		this.liaFromOther = liaFromOther;
	}



	public String getLiaOfWhichBd() {
		return liaOfWhichBd;
	}



	public void setLiaOfWhichBd(String liaOfWhichBd) {
		this.liaOfWhichBd = liaOfWhichBd;
	}



	public String getLiaSubTotalA() {
		return liaSubTotalA;
	}



	public void setLiaSubTotalA(String liaSubTotalA) {
		this.liaSubTotalA = liaSubTotalA;
	}



	public String getLiaShortTermBorrowings() {
		return liaShortTermBorrowings;
	}



	public void setLiaShortTermBorrowings(String liaShortTermBorrowings) {
		this.liaShortTermBorrowings = liaShortTermBorrowings;
	}



	public String getLiaAdvancePaymentsCust() {
		return liaAdvancePaymentsCust;
	}



	public void setLiaAdvancePaymentsCust(String liaAdvancePaymentsCust) {
		this.liaAdvancePaymentsCust = liaAdvancePaymentsCust;
	}



	public String getLiaProvisionTaxes() {
		return liaProvisionTaxes;
	}



	public void setLiaProvisionTaxes(String liaProvisionTaxes) {
		this.liaProvisionTaxes = liaProvisionTaxes;
	}



	public String getLiaDividantPay() {
		return liaDividantPay;
	}



	public void setLiaDividantPay(String liaDividantPay) {
		this.liaDividantPay = liaDividantPay;
	}



	public String getLiaOtherStatutory() {
		return liaOtherStatutory;
	}



	public void setLiaOtherStatutory(String liaOtherStatutory) {
		this.liaOtherStatutory = liaOtherStatutory;
	}



	public String getLiaDepositeOftermLoan() {
		return liaDepositeOftermLoan;
	}



	public void setLiaDepositeOftermLoan(String liaDepositeOftermLoan) {
		this.liaDepositeOftermLoan = liaDepositeOftermLoan;
	}



	public String getLiaSubTotalB() {
		return liaSubTotalB;
	}



	public void setLiaSubTotalB(String liaSubTotalB) {
		this.liaSubTotalB = liaSubTotalB;
	}



	public String getLiaTotalCurrLiability() {
		return liaTotalCurrLiability;
	}



	public void setLiaTotalCurrLiability(String liaTotalCurrLiability) {
		this.liaTotalCurrLiability = liaTotalCurrLiability;
	}



	public String getLiaDebenturs() {
		return liaDebenturs;
	}



	public void setLiaDebenturs(String liaDebenturs) {
		this.liaDebenturs = liaDebenturs;
	}



	public String getLiaPrefrenceShares() {
		return liaPrefrenceShares;
	}



	public void setLiaPrefrenceShares(String liaPrefrenceShares) {
		this.liaPrefrenceShares = liaPrefrenceShares;
	}



	public String getLiaTermSecured() {
		return liaTermSecured;
	}



	public void setLiaTermSecured(String liaTermSecured) {
		this.liaTermSecured = liaTermSecured;
	}



	public String getLiaTermUnSecured() {
		return liaTermUnSecured;
	}



	public void setLiaTermUnSecured(String liaTermUnSecured) {
		this.liaTermUnSecured = liaTermUnSecured;
	}



	public String getLiaDefferedPay() {
		return liaDefferedPay;
	}



	public void setLiaDefferedPay(String liaDefferedPay) {
		this.liaDefferedPay = liaDefferedPay;
	}



	public String getLiaOtherTermLia() {
		return liaOtherTermLia;
	}



	public void setLiaOtherTermLia(String liaOtherTermLia) {
		this.liaOtherTermLia = liaOtherTermLia;
	}



	public String getLiaTermLia() {
		return liaTermLia;
	}



	public void setLiaTermLia(String liaTermLia) {
		this.liaTermLia = liaTermLia;
	}



	public String getLiaNonCurLia() {
		return liaNonCurLia;
	}



	public void setLiaNonCurLia(String liaNonCurLia) {
		this.liaNonCurLia = liaNonCurLia;
	}



	public String getLiaUnsecLoansPromoters() {
		return liaUnsecLoansPromoters;
	}



	public void setLiaUnsecLoansPromoters(String liaUnsecLoansPromoters) {
		this.liaUnsecLoansPromoters = liaUnsecLoansPromoters;
	}



	public String getLiaUnsecureLoansOther() {
		return liaUnsecureLoansOther;
	}



	public void setLiaUnsecureLoansOther(String liaUnsecureLoansOther) {
		this.liaUnsecureLoansOther = liaUnsecureLoansOther;
	}



	public String getLiaOtherloans() {
		return liaOtherloans;
	}



	public void setLiaOtherloans(String liaOtherloans) {
		this.liaOtherloans = liaOtherloans;
	}



	public String getLiaTotalOutsideLia() {
		return liaTotalOutsideLia;
	}



	public void setLiaTotalOutsideLia(String liaTotalOutsideLia) {
		this.liaTotalOutsideLia = liaTotalOutsideLia;
	}



	public String getLiaMinorityInterest() {
		return liaMinorityInterest;
	}



	public void setLiaMinorityInterest(String liaMinorityInterest) {
		this.liaMinorityInterest = liaMinorityInterest;
	}



	public String getLiaGeneralRes() {
		return liaGeneralRes;
	}



	public void setLiaGeneralRes(String liaGeneralRes) {
		this.liaGeneralRes = liaGeneralRes;
	}



	public String getLiaRevalution() {
		return liaRevalution;
	}



	public void setLiaRevalution(String liaRevalution) {
		this.liaRevalution = liaRevalution;
	}



	public String getLiaSurPlus() {
		return liaSurPlus;
	}



	public void setLiaSurPlus(String liaSurPlus) {
		this.liaSurPlus = liaSurPlus;
	}



	public String getLiaOtherSpecify() {
		return liaOtherSpecify;
	}



	public void setLiaOtherSpecify(String liaOtherSpecify) {
		this.liaOtherSpecify = liaOtherSpecify;
	}



	public String getLiaNetworth() {
		return liaNetworth;
	}



	public void setLiaNetworth(String liaNetworth) {
		this.liaNetworth = liaNetworth;
	}



	public String getLiaOtherIncomeNeedToCheck() {
		return liaOtherIncomeNeedToCheck;
	}



	public void setLiaOtherIncomeNeedToCheck(String liaOtherIncomeNeedToCheck) {
		this.liaOtherIncomeNeedToCheck = liaOtherIncomeNeedToCheck;
	}



	public String getLiaTotalLiabilities() {
		return liaTotalLiabilities;
	}



	public void setLiaTotalLiabilities(String liaTotalLiabilities) {
		this.liaTotalLiabilities = liaTotalLiabilities;
	}



	public String getAssetInvestments() {
		return assetInvestments;
	}



	public void setAssetInvestments(String assetInvestments) {
		this.assetInvestments = assetInvestments;
	}



	public String getAssetInvestmentsGov() {
		return assetInvestmentsGov;
	}



	public void setAssetInvestmentsGov(String assetInvestmentsGov) {
		this.assetInvestmentsGov = assetInvestmentsGov;
	}



	public String getAssetInvestmentsFixed() {
		return assetInvestmentsFixed;
	}



	public void setAssetInvestmentsFixed(String assetInvestmentsFixed) {
		this.assetInvestmentsFixed = assetInvestmentsFixed;
	}



	public String getAssetExportRec() {
		return assetExportRec;
	}



	public void setAssetExportRec(String assetExportRec) {
		this.assetExportRec = assetExportRec;
	}



	public String getAssetInstalmentsDef() {
		return assetInstalmentsDef;
	}



	public void setAssetInstalmentsDef(String assetInstalmentsDef) {
		this.assetInstalmentsDef = assetInstalmentsDef;
	}



	public String getAssetRawMaterials() {
		return assetRawMaterials;
	}



	public void setAssetRawMaterials(String assetRawMaterials) {
		this.assetRawMaterials = assetRawMaterials;
	}



	public String getAssetRawMaterialsImp() {
		return assetRawMaterialsImp;
	}



	public void setAssetRawMaterialsImp(String assetRawMaterialsImp) {
		this.assetRawMaterialsImp = assetRawMaterialsImp;
	}



	public String getAssetRawMaterialIndi() {
		return assetRawMaterialIndi;
	}



	public void setAssetRawMaterialIndi(String assetRawMaterialIndi) {
		this.assetRawMaterialIndi = assetRawMaterialIndi;
	}



	public String getAssetStockesInPro() {
		return assetStockesInPro;
	}



	public void setAssetStockesInPro(String assetStockesInPro) {
		this.assetStockesInPro = assetStockesInPro;
	}



	public String getAssetFinishedGoods() {
		return assetFinishedGoods;
	}



	public void setAssetFinishedGoods(String assetFinishedGoods) {
		this.assetFinishedGoods = assetFinishedGoods;
	}



	public String getAssetOtherConsumSp() {
		return assetOtherConsumSp;
	}



	public void setAssetOtherConsumSp(String assetOtherConsumSp) {
		this.assetOtherConsumSp = assetOtherConsumSp;
	}



	public String getAssetOtherConsumSpIm() {
		return assetOtherConsumSpIm;
	}



	public void setAssetOtherConsumSpIm(String assetOtherConsumSpIm) {
		this.assetOtherConsumSpIm = assetOtherConsumSpIm;
	}



	public String getAssetOtherConsumSpIn() {
		return assetOtherConsumSpIn;
	}



	public void setAssetOtherConsumSpIn(String assetOtherConsumSpIn) {
		this.assetOtherConsumSpIn = assetOtherConsumSpIn;
	}



	public String getAssetAdvancePayTax() {
		return assetAdvancePayTax;
	}



	public void setAssetAdvancePayTax(String assetAdvancePayTax) {
		this.assetAdvancePayTax = assetAdvancePayTax;
	}



	public String getAssetTotalCurAssets() {
		return assetTotalCurAssets;
	}



	public void setAssetTotalCurAssets(String assetTotalCurAssets) {
		this.assetTotalCurAssets = assetTotalCurAssets;
	}



	public String getAssetGrossblockWIP() {
		return assetGrossblockWIP;
	}



	public void setAssetGrossblockWIP(String assetGrossblockWIP) {
		this.assetGrossblockWIP = assetGrossblockWIP;
	}



	public String getAssetGrossblockWIPBuild() {
		return assetGrossblockWIPBuild;
	}



	public void setAssetGrossblockWIPBuild(String assetGrossblockWIPBuild) {
		this.assetGrossblockWIPBuild = assetGrossblockWIPBuild;
	}



	public String getAssetGrossblockWIPPlant() {
		return assetGrossblockWIPPlant;
	}



	public void setAssetGrossblockWIPPlant(String assetGrossblockWIPPlant) {
		this.assetGrossblockWIPPlant = assetGrossblockWIPPlant;
	}



	public String getAssetDepriciationToDate() {
		return assetDepriciationToDate;
	}



	public void setAssetDepriciationToDate(String assetDepriciationToDate) {
		this.assetDepriciationToDate = assetDepriciationToDate;
	}



	public String getAssetImpairmentOfAsset() {
		return assetImpairmentOfAsset;
	}



	public void setAssetImpairmentOfAsset(String assetImpairmentOfAsset) {
		this.assetImpairmentOfAsset = assetImpairmentOfAsset;
	}



	public String getAssetInvestmentInSubd() {
		return assetInvestmentInSubd;
	}



	public void setAssetInvestmentInSubd(String assetInvestmentInSubd) {
		this.assetInvestmentInSubd = assetInvestmentInSubd;
	}



	public String getAssetInvestmentOthers() {
		return assetInvestmentOthers;
	}



	public void setAssetInvestmentOthers(String assetInvestmentOthers) {
		this.assetInvestmentOthers = assetInvestmentOthers;
	}



	public String getAssetAdvSupp() {
		return assetAdvSupp;
	}



	public void setAssetAdvSupp(String assetAdvSupp) {
		this.assetAdvSupp = assetAdvSupp;
	}



	public String getAssetDefferedRec() {
		return assetDefferedRec;
	}



	public void setAssetDefferedRec(String assetDefferedRec) {
		this.assetDefferedRec = assetDefferedRec;
	}



	public String getAssetOthersIV() {
		return assetOthersIV;
	}



	public void setAssetOthersIV(String assetOthersIV) {
		this.assetOthersIV = assetOthersIV;
	}



	public String getAssetPreOp() {
		return assetPreOp;
	}



	public void setAssetPreOp(String assetPreOp) {
		this.assetPreOp = assetPreOp;
	}



	public String getAssetInTransist() {
		return assetInTransist;
	}



	public void setAssetInTransist(String assetInTransist) {
		this.assetInTransist = assetInTransist;
	}



	public String getAssetothersIVothers() {
		return assetothersIVothers;
	}



	public void setAssetothersIVothers(String assetothersIVothers) {
		this.assetothersIVothers = assetothersIVothers;
	}



	public String getAssetNonConsumable() {
		return assetNonConsumable;
	}



	public void setAssetNonConsumable(String assetNonConsumable) {
		this.assetNonConsumable = assetNonConsumable;
	}



	public String getAssetTotalOtherNonCurAssets() {
		return assetTotalOtherNonCurAssets;
	}



	public void setAssetTotalOtherNonCurAssets(String assetTotalOtherNonCurAssets) {
		this.assetTotalOtherNonCurAssets = assetTotalOtherNonCurAssets;
	}



	public String getAssetpatent() {
		return assetpatent;
	}



	public void setAssetpatent(String assetpatent) {
		this.assetpatent = assetpatent;
	}



	public String getAssetGoddWill() {
		return assetGoddWill;
	}



	public void setAssetGoddWill(String assetGoddWill) {
		this.assetGoddWill = assetGoddWill;
	}



	public String getAssetPriliminaryExp() {
		return assetPriliminaryExp;
	}



	public void setAssetPriliminaryExp(String assetPriliminaryExp) {
		this.assetPriliminaryExp = assetPriliminaryExp;
	}



	public String getAssetDoubtFulExp() {
		return assetDoubtFulExp;
	}



	public void setAssetDoubtFulExp(String assetDoubtFulExp) {
		this.assetDoubtFulExp = assetDoubtFulExp;
	}



	public String getAssetothersIntangible() {
		return assetothersIntangible;
	}



	public void setAssetothersIntangible(String assetothersIntangible) {
		this.assetothersIntangible = assetothersIntangible;
	}



	public String getAssetTotalAssets() {
		return assetTotalAssets;
	}



	public void setAssetTotalAssets(String assetTotalAssets) {
		this.assetTotalAssets = assetTotalAssets;
	}



	public String getAssetTangibleNetworth() {
		return assetTangibleNetworth;
	}



	public void setAssetTangibleNetworth(String assetTangibleNetworth) {
		this.assetTangibleNetworth = assetTangibleNetworth;
	}



	public String getAssetNetWorkingCapital() {
		return assetNetWorkingCapital;
	}



	public void setAssetNetWorkingCapital(String assetNetWorkingCapital) {
		this.assetNetWorkingCapital = assetNetWorkingCapital;
	}



	


	



	public String getAssetCurrRatio() {
		return assetCurrRatio;
	}



	public void setAssetCurrRatio(String assetCurrRatio) {
		this.assetCurrRatio = assetCurrRatio;
	}



	



	public String getAssetTotalOutSideLia() {
		return assetTotalOutSideLia;
	}



	public void setAssetTotalOutSideLia(String assetTotalOutSideLia) {
		this.assetTotalOutSideLia = assetTotalOutSideLia;
	}



	public String getAssetTotalTermLia() {
		return assetTotalTermLia;
	}



	public void setAssetTotalTermLia(String assetTotalTermLia) {
		this.assetTotalTermLia = assetTotalTermLia;
	}



	public String getAssetOtherIncomeNeedToCheck() {
		return assetOtherIncomeNeedToCheck;
	}



	public void setAssetOtherIncomeNeedToCheck(String assetOtherIncomeNeedToCheck) {
		this.assetOtherIncomeNeedToCheck = assetOtherIncomeNeedToCheck;
	}



	public String getOsDividentRate() {
		return osDividentRate;
	}



	public void setOsDividentRate(String osDividentRate) {
		this.osDividentRate = osDividentRate;
	}



	public String getLiaShortTermBorrowingsFromBanks() {
		return liaShortTermBorrowingsFromBanks;
	}



	public void setLiaShortTermBorrowingsFromBanks(String liaShortTermBorrowingsFromBanks) {
		this.liaShortTermBorrowingsFromBanks = liaShortTermBorrowingsFromBanks;
	}



	public String getAssetAdvanceToSupp() {
		return assetAdvanceToSupp;
	}



	public void setAssetAdvanceToSupp(String assetAdvanceToSupp) {
		this.assetAdvanceToSupp = assetAdvanceToSupp;
	}



	public String getAssetInvestment() {
		return assetInvestment;
	}



	public void setAssetInvestment(String assetInvestment) {
		this.assetInvestment = assetInvestment;
	}



	
    
	

}
