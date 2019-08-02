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
	private Integer constitutionId;
	
	@Column(name="industry_name")
	private String industryName;
	
	@Column(name = "premise_number")
	private String premiseNumber;
	
	@Column(name = "street_name")
	private String streetName;
	
	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "pincode")
	private Long pincode;
	
	@Column(name = "factory_premise")
	private Integer factoryPremise;
	
	@Column(name = "exis_fact_premise_number")
	private String exisFactPremiseNumber;
	
	@Column(name = "exis_fact_street_name")
	private String exisFactStreetName;
	
	@Column(name = "exis_fact_land_mark")
	private String exisFactLandMark;
	
	@Column(name = "exis_fact_pincode")
	private Long exisFactPincode;
	
	@Column(name = "exis_factory_premise")
	private Integer exisFactoryPremise;
		
	@Column(name = "prop_fact_premise_number")
	private String propFactPremiseNumber;
	
	@Column(name = "prop_fact_street_name")
	private String propFactStreetName;
	
	@Column(name = "prop_fact_land_mark")
	private String propFactLandMark;
	
	@Column(name = "prop_fact_pincode")
	private Long propFactPincode;
	
	@Column(name = "prop_factory_premise")
	private Integer propFactoryPremise;
	
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
	
	@Column(name= "msme_uam_reg_no")
	private String msmeUamRegistrationNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "msme_uam_reg_date")
	private Date msmeUamRegistrationDate;
	
	@Column(name = "associated_group")
	private String associatedGroup;
	
	@Column(name = "sidbi_branch")
	private String sidbiBranch;
	
	@Column(name = "exisitng_activity")
	private String exisitngActivity;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "activity_since")
	private Date activitySince;
	
	@Column(name = "proposed_activity")
	private String proposedActivity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "trial_run_end_date")
	private Date trialRunEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commence_com_op_date")
	private Date dateOfCommencementOfCommercialOperations;
	
	@Column(name = "is_covered_under_cgtmse")
	private Boolean isCoveredUnderCGTMSE;

	@Column(name="repayment_months")
	private Integer repaymemtMonths;

	@Column(name="moratorium_period_months")
	private Integer moratoriumPeriodMonths;

	@Column(name="declaration_date")
	private Date declarationDate;

	@Column(name="declaration_place")
	private String declarationPlace;

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
	
	@Column(name="is_copy_of_existing_fact_addr")
	private Boolean isCopyOfExistingFactAddr;
    
	@Column(name="is_copy_of_registered_addr")
    private Boolean isCopyOfRegisteredAddr;
	
	@Column(name="activity_detail")
	private String activityDetail;
	
	@Column(name="all_amt_values_in")
	private Integer allAmountValuesIn;
	
	@Column(name="is_locked_values_in")
	private Boolean isLockedAllAmountValuesIn;
	

	public Long getId() {
		return id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public String getOrganisationName() {
		return organisationName;
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

	public String getAssociatedGroup() {
		return associatedGroup;
	}

	public String getSidbiBranch() {
		return sidbiBranch;
	}

	public String getExisitngActivity() {
		return exisitngActivity;
	}

	public Date getActivitySince() {
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

	public Integer getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
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
	
	public String getActivityDetail() {
		return activityDetail;
	}

	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}

	public void setAssociatedGroup(String associatedGroup) {
		this.associatedGroup = associatedGroup;
	}

	public void setSidbiBranch(String sidbiBranch) {
		this.sidbiBranch = sidbiBranch;
	}

	public void setExisitngActivity(String exisitngActivity) {
		this.exisitngActivity = exisitngActivity;
	}

	public void setActivitySince(Date activitySince) {
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

	public void setIsCoveredUnderCGTMSE(Boolean isCoveredUnderCGTMSE) {
		this.isCoveredUnderCGTMSE = isCoveredUnderCGTMSE;
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


	public Integer getRepaymemtMonths() {
		return repaymemtMonths;
	}

	public void setRepaymemtMonths(Integer repaymemtMonths) {
		this.repaymemtMonths = repaymemtMonths;
	}

	public String getMsmeUamRegistrationNumber() {
		return msmeUamRegistrationNumber;
	}

	public void setMsmeUamRegistrationNumber(String msmeUamRegistrationNumber) {
		this.msmeUamRegistrationNumber = msmeUamRegistrationNumber;
	}

	public Date getMsmeUamRegistrationDate() {
		return msmeUamRegistrationDate;
	}

	public void setMsmeUamRegistrationDate(Date msmeUamRegistrationDate) {
		this.msmeUamRegistrationDate = msmeUamRegistrationDate;
	}

	public Integer getMoratoriumPeriodMonths() {
		return moratoriumPeriodMonths;
	}

	public void setMoratoriumPeriodMonths(Integer moratoriumPeriodMonths) {
		this.moratoriumPeriodMonths = moratoriumPeriodMonths;
	}

	public Date getDeclarationDate() {
		return declarationDate;
	}

	public void setDeclarationDate(Date declarationDate) {
		this.declarationDate = declarationDate;
	}

	public String getDeclarationPlace() {
		return declarationPlace;
	}

	public void setDeclarationPlace(String declarationPlace) {
		this.declarationPlace = declarationPlace;
	}

	public Boolean getIsCopyOfExistingFactAddr() {
		return isCopyOfExistingFactAddr;
	}

	public void setIsCopyOfExistingFactAddr(Boolean isCopyOfExistingFactAddr) {
		this.isCopyOfExistingFactAddr = isCopyOfExistingFactAddr;
	}

	public Boolean getIsCopyOfRegisteredAddr() {
		return isCopyOfRegisteredAddr;
	}

	public void setIsCopyOfRegisteredAddr(Boolean isCopyOfRegisteredAddr) {
		this.isCopyOfRegisteredAddr = isCopyOfRegisteredAddr;
	}

	public Date getDateOfCommencementOfCommercialOperations() {
		return dateOfCommencementOfCommercialOperations;
	}

	public void setDateOfCommencementOfCommercialOperations(Date dateOfCommencementOfCommercialOperations) {
		this.dateOfCommencementOfCommercialOperations = dateOfCommencementOfCommercialOperations;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public Integer getFactoryPremise() {
		return factoryPremise;
	}

	public void setFactoryPremise(Integer factoryPremise) {
		this.factoryPremise = factoryPremise;
	}

	public Integer getExisFactoryPremise() {
		return exisFactoryPremise;
	}

	public void setExisFactoryPremise(Integer exisFactoryPremise) {
		this.exisFactoryPremise = exisFactoryPremise;
	}

	public Integer getPropFactoryPremise() {
		return propFactoryPremise;
	}

	public void setPropFactoryPremise(Integer propFactoryPremise) {
		this.propFactoryPremise = propFactoryPremise;
	}

	public Integer getAllAmountValuesIn() {
		return allAmountValuesIn;
	}

	public void setAllAmountValuesIn(Integer allAmountValuesIn) {
		this.allAmountValuesIn = allAmountValuesIn;
	}

	public Boolean getIsLockedAllAmountValuesIn() {
		return isLockedAllAmountValuesIn;
	}

	public void setIsLockedAllAmountValuesIn(Boolean isLockedAllAmountValuesIn) {
		this.isLockedAllAmountValuesIn = isLockedAllAmountValuesIn;
	}
	
}
