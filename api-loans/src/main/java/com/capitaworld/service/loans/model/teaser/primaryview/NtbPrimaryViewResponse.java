/**
 * 
 */
package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;

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


}
