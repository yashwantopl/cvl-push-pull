package com.opl.mudra.api.gst.model.model;

public class ProjectedSalesCalcResponse {
	
	private Double growth;
	
	private Double sales1;
	
	private Double sales2;
	
	private Double projectedSales;
	
	private Boolean isNegativeGrowth;

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
	
	

}
