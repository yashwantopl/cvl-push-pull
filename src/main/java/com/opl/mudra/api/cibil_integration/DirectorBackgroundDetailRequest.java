package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_promotor_background_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailRequest implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Address address;
	
	private Long applicationId;

	private Integer salutationId;
	
	private String panNo;

	private String directorsName;

	private Boolean isActive = true;
	
	private String stateCode;
	
	private Date dob;
	
	private String dobString;

	private String mobile;

	private Integer gender;

	private String firstName;

	private String lastName;

	private String middleName;

	private String title;
	
	private String email;

	private String xmlResponseExternal;
	private boolean isNbfcUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Integer getSalutationId() {
		return salutationId;
	}
	public void setSalutationId(Integer salutationId) {
		this.salutationId = salutationId;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getDirectorsName() {
		return directorsName;
	}
	public void setDirectorsName(String directorsName) {
		this.directorsName = directorsName;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getDobString() {
		return dobString;
	}
	public void setDobString(String dobString) {
		this.dobString = dobString;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getXmlResponseExternal() {
		return xmlResponseExternal;
	}
	public void setXmlResponseExternal(String xmlResponseExternal) {
		this.xmlResponseExternal = xmlResponseExternal;
	}
	public boolean isNbfcUser() {
		return isNbfcUser;
	}
	public void setNbfcUser(boolean isNbfcUser) {
		this.isNbfcUser = isNbfcUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}