package com.capitaworld.service.loans.model.micro_finance;

import java.util.Date;
import java.util.List;

public class MfiApplicantDetailsReq {

	private Long applicationId;

	private Long applicationProposalMapping;

	private String aadharNumber;

	private String nameAsPerAadharCard;

	private String firstName;

	private String lastName;

	private String middleName;

	private Date birthDate;

	private Integer genderId;

	private String mobile;

	private String email;

	private Integer maritalStatusId;

	private Boolean addressSameAsAadhar;

	private String currentDistrict;

	private String aadharDistrict;

	private String currentHouse;

	private String aadharHouse;

	private String currentLandmark;

	private String aadharLandmark;

	private String currentLocation;

	private String aadharLocation;

	private String currentState;

	private String aadharState;

	private String currentStreet;

	private String aadharStreet;

	private String currentVtc;

	private String aadharVtc;

	private String aadharSubdist;

	private String currentSubdist;

	private String aadharPo;

	private String currentPo;

	private String aadharCareOf;

	private String addressPincode;

	private String aadharPincode;

	private String fatherName;

	private String motherName;

	private String spouseName;

	private Date spouseBirthDate;

	private String spouseMobile;

	private Integer noDependent;

	private String nomineeName;

	private Date nomineeBirthDate;

	private Integer relationWithNomineeId;

	private String nomineeAddress;

	private String nomineePincode;

	private Integer religion;

	private Integer educationQualification;

	private Double landHolding;

	private String nameOfFirm;

	private Integer businessType;

	private Integer houseType;

	private String loanPurpose;

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

	private String nomineeState;
	private String nomineeCity;
	private String nomineeDistrict;
	private String nomineeLocation;
	private String nomineeHouseNo;
	private String nomineeLandmark;
	private String academicReligion;
	private String academicCaste;
	private Boolean isAcademicLifeInsurance;

	private String houseOwnership;

	private String areaType;

	private String businessPremises;

	private String expInSameLine;

	private String academicSumInsured;

	private Integer type;

	private String remarks;

	private Boolean isPersonalDetailsFilled;

	private Boolean isFamilyDetailsFilled;

	private Boolean isNomineeDetailsFilled;

	private Boolean isAcadamicDetailsFilled;

	private Boolean isBankDetailsFilled;

	private Boolean isAccountDetailsFilled;

	private Boolean isExistingLoanDetailsFilled;

	private Boolean isIncomeDetailsFilled;

	private Boolean isFamilyIncomeFilled;

	private Boolean isFamilyExpenseFilled;

	private Boolean isExpectedIncomeFilled;

	private Boolean isPPIFilled;

	private Boolean isProjectDetailsFilled;

	private Boolean isApplyLoanFilled;

	private Boolean isCostProjectFilled;

	private Boolean isMeanFinanceFilled;

	private Boolean isCashFlowDetailsFilled;

	private Boolean isAssetsDetailsFilled;

	private Boolean isCurrentAssetsFilled;

	private Boolean isFixedAssetsFilled;

	private Boolean isCurrntLiabilityFilled;

	private Boolean isRepaymentDetailsFilled;

	private Boolean isConsentFormFilled;

	private Boolean isLoanassessmentDetailsFilled;

	private Integer addressProofType;

	private byte[] addressProofImg;
	private byte[] consentFormImg;
	private byte[] profileImg;
	private Double shipShgiInstallment;
	private Double otherInstallment;
	private Double loanInstallment;
	private Double educationExpense;
	private Double medicalExpense;
	private Double foodExpense;
	private Double otherExpense;
	private String businessNameBrief;
	private Double monthlyCashflow;
	private Double monthlyExpenditure;
	private Double monthlyIncome;
	private Integer ppiNoFamilyMember;
	private Integer ppiAcadamicHeadFamily;
	private Integer ppiRafrigeratorInFamily;
	private Integer ppiStoveInFamily;
	private Integer ppiPressureCookerInFamily;
	private Integer ppiTvInFamily;
	private Integer ppiFanInFamily;
	private Integer ppiVehicleInFamily;
	private Integer ppiDressingTableInFamily;
	private Integer ppiOtherTableInFamily;

	private Double totalMonthlyIncomeForFamily;
    private List<MfiAssetsDetailsReq> assetsDetails;
    private List<MfiAssetsDetailsReq> liabilityDetails;

    private Long bankId;

    private String branchName;

    private String accountNo;

    private String ifscCode;

    private Integer accountType;

    private byte[] passbookImg;

    private List<MfiIncomeDetailsReq> incomeDetailsReqList;
    private Integer purposeOfLoan;

    private Boolean isBusinessPremiseVisited;
    private Integer repaymentTrack;
    private Integer creaditWorthiness;
    private Integer loanLiabilityRatio;
    private Double loanAmountRecomandation;
    private Integer tenureRecomandation;
    private Integer moratoriumRecomandation;
    private Integer interestRateRecomandation;
    private Integer installmentRecomandation;
    private Integer clientType;

	private Integer businessInBrief;
	
    public Integer getBusinessInBrief() {
		return businessInBrief;
	}

	public void setBusinessInBrief(Integer businessInBrief) {
		this.businessInBrief = businessInBrief;
	}

	public Boolean getIsBusinessPremiseVisited() {
		return isBusinessPremiseVisited;
	}

	public void setIsBusinessPremiseVisited(Boolean isBusinessPremiseVisited) {
		this.isBusinessPremiseVisited = isBusinessPremiseVisited;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMaritalStatusId() {
		return maritalStatusId;
	}

	public void setMaritalStatusId(Integer maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public Boolean getAddressSameAsAadhar() {
		return addressSameAsAadhar;
	}

	public void setAddressSameAsAadhar(Boolean addressSameAsAadhar) {
		this.addressSameAsAadhar = addressSameAsAadhar;
	}

	public String getCurrentDistrict() {
		return currentDistrict;
	}

	public void setCurrentDistrict(String currentDistrict) {
		this.currentDistrict = currentDistrict;
	}

	public String getAadharDistrict() {
		return aadharDistrict;
	}

	public void setAadharDistrict(String aadharDistrict) {
		this.aadharDistrict = aadharDistrict;
	}

	public String getCurrentHouse() {
		return currentHouse;
	}

	public void setCurrentHouse(String currentHouse) {
		this.currentHouse = currentHouse;
	}

	public String getAadharHouse() {
		return aadharHouse;
	}

	public void setAadharHouse(String aadharHouse) {
		this.aadharHouse = aadharHouse;
	}

	public String getCurrentLandmark() {
		return currentLandmark;
	}

	public void setCurrentLandmark(String currentLandmark) {
		this.currentLandmark = currentLandmark;
	}

	public String getAadharLandmark() {
		return aadharLandmark;
	}

	public void setAadharLandmark(String aadharLandmark) {
		this.aadharLandmark = aadharLandmark;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getAadharLocation() {
		return aadharLocation;
	}

	public void setAadharLocation(String aadharLocation) {
		this.aadharLocation = aadharLocation;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getAadharState() {
		return aadharState;
	}

	public void setAadharState(String aadharState) {
		this.aadharState = aadharState;
	}

	public String getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(String currentStreet) {
		this.currentStreet = currentStreet;
	}

	public String getAadharStreet() {
		return aadharStreet;
	}

	public void setAadharStreet(String aadharStreet) {
		this.aadharStreet = aadharStreet;
	}

	public String getCurrentVtc() {
		return currentVtc;
	}

	public void setCurrentVtc(String currentVtc) {
		this.currentVtc = currentVtc;
	}

	public String getAadharVtc() {
		return aadharVtc;
	}

	public void setAadharVtc(String aadharVtc) {
		this.aadharVtc = aadharVtc;
	}

	public String getAadharSubdist() {
		return aadharSubdist;
	}

	public void setAadharSubdist(String aadharSubdist) {
		this.aadharSubdist = aadharSubdist;
	}

	public String getCurrentSubdist() {
		return currentSubdist;
	}

	public void setCurrentSubdist(String currentSubdist) {
		this.currentSubdist = currentSubdist;
	}

	public String getAadharPo() {
		return aadharPo;
	}

	public void setAadharPo(String aadharPo) {
		this.aadharPo = aadharPo;
	}

	public String getCurrentPo() {
		return currentPo;
	}

	public void setCurrentPo(String currentPo) {
		this.currentPo = currentPo;
	}

	public String getAadharCareOf() {
		return aadharCareOf;
	}

	public void setAadharCareOf(String aadharCareOf) {
		this.aadharCareOf = aadharCareOf;
	}

	public String getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(String addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getAadharPincode() {
		return aadharPincode;
	}

	public void setAadharPincode(String aadharPincode) {
		this.aadharPincode = aadharPincode;
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

	public Date getNomineeBirthDate() {
		return nomineeBirthDate;
	}

	public void setNomineeBirthDate(Date nomineeBirthDate) {
		this.nomineeBirthDate = nomineeBirthDate;
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

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
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

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public String getNomineeState() {
		return nomineeState;
	}

	public void setNomineeState(String nomineeState) {
		this.nomineeState = nomineeState;
	}

	public String getNomineeCity() {
		return nomineeCity;
	}

	public void setNomineeCity(String nomineeCity) {
		this.nomineeCity = nomineeCity;
	}

	public String getNomineeDistrict() {
		return nomineeDistrict;
	}

	public void setNomineeDistrict(String nomineeDistrict) {
		this.nomineeDistrict = nomineeDistrict;
	}

	public String getNomineeLocation() {
		return nomineeLocation;
	}

	public void setNomineeLocation(String nomineeLocation) {
		this.nomineeLocation = nomineeLocation;
	}

	public String getNomineeHouseNo() {
		return nomineeHouseNo;
	}

	public void setNomineeHouseNo(String nomineeHouseNo) {
		this.nomineeHouseNo = nomineeHouseNo;
	}

	public String getNomineeLandmark() {
		return nomineeLandmark;
	}

	public void setNomineeLandmark(String nomineeLandmark) {
		this.nomineeLandmark = nomineeLandmark;
	}

	public String getAcademicReligion() {
		return academicReligion;
	}

	public void setAcademicReligion(String academicReligion) {
		this.academicReligion = academicReligion;
	}

	public String getAcademicCaste() {
		return academicCaste;
	}

	public void setAcademicCaste(String academicCaste) {
		this.academicCaste = academicCaste;
	}

	public Boolean getIsAcademicLifeInsurance() {
		return isAcademicLifeInsurance;
	}

	public void setIsAcademicLifeInsurance(Boolean isAcademicLifeInsurance) {
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
	}

	public String getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getBusinessPremises() {
		return businessPremises;
	}

	public void setBusinessPremises(String businessPremises) {
		this.businessPremises = businessPremises;
	}

	public String getExpInSameLine() {
		return expInSameLine;
	}

	public void setExpInSameLine(String expInSameLine) {
		this.expInSameLine = expInSameLine;
	}

	public String getAcademicSumInsured() {
		return academicSumInsured;
	}

	public void setAcademicSumInsured(String academicSumInsured) {
		this.academicSumInsured = academicSumInsured;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getIsPersonalDetailsFilled() {
		return isPersonalDetailsFilled;
	}

	public void setIsPersonalDetailsFilled(Boolean isPersonalDetailsFilled) {
		this.isPersonalDetailsFilled = isPersonalDetailsFilled;
	}

	public Boolean getIsFamilyDetailsFilled() {
		return isFamilyDetailsFilled;
	}

	public void setIsFamilyDetailsFilled(Boolean isFamilyDetailsFilled) {
		this.isFamilyDetailsFilled = isFamilyDetailsFilled;
	}

	public Boolean getIsNomineeDetailsFilled() {
		return isNomineeDetailsFilled;
	}

	public void setIsNomineeDetailsFilled(Boolean isNomineeDetailsFilled) {
		this.isNomineeDetailsFilled = isNomineeDetailsFilled;
	}

	public Boolean getIsAcadamicDetailsFilled() {
		return isAcadamicDetailsFilled;
	}

	public void setIsAcadamicDetailsFilled(Boolean isAcadamicDetailsFilled) {
		this.isAcadamicDetailsFilled = isAcadamicDetailsFilled;
	}

	public Boolean getIsBankDetailsFilled() {
		return isBankDetailsFilled;
	}

	public void setIsBankDetailsFilled(Boolean isBankDetailsFilled) {
		this.isBankDetailsFilled = isBankDetailsFilled;
	}

	public Boolean getIsAccountDetailsFilled() {
		return isAccountDetailsFilled;
	}

	public void setIsAccountDetailsFilled(Boolean isAccountDetailsFilled) {
		this.isAccountDetailsFilled = isAccountDetailsFilled;
	}

	public Boolean getIsExistingLoanDetailsFilled() {
		return isExistingLoanDetailsFilled;
	}

	public void setIsExistingLoanDetailsFilled(Boolean isExistingLoanDetailsFilled) {
		this.isExistingLoanDetailsFilled = isExistingLoanDetailsFilled;
	}

	public Boolean getIsIncomeDetailsFilled() {
		return isIncomeDetailsFilled;
	}

	public void setIsIncomeDetailsFilled(Boolean isIncomeDetailsFilled) {
		this.isIncomeDetailsFilled = isIncomeDetailsFilled;
	}

	public Boolean getIsFamilyIncomeFilled() {
		return isFamilyIncomeFilled;
	}

	public void setIsFamilyIncomeFilled(Boolean isFamilyIncomeFilled) {
		this.isFamilyIncomeFilled = isFamilyIncomeFilled;
	}

	public Boolean getIsFamilyExpenseFilled() {
		return isFamilyExpenseFilled;
	}

	public void setIsFamilyExpenseFilled(Boolean isFamilyExpenseFilled) {
		this.isFamilyExpenseFilled = isFamilyExpenseFilled;
	}

	public Boolean getIsExpectedIncomeFilled() {
		return isExpectedIncomeFilled;
	}

	public void setIsExpectedIncomeFilled(Boolean isExpectedIncomeFilled) {
		this.isExpectedIncomeFilled = isExpectedIncomeFilled;
	}

	public Boolean getIsPPIFilled() {
		return isPPIFilled;
	}

	public void setIsPPIFilled(Boolean isPPIFilled) {
		this.isPPIFilled = isPPIFilled;
	}

	public Boolean getIsProjectDetailsFilled() {
		return isProjectDetailsFilled;
	}

	public void setIsProjectDetailsFilled(Boolean isProjectDetailsFilled) {
		this.isProjectDetailsFilled = isProjectDetailsFilled;
	}

	public Boolean getIsApplyLoanFilled() {
		return isApplyLoanFilled;
	}

	public void setIsApplyLoanFilled(Boolean isApplyLoanFilled) {
		this.isApplyLoanFilled = isApplyLoanFilled;
	}

	public Boolean getIsCostProjectFilled() {
		return isCostProjectFilled;
	}

	public void setIsCostProjectFilled(Boolean isCostProjectFilled) {
		this.isCostProjectFilled = isCostProjectFilled;
	}

	public Boolean getIsMeanFinanceFilled() {
		return isMeanFinanceFilled;
	}

	public void setIsMeanFinanceFilled(Boolean isMeanFinanceFilled) {
		this.isMeanFinanceFilled = isMeanFinanceFilled;
	}

	public Boolean getIsCashFlowDetailsFilled() {
		return isCashFlowDetailsFilled;
	}

	public void setIsCashFlowDetailsFilled(Boolean isCashFlowDetailsFilled) {
		this.isCashFlowDetailsFilled = isCashFlowDetailsFilled;
	}

	public Boolean getIsAssetsDetailsFilled() {
		return isAssetsDetailsFilled;
	}

	public void setIsAssetsDetailsFilled(Boolean isAssetsDetailsFilled) {
		this.isAssetsDetailsFilled = isAssetsDetailsFilled;
	}

	public Boolean getIsCurrentAssetsFilled() {
		return isCurrentAssetsFilled;
	}

	public void setIsCurrentAssetsFilled(Boolean isCurrentAssetsFilled) {
		this.isCurrentAssetsFilled = isCurrentAssetsFilled;
	}

	public Boolean getIsFixedAssetsFilled() {
		return isFixedAssetsFilled;
	}

	public void setIsFixedAssetsFilled(Boolean isFixedAssetsFilled) {
		this.isFixedAssetsFilled = isFixedAssetsFilled;
	}

	public Boolean getIsCurrntLiabilityFilled() {
		return isCurrntLiabilityFilled;
	}

	public void setIsCurrntLiabilityFilled(Boolean isCurrntLiabilityFilled) {
		this.isCurrntLiabilityFilled = isCurrntLiabilityFilled;
	}

	public Boolean getIsRepaymentDetailsFilled() {
		return isRepaymentDetailsFilled;
	}

	public void setIsRepaymentDetailsFilled(Boolean isRepaymentDetailsFilled) {
		this.isRepaymentDetailsFilled = isRepaymentDetailsFilled;
	}

	public Boolean getIsConsentFormFilled() {
		return isConsentFormFilled;
	}

	public void setIsConsentFormFilled(Boolean isConsentFormFilled) {
		this.isConsentFormFilled = isConsentFormFilled;
	}

	public Integer getAddressProofType() {
		return addressProofType;
	}

	public void setAddressProofType(Integer addressProofType) {
		this.addressProofType = addressProofType;
	}

	public byte[] getAddressProofImg() {
		return addressProofImg;
	}

	public void setAddressProofImg(byte[] addressProofImg) {
		this.addressProofImg = addressProofImg;
	}

	public byte[] getConsentFormImg() {
		return consentFormImg;
	}

	public void setConsentFormImg(byte[] consentFormImg) {
		this.consentFormImg = consentFormImg;
	}

	public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
	}

	public Double getShipShgiInstallment() {
		return shipShgiInstallment;
	}

	public void setShipShgiInstallment(Double shipShgiInstallment) {
		this.shipShgiInstallment = shipShgiInstallment;
	}

	public Double getOtherInstallment() {
		return otherInstallment;
	}

	public void setOtherInstallment(Double otherInstallment) {
		this.otherInstallment = otherInstallment;
	}

	public Double getLoanInstallment() {
		return loanInstallment;
	}

	public void setLoanInstallment(Double loanInstallment) {
		this.loanInstallment = loanInstallment;
	}

	public Double getEducationExpense() {
		return educationExpense;
	}

	public void setEducationExpense(Double educationExpense) {
		this.educationExpense = educationExpense;
	}

	public Double getMedicalExpense() {
		return medicalExpense;
	}

	public void setMedicalExpense(Double medicalExpense) {
		this.medicalExpense = medicalExpense;
	}

	public Double getFoodExpense() {
		return foodExpense;
	}

	public void setFoodExpense(Double foodExpense) {
		this.foodExpense = foodExpense;
	}

	public Double getOtherExpense() {
		return otherExpense;
	}

	public void setOtherExpense(Double otherExpense) {
		this.otherExpense = otherExpense;
	}

	public String getBusinessNameBrief() {
		return businessNameBrief;
	}

	public void setBusinessNameBrief(String businessNameBrief) {
		this.businessNameBrief = businessNameBrief;
	}

	public Double getMonthlyCashflow() {
		return monthlyCashflow;
	}

	public void setMonthlyCashflow(Double monthlyCashflow) {
		this.monthlyCashflow = monthlyCashflow;
	}

	public Double getMonthlyExpenditure() {
		return monthlyExpenditure;
	}

	public void setMonthlyExpenditure(Double monthlyExpenditure) {
		this.monthlyExpenditure = monthlyExpenditure;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getPpiNoFamilyMember() {
		return ppiNoFamilyMember;
	}

	public void setPpiNoFamilyMember(Integer ppiNoFamilyMember) {
		this.ppiNoFamilyMember = ppiNoFamilyMember;
	}

	public Integer getPpiAcadamicHeadFamily() {
		return ppiAcadamicHeadFamily;
	}

	public void setPpiAcadamicHeadFamily(Integer ppiAcadamicHeadFamily) {
		this.ppiAcadamicHeadFamily = ppiAcadamicHeadFamily;
	}

	public Integer getPpiRafrigeratorInFamily() {
		return ppiRafrigeratorInFamily;
	}

	public void setPpiRafrigeratorInFamily(Integer ppiRafrigeratorInFamily) {
		this.ppiRafrigeratorInFamily = ppiRafrigeratorInFamily;
	}

	public Integer getPpiStoveInFamily() {
		return ppiStoveInFamily;
	}

	public void setPpiStoveInFamily(Integer ppiStoveInFamily) {
		this.ppiStoveInFamily = ppiStoveInFamily;
	}

	public Integer getPpiPressureCookerInFamily() {
		return ppiPressureCookerInFamily;
	}

	public void setPpiPressureCookerInFamily(Integer ppiPressureCookerInFamily) {
		this.ppiPressureCookerInFamily = ppiPressureCookerInFamily;
	}

	public Integer getPpiTvInFamily() {
		return ppiTvInFamily;
	}

	public void setPpiTvInFamily(Integer ppiTvInFamily) {
		this.ppiTvInFamily = ppiTvInFamily;
	}

	public Integer getPpiFanInFamily() {
		return ppiFanInFamily;
	}

	public void setPpiFanInFamily(Integer ppiFanInFamily) {
		this.ppiFanInFamily = ppiFanInFamily;
	}

	public Integer getPpiVehicleInFamily() {
		return ppiVehicleInFamily;
	}

	public void setPpiVehicleInFamily(Integer ppiVehicleInFamily) {
		this.ppiVehicleInFamily = ppiVehicleInFamily;
	}

	public Integer getPpiDressingTableInFamily() {
		return ppiDressingTableInFamily;
	}

	public void setPpiDressingTableInFamily(Integer ppiDressingTableInFamily) {
		this.ppiDressingTableInFamily = ppiDressingTableInFamily;
	}

	public Integer getPpiOtherTableInFamily() {
		return ppiOtherTableInFamily;
	}

	public void setPpiOtherTableInFamily(Integer ppiOtherTableInFamily) {
		this.ppiOtherTableInFamily = ppiOtherTableInFamily;
	}

	public Boolean getIsLoanassessmentDetailsFilled() {
		return isLoanassessmentDetailsFilled;
	}

	public void setIsLoanassessmentDetailsFilled(Boolean isLoanassessmentDetailsFilled) {
		this.isLoanassessmentDetailsFilled = isLoanassessmentDetailsFilled;
	}

	public Double getTotalMonthlyIncomeForFamily() {
		return totalMonthlyIncomeForFamily;
	}

	public void setTotalMonthlyIncomeForFamily(Double totalMonthlyIncomeForFamily) {
		this.totalMonthlyIncomeForFamily = totalMonthlyIncomeForFamily;
	}

    public List<MfiAssetsDetailsReq> getAssetsDetails() {
        return assetsDetails;
    }

    public void setAssetsDetails(List<MfiAssetsDetailsReq> assetsDetails) {
        this.assetsDetails = assetsDetails;
    }

    public List<MfiAssetsDetailsReq> getLiabilityDetails() {
        return liabilityDetails;
    }

    public void setLiabilityDetails(List<MfiAssetsDetailsReq> liabilityDetails) {
        this.liabilityDetails = liabilityDetails;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public byte[] getPassbookImg() {
        return passbookImg;
    }

    public void setPassbookImg(byte[] passbookImg) {
        this.passbookImg = passbookImg;
    }

    public List<MfiIncomeDetailsReq> getIncomeDetailsReqList() {
        return incomeDetailsReqList;
    }

    public void setIncomeDetailsReqList(List<MfiIncomeDetailsReq> incomeDetailsReqList) {
        this.incomeDetailsReqList = incomeDetailsReqList;
    }

    public Integer getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(Integer purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public Boolean getBusinessPremiseVisited() {
        return isBusinessPremiseVisited;
    }

    public void setBusinessPremiseVisited(Boolean businessPremiseVisited) {
        isBusinessPremiseVisited = businessPremiseVisited;
    }

    public Integer getRepaymentTrack() {
        return repaymentTrack;
    }

    public void setRepaymentTrack(Integer repaymentTrack) {
        this.repaymentTrack = repaymentTrack;
    }

    public Integer getCreaditWorthiness() {
        return creaditWorthiness;
    }

    public void setCreaditWorthiness(Integer creaditWorthiness) {
        this.creaditWorthiness = creaditWorthiness;
    }

    public Integer getLoanLiabilityRatio() {
        return loanLiabilityRatio;
    }

    public void setLoanLiabilityRatio(Integer loanLiabilityRatio) {
        this.loanLiabilityRatio = loanLiabilityRatio;
    }

    public Double getLoanAmountRecomandation() {
        return loanAmountRecomandation;
    }

    public void setLoanAmountRecomandation(Double loanAmountRecomandation) {
        this.loanAmountRecomandation = loanAmountRecomandation;
    }

    public Integer getTenureRecomandation() {
        return tenureRecomandation;
    }

    public void setTenureRecomandation(Integer tenureRecomandation) {
        this.tenureRecomandation = tenureRecomandation;
    }

    public Integer getMoratoriumRecomandation() {
        return moratoriumRecomandation;
    }

    public void setMoratoriumRecomandation(Integer moratoriumRecomandation) {
        this.moratoriumRecomandation = moratoriumRecomandation;
    }

    public Integer getInterestRateRecomandation() {
        return interestRateRecomandation;
    }

    public void setInterestRateRecomandation(Integer interestRateRecomandation) {
        this.interestRateRecomandation = interestRateRecomandation;
    }

    public Integer getInstallmentRecomandation() {
        return installmentRecomandation;
    }

    public void setInstallmentRecomandation(Integer installmentRecomandation) {
        this.installmentRecomandation = installmentRecomandation;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    
}
