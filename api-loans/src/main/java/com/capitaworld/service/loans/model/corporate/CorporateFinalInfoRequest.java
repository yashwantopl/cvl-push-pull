package com.capitaworld.service.loans.model.corporate;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateFinalInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long clientId;

    private Long applicationId;

    private Long proposalMappingId;

    private Address firstAddress;

    private Address secondAddress;

    private Boolean sameAs;

    private Boolean isFinalDetailsFilled;

    private String aadhar;

    private Integer creditRatingId;

    private Double contLiabilityFyAmt;
    private Double contLiabilitySyAmt;
    private Double contLiabilityTyAmt;
    private Integer contLiabilityYear;

    private Boolean notApplicable;

    private String aboutUs;

    private Double totalCostOfEstimate;
    private Double totalMeansOfFinance;
    private Double sharePriceFace;
    private Double sharePriceMarket;
    private Double totalCollateralDetails;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date establishmentDate;

    private String organisationName;
    private Integer constitutionId;
    private String panNo;
    private String landlineNo;
    private String remarks;

    private Long administrativeCityId;
    private Integer administrativeCountryId;
    private String administrativeLandMark;
    private Long administrativePincode;
    private String administrativePremiseNumber;
    private Integer administrativeStateId;
    private String administrativeStreetName;
    private Integer establishmentMonth;
    private Integer establishmentYear;
    private String groupName;
    private Boolean isActive;
    private Long keyVericalFunding;
    private Double latitude;
    private Double longitude;
    private Long registeredCityId;
    private Integer registeredCountryId;
    private String registeredLandMark;
    private Long registeredPincode;
    private String registeredPremiseNumber;
    private Integer registeredStateId;
    private String registeredStreetName;
    private String websiteAddress;
    private String gstIn;
    private String email;
    private Long keyVerticalSector;
    private Long keyVerticalSubsector;
    private String msmeRegistrationNumber;
    private Long administrativeDistMappingId;
    private Long registeredDistMappingId;
    private Long environmentalImpactId;

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}

	public Boolean getIsFinalDetailsFilled() {
		return isFinalDetailsFilled;
	}

	public void setIsFinalDetailsFilled(Boolean isFinalDetailsFilled) {
		this.isFinalDetailsFilled = isFinalDetailsFilled;
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

	public Integer getEstablishmentMonth() {
		return establishmentMonth;
	}

	public void setEstablishmentMonth(Integer establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}

	public Integer getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getKeyVericalFunding() {
		return keyVericalFunding;
	}

	public void setKeyVericalFunding(Long keyVericalFunding) {
		this.keyVericalFunding = keyVericalFunding;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
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

	public String getWebsiteAddress() {
		return websiteAddress;
	}

	public void setWebsiteAddress(String websiteAddress) {
		this.websiteAddress = websiteAddress;
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

	public Long getEnvironmentalImpactId() {
		return environmentalImpactId;
	}

	public void setEnvironmentalImpactId(Long environmentalImpactId) {
		this.environmentalImpactId = environmentalImpactId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getSameAs() {
        return sameAs;
    }

    public void setSameAs(Boolean sameAs) {
        this.sameAs = sameAs;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getFinalDetailsFilled() {
        return isFinalDetailsFilled;
    }

    public void setFinalDetailsFilled(Boolean finalDetailsFilled) {
        isFinalDetailsFilled = finalDetailsFilled;
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

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
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

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public Integer getConstitutionId() {
        return constitutionId;
    }

    public void setConstitutionId(Integer constitutionId) {
        this.constitutionId = constitutionId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
	public String toString() {
		return "CorporateFinalInfoRequest [id=" + id + ", userId=" + userId + ", clientId=" + clientId
				+ ", applicationId=" + applicationId + ", proposalMappingId=" + proposalMappingId + ", firstAddress="
				+ firstAddress + ", secondAddress=" + secondAddress + ", sameAs=" + sameAs + ", isFinalDetailsFilled="
				+ isFinalDetailsFilled + ", aadhar=" + aadhar + ", creditRatingId=" + creditRatingId
				+ ", contLiabilityFyAmt=" + contLiabilityFyAmt + ", contLiabilitySyAmt=" + contLiabilitySyAmt
				+ ", contLiabilityTyAmt=" + contLiabilityTyAmt + ", contLiabilityYear=" + contLiabilityYear
				+ ", notApplicable=" + notApplicable + ", aboutUs=" + aboutUs + ", totalCostOfEstimate="
				+ totalCostOfEstimate + ", totalMeansOfFinance=" + totalMeansOfFinance + ", sharePriceFace="
				+ sharePriceFace + ", sharePriceMarket=" + sharePriceMarket + ", totalCollateralDetails="
				+ totalCollateralDetails + ", establishmentDate=" + establishmentDate + ", organisationName="
				+ organisationName + ", constitutionId=" + constitutionId + ", panNo=" + panNo + ", landlineNo="
				+ landlineNo + ", administrativeCityId=" + administrativeCityId + ", administrativeCountryId="
				+ administrativeCountryId + ", administrativeLandMark=" + administrativeLandMark
				+ ", administrativePincode=" + administrativePincode + ", administrativePremiseNumber="
				+ administrativePremiseNumber + ", administrativeStateId=" + administrativeStateId
				+ ", administrativeStreetName=" + administrativeStreetName + ", establishmentMonth="
				+ establishmentMonth + ", establishmentYear=" + establishmentYear + ", groupName=" + groupName
				+ ", isActive=" + isActive + ", keyVericalFunding=" + keyVericalFunding + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", registeredCityId=" + registeredCityId + ", registeredCountryId="
				+ registeredCountryId + ", registeredLandMark=" + registeredLandMark + ", registeredPincode="
				+ registeredPincode + ", registeredPremiseNumber=" + registeredPremiseNumber + ", registeredStateId="
				+ registeredStateId + ", registeredStreetName=" + registeredStreetName + ", websiteAddress="
				+ websiteAddress + ", gstIn=" + gstIn + ", email=" + email + ", keyVerticalSector=" + keyVerticalSector
				+ ", keyVerticalSubsector=" + keyVerticalSubsector + ", msmeRegistrationNumber="
				+ msmeRegistrationNumber + ", administrativeDistMappingId=" + administrativeDistMappingId
				+ ", registeredDistMappingId=" + registeredDistMappingId + ", environmentalImpactId="
				+ environmentalImpactId + "]";
	}

}
