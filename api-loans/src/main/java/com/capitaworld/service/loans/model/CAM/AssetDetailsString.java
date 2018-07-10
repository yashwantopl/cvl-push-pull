package com.capitaworld.service.loans.model.CAM;

import java.io.Serializable;

public class AssetDetailsString implements Serializable{
	
	private static final long serialVersionUID = 1L;
	 
	private Long id;
	private String advancePaymentTaxes;
	private String advanceToSupplierRawMaterials;
	private String advanceToSuppliersCapitalGoods;
	private String anyOther;
	private String badOrDoubtfulExpenses;
	private String cashAndBankBalance;
	private String currentRatio;
	private String deferredReceviables;
	private String deferredReceviablesOthers;
	private String deferredTaxAssets;
	private String depreciationToDate;
	private String exportReceivables;
	private String financialYearlyStatement;
	private String finishedGoods;
	private String fixedDepositsWithBanks;
	private String goodWill;
	private String governmentAndOtherTrustee;
	private String grossBlock;
	private String instalmentsDeferred;
	private String intangibleAssets;
	private String inventory;
	private String investments;
	private String investmentsInSubsidiary;
	private String investmentsOrBookDebtsString;
	private String netBlock;
	private String netWorkingCapital;
	private String nonConsumableStoreAndSpares;
	private String otherConsumableSpares;
	private String otherConsumableSparesImported;
	private String otherConsumableSparesIndegenous;
	private String otherCurrentAssets;
	private String otherNonCurrentAssets;
	private String others;
	private String patents;
	private String prelimExpenses;
	private String rawMaterial;
	private String rawMaterialImported;
	private String rawMaterialIndegenous;
	private String receivableOtherThanDefferred;
	private String stockInProcess;
	private String tangibleNetWorth;
	private String totalAssets;
	private String totalCurrentAssets;
	private String totalIntangibleAssets;
	private String totalOtherNonCurrentAssets;
	private String totalOutSideLiability;
	private String totalTermLiability;
	private String year;
	private String landBuilding;
	private String plantMachines;
	private String impairmentAsset;
	private String othersPreOperativeExpensesPending;
	private String othersAssetsInTransit;
	private String othersOther;
	private String totalOtherNcaPatent;
	private String totalOtherNcaGoodwill;
	private String totalOtherNcaPrelimnaryExpenses;
	private String totalOtherNcaBadExpenses;
	private String totalOtherNcaOther;
	private String otherNcaOtherCapitalWorkInprogress;
	private String grossBlock1;
	private String grossBlock2;
	private String grossBlock3;
	private String grossBlock4;
	private String otherInvestmentsTotal;
	private String otherNonCurrentAssestsTotal;	
	private String sundryDebtorsTotal;
	private String otherCurrentAssetsTotal;
	private String shortTermLoansAndAdvancesTotal;
	private String otherIncomeNeedTocCheckAsset;
	
	
	public AssetDetailsString() {
        this.advancePaymentTaxes = "0.0";
        this.advanceToSupplierRawMaterials = "0.0";
        this.advanceToSuppliersCapitalGoods = "0.0";
        this.anyOther = "0.0";
        this.badOrDoubtfulExpenses = "0.0";
        this.cashAndBankBalance = "0.0";
        this.currentRatio = "0.0";
        this.deferredReceviables = "0.0";
        this.deferredReceviablesOthers = "0.0";
        this.deferredTaxAssets = "0.0";
        this.depreciationToDate = "0.0";
        this.exportReceivables = "0.0";
        this.financialYearlyStatement = "0.0";
        this.finishedGoods = "0.0";
        this.fixedDepositsWithBanks = "0.0";
        this.goodWill = "0.0";
        this.governmentAndOtherTrustee = "0.0";
        this.grossBlock = "0.0";
        this.instalmentsDeferred = "0.0";
        this.intangibleAssets = "0.0";
        this.inventory = "0.0";
        this.investments = "0.0";
        this.investmentsInSubsidiary = "0.0";
        this.investmentsOrBookDebtsString = "0.0";
        this.netBlock = "0.0";
        this.netWorkingCapital = "0.0";
        this.nonConsumableStoreAndSpares = "0.0";
        this.otherConsumableSpares = "0.0";
        this.otherConsumableSparesImported = "0.0";
        this.otherConsumableSparesIndegenous = "0.0";
        this.otherCurrentAssets = "0.0";
        this.otherNonCurrentAssets = "0.0";
        this.others = "0.0";
        this.patents = "0.0";
        this.prelimExpenses = "0.0";
        this.rawMaterial = "0.0";
        this.rawMaterialImported = "0.0";
        this.rawMaterialIndegenous = "0.0";
        this.receivableOtherThanDefferred = "0.0";
        this.stockInProcess = "0.0";
        this.tangibleNetWorth = "0.0";
        this.totalAssets = "0.0";
        this.totalCurrentAssets = "0.0";
        this.totalIntangibleAssets = "0.0";
        this.totalOtherNonCurrentAssets = "0.0";
        this.totalOutSideLiability = "0.0";
        this.totalTermLiability = "0.0";
        this.year = "0.0";
        this.landBuilding = "0.0";
        this.plantMachines = "0.0";
        this.impairmentAsset = "0.0";
        this.othersPreOperativeExpensesPending = "0.0";
        this.othersAssetsInTransit = "0.0";
        this.othersOther = "0.0";
        this.totalOtherNcaPatent = "0.0";
        this.totalOtherNcaGoodwill = "0.0";
        this.totalOtherNcaPrelimnaryExpenses = "0.0";
        this.totalOtherNcaBadExpenses = "0.0";
        this.totalOtherNcaOther = "0.0";
        this.otherNcaOtherCapitalWorkInprogress = "0.0";
        this.grossBlock1 = "0.0";
        this.grossBlock2 = "0.0";
        this.grossBlock3 = "0.0";
        this.grossBlock4 = "0.0";
        this.otherInvestmentsTotal = "0.0";
        this.otherNonCurrentAssestsTotal = "0.0";
        this.sundryDebtorsTotal = "0.0";
        this.otherCurrentAssetsTotal = "0.0";
        this.shortTermLoansAndAdvancesTotal = "0.0";
        this.otherIncomeNeedTocCheckAsset = "0.0";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdvancePaymentTaxes() {
		return advancePaymentTaxes;
	}
	public void setAdvancePaymentTaxes(String advancePaymentTaxes) {
		this.advancePaymentTaxes = advancePaymentTaxes;
	}
	public String getAdvanceToSupplierRawMaterials() {
		return advanceToSupplierRawMaterials;
	}
	public void setAdvanceToSupplierRawMaterials(String advanceToSupplierRawMaterials) {
		this.advanceToSupplierRawMaterials = advanceToSupplierRawMaterials;
	}
	public String getAdvanceToSuppliersCapitalGoods() {
		return advanceToSuppliersCapitalGoods;
	}
	public void setAdvanceToSuppliersCapitalGoods(String advanceToSuppliersCapitalGoods) {
		this.advanceToSuppliersCapitalGoods = advanceToSuppliersCapitalGoods;
	}
	public String getAnyOther() {
		return anyOther;
	}
	public void setAnyOther(String anyOther) {
		this.anyOther = anyOther;
	}
	public String getBadOrDoubtfulExpenses() {
		return badOrDoubtfulExpenses;
	}
	public void setBadOrDoubtfulExpenses(String badOrDoubtfulExpenses) {
		this.badOrDoubtfulExpenses = badOrDoubtfulExpenses;
	}
	public String getCashAndBankBalance() {
		return cashAndBankBalance;
	}
	public void setCashAndBankBalance(String cashAndBankBalance) {
		this.cashAndBankBalance = cashAndBankBalance;
	}
	public String getCurrentRatio() {
		return currentRatio;
	}
	public void setCurrentRatio(String currentRatio) {
		this.currentRatio = currentRatio;
	}
	public String getDeferredReceviables() {
		return deferredReceviables;
	}
	public void setDeferredReceviables(String deferredReceviables) {
		this.deferredReceviables = deferredReceviables;
	}
	public String getDeferredReceviablesOthers() {
		return deferredReceviablesOthers;
	}
	public void setDeferredReceviablesOthers(String deferredReceviablesOthers) {
		this.deferredReceviablesOthers = deferredReceviablesOthers;
	}
	public String getDeferredTaxAssets() {
		return deferredTaxAssets;
	}
	public void setDeferredTaxAssets(String deferredTaxAssets) {
		this.deferredTaxAssets = deferredTaxAssets;
	}
	public String getDepreciationToDate() {
		return depreciationToDate;
	}
	public void setDepreciationToDate(String depreciationToDate) {
		this.depreciationToDate = depreciationToDate;
	}
	public String getExportReceivables() {
		return exportReceivables;
	}
	public void setExportReceivables(String exportReceivables) {
		this.exportReceivables = exportReceivables;
	}
	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}
	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
	}
	public String getFinishedGoods() {
		return finishedGoods;
	}
	public void setFinishedGoods(String finishedGoods) {
		this.finishedGoods = finishedGoods;
	}
	public String getFixedDepositsWithBanks() {
		return fixedDepositsWithBanks;
	}
	public void setFixedDepositsWithBanks(String fixedDepositsWithBanks) {
		this.fixedDepositsWithBanks = fixedDepositsWithBanks;
	}
	public String getGoodWill() {
		return goodWill;
	}
	public void setGoodWill(String goodWill) {
		this.goodWill = goodWill;
	}
	public String getGovernmentAndOtherTrustee() {
		return governmentAndOtherTrustee;
	}
	public void setGovernmentAndOtherTrustee(String governmentAndOtherTrustee) {
		this.governmentAndOtherTrustee = governmentAndOtherTrustee;
	}
	public String getGrossBlock() {
		return grossBlock;
	}
	public void setGrossBlock(String grossBlock) {
		this.grossBlock = grossBlock;
	}
	public String getInstalmentsDeferred() {
		return instalmentsDeferred;
	}
	public void setInstalmentsDeferred(String instalmentsDeferred) {
		this.instalmentsDeferred = instalmentsDeferred;
	}
	public String getIntangibleAssets() {
		return intangibleAssets;
	}
	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	public String getInvestments() {
		return investments;
	}
	public void setInvestments(String investments) {
		this.investments = investments;
	}
	public String getInvestmentsInSubsidiary() {
		return investmentsInSubsidiary;
	}
	public void setInvestmentsInSubsidiary(String investmentsInSubsidiary) {
		this.investmentsInSubsidiary = investmentsInSubsidiary;
	}
	public String getInvestmentsOrBookDebtsString() {
		return investmentsOrBookDebtsString;
	}
	public void setInvestmentsOrBookDebtsString(String investmentsOrBookDebtsString) {
		this.investmentsOrBookDebtsString = investmentsOrBookDebtsString;
	}
	public String getNetBlock() {
		return netBlock;
	}
	public void setNetBlock(String netBlock) {
		this.netBlock = netBlock;
	}
	public String getNetWorkingCapital() {
		return netWorkingCapital;
	}
	public void setNetWorkingCapital(String netWorkingCapital) {
		this.netWorkingCapital = netWorkingCapital;
	}
	public String getNonConsumableStoreAndSpares() {
		return nonConsumableStoreAndSpares;
	}
	public void setNonConsumableStoreAndSpares(String nonConsumableStoreAndSpares) {
		this.nonConsumableStoreAndSpares = nonConsumableStoreAndSpares;
	}
	public String getOtherConsumableSpares() {
		return otherConsumableSpares;
	}
	public void setOtherConsumableSpares(String otherConsumableSpares) {
		this.otherConsumableSpares = otherConsumableSpares;
	}
	public String getOtherConsumableSparesImported() {
		return otherConsumableSparesImported;
	}
	public void setOtherConsumableSparesImported(String otherConsumableSparesImported) {
		this.otherConsumableSparesImported = otherConsumableSparesImported;
	}
	public String getOtherConsumableSparesIndegenous() {
		return otherConsumableSparesIndegenous;
	}
	public void setOtherConsumableSparesIndegenous(String otherConsumableSparesIndegenous) {
		this.otherConsumableSparesIndegenous = otherConsumableSparesIndegenous;
	}
	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}
	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}
	public String getOtherNonCurrentAssets() {
		return otherNonCurrentAssets;
	}
	public void setOtherNonCurrentAssets(String otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getPatents() {
		return patents;
	}
	public void setPatents(String patents) {
		this.patents = patents;
	}
	public String getPrelimExpenses() {
		return prelimExpenses;
	}
	public void setPrelimExpenses(String prelimExpenses) {
		this.prelimExpenses = prelimExpenses;
	}
	public String getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(String rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public String getRawMaterialImported() {
		return rawMaterialImported;
	}
	public void setRawMaterialImported(String rawMaterialImported) {
		this.rawMaterialImported = rawMaterialImported;
	}
	public String getRawMaterialIndegenous() {
		return rawMaterialIndegenous;
	}
	public void setRawMaterialIndegenous(String rawMaterialIndegenous) {
		this.rawMaterialIndegenous = rawMaterialIndegenous;
	}
	public String getReceivableOtherThanDefferred() {
		return receivableOtherThanDefferred;
	}
	public void setReceivableOtherThanDefferred(String receivableOtherThanDefferred) {
		this.receivableOtherThanDefferred = receivableOtherThanDefferred;
	}
	public String getStockInProcess() {
		return stockInProcess;
	}
	public void setStockInProcess(String stockInProcess) {
		this.stockInProcess = stockInProcess;
	}
	public String getTangibleNetWorth() {
		return tangibleNetWorth;
	}
	public void setTangibleNetWorth(String tangibleNetWorth) {
		this.tangibleNetWorth = tangibleNetWorth;
	}
	public String getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}
	public String getTotalCurrentAssets() {
		return totalCurrentAssets;
	}
	public void setTotalCurrentAssets(String totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}
	public String getTotalIntangibleAssets() {
		return totalIntangibleAssets;
	}
	public void setTotalIntangibleAssets(String totalIntangibleAssets) {
		this.totalIntangibleAssets = totalIntangibleAssets;
	}
	public String getTotalOtherNonCurrentAssets() {
		return totalOtherNonCurrentAssets;
	}
	public void setTotalOtherNonCurrentAssets(String totalOtherNonCurrentAssets) {
		this.totalOtherNonCurrentAssets = totalOtherNonCurrentAssets;
	}
	public String getTotalOutSideLiability() {
		return totalOutSideLiability;
	}
	public void setTotalOutSideLiability(String totalOutSideLiability) {
		this.totalOutSideLiability = totalOutSideLiability;
	}
	public String getTotalTermLiability() {
		return totalTermLiability;
	}
	public void setTotalTermLiability(String totalTermLiability) {
		this.totalTermLiability = totalTermLiability;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLandBuilding() {
		return landBuilding;
	}
	public void setLandBuilding(String landBuilding) {
		this.landBuilding = landBuilding;
	}
	public String getPlantMachines() {
		return plantMachines;
	}
	public void setPlantMachines(String plantMachines) {
		this.plantMachines = plantMachines;
	}
	public String getImpairmentAsset() {
		return impairmentAsset;
	}
	public void setImpairmentAsset(String impairmentAsset) {
		this.impairmentAsset = impairmentAsset;
	}
	public String getOthersPreOperativeExpensesPending() {
		return othersPreOperativeExpensesPending;
	}
	public void setOthersPreOperativeExpensesPending(String othersPreOperativeExpensesPending) {
		this.othersPreOperativeExpensesPending = othersPreOperativeExpensesPending;
	}
	public String getOthersAssetsInTransit() {
		return othersAssetsInTransit;
	}
	public void setOthersAssetsInTransit(String othersAssetsInTransit) {
		this.othersAssetsInTransit = othersAssetsInTransit;
	}
	public String getOthersOther() {
		return othersOther;
	}
	public void setOthersOther(String othersOther) {
		this.othersOther = othersOther;
	}
	public String getTotalOtherNcaPatent() {
		return totalOtherNcaPatent;
	}
	public void setTotalOtherNcaPatent(String totalOtherNcaPatent) {
		this.totalOtherNcaPatent = totalOtherNcaPatent;
	}
	public String getTotalOtherNcaGoodwill() {
		return totalOtherNcaGoodwill;
	}
	public void setTotalOtherNcaGoodwill(String totalOtherNcaGoodwill) {
		this.totalOtherNcaGoodwill = totalOtherNcaGoodwill;
	}
	public String getTotalOtherNcaPrelimnaryExpenses() {
		return totalOtherNcaPrelimnaryExpenses;
	}
	public void setTotalOtherNcaPrelimnaryExpenses(String totalOtherNcaPrelimnaryExpenses) {
		this.totalOtherNcaPrelimnaryExpenses = totalOtherNcaPrelimnaryExpenses;
	}
	public String getTotalOtherNcaBadExpenses() {
		return totalOtherNcaBadExpenses;
	}
	public void setTotalOtherNcaBadExpenses(String totalOtherNcaBadExpenses) {
		this.totalOtherNcaBadExpenses = totalOtherNcaBadExpenses;
	}
	public String getTotalOtherNcaOther() {
		return totalOtherNcaOther;
	}
	public void setTotalOtherNcaOther(String totalOtherNcaOther) {
		this.totalOtherNcaOther = totalOtherNcaOther;
	}
	public String getOtherNcaOtherCapitalWorkInprogress() {
		return otherNcaOtherCapitalWorkInprogress;
	}
	public void setOtherNcaOtherCapitalWorkInprogress(String otherNcaOtherCapitalWorkInprogress) {
		this.otherNcaOtherCapitalWorkInprogress = otherNcaOtherCapitalWorkInprogress;
	}
	public String getGrossBlock1() {
		return grossBlock1;
	}
	public void setGrossBlock1(String grossBlock1) {
		this.grossBlock1 = grossBlock1;
	}
	public String getGrossBlock2() {
		return grossBlock2;
	}
	public void setGrossBlock2(String grossBlock2) {
		this.grossBlock2 = grossBlock2;
	}
	public String getGrossBlock3() {
		return grossBlock3;
	}
	public void setGrossBlock3(String grossBlock3) {
		this.grossBlock3 = grossBlock3;
	}
	public String getGrossBlock4() {
		return grossBlock4;
	}
	public void setGrossBlock4(String grossBlock4) {
		this.grossBlock4 = grossBlock4;
	}
	public String getOtherInvestmentsTotal() {
		return otherInvestmentsTotal;
	}
	public void setOtherInvestmentsTotal(String otherInvestmentsTotal) {
		this.otherInvestmentsTotal = otherInvestmentsTotal;
	}
	public String getOtherNonCurrentAssestsTotal() {
		return otherNonCurrentAssestsTotal;
	}
	public void setOtherNonCurrentAssestsTotal(String otherNonCurrentAssestsTotal) {
		this.otherNonCurrentAssestsTotal = otherNonCurrentAssestsTotal;
	}
	public String getSundryDebtorsTotal() {
		return sundryDebtorsTotal;
	}
	public void setSundryDebtorsTotal(String sundryDebtorsTotal) {
		this.sundryDebtorsTotal = sundryDebtorsTotal;
	}
	public String getOtherCurrentAssetsTotal() {
		return otherCurrentAssetsTotal;
	}
	public void setOtherCurrentAssetsTotal(String otherCurrentAssetsTotal) {
		this.otherCurrentAssetsTotal = otherCurrentAssetsTotal;
	}
	public String getShortTermLoansAndAdvancesTotal() {
		return shortTermLoansAndAdvancesTotal;
	}
	public void setShortTermLoansAndAdvancesTotal(String shortTermLoansAndAdvancesTotal) {
		this.shortTermLoansAndAdvancesTotal = shortTermLoansAndAdvancesTotal;
	}
	public String getOtherIncomeNeedTocCheckAsset() {
		return otherIncomeNeedTocCheckAsset;
	}
	public void setOtherIncomeNeedTocCheckAsset(String otherIncomeNeedTocCheckAsset) {
		this.otherIncomeNeedTocCheckAsset = otherIncomeNeedTocCheckAsset;
	}
	
	
	
	
	
	

}
