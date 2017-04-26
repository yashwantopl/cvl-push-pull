package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_finance_means_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_finance_means_details")
public class FinanceMeansDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="already_infused")
	private Double alreadyInfused;

	@Column(name="application_id")
	private Long applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="finance_means_category_id")
	private Long financeMeansCategoryId;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="to_be_incurred")
	private Double toBeIncurred;

	private Double total;

	public FinanceMeansDetail() {
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

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getFinanceMeansCategoryId() {
		return this.financeMeansCategoryId;
	}

	public void setFinanceMeansCategoryId(Long financeMeansCategoryId) {
		this.financeMeansCategoryId = financeMeansCategoryId;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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