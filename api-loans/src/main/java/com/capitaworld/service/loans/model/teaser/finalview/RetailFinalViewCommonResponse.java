package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailResponse;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailResponse;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailResponse;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;

public class RetailFinalViewCommonResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String caste;
	private String casteOther;
	private String religion;
	private String religionOther;
	private String birthPlace;
	
	private String fatherFullName;
	private String motherName;
	private String spouseName;
	private String spouseEmployed;
	private String noOfChildren;
	private String noOfDependents;
	private String highestQualification;
	private String highestQualificationOther;
	private String qualifyingYear;
	private String instituteName;
	
	private String residenceType;
	private String annualRent;
	private String yearAtCurrentResident;
	private String monthsAtCurrentResident;
	private String website;
	
	private String employmentStatus;
	private String currentIndustry;
	private String currentDepartment;
	private String currentDesignation;
	private String yearsInCurrentJob;
	private String monthsInCurrentJob;
	private String totalExperienceInYears;
	private String totalExperienceInMonths;
	private String previousExperienceInYears;
	private String previousExperienceInMonths;
	private String previousEmployerName;
	private String previousEmployerAddress;
	private String totalLandOwned;
	private String presentlyIrrigated;
	private String seasonalIrrigated;
	private String rainFed;
	private String unAttended;
	private String entityName;
	private String ownershipType;
	private String officeType;
	private String noOfPartners;
	private String nameOfPartners;
	private String businessEstablishmentYear;
	private String shareHolding;
	private String annualTurnover;
	private String tradeLicenseNo;
	private String tradeExpiryDate;
	private String nameOfPoaHolder;
	
	private List<ExistingLoanDetailRequest> existingLoanDetailRequest;
	private List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequest;
	private List<CreditCardsDetailResponse> creditCardsDetailResponse;
	private List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequest;
	private List<OtherCurrentAssetDetailResponse> assetDetailResponseList;
	private List<OtherIncomeDetailResponse> incomeDetailResponseList;
	private List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequest;
	
	private String repaymentMode;
	private String repaymentCycle;
	private String interestRateOption;
	
	private List<Object> applicant_BankACStatments;
	private List<Object> applicant_SalaraySlip;
	private List<Object> applicant_ItReturn;
	private List<Object> applicant_BalanceSheet;
	private List<Object> applicant_AddressProof;
	private List<Object> applicant_IncomeProof;
	private List<Object> applicant_CropCultivation;
	private List<Object> applicant_AlliedActivities;
	private List<Object> coApplicant_BankACStatments;
	private List<Object> coApplicant_SalaraySlip;
	private List<Object> coApplicant_ItReturn;
	private List<Object> coApplicant_BalanceSheet;
	private List<Object> coApplicant_AddressProof;
	private List<Object> coApplicant_IncomeProof;
	private List<Object> coApplicant_CropCultivation;
	private List<Object> coApplicant_AlliedActivities;
	private List<Object> guarantor_BankACStatments;
	private List<Object> guarantor_SalaraySlip;
	private List<Object> guarantor_ItReturn;
	private List<Object> guarantor_BalanceSheet;
	private List<Object> guarantor_AddressProof;
	private List<Object> guarantor_IncomeProof;
	private List<Object> guarantor_CropCultivation;
	private List<Object> guarantor_AlliedActivities;
	
	public List<Object> getApplicant_BankACStatments() {
		return applicant_BankACStatments;
	}
	public void setApplicant_BankACStatments(List<Object> applicant_BankACStatments) {
		this.applicant_BankACStatments = applicant_BankACStatments;
	}
	public List<Object> getApplicant_SalaraySlip() {
		return applicant_SalaraySlip;
	}
	public void setApplicant_SalaraySlip(List<Object> applicant_SalaraySlip) {
		this.applicant_SalaraySlip = applicant_SalaraySlip;
	}
	public List<Object> getApplicant_ItReturn() {
		return applicant_ItReturn;
	}
	public void setApplicant_ItReturn(List<Object> applicant_ItReturn) {
		this.applicant_ItReturn = applicant_ItReturn;
	}
	public List<Object> getApplicant_BalanceSheet() {
		return applicant_BalanceSheet;
	}
	public void setApplicant_BalanceSheet(List<Object> applicant_BalanceSheet) {
		this.applicant_BalanceSheet = applicant_BalanceSheet;
	}
	public List<Object> getApplicant_AddressProof() {
		return applicant_AddressProof;
	}
	public void setApplicant_AddressProof(List<Object> applicant_AddressProof) {
		this.applicant_AddressProof = applicant_AddressProof;
	}
	public List<Object> getApplicant_IncomeProof() {
		return applicant_IncomeProof;
	}
	public void setApplicant_IncomeProof(List<Object> applicant_IncomeProof) {
		this.applicant_IncomeProof = applicant_IncomeProof;
	}
	public List<Object> getApplicant_CropCultivation() {
		return applicant_CropCultivation;
	}
	public void setApplicant_CropCultivation(List<Object> applicant_CropCultivation) {
		this.applicant_CropCultivation = applicant_CropCultivation;
	}
	public List<Object> getApplicant_AlliedActivities() {
		return applicant_AlliedActivities;
	}
	public void setApplicant_AlliedActivities(List<Object> applicant_AlliedActivities) {
		this.applicant_AlliedActivities = applicant_AlliedActivities;
	}
	public List<Object> getCoApplicant_BankACStatments() {
		return coApplicant_BankACStatments;
	}
	public void setCoApplicant_BankACStatments(List<Object> coApplicant_BankACStatments) {
		this.coApplicant_BankACStatments = coApplicant_BankACStatments;
	}
	public List<Object> getCoApplicant_SalaraySlip() {
		return coApplicant_SalaraySlip;
	}
	public void setCoApplicant_SalaraySlip(List<Object> coApplicant_SalaraySlip) {
		this.coApplicant_SalaraySlip = coApplicant_SalaraySlip;
	}
	public List<Object> getCoApplicant_ItReturn() {
		return coApplicant_ItReturn;
	}
	public void setCoApplicant_ItReturn(List<Object> coApplicant_ItReturn) {
		this.coApplicant_ItReturn = coApplicant_ItReturn;
	}
	public List<Object> getCoApplicant_BalanceSheet() {
		return coApplicant_BalanceSheet;
	}
	public void setCoApplicant_BalanceSheet(List<Object> coApplicant_BalanceSheet) {
		this.coApplicant_BalanceSheet = coApplicant_BalanceSheet;
	}
	public List<Object> getCoApplicant_AddressProof() {
		return coApplicant_AddressProof;
	}
	public void setCoApplicant_AddressProof(List<Object> coApplicant_AddressProof) {
		this.coApplicant_AddressProof = coApplicant_AddressProof;
	}
	public List<Object> getCoApplicant_IncomeProof() {
		return coApplicant_IncomeProof;
	}
	public void setCoApplicant_IncomeProof(List<Object> coApplicant_IncomeProof) {
		this.coApplicant_IncomeProof = coApplicant_IncomeProof;
	}
	public List<Object> getCoApplicant_CropCultivation() {
		return coApplicant_CropCultivation;
	}
	public void setCoApplicant_CropCultivation(List<Object> coApplicant_CropCultivation) {
		this.coApplicant_CropCultivation = coApplicant_CropCultivation;
	}
	public List<Object> getCoApplicant_AlliedActivities() {
		return coApplicant_AlliedActivities;
	}
	public void setCoApplicant_AlliedActivities(List<Object> coApplicant_AlliedActivities) {
		this.coApplicant_AlliedActivities = coApplicant_AlliedActivities;
	}
	public List<Object> getGuarantor_BankACStatments() {
		return guarantor_BankACStatments;
	}
	public void setGuarantor_BankACStatments(List<Object> guarantor_BankACStatments) {
		this.guarantor_BankACStatments = guarantor_BankACStatments;
	}
	public List<Object> getGuarantor_SalaraySlip() {
		return guarantor_SalaraySlip;
	}
	public void setGuarantor_SalaraySlip(List<Object> guarantor_SalaraySlip) {
		this.guarantor_SalaraySlip = guarantor_SalaraySlip;
	}
	public List<Object> getGuarantor_ItReturn() {
		return guarantor_ItReturn;
	}
	public void setGuarantor_ItReturn(List<Object> guarantor_ItReturn) {
		this.guarantor_ItReturn = guarantor_ItReturn;
	}
	public List<Object> getGuarantor_BalanceSheet() {
		return guarantor_BalanceSheet;
	}
	public void setGuarantor_BalanceSheet(List<Object> guarantor_BalanceSheet) {
		this.guarantor_BalanceSheet = guarantor_BalanceSheet;
	}
	public List<Object> getGuarantor_AddressProof() {
		return guarantor_AddressProof;
	}
	public void setGuarantor_AddressProof(List<Object> guarantor_AddressProof) {
		this.guarantor_AddressProof = guarantor_AddressProof;
	}
	public List<Object> getGuarantor_IncomeProof() {
		return guarantor_IncomeProof;
	}
	public void setGuarantor_IncomeProof(List<Object> guarantor_IncomeProof) {
		this.guarantor_IncomeProof = guarantor_IncomeProof;
	}
	public List<Object> getGuarantor_CropCultivation() {
		return guarantor_CropCultivation;
	}
	public void setGuarantor_CropCultivation(List<Object> guarantor_CropCultivation) {
		this.guarantor_CropCultivation = guarantor_CropCultivation;
	}
	public List<Object> getGuarantor_AlliedActivities() {
		return guarantor_AlliedActivities;
	}
	public void setGuarantor_AlliedActivities(List<Object> guarantor_AlliedActivities) {
		this.guarantor_AlliedActivities = guarantor_AlliedActivities;
	}
	public List<OtherIncomeDetailResponse> getIncomeDetailResponseList() {
		return incomeDetailResponseList;
	}
	public void setIncomeDetailResponseList(List<OtherIncomeDetailResponse> incomeDetailResponseList) {
		this.incomeDetailResponseList = incomeDetailResponseList;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getCasteOther() {
		return casteOther;
	}
	public void setCasteOther(String casteOther) {
		this.casteOther = casteOther;
	}
	
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
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
	public String getFatherFullName() {
		return fatherFullName;
	}
	public void setFatherFullName(String fatherFullName) {
		this.fatherFullName = fatherFullName;
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
	public String getSpouseEmployed() {
		return spouseEmployed;
	}
	public void setSpouseEmployed(String spouseEmployed) {
		this.spouseEmployed = spouseEmployed;
	}
	public String getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(String noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public String getNoOfDependents() {
		return noOfDependents;
	}
	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public String getHighestQualificationOther() {
		return highestQualificationOther;
	}
	public void setHighestQualificationOther(String highestQualificationOther) {
		this.highestQualificationOther = highestQualificationOther;
	}
	public String getQualifyingYear() {
		return qualifyingYear;
	}
	public void setQualifyingYear(String qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getResidenceType() {
		return residenceType;
	}
	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}
	public String getAnnualRent() {
		return annualRent;
	}
	public void setAnnualRent(String annualRent) {
		this.annualRent = annualRent;
	}
	public String getYearAtCurrentResident() {
		return yearAtCurrentResident;
	}
	public void setYearAtCurrentResident(String yearAtCurrentResident) {
		this.yearAtCurrentResident = yearAtCurrentResident;
	}
	public String getMonthsAtCurrentResident() {
		return monthsAtCurrentResident;
	}
	public void setMonthsAtCurrentResident(String monthsAtCurrentResident) {
		this.monthsAtCurrentResident = monthsAtCurrentResident;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
	public String getCurrentIndustry() {
		return currentIndustry;
	}
	public void setCurrentIndustry(String currentIndustry) {
		this.currentIndustry = currentIndustry;
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
	public String getYearsInCurrentJob() {
		return yearsInCurrentJob;
	}
	public void setYearsInCurrentJob(String yearsInCurrentJob) {
		this.yearsInCurrentJob = yearsInCurrentJob;
	}
	public String getMonthsInCurrentJob() {
		return monthsInCurrentJob;
	}
	public void setMonthsInCurrentJob(String monthsInCurrentJob) {
		this.monthsInCurrentJob = monthsInCurrentJob;
	}
	public String getTotalExperienceInYears() {
		return totalExperienceInYears;
	}
	public void setTotalExperienceInYears(String totalExperienceInYears) {
		this.totalExperienceInYears = totalExperienceInYears;
	}
	public String getTotalExperienceInMonths() {
		return totalExperienceInMonths;
	}
	public void setTotalExperienceInMonths(String totalExperienceInMonths) {
		this.totalExperienceInMonths = totalExperienceInMonths;
	}
	public String getPreviousEmployerName() {
		return previousEmployerName;
	}
	public void setPreviousEmployerName(String previousEmployerName) {
		this.previousEmployerName = previousEmployerName;
	}
	public String getPreviousEmployerAddress() {
		return previousEmployerAddress;
	}
	public void setPreviousEmployerAddress(String previousEmployerAddress) {
		this.previousEmployerAddress = previousEmployerAddress;
	}
	public String getTotalLandOwned() {
		return totalLandOwned;
	}
	public void setTotalLandOwned(String totalLandOwned) {
		this.totalLandOwned = totalLandOwned;
	}
	public String getPresentlyIrrigated() {
		return presentlyIrrigated;
	}
	public void setPresentlyIrrigated(String presentlyIrrigated) {
		this.presentlyIrrigated = presentlyIrrigated;
	}
	public String getSeasonalIrrigated() {
		return seasonalIrrigated;
	}
	public void setSeasonalIrrigated(String seasonalIrrigated) {
		this.seasonalIrrigated = seasonalIrrigated;
	}
	public String getRainFed() {
		return rainFed;
	}
	public void setRainFed(String rainFed) {
		this.rainFed = rainFed;
	}
	public String getUnAttended() {
		return unAttended;
	}
	public void setUnAttended(String unAttended) {
		this.unAttended = unAttended;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getOwnershipType() {
		return ownershipType;
	}
	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	public String getNoOfPartners() {
		return noOfPartners;
	}
	public void setNoOfPartners(String noOfPartners) {
		this.noOfPartners = noOfPartners;
	}
	public String getNameOfPartners() {
		return nameOfPartners;
	}
	public void setNameOfPartners(String nameOfPartners) {
		this.nameOfPartners = nameOfPartners;
	}
	public String getBusinessEstablishmentYear() {
		return businessEstablishmentYear;
	}
	public void setBusinessEstablishmentYear(String businessEstablishmentYear) {
		this.businessEstablishmentYear = businessEstablishmentYear;
	}
	public String getShareHolding() {
		return shareHolding;
	}
	public void setShareHolding(String shareHolding) {
		this.shareHolding = shareHolding;
	}
	public String getAnnualTurnover() {
		return annualTurnover;
	}
	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}
	public String getTradeLicenseNo() {
		return tradeLicenseNo;
	}
	public void setTradeLicenseNo(String tradeLicenseNo) {
		this.tradeLicenseNo = tradeLicenseNo;
	}
	public String getTradeExpiryDate() {
		return tradeExpiryDate;
	}
	public void setTradeExpiryDate(String tradeExpiryDate) {
		this.tradeExpiryDate = tradeExpiryDate;
	}
	public String getNameOfPoaHolder() {
		return nameOfPoaHolder;
	}
	public void setNameOfPoaHolder(String nameOfPoaHolder) {
		this.nameOfPoaHolder = nameOfPoaHolder;
	}
	
	public List<ExistingLoanDetailRequest> getExistingLoanDetailRequest() {
		return existingLoanDetailRequest;
	}
	public void setExistingLoanDetailRequest(List<ExistingLoanDetailRequest> existingLoanDetailRequest) {
		this.existingLoanDetailRequest = existingLoanDetailRequest;
	}
	public List<BankAccountHeldDetailsRequest> getBankAccountHeldDetailsRequest() {
		return bankAccountHeldDetailsRequest;
	}
	public void setBankAccountHeldDetailsRequest(List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequest) {
		this.bankAccountHeldDetailsRequest = bankAccountHeldDetailsRequest;
	}
	public List<CreditCardsDetailResponse> getCreditCardsDetailResponse() {
		return creditCardsDetailResponse;
	}
	public void setCreditCardsDetailResponse(List<CreditCardsDetailResponse> creditCardsDetailResponse) {
		this.creditCardsDetailResponse = creditCardsDetailResponse;
	}
	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailsRequest() {
		return fixedDepositsDetailsRequest;
	}
	public void setFixedDepositsDetailsRequest(List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequest) {
		this.fixedDepositsDetailsRequest = fixedDepositsDetailsRequest;
	}
	public List<OtherCurrentAssetDetailResponse> getAssetDetailResponseList() {
		return assetDetailResponseList;
	}
	public void setAssetDetailResponseList(List<OtherCurrentAssetDetailResponse> assetDetailResponseList) {
		this.assetDetailResponseList = assetDetailResponseList;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
	public String getRepaymentCycle() {
		return repaymentCycle;
	}
	public void setRepaymentCycle(String repaymentCycle) {
		this.repaymentCycle = repaymentCycle;
	}
	public String getInterestRateOption() {
		return interestRateOption;
	}
	public void setInterestRateOption(String interestRateOption) {
		this.interestRateOption = interestRateOption;
	}
	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailsRequest() {
		return referenceRetailDetailsRequest;
	}
	public void setReferenceRetailDetailsRequest(List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequest) {
		this.referenceRetailDetailsRequest = referenceRetailDetailsRequest;
	}
	public String getPreviousExperienceInYears() {
		return previousExperienceInYears;
	}
	public void setPreviousExperienceInYears(String previousExperienceInYears) {
		this.previousExperienceInYears = previousExperienceInYears;
	}
	public String getPreviousExperienceInMonths() {
		return previousExperienceInMonths;
	}
	public void setPreviousExperienceInMonths(String previousExperienceInMonths) {
		this.previousExperienceInMonths = previousExperienceInMonths;
	}
	
	
}
