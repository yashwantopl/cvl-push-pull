/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class Gstr4QuarterWisePurchase {
	
	private List<Gstr4QtrWisePurData> gstr4PurchaseData;
	private Double totalOfB2b;
	private Double totalOfB2bUnRegTaxPay;
	private Double totalOfImportOfService;
	private Double totalOfTotal;


	public List<Gstr4QtrWisePurData> getGstr4PurchaseData() {
		return gstr4PurchaseData;
	}
	public void setGstr4PurchaseData(List<Gstr4QtrWisePurData> gstr4PurchaseData) {
		this.gstr4PurchaseData = gstr4PurchaseData;
	}
	public Double getTotalOfB2b() {
		return totalOfB2b;
	}
	public void setTotalOfB2b(Double totalOfB2b) {
		this.totalOfB2b = totalOfB2b;
	}
	public Double getTotalOfB2bUnRegTaxPay() {
		return totalOfB2bUnRegTaxPay;
	}
	public void setTotalOfB2bUnRegTaxPay(Double totalOfB2bUnRegTaxPay) {
		this.totalOfB2bUnRegTaxPay = totalOfB2bUnRegTaxPay;
	}
	public Double getTotalOfImportOfService() {
		return totalOfImportOfService;
	}
	public void setTotalOfImportOfService(Double totalOfImportOfService) {
		this.totalOfImportOfService = totalOfImportOfService;
	}
	public Double getTotalOfTotal() {
		return totalOfTotal;
	}
	public void setTotalOfTotal(Double totalOfTotal) {
		this.totalOfTotal = totalOfTotal;
	}

}
