package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinanceMeansDetailRequest extends AuditorRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double alreadyInfused;

	private Long applicationId;

	private Long financeMeansCategoryId;
	
	private String financeMeansCategory;
	
	private Double toBeIncurred;

	private Double total;
	
	private Boolean isActive = true;

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

	public String getFinanceMeansCategory() {
		return financeMeansCategory;
	}

	public void setFinanceMeansCategory(String financeMeansCategory) {
		this.financeMeansCategory = financeMeansCategory;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "FinanceMeansDetailRequest [id=" + id + ", alreadyInfused=" + alreadyInfused + ", applicationId="
				+ applicationId + ", financeMeansCategoryId=" + financeMeansCategoryId + ", financeMeansCategory="
				+ financeMeansCategory + ", toBeIncurred=" + toBeIncurred + ", total=" + total + ", isActive="
				+ isActive + "]";
	}

}