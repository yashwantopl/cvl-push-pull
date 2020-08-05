package com.opl.mudra.api.loans.model.teaser.primaryview;

import java.util.List;

import com.opl.mudra.api.loans.model.AddressResponse;

/**
 * @author Sanket
 *
 */
public class ProfileViewPLResponse {
	

	
	private String firstName;
	private String lastName;
	private String middleName;
	private String title;
	private AddressResponse permanentAddress;
	private AddressResponse officeAddress;
	private String dateOfProposal;
	private String purposeOfLoan;
	private String gender;
	private String pan;
	private String maritalStatus;
	private String natureOfOccupation;
	private String monthlyIncome;
	private String companyName;
	private String employeeWith;
	private List<Object> panCardList;
	private List<Object> aadharCardList;
	private String age;
	private String relationShipWithApplicant;
	
	
	
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public List<Object> getPanCardList() {
		return panCardList;
	}
	public void setPanCardList(List<Object> panCardList) {
		this.panCardList = panCardList;
	}
	public List<Object> getAadharCardList() {
		return aadharCardList;
	}
	public void setAadharCardList(List<Object> aadharCardList) {
		this.aadharCardList = aadharCardList;
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
	public AddressResponse getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(AddressResponse permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public AddressResponse getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(AddressResponse officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getDateOfProposal() {
		return dateOfProposal;
	}
	public void setDateOfProposal(String dateOfProposal) {
		this.dateOfProposal = dateOfProposal;
	}
	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}
	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNatureOfOccupation() {
		return natureOfOccupation;
	}
	public void setNatureOfOccupation(String natureOfOccupation) {
		this.natureOfOccupation = natureOfOccupation;
	}
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEmployeeWith() {
		return employeeWith;
	}
	public void setEmployeeWith(String employeeWith) {
		this.employeeWith = employeeWith;
	}
	public String getRelationShipWithApplicant() {
		return relationShipWithApplicant;
	}
	public void setRelationShipWithApplicant(String relationShipWithApplicant) {
		this.relationShipWithApplicant = relationShipWithApplicant;
	}
	
	
	
	
	
	
	
	


}
