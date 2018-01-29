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
	
	@Column(name="general_admin_expenses")
	private double generalAdminExpenses;

	@Column(name="selling_distribution_expenses")
	private double sellingDistributionExpenses;

	@Column(name="expenses_capitalised")
	private double expensesCapitalised;

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
	
	

}