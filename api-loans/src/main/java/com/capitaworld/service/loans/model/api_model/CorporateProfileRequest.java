package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.capitaworld.service.loans.model.common.AuditActivityRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * The persistent class for the corporate_profile database table.
 * 
 */
public class CorporateProfileRequest extends AuditorRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String about;

	private String constitution;

	private Double contLiabilityFyAmt;

	private Double contLiabilitySyAmt;

	private Double contLiabilityTyAmt;

	private Integer contLiabilityYear;

	private String contactNo;

	private String email;

	private Integer establishmentYear;

	private String gstin;

	private String industry;

	private AddressRequest administrativeAddress;

	private String organisationName;

	private String pan;

	private AddressRequest registeredAddress;

	private String sector;

	private String subSector;

	private String udhyogAdhaar;

	private Long applicationId;

	private String msmeNumber;

	private Boolean isCgtmseEligible;

	private Long proposalId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date commercialOperationDate;

	private Double promoterContribution;

	private String factoryPremise;

	private String knowHow;

	private String competition;

	public CorporateProfileRequest() {
	}

	public String getAbout() {
		return this.about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getConstitution() {
		return this.constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public Double getContLiabilityFyAmt() {
		return this.contLiabilityFyAmt;
	}

	public void setContLiabilityFyAmt(Double contLiabilityFyAmt) {
		this.contLiabilityFyAmt = contLiabilityFyAmt;
	}

	public Double getContLiabilitySyAmt() {
		return this.contLiabilitySyAmt;
	}

	public void setContLiabilitySyAmt(Double contLiabilitySyAmt) {
		this.contLiabilitySyAmt = contLiabilitySyAmt;
	}

	public Double getContLiabilityTyAmt() {
		return this.contLiabilityTyAmt;
	}

	public void setContLiabilityTyAmt(Double contLiabilityTyAmt) {
		this.contLiabilityTyAmt = contLiabilityTyAmt;
	}

	public Integer getContLiabilityYear() {
		return this.contLiabilityYear;
	}

	public void setContLiabilityYear(Integer contLiabilityYear) {
		this.contLiabilityYear = contLiabilityYear;
	}

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEstablishmentYear() {
		return this.establishmentYear;
	}

	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getGstin() {
		return this.gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOrganisationName() {
		return this.organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getSubSector() {
		return this.subSector;
	}

	public void setSubSector(String subSector) {
		this.subSector = subSector;
	}

	public String getUdhyogAdhaar() {
		return this.udhyogAdhaar;
	}

	public void setUdhyogAdhaar(String udhyogAdhaar) {
		this.udhyogAdhaar = udhyogAdhaar;
	}

	public AddressRequest getAdministrativeAddress() {
		return administrativeAddress;
	}

	public void setAdministrativeAddress(AddressRequest administrativeAddress) {
		this.administrativeAddress = administrativeAddress;
	}

	public AddressRequest getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(AddressRequest registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getMsmeNumber() {
		return msmeNumber;
	}

	public void setMsmeNumber(String msmeNumber) {
		this.msmeNumber = msmeNumber;
	}

	public Boolean getIsCgtmseEligible() {
		return isCgtmseEligible;
	}

	public void setIsCgtmseEligible(Boolean isCgtmseEligible) {
		this.isCgtmseEligible = isCgtmseEligible;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Date getCommercialOperationDate() {
		return commercialOperationDate;
	}

	public void setCommercialOperationDate(Date commercialOperationDate) {
		this.commercialOperationDate = commercialOperationDate;
	}

	public Double getPromoterContribution() {
		return promoterContribution;
	}

	public void setPromoterContribution(Double promoterContribution) {
		this.promoterContribution = promoterContribution;
	}

	public String getFactoryPremise() {
		return factoryPremise;
	}

	public void setFactoryPremise(String factoryPremise) {
		this.factoryPremise = factoryPremise;
	}

	public String getKnowHow() {
		return knowHow;
	}

	public void setKnowHow(String knowHow) {
		this.knowHow = knowHow;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "CorporateProfileRequest [id=" + id + ", about=" + about + ", constitution=" + constitution
				+ ", contLiabilityFyAmt=" + contLiabilityFyAmt + ", contLiabilitySyAmt=" + contLiabilitySyAmt
				+ ", contLiabilityTyAmt=" + contLiabilityTyAmt + ", contLiabilityYear=" + contLiabilityYear
				+ ", contactNo=" + contactNo + ", email=" + email + ", establishmentYear=" + establishmentYear
				+ ", gstin=" + gstin + ", industry=" + industry + ", administrativeAddress=" + administrativeAddress
				+ ", organisationName=" + organisationName + ", pan=" + pan + ", registeredAddress=" + registeredAddress
				+ ", sector=" + sector + ", subSector=" + subSector + ", udhyogAdhaar=" + udhyogAdhaar
				+ ", applicationId=" + applicationId + ", msmeNumber=" + msmeNumber + ", isCgtmseEligible="
				+ isCgtmseEligible + "]";
	}
}