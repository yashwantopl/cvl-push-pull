package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
public class OrganisationBranchData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String branchAddress;

	private String branchEmail;

	private String branchName;

	private String branchPincode;

	private String contactPerson;

	private Boolean isActive;

	private Long organisationId;
	
	private String branchPhoneNumber;
	
	private String branchCity;
	
	
	

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchPhoneNumber() {
		return branchPhoneNumber;
	}

	public void setBranchPhoneNumber(String branchPhoneNumber) {
		this.branchPhoneNumber = branchPhoneNumber;
	}

	public Long getId() {
		return id;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public String getBranchEmail() {
		return branchEmail;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getBranchPincode() {
		return branchPincode;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public Long getOrganisationId() {
		return organisationId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public void setBranchEmail(String branchEmail) {
		this.branchEmail = branchEmail;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setBranchPincode(String branchPincode) {
		this.branchPincode = branchPincode;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setOrganisationId(Long organisationId) {
		this.organisationId = organisationId;
	}
	
	
	

}
