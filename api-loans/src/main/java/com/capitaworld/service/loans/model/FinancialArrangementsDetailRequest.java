package com.capitaworld.service.loans.model;

/**
 * @author Sanket
 *
 */
public class FinancialArrangementsDetailRequest {
	
	private Long id;

	private Double amount;

	private Integer facilityNatureId;

	private String financialInstitutionName;

	private Boolean isActive = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getFacilityNatureId() {
		return facilityNatureId;
	}

	public void setFacilityNatureId(Integer facilityNatureId) {
		this.facilityNatureId = facilityNatureId;
	}

	public String getFinancialInstitutionName() {
		return financialInstitutionName;
	}

	public void setFinancialInstitutionName(String financialInstitutionName) {
		this.financialInstitutionName = financialInstitutionName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	

}
