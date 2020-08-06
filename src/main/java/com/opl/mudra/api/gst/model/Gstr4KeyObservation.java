/**
 * 
 */
package com.opl.mudra.api.gst.model;

/**
 * @author nilay.darji
 *
 */
public class Gstr4KeyObservation {
	
	private Double grossMargin;
	private Double customerConcentration;
	private Double supplierConcentration;
	private Double avgSales;
	private Long medianSales;
	private Double avgPurchase;
	private Long medianPurchase;
	private String highestSalesInQtr;
	private Long highestSalesInQtrVal;
	private String highestPurchaseInQtr;
	private Long highestPurchaseInQtrVal;
	private Double ttlCrNoteVsTtlSales;
	private Double ttlDebNoteVsTtlPurchase;
	private String ttlCrNoteVsTtlSalesRetPer;
	private String ttlDebNoteVsTtlPurRetPer;
	
	public Double getGrossMargin() {
		return grossMargin;
	}
	public void setGrossMargin(Double grossMargin) {
		this.grossMargin = grossMargin;
	}
	public Double getCustomerConcentration() {
		return customerConcentration;
	}
	public void setCustomerConcentration(Double customerConcentration) {
		this.customerConcentration = customerConcentration;
	}
	public Double getSupplierConcentration() {
		return supplierConcentration;
	}
	public void setSupplierConcentration(Double supplierConcentration) {
		this.supplierConcentration = supplierConcentration;
	}
	public Double getAvgSales() {
		return avgSales;
	}
	public void setAvgSales(Double avgSales) {
		this.avgSales = avgSales;
	}
	public Long getMedianSales() {
		return medianSales;
	}
	public void setMedianSales(Long medianSales) {
		this.medianSales = medianSales;
	}
	public Double getAvgPurchase() {
		return avgPurchase;
	}
	public void setAvgPurchase(Double avgPurchase) {
		this.avgPurchase = avgPurchase;
	}
	public Long getMedianPurchase() {
		return medianPurchase;
	}
	public void setMedianPurchase(Long medianPurchase) {
		this.medianPurchase = medianPurchase;
	}
	public String getHighestSalesInQtr() {
		return highestSalesInQtr;
	}
	public void setHighestSalesInQtr(String highestSalesInQtr) {
		this.highestSalesInQtr = highestSalesInQtr;
	}
	public String getHighestPurchaseInQtr() {
		return highestPurchaseInQtr;
	}
	public void setHighestPurchaseInQtr(String highestPurchaseInQtr) {
		this.highestPurchaseInQtr = highestPurchaseInQtr;
	}
	public Long getHighestSalesInQtrVal() {
		return highestSalesInQtrVal;
	}
	public void setHighestSalesInQtrVal(Long highestSalesInQtrVal) {
		this.highestSalesInQtrVal = highestSalesInQtrVal;
	}
	public Long getHighestPurchaseInQtrVal() {
		return highestPurchaseInQtrVal;
	}
	public void setHighestPurchaseInQtrVal(Long highestPurchaseInQtrVal) {
		this.highestPurchaseInQtrVal = highestPurchaseInQtrVal;
	}

	public Double getTtlCrNoteVsTtlSales() {
		return ttlCrNoteVsTtlSales;
	}

	public void setTtlCrNoteVsTtlSales(Double ttlCrNoteVsTtlSales) {
		this.ttlCrNoteVsTtlSales = ttlCrNoteVsTtlSales;
	}
	public Double getTtlDebNoteVsTtlPurchase() {
		return ttlDebNoteVsTtlPurchase;
	}

	public void setTtlDebNoteVsTtlPurchase(Double ttlDebNoteVsTtlPurchase) {
		this.ttlDebNoteVsTtlPurchase = ttlDebNoteVsTtlPurchase;
	}

	public String getTtlCrNoteVsTtlSalesRetPer() {
		return ttlCrNoteVsTtlSalesRetPer;
	}

	public void setTtlCrNoteVsTtlSalesRetPer(String ttlCrNoteVsTtlSalesRetPer) {
		this.ttlCrNoteVsTtlSalesRetPer = ttlCrNoteVsTtlSalesRetPer;
	}

	public String getTtlDebNoteVsTtlPurRetPer() {
		return ttlDebNoteVsTtlPurRetPer;
	}

	public void setTtlDebNoteVsTtlPurRetPer(String ttlDebNoteVsTtlPurRetPer) {
		this.ttlDebNoteVsTtlPurRetPer = ttlDebNoteVsTtlPurRetPer;
	}
}
