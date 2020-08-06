/**
 * 
 */
package com.opl.mudra.api.gst.model;

/**
 * @author nilay.darji
 *
 */
public class Gstr4QtrWisePurData {
	
	private String retPer;
	private Double b2b;
	private Double b2bUnRegTaxPay;
	private Double importOfService;
	private Double total;
	public String getRetPer() {
		return retPer;
	}
	public void setRetPer(String retPer) {
		this.retPer = retPer;
	}
	public Double getB2b() {
		return b2b;
	}
	public void setB2b(Double b2b) {
		this.b2b = b2b;
	}
	public Double getB2bUnRegTaxPay() {
		return b2bUnRegTaxPay;
	}
	public void setB2bUnRegTaxPay(Double b2bUnRegTaxPay) {
		this.b2bUnRegTaxPay = b2bUnRegTaxPay;
	}
	public Double getImportOfService() {
		return importOfService;
	}
	public void setImportOfService(Double importOfService) {
		this.importOfService = importOfService;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
}
