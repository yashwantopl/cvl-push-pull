package com.capitaworld.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fs_retail_emi_nmi_mapping")
public class EmiNmiMapping implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fp_product_id")
	private Long fpProductId;
	
	@Column(name = "min_income")
	private Double minIncome;
	
	@Column(name = "max_income")
	private Double maxIncome;
	
	@Column(name = "emi_nmi")
	private Float emiNmi;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;
	

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

	public Long getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
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

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	


}
