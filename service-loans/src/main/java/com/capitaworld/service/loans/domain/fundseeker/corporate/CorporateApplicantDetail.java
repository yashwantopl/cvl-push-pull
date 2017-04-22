package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_applicant_details")
public class CorporateApplicantDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="application_id")
	private Long applicationId;

	@Lob
	@Column(name="about_us")
	private String aboutUs;

	@Column(name="administrative_city_id")
	private Long administrativeCityId;

	@Column(name="administrative_country_id")
	private Integer administrativeCountryId;

	@Column(name="administrative_land_mark")
	private String administrativeLandMark;

	@Column(name="administrative_pincode")
	private Long administrativePincode;

	@Column(name="administrative_premise_number")
	private String administrativePremiseNumber;

	@Column(name="administrative_state_id")
	private Integer administrativeStateId;

	@Column(name="administrative_street_name")
	private String administrativeStreetName;

	@Column(name="constitution_id")
	private Integer constitutionId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="establishment_month")
	private Integer establishmentMonth;

	@Column(name="establishment_year")
	private Integer establishmentYear;

	@Column(name="group_name")
	private String groupName;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="key_verical_funding")
	private Long keyVericalFunding;

	private Double latitude;

	private Double longitude;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="organisation_name")
	private String organisationName;

	@Column(name="registered_city_id")
	private Long registeredCityId;

	@Column(name="registered_country_id")
	private Integer registeredCountryId;

	@Column(name="registered_land_mark")
	private String registeredLandMark;

	@Column(name="registered_pincode")
	private Long registeredPincode;

	@Column(name="registered_premise_number")
	private String registeredPremiseNumber;

	@Column(name="registered_state_id")
	private Integer registeredStateId;

	@Column(name="registered_street_name")
	private String registeredStreetName;

	@Column(name="same_as")
	private Boolean sameAs;

	@Column(name="website_address")
	private String websiteAddress;

	public CorporateApplicantDetail() {
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getAboutUs() {
		return this.aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public Long getAdministrativeCityId() {
		return this.administrativeCityId;
	}

	public void setAdministrativeCityId(Long administrativeCityId) {
		this.administrativeCityId = administrativeCityId;
	}

	public Integer getAdministrativeCountryId() {
		return this.administrativeCountryId;
	}

	public void setAdministrativeCountryId(Integer administrativeCountryId) {
		this.administrativeCountryId = administrativeCountryId;
	}

	public String getAdministrativeLandMark() {
		return this.administrativeLandMark;
	}

	public void setAdministrativeLandMark(String administrativeLandMark) {
		this.administrativeLandMark = administrativeLandMark;
	}

	public Long getAdministrativePincode() {
		return this.administrativePincode;
	}

	public void setAdministrativePincode(Long administrativePincode) {
		this.administrativePincode = administrativePincode;
	}

	public String getAdministrativePremiseNumber() {
		return this.administrativePremiseNumber;
	}

	public void setAdministrativePremiseNumber(String administrativePremiseNumber) {
		this.administrativePremiseNumber = administrativePremiseNumber;
	}

	public Integer getAdministrativeStateId() {
		return this.administrativeStateId;
	}

	public void setAdministrativeStateId(Integer administrativeStateId) {
		this.administrativeStateId = administrativeStateId;
	}

	public String getAdministrativeStreetName() {
		return this.administrativeStreetName;
	}

	public void setAdministrativeStreetName(String administrativeStreetName) {
		this.administrativeStreetName = administrativeStreetName;
	}

	public Integer getConstitutionId() {
		return this.constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getEstablishmentMonth() {
		return this.establishmentMonth;
	}

	public void setEstablishmentMonth(Integer establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}

	public Integer getEstablishmentYear() {
		return this.establishmentYear;
	}

	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getKeyVericalFunding() {
		return this.keyVericalFunding;
	}

	public void setKeyVericalFunding(Long keyVericalFunding) {
		this.keyVericalFunding = keyVericalFunding;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOrganisationName() {
		return this.organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Long getRegisteredCityId() {
		return this.registeredCityId;
	}

	public void setRegisteredCityId(Long registeredCityId) {
		this.registeredCityId = registeredCityId;
	}

	public Integer getRegisteredCountryId() {
		return this.registeredCountryId;
	}

	public void setRegisteredCountryId(Integer registeredCountryId) {
		this.registeredCountryId = registeredCountryId;
	}

	public String getRegisteredLandMark() {
		return this.registeredLandMark;
	}

	public void setRegisteredLandMark(String registeredLandMark) {
		this.registeredLandMark = registeredLandMark;
	}

	public Long getRegisteredPincode() {
		return this.registeredPincode;
	}

	public void setRegisteredPincode(Long registeredPincode) {
		this.registeredPincode = registeredPincode;
	}

	public String getRegisteredPremiseNumber() {
		return this.registeredPremiseNumber;
	}

	public void setRegisteredPremiseNumber(String registeredPremiseNumber) {
		this.registeredPremiseNumber = registeredPremiseNumber;
	}

	public Integer getRegisteredStateId() {
		return this.registeredStateId;
	}

	public void setRegisteredStateId(Integer registeredStateId) {
		this.registeredStateId = registeredStateId;
	}

	public String getRegisteredStreetName() {
		return this.registeredStreetName;
	}

	public void setRegisteredStreetName(String registeredStreetName) {
		this.registeredStreetName = registeredStreetName;
	}

	public Boolean getSameAs() {
		return this.sameAs;
	}

	public void setSameAs(Boolean sameAs) {
		this.sameAs = sameAs;
	}

	public String getWebsiteAddress() {
		return this.websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

}