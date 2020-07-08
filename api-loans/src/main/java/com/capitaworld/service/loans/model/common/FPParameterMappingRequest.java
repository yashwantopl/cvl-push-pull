package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
public class FPParameterMappingRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long fpProductMappingId;
	
	private Boolean isActive;
	
	private Integer type;
	
	private Integer parameterId;
	
	private Double amount;
	
	private String value1;
	
	private String value2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getFpProductMappingId() {
		return fpProductMappingId;
	}

	public void setFpProductMappingId(Long fpProductMappingId) {
		this.fpProductMappingId = fpProductMappingId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getParameterId() {
		return parameterId;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}
}