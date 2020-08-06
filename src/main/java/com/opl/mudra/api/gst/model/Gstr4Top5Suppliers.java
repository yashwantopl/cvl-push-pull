/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class Gstr4Top5Suppliers {
	
	private List<Gstr4Top5SuppliersData> top5SuppliersData;
	private List<Gstr4Top5SuppliersData> allSuppliersData;
	private Double totalOfTtlRec;
	private Double totalOfTtlVal;
	private Double totalOfTtlTax;
	private Double totalOfPurchasePer;
	private Integer distinctCtins;
	
	
	
	public Gstr4Top5Suppliers() {
		super();
		this.totalOfTtlRec = 0d;
		this.totalOfTtlVal = 0d;
		this.totalOfTtlTax = 0d;
		this.totalOfPurchasePer = 0d;
		this.distinctCtins = 0;
	}
	public List<Gstr4Top5SuppliersData> getTop5SuppliersData() {
		return top5SuppliersData;
	}
	public void setTop5SuppliersData(List<Gstr4Top5SuppliersData> top5SuppliersData) {
		this.top5SuppliersData = top5SuppliersData;
	}
	public Double getTotalOfTtlRec() {
		return totalOfTtlRec;
	}
	public void setTotalOfTtlRec(Double totalOfTtlRec) {
		this.totalOfTtlRec = totalOfTtlRec;
	}
	public Double getTotalOfTtlVal() {
		return totalOfTtlVal;
	}
	public void setTotalOfTtlVal(Double totalOfTtlVal) {
		this.totalOfTtlVal = totalOfTtlVal;
	}
	public Double getTotalOfTtlTax() {
		return totalOfTtlTax;
	}
	public void setTotalOfTtlTax(Double totalOfTtlTax) {
		this.totalOfTtlTax = totalOfTtlTax;
	}
	public Double getTotalOfPurchasePer() {
		return totalOfPurchasePer;
	}
	public void setTotalOfPurchasePer(Double totalOfPurchasePer) {
		this.totalOfPurchasePer = totalOfPurchasePer;
	}
	public Integer getDistinctCtins() {
		return distinctCtins;
	}
	public void setDistinctCtins(Integer distinctCtins) {
		this.distinctCtins = distinctCtins;
	}
	public List<Gstr4Top5SuppliersData> getAllSuppliersData() {
		return allSuppliersData;
	}
	public void setAllSuppliersData(List<Gstr4Top5SuppliersData> allSuppliersData) {
		this.allSuppliersData = allSuppliersData;
	}
}
