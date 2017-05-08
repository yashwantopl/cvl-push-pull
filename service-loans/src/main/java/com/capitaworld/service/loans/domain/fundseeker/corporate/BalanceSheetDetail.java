package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_bs_balance_sheet_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_bs_balance_sheet_details")
@NamedQuery(name="BalanceSheetDetail.findAll", query="SELECT f FROM BalanceSheetDetail f")
public class BalanceSheetDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="advance_from_customers")
	private double advanceFromCustomers;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="capital_advance")
	private double capitalAdvance;

	@Column(name="capital_redemption_reserve")
	private double capitalRedemptionReserve;

	@Column(name="capital_reserve")
	private double capitalReserve;

	@Column(name="capital_work_in_progress")
	private double capitalWorkInProgress;

	@Column(name="contingency_reserve")
	private double contingencyReserve;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="current_investments")
	private double currentInvestments;

	@Column(name="debenture_redemption_reserve")
	private double debentureRedemptionReserve;

	private double debentures;

	@Column(name="debit_balance_pl")
	private double debitBalancePl;

	@Column(name="deferred_payment_credits")
	private double deferredPaymentCredits;

	@Column(name="deferred_tax_asset")
	private double deferredTaxAsset;

	@Column(name="deferred_tax_liability")
	private double deferredTaxLiability;

	@Column(name="deposits_and_installments")
	private double depositsAndInstallments;

	@Column(name="depreciation_to_date")
	private double depreciationToDate;

	@Column(name="dividend_payable")
	private double dividendPayable;

	private double exports;

	@Column(name="finished_goods")
	private double finishedGoods;

	@Column(name="fixed_assets")
	private double fixedAssets;

	@Column(name="fixed_deposits_with_banks")
	private double fixedDepositsWithBanks;

	@Column(name="foreign_currency_translation_reserve")
	private double foreignCurrencyTranslationReserve;

	@Column(name="general_reserve")
	private double generalReserve;

	@Column(name="government_and_other_trustee")
	private double governmentAndOtherTrustee;

	@Column(name="grand_total")
	private double grandTotal;

	@Column(name="gross_fixed_assets")
	private double grossFixedAssets;

	@Column(name="hedging_reserve")
	private double hedgingReserve;

	@Column(name="intangible_assets")
	private double intangibleAssets;

	private double inventory;

	@Column(name="investment_in_associates")
	private double investmentInAssociates;

	@Column(name="investment_in_quoted")
	private double investmentInQuoted;

	@Column(name="investment_in_subsidiaries")
	private double investmentInSubsidiaries;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="long_term_borrowing")
	private double longTermBorrowing;

	@Column(name="long_term_loans_and_advance")
	private double longTermLoansAndAdvance;

	@Column(name="long_term_provisions")
	private double longTermProvisions;

	@Column(name="misc_expences")
	private double miscExpences;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="non_current_investments")
	private double nonCurrentInvestments;

	@Column(name="ordinary_share_capital")
	private double ordinaryShareCapital;

	@Column(name="other_consumables_spares")
	private double otherConsumablesSpares;

	@Column(name="other_consumables_spares_imported")
	private double otherConsumablesSparesImported;

	@Column(name="other_consumables_spares_indegenous")
	private double otherConsumablesSparesIndegenous;

	@Column(name="other_non_current_liability")
	private double otherNonCurrentLiability;

	@Column(name="other_than_exports")
	private double otherThanExports;

	private double others;

	@Column(name="others_current_assets")
	private double othersCurrentAssets;

	@Column(name="others_current_liability")
	private double othersCurrentLiability;

	@Column(name="others_non_current_assets")
	private double othersNonCurrentAssets;

	@Column(name="others_pls_specify")
	private double othersPlsSpecify;

	@Column(name="preference_share_capital")
	private double preferenceShareCapital;

	@Column(name="provision_for_tax")
	private double provisionForTax;

	@Column(name="raw_material")
	private double rawMaterial;

	@Column(name="raw_material_imported")
	private double rawMaterialImported;

	@Column(name="raw_material_indegenous")
	private double rawMaterialIndegenous;

	@Column(name="reserves_and_surplus")
	private double reservesAndSurplus;

	@Column(name="revaluation_reserve")
	private double revaluationReserve;

	@Column(name="securities_premium_account")
	private double securitiesPremiumAccount;

	@Column(name="share_application_pending_allotment")
	private double shareApplicationPendingAllotment;

	@Column(name="short_term_borrowings")
	private double shortTermBorrowings;

	@Column(name="short_term_loans_and_advances")
	private double shortTermLoansAndAdvances;

	@Column(name="statutory_liability_dues")
	private double statutoryLiabilityDues;

	@Column(name="stock_in_process")
	private double stockInProcess;

	@Column(name="surplus_in_profit_and_loss_account")
	private double surplusInProfitAndLossAccount;

	@Column(name="term_deposits")
	private double termDeposits;

	@Column(name="term_loans")
	private double termLoans;

	@Column(name="total_current_and_non_current_liability")
	private double totalCurrentAndNonCurrentLiability;

	@Column(name="trade_payables")
	private double tradePayables;

	@Column(name="trade_receivables")
	private double tradeReceivables;

	@Column(name="unsecured_loans_from_promoters")
	private double unsecuredLoansFromPromoters;

	private String year;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;
	
	

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

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
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
	
	

	

}