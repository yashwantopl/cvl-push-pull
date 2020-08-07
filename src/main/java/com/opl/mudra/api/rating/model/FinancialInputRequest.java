package com.opl.mudra.api.rating.model;

import java.util.List;
import java.util.Map;

public class FinancialInputRequest{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer year;

	private String companyName;

	private String industryName;

	private Double adjustedTotalDebtEquityFy;

	private Double adjustedTotalDebtEquitySy;

	private Double adjustedTotalDebtEquityTy;

	private Double assetInTransitFy;

	private Double assetInTransitSy;

	private Double assetInTransitTy;

	private Double assetTurnOverFy;

	private Double assetTurnOverSy;

	private Double assetTurnOverTy;

	private Double bookValueFy;

	private Double bookValueSy;

	private Double bookValueTy;

	private Double capitalWorkInProgressFy;

	private Double capitalWorkInProgressSy;

	private Double capitalWorkInProgressTy;

	private Double cashAndBankFy;

	private Double cashAndBankSy;

	private Double cashAndBankTy;

	private Double cashFromOperatingFy;

	private Double cashFromOperatingSy;

	private Double cashInterestCoverFy;

	private Double cashInterestCoverSy;

	private Double cfoMargineFy;

	private Double cfoMargineSy;

	private Double contingentLiablitiesFy;

	private Double contingentLiablitiesSy;

	private Double contingentLiablitiestTy;

	private Double creditorsTurnoverFy;

	private Double creditorsTurnoverSy;

	private Double creditorsTurnoverTy;

	private Double curruntRatioXFy;

	private Double curruntRatioXSy;

	private Double curruntRatioXTy;

	private Double debtEbitadFy;

	private Double debtEbitadSy;

	private Double debtEbitadTy;

	private Double debtorsTurnoverFy;

	private Double debtorsTurnoverSy;

	private Double debtorsTurnoverTy;

	private Double deferredTaxLiablitiesFy;

	private Double deferredTaxLiablitiesSy;

	private Double deferredTaxLiablitiesTy;

	private Double depriciationFy;

	private Double depriciationSy;

	private Double depriciationTy;

	private Double dividendPayOutFy;

	private Double dividendPayOutSy;

	private Double dividendPayOutTy;

	private Double earningPerShareFy;

	private Double earningPerShareSy;

	private Double earningPerShareTy;

	private Double ebitadPercentageFy;

	private Double ebitadPercentageSy;

	private Double ebitadPercentageTy;

	private Double ebitdaFy;

	private Double ebitdaSy;

	private Double employeeCostFy;

	private Double employeeCostSy;

	private Double employeeCostTy;

	private Double equityDividendFy;

	private Double equityDividendSy;

	private Double equityDividendTy;

	private Double exceptionalIncomeFy;

	private Double exceptionalIncomeSy;

	private Double exceptionalIncomeTy;

	private Double freeReserveEquityFy;

	private Double freeReserveEquitySy;

	private Double freeReserveEquityTy;

	private Double generalAndAdminExpeFy;

	private Double generalAndAdminExpeSy;

	private Double generalAndAdminExpeTy;

	private Double grossBlockFy;

	private Double grossBlockSy;

	private Double grossBlockTy;

	private Double grossSalesFy;

	private Double grossSalesSy;

	private Double grossSalesTy;

	private Double growthCfoMargineFy;

	private Double growthCfoMargineSy;

	private Double growthDebtEquityFy;

	private Double growthDebtEquitySy;

	private Double impairmentofAssetFy;

	private Double impairmentofAssetSy;

	private Double impairmentofAssetTy;

	private Double increaseDecreaseStockFy;

	private Double increaseDecreaseStockSy;

	private Double increaseDecreaseStockTy;

	private Double increaseWorkingCapitalFy;

	private Double increaseWorkingCapitalSy;

	private Double intengibleAssetsFy;

	private Double intengibleAssetsSy;

	private Double intengibleAssetsTy;

	private Double interestFy;

	private Double interestPaidFy;

	private Double interestPaidSy;

	private Double interestSy;

	private Double interestTy;

	private Double inventoriesFy;

	private Double inventoriesSy;

	private Double inventoriesTy;

	private Double inventoryTurnOverFy;

	private Double inventoryTurnOverSy;

	private Double inventoryTurnOverTy;

	private Double investmentInSubsidiariesFy;

	private Double investmentInSubsidiariesSy;

	private Double investmentInSubsidiariesTy;

	private Double lessAccumulatedDepreFy;

	private Double lessAccumulatedDepreSy;

	private Double lessAccumulatedDepreTy;

	private Double lessExciseDuityFy;

	private Double lessExciseDuitySy;

	private Double lessExciseDuityTy;

	private Double lessExpeCapitaFy;

	private Double lessExpeCapitaSy;

	private Double lessExpeCapitaTy;

	private Double longTermLoansAndAdvaFy;

	private Double longTermLoansAndAdvaSy;

	private Double longTermLoansAndAdvaTy;

	private Double longTermProvisionFy;

	private Double longTermProvisionSy;

	private Double longTermProvisionTy;

	private Double minorityInterestFy;

	private Double minorityInterestSy;

	private Double minorityInterestTy;

	private Double miscelExpeFy;

	private Double miscelExpeSy;

	private Double miscelExpeTy;

	private Double netBlockFy;

	private Double netBlockSy;

	private Double netBlockTy;

	private Double netSaleFy;

	private Double netSaleSy;

	private Double netSaleTy;

	private Double netSalesGrowthCapitalFy;

	private Double netSalesGrowthCapitalSy;

	private Double noOfMonthFy;

	private Double noOfMonthSy;

	private Double noOfMonthTy;

	private Double operatingProfitEbitadOiFy;

	private Double operatingProfitEbitadOiSy;

	private Double operatingProfitEbitadOiTy;

	private Double operatingProfitExclOiFy;

	private Double operatingProfitExclOiSy;

	private Double operatingProfitExclOiTy;

	private Double otheNonCurruntAssetFy;

	private Double otheNonCurruntAssetSy;

	private Double otheNonCurruntAssetTy;

	private Double otherBorrowingFy;

	private Double otherBorrowingSy;

	private Double otherBorrowingTy;

	private Double otherCurruntAssetFy;

	private Double otherCurruntAssetSy;

	private Double otherCurruntAssetTy;

	private Double otherCurruntLiablitiesFy;

	private Double otherCurruntLiablitiesSy;

	private Double otherCurruntLiablitiesTy;

	private Double otherIncomeFy;

	private Double otherIncomeSy;

	private Double otherIncomeTy;

	private Double otherInvestmentFy;

	private Double otherInvestmentSy;

	private Double otherInvestmentTy;

	private Double otherLongTermLiablitiesFy;

	private Double otherLongTermLiablitiesSy;

	private Double otherLongTermLiablitiesTy;

	private Double otherReserveAndSurplusFy;

	private Double otherReserveAndSurplusSy;

	private Double otherReserveAndSurplusTy;

	private Double patGrowthCapitalFy;

	private Double patGrowthCapitalSy;

	private Double patmFy;

	private Double patmSy;

	private Double patmTy;

	private Double pbdtFy;

	private Double pbdtSy;

	private Double pbdtTy;

	private Double powerAndFuelCostFy;

	private Double powerAndFuelCostSy;

	private Double powerAndFuelCostTy;

	private Double preOperativeExpeFy;

	private Double preOperativeExpeSy;

	private Double preOperativeExpeTy;

	private Double profitAfterTaxFy;

	private Double profitAfterTaxSy;

	private Double profitAfterTaxTy;

	private Double profitBeforeTaxFy;

	private Double profitBeforeTaxSy;

	private Double profitBeforeTaxTy;

	private Double profitBeforeTaxationFy;

	private Double profitBeforeTaxationSy;

	private Double profitBeforeTaxationTy;

	private Double provisionForTaxFy;

	private Double provisionForTaxSy;

	private Double provisionForTaxTy;

	private Double quickRatioXFy;

	private Double quickRatioXSy;

	private Double quickRatioXTy;

	private Double rawMaterialConsumedFy;

	private Double rawMaterialConsumedSy;

	private Double rawMaterialConsumedTy;

	private Double revalationReserveFy;

	private Double revalationReserveSy;

	private Double revalationReserveTy;

	private Double roceFy;

	private Double roceSy;

	private Double salesWorkingCapitalFy;

	private Double salesWorkingCapitalSy;

	private Double salesWorkingCapitalTy;

	private Double securedLoansFy;

	private Double securedLoansSy;

	private Double securedLoansTy;

	private Double sellingAndDistriExpeFy;

	private Double sellingAndDistriExpeSy;

	private Double sellingAndDistriExpeTy;

	private Double shareCapitalFy;

	private Double shareCapitalSy;

	private Double shareCapitalTy;

	private Double shareFaceValue;

	private Double shareHolderFundsFy;

	private Double shareHolderFundsSy;

	private Double shareHolderFundsTy;

	private Double shareWarrantOutstandingsFy;

	private Double shareWarrantOutstandingsSy;

	private Double shareWarrantOutstandingsTy;

	private Double shortTermLoansAdvancesFy;

	private Double shortTermLoansAdvancesSy;

	private Double shortTermLoansAdvancesTy;

	private Double shortTermProvisionFy;

	private Double shortTermProvisionSy;

	private Double shortTermProvisionTy;

	private Double sundryDebtorsFy;

	private Double sundryDebtorsSy;

	private Double sundryDebtorsTy;

	private Double taxPaidFy;

	private Double taxPaidSy;

	private Double totalAssetFy;

	private Double totalAssetSy;

	private Double totalAssetTy;

	private Double totalCurruntAssetFy;

	private Double totalCurruntAssetSy;

	private Double totalCurruntAssetTy;

	private Double totalCurruntLiablitiesFy;

	private Double totalCurruntLiablitiesSy;

	private Double totalCurruntLiablitiesTy;

	private Double totalExpenditureFy;

	private Double totalExpenditureSy;

	private Double totalExpenditureTy;

	private Double totalLiablitiesFy;

	private Double totalLiablitiesSy;

	private Double totalLiablitiesTy;

	private Double totalNonCurruntAssetFy;

	private Double totalNonCurruntAssetSy;

	private Double totalNonCurruntAssetTy;

	private Double totalNonCurruntLiablitiesFy;

	private Double totalNonCurruntLiablitiesSy;

	private Double totalNonCurruntLiablitiesTy;

	private Double tradePayablesFy;

	private Double tradePayablesSy;

	private Double tradePayablesTy;

	private Double unsecuredLoansOthersFy;

	private Double unsecuredLoansOthersSy;

	private Double unsecuredLoansOthersTy;

	private Double unsecuredLoansPromotersFy;

	private Double unsecuredLoansPromotersSy;

	private Double unsecuredLoansPromotersTy;

	private String ratioAnalysisFyFullDate;

	private Double OtherIncomeNeedTocCheckAssetFy;
	private Double OtherIncomeNeedTocCheckAssetSy;
	private Double OtherIncomeNeedTocCheckAssetTy;
	private Double OtherIncomeNeedTocCheckOpFy;
	private Double OtherIncomeNeedTocCheckOpSy;
	private Double OtherIncomeNeedTocCheckOpTy;
	private Double OtherIncomeNeedTocCheckLiaFy;
	private Double OtherIncomeNeedTocCheckLiaSy;
	private Double OtherIncomeNeedTocCheckLiaTy;

	private Integer status;

	private String message;

	private Object data;
	private List<Map<String,Object>>yearSalesPurchasList;



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "FinancialInputRequest{" +
				"id=" + id +
				", year=" + year +
				", companyName='" + companyName + '\'' +
				", industryName='" + industryName + '\'' +
				", adjustedTotalDebtEquityFy=" + adjustedTotalDebtEquityFy +
				", adjustedTotalDebtEquitySy=" + adjustedTotalDebtEquitySy +
				", adjustedTotalDebtEquityTy=" + adjustedTotalDebtEquityTy +
				", assetInTransitFy=" + assetInTransitFy +
				", assetInTransitSy=" + assetInTransitSy +
				", assetInTransitTy=" + assetInTransitTy +
				", assetTurnOverFy=" + assetTurnOverFy +
				", assetTurnOverSy=" + assetTurnOverSy +
				", assetTurnOverTy=" + assetTurnOverTy +
				", bookValueFy=" + bookValueFy +
				", bookValueSy=" + bookValueSy +
				", bookValueTy=" + bookValueTy +
				", capitalWorkInProgressFy=" + capitalWorkInProgressFy +
				", capitalWorkInProgressSy=" + capitalWorkInProgressSy +
				", capitalWorkInProgressTy=" + capitalWorkInProgressTy +
				", cashAndBankFy=" + cashAndBankFy +
				", cashAndBankSy=" + cashAndBankSy +
				", cashAndBankTy=" + cashAndBankTy +
				", cashFromOperatingFy=" + cashFromOperatingFy +
				", cashFromOperatingSy=" + cashFromOperatingSy +
				", cashInterestCoverFy=" + cashInterestCoverFy +
				", cashInterestCoverSy=" + cashInterestCoverSy +
				", cfoMargineFy=" + cfoMargineFy +
				", cfoMargineSy=" + cfoMargineSy +
				", contingentLiablitiesFy=" + contingentLiablitiesFy +
				", contingentLiablitiesSy=" + contingentLiablitiesSy +
				", contingentLiablitiestTy=" + contingentLiablitiestTy +
				", creditorsTurnoverFy=" + creditorsTurnoverFy +
				", creditorsTurnoverSy=" + creditorsTurnoverSy +
				", creditorsTurnoverTy=" + creditorsTurnoverTy +
				", curruntRatioXFy=" + curruntRatioXFy +
				", curruntRatioXSy=" + curruntRatioXSy +
				", curruntRatioXTy=" + curruntRatioXTy +
				", debtEbitadFy=" + debtEbitadFy +
				", debtEbitadSy=" + debtEbitadSy +
				", debtEbitadTy=" + debtEbitadTy +
				", debtorsTurnoverFy=" + debtorsTurnoverFy +
				", debtorsTurnoverSy=" + debtorsTurnoverSy +
				", debtorsTurnoverTy=" + debtorsTurnoverTy +
				", deferredTaxLiablitiesFy=" + deferredTaxLiablitiesFy +
				", deferredTaxLiablitiesSy=" + deferredTaxLiablitiesSy +
				", deferredTaxLiablitiesTy=" + deferredTaxLiablitiesTy +
				", depriciationFy=" + depriciationFy +
				", depriciationSy=" + depriciationSy +
				", depriciationTy=" + depriciationTy +
				", dividendPayOutFy=" + dividendPayOutFy +
				", dividendPayOutSy=" + dividendPayOutSy +
				", dividendPayOutTy=" + dividendPayOutTy +
				", earningPerShareFy=" + earningPerShareFy +
				", earningPerShareSy=" + earningPerShareSy +
				", earningPerShareTy=" + earningPerShareTy +
				", ebitadPercentageFy=" + ebitadPercentageFy +
				", ebitadPercentageSy=" + ebitadPercentageSy +
				", ebitadPercentageTy=" + ebitadPercentageTy +
				", ebitdaFy=" + ebitdaFy +
				", ebitdaSy=" + ebitdaSy +
				", employeeCostFy=" + employeeCostFy +
				", employeeCostSy=" + employeeCostSy +
				", employeeCostTy=" + employeeCostTy +
				", equityDividendFy=" + equityDividendFy +
				", equityDividendSy=" + equityDividendSy +
				", equityDividendTy=" + equityDividendTy +
				", exceptionalIncomeFy=" + exceptionalIncomeFy +
				", exceptionalIncomeSy=" + exceptionalIncomeSy +
				", exceptionalIncomeTy=" + exceptionalIncomeTy +
				", freeReserveEquityFy=" + freeReserveEquityFy +
				", freeReserveEquitySy=" + freeReserveEquitySy +
				", freeReserveEquityTy=" + freeReserveEquityTy +
				", generalAndAdminExpeFy=" + generalAndAdminExpeFy +
				", generalAndAdminExpeSy=" + generalAndAdminExpeSy +
				", generalAndAdminExpeTy=" + generalAndAdminExpeTy +
				", grossBlockFy=" + grossBlockFy +
				", grossBlockSy=" + grossBlockSy +
				", grossBlockTy=" + grossBlockTy +
				", grossSalesFy=" + grossSalesFy +
				", grossSalesSy=" + grossSalesSy +
				", grossSalesTy=" + grossSalesTy +
				", growthCfoMargineFy=" + growthCfoMargineFy +
				", growthCfoMargineSy=" + growthCfoMargineSy +
				", growthDebtEquityFy=" + growthDebtEquityFy +
				", growthDebtEquitySy=" + growthDebtEquitySy +
				", impairmentofAssetFy=" + impairmentofAssetFy +
				", impairmentofAssetSy=" + impairmentofAssetSy +
				", impairmentofAssetTy=" + impairmentofAssetTy +
				", increaseDecreaseStockFy=" + increaseDecreaseStockFy +
				", increaseDecreaseStockSy=" + increaseDecreaseStockSy +
				", increaseDecreaseStockTy=" + increaseDecreaseStockTy +
				", increaseWorkingCapitalFy=" + increaseWorkingCapitalFy +
				", increaseWorkingCapitalSy=" + increaseWorkingCapitalSy +
				", intengibleAssetsFy=" + intengibleAssetsFy +
				", intengibleAssetsSy=" + intengibleAssetsSy +
				", intengibleAssetsTy=" + intengibleAssetsTy +
				", interestFy=" + interestFy +
				", interestPaidFy=" + interestPaidFy +
				", interestPaidSy=" + interestPaidSy +
				", interestSy=" + interestSy +
				", interestTy=" + interestTy +
				", inventoriesFy=" + inventoriesFy +
				", inventoriesSy=" + inventoriesSy +
				", inventoriesTy=" + inventoriesTy +
				", inventoryTurnOverFy=" + inventoryTurnOverFy +
				", inventoryTurnOverSy=" + inventoryTurnOverSy +
				", inventoryTurnOverTy=" + inventoryTurnOverTy +
				", investmentInSubsidiariesFy=" + investmentInSubsidiariesFy +
				", investmentInSubsidiariesSy=" + investmentInSubsidiariesSy +
				", investmentInSubsidiariesTy=" + investmentInSubsidiariesTy +
				", lessAccumulatedDepreFy=" + lessAccumulatedDepreFy +
				", lessAccumulatedDepreSy=" + lessAccumulatedDepreSy +
				", lessAccumulatedDepreTy=" + lessAccumulatedDepreTy +
				", lessExciseDuityFy=" + lessExciseDuityFy +
				", lessExciseDuitySy=" + lessExciseDuitySy +
				", lessExciseDuityTy=" + lessExciseDuityTy +
				", lessExpeCapitaFy=" + lessExpeCapitaFy +
				", lessExpeCapitaSy=" + lessExpeCapitaSy +
				", lessExpeCapitaTy=" + lessExpeCapitaTy +
				", longTermLoansAndAdvaFy=" + longTermLoansAndAdvaFy +
				", longTermLoansAndAdvaSy=" + longTermLoansAndAdvaSy +
				", longTermLoansAndAdvaTy=" + longTermLoansAndAdvaTy +
				", longTermProvisionFy=" + longTermProvisionFy +
				", longTermProvisionSy=" + longTermProvisionSy +
				", longTermProvisionTy=" + longTermProvisionTy +
				", minorityInterestFy=" + minorityInterestFy +
				", minorityInterestSy=" + minorityInterestSy +
				", minorityInterestTy=" + minorityInterestTy +
				", miscelExpeFy=" + miscelExpeFy +
				", miscelExpeSy=" + miscelExpeSy +
				", miscelExpeTy=" + miscelExpeTy +
				", netBlockFy=" + netBlockFy +
				", netBlockSy=" + netBlockSy +
				", netBlockTy=" + netBlockTy +
				", netSaleFy=" + netSaleFy +
				", netSaleSy=" + netSaleSy +
				", netSaleTy=" + netSaleTy +
				", netSalesGrowthCapitalFy=" + netSalesGrowthCapitalFy +
				", netSalesGrowthCapitalSy=" + netSalesGrowthCapitalSy +
				", noOfMonthFy=" + noOfMonthFy +
				", noOfMonthSy=" + noOfMonthSy +
				", noOfMonthTy=" + noOfMonthTy +
				", operatingProfitEbitadOiFy=" + operatingProfitEbitadOiFy +
				", operatingProfitEbitadOiSy=" + operatingProfitEbitadOiSy +
				", operatingProfitEbitadOiTy=" + operatingProfitEbitadOiTy +
				", operatingProfitExclOiFy=" + operatingProfitExclOiFy +
				", operatingProfitExclOiSy=" + operatingProfitExclOiSy +
				", operatingProfitExclOiTy=" + operatingProfitExclOiTy +
				", otheNonCurruntAssetFy=" + otheNonCurruntAssetFy +
				", otheNonCurruntAssetSy=" + otheNonCurruntAssetSy +
				", otheNonCurruntAssetTy=" + otheNonCurruntAssetTy +
				", otherBorrowingFy=" + otherBorrowingFy +
				", otherBorrowingSy=" + otherBorrowingSy +
				", otherBorrowingTy=" + otherBorrowingTy +
				", otherCurruntAssetFy=" + otherCurruntAssetFy +
				", otherCurruntAssetSy=" + otherCurruntAssetSy +
				", otherCurruntAssetTy=" + otherCurruntAssetTy +
				", otherCurruntLiablitiesFy=" + otherCurruntLiablitiesFy +
				", otherCurruntLiablitiesSy=" + otherCurruntLiablitiesSy +
				", otherCurruntLiablitiesTy=" + otherCurruntLiablitiesTy +
				", otherIncomeFy=" + otherIncomeFy +
				", otherIncomeSy=" + otherIncomeSy +
				", otherIncomeTy=" + otherIncomeTy +
				", otherInvestmentFy=" + otherInvestmentFy +
				", otherInvestmentSy=" + otherInvestmentSy +
				", otherInvestmentTy=" + otherInvestmentTy +
				", otherLongTermLiablitiesFy=" + otherLongTermLiablitiesFy +
				", otherLongTermLiablitiesSy=" + otherLongTermLiablitiesSy +
				", otherLongTermLiablitiesTy=" + otherLongTermLiablitiesTy +
				", otherReserveAndSurplusFy=" + otherReserveAndSurplusFy +
				", otherReserveAndSurplusSy=" + otherReserveAndSurplusSy +
				", otherReserveAndSurplusTy=" + otherReserveAndSurplusTy +
				", patGrowthCapitalFy=" + patGrowthCapitalFy +
				", patGrowthCapitalSy=" + patGrowthCapitalSy +
				", patmFy=" + patmFy +
				", patmSy=" + patmSy +
				", patmTy=" + patmTy +
				", pbdtFy=" + pbdtFy +
				", pbdtSy=" + pbdtSy +
				", pbdtTy=" + pbdtTy +
				", powerAndFuelCostFy=" + powerAndFuelCostFy +
				", powerAndFuelCostSy=" + powerAndFuelCostSy +
				", powerAndFuelCostTy=" + powerAndFuelCostTy +
				", preOperativeExpeFy=" + preOperativeExpeFy +
				", preOperativeExpeSy=" + preOperativeExpeSy +
				", preOperativeExpeTy=" + preOperativeExpeTy +
				", profitAfterTaxFy=" + profitAfterTaxFy +
				", profitAfterTaxSy=" + profitAfterTaxSy +
				", profitAfterTaxTy=" + profitAfterTaxTy +
				", profitBeforeTaxFy=" + profitBeforeTaxFy +
				", profitBeforeTaxSy=" + profitBeforeTaxSy +
				", profitBeforeTaxTy=" + profitBeforeTaxTy +
				", profitBeforeTaxationFy=" + profitBeforeTaxationFy +
				", profitBeforeTaxationSy=" + profitBeforeTaxationSy +
				", profitBeforeTaxationTy=" + profitBeforeTaxationTy +
				", provisionForTaxFy=" + provisionForTaxFy +
				", provisionForTaxSy=" + provisionForTaxSy +
				", provisionForTaxTy=" + provisionForTaxTy +
				", quickRatioXFy=" + quickRatioXFy +
				", quickRatioXSy=" + quickRatioXSy +
				", quickRatioXTy=" + quickRatioXTy +
				", rawMaterialConsumedFy=" + rawMaterialConsumedFy +
				", rawMaterialConsumedSy=" + rawMaterialConsumedSy +
				", rawMaterialConsumedTy=" + rawMaterialConsumedTy +
				", revalationReserveFy=" + revalationReserveFy +
				", revalationReserveSy=" + revalationReserveSy +
				", revalationReserveTy=" + revalationReserveTy +
				", roceFy=" + roceFy +
				", roceSy=" + roceSy +
				", salesWorkingCapitalFy=" + salesWorkingCapitalFy +
				", salesWorkingCapitalSy=" + salesWorkingCapitalSy +
				", salesWorkingCapitalTy=" + salesWorkingCapitalTy +
				", securedLoansFy=" + securedLoansFy +
				", securedLoansSy=" + securedLoansSy +
				", securedLoansTy=" + securedLoansTy +
				", sellingAndDistriExpeFy=" + sellingAndDistriExpeFy +
				", sellingAndDistriExpeSy=" + sellingAndDistriExpeSy +
				", sellingAndDistriExpeTy=" + sellingAndDistriExpeTy +
				", shareCapitalFy=" + shareCapitalFy +
				", shareCapitalSy=" + shareCapitalSy +
				", shareCapitalTy=" + shareCapitalTy +
				", shareFaceValue=" + shareFaceValue +
				", shareHolderFundsFy=" + shareHolderFundsFy +
				", shareHolderFundsSy=" + shareHolderFundsSy +
				", shareHolderFundsTy=" + shareHolderFundsTy +
				", shareWarrantOutstandingsFy=" + shareWarrantOutstandingsFy +
				", shareWarrantOutstandingsSy=" + shareWarrantOutstandingsSy +
				", shareWarrantOutstandingsTy=" + shareWarrantOutstandingsTy +
				", shortTermLoansAdvancesFy=" + shortTermLoansAdvancesFy +
				", shortTermLoansAdvancesSy=" + shortTermLoansAdvancesSy +
				", shortTermLoansAdvancesTy=" + shortTermLoansAdvancesTy +
				", shortTermProvisionFy=" + shortTermProvisionFy +
				", shortTermProvisionSy=" + shortTermProvisionSy +
				", shortTermProvisionTy=" + shortTermProvisionTy +
				", sundryDebtorsFy=" + sundryDebtorsFy +
				", sundryDebtorsSy=" + sundryDebtorsSy +
				", sundryDebtorsTy=" + sundryDebtorsTy +
				", taxPaidFy=" + taxPaidFy +
				", taxPaidSy=" + taxPaidSy +
				", totalAssetFy=" + totalAssetFy +
				", totalAssetSy=" + totalAssetSy +
				", totalAssetTy=" + totalAssetTy +
				", totalCurruntAssetFy=" + totalCurruntAssetFy +
				", totalCurruntAssetSy=" + totalCurruntAssetSy +
				", totalCurruntAssetTy=" + totalCurruntAssetTy +
				", totalCurruntLiablitiesFy=" + totalCurruntLiablitiesFy +
				", totalCurruntLiablitiesSy=" + totalCurruntLiablitiesSy +
				", totalCurruntLiablitiesTy=" + totalCurruntLiablitiesTy +
				", totalExpenditureFy=" + totalExpenditureFy +
				", totalExpenditureSy=" + totalExpenditureSy +
				", totalExpenditureTy=" + totalExpenditureTy +
				", totalLiablitiesFy=" + totalLiablitiesFy +
				", totalLiablitiesSy=" + totalLiablitiesSy +
				", totalLiablitiesTy=" + totalLiablitiesTy +
				", totalNonCurruntAssetFy=" + totalNonCurruntAssetFy +
				", totalNonCurruntAssetSy=" + totalNonCurruntAssetSy +
				", totalNonCurruntAssetTy=" + totalNonCurruntAssetTy +
				", totalNonCurruntLiablitiesFy=" + totalNonCurruntLiablitiesFy +
				", totalNonCurruntLiablitiesSy=" + totalNonCurruntLiablitiesSy +
				", totalNonCurruntLiablitiesTy=" + totalNonCurruntLiablitiesTy +
				", tradePayablesFy=" + tradePayablesFy +
				", tradePayablesSy=" + tradePayablesSy +
				", tradePayablesTy=" + tradePayablesTy +
				", unsecuredLoansOthersFy=" + unsecuredLoansOthersFy +
				", unsecuredLoansOthersSy=" + unsecuredLoansOthersSy +
				", unsecuredLoansOthersTy=" + unsecuredLoansOthersTy +
				", unsecuredLoansPromotersFy=" + unsecuredLoansPromotersFy +
				", unsecuredLoansPromotersSy=" + unsecuredLoansPromotersSy +
				", unsecuredLoansPromotersTy=" + unsecuredLoansPromotersTy +
				", ratioAnalysisFyFullDate='" + ratioAnalysisFyFullDate + '\'' +
				", OtherIncomeNeedTocCheckAssetFy=" + OtherIncomeNeedTocCheckAssetFy +
				", OtherIncomeNeedTocCheckAssetSy=" + OtherIncomeNeedTocCheckAssetSy +
				", OtherIncomeNeedTocCheckAssetTy=" + OtherIncomeNeedTocCheckAssetTy +
				", OtherIncomeNeedTocCheckOpFy=" + OtherIncomeNeedTocCheckOpFy +
				", OtherIncomeNeedTocCheckOpSy=" + OtherIncomeNeedTocCheckOpSy +
				", OtherIncomeNeedTocCheckOpTy=" + OtherIncomeNeedTocCheckOpTy +
				", OtherIncomeNeedTocCheckLiaFy=" + OtherIncomeNeedTocCheckLiaFy +
				", OtherIncomeNeedTocCheckLiaSy=" + OtherIncomeNeedTocCheckLiaSy +
				", OtherIncomeNeedTocCheckLiaTy=" + OtherIncomeNeedTocCheckLiaTy +
				", status=" + status +
				", message='" + message + '\'' +
				", data=" + data +
				'}';
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public FinancialInputRequest(String message,Integer status) {
		super();
		this.status = status;
		this.message = message;
	}

	public FinancialInputRequest(Object data, String message,Integer status) {

		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Double getAdjustedTotalDebtEquityFy() {
		return adjustedTotalDebtEquityFy;
	}

	public void setAdjustedTotalDebtEquityFy(Double adjustedTotalDebtEquityFy) {
		this.adjustedTotalDebtEquityFy = adjustedTotalDebtEquityFy;
	}

	public Double getAdjustedTotalDebtEquitySy() {
		return adjustedTotalDebtEquitySy;
	}

	public void setAdjustedTotalDebtEquitySy(Double adjustedTotalDebtEquitySy) {
		this.adjustedTotalDebtEquitySy = adjustedTotalDebtEquitySy;
	}

	public Double getAdjustedTotalDebtEquityTy() {
		return adjustedTotalDebtEquityTy;
	}

	public void setAdjustedTotalDebtEquityTy(Double adjustedTotalDebtEquityTy) {
		this.adjustedTotalDebtEquityTy = adjustedTotalDebtEquityTy;
	}

	public Double getAssetInTransitFy() {
		return assetInTransitFy;
	}

	public void setAssetInTransitFy(Double assetInTransitFy) {
		this.assetInTransitFy = assetInTransitFy;
	}

	public Double getAssetInTransitSy() {
		return assetInTransitSy;
	}

	public void setAssetInTransitSy(Double assetInTransitSy) {
		this.assetInTransitSy = assetInTransitSy;
	}

	public Double getAssetInTransitTy() {
		return assetInTransitTy;
	}

	public FinancialInputRequest() {
		super();
		this.adjustedTotalDebtEquityFy = 0.0;
		this.adjustedTotalDebtEquitySy = 0.0;
		this.adjustedTotalDebtEquityTy = 0.0;
		this.assetInTransitFy = 0.0;
		this.assetInTransitSy = 0.0;
		this.assetInTransitTy = 0.0;
		this.assetTurnOverFy =  0.0;
		this.assetTurnOverSy =  0.0;
		this.assetTurnOverTy =   0.0;
		this.bookValueFy =  0.0;
		this.bookValueSy =  0.0;
		this.bookValueTy =  0.0;
		this.capitalWorkInProgressFy = 0.0;
		this.capitalWorkInProgressSy = 0.0;
		this.capitalWorkInProgressTy = 0.0;
		this.cashAndBankFy =  0.0;
		this.cashAndBankSy =  0.0;
		this.cashAndBankTy =  0.0;
		this.cashFromOperatingFy =  0.0;
		this.cashFromOperatingSy =  0.0;
		this.cashInterestCoverFy =  0.0;
		this.cashInterestCoverSy =  0.0;
		this.cfoMargineFy = 0.0;
		this.cfoMargineSy = 0.0;
		this.contingentLiablitiesFy =  0.0;
		this.contingentLiablitiesSy =  0.0;
		this.contingentLiablitiestTy = 0.0;
		this.creditorsTurnoverFy =  0.0;
		this.creditorsTurnoverSy =  0.0;
		this.creditorsTurnoverTy =  0.0;
		this.curruntRatioXFy =  0.0;
		this.curruntRatioXSy =  0.0;
		this.curruntRatioXTy =  0.0;
		this.debtEbitadFy =  0.0;
		this.debtEbitadSy =  0.0;
		this.debtEbitadTy =  0.0;
		this.debtorsTurnoverFy =  0.0;
		this.debtorsTurnoverSy =  0.0;
		this.debtorsTurnoverTy =  0.0;
		this.deferredTaxLiablitiesFy =  0.0;
		this.deferredTaxLiablitiesSy =  0.0;
		this.deferredTaxLiablitiesTy =  0.0;
		this.depriciationFy =  0.0;
		this.depriciationSy =  0.0;
		this.depriciationTy =  0.0;
		this.dividendPayOutFy =  0.0;
		this.dividendPayOutSy =  0.0;
		this.dividendPayOutTy =  0.0;
		this.earningPerShareFy = 0.0;
		this.earningPerShareSy = 0.0;
		this.earningPerShareTy = 0.0;
		this.ebitadPercentageFy =  0.0;
		this.ebitadPercentageSy =  0.0;
		this.ebitadPercentageTy =  0.0;
		this.ebitdaFy =  0.0;
		this.ebitdaSy =  0.0;
		this.employeeCostFy =  0.0;
		this.employeeCostSy =  0.0;
		this.employeeCostTy =  0.0;
		this.equityDividendFy =  0.0;
		this.equityDividendSy =  0.0;
		this.equityDividendTy =  0.0;
		this.exceptionalIncomeFy =  0.0;
		this.exceptionalIncomeSy =  0.0;
		this.exceptionalIncomeTy =  0.0;
		this.freeReserveEquityFy =  0.0;
		this.freeReserveEquitySy =  0.0;
		this.freeReserveEquityTy =  0.0;
		this.generalAndAdminExpeFy =  0.0;
		this.generalAndAdminExpeSy =  0.0;
		this.generalAndAdminExpeTy =  0.0;
		this.grossBlockFy =  0.0;
		this.grossBlockSy =  0.0;
		this.grossBlockTy =  0.0;
		this.grossSalesFy =  0.0;
		this.grossSalesSy =  0.0;
		this.grossSalesTy =  0.0;
		this.growthCfoMargineFy =  0.0;
		this.growthCfoMargineSy =  0.0;
		this.growthDebtEquityFy =  0.0;
		this.growthDebtEquitySy =  0.0;
		this.impairmentofAssetFy = 0.0;
		this.impairmentofAssetSy = 0.0;
		this.impairmentofAssetTy = 0.0;
		this.increaseDecreaseStockFy =  0.0;
		this.increaseDecreaseStockSy =  0.0;
		this.increaseDecreaseStockTy =  0.0;
		this.increaseWorkingCapitalFy = 0.0;
		this.increaseWorkingCapitalSy = 0.0;
		this.intengibleAssetsFy = 0.0;
		this.intengibleAssetsSy = 0.0;
		this.intengibleAssetsTy = 0.0;
		this.interestFy =  0.0;
		this.interestPaidFy =  0.0;
		this.interestPaidSy =  0.0;
		this.interestSy =  0.0;
		this.interestTy =  0.0;
		this.inventoriesFy =  0.0;
		this.inventoriesSy =  0.0;
		this.inventoriesTy =  0.0;
		this.inventoryTurnOverFy =  0.0;
		this.inventoryTurnOverSy =  0.0;
		this.inventoryTurnOverTy =  0.0;
		this.investmentInSubsidiariesFy =  0.0;
		this.investmentInSubsidiariesSy =  0.0;
		this.investmentInSubsidiariesTy =  0.0;
		this.lessAccumulatedDepreFy =  0.0;
		this.lessAccumulatedDepreSy =  0.0;
		this.lessAccumulatedDepreTy =  0.0;
		this.lessExciseDuityFy =  0.0;
		this.lessExciseDuitySy =  0.0;
		this.lessExciseDuityTy =  0.0;
		this.lessExpeCapitaFy =  0.0;
		this.lessExpeCapitaSy =  0.0;
		this.lessExpeCapitaTy =  0.0;
		this.longTermLoansAndAdvaFy =  0.0;
		this.longTermLoansAndAdvaSy =  0.0;
		this.longTermLoansAndAdvaTy =  0.0;
		this.longTermProvisionFy =  0.0;
		this.longTermProvisionSy =  0.0;
		this.longTermProvisionTy =  0.0;
		this.minorityInterestFy =  0.0;
		this.minorityInterestSy =  0.0;
		this.minorityInterestTy =  0.0;
		this.miscelExpeFy = 0.0;
		this.miscelExpeSy = 0.0;
		this.miscelExpeTy = 0.0;
		this.netBlockFy =  0.0;
		this.netBlockSy =  0.0;
		this.netBlockTy =  0.0;
		this.netSaleFy =  0.0;
		this.netSaleSy =  0.0;
		this.netSaleTy =  0.0;
		this.netSalesGrowthCapitalFy = 0.0;
		this.netSalesGrowthCapitalSy = 0.0;
		this.noOfMonthFy =  0.0;
		this.noOfMonthSy =  0.0;
		this.noOfMonthTy =  0.0;
		this.operatingProfitEbitadOiFy =  0.0;
		this.operatingProfitEbitadOiSy =  0.0;
		this.operatingProfitEbitadOiTy =  0.0;
		this.operatingProfitExclOiFy =  0.0;
		this.operatingProfitExclOiSy =  0.0;
		this.operatingProfitExclOiTy =  0.0;
		this.otheNonCurruntAssetFy =  0.0;
		this.otheNonCurruntAssetSy =  0.0;
		this.otheNonCurruntAssetTy =  0.0;
		this.otherBorrowingFy =  0.0;
		this.otherBorrowingSy =  0.0;
		this.otherBorrowingTy =  0.0;
		this.otherCurruntAssetFy =  0.0;
		this.otherCurruntAssetSy =  0.0;
		this.otherCurruntAssetTy =  0.0;
		this.otherCurruntLiablitiesFy =  0.0;
		this.otherCurruntLiablitiesSy =  0.0;
		this.otherCurruntLiablitiesTy =  0.0;
		this.otherIncomeFy =  0.0;
		this.otherIncomeSy =  0.0;
		this.otherIncomeTy =  0.0;
		this.otherInvestmentFy =  0.0;
		this.otherInvestmentSy =  0.0;
		this.otherInvestmentTy =  0.0;
		this.otherLongTermLiablitiesFy =  0.0;
		this.otherLongTermLiablitiesSy =  0.0;
		this.otherLongTermLiablitiesTy =  0.0;
		this.otherReserveAndSurplusFy =  0.0;
		this.otherReserveAndSurplusSy =  0.0;
		this.otherReserveAndSurplusTy =  0.0;
		this.patGrowthCapitalFy =  0.0;
		this.patGrowthCapitalSy =  0.0;
		this.patmFy =  0.0;
		this.patmSy =  0.0;
		this.patmTy =  0.0;
		this.pbdtFy =  0.0;
		this.pbdtSy =  0.0;
		this.pbdtTy =  0.0;
		this.powerAndFuelCostFy =  0.0;
		this.powerAndFuelCostSy =  0.0;
		this.powerAndFuelCostTy =  0.0;
		this.preOperativeExpeFy =  0.0;
		this.preOperativeExpeSy =  0.0;
		this.preOperativeExpeTy =  0.0;
		this.profitAfterTaxFy =  0.0;
		this.profitAfterTaxSy =  0.0;
		this.profitAfterTaxTy =  0.0;
		this.profitBeforeTaxFy = 0.0;
		this.profitBeforeTaxSy = 0.0;
		this.profitBeforeTaxTy = 0.0;
		this.profitBeforeTaxationFy =  0.0;
		this.profitBeforeTaxationSy =  0.0;
		this.profitBeforeTaxationTy =  0.0;
		this.provisionForTaxFy = 0.0;
		this.provisionForTaxSy = 0.0;
		this.provisionForTaxTy = 0.0;
		this.quickRatioXFy = 0.0;
		this.quickRatioXSy = 0.0;
		this.quickRatioXTy = 0.0;
		this.rawMaterialConsumedFy = 0.0;
		this.rawMaterialConsumedSy = 0.0;
		this.rawMaterialConsumedTy = 0.0;
		this.revalationReserveFy =  0.0;
		this.revalationReserveSy =  0.0;
		this.revalationReserveTy =  0.0;
		this.roceFy =  0.0;
		this.roceSy =  0.0;
		this.salesWorkingCapitalFy = 0.0;
		this.salesWorkingCapitalSy = 0.0;
		this.salesWorkingCapitalTy = 0.0;
		this.securedLoansFy =  0.0;
		this.securedLoansSy =  0.0;
		this.securedLoansTy =  0.0;
		this.sellingAndDistriExpeFy =  0.0;
		this.sellingAndDistriExpeSy =  0.0;
		this.sellingAndDistriExpeTy =  0.0;
		this.shareCapitalFy =  0.0;
		this.shareCapitalSy =  0.0;
		this.shareCapitalTy =  0.0;
		this.shareFaceValue =  0.0;
		this.shareHolderFundsFy =  0.0;
		this.shareHolderFundsSy =  0.0;
		this.shareHolderFundsTy =  0.0;
		this.shareWarrantOutstandingsFy =  0.0;
		this.shareWarrantOutstandingsSy =  0.0;
		this.shareWarrantOutstandingsTy =  0.0;
		this.shortTermLoansAdvancesFy =  0.0;
		this.shortTermLoansAdvancesSy =  0.0;
		this.shortTermLoansAdvancesTy =  0.0;
		this.shortTermProvisionFy =  0.0;
		this.shortTermProvisionSy =  0.0;
		this.shortTermProvisionTy =  0.0;
		this.sundryDebtorsFy = 0.0;
		this.sundryDebtorsSy = 0.0;
		this.sundryDebtorsTy = 0.0;
		this.taxPaidFy =  0.0;
		this.taxPaidSy =  0.0;
		this.totalAssetFy =  0.0;
		this.totalAssetSy =  0.0;
		this.totalAssetTy =  0.0;
		this.totalCurruntAssetFy =  0.0;
		this.totalCurruntAssetSy =  0.0;
		this.totalCurruntAssetTy =  0.0;
		this.totalCurruntLiablitiesFy =  0.0;
		this.totalCurruntLiablitiesSy =  0.0;
		this.totalCurruntLiablitiesTy =  0.0;
		this.totalExpenditureFy =  0.0;
		this.totalExpenditureSy =  0.0;
		this.totalExpenditureTy =  0.0;
		this.totalLiablitiesFy = 0.0;
		this.totalLiablitiesSy = 0.0;
		this.totalLiablitiesTy = 0.0;
		this.totalNonCurruntAssetFy =  0.0;
		this.totalNonCurruntAssetSy =  0.0;
		this.totalNonCurruntAssetTy =  0.0;
		this.totalNonCurruntLiablitiesFy =  0.0;
		this.totalNonCurruntLiablitiesSy =  0.0;
		this.totalNonCurruntLiablitiesTy =  0.0;
		this.tradePayablesFy =  0.0;
		this.tradePayablesSy =  0.0;
		this.tradePayablesTy =  0.0;
		this.unsecuredLoansOthersFy = 0.0;
		this.unsecuredLoansOthersSy = 0.0;
		this.unsecuredLoansOthersTy = 0.0;
		this.unsecuredLoansPromotersFy = 0.0;
		this.unsecuredLoansPromotersSy = 0.0;
		this.unsecuredLoansPromotersTy = 0.0;
		this.OtherIncomeNeedTocCheckAssetFy =  0.0;
		this.OtherIncomeNeedTocCheckAssetSy =  0.0;
		this.OtherIncomeNeedTocCheckAssetTy =  0.0;
		this.OtherIncomeNeedTocCheckOpFy =  0.0;
		this.OtherIncomeNeedTocCheckOpSy =  0.0;
		this.OtherIncomeNeedTocCheckOpTy =  0.0;
		this.OtherIncomeNeedTocCheckLiaFy = 0.0;
		this.OtherIncomeNeedTocCheckLiaSy = 0.0;
		this.OtherIncomeNeedTocCheckLiaTy = 0.0;
	}

	public void setAssetInTransitTy(Double assetInTransitTy) {
		this.assetInTransitTy = assetInTransitTy;
	}

	public Double getAssetTurnOverFy() {
		return assetTurnOverFy;
	}

	public void setAssetTurnOverFy(Double assetTurnOverFy) {
		this.assetTurnOverFy = assetTurnOverFy;
	}

	public Double getAssetTurnOverSy() {
		return assetTurnOverSy;
	}

	public void setAssetTurnOverSy(Double assetTurnOverSy) {
		this.assetTurnOverSy = assetTurnOverSy;
	}

	public Double getAssetTurnOverTy() {
		return assetTurnOverTy;
	}

	public void setAssetTurnOverTy(Double assetTurnOverTy) {
		this.assetTurnOverTy = assetTurnOverTy;
	}

	public Double getBookValueFy() {
		return bookValueFy;
	}

	public void setBookValueFy(Double bookValueFy) {
		this.bookValueFy = bookValueFy;
	}

	public Double getBookValueSy() {
		return bookValueSy;
	}

	public void setBookValueSy(Double bookValueSy) {
		this.bookValueSy = bookValueSy;
	}

	public Double getBookValueTy() {
		return bookValueTy;
	}

	public void setBookValueTy(Double bookValueTy) {
		this.bookValueTy = bookValueTy;
	}

	public Double getCapitalWorkInProgressFy() {
		return capitalWorkInProgressFy;
	}

	public void setCapitalWorkInProgressFy(Double capitalWorkInProgressFy) {
		this.capitalWorkInProgressFy = capitalWorkInProgressFy;
	}

	public Double getCapitalWorkInProgressSy() {
		return capitalWorkInProgressSy;
	}

	public void setCapitalWorkInProgressSy(Double capitalWorkInProgressSy) {
		this.capitalWorkInProgressSy = capitalWorkInProgressSy;
	}

	public Double getCapitalWorkInProgressTy() {
		return capitalWorkInProgressTy;
	}

	public void setCapitalWorkInProgressTy(Double capitalWorkInProgressTy) {
		this.capitalWorkInProgressTy = capitalWorkInProgressTy;
	}

	public Double getCashAndBankFy() {
		return cashAndBankFy;
	}

	public void setCashAndBankFy(Double cashAndBankFy) {
		this.cashAndBankFy = cashAndBankFy;
	}

	public Double getCashAndBankSy() {
		return cashAndBankSy;
	}

	public void setCashAndBankSy(Double cashAndBankSy) {
		this.cashAndBankSy = cashAndBankSy;
	}

	public Double getCashAndBankTy() {
		return cashAndBankTy;
	}

	public void setCashAndBankTy(Double cashAndBankTy) {
		this.cashAndBankTy = cashAndBankTy;
	}

	public Double getCashFromOperatingFy() {
		return cashFromOperatingFy;
	}

	public void setCashFromOperatingFy(Double cashFromOperatingFy) {
		this.cashFromOperatingFy = cashFromOperatingFy;
	}

	public Double getCashFromOperatingSy() {
		return cashFromOperatingSy;
	}

	public void setCashFromOperatingSy(Double cashFromOperatingSy) {
		this.cashFromOperatingSy = cashFromOperatingSy;
	}

	public Double getCashInterestCoverFy() {
		return cashInterestCoverFy;
	}

	public void setCashInterestCoverFy(Double cashInterestCoverFy) {
		this.cashInterestCoverFy = cashInterestCoverFy;
	}

	public Double getCashInterestCoverSy() {
		return cashInterestCoverSy;
	}

	public void setCashInterestCoverSy(Double cashInterestCoverSy) {
		this.cashInterestCoverSy = cashInterestCoverSy;
	}

	public Double getCfoMargineFy() {
		return cfoMargineFy;
	}

	public void setCfoMargineFy(Double cfoMargineFy) {
		this.cfoMargineFy = cfoMargineFy;
	}

	public Double getCfoMargineSy() {
		return cfoMargineSy;
	}

	public void setCfoMargineSy(Double cfoMargineSy) {
		this.cfoMargineSy = cfoMargineSy;
	}

	public Double getContingentLiablitiesFy() {
		return contingentLiablitiesFy;
	}

	public void setContingentLiablitiesFy(Double contingentLiablitiesFy) {
		this.contingentLiablitiesFy = contingentLiablitiesFy;
	}

	public Double getContingentLiablitiesSy() {
		return contingentLiablitiesSy;
	}

	public void setContingentLiablitiesSy(Double contingentLiablitiesSy) {
		this.contingentLiablitiesSy = contingentLiablitiesSy;
	}

	public Double getContingentLiablitiestTy() {
		return contingentLiablitiestTy;
	}

	public void setContingentLiablitiestTy(Double contingentLiablitiestTy) {
		this.contingentLiablitiestTy = contingentLiablitiestTy;
	}

	public Double getCreditorsTurnoverFy() {
		return creditorsTurnoverFy;
	}

	public void setCreditorsTurnoverFy(Double creditorsTurnoverFy) {
		this.creditorsTurnoverFy = creditorsTurnoverFy;
	}

	public Double getCreditorsTurnoverSy() {
		return creditorsTurnoverSy;
	}

	public void setCreditorsTurnoverSy(Double creditorsTurnoverSy) {
		this.creditorsTurnoverSy = creditorsTurnoverSy;
	}

	public Double getCreditorsTurnoverTy() {
		return creditorsTurnoverTy;
	}

	public void setCreditorsTurnoverTy(Double creditorsTurnoverTy) {
		this.creditorsTurnoverTy = creditorsTurnoverTy;
	}

	public Double getCurruntRatioXFy() {
		return curruntRatioXFy;
	}

	public void setCurruntRatioXFy(Double curruntRatioXFy) {
		this.curruntRatioXFy = curruntRatioXFy;
	}

	public Double getCurruntRatioXSy() {
		return curruntRatioXSy;
	}

	public void setCurruntRatioXSy(Double curruntRatioXSy) {
		this.curruntRatioXSy = curruntRatioXSy;
	}

	public Double getCurruntRatioXTy() {
		return curruntRatioXTy;
	}

	public void setCurruntRatioXTy(Double curruntRatioXTy) {
		this.curruntRatioXTy = curruntRatioXTy;
	}

	public Double getDebtEbitadFy() {
		return debtEbitadFy;
	}

	public void setDebtEbitadFy(Double debtEbitadFy) {
		this.debtEbitadFy = debtEbitadFy;
	}

	public Double getDebtEbitadSy() {
		return debtEbitadSy;
	}

	public void setDebtEbitadSy(Double debtEbitadSy) {
		this.debtEbitadSy = debtEbitadSy;
	}

	public Double getDebtEbitadTy() {
		return debtEbitadTy;
	}

	public void setDebtEbitadTy(Double debtEbitadTy) {
		this.debtEbitadTy = debtEbitadTy;
	}

	public Double getDebtorsTurnoverFy() {
		return debtorsTurnoverFy;
	}

	public void setDebtorsTurnoverFy(Double debtorsTurnoverFy) {
		this.debtorsTurnoverFy = debtorsTurnoverFy;
	}

	public Double getDebtorsTurnoverSy() {
		return debtorsTurnoverSy;
	}

	public void setDebtorsTurnoverSy(Double debtorsTurnoverSy) {
		this.debtorsTurnoverSy = debtorsTurnoverSy;
	}

	public Double getDebtorsTurnoverTy() {
		return debtorsTurnoverTy;
	}

	public void setDebtorsTurnoverTy(Double debtorsTurnoverTy) {
		this.debtorsTurnoverTy = debtorsTurnoverTy;
	}

	public Double getDeferredTaxLiablitiesFy() {
		return deferredTaxLiablitiesFy;
	}

	public void setDeferredTaxLiablitiesFy(Double deferredTaxLiablitiesFy) {
		this.deferredTaxLiablitiesFy = deferredTaxLiablitiesFy;
	}

	public Double getDeferredTaxLiablitiesSy() {
		return deferredTaxLiablitiesSy;
	}

	public void setDeferredTaxLiablitiesSy(Double deferredTaxLiablitiesSy) {
		this.deferredTaxLiablitiesSy = deferredTaxLiablitiesSy;
	}

	public Double getDeferredTaxLiablitiesTy() {
		return deferredTaxLiablitiesTy;
	}

	public void setDeferredTaxLiablitiesTy(Double deferredTaxLiablitiesTy) {
		this.deferredTaxLiablitiesTy = deferredTaxLiablitiesTy;
	}

	public Double getDepriciationFy() {
		return depriciationFy;
	}

	public void setDepriciationFy(Double depriciationFy) {
		this.depriciationFy = depriciationFy;
	}

	public Double getDepriciationSy() {
		return depriciationSy;
	}

	public void setDepriciationSy(Double depriciationSy) {
		this.depriciationSy = depriciationSy;
	}

	public Double getDepriciationTy() {
		return depriciationTy;
	}

	public void setDepriciationTy(Double depriciationTy) {
		this.depriciationTy = depriciationTy;
	}

	public Double getDividendPayOutFy() {
		return dividendPayOutFy;
	}

	public void setDividendPayOutFy(Double dividendPayOutFy) {
		this.dividendPayOutFy = dividendPayOutFy;
	}

	public Double getDividendPayOutSy() {
		return dividendPayOutSy;
	}

	public void setDividendPayOutSy(Double dividendPayOutSy) {
		this.dividendPayOutSy = dividendPayOutSy;
	}

	public Double getDividendPayOutTy() {
		return dividendPayOutTy;
	}

	public void setDividendPayOutTy(Double dividendPayOutTy) {
		this.dividendPayOutTy = dividendPayOutTy;
	}

	public Double getEarningPerShareFy() {
		return earningPerShareFy;
	}

	public void setEarningPerShareFy(Double earningPerShareFy) {
		this.earningPerShareFy = earningPerShareFy;
	}

	public Double getEarningPerShareSy() {
		return earningPerShareSy;
	}

	public void setEarningPerShareSy(Double earningPerShareSy) {
		this.earningPerShareSy = earningPerShareSy;
	}

	public Double getEarningPerShareTy() {
		return earningPerShareTy;
	}

	public void setEarningPerShareTy(Double earningPerShareTy) {
		this.earningPerShareTy = earningPerShareTy;
	}

	public Double getEbitadPercentageFy() {
		return ebitadPercentageFy;
	}

	public void setEbitadPercentageFy(Double ebitadPercentageFy) {
		this.ebitadPercentageFy = ebitadPercentageFy;
	}

	public Double getEbitadPercentageSy() {
		return ebitadPercentageSy;
	}

	public void setEbitadPercentageSy(Double ebitadPercentageSy) {
		this.ebitadPercentageSy = ebitadPercentageSy;
	}

	public Double getEbitadPercentageTy() {
		return ebitadPercentageTy;
	}

	public void setEbitadPercentageTy(Double ebitadPercentageTy) {
		this.ebitadPercentageTy = ebitadPercentageTy;
	}

	public Double getEbitdaFy() {
		return ebitdaFy;
	}

	public void setEbitdaFy(Double ebitdaFy) {
		this.ebitdaFy = ebitdaFy;
	}

	public Double getEbitdaSy() {
		return ebitdaSy;
	}

	public void setEbitdaSy(Double ebitdaSy) {
		this.ebitdaSy = ebitdaSy;
	}

	public Double getEmployeeCostFy() {
		return employeeCostFy;
	}

	public void setEmployeeCostFy(Double employeeCostFy) {
		this.employeeCostFy = employeeCostFy;
	}

	public Double getEmployeeCostSy() {
		return employeeCostSy;
	}

	public void setEmployeeCostSy(Double employeeCostSy) {
		this.employeeCostSy = employeeCostSy;
	}

	public Double getEmployeeCostTy() {
		return employeeCostTy;
	}

	public void setEmployeeCostTy(Double employeeCostTy) {
		this.employeeCostTy = employeeCostTy;
	}

	public Double getEquityDividendFy() {
		return equityDividendFy;
	}

	public void setEquityDividendFy(Double equityDividendFy) {
		this.equityDividendFy = equityDividendFy;
	}

	public Double getEquityDividendSy() {
		return equityDividendSy;
	}

	public void setEquityDividendSy(Double equityDividendSy) {
		this.equityDividendSy = equityDividendSy;
	}

	public Double getEquityDividendTy() {
		return equityDividendTy;
	}

	public void setEquityDividendTy(Double equityDividendTy) {
		this.equityDividendTy = equityDividendTy;
	}

	public Double getExceptionalIncomeFy() {
		return exceptionalIncomeFy;
	}

	public void setExceptionalIncomeFy(Double exceptionalIncomeFy) {
		this.exceptionalIncomeFy = exceptionalIncomeFy;
	}

	public Double getExceptionalIncomeSy() {
		return exceptionalIncomeSy;
	}

	public void setExceptionalIncomeSy(Double exceptionalIncomeSy) {
		this.exceptionalIncomeSy = exceptionalIncomeSy;
	}

	public Double getExceptionalIncomeTy() {
		return exceptionalIncomeTy;
	}

	public void setExceptionalIncomeTy(Double exceptionalIncomeTy) {
		this.exceptionalIncomeTy = exceptionalIncomeTy;
	}

	public Double getFreeReserveEquityFy() {
		return freeReserveEquityFy;
	}

	public void setFreeReserveEquityFy(Double freeReserveEquityFy) {
		this.freeReserveEquityFy = freeReserveEquityFy;
	}

	public Double getFreeReserveEquitySy() {
		return freeReserveEquitySy;
	}

	public void setFreeReserveEquitySy(Double freeReserveEquitySy) {
		this.freeReserveEquitySy = freeReserveEquitySy;
	}

	public Double getFreeReserveEquityTy() {
		return freeReserveEquityTy;
	}

	public void setFreeReserveEquityTy(Double freeReserveEquityTy) {
		this.freeReserveEquityTy = freeReserveEquityTy;
	}

	public Double getGeneralAndAdminExpeFy() {
		return generalAndAdminExpeFy;
	}

	public void setGeneralAndAdminExpeFy(Double generalAndAdminExpeFy) {
		this.generalAndAdminExpeFy = generalAndAdminExpeFy;
	}

	public Double getGeneralAndAdminExpeSy() {
		return generalAndAdminExpeSy;
	}

	public void setGeneralAndAdminExpeSy(Double generalAndAdminExpeSy) {
		this.generalAndAdminExpeSy = generalAndAdminExpeSy;
	}

	public Double getGeneralAndAdminExpeTy() {
		return generalAndAdminExpeTy;
	}

	public void setGeneralAndAdminExpeTy(Double generalAndAdminExpeTy) {
		this.generalAndAdminExpeTy = generalAndAdminExpeTy;
	}

	public Double getGrossBlockFy() {
		return grossBlockFy;
	}

	public void setGrossBlockFy(Double grossBlockFy) {
		this.grossBlockFy = grossBlockFy;
	}

	public Double getGrossBlockSy() {
		return grossBlockSy;
	}

	public void setGrossBlockSy(Double grossBlockSy) {
		this.grossBlockSy = grossBlockSy;
	}

	public Double getGrossBlockTy() {
		return grossBlockTy;
	}

	public void setGrossBlockTy(Double grossBlockTy) {
		this.grossBlockTy = grossBlockTy;
	}

	public Double getGrossSalesFy() {
		return grossSalesFy;
	}

	public void setGrossSalesFy(Double grossSalesFy) {
		this.grossSalesFy = grossSalesFy;
	}

	public Double getGrossSalesSy() {
		return grossSalesSy;
	}

	public void setGrossSalesSy(Double grossSalesSy) {
		this.grossSalesSy = grossSalesSy;
	}

	public Double getGrossSalesTy() {
		return grossSalesTy;
	}

	public void setGrossSalesTy(Double grossSalesTy) {
		this.grossSalesTy = grossSalesTy;
	}

	public Double getGrowthCfoMargineFy() {
		return growthCfoMargineFy;
	}

	public void setGrowthCfoMargineFy(Double growthCfoMargineFy) {
		this.growthCfoMargineFy = growthCfoMargineFy;
	}

	public Double getGrowthCfoMargineSy() {
		return growthCfoMargineSy;
	}

	public void setGrowthCfoMargineSy(Double growthCfoMargineSy) {
		this.growthCfoMargineSy = growthCfoMargineSy;
	}

	public Double getGrowthDebtEquityFy() {
		return growthDebtEquityFy;
	}

	public void setGrowthDebtEquityFy(Double growthDebtEquityFy) {
		this.growthDebtEquityFy = growthDebtEquityFy;
	}

	public Double getGrowthDebtEquitySy() {
		return growthDebtEquitySy;
	}

	public void setGrowthDebtEquitySy(Double growthDebtEquitySy) {
		this.growthDebtEquitySy = growthDebtEquitySy;
	}

	public Double getImpairmentofAssetFy() {
		return impairmentofAssetFy;
	}

	public void setImpairmentofAssetFy(Double impairmentofAssetFy) {
		this.impairmentofAssetFy = impairmentofAssetFy;
	}

	public Double getImpairmentofAssetSy() {
		return impairmentofAssetSy;
	}

	public void setImpairmentofAssetSy(Double impairmentofAssetSy) {
		this.impairmentofAssetSy = impairmentofAssetSy;
	}

	public Double getImpairmentofAssetTy() {
		return impairmentofAssetTy;
	}

	public void setImpairmentofAssetTy(Double impairmentofAssetTy) {
		this.impairmentofAssetTy = impairmentofAssetTy;
	}

	public Double getIncreaseDecreaseStockFy() {
		return increaseDecreaseStockFy;
	}

	public void setIncreaseDecreaseStockFy(Double increaseDecreaseStockFy) {
		this.increaseDecreaseStockFy = increaseDecreaseStockFy;
	}

	public Double getIncreaseDecreaseStockSy() {
		return increaseDecreaseStockSy;
	}

	public void setIncreaseDecreaseStockSy(Double increaseDecreaseStockSy) {
		this.increaseDecreaseStockSy = increaseDecreaseStockSy;
	}

	public Double getIncreaseDecreaseStockTy() {
		return increaseDecreaseStockTy;
	}

	public void setIncreaseDecreaseStockTy(Double increaseDecreaseStockTy) {
		this.increaseDecreaseStockTy = increaseDecreaseStockTy;
	}

	public Double getIncreaseWorkingCapitalFy() {
		return increaseWorkingCapitalFy;
	}

	public void setIncreaseWorkingCapitalFy(Double increaseWorkingCapitalFy) {
		this.increaseWorkingCapitalFy = increaseWorkingCapitalFy;
	}

	public Double getIncreaseWorkingCapitalSy() {
		return increaseWorkingCapitalSy;
	}

	public void setIncreaseWorkingCapitalSy(Double increaseWorkingCapitalSy) {
		this.increaseWorkingCapitalSy = increaseWorkingCapitalSy;
	}

	public Double getIntengibleAssetsFy() {
		return intengibleAssetsFy;
	}

	public void setIntengibleAssetsFy(Double intengibleAssetsFy) {
		this.intengibleAssetsFy = intengibleAssetsFy;
	}

	public Double getIntengibleAssetsSy() {
		return intengibleAssetsSy;
	}

	public void setIntengibleAssetsSy(Double intengibleAssetsSy) {
		this.intengibleAssetsSy = intengibleAssetsSy;
	}

	public Double getIntengibleAssetsTy() {
		return intengibleAssetsTy;
	}

	public void setIntengibleAssetsTy(Double intengibleAssetsTy) {
		this.intengibleAssetsTy = intengibleAssetsTy;
	}

	public Double getInterestFy() {
		return interestFy;
	}

	public void setInterestFy(Double interestFy) {
		this.interestFy = interestFy;
	}

	public Double getInterestPaidFy() {
		return interestPaidFy;
	}

	public void setInterestPaidFy(Double interestPaidFy) {
		this.interestPaidFy = interestPaidFy;
	}

	public Double getInterestPaidSy() {
		return interestPaidSy;
	}

	public void setInterestPaidSy(Double interestPaidSy) {
		this.interestPaidSy = interestPaidSy;
	}

	public Double getInterestSy() {
		return interestSy;
	}

	public void setInterestSy(Double interestSy) {
		this.interestSy = interestSy;
	}

	public Double getInterestTy() {
		return interestTy;
	}

	public void setInterestTy(Double interestTy) {
		this.interestTy = interestTy;
	}

	public Double getInventoriesFy() {
		return inventoriesFy;
	}

	public void setInventoriesFy(Double inventoriesFy) {
		this.inventoriesFy = inventoriesFy;
	}

	public Double getInventoriesSy() {
		return inventoriesSy;
	}

	public void setInventoriesSy(Double inventoriesSy) {
		this.inventoriesSy = inventoriesSy;
	}

	public Double getInventoriesTy() {
		return inventoriesTy;
	}

	public void setInventoriesTy(Double inventoriesTy) {
		this.inventoriesTy = inventoriesTy;
	}

	public Double getInventoryTurnOverFy() {
		return inventoryTurnOverFy;
	}

	public void setInventoryTurnOverFy(Double inventoryTurnOverFy) {
		this.inventoryTurnOverFy = inventoryTurnOverFy;
	}

	public Double getInventoryTurnOverSy() {
		return inventoryTurnOverSy;
	}

	public void setInventoryTurnOverSy(Double inventoryTurnOverSy) {
		this.inventoryTurnOverSy = inventoryTurnOverSy;
	}

	public Double getInventoryTurnOverTy() {
		return inventoryTurnOverTy;
	}

	public void setInventoryTurnOverTy(Double inventoryTurnOverTy) {
		this.inventoryTurnOverTy = inventoryTurnOverTy;
	}

	public Double getInvestmentInSubsidiariesFy() {
		return investmentInSubsidiariesFy;
	}

	public void setInvestmentInSubsidiariesFy(Double investmentInSubsidiariesFy) {
		this.investmentInSubsidiariesFy = investmentInSubsidiariesFy;
	}

	public Double getInvestmentInSubsidiariesSy() {
		return investmentInSubsidiariesSy;
	}

	public void setInvestmentInSubsidiariesSy(Double investmentInSubsidiariesSy) {
		this.investmentInSubsidiariesSy = investmentInSubsidiariesSy;
	}

	public Double getInvestmentInSubsidiariesTy() {
		return investmentInSubsidiariesTy;
	}

	public void setInvestmentInSubsidiariesTy(Double investmentInSubsidiariesTy) {
		this.investmentInSubsidiariesTy = investmentInSubsidiariesTy;
	}

	public Double getLessAccumulatedDepreFy() {
		return lessAccumulatedDepreFy;
	}

	public void setLessAccumulatedDepreFy(Double lessAccumulatedDepreFy) {
		this.lessAccumulatedDepreFy = lessAccumulatedDepreFy;
	}

	public Double getLessAccumulatedDepreSy() {
		return lessAccumulatedDepreSy;
	}

	public void setLessAccumulatedDepreSy(Double lessAccumulatedDepreSy) {
		this.lessAccumulatedDepreSy = lessAccumulatedDepreSy;
	}

	public Double getLessAccumulatedDepreTy() {
		return lessAccumulatedDepreTy;
	}

	public void setLessAccumulatedDepreTy(Double lessAccumulatedDepreTy) {
		this.lessAccumulatedDepreTy = lessAccumulatedDepreTy;
	}

	public Double getLessExciseDuityFy() {
		return lessExciseDuityFy;
	}

	public void setLessExciseDuityFy(Double lessExciseDuityFy) {
		this.lessExciseDuityFy = lessExciseDuityFy;
	}

	public Double getLessExciseDuitySy() {
		return lessExciseDuitySy;
	}

	public void setLessExciseDuitySy(Double lessExciseDuitySy) {
		this.lessExciseDuitySy = lessExciseDuitySy;
	}

	public Double getLessExciseDuityTy() {
		return lessExciseDuityTy;
	}

	public void setLessExciseDuityTy(Double lessExciseDuityTy) {
		this.lessExciseDuityTy = lessExciseDuityTy;
	}

	public Double getLessExpeCapitaFy() {
		return lessExpeCapitaFy;
	}

	public void setLessExpeCapitaFy(Double lessExpeCapitaFy) {
		this.lessExpeCapitaFy = lessExpeCapitaFy;
	}

	public Double getLessExpeCapitaSy() {
		return lessExpeCapitaSy;
	}

	public void setLessExpeCapitaSy(Double lessExpeCapitaSy) {
		this.lessExpeCapitaSy = lessExpeCapitaSy;
	}

	public Double getLessExpeCapitaTy() {
		return lessExpeCapitaTy;
	}

	public void setLessExpeCapitaTy(Double lessExpeCapitaTy) {
		this.lessExpeCapitaTy = lessExpeCapitaTy;
	}

	public Double getLongTermLoansAndAdvaFy() {
		return longTermLoansAndAdvaFy;
	}

	public void setLongTermLoansAndAdvaFy(Double longTermLoansAndAdvaFy) {
		this.longTermLoansAndAdvaFy = longTermLoansAndAdvaFy;
	}

	public Double getLongTermLoansAndAdvaSy() {
		return longTermLoansAndAdvaSy;
	}

	public void setLongTermLoansAndAdvaSy(Double longTermLoansAndAdvaSy) {
		this.longTermLoansAndAdvaSy = longTermLoansAndAdvaSy;
	}

	public Double getLongTermLoansAndAdvaTy() {
		return longTermLoansAndAdvaTy;
	}

	public void setLongTermLoansAndAdvaTy(Double longTermLoansAndAdvaTy) {
		this.longTermLoansAndAdvaTy = longTermLoansAndAdvaTy;
	}

	public Double getLongTermProvisionFy() {
		return longTermProvisionFy;
	}

	public void setLongTermProvisionFy(Double longTermProvisionFy) {
		this.longTermProvisionFy = longTermProvisionFy;
	}

	public Double getLongTermProvisionSy() {
		return longTermProvisionSy;
	}

	public void setLongTermProvisionSy(Double longTermProvisionSy) {
		this.longTermProvisionSy = longTermProvisionSy;
	}

	public Double getLongTermProvisionTy() {
		return longTermProvisionTy;
	}

	public void setLongTermProvisionTy(Double longTermProvisionTy) {
		this.longTermProvisionTy = longTermProvisionTy;
	}

	public Double getMinorityInterestFy() {
		return minorityInterestFy;
	}

	public void setMinorityInterestFy(Double minorityInterestFy) {
		this.minorityInterestFy = minorityInterestFy;
	}

	public Double getMinorityInterestSy() {
		return minorityInterestSy;
	}

	public void setMinorityInterestSy(Double minorityInterestSy) {
		this.minorityInterestSy = minorityInterestSy;
	}

	public Double getMinorityInterestTy() {
		return minorityInterestTy;
	}

	public void setMinorityInterestTy(Double minorityInterestTy) {
		this.minorityInterestTy = minorityInterestTy;
	}

	public Double getMiscelExpeFy() {
		return miscelExpeFy;
	}

	public void setMiscelExpeFy(Double miscelExpeFy) {
		this.miscelExpeFy = miscelExpeFy;
	}

	public Double getMiscelExpeSy() {
		return miscelExpeSy;
	}

	public void setMiscelExpeSy(Double miscelExpeSy) {
		this.miscelExpeSy = miscelExpeSy;
	}

	public Double getMiscelExpeTy() {
		return miscelExpeTy;
	}

	public void setMiscelExpeTy(Double miscelExpeTy) {
		this.miscelExpeTy = miscelExpeTy;
	}

	public Double getNetBlockFy() {
		return netBlockFy;
	}

	public void setNetBlockFy(Double netBlockFy) {
		this.netBlockFy = netBlockFy;
	}

	public Double getNetBlockSy() {
		return netBlockSy;
	}

	public void setNetBlockSy(Double netBlockSy) {
		this.netBlockSy = netBlockSy;
	}

	public Double getNetBlockTy() {
		return netBlockTy;
	}

	public void setNetBlockTy(Double netBlockTy) {
		this.netBlockTy = netBlockTy;
	}

	public Double getNetSaleFy() {
		return netSaleFy;
	}

	public void setNetSaleFy(Double netSaleFy) {
		this.netSaleFy = netSaleFy;
	}

	public Double getNetSaleSy() {
		return netSaleSy;
	}

	public void setNetSaleSy(Double netSaleSy) {
		this.netSaleSy = netSaleSy;
	}

	public Double getNetSaleTy() {
		return netSaleTy;
	}

	public void setNetSaleTy(Double netSaleTy) {
		this.netSaleTy = netSaleTy;
	}

	public Double getNetSalesGrowthCapitalFy() {
		return netSalesGrowthCapitalFy;
	}

	public void setNetSalesGrowthCapitalFy(Double netSalesGrowthCapitalFy) {
		this.netSalesGrowthCapitalFy = netSalesGrowthCapitalFy;
	}

	public Double getNetSalesGrowthCapitalSy() {
		return netSalesGrowthCapitalSy;
	}

	public void setNetSalesGrowthCapitalSy(Double netSalesGrowthCapitalSy) {
		this.netSalesGrowthCapitalSy = netSalesGrowthCapitalSy;
	}

	public Double getNoOfMonthFy() {
		return noOfMonthFy;
	}

	public void setNoOfMonthFy(Double noOfMonthFy) {
		this.noOfMonthFy = noOfMonthFy;
	}

	public Double getNoOfMonthSy() {
		return noOfMonthSy;
	}

	public void setNoOfMonthSy(Double noOfMonthSy) {
		this.noOfMonthSy = noOfMonthSy;
	}

	public Double getNoOfMonthTy() {
		return noOfMonthTy;
	}

	public void setNoOfMonthTy(Double noOfMonthTy) {
		this.noOfMonthTy = noOfMonthTy;
	}

	public Double getOperatingProfitEbitadOiFy() {
		return operatingProfitEbitadOiFy;
	}

	public void setOperatingProfitEbitadOiFy(Double operatingProfitEbitadOiFy) {
		this.operatingProfitEbitadOiFy = operatingProfitEbitadOiFy;
	}

	public Double getOperatingProfitEbitadOiSy() {
		return operatingProfitEbitadOiSy;
	}

	public void setOperatingProfitEbitadOiSy(Double operatingProfitEbitadOiSy) {
		this.operatingProfitEbitadOiSy = operatingProfitEbitadOiSy;
	}

	public Double getOperatingProfitEbitadOiTy() {
		return operatingProfitEbitadOiTy;
	}

	public void setOperatingProfitEbitadOiTy(Double operatingProfitEbitadOiTy) {
		this.operatingProfitEbitadOiTy = operatingProfitEbitadOiTy;
	}

	public Double getOperatingProfitExclOiFy() {
		return operatingProfitExclOiFy;
	}

	public void setOperatingProfitExclOiFy(Double operatingProfitExclOiFy) {
		this.operatingProfitExclOiFy = operatingProfitExclOiFy;
	}

	public Double getOperatingProfitExclOiSy() {
		return operatingProfitExclOiSy;
	}

	public void setOperatingProfitExclOiSy(Double operatingProfitExclOiSy) {
		this.operatingProfitExclOiSy = operatingProfitExclOiSy;
	}

	public Double getOperatingProfitExclOiTy() {
		return operatingProfitExclOiTy;
	}

	public void setOperatingProfitExclOiTy(Double operatingProfitExclOiTy) {
		this.operatingProfitExclOiTy = operatingProfitExclOiTy;
	}

	public Double getOtheNonCurruntAssetFy() {
		return otheNonCurruntAssetFy;
	}

	public void setOtheNonCurruntAssetFy(Double otheNonCurruntAssetFy) {
		this.otheNonCurruntAssetFy = otheNonCurruntAssetFy;
	}

	public Double getOtheNonCurruntAssetSy() {
		return otheNonCurruntAssetSy;
	}

	public void setOtheNonCurruntAssetSy(Double otheNonCurruntAssetSy) {
		this.otheNonCurruntAssetSy = otheNonCurruntAssetSy;
	}

	public Double getOtheNonCurruntAssetTy() {
		return otheNonCurruntAssetTy;
	}

	public void setOtheNonCurruntAssetTy(Double otheNonCurruntAssetTy) {
		this.otheNonCurruntAssetTy = otheNonCurruntAssetTy;
	}

	public Double getOtherBorrowingFy() {
		return otherBorrowingFy;
	}

	public void setOtherBorrowingFy(Double otherBorrowingFy) {
		this.otherBorrowingFy = otherBorrowingFy;
	}

	public Double getOtherBorrowingSy() {
		return otherBorrowingSy;
	}

	public void setOtherBorrowingSy(Double otherBorrowingSy) {
		this.otherBorrowingSy = otherBorrowingSy;
	}

	public Double getOtherBorrowingTy() {
		return otherBorrowingTy;
	}

	public void setOtherBorrowingTy(Double otherBorrowingTy) {
		this.otherBorrowingTy = otherBorrowingTy;
	}

	public Double getOtherCurruntAssetFy() {
		return otherCurruntAssetFy;
	}

	public void setOtherCurruntAssetFy(Double otherCurruntAssetFy) {
		this.otherCurruntAssetFy = otherCurruntAssetFy;
	}

	public Double getOtherCurruntAssetSy() {
		return otherCurruntAssetSy;
	}

	public void setOtherCurruntAssetSy(Double otherCurruntAssetSy) {
		this.otherCurruntAssetSy = otherCurruntAssetSy;
	}

	public Double getOtherCurruntAssetTy() {
		return otherCurruntAssetTy;
	}

	public void setOtherCurruntAssetTy(Double otherCurruntAssetTy) {
		this.otherCurruntAssetTy = otherCurruntAssetTy;
	}

	public Double getOtherCurruntLiablitiesFy() {
		return otherCurruntLiablitiesFy;
	}

	public void setOtherCurruntLiablitiesFy(Double otherCurruntLiablitiesFy) {
		this.otherCurruntLiablitiesFy = otherCurruntLiablitiesFy;
	}

	public Double getOtherCurruntLiablitiesSy() {
		return otherCurruntLiablitiesSy;
	}

	public void setOtherCurruntLiablitiesSy(Double otherCurruntLiablitiesSy) {
		this.otherCurruntLiablitiesSy = otherCurruntLiablitiesSy;
	}

	public Double getOtherCurruntLiablitiesTy() {
		return otherCurruntLiablitiesTy;
	}

	public void setOtherCurruntLiablitiesTy(Double otherCurruntLiablitiesTy) {
		this.otherCurruntLiablitiesTy = otherCurruntLiablitiesTy;
	}

	public Double getOtherIncomeFy() {
		return otherIncomeFy;
	}

	public void setOtherIncomeFy(Double otherIncomeFy) {
		this.otherIncomeFy = otherIncomeFy;
	}

	public Double getOtherIncomeSy() {
		return otherIncomeSy;
	}

	public void setOtherIncomeSy(Double otherIncomeSy) {
		this.otherIncomeSy = otherIncomeSy;
	}

	public Double getOtherIncomeTy() {
		return otherIncomeTy;
	}

	public void setOtherIncomeTy(Double otherIncomeTy) {
		this.otherIncomeTy = otherIncomeTy;
	}

	public Double getOtherInvestmentFy() {
		return otherInvestmentFy;
	}

	public void setOtherInvestmentFy(Double otherInvestmentFy) {
		this.otherInvestmentFy = otherInvestmentFy;
	}

	public Double getOtherInvestmentSy() {
		return otherInvestmentSy;
	}

	public void setOtherInvestmentSy(Double otherInvestmentSy) {
		this.otherInvestmentSy = otherInvestmentSy;
	}

	public Double getOtherInvestmentTy() {
		return otherInvestmentTy;
	}

	public void setOtherInvestmentTy(Double otherInvestmentTy) {
		this.otherInvestmentTy = otherInvestmentTy;
	}

	public Double getOtherLongTermLiablitiesFy() {
		return otherLongTermLiablitiesFy;
	}

	public void setOtherLongTermLiablitiesFy(Double otherLongTermLiablitiesFy) {
		this.otherLongTermLiablitiesFy = otherLongTermLiablitiesFy;
	}

	public Double getOtherLongTermLiablitiesSy() {
		return otherLongTermLiablitiesSy;
	}

	public void setOtherLongTermLiablitiesSy(Double otherLongTermLiablitiesSy) {
		this.otherLongTermLiablitiesSy = otherLongTermLiablitiesSy;
	}

	public Double getOtherLongTermLiablitiesTy() {
		return otherLongTermLiablitiesTy;
	}

	public void setOtherLongTermLiablitiesTy(Double otherLongTermLiablitiesTy) {
		this.otherLongTermLiablitiesTy = otherLongTermLiablitiesTy;
	}

	public Double getOtherReserveAndSurplusFy() {
		return otherReserveAndSurplusFy;
	}

	public void setOtherReserveAndSurplusFy(Double otherReserveAndSurplusFy) {
		this.otherReserveAndSurplusFy = otherReserveAndSurplusFy;
	}

	public Double getOtherReserveAndSurplusSy() {
		return otherReserveAndSurplusSy;
	}

	public void setOtherReserveAndSurplusSy(Double otherReserveAndSurplusSy) {
		this.otherReserveAndSurplusSy = otherReserveAndSurplusSy;
	}

	public Double getOtherReserveAndSurplusTy() {
		return otherReserveAndSurplusTy;
	}

	public void setOtherReserveAndSurplusTy(Double otherReserveAndSurplusTy) {
		this.otherReserveAndSurplusTy = otherReserveAndSurplusTy;
	}

	public Double getPatGrowthCapitalFy() {
		return patGrowthCapitalFy;
	}

	public void setPatGrowthCapitalFy(Double patGrowthCapitalFy) {
		this.patGrowthCapitalFy = patGrowthCapitalFy;
	}

	public Double getPatGrowthCapitalSy() {
		return patGrowthCapitalSy;
	}

	public void setPatGrowthCapitalSy(Double patGrowthCapitalSy) {
		this.patGrowthCapitalSy = patGrowthCapitalSy;
	}

	public Double getPatmFy() {
		return patmFy;
	}

	public void setPatmFy(Double patmFy) {
		this.patmFy = patmFy;
	}

	public Double getPatmSy() {
		return patmSy;
	}

	public void setPatmSy(Double patmSy) {
		this.patmSy = patmSy;
	}

	public Double getPatmTy() {
		return patmTy;
	}

	public void setPatmTy(Double patmTy) {
		this.patmTy = patmTy;
	}

	public Double getPbdtFy() {
		return pbdtFy;
	}

	public void setPbdtFy(Double pbdtFy) {
		this.pbdtFy = pbdtFy;
	}

	public Double getPbdtSy() {
		return pbdtSy;
	}

	public void setPbdtSy(Double pbdtSy) {
		this.pbdtSy = pbdtSy;
	}

	public Double getPbdtTy() {
		return pbdtTy;
	}

	public void setPbdtTy(Double pbdtTy) {
		this.pbdtTy = pbdtTy;
	}

	public Double getPowerAndFuelCostFy() {
		return powerAndFuelCostFy;
	}

	public void setPowerAndFuelCostFy(Double powerAndFuelCostFy) {
		this.powerAndFuelCostFy = powerAndFuelCostFy;
	}

	public Double getPowerAndFuelCostSy() {
		return powerAndFuelCostSy;
	}

	public void setPowerAndFuelCostSy(Double powerAndFuelCostSy) {
		this.powerAndFuelCostSy = powerAndFuelCostSy;
	}

	public Double getPowerAndFuelCostTy() {
		return powerAndFuelCostTy;
	}

	public void setPowerAndFuelCostTy(Double powerAndFuelCostTy) {
		this.powerAndFuelCostTy = powerAndFuelCostTy;
	}

	public Double getPreOperativeExpeFy() {
		return preOperativeExpeFy;
	}

	public void setPreOperativeExpeFy(Double preOperativeExpeFy) {
		this.preOperativeExpeFy = preOperativeExpeFy;
	}

	public Double getPreOperativeExpeSy() {
		return preOperativeExpeSy;
	}

	public void setPreOperativeExpeSy(Double preOperativeExpeSy) {
		this.preOperativeExpeSy = preOperativeExpeSy;
	}

	public Double getPreOperativeExpeTy() {
		return preOperativeExpeTy;
	}

	public void setPreOperativeExpeTy(Double preOperativeExpeTy) {
		this.preOperativeExpeTy = preOperativeExpeTy;
	}

	public Double getProfitAfterTaxFy() {
		return profitAfterTaxFy;
	}

	public void setProfitAfterTaxFy(Double profitAfterTaxFy) {
		this.profitAfterTaxFy = profitAfterTaxFy;
	}

	public Double getProfitAfterTaxSy() {
		return profitAfterTaxSy;
	}

	public void setProfitAfterTaxSy(Double profitAfterTaxSy) {
		this.profitAfterTaxSy = profitAfterTaxSy;
	}

	public Double getProfitAfterTaxTy() {
		return profitAfterTaxTy;
	}

	public void setProfitAfterTaxTy(Double profitAfterTaxTy) {
		this.profitAfterTaxTy = profitAfterTaxTy;
	}

	public Double getProfitBeforeTaxFy() {
		return profitBeforeTaxFy;
	}

	public void setProfitBeforeTaxFy(Double profitBeforeTaxFy) {
		this.profitBeforeTaxFy = profitBeforeTaxFy;
	}

	public Double getProfitBeforeTaxSy() {
		return profitBeforeTaxSy;
	}

	public void setProfitBeforeTaxSy(Double profitBeforeTaxSy) {
		this.profitBeforeTaxSy = profitBeforeTaxSy;
	}

	public Double getProfitBeforeTaxTy() {
		return profitBeforeTaxTy;
	}

	public void setProfitBeforeTaxTy(Double profitBeforeTaxTy) {
		this.profitBeforeTaxTy = profitBeforeTaxTy;
	}

	public Double getProfitBeforeTaxationFy() {
		return profitBeforeTaxationFy;
	}

	public void setProfitBeforeTaxationFy(Double profitBeforeTaxationFy) {
		this.profitBeforeTaxationFy = profitBeforeTaxationFy;
	}

	public Double getProfitBeforeTaxationSy() {
		return profitBeforeTaxationSy;
	}

	public void setProfitBeforeTaxationSy(Double profitBeforeTaxationSy) {
		this.profitBeforeTaxationSy = profitBeforeTaxationSy;
	}

	public Double getProfitBeforeTaxationTy() {
		return profitBeforeTaxationTy;
	}

	public void setProfitBeforeTaxationTy(Double profitBeforeTaxationTy) {
		this.profitBeforeTaxationTy = profitBeforeTaxationTy;
	}

	public Double getProvisionForTaxFy() {
		return provisionForTaxFy;
	}

	public void setProvisionForTaxFy(Double provisionForTaxFy) {
		this.provisionForTaxFy = provisionForTaxFy;
	}

	public Double getProvisionForTaxSy() {
		return provisionForTaxSy;
	}

	public void setProvisionForTaxSy(Double provisionForTaxSy) {
		this.provisionForTaxSy = provisionForTaxSy;
	}

	public Double getProvisionForTaxTy() {
		return provisionForTaxTy;
	}

	public void setProvisionForTaxTy(Double provisionForTaxTy) {
		this.provisionForTaxTy = provisionForTaxTy;
	}

	public Double getQuickRatioXFy() {
		return quickRatioXFy;
	}

	public void setQuickRatioXFy(Double quickRatioXFy) {
		this.quickRatioXFy = quickRatioXFy;
	}

	public Double getQuickRatioXSy() {
		return quickRatioXSy;
	}

	public void setQuickRatioXSy(Double quickRatioXSy) {
		this.quickRatioXSy = quickRatioXSy;
	}

	public Double getQuickRatioXTy() {
		return quickRatioXTy;
	}

	public void setQuickRatioXTy(Double quickRatioXTy) {
		this.quickRatioXTy = quickRatioXTy;
	}

	public Double getRawMaterialConsumedFy() {
		return rawMaterialConsumedFy;
	}

	public void setRawMaterialConsumedFy(Double rawMaterialConsumedFy) {
		this.rawMaterialConsumedFy = rawMaterialConsumedFy;
	}

	public Double getRawMaterialConsumedSy() {
		return rawMaterialConsumedSy;
	}

	public void setRawMaterialConsumedSy(Double rawMaterialConsumedSy) {
		this.rawMaterialConsumedSy = rawMaterialConsumedSy;
	}

	public Double getRawMaterialConsumedTy() {
		return rawMaterialConsumedTy;
	}

	public void setRawMaterialConsumedTy(Double rawMaterialConsumedTy) {
		this.rawMaterialConsumedTy = rawMaterialConsumedTy;
	}

	public Double getRevalationReserveFy() {
		return revalationReserveFy;
	}

	public void setRevalationReserveFy(Double revalationReserveFy) {
		this.revalationReserveFy = revalationReserveFy;
	}

	public Double getRevalationReserveSy() {
		return revalationReserveSy;
	}

	public void setRevalationReserveSy(Double revalationReserveSy) {
		this.revalationReserveSy = revalationReserveSy;
	}

	public Double getRevalationReserveTy() {
		return revalationReserveTy;
	}

	public void setRevalationReserveTy(Double revalationReserveTy) {
		this.revalationReserveTy = revalationReserveTy;
	}

	public Double getRoceFy() {
		return roceFy;
	}

	public void setRoceFy(Double roceFy) {
		this.roceFy = roceFy;
	}

	public Double getRoceSy() {
		return roceSy;
	}

	public void setRoceSy(Double roceSy) {
		this.roceSy = roceSy;
	}

	public Double getSalesWorkingCapitalFy() {
		return salesWorkingCapitalFy;
	}

	public void setSalesWorkingCapitalFy(Double salesWorkingCapitalFy) {
		this.salesWorkingCapitalFy = salesWorkingCapitalFy;
	}

	public Double getSalesWorkingCapitalSy() {
		return salesWorkingCapitalSy;
	}

	public void setSalesWorkingCapitalSy(Double salesWorkingCapitalSy) {
		this.salesWorkingCapitalSy = salesWorkingCapitalSy;
	}

	public Double getSalesWorkingCapitalTy() {
		return salesWorkingCapitalTy;
	}

	public void setSalesWorkingCapitalTy(Double salesWorkingCapitalTy) {
		this.salesWorkingCapitalTy = salesWorkingCapitalTy;
	}

	public Double getSecuredLoansFy() {
		return securedLoansFy;
	}

	public void setSecuredLoansFy(Double securedLoansFy) {
		this.securedLoansFy = securedLoansFy;
	}

	public Double getSecuredLoansSy() {
		return securedLoansSy;
	}

	public void setSecuredLoansSy(Double securedLoansSy) {
		this.securedLoansSy = securedLoansSy;
	}

	public Double getSecuredLoansTy() {
		return securedLoansTy;
	}

	public void setSecuredLoansTy(Double securedLoansTy) {
		this.securedLoansTy = securedLoansTy;
	}

	public Double getSellingAndDistriExpeFy() {
		return sellingAndDistriExpeFy;
	}

	public void setSellingAndDistriExpeFy(Double sellingAndDistriExpeFy) {
		this.sellingAndDistriExpeFy = sellingAndDistriExpeFy;
	}

	public Double getSellingAndDistriExpeSy() {
		return sellingAndDistriExpeSy;
	}

	public void setSellingAndDistriExpeSy(Double sellingAndDistriExpeSy) {
		this.sellingAndDistriExpeSy = sellingAndDistriExpeSy;
	}

	public Double getSellingAndDistriExpeTy() {
		return sellingAndDistriExpeTy;
	}

	public void setSellingAndDistriExpeTy(Double sellingAndDistriExpeTy) {
		this.sellingAndDistriExpeTy = sellingAndDistriExpeTy;
	}

	public Double getShareCapitalFy() {
		return shareCapitalFy;
	}

	public void setShareCapitalFy(Double shareCapitalFy) {
		this.shareCapitalFy = shareCapitalFy;
	}

	public Double getShareCapitalSy() {
		return shareCapitalSy;
	}

	public void setShareCapitalSy(Double shareCapitalSy) {
		this.shareCapitalSy = shareCapitalSy;
	}

	public Double getShareCapitalTy() {
		return shareCapitalTy;
	}

	public void setShareCapitalTy(Double shareCapitalTy) {
		this.shareCapitalTy = shareCapitalTy;
	}

	public Double getShareFaceValue() {
		return shareFaceValue;
	}

	public void setShareFaceValue(Double shareFaceValue) {
		this.shareFaceValue = shareFaceValue;
	}

	public Double getShareHolderFundsFy() {
		return shareHolderFundsFy;
	}

	public void setShareHolderFundsFy(Double shareHolderFundsFy) {
		this.shareHolderFundsFy = shareHolderFundsFy;
	}

	public Double getShareHolderFundsSy() {
		return shareHolderFundsSy;
	}

	public void setShareHolderFundsSy(Double shareHolderFundsSy) {
		this.shareHolderFundsSy = shareHolderFundsSy;
	}

	public Double getShareHolderFundsTy() {
		return shareHolderFundsTy;
	}

	public void setShareHolderFundsTy(Double shareHolderFundsTy) {
		this.shareHolderFundsTy = shareHolderFundsTy;
	}

	public Double getShareWarrantOutstandingsFy() {
		return shareWarrantOutstandingsFy;
	}

	public void setShareWarrantOutstandingsFy(Double shareWarrantOutstandingsFy) {
		this.shareWarrantOutstandingsFy = shareWarrantOutstandingsFy;
	}

	public Double getShareWarrantOutstandingsSy() {
		return shareWarrantOutstandingsSy;
	}

	public void setShareWarrantOutstandingsSy(Double shareWarrantOutstandingsSy) {
		this.shareWarrantOutstandingsSy = shareWarrantOutstandingsSy;
	}

	public Double getShareWarrantOutstandingsTy() {
		return shareWarrantOutstandingsTy;
	}

	public void setShareWarrantOutstandingsTy(Double shareWarrantOutstandingsTy) {
		this.shareWarrantOutstandingsTy = shareWarrantOutstandingsTy;
	}

	public Double getShortTermLoansAdvancesFy() {
		return shortTermLoansAdvancesFy;
	}

	public void setShortTermLoansAdvancesFy(Double shortTermLoansAdvancesFy) {
		this.shortTermLoansAdvancesFy = shortTermLoansAdvancesFy;
	}

	public Double getShortTermLoansAdvancesSy() {
		return shortTermLoansAdvancesSy;
	}

	public void setShortTermLoansAdvancesSy(Double shortTermLoansAdvancesSy) {
		this.shortTermLoansAdvancesSy = shortTermLoansAdvancesSy;
	}

	public Double getShortTermLoansAdvancesTy() {
		return shortTermLoansAdvancesTy;
	}

	public void setShortTermLoansAdvancesTy(Double shortTermLoansAdvancesTy) {
		this.shortTermLoansAdvancesTy = shortTermLoansAdvancesTy;
	}

	public Double getShortTermProvisionFy() {
		return shortTermProvisionFy;
	}

	public void setShortTermProvisionFy(Double shortTermProvisionFy) {
		this.shortTermProvisionFy = shortTermProvisionFy;
	}

	public Double getShortTermProvisionSy() {
		return shortTermProvisionSy;
	}

	public void setShortTermProvisionSy(Double shortTermProvisionSy) {
		this.shortTermProvisionSy = shortTermProvisionSy;
	}

	public Double getShortTermProvisionTy() {
		return shortTermProvisionTy;
	}

	public void setShortTermProvisionTy(Double shortTermProvisionTy) {
		this.shortTermProvisionTy = shortTermProvisionTy;
	}

	public Double getSundryDebtorsFy() {
		return sundryDebtorsFy;
	}

	public void setSundryDebtorsFy(Double sundryDebtorsFy) {
		this.sundryDebtorsFy = sundryDebtorsFy;
	}

	public Double getSundryDebtorsSy() {
		return sundryDebtorsSy;
	}

	public void setSundryDebtorsSy(Double sundryDebtorsSy) {
		this.sundryDebtorsSy = sundryDebtorsSy;
	}

	public Double getSundryDebtorsTy() {
		return sundryDebtorsTy;
	}

	public void setSundryDebtorsTy(Double sundryDebtorsTy) {
		this.sundryDebtorsTy = sundryDebtorsTy;
	}

	public Double getTaxPaidFy() {
		return taxPaidFy;
	}

	public void setTaxPaidFy(Double taxPaidFy) {
		this.taxPaidFy = taxPaidFy;
	}

	public Double getTaxPaidSy() {
		return taxPaidSy;
	}

	public void setTaxPaidSy(Double taxPaidSy) {
		this.taxPaidSy = taxPaidSy;
	}

	public Double getTotalAssetFy() {
		return totalAssetFy;
	}

	public void setTotalAssetFy(Double totalAssetFy) {
		this.totalAssetFy = totalAssetFy;
	}

	public Double getTotalAssetSy() {
		return totalAssetSy;
	}

	public void setTotalAssetSy(Double totalAssetSy) {
		this.totalAssetSy = totalAssetSy;
	}

	public Double getTotalAssetTy() {
		return totalAssetTy;
	}

	public void setTotalAssetTy(Double totalAssetTy) {
		this.totalAssetTy = totalAssetTy;
	}

	public Double getTotalCurruntAssetFy() {
		return totalCurruntAssetFy;
	}

	public void setTotalCurruntAssetFy(Double totalCurruntAssetFy) {
		this.totalCurruntAssetFy = totalCurruntAssetFy;
	}

	public Double getTotalCurruntAssetSy() {
		return totalCurruntAssetSy;
	}

	public void setTotalCurruntAssetSy(Double totalCurruntAssetSy) {
		this.totalCurruntAssetSy = totalCurruntAssetSy;
	}

	public Double getTotalCurruntAssetTy() {
		return totalCurruntAssetTy;
	}

	public void setTotalCurruntAssetTy(Double totalCurruntAssetTy) {
		this.totalCurruntAssetTy = totalCurruntAssetTy;
	}

	public Double getTotalCurruntLiablitiesFy() {
		return totalCurruntLiablitiesFy;
	}

	public void setTotalCurruntLiablitiesFy(Double totalCurruntLiablitiesFy) {
		this.totalCurruntLiablitiesFy = totalCurruntLiablitiesFy;
	}

	public Double getTotalCurruntLiablitiesSy() {
		return totalCurruntLiablitiesSy;
	}

	public void setTotalCurruntLiablitiesSy(Double totalCurruntLiablitiesSy) {
		this.totalCurruntLiablitiesSy = totalCurruntLiablitiesSy;
	}

	public Double getTotalCurruntLiablitiesTy() {
		return totalCurruntLiablitiesTy;
	}

	public void setTotalCurruntLiablitiesTy(Double totalCurruntLiablitiesTy) {
		this.totalCurruntLiablitiesTy = totalCurruntLiablitiesTy;
	}

	public Double getTotalExpenditureFy() {
		return totalExpenditureFy;
	}

	public void setTotalExpenditureFy(Double totalExpenditureFy) {
		this.totalExpenditureFy = totalExpenditureFy;
	}

	public Double getTotalExpenditureSy() {
		return totalExpenditureSy;
	}

	public void setTotalExpenditureSy(Double totalExpenditureSy) {
		this.totalExpenditureSy = totalExpenditureSy;
	}

	public Double getTotalExpenditureTy() {
		return totalExpenditureTy;
	}

	public void setTotalExpenditureTy(Double totalExpenditureTy) {
		this.totalExpenditureTy = totalExpenditureTy;
	}

	public Double getTotalLiablitiesFy() {
		return totalLiablitiesFy;
	}

	public void setTotalLiablitiesFy(Double totalLiablitiesFy) {
		this.totalLiablitiesFy = totalLiablitiesFy;
	}

	public Double getTotalLiablitiesSy() {
		return totalLiablitiesSy;
	}

	public void setTotalLiablitiesSy(Double totalLiablitiesSy) {
		this.totalLiablitiesSy = totalLiablitiesSy;
	}

	public Double getTotalLiablitiesTy() {
		return totalLiablitiesTy;
	}

	public void setTotalLiablitiesTy(Double totalLiablitiesTy) {
		this.totalLiablitiesTy = totalLiablitiesTy;
	}

	public Double getTotalNonCurruntAssetFy() {
		return totalNonCurruntAssetFy;
	}

	public void setTotalNonCurruntAssetFy(Double totalNonCurruntAssetFy) {
		this.totalNonCurruntAssetFy = totalNonCurruntAssetFy;
	}

	public Double getTotalNonCurruntAssetSy() {
		return totalNonCurruntAssetSy;
	}

	public void setTotalNonCurruntAssetSy(Double totalNonCurruntAssetSy) {
		this.totalNonCurruntAssetSy = totalNonCurruntAssetSy;
	}

	public Double getTotalNonCurruntAssetTy() {
		return totalNonCurruntAssetTy;
	}

	public void setTotalNonCurruntAssetTy(Double totalNonCurruntAssetTy) {
		this.totalNonCurruntAssetTy = totalNonCurruntAssetTy;
	}

	public Double getTotalNonCurruntLiablitiesFy() {
		return totalNonCurruntLiablitiesFy;
	}

	public void setTotalNonCurruntLiablitiesFy(Double totalNonCurruntLiablitiesFy) {
		this.totalNonCurruntLiablitiesFy = totalNonCurruntLiablitiesFy;
	}

	public Double getTotalNonCurruntLiablitiesSy() {
		return totalNonCurruntLiablitiesSy;
	}

	public void setTotalNonCurruntLiablitiesSy(Double totalNonCurruntLiablitiesSy) {
		this.totalNonCurruntLiablitiesSy = totalNonCurruntLiablitiesSy;
	}

	public Double getTotalNonCurruntLiablitiesTy() {
		return totalNonCurruntLiablitiesTy;
	}

	public void setTotalNonCurruntLiablitiesTy(Double totalNonCurruntLiablitiesTy) {
		this.totalNonCurruntLiablitiesTy = totalNonCurruntLiablitiesTy;
	}

	public Double getTradePayablesFy() {
		return tradePayablesFy;
	}

	public void setTradePayablesFy(Double tradePayablesFy) {
		this.tradePayablesFy = tradePayablesFy;
	}

	public Double getTradePayablesSy() {
		return tradePayablesSy;
	}

	public void setTradePayablesSy(Double tradePayablesSy) {
		this.tradePayablesSy = tradePayablesSy;
	}

	public Double getTradePayablesTy() {
		return tradePayablesTy;
	}

	public void setTradePayablesTy(Double tradePayablesTy) {
		this.tradePayablesTy = tradePayablesTy;
	}

	public Double getUnsecuredLoansOthersFy() {
		return unsecuredLoansOthersFy;
	}

	public void setUnsecuredLoansOthersFy(Double unsecuredLoansOthersFy) {
		this.unsecuredLoansOthersFy = unsecuredLoansOthersFy;
	}

	public Double getUnsecuredLoansOthersSy() {
		return unsecuredLoansOthersSy;
	}

	public void setUnsecuredLoansOthersSy(Double unsecuredLoansOthersSy) {
		this.unsecuredLoansOthersSy = unsecuredLoansOthersSy;
	}

	public Double getUnsecuredLoansOthersTy() {
		return unsecuredLoansOthersTy;
	}

	public void setUnsecuredLoansOthersTy(Double unsecuredLoansOthersTy) {
		this.unsecuredLoansOthersTy = unsecuredLoansOthersTy;
	}

	public Double getUnsecuredLoansPromotersFy() {
		return unsecuredLoansPromotersFy;
	}

	public void setUnsecuredLoansPromotersFy(Double unsecuredLoansPromotersFy) {
		this.unsecuredLoansPromotersFy = unsecuredLoansPromotersFy;
	}

	public Double getUnsecuredLoansPromotersSy() {
		return unsecuredLoansPromotersSy;
	}

	public void setUnsecuredLoansPromotersSy(Double unsecuredLoansPromotersSy) {
		this.unsecuredLoansPromotersSy = unsecuredLoansPromotersSy;
	}

	public Double getUnsecuredLoansPromotersTy() {
		return unsecuredLoansPromotersTy;
	}

	public void setUnsecuredLoansPromotersTy(Double unsecuredLoansPromotersTy) {
		this.unsecuredLoansPromotersTy = unsecuredLoansPromotersTy;
	}

	public String getRatioAnalysisFyFullDate() {
		return ratioAnalysisFyFullDate;
	}

	public void setRatioAnalysisFyFullDate(String ratioAnalysisFyFullDate) {
		this.ratioAnalysisFyFullDate = ratioAnalysisFyFullDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the otherIncomeNeedTocCheckAssetFy
	 */
	public Double getOtherIncomeNeedTocCheckAssetFy() {
		return OtherIncomeNeedTocCheckAssetFy;
	}

	/**
	 * @param otherIncomeNeedTocCheckAssetFy the otherIncomeNeedTocCheckAssetFy to set
	 */
	public void setOtherIncomeNeedTocCheckAssetFy(Double otherIncomeNeedTocCheckAssetFy) {
		OtherIncomeNeedTocCheckAssetFy = otherIncomeNeedTocCheckAssetFy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckAssetSy
	 */
	public Double getOtherIncomeNeedTocCheckAssetSy() {
		return OtherIncomeNeedTocCheckAssetSy;
	}

	/**
	 * @param otherIncomeNeedTocCheckAssetSy the otherIncomeNeedTocCheckAssetSy to set
	 */
	public void setOtherIncomeNeedTocCheckAssetSy(Double otherIncomeNeedTocCheckAssetSy) {
		OtherIncomeNeedTocCheckAssetSy = otherIncomeNeedTocCheckAssetSy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckAssetTy
	 */
	public Double getOtherIncomeNeedTocCheckAssetTy() {
		return OtherIncomeNeedTocCheckAssetTy;
	}

	/**
	 * @param otherIncomeNeedTocCheckAssetTy the otherIncomeNeedTocCheckAssetTy to set
	 */
	public void setOtherIncomeNeedTocCheckAssetTy(Double otherIncomeNeedTocCheckAssetTy) {
		OtherIncomeNeedTocCheckAssetTy = otherIncomeNeedTocCheckAssetTy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckOpFy
	 */
	public Double getOtherIncomeNeedTocCheckOpFy() {
		return OtherIncomeNeedTocCheckOpFy;
	}

	/**
	 * @param otherIncomeNeedTocCheckOpFy the otherIncomeNeedTocCheckOpFy to set
	 */
	public void setOtherIncomeNeedTocCheckOpFy(Double otherIncomeNeedTocCheckOpFy) {
		OtherIncomeNeedTocCheckOpFy = otherIncomeNeedTocCheckOpFy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckOpSy
	 */
	public Double getOtherIncomeNeedTocCheckOpSy() {
		return OtherIncomeNeedTocCheckOpSy;
	}

	/**
	 * @param otherIncomeNeedTocCheckOpSy the otherIncomeNeedTocCheckOpSy to set
	 */
	public void setOtherIncomeNeedTocCheckOpSy(Double otherIncomeNeedTocCheckOpSy) {
		OtherIncomeNeedTocCheckOpSy = otherIncomeNeedTocCheckOpSy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckOpTy
	 */
	public Double getOtherIncomeNeedTocCheckOpTy() {
		return OtherIncomeNeedTocCheckOpTy;
	}

	/**
	 * @param otherIncomeNeedTocCheckOpTy the otherIncomeNeedTocCheckOpTy to set
	 */
	public void setOtherIncomeNeedTocCheckOpTy(Double otherIncomeNeedTocCheckOpTy) {
		OtherIncomeNeedTocCheckOpTy = otherIncomeNeedTocCheckOpTy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckLiaFy
	 */
	public Double getOtherIncomeNeedTocCheckLiaFy() {
		return OtherIncomeNeedTocCheckLiaFy;
	}

	/**
	 * @param otherIncomeNeedTocCheckLiaFy the otherIncomeNeedTocCheckLiaFy to set
	 */
	public void setOtherIncomeNeedTocCheckLiaFy(Double otherIncomeNeedTocCheckLiaFy) {
		OtherIncomeNeedTocCheckLiaFy = otherIncomeNeedTocCheckLiaFy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckLiaSy
	 */
	public Double getOtherIncomeNeedTocCheckLiaSy() {
		return OtherIncomeNeedTocCheckLiaSy;
	}

	/**
	 * @param otherIncomeNeedTocCheckLiaSy the otherIncomeNeedTocCheckLiaSy to set
	 */
	public void setOtherIncomeNeedTocCheckLiaSy(Double otherIncomeNeedTocCheckLiaSy) {
		OtherIncomeNeedTocCheckLiaSy = otherIncomeNeedTocCheckLiaSy;
	}

	/**
	 * @return the otherIncomeNeedTocCheckLiaTy
	 */
	public Double getOtherIncomeNeedTocCheckLiaTy() {
		return OtherIncomeNeedTocCheckLiaTy;
	}

	/**
	 * @param otherIncomeNeedTocCheckLiaTy the otherIncomeNeedTocCheckLiaTy to set
	 */
	public void setOtherIncomeNeedTocCheckLiaTy(Double otherIncomeNeedTocCheckLiaTy) {
		OtherIncomeNeedTocCheckLiaTy = otherIncomeNeedTocCheckLiaTy;
	}

	public List<Map<String, Object>> getYearSalesPurchasList() {
		return yearSalesPurchasList;
	}

	public void setYearSalesPurchasList(
			List<Map<String, Object>> yearSalesPurchasList) {
		this.yearSalesPurchasList = yearSalesPurchasList;
	}




}