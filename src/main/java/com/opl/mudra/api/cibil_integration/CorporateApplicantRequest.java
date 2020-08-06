package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the fs_corporate_applicant_details database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long applicationId;

	private String panNo;

	private Integer constitutionId;

	private String organisationName;

	private Address firstAddress;

    private Long orgId;
    
    private boolean isNbfcUser;
    
    private String landlineNo;
    
    private String xmlResponseExternal;
    
    private Date loanApplicationCreatedDate;
    
    private List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestsList = Collections.emptyList();

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

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public Integer getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(Integer constitutionId) {
		this.constitutionId = constitutionId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Address getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isNbfcUser() {
		return isNbfcUser;
	}

	public void setNbfcUser(boolean isNbfcUser) {
		this.isNbfcUser = isNbfcUser;
	}

	public String getXmlResponseExternal() {
		return xmlResponseExternal;
	}

	public void setXmlResponseExternal(String xmlResponseExternal) {
		this.xmlResponseExternal = xmlResponseExternal;
	}

	public Date getLoanApplicationCreatedDate() {
		return loanApplicationCreatedDate;
	}

	public void setLoanApplicationCreatedDate(Date loanApplicationCreatedDate) {
		this.loanApplicationCreatedDate = loanApplicationCreatedDate;
	}

	public List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetailRequestsList() {
		return directorBackgroundDetailRequestsList;
	}

	public void setDirectorBackgroundDetailRequestsList(
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestsList) {
		this.directorBackgroundDetailRequestsList = directorBackgroundDetailRequestsList;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}
}