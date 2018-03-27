package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;
import com.capitaworld.service.loans.model.LoanApplicationRequest;

public class BalanceSheetDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private double advanceFromCustomers;

	private LoanApplicationRequest applicationId;

	private double capitalAdvance;

	private double capitalRedemptionReserve;

	private double capitalReserve;

	private double capitalWorkInProgress;

	private double contingencyReserve;

	private Long createdBy;

	private Date createdDate;

	private double currentInvestments;

	private double debentureRedemptionReserve;

	private double debentures;

	private double debitBalancePl;

	private double deferredPaymentCredits;

	private double deferredTaxAsset;

	private double deferredTaxLiability;

	private double depositsAndInstallments;

	private double depreciationToDate;

	private double dividendPayable;

	private double exports;
	
	private String financialYearlyStatement;
	
	private double finishedGoods;

	private double fixedAssets;

	private double fixedDepositsWithBanks;

	private double foreignCurrencyTranslationReserve;

	private double generalReserve;

	private double governmentAndOtherTrustee;

	private double grandTotal;

	private double grossFixedAssets;

	private double hedgingReserve;

	private double intangibleAssets;

	private double inventory;
	
	private double investmentInAssociates;

	private double investmentInQuoted;

	private double investmentInSubsidiaries;

	private Boolean isActive;

	private double longTermBorrowing;

	private double longTermLoansAndAdvance;

	private double longTermProvisions;

	private double miscExpences;

	private Long modifiedBy;

	private Date modifiedDate;

	private double nonCurrentInvestments;

	private double ordinaryShareCapital;

	private double otherConsumablesSpares;

	private double otherConsumablesSparesImported;

	private double otherConsumablesSparesIndegenous;

	private double otherNonCurrentLiability;

	private double otherThanExports;

	private double others;

	private double othersCurrentAssets;

	private double othersCurrentLiability;

	private double othersNonCurrentAssets;

	private double othersPlsSpecify;

	private double preferenceShareCapital;

	private double provisionForTax;

	private double rawMaterial;

	private double rawMaterialImported;

	private double rawMaterialIndegenous;

	private double reservesAndSurplus;

	private double revaluationReserve;

	private double securitiesPremiumAccount;

	private double shareApplicationPendingAllotment;

	private double shortTermBorrowings;

	private double shortTermLoansAndAdvances;

	private double statutoryLiabilityDues;

	private double stockInProcess;

	private double surplusInProfitAndLossAccount;

	private double termDeposits;

	private double termLoans;

	private double totalCurrentAndNonCurrentLiability;

	private double tradePayables;

	private double tradeReceivables;

	private double unsecuredLoansFromPromoters;

	private String year;
	
	private Long storageDetailsId;
	
	private Double Others1;

	private Double Others2;

	private Double Others3;

	private Double Others4;

	private Double Others5;

	private Double OthersLiabilities1;

	private Double OthersLiabilities2;

	private Double OthersLiabilities3;

	private Double OthersTotals1;

	private Double OthersTotals2;

	private Double OthersTotals3;

	private Double OthersTotals4;

	private Double OthersTotals5;

	private Double IntangibleAssets1;

	private Double IntangibleAssets2;

	private Double IntangibleAssets3;

	private Double IntangibleAssets4;

	private Double IntangibleAssets5;
	
	private Double IntangibleAssets6;

	private Double AssetsInTransit1;

	private Double AssetsInTransit2;

	private Double AssetsInTransit3;

	private Double AssetsInTransit4;

	private Double OthersInvestment1;

	private Double OthersInvestment2;

	private Double OthersInvestment3;

	private Double OthersInvestment4;

	private Double OthersInvestment5;

	private Double ShortTerm1;

	private Double ShortTerm2;

	private Double ShortTerm3;

	private Double ShortTerm4;

	private Double OtherDetails1;

	private Double OtherDetails2;

	private Double OtherDetails3;

	private Double OtherDetails4;

	private Double OtherDetails5;
	
	private double moneyReceivedAgainstShareWarrants;
	
	private double minorityInterest;
	
	private double termLoansSecured;
	
	private double termLoansUnsecured;
	
	private double unsecuredLoansFromOthers;
	
	private double currentLiabilitiesSecured;
	
	private double currentLiabilitiesUnsecured;
	
	private double impairmentsOfAssests;

	private double preOperativeExpensesPending;
	
	private double assetsInTransit;
	
	private double cashAndCashEquivalents;
	
	private double OtherTermLiability;

	private double OthersTotals;
	
	private double OthersAssetsTransit;
	
	private double OtherInvestments;
	
	private double OtherDetails;
	
	
	public BalanceSheetDetailRequest() {
		super();
		this.advanceFromCustomers  =0.0;
		this.capitalAdvance  =0.0;
		this.capitalRedemptionReserve  =0.0;
		this.capitalReserve  =0.0;
		this.capitalWorkInProgress  =0.0;
		this.contingencyReserve  =0.0;
		this.currentInvestments  =0.0;
		this.debentureRedemptionReserve =0.0;
		this.debentures  =0.0;
		this.debitBalancePl  =0.0;
		this.deferredPaymentCredits  =0.0;
		this.deferredTaxAsset  =0.0;
		this.deferredTaxLiability  =0.0;
		this.depositsAndInstallments  =0.0;
		this.depreciationToDate  =0.0;
		this.dividendPayable =0.0;
		this.exports  =0.0;
		this.finishedGoods  =0.0;
		this.fixedAssets  =0.0;
		this.fixedDepositsWithBanks  =0.0;
		this.foreignCurrencyTranslationReserve  =0.0;
		this.generalReserve  =0.0;
		this.governmentAndOtherTrustee  =0.0;
		this.grandTotal  =0.0;
		this.grossFixedAssets  =0.0;
		this.hedgingReserve  =0.0;
		this.intangibleAssets  =0.0;
		this.inventory  =0.0;
		this.investmentInAssociates =0.0;
		this.investmentInQuoted  =0.0;
		this.investmentInSubsidiaries =0.0;
		this.longTermBorrowing  =0.0;
		this.longTermLoansAndAdvance  =0.0;
		this.longTermProvisions  =0.0;
		this.miscExpences  =0.0;
		this.nonCurrentInvestments  =0.0;
		this.ordinaryShareCapital  =0.0;
		this.otherConsumablesSpares  =0.0;
		this.otherConsumablesSparesImported  =0.0;
		this.otherConsumablesSparesIndegenous  =0.0;
		this.otherNonCurrentLiability  =0.0;
		this.otherThanExports  =0.0;
		this.others  =0.0;
		this.othersCurrentAssets  =0.0;
		this.othersCurrentLiability  =0.0;
		this.othersNonCurrentAssets =0.0;
		this.othersPlsSpecify  =0.0;
		this.preferenceShareCapital  =0.0;
		this.provisionForTax  =0.0;
		this.rawMaterial  =0.0;
		this.rawMaterialImported  =0.0;
		this.rawMaterialIndegenous =0.0;
		this.reservesAndSurplus  =0.0;
		this.revaluationReserve =0.0;
		this.securitiesPremiumAccount  =0.0;
		this.shareApplicationPendingAllotment  =0.0;
		this.shortTermBorrowings =0.0;
		this.shortTermLoansAndAdvances  =0.0;
		this.statutoryLiabilityDues  =0.0;
		this.stockInProcess = 0.0;
		this.surplusInProfitAndLossAccount  =0.0;
		this.termDeposits  =0.0;
		this.termLoans =0.0;
		this.totalCurrentAndNonCurrentLiability  =0.0;
		this.tradePayables  =0.0;
		this.tradeReceivables  =0.0;
		this.unsecuredLoansFromPromoters  =0.0;
		this.Others1 =0.0;
		this.Others2  =0.0;
		this.Others3  =0.0;
		this.Others4  =0.0;
		this.Others5 =0.0;
		this.OthersLiabilities1  =0.0;
		this.OthersLiabilities2  =0.0;
		this.OthersLiabilities3  =0.0;
		this.OthersTotals1  =0.0;
		this.OthersTotals2  =0.0;
		this.OthersTotals3  =0.0;
		this.OthersTotals4 =0.0;
		this.OthersTotals5  =0.0;
		this.IntangibleAssets1  =0.0;
		this.IntangibleAssets2  =0.0;
		this.IntangibleAssets3  =0.0;
		this.IntangibleAssets4  =0.0;
		this.IntangibleAssets5  =0.0;
		this.IntangibleAssets6  =0.0;
		this.AssetsInTransit1  =0.0;
		this.AssetsInTransit2  =0.0;
		this.AssetsInTransit3  =0.0;
		this.AssetsInTransit4 =0.0;
		this.OthersInvestment1 =0.0;
		this.OthersInvestment2 =0.0;
		this.OthersInvestment3  =0.0;
		this.OthersInvestment4  =0.0;
		this.OthersInvestment5  =0.0;
		this.ShortTerm1  =0.0;
		this.ShortTerm2  =0.0;
		this.ShortTerm3  =0.0;
		this.ShortTerm4  =0.0;
		this.OtherDetails1  =0.0;
		this.OtherDetails2  =0.0;
		this.OtherDetails3  =0.0;
		this.OtherDetails4  =0.0;
		this.OtherDetails5 =0.0;
		this.moneyReceivedAgainstShareWarrants =0.0;
		this.minorityInterest  =0.0;
		this.termLoansSecured  =0.0;
		this.termLoansUnsecured  =0.0;
		this.unsecuredLoansFromOthers =0.0;
		this.currentLiabilitiesSecured  =0.0;
		this.currentLiabilitiesUnsecured  =0.0;
		this.impairmentsOfAssests  =0.0;
		this.preOperativeExpensesPending  =0.0;
		this.assetsInTransit  =0.0;
		this.cashAndCashEquivalents =0.0;
		this.OtherTermLiability  =0.0;
		this.OthersTotals =0.0;
		this.OthersAssetsTransit =0.0;
		this.OtherInvestments  =0.0;
		this.OtherDetails  =0.0;
	}

	public Long getStorageDetailsId() {
		return storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAdvanceFromCustomers() {
		return advanceFromCustomers;
	}

	public void setAdvanceFromCustomers(double advanceFromCustomers) {
		this.advanceFromCustomers = advanceFromCustomers;
	}

	public LoanApplicationRequest getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationRequest applicationId) {
		this.applicationId = applicationId;
	}

	public double getCapitalAdvance() {
		return capitalAdvance;
	}

	public void setCapitalAdvance(double capitalAdvance) {
		this.capitalAdvance = capitalAdvance;
	}

	public double getCapitalRedemptionReserve() {
		return capitalRedemptionReserve;
	}

	public void setCapitalRedemptionReserve(double capitalRedemptionReserve) {
		this.capitalRedemptionReserve = capitalRedemptionReserve;
	}

	public double getCapitalReserve() {
		return capitalReserve;
	}

	public void setCapitalReserve(double capitalReserve) {
		this.capitalReserve = capitalReserve;
	}

	public double getCapitalWorkInProgress() {
		return capitalWorkInProgress;
	}

	public void setCapitalWorkInProgress(double capitalWorkInProgress) {
		this.capitalWorkInProgress = capitalWorkInProgress;
	}

	public double getContingencyReserve() {
		return contingencyReserve;
	}

	public void setContingencyReserve(double contingencyReserve) {
		this.contingencyReserve = contingencyReserve;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public double getCurrentInvestments() {
		return currentInvestments;
	}

	public void setCurrentInvestments(double currentInvestments) {
		this.currentInvestments = currentInvestments;
	}

	public double getDebentureRedemptionReserve() {
		return debentureRedemptionReserve;
	}

	public void setDebentureRedemptionReserve(double debentureRedemptionReserve) {
		this.debentureRedemptionReserve = debentureRedemptionReserve;
	}

	public double getDebentures() {
		return debentures;
	}

	public void setDebentures(double debentures) {
		this.debentures = debentures;
	}

	public double getDebitBalancePl() {
		return debitBalancePl;
	}

	public void setDebitBalancePl(double debitBalancePl) {
		this.debitBalancePl = debitBalancePl;
	}

	public double getDeferredPaymentCredits() {
		return deferredPaymentCredits;
	}

	public void setDeferredPaymentCredits(double deferredPaymentCredits) {
		this.deferredPaymentCredits = deferredPaymentCredits;
	}

	public double getDeferredTaxAsset() {
		return deferredTaxAsset;
	}

	public void setDeferredTaxAsset(double deferredTaxAsset) {
		this.deferredTaxAsset = deferredTaxAsset;
	}

	public double getDeferredTaxLiability() {
		return deferredTaxLiability;
	}

	public void setDeferredTaxLiability(double deferredTaxLiability) {
		this.deferredTaxLiability = deferredTaxLiability;
	}

	public double getDepositsAndInstallments() {
		return depositsAndInstallments;
	}

	public void setDepositsAndInstallments(double depositsAndInstallments) {
		this.depositsAndInstallments = depositsAndInstallments;
	}

	public double getDepreciationToDate() {
		return depreciationToDate;
	}

	public void setDepreciationToDate(double depreciationToDate) {
		this.depreciationToDate = depreciationToDate;
	}

	public double getDividendPayable() {
		return dividendPayable;
	}

	public void setDividendPayable(double dividendPayable) {
		this.dividendPayable = dividendPayable;
	}

	public double getExports() {
		return exports;
	}

	public void setExports(double exports) {
		this.exports = exports;
	}
	
	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}

	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
	}

	public double getFinishedGoods() {
		return finishedGoods;
	}

	public void setFinishedGoods(double finishedGoods) {
		this.finishedGoods = finishedGoods;
	}

	public double getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public double getFixedDepositsWithBanks() {
		return fixedDepositsWithBanks;
	}

	public void setFixedDepositsWithBanks(double fixedDepositsWithBanks) {
		this.fixedDepositsWithBanks = fixedDepositsWithBanks;
	}

	public double getForeignCurrencyTranslationReserve() {
		return foreignCurrencyTranslationReserve;
	}

	public void setForeignCurrencyTranslationReserve(double foreignCurrencyTranslationReserve) {
		this.foreignCurrencyTranslationReserve = foreignCurrencyTranslationReserve;
	}

	public double getGeneralReserve() {
		return generalReserve;
	}

	public void setGeneralReserve(double generalReserve) {
		this.generalReserve = generalReserve;
	}

	public double getGovernmentAndOtherTrustee() {
		return governmentAndOtherTrustee;
	}

	public void setGovernmentAndOtherTrustee(double governmentAndOtherTrustee) {
		this.governmentAndOtherTrustee = governmentAndOtherTrustee;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public double getGrossFixedAssets() {
		return grossFixedAssets;
	}

	public void setGrossFixedAssets(double grossFixedAssets) {
		this.grossFixedAssets = grossFixedAssets;
	}

	public double getHedgingReserve() {
		return hedgingReserve;
	}

	public void setHedgingReserve(double hedgingReserve) {
		this.hedgingReserve = hedgingReserve;
	}

	public double getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(double intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public double getInventory() {
		return inventory;
	}

	public void setInventory(double inventory) {
		this.inventory = inventory;
	}

	public double getInvestmentInAssociates() {
		return investmentInAssociates;
	}

	public void setInvestmentInAssociates(double investmentInAssociates) {
		this.investmentInAssociates = investmentInAssociates;
	}

	public double getInvestmentInQuoted() {
		return investmentInQuoted;
	}

	public void setInvestmentInQuoted(double investmentInQuoted) {
		this.investmentInQuoted = investmentInQuoted;
	}

	public double getInvestmentInSubsidiaries() {
		return investmentInSubsidiaries;
	}

	public void setInvestmentInSubsidiaries(double investmentInSubsidiaries) {
		this.investmentInSubsidiaries = investmentInSubsidiaries;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public double getLongTermBorrowing() {
		return longTermBorrowing;
	}

	public void setLongTermBorrowing(double longTermBorrowing) {
		this.longTermBorrowing = longTermBorrowing;
	}

	public double getLongTermLoansAndAdvance() {
		return longTermLoansAndAdvance;
	}

	public void setLongTermLoansAndAdvance(double longTermLoansAndAdvance) {
		this.longTermLoansAndAdvance = longTermLoansAndAdvance;
	}

	public double getLongTermProvisions() {
		return longTermProvisions;
	}

	public void setLongTermProvisions(double longTermProvisions) {
		this.longTermProvisions = longTermProvisions;
	}

	public double getMiscExpences() {
		return miscExpences;
	}

	public void setMiscExpences(double miscExpences) {
		this.miscExpences = miscExpences;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public double getNonCurrentInvestments() {
		return nonCurrentInvestments;
	}

	public void setNonCurrentInvestments(double nonCurrentInvestments) {
		this.nonCurrentInvestments = nonCurrentInvestments;
	}

	public double getOrdinaryShareCapital() {
		return ordinaryShareCapital;
	}

	public void setOrdinaryShareCapital(double ordinaryShareCapital) {
		this.ordinaryShareCapital = ordinaryShareCapital;
	}

	public double getOtherConsumablesSpares() {
		return otherConsumablesSpares;
	}

	public void setOtherConsumablesSpares(double otherConsumablesSpares) {
		this.otherConsumablesSpares = otherConsumablesSpares;
	}

	public double getOtherConsumablesSparesImported() {
		return otherConsumablesSparesImported;
	}

	public void setOtherConsumablesSparesImported(double otherConsumablesSparesImported) {
		this.otherConsumablesSparesImported = otherConsumablesSparesImported;
	}

	public double getOtherConsumablesSparesIndegenous() {
		return otherConsumablesSparesIndegenous;
	}

	public void setOtherConsumablesSparesIndegenous(double otherConsumablesSparesIndegenous) {
		this.otherConsumablesSparesIndegenous = otherConsumablesSparesIndegenous;
	}

	public double getOtherNonCurrentLiability() {
		return otherNonCurrentLiability;
	}

	public void setOtherNonCurrentLiability(double otherNonCurrentLiability) {
		this.otherNonCurrentLiability = otherNonCurrentLiability;
	}

	public double getOtherThanExports() {
		return otherThanExports;
	}

	public void setOtherThanExports(double otherThanExports) {
		this.otherThanExports = otherThanExports;
	}

	public double getOthers() {
		return others;
	}

	public void setOthers(double others) {
		this.others = others;
	}

	public double getOthersCurrentAssets() {
		return othersCurrentAssets;
	}

	public void setOthersCurrentAssets(double othersCurrentAssets) {
		this.othersCurrentAssets = othersCurrentAssets;
	}

	public double getOthersCurrentLiability() {
		return othersCurrentLiability;
	}

	public void setOthersCurrentLiability(double othersCurrentLiability) {
		this.othersCurrentLiability = othersCurrentLiability;
	}

	public double getOthersNonCurrentAssets() {
		return othersNonCurrentAssets;
	}

	public void setOthersNonCurrentAssets(double othersNonCurrentAssets) {
		this.othersNonCurrentAssets = othersNonCurrentAssets;
	}

	public double getOthersPlsSpecify() {
		return othersPlsSpecify;
	}

	public void setOthersPlsSpecify(double othersPlsSpecify) {
		this.othersPlsSpecify = othersPlsSpecify;
	}

	public double getPreferenceShareCapital() {
		return preferenceShareCapital;
	}

	public void setPreferenceShareCapital(double preferenceShareCapital) {
		this.preferenceShareCapital = preferenceShareCapital;
	}

	public double getProvisionForTax() {
		return provisionForTax;
	}

	public void setProvisionForTax(double provisionForTax) {
		this.provisionForTax = provisionForTax;
	}

	public double getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(double rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public double getRawMaterialImported() {
		return rawMaterialImported;
	}

	public void setRawMaterialImported(double rawMaterialImported) {
		this.rawMaterialImported = rawMaterialImported;
	}

	public double getRawMaterialIndegenous() {
		return rawMaterialIndegenous;
	}

	public void setRawMaterialIndegenous(double rawMaterialIndegenous) {
		this.rawMaterialIndegenous = rawMaterialIndegenous;
	}

	public double getReservesAndSurplus() {
		return reservesAndSurplus;
	}

	public void setReservesAndSurplus(double reservesAndSurplus) {
		this.reservesAndSurplus = reservesAndSurplus;
	}

	public double getRevaluationReserve() {
		return revaluationReserve;
	}

	public void setRevaluationReserve(double revaluationReserve) {
		this.revaluationReserve = revaluationReserve;
	}

	public double getSecuritiesPremiumAccount() {
		return securitiesPremiumAccount;
	}

	public void setSecuritiesPremiumAccount(double securitiesPremiumAccount) {
		this.securitiesPremiumAccount = securitiesPremiumAccount;
	}

	public double getShareApplicationPendingAllotment() {
		return shareApplicationPendingAllotment;
	}

	public void setShareApplicationPendingAllotment(double shareApplicationPendingAllotment) {
		this.shareApplicationPendingAllotment = shareApplicationPendingAllotment;
	}

	public double getShortTermBorrowings() {
		return shortTermBorrowings;
	}

	public void setShortTermBorrowings(double shortTermBorrowings) {
		this.shortTermBorrowings = shortTermBorrowings;
	}

	public double getShortTermLoansAndAdvances() {
		return shortTermLoansAndAdvances;
	}

	public void setShortTermLoansAndAdvances(double shortTermLoansAndAdvances) {
		this.shortTermLoansAndAdvances = shortTermLoansAndAdvances;
	}

	public double getStatutoryLiabilityDues() {
		return statutoryLiabilityDues;
	}

	public void setStatutoryLiabilityDues(double statutoryLiabilityDues) {
		this.statutoryLiabilityDues = statutoryLiabilityDues;
	}

	public double getStockInProcess() {
		return stockInProcess;
	}

	public void setStockInProcess(double stockInProcess) {
		this.stockInProcess = stockInProcess;
	}

	public double getSurplusInProfitAndLossAccount() {
		return surplusInProfitAndLossAccount;
	}

	public void setSurplusInProfitAndLossAccount(double surplusInProfitAndLossAccount) {
		this.surplusInProfitAndLossAccount = surplusInProfitAndLossAccount;
	}

	public double getTermDeposits() {
		return termDeposits;
	}

	public void setTermDeposits(double termDeposits) {
		this.termDeposits = termDeposits;
	}

	public double getTermLoans() {
		return termLoans;
	}

	public void setTermLoans(double termLoans) {
		this.termLoans = termLoans;
	}

	public double getTotalCurrentAndNonCurrentLiability() {
		return totalCurrentAndNonCurrentLiability;
	}

	public void setTotalCurrentAndNonCurrentLiability(double totalCurrentAndNonCurrentLiability) {
		this.totalCurrentAndNonCurrentLiability = totalCurrentAndNonCurrentLiability;
	}

	public double getTradePayables() {
		return tradePayables;
	}

	public void setTradePayables(double tradePayables) {
		this.tradePayables = tradePayables;
	}

	public double getTradeReceivables() {
		return tradeReceivables;
	}

	public void setTradeReceivables(double tradeReceivables) {
		this.tradeReceivables = tradeReceivables;
	}

	public double getUnsecuredLoansFromPromoters() {
		return unsecuredLoansFromPromoters;
	}

	public void setUnsecuredLoansFromPromoters(double unsecuredLoansFromPromoters) {
		this.unsecuredLoansFromPromoters = unsecuredLoansFromPromoters;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getOthers1() {
		return Others1;
	}

	public void setOthers1(Double others1) {
		Others1 = others1;
	}

	public Double getOthers2() {
		return Others2;
	}

	public void setOthers2(Double others2) {
		Others2 = others2;
	}

	public Double getOthers3() {
		return Others3;
	}

	public void setOthers3(Double others3) {
		Others3 = others3;
	}

	public Double getOthers4() {
		return Others4;
	}

	public void setOthers4(Double others4) {
		Others4 = others4;
	}

	public Double getOthers5() {
		return Others5;
	}

	public void setOthers5(Double others5) {
		Others5 = others5;
	}

	public Double getOthersLiabilities1() {
		return OthersLiabilities1;
	}

	public void setOthersLiabilities1(Double othersLiabilities1) {
		OthersLiabilities1 = othersLiabilities1;
	}

	public Double getOthersLiabilities2() {
		return OthersLiabilities2;
	}

	public void setOthersLiabilities2(Double othersLiabilities2) {
		OthersLiabilities2 = othersLiabilities2;
	}

	public Double getOthersLiabilities3() {
		return OthersLiabilities3;
	}

	public void setOthersLiabilities3(Double othersLiabilities3) {
		OthersLiabilities3 = othersLiabilities3;
	}

	public Double getOthersTotals1() {
		return OthersTotals1;
	}

	public void setOthersTotals1(Double othersTotals1) {
		OthersTotals1 = othersTotals1;
	}

	public Double getOthersTotals2() {
		return OthersTotals2;
	}

	public void setOthersTotals2(Double othersTotals2) {
		OthersTotals2 = othersTotals2;
	}

	public Double getOthersTotals3() {
		return OthersTotals3;
	}

	public void setOthersTotals3(Double othersTotals3) {
		OthersTotals3 = othersTotals3;
	}

	public Double getOthersTotals4() {
		return OthersTotals4;
	}

	public void setOthersTotals4(Double othersTotals4) {
		OthersTotals4 = othersTotals4;
	}

	public Double getOthersTotals5() {
		return OthersTotals5;
	}

	public void setOthersTotals5(Double othersTotals5) {
		OthersTotals5 = othersTotals5;
	}

	public Double getIntangibleAssets1() {
		return IntangibleAssets1;
	}

	public void setIntangibleAssets1(Double intangibleAssets1) {
		IntangibleAssets1 = intangibleAssets1;
	}

	public Double getIntangibleAssets2() {
		return IntangibleAssets2;
	}

	public void setIntangibleAssets2(Double intangibleAssets2) {
		IntangibleAssets2 = intangibleAssets2;
	}

	public Double getIntangibleAssets3() {
		return IntangibleAssets3;
	}

	public void setIntangibleAssets3(Double intangibleAssets3) {
		IntangibleAssets3 = intangibleAssets3;
	}

	public Double getIntangibleAssets4() {
		return IntangibleAssets4;
	}

	public void setIntangibleAssets4(Double intangibleAssets4) {
		IntangibleAssets4 = intangibleAssets4;
	}

	public Double getIntangibleAssets5() {
		return IntangibleAssets5;
	}

	public void setIntangibleAssets5(Double intangibleAssets5) {
		IntangibleAssets5 = intangibleAssets5;
	}

	
	
	
	public Double getIntangibleAssets6() {
		return IntangibleAssets6;
	}

	public void setIntangibleAssets6(Double intangibleAssets6) {
		IntangibleAssets6 = intangibleAssets6;
	}

	public Double getAssetsInTransit1() {
		return AssetsInTransit1;
	}

	public void setAssetsInTransit1(Double assetsInTransit1) {
		AssetsInTransit1 = assetsInTransit1;
	}

	public Double getAssetsInTransit2() {
		return AssetsInTransit2;
	}

	public void setAssetsInTransit2(Double assetsInTransit2) {
		AssetsInTransit2 = assetsInTransit2;
	}

	public Double getAssetsInTransit3() {
		return AssetsInTransit3;
	}

	public void setAssetsInTransit3(Double assetsInTransit3) {
		AssetsInTransit3 = assetsInTransit3;
	}

	public Double getAssetsInTransit4() {
		return AssetsInTransit4;
	}

	public void setAssetsInTransit4(Double assetsInTransit4) {
		AssetsInTransit4 = assetsInTransit4;
	}

	public Double getOthersInvestment1() {
		return OthersInvestment1;
	}

	public void setOthersInvestment1(Double othersInvestment1) {
		OthersInvestment1 = othersInvestment1;
	}

	public Double getOthersInvestment2() {
		return OthersInvestment2;
	}

	public void setOthersInvestment2(Double othersInvestment2) {
		OthersInvestment2 = othersInvestment2;
	}

	public Double getOthersInvestment3() {
		return OthersInvestment3;
	}

	public void setOthersInvestment3(Double othersInvestment3) {
		OthersInvestment3 = othersInvestment3;
	}

	public Double getOthersInvestment4() {
		return OthersInvestment4;
	}

	public void setOthersInvestment4(Double othersInvestment4) {
		OthersInvestment4 = othersInvestment4;
	}

	public Double getOthersInvestment5() {
		return OthersInvestment5;
	}

	public void setOthersInvestment5(Double othersInvestment5) {
		OthersInvestment5 = othersInvestment5;
	}

	public Double getShortTerm1() {
		return ShortTerm1;
	}

	public void setShortTerm1(Double shortTerm1) {
		ShortTerm1 = shortTerm1;
	}

	public Double getShortTerm2() {
		return ShortTerm2;
	}

	public void setShortTerm2(Double shortTerm2) {
		ShortTerm2 = shortTerm2;
	}

	public Double getShortTerm3() {
		return ShortTerm3;
	}

	public void setShortTerm3(Double shortTerm3) {
		ShortTerm3 = shortTerm3;
	}

	public Double getShortTerm4() {
		return ShortTerm4;
	}

	public void setShortTerm4(Double shortTerm4) {
		ShortTerm4 = shortTerm4;
	}

	public Double getOtherDetails1() {
		return OtherDetails1;
	}

	public void setOtherDetails1(Double otherDetails1) {
		OtherDetails1 = otherDetails1;
	}

	public Double getOtherDetails2() {
		return OtherDetails2;
	}

	public void setOtherDetails2(Double otherDetails2) {
		OtherDetails2 = otherDetails2;
	}

	public Double getOtherDetails3() {
		return OtherDetails3;
	}

	public void setOtherDetails3(Double otherDetails3) {
		OtherDetails3 = otherDetails3;
	}

	public Double getOtherDetails4() {
		return OtherDetails4;
	}

	public void setOtherDetails4(Double otherDetails4) {
		OtherDetails4 = otherDetails4;
	}

	public Double getOtherDetails5() {
		return OtherDetails5;
	}

	public void setOtherDetails5(Double otherDetails5) {
		OtherDetails5 = otherDetails5;
	}

	public double getMoneyReceivedAgainstShareWarrants() {
		return moneyReceivedAgainstShareWarrants;
	}

	public void setMoneyReceivedAgainstShareWarrants(double moneyReceivedAgainstShareWarrants) {
		this.moneyReceivedAgainstShareWarrants = moneyReceivedAgainstShareWarrants;
	}

	public double getMinorityInterest() {
		return minorityInterest;
	}

	public void setMinorityInterest(double minorityInterest) {
		this.minorityInterest = minorityInterest;
	}

	public double getTermLoansSecured() {
		return termLoansSecured;
	}

	public void setTermLoansSecured(double termLoansSecured) {
		this.termLoansSecured = termLoansSecured;
	}

	public double getTermLoansUnsecured() {
		return termLoansUnsecured;
	}

	public void setTermLoansUnsecured(double termLoansUnsecured) {
		this.termLoansUnsecured = termLoansUnsecured;
	}

	public double getUnsecuredLoansFromOthers() {
		return unsecuredLoansFromOthers;
	}

	public void setUnsecuredLoansFromOthers(double unsecuredLoansFromOthers) {
		this.unsecuredLoansFromOthers = unsecuredLoansFromOthers;
	}

	public double getCurrentLiabilitiesSecured() {
		return currentLiabilitiesSecured;
	}

	public void setCurrentLiabilitiesSecured(double currentLiabilitiesSecured) {
		this.currentLiabilitiesSecured = currentLiabilitiesSecured;
	}

	public double getCurrentLiabilitiesUnsecured() {
		return currentLiabilitiesUnsecured;
	}

	public void setCurrentLiabilitiesUnsecured(double currentLiabilitiesUnsecured) {
		this.currentLiabilitiesUnsecured = currentLiabilitiesUnsecured;
	}

	public double getImpairmentsOfAssests() {
		return impairmentsOfAssests;
	}

	public void setImpairmentsOfAssests(double impairmentsOfAssests) {
		this.impairmentsOfAssests = impairmentsOfAssests;
	}

	public double getPreOperativeExpensesPending() {
		return preOperativeExpensesPending;
	}

	public void setPreOperativeExpensesPending(double preOperativeExpensesPending) {
		this.preOperativeExpensesPending = preOperativeExpensesPending;
	}

	public double getAssetsInTransit() {
		return assetsInTransit;
	}

	public void setAssetsInTransit(double assetsInTransit) {
		this.assetsInTransit = assetsInTransit;
	}

	public double getCashAndCashEquivalents() {
		return cashAndCashEquivalents;
	}

	public void setCashAndCashEquivalents(double cashAndCashEquivalents) {
		this.cashAndCashEquivalents = cashAndCashEquivalents;
	}


	public double getOthersTotals() {
		return OthersTotals;
	}

	public void setOthersTotals(double othersTotals) {
		OthersTotals = othersTotals;
	}

	public double getOthersAssetsTransit() {
		return OthersAssetsTransit;
	}

	public void setOthersAssetsTransit(double othersAssetsTransit) {
		OthersAssetsTransit = othersAssetsTransit;
	}

	public double getOtherInvestments() {
		return OtherInvestments;
	}

	public void setOtherInvestments(double otherInvestments) {
		OtherInvestments = otherInvestments;
	}

	public double getOtherDetails() {
		return OtherDetails;
	}

	public void setOtherDetails(double otherDetails) {
		OtherDetails = otherDetails;
	}

	public double getOtherTermLiability() {
		return OtherTermLiability;
	}

	public void setOtherTermLiability(double otherTermLiability) {
		OtherTermLiability = otherTermLiability;
	}
	
	
	

	

}