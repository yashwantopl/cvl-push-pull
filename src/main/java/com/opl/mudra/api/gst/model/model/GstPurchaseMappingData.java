package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class GstPurchaseMappingData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	Long applicationId;
	
	private Boolean isActive;
	
	private Long createdBy;

	private Timestamp createdDate;

	private Long modifiedBy;

	private Timestamp modifiedDate;
	
	private String gstin;

	private String imGstin;
	
	private Double noCustomer;

	private Double customerConentration;
	
	private String retPeriod;
	
	private Double noOfReturnPeriod;

	private String momSales;
	
	private Double previousYearpurchases;	
	
	private Double purchase1;	
	private Double purchase2;
	
	private Double projectedPurchase;
	
	private Double historicalPurchase;
	

	private Boolean isHistoric;
	
	private Boolean isNegativeGrowth;
	
	private Double growth;
	
	private Boolean isComposite;
	
	private String growthSalesData;
	
	String pan;
	
	private Double gstr3bPreviousYrSales;
	
	private Double imPurchase1;	
	
	private Double imPurchase2;
	
	private Double imGrowthPurchase;	

	private Double imProjectedPurchase;
	
	private Double imHistoricalPurchase;
	
	private Double imGstinPreviousYrPurchase;
	
	private Double panPurchase1;
	
	private Double panPurchase2;
	
	private Double panGrowthPurchase;	

	private Double panProjectedPurchase;
	
	private Double panHistoricalPurchase;
	
	private Double panGstinPreviousYrPurchase;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getImGstin() {
		return imGstin;
	}

	public void setImGstin(String imGstin) {
		this.imGstin = imGstin;
	}

	public Double getNoCustomer() {
		return noCustomer;
	}

	public void setNoCustomer(Double noCustomer) {
		this.noCustomer = noCustomer;
	}

	public Double getCustomerConentration() {
		return customerConentration;
	}

	public void setCustomerConentration(Double customerConentration) {
		this.customerConentration = customerConentration;
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public Double getNoOfReturnPeriod() {
		return noOfReturnPeriod;
	}

	public void setNoOfReturnPeriod(Double noOfReturnPeriod) {
		this.noOfReturnPeriod = noOfReturnPeriod;
	}

	public String getMomSales() {
		return momSales;
	}

	public void setMomSales(String momSales) {
		this.momSales = momSales;
	}

	public Double getPreviousYearpurchases() {
		return previousYearpurchases;
	}

	public void setPreviousYearpurchases(Double previousYearpurchases) {
		this.previousYearpurchases = previousYearpurchases;
	}

	public Double getPurchase1() {
		return purchase1;
	}

	public void setPurchase1(Double purchase1) {
		this.purchase1 = purchase1;
	}

	public Double getPurchase2() {
		return purchase2;
	}

	public void setPurchase2(Double purchase2) {
		this.purchase2 = purchase2;
	}

	public Double getProjectedPurchase() {
		return projectedPurchase;
	}

	public void setProjectedPurchase(Double projectedPurchase) {
		this.projectedPurchase = projectedPurchase;
	}

	public Double getHistoricalPurchase() {
		return historicalPurchase;
	}

	public void setHistoricalPurchase(Double historicalPurchase) {
		this.historicalPurchase = historicalPurchase;
	}

	public Boolean getIsHistoric() {
		return isHistoric;
	}

	public void setIsHistoric(Boolean isHistoric) {
		this.isHistoric = isHistoric;
	}

	public Boolean getIsNegativeGrowth() {
		return isNegativeGrowth;
	}

	public void setIsNegativeGrowth(Boolean isNegativeGrowth) {
		this.isNegativeGrowth = isNegativeGrowth;
	}

	public Double getGrowth() {
		return growth;
	}

	public void setGrowth(Double growth) {
		this.growth = growth;
	}

	public Boolean getIsComposite() {
		return isComposite;
	}

	public void setIsComposite(Boolean isComposite) {
		this.isComposite = isComposite;
	}

	public String getGrowthSalesData() {
		return growthSalesData;
	}

	public void setGrowthSalesData(String growthSalesData) {
		this.growthSalesData = growthSalesData;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getGstr3bPreviousYrSales() {
		return gstr3bPreviousYrSales;
	}

	public void setGstr3bPreviousYrSales(Double gstr3bPreviousYrSales) {
		this.gstr3bPreviousYrSales = gstr3bPreviousYrSales;
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

	public Double getImProjectedPurchase() {
		return imProjectedPurchase;
	}

	public void setImProjectedPurchase(Double imProjectedPurchase) {
		this.imProjectedPurchase = imProjectedPurchase;
	}

	public Double getImHistoricalPurchase() {
		return imHistoricalPurchase;
	}

	public void setImHistoricalPurchase(Double imHistoricalPurchase) {
		this.imHistoricalPurchase = imHistoricalPurchase;
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

	public Double getPanProjectedPurchase() {
		return panProjectedPurchase;
	}

	public void setPanProjectedPurchase(Double panProjectedPurchase) {
		this.panProjectedPurchase = panProjectedPurchase;
	}

	public Double getPanHistoricalPurchase() {
		return panHistoricalPurchase;
	}

	public void setPanHistoricalPurchase(Double panHistoricalPurchase) {
		this.panHistoricalPurchase = panHistoricalPurchase;
	}

	public Double getPanGstinPreviousYrPurchase() {
		return panGstinPreviousYrPurchase;
	}

	public void setPanGstinPreviousYrPurchase(Double panGstinPreviousYrPurchase) {
		this.panGstinPreviousYrPurchase = panGstinPreviousYrPurchase;
	}
	
	

}