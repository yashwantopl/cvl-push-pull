package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceMeansDetailResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private Double alreadyInfused;

	private String financeMeansCategory;

	private Double toBeIncurred;

	private Double total;

	public Double getAlreadyInfused() {
		return alreadyInfused;
	}

	public void setAlreadyInfused(Double alreadyInfused) {
		this.alreadyInfused = alreadyInfused;
	}

	public String getFinanceMeansCategory() {
		return financeMeansCategory;
	}

	public void setFinanceMeansCategory(String financeMeansCategory) {
		this.financeMeansCategory = financeMeansCategory;
	}

	public Double getToBeIncurred() {
		return toBeIncurred;
	}

	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
