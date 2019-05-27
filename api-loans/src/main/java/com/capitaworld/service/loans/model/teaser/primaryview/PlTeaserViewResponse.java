/**
 * 
 */
package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;

/**
 * @author nilay.darji
 *
 */
public class PlTeaserViewResponse {
	
	private List<?> matchesList;

    private String organisationName;
    private String fpProductName;
    private String scoringModelName;
    public String getFpProductName() {
		return fpProductName;
	}
	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}
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
    private Object dateOfProposal;
    private String isCreditRatingAvailable;
    private Double sharePriceFace;
    private Double sharePriceMarket;
    private Long loanAmount;
    private Integer productId;
    private String haveCollateralSecurity;
    private String collateralSecurityAmount;
    private List<Object> profilePic;

    /*private String  isBusinessAssetChecked;
    private String  isWorkingCapitalChecked;
    private String  isOtherGeneralChecked;*/

    private String purposeOfLoan;
    private String  businessAssetAmount;

    
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
    private Object cibilScore;
    private Object promotersContribution;
    private Object promotersContributionPer;
    private Object fraudDetectionData;
    private Object proposedDetailOfUnitFact;
    private Object proposedConstitutionOfUnit;
    
    private Object nameOfEntity;
    private Object pan;
    private Object establishDate;
    
    private String religion;
    private String residentialStatus;
    private String castCategory;
    private String diasablityType;
    private String ddoOrganizationType;
    
    private Object finalDetails;
    private Object	bankAccountDetails;
	private Object	fixDepositDetails;
	private Object	otherCurruntAssetDetail;
	private Object	obligationDetails;
	private Object	referenceDetails;
	
	private String tenure;
    private Object nameAsPerItr;
    
    
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
	
	private Object retailApplicantDetail;
	private Object retailApplicantIncomeDetails;
	private Long userId;
	
	private Object presentAdd;
    private Object presentAddPremise;
    private Object presentAddStreetName;
    private Object presentAddLandmark;
    private Object presentAddCountry;
    private Object presentAddState;
    private Object presentAddCity;
    private Object presentAddPincode;
    private Object presentAddPinData;
    private String presentAddDist;
    private String presentAddTaluko;
    
    private Object permAdd;
    private Object permAddPremise;
    private Object permAddStreetName;
    private Object permAddLandmark;
    private Object permAddCountry;
    private Object permAddState;
    private Object permAddCity;
    private Object permAddPincode;
    private Object permAddPinData;
    private String permAddDist;
    private String permAddTaluko;
    
    private Object offAdd;
    private Object offAddPremise;
    private Object offAddStreetName;
    private Object offAddLandmark;
    private Object offAddCountry;
    private Object offAddState;
    private Object offAddCity;
    private Object offAddPincode;
    private Object offPinData;
    private String offAddDist;
    private String offAddTaluko;
    
    private Object proposalData;
    
    private Object epfData;
    
    private String previousJobYear;
    private String tenureReq;
    
    
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
	
	public Object getDateOfProposal() {
		return dateOfProposal;
	}
	public void setDateOfProposal(Object dateOfProposal) {
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
	
	
	public Long getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	public List<Object> getBankStatement() {
		return bankStatement;
	}
	public void setBankStatement(List<Object> bankStatement) {
		this.bankStatement = bankStatement;
	}
	public List<Object> getCibilReport() {
		return cibilReport;
	}
	public void setCibilReport(List<Object> cibilReport) {
		this.cibilReport = cibilReport;
	}
	public List<Object> getIrtPdfReport() {
		return irtPdfReport;
	}
	public void setIrtPdfReport(List<Object> irtPdfReport) {
		this.irtPdfReport = irtPdfReport;
	}
	public List<Object> getIrtXMLReport() {
		return irtXMLReport;
	}
	public void setIrtXMLReport(List<Object> irtXMLReport) {
		this.irtXMLReport = irtXMLReport;
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
	public Object getMonthlyDetailList() {
		return monthlyDetailList;
	}
	public void setMonthlyDetailList(Object monthlyDetailList) {
		this.monthlyDetailList = monthlyDetailList;
	}
	public Object getTop5FundReceivedList() {
		return top5FundReceivedList;
	}
	public void setTop5FundReceivedList(Object top5FundReceivedList) {
		this.top5FundReceivedList = top5FundReceivedList;
	}
	public Object getTop5FundTransferedList() {
		return top5FundTransferedList;
	}
	public void setTop5FundTransferedList(Object top5FundTransferedList) {
		this.top5FundTransferedList = top5FundTransferedList;
	}
	public Object getEligibilityDataObject() {
		return eligibilityDataObject;
	}
	public void setEligibilityDataObject(Object eligibilityDataObject) {
		this.eligibilityDataObject = eligibilityDataObject;
	}
	public Object getDataList() {
		return dataList;
	}
	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}
	public Object getCgtmseData() {
		return cgtmseData;
	}
	public void setCgtmseData(Object cgtmseData) {
		this.cgtmseData = cgtmseData;
	}
	public Long getNpOrgId() {
		return npOrgId;
	}
	public void setNpOrgId(Long npOrgId) {
		this.npOrgId = npOrgId;
	}
	public Object getZipBytes() {
		return zipBytes;
	}
	public void setZipBytes(Object zipBytes) {
		this.zipBytes = zipBytes;
	}
	public Object getZipData() {
		return zipData;
	}
	public void setZipData(Object zipData) {
		this.zipData = zipData;
	}
	public Object getManagementRiskScore() {
		return managementRiskScore;
	}
	public void setManagementRiskScore(Object managementRiskScore) {
		this.managementRiskScore = managementRiskScore;
	}
	public Object getFinancialRiskScore() {
		return financialRiskScore;
	}
	public void setFinancialRiskScore(Object financialRiskScore) {
		this.financialRiskScore = financialRiskScore;
	}
	public Object getBuisnessRiskScore() {
		return buisnessRiskScore;
	}
	public void setBuisnessRiskScore(Object buisnessRiskScore) {
		this.buisnessRiskScore = buisnessRiskScore;
	}
	public Object getManagementRiskScoreWeight() {
		return managementRiskScoreWeight;
	}
	public void setManagementRiskScoreWeight(Object managementRiskScoreWeight) {
		this.managementRiskScoreWeight = managementRiskScoreWeight;
	}
	public Object getFinancialRiskScoreWeight() {
		return financialRiskScoreWeight;
	}
	public void setFinancialRiskScoreWeight(Object financialRiskScoreWeight) {
		this.financialRiskScoreWeight = financialRiskScoreWeight;
	}
	public Object getBuisnessRiskScoreWeight() {
		return buisnessRiskScoreWeight;
	}
	public void setBuisnessRiskScoreWeight(Object buisnessRiskScoreWeight) {
		this.buisnessRiskScoreWeight = buisnessRiskScoreWeight;
	}
	public Object getScoreInterpretation() {
		return scoreInterpretation;
	}
	public void setScoreInterpretation(Object scoreInterpretation) {
		this.scoreInterpretation = scoreInterpretation;
	}
	public Object getBankData() {
		return bankData;
	}
	public void setBankData(Object bankData) {
		this.bankData = bankData;
	}
	public Long getAssesmentId() {
		return assesmentId;
	}
	public void setAssesmentId(Long assesmentId) {
		this.assesmentId = assesmentId;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public Object getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(Object otherDetails) {
		this.otherDetails = otherDetails;
	}
	public String getConsitution() {
		return consitution;
	}
	public void setConsitution(String consitution) {
		this.consitution = consitution;
	}
	public String getConsitutionDetail() {
		return consitutionDetail;
	}
	public void setConsitutionDetail(String consitutionDetail) {
		this.consitutionDetail = consitutionDetail;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Object getDataObject() {
		return dataObject;
	}
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	public Object getScoringResponseList() {
		return scoringResponseList;
	}
	public void setScoringResponseList(Object scoringResponseList) {
		this.scoringResponseList = scoringResponseList;
	}
	public Boolean getIsMultipleUser() {
		return isMultipleUser;
	}
	public void setIsMultipleUser(Boolean isMultipleUser) {
		this.isMultipleUser = isMultipleUser;
	}
	public Boolean getIsMainDir() {
		return isMainDir;
	}
	public void setIsMainDir(Boolean isMainDir) {
		this.isMainDir = isMainDir;
	}
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getDirPan() {
		return dirPan;
	}
	public void setDirPan(String dirPan) {
		this.dirPan = dirPan;
	}
	
	public Object getCibilScore() {
		return cibilScore;
	}
	public void setCibilScore(Object cibilScore) {
		this.cibilScore = cibilScore;
	}
	public Object getPromotersContribution() {
		return promotersContribution;
	}
	public void setPromotersContribution(Object promotersContribution) {
		this.promotersContribution = promotersContribution;
	}
	public Object getPromotersContributionPer() {
		return promotersContributionPer;
	}
	public void setPromotersContributionPer(Object promotersContributionPer) {
		this.promotersContributionPer = promotersContributionPer;
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
	
	public Object getPresentAdd() {
		return presentAdd;
	}
	public void setPresentAdd(Object presentAdd) {
		this.presentAdd = presentAdd;
	}
	public Object getPresentAddPremise() {
		return presentAddPremise;
	}
	public void setPresentAddPremise(Object presentAddPremise) {
		this.presentAddPremise = presentAddPremise;
	}
	public Object getPresentAddStreetName() {
		return presentAddStreetName;
	}
	public void setPresentAddStreetName(Object presentAddStreetName) {
		this.presentAddStreetName = presentAddStreetName;
	}
	public Object getPresentAddLandmark() {
		return presentAddLandmark;
	}
	public void setPresentAddLandmark(Object presentAddLandmark) {
		this.presentAddLandmark = presentAddLandmark;
	}
	public Object getPresentAddCountry() {
		return presentAddCountry;
	}
	public void setPresentAddCountry(Object presentAddCountry) {
		this.presentAddCountry = presentAddCountry;
	}
	public Object getPresentAddState() {
		return presentAddState;
	}
	public void setPresentAddState(Object presentAddState) {
		this.presentAddState = presentAddState;
	}
	public Object getPresentAddCity() {
		return presentAddCity;
	}
	public void setPresentAddCity(Object presentAddCity) {
		this.presentAddCity = presentAddCity;
	}
	public Object getPresentAddPincode() {
		return presentAddPincode;
	}
	public void setPresentAddPincode(Object presentAddPincode) {
		this.presentAddPincode = presentAddPincode;
	}
	public Object getPresentAddPinData() {
		return presentAddPinData;
	}
	public void setPresentAddPinData(Object presentAddPinData) {
		this.presentAddPinData = presentAddPinData;
	}
	public String getPresentAddDist() {
		return presentAddDist;
	}
	public void setPresentAddDist(String presentAddDist) {
		this.presentAddDist = presentAddDist;
	}
	public String getPresentAddTaluko() {
		return presentAddTaluko;
	}
	public void setPresentAddTaluko(String presentAddTaluko) {
		this.presentAddTaluko = presentAddTaluko;
	}
	
	public Object getPermAdd() {
		return permAdd;
	}
	public void setPermAdd(Object permAdd) {
		this.permAdd = permAdd;
	}
	public Object getPermAddPremise() {
		return permAddPremise;
	}
	public void setPermAddPremise(Object permAddPremise) {
		this.permAddPremise = permAddPremise;
	}
	public Object getPermAddStreetName() {
		return permAddStreetName;
	}
	public void setPermAddStreetName(Object permAddStreetName) {
		this.permAddStreetName = permAddStreetName;
	}
	public Object getPermAddLandmark() {
		return permAddLandmark;
	}
	public void setPermAddLandmark(Object permAddLandmark) {
		this.permAddLandmark = permAddLandmark;
	}
	public Object getPermAddCountry() {
		return permAddCountry;
	}
	public void setPermAddCountry(Object permAddCountry) {
		this.permAddCountry = permAddCountry;
	}
	public Object getPermAddState() {
		return permAddState;
	}
	public void setPermAddState(Object permAddState) {
		this.permAddState = permAddState;
	}
	public Object getPermAddCity() {
		return permAddCity;
	}
	public void setPermAddCity(Object permAddCity) {
		this.permAddCity = permAddCity;
	}
	public Object getPermAddPincode() {
		return permAddPincode;
	}
	public void setPermAddPincode(Object permAddPincode) {
		this.permAddPincode = permAddPincode;
	}
	public Object getPermAddPinData() {
		return permAddPinData;
	}
	public void setPermAddPinData(Object permAddPinData) {
		this.permAddPinData = permAddPinData;
	}
	public String getPermAddDist() {
		return permAddDist;
	}
	public void setPermAddDist(String permAddDist) {
		this.permAddDist = permAddDist;
	}
	public String getPermAddTaluko() {
		return permAddTaluko;
	}
	public void setPermAddTaluko(String permAddTaluko) {
		this.permAddTaluko = permAddTaluko;
	}
	public Object getOffAdd() {
		return offAdd;
	}
	public void setOffAdd(Object offAdd) {
		this.offAdd = offAdd;
	}
	public Object getOffAddPremise() {
		return offAddPremise;
	}
	public void setOffAddPremise(Object offAddPremise) {
		this.offAddPremise = offAddPremise;
	}
	public Object getOffAddStreetName() {
		return offAddStreetName;
	}
	public void setOffAddStreetName(Object offAddStreetName) {
		this.offAddStreetName = offAddStreetName;
	}
	public Object getOffAddLandmark() {
		return offAddLandmark;
	}
	public void setOffAddLandmark(Object offAddLandmark) {
		this.offAddLandmark = offAddLandmark;
	}
	public Object getOffAddCountry() {
		return offAddCountry;
	}
	public void setOffAddCountry(Object offAddCountry) {
		this.offAddCountry = offAddCountry;
	}
	public Object getOffAddState() {
		return offAddState;
	}
	public void setOffAddState(Object offAddState) {
		this.offAddState = offAddState;
	}
	public Object getOffAddCity() {
		return offAddCity;
	}
	public void setOffAddCity(Object offAddCity) {
		this.offAddCity = offAddCity;
	}
	public Object getOffAddPincode() {
		return offAddPincode;
	}
	public void setOffAddPincode(Object offAddPincode) {
		this.offAddPincode = offAddPincode;
	}
	public Object getOffPinData() {
		return offPinData;
	}
	public void setOffPinData(Object offPinData) {
		this.offPinData = offPinData;
	}
	public String getOffAddDist() {
		return offAddDist;
	}
	public void setOffAddDist(String offAddDist) {
		this.offAddDist = offAddDist;
	}
	public String getOffAddTaluko() {
		return offAddTaluko;
	}
	public void setOffAddTaluko(String offAddTaluko) {
		this.offAddTaluko = offAddTaluko;
	}
	public Object getRetailApplicantDetail() {
		return retailApplicantDetail;
	}
	public void setRetailApplicantDetail(Object retailApplicantDetail) {
		this.retailApplicantDetail = retailApplicantDetail;
	}
	public Object getRetailApplicantIncomeDetails() {
		return retailApplicantIncomeDetails;
	}
	public void setRetailApplicantIncomeDetails(Object retailApplicantIncomeDetails) {
		this.retailApplicantIncomeDetails = retailApplicantIncomeDetails;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getResidentialStatus() {
		return residentialStatus;
	}
	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}
	public String getCastCategory() {
		return castCategory;
	}
	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}
	public String getDiasablityType() {
		return diasablityType;
	}
	public void setDiasablityType(String diasablityType) {
		this.diasablityType = diasablityType;
	}
	public String getDdoOrganizationType() {
		return ddoOrganizationType;
	}
	public void setDdoOrganizationType(String ddoOrganizationType) {
		this.ddoOrganizationType = ddoOrganizationType;
	}
	public Object getFinalDetails() {
		return finalDetails;
	}
	public void setFinalDetails(Object finalDetails) {
		this.finalDetails = finalDetails;
	}
	public Object getBankAccountDetails() {
		return bankAccountDetails;
	}
	public void setBankAccountDetails(Object bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}
	public Object getFixDepositDetails() {
		return fixDepositDetails;
	}
	public void setFixDepositDetails(Object fixDepositDetails) {
		this.fixDepositDetails = fixDepositDetails;
	}
	public Object getOtherCurruntAssetDetail() {
		return otherCurruntAssetDetail;
	}
	public void setOtherCurruntAssetDetail(Object otherCurruntAssetDetail) {
		this.otherCurruntAssetDetail = otherCurruntAssetDetail;
	}
	public Object getObligationDetails() {
		return obligationDetails;
	}
	public void setObligationDetails(Object obligationDetails) {
		this.obligationDetails = obligationDetails;
	}
	public Object getReferenceDetails() {
		return referenceDetails;
	}
	public void setReferenceDetails(Object referenceDetails) {
		this.referenceDetails = referenceDetails;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public Object getNameAsPerItr() {
		return nameAsPerItr;
	}
	public void setNameAsPerItr(Object nameAsPerItr) {
		this.nameAsPerItr = nameAsPerItr;
	}
	public Object getProposalData() {
		return proposalData;
	}
	public void setProposalData(Object proposalData) {
		this.proposalData = proposalData;
	}
	public String getScoringModelName() {
		return scoringModelName;
	}
	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}
	public Object getEpfData() {
		return epfData;
	}
	public void setEpfData(Object epfData) {
		this.epfData = epfData;
	}
	public String getPreviousJobYear() {
		return previousJobYear;
	}
	public void setPreviousJobYear(String previousJobYear) {
		this.previousJobYear = previousJobYear;
	}
	public String getTenureReq() {
		return tenureReq;
	}
	public void setTenureReq(String tenureReq) {
		this.tenureReq = tenureReq;
	}

}
