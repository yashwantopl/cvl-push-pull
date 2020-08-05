package com.opl.mudra.api.loans.model.retail;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FinalCommonRetailRequestOld {

	private Long id;
	private Long clientId;
	private Long applicationId;
	// Based on these two fields we have show some field in final section

	// Primary Field starts
	private Integer statusId;
	private Integer occupationId;
	private String currencyValue;
	// Primary Field Ends

	private Integer castId;
	private String castOther;
	private Integer religion;
	private String religionOther;
	private String birthPlace;
	private String fatherName;
	private String motherName;
	private Integer noChildren;
	private Integer noDependent;
	private String highestQualificationOther;
	
	
	private Integer residenceType;
	private Double annualRent;
	private Double annualTurnover;
	private Integer noPartners;
	private Date birthDate;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date businessStartDate;
	private String currentDepartment;
	private String currentDesignation;
	private String currentIndustry;
	private Integer currentJobMonth;
	private Integer currentJobYear;
	private Integer employmentStatus;
	private Integer interestRate;
	private String nameOfEntity;
	private Integer officeType;
	private Integer ownershipType;
	private String partnersName;
	private String poaHolderName;
	private String presentlyIrrigated;
	private String previousEmployersAddress;
	private String previousEmployersName;
	private Integer previousJobMonth;
	private Integer previousJobYear;
	private String rainFed;
	private Integer repaymentCycle;
	private Integer repaymentMode;
	private String seasonalIrrigated;
	private String shareHolding;
	private Integer totalExperienceMonth;
	private Integer totalExperienceYear;
	private Double totalLandOwned;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date drivingLicenseExpiryDate;
	private String drivingLicenseNumber;
	private String unattended;
	private String websiteAddress;
	
	private Boolean isApplicantFinalFilled;
	private Boolean isCoApp1FinalFilled;
	private Boolean isCoApp2FinalFilled;
	
	private Boolean isGuarantor1FinalFilled;
	private Boolean isGuarantor2FinalFilled;
	
	private String finalFilledCount;
	
	//Added by bihag for UBI - HL
		private String ownershipTypeOthers;

		public String getOwnershipTypeOthers() {
			return ownershipTypeOthers;
		}

		public void setOwnershipTypeOthers(String ownershipTypeOthers) {
			this.ownershipTypeOthers = ownershipTypeOthers;
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

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
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


	public String getHighestQualificationOther() {
		return highestQualificationOther;
	}

	public void setHighestQualificationOther(String highestQualificationOther) {
		this.highestQualificationOther = highestQualificationOther;
	}

	

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
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

	public Integer getNoPartners() {
		return noPartners;
	}

	public void setNoPartners(Integer noPartners) {
		this.noPartners = noPartners;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
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

	public Integer getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(Integer employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public Integer getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Integer interestRate) {
		this.interestRate = interestRate;
	}

	public String getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(String nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
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

	public String getPartnersName() {
		return partnersName;
	}

	public void setPartnersName(String partnersName) {
		this.partnersName = partnersName;
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

	public String getRainFed() {
		return rainFed;
	}

	public void setRainFed(String rainFed) {
		this.rainFed = rainFed;
	}

	public Integer getRepaymentCycle() {
		return repaymentCycle;
	}

	public void setRepaymentCycle(Integer repaymentCycle) {
		this.repaymentCycle = repaymentCycle;
	}

	public Integer getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(Integer repaymentMode) {
		this.repaymentMode = repaymentMode;
	}


	public String getSeasonalIrrigated() {
		return seasonalIrrigated;
	}

	public void setSeasonalIrrigated(String seasonalIrrigated) {
		this.seasonalIrrigated = seasonalIrrigated;
	}

	public String getShareHolding() {
		return shareHolding;
	}

	public void setShareHolding(String shareHolding) {
		this.shareHolding = shareHolding;
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

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Boolean getIsApplicantFinalFilled() {
		return isApplicantFinalFilled;
	}

	public void setIsApplicantFinalFilled(Boolean isApplicantFinalFilled) {
		this.isApplicantFinalFilled = isApplicantFinalFilled;
	}

	public Boolean getIsCoApp1FinalFilled() {
		return isCoApp1FinalFilled;
	}

	public void setIsCoApp1FinalFilled(Boolean isCoApp1FinalFilled) {
		this.isCoApp1FinalFilled = isCoApp1FinalFilled;
	}

	public Boolean getIsCoApp2FinalFilled() {
		return isCoApp2FinalFilled;
	}

	public void setIsCoApp2FinalFilled(Boolean isCoApp2FinalFilled) {
		this.isCoApp2FinalFilled = isCoApp2FinalFilled;
	}

	public Boolean getIsGuarantor1FinalFilled() {
		return isGuarantor1FinalFilled;
	}

	public void setIsGuarantor1FinalFilled(Boolean isGuarantor1FinalFilled) {
		this.isGuarantor1FinalFilled = isGuarantor1FinalFilled;
	}

	public Boolean getIsGuarantor2FinalFilled() {
		return isGuarantor2FinalFilled;
	}

	public void setIsGuarantor2FinalFilled(Boolean isGuarantor2FinalFilled) {
		this.isGuarantor2FinalFilled = isGuarantor2FinalFilled;
	}

	public String getFinalFilledCount() {
		return finalFilledCount;
	}

	public void setFinalFilledCount(String finalFilledCount) {
		this.finalFilledCount = finalFilledCount;
	}

}
