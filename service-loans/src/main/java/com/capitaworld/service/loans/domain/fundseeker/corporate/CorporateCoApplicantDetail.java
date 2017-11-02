package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */
@Entity
@Table(name = "fs_corporate_co_applicant_details")
public class CorporateCoApplicantDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name = "administrative_city_id")
	private Long administrativeCityId;

	@Column(name = "administrative_country_id")
	private Integer administrativeCountryId;

	@Column(name = "administrative_land_mark")
	private String administrativeLandMark;

	@Column(name = "administrative_pincode")
	private Long administrativePincode;

	@Column(name = "administrative_premise_number")
	private String administrativePremiseNumber;

	@Column(name = "administrative_state_id")
	private Integer administrativeStateId;

	@Column(name = "administrative_street_name")
	private String administrativeStreetName;

	@Column(name = "constitution_id")
	private Integer constitutionId;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "organisation_name")
	private String organisationName;

	@Column(name = "registered_city_id")
	private Long registeredCityId;

	@Column(name = "registered_country_id")
	private Integer registeredCountryId;

	@Column(name = "registered_land_mark")
	private String registeredLandMark;

	@Column(name = "registered_pincode")
	private Long registeredPincode;

	@Column(name = "registered_premise_number")
	private String registeredPremiseNumber;

	@Column(name = "registered_state_id")
	private Integer registeredStateId;

	@Column(name = "registered_street_name")
	private String registeredStreetName;

	@Column(name = "same_as")
	private Boolean sameAs;
	
	@Column(name = "aadhar_number")
	private String aadharNumber;

	@Column(name = "pan")
	private String panNo;

	@Column(name = "landline_no")
	private String landlineNo;
	
	@Column(name = "relationship_with_applicant")
	private Integer relationshipWithApplicant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getAdministrativeCityId() {
		return administrativeCityId;
	}

	public void setAdministrativeCityId(Long administrativeCityId) {
		this.administrativeCityId = administrativeCityId;
	}

	public Integer getAdministrativeCountryId() {
		return administrativeCountryId;
	}

	public void setAdministrativeCountryId(Integer administrativeCountryId) {
		this.administrativeCountryId = administrativeCountryId;
	}

	public String getAdministrativeLandMark() {
		return administrativeLandMark;
	}

	public void setAdministrativeLandMark(String administrativeLandMark) {
		this.administrativeLandMark = administrativeLandMark;
	}

	public Long getAdministrativePincode() {
		return administrativePincode;
	}

	public void setAdministrativePincode(Long administrativePincode) {
		this.administrativePincode = administrativePincode;
	}

	public String getAdministrativePremiseNumber() {
		return administrativePremiseNumber;
	}

	public void setAdministrativePremiseNumber(String administrativePremiseNumber) {
		this.administrativePremiseNumber = administrativePremiseNumber;
	}

	public Integer getAdministrativeStateId() {
		return administrativeStateId;
	}

	public void setAdministrativeStateId(Integer administrativeStateId) {
		this.administrativeStateId = administrativeStateId;
	}

	public String getAdministrativeStreetName() {
		return administrativeStreetName;
	}

	public void setAdministrativeStreetName(String administrativeStreetName) {
		this.administrativeStreetName = administrativeStreetName;
	}

	public Integer getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Long getRegisteredCityId() {
		return registeredCityId;
	}

	public void setRegisteredCityId(Long registeredCityId) {
		this.registeredCityId = registeredCityId;
	}

	public Integer getRegisteredCountryId() {
		return registeredCountryId;
	}

	public void setRegisteredCountryId(Integer registeredCountryId) {
		this.registeredCountryId = registeredCountryId;
	}

	public String getRegisteredLandMark() {
		return registeredLandMark;
	}

	public void setRegisteredLandMark(String registeredLandMark) {
		this.registeredLandMark = registeredLandMark;
	}

	public Long getRegisteredPincode() {
		return registeredPincode;
	}

	public void setRegisteredPincode(Long registeredPincode) {
		this.registeredPincode = registeredPincode;
	}

	public String getRegisteredPremiseNumber() {
		return registeredPremiseNumber;
	}

	public void setRegisteredPremiseNumber(String registeredPremiseNumber) {
		this.registeredPremiseNumber = registeredPremiseNumber;
	}

	public Integer getRegisteredStateId() {
		return registeredStateId;
	}

	public void setRegisteredStateId(Integer registeredStateId) {
		this.registeredStateId = registeredStateId;
	}

	public String getRegisteredStreetName() {
		return registeredStreetName;
	}

	public void setRegisteredStreetName(String registeredStreetName) {
		this.registeredStreetName = registeredStreetName;
	}

	public Boolean getSameAs() {
		return sameAs;
	}

	public void setSameAs(Boolean sameAs) {
		this.sameAs = sameAs;
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

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	
	
	
}