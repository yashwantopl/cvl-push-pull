package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.gst.model.MonthListSales;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GSTNotApplicable implements Serializable {

	private static final long serialVersionUID = 1905122041950251207L;

	private String entityName;
	
//	@Convert(converter=CryptoConverter.class)
	private String pan;

	private String constitution;
	
	private Long applicationId;
	
	private Double highestSalestoOneCustomer;
	
	private Double noOfCustomer;
	
	private Map<Integer, Map<String, Double>> momSales;
	
	private List<MonthListSales> monthSalesDetails;
	
	private Double historicalSales;

	private Double concentration;
	
	private Date dob;
	
	private Long cityId;

	private Long stateId;
	
	private  Map<String, Object> momSalesBigDecimal;
	
	private Double totalPurchaseFromIm;	
	private Double projectedPurchaseFromIm;
	
	private Double highestSalesPerticularfromIM;
	
	private Date dateOfIncorporation;
	
	
	public GSTNotApplicable() {
		this.entityName="";
		this.pan = "";
		this.constitution = "";
		this.applicationId = 0L;
		this.highestSalestoOneCustomer=0.0;
		this.noOfCustomer = 0.0;
		this.momSales = new HashMap<>();
		this.historicalSales = 0.0;
		this.concentration = 0.0;
	}

	public Double getHighestSalesPerticularfromIM() {
		return highestSalesPerticularfromIM;
	}
	public void setHighestSalesPerticularfromIM(Double highestSalesPerticularfromIM) {
		this.highestSalesPerticularfromIM = highestSalesPerticularfromIM;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getHighestSalestoOneCustomer() {
		return highestSalestoOneCustomer;
	}

	public void setHighestSalestoOneCustomer(Double highestSalestoOneCustomer) {
		this.highestSalestoOneCustomer = highestSalestoOneCustomer;
	}

	public Double getNoOfCustomer() {
		return noOfCustomer;
	}

	public void setNoOfCustomer(Double noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	public Map<Integer, Map<String, Double>> getMomSales() {
		return momSales;
	}

	public void setMomSales(Map<Integer, Map<String, Double>> momSales) {
		this.momSales = momSales;
	}

	

	public Double getHistoricalSales() {
		return historicalSales;
	}

	public void setHistoricalSales(Double historicalSales) {
		this.historicalSales = historicalSales;
	}

	public Double getConcentration() {
		return concentration;
	}

	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Map<String, Object> getMomSalesBigDecimal() {
		return momSalesBigDecimal;
	}

	public void setMomSalesBigDecimal(Map<String, Object> momSalesBigDecimal) {
		this.momSalesBigDecimal = momSalesBigDecimal;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Double getTotalPurchaseFromIm() {
		return totalPurchaseFromIm;
	}

	public void setTotalPurchaseFromIm(Double totalPurchaseFromIm) {
		this.totalPurchaseFromIm = totalPurchaseFromIm;
	}

	public Double getProjectedPurchaseFromIm() {
		return projectedPurchaseFromIm;
	}

	public void setProjectedPurchaseFromIm(Double projectedPurchaseFromIm) {
		this.projectedPurchaseFromIm = projectedPurchaseFromIm;
	}

	public List<MonthListSales> getMonthSalesDetails() {
		return monthSalesDetails;
	}

	public void setMonthSalesDetails(List<MonthListSales> monthSalesDetails) {
		this.monthSalesDetails = monthSalesDetails;
	}

	public Date getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(Date dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}
	
	
	
}
