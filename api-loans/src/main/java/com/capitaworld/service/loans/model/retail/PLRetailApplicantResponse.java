package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    
    private String maritalStatus;

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

    private Boolean isApplicantDetailsFilled;
    
    //FOR PL CAM REPORT
    private String loanAmountRequiredString;
    private String monthlyIncomeString;

    /*
    * Final
    * */

    public PLRetailApplicantResponse() {
        // Do nothing because of X and Y.
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
    
	
}
