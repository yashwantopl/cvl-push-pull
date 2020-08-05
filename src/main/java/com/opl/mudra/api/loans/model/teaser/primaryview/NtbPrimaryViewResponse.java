/**
 * 
 */
package com.opl.mudra.api.loans.model.teaser.primaryview;

import java.util.List;
import java.util.Map;

import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ExistingProductDetailRequest;
import com.opl.mudra.api.loans.model.FinanceMeansDetailResponse;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailResponse;
import com.opl.mudra.api.loans.model.GuarantorsCorporateDetailRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;
import com.opl.mudra.api.loans.model.TotalCostOfProjectResponse;
import com.opl.mudra.api.loans.model.corporate.CorporateDirectorIncomeRequest;

/**
 * @author nilay
 *
 */
/**
 * @author nilay
 *
 */
public class NtbPrimaryViewResponse {
	
	
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
	    private Integer productId;
	    private String haveCollateralSecurity;
	    private String collateralSecurityAmount;
	    private List<Object> profilePic;

	    /*private String  isBusinessAssetChecked;
	    private String  isWorkingCapitalChecked;
	    private String  isOtherGeneralChecked;*/

	    private String purposeOfLoan;
	    private String  businessAssetAmount;
	    private String  wcAmount;
	    private String  otherAmt;

	    private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
	    private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
	    private Object FinancialInputRequest;
	    private List<Object> bankStatement;
	    private List<Object> cibilReport;
	    private List<Object> irtPdfReport;
	    private List<Object> irtXMLReport;
	    private List<Map<String,Object>> directorBackGroundDetails;
	    private List<CorporateDirectorIncomeRequest> directorIncomeDetails;
	    private Object monthlyDetailList;
	    private Object top5FundReceivedList;
	    private Object top5FundTransferedList;
	    private Object eligibilityDataObject;
	    private Object dataList;
	    private Object cgtmseData;
	    private Long npOrgId;
	    private Object zipBytes;
	    private Object zipData;
	    private Object managementRiskScore;
	    private Object financialRiskScore;
	    private Object buisnessRiskScore;
	    private Object managementRiskScoreWeight;
	    private Object financialRiskScoreWeight;
	    private Object buisnessRiskScoreWeight;
	    private Object scoreInterpretation;
	    private Object bankData;
	    private Long assesmentId;
	    private String qualification;
	    private Object otherDetails;
	    private String consitution;
	    private String consitutionDetail;
	    private Integer businessTypeId;
	    private Object dataObject;
	    private Object scoringResponseList;
	    private Boolean isMultipleUser;
	    private Boolean isMainDir;
	    private Long appId;
	    private String dirPan;
	    private Object cibilOfMainDir;
	    private Object promotersContribution;
	    private Object promotersContributionPer;
	    private Object fraudDetectionData;
	    private Object proposedDetailOfUnitFact;
	    private Object proposedConstitutionOfUnit;
	    
	    private Object nameOfEntity;
	    private Object pan;
	    private Object establishDate;
	    
	    
	    
		private List<ExistingProductDetailRequest> existingProductDetailRequestList;
		private List<TotalCostOfProjectResponse> totalCostOfProjectResponseList;
		private List<FinanceMeansDetailResponse> financeMeansDetailResponseList;
		private List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList;
		private List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList;
		private List<AssociatedConcernDetailRequest> associatedConcernDetailRequests;
	    
		
		private List<Object> itr;
		private List<Object> bankStatementFinalView;
		private List<Object> sanctionLetter;
		private List<Object> netWorthStatements;
		private List<Object> momAndAoa;
		private List<Object> copyOfPanCard;
		private List<Object> panOfAllDirectors;
		private List<Object> photosOfDirectors;
		private List<Object> residenceAddOfDirectors;
		private List<Object> cmaList;
		private List<Object> aadhar;
		private List<Object> brochure;
		
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
		 * @return the zipBytes
		 */
		public Object getZipBytes() {
			return zipBytes;
		}
		/**
		 * @param zipBytes the zipBytes to set
		 */
		public void setZipBytes(Object zipBytes) {
			this.zipBytes = zipBytes;
		}
		/**
		 * @return the zipData
		 */
		public Object getZipData() {
			return zipData;
		}
		/**
		 * @param zipData the zipData to set
		 */
		public void setZipData(Object zipData) {
			this.zipData = zipData;
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
		 * @return the assesmentId
		 */
		public Long getAssesmentId() {
			return assesmentId;
		}
		/**
		 * @param assesmentId the assesmentId to set
		 */
		public void setAssesmentId(Long assesmentId) {
			this.assesmentId = assesmentId;
		}
		/**
		 * @return the qualification
		 */
		public String getQualification() {
			return qualification;
		}
		/**
		 * @param qualification the qualification to set
		 */
		public void setQualification(String qualification) {
			this.qualification = qualification;
		}
		public List<Map<String, Object>> getDirectorBackGroundDetails() {
			return directorBackGroundDetails;
		}
		public void setDirectorBackGroundDetails(List<Map<String, Object>> directorBackGroundDetails) {
			this.directorBackGroundDetails = directorBackGroundDetails;
		}
		public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails() {
			return directorIncomeDetails;
		}
		public void setDirectorIncomeDetails(List<CorporateDirectorIncomeRequest> directorIncomeDetails) {
			this.directorIncomeDetails = directorIncomeDetails;
		}
		/**
		 * @return the otherDetails
		 */
		public Object getOtherDetails() {
			return otherDetails;
		}
		/**
		 * @param otherDetails the otherDetails to set
		 */
		public void setOtherDetails(Object otherDetails) {
			this.otherDetails = otherDetails;
		}
		/**
		 * @return the consitution
		 */
		public String getConsitution() {
			return consitution;
		}
		/**
		 * @param consitution the consitution to set
		 */
		public void setConsitution(String consitution) {
			this.consitution = consitution;
		}
		/**
		 * @return the consitutionDetail
		 */
		public String getConsitutionDetail() {
			return consitutionDetail;
		}
		/**
		 * @param consitutionDetail the consitutionDetail to set
		 */
		public void setConsitutionDetail(String consitutionDetail) {
			this.consitutionDetail = consitutionDetail;
		}
		/**
		 * @return the businessTypeId
		 */
		public Integer getBusinessTypeId() {
			return businessTypeId;
		}
		/**
		 * @param businessTypeId the businessTypeId to set
		 */
		public void setBusinessTypeId(Integer businessTypeId) {
			this.businessTypeId = businessTypeId;
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
		 * @return the scoringResponseList
		 */
		public Object getScoringResponseList() {
			return scoringResponseList;
		}
		/**
		 * @param scoringResponseList the scoringResponseList to set
		 */
		public void setScoringResponseList(Object scoringResponseList) {
			this.scoringResponseList = scoringResponseList;
		}
		/**
		 * @return the isMultipleUser
		 */
		public Boolean getIsMultipleUser() {
			return isMultipleUser;
		}
		/**
		 * @param isMultipleUser the isMultipleUser to set
		 */
		public void setIsMultipleUser(Boolean isMultipleUser) {
			this.isMultipleUser = isMultipleUser;
		}
		/**
		 * @return the isMainDir
		 */
		public Boolean getIsMainDir() {
			return isMainDir;
		}
		/**
		 * @param isMainDir the isMainDir to set
		 */
		public void setIsMainDir(Boolean isMainDir) {
			this.isMainDir = isMainDir;
		}
		/**
		 * @return the appId
		 */
		public Long getAppId() {
			return appId;
		}
		/**
		 * @param appId the appId to set
		 */
		public void setAppId(Long appId) {
			this.appId = appId;
		}
		/**
		 * @return the dirPan
		 */
		/**
		 * @return the dirPan
		 */
		public String getDirPan() {
			return dirPan;
		}
		/**
		 * @param dirPan the dirPan to set
		 */
		public void setDirPan(String dirPan) {
			this.dirPan = dirPan;
		}
		/**
		 * @return the cibilOfMainDir
		 */
		public Object getCibilOfMainDir() {
			return cibilOfMainDir;
		}
		/**
		 * @param cibilOfMainDir the cibilOfMainDir to set
		 */
		public void setCibilOfMainDir(Object cibilOfMainDir) {
			this.cibilOfMainDir = cibilOfMainDir;
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
		public List<Object> getIrtXMLReport() {
			return irtXMLReport;
		}
		public void setIrtXMLReport(List<Object> irtXMLReport) {
			this.irtXMLReport = irtXMLReport;
		}
		public Object getFraudDetectionData() {
			return fraudDetectionData;
		}
		public void setFraudDetectionData(Object fraudDetectionData) {
			this.fraudDetectionData = fraudDetectionData;
		}
		public Object getProposedDetailOfUnitFact() {
			return proposedDetailOfUnitFact;
		}
		public void setProposedDetailOfUnitFact(Object proposedDetailOfUnitFact) {
			this.proposedDetailOfUnitFact = proposedDetailOfUnitFact;
		}
		public Object getProposedConstitutionOfUnit() {
			return proposedConstitutionOfUnit;
		}
		public void setProposedConstitutionOfUnit(Object proposedConstitutionOfUnit) {
			this.proposedConstitutionOfUnit = proposedConstitutionOfUnit;
		}
		public Object getNameOfEntity() {
			return nameOfEntity;
		}
		public void setNameOfEntity(Object nameOfEntity) {
			this.nameOfEntity = nameOfEntity;
		}
		public Object getPan() {
			return pan;
		}
		public void setPan(Object pan) {
			this.pan = pan;
		}
		public Object getEstablishDate() {
			return establishDate;
		}
		public void setEstablishDate(Object establishDate) {
			this.establishDate = establishDate;
		}
		public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
			return existingProductDetailRequestList;
		}
		public void setExistingProductDetailRequestList(List<ExistingProductDetailRequest> existingProductDetailRequestList) {
			this.existingProductDetailRequestList = existingProductDetailRequestList;
		}
		public List<TotalCostOfProjectResponse> getTotalCostOfProjectResponseList() {
			return totalCostOfProjectResponseList;
		}
		public void setTotalCostOfProjectResponseList(List<TotalCostOfProjectResponse> totalCostOfProjectResponseList) {
			this.totalCostOfProjectResponseList = totalCostOfProjectResponseList;
		}
		public List<FinanceMeansDetailResponse> getFinanceMeansDetailResponseList() {
			return financeMeansDetailResponseList;
		}
		public void setFinanceMeansDetailResponseList(List<FinanceMeansDetailResponse> financeMeansDetailResponseList) {
			this.financeMeansDetailResponseList = financeMeansDetailResponseList;
		}
		public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList() {
			return securityCorporateDetailRequestList;
		}
		public void setSecurityCorporateDetailRequestList(
				List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList) {
			this.securityCorporateDetailRequestList = securityCorporateDetailRequestList;
		}
		public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailRequestList() {
			return guarantorsCorporateDetailRequestList;
		}
		public void setGuarantorsCorporateDetailRequestList(
				List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList) {
			this.guarantorsCorporateDetailRequestList = guarantorsCorporateDetailRequestList;
		}
		public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
			return associatedConcernDetailRequests;
		}
		public void setAssociatedConcernDetailRequests(List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
			this.associatedConcernDetailRequests = associatedConcernDetailRequests;
		}
		public List<Object> getItr() {
			return itr;
		}
		public void setItr(List<Object> itr) {
			this.itr = itr;
		}
		public List<Object> getBankStatementFinalView() {
			return bankStatementFinalView;
		}
		public void setBankStatementFinalView(List<Object> bankStatementFinalView) {
			this.bankStatementFinalView = bankStatementFinalView;
		}
		public List<Object> getSanctionLetter() {
			return sanctionLetter;
		}
		public void setSanctionLetter(List<Object> sanctionLetter) {
			this.sanctionLetter = sanctionLetter;
		}
		public List<Object> getNetWorthStatements() {
			return netWorthStatements;
		}
		public void setNetWorthStatements(List<Object> netWorthStatements) {
			this.netWorthStatements = netWorthStatements;
		}
		public List<Object> getMomAndAoa() {
			return momAndAoa;
		}
		public void setMomAndAoa(List<Object> momAndAoa) {
			this.momAndAoa = momAndAoa;
		}
		public List<Object> getCopyOfPanCard() {
			return copyOfPanCard;
		}
		public void setCopyOfPanCard(List<Object> copyOfPanCard) {
			this.copyOfPanCard = copyOfPanCard;
		}
		public List<Object> getPanOfAllDirectors() {
			return panOfAllDirectors;
		}
		public void setPanOfAllDirectors(List<Object> panOfAllDirectors) {
			this.panOfAllDirectors = panOfAllDirectors;
		}
		public List<Object> getPhotosOfDirectors() {
			return photosOfDirectors;
		}
		public void setPhotosOfDirectors(List<Object> photosOfDirectors) {
			this.photosOfDirectors = photosOfDirectors;
		}
		public List<Object> getResidenceAddOfDirectors() {
			return residenceAddOfDirectors;
		}
		public void setResidenceAddOfDirectors(List<Object> residenceAddOfDirectors) {
			this.residenceAddOfDirectors = residenceAddOfDirectors;
		}
		public List<Object> getCmaList() {
			return cmaList;
		}
		public void setCmaList(List<Object> cmaList) {
			this.cmaList = cmaList;
		}
		public List<Object> getAadhar() {
			return aadhar;
		}
		public void setAadhar(List<Object> aadhar) {
			this.aadhar = aadhar;
		}
		public List<Object> getBrochure() {
			return brochure;
		}
		public void setBrochure(List<Object> brochure) {
			this.brochure = brochure;
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
		






}
