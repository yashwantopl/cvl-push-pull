package com.opl.mudra.api.loans.model.retail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.model.Address;

/**
 * The persistent class for the fs_retail_final_auto_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinalAutoLoanDetailRequest implements Serializable {

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
	private List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsList;
	private List<FixedDepositsDetailsRequest> fixedDepositsDetailsList;
	private List<OtherCurrentAssetDetailRequest> assetDetailList;
	private List<OtherIncomeDetailRequest> otherIncomeDetailsList;
	private List<PurchasePropertyDetailsRequest> purchasePropertyDetailsList;
	private List<OtherPropertyDetailsRequest> otherPropertyDetailsList;
	private List<EmpSalariedTypeRequest> empSalariedTypeList;
	private List<EmpAgriculturistTypeRequest> empAgriculturistTypeList;
	private List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeList;
	private List<ReferenceRetailDetailsRequest> referenceRetailDetailsList;
	private PreSanctionVisitRequest preSanctionVisit; 
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfExistingLoanTaken;
	private Integer originalValueOfProperty;
	private Long clientId;
	private String currencyValue;
	private String finalFilledCount;
	private Boolean isSameAsPermanentAddress;
	private Integer statusId;
	private Object workflowData;
	private String remarks;
	private Boolean isFinalInformationFilled;
	

	// guarantor info
	private String guarantorName;
	private String employmentOfGuarantor;
	private Boolean isGuarantorDefaulter ;	
	private String guarantorRemark;
	private String guarantorAddress;

	// auto dealer info
	private String dealerName;
	private String dealerMarketReputation;
	private String dealerAddress;

	
	
	

	
	
	public FinalAutoLoanDetailRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	public Date getDateOfExistingLoanTaken() {
		return dateOfExistingLoanTaken;
	}
	public void setDateOfExistingLoanTaken(Date dateOfExistingLoanTaken) {
		this.dateOfExistingLoanTaken = dateOfExistingLoanTaken;
	}
	public Integer getOriginalValueOfProperty() {
		return originalValueOfProperty;
	}
	public void setOriginalValueOfProperty(Integer originalValueOfProperty) {
		this.originalValueOfProperty = originalValueOfProperty;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getCurrencyValue() {
		return currencyValue;
	}
	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}
	public String getFinalFilledCount() {
		return finalFilledCount;
	}
	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}
	public Boolean getIsSameAsPermanentAddress() {
		return isSameAsPermanentAddress;
	}
	public void setIsSameAsPermanentAddress(Boolean isSameAsPermanentAddress) {
		this.isSameAsPermanentAddress = isSameAsPermanentAddress;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Object getWorkflowData() {
		return workflowData;
	}
	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getIsFinalInformationFilled() {
		return isFinalInformationFilled;
	}

	public void setIsFinalInformationFilled(Boolean isFinalInformationFilled) {
		this.isFinalInformationFilled = isFinalInformationFilled;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public String getEmploymentOfGuarantor() {
		return employmentOfGuarantor;
	}

	public void setEmploymentOfGuarantor(String employmentOfGuarantor) {
		this.employmentOfGuarantor = employmentOfGuarantor;
	}

	public Boolean getIsGuarantorDefaulter() {
		return isGuarantorDefaulter;
	}

	public void setIsGuarantorDefaulter(Boolean isGuarantorDefaulter) {
		this.isGuarantorDefaulter = isGuarantorDefaulter;
	}

	public String getGuarantorRemark() {
		return guarantorRemark;
	}

	public void setGuarantorRemark(String guarantorRemark) {
		this.guarantorRemark = guarantorRemark;
	}

	public String getGuarantorAddress() {
		return guarantorAddress;
	}

	public void setGuarantorAddress(String guarantorAddress) {
		this.guarantorAddress = guarantorAddress;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerMarketReputation() {
		return dealerMarketReputation;
	}

	public void setDealerMarketReputation(String dealerMarketReputation) {
		this.dealerMarketReputation = dealerMarketReputation;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public PreSanctionVisitRequest getPreSanctionVisit() {
		return preSanctionVisit;
	}

	public void setPreSanctionVisit(PreSanctionVisitRequest preSanctionVisit) {
		this.preSanctionVisit = preSanctionVisit;
	}

}
