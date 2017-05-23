package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalCostOfProjectResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Double toBeIncurred;

	private Double alreadyIncurred;

	private Double totalCost;

	private String particulars;

	public Double getToBeIncurred() {
		return toBeIncurred;
	}

	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}

	public Double getAlreadyIncurred() {
		return alreadyIncurred;
	}

	public void setAlreadyIncurred(Double alreadyIncurred) {
		this.alreadyIncurred = alreadyIncurred;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}
	
}
