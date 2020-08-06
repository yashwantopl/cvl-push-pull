package com.opl.mudra.api.loans.model.teaser.primaryview;

import java.util.List;

import com.opl.mudra.api.loans.model.AchievementDetailRequest;
import com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailResponse;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ExistingProductDetailRequest;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailResponse;
import com.opl.mudra.api.loans.model.FutureFinancialEstimatesDetailRequest;
import com.opl.mudra.api.loans.model.OwnershipDetailResponse;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ProposedProductDetailRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;
import com.opl.mudra.api.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.opl.mudra.api.loans.model.retail.ReferenceRetailDetailsRequest;

/**
 * Created by dhaval on 19-May-17.
 */
public class WorkingCapitalPrimaryViewResponse {

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

	private String constitution;
    private String establishmentMonth;
    private String establishmentYear;
    private String groupName;
    private String keyVericalFunding;
    private String projectBrief;
    private String currencyDenomination;
    private String LoanType;
    private String dateOfProposal;
    private String isCreditRatingAvailable;
    private List<?> matchesList;
    private Double sharePriceFace;
	private Double sharePriceMarket;
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
    private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
    private List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests;
    private String loanAmount;
    private String gstIn;
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

	private List<?> industrySector;
    private List<String> shortTermRating;
    private List<String> longTermRating;

    private List<Object> brochureList;
    private List<Object> certificateList;
    private List<Object> panCardList;
    private List<Object> profilePic;
    private String haveCollateralSecurity;
    private String collateralSecurityAmount;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public List<?> getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(List<?> industrySector) {
        this.industrySector = industrySector;
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

    public List<ProposedProductDetailRequest> getProposedProductDetailRequestList() {
        return proposedProductDetailRequestList;
    }

    public void setProposedProductDetailRequestList(List<ProposedProductDetailRequest> proposedProductDetailRequestList) {
        this.proposedProductDetailRequestList = proposedProductDetailRequestList;
    }

    public List<AchievementDetailRequest> getAchievementDetailList() {
        return AchievementDetailList;
    }

    public void setAchievementDetailList(List<AchievementDetailRequest> achievementDetailList) {
        AchievementDetailList = achievementDetailList;
    }

    public List<com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailResponse> getCreditRatingOrganizationDetailResponse() {
        return CreditRatingOrganizationDetailResponse;
    }

    public void setCreditRatingOrganizationDetailResponse(List<com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponse) {
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

    public void setPromotorBackgroundDetailResponseList(List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList) {
        this.promotorBackgroundDetailResponseList = promotorBackgroundDetailResponseList;
    }

    public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimatesDetailRequestList() {
        return pastFinancialEstimatesDetailRequestList;
    }

    public void setPastFinancialEstimatesDetailRequestList(List<PastFinancialEstimatesDetailRequest> pastFinancialEstimatesDetailRequestList) {
        this.pastFinancialEstimatesDetailRequestList = pastFinancialEstimatesDetailRequestList;
    }

    public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimatesDetailRequestList() {
        return futureFinancialEstimatesDetailRequestList;
    }

    public void setFutureFinancialEstimatesDetailRequestList(List<FutureFinancialEstimatesDetailRequest> futureFinancialEstimatesDetailRequestList) {
        this.futureFinancialEstimatesDetailRequestList = futureFinancialEstimatesDetailRequestList;
    }

    public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
        return existingProductDetailRequestList;
    }

    public void setExistingProductDetailRequestList(List<ExistingProductDetailRequest> existingProductDetailRequestList) {
        this.existingProductDetailRequestList = existingProductDetailRequestList;
    }

    public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList() {
        return securityCorporateDetailRequestList;
    }

    public void setSecurityCorporateDetailRequestList(List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList) {
        this.securityCorporateDetailRequestList = securityCorporateDetailRequestList;
    }

    public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
        return financialArrangementsDetailResponseList;
    }

    public void setFinancialArrangementsDetailResponseList(List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
        this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
    }

    public List<Object> getBrochureList() {
        return brochureList;
    }

    public void setBrochureList(List<Object> brochureList) {
        this.brochureList = brochureList;
    }

    public List<Object> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<Object> certificateList) {
        this.certificateList = certificateList;
    }

    public List<Object> getPanCardList() {
        return panCardList;
    }

    public void setPanCardList(List<Object> panCardList) {
        this.panCardList = panCardList;
    }

    public List<Object> getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(List<Object> profilePic) {
        this.profilePic = profilePic;
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

    public List<?> getMatchesList() {
        return matchesList;
    }

    public void setMatchesList(List<?> matchesList) {
        this.matchesList = matchesList;
    }

	public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
		return directorBackgroundDetailResponses;
	}

	public void setDirectorBackgroundDetailResponses(
			List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
		this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
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

	public List<ReferenceRetailDetailsRequest> getReferenceRetailDetailsRequests() {
		return referenceRetailDetailsRequests;
	}

	public void setReferenceRetailDetailsRequests(List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests) {
		this.referenceRetailDetailsRequests = referenceRetailDetailsRequests;
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
	 * @return the haveCollateralSecurity
	 */
	
	
	
    
}
