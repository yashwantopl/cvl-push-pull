package com.capitaworld.service.loans.model.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.Address;

public class CorporateCoApplicantRequest {

	private Long id;

	private Long clientId;
	
	private Long applicationId;

	private String detailsFilledCount;
	
	private Integer constitutionId;

	private String organisationName;

	private Address firstAddress;

	private Address secondAddress;
	
	private Double monthlyIncome;
	
	private Boolean sameAs;

	private String aadharNumber;

	private String panNo;

	private String landlineNo;

	private Integer relationshipWithApplicant;

	private Boolean isCoApp1DetailsFilled;

	private Boolean isCoApp2DetailsFilled;

	private Boolean isActive;
	
	//CO-APPLICANT UPLOADS

	private List<Object> coApplicant_BankACStatments;
	private List<Object> coApplicant_ItReturn;
	private List<Object> coApplicant_BalanceSheet;
	private List<Object> coApplicant_Form_16;

	private List<Object> coApplicant_AddressProof;
	private List<Object> coApplicant_aadharCardList;
	private List<Object> coApplicant_panCardList;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
	}

	public Integer getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Address getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}

	public Address getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(Address secondAddress) {
		this.secondAddress = secondAddress;
	}

	public Boolean getSameAs() {
		return sameAs;
	}

	public void setSameAs(Boolean sameAs) {
		this.sameAs = sameAs;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public Integer getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(Integer relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public Boolean getIsCoApp1DetailsFilled() {
		return isCoApp1DetailsFilled;
	}

	public void setIsCoApp1DetailsFilled(Boolean isCoApp1DetailsFilled) {
		this.isCoApp1DetailsFilled = isCoApp1DetailsFilled;
	}

	public Boolean getIsCoApp2DetailsFilled() {
		return isCoApp2DetailsFilled;
	}

	public void setIsCoApp2DetailsFilled(Boolean isCoApp2DetailsFilled) {
		this.isCoApp2DetailsFilled = isCoApp2DetailsFilled;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
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




}
