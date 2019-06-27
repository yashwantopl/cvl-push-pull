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
@Table(name="fs_corporate_sidbi_basic_details")
public class SidbiBasicDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="organisation_name")
	private String organisationName;
	
	@Column(name="constitution_id")
	private Long constitutionId;
	
	@Column(name="industry_id")
	private Long industryId;
	
	@Column(name = "premise_number")
	private String premiseNumber;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "pincode")
	private Long pincode;
	
	@Column(name = "exis_fact_premise_number")
	private String exisFactPremiseNumber;
	
	@Column(name = "exis_fact_street_name")
	private String exisFactStreetName;
	
	@Column(name = "exis_fact_land_mark")
	private String exisFactLandMark;
	
	@Column(name = "exis_fact_pincode")
	private Long exisFactPincode;
	
	@Column(name="exis_fact_Owned")
	private Boolean exisFactOwned;
	
	@Column(name="exis_fact_ranted")
	private Boolean exisFactRanted;
	
	@Column(name="exis_fact_leased")
	private Boolean exisFactLeased;
	
	@Column(name = "prop_fact_premise_number")
	private String propFactPremiseNumber;
	
	@Column(name = "prop_fact_street_name")
	private String propFactStreetName;
	
	@Column(name = "prop_fact_land_mark")
	private String propFactLandMark;
	
	@Column(name = "prop_fact_pincode")
	private Long propFactPincode;
	
	@Column(name="prop_fact_Owned")
	private Boolean propFactOwned;
	
	@Column(name="prop_fact_ranted")
	private Boolean propFactRanted;
	
	@Column(name="prop_fact_leased")
	private Boolean propFactLeased;
		
	@Column(name = "landline_no")
	private String landlineNo;
	
	private String mobile;
	
	private String email;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "establishment_date")
	private Date establishmentDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commencement_date")
	private Date commencementDate;
	
	@Column(name= "msme_registration_number")
	private String msmeRegistrationNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "msme_registration_date")
	private Date msmeRegistrationDate;
	
	@Column(name = "aadhar")
	private String aadhar;
	
	@Column(name = "associated_group")
	private String associatedGroup;
	
	@Column(name = "sidbi_branch")
	private String sidbiBranch;
	
	@Column(name = "exisitng_activity")
	private Integer exisitngActivity;
	
	@Column(name = "activity_since")
	private String activitySince;
	
	@Column(name = "proposed_activity")
	private String proposedActivity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "trial_run_end_date")
	private Date trialRunEndDate;
	
	@Column(name = "is_covered_under_cgtmse")
	private Boolean isCoveredUnderCGTMSE;
	
	@Column(name = "repaymemt_period")
	private Integer repaymemtPeriod;
	
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

	public String getOrganisationName() {
		return organisationName;
	}

	public Long getConstitutionId() {
		return constitutionId;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getLandMark() {
		return landMark;
	}

	public Long getPincode() {
		return pincode;
	}

	

	public String getLandlineNo() {
		return landlineNo;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public Date getCommencementDate() {
		return commencementDate;
	}

	public String getMsmeRegistrationNumber() {
		return msmeRegistrationNumber;
	}

	public Date getMsmeRegistrationDate() {
		return msmeRegistrationDate;
	}

	public String getAadhar() {
		return aadhar;
	}

	public String getAssociatedGroup() {
		return associatedGroup;
	}

	public String getSidbiBranch() {
		return sidbiBranch;
	}

	public Integer getExisitngActivity() {
		return exisitngActivity;
	}

	public String getActivitySince() {
		return activitySince;
	}

	public String getProposedActivity() {
		return proposedActivity;
	}

	public Date getTrialRunEndDate() {
		return trialRunEndDate;
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

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public void setConstitutionId(Long constitutionId) {
		this.constitutionId = constitutionId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}

	public void setMsmeRegistrationNumber(String msmeRegistrationNumber) {
		this.msmeRegistrationNumber = msmeRegistrationNumber;
	}

	public void setMsmeRegistrationDate(Date msmeRegistrationDate) {
		this.msmeRegistrationDate = msmeRegistrationDate;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public void setAssociatedGroup(String associatedGroup) {
		this.associatedGroup = associatedGroup;
	}

	public void setSidbiBranch(String sidbiBranch) {
		this.sidbiBranch = sidbiBranch;
	}

	public void setExisitngActivity(Integer exisitngActivity) {
		this.exisitngActivity = exisitngActivity;
	}

	public void setActivitySince(String activitySince) {
		this.activitySince = activitySince;
	}

	public void setProposedActivity(String proposedActivity) {
		this.proposedActivity = proposedActivity;
	}

	public void setTrialRunEndDate(Date trialRunEndDate) {
		this.trialRunEndDate = trialRunEndDate;
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

	public Boolean getIsCoveredUnderCGTMSE() {
		return isCoveredUnderCGTMSE;
	}

	public Integer getRepaymemtPeriod() {
		return repaymemtPeriod;
	}

	public void setIsCoveredUnderCGTMSE(Boolean isCoveredUnderCGTMSE) {
		this.isCoveredUnderCGTMSE = isCoveredUnderCGTMSE;
	}

	public void setRepaymemtPeriod(Integer repaymemtPeriod) {
		this.repaymemtPeriod = repaymemtPeriod;
	}

	public String getExisFactPremiseNumber() {
		return exisFactPremiseNumber;
	}

	public void setExisFactPremiseNumber(String exisFactPremiseNumber) {
		this.exisFactPremiseNumber = exisFactPremiseNumber;
	}

	public String getExisFactStreetName() {
		return exisFactStreetName;
	}

	public void setExisFactStreetName(String exisFactStreetName) {
		this.exisFactStreetName = exisFactStreetName;
	}

	public String getExisFactLandMark() {
		return exisFactLandMark;
	}

	public void setExisFactLandMark(String exisFactLandMark) {
		this.exisFactLandMark = exisFactLandMark;
	}

	public Long getExisFactPincode() {
		return exisFactPincode;
	}

	public void setExisFactPincode(Long exisFactPincode) {
		this.exisFactPincode = exisFactPincode;
	}

	public Boolean getExisFactOwned() {
		return exisFactOwned;
	}

	public void setExisFactOwned(Boolean exisFactOwned) {
		this.exisFactOwned = exisFactOwned;
	}

	public Boolean getExisFactRanted() {
		return exisFactRanted;
	}

	public void setExisFactRanted(Boolean exisFactRanted) {
		this.exisFactRanted = exisFactRanted;
	}

	public Boolean getExisFactLeased() {
		return exisFactLeased;
	}

	public void setExisFactLeased(Boolean exisFactLeased) {
		this.exisFactLeased = exisFactLeased;
	}

	public String getPropFactPremiseNumber() {
		return propFactPremiseNumber;
	}

	public void setPropFactPremiseNumber(String propFactPremiseNumber) {
		this.propFactPremiseNumber = propFactPremiseNumber;
	}

	public String getPropFactStreetName() {
		return propFactStreetName;
	}

	public void setPropFactStreetName(String propFactStreetName) {
		this.propFactStreetName = propFactStreetName;
	}

	public String getPropFactLandMark() {
		return propFactLandMark;
	}

	public void setPropFactLandMark(String propFactLandMark) {
		this.propFactLandMark = propFactLandMark;
	}

	public Long getPropFactPincode() {
		return propFactPincode;
	}

	public void setPropFactPincode(Long propFactPincode) {
		this.propFactPincode = propFactPincode;
	}

	public Boolean getPropFactOwned() {
		return propFactOwned;
	}

	public void setPropFactOwned(Boolean propFactOwned) {
		this.propFactOwned = propFactOwned;
	}

	public Boolean getPropFactRanted() {
		return propFactRanted;
	}

	public void setPropFactRanted(Boolean propFactRanted) {
		this.propFactRanted = propFactRanted;
	}

	public Boolean getPropFactLeased() {
		return propFactLeased;
	}

	public void setPropFactLeased(Boolean propFactLeased) {
		this.propFactLeased = propFactLeased;
	}

	
}
