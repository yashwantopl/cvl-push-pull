package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.LoanApplicationRequest;


public class OperatingStatementDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Double addOperatingStock;

	private Double addOperatingStockFg;

	private Double addOtherNonOpIncome;

	private Double addOtherRevenueIncome;

	private Long createdBy;

	private Date createdDate;

	private Double deductClStockFg;

	private Double deductOtherItems;

	private Double deductOtherNonOpExp;

	private Double deductStockInProcess;

	private Double depreciation;

	private Double directLabour;

	private Double dividendRate;

	private Double domesticSales;

	private Double equityDeividendPaidAmt;

	private Double expensesAmortised;

	private Double exportSales;

	private String financialYearlyStatement;
	
	private Double interest;

	private Boolean isActive;

	private Double lessExciseDuty;

	private Long modifiedBy;

	private Date modifiedDate;

	private Double netProfitOrLoss;

	private Double netSales;

	private Double netofNonOpIncomeOrExpenses;

	private Double opProfitAfterInterest;

	private Double opProfitBeforeIntrest;

	private Double otherMfgExpenses;

	private Double otherSpares;

	private Double otherSparesImported;

	private Double otherSparesIndigenous;

	private Double percentageRiseOrFall;

	private Double powerAndFuel;

	private Double productionCost;

	private Double profitBeforeTaxOrLoss;

	private Double provisionForDeferredTax;

	private Double provisionForTaxes;

	private Double rawMaterials;

	private Double rawMaterialsImported;

	private Double rawMaterialsIndigenous;

	private Double retainedProfit;

	private Double retainedProfitOrNetProfit;

	private Double sellingGenlAdmnExpenses;

	private Double subTotalCostSales;

	private Double subTotalCostSalesAndSelling;

	private Double subTotalDeductAndCostOfProduction;

	private Double subTotalExpenses;

	private Double subTotalOfCostSalesAndOperatingStock;

	private Double subTotalOfIncome;

	private Double totalCostSales;

	private Double totalGrossSales;

	private String year;
	
	private Long storageDetailsId;
	
	private Double generalAdminExp;
	
	private Double SellingAndDistributionExpenses;
	
	private LoanApplicationRequest loanApplicationMaster;

	public OperatingStatementDetailsRequest() {
		super();
		this.addOperatingStock=0.0;
		this.addOperatingStockFg=0.0;
		this.addOtherNonOpIncome=0.0;
		this.addOtherRevenueIncome=0.0;
		this.deductClStockFg=0.0;
		this.deductOtherItems=0.0;
		this.deductOtherNonOpExp=0.0;
		this.deductStockInProcess=0.0;
		this.depreciation=0.0;
		this.directLabour=0.0;
		this.dividendRate=0.0;
		this.domesticSales=0.0;
		this.equityDeividendPaidAmt=0.0;
		this.expensesAmortised =0.0;
		this.exportSales=0.0;
		this.interest=0.0;
		this.lessExciseDuty =0.0;
		this.netProfitOrLoss =0.0;
		this.netSales =0.0;
		this.netofNonOpIncomeOrExpenses=0.0;
		this.opProfitAfterInterest=0.0;
		this.opProfitBeforeIntrest=0.0;
		this.otherMfgExpenses=0.0;
		this.otherSpares =0.0;
		this.otherSparesImported=0.0;
		this.otherSparesIndigenous=0.0;
		this.percentageRiseOrFall =0.0;
		this.powerAndFuel =0.0;
		this.productionCost =0.0;
		this.profitBeforeTaxOrLoss=0.0;
		this.provisionForDeferredTax=0.0;
		this.provisionForTaxes =0.0;
		this.rawMaterials =0.0;
		this.rawMaterialsImported =0.0;
		this.rawMaterialsIndigenous=0.0;
		this.retainedProfit =0.0;
		this.retainedProfitOrNetProfit=0.0;
		this.sellingGenlAdmnExpenses =0.0;
		this.subTotalCostSales =0.0;
		this.subTotalCostSalesAndSelling =0.0;
		this.subTotalDeductAndCostOfProduction=0.0;
		this.subTotalExpenses =0.0;
		this.subTotalOfCostSalesAndOperatingStock=0.0;
		this.subTotalOfIncome =0.0;
		this.totalCostSales =0.0;
		this.totalGrossSales = 0.0;
		this.generalAdminExp=0.0;
	}

	public Double getSellingAndDistributionExpenses() {
		return SellingAndDistributionExpenses;
	}

	public void setSellingAndDistributionExpenses(Double sellingAndDistributionExpenses) {
		SellingAndDistributionExpenses = sellingAndDistributionExpenses;
	}

	

	public Double getGeneralAdminExp() {
		return generalAdminExp;
	}


	public void setGeneralAdminExp(Double generalAdminExp) {
		this.generalAdminExp = generalAdminExp;
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

	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}

	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
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

	public LoanApplicationRequest getLoanApplicationMaster() {
		return loanApplicationMaster;
	}

	public void setLoanApplicationMaster(LoanApplicationRequest loanApplicationMaster) {
		this.loanApplicationMaster = loanApplicationMaster;
	}

	@Override
	public String toString() {
		return "OperatingStatementDetailsRequest [id=" + id + ", addOperatingStock=" + addOperatingStock
				+ ", addOperatingStockFg=" + addOperatingStockFg + ", addOtherNonOpIncome=" + addOtherNonOpIncome
				+ ", addOtherRevenueIncome=" + addOtherRevenueIncome + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", deductClStockFg=" + deductClStockFg + ", deductOtherItems=" + deductOtherItems
				+ ", deductOtherNonOpExp=" + deductOtherNonOpExp + ", deductStockInProcess=" + deductStockInProcess
				+ ", depreciation=" + depreciation + ", directLabour=" + directLabour + ", dividendRate=" + dividendRate
				+ ", domesticSales=" + domesticSales + ", equityDeividendPaidAmt=" + equityDeividendPaidAmt
				+ ", expensesAmortised=" + expensesAmortised + ", exportSales=" + exportSales
				+ ", financialYearlyStatement=" + financialYearlyStatement + ", interest=" + interest + ", isActive="
				+ isActive + ", lessExciseDuty=" + lessExciseDuty + ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", netProfitOrLoss=" + netProfitOrLoss + ", netSales=" + netSales
				+ ", netofNonOpIncomeOrExpenses=" + netofNonOpIncomeOrExpenses + ", opProfitAfterInterest="
				+ opProfitAfterInterest + ", opProfitBeforeIntrest=" + opProfitBeforeIntrest + ", otherMfgExpenses="
				+ otherMfgExpenses + ", otherSpares=" + otherSpares + ", otherSparesImported=" + otherSparesImported
				+ ", otherSparesIndigenous=" + otherSparesIndigenous + ", percentageRiseOrFall=" + percentageRiseOrFall
				+ ", powerAndFuel=" + powerAndFuel + ", productionCost=" + productionCost + ", profitBeforeTaxOrLoss="
				+ profitBeforeTaxOrLoss + ", provisionForDeferredTax=" + provisionForDeferredTax
				+ ", provisionForTaxes=" + provisionForTaxes + ", rawMaterials=" + rawMaterials
				+ ", rawMaterialsImported=" + rawMaterialsImported + ", rawMaterialsIndigenous="
				+ rawMaterialsIndigenous + ", retainedProfit=" + retainedProfit + ", retainedProfitOrNetProfit="
				+ retainedProfitOrNetProfit + ", sellingGenlAdmnExpenses=" + sellingGenlAdmnExpenses
				+ ", subTotalCostSales=" + subTotalCostSales + ", subTotalCostSalesAndSelling="
				+ subTotalCostSalesAndSelling + ", subTotalDeductAndCostOfProduction="
				+ subTotalDeductAndCostOfProduction + ", subTotalExpenses=" + subTotalExpenses
				+ ", subTotalOfCostSalesAndOperatingStock=" + subTotalOfCostSalesAndOperatingStock
				+ ", subTotalOfIncome=" + subTotalOfIncome + ", totalCostSales=" + totalCostSales + ", totalGrossSales="
				+ totalGrossSales + ", year=" + year + ", storageDetailsId=" + storageDetailsId + ", generalAdminExp="
				+ generalAdminExp + ", SellingAndDistributionExpenses=" + SellingAndDistributionExpenses
				+ ", loanApplicationMaster=" + loanApplicationMaster + "]";
	}
	
	

	
}