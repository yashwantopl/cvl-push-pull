package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileReqRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1425916690320825008L;

	// Prelim Info
	private CorporateProfileRequest corporateProfileRequest;
	private List<DirectorBackgroundDetailRequest> dirBackList;

	// Detailed Info
	private List<AchievementDetailRequest> achievementList;
	private List<AssociatedConcernDetailRequest> associateConcernList;
	private List<CreditRatingOrganizationDetailRequest> creditRatingOrgList;
	private List<ExistingProductDetailRequest> exPrList;
	private List<GuarantorsCorporateDetailRequest> guaCorpList;
	private List<MonthlyTurnoverDetailRequest> monTurnoverList;
	private List<OwnershipDetailRequest> ownerShipDetailsList;
	private List<ProposedProductDetailRequest> proposedProdList;
	private List<GuarantorsCorporateDetailRequest> guaDetailList;

	// new class add by Ankit
	private List<TotalCostOfProjectRequest> costOfProjectRequestsList;
	private List<FinanceMeansDetailRequest> financeMeansDetailRequestsList;
	private List<SecurityCorporateDetailRequest> securityCorporateDetailRequestsList;
	private List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestsList;
	private String userName;

	private String password;

	public ProfileReqRes() {
	}

	public List<AchievementDetailRequest> getAchievementList() {

		return achievementList;

	}

	public void setAchievementList(List<AchievementDetailRequest> achievementList) {
		this.achievementList = achievementList;
	}

	public List<AssociatedConcernDetailRequest> getAssociateConcernList() {
		return associateConcernList;
	}

	public void setAssociateConcernList(List<AssociatedConcernDetailRequest> associateConcernList) {
		this.associateConcernList = associateConcernList;
	}

	public List<CreditRatingOrganizationDetailRequest> getCreditRatingOrgList() {
		return creditRatingOrgList;
	}

	public void setCreditRatingOrgList(List<CreditRatingOrganizationDetailRequest> creditRatingOrgList) {
		this.creditRatingOrgList = creditRatingOrgList;
	}

	public List<DirectorBackgroundDetailRequest> getDirBackList() {
		return dirBackList;
	}

	public void setDirBackList(List<DirectorBackgroundDetailRequest> dirBackList) {
		this.dirBackList = dirBackList;
	}

	public List<ExistingProductDetailRequest> getExPrList() {
		return exPrList;
	}

	public void setExPrList(List<ExistingProductDetailRequest> exPrList) {
		this.exPrList = exPrList;
	}

	public List<GuarantorsCorporateDetailRequest> getGuaCorpList() {
		return guaCorpList;
	}

	public void setGuaCorpList(List<GuarantorsCorporateDetailRequest> guaCorpList) {
		this.guaCorpList = guaCorpList;
	}

	public List<MonthlyTurnoverDetailRequest> getMonTurnoverList() {
		return monTurnoverList;
	}

	public void setMonTurnoverList(List<MonthlyTurnoverDetailRequest> monTurnoverList) {
		this.monTurnoverList = monTurnoverList;
	}

	public List<OwnershipDetailRequest> getOwnerShipDetailsList() {
		return ownerShipDetailsList;
	}

	public void setOwnerShipDetailsList(List<OwnershipDetailRequest> ownerShipDetailsList) {
		this.ownerShipDetailsList = ownerShipDetailsList;
	}

	public List<ProposedProductDetailRequest> getProposedProdList() {
		return proposedProdList;
	}

	public void setProposedProdList(List<ProposedProductDetailRequest> proposedProdList) {
		this.proposedProdList = proposedProdList;
	}

	public CorporateProfileRequest getCorporateProfileRequest() {
		return corporateProfileRequest;
	}

	public void setCorporateProfileRequest(CorporateProfileRequest corporateProfileRequest) {
		this.corporateProfileRequest = corporateProfileRequest;
	}

	public List<GuarantorsCorporateDetailRequest> getGuaDetailList() {
		return guaDetailList;
	}

	public void setGuaDetailList(List<GuarantorsCorporateDetailRequest> guaDetailList) {
		this.guaDetailList = guaDetailList;
	}

	public List<TotalCostOfProjectRequest> getCostOfProjectRequestsList() {
		return costOfProjectRequestsList;
	}

	public void setCostOfProjectRequestsList(List<TotalCostOfProjectRequest> costOfProjectRequestsList) {
		this.costOfProjectRequestsList = costOfProjectRequestsList;
	}

	public List<FinanceMeansDetailRequest> getFinanceMeansDetailRequestsList() {
		return financeMeansDetailRequestsList;
	}

	public void setFinanceMeansDetailRequestsList(List<FinanceMeansDetailRequest> financeMeansDetailRequestsList) {
		this.financeMeansDetailRequestsList = financeMeansDetailRequestsList;
	}

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestsList() {
		return securityCorporateDetailRequestsList;
	}

	public void setSecurityCorporateDetailRequestsList(
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestsList) {
		this.securityCorporateDetailRequestsList = securityCorporateDetailRequestsList;
	}

	public List<PromotorBackgroundDetailRequest> getPromotorBackgroundDetailRequestsList() {
		return promotorBackgroundDetailRequestsList;
	}

	public void setPromotorBackgroundDetailRequestsList(
			List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestsList) {
		this.promotorBackgroundDetailRequestsList = promotorBackgroundDetailRequestsList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ProfileReqRes [corporateProfileRequest=" + corporateProfileRequest + ", dirBackList=" + dirBackList
				+ ", achievementList=" + achievementList + ", associateConcernList=" + associateConcernList
				+ ", creditRatingOrgList=" + creditRatingOrgList + ", exPrList=" + exPrList + ", guaCorpList="
				+ guaCorpList + ", monTurnoverList=" + monTurnoverList + ", ownerShipDetailsList="
				+ ownerShipDetailsList + ", proposedProdList=" + proposedProdList + ", guaDetailList=" + guaDetailList
				+ ", costOfProjectRequestsList=" + costOfProjectRequestsList + ", financeMeansDetailRequestsList="
				+ financeMeansDetailRequestsList + ", securityCorporateDetailRequestsList="
				+ securityCorporateDetailRequestsList + ", promotorBackgroundDetailRequestsList="
				+ promotorBackgroundDetailRequestsList + ", userName=" + userName + ", password=" + password + "]";
	}
}
