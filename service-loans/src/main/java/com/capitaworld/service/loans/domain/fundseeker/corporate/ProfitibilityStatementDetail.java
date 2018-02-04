package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.math.BigInteger;
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
 * The persistent class for the fs_corporate_bs_profitibility_statement_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_bs_profitibility_statement_details")
@NamedQuery(name="ProfitibilityStatementDetail.findAll", query="SELECT f FROM ProfitibilityStatementDetail f")
public class ProfitibilityStatementDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="admin_and_selling_expenses")
	private double adminAndSellingExpenses;

	@Column(name="already_paid")
	private double alreadyPaid;

	private double amortisation;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="bs_provision")
	private double bsProvision;

	@Column(name="closing_stock_fg")
	private double closingStockFg;

	@Column(name="closing_stock_wip")
	private double closingStockWip;

	@Column(name="cost_raw_material_consumed")
	private double costRawMaterialConsumed;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="current_tax")
	private double currentTax;

	@Column(name="deferred_tax")
	private double deferredTax;

	private double depreciation;

	@Column(name="depreciation_and_amortisation")
	private double depreciationAndAmortisation;

	private double dividend;

	@Column(name="employee_benefit_expenses")
	private double employeeBenefitExpenses;

	@Column(name="extraordinary_items")
	private double extraordinaryItems;

	@Column(name="factory_wages")
	private double factoryWages;

	@Column(name="finance_cost")
	private double financeCost;

	@Column(name="gross_operating_revenue")
	private double grossOperatingRevenue;

	@Column(name="increase_or_decrease_in_inventory_fg")
	private double increaseOrDecreaseInInventoryFg;

	@Column(name="increase_or_decrease_in_inventory_wip")
	private double increaseOrDecreaseInInventoryWip;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="less_any_other_item")
	private double lessAnyOtherItem;

	@Column(name="less_excise_duty_or_vat_or_service_tax")
	private double lessExciseDutyOrVatOrServiceTax;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_sales")
	private double netSales;

	@Column(name="non_operating_expenses")
	private double nonOperatingExpenses;

	@Column(name="non_operating_income")
	private double nonOperatingIncome;

	@Column(name="opening_stock_of_fg")
	private double openingStockOfFg;

	@Column(name="opening_stock_wip")
	private double openingStockWip;

	@Column(name="operating_expenses")
	private double operatingExpenses;

	@Column(name="operating_profit_before_depreciation")
	private double operatingProfitBeforeDepreciation;

	@Column(name="operating_profit_before_interest_and_tax")
	private double operatingProfitBeforeInterestAndTax;

	@Column(name="operating_profit_before_tax")
	private double operatingProfitBeforeTax;

	@Column(name="other_expenses")
	private double otherExpenses;

	@Column(name="other_operating_revenue")
	private double otherOperatingRevenue;

	@Column(name="other_pls_specify")
	private double otherPlsSpecify;

	@Column(name="personnel_cost")
	private double personnelCost;

	@Column(name="power_and_fuel")
	private double powerAndFuel;

	@Column(name="profit_after_tax")
	private double profitAfterTax;

	@Column(name="profit_before_tax")
	private double profitBeforeTax;

	@Column(name="provision_for_tax")
	private double provisionForTax;

	@Column(name="purchases_stock_tn_trade")
	private double purchasesStockTnTrade;

	@Column(name="raw_material_imported")
	private double rawMaterialImported;

	@Column(name="raw_material_indigenous")
	private double rawMaterialIndigenous;

	@Column(name="retained_profit")
	private double retainedProfit;

	private double sales;

	@Column(name="sales_domestic")
	private double salesDomestic;

	@Column(name="sales_export")
	private double salesExport;

	@Column(name="store_and_spares")
	private double storeAndSpares;

	@Column(name="store_and_spares_imported")
	private double storeAndSparesImported;

	@Column(name="store_and_spares_indeigenous")
	private double storeAndSparesIndeigenous;

	private String year;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;
	
	@Column(name = "less_item_1")
	private Double LessItem1;

	@Column(name = "less_item_2")
	private Double LessItem2;

	@Column(name = "less_item_3")
	private Double LessItem3;

	@Column(name = "less_item_4")
	private Double LessItem4;

	@Column(name = "less_item_5")
	private Double LessItem5;

	// other_operating_revenue

	@Column(name = "other_operating_revenue_1")
	private Double OtherOperatingRevenue1;

	@Column(name = "other_operating_revenue_2")
	private Double OtherOperatingRevenue2;

	@Column(name = "other_operating_revenue_3")
	private Double OtherOperatingRevenue3;

	@Column(name = "other_operating_revenue_4")
	private Double OtherOperatingRevenue4;

	@Column(name = "other_operating_revenue_5")
	private Double OtherOperatingRevenue5;

	// expense_capitalized

	@Column(name = "expense_capitalized_1")
	private Double ExpenseCapitalized1;

	@Column(name = "expense_capitalized_2")
	private Double ExpenseCapitalized2;

	@Column(name = "expense_capitalized_3")
	private Double ExpenseCapitalized3;

	@Column(name = "expense_capitalized_4")
	private Double ExpenseCapitalized4;

	// extraordinary_items

	@Column(name = "extraordinary_items_1")
	private Double ExtraordinaryItems1;

	@Column(name = "extraordinary_items_2")
	private Double ExtraordinaryItems2;

	@Column(name = "extraordinary_items_3")
	private Double ExtraordinaryItems3;

	@Column(name = "extraordinary_items_4")
	private Double ExtraordinaryItems4;

	@Column(name = "extraordinary_items_5")
	private Double ExtraordinaryItems5;
	
	@Column(name="general_admin_expenses")
	private double generalAdminExpenses;

	@Column(name="selling_distribution_expenses")
	private double sellingDistributionExpenses;
	
	@Column(name="expenses_capitalised")
	private double expensesCapitalised;
	
	

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

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
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