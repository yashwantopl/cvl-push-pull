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
@Table(name = "fs_corporate_applicant_details")
public class CorporateApplicantDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Lob
	@Column(name = "about_us")
	private String aboutUs;

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

	@Column(name = "establishment_month")
	private Integer establishmentMonth;

	@Column(name = "establishment_year")
	private Integer establishmentYear;

	@Column(name = "group_name")
	private String groupName;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "key_verical_funding")
	private Long keyVericalFunding;

	private Double latitude;

	private Double longitude;

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

	@Column(name = "website_address")
	private String websiteAddress;

	@Column(name = "pan")
	private String panNo;

	@Column(name = "gstin")
	private String gstIn;

	@Column(name = "email")
	private String email;

	@Column(name = "landline_no")
	private String landlineNo;

	@Column(name = "key_vertical_sector")
	private Long keyVerticalSector;
	
	@Column(name = "key_vertical_subsector")
	private Long keyVerticalSubsector;

	@Column(name = "aadhar")
	private String aadhar;

	@Column(name = "credit_rating_id")
	private Integer creditRatingId;

	@Column(name = "cont_liability_fy_amt")
	private Double contLiabilityFyAmt;

	@Column(name = "cont_liability_sy_amt")
	private Double contLiabilitySyAmt;

	@Column(name = "cont_liability_ty_amt")
	private Double contLiabilityTyAmt;

	@Column(name = "cont_liability_year")
	private Integer contLiabilityYear;

	@Column(name = "not_applicable")
	private Boolean notApplicable;

	@Column(name = "total_cost_of_estimate")
	private Double totalCostOfEstimate;

	@Column(name = "total_means_of_finance")
	private Double totalMeansOfFinance;

	@Column(name = "share_price_face_value")
	private Double sharePriceFace;

	@Column(name = "share_price_market_value")
	private Double sharePriceMarket;

	@Column(name = "collateral_security_amt_total")
	private Double totalCollateralDetails;

	@Column(name= "msme_registration_number")
	private String msmeRegistrationNumber;

	@Column(name= "administrative_dist_mapping_id")
	private Long administrativeDistMappingId;

	@Column(name= "registered_dist_mapping_id")
	private Long registeredDistMappingId;

	public CorporateApplicantDetail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public Integer getCreditRatingId() {
		return creditRatingId;
	}

	public void setCreditRatingId(Integer creditRatingId) {
		this.creditRatingId = creditRatingId;
	}

	public Integer getContLiabilityYear() {
		return contLiabilityYear;
	}

	public void setContLiabilityYear(Integer contLiabilityYear) {
		this.contLiabilityYear = contLiabilityYear;
	}


	public Boolean getNotApplicable() {
		return notApplicable;
	}

	public void setNotApplicable(Boolean notApplicable) {
		this.notApplicable = notApplicable;
	}

	public Double getContLiabilityFyAmt() {
		return contLiabilityFyAmt;
	}

	public void setContLiabilityFyAmt(Double contLiabilityFyAmt) {
		this.contLiabilityFyAmt = contLiabilityFyAmt;
	}

	public Double getContLiabilitySyAmt() {
		return contLiabilitySyAmt;
	}

	public void setContLiabilitySyAmt(Double contLiabilitySyAmt) {
		this.contLiabilitySyAmt = contLiabilitySyAmt;
	}

	public Double getContLiabilityTyAmt() {
		return contLiabilityTyAmt;
	}

	public void setContLiabilityTyAmt(Double contLiabilityTyAmt) {
		this.contLiabilityTyAmt = contLiabilityTyAmt;
	}

	public Double getTotalCostOfEstimate() {
		return totalCostOfEstimate;
	}

	public void setTotalCostOfEstimate(Double totalCostOfEstimate) {
		this.totalCostOfEstimate = totalCostOfEstimate;
	}

	public Double getTotalMeansOfFinance() {
		return totalMeansOfFinance;
	}

	public void setTotalMeansOfFinance(Double totalMeansOfFinance) {
		this.totalMeansOfFinance = totalMeansOfFinance;
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

	public Double getTotalCollateralDetails() {
		return totalCollateralDetails;
	}

	public void setTotalCollateralDetails(Double totalCollateralDetails) {
		this.totalCollateralDetails = totalCollateralDetails;
	}

	public String getMsmeRegistrationNumber() {
		return msmeRegistrationNumber;
	}

	public void setMsmeRegistrationNumber(String msmeRegistrationNumber) {
		this.msmeRegistrationNumber = msmeRegistrationNumber;
	}

	public Long getAdministrativeDistMappingId() {
		return administrativeDistMappingId;
	}

	public void setAdministrativeDistMappingId(Long administrativeDistMappingId) {
		this.administrativeDistMappingId = administrativeDistMappingId;
	}

	public Long getRegisteredDistMappingId() {
		return registeredDistMappingId;
	}

	public void setRegisteredDistMappingId(Long registeredDistMappingId) {
		this.registeredDistMappingId = registeredDistMappingId;
	}
}