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
	
	@Column(name="financial_yearly_statement")
	private String financialYearlyStatement;
	
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
	
	
	@Column(name = "others_1")
	private Double Others1;

	@Column(name = "others_2")
	private Double Others2;

	@Column(name = "others_3")
	private Double Others3;

	@Column(name = "others_4")
	private Double Others4;

	@Column(name = "others_5")
	private Double Others5;

	// others_liabilities

	@Column(name = "others_liabilities_1")
	private Double OthersLiabilities1;

	@Column(name = "others_liabilities_2")
	private Double OthersLiabilities2;

	@Column(name = "others_liabilities_3")
	private Double OthersLiabilities3;

	// others_totals

	@Column(name = "others_totals_1")
	private Double OthersTotals1;

	@Column(name = "others_totals_2")
	private Double OthersTotals2;

	@Column(name = "others_totals_3")
	private Double OthersTotals3;

	@Column(name = "others_totals_4")
	private Double OthersTotals4;

	@Column(name = "others_totals_5")
	private Double OthersTotals5;

	// intangible_assets

	@Column(name = "intangible_assets_1")
	private Double IntangibleAssets1;

	@Column(name = "intangible_assets_2")
	private Double IntangibleAssets2;

	@Column(name = "intangible_assets_3")
	private Double IntangibleAssets3;

	@Column(name = "intangible_assets_4")
	private Double IntangibleAssets4;

	@Column(name = "intangible_assets_5")
	private Double IntangibleAssets5;
	
	@Column(name = "intangible_assets_6")
	private Double IntangibleAssets6;

	// assets_in_transit

	@Column(name = "assets_in_transit_1")
	private Double AssetsInTransit1;

	@Column(name = "assets_in_transit_2")
	private Double AssetsInTransit2;

	@Column(name = "assets_in_transit_3")
	private Double AssetsInTransit3;

	@Column(name = "assets_in_transit_4")
	private Double AssetsInTransit4;

	// others_investment
	@Column(name = "others_investment_1")
	private Double OthersInvestment1;

	@Column(name = "others_investment_2")
	private Double OthersInvestment2;

	@Column(name = "others_investment_3")
	private Double OthersInvestment3;

	@Column(name = "others_investment_4")
	private Double OthersInvestment4;

	@Column(name = "others_investment_5")
	private Double OthersInvestment5;

	// short_term

	@Column(name = "short_term_1")
	private Double ShortTerm1;

	@Column(name = "short_term_2")
	private Double ShortTerm2;

	@Column(name = "short_term_3")
	private Double ShortTerm3;

	@Column(name = "short_term_4")
	private Double ShortTerm4;

	// other_details

	@Column(name = "other_details_1")
	private Double OtherDetails1;

	@Column(name = "other_details_2")
	private Double OtherDetails2;

	@Column(name = "other_details_3")
	private Double OtherDetails3;

	@Column(name = "other_details_4")
	private Double OtherDetails4;

	@Column(name = "other_details_5")
	private Double OtherDetails5;
	
	//latest1-2
	
	@Column(name="money_received_against_share_warrants")
	private double moneyReceivedAgainstShareWarrants;
	
	@Column(name="minority_interest")
	private double minorityInterest;
	
	@Column(name="term_loans_secured")
	private double termLoansSecured;
	
	@Column(name="term_loans_unsecured")
	private double termLoansUnsecured;
	
	@Column(name="unsecured_loans_from_others")
	private double unsecuredLoansFromOthers;
	
	
	@Column(name="current_liabilities_secured")
	private double currentLiabilitiesSecured;
	
	@Column(name="current_liabilities_unsecured")
	private double currentLiabilitiesUnsecured;
	
	@Column(name="impairments_of_assests")
	private double impairmentsOfAssests;

	@Column(name="pre_operative_expenses_pending")
	private double preOperativeExpensesPending;
	
	@Column(name="assets_in_transit")
	private double assetsInTransit;
	
	@Column(name="cash_and_cash_equivalents")
	private double cashAndCashEquivalents;
	
	@Column(name="other_term_liability")
	private double OtherTermLiability;

	@Column(name="others_totals")
	private double OthersTotals;
	
	@Column(name="others_assets_transit")
	private double OthersAssetsTransit;
	
	@Column(name="other_investments")
	private double OtherInvestments;
	
	@Column(name="other_details")
	private double OtherDetails;
	
	
	public BalanceSheetDetail() {
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