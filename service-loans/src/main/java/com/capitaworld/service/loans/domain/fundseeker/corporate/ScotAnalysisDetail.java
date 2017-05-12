package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_scot_analysis_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_scot_analysis_details")
@NamedQuery(name="ScotAnalysisDetail.findAll", query="SELECT s FROM ScotAnalysisDetail s")
public class ScotAnalysisDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Lob
	@Column(name="concerns_details")
	private String concernsDetails;

	@Lob
	@Column(name="concerns_measure")
	private String concernsMeasure;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Lob
	@Column(name="opportunities_detials")
	private String opportunitiesDetials;

	@Column(name="storage_details_id")
	private Long storageDetailsId;

	@Lob
	@Column(name="strength_details")
	private String strengthDetails;

	@Lob
	@Column(name="weakness_detials")
	private String weaknessDetials;

	@Lob
	@Column(name="weakness_measure")
	private String weaknessMeasure;

	public ScotAnalysisDetail() {
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

	public String getConcernsDetails() {
		return this.concernsDetails;
	}

	public void setConcernsDetails(String concernsDetails) {
		this.concernsDetails = concernsDetails;
	}

	public String getConcernsMeasure() {
		return this.concernsMeasure;
	}

	public void setConcernsMeasure(String concernsMeasure) {
		this.concernsMeasure = concernsMeasure;
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

	public String getOpportunitiesDetials() {
		return this.opportunitiesDetials;
	}

	public void setOpportunitiesDetials(String opportunitiesDetials) {
		this.opportunitiesDetials = opportunitiesDetials;
	}

	public Long getStorageDetailsId() {
		return this.storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public String getStrengthDetails() {
		return this.strengthDetails;
	}

	public void setStrengthDetails(String strengthDetails) {
		this.strengthDetails = strengthDetails;
	}

	public String getWeaknessDetials() {
		return this.weaknessDetials;
	}

	public void setWeaknessDetials(String weaknessDetials) {
		this.weaknessDetials = weaknessDetials;
	}

	public String getWeaknessMeasure() {
		return this.weaknessMeasure;
	}

	public void setWeaknessMeasure(String weaknessMeasure) {
		this.weaknessMeasure = weaknessMeasure;
	}

}