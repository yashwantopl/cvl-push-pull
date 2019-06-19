package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.Address;

public class CorporateAdditionalDetailRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long clientId;
	private String organisationName;
	private Long constitutionId;
	private Long keyVericalFunding;
	private Address firstAddress;
	private String factoryAddress;
	private String existing;
	private String proposed;
	private String landlineNo;
	private String mobile;
	private String email;
	private Date establishmentDate;
	private Date commencementDate;
	private String msmeRegistrationNum;
	private Date msmeRegistrationDate;
	private String aadhar;
	private String associatedGroup;
	private String sidbiBranch;
	private Integer exisitngActivity;
	private String activitySince;
	private String proposedActivity;
	
	
	public Long getApplicationId() {
		return applicationId;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public Long getConstitutionId() {
		return constitutionId;
	}
	public Long getKeyVericalFunding() {
		return keyVericalFunding;
	}
	public Address getFirstAddress() {
		return firstAddress;
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
	public String getMsmeRegistrationNum() {
		return msmeRegistrationNum;
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
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public void setConstitutionId(Long constitutionId) {
		this.constitutionId = constitutionId;
	}
	public void setKeyVericalFunding(Long keyVericalFunding) {
		this.keyVericalFunding = keyVericalFunding;
	}
	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
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
	public void setMsmeRegistrationNum(String msmeRegistrationNum) {
		this.msmeRegistrationNum = msmeRegistrationNum;
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
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	

	
}
