/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class Gstr4CalculationResponse {
	
	private String qOnqSales;
	private	Double currTotal;
	private	Double prevTotal;  
	private	Double growth;
	private	Double proSales;
	private List<String> retPerStr;
	private Boolean isHistoric;
	
	public List<String> getRetPerStr() {
		return retPerStr;
	}
	public void setRetPerStr(List<String> retPerStr) {
		this.retPerStr = retPerStr;
	}
	public Double getCurrTotal() {
		return currTotal;
	}
	public void setCurrTotal(Double currTotal) {
		this.currTotal = currTotal;
	}
	public Double getPrevTotal() {
		return prevTotal;
	}
	public void setPrevTotal(Double prevTotal) {
		this.prevTotal = prevTotal;
	}
	public Double getGrowth() {
		return growth;
	}
	public void setGrowth(Double growth) {
		this.growth = growth;
	}
	public Double getProSales() {
		return proSales;
	}
	public void setProSales(Double proSales) {
		this.proSales = proSales;
	}
	public String getqOnqSales() {
		return qOnqSales;
	}
	public void setqOnqSales(String qOnqSales) {
		this.qOnqSales = qOnqSales;
	}
	public Boolean getIsHistoric() {
		return isHistoric;
	}
	public void setIsHistoric(Boolean isHistoric) {
		this.isHistoric = isHistoric;
	}
}
