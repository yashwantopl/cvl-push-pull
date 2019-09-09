package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author rohit.chaudhary
 *
 */
public class PreSanctionVisitRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicantId;

	private Long applicationProposalMapping;

	private Long coApplicantDetailId;

	private Long createdBy;

	private Date createdDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date visitDate;

	private String visitName;

	private String visitDesignation;

	private Boolean isVisited;

	private String premiseNo;

	private String streetName;

	private String landmark;

	private String pincode;

	private Long cityId;

	private Long stateId;

	private Long countryId;

	private String mobileNumber;

	private String contactedPersonName;

	private String applicantImpression;

	private String officerObservations;

	private Boolean isApplicantOfficial;

	public PreSanctionVisitRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Long getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(Long applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}

	public Long getCoApplicantDetailId() {
		return coApplicantDetailId;
	}

	public void setCoApplicantDetailId(Long coApplicantDetailId) {
		this.coApplicantDetailId = coApplicantDetailId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public String getVisitDesignation() {
		return visitDesignation;
	}

	public void setVisitDesignation(String visitDesignation) {
		this.visitDesignation = visitDesignation;
	}

	public Boolean getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(Boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getPremiseNo() {
		return premiseNo;
	}

	public void setPremiseNo(String premiseNo) {
		this.premiseNo = premiseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getApplicantImpression() {
		return applicantImpression;
	}

	public void setApplicantImpression(String applicantImpression) {
		this.applicantImpression = applicantImpression;
	}

	public String getOfficerObservations() {
		return officerObservations;
	}

	public void setOfficerObservations(String officerObservations) {
		this.officerObservations = officerObservations;
	}

	public Boolean getIsApplicantOfficial() {
		return isApplicantOfficial;
	}

	public void setIsApplicantOfficial(Boolean isApplicantOfficial) {
		this.isApplicantOfficial = isApplicantOfficial;
	}

	public String getContactedPersonName() {
		return contactedPersonName;
	}

	public void setContactedPersonName(String contactedPersonName) {
		this.contactedPersonName = contactedPersonName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}


}
