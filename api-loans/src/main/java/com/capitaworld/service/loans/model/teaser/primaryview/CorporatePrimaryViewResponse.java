package com.capitaworld.service.loans.model.teaser.primaryview;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.corporate.CollateralSecurityDetailRequest;

import java.util.List;

public class CorporatePrimaryViewResponse {

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
    private Double enhancementAmount;
    private String castCategory;

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
    private List<Object> mcaCorpZipFile;
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
    private Object hufReport;
    private Object cibilConsumerReport;
    private Object nameAsPerItr;
    private Object gstData;
    private Object mcaCheckStatus;
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
    private String scoringModelName;
    private Object mcaFinancialAndDetailsResponse;
    private String eligibilityFinancialYear;
    private Double loanObligation;
    private String cibilCmrScore;
    
    private String productServiceDesc;
    
    private List<CollateralSecurityDetailRequest> collateralSecurityDetails;
   
	public List<Object> getCibilReport() {
		return cibilReport;
	}
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

    public List<Object> getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(List<Object> profilePic) {
        this.profilePic = profilePic;
    }


    public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
        return directorBackgroundDetailResponses;
    }

    public void setDirectorBackgroundDetailResponses(List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
        this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
    }

    public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
        return financialArrangementsDetailResponseList;
    }

    public void setFinancialArrangementsDetailResponseList(List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
        this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
    }

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
	public List<Object> getIrtPdfReport() {
		return irtPdfReport;
	}
	public void setIrtPdfReport(List<Object> irtPdfReport) {
		this.irtPdfReport = irtPdfReport;
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
	 * @return the itrXmlIsUploaded
	 */
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
	 * @return the hufReport
	 */
	public Object getHufReport() {
		return hufReport;
	}
	/**
	 * @param hufReport the hufReport to set
	 */
	public void setHufReport(Object hufReport) {
		this.hufReport = hufReport;
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
	public Object getAdminAdd() {
		return adminAdd;
	}
	public void setAdminAdd(Object adminAdd) {
		this.adminAdd = adminAdd;
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

	public String getProductServiceDesc() {
		return productServiceDesc;
	}
	public void setProductServiceDesc(String productServiceDesc) {
		this.productServiceDesc = productServiceDesc;
	}
	
	
    
}
