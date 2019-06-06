package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fs_retail_final_home_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalHomeLoanDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long applicationId;
	private Long proposalId;
	private String name;
	private String fatherFullName;
	private String matherMaidenName;
	private String nameOfSpouse;
	private Integer cast;
	private Integer religion;
	private String placeOfBirth;
	private Integer noOfChildren;
	private Address permanentAddress;
	private Address correspondenceAddress;

	private String user	;
	private String educationalQualification;
	private Integer year;
	private Integer employeeType;
	private Date createdDate;
	private Date modifiedDate;
	private Boolean isActive;
	List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsList;
	List<FixedDepositsDetailsRequest> fixedDepositsDetailsList;
	List<OtherCurrentAssetDetailRequest> assetDetailList;
	List<OtherIncomeDetailRequest> otherIncomeDetailsList;
	List<PurchasePropertyDetailsRequest> purchasePropertyDetailsList;
	List<OtherPropertyDetailsRequest> otherPropertyDetailsList;
	List<EmpSalariedTypeRequest> empSalariedTypeList;
	List<EmpAgriculturistTypeRequest> empAgriculturistTypeList;
	List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeList;
	List<ReferenceRetailDetailsRequest> referenceRetailDetailsList;
	private String nameOfSeller;
	private String sellerAddress;
	private Integer sellerPincode;
	private Integer sellerCity;
	private Integer sellerState;
	private Date dateOfExistingLoanTaken;
	private String originalValueOfProperty;
	private Long clientId;
	private String currencyValue;
	private String finalFilledCount;
	private Boolean sameAsPermanentAddress;
	private Integer statusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherFullName() {
		return fatherFullName;
	}

	public void setFatherFullName(String fatherFullName) {
		this.fatherFullName = fatherFullName;
	}

	public String getMatherMaidenName() {
		return matherMaidenName;
	}

	public void setMatherMaidenName(String matherMaidenName) {
		this.matherMaidenName = matherMaidenName;
	}

	public String getNameOfSpouse() {
		return nameOfSpouse;
	}

	public void setNameOfSpouse(String nameOfSpouse) {
		this.nameOfSpouse = nameOfSpouse;
	}

	public Integer getCast() {
		return cast;
	}

	public void setCast(Integer cast) {
		this.cast = cast;
	}

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public Address getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(Address permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public Address getCorrespondenceAddress() {
		return correspondenceAddress;
	}

	public void setCorrespondenceAddress(Address correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Integer employeeType) {
		this.employeeType = employeeType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	public List<BankAccountHeldDetailsRequest> getBankAccountHeldDetailsList() {
		return bankAccountHeldDetailsList;
	}

	public void setBankAccountHeldDetailsList(List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsList) {
		this.bankAccountHeldDetailsList = bankAccountHeldDetailsList;
	}

	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailsList() {
		return fixedDepositsDetailsList;
	}

	public void setFixedDepositsDetailsList(List<FixedDepositsDetailsRequest> fixedDepositsDetailsList) {
		this.fixedDepositsDetailsList = fixedDepositsDetailsList;
	}

	public List<OtherCurrentAssetDetailRequest> getAssetDetailList() {
		return assetDetailList;
	}

	public void setAssetDetailList(List<OtherCurrentAssetDetailRequest> assetDetailList) {
		this.assetDetailList = assetDetailList;
	}

	public List<OtherIncomeDetailRequest> getOtherIncomeDetailsList() {
		return otherIncomeDetailsList;
	}

	public void setOtherIncomeDetailsList(List<OtherIncomeDetailRequest> otherIncomeDetailsList) {
		this.otherIncomeDetailsList = otherIncomeDetailsList;
	}

	public String getNameOfSeller() {
		return nameOfSeller;
	}

	public void setNameOfSeller(String nameOfSeller) {
		this.nameOfSeller = nameOfSeller;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public Integer getSellerPincode() {
		return sellerPincode;
	}

	public void setSellerPincode(Integer sellerPincode) {
		this.sellerPincode = sellerPincode;
	}

	public Integer getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(Integer sellerCity) {
		this.sellerCity = sellerCity;
	}

	public Integer getSellerState() {
		return sellerState;
	}

	public void setSellerState(Integer sellerState) {
		this.sellerState = sellerState;
	}

	public Date getDateOfExistingLoanTaken() {
		return dateOfExistingLoanTaken;
	}

	public void setDateOfExistingLoanTaken(Date dateOfExistingLoanTaken) {
		this.dateOfExistingLoanTaken = dateOfExistingLoanTaken;
	}

	public String getOriginalValueOfProperty() {
		return originalValueOfProperty;
	}

	public void setOriginalValueOfProperty(String originalValueOfProperty) {
		this.originalValueOfProperty = originalValueOfProperty;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}

	public String getFinalFilledCount() {
		return finalFilledCount;
	}

	public List<PurchasePropertyDetailsRequest> getPurchasePropertyDetailsList() {
		return purchasePropertyDetailsList;
	}

	public void setPurchasePropertyDetailsList(List<PurchasePropertyDetailsRequest> purchasePropertyDetailsList) {
		this.purchasePropertyDetailsList = purchasePropertyDetailsList;
	}

	public List<OtherPropertyDetailsRequest> getOtherPropertyDetailsList() {
		return otherPropertyDetailsList;
	}

	public void setOtherPropertyDetailsList(List<OtherPropertyDetailsRequest> otherPropertyDetailsList) {
		this.otherPropertyDetailsList = otherPropertyDetailsList;
	}

	public List<EmpSalariedTypeRequest> getEmpSalariedTypeList() {
		return empSalariedTypeList;
	}

	public void setEmpSalariedTypeList(List<EmpSalariedTypeRequest> empSalariedTypeList) {
		this.empSalariedTypeList = empSalariedTypeList;
	}

	public List<EmpAgriculturistTypeRequest> getEmpAgriculturistTypeList() {
		return empAgriculturistTypeList;
	}

	public void setEmpAgriculturistTypeList(List<EmpAgriculturistTypeRequest> empAgriculturistTypeList) {
		this.empAgriculturistTypeList = empAgriculturistTypeList;
	}

	public List<EmpSelfEmployedTypeRequest> getEmpSelfEmployedTypeList() {
		return empSelfEmployedTypeList;
	}

	public void setEmpSelfEmployedTypeList(List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeList) {
		this.empSelfEmployedTypeList = empSelfEmployedTypeList;
	}

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailsList() {
		return referenceRetailDetailsList;
	}

	public void setReferenceRetailDetailsList(List<ReferenceRetailDetailsRequest> referenceRetailDetailsList) {
		this.referenceRetailDetailsList = referenceRetailDetailsList;
	}

	public Boolean getIsSameAsPermanentAddress() {
		return sameAsPermanentAddress;
	}

	public void setIsSameAsPermanentAddress(Boolean sameAsPermanentAddress) {
		this.sameAsPermanentAddress = sameAsPermanentAddress;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
}