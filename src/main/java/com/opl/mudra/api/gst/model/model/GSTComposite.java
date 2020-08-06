package com.opl.mudra.api.gst.model.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.gst.model.Gstr4QoqSales;
import com.opl.mudra.api.gst.model.MonthListSales;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GSTComposite {

	private String gstin;
	
	private Long applicationId;
	
	private Double projectedSales;
	
	private Double noOfCustomer;
	
	private Double concentration;
	
	private Map<Integer, Map<String, Double>> momSales;
	
	private List<MonthListSales> monthSalesDetails;
	
	private BigDecimal highSales;
	
	private List<Gstr4QoqSales> data;

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getProjectedSales() {
		return projectedSales;
	}

	public void setProjectedSales(Double projectedSales) {
		this.projectedSales = projectedSales;
	}

	public Double getNoOfCustomer() {
		return noOfCustomer;
	}

	public void setNoOfCustomer(Double noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	public Double getConcentration() {
		return concentration;
	}

	public void setConcentration(Double concentration) {
		this.concentration = concentration;
	}

	public Map<Integer, Map<String, Double>> getMomSales() {
		return momSales;
	}

	public void setMomSales(Map<Integer, Map<String, Double>> momSales) {
		this.momSales = momSales;
	}

	public GSTComposite() {

		this.gstin = "";
		this.applicationId = 0L;
		this.projectedSales = 0.0;
		this.noOfCustomer = 0.0;
		this.concentration = 0.0;
		this.momSales = new HashMap<>();
	}

	public BigDecimal getHighSales() {
		return highSales;
	}

	public void setHighSales(BigDecimal highSales) {
		this.highSales = highSales;
	}

	public List<Gstr4QoqSales> getData() {
		return data;
	}

	public void setData(List<Gstr4QoqSales> data) {
		this.data = data;
	}

	public List<MonthListSales> getMonthSalesDetails() {
		return monthSalesDetails;
	}

	public void setMonthSalesDetails(List<MonthListSales> monthSalesDetails) {
		this.monthSalesDetails = monthSalesDetails;
	}
	
	
}
