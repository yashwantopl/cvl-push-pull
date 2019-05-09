package com.capitaworld.service.loans.model.retail;

public class EmiNmiDetailRequest {

	private Long id;

	private Long fpProductId;
	
	private Double minIncome;
	
	private Double maxIncome;
	
	private Float emiNmi;

	

	public Long getId() {
		return id;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public Double getMinIncome() {
		return minIncome;
	}

	public Double getMaxIncome() {
		return maxIncome;
	}

	public Float getEmiNmi() {
		return emiNmi;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public void setMinIncome(Double minIncome) {
		this.minIncome = minIncome;
	}

	public void setMaxIncome(Double maxIncome) {
		this.maxIncome = maxIncome;
	}

	public void setEmiNmi(Float emiNmi) {
		this.emiNmi = emiNmi;
	}

	
}
