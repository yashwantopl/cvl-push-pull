package com.capitaworld.service.loans.model.teaser.primaryview;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;

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
	
    
}
