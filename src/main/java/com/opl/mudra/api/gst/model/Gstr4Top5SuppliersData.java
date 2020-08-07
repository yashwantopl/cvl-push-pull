/**
 * 
 */
package com.opl.mudra.api.gst.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay.darji
 *
 */
public class Gstr4Top5SuppliersData {
	private Double purchasePer;
	@JsonProperty("ctin")
	private String ctin;
	@JsonProperty("trade_name")
	private String tradeName;
	@JsonProperty("ttl_rec")
	private Integer ttlRec;
	@JsonProperty("ttl_val")
	private Integer ttlVal;
	@JsonProperty("ttl_tax")
	private Integer ttlTax;
	private Double purchaseTotal;// for only use of related party
	
	
	public Double getPurchasePer() {
		return purchasePer;
	}
	public void setPurchasePer(Double purchasePer) {
		this.purchasePer = purchasePer;
	}
	public String getCtin() {
		return ctin;
	}
	public void setCtin(String ctin) {
		this.ctin = ctin;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public Integer getTtlRec() {
		return ttlRec;
	}
	public void setTtlRec(Integer ttlRec) {
		this.ttlRec = ttlRec;
	}
	public Integer getTtlVal() {
		return ttlVal;
	}
	public void setTtlVal(Integer ttlVal) {
		this.ttlVal = ttlVal;
	}
	public Integer getTtlTax() {
		return ttlTax;
	}
	public void setTtlTax(Integer ttlTax) {
		this.ttlTax = ttlTax;
	}
	public Double getPurchaseTotal() {
		return purchaseTotal;
	}
	public void setPurchaseTotal(Double purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
}
