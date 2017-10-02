package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * @author Sanket
 *
 */

@Entity
@Table(name = "fs_retail_guarantor_details")
public class GuarantorDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name = "aadhar_number")
	private String aadharNumber;
	
	@Column(name = "name_as_per_aadharCard")
	private String nameAsPerAadharCard;

	@Column(name = "address_city")
	private Integer addressCity;

	@Column(name = "address_country")
	private Integer addressCountry;

	@Column(name = "address_landmark")
	private String addressLandmark;

	@Column(name = "address_pincode")
	private BigInteger addressPincode;

	@Column(name = "address_premise_name")
	private String addressPremiseName;

	@Column(name = "address_same_as")
	private Boolean addressSameAs;

	@Column(name = "address_state")
	private Integer addressState;

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

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

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
	private Integer officeCityId;

	@Column(name = "office_country_id")
	private Integer officeCountryId;

	@Column(name = "office_land_mark")
	private String officeLandMark;

	@Column(name = "office_pincode")
	private Integer officePincode;

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

	@Column(name = "partners_name")
	private String partnersName;

	@Column(name = "permanent_city_id")
	private Integer permanentCityId;

	@Column(name = "permanent_country_id")
	private Integer permanentCountryId;

	@Column(name = "permanent_land_mark")
	private String permanentLandMark;

	@Column(name = "permanent_pincode")
	private Integer permanentPincode;

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

	// @Column(name = "relationship_with_applicant")
	// private Integer relationshipWithApplicant;

	private Integer religion;

	@Column(name = "religion_other")
	private String religionOther;

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
	@Column(name = "trade_license_expiry_date")
	private Date tradeLicenseExpiryDate;

	@Column(name = "trade_license_number")
	private String tradeLicenseNumber;

	private String unattended;

	@Column(name = "website_address")
	private String websiteAddress;

	@Column(name = "gender_id")
	private Integer genderId;

	@Column(name = "contact_no")
	private String contactNo;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(Integer addressCity) {
		this.addressCity = addressCity;
	}

	public Integer getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Integer addressCountry) {
		this.addressCountry = addressCountry;
	}

	public String getAddressLandmark() {
		return addressLandmark;
	}

	public void setAddressLandmark(String addressLandmark) {
		this.addressLandmark = addressLandmark;
	}

	public BigInteger getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(BigInteger addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getAddressPremiseName() {
		return addressPremiseName;
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

	public Integer getAddressState() {
		return addressState;
	}

	public void setAddressState(Integer addressState) {
		this.addressState = addressState;
	}

	public String getAddressStreetName() {
		return addressStreetName;
	}

	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}

	public Integer getAlliedActivityId() {
		return alliedActivityId;
	}

	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}

	public Double getAnnualRent() {
		return annualRent;
	}

	public void setAnnualRent(Double annualRent) {
		this.annualRent = annualRent;
	}

	public Double getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAnnualTurnover(Double annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public Integer getCastId() {
		return castId;
	}

	public void setCastId(Integer castId) {
		this.castId = castId;
	}

	public String getCastOther() {
		return castOther;
	}

	public void setCastOther(String castOther) {
		this.castOther = castOther;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCurrentDepartment() {
		return currentDepartment;
	}

	public void setCurrentDepartment(String currentDepartment) {
		this.currentDepartment = currentDepartment;
	}

	public String getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public String getCurrentIndustry() {
		return currentIndustry;
	}

	public void setCurrentIndustry(String currentIndustry) {
		this.currentIndustry = currentIndustry;
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

	public Integer getEmployedWithId() {
		return employedWithId;
	}

	public void setEmployedWithId(Integer employedWithId) {
		this.employedWithId = employedWithId;
	}

	public String getEmployedWithOther() {
		return employedWithOther;
	}

	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}

	public Integer getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(Integer highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getHighestQualificationOther() {
		return highestQualificationOther;
	}

	public void setHighestQualificationOther(String highestQualificationOther) {
		this.highestQualificationOther = highestQualificationOther;
	}

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeOther() {
		return industryTypeOther;
	}

	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
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

	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
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

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}

	public Integer getNoChildren() {
		return noChildren;
	}

	public void setNoChildren(Integer noChildren) {
		this.noChildren = noChildren;
	}

	public Integer getNoDependent() {
		return noDependent;
	}

	public void setNoDependent(Integer noDependent) {
		this.noDependent = noDependent;
	}

	public Integer getNoPartners() {
		return noPartners;
	}

	public void setNoPartners(Integer noPartners) {
		this.noPartners = noPartners;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Integer getOfficeCityId() {
		return officeCityId;
	}

	public void setOfficeCityId(Integer officeCityId) {
		this.officeCityId = officeCityId;
	}

	public Integer getOfficeCountryId() {
		return officeCountryId;
	}

	public void setOfficeCountryId(Integer officeCountryId) {
		this.officeCountryId = officeCountryId;
	}

	public String getOfficeLandMark() {
		return officeLandMark;
	}

	public void setOfficeLandMark(String officeLandMark) {
		this.officeLandMark = officeLandMark;
	}

	public Integer getOfficePincode() {
		return officePincode;
	}

	public void setOfficePincode(Integer officePincode) {
		this.officePincode = officePincode;
	}

	public String getOfficePremiseNumberName() {
		return officePremiseNumberName;
	}

	public void setOfficePremiseNumberName(String officePremiseNumberName) {
		this.officePremiseNumberName = officePremiseNumberName;
	}

	public Integer getOfficeStateId() {
		return officeStateId;
	}

	public void setOfficeStateId(Integer officeStateId) {
		this.officeStateId = officeStateId;
	}

	public String getOfficeStreetName() {
		return officeStreetName;
	}

	public void setOfficeStreetName(String officeStreetName) {
		this.officeStreetName = officeStreetName;
	}

	public Integer getOfficeType() {
		return officeType;
	}

	public void setOfficeType(Integer officeType) {
		this.officeType = officeType;
	}

	public Integer getOwnershipType() {
		return ownershipType;
	}

	public void setOwnershipType(Integer ownershipType) {
		this.ownershipType = ownershipType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPartnersName() {
		return partnersName;
	}

	public void setPartnersName(String partnersName) {
		this.partnersName = partnersName;
	}

	public Integer getPermanentCityId() {
		return permanentCityId;
	}

	public void setPermanentCityId(Integer permanentCityId) {
		this.permanentCityId = permanentCityId;
	}

	public Integer getPermanentCountryId() {
		return permanentCountryId;
	}

	public void setPermanentCountryId(Integer permanentCountryId) {
		this.permanentCountryId = permanentCountryId;
	}

	public String getPermanentLandMark() {
		return permanentLandMark;
	}

	public void setPermanentLandMark(String permanentLandMark) {
		this.permanentLandMark = permanentLandMark;
	}

	public Integer getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(Integer permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public String getPermanentPremiseNumberName() {
		return permanentPremiseNumberName;
	}

	public void setPermanentPremiseNumberName(String permanentPremiseNumberName) {
		this.permanentPremiseNumberName = permanentPremiseNumberName;
	}

	public Integer getPermanentStateId() {
		return permanentStateId;
	}

	public void setPermanentStateId(Integer permanentStateId) {
		this.permanentStateId = permanentStateId;
	}

	public String getPermanentStreetName() {
		return permanentStreetName;
	}

	public void setPermanentStreetName(String permanentStreetName) {
		this.permanentStreetName = permanentStreetName;
	}

	public String getPoaHolderName() {
		return poaHolderName;
	}

	public void setPoaHolderName(String poaHolderName) {
		this.poaHolderName = poaHolderName;
	}

	public String getPresentlyIrrigated() {
		return presentlyIrrigated;
	}

	public void setPresentlyIrrigated(String presentlyIrrigated) {
		this.presentlyIrrigated = presentlyIrrigated;
	}

	public String getPreviousEmployersAddress() {
		return previousEmployersAddress;
	}

	public void setPreviousEmployersAddress(String previousEmployersAddress) {
		this.previousEmployersAddress = previousEmployersAddress;
	}

	public String getPreviousEmployersName() {
		return previousEmployersName;
	}

	public void setPreviousEmployersName(String previousEmployersName) {
		this.previousEmployersName = previousEmployersName;
	}

	public Integer getPreviousJobMonth() {
		return previousJobMonth;
	}

	public void setPreviousJobMonth(Integer previousJobMonth) {
		this.previousJobMonth = previousJobMonth;
	}

	public Integer getPreviousJobYear() {
		return previousJobYear;
	}

	public void setPreviousJobYear(Integer previousJobYear) {
		this.previousJobYear = previousJobYear;
	}

	public Date getQualifyingYear() {
		return qualifyingYear;
	}

	public void setQualifyingYear(Date qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public String getRainFed() {
		return rainFed;
	}

	public void setRainFed(String rainFed) {
		this.rainFed = rainFed;
	}

	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}

	public String getReligionOther() {
		return religionOther;
	}

	public void setReligionOther(String religionOther) {
		this.religionOther = religionOther;
	}

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
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

	public String getSeasonalIrrigated() {
		return seasonalIrrigated;
	}

	public void setSeasonalIrrigated(String seasonalIrrigated) {
		this.seasonalIrrigated = seasonalIrrigated;
	}

	public Integer getSelfEmployedOccupationId() {
		return selfEmployedOccupationId;
	}

	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}

	public String getSelfEmployedOccupationOther() {
		return selfEmployedOccupationOther;
	}

	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}
	
	public String getShareHolding() {
		return shareHolding;
	}

	public void setShareHolding(String shareHolding) {
		this.shareHolding = shareHolding;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
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

	public Double getTotalLandOwned() {
		return totalLandOwned;
	}

	public void setTotalLandOwned(Double totalLandOwned) {
		this.totalLandOwned = totalLandOwned;
	}

	public Date getTradeLicenseExpiryDate() {
		return tradeLicenseExpiryDate;
	}

	public void setTradeLicenseExpiryDate(Date tradeLicenseExpiryDate) {
		this.tradeLicenseExpiryDate = tradeLicenseExpiryDate;
	}

	public String getTradeLicenseNumber() {
		return tradeLicenseNumber;
	}

	public void setTradeLicenseNumber(String tradeLicenseNumber) {
		this.tradeLicenseNumber = tradeLicenseNumber;
	}

	public String getUnattended() {
		return unattended;
	}

	public void setUnattended(String unattended) {
		this.unattended = unattended;
	}

	public String getWebsiteAddress() {
		return websiteAddress;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	
	

}
