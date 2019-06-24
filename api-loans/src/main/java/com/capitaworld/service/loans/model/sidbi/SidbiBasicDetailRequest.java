package com.capitaworld.service.loans.model.sidbi;

import java.io.Serializable;
import java.util.Date;

public class SidbiBasicDetailRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long clientId;
	
	private Long applicationId;
	
	private String organisationName;
	
	private Long constitutionId;
	
	private Long industryId;
	
	private String premiseNumber;
	
	private String streetName;
	
	private String landMark;
	
	private Long pincode;
	
	private String factoryAddress;
	
	private String existing;
	
	private String proposed;
	
	private String landlineNo;
	
	private String mobile;
	
	private String email;
	
	private Date establishmentDate;
	
	private Date commencementDate;
	
	private String msmeRegistrationNumber;
	
	private Date msmeRegistrationDate;
	
	private String aadhar;
	
	private String associatedGroup;
	
	private String sidbiBranch;
	
	private Integer exisitngActivity;
	
	private String activitySince;
	
	private String proposedActivity;
	
	private Date trialRunEndDate;
	
	private Boolean isCoveredUnderCGTMSE;
	
	private Integer repaymemtPeriod;
	
	public Long getId() {
		return id;
	}

	public Long getClientId() {
		return clientId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public String getOrganisationName() {
		return organisationName;
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

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public String getExisting() {
		return existing;
	}

	public String getProposed() {
		return proposed;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
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

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public void setExisting(String existing) {
		this.existing = existing;
	}

	public void setProposed(String proposed) {
		this.proposed = proposed;
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

	public Boolean getIsCoveredUnderCGTMSE() {
		return isCoveredUnderCGTMSE;
	}

	public void setIsCoveredUnderCGTMSE(Boolean isCoveredUnderCGTMSE) {
		this.isCoveredUnderCGTMSE = isCoveredUnderCGTMSE;
	}

	public Integer getRepaymemtPeriod() {
		return repaymemtPeriod;
	}

	public void setRepaymemtPeriod(Integer repaymemtPeriod) {
		this.repaymemtPeriod = repaymemtPeriod;
	}

	public Long getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Long constitutionId) {
		this.constitutionId = constitutionId;
	}
}
