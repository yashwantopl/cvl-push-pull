/**
 * 
 */
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
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;

/**
 * @author nilay
 *
 */
public class NtbFinalViewResponse implements Serializable{

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
	private Integer productId;

	private String purposeOfLoan;
	private String businessAssetAmount;
	private String wcAmount;
	private String otherAmt;
	private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
	private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
	private Object FinancialInputRequest;

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
		/**
		 * @return the matchesList
		 */
		public List<?> getMatchesList() {
			return matchesList;
		}
		/**
		 * @param matchesList the matchesList to set
		 */
		public void setMatchesList(List<?> matchesList) {
			this.matchesList = matchesList;
		}
		/**
		 * @return the organisationName
		 */
		public String getOrganisationName() {
			return organisationName;
		}
		/**
		 * @param organisationName the organisationName to set
		 */
		public void setOrganisationName(String organisationName) {
			this.organisationName = organisationName;
		}
		/**
		 * @return the aboutUs
		 */
		public String getAboutUs() {
			return aboutUs;
		}
		/**
		 * @param aboutUs the aboutUs to set
		 */
		public void setAboutUs(String aboutUs) {
			this.aboutUs = aboutUs;
		}
		/**
		 * @return the panNo
		 */
		public String getPanNo() {
			return panNo;
		}
		/**
		 * @param panNo the panNo to set
		 */
		public void setPanNo(String panNo) {
			this.panNo = panNo;
		}
		/**
		 * @return the websiteAddress
		 */
		public String getWebsiteAddress() {
			return websiteAddress;
		}
		/**
		 * @param websiteAddress the websiteAddress to set
		 */
		public void setWebsiteAddress(String websiteAddress) {
			this.websiteAddress = websiteAddress;
		}
		/**
		 * @return the city
		 */
		public String getCity() {
			return city;
		}
		/**
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}
		/**
		 * @return the regOfficeCity
		 */
		public String getRegOfficeCity() {
			return regOfficeCity;
		}
		/**
		 * @param regOfficeCity the regOfficeCity to set
		 */
		public void setRegOfficeCity(String regOfficeCity) {
			this.regOfficeCity = regOfficeCity;
		}
		/**
		 * @return the addOfficeCity
		 */
		public String getAddOfficeCity() {
			return addOfficeCity;
		}
		/**
		 * @param addOfficeCity the addOfficeCity to set
		 */
		public void setAddOfficeCity(String addOfficeCity) {
			this.addOfficeCity = addOfficeCity;
		}
		/**
		 * @return the country
		 */
		public String getCountry() {
			return country;
		}
		/**
		 * @param country the country to set
		 */
		public void setCountry(String country) {
			this.country = country;
		}
		/**
		 * @return the regOfficecountry
		 */
		public String getRegOfficecountry() {
			return regOfficecountry;
		}
		/**
		 * @param regOfficecountry the regOfficecountry to set
		 */
		public void setRegOfficecountry(String regOfficecountry) {
			this.regOfficecountry = regOfficecountry;
		}
		/**
		 * @return the addOfficecountry
		 */
		public String getAddOfficecountry() {
			return addOfficecountry;
		}
		/**
		 * @param addOfficecountry the addOfficecountry to set
		 */
		public void setAddOfficecountry(String addOfficecountry) {
			this.addOfficecountry = addOfficecountry;
		}
		/**
		 * @return the state
		 */
		public String getState() {
			return state;
		}
		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}
		/**
		 * @return the regOfficestate
		 */
		public String getRegOfficestate() {
			return regOfficestate;
		}
		/**
		 * @param regOfficestate the regOfficestate to set
		 */
		public void setRegOfficestate(String regOfficestate) {
			this.regOfficestate = regOfficestate;
		}
		/**
		 * @return the addOfficestate
		 */
		public String getAddOfficestate() {
			return addOfficestate;
		}
		/**
		 * @param addOfficestate the addOfficestate to set
		 */
		public void setAddOfficestate(String addOfficestate) {
			this.addOfficestate = addOfficestate;
		}
		/**
		 * @return the constitution
		 */
		public String getConstitution() {
			return constitution;
		}
		/**
		 * @param constitution the constitution to set
		 */
		public void setConstitution(String constitution) {
			this.constitution = constitution;
		}
		/**
		 * @return the establishmentMonth
		 */
		public String getEstablishmentMonth() {
			return establishmentMonth;
		}
		/**
		 * @param establishmentMonth the establishmentMonth to set
		 */
		public void setEstablishmentMonth(String establishmentMonth) {
			this.establishmentMonth = establishmentMonth;
		}
		/**
		 * @return the establishmentYear
		 */
		public String getEstablishmentYear() {
			return establishmentYear;
		}
		/**
		 * @param establishmentYear the establishmentYear to set
		 */
		public void setEstablishmentYear(String establishmentYear) {
			this.establishmentYear = establishmentYear;
		}
		/**
		 * @return the groupName
		 */
		public String getGroupName() {
			return groupName;
		}
		/**
		 * @param groupName the groupName to set
		 */
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		/**
		 * @return the keyVericalFunding
		 */
		public String getKeyVericalFunding() {
			return keyVericalFunding;
		}
		/**
		 * @param keyVericalFunding the keyVericalFunding to set
		 */
		public void setKeyVericalFunding(String keyVericalFunding) {
			this.keyVericalFunding = keyVericalFunding;
		}
		/**
		 * @return the keyVericalSector
		 */
		public String getKeyVericalSector() {
			return keyVericalSector;
		}
		/**
		 * @param keyVericalSector the keyVericalSector to set
		 */
		public void setKeyVericalSector(String keyVericalSector) {
			this.keyVericalSector = keyVericalSector;
		}
		/**
		 * @return the keyVericalSubsector
		 */
		public String getKeyVericalSubsector() {
			return keyVericalSubsector;
		}
		/**
		 * @param keyVericalSubsector the keyVericalSubsector to set
		 */
		public void setKeyVericalSubsector(String keyVericalSubsector) {
			this.keyVericalSubsector = keyVericalSubsector;
		}
		/**
		 * @return the projectBrief
		 */
		public String getProjectBrief() {
			return projectBrief;
		}
		/**
		 * @param projectBrief the projectBrief to set
		 */
		public void setProjectBrief(String projectBrief) {
			this.projectBrief = projectBrief;
		}
		/**
		 * @return the currencyDenomination
		 */
		public String getCurrencyDenomination() {
			return currencyDenomination;
		}
		/**
		 * @param currencyDenomination the currencyDenomination to set
		 */
		public void setCurrencyDenomination(String currencyDenomination) {
			this.currencyDenomination = currencyDenomination;
		}
		/**
		 * @return the loanType
		 */
		public String getLoanType() {
			return LoanType;
		}
		/**
		 * @param loanType the loanType to set
		 */
		public void setLoanType(String loanType) {
			LoanType = loanType;
		}
		/**
		 * @return the dateOfProposal
		 */
		public String getDateOfProposal() {
			return dateOfProposal;
		}
		/**
		 * @param dateOfProposal the dateOfProposal to set
		 */
		public void setDateOfProposal(String dateOfProposal) {
			this.dateOfProposal = dateOfProposal;
		}
		/**
		 * @return the isCreditRatingAvailable
		 */
		public String getIsCreditRatingAvailable() {
			return isCreditRatingAvailable;
		}
		/**
		 * @param isCreditRatingAvailable the isCreditRatingAvailable to set
		 */
		public void setIsCreditRatingAvailable(String isCreditRatingAvailable) {
			this.isCreditRatingAvailable = isCreditRatingAvailable;
		}
		/**
		 * @return the sharePriceFace
		 */
		public Double getSharePriceFace() {
			return sharePriceFace;
		}
		/**
		 * @param sharePriceFace the sharePriceFace to set
		 */
		public void setSharePriceFace(Double sharePriceFace) {
			this.sharePriceFace = sharePriceFace;
		}
		/**
		 * @return the sharePriceMarket
		 */
		public Double getSharePriceMarket() {
			return sharePriceMarket;
		}
		/**
		 * @param sharePriceMarket the sharePriceMarket to set
		 */
		public void setSharePriceMarket(Double sharePriceMarket) {
			this.sharePriceMarket = sharePriceMarket;
		}
		/**
		 * @return the loanAmount
		 */
		public String getLoanAmount() {
			return loanAmount;
		}
		/**
		 * @param loanAmount the loanAmount to set
		 */
		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}
		/**
		 * @return the gstIn
		 */
		public String getGstIn() {
			return gstIn;
		}
		/**
		 * @param gstIn the gstIn to set
		 */
		public void setGstIn(String gstIn) {
			this.gstIn = gstIn;
		}
		/**
		 * @return the haveCollateralSecurity
		 */
		public String getHaveCollateralSecurity() {
			return haveCollateralSecurity;
		}
		/**
		 * @param haveCollateralSecurity the haveCollateralSecurity to set
		 */
		public void setHaveCollateralSecurity(String haveCollateralSecurity) {
			this.haveCollateralSecurity = haveCollateralSecurity;
		}
		/**
		 * @return the collateralSecurityAmount
		 */
		public String getCollateralSecurityAmount() {
			return collateralSecurityAmount;
		}
		/**
		 * @param collateralSecurityAmount the collateralSecurityAmount to set
		 */
		public void setCollateralSecurityAmount(String collateralSecurityAmount) {
			this.collateralSecurityAmount = collateralSecurityAmount;
		}
		/**
		 * @return the profilePic
		 */
		public List<Object> getProfilePic() {
			return profilePic;
		}
		/**
		 * @param profilePic the profilePic to set
		 */
		public void setProfilePic(List<Object> profilePic) {
			this.profilePic = profilePic;
		}
		/**
		 * @return the productId
		 */
		public Integer getProductId() {
			return productId;
		}
		/**
		 * @param productId the productId to set
		 */
		public void setProductId(Integer productId) {
			this.productId = productId;
		}
		/**
		 * @return the purposeOfLoan
		 */
		public String getPurposeOfLoan() {
			return purposeOfLoan;
		}
		/**
		 * @param purposeOfLoan the purposeOfLoan to set
		 */
		public void setPurposeOfLoan(String purposeOfLoan) {
			this.purposeOfLoan = purposeOfLoan;
		}
		/**
		 * @return the businessAssetAmount
		 */
		public String getBusinessAssetAmount() {
			return businessAssetAmount;
		}
		/**
		 * @param businessAssetAmount the businessAssetAmount to set
		 */
		public void setBusinessAssetAmount(String businessAssetAmount) {
			this.businessAssetAmount = businessAssetAmount;
		}
		/**
		 * @return the wcAmount
		 */
		public String getWcAmount() {
			return wcAmount;
		}
		/**
		 * @param wcAmount the wcAmount to set
		 */
		public void setWcAmount(String wcAmount) {
			this.wcAmount = wcAmount;
		}
		/**
		 * @return the otherAmt
		 */
		public String getOtherAmt() {
			return otherAmt;
		}
		/**
		 * @param otherAmt the otherAmt to set
		 */
		public void setOtherAmt(String otherAmt) {
			this.otherAmt = otherAmt;
		}
		/**
		 * @return the directorBackgroundDetailResponses
		 */
		public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
			return directorBackgroundDetailResponses;
		}
		/**
		 * @param directorBackgroundDetailResponses the directorBackgroundDetailResponses to set
		 */
		public void setDirectorBackgroundDetailResponses(
				List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
			this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
		}
		/**
		 * @return the financialArrangementsDetailResponseList
		 */
		public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
			return financialArrangementsDetailResponseList;
		}
		/**
		 * @param financialArrangementsDetailResponseList the financialArrangementsDetailResponseList to set
		 */
		public void setFinancialArrangementsDetailResponseList(
				List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
			this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
		}
		/**
		 * @return the financialInputRequest
		 */
		public Object getFinancialInputRequest() {
			return FinancialInputRequest;
		}
		/**
		 * @param financialInputRequest the financialInputRequest to set
		 */
		public void setFinancialInputRequest(Object financialInputRequest) {
			FinancialInputRequest = financialInputRequest;
		}
		/**
		 * @return the udhyogAadharNo
		 */
		public String getUdhyogAadharNo() {
			return udhyogAadharNo;
		}
		/**
		 * @param udhyogAadharNo the udhyogAadharNo to set
		 */
		public void setUdhyogAadharNo(String udhyogAadharNo) {
			this.udhyogAadharNo = udhyogAadharNo;
		}
		/**
		 * @return the creditRating
		 */
		public String getCreditRating() {
			return creditRating;
		}
		/**
		 * @param creditRating the creditRating to set
		 */
		public void setCreditRating(String creditRating) {
			this.creditRating = creditRating;
		}
		/**
		 * @return the achievementDetailList
		 */
		public List<AchievementDetailRequest> getAchievementDetailList() {
			return achievementDetailList;
		}
		/**
		 * @param achievementDetailList the achievementDetailList to set
		 */
		public void setAchievementDetailList(List<AchievementDetailRequest> achievementDetailList) {
			this.achievementDetailList = achievementDetailList;
		}
		/**
		 * @return the creditRatingOrganizationDetailResponse
		 */
		public List<CreditRatingOrganizationDetailResponse> getCreditRatingOrganizationDetailResponse() {
			return creditRatingOrganizationDetailResponse;
		}
		/**
		 * @param creditRatingOrganizationDetailResponse the creditRatingOrganizationDetailResponse to set
		 */
		public void setCreditRatingOrganizationDetailResponse(
				List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse) {
			this.creditRatingOrganizationDetailResponse = creditRatingOrganizationDetailResponse;
		}
		/**
		 * @return the proposedProductDetailRequestList
		 */
		public List<ProposedProductDetailRequest> getProposedProductDetailRequestList() {
			return proposedProductDetailRequestList;
		}
		/**
		 * @param proposedProductDetailRequestList the proposedProductDetailRequestList to set
		 */
		public void setProposedProductDetailRequestList(List<ProposedProductDetailRequest> proposedProductDetailRequestList) {
			this.proposedProductDetailRequestList = proposedProductDetailRequestList;
		}
		/**
		 * @return the promotorBackgroundDetailResponseList
		 */
		public List<PromotorBackgroundDetailResponse> getPromotorBackgroundDetailResponseList() {
			return promotorBackgroundDetailResponseList;
		}
		/**
		 * @param promotorBackgroundDetailResponseList the promotorBackgroundDetailResponseList to set
		 */
		public void setPromotorBackgroundDetailResponseList(
				List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList) {
			this.promotorBackgroundDetailResponseList = promotorBackgroundDetailResponseList;
		}
		/**
		 * @return the existingProductDetailRequestList
		 */
		public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
			return existingProductDetailRequestList;
		}
		/**
		 * @param existingProductDetailRequestList the existingProductDetailRequestList to set
		 */
		public void setExistingProductDetailRequestList(List<ExistingProductDetailRequest> existingProductDetailRequestList) {
			this.existingProductDetailRequestList = existingProductDetailRequestList;
		}
		/**
		 * @return the associatedConcernDetailRequests
		 */
		public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
			return associatedConcernDetailRequests;
		}
		/**
		 * @param associatedConcernDetailRequests the associatedConcernDetailRequests to set
		 */
		public void setAssociatedConcernDetailRequests(List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
			this.associatedConcernDetailRequests = associatedConcernDetailRequests;
		}
		/**
		 * @return the contigentLiablitiesFy
		 */
		public String getContigentLiablitiesFy() {
			return contigentLiablitiesFy;
		}
		/**
		 * @param contigentLiablitiesFy the contigentLiablitiesFy to set
		 */
		public void setContigentLiablitiesFy(String contigentLiablitiesFy) {
			this.contigentLiablitiesFy = contigentLiablitiesFy;
		}
		/**
		 * @return the contigentLiablitiesSy
		 */
		public String getContigentLiablitiesSy() {
			return contigentLiablitiesSy;
		}
		/**
		 * @param contigentLiablitiesSy the contigentLiablitiesSy to set
		 */
		public void setContigentLiablitiesSy(String contigentLiablitiesSy) {
			this.contigentLiablitiesSy = contigentLiablitiesSy;
		}
		/**
		 * @return the contigentLiablitiesTy
		 */
		public String getContigentLiablitiesTy() {
			return contigentLiablitiesTy;
		}
		/**
		 * @param contigentLiablitiesTy the contigentLiablitiesTy to set
		 */
		public void setContigentLiablitiesTy(String contigentLiablitiesTy) {
			this.contigentLiablitiesTy = contigentLiablitiesTy;
		}
		/**
		 * @return the technologyRiskId
		 */
		public String getTechnologyRiskId() {
			return technologyRiskId;
		}
		/**
		 * @param technologyRiskId the technologyRiskId to set
		 */
		public void setTechnologyRiskId(String technologyRiskId) {
			this.technologyRiskId = technologyRiskId;
		}
		/**
		 * @return the customerQuality
		 */
		public String getCustomerQuality() {
			return customerQuality;
		}
		/**
		 * @param customerQuality the customerQuality to set
		 */
		public void setCustomerQuality(String customerQuality) {
			this.customerQuality = customerQuality;
		}
		/**
		 * @return the supplierQuality
		 */
		public String getSupplierQuality() {
			return supplierQuality;
		}
		/**
		 * @param supplierQuality the supplierQuality to set
		 */
		public void setSupplierQuality(String supplierQuality) {
			this.supplierQuality = supplierQuality;
		}
		/**
		 * @return the sustainabilityProduct
		 */
		public String getSustainabilityProduct() {
			return sustainabilityProduct;
		}
		/**
		 * @param sustainabilityProduct the sustainabilityProduct to set
		 */
		public void setSustainabilityProduct(String sustainabilityProduct) {
			this.sustainabilityProduct = sustainabilityProduct;
		}
		/**
		 * @return the orderBookPosition
		 */
		public String getOrderBookPosition() {
			return orderBookPosition;
		}
		/**
		 * @param orderBookPosition the orderBookPosition to set
		 */
		public void setOrderBookPosition(String orderBookPosition) {
			this.orderBookPosition = orderBookPosition;
		}
		/**
		 * @return the employeeRelations
		 */
		public String getEmployeeRelations() {
			return employeeRelations;
		}
		/**
		 * @param employeeRelations the employeeRelations to set
		 */
		public void setEmployeeRelations(String employeeRelations) {
			this.employeeRelations = employeeRelations;
		}
		/**
		 * @return the productSeasonality
		 */
		public String getProductSeasonality() {
			return productSeasonality;
		}
		/**
		 * @param productSeasonality the productSeasonality to set
		 */
		public void setProductSeasonality(String productSeasonality) {
			this.productSeasonality = productSeasonality;
		}
		/**
		 * @return the impactOnOperatingMargins
		 */
		public String getImpactOnOperatingMargins() {
			return impactOnOperatingMargins;
		}
		/**
		 * @param impactOnOperatingMargins the impactOnOperatingMargins to set
		 */
		public void setImpactOnOperatingMargins(String impactOnOperatingMargins) {
			this.impactOnOperatingMargins = impactOnOperatingMargins;
		}
		/**
		 * @return the environmentalImpact
		 */
		public String getEnvironmentalImpact() {
			return environmentalImpact;
		}
		/**
		 * @param environmentalImpact the environmentalImpact to set
		 */
		public void setEnvironmentalImpact(String environmentalImpact) {
			this.environmentalImpact = environmentalImpact;
		}
		/**
		 * @return the accountingQuality
		 */
		public String getAccountingQuality() {
			return accountingQuality;
		}
		/**
		 * @param accountingQuality the accountingQuality to set
		 */
		public void setAccountingQuality(String accountingQuality) {
			this.accountingQuality = accountingQuality;
		}
		/**
		 * @return the financialRestructuringHistory
		 */
		public String getFinancialRestructuringHistory() {
			return financialRestructuringHistory;
		}
		/**
		 * @param financialRestructuringHistory the financialRestructuringHistory to set
		 */
		public void setFinancialRestructuringHistory(String financialRestructuringHistory) {
			this.financialRestructuringHistory = financialRestructuringHistory;
		}
		/**
		 * @return the integrity
		 */
		public String getIntegrity() {
			return integrity;
		}
		/**
		 * @param integrity the integrity to set
		 */
		public void setIntegrity(String integrity) {
			this.integrity = integrity;
		}
		/**
		 * @return the businessCommitment
		 */
		public String getBusinessCommitment() {
			return businessCommitment;
		}
		/**
		 * @param businessCommitment the businessCommitment to set
		 */
		public void setBusinessCommitment(String businessCommitment) {
			this.businessCommitment = businessCommitment;
		}
		/**
		 * @return the managementCompetence
		 */
		public String getManagementCompetence() {
			return managementCompetence;
		}
		/**
		 * @param managementCompetence the managementCompetence to set
		 */
		public void setManagementCompetence(String managementCompetence) {
			this.managementCompetence = managementCompetence;
		}
		/**
		 * @return the businessExperience
		 */
		public String getBusinessExperience() {
			return businessExperience;
		}
		/**
		 * @param businessExperience the businessExperience to set
		 */
		public void setBusinessExperience(String businessExperience) {
			this.businessExperience = businessExperience;
		}
		/**
		 * @return the successionPlanning
		 */
		public String getSuccessionPlanning() {
			return successionPlanning;
		}
		/**
		 * @param successionPlanning the successionPlanning to set
		 */
		public void setSuccessionPlanning(String successionPlanning) {
			this.successionPlanning = successionPlanning;
		}
		/**
		 * @return the financialStrength
		 */
		public String getFinancialStrength() {
			return financialStrength;
		}
		/**
		 * @param financialStrength the financialStrength to set
		 */
		public void setFinancialStrength(String financialStrength) {
			this.financialStrength = financialStrength;
		}
		/**
		 * @return the financialSupport
		 */
		public String getFinancialSupport() {
			return financialSupport;
		}
		/**
		 * @param financialSupport the financialSupport to set
		 */
		public void setFinancialSupport(String financialSupport) {
			this.financialSupport = financialSupport;
		}
		/**
		 * @return the abilityToRaiseFunds
		 */
		public String getAbilityToRaiseFunds() {
			return abilityToRaiseFunds;
		}
		/**
		 * @param abilityToRaiseFunds the abilityToRaiseFunds to set
		 */
		public void setAbilityToRaiseFunds(String abilityToRaiseFunds) {
			this.abilityToRaiseFunds = abilityToRaiseFunds;
		}
		/**
		 * @return the intraCompany
		 */
		public String getIntraCompany() {
			return intraCompany;
		}
		/**
		 * @param intraCompany the intraCompany to set
		 */
		public void setIntraCompany(String intraCompany) {
			this.intraCompany = intraCompany;
		}
		/**
		 * @return the internalControl
		 */
		public String getInternalControl() {
			return internalControl;
		}
		/**
		 * @param internalControl the internalControl to set
		 */
		public void setInternalControl(String internalControl) {
			this.internalControl = internalControl;
		}
		/**
		 * @return the creditTrackRecord
		 */
		public String getCreditTrackRecord() {
			return creditTrackRecord;
		}
		/**
		 * @param creditTrackRecord the creditTrackRecord to set
		 */
		public void setCreditTrackRecord(String creditTrackRecord) {
			this.creditTrackRecord = creditTrackRecord;
		}
		/**
		 * @return the statusOfProjectClearances
		 */
		public String getStatusOfProjectClearances() {
			return statusOfProjectClearances;
		}
		/**
		 * @param statusOfProjectClearances the statusOfProjectClearances to set
		 */
		public void setStatusOfProjectClearances(String statusOfProjectClearances) {
			this.statusOfProjectClearances = statusOfProjectClearances;
		}
		/**
		 * @return the statusOfFinancialClosure
		 */
		public String getStatusOfFinancialClosure() {
			return statusOfFinancialClosure;
		}
		/**
		 * @param statusOfFinancialClosure the statusOfFinancialClosure to set
		 */
		public void setStatusOfFinancialClosure(String statusOfFinancialClosure) {
			this.statusOfFinancialClosure = statusOfFinancialClosure;
		}
		/**
		 * @return the infrastructureAvailability
		 */
		public String getInfrastructureAvailability() {
			return infrastructureAvailability;
		}
		/**
		 * @param infrastructureAvailability the infrastructureAvailability to set
		 */
		public void setInfrastructureAvailability(String infrastructureAvailability) {
			this.infrastructureAvailability = infrastructureAvailability;
		}
		/**
		 * @return the constructionContract
		 */
		public String getConstructionContract() {
			return constructionContract;
		}
		/**
		 * @param constructionContract the constructionContract to set
		 */
		public void setConstructionContract(String constructionContract) {
			this.constructionContract = constructionContract;
		}
		/**
		 * @return the numberOfCheques
		 */
		public String getNumberOfCheques() {
			return numberOfCheques;
		}
		/**
		 * @param numberOfCheques the numberOfCheques to set
		 */
		public void setNumberOfCheques(String numberOfCheques) {
			this.numberOfCheques = numberOfCheques;
		}
		/**
		 * @return the numberOfTimesDp
		 */
		public String getNumberOfTimesDp() {
			return numberOfTimesDp;
		}
		/**
		 * @param numberOfTimesDp the numberOfTimesDp to set
		 */
		public void setNumberOfTimesDp(String numberOfTimesDp) {
			this.numberOfTimesDp = numberOfTimesDp;
		}
		/**
		 * @return the cumulativeNoOfDaysDp
		 */
		public String getCumulativeNoOfDaysDp() {
			return cumulativeNoOfDaysDp;
		}
		/**
		 * @param cumulativeNoOfDaysDp the cumulativeNoOfDaysDp to set
		 */
		public void setCumulativeNoOfDaysDp(String cumulativeNoOfDaysDp) {
			this.cumulativeNoOfDaysDp = cumulativeNoOfDaysDp;
		}
		/**
		 * @return the complianceWithSanctioned
		 */
		public String getComplianceWithSanctioned() {
			return complianceWithSanctioned;
		}
		/**
		 * @param complianceWithSanctioned the complianceWithSanctioned to set
		 */
		public void setComplianceWithSanctioned(String complianceWithSanctioned) {
			this.complianceWithSanctioned = complianceWithSanctioned;
		}
		/**
		 * @return the progressReports
		 */
		public String getProgressReports() {
			return progressReports;
		}
		/**
		 * @param progressReports the progressReports to set
		 */
		public void setProgressReports(String progressReports) {
			this.progressReports = progressReports;
		}
		/**
		 * @return the delayInReceipt
		 */
		public String getDelayInReceipt() {
			return delayInReceipt;
		}
		/**
		 * @param delayInReceipt the delayInReceipt to set
		 */
		public void setDelayInReceipt(String delayInReceipt) {
			this.delayInReceipt = delayInReceipt;
		}
		/**
		 * @return the delayInSubmission
		 */
		public String getDelayInSubmission() {
			return delayInSubmission;
		}
		/**
		 * @param delayInSubmission the delayInSubmission to set
		 */
		public void setDelayInSubmission(String delayInSubmission) {
			this.delayInSubmission = delayInSubmission;
		}
		/**
		 * @return the numberOfLc
		 */
		public String getNumberOfLc() {
			return numberOfLc;
		}
		/**
		 * @param numberOfLc the numberOfLc to set
		 */
		public void setNumberOfLc(String numberOfLc) {
			this.numberOfLc = numberOfLc;
		}
		/**
		 * @return the unhedgedForeignCurrency
		 */
		public String getUnhedgedForeignCurrency() {
			return unhedgedForeignCurrency;
		}
		/**
		 * @param unhedgedForeignCurrency the unhedgedForeignCurrency to set
		 */
		public void setUnhedgedForeignCurrency(String unhedgedForeignCurrency) {
			this.unhedgedForeignCurrency = unhedgedForeignCurrency;
		}
		/**
		 * @return the projectedDebtService
		 */
		public String getProjectedDebtService() {
			return projectedDebtService;
		}
		/**
		 * @param projectedDebtService the projectedDebtService to set
		 */
		public void setProjectedDebtService(String projectedDebtService) {
			this.projectedDebtService = projectedDebtService;
		}
		/**
		 * @return the internalRateReturn
		 */
		public String getInternalRateReturn() {
			return internalRateReturn;
		}
		/**
		 * @param internalRateReturn the internalRateReturn to set
		 */
		public void setInternalRateReturn(String internalRateReturn) {
			this.internalRateReturn = internalRateReturn;
		}
		/**
		 * @return the sensititivityAnalysis
		 */
		public String getSensititivityAnalysis() {
			return sensititivityAnalysis;
		}
		/**
		 * @param sensititivityAnalysis the sensititivityAnalysis to set
		 */
		public void setSensititivityAnalysis(String sensititivityAnalysis) {
			this.sensititivityAnalysis = sensititivityAnalysis;
		}
		/**
		 * @return the varianceInProjectedSales
		 */
		public String getVarianceInProjectedSales() {
			return varianceInProjectedSales;
		}
		/**
		 * @param varianceInProjectedSales the varianceInProjectedSales to set
		 */
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
		 * @param auditedAnnualReport the auditedAnnualReport to set
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
		 * @param itr the itr to set
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
		 * @param bankStatementFinalView the bankStatementFinalView to set
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
		 * @param sanctionLetter the sanctionLetter to set
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
		 * @param provisionalFinancials the provisionalFinancials to set
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
		 * @param netWorthStatements the netWorthStatements to set
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
		 * @param financialsOfHolding the financialsOfHolding to set
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
		 * @param assessmentOrders the assessmentOrders to set
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
		 * @param momAndAoa the momAndAoa to set
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
		 * @param gstCertificate the gstCertificate to set
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
		 * @param certificateOfIncorporation the certificateOfIncorporation to set
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
		 * @param copyOfPanCard the copyOfPanCard to set
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
		 * @param panOfAllDirectors the panOfAllDirectors to set
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
		 * @param photosOfDirectors the photosOfDirectors to set
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
		 * @param residenceAddOfDirectors the residenceAddOfDirectors to set
		 */
		public void setResidenceAddOfDirectors(List<Object> residenceAddOfDirectors) {
			this.residenceAddOfDirectors = residenceAddOfDirectors;
		}
		/**
		 * @return the cibilReport
		 */
		public List<Object> getCibilReport() {
			return cibilReport;
		}
		/**
		 * @param cibilReport the cibilReport to set
		 */
		public void setCibilReport(List<Object> cibilReport) {
			this.cibilReport = cibilReport;
		}
		/**
		 * @return the bankStatement
		 */
		public List<Object> getBankStatement() {
			return bankStatement;
		}
		/**
		 * @param bankStatement the bankStatement to set
		 */
		public void setBankStatement(List<Object> bankStatement) {
			this.bankStatement = bankStatement;
		}
		/**
		 * @return the cmaList
		 */
		public List<Object> getCmaList() {
			return cmaList;
		}
		/**
		 * @param cmaList the cmaList to set
		 */
		public void setCmaList(List<Object> cmaList) {
			this.cmaList = cmaList;
		}
		/**
		 * @return the irtPdfReport
		 */
		public List<Object> getIrtPdfReport() {
			return irtPdfReport;
		}
		/**
		 * @param irtPdfReport the irtPdfReport to set
		 */
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
		 * @param ownershipDetailResponseList the ownershipDetailResponseList to set
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
		 * @param totalCostOfProjectResponseList the totalCostOfProjectResponseList to set
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
		 * @param financeMeansDetailResponseList the financeMeansDetailResponseList to set
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
		 * @param securityCorporateDetailRequestList the securityCorporateDetailRequestList to set
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
		 * @param guarantorsCorporateDetailRequestList the guarantorsCorporateDetailRequestList to set
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
		 * @param monthlyTurnoverDetailRequestList the monthlyTurnoverDetailRequestList to set
		 */
		public void setMonthlyTurnoverDetailRequestList(List<MonthlyTurnoverDetailRequest> monthlyTurnoverDetailRequestList) {
			this.monthlyTurnoverDetailRequestList = monthlyTurnoverDetailRequestList;
		}
		/**
		 * @return the monthlyDetailList
		 */
		public Object getMonthlyDetailList() {
			return monthlyDetailList;
		}
		/**
		 * @param monthlyDetailList the monthlyDetailList to set
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
		 * @param top5FundReceivedList the top5FundReceivedList to set
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
		 * @param top5FundTransferedList the top5FundTransferedList to set
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
		 * @param dataObject the dataObject to set
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
		 * @param dataList the dataList to set
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
		 * @param eligibilityDataObject the eligibilityDataObject to set
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
		 * @param cgtmseData the cgtmseData to set
		 */
		public void setCgtmseData(Object cgtmseData) {
			this.cgtmseData = cgtmseData;
		}
		/**
		 * @return the npOrgId
		 */
		public Long getNpOrgId() {
			return npOrgId;
		}
		/**
		 * @param npOrgId the npOrgId to set
		 */
		public void setNpOrgId(Long npOrgId) {
			this.npOrgId = npOrgId;
		}
		/**
		 * @return the bankData
		 */
		public Object getBankData() {
			return bankData;
		}
		/**
		 * @param bankData the bankData to set
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
		 * @param managementRiskScore the managementRiskScore to set
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
		 * @param financialRiskScore the financialRiskScore to set
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
		 * @param buisnessRiskScore the buisnessRiskScore to set
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
		 * @param managementRiskScoreWeight the managementRiskScoreWeight to set
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
		 * @param financialRiskScoreWeight the financialRiskScoreWeight to set
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
		 * @param buisnessRiskScoreWeight the buisnessRiskScoreWeight to set
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
		 * @param scoreInterpretation the scoreInterpretation to set
		 */
		public void setScoreInterpretation(Object scoreInterpretation) {
			this.scoreInterpretation = scoreInterpretation;
		}
	    
	    
	    
	    
	
}
