package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AadharDetailsReq {

    private Long id;

    private Long applicationId;

    private Long applicationProposalMapping;

    private String aadharNumber;

    private String nameAsPerAadharCard;

    private String firstName;

    private String lastName;

    private String middleName;

    private Date birthDate;

    private Integer genderId;

    private Integer maritalStatusId;

    private String currentAddress;

    private Boolean addressSameAsAadhar;

    private String addressPincode;

    private String aadharAddress;

    private String aadharPincode;

    private Integer addressProfType;

    private String fatherName;

    private String motherName;

    private String spouseName;

    private Date spouseBirthDate;

    private String spouseMobile;

    private Integer noDependent;

    private String nomineeName;

    private Integer relationWithNomineeId;

    private String nomineeAddress;

    private String nomineePincode;

    private Integer religion;

    private Integer educationQualification;

    private Double landHolding;

    private String nameOfFirm;

    private Integer businessType;

    private Integer houseType;

    private Integer loanPurpose;

    private Double loanAmountRequired;

    private Double costOfProject;

    private Double costOfEquipment;

    private Double workingCapOfEquipment;

    private Double totalCostEquipment;

    private Double promoterContribution;

    private Double loanRequiredFromSidbi;

    private Double totalMeanFinance;

    private Double totalCashFlow;

    private Integer repaymentFrequency;

    private Boolean insurenceRequired;

    private String insurenceCompanyName;

    private Double insurencePremium;

    private Long createdBy;

    private Date createdDate;

    private Boolean isActive;

    private Long modifiedBy;

    private Date modifiedDate;

    private Integer loanType;

    private Integer type;

    private String remarks;

    private Long userId;

    private Integer addressProofType;

    private Integer businessTypeId;

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

    public Long getApplicationProposalMapping() {
        return applicationProposalMapping;
    }

    public void setApplicationProposalMapping(Long applicationProposalMapping) {
        this.applicationProposalMapping = applicationProposalMapping;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getNameAsPerAadharCard() {
        return nameAsPerAadharCard;
    }

    public void setNameAsPerAadharCard(String nameAsPerAadharCard) {
        this.nameAsPerAadharCard = nameAsPerAadharCard;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getMaritalStatusId() {
        return maritalStatusId;
    }

    public void setMaritalStatusId(Integer maritalStatusId) {
        this.maritalStatusId = maritalStatusId;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public Boolean getAddressSameAsAadhar() {
        return addressSameAsAadhar;
    }

    public void setAddressSameAsAadhar(Boolean addressSameAsAadhar) {
        this.addressSameAsAadhar = addressSameAsAadhar;
    }

    public String getAddressPincode() {
        return addressPincode;
    }

    public void setAddressPincode(String addressPincode) {
        this.addressPincode = addressPincode;
    }

    public String getAadharAddress() {
        return aadharAddress;
    }

    public void setAadharAddress(String aadharAddress) {
        this.aadharAddress = aadharAddress;
    }

    public String getAadharPincode() {
        return aadharPincode;
    }

    public void setAadharPincode(String aadharPincode) {
        this.aadharPincode = aadharPincode;
    }

    public Integer getAddressProfType() {
        return addressProfType;
    }

    public void setAddressProfType(Integer addressProfType) {
        this.addressProfType = addressProfType;
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

    public Date getSpouseBirthDate() {
        return spouseBirthDate;
    }

    public void setSpouseBirthDate(Date spouseBirthDate) {
        this.spouseBirthDate = spouseBirthDate;
    }

    public String getSpouseMobile() {
        return spouseMobile;
    }

    public void setSpouseMobile(String spouseMobile) {
        this.spouseMobile = spouseMobile;
    }

    public Integer getNoDependent() {
        return noDependent;
    }

    public void setNoDependent(Integer noDependent) {
        this.noDependent = noDependent;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public Integer getRelationWithNomineeId() {
        return relationWithNomineeId;
    }

    public void setRelationWithNomineeId(Integer relationWithNomineeId) {
        this.relationWithNomineeId = relationWithNomineeId;
    }

    public String getNomineeAddress() {
        return nomineeAddress;
    }

    public void setNomineeAddress(String nomineeAddress) {
        this.nomineeAddress = nomineeAddress;
    }

    public String getNomineePincode() {
        return nomineePincode;
    }

    public void setNomineePincode(String nomineePincode) {
        this.nomineePincode = nomineePincode;
    }

    public Integer getReligion() {
        return religion;
    }

    public void setReligion(Integer religion) {
        this.religion = religion;
    }

    public Integer getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(Integer educationQualification) {
        this.educationQualification = educationQualification;
    }

    public Double getLandHolding() {
        return landHolding;
    }

    public void setLandHolding(Double landHolding) {
        this.landHolding = landHolding;
    }

    public String getNameOfFirm() {
        return nameOfFirm;
    }

    public void setNameOfFirm(String nameOfFirm) {
        this.nameOfFirm = nameOfFirm;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Double getLoanAmountRequired() {
        return loanAmountRequired;
    }

    public void setLoanAmountRequired(Double loanAmountRequired) {
        this.loanAmountRequired = loanAmountRequired;
    }

    public Double getCostOfProject() {
        return costOfProject;
    }

    public void setCostOfProject(Double costOfProject) {
        this.costOfProject = costOfProject;
    }

    public Double getCostOfEquipment() {
        return costOfEquipment;
    }

    public void setCostOfEquipment(Double costOfEquipment) {
        this.costOfEquipment = costOfEquipment;
    }

    public Double getWorkingCapOfEquipment() {
        return workingCapOfEquipment;
    }

    public void setWorkingCapOfEquipment(Double workingCapOfEquipment) {
        this.workingCapOfEquipment = workingCapOfEquipment;
    }

    public Double getTotalCostEquipment() {
        return totalCostEquipment;
    }

    public void setTotalCostEquipment(Double totalCostEquipment) {
        this.totalCostEquipment = totalCostEquipment;
    }

    public Double getPromoterContribution() {
        return promoterContribution;
    }

    public void setPromoterContribution(Double promoterContribution) {
        this.promoterContribution = promoterContribution;
    }

    public Double getLoanRequiredFromSidbi() {
        return loanRequiredFromSidbi;
    }

    public void setLoanRequiredFromSidbi(Double loanRequiredFromSidbi) {
        this.loanRequiredFromSidbi = loanRequiredFromSidbi;
    }

    public Double getTotalMeanFinance() {
        return totalMeanFinance;
    }

    public void setTotalMeanFinance(Double totalMeanFinance) {
        this.totalMeanFinance = totalMeanFinance;
    }

    public Double getTotalCashFlow() {
        return totalCashFlow;
    }

    public void setTotalCashFlow(Double totalCashFlow) {
        this.totalCashFlow = totalCashFlow;
    }

    public Integer getRepaymentFrequency() {
        return repaymentFrequency;
    }

    public void setRepaymentFrequency(Integer repaymentFrequency) {
        this.repaymentFrequency = repaymentFrequency;
    }

    public Boolean getInsurenceRequired() {
        return insurenceRequired;
    }

    public void setInsurenceRequired(Boolean insurenceRequired) {
        this.insurenceRequired = insurenceRequired;
    }

    public String getInsurenceCompanyName() {
        return insurenceCompanyName;
    }

    public void setInsurenceCompanyName(String insurenceCompanyName) {
        this.insurenceCompanyName = insurenceCompanyName;
    }

    public Double getInsurencePremium() {
        return insurencePremium;
    }

    public void setInsurencePremium(Double insurencePremium) {
        this.insurencePremium = insurencePremium;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getAddressProofType() {
        return addressProofType;
    }

    public void setAddressProofType(Integer addressProofType) {
        this.addressProofType = addressProofType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }
}
