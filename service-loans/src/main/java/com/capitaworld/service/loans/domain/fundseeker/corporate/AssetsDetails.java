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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


@Entity
@Table(name="fs_corporate_cma_assets_details")
public class AssetsDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="advance_payment_taxes")
	private Double advancePaymentTaxes;

	@Column(name="advance_to_supplier_raw_materials")
	private Double advanceToSupplierRawMaterials;

	@Column(name="advance_to_suppliers_capital_goods")
	private Double advanceToSuppliersCapitalGoods;

	@Column(name="any_other")
	private Double anyOther;

	@Column(name="bad_or_doubtful_expenses")
	private Double badOrDoubtfulExpenses;

	@Column(name="cash_and_bank_balance")
	private Double cashAndBankBalance;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="current_ratio")
	private Double currentRatio;

	@Column(name="deferred_receviables")
	private Double deferredReceviables;

	@Column(name="deferred_receviables_others")
	private Double deferredReceviablesOthers;

	@Column(name="deferred_tax_assets")
	private Double deferredTaxAssets;

	@Column(name="depreciation_to_date")
	private Double depreciationToDate;

	@Column(name="export_receivables")
	private Double exportReceivables;
	
	@Column(name="financial_yearly_statement")
	private String financialYearlyStatement;

	@Column(name="finished_goods")
	private Double finishedGoods;

	@Column(name="fixed_deposits_with_banks")
	private Double fixedDepositsWithBanks;

	@Column(name="good_will")
	private Double goodWill;

	@Column(name="government_and_other_trustee")
	private Double governmentAndOtherTrustee;

	@Column(name="gross_block")
	private Double grossBlock;

	@Column(name="instalments_deferred")
	private Double instalmentsDeferred;

	@Column(name="intangible_assets")
	private Double intangibleAssets;

	private Double inventory;

	private Double investments;

	@Column(name="investments_in_subsidiary")
	private Double investmentsInSubsidiary;

	@Column(name="investments_or_book_debts")
	private Double investmentsOrBookDebts;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_block")
	private Double netBlock;

	@Column(name="net_working_capital")
	private Double netWorkingCapital;

	@Column(name="non_consumable_store_and_spares")
	private Double nonConsumableStoreAndSpares;

	@Column(name="other_consumable_spares")
	private Double otherConsumableSpares;

	@Column(name="other_consumable_spares_imported")
	private Double otherConsumableSparesImported;

	@Column(name="other_consumable_spares_indegenous")
	private Double otherConsumableSparesIndegenous;

	@Column(name="other_current_assets")
	private Double otherCurrentAssets;

	@Column(name="other_non_current_assets")
	private Double otherNonCurrentAssets;

	private Double others;

	private Double patents;

	@Column(name="prelim_expenses")
	private Double prelimExpenses;

	@Column(name="raw_material")
	private Double rawMaterial;

	@Column(name="raw_material_imported")
	private Double rawMaterialImported;

	@Column(name="raw_material_indegenous")
	private Double rawMaterialIndegenous;

	@Column(name="receivable_other_than_defferred")
	private Double receivableOtherThanDefferred;

	@Column(name="stock_in_process")
	private Double stockInProcess;

	@Column(name="tangible_net_worth")
	private Double tangibleNetWorth;

	@Column(name="total_assets")
	private Double totalAssets;

	@Column(name="total_current_assets")
	private Double totalCurrentAssets;

	@Column(name="total_intangible_assets")
	private Double totalIntangibleAssets;

	@Column(name="total_other_non_current_assets")
	private Double totalOtherNonCurrentAssets;

	@Column(name="total_out_side_liability")
	private Double totalOutSideLiability;

	@Column(name="total_term_liability")
	private Double totalTermLiability;

	private String year;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;
	
	@Column(name="land_building")
	private Double landBuilding;
	
	@Column(name="plant_machines")
	private Double plantMachines;
	
	
	@Column(name="impairment_asset")
	private Double impairmentAsset;
	
	@Column(name="others_pre_operative_expenses_pending")
	private Double othersPreOperativeExpensesPending;
	
	@Column(name="others_assets_in_transit")
	private Double othersAssetsInTransit;
	
	
	@Column(name="others_other")
	private Double othersOther;
	

	@Column(name="total_other_nca_patent")
	private Double totalOtherNcaPatent;
	
	@Column(name="total_other_nca_goodwill")
	private Double totalOtherNcaGoodwill;
	
	@Column(name="total_other_nca_prelimnary_expenses")
	private Double totalOtherNcaPrelimnaryExpenses;
	
	@Column(name="total_other_nca_bad_expenses")
	private Double totalOtherNcaBadExpenses;
	
	@Column(name="total_other_nca_other")
	private Double totalOtherNcaOther;
	
	@Column(name = "other_nca_other_capital_work_inprogress")
	private Double otherNcaOtherCapitalWorkInprogress;
	
	
	@Column(name = "gross_block_1")
	private Double GrossBlock1;

	@Column(name = "gross_block_2")
	private Double GrossBlock2;

	@Column(name = "gross_block_3")
	private Double GrossBlock3;

	@Column(name = "gross_block_4")
	private Double GrossBlock4;
	
	@Column(name = "other_income_need_to_chek_asset")
	private Double otherIncomeNeedTocCheckAsset;
	

//	@Column(name = "gross_block_5")
//	private Double GrossBlock5;

	//bi-directional many-to-one association to FsLoanApplicationMaster
	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster loanApplicationMaster;
	
	
	public AssetsDetails() {
		super();
		this.advancePaymentTaxes = 0.0;
		this.advanceToSupplierRawMaterials = 0.0;
		this.advanceToSuppliersCapitalGoods = 0.0;
		this.anyOther = 0.0;
		this.badOrDoubtfulExpenses = 0.0;
		this.cashAndBankBalance= 0.0;
		this.currentRatio = 0.0;
		this.deferredReceviables = 0.0;
		this.deferredReceviablesOthers = 0.0;
		this.deferredTaxAssets = 0.0;
		this.depreciationToDate = 0.0;
		this.exportReceivables = 0.0;
		this.finishedGoods= 0.0;
		this.fixedDepositsWithBanks = 0.0;
		this.goodWill = 0.0;
		this.governmentAndOtherTrustee = 0.0;
		this.grossBlock = 0.0;
		this.instalmentsDeferred= 0.0;
		this.intangibleAssets = 0.0;
		this.inventory = 0.0;
		this.investments = 0.0;
		this.investmentsInSubsidiary= 0.0;
		this.investmentsOrBookDebts= 0.0;
		this.netBlock= 0.0;
		this.netWorkingCapital = 0.0;
		this.nonConsumableStoreAndSpares= 0.0;
		this.otherConsumableSpares = 0.0;
		this.otherConsumableSparesImported = 0.0;
		this.otherConsumableSparesIndegenous = 0.0;
		this.otherCurrentAssets = 0.0;
		this.otherNonCurrentAssets = 0.0;
		this.others = 0.0;
		this.patents = 0.0;
		this.prelimExpenses= 0.0;
		this.rawMaterial = 0.0;
		this.rawMaterialImported = 0.0;
		this.rawMaterialIndegenous = 0.0;
		this.receivableOtherThanDefferred = 0.0;
		this.stockInProcess = 0.0;
		this.tangibleNetWorth = 0.0;
		this.totalAssets = 0.0;
		this.totalCurrentAssets = 0.0;
		this.totalIntangibleAssets = 0.0;
		this.totalOtherNonCurrentAssets = 0.0;
		this.totalOutSideLiability = 0.0;
		this.totalTermLiability = 0.0;
		this.landBuilding= 0.0;
		this.plantMachines = 0.0;
		this.impairmentAsset = 0.0;
		this.othersPreOperativeExpensesPending = 0.0;
		this.othersAssetsInTransit = 0.0;
		this.othersOther = 0.0;
		this.totalOtherNcaPatent= 0.0;
		this.totalOtherNcaGoodwill = 0.0;
		this.totalOtherNcaPrelimnaryExpenses = 0.0;
		this.totalOtherNcaBadExpenses = 0.0;
		this.totalOtherNcaOther= 0.0;
		this.otherNcaOtherCapitalWorkInprogress = 0.0;
		this.GrossBlock1 = 0.0;
		this.GrossBlock2 = 0.0;
		this.GrossBlock3 = 0.0;
		this.GrossBlock4 = 0.0;
		this.otherIncomeNeedTocCheckAsset=0.0;
	}

	public Long getStorageDetailsId() {
		return storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAdvancePaymentTaxes() {
		return this.advancePaymentTaxes;
	}

	public void setAdvancePaymentTaxes(Double advancePaymentTaxes) {
		this.advancePaymentTaxes = advancePaymentTaxes;
	}

	public Double getAdvanceToSupplierRawMaterials() {
		return this.advanceToSupplierRawMaterials;
	}

	public void setAdvanceToSupplierRawMaterials(Double advanceToSupplierRawMaterials) {
		this.advanceToSupplierRawMaterials = advanceToSupplierRawMaterials;
	}

	public Double getAdvanceToSuppliersCapitalGoods() {
		return this.advanceToSuppliersCapitalGoods;
	}

	public void setAdvanceToSuppliersCapitalGoods(Double advanceToSuppliersCapitalGoods) {
		this.advanceToSuppliersCapitalGoods = advanceToSuppliersCapitalGoods;
	}

	public Double getAnyOther() {
		return this.anyOther;
	}

	public void setAnyOther(Double anyOther) {
		this.anyOther = anyOther;
	}

	public Double getBadOrDoubtfulExpenses() {
		return this.badOrDoubtfulExpenses;
	}

	public void setBadOrDoubtfulExpenses(Double badOrDoubtfulExpenses) {
		this.badOrDoubtfulExpenses = badOrDoubtfulExpenses;
	}

	public Double getCashAndBankBalance() {
		return this.cashAndBankBalance;
	}

	public void setCashAndBankBalance(Double cashAndBankBalance) {
		this.cashAndBankBalance = cashAndBankBalance;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getCurrentRatio() {
		return this.currentRatio;
	}

	public void setCurrentRatio(Double currentRatio) {
		this.currentRatio = currentRatio;
	}

	public Double getDeferredReceviables() {
		return this.deferredReceviables;
	}

	public void setDeferredReceviables(Double deferredReceviables) {
		this.deferredReceviables = deferredReceviables;
	}

	public Double getDeferredReceviablesOthers() {
		return this.deferredReceviablesOthers;
	}

	public void setDeferredReceviablesOthers(Double deferredReceviablesOthers) {
		this.deferredReceviablesOthers = deferredReceviablesOthers;
	}

	public Double getDeferredTaxAssets() {
		return this.deferredTaxAssets;
	}

	public void setDeferredTaxAssets(Double deferredTaxAssets) {
		this.deferredTaxAssets = deferredTaxAssets;
	}

	public Double getDepreciationToDate() {
		return this.depreciationToDate;
	}

	public void setDepreciationToDate(Double depreciationToDate) {
		this.depreciationToDate = depreciationToDate;
	}

	public Double getExportReceivables() {
		return this.exportReceivables;
	}

	public void setExportReceivables(Double exportReceivables) {
		this.exportReceivables = exportReceivables;
	}
	
	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}

	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
	}

	public Double getFinishedGoods() {
		return this.finishedGoods;
	}

	public void setFinishedGoods(Double finishedGoods) {
		this.finishedGoods = finishedGoods;
	}

	public Double getFixedDepositsWithBanks() {
		return this.fixedDepositsWithBanks;
	}

	public void setFixedDepositsWithBanks(Double fixedDepositsWithBanks) {
		this.fixedDepositsWithBanks = fixedDepositsWithBanks;
	}

	public Double getGoodWill() {
		return this.goodWill;
	}

	public void setGoodWill(Double goodWill) {
		this.goodWill = goodWill;
	}

	public Double getGovernmentAndOtherTrustee() {
		return this.governmentAndOtherTrustee;
	}

	public void setGovernmentAndOtherTrustee(Double governmentAndOtherTrustee) {
		this.governmentAndOtherTrustee = governmentAndOtherTrustee;
	}

	public Double getGrossBlock() {
		return this.grossBlock;
	}

	public void setGrossBlock(Double grossBlock) {
		this.grossBlock = grossBlock;
	}

	public Double getInstalmentsDeferred() {
		return this.instalmentsDeferred;
	}

	public void setInstalmentsDeferred(Double instalmentsDeferred) {
		this.instalmentsDeferred = instalmentsDeferred;
	}

	public Double getIntangibleAssets() {
		return this.intangibleAssets;
	}

	public void setIntangibleAssets(Double intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public Double getInventory() {
		return this.inventory;
	}

	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}

	public Double getInvestments() {
		return this.investments;
	}

	public void setInvestments(Double investments) {
		this.investments = investments;
	}

	public Double getInvestmentsInSubsidiary() {
		return this.investmentsInSubsidiary;
	}

	public void setInvestmentsInSubsidiary(Double investmentsInSubsidiary) {
		this.investmentsInSubsidiary = investmentsInSubsidiary;
	}

	public Double getInvestmentsOrBookDebts() {
		return this.investmentsOrBookDebts;
	}

	public void setInvestmentsOrBookDebts(Double investmentsOrBookDebts) {
		this.investmentsOrBookDebts = investmentsOrBookDebts;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public Double getNetBlock() {
		return netBlock;
	}

	public void setNetBlock(Double netBlock) {
		this.netBlock = netBlock;
	}


	public Double getNetWorkingCapital() {
		return netWorkingCapital;
	}

	public void setNetWorkingCapital(Double netWorkingCapital) {
		this.netWorkingCapital = netWorkingCapital;
	}

	public Double getNonConsumableStoreAndSpares() {
		return this.nonConsumableStoreAndSpares;
	}

	public void setNonConsumableStoreAndSpares(Double nonConsumableStoreAndSpares) {
		this.nonConsumableStoreAndSpares = nonConsumableStoreAndSpares;
	}

	public Double getOtherConsumableSpares() {
		return this.otherConsumableSpares;
	}

	public void setOtherConsumableSpares(Double otherConsumableSpares) {
		this.otherConsumableSpares = otherConsumableSpares;
	}

	public Double getOtherConsumableSparesImported() {
		return this.otherConsumableSparesImported;
	}

	public void setOtherConsumableSparesImported(Double otherConsumableSparesImported) {
		this.otherConsumableSparesImported = otherConsumableSparesImported;
	}

	public Double getOtherConsumableSparesIndegenous() {
		return this.otherConsumableSparesIndegenous;
	}

	public void setOtherConsumableSparesIndegenous(Double otherConsumableSparesIndegenous) {
		this.otherConsumableSparesIndegenous = otherConsumableSparesIndegenous;
	}

	public Double getOtherCurrentAssets() {
		return this.otherCurrentAssets;
	}

	public void setOtherCurrentAssets(Double otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	public Double getOtherNonCurrentAssets() {
		return this.otherNonCurrentAssets;
	}

	public void setOtherNonCurrentAssets(Double otherNonCurrentAssets) {
		this.otherNonCurrentAssets = otherNonCurrentAssets;
	}

	public Double getOthers() {
		return this.others;
	}

	public void setOthers(Double others) {
		this.others = others;
	}

	public Double getPatents() {
		return this.patents;
	}

	public void setPatents(Double patents) {
		this.patents = patents;
	}

	public Double getPrelimExpenses() {
		return this.prelimExpenses;
	}

	public void setPrelimExpenses(Double prelimExpenses) {
		this.prelimExpenses = prelimExpenses;
	}

	public Double getRawMaterial() {
		return this.rawMaterial;
	}

	public void setRawMaterial(Double rawMaterial) {
		this.rawMaterial = rawMaterial;
	}

	public Double getRawMaterialImported() {
		return this.rawMaterialImported;
	}

	public void setRawMaterialImported(Double rawMaterialImported) {
		this.rawMaterialImported = rawMaterialImported;
	}

	public Double getRawMaterialIndegenous() {
		return this.rawMaterialIndegenous;
	}

	public void setRawMaterialIndegenous(Double rawMaterialIndegenous) {
		this.rawMaterialIndegenous = rawMaterialIndegenous;
	}

	public Double getReceivableOtherThanDefferred() {
		return this.receivableOtherThanDefferred;
	}

	public void setReceivableOtherThanDefferred(Double receivableOtherThanDefferred) {
		this.receivableOtherThanDefferred = receivableOtherThanDefferred;
	}

	public Double getStockInProcess() {
		return this.stockInProcess;
	}

	public void setStockInProcess(Double stockInProcess) {
		this.stockInProcess = stockInProcess;
	}

	public Double getTangibleNetWorth() {
		return this.tangibleNetWorth;
	}

	public void setTangibleNetWorth(Double tangibleNetWorth) {
		this.tangibleNetWorth = tangibleNetWorth;
	}

	public Double getTotalAssets() {
		return this.totalAssets;
	}

	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}


	public Double getTotalCurrentAssets() {
		return totalCurrentAssets;
	}

	public void setTotalCurrentAssets(Double totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}

	public Double getTotalIntangibleAssets() {
		return this.totalIntangibleAssets;
	}

	public void setTotalIntangibleAssets(Double totalIntangibleAssets) {
		this.totalIntangibleAssets = totalIntangibleAssets;
	}

	public Double getTotalOtherNonCurrentAssets() {
		return this.totalOtherNonCurrentAssets;
	}

	public void setTotalOtherNonCurrentAssets(Double totalOtherNonCurrentAssets) {
		this.totalOtherNonCurrentAssets = totalOtherNonCurrentAssets;
	}

	public Double getTotalOutSideLiability() {
		return this.totalOutSideLiability;
	}

	public void setTotalOutSideLiability(Double totalOutSideLiability) {
		this.totalOutSideLiability = totalOutSideLiability;
	}

	public Double getTotalTermLiability() {
		return this.totalTermLiability;
	}

	public void setTotalTermLiability(Double totalTermLiability) {
		this.totalTermLiability = totalTermLiability;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LoanApplicationMaster getLoanApplicationMaster() {
		return loanApplicationMaster;
	}

	public void setLoanApplicationMaster(LoanApplicationMaster loanApplicationMaster) {
		this.loanApplicationMaster = loanApplicationMaster;
	}

	public Double getGrossBlock1() {
		return GrossBlock1;
	}

	public void setGrossBlock1(Double grossBlock1) {
		GrossBlock1 = grossBlock1;
	}

	public Double getGrossBlock2() {
		return GrossBlock2;
	}

	public void setGrossBlock2(Double grossBlock2) {
		GrossBlock2 = grossBlock2;
	}

	public Double getGrossBlock3() {
		return GrossBlock3;
	}

	public void setGrossBlock3(Double grossBlock3) {
		GrossBlock3 = grossBlock3;
	}

	public Double getGrossBlock4() {
		return GrossBlock4;
	}

	public void setGrossBlock4(Double grossBlock4) {
		GrossBlock4 = grossBlock4;
	}

//	public Double getGrossBlock5() {
//		return GrossBlock5;
//	}
//
//	public void setGrossBlock5(Double grossBlock5) {
//		GrossBlock5 = grossBlock5;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getLandBuilding() {
		return landBuilding;
	}

	public void setLandBuilding(Double landBuilding) {
		this.landBuilding = landBuilding;
	}

	public Double getPlantMachines() {
		return plantMachines;
	}

	public void setPlantMachines(Double plantMachines) {
		this.plantMachines = plantMachines;
	}

	public Double getImpairmentAsset() {
		return impairmentAsset;
	}

	public void setImpairmentAsset(Double impairmentAsset) {
		this.impairmentAsset = impairmentAsset;
	}

	public Double getOthersPreOperativeExpensesPending() {
		return othersPreOperativeExpensesPending;
	}

	public void setOthersPreOperativeExpensesPending(Double othersPreOperativeExpensesPending) {
		this.othersPreOperativeExpensesPending = othersPreOperativeExpensesPending;
	}

	public Double getOthersAssetsInTransit() {
		return othersAssetsInTransit;
	}

	public void setOthersAssetsInTransit(Double othersAssetsInTransit) {
		this.othersAssetsInTransit = othersAssetsInTransit;
	}

	public Double getOthersOther() {
		return othersOther;
	}

	public void setOthersOther(Double othersOther) {
		this.othersOther = othersOther;
	}

	public Double getTotalOtherNcaPatent() {
		return totalOtherNcaPatent;
	}

	public void setTotalOtherNcaPatent(Double totalOtherNcaPatent) {
		this.totalOtherNcaPatent = totalOtherNcaPatent;
	}

	public Double getTotalOtherNcaGoodwill() {
		return totalOtherNcaGoodwill;
	}

	public void setTotalOtherNcaGoodwill(Double totalOtherNcaGoodwill) {
		this.totalOtherNcaGoodwill = totalOtherNcaGoodwill;
	}

	public Double getTotalOtherNcaPrelimnaryExpenses() {
		return totalOtherNcaPrelimnaryExpenses;
	}

	public void setTotalOtherNcaPrelimnaryExpenses(Double totalOtherNcaPrelimnaryExpenses) {
		this.totalOtherNcaPrelimnaryExpenses = totalOtherNcaPrelimnaryExpenses;
	}

	public Double getTotalOtherNcaBadExpenses() {
		return totalOtherNcaBadExpenses;
	}

	public void setTotalOtherNcaBadExpenses(Double totalOtherNcaBadExpenses) {
		this.totalOtherNcaBadExpenses = totalOtherNcaBadExpenses;
	}

	public Double getTotalOtherNcaOther() {
		return totalOtherNcaOther;
	}

	public void setTotalOtherNcaOther(Double totalOtherNcaOther) {
		this.totalOtherNcaOther = totalOtherNcaOther;
	}

	public Double getOtherNcaOtherCapitalWorkInprogress() {
		return otherNcaOtherCapitalWorkInprogress;
	}

	public void setOtherNcaOtherCapitalWorkInprogress(Double otherNcaOtherCapitalWorkInprogress) {
		this.otherNcaOtherCapitalWorkInprogress = otherNcaOtherCapitalWorkInprogress;
	}

	/**
	 * @return the otherIncomeNeedTocCheckAsset
	 */
	public Double getOtherIncomeNeedTocCheckAsset() {
		return otherIncomeNeedTocCheckAsset;
	}

	/**
	 * @param otherIncomeNeedTocCheckAsset the otherIncomeNeedTocCheckAsset to set
	 */
	public void setOtherIncomeNeedTocCheckAsset(Double otherIncomeNeedTocCheckAsset) {
		this.otherIncomeNeedTocCheckAsset = otherIncomeNeedTocCheckAsset;
	}

	

}
