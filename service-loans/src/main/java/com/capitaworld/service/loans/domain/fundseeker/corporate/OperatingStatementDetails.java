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
@Table(name="fs_corporate_cma_operating_statement_details")
public class OperatingStatementDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="add_operating_stock")
	private Double addOperatingStock;

	@Column(name="add_operating_stock_fg")
	private Double addOperatingStockFg;

	@Column(name="add_other_non_op_income")
	private Double addOtherNonOpIncome;

	@Column(name="add_other_revenue_income")
	private Double addOtherRevenueIncome;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="deduct_cl_stock_fg")
	private Double deductClStockFg;

	@Column(name="deduct_other_items")
	private Double deductOtherItems;

	@Column(name="deduct_other_non_op_exp")
	private Double deductOtherNonOpExp;

	@Column(name="deduct_stock_in_process")
	private Double deductStockInProcess;

	private Double depreciation;

	@Column(name="direct_labour")
	private Double directLabour;

	@Column(name="dividend_rate")
	private Double dividendRate;

	@Column(name="domestic_sales")
	private Double domesticSales;

	@Column(name="equity_deividend_paid_amt")
	private Double equityDeividendPaidAmt;

	@Column(name="expenses_amortised")
	private Double expensesAmortised;

	@Column(name="export_sales")
	private Double exportSales;

	private Double interest;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="less_excise_duty")
	private Double lessExciseDuty;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_profit_or_loss")
	private Double netProfitOrLoss;

	@Column(name="net_sales")
	private Double netSales;

	@Column(name="netof_non_op_income_or_expenses")
	private Double netofNonOpIncomeOrExpenses;

	@Column(name="op_profit_after_interest")
	private Double opProfitAfterInterest;

	@Column(name="op_profit_before_intrest")
	private Double opProfitBeforeIntrest;

	@Column(name="other_mfg_expenses")
	private Double otherMfgExpenses;

	@Column(name="other_spares")
	private Double otherSpares;

	@Column(name="other_spares_imported")
	private Double otherSparesImported;

	@Column(name="other_spares_indigenous")
	private Double otherSparesIndigenous;

	@Column(name="percentage_rise_or_fall")
	private Double percentageRiseOrFall;

	@Column(name="power_and_fuel")
	private Double powerAndFuel;

	@Column(name="production_cost")
	private Double productionCost;

	@Column(name="profit_before_tax_or_loss")
	private Double profitBeforeTaxOrLoss;

	@Column(name="provision_for_deferred_tax")
	private Double provisionForDeferredTax;

	@Column(name="provision_for_taxes")
	private Double provisionForTaxes;

	@Column(name="raw_materials")
	private Double rawMaterials;

	@Column(name="raw_materials_imported")
	private Double rawMaterialsImported;

	@Column(name="raw_materials_indigenous")
	private Double rawMaterialsIndigenous;

	@Column(name="retained_profit")
	private Double retainedProfit;

	@Column(name="retained_profit_or_net_profit")
	private Double retainedProfitOrNetProfit;

	@Column(name="selling_genl_admn_expenses")
	private Double sellingGenlAdmnExpenses;

	@Column(name="sub_total_cost_Sales")
	private Double subTotalCostSales;

	@Column(name="sub_total_cost_sales_and_selling")
	private Double subTotalCostSalesAndSelling;

	@Column(name="sub_total_deduct_and_cost_of_production")
	private Double subTotalDeductAndCostOfProduction;

	@Column(name="sub_total_expenses")
	private Double subTotalExpenses;

	@Column(name="sub_total_of_cost_sales_and_operating_stock")
	private Double subTotalOfCostSalesAndOperatingStock;

	@Column(name="sub_total_of_income")
	private Double subTotalOfIncome;

	@Column(name="total_cost_sales")
	private Double totalCostSales;

	@Column(name="total_gross_sales")
	private Double totalGrossSales;

	private String year;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;

	//bi-directional many-to-one association to FsLoanApplicationMaster
	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster loanApplicationMaster;
	
	
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

	public Double getAddOperatingStock() {
		return this.addOperatingStock;
	}

	public void setAddOperatingStock(Double addOperatingStock) {
		this.addOperatingStock = addOperatingStock;
	}

	public Double getAddOperatingStockFg() {
		return this.addOperatingStockFg;
	}

	public void setAddOperatingStockFg(Double addOperatingStockFg) {
		this.addOperatingStockFg = addOperatingStockFg;
	}

	public Double getAddOtherNonOpIncome() {
		return this.addOtherNonOpIncome;
	}

	public void setAddOtherNonOpIncome(Double addOtherNonOpIncome) {
		this.addOtherNonOpIncome = addOtherNonOpIncome;
	}

	public Double getAddOtherRevenueIncome() {
		return this.addOtherRevenueIncome;
	}

	public void setAddOtherRevenueIncome(Double addOtherRevenueIncome) {
		this.addOtherRevenueIncome = addOtherRevenueIncome;
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

	public Double getDeductClStockFg() {
		return this.deductClStockFg;
	}

	public void setDeductClStockFg(Double deductClStockFg) {
		this.deductClStockFg = deductClStockFg;
	}

	public Double getDeductOtherItems() {
		return this.deductOtherItems;
	}

	public void setDeductOtherItems(Double deductOtherItems) {
		this.deductOtherItems = deductOtherItems;
	}

	public Double getDeductOtherNonOpExp() {
		return this.deductOtherNonOpExp;
	}

	public void setDeductOtherNonOpExp(Double deductOtherNonOpExp) {
		this.deductOtherNonOpExp = deductOtherNonOpExp;
	}

	public Double getDeductStockInProcess() {
		return this.deductStockInProcess;
	}

	public void setDeductStockInProcess(Double deductStockInProcess) {
		this.deductStockInProcess = deductStockInProcess;
	}

	public Double getDepreciation() {
		return this.depreciation;
	}

	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}

	public Double getDirectLabour() {
		return this.directLabour;
	}

	public void setDirectLabour(Double directLabour) {
		this.directLabour = directLabour;
	}

	public Double getDividendRate() {
		return this.dividendRate;
	}

	public void setDividendRate(Double dividendRate) {
		this.dividendRate = dividendRate;
	}

	public Double getDomesticSales() {
		return this.domesticSales;
	}

	public void setDomesticSales(Double domesticSales) {
		this.domesticSales = domesticSales;
	}

	public Double getEquityDeividendPaidAmt() {
		return this.equityDeividendPaidAmt;
	}

	public void setEquityDeividendPaidAmt(Double equityDeividendPaidAmt) {
		this.equityDeividendPaidAmt = equityDeividendPaidAmt;
	}

	public Double getExpensesAmortised() {
		return this.expensesAmortised;
	}

	public void setExpensesAmortised(Double expensesAmortised) {
		this.expensesAmortised = expensesAmortised;
	}

	public Double getExportSales() {
		return this.exportSales;
	}

	public void setExportSales(Double exportSales) {
		this.exportSales = exportSales;
	}

	public Double getInterest() {
		return this.interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


	public Double getLessExciseDuty() {
		return lessExciseDuty;
	}

	public void setLessExciseDuty(Double lessExciseDuty) {
		this.lessExciseDuty = lessExciseDuty;
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

	public Double getNetProfitOrLoss() {
		return this.netProfitOrLoss;
	}

	public void setNetProfitOrLoss(Double netProfitOrLoss) {
		this.netProfitOrLoss = netProfitOrLoss;
	}

	public Double getNetSales() {
		return this.netSales;
	}

	public void setNetSales(Double netSales) {
		this.netSales = netSales;
	}

	public Double getNetofNonOpIncomeOrExpenses() {
		return this.netofNonOpIncomeOrExpenses;
	}

	public void setNetofNonOpIncomeOrExpenses(Double netofNonOpIncomeOrExpenses) {
		this.netofNonOpIncomeOrExpenses = netofNonOpIncomeOrExpenses;
	}

	public Double getOpProfitAfterInterest() {
		return this.opProfitAfterInterest;
	}

	public void setOpProfitAfterInterest(Double opProfitAfterInterest) {
		this.opProfitAfterInterest = opProfitAfterInterest;
	}

	public Double getOpProfitBeforeIntrest() {
		return this.opProfitBeforeIntrest;
	}

	public void setOpProfitBeforeIntrest(Double opProfitBeforeIntrest) {
		this.opProfitBeforeIntrest = opProfitBeforeIntrest;
	}

	public Double getOtherMfgExpenses() {
		return this.otherMfgExpenses;
	}

	public void setOtherMfgExpenses(Double otherMfgExpenses) {
		this.otherMfgExpenses = otherMfgExpenses;
	}

	public Double getOtherSpares() {
		return this.otherSpares;
	}

	public void setOtherSpares(Double otherSpares) {
		this.otherSpares = otherSpares;
	}

	public Double getOtherSparesImported() {
		return this.otherSparesImported;
	}

	public void setOtherSparesImported(Double otherSparesImported) {
		this.otherSparesImported = otherSparesImported;
	}

	public Double getOtherSparesIndigenous() {
		return this.otherSparesIndigenous;
	}

	public void setOtherSparesIndigenous(Double otherSparesIndigenous) {
		this.otherSparesIndigenous = otherSparesIndigenous;
	}

	public Double getPercentageRiseOrFall() {
		return this.percentageRiseOrFall;
	}

	public void setPercentageRiseOrFall(Double percentageRiseOrFall) {
		this.percentageRiseOrFall = percentageRiseOrFall;
	}

	public Double getPowerAndFuel() {
		return this.powerAndFuel;
	}

	public void setPowerAndFuel(Double powerAndFuel) {
		this.powerAndFuel = powerAndFuel;
	}

	public Double getProductionCost() {
		return this.productionCost;
	}

	public void setProductionCost(Double productionCost) {
		this.productionCost = productionCost;
	}

	public Double getProfitBeforeTaxOrLoss() {
		return this.profitBeforeTaxOrLoss;
	}

	public void setProfitBeforeTaxOrLoss(Double profitBeforeTaxOrLoss) {
		this.profitBeforeTaxOrLoss = profitBeforeTaxOrLoss;
	}

	public Double getProvisionForDeferredTax() {
		return this.provisionForDeferredTax;
	}

	public void setProvisionForDeferredTax(Double provisionForDeferredTax) {
		this.provisionForDeferredTax = provisionForDeferredTax;
	}

	public Double getProvisionForTaxes() {
		return this.provisionForTaxes;
	}

	public void setProvisionForTaxes(Double provisionForTaxes) {
		this.provisionForTaxes = provisionForTaxes;
	}

	public Double getRawMaterials() {
		return this.rawMaterials;
	}

	public void setRawMaterials(Double rawMaterials) {
		this.rawMaterials = rawMaterials;
	}

	public Double getRawMaterialsImported() {
		return this.rawMaterialsImported;
	}

	public void setRawMaterialsImported(Double rawMaterialsImported) {
		this.rawMaterialsImported = rawMaterialsImported;
	}

	public Double getRawMaterialsIndigenous() {
		return this.rawMaterialsIndigenous;
	}

	public void setRawMaterialsIndigenous(Double rawMaterialsIndigenous) {
		this.rawMaterialsIndigenous = rawMaterialsIndigenous;
	}

	public Double getRetainedProfit() {
		return this.retainedProfit;
	}

	public void setRetainedProfit(Double retainedProfit) {
		this.retainedProfit = retainedProfit;
	}

	public Double getRetainedProfitOrNetProfit() {
		return this.retainedProfitOrNetProfit;
	}

	public void setRetainedProfitOrNetProfit(Double retainedProfitOrNetProfit) {
		this.retainedProfitOrNetProfit = retainedProfitOrNetProfit;
	}

	public Double getSellingGenlAdmnExpenses() {
		return this.sellingGenlAdmnExpenses;
	}

	public void setSellingGenlAdmnExpenses(Double sellingGenlAdmnExpenses) {
		this.sellingGenlAdmnExpenses = sellingGenlAdmnExpenses;
	}


	public Double getSubTotalCostSales() {
		return subTotalCostSales;
	}

	public void setSubTotalCostSales(Double subTotalCostSales) {
		this.subTotalCostSales = subTotalCostSales;
	}

	public Double getSubTotalCostSalesAndSelling() {
		return this.subTotalCostSalesAndSelling;
	}

	public void setSubTotalCostSalesAndSelling(Double subTotalCostSalesAndSelling) {
		this.subTotalCostSalesAndSelling = subTotalCostSalesAndSelling;
	}

	public Double getSubTotalDeductAndCostOfProduction() {
		return this.subTotalDeductAndCostOfProduction;
	}

	public void setSubTotalDeductAndCostOfProduction(Double subTotalDeductAndCostOfProduction) {
		this.subTotalDeductAndCostOfProduction = subTotalDeductAndCostOfProduction;
	}

	public Double getSubTotalExpenses() {
		return this.subTotalExpenses;
	}

	public void setSubTotalExpenses(Double subTotalExpenses) {
		this.subTotalExpenses = subTotalExpenses;
	}

	public Double getSubTotalOfCostSalesAndOperatingStock() {
		return this.subTotalOfCostSalesAndOperatingStock;
	}

	public void setSubTotalOfCostSalesAndOperatingStock(Double subTotalOfCostSalesAndOperatingStock) {
		this.subTotalOfCostSalesAndOperatingStock = subTotalOfCostSalesAndOperatingStock;
	}

	public Double getSubTotalOfIncome() {
		return this.subTotalOfIncome;
	}

	public void setSubTotalOfIncome(Double subTotalOfIncome) {
		this.subTotalOfIncome = subTotalOfIncome;
	}

	public Double getTotalCostSales() {
		return this.totalCostSales;
	}

	public void setTotalCostSales(Double totalCostSales) {
		this.totalCostSales = totalCostSales;
	}

	public Double getTotalGrossSales() {
		return this.totalGrossSales;
	}

	public void setTotalGrossSales(Double totalGrossSales) {
		this.totalGrossSales = totalGrossSales;
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

	
}
