package com.opl.mudra.api.user.model.loans;;

public class CorporateApplicantDetail {

	private Long id;

	private LoanApplicationMaster applicationId;

	private String organisationName;

	private Long registeredCityId;

	private Integer registeredCountryId;

	private String registeredLandMark;

	private Long registeredPincode;

	private String registeredPremiseNumber;

	private Integer registeredStateId;

	private String registeredStreetName;

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

	@Override
	public String toString() {
		return "CorporateApplicantDetail [id=" + id + ", applicationId=" + applicationId + ", organisationName="
				+ organisationName + ", registeredCityId=" + registeredCityId + ", registeredCountryId="
				+ registeredCountryId + ", registeredLandMark=" + registeredLandMark + ", registeredPincode="
				+ registeredPincode + ", registeredPremiseNumber=" + registeredPremiseNumber + ", registeredStateId="
				+ registeredStateId + ", registeredStreetName=" + registeredStreetName + "]";
	}

	
}