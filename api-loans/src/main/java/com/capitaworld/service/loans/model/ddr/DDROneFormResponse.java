package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.OwnershipDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDROneFormResponse  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nameOfBorrower;
	private String regOfficeAddress;
	private String contactNo;
	private String corpOfficeAddress;
	private String regEmailId;
	private String constitution;
	private String establishMentYear;
	private String aboutMe;
	
	List<PromotorBackgroundDetailResponse> promoBackRespList = new ArrayList<PromotorBackgroundDetailResponse>();
	
	List<OwnershipDetailResponse> ownershipRespList = new ArrayList<OwnershipDetailResponse>();
	
	List<FinancialArrangementsDetailResponse> fincArrngDetailResList = new ArrayList<FinancialArrangementsDetailResponse>();
	
	List<ProposedProductDetailRequest> proposedProductDetailList = new ArrayList<ProposedProductDetailRequest>();
	List<ExistingProductDetailRequest> existingProductDetailList = new ArrayList<ExistingProductDetailRequest>();
	
	
	public String getNameOfBorrower() {
		return nameOfBorrower;
	}
	public void setNameOfBorrower(String nameOfBorrower) {
		this.nameOfBorrower = nameOfBorrower;
	}
	public String getRegOfficeAddress() {
		return regOfficeAddress;
	}
	public void setRegOfficeAddress(String regOfficeAddress) {
		this.regOfficeAddress = regOfficeAddress;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getCorpOfficeAddress() {
		return corpOfficeAddress;
	}
	public void setCorpOfficeAddress(String corpOfficeAddress) {
		this.corpOfficeAddress = corpOfficeAddress;
	}
	public String getRegEmailId() {
		return regEmailId;
	}
	public void setRegEmailId(String regEmailId) {
		this.regEmailId = regEmailId;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getEstablishMentYear() {
		return establishMentYear;
	}
	public void setEstablishMentYear(String establishMentYear) {
		this.establishMentYear = establishMentYear;
	}
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public List<PromotorBackgroundDetailResponse> getPromoBackRespList() {
		return promoBackRespList;
	}
	public void setPromoBackRespList(List<PromotorBackgroundDetailResponse> promoBackRespList) {
		this.promoBackRespList = promoBackRespList;
	}
	public List<OwnershipDetailResponse> getOwnershipRespList() {
		return ownershipRespList;
	}
	public void setOwnershipRespList(List<OwnershipDetailResponse> ownershipRespList) {
		this.ownershipRespList = ownershipRespList;
	}
	public List<FinancialArrangementsDetailResponse> getFincArrngDetailResList() {
		return fincArrngDetailResList;
	}
	public void setFincArrngDetailResList(List<FinancialArrangementsDetailResponse> fincArrngDetailResList) {
		this.fincArrngDetailResList = fincArrngDetailResList;
	}
	public List<ProposedProductDetailRequest> getProposedProductDetailList() {
		return proposedProductDetailList;
	}
	public void setProposedProductDetailList(List<ProposedProductDetailRequest> proposedProductDetailList) {
		this.proposedProductDetailList = proposedProductDetailList;
	}
	public List<ExistingProductDetailRequest> getExistingProductDetailList() {
		return existingProductDetailList;
	}
	public void setExistingProductDetailList(List<ExistingProductDetailRequest> existingProductDetailList) {
		this.existingProductDetailList = existingProductDetailList;
	}
	
	
	
	
	
	

}
