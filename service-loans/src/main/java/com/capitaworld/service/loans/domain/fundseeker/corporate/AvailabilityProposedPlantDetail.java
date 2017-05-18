package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_availability_proposed_plant_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_availability_proposed_plant_details")
@NamedQuery(name="AvailabilityProposedPlantDetail.findAll", query="SELECT a FROM AvailabilityProposedPlantDetail a")
public class AvailabilityProposedPlantDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="description_p_m")
	private String descriptionPM;

	@Column(name="estimated_value")
	private String estimatedValue;

	@Column(name="imported_or_indigenous")
	private String importedOrIndigenous;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="storage_details_id")
	private Long storageDetailsId;

	private String supplier;

	@Column(name="use_or_purpose")
	private String useOrPurpose;

	public AvailabilityProposedPlantDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
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

	public String getDescriptionPM() {
		return this.descriptionPM;
	}

	public void setDescriptionPM(String descriptionPM) {
		this.descriptionPM = descriptionPM;
	}

	public String getEstimatedValue() {
		return this.estimatedValue;
	}

	public void setEstimatedValue(String estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	public String getImportedOrIndigenous() {
		return this.importedOrIndigenous;
	}

	public void setImportedOrIndigenous(String importedOrIndigenous) {
		this.importedOrIndigenous = importedOrIndigenous;
	}

	public Boolean getIsActive() {
		return this.isActive;
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

	public Long getStorageDetailsId() {
		return this.storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getUseOrPurpose() {
		return this.useOrPurpose;
	}

	public void setUseOrPurpose(String useOrPurpose) {
		this.useOrPurpose = useOrPurpose;
	}

}