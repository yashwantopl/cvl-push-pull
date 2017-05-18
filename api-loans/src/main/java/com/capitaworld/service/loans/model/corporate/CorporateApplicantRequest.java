package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private String aboutUs;

	private String panNo;

	private Integer constitutionId;

	private Integer establishmentMonth;

	private Integer establishmentYear;

	private String groupName;

	private Long keyVericalFunding;

	private Double latitude;

	private Double longitude;

	private String organisationName;

	private Address firstAddress;

	private Address secondAddress;

	private Boolean sameAs;

	private String websiteAddress;

	private String landlineNo;

	private List<Long> industrylist = Collections.emptyList();

	private List<Long> sectorlist = Collections.emptyList();

	private List<Long> subsectors = Collections.emptyList();

	public CorporateApplicantRequest() {
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
		return sameAs;
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

	public Address getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(Address secondAddress) {
		this.secondAddress = secondAddress;
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

}