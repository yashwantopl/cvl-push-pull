package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * The persistent class for the fs_retail_applicant_details database table.
 * 
 */
@Entity
@Table(name = "fs_retail_applicant_details")
public class RetailApplicantDetail implements Serializable {
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
	
	@Column(name = "address_city")
	private Long addressCity;

	@Column(name = "address_country")
	private Integer addressCountry;

	@Column(name = "address_landmark")
	private String addressLandmark;

	@Column(name = "address_pincode")
	private Long addressPincode;

	@Column(name = "address_premise_name")
	private String addressPremiseName;

	@Column(name = "address_same_as")
	private Boolean addressSameAs;

	@Column(name = "address_state")
	private Long addressState;

	@Column(name = "address_street_name")
	private String addressStreetName;

	@Column(name = "allied_activity_id")
	private Integer alliedActivityId;

	@Column(name = "annual_rent")
	private Double annualRent;

	@Column(name = "annual_turnover")
	private Double annualTurnover;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "birth_place")
	private String birthPlace;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "business_start_date")
	private Date businessStartDate;

	@Column(name = "cast_id")
	private Integer castId;

	@Column(name = "cast_other")
	private String castOther;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_no")
	private String contactNo;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "currency_id")
	private Integer currencyId;

	@Column(name = "current_department")
	private String currentDepartment;

	@Column(name = "current_designation")
	private String currentDesignation;

	@Column(name = "current_industry")
	private String currentIndustry;

	@Column(name = "current_job_month")
	private Integer currentJobMonth;

	@Column(name = "current_job_year")
	private Integer currentJobYear;

	@Column(name = "employed_with_id")
	private Integer employedWithId;

	@Column(name = "employed_with_other")
	private String employedWithOther;

	@Column(name = "employment_status")
	private Integer employmentStatus;
	
	@Column(name = "employment_sub_status")
	private Integer employmentSubStatus;
	
	@Column(name = "employment_status_other")
	private String employmentStatusOther;
	
	@Column(name = "current_employment_status")
	private Integer currentEmploymentStatus;

	@Column(name = "entity_name")
	private String entityName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "highest_qualification")
	private Integer highestQualification;

	@Column(name = "highest_qualification_other")
	private String highestQualificationOther;

	@Column(name = "industry_type_id")
	private Integer industryTypeId;

	@Column(name = "industry_type_other")
	private String industryTypeOther;

	private String institute;

	@Column(name = "interest_rate")
	private Integer interestRate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_spouse_employed")
	private Boolean isSpouseEmployed;

	@Column(name = "land_size")
	private Double landSize;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "monthly_income")
	private Double monthlyIncome;
	
	@Column(name = "gross_monthly_income")
	private Double grossMonthlyIncome;

	@Column(name = "mother_name")
	private String motherName;

	@Column(name = "name_of_entity")
	private String nameOfEntity;

	@Column(name = "no_children")
	private Integer noChildren;

	@Column(name = "no_dependent")
	private Integer noDependent;

	@Column(name = "no_partners")
	private Integer noPartners;

	@Column(name = "occupation_id")
	private Integer occupationId;

	@Column(name = "office_city_id")
	private Long officeCityId;

	@Column(name = "office_country_id")
	private Integer officeCountryId;

	@Column(name = "office_land_mark")
	private String officeLandMark;

	@Column(name = "office_pincode")
	private Long officePincode;

	@Column(name = "office_premise_number_name")
	private String officePremiseNumberName;

	@Column(name = "office_state_id")
	private Integer officeStateId;

	@Column(name = "office_street_name")
	private String officeStreetName;

	@Column(name = "office_type")
	private Integer officeType;

	@Column(name = "ownership_type")
	private Integer ownershipType;

	private String pan;
	
	@Column(name = "ownership_type_others")
	private String ownershipTypeOthers;

	@Column(name = "partners_name")
	private String partnersName;

	@Column(name = "permanent_city_id")
	private Long permanentCityId;

	@Column(name = "permanent_country_id")
	private Integer permanentCountryId;

	@Column(name = "permanent_land_mark")
	private String permanentLandMark;

	@Column(name = "permanent_pincode")
	private Long permanentPincode;

	@Column(name = "permanent_premise_number_name")
	private String permanentPremiseNumberName;

	@Column(name = "permanent_state_id")
	private Integer permanentStateId;

	@Column(name = "permanent_street_name")
	private String permanentStreetName;

	@Column(name = "poa_holder_name")
	private String poaHolderName;

	@Column(name = "presently_irrigated")
	private String presentlyIrrigated;

	@Column(name = "previous_employers_address")
	private String previousEmployersAddress;

	@Column(name = "previous_employers_name")
	private String previousEmployersName;

	@Column(name = "previous_job_month")
	private Integer previousJobMonth;

	@Column(name = "previous_job_year")
	private Integer previousJobYear;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "qualifying_year")
	private Date qualifyingYear;

	@Column(name = "rain_fed")
	private String rainFed;

	private Integer religion;

	@Column(name = "religion_other")
	private String religionOther;

	@Column(name = "repayment_cycle")
	private Integer repaymentCycle;

	@Column(name = "repayment_mode")
	private Integer repaymentMode;

	@Column(name = "residence_type")
	private Integer residenceType;

	@Column(name = "residing_month")
	private Double residingMonth;

	@Column(name = "residing_year")
	private Double residingYear;

	@Column(name = "seasonal_irrigated")
	private String seasonalIrrigated;

	@Column(name = "self_employed_occupation_id")
	private Integer selfEmployedOccupationId;

	@Column(name = "self_employed_occupation_other")
	private String selfEmployedOccupationOther;

	@Column(name = "share_holding")
	private String shareHolding;

	@Column(name = "spouse_name")
	private String spouseName;
	
	@Column(name = "annual_income_of_spouse")
	private Double annualIncomeOfSpouse;
	
	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "title_id")
	private Integer titleId;

	@Column(name = "total_experience_month")
	private Integer totalExperienceMonth;

	@Column(name = "total_experience_year")
	private Integer totalExperienceYear;

	@Column(name = "total_land_owned")
	private Double totalLandOwned;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "driving_license_expiry_date")
	private Date drivingLicenseExpiryDate;

	@Column(name = "driving_license_number")
	private String drivingLicenseNumber;

	private String unattended;

	@Column(name = "website_address")
	private String websiteAddress;
	
	@Column(name = "gender_id")
	private Integer genderId;
	
	@Column(name = "monthly_loan_obligation")
	private Double monthlyLoanObligation;
	
	@Column(name = "pat_previous_year")
	private Double patPreviousYear;
	
	@Column(name = "pat_current_year")
	private Double patCurrentYear;
	
	@Column(name = "depreciation_previous_year")
	private Double depreciationPreviousYear;
	
	@Column(name = "depreciation_current_year")
	private Double depreciationCurrentYear;
	
	@Column(name = "remuneration_previous_year")
	private Double remunerationPreviousYear;
	
	@Column(name = "remuneration_current_year")
	private Double remunerationCurrentYear;
	
	@Column(name = "bonus_per_annum")
	private Double bonusPerAnnum;
	
	@Column(name = "incentive_per_annum")
	private Double incentivePerAnnum;
	
	@Column(name = "other_income")
	private Double otherIncome;
	
	@Column(name = "other_investment")
	private Double otherInvestment;
	
	@Column(name = "tax_paid_last_year")
	private Double taxPaidLastYear;

	@Column(name = "mode_of_receipt")
	private Integer modeOfReceipt;

	@Column(name = "loan_amount_required")
	private Double loanAmountRequired;

	@Column(name="loan_purpose")
	private Integer loanPurpose;
	
	@Column(name="loan_purpose_que_type")
	private Integer loanPurposeQueType;
	
	@Column(name="loan_purpose_que_value")
	private String loanPurposeQueValue;

	@Column(name="loan_purpose_other")
	private String loanPurposeOther;

	@Column(name="tenure_required")
	private Integer tenureRequired;

	@Column(name="repayment")
	private Integer repayment;

	@Column(name="education_qualification")
	private Integer educationQualification;

	@Column(name="employment_type")
	private Integer employmentType;

	@Column(name="name_of_employer")
	private String nameOfEmployer;

	@Column(name = "key_verical_funding")
	private Long keyVerticalFunding;

	@Column(name = "key_vertical_sector")
	private Long keyVerticalSector;

	@Column(name = "key_vertical_subsector")
	private Long keyVerticalSubSector;

	@Column(name = "email")
	private String email;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "permanent_district_mapping_id")
	private Long permanentdistrictMappingId;

	@Column(name = "office_district_mapping_id")
	private Long officeDistrictMappingId;

	@Column(name = "address_district_mapping_id")
	private Long addressDistrictMappingId;

	@Column(name="employment_with")
	private Integer employmentWith;

	@Column(name="central_gov_id")
	private Integer centralGovId;

	@Column(name="state_gov_id")
	private Integer stateGovId;

	@Column(name="psu_id")
	private Integer psuId;

	@Column(name="corporate_id")
	private Integer corporateId;

	@Column(name="edu_inst_id")
	private Integer eduInstId;

    @Column(name="nationality")
    private String nationality;

    @Column(name="residential_status")
    private Integer residentialStatus;

    @Column(name="disability_type")
    private Integer disabilityType;

    @Column(name="passport")
    private String passport;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="passport_validity")
    private Date passportValidity;

    @Column(name="voter_id")
    private String voterId;

    @Column(name="residential_proof_no")
    private String residentialProofNo;

    @Column(name="office_name_of_org")
    private String officeNameOfOrg;

    @Column(name="office_email")
    private String officeEmail;

    @Column(name="previous_employers_contact")
    private String previousEmployersContact;

    @Column(name="ddo_website")
    private String ddoWebsite;


    @Column(name="ddo_remaining_ser_yrs")
    private Integer ddoRemainingSerYrs;


    @Column(name="ddo_remaining_ser_months")
    private Integer ddoRemainingSerMonths;


    @Column(name="ddo_employee_no")
    private String ddoEmployeeNo;


    @Column(name="ddo_designation")
    private String ddoDesignation;


    @Column(name="ddo_department")
    private String ddoDepartment;


    @Column(name="ddo_organization_type")
    private Integer ddoOrganizationType;

    @Column(name="spouse_employment")
    private Integer spouseEmployment;

    @Column(name="no_of_dependent")
    private Integer noOfDependent;

    @Column(name="designation")
    private Integer designation;

    @Column(name="residence_since_year")
    private Integer residenceSinceYear;

    @Column(name="residence_since_month")
    private Integer residenceSinceMonth;

    @Column(name="salary_mode")
    private Integer salaryMode;

    @Column(name="salary_bank_name")
    private String salaryBankName;

    @Column(name="salary_bank_month")
    private Integer salaryBankMonth;

    @Column(name="salary_bank_year")
    private Integer salaryBankYear;

    @Column(name="is_other_salary_bank")
    private Boolean isOtherSalaryBank;
    
    @Column(name="is_one_form_completed")
    private Boolean isOneFormCompleted;
    
    @Column(name="is_oneform_primary_complete")
    private Boolean isOneformPrimaryComplete;
    
    @Column(name="is_cibil_completed")
    private Boolean isCibilCompleted;
    
    @Column(name="category")
    private Integer category;
    
    @Column(name="networth")
    private Double networth;
    
    @Column(name="is_basic_info_filled")
    private Boolean isBasicInfoFilled;
    
    @Column(name="is_employment_info_filled")
    private Boolean isEmploymentInfoFilled;
    
    @Column(name="is_contact_info_filled")
    private Boolean isContactInfoFilled;
    
    @Column(name="is_credit_info_filled")
    private Boolean isCreditInfoFilled;

    
    @Column(name="kid")
    private String kid;

    private String remarks;


	public RetailApplicantDetail() {
		// Do nothing because of X and Y.
	}

	public Long getId() {
		return this.id;
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

	public String getAadharNumber() {
		return this.aadharNumber;
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

	public Long getAddressCity() {
		return this.addressCity;
	}

	public void setAddressCity(Long addressCity) {
		this.addressCity = addressCity;
	}

	public Integer getAddressCountry() {
		return this.addressCountry;
	}

	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressLandmark() {
		return this.addressLandmark;
	}

	public void setAddressLandmark(String addressLandmark) {
		this.addressLandmark = addressLandmark;
	}

	public Long getAddressPincode() {
		return this.addressPincode;
	}

	public void setAddressPincode(Long addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getAddressPremiseName() {
		return this.addressPremiseName;
	}

	public void setAddressPremiseName(String addressPremiseName) {
		this.addressPremiseName = addressPremiseName;
	}

	public Boolean getAddressSameAs() {
		return addressSameAs;
	}

	public void setAddressSameAs(Boolean addressSameAs) {
		this.addressSameAs = addressSameAs;
	}

	public Long getAddressState() {
		return this.addressState;
	}

	public void setAddressState(Long addressState) {
		this.addressState = addressState;
	}

	public String getAddressStreetName() {
		return this.addressStreetName;
	}

	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}

	public Integer getAlliedActivityId() {
		return this.alliedActivityId;
	}

	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}

	public Double getAnnualRent() {
		return this.annualRent;
	}

	public void setAnnualRent(Double annualRent) {
		this.annualRent = annualRent;
	}

	public Double getAnnualTurnover() {
		return this.annualTurnover;
	}

	public void setAnnualTurnover(Double annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return this.birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBusinessStartDate() {
		return this.businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Integer getCastId() {
		return this.castId;
	}

	public void setCastId(Integer castId) {
		this.castId = castId;
	}

	public String getCastOther() {
		return this.castOther;
	}

	public void setCastOther(String castOther) {
		this.castOther = castOther;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrentDepartment() {
		return this.currentDepartment;
	}

	public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public String getCurrentDesignation() {
		return this.currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public String getCurrentIndustry() {
		return this.currentIndustry;
	}

	public void setCurrentIndustry(String currentIndustry) {
		this.currentIndustry = currentIndustry;
	}

	public Integer getCurrentJobMonth() {
		return this.currentJobMonth;
	}

	public void setCurrentJobMonth(Integer currentJobMonth) {
		this.currentJobMonth = currentJobMonth;
	}

	public Integer getCurrentJobYear() {
		return this.currentJobYear;
	}

	public void setCurrentJobYear(Integer currentJobYear) {
		this.currentJobYear = currentJobYear;
	}

	public Integer getEmployedWithId() {
		return this.employedWithId;
	}

	public void setEmployedWithId(Integer employedWithId) {
		this.employedWithId = employedWithId;
	}

	public String getEmployedWithOther() {
		return this.employedWithOther;
	}

	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}

	public Integer getEmploymentStatus() {
		return this.employmentStatus;
	}

	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getEntityName() {
		return this.entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getHighestQualification() {
		return this.highestQualification;
	}

	public void setHighestQualification(Integer highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getHighestQualificationOther() {
		return this.highestQualificationOther;
	}

	public void setHighestQualificationOther(String highestQualificationOther) {
		this.highestQualificationOther = highestQualificationOther;
	}

	public Integer getIndustryTypeId() {
		return this.industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeOther() {
		return this.industryTypeOther;
	}

	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}

	public String getInstitute() {
		return this.institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public Integer getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(Integer interestRate) {
		this.interestRate = interestRate;
	}


	public Double getLandSize() {
		return this.landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getNameOfEntity() {
		return this.nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}

	public Integer getNoChildren() {
		return this.noChildren;
	}

	public void setNoChildren(Integer noChildren) {
		this.noChildren = noChildren;
	}

	public Integer getNoDependent() {
		return this.noDependent;
	}

	public void setNoDependent(Integer noDependent) {
		this.noDependent = noDependent;
	}

	public Integer getNoPartners() {
		return this.noPartners;
	}

	public void setNoPartners(Integer noPartners) {
		this.noPartners = noPartners;
	}

	public Integer getOccupationId() {
		return this.occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Long getOfficeCityId() {
		return this.officeCityId;
	}

	public void setOfficeCityId(Long officeCityId) {
		this.officeCityId = officeCityId;
	}

	public Integer getOfficeCountryId() {
		return this.officeCountryId;
	}

	public void setOfficeCountryId(Integer officeCountryId) {
		this.officeCountryId = officeCountryId;
	}

	public String getOfficeLandMark() {
		return this.officeLandMark;
	}

	public void setOfficeLandMark(String officeLandMark) {
		this.officeLandMark = officeLandMark;
	}

	public Long getOfficePincode() {
		return this.officePincode;
	}

	public void setOfficePincode(Long officePincode) {
		this.officePincode = officePincode;
	}

	public String getOfficePremiseNumberName() {
		return this.officePremiseNumberName;
	}

	public void setOfficePremiseNumberName(String officePremiseNumberName) {
		this.officePremiseNumberName = officePremiseNumberName;
	}

	public Integer getOfficeStateId() {
		return this.officeStateId;
	}

	public void setOfficeStateId(Integer officeStateId) {
		this.officeStateId = officeStateId;
	}

	public String getOfficeStreetName() {
		return this.officeStreetName;
	}

	public void setOfficeStreetName(String officeStreetName) {
		this.officeStreetName = officeStreetName;
	}

	public Integer getOfficeType() {
		return this.officeType;
	}

	public void setOfficeType(Integer officeType) {
		this.officeType = officeType;
	}

	public Integer getOwnershipType() {
		return this.ownershipType;
	}

	public void setOwnershipType(Integer ownershipType) {
		this.ownershipType = ownershipType;
	}

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPartnersName() {
		return this.partnersName;
	}

	public void setPartnersName(String partnersName) {
		this.partnersName = partnersName;
	}

	public Long getPermanentCityId() {
		return this.permanentCityId;
	}

	public void setPermanentCityId(Long permanentCityId) {
		this.permanentCityId = permanentCityId;
	}

	public Integer getPermanentCountryId() {
		return this.permanentCountryId;
	}

	public void setPermanentCountryId(Integer permanentCountryId) {
		this.permanentCountryId = permanentCountryId;
	}

	public String getPermanentLandMark() {
		return this.permanentLandMark;
	}

	public void setPermanentLandMark(String permanentLandMark) {
		this.permanentLandMark = permanentLandMark;
	}

	public Long getPermanentPincode() {
		return this.permanentPincode;
	}

	public void setPermanentPincode(Long permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPermanentPremiseNumberName() {
		return this.permanentPremiseNumberName;
	}

	public void setPermanentPremiseNumberName(String permanentPremiseNumberName) {
		this.permanentPremiseNumberName = permanentPremiseNumberName;
	}

	public Integer getPermanentStateId() {
		return this.permanentStateId;
	}

	public void setPermanentStateId(Integer permanentStateId) {
		this.permanentStateId = permanentStateId;
	}

	public String getPermanentStreetName() {
		return this.permanentStreetName;
	}

	public void setPermanentStreetName(String permanentStreetName) {
		this.permanentStreetName = permanentStreetName;
	}

	public String getPoaHolderName() {
		return this.poaHolderName;
	}

	public void setPoaHolderName(String poaHolderName) {
		this.poaHolderName = poaHolderName;
	}

	public String getPresentlyIrrigated() {
		return this.presentlyIrrigated;
	}

	public void setPresentlyIrrigated(String presentlyIrrigated) {
		this.presentlyIrrigated = presentlyIrrigated;
	}

	public String getPreviousEmployersAddress() {
		return this.previousEmployersAddress;
	}

	public void setPreviousEmployersAddress(String previousEmployersAddress) {
		this.previousEmployersAddress = previousEmployersAddress;
	}

	public String getPreviousEmployersName() {
		return this.previousEmployersName;
	}

	public void setPreviousEmployersName(String previousEmployersName) {
		this.previousEmployersName = previousEmployersName;
	}

	public Integer getPreviousJobMonth() {
		return this.previousJobMonth;
	}

	public void setPreviousJobMonth(Integer previousJobMonth) {
		this.previousJobMonth = previousJobMonth;
	}

	public Integer getPreviousJobYear() {
		return this.previousJobYear;
	}

	public void setPreviousJobYear(Integer previousJobYear) {
		this.previousJobYear = previousJobYear;
	}

	public Date getQualifyingYear() {
		return this.qualifyingYear;
	}

	public void setQualifyingYear(Date qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public String getRainFed() {
		return this.rainFed;
	}

	public void setRainFed(String rainFed) {
		this.rainFed = rainFed;
	}

	public Integer getReligion() {
		return this.religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public String getReligionOther() {
		return this.religionOther;
	}

	public void setReligionOther(String religionOther) {
		this.religionOther = religionOther;
	}

	public Integer getRepaymentCycle() {
		return this.repaymentCycle;
	}

	public void setRepaymentCycle(Integer repaymentCycle) {
		this.repaymentCycle = repaymentCycle;
	}

	public Integer getRepaymentMode() {
		return this.repaymentMode;
	}

	public void setRepaymentMode(Integer repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public Integer getResidenceType() {
		return this.residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}

	public Double getResidingMonth() {
		return this.residingMonth;
	}

	public void setResidingMonth(Double residingMonth) {
		this.residingMonth = residingMonth;
	}

	public Double getResidingYear() {
		return this.residingYear;
	}

	public void setResidingYear(Double residingYear) {
		this.residingYear = residingYear;
	}

	public String getSeasonalIrrigated() {
		return this.seasonalIrrigated;
	}

	public void setSeasonalIrrigated(String seasonalIrrigated) {
		this.seasonalIrrigated = seasonalIrrigated;
	}

	public Integer getSelfEmployedOccupationId() {
		return this.selfEmployedOccupationId;
	}

	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}

	public String getSelfEmployedOccupationOther() {
		return this.selfEmployedOccupationOther;
	}

	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}

	public String getShareHolding() {
		return this.shareHolding;
	}

	public void setShareHolding(String shareHolding) {
		this.shareHolding = shareHolding;
	}

	public String getSpouseName() {
		return this.spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Integer getStatusId() {
		return this.statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getTitleId() {
		return this.titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getTotalExperienceMonth() {
		return this.totalExperienceMonth;
	}

	public void setTotalExperienceMonth(Integer totalExperienceMonth) {
		this.totalExperienceMonth = totalExperienceMonth;
	}

	public Integer getTotalExperienceYear() {
		return this.totalExperienceYear;
	}

	public void setTotalExperienceYear(Integer totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}

	public Double getTotalLandOwned() {
		return this.totalLandOwned;
	}

	public void setTotalLandOwned(Double totalLandOwned) {
		this.totalLandOwned = totalLandOwned;
	}

	public Date getDrivingLicenseExpiryDate() {
		return drivingLicenseExpiryDate;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
		this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getUnattended() {
		return this.unattended;
	}

	public void setUnattended(String unattended) {
		this.unattended = unattended;
	}

	public String getWebsiteAddress() {
		return this.websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsSpouseEmployed() {
		return isSpouseEmployed;
	}

	public void setIsSpouseEmployed(Boolean isSpouseEmployed) {
		this.isSpouseEmployed = isSpouseEmployed;
	}

	public Double getMonthlyLoanObligation() {
		return monthlyLoanObligation;
	}

	public void setMonthlyLoanObligation(Double monthlyLoanObligation) {
		this.monthlyLoanObligation = monthlyLoanObligation;
	}

	public Double getPatPreviousYear() {
		return patPreviousYear;
	}

	public void setPatPreviousYear(Double patPreviousYear) {
		this.patPreviousYear = patPreviousYear;
	}

	public Double getPatCurrentYear() {
		return patCurrentYear;
	}

	public void setPatCurrentYear(Double patCurrentYear) {
		this.patCurrentYear = patCurrentYear;
	}

	public Double getDepreciationPreviousYear() {
		return depreciationPreviousYear;
	}

	public void setDepreciationPreviousYear(Double depreciationPreviousYear) {
		this.depreciationPreviousYear = depreciationPreviousYear;
	}

	public Double getDepreciationCurrentYear() {
		return depreciationCurrentYear;
	}

	public void setDepreciationCurrentYear(Double depreciationCurrentYear) {
		this.depreciationCurrentYear = depreciationCurrentYear;
	}

	public Double getRemunerationPreviousYear() {
		return remunerationPreviousYear;
	}

	public void setRemunerationPreviousYear(Double remunerationPreviousYear) {
		this.remunerationPreviousYear = remunerationPreviousYear;
	}

	public Double getRemunerationCurrentYear() {
		return remunerationCurrentYear;
	}

	public void setRemunerationCurrentYear(Double remunerationCurrentYear) {
		this.remunerationCurrentYear = remunerationCurrentYear;
	}

	public Double getBonusPerAnnum() {
		return bonusPerAnnum;
	}

	public void setBonusPerAnnum(Double bonusPerAnnum) {
		this.bonusPerAnnum = bonusPerAnnum;
	}

	public Double getIncentivePerAnnum() {
		return incentivePerAnnum;
	}

	public void setIncentivePerAnnum(Double incentivePerAnnum) {
		this.incentivePerAnnum = incentivePerAnnum;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getOtherInvestment() {
		return otherInvestment;
	}

	public void setOtherInvestment(Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}

	public Double getTaxPaidLastYear() {
		return taxPaidLastYear;
	}

	public void setTaxPaidLastYear(Double taxPaidLastYear) {
		this.taxPaidLastYear = taxPaidLastYear;
	}
	public String getOwnershipTypeOthers() {
		return ownershipTypeOthers;
	}

	public void setOwnershipTypeOthers(String ownershipTypeOthers) {
		this.ownershipTypeOthers = ownershipTypeOthers;
	}

	public Integer getModeOfReceipt() {
		return modeOfReceipt;
	}

	public void setModeOfReceipt(Integer modeOfReceipt) {
		this.modeOfReceipt = modeOfReceipt;
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

    public String getLoanPurposeOther() {
        return loanPurposeOther;
    }

    public void setLoanPurposeOther(String loanPurposeOther) {
        this.loanPurposeOther = loanPurposeOther;
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

	public Integer getEducationQualification() {
		return educationQualification;
	}

	public void setEducationQualification(Integer educationQualification) {
		this.educationQualification = educationQualification;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public String getNameOfEmployer() {
		return nameOfEmployer;
	}

	public void setNameOfEmployer(String nameOfEmployer) {
		this.nameOfEmployer = nameOfEmployer;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getPermanentdistrictMappingId() {
		return permanentdistrictMappingId;
	}

	public void setPermanentdistrictMappingId(Long permanentdistrictMappingId) {
		this.permanentdistrictMappingId = permanentdistrictMappingId;
	}

	public Long getAddressDistrictMappingId() {
		return addressDistrictMappingId;
	}

	public void setAddressDistrictMappingId(Long addressDistrictMappingId) {
		this.addressDistrictMappingId = addressDistrictMappingId;
	}

    public Long getOfficeDistrictMappingId() {
        return officeDistrictMappingId;
    }

    public void setOfficeDistrictMappingId(Long officeDistrictMappingId) {
        this.officeDistrictMappingId = officeDistrictMappingId;
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

	public Integer getResidenceSinceYear() {
		return residenceSinceYear;
	}

	public Integer getResidenceSinceMonth() {
		return residenceSinceMonth;
	}

	public void setResidenceSinceYear(Integer residenceSinceYear) {
		this.residenceSinceYear = residenceSinceYear;
	}

	public void setResidenceSinceMonth(Integer residenceSinceMonth) {
		this.residenceSinceMonth = residenceSinceMonth;
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

	public void setSpouseEmployment(Integer spouseEmployment) {
		this.spouseEmployment = spouseEmployment;
	}

	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	public Integer getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(Integer salaryMode) {
		this.salaryMode = salaryMode;
	}

	public String getSalaryBankName() {
		return salaryBankName;
	}



	public Boolean getIsOtherSalaryBank() {
		return isOtherSalaryBank;
	}

	public void setIsOtherSalaryBank(Boolean isOtherSalaryBank) {
		this.isOtherSalaryBank = isOtherSalaryBank;
	}

	public void setSalaryBankName(String salaryBankName) {
		this.salaryBankName = salaryBankName;
	}

	public Integer getSalaryBankMonth() {
		return salaryBankMonth;
	}

	public void setSalaryBankMonth(Integer salaryBankMonth) {
		this.salaryBankMonth = salaryBankMonth;
	}

	public Integer getSalaryBankYear() {
		return salaryBankYear;
	}

	public void setSalaryBankYear(Integer salaryBankYear) {
		this.salaryBankYear = salaryBankYear;
	}

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Boolean getIsOneFormCompleted() {
		return isOneFormCompleted;
	}

	public void setIsOneFormCompleted(Boolean isOneFormCompleted) {
		this.isOneFormCompleted = isOneFormCompleted;
	}

	public Boolean getIsCibilCompleted() {
		return isCibilCompleted;
	}

	public void setIsCibilCompleted(Boolean isCibilCompleted) {
		this.isCibilCompleted = isCibilCompleted;
	}
	public String getEmploymentStatusOther() {
		return employmentStatusOther;
	}

	public void setEmploymentStatusOther(String employmentStatusOther) {
		this.employmentStatusOther = employmentStatusOther;
	}

	public Integer getCurrentEmploymentStatus() {
		return currentEmploymentStatus;
	}

	public void setCurrentEmploymentStatus(Integer currentEmploymentStatus) {
		this.currentEmploymentStatus = currentEmploymentStatus;
	}

	public Double getGrossMonthlyIncome() {
		return grossMonthlyIncome;
	}

	public void setGrossMonthlyIncome(Double grossMonthlyIncome) {
		this.grossMonthlyIncome = grossMonthlyIncome;
	}

	public Double getAnnualIncomeOfSpouse() {
		return annualIncomeOfSpouse;
	}

	public void setAnnualIncomeOfSpouse(Double annualIncomeOfSpouse) {
		this.annualIncomeOfSpouse = annualIncomeOfSpouse;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public Boolean getIsOneformPrimaryComplete() {
		return isOneformPrimaryComplete;
	}

	public void setIsOneformPrimaryComplete(Boolean isOneformPrimaryComplete) {
		this.isOneformPrimaryComplete = isOneformPrimaryComplete;
	}

	public Integer getEmploymentSubStatus() {
		return employmentSubStatus;
	}

	public void setEmploymentSubStatus(Integer employmentSubStatus) {
		this.employmentSubStatus = employmentSubStatus;
	}

	public Integer getLoanPurposeQueType() {
		return loanPurposeQueType;
	}

	public void setLoanPurposeQueType(Integer loanPurposeQueType) {
		this.loanPurposeQueType = loanPurposeQueType;
	}

	public String getLoanPurposeQueValue() {
		return loanPurposeQueValue;
	}

	public void setLoanPurposeQueValue(String loanPurposeQueValue) {
		this.loanPurposeQueValue = loanPurposeQueValue;
	}

	public Boolean getIsBasicInfoFilled() {
		return isBasicInfoFilled;
	}

	public Boolean getIsEmploymentInfoFilled() {
		return isEmploymentInfoFilled;
	}

	public Boolean getIsContactInfoFilled() {
		return isContactInfoFilled;
	}

	public Boolean getIsCreditInfoFilled() {
		return isCreditInfoFilled;
	}

	public void setIsBasicInfoFilled(Boolean isBasicInfoFilled) {
		this.isBasicInfoFilled = isBasicInfoFilled;
	}

	public void setIsEmploymentInfoFilled(Boolean isEmploymentInfoFilled) {
		this.isEmploymentInfoFilled = isEmploymentInfoFilled;
	}

	public void setIsContactInfoFilled(Boolean isContactInfoFilled) {
		this.isContactInfoFilled = isContactInfoFilled;
	}

	public void setIsCreditInfoFilled(Boolean isCreditInfoFilled) {
		this.isCreditInfoFilled = isCreditInfoFilled;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	



}