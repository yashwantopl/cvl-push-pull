package com.capitaworld.service.loans.domain.fundseeker.mfi;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jaimin.darji
 */
@Entity
@Table(name = "fs_mfi_applicant_details")
public class MFIApplicantDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@OneToOne
	@JoinColumn(name = "proposal_mapping_id")
	private ApplicationProposalMapping applicationProposalMapping;

	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "name_as_per_aadharCard")
	private String nameAsPerAadharCard;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "gender_id")
	private Integer genderId;

	private String mobile;

	private String email;

	@Column(name = "marital_status_id")
	private Integer maritalStatusId;

	@Column(name = "address_same_as_aadhar")
	private Boolean addressSameAsAadhar;

	@Column(name = "current_district")
	private String currentDistrict;

	@Column(name = "aadhar_district")
	private String aadharDistrict;

	@Column(name = "current_house")
	private String currentHouse;

	@Column(name = "aadhar_house")
	private String aadharHouse;

	@Column(name = "current_landmark")
	private String currentLandmark;

	@Column(name = "aadhar_landmark")
	private String aadharLandmark;

	@Column(name = "current_location")
	private String currentLocation;

	@Column(name = "aadhar_location")
	private String aadharLocation;

	@Column(name = "current_state")
	private String currentState;

	@Column(name = "aadhar_state")
	private String aadharState;

	@Column(name = "current_street")
	private String currentStreet;

	@Column(name = "aadhar_street")
	private String aadharStreet;

	@Column(name = "current_vtc")
	private String currentVtc;

	@Column(name = "aadhar_vtc")
	private String aadharVtc;

	@Column(name = "aadhar_subdist")
	private String aadharSubdist;

	@Column(name = "current_subdist")
	private String currentSubdist;

	@Column(name = "aadhar_po")
	private String aadharPo;

	@Column(name = "current_po")
	private String currentPo;

	@Column(name = "aadhar_care_of")
	private String aadharCareOf;

	@Column(name = "address_pincode")
	private String addressPincode;

	@Column(name = "aadhar_pincode")
	private String aadharPincode;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "spouse_name")
	private String spouseName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "spouse_birth_date")
	private Date spouseBirthDate;

	@Column(name = "spouse_mobile")
	private String spouseMobile;

	@Column(name = "no_dependent")
	private Integer noDependent;

	@Column(name = "nominee_name")
	private String nomineeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "nominee_birth_date")
	private Date nomineeBirthDate;

	@Column(name = "relation_with_nominee_id")
	private Integer relationWithNomineeId;

	@Column(name = "nominee_address")
	private String nomineeAddress;

	@Column(name = "nominee_pincode")
	private String nomineePincode;

	@Column(name = "religion")
	private Integer religion;

	@Column(name = "education_qualification")
	private Integer educationQualification;

	@Column(name = "land_holding")
	private Double landHolding;

	@Column(name = "name_of_firm")
	private String nameOfFirm;

	@Column(name = "business_type")
	private Integer businessType;

	@Column(name = "house_type")
	private Integer houseType;

	@Column(name = "loan_purpose")
	private String loanPurpose;

	@Column(name = "loan_amount_required")
	private Double loanAmountRequired;

	@Column(name = "cost_of_project")
	private Double costOfProject;

	@Column(name = "cost_of_equipment")
	private Double costOfEquipment;

	@Column(name = "working_cap_of_equipment")
	private Double workingCapOfEquipment;

	@Column(name = "total_cost_equipment")
	private Double totalCostEquipment;

	@Column(name = "promoter_contribution")
	private Double promoterContribution;

	@Column(name = "loan_required_from_sidbi")
	private Double loanRequiredFromSidbi;

	@Column(name = "total_mean_finance")
	private Double totalMeanFinance;

	@Column(name = "total_cash_flow")
	private Double totalCashFlow;

	@Column(name = "repayment_frequency")
	private Integer repaymentFrequency;

	@Column(name = "insurence_required")
	private Boolean insurenceRequired;

	@Column(name = "insurence_company_name")
	private String insurenceCompanyName;

	@Column(name = "insurence_premium")
	private Double insurencePremium;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "loan_type")
	private Integer loanType;

	@Column(name = "nominee_state")
	private String nomineeState;
	@Column(name = "nominee_city")
	private String nomineeCity;
	@Column(name = "nominee_district")
	private String nomineeDistrict;
	@Column(name = "nominee_location")
	private String nomineeLocation;
	@Column(name = "nominee_house_no")
	private String nomineeHouseNo;
	@Column(name = "nominee_landmark")
	private String nomineeLandmark;
	@Column(name = "academic_religion")
	private Integer academicReligion;
	@Column(name = "academic_caste")
	private Integer academicCaste;
	@Column(name = "is_academic_life_insurance")
	private Integer isAcademicLifeInsurance;

	@Column(name = "house_ownership")
	private Integer houseOwnership;

	@Column(name = "area_type")
	private Integer areaType;

	@Column(name = "business_premises")
	private Integer businessPremises;

	@Column(name = "exp_in_same_line")
	private Integer expInSameLine;

	@Column(name = "academic_sum_insured")
	private Double academicSumInsured;

	private Integer type;

	private String remarks;

	@Column(name = "is_personal_details_filled")
	private Boolean isPersonalDetailsFilled;

	@Column(name = "is_family_details_filled")
	private Boolean isFamilyDetailsFilled;

	@Column(name = "is_nominee_details_filled")
	private Boolean isNomineeDetailsFilled;

	@Column(name = "is_acadamic_details_filled")
	private Boolean isAcadamicDetailsFilled;

	@Column(name = "is_bank_details_filled")
	private Boolean isBankDetailsFilled;

	@Column(name = "is_account_details_filled")
	private Boolean isAccountDetailsFilled;

	@Column(name = "is_existing_loan_details_filled")
	private Boolean isExistingLoanDetailsFilled;

	@Column(name = "is_income_details_filled")
	private Boolean isIncomeDetailsFilled;

	@Column(name = "is_family_income_filled")
	private Boolean isFamilyIncomeFilled;

	@Column(name = "is_family_expense_filled")
	private Boolean isFamilyExpenseFilled;

	@Column(name = "is_expected_income_filled")
	private Boolean isExpectedIncomeFilled;

	@Column(name = "is_ppi_filled")
	private Boolean isPPIFilled;

	@Column(name = "is_project_details_filled")
	private Boolean isProjectDetailsFilled;

	@Column(name = "is_apply_loan_filled")
	private Boolean isApplyLoanFilled;

	@Column(name = "is_cost_project_filled")
	private Boolean isCostProjectFilled;

	@Column(name = "is_mean_finance_filled")
	private Boolean isMeanFinanceFilled;

	@Column(name = "is_cash_flow_details_filled")
	private Boolean isCashFlowDetailsFilled;

	@Column(name = "is_assets_details_filled")
	private Boolean isAssetsDetailsFilled;

	@Column(name = "is_current_assets_filled")
	private Boolean isCurrentAssetsFilled;

	@Column(name = "is_fixed_assets_filled")
	private Boolean isFixedAssetsFilled;

	@Column(name = "is_currnt_liability_filled")
	private Boolean isCurrntLiabilityFilled;

	@Column(name = "is_repayment_details_filled")
	private Boolean isRepaymentDetailsFilled;

	@Column(name = "is_consent_form_filled")
	private Boolean isConsentFormFilled;

	@Column(name = "address_proof_type")
	private Integer addressProofType;
	@Column(name = "address_proof_no")
	private String addressProofNo;

	@Column(name = "address_proof_img")
	private String addressProofImg;
	@Column(name = "consent_form_img")
	private String consentFormImg;
	@Column(name = "profile_img")
	private String profileImg;
	@Column(name = "ppi_no_family_member")
	private Integer ppiNoFamilyMember;
	@Column(name = "ppi_acadamic_head_family")
	private Integer ppiAcadamicHeadFamily;
	@Column(name = "ppi_rafrigerator_in_family")
	private Integer ppiRafrigeratorInFamily;
	@Column(name = "ppi_stove_in_family")
	private Integer ppiStoveInFamily;
	@Column(name = "ppi_pressure_cooker_in_family")
	private Integer ppiPressureCookerInFamily;
	@Column(name = "ppi_tv_in_family")
	private Integer ppiTvInFamily;
	@Column(name = "ppi_fan_in_family")
	private Integer ppiFanInFamily;
	@Column(name = "ppi_vehicle_in_family")
	private Integer ppiVehicleInFamily;
	@Column(name = "ppi_dressing_table_in_family")
	private Integer ppiDressingTableInFamily;
	@Column(name = "ppi_other_table_in_family")
	private Integer ppiOtherTableInFamily;

	@Column(name = "purpose_of_loan")
	private Integer purposeOfLoan;
	@Column(name = "client_type")
	private Integer clientType;
	@Column(name = "is_business_premise_visited")
	private Boolean isBusinessPremiseVisited;
	@Column(name = "repayment_track")
	private Integer repaymentTrack;
	@Column(name = "creadit_worthiness")
	private Integer creaditWorthiness;
	@Column(name = "loan_liability_ratio")
	private String loanLiabilityRatio;
	@Column(name = "competition")
	private Integer competition;
	@Column(name = "loan_amount_recomandation")
	private Double loanAmountRecomandation;
	@Column(name = "tenure_recomandation")
	private Integer tenureRecomandation;
	@Column(name = "moratorium_recomandation")
	private Integer moratoriumRecomandation;
	@Column(name = "interest_rate_recomandation")
	private Double interestRateRecomandation;
	@Column(name = "installment_recomandation")
	private Integer installmentRecomandation;
	@Column(name = "is_loanassessment_details_filled")
	private Boolean isLoanassessmentDetailsFilled;

	@Column(name = "loan_amount_mfi_checker")
	private Double loanAmountMFIChecker;
	@Column(name = "loan_tenure")
	private Integer loanTenure;

	@Column(name = "loan_amount_bank_checker")
	private Double loanAmountBankMaker;
	@Column(name = "job_id")
	private Long jobId;

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

	//	@Column(name = "total_expense")
//	private Double totalExpense;
	
//	@Column(name = "total_monthly_income_for_family")
//	private Double totalMonthlyIncomeForFamily;
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getIsAcademicLifeInsurance() {
		return isAcademicLifeInsurance;
	}

	public void setIsAcademicLifeInsurance(Integer isAcademicLifeInsurance) {
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
	}

	public Integer getAcademicReligion() {
		return academicReligion;
	}

	public void setAcademicReligion(Integer academicReligion) {
		this.academicReligion = academicReligion;
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

	public Double getAcademicSumInsured() {
		return academicSumInsured;
	}

	public void setAcademicSumInsured(Double academicSumInsured) {
		this.academicSumInsured = academicSumInsured;
	}

	public Integer getExpInSameLine() {
		return expInSameLine;
	}

	public void setExpInSameLine(Integer expInSameLine) {
		this.expInSameLine = expInSameLine;
	}


	public Date getNomineeBirthDate() {
		return nomineeBirthDate;
	}

	public void setNomineeBirthDate(Date nomineeBirthDate) {
		this.nomineeBirthDate = nomineeBirthDate;
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

	public Integer getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(Integer purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Boolean getIsBusinessPremiseVisited() {
		return isBusinessPremiseVisited;
	}

	public void setIsBusinessPremiseVisited(Boolean isBusinessPremiseVisited) {
		this.isBusinessPremiseVisited = isBusinessPremiseVisited;
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


	public Boolean getIsLoanassessmentDetailsFilled() {
		return isLoanassessmentDetailsFilled;
	}

	public void setIsLoanassessmentDetailsFilled(Boolean isLoanassessmentDetailsFilled) {
		this.isLoanassessmentDetailsFilled = isLoanassessmentDetailsFilled;
	}

//	public Double getTotalExpense() {
//		return totalExpense;
//	}
//
//	public void setTotalExpense(Double totalExpense) {
//		this.totalExpense = totalExpense;
//	}
//
//	public Double getTotalMonthlyIncomeForFamily() {
//		return totalMonthlyIncomeForFamily;
//	}
//
//	public void setTotalMonthlyIncomeForFamily(Double totalMonthlyIncomeForFamily) {
//		this.totalMonthlyIncomeForFamily = totalMonthlyIncomeForFamily;
//	}

	
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

	public String getLoanLiabilityRatio() {
		return loanLiabilityRatio;
	}

	public void setLoanLiabilityRatio(String loanLiabilityRatio) {
		this.loanLiabilityRatio = loanLiabilityRatio;
	}

	public Double getInterestRateRecomandation() {
		return interestRateRecomandation;
	}

	public void setInterestRateRecomandation(Double interestRateRecomandation) {
		this.interestRateRecomandation = interestRateRecomandation;
	}

	public Integer getInstallmentRecomandation() {
		return installmentRecomandation;
	}

	public void setInstallmentRecomandation(Integer installmentRecomandation) {
		this.installmentRecomandation = installmentRecomandation;
	}

	public Integer getCompetition() {
		return competition;
	}

	public void setCompetition(Integer competition) {
		this.competition = competition;
	}

	public String getAddressProofNo() {
		return addressProofNo;
	}

	public void setAddressProofNo(String addressProofNo) {
		this.addressProofNo = addressProofNo;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
}
