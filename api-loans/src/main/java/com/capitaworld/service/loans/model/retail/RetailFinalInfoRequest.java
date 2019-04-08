package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by ravina.panchal on 03-10-2018.
 */
public class RetailFinalInfoRequest {

    private Long id;
    private Long clientId;
    private Long applicationId;
    private Long proposalId;

    private Integer religion;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date qualifyingYear;
    private Integer noChildren;
    private String fatherName;
    private String motherName;
    private String spouseName;
    private Integer noDependent;
    private Double residingMonth;
    private Double residingYear;
    private String nationality;
    private Integer residentialStatus;
    private Integer castId;
    private String birthPlace;
    private Integer disabilityType;
    private String drivingLicenseNumber;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date drivingLicenseExpiryDate;
    private String passport;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date passportValidity;
    private String voterId;
    private String residentialProofNo;

    private Boolean addressSameAs;
    private Address permanentAddress;
    private Address officeAddress;
    private String officeNameOfOrg;
    private String officeEmail;
    private Address contactAddress;

    private Integer previousJobYear;
    private Integer previousJobMonth;
    private String previousEmployersName;
    private String previousEmployersAddress;
    private String previousEmployersContact;

    private String ddoWebsite;
    private Integer ddoRemainingSerYrs;
    private Integer ddoRemainingSerMonths;
    private String ddoEmployeeNo;
    private String ddoDesignation;
    private String ddoDepartment;
    private Integer ddoOrganizationType;

    private Boolean isApplicantFinalFilled;
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

    public Integer getReligion() {
        return religion;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public Date getQualifyingYear() {
        return qualifyingYear;
    }

    public void setQualifyingYear(Date qualifyingYear) {
        this.qualifyingYear = qualifyingYear;
    }

    public Integer getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(Integer noChildren) {
        this.noChildren = noChildren;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Integer getNoDependent() {
        return noDependent;
    }

    public void setNoDependent(Integer noDependent) {
        this.noDependent = noDependent;
    }

    public Double getResidingMonth() {
        return residingMonth;
    }

    public void setResidingMonth(Double residingMonth) {
        this.residingMonth = residingMonth;
    }

    public Double getResidingYear() {
        return residingYear;
    }

    public void setResidingYear(Double residingYear) {
        this.residingYear = residingYear;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getResidentialStatus() {
        return residentialStatus;
    }

    public void setResidentialStatus(Integer residentialStatus) {
        this.residentialStatus = residentialStatus;
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(Integer disabilityType) {
        this.disabilityType = disabilityType;
    }


    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Date getPassportValidity() {
        return passportValidity;
    }

    public void setPassportValidity(Date passportValidity) {
        this.passportValidity = passportValidity;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getResidentialProofNo() {
        return residentialProofNo;
    }

    public void setResidentialProofNo(String residentialProofNo) {
        this.residentialProofNo = residentialProofNo;
    }

    public Boolean getAddressSameAs() {
        return addressSameAs;
    }

    public void setAddressSameAs(Boolean addressSameAs) {
        this.addressSameAs = addressSameAs;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeNameOfOrg() {
        return officeNameOfOrg;
    }

    public void setOfficeNameOfOrg(String officeNameOfOrg) {
        this.officeNameOfOrg = officeNameOfOrg;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public Integer getPreviousJobYear() {
        return previousJobYear;
    }

    public void setPreviousJobYear(Integer previousJobYear) {
        this.previousJobYear = previousJobYear;
    }

    public Integer getPreviousJobMonth() {
        return previousJobMonth;
    }

    public void setPreviousJobMonth(Integer previousJobMonth) {
        this.previousJobMonth = previousJobMonth;
    }

    public String getPreviousEmployersName() {
        return previousEmployersName;
    }

    public void setPreviousEmployersName(String previousEmployersName) {
        this.previousEmployersName = previousEmployersName;
    }

    public String getPreviousEmployersAddress() {
        return previousEmployersAddress;
    }

    public void setPreviousEmployersAddress(String previousEmployersAddress) {
        this.previousEmployersAddress = previousEmployersAddress;
    }

    public String getPreviousEmployersContact() {
        return previousEmployersContact;
    }

    public void setPreviousEmployersContact(String previousEmployersContact) {
        this.previousEmployersContact = previousEmployersContact;
    }

    public String getDdoWebsite() {
        return ddoWebsite;
    }

    public void setDdoWebsite(String ddoWebsite) {
        this.ddoWebsite = ddoWebsite;
    }

    public Integer getDdoRemainingSerYrs() {
        return ddoRemainingSerYrs;
    }

    public void setDdoRemainingSerYrs(Integer ddoRemainingSerYrs) {
        this.ddoRemainingSerYrs = ddoRemainingSerYrs;
    }

    public Integer getDdoRemainingSerMonths() {
        return ddoRemainingSerMonths;
    }

    public void setDdoRemainingSerMonths(Integer ddoRemainingSerMonths) {
        this.ddoRemainingSerMonths = ddoRemainingSerMonths;
    }

    public String getDdoEmployeeNo() {
        return ddoEmployeeNo;
    }

    public void setDdoEmployeeNo(String ddoEmployeeNo) {
        this.ddoEmployeeNo = ddoEmployeeNo;
    }

    public String getDdoDesignation() {
        return ddoDesignation;
    }

    public void setDdoDesignation(String ddoDesignation) {
        this.ddoDesignation = ddoDesignation;
    }

    public String getDdoDepartment() {
        return ddoDepartment;
    }

    public void setDdoDepartment(String ddoDepartment) {
        this.ddoDepartment = ddoDepartment;
    }

    public Integer getDdoOrganizationType() {
        return ddoOrganizationType;
    }

    public void setDdoOrganizationType(Integer ddoOrganizationType) {
        this.ddoOrganizationType = ddoOrganizationType;
    }

    public Boolean getIsApplicantFinalFilled() {
        return isApplicantFinalFilled;
    }

    public void setIsApplicantFinalFilled(Boolean isApplicantFinalFilled) {
        this.isApplicantFinalFilled = isApplicantFinalFilled;
    }

    public Address getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(Address contactAddress) {
        this.contactAddress = contactAddress;
    }

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public Date getDrivingLicenseExpiryDate() {
		return drivingLicenseExpiryDate;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
		this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
	}
    
    
}
