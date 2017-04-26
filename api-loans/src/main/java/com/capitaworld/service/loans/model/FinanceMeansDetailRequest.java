package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_finance_means_details database table.
 * 
 */
public class FinanceMeansDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double alreadyInfused;

	private Long applicationId;

	private Long financeMeansCategoryId;

	private Double toBeIncurred;

	private Double total;

	public FinanceMeansDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAlreadyInfused() {
		return this.alreadyInfused;
	}

	public void setAlreadyInfused(Double alreadyInfused) {
		this.alreadyInfused = alreadyInfused;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public Long getFinanceMeansCategoryId() {
		return this.financeMeansCategoryId;
	}

	public void setFinanceMeansCategoryId(Long financeMeansCategoryId) {
		this.financeMeansCategoryId = financeMeansCategoryId;
	}

	public Double getToBeIncurred() {
		return this.toBeIncurred;
	}

	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}