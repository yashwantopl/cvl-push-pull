/**
 * 
 */
package com.opl.mudra.api.gst.model;

/**
 * @author nilay.darji
 *
 */
public class Gstr4OverView {

	private Long totalNoOfQuarters;
	private Long totalNoOfCustomers;
	private Long totalNoOfSuppliers;
	private Long totalSales;
	private Long totalPurchase;
	private Long totalOfB2b;
	private Long totalOfB2bUnRegTaxPay;
	private Long totalOfImportOfService;
	private Long diffrence;
	private Double margin;
	
	public Gstr4OverView() {
		super();
		this.totalNoOfQuarters = 0l;
		this.totalNoOfCustomers = 0l;
		this.totalNoOfSuppliers = 0l;
		this.totalSales = 0l;
		this.totalPurchase = 0l;
		this.totalOfB2b = 0l;
		this.totalOfB2bUnRegTaxPay = 0l;
		this.totalOfImportOfService = 0l;
		this.diffrence = 0l;
		this.margin = 0d;
	}
	
	public Long getTotalNoOfQuarters() {
		return totalNoOfQuarters;
	}
	public void setTotalNoOfQuarters(Long totalNoOfQuarters) {
		this.totalNoOfQuarters = totalNoOfQuarters;
	}
	public Long getTotalNoOfCustomers() {
		return totalNoOfCustomers;
	}
	public void setTotalNoOfCustomers(Long totalNoOfCustomers) {
		this.totalNoOfCustomers = totalNoOfCustomers;
	}
	public Long getTotalNoOfSuppliers() {
		return totalNoOfSuppliers;
	}
	public void setTotalNoOfSuppliers(Long totalNoOfSuppliers) {
		this.totalNoOfSuppliers = totalNoOfSuppliers;
	}
	public Long getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(Long totalSales) {
		this.totalSales = totalSales;
	}
	public Long getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(Long totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public Long getTotalOfB2b() {
		return totalOfB2b;
	}
	public void setTotalOfB2b(Long totalOfB2b) {
		this.totalOfB2b = totalOfB2b;
	}
	public Long getTotalOfB2bUnRegTaxPay() {
		return totalOfB2bUnRegTaxPay;
	}
	public void setTotalOfB2bUnRegTaxPay(Long totalOfB2bUnRegTaxPay) {
		this.totalOfB2bUnRegTaxPay = totalOfB2bUnRegTaxPay;
	}
	public Long getTotalOfImportOfService() {
		return totalOfImportOfService;
	}
	public void setTotalOfImportOfService(Long totalOfImportOfService) {
		this.totalOfImportOfService = totalOfImportOfService;
	}
	public Long getDiffrence() {
		return diffrence;
	}
	public void setDiffrence(Long diffrence) {
		this.diffrence = diffrence;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}
}
