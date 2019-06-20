package com.capitaworld.service.loans.domain.sidbi;

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
@Table(name="fs_sidbi_corporate_governance_compliance")
public class CorporateGovernanceCompliance implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="corporate_governance_id")
	private Integer corporateGovernanceId;
	
	@Column(name="selected_option")
	private Integer selectedOption;
	
	@Column(name="updated_value")
	private String updatedValue;

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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getCorporateGovernanceId() {
		return corporateGovernanceId;
	}

	public void setCorporateGovernanceId(Integer corporateGovernanceId) {
		this.corporateGovernanceId = corporateGovernanceId;
	}

	public Integer getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(Integer selectedOption) {
		this.selectedOption = selectedOption;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getUpdatedValue() {
		return updatedValue;
	}

	public void setUpdatedValue(String updatedValue) {
		this.updatedValue = updatedValue;
	}

	@Override
	public String toString() {
		return "CorporateGovernanceCompliance [id=" + id + ", applicationId=" + applicationId
				+ ", corporateGovernanceId=" + corporateGovernanceId + ", selectedOption=" + selectedOption
				+ ", updatedValue=" + updatedValue + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

}
