package com.opl.mudra.api.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.opl.mudra.api.loans.model.AchievementDetailRequest;
import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailResponse;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ExistingProductDetailRequest;
import com.opl.mudra.api.loans.model.FinanceMeansDetailResponse;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailResponse;
import com.opl.mudra.api.loans.model.GuarantorsCorporateDetailRequest;
import com.opl.mudra.api.loans.model.MonthlyTurnoverDetailRequest;
import com.opl.mudra.api.loans.model.OwnershipDetailResponse;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ProposedProductDetailRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;
import com.opl.mudra.api.loans.model.TotalCostOfProjectResponse;
import com.opl.mudra.api.loans.model.corporate.CollateralSecurityDetailRequest;

/**
 * @author nilay.darji
 *
 */
public class CorporateFinalViewResponse implements Serializable {

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
	private Double enhancementAmount;
	private String gstIn;
	private String haveCollateralSecurity;
	private String collateralSecurityAmount;
	private List<Object> profilePic;
	private Integer productId;
	private Object disbursementRequestDetails;

	private String purposeOfLoan;
	private String businessAssetAmount;
	private String wcAmount;
	private String otherAmt;
	private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
	private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
	private Object FinancialInputRequest;
	private String castCategory;

	// FINAL VIEW RESPONSE OBJECT
	private String udhyogAadharNo;
	private String creditRating;

	private List<AchievementDetailRequest> achievementDetailList;
	private List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse;
	private List<ProposedProductDetailRequest> proposedProductDetailRequestList;
	private List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList;
	private List<ExistingProductDetailRequest> existingProductDetailRequestList;
	private List<AssociatedConcernDetailRequest> associatedConcernDetailRequests;

	private String contigentLiablitiesFy;
	private String contigentLiablitiesSy;
	private String contigentLiablitiesTy;

	// FINAL MCQ

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

	private List<Object> auditedAnnualReport;
	private List<Object> itr;
	private List<Object> bankStatementFinalView;
	private List<Object> sanctionLetter;
	private List<Object> provisionalFinancials;
	private List<Object> netWorthStatements;
	private List<Object> financialsOfHolding;
	private List<Object> assessmentOrders;
	private List<Object> momAndAoa;
	private List<Object> gstCertificate;
	private List<Object> certificateOfIncorporation;
	private List<Object> copyOfPanCard;
	private List<Object> panOfAllDirectors;
	private List<Object> photosOfDirectors;
	private List<Object> residenceAddOfDirectors;
	private List<Object> cibilReport;
	private List<Object> bankStatement;
	private List<Object> cmaList;
	private List<Object> irtPdfReport;
	private List<Object> nbfcPANReport;
	private List<Object> nbfcAddressProofReport;
	private List<Object> nbfcTripartiteAgreement;
	private List<Object> nbfcSanctionLetterOfNBFC;
	private List<Object> nbfcLetterOfIntent;
	private List<Object> nbfcDemandPromissonryNote;
	private List<Object> ageProofDocument;
	private List<Object> idProofDocument;
	private List<Object> addressProofDocument;
	private List<Object> applicationForm;
	private List<Object> detailedAssessmentNote;
	private List<Object> nbfcOther;
	private Boolean isNBFCApplication;
	private List<Object> nbfcProjectedFinancials;
	private List<OwnershipDetailResponse> ownershipDetailResponseList;
	private List<TotalCostOfProjectResponse> totalCostOfProjectResponseList;
	private List<FinanceMeansDetailResponse> financeMeansDetailResponseList;
	private List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList;
	private List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList;
	private List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequestList;
	private Object monthlyDetailList;
	private Object top5FundReceivedList;
	private Object top5FundTransferedList;
	private Object dataObject;
	private Object dataList;
	private Object eligibilityDataObject;
	private Object cgtmseData;
	private Long npOrgId;
	private Object bankData;
	private Object managementRiskScore;
	private Object financialRiskScore;
	private Object buisnessRiskScore;
	private Object managementRiskScoreWeight;
	private Object financialRiskScoreWeight;
	private Object buisnessRiskScoreWeight;
	private Object scoreInterpretation;
	private Long assesmentId;
	private Object itrXmlIsUploaded;
	private Object managementRiskMaxTotalScore;
    private Object financialRiskMaxTotalScore;
    private Object businessRiskMaxTotalScore;
    private Object managementRiskWeightOfScoring;
    private Object financialRiskWeightOfScoring;
    private Object businessRiskWeightOfScoring;
    private Object promotersContribution;
    private Object promotersContributionPer;
    private Object mcaData;
    private Boolean mcaNotApplicable;
    private Object nameAsPerItr;
    private Object mcaCheckStatus;
    private Object gstData;
    private Object weightConsider;
    private Object managementRiskMaxTotalWeight;
    private Object financialRiskMaxTotalWeight;
    private Object businessRiskMaxTotalWeight;
    private Object isProportionateScoreConsider;
    private Object proportionateScore;
    private Object proportionateScoreFS;
    private Object fraudDetectionData;
    
    private Object comercialOpDate;
    private Object factoryPremise;
    private Object knoHow;
    private Object competition;
    
    private Object costOfMachinery;
    private Object incrementalTurnover;
    private Object incrementalMargin;
    
    private Object adminAdd;
    private Object adminAddPremise;
    private Object adminAddStreetName;
    private Object adminAddLandmark;
    private Object adminAddCountry;
    private Object adminAddState;
    private Object adminAddCity;
    private Object adminAddPincode;
    private Object adminPinData;
    private String adminAddDist;
    private String adminAddTaluko;
    
    private Object regAdd;
    private Object regAddPremise;
    private Object regAddStreetName;
    private Object regAddLandmark;
    private Object regAddCountry;
    private Object regAddState;
    private Object regAddCity;
    private Object regAddPincode;
    private Object regPinData;
    private String regAddDist;
    private String regAddTaluko;

    private String applicationType;
    private String fpProductName;
    
    private Object mcaFinancialAndDetailsResponse;
    private Boolean isCampaignUser;
    private Boolean isMcqSkipped;
    private String companyId;
    private String scoringModelName;
    private String eligibilityFinancialYear;
    private Double loanObligation;
    private Object cibilConsumerReport;
    private List<Object> mcaCorpZipFile;
    private String cibilCmrScore;
    private String comercialPanNo;
    private String productServiceDesc;
    private String msmeRankTitle;
    private Object verifyApiData;
    private Object bankComparisionData;
    private List<CollateralSecurityDetailRequest> collateralSecurityDetails;
    Map<String, Object> gstRelatedParty;
    private String noteOfBorrower;
    

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
		return achievementDetailList;
	}

	public void setAchievementDetailList(List<AchievementDetailRequest> achievementDetailList) {
		this.achievementDetailList = achievementDetailList;
	}

	public List<CreditRatingOrganizationDetailResponse> getCreditRatingOrganizationDetailResponse() {
		return creditRatingOrganizationDetailResponse;
	}

	public void setCreditRatingOrganizationDetailResponse(
			List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse) {
		this.creditRatingOrganizationDetailResponse = creditRatingOrganizationDetailResponse;
	}

	public List<ProposedProductDetailRequest> getProposedProductDetailRequestList() {
		return proposedProductDetailRequestList;
	}

	public void setProposedProductDetailRequestList(
			List<ProposedProductDetailRequest> proposedProductDetailRequestList) {
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

	public void setExistingProductDetailRequestList(
			List<ExistingProductDetailRequest> existingProductDetailRequestList) {
		this.existingProductDetailRequestList = existingProductDetailRequestList;
	}

	public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
		return associatedConcernDetailRequests;
	}

	public void setAssociatedConcernDetailRequests(
			List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
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

	/**
	 * @return the auditedAnnualReport
	 */
	public List<Object> getAuditedAnnualReport() {
		return auditedAnnualReport;
	}

	/**
	 * @param auditedAnnualReport
	 *            the auditedAnnualReport to set
	 */
	public void setAuditedAnnualReport(List<Object> auditedAnnualReport) {
		this.auditedAnnualReport = auditedAnnualReport;
	}

	/**
	 * @return the itr
	 */
	public List<Object> getItr() {
		return itr;
	}

	/**
	 * @param itr
	 *            the itr to set
	 */
	public void setItr(List<Object> itr) {
		this.itr = itr;
	}

	/**
	 * @return the bankStatementFinalView
	 */
	public List<Object> getBankStatementFinalView() {
		return bankStatementFinalView;
	}

	/**
	 * @param bankStatementFinalView
	 *            the bankStatementFinalView to set
	 */
	public void setBankStatementFinalView(List<Object> bankStatementFinalView) {
		this.bankStatementFinalView = bankStatementFinalView;
	}

	/**
	 * @return the sanctionLetter
	 */
	public List<Object> getSanctionLetter() {
		return sanctionLetter;
	}

	/**
	 * @param sanctionLetter
	 *            the sanctionLetter to set
	 */
	public void setSanctionLetter(List<Object> sanctionLetter) {
		this.sanctionLetter = sanctionLetter;
	}

	/**
	 * @return the provisionalFinancials
	 */
	public List<Object> getProvisionalFinancials() {
		return provisionalFinancials;
	}

	/**
	 * @param provisionalFinancials
	 *            the provisionalFinancials to set
	 */
	public void setProvisionalFinancials(List<Object> provisionalFinancials) {
		this.provisionalFinancials = provisionalFinancials;
	}

	/**
	 * @return the netWorthStatements
	 */
	public List<Object> getNetWorthStatements() {
		return netWorthStatements;
	}

	/**
	 * @param netWorthStatements
	 *            the netWorthStatements to set
	 */
	public void setNetWorthStatements(List<Object> netWorthStatements) {
		this.netWorthStatements = netWorthStatements;
	}

	/**
	 * @return the financialsOfHolding
	 */
	public List<Object> getFinancialsOfHolding() {
		return financialsOfHolding;
	}

	/**
	 * @param financialsOfHolding
	 *            the financialsOfHolding to set
	 */
	public void setFinancialsOfHolding(List<Object> financialsOfHolding) {
		this.financialsOfHolding = financialsOfHolding;
	}

	/**
	 * @return the assessmentOrders
	 */
	public List<Object> getAssessmentOrders() {
		return assessmentOrders;
	}

	/**
	 * @param assessmentOrders
	 *            the assessmentOrders to set
	 */
	public void setAssessmentOrders(List<Object> assessmentOrders) {
		this.assessmentOrders = assessmentOrders;
	}

	/**
	 * @return the momAndAoa
	 */
	public List<Object> getMomAndAoa() {
		return momAndAoa;
	}

	/**
	 * @param momAndAoa
	 *            the momAndAoa to set
	 */
	public void setMomAndAoa(List<Object> momAndAoa) {
		this.momAndAoa = momAndAoa;
	}

	/**
	 * @return the gstCertificate
	 */
	public List<Object> getGstCertificate() {
		return gstCertificate;
	}

	/**
	 * @param gstCertificate
	 *            the gstCertificate to set
	 */
	public void setGstCertificate(List<Object> gstCertificate) {
		this.gstCertificate = gstCertificate;
	}

	/**
	 * @return the certificateOfIncorporation
	 */
	public List<Object> getCertificateOfIncorporation() {
		return certificateOfIncorporation;
	}

	/**
	 * @param certificateOfIncorporation
	 *            the certificateOfIncorporation to set
	 */
	public void setCertificateOfIncorporation(List<Object> certificateOfIncorporation) {
		this.certificateOfIncorporation = certificateOfIncorporation;
	}

	/**
	 * @return the copyOfPanCard
	 */
	public List<Object> getCopyOfPanCard() {
		return copyOfPanCard;
	}

	/**
	 * @param copyOfPanCard
	 *            the copyOfPanCard to set
	 */
	public void setCopyOfPanCard(List<Object> copyOfPanCard) {
		this.copyOfPanCard = copyOfPanCard;
	}

	/**
	 * @return the panOfAllDirectors
	 */
	public List<Object> getPanOfAllDirectors() {
		return panOfAllDirectors;
	}

	/**
	 * @param panOfAllDirectors
	 *            the panOfAllDirectors to set
	 */
	public void setPanOfAllDirectors(List<Object> panOfAllDirectors) {
		this.panOfAllDirectors = panOfAllDirectors;
	}

	/**
	 * @return the photosOfDirectors
	 */
	public List<Object> getPhotosOfDirectors() {
		return photosOfDirectors;
	}

	/**
	 * @param photosOfDirectors
	 *            the photosOfDirectors to set
	 */
	public void setPhotosOfDirectors(List<Object> photosOfDirectors) {
		this.photosOfDirectors = photosOfDirectors;
	}

	/**
	 * @return the residenceAddOfDirectors
	 */
	public List<Object> getResidenceAddOfDirectors() {
		return residenceAddOfDirectors;
	}

	/**
	 * @param residenceAddOfDirectors
	 *            the residenceAddOfDirectors to set
	 */
	public void setResidenceAddOfDirectors(List<Object> residenceAddOfDirectors) {
		this.residenceAddOfDirectors = residenceAddOfDirectors;
	}

	public List<Object> getCibilReport() {
		return cibilReport;
	}

	public void setCibilReport(List<Object> cibilReport) {
		this.cibilReport = cibilReport;
	}

	public List<Object> getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(List<Object> bankStatement) {
		this.bankStatement = bankStatement;
	}

	public List<Object> getCmaList() {
		return cmaList;
	}

	public void setCmaList(List<Object> cmaList) {
		this.cmaList = cmaList;
	}

	public List<Object> getIrtPdfReport() {
		return irtPdfReport;
	}

	public void setIrtPdfReport(List<Object> irtPdfReport) {
		this.irtPdfReport = irtPdfReport;
	}

	/**
	 * @return the ownershipDetailResponseList
	 */
	public List<OwnershipDetailResponse> getOwnershipDetailResponseList() {
		return ownershipDetailResponseList;
	}

	/**
	 * @param ownershipDetailResponseList
	 *            the ownershipDetailResponseList to set
	 */
	public void setOwnershipDetailResponseList(List<OwnershipDetailResponse> ownershipDetailResponseList) {
		this.ownershipDetailResponseList = ownershipDetailResponseList;
	}

	/**
	 * @return the totalCostOfProjectResponseList
	 */
	public List<TotalCostOfProjectResponse> getTotalCostOfProjectResponseList() {
		return totalCostOfProjectResponseList;
	}

	/**
	 * @param totalCostOfProjectResponseList
	 *            the totalCostOfProjectResponseList to set
	 */
	public void setTotalCostOfProjectResponseList(List<TotalCostOfProjectResponse> totalCostOfProjectResponseList) {
		this.totalCostOfProjectResponseList = totalCostOfProjectResponseList;
	}

	/**
	 * @return the financeMeansDetailResponseList
	 */
	public List<FinanceMeansDetailResponse> getFinanceMeansDetailResponseList() {
		return financeMeansDetailResponseList;
	}

	/**
	 * @param financeMeansDetailResponseList
	 *            the financeMeansDetailResponseList to set
	 */
	public void setFinanceMeansDetailResponseList(List<FinanceMeansDetailResponse> financeMeansDetailResponseList) {
		this.financeMeansDetailResponseList = financeMeansDetailResponseList;
	}

	/**
	 * @return the securityCorporateDetailRequestList
	 */
	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList() {
		return securityCorporateDetailRequestList;
	}

	/**
	 * @param securityCorporateDetailRequestList
	 *            the securityCorporateDetailRequestList to set
	 */
	public void setSecurityCorporateDetailRequestList(
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList) {
		this.securityCorporateDetailRequestList = securityCorporateDetailRequestList;
	}

	/**
	 * @return the guarantorsCorporateDetailRequestList
	 */
	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailRequestList() {
		return guarantorsCorporateDetailRequestList;
	}

	/**
	 * @param guarantorsCorporateDetailRequestList
	 *            the guarantorsCorporateDetailRequestList to set
	 */
	public void setGuarantorsCorporateDetailRequestList(
			List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList) {
		this.guarantorsCorporateDetailRequestList = guarantorsCorporateDetailRequestList;
	}

	/**
	 * @return the monthlyTurnoverDetailRequestList
	 */
	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailRequestList() {
		return monthlyTurnoverDetailRequestList;
	}

	/**
	 * @param monthlyTurnoverDetailRequestList
	 *            the monthlyTurnoverDetailRequestList to set
	 */
	public void setMonthlyTurnoverDetailRequestList(
			List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequestList) {
		this.monthlyTurnoverDetailRequestList = monthlyTurnoverDetailRequestList;
	}

	/**
	 * @return the npOrgId
	 */
	public Long getNpOrgId() {
		return npOrgId;
	}

	/**
	 * @param npOrgId
	 *            the npOrgId to set
	 */
	public void setNpOrgId(Long npOrgId) {
		this.npOrgId = npOrgId;
	}

	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * @return the monthlyDetailList
	 */
	public Object getMonthlyDetailList() {
		return monthlyDetailList;
	}

	/**
	 * @param monthlyDetailList
	 *            the monthlyDetailList to set
	 */
	public void setMonthlyDetailList(Object monthlyDetailList) {
		this.monthlyDetailList = monthlyDetailList;
	}

	/**
	 * @return the top5FundReceivedList
	 */
	public Object getTop5FundReceivedList() {
		return top5FundReceivedList;
	}

	/**
	 * @param top5FundReceivedList
	 *            the top5FundReceivedList to set
	 */
	public void setTop5FundReceivedList(Object top5FundReceivedList) {
		this.top5FundReceivedList = top5FundReceivedList;
	}

	/**
	 * @return the top5FundTransferedList
	 */
	public Object getTop5FundTransferedList() {
		return top5FundTransferedList;
	}

	/**
	 * @param top5FundTransferedList
	 *            the top5FundTransferedList to set
	 */
	public void setTop5FundTransferedList(Object top5FundTransferedList) {
		this.top5FundTransferedList = top5FundTransferedList;
	}

	/**
	 * @return the dataObject
	 */
	public Object getDataObject() {
		return dataObject;
	}

	/**
	 * @param dataObject
	 *            the dataObject to set
	 */
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	/**
	 * @return the dataList
	 */
	public Object getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the eligibilityDataObject
	 */
	public Object getEligibilityDataObject() {
		return eligibilityDataObject;
	}

	/**
	 * @param eligibilityDataObject
	 *            the eligibilityDataObject to set
	 */
	public void setEligibilityDataObject(Object eligibilityDataObject) {
		this.eligibilityDataObject = eligibilityDataObject;
	}

	/**
	 * @return the cgtmseData
	 */
	public Object getCgtmseData() {
		return cgtmseData;
	}

	/**
	 * @param cgtmseData
	 *            the cgtmseData to set
	 */
	public void setCgtmseData(Object cgtmseData) {
		this.cgtmseData = cgtmseData;
	}

	/**
	 * @return the bankData
	 */
	public Object getBankData() {
		return bankData;
	}

	/**
	 * @param bankData
	 *            the bankData to set
	 */
	public void setBankData(Object bankData) {
		this.bankData = bankData;
	}

	/**
	 * @return the managementRiskScore
	 */
	public Object getManagementRiskScore() {
		return managementRiskScore;
	}

	/**
	 * @param managementRiskScore
	 *            the managementRiskScore to set
	 */
	public void setManagementRiskScore(Object managementRiskScore) {
		this.managementRiskScore = managementRiskScore;
	}

	/**
	 * @return the financialRiskScore
	 */
	public Object getFinancialRiskScore() {
		return financialRiskScore;
	}

	/**
	 * @param financialRiskScore
	 *            the financialRiskScore to set
	 */
	public void setFinancialRiskScore(Object financialRiskScore) {
		this.financialRiskScore = financialRiskScore;
	}

	/**
	 * @return the buisnessRiskScore
	 */
	public Object getBuisnessRiskScore() {
		return buisnessRiskScore;
	}

	/**
	 * @param buisnessRiskScore
	 *            the buisnessRiskScore to set
	 */
	public void setBuisnessRiskScore(Object buisnessRiskScore) {
		this.buisnessRiskScore = buisnessRiskScore;
	}

	/**
	 * @return the managementRiskScoreWeight
	 */
	public Object getManagementRiskScoreWeight() {
		return managementRiskScoreWeight;
	}

	/**
	 * @param managementRiskScoreWeight
	 *            the managementRiskScoreWeight to set
	 */
	public void setManagementRiskScoreWeight(Object managementRiskScoreWeight) {
		this.managementRiskScoreWeight = managementRiskScoreWeight;
	}

	/**
	 * @return the financialRiskScoreWeight
	 */
	public Object getFinancialRiskScoreWeight() {
		return financialRiskScoreWeight;
	}

	/**
	 * @param financialRiskScoreWeight
	 *            the financialRiskScoreWeight to set
	 */
	public void setFinancialRiskScoreWeight(Object financialRiskScoreWeight) {
		this.financialRiskScoreWeight = financialRiskScoreWeight;
	}

	/**
	 * @return the buisnessRiskScoreWeight
	 */
	public Object getBuisnessRiskScoreWeight() {
		return buisnessRiskScoreWeight;
	}

	/**
	 * @param buisnessRiskScoreWeight
	 *            the buisnessRiskScoreWeight to set
	 */
	public void setBuisnessRiskScoreWeight(Object buisnessRiskScoreWeight) {
		this.buisnessRiskScoreWeight = buisnessRiskScoreWeight;
	}

	/**
	 * @return the scoreInterpretation
	 */
	public Object getScoreInterpretation() {
		return scoreInterpretation;
	}

	/**
	 * @param scoreInterpretation
	 *            the scoreInterpretation to set
	 */
	public void setScoreInterpretation(Object scoreInterpretation) {
		this.scoreInterpretation = scoreInterpretation;
	}

	/**
	 * @return the assesmentId
	 */
	public Long getAssesmentId() {
		return assesmentId;
	}

	/**
	 * @param assesmentId
	 *            the assesmentId to set
	 */
	public void setAssesmentId(Long assesmentId) {
		this.assesmentId = assesmentId;
	}

	/**
	 * @return the itrXmlIsUploaded
	 */
	public Object getItrXmlIsUploaded() {
		return itrXmlIsUploaded;
	}

	/**
	 * @param itrXmlIsUploaded the itrXmlIsUploaded to set
	 */
	public void setItrXmlIsUploaded(Object itrXmlIsUploaded) {
		this.itrXmlIsUploaded = itrXmlIsUploaded;
	}

	/**
	 * @return the managementRiskMaxTotalScore
	 */
	public Object getManagementRiskMaxTotalScore() {
		return managementRiskMaxTotalScore;
	}

	/**
	 * @param managementRiskMaxTotalScore the managementRiskMaxTotalScore to set
	 */
	public void setManagementRiskMaxTotalScore(Object managementRiskMaxTotalScore) {
		this.managementRiskMaxTotalScore = managementRiskMaxTotalScore;
	}

	/**
	 * @return the financialRiskMaxTotalScore
	 */
	public Object getFinancialRiskMaxTotalScore() {
		return financialRiskMaxTotalScore;
	}

	/**
	 * @param financialRiskMaxTotalScore the financialRiskMaxTotalScore to set
	 */
	public void setFinancialRiskMaxTotalScore(Object financialRiskMaxTotalScore) {
		this.financialRiskMaxTotalScore = financialRiskMaxTotalScore;
	}

	/**
	 * @return the businessRiskMaxTotalScore
	 */
	public Object getBusinessRiskMaxTotalScore() {
		return businessRiskMaxTotalScore;
	}

	/**
	 * @param businessRiskMaxTotalScore the businessRiskMaxTotalScore to set
	 */
	public void setBusinessRiskMaxTotalScore(Object businessRiskMaxTotalScore) {
		this.businessRiskMaxTotalScore = businessRiskMaxTotalScore;
	}

	/**
	 * @return the managementRiskWeightOfScoring
	 */
	public Object getManagementRiskWeightOfScoring() {
		return managementRiskWeightOfScoring;
	}

	/**
	 * @param managementRiskWeightOfScoring the managementRiskWeightOfScoring to set
	 */
	public void setManagementRiskWeightOfScoring(Object managementRiskWeightOfScoring) {
		this.managementRiskWeightOfScoring = managementRiskWeightOfScoring;
	}

	/**
	 * @return the financialRiskWeightOfScoring
	 */
	public Object getFinancialRiskWeightOfScoring() {
		return financialRiskWeightOfScoring;
	}

	/**
	 * @param financialRiskWeightOfScoring the financialRiskWeightOfScoring to set
	 */
	public void setFinancialRiskWeightOfScoring(Object financialRiskWeightOfScoring) {
		this.financialRiskWeightOfScoring = financialRiskWeightOfScoring;
	}

	/**
	 * @return the businessRiskWeightOfScoring
	 */
	public Object getBusinessRiskWeightOfScoring() {
		return businessRiskWeightOfScoring;
	}

	/**
	 * @param businessRiskWeightOfScoring the businessRiskWeightOfScoring to set
	 */
	public void setBusinessRiskWeightOfScoring(Object businessRiskWeightOfScoring) {
		this.businessRiskWeightOfScoring = businessRiskWeightOfScoring;
	}

	/**
	 * @return the promotersContribution
	 */
	public Object getPromotersContribution() {
		return promotersContribution;
	}

	/**
	 * @param promotersContribution the promotersContribution to set
	 */
	public void setPromotersContribution(Object promotersContribution) {
		this.promotersContribution = promotersContribution;
	}

	/**
	 * @return the promotersContributionPer
	 */
	public Object getPromotersContributionPer() {
		return promotersContributionPer;
	}

	/**
	 * @param promotersContributionPer the promotersContributionPer to set
	 */
	public void setPromotersContributionPer(Object promotersContributionPer) {
		this.promotersContributionPer = promotersContributionPer;
	}

	/**
	 * @return the mcaData
	 */
	public Object getMcaData() {
		return mcaData;
	}

	/**
	 * @param mcaData the mcaData to set
	 */
	public void setMcaData(Object mcaData) {
		this.mcaData = mcaData;
	}

	/**
	 * @return the nameAsPerItr
	 */
	public Object getNameAsPerItr() {
		return nameAsPerItr;
	}

	/**
	 * @param nameAsPerItr the nameAsPerItr to set
	 */
	public void setNameAsPerItr(Object nameAsPerItr) {
		this.nameAsPerItr = nameAsPerItr;
	}

	/**
	 * @return the mcaCheckStatus
	 */
	public Object getMcaCheckStatus() {
		return mcaCheckStatus;
	}

	/**
	 * @param mcaCheckStatus the mcaCheckStatus to set
	 */
	public void setMcaCheckStatus(Object mcaCheckStatus) {
		this.mcaCheckStatus = mcaCheckStatus;
	}

	/**
	 * @return the gstData
	 */
	public Object getGstData() {
		return gstData;
	}

	/**
	 * @param gstData the gstData to set
	 */
	public void setGstData(Object gstData) {
		this.gstData = gstData;
	}

	/**
	 * @return the weightConsider
	 */
	public Object getWeightConsider() {
		return weightConsider;
	}

	/**
	 * @param weightConsider the weightConsider to set
	 */
	public void setWeightConsider(Object weightConsider) {
		this.weightConsider = weightConsider;
	}

	/**
	 * @return the managementRiskMaxTotalWeight
	 */
	public Object getManagementRiskMaxTotalWeight() {
		return managementRiskMaxTotalWeight;
	}

	/**
	 * @param managementRiskMaxTotalWeight the managementRiskMaxTotalWeight to set
	 */
	public void setManagementRiskMaxTotalWeight(Object managementRiskMaxTotalWeight) {
		this.managementRiskMaxTotalWeight = managementRiskMaxTotalWeight;
	}

	/**
	 * @return the financialRiskMaxTotalWeight
	 */
	public Object getFinancialRiskMaxTotalWeight() {
		return financialRiskMaxTotalWeight;
	}

	/**
	 * @param financialRiskMaxTotalWeight the financialRiskMaxTotalWeight to set
	 */
	public void setFinancialRiskMaxTotalWeight(Object financialRiskMaxTotalWeight) {
		this.financialRiskMaxTotalWeight = financialRiskMaxTotalWeight;
	}

	/**
	 * @return the businessRiskMaxTotalWeight
	 */
	public Object getBusinessRiskMaxTotalWeight() {
		return businessRiskMaxTotalWeight;
	}

	/**
	 * @param businessRiskMaxTotalWeight the businessRiskMaxTotalWeight to set
	 */
	public void setBusinessRiskMaxTotalWeight(Object businessRiskMaxTotalWeight) {
		this.businessRiskMaxTotalWeight = businessRiskMaxTotalWeight;
	}

	/**
	 * @return the isProportionateScoreConsider
	 */
	public Object getIsProportionateScoreConsider() {
		return isProportionateScoreConsider;
	}

	/**
	 * @param isProportionateScoreConsider the isProportionateScoreConsider to set
	 */
	public void setIsProportionateScoreConsider(Object isProportionateScoreConsider) {
		this.isProportionateScoreConsider = isProportionateScoreConsider;
	}

	/**
	 * @return the proportionateScore
	 */
	public Object getProportionateScore() {
		return proportionateScore;
	}

	/**
	 * @param proportionateScore the proportionateScore to set
	 */
	public void setProportionateScore(Object proportionateScore) {
		this.proportionateScore = proportionateScore;
	}

	/**
	 * @return the proportionateScoreFS
	 */
	public Object getProportionateScoreFS() {
		return proportionateScoreFS;
	}

	/**
	 * @param proportionateScoreFS the proportionateScoreFS to set
	 */
	public void setProportionateScoreFS(Object proportionateScoreFS) {
		this.proportionateScoreFS = proportionateScoreFS;
	}

	/**
	 * @return the fraudDetectionData
	 */
	public Object getFraudDetectionData() {
		return fraudDetectionData;
	}

	/**
	 * @param fraudDetectionData the fraudDetectionData to set
	 */
	public void setFraudDetectionData(Object fraudDetectionData) {
		this.fraudDetectionData = fraudDetectionData;
	}

	public Object getComercialOpDate() {
		return comercialOpDate;
	}

	public void setComercialOpDate(Object comercialOpDate) {
		this.comercialOpDate = comercialOpDate;
	}

	public Object getFactoryPremise() {
		return factoryPremise;
	}

	public void setFactoryPremise(Object factoryPremise) {
		this.factoryPremise = factoryPremise;
	}

	public Object getKnoHow() {
		return knoHow;
	}

	public void setKnoHow(Object knoHow) {
		this.knoHow = knoHow;
	}

	public Object getCompetition() {
		return competition;
	}

	public void setCompetition(Object competition) {
		this.competition = competition;
	}

	public Object getCostOfMachinery() {
		return costOfMachinery;
	}

	public void setCostOfMachinery(Object costOfMachinery) {
		this.costOfMachinery = costOfMachinery;
	}

	public Object getIncrementalTurnover() {
		return incrementalTurnover;
	}

	public void setIncrementalTurnover(Object incrementalTurnover) {
		this.incrementalTurnover = incrementalTurnover;
	}

	public Object getIncrementalMargin() {
		return incrementalMargin;
	}

	public void setIncrementalMargin(Object incrementalMargin) {
		this.incrementalMargin = incrementalMargin;
	}

	public Object getAdminAdd() {
		return adminAdd;
	}

	public void setAdminAdd(Object adminAdd) {
		this.adminAdd = adminAdd;
	}

	public Object getAdminAddPremise() {
		return adminAddPremise;
	}

	public void setAdminAddPremise(Object adminAddPremise) {
		this.adminAddPremise = adminAddPremise;
	}

	public Object getAdminAddStreetName() {
		return adminAddStreetName;
	}

	public void setAdminAddStreetName(Object adminAddStreetName) {
		this.adminAddStreetName = adminAddStreetName;
	}

	public Object getAdminAddLandmark() {
		return adminAddLandmark;
	}

	public void setAdminAddLandmark(Object adminAddLandmark) {
		this.adminAddLandmark = adminAddLandmark;
	}

	public Object getAdminAddCountry() {
		return adminAddCountry;
	}

	public void setAdminAddCountry(Object adminAddCountry) {
		this.adminAddCountry = adminAddCountry;
	}

	public Object getAdminAddState() {
		return adminAddState;
	}

	public void setAdminAddState(Object adminAddState) {
		this.adminAddState = adminAddState;
	}

	public Object getAdminAddCity() {
		return adminAddCity;
	}

	public void setAdminAddCity(Object adminAddCity) {
		this.adminAddCity = adminAddCity;
	}

	public Object getAdminAddPincode() {
		return adminAddPincode;
	}

	public void setAdminAddPincode(Object adminAddPincode) {
		this.adminAddPincode = adminAddPincode;
	}

	public Object getAdminPinData() {
		return adminPinData;
	}

	public void setAdminPinData(Object adminPinData) {
		this.adminPinData = adminPinData;
	}

	public String getAdminAddDist() {
		return adminAddDist;
	}

	public void setAdminAddDist(String adminAddDist) {
		this.adminAddDist = adminAddDist;
	}

	public String getAdminAddTaluko() {
		return adminAddTaluko;
	}

	public void setAdminAddTaluko(String adminAddTaluko) {
		this.adminAddTaluko = adminAddTaluko;
	}

	public Object getRegAdd() {
		return regAdd;
	}

	public void setRegAdd(Object regAdd) {
		this.regAdd = regAdd;
	}

	public Object getRegAddPremise() {
		return regAddPremise;
	}

	public void setRegAddPremise(Object regAddPremise) {
		this.regAddPremise = regAddPremise;
	}

	public Object getRegAddStreetName() {
		return regAddStreetName;
	}

	public void setRegAddStreetName(Object regAddStreetName) {
		this.regAddStreetName = regAddStreetName;
	}

	public Object getRegAddLandmark() {
		return regAddLandmark;
	}

	public void setRegAddLandmark(Object regAddLandmark) {
		this.regAddLandmark = regAddLandmark;
	}

	public Object getRegAddCountry() {
		return regAddCountry;
	}

	public void setRegAddCountry(Object regAddCountry) {
		this.regAddCountry = regAddCountry;
	}

	public Object getRegAddState() {
		return regAddState;
	}

	public void setRegAddState(Object regAddState) {
		this.regAddState = regAddState;
	}

	public Object getRegAddCity() {
		return regAddCity;
	}

	public void setRegAddCity(Object regAddCity) {
		this.regAddCity = regAddCity;
	}

	public Object getRegAddPincode() {
		return regAddPincode;
	}

	public void setRegAddPincode(Object regAddPincode) {
		this.regAddPincode = regAddPincode;
	}

	public Object getRegPinData() {
		return regPinData;
	}

	public void setRegPinData(Object regPinData) {
		this.regPinData = regPinData;
	}

	public String getRegAddDist() {
		return regAddDist;
	}

	public void setRegAddDist(String regAddDist) {
		this.regAddDist = regAddDist;
	}

	public String getRegAddTaluko() {
		return regAddTaluko;
	}

	public void setRegAddTaluko(String regAddTaluko) {
		this.regAddTaluko = regAddTaluko;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public Object getMcaFinancialAndDetailsResponse() {
		return mcaFinancialAndDetailsResponse;
	}

	public void setMcaFinancialAndDetailsResponse(Object mcaFinancialAndDetailsResponse) {
		this.mcaFinancialAndDetailsResponse = mcaFinancialAndDetailsResponse;
	}

	public Boolean getIsMcqSkipped() {
		return isMcqSkipped;
	}

	public void setIsMcqSkipped(Boolean isMcqSkipped) {
		this.isMcqSkipped = isMcqSkipped;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Double getEnhancementAmount() {
		return enhancementAmount;
	}

	public void setEnhancementAmount(Double enhancementAmount) {
		this.enhancementAmount = enhancementAmount;
	}

	public String getScoringModelName() {
		return scoringModelName;
	}

	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}

	public String getEligibilityFinancialYear() {
		return eligibilityFinancialYear;
	}

	public void setEligibilityFinancialYear(String eligibilityFinancialYear) {
		this.eligibilityFinancialYear = eligibilityFinancialYear;
	}

	public Double getLoanObligation() {
		return loanObligation;
	}

	public void setLoanObligation(Double loanObligation) {
		this.loanObligation = loanObligation;
	}

	public Object getCibilConsumerReport() {
		return cibilConsumerReport;
	}

	public void setCibilConsumerReport(Object cibilConsumerReport) {
		this.cibilConsumerReport = cibilConsumerReport;
	}

	public List<Object> getMcaCorpZipFile() {
		return mcaCorpZipFile;
	}

	public void setMcaCorpZipFile(List<Object> mcaCorpZipFile) {
		this.mcaCorpZipFile = mcaCorpZipFile;
	}

	public String getCibilCmrScore() {
		return cibilCmrScore;
	}

	public void setCibilCmrScore(String cibilCmrScore) {
		this.cibilCmrScore = cibilCmrScore;
	}

	public List<CollateralSecurityDetailRequest> getCollateralSecurityDetails() {
		return collateralSecurityDetails;
	}

	public void setCollateralSecurityDetails(List<CollateralSecurityDetailRequest> collateralSecurityDetails) {
		this.collateralSecurityDetails = collateralSecurityDetails;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public String getProductServiceDesc() {
		return productServiceDesc;
	}

	public void setProductServiceDesc(String productServiceDesc) {
		this.productServiceDesc = productServiceDesc;
	}

	public String getComercialPanNo() {
		return comercialPanNo;
	}

	public void setComercialPanNo(String comercialPanNo) {
		this.comercialPanNo = comercialPanNo;
	}

	public String getMsmeRankTitle() {
		return msmeRankTitle;
	}

	public void setMsmeRankTitle(String msmeRankTitle) {
		this.msmeRankTitle = msmeRankTitle;
	}

	public Boolean getMcaNotApplicable() {
		return mcaNotApplicable;
	}

	public void setMcaNotApplicable(Boolean mcaNotApplicable) {
		this.mcaNotApplicable = mcaNotApplicable;
	}

	public Boolean getIsCampaignUser() {
		return isCampaignUser;
	}

	public void setIsCampaignUser(Boolean isCampaignUser) {
		this.isCampaignUser = isCampaignUser;
	}

	public Object getVerifyApiData() {
		return verifyApiData;
	}

	public void setVerifyApiData(Object verifyApiData) {
		this.verifyApiData = verifyApiData;
	}

	public Map<String, Object> getGstRelatedParty() {
		return gstRelatedParty;
	}

	public void setGstRelatedParty(Map<String, Object> gstRelatedParty) {
		this.gstRelatedParty = gstRelatedParty;
	}

	public Object getBankComparisionData() {
		return bankComparisionData;
	}

	public void setBankComparisionData(Object bankComparisionData) {
		this.bankComparisionData = bankComparisionData;
	}

	public List<Object> getNbfcPANReport() {
		return nbfcPANReport;
	}

	public void setNbfcPANReport(List<Object> nbfcPANReport) {
		this.nbfcPANReport = nbfcPANReport;
	}

	public List<Object> getNbfcAddressProofReport() {
		return nbfcAddressProofReport;
	}

	public void setNbfcAddressProofReport(List<Object> nbfcAddressProofReport) {
		this.nbfcAddressProofReport = nbfcAddressProofReport;
	}

	public List<Object> getNbfcTripartiteAgreement() {
		return nbfcTripartiteAgreement;
	}

	public void setNbfcTripartiteAgreement(List<Object> nbfcTripartiteAgreement) {
		this.nbfcTripartiteAgreement = nbfcTripartiteAgreement;
	}

	public List<Object> getNbfcSanctionLetterOfNBFC() {
		return nbfcSanctionLetterOfNBFC;
	}

	public void setNbfcSanctionLetterOfNBFC(List<Object> nbfcSanctionLetterOfNBFC) {
		this.nbfcSanctionLetterOfNBFC = nbfcSanctionLetterOfNBFC;
	}

	public List<Object> getNbfcLetterOfIntent() {
		return nbfcLetterOfIntent;
	}

	public void setNbfcLetterOfIntent(List<Object> nbfcLetterOfIntent) {
		this.nbfcLetterOfIntent = nbfcLetterOfIntent;
	}

	public List<Object> getNbfcDemandPromissonryNote() {
		return nbfcDemandPromissonryNote;
	}

	public void setNbfcDemandPromissonryNote(List<Object> nbfcDemandPromissonryNote) {
		this.nbfcDemandPromissonryNote = nbfcDemandPromissonryNote;
	}

	public List<Object> getNbfcOther() {
		return nbfcOther;
	}

	public void setNbfcOther(List<Object> nbfcOther) {
		this.nbfcOther = nbfcOther;
	}

	public Object getDisbursementRequestDetails() {
		return disbursementRequestDetails;
	}

	public void setDisbursementRequestDetails(Object disbursementRequestDetails) {
		this.disbursementRequestDetails = disbursementRequestDetails;
	}

	public Boolean getIsNBFCApplication() {
		return isNBFCApplication;
	}

	public void setIsNBFCApplication(Boolean NBFCApplication) {
		isNBFCApplication = NBFCApplication;
	}

	public List<Object> getAgeProofDocument() {
		return ageProofDocument;
	}

	public void setAgeProofDocument(List<Object> ageProofDocument) {
		this.ageProofDocument = ageProofDocument;
	}

	public List<Object> getIdProofDocument() {
		return idProofDocument;
	}

	public void setIdProofDocument(List<Object> idProofDocument) {
		this.idProofDocument = idProofDocument;
	}

	public List<Object> getAddressProofDocument() {
		return addressProofDocument;
	}

	public void setAddressProofDocument(List<Object> addressProofDocument) {
		this.addressProofDocument = addressProofDocument;
	}

	public List<Object> getApplicationForm() {
		return applicationForm;
	}

	public void setApplicationForm(List<Object> applicationForm) {
		this.applicationForm = applicationForm;
	}

	public List<Object> getDetailedAssessmentNote() {
		return detailedAssessmentNote;
	}

	public void setDetailedAssessmentNote(List<Object> detailedAssessmentNote) {
		this.detailedAssessmentNote = detailedAssessmentNote;
	}

	public List<Object> getNbfcProjectedFinancials() {
		return nbfcProjectedFinancials;
	}

	public void setNbfcProjectedFinancials(List<Object> nbfcProjectedFinancials) {
		this.nbfcProjectedFinancials = nbfcProjectedFinancials;
	}

	public String getNoteOfBorrower() {
		return noteOfBorrower;
	}

	public void setNoteOfBorrower(String noteOfBorrower) {
		this.noteOfBorrower = noteOfBorrower;
	}
	
	
}
