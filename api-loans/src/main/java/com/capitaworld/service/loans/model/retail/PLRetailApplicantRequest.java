package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mohammad.maaz
 *
 */
public class PLRetailApplicantRequest implements Serializable {
    // Common Fields
    private Long id;
    private Long clientId;
    private Long applicationId;
    private Long proposalId;
    
    /*
    * Profile Fields
    * */
    private Integer titleId;
    private String firstName;
    private String middleName;
    private String lastName;
    
    private Integer genderId;
    private String pan;
    private String aadharNumber;
    private String mobile;
    private Integer educationQualification;
    private Integer statusId;
    private Integer residenceType;
    private Date birthDate;
    private Integer employmentType;
    private Integer employmentWith;
    private Integer centralGovId;
    private Integer stateGovId;
    private Integer psuId;
    private Integer corporateId;
    private Integer eduInstId;
    private String nameOfEmployer;
    private Integer employmentStatus;
    private Integer currentJobMonth;
    private Integer currentJobYear;
    private Integer totalExperienceMonth;
    private Integer totalExperienceYear;
    private Long keyVerticalFunding;
    private Long keyVerticalSector;
    private Long keyVerticalSubSector;
    private Address contactAddress;
    private Address officeAddress;
    private String contactNo;
    private String email;
    private String companyName;

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
    private List<BankRelationshipRequest> bankingRelationshipList;

    private Boolean isApplicantDetailsFilled;
    
    //FOR PL CAM REPORT
    private String loanAmountRequiredString;
    private String monthlyIncomeString;

    /*
    * Final
    * */
    
    private Long addressCity;
    private Long addressCountry;
    private Long addressState;
    
    private Integer spouseEmployment;
    private Integer noOfDependent;
    private Integer designation;
    private Integer residenceSinceYear;
    private Integer residenceSinceMonth;
    private Integer salaryMode;
    private String salaryBankName;
    private Integer salaryBankMonth;
    private Integer salaryBankYear;
    private Boolean isOtherSalaryBank;
    
    /*ForTeaser*/
    private Integer category;
    private String fatherName;
    private Double annualIncomeOfSpouse;
    private String nationality;
    private Date businessStartDate;
    private Double networth;
    
    public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public PLRetailApplicantRequest() {
        // Do nothing because of X and Y.
    }

    public String getNationality() {
		return nationality;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
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

    public Integer getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(Integer residenceType) {
        this.residenceType = residenceType;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Integer employmentType) {
        this.employmentType = employmentType;
    }

    public Integer getEmploymentWith() {
        return employmentWith;
    }

    public void setEmploymentWith(Integer employmentWith) {
        this.employmentWith = employmentWith;
    }

    public Integer getCentralGovId() {
        return centralGovId;
    }

    public void setCentralGovId(Integer centralGovId) {
        this.centralGovId = centralGovId;
    }

    public Integer getStateGovId() {
        return stateGovId;
    }

    public void setStateGovId(Integer stateGovId) {
        this.stateGovId = stateGovId;
    }

    public Integer getPsuId() {
        return psuId;
    }

    public void setPsuId(Integer psuId) {
        this.psuId = psuId;
    }

    public Integer getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(Integer corporateId) {
        this.corporateId = corporateId;
    }

    public Integer getEduInstId() {
        return eduInstId;
    }

    public void setEduInstId(Integer eduInstId) {
        this.eduInstId = eduInstId;
    }

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

    public Integer getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Integer employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Integer getCurrentJobMonth() {
        return currentJobMonth;
    }

    public void setCurrentJobMonth(Integer currentJobMonth) {
        this.currentJobMonth = currentJobMonth;
    }

    public Integer getCurrentJobYear() {
        return currentJobYear;
    }

    public void setCurrentJobYear(Integer currentJobYear) {
        this.currentJobYear = currentJobYear;
    }

    public Integer getTotalExperienceMonth() {
        return totalExperienceMonth;
    }

    public void setTotalExperienceMonth(Integer totalExperienceMonth) {
        this.totalExperienceMonth = totalExperienceMonth;
    }

    public Integer getTotalExperienceYear() {
        return totalExperienceYear;
    }

    public void setTotalExperienceYear(Integer totalExperienceYear) {
        this.totalExperienceYear = totalExperienceYear;
    }

    public Long getKeyVerticalFunding() {
        return keyVerticalFunding;
    }

    public void setKeyVerticalFunding(Long keyVerticalFunding) {
        this.keyVerticalFunding = keyVerticalFunding;
    }

    public Long getKeyVerticalSector() {
        return keyVerticalSector;
    }

    public void setKeyVerticalSector(Long keyVerticalSector) {
        this.keyVerticalSector = keyVerticalSector;
    }

    public Long getKeyVerticalSubSector() {
        return keyVerticalSubSector;
    }

    public void setKeyVerticalSubSector(Long keyVerticalSubSector) {
        this.keyVerticalSubSector = keyVerticalSubSector;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

	public Long getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(Long addressCity) {
		this.addressCity = addressCity;
	}

	public Long getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Long addressCountry) {
		this.addressCountry = addressCountry;
	}

	public Long getAddressState() {
		return addressState;
	}

	public void setAddressState(Long addressState) {
		this.addressState = addressState;
	}

	public Integer getSpouseEmployment() {
		return spouseEmployment;
	}

	public Integer getNoOfDependent() {
		return noOfDependent;
	}

	public Integer getDesignation() {
		return designation;
	}

	public Integer getResidenceSinceYear() {
		return residenceSinceYear;
	}

	public Integer getResidenceSinceMonth() {
		return residenceSinceMonth;
	}

	public void setSpouseEmployment(Integer spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}

	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	public void setResidenceSinceYear(Integer residenceSinceYear) {
		this.residenceSinceYear = residenceSinceYear;
	}

	public void setResidenceSinceMonth(Integer residenceSinceMonth) {
		this.residenceSinceMonth = residenceSinceMonth;
	}

	public Integer getSalaryMode() {
		return salaryMode;
	}

	public String getSalaryBankName() {
		return salaryBankName;
	}

	public Integer getSalaryBankMonth() {
		return salaryBankMonth;
	}

	public Integer getSalaryBankYear() {
		return salaryBankYear;
	}

	public void setSalaryMode(Integer salaryMode) {
		this.salaryMode = salaryMode;
	}

	public void setSalaryBankName(String salaryBankName) {
		this.salaryBankName = salaryBankName;
	}

	public void setSalaryBankMonth(Integer salaryBankMonth) {
		this.salaryBankMonth = salaryBankMonth;
	}

	public void setSalaryBankYear(Integer salaryBankYear) {
		this.salaryBankYear = salaryBankYear;
	}

	public List<BankRelationshipRequest> getBankingRelationshipList() {
		return bankingRelationshipList;
	}

	public void setBankingRelationshipList(List<BankRelationshipRequest> bankingRelationshipList) {
		this.bankingRelationshipList = bankingRelationshipList;
	}

	public Boolean getIsOtherSalaryBank() {
		return isOtherSalaryBank;
	}

	public void setIsOtherSalaryBank(Boolean isOtherSalaryBank) {
		this.isOtherSalaryBank = isOtherSalaryBank;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Address getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Double getAnnualIncomeOfSpouse() {
		return annualIncomeOfSpouse;
	}

	public void setAnnualIncomeOfSpouse(Double annualIncomeOfSpouse) {
		this.annualIncomeOfSpouse = annualIncomeOfSpouse;
	}

	@Override
	public String toString() {
		return "PLRetailApplicantRequest [id=" + id + ", clientId=" + clientId + ", applicationId=" + applicationId
				+ ", proposalId=" + proposalId + ", titleId=" + titleId + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", genderId=" + genderId + ", pan=" + pan + ", aadharNumber="
				+ aadharNumber + ", mobile=" + mobile + ", educationQualification=" + educationQualification
				+ ", statusId=" + statusId + ", residenceType=" + residenceType + ", birthDate=" + birthDate
				+ ", employmentType=" + employmentType + ", employmentWith=" + employmentWith + ", centralGovId="
				+ centralGovId + ", stateGovId=" + stateGovId + ", psuId=" + psuId + ", corporateId=" + corporateId
				+ ", eduInstId=" + eduInstId + ", nameOfEmployer=" + nameOfEmployer + ", employmentStatus="
				+ employmentStatus + ", currentJobMonth=" + currentJobMonth + ", currentJobYear=" + currentJobYear
				+ ", totalExperienceMonth=" + totalExperienceMonth + ", totalExperienceYear=" + totalExperienceYear
				+ ", keyVerticalFunding=" + keyVerticalFunding + ", keyVerticalSector=" + keyVerticalSector
				+ ", keyVerticalSubSector=" + keyVerticalSubSector + ", contactAddress=" + contactAddress
				+ ", officeAddress=" + officeAddress + ", contactNo=" + contactNo + ", email=" + email
				+ ", companyName=" + companyName + ", retailApplicantIncomeRequestList="
				+ retailApplicantIncomeRequestList + ", loanAmountRequired=" + loanAmountRequired + ", loanPurpose="
				+ loanPurpose + ", tenureRequired=" + tenureRequired + ", repayment=" + repayment + ", monthlyIncome="
				+ monthlyIncome + ", financialArrangementsDetailRequestsList=" + financialArrangementsDetailRequestsList
				+ ", creditCardsDetailRequestList=" + creditCardsDetailRequestList + ", bankingRelationshipList="
				+ bankingRelationshipList + ", isApplicantDetailsFilled=" + isApplicantDetailsFilled
				+ ", loanAmountRequiredString=" + loanAmountRequiredString + ", monthlyIncomeString="
				+ monthlyIncomeString + ", addressCity=" + addressCity + ", addressCountry=" + addressCountry
				+ ", addressState=" + addressState + ", spouseEmployment=" + spouseEmployment + ", noOfDependent="
				+ noOfDependent + ", designation=" + designation + ", residenceSinceYear=" + residenceSinceYear
				+ ", residenceSinceMonth=" + residenceSinceMonth + ", salaryMode=" + salaryMode + ", salaryBankName="
				+ salaryBankName + ", salaryBankMonth=" + salaryBankMonth + ", salaryBankYear=" + salaryBankYear
				+ ", isOtherSalaryBank=" + isOtherSalaryBank + ", category=" + category + ", fatherName=" + fatherName
				+ ", annualIncomeOfSpouse=" + annualIncomeOfSpouse + "]";
	}
    
    
}
