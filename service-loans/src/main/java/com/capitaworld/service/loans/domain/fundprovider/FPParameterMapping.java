package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the fp_home_loan_details database table.
 * 
 */
@Entity
@Table(name = "fp_parameter_mapping")
public class FPParameterMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fp_product_mapping_id")
	private Long fpProductMappingId;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	private Integer type;
	
	@Column(name = "parameter_id")
	private Integer parameterId;

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
}