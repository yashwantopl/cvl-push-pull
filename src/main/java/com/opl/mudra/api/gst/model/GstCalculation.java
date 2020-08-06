/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GstCalculation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double noOfCustomer;
	
	private Double concentration;
	
	private String retPeriod;
	
	private Double projectedSales;
	
	private Double noOfReturnPeriod;
	
	
	private Double growth;
	
	private Double sales1;
	
	private Double sales2;
	
	private Double historicalSales;
	
	private Boolean isNegativeGrowth;
	
	private Boolean isHistoric;
	
	private Integer gstType;
	
	
	private Double previousYearsales;
	
	


	private Double imPurchase1;	
	private Double imPurchase2;
	private Double imGrowthPurchase;	

	private Double imProjectedSales;
	
	private Double imHistoricalSales;
	
	private Double imGstinPreviousYrPurchase;
	


	private Double panPurchase1;	
	private Double panPurchase2;
	private Double panGrowthPurchase;	

	private Double panProjectedSales;
	
	private Double panHistoricalSales;
	
	private Double panGstinPreviousYrPurchase;

	//For VFS
	private Double peakMonthlySales;
	
	
	
	public Double getPeakMonthlySales() {
		return peakMonthlySales;
	}

	public void setPeakMonthlySales(Double peakMonthlySales) {
		this.peakMonthlySales = peakMonthlySales;
	}

	public Double getImPurchase1() {
		return imPurchase1;
	}

	public void setImPurchase1(Double imPurchase1) {
		this.imPurchase1 = imPurchase1;
	}

	public Double getImPurchase2() {
		return imPurchase2;
	}

	public void setImPurchase2(Double imPurchase2) {
		this.imPurchase2 = imPurchase2;
	}

	public Double getImGrowthPurchase() {
		return imGrowthPurchase;
	}

	public void setImGrowthPurchase(Double imGrowthPurchase) {
		this.imGrowthPurchase = imGrowthPurchase;
	}

	public Double getImProjectedSales() {
		return imProjectedSales;
	}

	public void setImProjectedSales(Double imProjectedSales) {
		this.imProjectedSales = imProjectedSales;
	}

	public Double getImHistoricalSales() {
		return imHistoricalSales;
	}

	public void setImHistoricalSales(Double imHistoricalSales) {
		this.imHistoricalSales = imHistoricalSales;
	}

	public Double getImGstinPreviousYrPurchase() {
		return imGstinPreviousYrPurchase;
	}

	public void setImGstinPreviousYrPurchase(Double imGstinPreviousYrPurchase) {
		this.imGstinPreviousYrPurchase = imGstinPreviousYrPurchase;
	}

	public Double getPanPurchase1() {
		return panPurchase1;
	}

	public void setPanPurchase1(Double panPurchase1) {
		this.panPurchase1 = panPurchase1;
	}

	public Double getPanPurchase2() {
		return panPurchase2;
	}

	public void setPanPurchase2(Double panPurchase2) {
		this.panPurchase2 = panPurchase2;
	}

	public Double getPanGrowthPurchase() {
		return panGrowthPurchase;
	}

	public void setPanGrowthPurchase(Double panGrowthPurchase) {
		this.panGrowthPurchase = panGrowthPurchase;
	}

	public Double getPanProjectedSales() {
		return panProjectedSales;
	}

	public void setPanProjectedSales(Double panProjectedSales) {
		this.panProjectedSales = panProjectedSales;
	}

	public Double getPanHistoricalSales() {
		return panHistoricalSales;
	}

	public void setPanHistoricalSales(Double panHistoricalSales) {
		this.panHistoricalSales = panHistoricalSales;
	}

	public Double getPanGstinPreviousYrPurchase() {
		return panGstinPreviousYrPurchase;
	}

	public void setPanGstinPreviousYrPurchase(Double panGstinPreviousYrPurchase) {
		this.panGstinPreviousYrPurchase = panGstinPreviousYrPurchase;
	}

	/**
	 * @return the gstType
	 */
	public Integer getGstType() {
		return gstType;
	}

	public Double getPreviousYearsales() {
		return previousYearsales;
	}

	public void setPreviousYearsales(Double previousYearsales) {
		this.previousYearsales = previousYearsales;
	}

	/**
	 * @param gstType the gstType to set
	 */
	public void setGstType(Integer gstType) {
		this.gstType = gstType;
	}

	/**
	 * @return the isHistoric
	 */
	public Boolean getIsHistoric() {
		return isHistoric;
	}

	/**
	 * @param isHistoric the isHistoric to set
	 */
	public void setIsHistoric(Boolean isHistoric) {
		this.isHistoric = isHistoric;
	}

	/**
	 * @return the growth
	 */
	public Double getGrowth() {
		return growth;
	}

	/**
	 * @param growth the growth to set
	 */
	public void setGrowth(Double growth) {
		this.growth = growth;
	}

	/**
	 * @return the sales1
	 */
	public Double getSales1() {
		return sales1;
	}

	/**
	 * @param sales1 the sales1 to set
	 */
	public void setSales1(Double sales1) {
		this.sales1 = sales1;
	}

	/**
	 * @return the sales2
	 */
	public Double getSales2() {
		return sales2;
	}

	/**
	 * @param sales2 the sales2 to set
	 */
	public void setSales2(Double sales2) {
		this.sales2 = sales2;
	}

	/**
	 * @return the historicalSales
	 */
	public Double getHistoricalSales() {
		return historicalSales;
	}

	/**
	 * @param historicalSales the historicalSales to set
	 */
	public void setHistoricalSales(Double historicalSales) {
		this.historicalSales = historicalSales;
	}

	/**
	 * @return the isNegativeGrowth
	 */
	public Boolean getIsNegativeGrowth() {
		return isNegativeGrowth;
	}

	/**
	 * @param isNegativeGrowth the isNegativeGrowth to set
	 */
	public void setIsNegativeGrowth(Boolean isNegativeGrowth) {
		this.isNegativeGrowth = isNegativeGrowth;
	}

	/**
	 * @return the noOfReturnPeriod
	 */
	public Double getNoOfReturnPeriod() {
		return noOfReturnPeriod;
	}

	/**
	 * @param noOfReturnPeriod the noOfReturnPeriod to set
	 */
	public void setNoOfReturnPeriod(Double noOfReturnPeriod) {
		this.noOfReturnPeriod = noOfReturnPeriod;
	}

	/**
	 * @return the projectedSales
	 */
	public Double getProjectedSales() {
		return projectedSales;
	}

	/**
	 * @param projectedSales the projectedSales to set
	 */
	public void setProjectedSales(Double projectedSales) {
		this.projectedSales = projectedSales;
	}

	/**
	 * @return the retPeriod
	 */
	public String getRetPeriod() {
		return retPeriod;
	}

	/**
	 * @param retPeriod the retPeriod to set
	 */
	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	/**
	 * @return the noOfCustomer
	 */
	public Double getNoOfCustomer() {
		return noOfCustomer;
	}

	/**
	 * @param noOfCustomer the noOfCustomer to set
	 */
	public void setNoOfCustomer(Double noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	/**
	 * @return the concentration
	 */
	public Double getConcentration() {
		return concentration;
	}

	/**
	 * @param concentration the concentration to set
	 */
	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GstCalculation [noOfCustomer=" + noOfCustomer + ", concentration=" + concentration + ", retPeriod="
				+ retPeriod + ", projectedSales=" + projectedSales + ", noOfReturnPeriod=" + noOfReturnPeriod + "]";
	}

	public GstCalculation() {
		this.noOfCustomer = 0.0;
		this.concentration = 0.0;
		this.retPeriod =null;
		this.projectedSales = 0.0;
		this.noOfReturnPeriod = 0.0;
	}
}
