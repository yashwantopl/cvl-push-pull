/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
public class GstPurchaseCalculation implements Serializable{
	
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

	public GstPurchaseCalculation() {
		this.noOfCustomer = 0.0;
		this.concentration = 0.0;
		this.retPeriod =null;
		this.projectedSales = 0.0;
		this.noOfReturnPeriod = 0.0;
	}
}
