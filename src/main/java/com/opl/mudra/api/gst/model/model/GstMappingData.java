package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GstMappingData implements Serializable{




	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GstMappingData [id=" + id + ", status=" + status + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", gstin=" + gstin + ", isActive=" + isActive + ", isComposite=" + isComposite
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", noCustomer=" + noCustomer
				+ ", customerConentration=" + customerConentration + ", retPeriod=" + retPeriod + ", projectedSales="
				+ projectedSales + ", noOfReturnPeriod=" + noOfReturnPeriod + ", momSales=" + momSales + ", growth="
				+ growth + ", sales1=" + sales1 + ", sales2=" + sales2 + ", historicalSales=" + historicalSales
				+ ", isNegativeGrowth=" + isNegativeGrowth + ", growthSalesData=" + growthSalesData + "]";
	}

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long status;

	private Long createdBy;

	private Timestamp createdDate;

	private String gstin;

	private Boolean isActive;
	
	private Boolean isComposite;

	private Long modifiedBy;

	private Timestamp modifiedDate;
	
	private Double noCustomer;
	
	private Double customerConentration;
	
	private String retPeriod;
	
	
	private Double projectedSales;
	
	private Double noOfReturnPeriod;
	
	private String momSales;
	

	private Double growth;
	
	private Double sales1;
	
	private Double sales2;
	
	private Double historicalSales;
	
	private Boolean isNegativeGrowth;
	
	private String growthSalesData;
	
	private DataMapping dataMapping;
	private Double previousYearsales;
	
	private Boolean isHistoric;
	
	
	private Double imGstinPreviousYrSales;
	
	private Double gstr3bPreviousYrSales;
	

	
	
	public Double getImGstinPreviousYrSales() {
		return imGstinPreviousYrSales;
	}

	public void setImGstinPreviousYrSales(Double imGstinPreviousYrSales) {
		this.imGstinPreviousYrSales = imGstinPreviousYrSales;
	}

	public Double getGstr3bPreviousYrSales() {
		return gstr3bPreviousYrSales;
	}

	public void setGstr3bPreviousYrSales(Double gstr3bPreviousYrSales) {
		this.gstr3bPreviousYrSales = gstr3bPreviousYrSales;
	}

	public Boolean getIsHistoric() {
		return isHistoric;
	}

	public void setIsHistoric(Boolean isHistoric) {
		this.isHistoric = isHistoric;
	}

	/**
	 * @return the dataMapping
	 */
	public DataMapping getDataMapping() {
		return dataMapping;
	}

	/**
	 * @param dataMapping the dataMapping to set
	 */
	public void setDataMapping(DataMapping dataMapping) {
		this.dataMapping = dataMapping;
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
	 * @return the growthSalesData
	 */
	public String getGrowthSalesData() {
		return growthSalesData;
	}

	/**
	 * @param growthSalesData the growthSalesData to set
	 */
	public void setGrowthSalesData(String growthSalesData) {
		this.growthSalesData = growthSalesData;
	}

	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isComposite
	 */
	public Boolean getIsComposite() {
		return isComposite;
	}

	/**
	 * @param isComposite the isComposite to set
	 */
	public void setIsComposite(Boolean isComposite) {
		this.isComposite = isComposite;
	}

	/**
	 * @return the modifiedBy
	 */
	public Long getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the modifiedDate
	 */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the noCustomer
	 */
	public Double getNoCustomer() {
		return noCustomer;
	}

	/**
	 * @param noCustomer the noCustomer to set
	 */
	public void setNoCustomer(Double noCustomer) {
		this.noCustomer = noCustomer;
	}

	/**
	 * @return the customerConentration
	 */
	public Double getCustomerConentration() {
		return customerConentration;
	}

	/**
	 * @param customerConentration the customerConentration to set
	 */
	public void setCustomerConentration(Double customerConentration) {
		this.customerConentration = customerConentration;
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
	 * @return the momSales
	 */
	public String getMomSales() {
		return momSales;
	}

	/**
	 * @param momSales the momSales to set
	 */
	public void setMomSales(String momSales) {
		this.momSales = momSales;
	}

	public Double getPreviousYearsales() {
		return previousYearsales;
	}

	public void setPreviousYearsales(Double previousYearsales) {
		this.previousYearsales = previousYearsales;
	}
	


	
	
}