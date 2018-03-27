package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;
import com.capitaworld.service.loans.model.LoanApplicationRequest;

/**
 * The persistent class for the fs_corporate_bs_profitibility_statement_details
 * database table.
 * 
 */
public class ProfitibilityStatementDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private double adminAndSellingExpenses;

	private double alreadyPaid;

	private double amortisation;

	private LoanApplicationRequest applicationId;

	private double bsProvision;

	private double closingStockFg;

	private double closingStockWip;

	private double costRawMaterialConsumed;

	private Long createdBy;

	private Date createdDate;

	private double currentTax;

	private double deferredTax;

	private double depreciation;

	private double depreciationAndAmortisation;

	private double dividend;

	private double employeeBenefitExpenses;

	private double extraordinaryItems;

	private String financialYearlyStatement;
	
	private double factoryWages;

	private double financeCost;

	private double grossOperatingRevenue;

	private double increaseOrDecreaseInInventoryFg;

	private double increaseOrDecreaseInInventoryWip;

	private Boolean isActive;

	private double lessAnyOtherItem;

	private double lessExciseDutyOrVatOrServiceTax;

	private Long modifiedBy;

	private Date modifiedDate;

	private double netSales;

	private double nonOperatingExpenses;

	private double nonOperatingIncome;

	private double openingStockOfFg;

	private double openingStockWip;

	private double operatingExpenses;

	private double operatingProfitBeforeDepreciation;

	private double operatingProfitBeforeInterestAndTax;

	private double operatingProfitBeforeTax;

	private double otherExpenses;

	private double otherOperatingRevenue;

	private double otherPlsSpecify;

	private double personnelCost;

	private double powerAndFuel;

	private double profitAfterTax;

	private double profitBeforeTax;

	private double provisionForTax;

	private double purchasesStockTnTrade;

	private double rawMaterialImported;

	private double rawMaterialIndigenous;

	private double retainedProfit;

	private double sales;

	private double salesDomestic;

	private double salesExport;

	private double storeAndSpares;

	private double storeAndSparesImported;

	private double storeAndSparesIndeigenous;

	private String year;

	private Long storageDetailsId;

	private Double LessItem1;

	private Double LessItem2;

	private Double LessItem3;

	private Double LessItem4;

	private Double LessItem5;

	// other_operating_revenue
	private Double OtherOperatingRevenue1;

	private Double OtherOperatingRevenue2;

	private Double OtherOperatingRevenue3;

	private Double OtherOperatingRevenue4;

	private Double OtherOperatingRevenue5;

	// expense_capitalized

	private Double ExpenseCapitalized1;

	private Double ExpenseCapitalized2;

	private Double ExpenseCapitalized3;

	private Double ExpenseCapitalized4;

	// extraordinary_items

	private Double ExtraordinaryItems1;

	private Double ExtraordinaryItems2;

	private Double ExtraordinaryItems3;

	private Double ExtraordinaryItems4;

	private Double ExtraordinaryItems5;

	private double generalAdminExpenses;

	private double sellingDistributionExpenses;

	private double expensesCapitalised;

	
	public ProfitibilityStatementDetailRequest() {
		super();
		this.adminAndSellingExpenses  = 0.0;
		this.alreadyPaid  = 0.0;
		this.amortisation  = 0.0;
		this.bsProvision  = 0.0;
		this.closingStockFg  = 0.0;
		this.closingStockWip  = 0.0;
		this.costRawMaterialConsumed = 0.0;
		this.currentTax  = 0.0;
		this.deferredTax  = 0.0;
		this.depreciation = 0.0;
		this.depreciationAndAmortisation = 0.0;
		this.dividend  = 0.0;
		this.employeeBenefitExpenses  = 0.0;
		this.extraordinaryItems  = 0.0;
		this.factoryWages = 0.0;
		this.financeCost  = 0.0;
		this.grossOperatingRevenue  = 0.0;
		this.increaseOrDecreaseInInventoryFg  = 0.0;
		this.increaseOrDecreaseInInventoryWip  = 0.0;
		this.lessAnyOtherItem  = 0.0;
		this.lessExciseDutyOrVatOrServiceTax  = 0.0;
		this.netSales = 0.0;
		this.nonOperatingExpenses  = 0.0;
		this.nonOperatingIncome  = 0.0;
		this.openingStockOfFg = 0.0;
		this.openingStockWip  = 0.0;
		this.operatingExpenses  = 0.0;
		this.operatingProfitBeforeDepreciation  = 0.0;
		this.operatingProfitBeforeInterestAndTax  = 0.0;
		this.operatingProfitBeforeTax  = 0.0;
		this.otherExpenses  = 0.0;
		this.otherOperatingRevenue = 0.0;
		this.otherPlsSpecify  = 0.0;
		this.personnelCost  = 0.0;
		this.powerAndFuel = 0.0;
		this.profitAfterTax  = 0.0;
		this.profitBeforeTax = 0.0;
		this.provisionForTax  = 0.0;
		this.purchasesStockTnTrade  = 0.0;
		this.rawMaterialImported  = 0.0;
		this.rawMaterialIndigenous = 0.0;
		this.sales  = 0.0;
		this.salesDomestic  = 0.0;
		this.salesExport  = 0.0;
		this.storeAndSpares  = 0.0;
		this.storeAndSparesImported  = 0.0;
		this.storeAndSparesIndeigenous = 0.0;
		this.LessItem1  = 0.0;
		this.LessItem2  = 0.0;
		this.LessItem3  = 0.0;
		this.LessItem4  = 0.0;
		this.LessItem5  = 0.0;
		this.OtherOperatingRevenue1 = 0.0;
		this.OtherOperatingRevenue2  = 0.0;
		this.OtherOperatingRevenue3 = 0.0;
		this.OtherOperatingRevenue4 = 0.0;
		this.OtherOperatingRevenue5  = 0.0;
		this.ExpenseCapitalized1  = 0.0;
		this.ExpenseCapitalized2  = 0.0;
		this.ExpenseCapitalized3  = 0.0;
		this.ExpenseCapitalized4  = 0.0;
		this.ExtraordinaryItems1  = 0.0;
		this.ExtraordinaryItems2  = 0.0;
		this.ExtraordinaryItems3  = 0.0;
		this.ExtraordinaryItems4 = 0.0;
		this.ExtraordinaryItems5  = 0.0;
		this.generalAdminExpenses  = 0.0;
		this.sellingDistributionExpenses  = 0.0;
		this.expensesCapitalised  = 0.0;
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

	public double getAdminAndSellingExpenses() {
		return adminAndSellingExpenses;
	}

	public void setAdminAndSellingExpenses(double adminAndSellingExpenses) {
		this.adminAndSellingExpenses = adminAndSellingExpenses;
	}

	public double getAlreadyPaid() {
		return alreadyPaid;
	}

	public void setAlreadyPaid(double alreadyPaid) {
		this.alreadyPaid = alreadyPaid;
	}

	public double getAmortisation() {
		return amortisation;
	}

	public void setAmortisation(double amortisation) {
		this.amortisation = amortisation;
	}

	public LoanApplicationRequest getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationRequest applicationId) {
		this.applicationId = applicationId;
	}

	public double getBsProvision() {
		return bsProvision;
	}

	public void setBsProvision(double bsProvision) {
		this.bsProvision = bsProvision;
	}

	public double getClosingStockFg() {
		return closingStockFg;
	}

	public void setClosingStockFg(double closingStockFg) {
		this.closingStockFg = closingStockFg;
	}

	public double getClosingStockWip() {
		return closingStockWip;
	}

	public void setClosingStockWip(double closingStockWip) {
		this.closingStockWip = closingStockWip;
	}

	public double getCostRawMaterialConsumed() {
		return costRawMaterialConsumed;
	}

	public void setCostRawMaterialConsumed(double costRawMaterialConsumed) {
		this.costRawMaterialConsumed = costRawMaterialConsumed;
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

	public double getCurrentTax() {
		return currentTax;
	}

	public void setCurrentTax(double currentTax) {
		this.currentTax = currentTax;
	}

	public double getDeferredTax() {
		return deferredTax;
	}

	public void setDeferredTax(double deferredTax) {
		this.deferredTax = deferredTax;
	}

	public double getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(double depreciation) {
		this.depreciation = depreciation;
	}

	public double getDepreciationAndAmortisation() {
		return depreciationAndAmortisation;
	}

	public void setDepreciationAndAmortisation(double depreciationAndAmortisation) {
		this.depreciationAndAmortisation = depreciationAndAmortisation;
	}

	public double getDividend() {
		return dividend;
	}

	public void setDividend(double dividend) {
		this.dividend = dividend;
	}

	public double getEmployeeBenefitExpenses() {
		return employeeBenefitExpenses;
	}

	public void setEmployeeBenefitExpenses(double employeeBenefitExpenses) {
		this.employeeBenefitExpenses = employeeBenefitExpenses;
	}

	public double getExtraordinaryItems() {
		return extraordinaryItems;
	}

	public void setExtraordinaryItems(double extraordinaryItems) {
		this.extraordinaryItems = extraordinaryItems;
	}

	public String getFinancialYearlyStatement() {
		return financialYearlyStatement;
	}


	public void setFinancialYearlyStatement(String financialYearlyStatement) {
		this.financialYearlyStatement = financialYearlyStatement;
	}

	public double getFactoryWages() {
		return factoryWages;
	}

	public void setFactoryWages(double factoryWages) {
		this.factoryWages = factoryWages;
	}

	public double getFinanceCost() {
		return financeCost;
	}

	public void setFinanceCost(double financeCost) {
		this.financeCost = financeCost;
	}

	public double getGrossOperatingRevenue() {
		return grossOperatingRevenue;
	}

	public void setGrossOperatingRevenue(double grossOperatingRevenue) {
		this.grossOperatingRevenue = grossOperatingRevenue;
	}

	public double getIncreaseOrDecreaseInInventoryFg() {
		return increaseOrDecreaseInInventoryFg;
	}

	public void setIncreaseOrDecreaseInInventoryFg(double increaseOrDecreaseInInventoryFg) {
		this.increaseOrDecreaseInInventoryFg = increaseOrDecreaseInInventoryFg;
	}

	public double getIncreaseOrDecreaseInInventoryWip() {
		return increaseOrDecreaseInInventoryWip;
	}

	public void setIncreaseOrDecreaseInInventoryWip(double increaseOrDecreaseInInventoryWip) {
		this.increaseOrDecreaseInInventoryWip = increaseOrDecreaseInInventoryWip;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public double getLessAnyOtherItem() {
		return lessAnyOtherItem;
	}

	public void setLessAnyOtherItem(double lessAnyOtherItem) {
		this.lessAnyOtherItem = lessAnyOtherItem;
	}

	public double getLessExciseDutyOrVatOrServiceTax() {
		return lessExciseDutyOrVatOrServiceTax;
	}

	public void setLessExciseDutyOrVatOrServiceTax(double lessExciseDutyOrVatOrServiceTax) {
		this.lessExciseDutyOrVatOrServiceTax = lessExciseDutyOrVatOrServiceTax;
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

	public double getNetSales() {
		return netSales;
	}

	public void setNetSales(double netSales) {
		this.netSales = netSales;
	}

	public double getNonOperatingExpenses() {
		return nonOperatingExpenses;
	}

	public void setNonOperatingExpenses(double nonOperatingExpenses) {
		this.nonOperatingExpenses = nonOperatingExpenses;
	}

	public double getNonOperatingIncome() {
		return nonOperatingIncome;
	}

	public void setNonOperatingIncome(double nonOperatingIncome) {
		this.nonOperatingIncome = nonOperatingIncome;
	}

	public double getOpeningStockOfFg() {
		return openingStockOfFg;
	}

	public void setOpeningStockOfFg(double openingStockOfFg) {
		this.openingStockOfFg = openingStockOfFg;
	}

	public double getOpeningStockWip() {
		return openingStockWip;
	}

	public void setOpeningStockWip(double openingStockWip) {
		this.openingStockWip = openingStockWip;
	}

	public double getOperatingExpenses() {
		return operatingExpenses;
	}

	public void setOperatingExpenses(double operatingExpenses) {
		this.operatingExpenses = operatingExpenses;
	}

	public double getOperatingProfitBeforeDepreciation() {
		return operatingProfitBeforeDepreciation;
	}

	public void setOperatingProfitBeforeDepreciation(double operatingProfitBeforeDepreciation) {
		this.operatingProfitBeforeDepreciation = operatingProfitBeforeDepreciation;
	}

	public double getOperatingProfitBeforeInterestAndTax() {
		return operatingProfitBeforeInterestAndTax;
	}

	public void setOperatingProfitBeforeInterestAndTax(double operatingProfitBeforeInterestAndTax) {
		this.operatingProfitBeforeInterestAndTax = operatingProfitBeforeInterestAndTax;
	}

	public double getOperatingProfitBeforeTax() {
		return operatingProfitBeforeTax;
	}

	public void setOperatingProfitBeforeTax(double operatingProfitBeforeTax) {
		this.operatingProfitBeforeTax = operatingProfitBeforeTax;
	}

	public double getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(double otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public double getOtherOperatingRevenue() {
		return otherOperatingRevenue;
	}

	public void setOtherOperatingRevenue(double otherOperatingRevenue) {
		this.otherOperatingRevenue = otherOperatingRevenue;
	}

	public double getOtherPlsSpecify() {
		return otherPlsSpecify;
	}

	public void setOtherPlsSpecify(double otherPlsSpecify) {
		this.otherPlsSpecify = otherPlsSpecify;
	}

	public double getPersonnelCost() {
		return personnelCost;
	}

	public void setPersonnelCost(double personnelCost) {
		this.personnelCost = personnelCost;
	}

	public double getPowerAndFuel() {
		return powerAndFuel;
	}

	public void setPowerAndFuel(double powerAndSuel) {
		this.powerAndFuel = powerAndSuel;
	}

	public double getProfitAfterTax() {
		return profitAfterTax;
	}

	public void setProfitAfterTax(double profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}

	public double getProfitBeforeTax() {
		return profitBeforeTax;
	}

	public void setProfitBeforeTax(double profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}

	public double getProvisionForTax() {
		return provisionForTax;
	}

	public void setProvisionForTax(double provisionForTax) {
		this.provisionForTax = provisionForTax;
	}

	public double getPurchasesStockTnTrade() {
		return purchasesStockTnTrade;
	}

	public void setPurchasesStockTnTrade(double purchasesStockTnTrade) {
		this.purchasesStockTnTrade = purchasesStockTnTrade;
	}

	public double getRawMaterialImported() {
		return rawMaterialImported;
	}

	public void setRawMaterialImported(double rawMaterialImported) {
		this.rawMaterialImported = rawMaterialImported;
	}

	public double getRawMaterialIndigenous() {
		return rawMaterialIndigenous;
	}

	public void setRawMaterialIndigenous(double rawMaterialIndigenous) {
		this.rawMaterialIndigenous = rawMaterialIndigenous;
	}

	public double getRetainedProfit() {
		return retainedProfit;
	}

	public void setRetainedProfit(double retainedProfit) {
		this.retainedProfit = retainedProfit;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public double getSalesDomestic() {
		return salesDomestic;
	}

	public void setSalesDomestic(double salesDomestic) {
		this.salesDomestic = salesDomestic;
	}

	public double getSalesExport() {
		return salesExport;
	}

	public void setSalesExport(double salesExport) {
		this.salesExport = salesExport;
	}

	public double getStoreAndSpares() {
		return storeAndSpares;
	}

	public void setStoreAndSpares(double storeAndSpares) {
		this.storeAndSpares = storeAndSpares;
	}

	public double getStoreAndSparesImported() {
		return storeAndSparesImported;
	}

	public void setStoreAndSparesImported(double storeAndSparesImported) {
		this.storeAndSparesImported = storeAndSparesImported;
	}

	public double getStoreAndSparesIndeigenous() {
		return storeAndSparesIndeigenous;
	}

	public void setStoreAndSparesIndeigenous(double storeAndSparesIndeigenous) {
		this.storeAndSparesIndeigenous = storeAndSparesIndeigenous;
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

	public Double getLessItem1() {
		return LessItem1;
	}

	public void setLessItem1(Double lessItem1) {
		LessItem1 = lessItem1;
	}

	public Double getLessItem2() {
		return LessItem2;
	}

	public void setLessItem2(Double lessItem2) {
		LessItem2 = lessItem2;
	}

	public Double getLessItem3() {
		return LessItem3;
	}

	public void setLessItem3(Double lessItem3) {
		LessItem3 = lessItem3;
	}

	public Double getLessItem4() {
		return LessItem4;
	}

	public void setLessItem4(Double lessItem4) {
		LessItem4 = lessItem4;
	}

	public Double getLessItem5() {
		return LessItem5;
	}

	public void setLessItem5(Double lessItem5) {
		LessItem5 = lessItem5;
	}

	public Double getOtherOperatingRevenue1() {
		return OtherOperatingRevenue1;
	}

	public void setOtherOperatingRevenue1(Double otherOperatingRevenue1) {
		OtherOperatingRevenue1 = otherOperatingRevenue1;
	}

	public Double getOtherOperatingRevenue2() {
		return OtherOperatingRevenue2;
	}

	public void setOtherOperatingRevenue2(Double otherOperatingRevenue2) {
		OtherOperatingRevenue2 = otherOperatingRevenue2;
	}

	public Double getOtherOperatingRevenue3() {
		return OtherOperatingRevenue3;
	}

	public void setOtherOperatingRevenue3(Double otherOperatingRevenue3) {
		OtherOperatingRevenue3 = otherOperatingRevenue3;
	}

	public Double getOtherOperatingRevenue4() {
		return OtherOperatingRevenue4;
	}

	public void setOtherOperatingRevenue4(Double otherOperatingRevenue4) {
		OtherOperatingRevenue4 = otherOperatingRevenue4;
	}

	public Double getOtherOperatingRevenue5() {
		return OtherOperatingRevenue5;
	}

	public void setOtherOperatingRevenue5(Double otherOperatingRevenue5) {
		OtherOperatingRevenue5 = otherOperatingRevenue5;
	}

	public Double getExpenseCapitalized1() {
		return ExpenseCapitalized1;
	}

	public void setExpenseCapitalized1(Double expenseCapitalized1) {
		ExpenseCapitalized1 = expenseCapitalized1;
	}

	public Double getExpenseCapitalized2() {
		return ExpenseCapitalized2;
	}

	public void setExpenseCapitalized2(Double expenseCapitalized2) {
		ExpenseCapitalized2 = expenseCapitalized2;
	}

	public Double getExpenseCapitalized3() {
		return ExpenseCapitalized3;
	}

	public void setExpenseCapitalized3(Double expenseCapitalized3) {
		ExpenseCapitalized3 = expenseCapitalized3;
	}

	public Double getExpenseCapitalized4() {
		return ExpenseCapitalized4;
	}

	public void setExpenseCapitalized4(Double expenseCapitalized4) {
		ExpenseCapitalized4 = expenseCapitalized4;
	}

	public Double getExtraordinaryItems1() {
		return ExtraordinaryItems1;
	}

	public void setExtraordinaryItems1(Double extraordinaryItems1) {
		ExtraordinaryItems1 = extraordinaryItems1;
	}

	public Double getExtraordinaryItems2() {
		return ExtraordinaryItems2;
	}

	public void setExtraordinaryItems2(Double extraordinaryItems2) {
		ExtraordinaryItems2 = extraordinaryItems2;
	}

	public Double getExtraordinaryItems3() {
		return ExtraordinaryItems3;
	}

	public void setExtraordinaryItems3(Double extraordinaryItems3) {
		ExtraordinaryItems3 = extraordinaryItems3;
	}

	public Double getExtraordinaryItems4() {
		return ExtraordinaryItems4;
	}

	public void setExtraordinaryItems4(Double extraordinaryItems4) {
		ExtraordinaryItems4 = extraordinaryItems4;
	}

	public Double getExtraordinaryItems5() {
		return ExtraordinaryItems5;
	}

	public void setExtraordinaryItems5(Double extraordinaryItems5) {
		ExtraordinaryItems5 = extraordinaryItems5;
	}

	public double getGeneralAdminExpenses() {
		return generalAdminExpenses;
	}

	public void setGeneralAdminExpenses(double generalAdminExpenses) {
		this.generalAdminExpenses = generalAdminExpenses;
	}

	public double getSellingDistributionExpenses() {
		return sellingDistributionExpenses;
	}

	public void setSellingDistributionExpenses(double sellingDistributionExpenses) {
		this.sellingDistributionExpenses = sellingDistributionExpenses;
	}

	public double getExpensesCapitalised() {
		return expensesCapitalised;
	}

	public void setExpensesCapitalised(double expensesCapitalised) {
		this.expensesCapitalised = expensesCapitalised;
	}

}