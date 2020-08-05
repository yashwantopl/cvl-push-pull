package com.opl.mudra.api.loans.model.micro_finance;

import java.util.Date;
import java.util.List;

import org.springframework.core.io.Resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opl.mudra.api.loans.model.LoanDisbursementRequest;
import com.opl.mudra.api.loans.model.LoanSanctionRequest;
import com.opl.mudra.api.loans.model.mfi.MFIFinancialArrangementRequest;

public class MfiApplicantDetailsReq {

	private Long applicationId;

	private Long applicationProposalMapping;

	private String aadharNumber;

	private String nameAsPerAadharCard;

	private String firstName;

	private String lastName;

	private String middleName;
	
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date spouseBirthDate;

	private String spouseMobile;

	private Integer noDependent;

	private String nomineeName;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
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

	private Integer academicReligion;

	private Integer academicCaste;

	private Integer isAcademicLifeInsurance;

	private Integer houseOwnership;

	private Integer areaType;

	private Integer businessPremises;

	private Integer expInSameLine;

	private Double academicSumInsured;

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

	private String addressProofImg;
	private String consentFormImg;
	private String profileImg;
	private String aadharImg;
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
	
	private Double ppiNoFamilyMemberScore;
	private Double ppiAcadamicHeadFamilyScore;
	private Double ppiRafrigeratorInFamilyScore;
	private Double ppiStoveInFamilyScore;
	private Double ppiPressureCookerInFamilyScore;
	private Double ppiTvInFamilyScore;
	private Double ppiFanInFamilyScore;
	private Double ppiVehicleInFamilyScore;
	private Double ppiDressingTableInFamilyScore;
	private Double ppiOtherTableInFamilyScore;

	private Double totalMonthlyIncomeForFamily;
	private List<MfiAssetsDetailsReq> assetsDetails;
	private List<MfiAssetsDetailsReq> liabilityDetails;
	private List<AadharDetailsReq> coApplicantDetails;
	private Double totalExpense;

	private Long bankId;

	private String branchName;
	private String bankName;

	private String acHolderName;

	private String accountNo;

	private String ifscCode;

	private Integer accountType;

	private String passbookImg;

	private List<MfiIncomeDetailsReq> incomeDetailsReqList;
	private List<MfiIncomeDetailsReq> incomeDetailsTypeTwoList;

	private MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReqMFIMaker;

	private MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReqMFIChecker;

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

	// For Cash Flow Assessment
	private Double incomeFromOccupation;
	private Double existingExpensesOfFamily;
	private Double netSaving;
	private Double expectedIncome;
	private Integer competition;
	private Long id;

	private Integer addressProfType;

	private String addressProofNo;

	private Integer businessTypeId;

	private Double amount;

	private Double outstanding;

	private Integer particulars;

	private Double houseHoldExpense;
	private Double clothesExpense;

	private Double loanAmountMFIChecker;

	private Double loanAmountBankMaker;

	private Double totalEmi;

	private Long jobId;

	private Integer status;

	private Integer loanTenure;
	
	private List<MFIFinancialArrangementRequest> financialArrangementDetails;
	
	private LoanSanctionRequest sanctionDetail;
	
	private List<LoanDisbursementRequest> disbursementDetails;
	
	/* STRING FOR ENUMS */
	private String maritalStatus;
	private String gender;
	private String relationWithNominee;
	private String eduQualification;
	private String houseType1;
	private String repayFreq;
	private String academicReli;
	private String academicCast;
	private String houseOwnerShip;
	private String areaType1;
	private String businessPremises1;
	private String addressProofType1;
	private String dateOfBirth;
	private String businessType1;
	private String accountType1;
	private String businessNameString;
	private String ppiAcadamicHeadFamily1;
	private String loanTypeString;
	private String purposeOfLoanString;
	private String clientType1;
	private String repayTrack;
	private String competition1;
	private List<Resource> listOfImages;
	
	List<String> byteList; 
	private Double mfiMakerTotalExpense;
	private Double mfiCheckerTotalExpense;
	private Double totalIncomeChecker;
	private Double netSavingChecker;
	private Double increasedIncomeChecker;
	private Double totalCashFlowChecker;
	

	private String dpnDoc;
	private String loiDoc;
	private String lohDoc;
	private String agreementDoc;
	
	private String dpnDocFileName;
	private String loiDocFileName;
	private String lohDocFileName;
	private String agreementDocFileName;
	

	private Long userId;
	private Long orgId;
	private String makerName;
    private String loanPurposeStr;
    private String currDateStr;
    private String piName;
    private String piAddress;
    private String loginUser;
    private Boolean isConsolidated;
    private String consolidatedName;

    	List<String> byteListAddProof;
	List<String> ByteListPassImg;
	private List<String> coAppAddProof;
	

	/* GETTER SETTERS */
	
	public String getMaritalStatus() {
		return maritalStatus;
	}


	

	public Double getTotalCashFlowChecker() {
		return totalCashFlowChecker;
	}




	public void setTotalCashFlowChecker(Double totalCashFlowChecker) {
		this.totalCashFlowChecker = totalCashFlowChecker;
	}




	public Double getIncreasedIncomeChecker() {
		return increasedIncomeChecker;
	}




	public void setIncreasedIncomeChecker(Double increasedIncomeChecker) {
		this.increasedIncomeChecker = increasedIncomeChecker;
	}




	public Double getNetSavingChecker() {
		return netSavingChecker;
	}




	public void setNetSavingChecker(Double netSavingChecker) {
		this.netSavingChecker = netSavingChecker;
	}




	public Double getTotalIncomeChecker() {
		return totalIncomeChecker;
	}




	public void setTotalIncomeChecker(Double totalIncomeChecker) {
		this.totalIncomeChecker = totalIncomeChecker;
	}




	public Double getMfiMakerTotalExpense() {
		return mfiMakerTotalExpense;
	}




	public void setMfiMakerTotalExpense(Double mfiMakerTotalExpense) {
		this.mfiMakerTotalExpense = mfiMakerTotalExpense;
	}




	public Double getMfiCheckerTotalExpense() {
		return mfiCheckerTotalExpense;
	}




	public void setMfiCheckerTotalExpense(Double mfiCheckerTotalExpense) {
		this.mfiCheckerTotalExpense = mfiCheckerTotalExpense;
	}




	public List<Resource> getListOfImages() {
		return listOfImages;
	}




	public void setListOfImages(List<Resource> listOfImages) {
		this.listOfImages = listOfImages;
	}




	public String getCompetition1() {
		return competition1;
	}


	public void setCompetition1(String competition1) {
		this.competition1 = competition1;
	}


	public String getRepayTrack() {
		return repayTrack;
	}


	public void setRepayTrack(String repayTrack) {
		this.repayTrack = repayTrack;
	}


	public String getClientType1() {
		return clientType1;
	}


	public void setClientType1(String clientType1) {
		this.clientType1 = clientType1;
	}


	public String getPurposeOfLoanString() {
		return purposeOfLoanString;
	}


	public void setPurposeOfLoanString(String purposeOfLoanString) {
		this.purposeOfLoanString = purposeOfLoanString;
	}


	public String getLoanTypeString() {
		return loanTypeString;
	}


	public void setLoanTypeString(String loanTypeString) {
		this.loanTypeString = loanTypeString;
	}


	public String getPpiAcadamicHeadFamily1() {
		return ppiAcadamicHeadFamily1;
	}


	public void setPpiAcadamicHeadFamily1(String ppiAcadamicHeadFamily1) {
		this.ppiAcadamicHeadFamily1 = ppiAcadamicHeadFamily1;
	}


	public String getBusinessNameString() {
		return businessNameString;
	}


	public void setBusinessNameString(String businessNameString) {
		this.businessNameString = businessNameString;
	}


	public String getAccountType1() {
		return accountType1;
	}


	public void setAccountType1(String accountType1) {
		this.accountType1 = accountType1;
	}


	public String getBusinessType1() {
		return businessType1;
	}


	public void setBusinessType1(String businessType1) {
		this.businessType1 = businessType1;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRelationWithNominee() {
		return relationWithNominee;
	}

	public void setRelationWithNominee(String relationWithNominee) {
		this.relationWithNominee = relationWithNominee;
	}

	public String getEduQualification() {
		return eduQualification;
	}

	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}

	public String getHouseType1() {
		return houseType1;
	}

	public void setHouseType1(String houseType1) {
		this.houseType1 = houseType1;
	}

	public String getRepayFreq() {
		return repayFreq;
	}

	public void setRepayFreq(String repayFreq) {
		this.repayFreq = repayFreq;
	}

	public String getAcademicReli() {
		return academicReli;
	}

	public void setAcademicReli(String academicReli) {
		this.academicReli = academicReli;
	}

	public String getAcademicCast() {
		return academicCast;
	}

	public void setAcademicCast(String academicCast) {
		this.academicCast = academicCast;
	}

	public String getHouseOwnerShip() {
		return houseOwnerShip;
	}

	public void setHouseOwnerShip(String houseOwnerShip) {
		this.houseOwnerShip = houseOwnerShip;
	}

	public String getAreaType1() {
		return areaType1;
	}

	public void setAreaType1(String areaType1) {
		this.areaType1 = areaType1;
	}

	public String getBusinessPremises1() {
		return businessPremises1;
	}

	public void setBusinessPremises1(String businessPremises1) {
		this.businessPremises1 = businessPremises1;
	}

	public String getAddressProofType1() {
		return addressProofType1;
	}

	public void setAddressProofType1(String addressProofType1) {
		this.addressProofType1 = addressProofType1;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	/* GETTER SETTERS */
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public MfiIncomeAndExpenditureReq getMfiIncomeAndExpenditureReqMFIMaker() {
		return mfiIncomeAndExpenditureReqMFIMaker;
	}

	public void setMfiIncomeAndExpenditureReqMFIMaker(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReqMFIMaker) {
		this.mfiIncomeAndExpenditureReqMFIMaker = mfiIncomeAndExpenditureReqMFIMaker;
	}

	public MfiIncomeAndExpenditureReq getMfiIncomeAndExpenditureReqMFIChecker() {
		return mfiIncomeAndExpenditureReqMFIChecker;
	}

	public void setMfiIncomeAndExpenditureReqMFIChecker(
			MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReqMFIChecker) {
		this.mfiIncomeAndExpenditureReqMFIChecker = mfiIncomeAndExpenditureReqMFIChecker;
	}

	public Double getLoanAmountMFIChecker() {
		return loanAmountMFIChecker;
	}

	public void setLoanAmountMFIChecker(Double loanAmountMFIChecker) {
		this.loanAmountMFIChecker = loanAmountMFIChecker;
	}

	public Double getLoanAmountBankMaker() {
		return loanAmountBankMaker;
	}

	public void setLoanAmountBankMaker(Double loanAmountBankMaker) {
		this.loanAmountBankMaker = loanAmountBankMaker;
	}

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

	public String getPassbookImg() {
		return passbookImg;
	}

	public void setPassbookImg(String passbookImg) {
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

	public Double getIncomeFromOccupation() {
		return incomeFromOccupation;
	}

	public void setIncomeFromOccupation(Double incomeFromOccupation) {
		this.incomeFromOccupation = incomeFromOccupation;
	}

	public Double getExistingExpensesOfFamily() {
		return existingExpensesOfFamily;
	}

	public void setExistingExpensesOfFamily(Double existingExpensesOfFamily) {
		this.existingExpensesOfFamily = existingExpensesOfFamily;
	}

	public Double getNetSaving() {
		return netSaving;
	}

	public void setNetSaving(Double netSaving) {
		this.netSaving = netSaving;
	}

	public Double getExpectedIncome() {
		return expectedIncome;
	}

	public void setExpectedIncome(Double expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public Double getTotalExpense() {
		return totalExpense;
	}

	public void setTotalExpense(Double totalExpense) {
		this.totalExpense = totalExpense;
	}

	public Integer getAddressProfType() {
		return addressProfType;
	}

	public void setAddressProfType(Integer addressProfType) {
		this.addressProfType = addressProfType;
	}

	public String getAddressProofNo() {
		return addressProofNo;
	}

	public void setAddressProofNo(String addressProofNo) {
		this.addressProofNo = addressProofNo;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(Double outstanding) {
		this.outstanding = outstanding;
	}

	public Integer getParticulars() {
		return particulars;
	}

	public void setParticulars(Integer particulars) {
		this.particulars = particulars;
	}

	public Double getHouseHoldExpense() {
		return houseHoldExpense;
	}

	public void setHouseHoldExpense(Double houseHoldExpense) {
		this.houseHoldExpense = houseHoldExpense;
	}

	public Double getClothesExpense() {
		return clothesExpense;
	}

	public void setClothesExpense(Double clothesExpense) {
		this.clothesExpense = clothesExpense;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCompetition() {
		return competition;
	}

	public void setCompetition(Integer competition) {
		this.competition = competition;
	}

	public List<MfiIncomeDetailsReq> getIncomeDetailsTypeTwoList() {
		return incomeDetailsTypeTwoList;
	}

	public void setIncomeDetailsTypeTwoList(List<MfiIncomeDetailsReq> incomeDetailsTypeTwoList) {
		this.incomeDetailsTypeTwoList = incomeDetailsTypeTwoList;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Integer getAcademicReligion() {
		return academicReligion;
	}

	public void setAcademicReligion(Integer academicReligion) {
		this.academicReligion = academicReligion;
	}

	public Integer getIsAcademicLifeInsurance() {
		return isAcademicLifeInsurance;
	}

	public void setIsAcademicLifeInsurance(Integer isAcademicLifeInsurance) {
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
	}

	public Integer getAcademicCaste() {
		return academicCaste;
	}

	public void setAcademicCaste(Integer academicCaste) {
		this.academicCaste = academicCaste;
	}

	public Integer getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(Integer houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public Integer getBusinessPremises() {
		return businessPremises;
	}

	public void setBusinessPremises(Integer businessPremises) {
		this.businessPremises = businessPremises;
	}

	public Integer getExpInSameLine() {
		return expInSameLine;
	}

	public void setExpInSameLine(Integer expInSameLine) {
		this.expInSameLine = expInSameLine;
	}

	public Double getAcademicSumInsured() {
		return academicSumInsured;
	}

	public void setAcademicSumInsured(Double academicSumInsured) {
		this.academicSumInsured = academicSumInsured;
	}

	public String getAcHolderName() {
		return acHolderName;
	}

	public void setAcHolderName(String acHolderName) {
		this.acHolderName = acHolderName;
	}

	public String getAddressProofImg() {
		return addressProofImg;
	}

	public void setAddressProofImg(String addressProofImg) {
		this.addressProofImg = addressProofImg;
	}

	public String getConsentFormImg() {
		return consentFormImg;
	}

	public void setConsentFormImg(String consentFormImg) {
		this.consentFormImg = consentFormImg;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getAadharImg() {
		return aadharImg;
	}

	public void setAadharImg(String aadharImg) {
		this.aadharImg = aadharImg;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public List<MFIFinancialArrangementRequest> getFinancialArrangementDetails() {
		return financialArrangementDetails;
	}

	public void setFinancialArrangementDetails(List<MFIFinancialArrangementRequest> financialArrangementDetails) {
		this.financialArrangementDetails = financialArrangementDetails;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public List<AadharDetailsReq> getCoApplicantDetails() {
		return coApplicantDetails;
	}

	public void setCoApplicantDetails(List<AadharDetailsReq> coApplicantDetails) {
		this.coApplicantDetails = coApplicantDetails;
	}

	public LoanSanctionRequest getSanctionDetail() {
		return sanctionDetail;
	}

	public void setSanctionDetail(LoanSanctionRequest sanctionDetail) {
		this.sanctionDetail = sanctionDetail;
	}

	public List<LoanDisbursementRequest> getDisbursementDetails() {
		return disbursementDetails;
	}

	public void setDisbursementDetails(List<LoanDisbursementRequest> disbursementDetails) {
		this.disbursementDetails = disbursementDetails;
	}

	public Double getPpiNoFamilyMemberScore() {
		return ppiNoFamilyMemberScore;
	}

	public void setPpiNoFamilyMemberScore(Double ppiNoFamilyMemberScore) {
		this.ppiNoFamilyMemberScore = ppiNoFamilyMemberScore;
	}

	public Double getPpiAcadamicHeadFamilyScore() {
		return ppiAcadamicHeadFamilyScore;
	}

	public void setPpiAcadamicHeadFamilyScore(Double ppiAcadamicHeadFamilyScore) {
		this.ppiAcadamicHeadFamilyScore = ppiAcadamicHeadFamilyScore;
	}

	public Double getPpiRafrigeratorInFamilyScore() {
		return ppiRafrigeratorInFamilyScore;
	}

	public void setPpiRafrigeratorInFamilyScore(Double ppiRafrigeratorInFamilyScore) {
		this.ppiRafrigeratorInFamilyScore = ppiRafrigeratorInFamilyScore;
	}

	public Double getPpiStoveInFamilyScore() {
		return ppiStoveInFamilyScore;
	}

	public void setPpiStoveInFamilyScore(Double ppiStoveInFamilyScore) {
		this.ppiStoveInFamilyScore = ppiStoveInFamilyScore;
	}

	public Double getPpiPressureCookerInFamilyScore() {
		return ppiPressureCookerInFamilyScore;
	}

	public void setPpiPressureCookerInFamilyScore(Double ppiPressureCookerInFamilyScore) {
		this.ppiPressureCookerInFamilyScore = ppiPressureCookerInFamilyScore;
	}

	public Double getPpiTvInFamilyScore() {
		return ppiTvInFamilyScore;
	}

	public void setPpiTvInFamilyScore(Double ppiTvInFamilyScore) {
		this.ppiTvInFamilyScore = ppiTvInFamilyScore;
	}

	public Double getPpiFanInFamilyScore() {
		return ppiFanInFamilyScore;
	}

	public void setPpiFanInFamilyScore(Double ppiFanInFamilyScore) {
		this.ppiFanInFamilyScore = ppiFanInFamilyScore;
	}

	public Double getPpiVehicleInFamilyScore() {
		return ppiVehicleInFamilyScore;
	}

	public void setPpiVehicleInFamilyScore(Double ppiVehicleInFamilyScore) {
		this.ppiVehicleInFamilyScore = ppiVehicleInFamilyScore;
	}

	public Double getPpiDressingTableInFamilyScore() {
		return ppiDressingTableInFamilyScore;
	}

	public void setPpiDressingTableInFamilyScore(Double ppiDressingTableInFamilyScore) {
		this.ppiDressingTableInFamilyScore = ppiDressingTableInFamilyScore;
	}

	public Double getPpiOtherTableInFamilyScore() {
		return ppiOtherTableInFamilyScore;
	}

	public void setPpiOtherTableInFamilyScore(Double ppiOtherTableInFamilyScore) {
		this.ppiOtherTableInFamilyScore = ppiOtherTableInFamilyScore;
	}

	public Double getTotalEmi() {
		return totalEmi;
	}

	public void setTotalEmi(Double totalEmi) {
		this.totalEmi = totalEmi;
	}




	public List<String> getByteList() {
		return byteList;
	}




	public void setByteList(List<String> byteList) {
		this.byteList = byteList;
	}
	
	




	public List<String> getByteListAddProof() {
		return byteListAddProof;
	}




	public void setByteListAddProof(List<String> byteListAddProof) {
		this.byteListAddProof = byteListAddProof;
	}




	public List<String> getByteListPassImg() {
		return ByteListPassImg;
	}




	public void setByteListPassImg(List<String> byteListPassImg) {
		ByteListPassImg = byteListPassImg;
	}



	public String getDpnDoc() {
		return dpnDoc;
  }
	public String getMakerName() {
		return makerName;
	}





	public void setDpnDoc(String dpnDoc) {
		this.dpnDoc = dpnDoc;
  }
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}





	public String getLoiDoc() {
		return loiDoc;
  }
	public String getLoanPurposeStr() {
		return loanPurposeStr;
	}





	public void setLoiDoc(String loiDoc) {
		this.loiDoc = loiDoc;
  }
	public void setLoanPurposeStr(String loanPurposeStr) {
		this.loanPurposeStr = loanPurposeStr;
	}





	public String getLohDoc() {
		return lohDoc;
  }
	public String getCurrDateStr() {
		return currDateStr;
	}





	public void setLohDoc(String lohDoc) {
		this.lohDoc = lohDoc;
  }
	public void setCurrDateStr(String currDateStr) {
		this.currDateStr = currDateStr;
	}





	public String getAgreementDoc() {
		return agreementDoc;
  }
	public String getPiName() {
		return piName;
	}





	public void setAgreementDoc(String agreementDoc) {
		this.agreementDoc = agreementDoc;
  }
	public void setPiName(String piName) {
		this.piName = piName;
	}




	public String getDpnDocFileName() {
		return dpnDocFileName;
  }
	public String getPiAddress() {
		return piAddress;
	}





	public void setDpnDocFileName(String dpnDocFileName) {
		this.dpnDocFileName = dpnDocFileName;
  }
	public void setPiAddress(String piAddress) {
		this.piAddress = piAddress;
	}




	public String getLoiDocFileName() {
		return loiDocFileName;
  }
	public String getLoginUser() {
		return loginUser;
	}




	public void setLoiDocFileName(String loiDocFileName) {
		this.loiDocFileName = loiDocFileName;
  }
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}




	public String getLohDocFileName() {
		return lohDocFileName;
  }
	public Boolean getIsConsolidated() {
		return isConsolidated;
	}




	public void setLohDocFileName(String lohDocFileName) {
		this.lohDocFileName = lohDocFileName;
  }
	public void setIsConsolidated(Boolean isConsolidated) {
		this.isConsolidated = isConsolidated;
	}





	public String getAgreementDocFileName() {
		return agreementDocFileName;
  }
	public String getConsolidatedName() {
		return consolidatedName;
	}




	public void setAgreementDocFileName(String agreementDocFileName) {
		this.agreementDocFileName = agreementDocFileName;
  }
	public void setConsolidatedName(String consolidatedName) {
		this.consolidatedName = consolidatedName;
	}



	public Long getUserId() {
		return userId;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}




	public Long getOrgId() {
		return orgId;
	}




	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	

	public List<String> getCoAppAddProof() {
		return coAppAddProof;
	}

	public void setCoAppAddProof(List<String> coAppAddProof) {
		this.coAppAddProof = coAppAddProof;
	}
    




	@Override
	public String toString() {
		return "MfiApplicantDetailsReq [applicationId=" + applicationId + ", applicationProposalMapping="
				+ applicationProposalMapping + ", aadharNumber=" + aadharNumber + ", nameAsPerAadharCard="
				+ nameAsPerAadharCard + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", birthDate=" + birthDate + ", genderId=" + genderId + ", mobile=" + mobile + ", email="
				+ email + ", maritalStatusId=" + maritalStatusId + ", addressSameAsAadhar=" + addressSameAsAadhar
				+ ", currentDistrict=" + currentDistrict + ", aadharDistrict=" + aadharDistrict + ", currentHouse="
				+ currentHouse + ", aadharHouse=" + aadharHouse + ", currentLandmark=" + currentLandmark
				+ ", aadharLandmark=" + aadharLandmark + ", currentLocation=" + currentLocation + ", aadharLocation="
				+ aadharLocation + ", currentState=" + currentState + ", aadharState=" + aadharState
				+ ", currentStreet=" + currentStreet + ", aadharStreet=" + aadharStreet + ", currentVtc=" + currentVtc
				+ ", aadharVtc=" + aadharVtc + ", aadharSubdist=" + aadharSubdist + ", currentSubdist=" + currentSubdist
				+ ", aadharPo=" + aadharPo + ", currentPo=" + currentPo + ", aadharCareOf=" + aadharCareOf
				+ ", addressPincode=" + addressPincode + ", aadharPincode=" + aadharPincode + ", fatherName="
				+ fatherName + ", motherName=" + motherName + ", spouseName=" + spouseName + ", spouseBirthDate="
				+ spouseBirthDate + ", spouseMobile=" + spouseMobile + ", noDependent=" + noDependent + ", nomineeName="
				+ nomineeName + ", nomineeBirthDate=" + nomineeBirthDate + ", relationWithNomineeId="
				+ relationWithNomineeId + ", nomineeAddress=" + nomineeAddress + ", nomineePincode=" + nomineePincode
				+ ", religion=" + religion + ", educationQualification=" + educationQualification + ", landHolding="
				+ landHolding + ", nameOfFirm=" + nameOfFirm + ", businessType=" + businessType + ", houseType="
				+ houseType + ", loanAmountRequired=" + loanAmountRequired + ", costOfProject=" + costOfProject
				+ ", costOfEquipment=" + costOfEquipment + ", workingCapOfEquipment=" + workingCapOfEquipment
				+ ", totalCostEquipment=" + totalCostEquipment + ", promoterContribution=" + promoterContribution
				+ ", loanRequiredFromSidbi=" + loanRequiredFromSidbi + ", totalMeanFinance=" + totalMeanFinance
				+ ", totalCashFlow=" + totalCashFlow + ", repaymentFrequency=" + repaymentFrequency
				+ ", insurenceRequired=" + insurenceRequired + ", insurenceCompanyName=" + insurenceCompanyName
				+ ", insurencePremium=" + insurencePremium + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", isActive=" + isActive + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", loanType=" + loanType + ", nomineeState=" + nomineeState + ", nomineeCity=" + nomineeCity
				+ ", nomineeDistrict=" + nomineeDistrict + ", nomineeLocation=" + nomineeLocation + ", nomineeHouseNo="
				+ nomineeHouseNo + ", nomineeLandmark=" + nomineeLandmark + ", academicReligion=" + academicReligion
				+ ", academicCaste=" + academicCaste + ", isAcademicLifeInsurance=" + isAcademicLifeInsurance
				+ ", houseOwnership=" + houseOwnership + ", areaType=" + areaType + ", businessPremises="
				+ businessPremises + ", expInSameLine=" + expInSameLine + ", academicSumInsured=" + academicSumInsured
				+ ", type=" + type + ", remarks=" + remarks + ", isPersonalDetailsFilled=" + isPersonalDetailsFilled
				+ ", isFamilyDetailsFilled=" + isFamilyDetailsFilled + ", isNomineeDetailsFilled="
				+ isNomineeDetailsFilled + ", isAcadamicDetailsFilled=" + isAcadamicDetailsFilled
				+ ", isBankDetailsFilled=" + isBankDetailsFilled + ", isAccountDetailsFilled=" + isAccountDetailsFilled
				+ ", isExistingLoanDetailsFilled=" + isExistingLoanDetailsFilled + ", isIncomeDetailsFilled="
				+ isIncomeDetailsFilled + ", isFamilyIncomeFilled=" + isFamilyIncomeFilled + ", isFamilyExpenseFilled="
				+ isFamilyExpenseFilled + ", isExpectedIncomeFilled=" + isExpectedIncomeFilled + ", isPPIFilled="
				+ isPPIFilled + ", isProjectDetailsFilled=" + isProjectDetailsFilled + ", isApplyLoanFilled="
				+ isApplyLoanFilled + ", isCostProjectFilled=" + isCostProjectFilled + ", isMeanFinanceFilled="
				+ isMeanFinanceFilled + ", isCashFlowDetailsFilled=" + isCashFlowDetailsFilled
				+ ", isAssetsDetailsFilled=" + isAssetsDetailsFilled + ", isCurrentAssetsFilled="
				+ isCurrentAssetsFilled + ", isFixedAssetsFilled=" + isFixedAssetsFilled + ", isCurrntLiabilityFilled="
				+ isCurrntLiabilityFilled + ", isRepaymentDetailsFilled=" + isRepaymentDetailsFilled
				+ ", isConsentFormFilled=" + isConsentFormFilled + ", isLoanassessmentDetailsFilled="
				+ isLoanassessmentDetailsFilled + ", addressProofType=" + addressProofType + ", addressProofImg="
				+ addressProofImg + ", consentFormImg=" + consentFormImg + ", profileImg=" + profileImg + ", aadharImg="
				+ aadharImg + ", educationExpense=" + educationExpense + ", medicalExpense=" + medicalExpense
				+ ", foodExpense=" + foodExpense + ", otherExpense=" + otherExpense + ", businessNameBrief="
				+ businessNameBrief + ", monthlyCashflow=" + monthlyCashflow + ", monthlyExpenditure="
				+ monthlyExpenditure + ", monthlyIncome=" + monthlyIncome + ", ppiNoFamilyMember=" + ppiNoFamilyMember
				+ ", ppiAcadamicHeadFamily=" + ppiAcadamicHeadFamily + ", ppiRafrigeratorInFamily="
				+ ppiRafrigeratorInFamily + ", ppiStoveInFamily=" + ppiStoveInFamily + ", ppiPressureCookerInFamily="
				+ ppiPressureCookerInFamily + ", ppiTvInFamily=" + ppiTvInFamily + ", ppiFanInFamily=" + ppiFanInFamily
				+ ", ppiVehicleInFamily=" + ppiVehicleInFamily + ", ppiDressingTableInFamily="
				+ ppiDressingTableInFamily + ", ppiOtherTableInFamily=" + ppiOtherTableInFamily
				+ ", ppiNoFamilyMemberScore=" + ppiNoFamilyMemberScore + ", ppiAcadamicHeadFamilyScore="
				+ ppiAcadamicHeadFamilyScore + ", ppiRafrigeratorInFamilyScore=" + ppiRafrigeratorInFamilyScore
				+ ", ppiStoveInFamilyScore=" + ppiStoveInFamilyScore + ", ppiPressureCookerInFamilyScore="
				+ ppiPressureCookerInFamilyScore + ", ppiTvInFamilyScore=" + ppiTvInFamilyScore
				+ ", ppiFanInFamilyScore=" + ppiFanInFamilyScore + ", ppiVehicleInFamilyScore="
				+ ppiVehicleInFamilyScore + ", ppiDressingTableInFamilyScore=" + ppiDressingTableInFamilyScore
				+ ", ppiOtherTableInFamilyScore=" + ppiOtherTableInFamilyScore + ", totalMonthlyIncomeForFamily="
				+ totalMonthlyIncomeForFamily + ", assetsDetails=" + assetsDetails + ", liabilityDetails="
				+ liabilityDetails + ", coApplicantDetails=" + coApplicantDetails + ", totalExpense=" + totalExpense
				+ ", bankId=" + bankId + ", branchName=" + branchName + ", bankName=" + bankName + ", acHolderName="
				+ acHolderName + ", accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", accountType=" + accountType
				+ ", passbookImg=" + passbookImg + ", incomeDetailsReqList=" + incomeDetailsReqList
				+ ", incomeDetailsTypeTwoList=" + incomeDetailsTypeTwoList + ", mfiIncomeAndExpenditureReqMFIMaker="
				+ mfiIncomeAndExpenditureReqMFIMaker + ", mfiIncomeAndExpenditureReqMFIChecker="
				+ mfiIncomeAndExpenditureReqMFIChecker + ", purposeOfLoan=" + purposeOfLoan
				+ ", isBusinessPremiseVisited=" + isBusinessPremiseVisited + ", repaymentTrack=" + repaymentTrack
				+ ", creaditWorthiness=" + creaditWorthiness + ", loanLiabilityRatio=" + loanLiabilityRatio
				+ ", loanAmountRecomandation=" + loanAmountRecomandation + ", tenureRecomandation="
				+ tenureRecomandation + ", moratoriumRecomandation=" + moratoriumRecomandation
				+ ", interestRateRecomandation=" + interestRateRecomandation + ", installmentRecomandation="
				+ installmentRecomandation + ", clientType=" + clientType + ", businessInBrief=" + businessInBrief
				+ ", incomeFromOccupation=" + incomeFromOccupation + ", existingExpensesOfFamily="
				+ existingExpensesOfFamily + ", netSaving=" + netSaving + ", expectedIncome=" + expectedIncome
				+ ", competition=" + competition + ", id=" + id + ", addressProfType=" + addressProfType
				+ ", addressProofNo=" + addressProofNo + ", businessTypeId=" + businessTypeId + ", amount=" + amount
				+ ", outstanding=" + outstanding + ", particulars=" + particulars + ", houseHoldExpense="
				+ houseHoldExpense + ", clothesExpense=" + clothesExpense + ", loanAmountMFIChecker="
				+ loanAmountMFIChecker + ", loanAmountBankMaker=" + loanAmountBankMaker + ", totalEmi=" + totalEmi
				+ ", jobId=" + jobId + ", status=" + status + ", loanTenure=" + loanTenure
				+ ", financialArrangementDetails=" + financialArrangementDetails + ", sanctionDetail=" + sanctionDetail
				+ ", disbursementDetails=" + disbursementDetails + ", maritalStatus=" + maritalStatus + ", gender="
				+ gender + ", relationWithNominee=" + relationWithNominee + ", eduQualification=" + eduQualification
				+ ", houseType1=" + houseType1 + ", repayFreq=" + repayFreq + ", academicReli=" + academicReli
				+ ", academicCast=" + academicCast + ", houseOwnerShip=" + houseOwnerShip + ", areaType1=" + areaType1
				+ ", businessPremises1=" + businessPremises1 + ", addressProofType1=" + addressProofType1
				+ ", dateOfBirth=" + dateOfBirth + ", businessType1=" + businessType1 + ", accountType1=" + accountType1
				+ ", businessNameString=" + businessNameString + ", ppiAcadamicHeadFamily1=" + ppiAcadamicHeadFamily1
				+ ", loanTypeString=" + loanTypeString + ", purposeOfLoanString=" + purposeOfLoanString
				+ ", clientType1=" + clientType1 + ", repayTrack=" + repayTrack + ", competition1=" + competition1
				+ ", listOfImages=" + listOfImages + ", byteList=" + byteList + ", mfiMakerTotalExpense="
				+ mfiMakerTotalExpense + ", mfiCheckerTotalExpense=" + mfiCheckerTotalExpense + ", totalIncomeChecker="
				+ totalIncomeChecker + ", netSavingChecker=" + netSavingChecker + ", increasedIncomeChecker="
				+ increasedIncomeChecker + ", totalCashFlowChecker=" + totalCashFlowChecker + ", dpnDoc=" + dpnDoc
				+ ", loiDoc=" + loiDoc + ", lohDoc=" + lohDoc + ", agreementDoc=" + agreementDoc + ", dpnDocFileName="
				+ dpnDocFileName + ", loiDocFileName=" + loiDocFileName + ", lohDocFileName=" + lohDocFileName
				+ ", agreementDocFileName=" + agreementDocFileName + ", userId=" + userId + ", orgId=" + orgId
				+ ", makerName=" + makerName + ", loanPurposeStr=" + loanPurposeStr + ", currDateStr=" + currDateStr
				+ ", piName=" + piName + ", piAddress=" + piAddress + ", loginUser=" + loginUser + ", isConsolidated="
				+ isConsolidated + ", consolidatedName=" + consolidatedName + ", byteListAddProof=" + byteListAddProof
				+ ", ByteListPassImg=" + ByteListPassImg + "]";
	}
	
	

}
