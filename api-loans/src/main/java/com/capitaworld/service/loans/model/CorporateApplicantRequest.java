package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long applicationId;

	private String aboutUs;

	private Integer constitutionId;

	private Integer establishmentMonth;

	private Integer establishmentYear;

	private String groupName;

	private Long keyVericalFunding;

	private Double latitude;

	private Double longitude;

	private String organisationName;

	private Address administrativeAddress;

	private Address registeredAddress;
	private Boolean sameAs;

	private String websiteAddress;

	private List<IndustrySector> industrylist = Collections.emptyList();

	private List<IndustrySector> sectorlist = Collections.emptyList();;
	
	private List<Subsector>   subsectors= Collections.emptyList();;

	public CorporateApplicantRequest() {
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

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<IndustrySector> getIndustrylist() {
		return industrylist;
	}

	public void setIndustrylist(List<IndustrySector> industrylist) {
		this.industrylist = industrylist;
	}

	public List<IndustrySector> getSectorlist() {
		return sectorlist;
	}

	public void setSectorlist(List<IndustrySector> sectorlist) {
		this.sectorlist = sectorlist;
	}

	public Address getAdministrativeAddress() {
		return administrativeAddress;
	}

	public void setAdministrativeAddress(Address administrativeAddress) {
		this.administrativeAddress = administrativeAddress;
	}

	public Address getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(Address registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public List<Subsector> getSubsectors() {
		return subsectors;
	}

	public void setSubsectors(List<Subsector> subsectors) {
		this.subsectors = subsectors;
	}
	

}