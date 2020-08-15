package com.opl.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * 
 * @author rohit.chaudhary
 *
 */
@Entity
@Table(name = "fs_retail_pre_sanction_visit")
public class PreSanctionVisit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@ManyToOne
	@JoinColumn(name = "proposal_mapping_id")
	private ApplicationProposalMapping applicationProposalMapping;

	@ManyToOne
	@JoinColumn(name = "co_applicant_detail_id")
	private CoApplicantDetail coApplicantDetailId;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "visit_date")
	private Date visitDate;

	@Column(name = "visit_name")
	private String visitName;

	@Column(name = "visit_designation")
	private String visitDesignation;

	@Column(name = "is_visited")
	private Boolean isVisited;

	@Column(name = "premise_no")
	private String premiseNo;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "country_id")
	private Long countryId;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@Column(name = "contacted_person_name")
	private String contactedPersonName;

	@Column(name = "applicant_impression")
	private String applicantImpression;

	@Column(name = "officer_observations")
	private String officerObservations;

	@Column(name = "is_applicant_official")
	private Boolean isApplicantOfficial;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_date")
	private Date modifiedDate;

	public PreSanctionVisit() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}

	public CoApplicantDetail getCoApplicantDetailId() {
		return coApplicantDetailId;
	}

	public void setCoApplicantDetailId(CoApplicantDetail coApplicantDetailId) {
		this.coApplicantDetailId = coApplicantDetailId;
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

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public String getVisitDesignation() {
		return visitDesignation;
	}

	public void setVisitDesignation(String visitDesignation) {
		this.visitDesignation = visitDesignation;
	}

	public Boolean getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getPremiseNo() {
		return premiseNo;
	}

	public void setPremiseNo(String premiseNo) {
		this.premiseNo = premiseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getApplicantImpression() {
		return applicantImpression;
	}

	public void setApplicantImpression(String applicantImpression) {
		this.applicantImpression = applicantImpression;
	}

	public String getOfficerObservations() {
		return officerObservations;
	}

	public void setOfficerObservations(String officerObservations) {
		this.officerObservations = officerObservations;
	}

	public Boolean getIsApplicantOfficial() {
		return isApplicantOfficial;
	}

	public void setIsApplicantOfficial(Boolean isApplicantOfficial) {
		this.isApplicantOfficial = isApplicantOfficial;
	}

	public String getContactedPersonName() {
		return contactedPersonName;
	}

	public void setContactedPersonName(String contactedPersonName) {
		this.contactedPersonName = contactedPersonName;
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

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	


}
