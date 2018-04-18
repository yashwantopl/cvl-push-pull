package com.capitaworld.service.loans.model;

import java.io.Serializable;

public class CMADetailResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 //asset	
		private Double receivableOtherThanDefferred; //D16
		private Double exportReceivables; //D18
		private Double inventory; //D22
		private Double advanceToSupplierRawMaterials; //D37
		//liabilities
		private Double sundryCreditors; //D19
		private Double advancePaymentsFromCustomers;//D21
		
		private Double domesticSales; //D8
		private Double exportSales; //D9
		private Double netProfitOrLoss; //D78
		private Double depreciation; //D34
		private Double interest;  //D62
		private Double provisionForDeferredTax; //D77
		
		
		public Double getReceivableOtherThanDefferred() {
			return receivableOtherThanDefferred;
		}
		public void setReceivableOtherThanDefferred(Double receivableOtherThanDefferred) {
			this.receivableOtherThanDefferred = receivableOtherThanDefferred;
		}
		public Double getExportReceivables() {
			return exportReceivables;
		}
		public void setExportReceivables(Double exportReceivables) {
			this.exportReceivables = exportReceivables;
		}
		public Double getInventory() {
			return inventory;
		}
		public void setInventory(Double inventory) {
			this.inventory = inventory;
		}
		public Double getAdvanceToSupplierRawMaterials() {
			return advanceToSupplierRawMaterials;
		}
		public void setAdvanceToSupplierRawMaterials(Double advanceToSupplierRawMaterials) {
			this.advanceToSupplierRawMaterials = advanceToSupplierRawMaterials;
		}
		public Double getSundryCreditors() {
			return sundryCreditors;
		}
		public void setSundryCreditors(Double sundryCreditors) {
			this.sundryCreditors = sundryCreditors;
		}
		public Double getAdvancePaymentsFromCustomers() {
			return advancePaymentsFromCustomers;
		}
		public void setAdvancePaymentsFromCustomers(Double advancePaymentsFromCustomers) {
			this.advancePaymentsFromCustomers = advancePaymentsFromCustomers;
		}
		public Double getDomesticSales() {
			return domesticSales;
		}
		public void setDomesticSales(Double domesticSales) {
			this.domesticSales = domesticSales;
		}
		public Double getExportSales() {
			return exportSales;
		}
		public void setExportSales(Double exportSales) {
			this.exportSales = exportSales;
		}
		public Double getNetProfitOrLoss() {
			return netProfitOrLoss;
		}
		public void setNetProfitOrLoss(Double netProfitOrLoss) {
			this.netProfitOrLoss = netProfitOrLoss;
		}
		public Double getDepreciation() {
			return depreciation;
		}
		public void setDepreciation(Double depreciation) {
			this.depreciation = depreciation;
		}
		
		public Double getInterest() {
			return interest;
		}
		public void setInterest(Double interest) {
			this.interest = interest;
		}
		
		public Double getProvisionForDeferredTax() {
			return provisionForDeferredTax;
		}
		public void setProvisionForDeferredTax(Double provisionForDeferredTax) {
			this.provisionForDeferredTax = provisionForDeferredTax;
		}
		@Override
		public String toString() {
			return "CMADetailResponse [receivableOtherThanDefferred=" + receivableOtherThanDefferred
					+ ", exportReceivables=" + exportReceivables + ", inventory=" + inventory
					+ ", advanceToSupplierRawMaterials=" + advanceToSupplierRawMaterials + ", sundryCreditors="
					+ sundryCreditors + ", advancePaymentsFromCustomers=" + advancePaymentsFromCustomers
					+ ", domesticSales=" + domesticSales + ", exportSales=" + exportSales + ", netProfitOrLoss="
					+ netProfitOrLoss + ", depreciation=" + depreciation + ", interest=" + interest
					+ ", provisionForDeferredTax=" + provisionForDeferredTax + "]";
		}
		
		
		
}
