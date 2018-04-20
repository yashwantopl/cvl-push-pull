package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;

public class CorporateFinalViewResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<?> matchesList;
    private String organisationName;
    private String aboutUs;
    private String panNo;
    private String websiteAddress;
    private String city;
    private String regOfficeCity;
    private String addOfficeCity;
    private String country;
    private String regOfficecountry;
    private String addOfficecountry;
    private String state;
    private String regOfficestate;
    private String addOfficestate;
    private String constitution;
    private String establishmentMonth;
    private String establishmentYear;
    private String groupName;
    private String keyVericalFunding;
    private String keyVericalSector;
    private String keyVericalSubsector;
    private String projectBrief;
    private String currencyDenomination;
    private String LoanType;
    private String dateOfProposal;
    private String isCreditRatingAvailable;
    private Double sharePriceFace;
    private Double sharePriceMarket;
    private String loanAmount;
    private String gstIn;
    private String haveCollateralSecurity;
    private String collateralSecurityAmount;
    private List<Object> profilePic;

    private String purposeOfLoan;
    private String  businessAssetAmount;
    private String  wcAmount;
    private String  otherAmt;
    private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
    private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
    private Object FinancialInputRequest;
    
    //FINAL VIEW RESPONSE OBJECT
    private String udhyogAadharNo;
    private String creditRating;
    
    private List<AchievementDetailRequest> AchievementDetailList;
    private List<CreditRatingOrganizationDetailResponse> CreditRatingOrganizationDetailResponse;
    private List<ProposedProductDetailRequest> proposedProductDetailRequestList;
    private List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList;
    private List<ExistingProductDetailRequest> existingProductDetailRequestList;
    private List<AssociatedConcernDetailRequest> associatedConcernDetailRequests;
    
    private String contigentLiablitiesFy;
    private String contigentLiablitiesSy;
    private String contigentLiablitiesTy;
    
    
    //FINAL MCQ
    
    private String technologyRiskId;
    private String customerQuality;
    private String supplierQuality;
    private String sustainabilityProduct;
    private String orderBookPosition;
    private String employeeRelations;
    private String productSeasonality;
    private String impactOnOperatingMargins;
    private String environmentalImpact;
    private String accountingQuality;
    private String financialRestructuringHistory;
    private String integrity;
    private String businessCommitment;
    private String managementCompetence;
    private String businessExperience;
    private String successionPlanning;
    private String financialStrength;
    private String financialSupport;
    private String abilityToRaiseFunds;
    private String intraCompany;
    private String internalControl;
    private String creditTrackRecord;
    private String statusOfProjectClearances;
    private String statusOfFinancialClosure;
    private String infrastructureAvailability;
    private String constructionContract;
    private String numberOfCheques;
    private String numberOfTimesDp;
    private String cumulativeNoOfDaysDp;
    private String complianceWithSanctioned;
    private String progressReports;
    private String delayInReceipt;
    private String delayInSubmission;
    private String numberOfLc;
    private String unhedgedForeignCurrency;
    private String projectedDebtService;
    private String internalRateReturn;
    private String sensititivityAnalysis;
    private String varianceInProjectedSales;
    
    
	public List<?> getMatchesList() {
		return matchesList;
	}
	public void setMatchesList(List<?> matchesList) {
		this.matchesList = matchesList;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getAboutUs() {
		return aboutUs;
	}
	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getWebsiteAddress() {
		return websiteAddress;
	}
	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegOfficeCity() {
		return regOfficeCity;
	}
	public void setRegOfficeCity(String regOfficeCity) {
		this.regOfficeCity = regOfficeCity;
	}
	public String getAddOfficeCity() {
		return addOfficeCity;
	}
	public void setAddOfficeCity(String addOfficeCity) {
		this.addOfficeCity = addOfficeCity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegOfficecountry() {
		return regOfficecountry;
	}
	public void setRegOfficecountry(String regOfficecountry) {
		this.regOfficecountry = regOfficecountry;
	}
	public String getAddOfficecountry() {
		return addOfficecountry;
	}
	public void setAddOfficecountry(String addOfficecountry) {
		this.addOfficecountry = addOfficecountry;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRegOfficestate() {
		return regOfficestate;
	}
	public void setRegOfficestate(String regOfficestate) {
		this.regOfficestate = regOfficestate;
	}
	public String getAddOfficestate() {
		return addOfficestate;
	}
	public void setAddOfficestate(String addOfficestate) {
		this.addOfficestate = addOfficestate;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getEstablishmentMonth() {
		return establishmentMonth;
	}
	public void setEstablishmentMonth(String establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}
	public String getEstablishmentYear() {
		return establishmentYear;
	}
	public void setEstablishmentYear(String establishmentYear) {
		this.establishmentYear = establishmentYear;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getKeyVericalFunding() {
		return keyVericalFunding;
	}
	public void setKeyVericalFunding(String keyVericalFunding) {
		this.keyVericalFunding = keyVericalFunding;
	}
	public String getKeyVericalSector() {
		return keyVericalSector;
	}
	public void setKeyVericalSector(String keyVericalSector) {
		this.keyVericalSector = keyVericalSector;
	}
	public String getKeyVericalSubsector() {
		return keyVericalSubsector;
	}
	public void setKeyVericalSubsector(String keyVericalSubsector) {
		this.keyVericalSubsector = keyVericalSubsector;
	}
	public String getProjectBrief() {
		return projectBrief;
	}
	public void setProjectBrief(String projectBrief) {
		this.projectBrief = projectBrief;
	}
	public String getCurrencyDenomination() {
		return currencyDenomination;
	}
	public void setCurrencyDenomination(String currencyDenomination) {
		this.currencyDenomination = currencyDenomination;
	}
	public String getLoanType() {
		return LoanType;
	}
	public void setLoanType(String loanType) {
		LoanType = loanType;
	}
	public String getDateOfProposal() {
		return dateOfProposal;
	}
	public void setDateOfProposal(String dateOfProposal) {
		this.dateOfProposal = dateOfProposal;
	}
	public String getIsCreditRatingAvailable() {
		return isCreditRatingAvailable;
	}
	public void setIsCreditRatingAvailable(String isCreditRatingAvailable) {
		this.isCreditRatingAvailable = isCreditRatingAvailable;
	}
	public Double getSharePriceFace() {
		return sharePriceFace;
	}
	public void setSharePriceFace(Double sharePriceFace) {
		this.sharePriceFace = sharePriceFace;
	}
	public Double getSharePriceMarket() {
		return sharePriceMarket;
	}
	public void setSharePriceMarket(Double sharePriceMarket) {
		this.sharePriceMarket = sharePriceMarket;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getGstIn() {
		return gstIn;
	}
	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}
	public String getHaveCollateralSecurity() {
		return haveCollateralSecurity;
	}
	public void setHaveCollateralSecurity(String haveCollateralSecurity) {
		this.haveCollateralSecurity = haveCollateralSecurity;
	}
	public String getCollateralSecurityAmount() {
		return collateralSecurityAmount;
	}
	public void setCollateralSecurityAmount(String collateralSecurityAmount) {
		this.collateralSecurityAmount = collateralSecurityAmount;
	}
	public List<Object> getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(List<Object> profilePic) {
		this.profilePic = profilePic;
	}
	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}
	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}
	public String getBusinessAssetAmount() {
		return businessAssetAmount;
	}
	public void setBusinessAssetAmount(String businessAssetAmount) {
		this.businessAssetAmount = businessAssetAmount;
	}
	public String getWcAmount() {
		return wcAmount;
	}
	public void setWcAmount(String wcAmount) {
		this.wcAmount = wcAmount;
	}
	public String getOtherAmt() {
		return otherAmt;
	}
	public void setOtherAmt(String otherAmt) {
		this.otherAmt = otherAmt;
	}
	public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
		return directorBackgroundDetailResponses;
	}
	public void setDirectorBackgroundDetailResponses(
			List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
		this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
	}
	public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
		return financialArrangementsDetailResponseList;
	}
	public void setFinancialArrangementsDetailResponseList(
			List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
		this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
	}
	public Object getFinancialInputRequest() {
		return FinancialInputRequest;
	}
	public void setFinancialInputRequest(Object financialInputRequest) {
		FinancialInputRequest = financialInputRequest;
	}
	public String getUdhyogAadharNo() {
		return udhyogAadharNo;
	}
	public void setUdhyogAadharNo(String udhyogAadharNo) {
		this.udhyogAadharNo = udhyogAadharNo;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public List<AchievementDetailRequest> getAchievementDetailList() {
		return AchievementDetailList;
	}
	public void setAchievementDetailList(List<AchievementDetailRequest> achievementDetailList) {
		AchievementDetailList = achievementDetailList;
	}
	public List<CreditRatingOrganizationDetailResponse> getCreditRatingOrganizationDetailResponse() {
		return CreditRatingOrganizationDetailResponse;
	}
	public void setCreditRatingOrganizationDetailResponse(
			List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse) {
		CreditRatingOrganizationDetailResponse = creditRatingOrganizationDetailResponse;
	}
	public List<ProposedProductDetailRequest> getProposedProductDetailRequestList() {
		return proposedProductDetailRequestList;
	}
	public void setProposedProductDetailRequestList(List<ProposedProductDetailRequest> proposedProductDetailRequestList) {
		this.proposedProductDetailRequestList = proposedProductDetailRequestList;
	}
	public List<PromotorBackgroundDetailResponse> getPromotorBackgroundDetailResponseList() {
		return promotorBackgroundDetailResponseList;
	}
	public void setPromotorBackgroundDetailResponseList(
			List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList) {
		this.promotorBackgroundDetailResponseList = promotorBackgroundDetailResponseList;
	}
	public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
		return existingProductDetailRequestList;
	}
	public void setExistingProductDetailRequestList(List<ExistingProductDetailRequest> existingProductDetailRequestList) {
		this.existingProductDetailRequestList = existingProductDetailRequestList;
	}
	public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
		return associatedConcernDetailRequests;
	}
	public void setAssociatedConcernDetailRequests(List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
		this.associatedConcernDetailRequests = associatedConcernDetailRequests;
	}
	public String getContigentLiablitiesFy() {
		return contigentLiablitiesFy;
	}
	public void setContigentLiablitiesFy(String contigentLiablitiesFy) {
		this.contigentLiablitiesFy = contigentLiablitiesFy;
	}
	public String getContigentLiablitiesSy() {
		return contigentLiablitiesSy;
	}
	public void setContigentLiablitiesSy(String contigentLiablitiesSy) {
		this.contigentLiablitiesSy = contigentLiablitiesSy;
	}
	public String getContigentLiablitiesTy() {
		return contigentLiablitiesTy;
	}
	public void setContigentLiablitiesTy(String contigentLiablitiesTy) {
		this.contigentLiablitiesTy = contigentLiablitiesTy;
	}
	public String getTechnologyRiskId() {
		return technologyRiskId;
	}
	public void setTechnologyRiskId(String technologyRiskId) {
		this.technologyRiskId = technologyRiskId;
	}
	public String getCustomerQuality() {
		return customerQuality;
	}
	public void setCustomerQuality(String customerQuality) {
		this.customerQuality = customerQuality;
	}
	public String getSupplierQuality() {
		return supplierQuality;
	}
	public void setSupplierQuality(String supplierQuality) {
		this.supplierQuality = supplierQuality;
	}
	public String getSustainabilityProduct() {
		return sustainabilityProduct;
	}
	public void setSustainabilityProduct(String sustainabilityProduct) {
		this.sustainabilityProduct = sustainabilityProduct;
	}
	public String getOrderBookPosition() {
		return orderBookPosition;
	}
	public void setOrderBookPosition(String orderBookPosition) {
		this.orderBookPosition = orderBookPosition;
	}
	public String getEmployeeRelations() {
		return employeeRelations;
	}
	public void setEmployeeRelations(String employeeRelations) {
		this.employeeRelations = employeeRelations;
	}
	public String getProductSeasonality() {
		return productSeasonality;
	}
	public void setProductSeasonality(String productSeasonality) {
		this.productSeasonality = productSeasonality;
	}
	public String getImpactOnOperatingMargins() {
		return impactOnOperatingMargins;
	}
	public void setImpactOnOperatingMargins(String impactOnOperatingMargins) {
		this.impactOnOperatingMargins = impactOnOperatingMargins;
	}
	public String getEnvironmentalImpact() {
		return environmentalImpact;
	}
	public void setEnvironmentalImpact(String environmentalImpact) {
		this.environmentalImpact = environmentalImpact;
	}
	public String getAccountingQuality() {
		return accountingQuality;
	}
	public void setAccountingQuality(String accountingQuality) {
		this.accountingQuality = accountingQuality;
	}
	public String getFinancialRestructuringHistory() {
		return financialRestructuringHistory;
	}
	public void setFinancialRestructuringHistory(String financialRestructuringHistory) {
		this.financialRestructuringHistory = financialRestructuringHistory;
	}
	public String getIntegrity() {
		return integrity;
	}
	public void setIntegrity(String integrity) {
		this.integrity = integrity;
	}
	public String getBusinessCommitment() {
		return businessCommitment;
	}
	public void setBusinessCommitment(String businessCommitment) {
		this.businessCommitment = businessCommitment;
	}
	public String getManagementCompetence() {
		return managementCompetence;
	}
	public void setManagementCompetence(String managementCompetence) {
		this.managementCompetence = managementCompetence;
	}
	public String getBusinessExperience() {
		return businessExperience;
	}
	public void setBusinessExperience(String businessExperience) {
		this.businessExperience = businessExperience;
	}
	public String getSuccessionPlanning() {
		return successionPlanning;
	}
	public void setSuccessionPlanning(String successionPlanning) {
		this.successionPlanning = successionPlanning;
	}
	public String getFinancialStrength() {
		return financialStrength;
	}
	public void setFinancialStrength(String financialStrength) {
		this.financialStrength = financialStrength;
	}
	public String getFinancialSupport() {
		return financialSupport;
	}
	public void setFinancialSupport(String financialSupport) {
		this.financialSupport = financialSupport;
	}
	public String getAbilityToRaiseFunds() {
		return abilityToRaiseFunds;
	}
	public void setAbilityToRaiseFunds(String abilityToRaiseFunds) {
		this.abilityToRaiseFunds = abilityToRaiseFunds;
	}
	public String getIntraCompany() {
		return intraCompany;
	}
	public void setIntraCompany(String intraCompany) {
		this.intraCompany = intraCompany;
	}
	public String getInternalControl() {
		return internalControl;
	}
	public void setInternalControl(String internalControl) {
		this.internalControl = internalControl;
	}
	public String getCreditTrackRecord() {
		return creditTrackRecord;
	}
	public void setCreditTrackRecord(String creditTrackRecord) {
		this.creditTrackRecord = creditTrackRecord;
	}
	public String getStatusOfProjectClearances() {
		return statusOfProjectClearances;
	}
	public void setStatusOfProjectClearances(String statusOfProjectClearances) {
		this.statusOfProjectClearances = statusOfProjectClearances;
	}
	public String getStatusOfFinancialClosure() {
		return statusOfFinancialClosure;
	}
	public void setStatusOfFinancialClosure(String statusOfFinancialClosure) {
		this.statusOfFinancialClosure = statusOfFinancialClosure;
	}
	public String getInfrastructureAvailability() {
		return infrastructureAvailability;
	}
	public void setInfrastructureAvailability(String infrastructureAvailability) {
		this.infrastructureAvailability = infrastructureAvailability;
	}
	public String getConstructionContract() {
		return constructionContract;
	}
	public void setConstructionContract(String constructionContract) {
		this.constructionContract = constructionContract;
	}
	public String getNumberOfCheques() {
		return numberOfCheques;
	}
	public void setNumberOfCheques(String numberOfCheques) {
		this.numberOfCheques = numberOfCheques;
	}
	public String getNumberOfTimesDp() {
		return numberOfTimesDp;
	}
	public void setNumberOfTimesDp(String numberOfTimesDp) {
		this.numberOfTimesDp = numberOfTimesDp;
	}
	public String getCumulativeNoOfDaysDp() {
		return cumulativeNoOfDaysDp;
	}
	public void setCumulativeNoOfDaysDp(String cumulativeNoOfDaysDp) {
		this.cumulativeNoOfDaysDp = cumulativeNoOfDaysDp;
	}
	public String getComplianceWithSanctioned() {
		return complianceWithSanctioned;
	}
	public void setComplianceWithSanctioned(String complianceWithSanctioned) {
		this.complianceWithSanctioned = complianceWithSanctioned;
	}
	public String getProgressReports() {
		return progressReports;
	}
	public void setProgressReports(String progressReports) {
		this.progressReports = progressReports;
	}
	public String getDelayInReceipt() {
		return delayInReceipt;
	}
	public void setDelayInReceipt(String delayInReceipt) {
		this.delayInReceipt = delayInReceipt;
	}
	public String getDelayInSubmission() {
		return delayInSubmission;
	}
	public void setDelayInSubmission(String delayInSubmission) {
		this.delayInSubmission = delayInSubmission;
	}
	public String getNumberOfLc() {
		return numberOfLc;
	}
	public void setNumberOfLc(String numberOfLc) {
		this.numberOfLc = numberOfLc;
	}
	public String getUnhedgedForeignCurrency() {
		return unhedgedForeignCurrency;
	}
	public void setUnhedgedForeignCurrency(String unhedgedForeignCurrency) {
		this.unhedgedForeignCurrency = unhedgedForeignCurrency;
	}
	public String getProjectedDebtService() {
		return projectedDebtService;
	}
	public void setProjectedDebtService(String projectedDebtService) {
		this.projectedDebtService = projectedDebtService;
	}
	public String getInternalRateReturn() {
		return internalRateReturn;
	}
	public void setInternalRateReturn(String internalRateReturn) {
		this.internalRateReturn = internalRateReturn;
	}
	public String getSensititivityAnalysis() {
		return sensititivityAnalysis;
	}
	public void setSensititivityAnalysis(String sensititivityAnalysis) {
		this.sensititivityAnalysis = sensititivityAnalysis;
	}
	public String getVarianceInProjectedSales() {
		return varianceInProjectedSales;
	}
	public void setVarianceInProjectedSales(String varianceInProjectedSales) {
		this.varianceInProjectedSales = varianceInProjectedSales;
	}
    
    
    

}
