package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.FutureFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateCoApplicantRequest;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailResponse;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;

/**
 * Created by garima
 */
public class UnsecuredLoanFinalViewResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Term Loan Primary and Profile Data Fields
	 */
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
	private String projectBrief;
	private String currencyDenomination;
	private String LoanType;
	private String dateOfProposal;
	private Double tenure;
	private String loanAmount;
	private String landlineNo;
	private String registeredEmailAddress;
	private String registeredContactNumber;
	private Double totalCostOfEstimate;
	private List<?> industrySector;
	private List<Object> profilePic;
	private List<String> shortTermRating;
	private List<String> longTermRating;

	private List<CorporateCoApplicantRequest> coApplicantList;

	/**
	 * Term Loan Primary Frame Data Fields
	 */
	private Boolean isDprUploaded;
	private List<ProposedProductDetailRequest> proposedProductDetailRequestList;
	private List<AchievementDetailRequest> AchievementDetailList;
	private List<CreditRatingOrganizationDetailResponse> CreditRatingOrganizationDetailResponse;
	private List<OwnershipDetailResponse> ownershipDetailResponseList;
	private List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList;
	private List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequestList;
	private List<FutureFinancialEstimatesDetailRequest> futureFinancialEstimatesDetailRequestList;
	private List<ExistingProductDetailRequest> existingProductDetailRequestList;
	private List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList;
	private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
	private List<FinanceMeansDetailResponse> financeMeansDetailResponseList;
	private List<TotalCostOfProjectResponse> totalCostOfProjectResponseList;

	private List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequest;
	private List<CreditCardsDetailResponse> creditCardsDetailResponse;
	private List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequest;

	/**
	 * DPR Data Fields
	 */
	private List<BoardOfDirectorsResponse> boardOfDirectorsResponseList;
	private List<StrategicAlliancesResponse> strategicAlliancesResponseList;
	private List<KeyManagementResponse> keyManagementResponseList;
	private List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList;
	private List<TechnologyPositioningResponse> technologyPositioningResponseList;
	private List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList;
	private List<CapacityDetailResponse> capacityDetailResponses;
	private List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponse;
	private List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponse;
	private ScotAnalysisDetailResponse scotAnalysisDetailResponses;
	private DprUserDataDetailResponse dprUserDataDetailResponses;
	private DriverForFutureGrowthResponse driverForFutureGrowthResponse;
	private List<ProjectImplementationScheduleResponse> projectImplementationScheduleResponseList;

	/**
	 * Term Loan Final Frame Data Fields
	 */
	private List<AssociatedConcernDetailRequest> associatedConcernDetailRequests;
	private List<GuarantorsCorporateDetailResponse> guarantorsCorporateDetailResponseList;
	private List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequestList;

	/**
	 * Unsecured Loan Final Uploads Data Fields
	 */
	// FINANCIAL UPLOADS
	private List<Object> lastAuditedAnnualReportList;
	private List<Object> sanctionLetterCopyList;
	private List<Object> lastITReturnList;
	private List<Object> bankStatementList;
	private List<Object> netWorthStatementOfdirectorsList;
	private List<Object> provisionalFinancialsList;
	private List<Object> brochureList;
	private List<Object> itReturnForFYOfAllDirectorsList;
	private List<Object> finSubsidiariesEntitiesList;
	private List<Object> assessOrderForLastThreeYearsList;

	// KYC UPLOADS
	private List<Object> certificateOfIncorpList;
	private List<Object> detailedListOfShareholdersList;
	private List<Object> panCardList;
	private List<Object> photoOfDirectorsList;
	private List<Object> panOfDirectorsList;
	private List<Object> residenceAddProofList;
	private List<Object> resolutionForAdditionOfDirectorsList;

	// OFFLINE UPLOADS
	private List<Object> momAndAOAList;
	private List<Object> debtorsList;
	private List<Object> gstVATExciseList;
	private List<Object> letterOfIntentFromFPList;
	private List<Object> copiesOfRelevantLicenseList;
	private List<Object> salesTaxReturnsList;
	private List<Object> latestTaxPaidCoyList;
	private List<Object> encumbranceList;
	private List<Object> copiesOfTrustDeedList;
	private List<Object> marketSurveyReportList;
	private List<Object> detailsOfContLiabilitiesList;

    /**
     * Unsecured Loan Final CO-APPLICANT Uploads Data Fields
     */

	private List<Object> coApplicant_BankACStatments;
	private List<Object> coApplicant_ItReturn;
	private List<Object> coApplicant_BalanceSheet;
	private List<Object> coApplicant_Form_16;

	private List<Object> coApplicant_AddressProof;
	private List<Object> coApplicant_aadharCardList;
	private List<Object> coApplicant_panCardList;

	/**
	 * Final Term Loan Information
	 */
	private String technologyType;
	private String whetherTechnologyIsTied;
	private String technologyPatented;
	private String technologyRequiresUpgradation;
	private String marketPosition;
	private String marketPositioningTop;
	private String marketShareTurnover;

	// NEW MCQ FOR NHBS
	private String technologyRiskId;
	private String customerQuality;
	private String supplierQuality;
	private String sustainabilityProduct;
	private String employeeRelations;
	private String productSeasonality;
	private String impactOnOperatingMargins;
	private String orderBookPosition;
	private String environmentalImpact;
	private String accountingQuality;
	private String financialRestructuringHistory;
	private String integrity;
	private String businessCommitment;
	private String managementCompetence;
	private String businessExperience;
	private String successionPlanning;
	private String financialStrength;
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

	private Double sharePriceFace;
	private Double sharePriceMarket;
	private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
	private List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests;

	public String getMajorlyOnGovernment() {
		return majorlyOnGovernment;
	}

	public void setMajorlyOnGovernment(String majorlyOnGovernment) {
		this.majorlyOnGovernment = majorlyOnGovernment;
	}

	public String getBrandAmbassador() {
		return brandAmbassador;
	}

	public void setBrandAmbassador(String brandAmbassador) {
		this.brandAmbassador = brandAmbassador;
	}

	public String getDistributionAndTieUps() {
		return distributionAndTieUps;
	}

	public void setDistributionAndTieUps(String distributionAndTieUps) {
		this.distributionAndTieUps = distributionAndTieUps;
	}

	public String getMarketPositioningProduct() {
		return marketPositioningProduct;
	}

	public void setMarketPositioningProduct(String marketPositioningProduct) {
		this.marketPositioningProduct = marketPositioningProduct;
	}

	public String getMarketingPositioning() {
		return marketingPositioning;
	}

	public void setMarketingPositioning(String marketingPositioning) {
		this.marketingPositioning = marketingPositioning;
	}

	private String overseasNetwork;
	private String indiaDistributionNetwork;
	private String environmentCertification;
	private String isIsoCertified;
	private String accountingSystems;
	private String internalAudit;
	private String competence;
	private String majorlyOnGovernment;
	private String brandAmbassador;
	private String distributionAndTieUps;
	private String marketPositioningProduct;
	private String marketingPositioning;
	private String existingShareHolder;
	private String productServicesPerse;

	/**
	 * Working Capital DPR, CMA, BS Formate
	 */
	private List<Object> dprList;
	private List<Object> cmaList;
	private List<Object> bsFormatList;
	private List<Object> financialModelList;
	private List<Object> dprYourFormatList;

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getRegisteredEmailAddress() {
		return registeredEmailAddress;
	}

	public void setRegisteredEmailAddress(String registeredEmailAddress) {
		this.registeredEmailAddress = registeredEmailAddress;
	}

	public String getRegisteredContactNumber() {
		return registeredContactNumber;
	}

	public void setRegisteredContactNumber(String registeredContactNumber) {
		this.registeredContactNumber = registeredContactNumber;
	}

	public List<?> getIndustrySector() {
		return industrySector;
	}

	public void setIndustrySector(List<?> industrySector) {
		this.industrySector = industrySector;
	}

	public List<Object> getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(List<Object> profilePic) {
		this.profilePic = profilePic;
	}

	public List<String> getShortTermRating() {
		return shortTermRating;
	}

	public void setShortTermRating(List<String> shortTermRating) {
		this.shortTermRating = shortTermRating;
	}

	public List<String> getLongTermRating() {
		return longTermRating;
	}

	public void setLongTermRating(List<String> longTermRating) {
		this.longTermRating = longTermRating;
	}

	public List<BoardOfDirectorsResponse> getBoardOfDirectorsResponseList() {
		return boardOfDirectorsResponseList;
	}

	public void setBoardOfDirectorsResponseList(List<BoardOfDirectorsResponse> boardOfDirectorsResponseList) {
		this.boardOfDirectorsResponseList = boardOfDirectorsResponseList;
	}

	public List<StrategicAlliancesResponse> getStrategicAlliancesResponseList() {
		return strategicAlliancesResponseList;
	}

	public void setStrategicAlliancesResponseList(List<StrategicAlliancesResponse> strategicAlliancesResponseList) {
		this.strategicAlliancesResponseList = strategicAlliancesResponseList;
	}

	public List<KeyManagementResponse> getKeyManagementResponseList() {
		return keyManagementResponseList;
	}

	public void setKeyManagementResponseList(List<KeyManagementResponse> keyManagementResponseList) {
		this.keyManagementResponseList = keyManagementResponseList;
	}

	public List<EmployeesCategoryBreaksResponse> getEmployeesCategoryBreaksResponseList() {
		return employeesCategoryBreaksResponseList;
	}

	public void setEmployeesCategoryBreaksResponseList(
			List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList) {
		this.employeesCategoryBreaksResponseList = employeesCategoryBreaksResponseList;
	}

	public List<TechnologyPositioningResponse> getTechnologyPositioningResponseList() {
		return technologyPositioningResponseList;
	}

	public void setTechnologyPositioningResponseList(
			List<TechnologyPositioningResponse> technologyPositioningResponseList) {
		this.technologyPositioningResponseList = technologyPositioningResponseList;
	}

	public List<RevenueAndOrderBookResponse> getRevenueAndOrderBookResponseList() {
		return revenueAndOrderBookResponseList;
	}

	public void setRevenueAndOrderBookResponseList(List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList) {
		this.revenueAndOrderBookResponseList = revenueAndOrderBookResponseList;
	}

	public List<CapacityDetailResponse> getCapacityDetailResponses() {
		return capacityDetailResponses;
	}

	public void setCapacityDetailResponses(List<CapacityDetailResponse> capacityDetailResponses) {
		this.capacityDetailResponses = capacityDetailResponses;
	}

	public List<AvailabilityProposedPlantDetailResponse> getAvailabilityProposedPlantDetailResponse() {
		return availabilityProposedPlantDetailResponse;
	}

	public void setAvailabilityProposedPlantDetailResponse(
			List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponse) {
		this.availabilityProposedPlantDetailResponse = availabilityProposedPlantDetailResponse;
	}

	public List<RequirementsAndAvailabilityRawMaterialsDetailResponse> getRequirementsAndAvailabilityRawMaterialsDetailResponse() {
		return requirementsAndAvailabilityRawMaterialsDetailResponse;
	}

	public void setRequirementsAndAvailabilityRawMaterialsDetailResponse(
			List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponse) {
		this.requirementsAndAvailabilityRawMaterialsDetailResponse = requirementsAndAvailabilityRawMaterialsDetailResponse;
	}

	public ScotAnalysisDetailResponse getScotAnalysisDetailResponses() {
		return scotAnalysisDetailResponses;
	}

	public void setScotAnalysisDetailResponses(ScotAnalysisDetailResponse scotAnalysisDetailResponses) {
		this.scotAnalysisDetailResponses = scotAnalysisDetailResponses;
	}

	public DprUserDataDetailResponse getDprUserDataDetailResponses() {
		return dprUserDataDetailResponses;
	}

	public void setDprUserDataDetailResponses(DprUserDataDetailResponse dprUserDataDetailResponses) {
		this.dprUserDataDetailResponses = dprUserDataDetailResponses;
	}

	public DriverForFutureGrowthResponse getDriverForFutureGrowthResponse() {
		return driverForFutureGrowthResponse;
	}

	public void setDriverForFutureGrowthResponse(DriverForFutureGrowthResponse driverForFutureGrowthResponse) {
		this.driverForFutureGrowthResponse = driverForFutureGrowthResponse;
	}

	public List<ProjectImplementationScheduleResponse> getProjectImplementationScheduleResponseList() {
		return projectImplementationScheduleResponseList;
	}

	public void setProjectImplementationScheduleResponseList(
			List<ProjectImplementationScheduleResponse> projectImplementationScheduleResponseList) {
		this.projectImplementationScheduleResponseList = projectImplementationScheduleResponseList;
	}

	public Boolean getIsDprUploaded() {
		return isDprUploaded;
	}

	public void setIsDprUploaded(Boolean isDprUploaded) {
		this.isDprUploaded = isDprUploaded;
	}

	public List<ProposedProductDetailRequest> getProposedProductDetailRequestList() {
		return proposedProductDetailRequestList;
	}

	public void setProposedProductDetailRequestList(
			List<ProposedProductDetailRequest> proposedProductDetailRequestList) {
		this.proposedProductDetailRequestList = proposedProductDetailRequestList;
	}

	public List<AchievementDetailRequest> getAchievementDetailList() {
		return AchievementDetailList;
	}

	public void setAchievementDetailList(List<AchievementDetailRequest> achievementDetailList) {
		AchievementDetailList = achievementDetailList;
	}

	public List<com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse> getCreditRatingOrganizationDetailResponse() {
		return CreditRatingOrganizationDetailResponse;
	}

	public void setCreditRatingOrganizationDetailResponse(
			List<com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse) {
		CreditRatingOrganizationDetailResponse = creditRatingOrganizationDetailResponse;
	}

	public List<OwnershipDetailResponse> getOwnershipDetailResponseList() {
		return ownershipDetailResponseList;
	}

	public void setOwnershipDetailResponseList(List<OwnershipDetailResponse> ownershipDetailResponseList) {
		this.ownershipDetailResponseList = ownershipDetailResponseList;
	}

	public List<PromotorBackgroundDetailResponse> getPromotorBackgroundDetailResponseList() {
		return promotorBackgroundDetailResponseList;
	}

	public void setPromotorBackgroundDetailResponseList(
			List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList) {
		this.promotorBackgroundDetailResponseList = promotorBackgroundDetailResponseList;
	}

	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimatesDetailRequestList() {
		return pastFinancialEstimatesDetailRequestList;
	}

	public void setPastFinancialEstimatesDetailRequestList(
			List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequestList) {
		this.pastFinancialEstimatesDetailRequestList = pastFinancialEstimatesDetailRequestList;
	}

	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimatesDetailRequestList() {
		return futureFinancialEstimatesDetailRequestList;
	}

	public void setFutureFinancialEstimatesDetailRequestList(
			List<FutureFinancialEstimatesDetailRequest> futureFinancialEstimatesDetailRequestList) {
		this.futureFinancialEstimatesDetailRequestList = futureFinancialEstimatesDetailRequestList;
	}

	public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
		return existingProductDetailRequestList;
	}

	public void setExistingProductDetailRequestList(
			List<ExistingProductDetailRequest> existingProductDetailRequestList) {
		this.existingProductDetailRequestList = existingProductDetailRequestList;
	}

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList() {
		return securityCorporateDetailRequestList;
	}

	public void setSecurityCorporateDetailRequestList(
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList) {
		this.securityCorporateDetailRequestList = securityCorporateDetailRequestList;
	}

	public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
		return financialArrangementsDetailResponseList;
	}

	public void setFinancialArrangementsDetailResponseList(
			List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
		this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
	}

	public List<FinanceMeansDetailResponse> getFinanceMeansDetailResponseList() {
		return financeMeansDetailResponseList;
	}

	public void setFinanceMeansDetailResponseList(List<FinanceMeansDetailResponse> financeMeansDetailResponseList) {
		this.financeMeansDetailResponseList = financeMeansDetailResponseList;
	}

	public List<TotalCostOfProjectResponse> getTotalCostOfProjectResponseList() {
		return totalCostOfProjectResponseList;
	}

	public void setTotalCostOfProjectResponseList(List<TotalCostOfProjectResponse> totalCostOfProjectResponseList) {
		this.totalCostOfProjectResponseList = totalCostOfProjectResponseList;
	}

	public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
		return associatedConcernDetailRequests;
	}

	public void setAssociatedConcernDetailRequests(
			List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
		this.associatedConcernDetailRequests = associatedConcernDetailRequests;
	}

	public List<GuarantorsCorporateDetailResponse> getGuarantorsCorporateDetailResponseList() {
		return guarantorsCorporateDetailResponseList;
	}

	public void setGuarantorsCorporateDetailResponseList(
			List<GuarantorsCorporateDetailResponse> guarantorsCorporateDetailResponseList) {
		this.guarantorsCorporateDetailResponseList = guarantorsCorporateDetailResponseList;
	}

	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailRequestList() {
		return monthlyTurnoverDetailRequestList;
	}

	public void setMonthlyTurnoverDetailRequestList(
			List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequestList) {
		this.monthlyTurnoverDetailRequestList = monthlyTurnoverDetailRequestList;
	}

	public List<Object> getLastAuditedAnnualReportList() {
		return lastAuditedAnnualReportList;
	}

	public void setLastAuditedAnnualReportList(List<Object> lastAuditedAnnualReportList) {
		this.lastAuditedAnnualReportList = lastAuditedAnnualReportList;
	}

	public List<Object> getSanctionLetterCopyList() {
		return sanctionLetterCopyList;
	}

	public void setSanctionLetterCopyList(List<Object> sanctionLetterCopyList) {
		this.sanctionLetterCopyList = sanctionLetterCopyList;
	}

	public List<Object> getLastITReturnList() {
		return lastITReturnList;
	}

	public void setLastITReturnList(List<Object> lastITReturnList) {
		this.lastITReturnList = lastITReturnList;
	}

	public List<Object> getNetWorthStatementOfdirectorsList() {
		return netWorthStatementOfdirectorsList;
	}

	public void setNetWorthStatementOfdirectorsList(List<Object> netWorthStatementOfdirectorsList) {
		this.netWorthStatementOfdirectorsList = netWorthStatementOfdirectorsList;
	}

	public List<Object> getProvisionalFinancialsList() {
		return provisionalFinancialsList;
	}

	public void setProvisionalFinancialsList(List<Object> provisionalFinancialsList) {
		this.provisionalFinancialsList = provisionalFinancialsList;
	}

	public List<Object> getPanOfDirectorsList() {
		return panOfDirectorsList;
	}

	public void setPanOfDirectorsList(List<Object> panOfDirectorsList) {
		this.panOfDirectorsList = panOfDirectorsList;
	}

	public List<Object> getDetailedListOfShareholdersList() {
		return detailedListOfShareholdersList;
	}

	public void setDetailedListOfShareholdersList(List<Object> detailedListOfShareholdersList) {
		this.detailedListOfShareholdersList = detailedListOfShareholdersList;
	}

	public List<Object> getPhotoOfDirectorsList() {
		return photoOfDirectorsList;
	}

	public void setPhotoOfDirectorsList(List<Object> photoOfDirectorsList) {
		this.photoOfDirectorsList = photoOfDirectorsList;
	}

	public String getTechnologyType() {
		return technologyType;
	}

	public void setTechnologyType(String technologyType) {
		this.technologyType = technologyType;
	}

	public String getWhetherTechnologyIsTied() {
		return whetherTechnologyIsTied;
	}

	public void setWhetherTechnologyIsTied(String whetherTechnologyIsTied) {
		this.whetherTechnologyIsTied = whetherTechnologyIsTied;
	}

	public String getTechnologyPatented() {
		return technologyPatented;
	}

	public void setTechnologyPatented(String technologyPatented) {
		this.technologyPatented = technologyPatented;
	}

	public String getTechnologyRequiresUpgradation() {
		return technologyRequiresUpgradation;
	}

	public void setTechnologyRequiresUpgradation(String technologyRequiresUpgradation) {
		this.technologyRequiresUpgradation = technologyRequiresUpgradation;
	}

	public String getMarketPosition() {
		return marketPosition;
	}

	public void setMarketPosition(String marketPosition) {
		this.marketPosition = marketPosition;
	}

	public String getMarketPositioningTop() {
		return marketPositioningTop;
	}

	public void setMarketPositioningTop(String marketPositioningTop) {
		this.marketPositioningTop = marketPositioningTop;
	}

	public String getMarketShareTurnover() {
		return marketShareTurnover;
	}

	public void setMarketShareTurnover(String marketShareTurnover) {
		this.marketShareTurnover = marketShareTurnover;
	}

	public String getOverseasNetwork() {
		return overseasNetwork;
	}

	public void setOverseasNetwork(String overseasNetwork) {
		this.overseasNetwork = overseasNetwork;
	}

	public String getIndiaDistributionNetwork() {
		return indiaDistributionNetwork;
	}

	public void setIndiaDistributionNetwork(String indiaDistributionNetwork) {
		this.indiaDistributionNetwork = indiaDistributionNetwork;
	}

	public String getEnvironmentCertification() {
		return environmentCertification;
	}

	public void setEnvironmentCertification(String environmentCertification) {
		this.environmentCertification = environmentCertification;
	}

	public String getIsIsoCertified() {
		return isIsoCertified;
	}

	public void setIsIsoCertified(String isIsoCertified) {
		this.isIsoCertified = isIsoCertified;
	}

	public String getAccountingSystems() {
		return accountingSystems;
	}

	public void setAccountingSystems(String accountingSystems) {
		this.accountingSystems = accountingSystems;
	}

	public String getInternalAudit() {
		return internalAudit;
	}

	public void setInternalAudit(String internalAudit) {
		this.internalAudit = internalAudit;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public List<Object> getDprList() {
		return dprList;
	}

	public void setDprList(List<Object> dprList) {
		this.dprList = dprList;
	}

	public List<Object> getCmaList() {
		return cmaList;
	}

	public void setCmaList(List<Object> cmaList) {
		this.cmaList = cmaList;
	}

	public List<Object> getBsFormatList() {
		return bsFormatList;
	}

	public void setBsFormatList(List<Object> bsFormatList) {
		this.bsFormatList = bsFormatList;
	}

	public String getExistingShareHolder() {
		return existingShareHolder;
	}

	public void setExistingShareHolder(String existingShareHolder) {
		this.existingShareHolder = existingShareHolder;
	}

	public String getProductServicesPerse() {
		return productServicesPerse;
	}

	public void setProductServicesPerse(String productServicesPerse) {
		this.productServicesPerse = productServicesPerse;
	}

	public Double getTotalCostOfEstimate() {
		return totalCostOfEstimate;
	}

	public void setTotalCostOfEstimate(Double totalCostOfEstimate) {
		this.totalCostOfEstimate = totalCostOfEstimate;
	}

	public List<Object> getFinancialModelList() {
		return financialModelList;
	}

	public void setFinancialModelList(List<Object> financialModelList) {
		this.financialModelList = financialModelList;
	}

	public List<Object> getDprYourFormatList() {
		return dprYourFormatList;
	}

	public void setDprYourFormatList(List<Object> dprYourFormatList) {
		this.dprYourFormatList = dprYourFormatList;
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

	public List<Object> getBrochureList() {
		return brochureList;
	}

	public void setBrochureList(List<Object> brochureList) {
		this.brochureList = brochureList;
	}

	public List<Object> getPanCardList() {
		return panCardList;
	}

	public void setPanCardList(List<Object> panCardList) {
		this.panCardList = panCardList;
	}

	public List<CorporateCoApplicantRequest> getCoApplicantList() {
		return coApplicantList;
	}

	public void setCoApplicantList(List<CorporateCoApplicantRequest> coApplicantList) {
		this.coApplicantList = coApplicantList;
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

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailsRequest() {
		return referenceRetailDetailsRequest;
	}

	public void setReferenceRetailDetailsRequest(List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequest) {
		this.referenceRetailDetailsRequest = referenceRetailDetailsRequest;
	}

	public List<Object> getCoApplicant_BankACStatments() {
		return coApplicant_BankACStatments;
	}

	public void setCoApplicant_BankACStatments(List<Object> coApplicant_BankACStatments) {
		this.coApplicant_BankACStatments = coApplicant_BankACStatments;
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

	public List<Object> getCoApplicant_Form_16() {
		return coApplicant_Form_16;
	}

	public void setCoApplicant_Form_16(List<Object> coApplicant_Form_16) {
		this.coApplicant_Form_16 = coApplicant_Form_16;
	}

	public List<Object> getCoApplicant_AddressProof() {
		return coApplicant_AddressProof;
	}

	public void setCoApplicant_AddressProof(List<Object> coApplicant_AddressProof) {
		this.coApplicant_AddressProof = coApplicant_AddressProof;
	}

	public List<Object> getCoApplicant_aadharCardList() {
		return coApplicant_aadharCardList;
	}

	public void setCoApplicant_aadharCardList(List<Object> coApplicant_aadharCardList) {
		this.coApplicant_aadharCardList = coApplicant_aadharCardList;
	}

	public List<Object> getCoApplicant_panCardList() {
		return coApplicant_panCardList;
	}

	public void setCoApplicant_panCardList(List<Object> coApplicant_panCardList) {
		this.coApplicant_panCardList = coApplicant_panCardList;
	}

	public CorporateCoApplicantRequest addCoApplicantList(CorporateCoApplicantRequest corporateCoApplicantRequest) {
		getCoApplicantList().add(corporateCoApplicantRequest);
		return corporateCoApplicantRequest;
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

	public String getOrderBookPosition() {
		return orderBookPosition;
	}

	public void setOrderBookPosition(String orderBookPosition) {
		this.orderBookPosition = orderBookPosition;
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

	public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
		return directorBackgroundDetailResponses;
	}

	public void setDirectorBackgroundDetailResponses(
			List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
		this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
	}

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailsRequests() {
		return referenceRetailDetailsRequests;
	}

	public void setReferenceRetailDetailsRequests(List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests) {
		this.referenceRetailDetailsRequests = referenceRetailDetailsRequests;
	}

	public List<Object> getBankStatementList() {
		return bankStatementList;
	}

	public void setBankStatementList(List<Object> bankStatementList) {
		this.bankStatementList = bankStatementList;
	}

	public List<Object> getItReturnForFYOfAllDirectorsList() {
		return itReturnForFYOfAllDirectorsList;
	}

	public void setItReturnForFYOfAllDirectorsList(List<Object> itReturnForFYOfAllDirectorsList) {
		this.itReturnForFYOfAllDirectorsList = itReturnForFYOfAllDirectorsList;
	}

	public List<Object> getFinSubsidiariesEntitiesList() {
		return finSubsidiariesEntitiesList;
	}

	public void setFinSubsidiariesEntitiesList(List<Object> finSubsidiariesEntitiesList) {
		this.finSubsidiariesEntitiesList = finSubsidiariesEntitiesList;
	}

	public List<Object> getAssessOrderForLastThreeYearsList() {
		return assessOrderForLastThreeYearsList;
	}

	public void setAssessOrderForLastThreeYearsList(List<Object> assessOrderForLastThreeYearsList) {
		this.assessOrderForLastThreeYearsList = assessOrderForLastThreeYearsList;
	}

	public List<Object> getCertificateOfIncorpList() {
		return certificateOfIncorpList;
	}

	public void setCertificateOfIncorpList(List<Object> certificateOfIncorpList) {
		this.certificateOfIncorpList = certificateOfIncorpList;
	}

	public List<Object> getResidenceAddProofList() {
		return residenceAddProofList;
	}

	public void setResidenceAddProofList(List<Object> residenceAddProofList) {
		this.residenceAddProofList = residenceAddProofList;
	}

	public List<Object> getResolutionForAdditionOfDirectorsList() {
		return resolutionForAdditionOfDirectorsList;
	}

	public void setResolutionForAdditionOfDirectorsList(List<Object> resolutionForAdditionOfDirectorsList) {
		this.resolutionForAdditionOfDirectorsList = resolutionForAdditionOfDirectorsList;
	}

	public List<Object> getMomAndAOAList() {
		return momAndAOAList;
	}

	public void setMomAndAOAList(List<Object> momAndAOAList) {
		this.momAndAOAList = momAndAOAList;
	}

	public List<Object> getDebtorsList() {
		return debtorsList;
	}

	public void setDebtorsList(List<Object> debtorsList) {
		this.debtorsList = debtorsList;
	}

	public List<Object> getGstVATExciseList() {
		return gstVATExciseList;
	}

	public void setGstVATExciseList(List<Object> gstVATExciseList) {
		this.gstVATExciseList = gstVATExciseList;
	}

	public List<Object> getLetterOfIntentFromFPList() {
		return letterOfIntentFromFPList;
	}

	public void setLetterOfIntentFromFPList(List<Object> letterOfIntentFromFPList) {
		this.letterOfIntentFromFPList = letterOfIntentFromFPList;
	}

	public List<Object> getCopiesOfRelevantLicenseList() {
		return copiesOfRelevantLicenseList;
	}

	public void setCopiesOfRelevantLicenseList(List<Object> copiesOfRelevantLicenseList) {
		this.copiesOfRelevantLicenseList = copiesOfRelevantLicenseList;
	}

	public List<Object> getSalesTaxReturnsList() {
		return salesTaxReturnsList;
	}

	public void setSalesTaxReturnsList(List<Object> salesTaxReturnsList) {
		this.salesTaxReturnsList = salesTaxReturnsList;
	}

	public List<Object> getLatestTaxPaidCoyList() {
		return latestTaxPaidCoyList;
	}

	public void setLatestTaxPaidCoyList(List<Object> latestTaxPaidCoyList) {
		this.latestTaxPaidCoyList = latestTaxPaidCoyList;
	}

	public List<Object> getEncumbranceList() {
		return encumbranceList;
	}

	public void setEncumbranceList(List<Object> encumbranceList) {
		this.encumbranceList = encumbranceList;
	}

	public List<Object> getCopiesOfTrustDeedList() {
		return copiesOfTrustDeedList;
	}

	public void setCopiesOfTrustDeedList(List<Object> copiesOfTrustDeedList) {
		this.copiesOfTrustDeedList = copiesOfTrustDeedList;
	}

	public List<Object> getMarketSurveyReportList() {
		return marketSurveyReportList;
	}

	public void setMarketSurveyReportList(List<Object> marketSurveyReportList) {
		this.marketSurveyReportList = marketSurveyReportList;
	}

	public List<Object> getDetailsOfContLiabilitiesList() {
		return detailsOfContLiabilitiesList;
	}

	public void setDetailsOfContLiabilitiesList(List<Object> detailsOfContLiabilitiesList) {
		this.detailsOfContLiabilitiesList = detailsOfContLiabilitiesList;
	}
}
