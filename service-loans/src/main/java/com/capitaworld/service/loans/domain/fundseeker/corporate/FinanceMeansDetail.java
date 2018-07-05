package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

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

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="finance_means_category_id")
	private Long financeMeansCategoryId;

	@Column(name="is_active")
	private Boolean isActive;

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

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
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

	@Override
	public String toString() {
		return "FinanceMeansDetail [id=" + id + ", alreadyInfused=" + alreadyInfused + ", applicationId="
				+ applicationId + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", financeMeansCategoryId=" + financeMeansCategoryId + ", isActive=" + isActive + ", modifiedBy="
				+ modifiedBy + ", modifiedDate=" + modifiedDate + ", toBeIncurred=" + toBeIncurred + ", total=" + total
				+ "]";
	}

}