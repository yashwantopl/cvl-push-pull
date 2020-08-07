/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author nilay.darji
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gstr4PopUpResponse {
	
	private Long applicationId;
	
	private String gstin;
	
	private Long noOfCustomer;
	
	private Double highestSalestoOneCustomer;
	
	private Double projectedSales;
	
	private List<Gstr4QoqSales> data;

	public Long getNoOfCustomer() {
		return noOfCustomer;
	}

	public void setNoOfCustomer(Long noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	public List<Gstr4QoqSales> getData() {
		return data;
	}

	public void setData(List<Gstr4QoqSales> data) {
		this.data = data;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Double getHighestSalestoOneCustomer() {
		return highestSalestoOneCustomer;
	}

	public void setHighestSalestoOneCustomer(Double highestSalestoOneCustomer) {
		this.highestSalestoOneCustomer = highestSalestoOneCustomer;
	}

	public Double getProjectedSales() {
		return projectedSales;
	}

	public void setProjectedSales(Double projectedSales) {
		this.projectedSales = projectedSales;
	}
	
}
