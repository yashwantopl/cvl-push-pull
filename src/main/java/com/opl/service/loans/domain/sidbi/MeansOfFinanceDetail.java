package com.opl.service.loans.domain.sidbi;

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
@Table(name="fs_corporate_sidbi_means_of_finance_details")
public class MeansOfFinanceDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="particulars_id")
	private Integer particularsId;
	
	@Column(name="already_incurred")
	private Double alreadyIncurred;
	
	@Column(name="to_be_incurred")
	private Double toBeIncurred;
	
	@Column(name="total_cost")
	private Double totalCost;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public Integer getParticularsId() {
		return particularsId;
	}

	public Double getAlreadyIncurred() {
		return alreadyIncurred;
	}

	public Double getToBeIncurred() {
		return toBeIncurred;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
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

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setParticularsId(Integer particularsId) {
		this.particularsId = particularsId;
	}

	public void setAlreadyIncurred(Double alreadyIncurred) {
		this.alreadyIncurred = alreadyIncurred;
	}

	public void setToBeIncurred(Double toBeIncurred) {
		this.toBeIncurred = toBeIncurred;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
}
