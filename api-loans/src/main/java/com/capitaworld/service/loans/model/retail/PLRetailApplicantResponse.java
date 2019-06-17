package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;

public class PLRetailApplicantResponse implements Serializable {
    // Common Fields
    private Long id;
    private Long clientId;
    private Long applicationId;

    /*
    * Profile Fields
    * */
    private Integer titleId;
    private String fullName;
    private String gender;
    private String pan;
    private String aadharNumber;
    private String mobile;
    private Integer educationQualification;
    private String educationQualificationString;
    private Object statusId;
    private String residenceType;
    private Date birthDate;
    private String employmentType;
    private String employmentWith;
    private String centralGov;
    private String stateGov;
    private String psu;
    private String corporate;
    private String eduInst;
    private String nameOfEmployer;
    private String employmentStatus;
    private Integer currentJobMonth;
    private String currentJobYear;
    private Integer totalExperienceMonth;
    private String totalExperienceYear;
    private String keyVerticalFunding;
    private String keyVerticalSector;
    private String keyVerticalSubSector;
    private Address contactAddress;
    private String contactNo;
    private String email;
    private Long coAppId;
    private String maritalStatus;

    private String category;
    private String fatherName;
    private Integer residenceSinceYear;
    private Date businessStartDate;
    private Double networth;
    private Double grossMonthlyIncome;
    private String currentEmploymentStatus;

    private List<RetailApplicantIncomeRequest> retailApplicantIncomeRequestList;
    /*
    * Primary Fields
    * */
    private Double loanAmountRequired;
    private Integer loanPurpose;
    private Integer tenureRequired;
    private Integer repayment;
    private Double monthlyIncome;
    private List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList;
    private List<CreditCardsDetailRequest> creditCardsDetailRequestList;
    private List<BankRelationshipRequest> bankRelationShipList;
    private Boolean isApplicantDetailsFilled;
    
    //FOR PL CAM REPORT
    private String loanAmountRequiredString;
    private String monthlyIncomeString;
    private String spouseEmployment;
    private Integer noOfDependent;
    private String designation;
    private String residenceSinceMonthYear;
    private String salaryMode;
    private String salaryAccountBankName;
    private String salaryAccountBankSince;
    private Boolean isOtherSalaryAccBank;
    private String nationality;
    private Double annualIncomeOfSpouse;
    private String address;
    private String coApplicantNameAsPerITR;
    private String tenureReq;
    private String companyName;
    private Object coAppItrXml;
    private Object coAppItrPdf;
    private String OccupationHL;
    private String scoringModelName;
    private Object dataList;
    private Object dataObject;
    private Object scoringResponseList;
    private String motherName;
	private String educationStatus;
	private String educationYear;
	private String nameOfSpouse;
	private Integer noOfChildren;
	private String birthPlace;
	private String religion;
	private String castCategory;
	private Date qualifyingYear;
	private String relationshipWithApplicant;
	private String relationWithApp;
	private Object corrAdd;
	private Object corrAddPremise;
	private Object corrAddStreetName;
	private Object corrAddLandmark;
	private Object corrAddCountry;
	private Object corrAddState;
	private Object corrAddCity;
	private Object corrAddPincode;
	private Object corrAddPinData;
	private String corrAddDist;
	private String corrAddTaluko;
	private Object permAdd;
	private Object permAddPremise;
	private Object permAddStreetName;
	private Object permAddLandmark;
	private Object permAddCountry;
	private Object permAddState;
	private Object permAddCity;
	private Object permAddPincode;
	private Object permAddPinData;
	private String permAddDist;
	private String permAddTaluko;

    /*
    * Final
    * */

	public String getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(String relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public Date getQualifyingYear() {
		return qualifyingYear;
	}

	public void setQualifyingYear(Date qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public PLRetailApplicantResponse() {
        // Do nothing because of X and Y.
    }

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public String getEducationYear() {
		return educationYear;
	}

	public void setEducationYear(String educationYear) {
		this.educationYear = educationYear;
	}

	public String getNameOfSpouse() {
		return nameOfSpouse;
	}

	public void setNameOfSpouse(String nameOfSpouse) {
		this.nameOfSpouse = nameOfSpouse;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getCategory() {
		return category;
	}

	public String getAddress() {
		return address;
	}

	public String getCoApplicantNameAsPerITR() {
		return coApplicantNameAsPerITR;
	}

	public void setCoApplicantNameAsPerITR(String coApplicantNameAsPerITR) {
		this.coApplicantNameAsPerITR = coApplicantNameAsPerITR;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCurrentEmploymentStatus() {
		return currentEmploymentStatus;
	}

	public void setCurrentEmploymentStatus(String currentEmploymentStatus) {
		this.currentEmploymentStatus = currentEmploymentStatus;
	}

	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Double getAnnualIncomeOfSpouse() {
		return annualIncomeOfSpouse;
	}

	public void setAnnualIncomeOfSpouse(Double annualIncomeOfSpouse) {
		this.annualIncomeOfSpouse = annualIncomeOfSpouse;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Integer getResidenceSinceYear() {
		return residenceSinceYear;
	}

	public void setResidenceSinceYear(Integer residenceSinceYear) {
		this.residenceSinceYear = residenceSinceYear;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

   
   

    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }



    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

   
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(Integer educationQualification) {
        this.educationQualification = educationQualification;
    }

  

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

   

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

  
    public Integer getCurrentJobMonth() {
        return currentJobMonth;
    }

    public void setCurrentJobMonth(Integer currentJobMonth) {
        this.currentJobMonth = currentJobMonth;
    }

    

    public String getCurrentJobYear() {
		return currentJobYear;
	}

	public void setCurrentJobYear(String currentJobYear) {
		this.currentJobYear = currentJobYear;
	}

	public Integer getTotalExperienceMonth() {
        return totalExperienceMonth;
    }

    public void setTotalExperienceMonth(Integer totalExperienceMonth) {
        this.totalExperienceMonth = totalExperienceMonth;
    }

   

   
    public String getTotalExperienceYear() {
		return totalExperienceYear;
	}

	public void setTotalExperienceYear(String totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLoanAmountRequired() {
        return loanAmountRequired;
    }

    public void setLoanAmountRequired(Double loanAmountRequired) {
        this.loanAmountRequired = loanAmountRequired;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Integer getTenureRequired() {
        return tenureRequired;
    }

    public void setTenureRequired(Integer tenureRequired) {
        this.tenureRequired = tenureRequired;
    }

    public Integer getRepayment() {
        return repayment;
    }

    public void setRepayment(Integer repayment) {
        this.repayment = repayment;
    }

    public Boolean getIsApplicantDetailsFilled() {
        return isApplicantDetailsFilled;
    }

    public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
        this.isApplicantDetailsFilled = isApplicantDetailsFilled;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

    public List<RetailApplicantIncomeRequest> getRetailApplicantIncomeRequestList() {
        return retailApplicantIncomeRequestList;
    }

    public void setRetailApplicantIncomeRequestList(List<RetailApplicantIncomeRequest> retailApplicantIncomeRequestList) {
        this.retailApplicantIncomeRequestList = retailApplicantIncomeRequestList;
    }

    public List<FinancialArrangementsDetailRequest> getFinancialArrangementsDetailRequestsList() {
        return financialArrangementsDetailRequestsList;
    }

    public void setFinancialArrangementsDetailRequestsList(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList) {
        this.financialArrangementsDetailRequestsList = financialArrangementsDetailRequestsList;
    }

    public List<CreditCardsDetailRequest> getCreditCardsDetailRequestList() {
        return creditCardsDetailRequestList;
    }

    public void setCreditCardsDetailRequestList(List<CreditCardsDetailRequest> creditCardsDetailRequestList) {
        this.creditCardsDetailRequestList = creditCardsDetailRequestList;
    }

	public String getLoanAmountRequiredString() {
		return loanAmountRequiredString;
	}

	public void setLoanAmountRequiredString(String loanAmountRequiredString) {
		this.loanAmountRequiredString = loanAmountRequiredString;
	}

	public String getMonthlyIncomeString() {
		return monthlyIncomeString;
	}

	public void setMonthlyIncomeString(String monthlyIncomeString) {
		this.monthlyIncomeString = monthlyIncomeString;
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Object getStatusId() {
		return statusId;
	}

	public void setStatusId(Object statusId) {
		this.statusId = statusId;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getEmploymentWith() {
		return employmentWith;
	}

	public void setEmploymentWith(String employmentWith) {
		this.employmentWith = employmentWith;
	}

	public String getCentralGov() {
		return centralGov;
	}

	public void setCentralGov(String centralGov) {
		this.centralGov = centralGov;
	}

	public String getStateGov() {
		return stateGov;
	}

	public void setStateGov(String stateGov) {
		this.stateGov = stateGov;
	}

	public String getPsu() {
		return psu;
	}

	public void setPsu(String psu) {
		this.psu = psu;
	}

	public String getCorporate() {
		return corporate;
	}

	public void setCorporate(String corporate) {
		this.corporate = corporate;
	}

	public String getEduInst() {
		return eduInst;
	}

	public void setEduInst(String eduInst) {
		this.eduInst = eduInst;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getKeyVerticalFunding() {
		return keyVerticalFunding;
	}

	public void setKeyVerticalFunding(String keyVerticalFunding) {
		this.keyVerticalFunding = keyVerticalFunding;
	}

	public String getKeyVerticalSector() {
		return keyVerticalSector;
	}

	public void setKeyVerticalSector(String keyVerticalSector) {
		this.keyVerticalSector = keyVerticalSector;
	}

	public String getKeyVerticalSubSector() {
		return keyVerticalSubSector;
	}

	public void setKeyVerticalSubSector(String keyVerticalSubSector) {
		this.keyVerticalSubSector = keyVerticalSubSector;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducationQualificationString() {
		return educationQualificationString;
	}

	public void setEducationQualificationString(String educationQualificationString) {
		this.educationQualificationString = educationQualificationString;
	}

	

	public String getSpouseEmployment() {
		return spouseEmployment;
	}

	public void setSpouseEmployment(String spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}

	public Integer getNoOfDependent() {
		return noOfDependent;
	}

	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}



	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getResidenceSinceMonthYear() {
		return residenceSinceMonthYear;
	}

	public void setResidenceSinceMonthYear(String residenceSinceMonthYear) {
		this.residenceSinceMonthYear = residenceSinceMonthYear;
	}

	public String getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(String salaryMode) {
		this.salaryMode = salaryMode;
	}

	public String getSalaryAccountBankName() {
		return salaryAccountBankName;
	}

	public void setSalaryAccountBankName(String salaryAccountBankName) {
		this.salaryAccountBankName = salaryAccountBankName;
	}

	public String getSalaryAccountBankSince() {
		return salaryAccountBankSince;
	}

	public void setSalaryAccountBankSince(String salaryAccountBankSince) {
		this.salaryAccountBankSince = salaryAccountBankSince;
	}

	public Boolean getIsOtherSalaryAccBank() {
		return isOtherSalaryAccBank;
	}

	public void setIsOtherSalaryAccBank(Boolean isOtherSalaryAccBank) {
		this.isOtherSalaryAccBank = isOtherSalaryAccBank;
	}

	public List<BankRelationshipRequest> getBankRelationShipList() {
		return bankRelationShipList;
	}

	public void setBankRelationShipList(List<BankRelationshipRequest> bankRelationShipList) {
		this.bankRelationShipList = bankRelationShipList;
	}

	public String getTenureReq() {
		return tenureReq;
	}

	public void setTenureReq(String tenureReq) {
		this.tenureReq = tenureReq;
	}

	public Object getCoAppItrXml() {
		return coAppItrXml;
	}

	public void setCoAppItrXml(Object coAppItrXml) {
		this.coAppItrXml = coAppItrXml;
	}

	public Object getCoAppItrPdf() {
		return coAppItrPdf;
	}

	public void setCoAppItrPdf(Object coAppItrPdf) {
		this.coAppItrPdf = coAppItrPdf;
	}

	public String getOccupationHL() {
		return OccupationHL;
	}

	public void setOccupationHL(String occupationHL) {
		OccupationHL = occupationHL;
	}

	public String getScoringModelName() {
		return scoringModelName;
	}

	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}

	public Object getDataList() {
		return dataList;
	}

	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	public Object getScoringResponseList() {
		return scoringResponseList;
	}

	public void setScoringResponseList(Object scoringResponseList) {
		this.scoringResponseList = scoringResponseList;
	}

	public Long getCoAppId() {
		return coAppId;
	}

	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRelationWithApp() {
		return relationWithApp;
	}

	public void setRelationWithApp(String relationWithApp) {
		this.relationWithApp = relationWithApp;
	}

	public Object getCorrAdd() {
		return corrAdd;
	}

	public void setCorrAdd(Object corrAdd) {
		this.corrAdd = corrAdd;
	}

	public Object getCorrAddPremise() {
		return corrAddPremise;
	}

	public void setCorrAddPremise(Object corrAddPremise) {
		this.corrAddPremise = corrAddPremise;
	}

	public Object getCorrAddStreetName() {
		return corrAddStreetName;
	}

	public void setCorrAddStreetName(Object corrAddStreetName) {
		this.corrAddStreetName = corrAddStreetName;
	}

	public Object getCorrAddLandmark() {
		return corrAddLandmark;
	}

	public void setCorrAddLandmark(Object corrAddLandmark) {
		this.corrAddLandmark = corrAddLandmark;
	}

	public Object getCorrAddCountry() {
		return corrAddCountry;
	}

	public void setCorrAddCountry(Object corrAddCountry) {
		this.corrAddCountry = corrAddCountry;
	}

	public Object getCorrAddState() {
		return corrAddState;
	}

	public void setCorrAddState(Object corrAddState) {
		this.corrAddState = corrAddState;
	}

	public Object getCorrAddCity() {
		return corrAddCity;
	}

	public void setCorrAddCity(Object corrAddCity) {
		this.corrAddCity = corrAddCity;
	}

	public Object getCorrAddPincode() {
		return corrAddPincode;
	}

	public void setCorrAddPincode(Object corrAddPincode) {
		this.corrAddPincode = corrAddPincode;
	}

	public Object getCorrAddPinData() {
		return corrAddPinData;
	}

	public void setCorrAddPinData(Object corrAddPinData) {
		this.corrAddPinData = corrAddPinData;
	}

	public String getCorrAddDist() {
		return corrAddDist;
	}

	public void setCorrAddDist(String corrAddDist) {
		this.corrAddDist = corrAddDist;
	}

	public String getCorrAddTaluko() {
		return corrAddTaluko;
	}

	public void setCorrAddTaluko(String corrAddTaluko) {
		this.corrAddTaluko = corrAddTaluko;
	}

	public Object getPermAdd() {
		return permAdd;
	}

	public void setPermAdd(Object permAdd) {
		this.permAdd = permAdd;
	}

	public Object getPermAddPremise() {
		return permAddPremise;
	}

	public void setPermAddPremise(Object permAddPremise) {
		this.permAddPremise = permAddPremise;
	}

	public Object getPermAddStreetName() {
		return permAddStreetName;
	}

	public void setPermAddStreetName(Object permAddStreetName) {
		this.permAddStreetName = permAddStreetName;
	}

	public Object getPermAddLandmark() {
		return permAddLandmark;
	}

	public void setPermAddLandmark(Object permAddLandmark) {
		this.permAddLandmark = permAddLandmark;
	}

	public Object getPermAddCountry() {
		return permAddCountry;
	}

	public void setPermAddCountry(Object permAddCountry) {
		this.permAddCountry = permAddCountry;
	}

	public Object getPermAddState() {
		return permAddState;
	}

	public void setPermAddState(Object permAddState) {
		this.permAddState = permAddState;
	}

	public Object getPermAddCity() {
		return permAddCity;
	}

	public void setPermAddCity(Object permAddCity) {
		this.permAddCity = permAddCity;
	}

	public Object getPermAddPincode() {
		return permAddPincode;
	}

	public void setPermAddPincode(Object permAddPincode) {
		this.permAddPincode = permAddPincode;
	}

	public Object getPermAddPinData() {
		return permAddPinData;
	}

	public void setPermAddPinData(Object permAddPinData) {
		this.permAddPinData = permAddPinData;
	}

	public String getPermAddDist() {
		return permAddDist;
	}

	public void setPermAddDist(String permAddDist) {
		this.permAddDist = permAddDist;
	}

	public String getPermAddTaluko() {
		return permAddTaluko;
	}

	public void setPermAddTaluko(String permAddTaluko) {
		this.permAddTaluko = permAddTaluko;
	}
}
