package com.capitaworld.service.loans.model;

import java.io.Serializable;

public class CMADetailRequest implements Serializable {


	private static final long serialVersionUID = 1L;
	 //asset	
	
		private Double receivableOtherThanDefferred;
		private Double exportReceivables;
		private Double inventory;
		private Double advanceToSupplierRawMaterials;
		//liabilities
		private Double sundryCreditors;
		private Double advancePaymentsFromCustomers;
		
		private Double domesticSales;
		private Double exportSales;
		private Double netProfitOrLoss;
		private Double depreciation;
		private Double provisionForDeferredTax;
		
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
		public Double getProvisionForDeferredTax() {
			return provisionForDeferredTax;
		}
		public void setProvisionForDeferredTax(Double provisionForDeferredTax) {
			this.provisionForDeferredTax = provisionForDeferredTax;
		}
		
		@Override
		public String toString() {
			return "CMADetailDomain [receivableOtherThanDefferred=" + receivableOtherThanDefferred + ", exportReceivables="
					+ exportReceivables + ", inventory=" + inventory + ", advanceToSupplierRawMaterials="
					+ advanceToSupplierRawMaterials + ", sundryCreditors=" + sundryCreditors
					+ ", advancePaymentsFromCustomers=" + advancePaymentsFromCustomers + ", domesticSales=" + domesticSales
					+ ", exportSales=" + exportSales + ", netProfitOrLoss=" + netProfitOrLoss + ", depreciation="
					+ depreciation + ", provisionForDeferredTax=" + provisionForDeferredTax + "]";
		}
		

}
