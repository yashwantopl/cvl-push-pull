package com.opl.mudra.api.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.model.Address;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long clientId;
	
	private Long applicationId;

	//private String aboutUs;

	private String panNo;

	private Integer constitutionId;

	private Integer establishmentMonth;

	private Integer establishmentYear;

	//private String groupName;

	private Long keyVericalFunding;

	private Double latitude;

	private Double longitude;

	private String organisationName;

	private Address firstAddress;

	private String websiteAddress;

	private String landlineNo;
	
	//private List<CorporateCoApplicantRequest> coApplicants = Collections.emptyList();

	private List<Long> industrylist = Collections.emptyList();

	private List<Long> sectorlist = Collections.emptyList();

	private List<Long> subsectors = Collections.emptyList();
	
	private Boolean isApplicantDetailsFilled;
	
	private String detailsFilledCount;
	
	private Long userId;
	
	private Long keyVerticalSector;
 	
	private Long keyVerticalSubsector;

	private String gstIn;
	private String email;
	
	private String companyCIN;
	private Long environmentalImpactId;
	
	
	///For Uniform Product
	private Double turnOverPrevFinYear;
    
    private Double turnOverCurrFinYearTillMonth;
    
    private Double profitCurrFinYear;
    
    private Double grossSales;
    
    
    private Boolean isMultiGST;
    
    private Date loanApplicationCreatedDate;
    
    private Long registeredCityId;

	private Integer registeredCountryId;

	private String registeredLandMark;

	private Long registeredPincode;

	private String registeredPremiseNumber;

	private Integer registeredStateId;

	private String registeredStreetName;
    
    //private Map<String, Object> incomeDetails;
	private LinkedHashMap<String, Object> incomeDetails;
	
    
    private Date dob;
    
    private String premiseNo;
    
    private String  streetName;
    
    private String  landmark;
    
    private Integer CountryId;
    
    private Integer stateId;
    
    private Integer cityId;
    
    private Integer pincode;
    
    private String  pan;
    
    private Long distId;
    
    private Long registeredDistMappingId;
    
    private Integer employmentGeneration;
    
	public Integer getEmploymentGeneration() {
		return employmentGeneration;
	}

	public void setEmploymentGeneration(Integer employmentGeneration) {
		this.employmentGeneration = employmentGeneration;
	}

	/**
	 * @return the isMultiGST
	 */
	public Boolean getIsMultiGST() {
		return isMultiGST;
	}

	/**
	 * @param isMultiGST the isMultiGST to set
	 */
	public void setIsMultiGST(Boolean isMultiGST) {
		this.isMultiGST = isMultiGST;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CorporateApplicantRequest() {
		// Do nothing because of X and Y.
	}

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



	public Integer getConstitutionId() {
		return this.constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
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

	public String getOrganisationName() {
		return this.organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}



	public String getWebsiteAddress() {
		return this.websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
	}

	public List<Long> getIndustrylist() {
		return industrylist;
	}

	public void setIndustrylist(List<Long> industrylist) {
		this.industrylist = industrylist;
	}

	public List<Long> getSectorlist() {
		return sectorlist;
	}

	public void setSectorlist(List<Long> sectorlist) {
		this.sectorlist = sectorlist;
	}

	public Address getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}



	public List<Long> getSubsectors() {
		return subsectors;
	}

	public void setSubsectors(List<Long> subsectors) {
		this.subsectors = subsectors;
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

	public Boolean getIsApplicantDetailsFilled() {
		return isApplicantDetailsFilled;
	}

	public void setIsApplicantDetailsFilled(Boolean isApplicantDetailsFilled) {
		this.isApplicantDetailsFilled = isApplicantDetailsFilled;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
	}


	public Long getKeyVerticalSector() {
		return keyVerticalSector;
	}

	public void setKeyVerticalSector(Long keyVerticalSector) {
		this.keyVerticalSector = keyVerticalSector;
	}

	public Long getKeyVerticalSubsector() {
		return keyVerticalSubsector;
	}

	public void setKeyVerticalSubsector(Long keyVerticalSubsector) {
		this.keyVerticalSubsector = keyVerticalSubsector;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getEnvironmentalImpactId() {
		return environmentalImpactId;
	}

	public void setEnvironmentalImpactId(Long environmentalImpactId) {
		this.environmentalImpactId = environmentalImpactId;
	}

	/**
	 * @return the companyCIN
	 */
	public String getCompanyCIN() {
		return companyCIN;
	}

	/**
	 * @param companyCIN the companyCIN to set
	 */
	public void setCompanyCIN(String companyCIN) {
		this.companyCIN = companyCIN;
	}

	public Double getTurnOverPrevFinYear() {
		return turnOverPrevFinYear;
	}

	public void setTurnOverPrevFinYear(Double turnOverPrevFinYear) {
		this.turnOverPrevFinYear = turnOverPrevFinYear;
	}

	public Double getTurnOverCurrFinYearTillMonth() {
		return turnOverCurrFinYearTillMonth;
	}

	public void setTurnOverCurrFinYearTillMonth(Double turnOverCurrFinYearTillMonth) {
		this.turnOverCurrFinYearTillMonth = turnOverCurrFinYearTillMonth;
	}

	public Double getProfitCurrFinYear() {
		return profitCurrFinYear;
	}

	public void setProfitCurrFinYear(Double profitCurrFinYear) {
		this.profitCurrFinYear = profitCurrFinYear;
	}

	public Double getGrossSales() {
		return grossSales;
	}

	public void setGrossSales(Double grossSales) {
		this.grossSales = grossSales;
	}
	
	public Date getLoanApplicationCreatedDate() {
		return loanApplicationCreatedDate;
	}
	
	public void setLoanApplicationCreatedDate(Date loanApplicationCreatedDate) {
		this.loanApplicationCreatedDate = loanApplicationCreatedDate;
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
	
//	public Map<String, Object> getIncomeDetails() {
//		return incomeDetails;
//	}
//
//	public void setIncomeDetails(Map<String, Object> incomeDetails) {
//		this.incomeDetails = incomeDetails;
//	}
	
	

	public Date getDob() {
		return dob;
	}

	public LinkedHashMap<String, Object> getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(LinkedHashMap<String, Object> incomeDetails) {
		this.incomeDetails = incomeDetails;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public Integer getCountryId() {
		return CountryId;
	}

	public void setCountryId(Integer countryId) {
		CountryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public Long getRegisteredDistMappingId() {
		return registeredDistMappingId;
	}

	public void setRegisteredDistMappingId(Long registeredDistMappingId) {
		this.registeredDistMappingId = registeredDistMappingId;
	}

	@Override
	public String toString() {
		return "CorporateApplicantRequest [id=" + id + ", clientId=" + clientId + ", applicationId=" + applicationId
				+ ", panNo=" + panNo + ", constitutionId=" + constitutionId + ", establishmentMonth="
				+ establishmentMonth + ", establishmentYear=" + establishmentYear + ", keyVericalFunding="
				+ keyVericalFunding + ", latitude=" + latitude + ", longitude=" + longitude + ", organisationName="
				+ organisationName + ", firstAddress=" + firstAddress + ", websiteAddress=" + websiteAddress
				+ ", landlineNo=" + landlineNo + ", industrylist=" + industrylist + ", sectorlist=" + sectorlist
				+ ", subsectors=" + subsectors + ", isApplicantDetailsFilled=" + isApplicantDetailsFilled
				+ ", detailsFilledCount=" + detailsFilledCount + ", userId=" + userId + ", keyVerticalSector="
				+ keyVerticalSector + ", keyVerticalSubsector=" + keyVerticalSubsector + ", gstIn=" + gstIn + ", email="
				+ email + ", companyCIN=" + companyCIN + ", environmentalImpactId=" + environmentalImpactId
				+ ", turnOverPrevFinYear=" + turnOverPrevFinYear + ", turnOverCurrFinYearTillMonth="
				+ turnOverCurrFinYearTillMonth + ", profitCurrFinYear=" + profitCurrFinYear + ", grossSales="
				+ grossSales + ", isMultiGST=" + isMultiGST + ", loanApplicationCreatedDate="
				+ loanApplicationCreatedDate + "]";
	}

	
}