package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private Long userId;
	private Long id;
	private Long applicationId;
	private Long clientId;
	private String pan;
	private List<String> pans;
	private Long orgId;
	private Integer dpdDays;
	private Boolean isBankSpecificRequest;
	private Integer provider;
	private Integer businessTypeId;
	private Long coApplicantId;
	private String xmlResponseExternal;
	private Object dataInput;
	private boolean isNbfcUser;
	private Long applicantId;


	public CibilRequest() {// Do nothing because of X and Y.

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicantId = applicationId;
		this.applicationId = applicationId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	

	public Integer getDpdDays() {
		return dpdDays;
	}

	public void setDpdDays(Integer dpdDays) {
		this.dpdDays = dpdDays;
	}

	public Boolean getIsBankSpecificRequest() {
		return isBankSpecificRequest;
	}

	public void setIsBankSpecificRequest(Boolean isBankSpecificRequest) {
		this.isBankSpecificRequest = isBankSpecificRequest;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getCoApplicantId() {
		return coApplicantId;
	}

	public void setCoApplicantId(Long coApplicantId) {
		this.coApplicantId = coApplicantId;
	}

	public Object getDataInput() {
		return dataInput;
	}

	public void setDataInput(Object dataInput) {
		this.dataInput = dataInput;
	}

	public boolean getIsNbfcUser() {
		return isNbfcUser;
	}

	public void setIsNbfcUser(boolean isNbfcUser) {
		this.isNbfcUser = isNbfcUser;
	}
	public String getXmlResponseExternal() {
		return xmlResponseExternal;
	}

	public void setXmlResponseExternal(String xmlResponseExternal) {
		this.xmlResponseExternal = xmlResponseExternal;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicationId = applicantId;
		this.applicantId = applicantId;
	}

	public List<String> getPans() {
		return pans;
	}

	public void setPans(List<String> pans) {
		this.pans = pans;
	}

	@Override
	public String toString() {
		return "CibilRequest [userId=" + userId + ", id=" + id + ", applicationId=" + applicationId + ", clientId="
				+ clientId + ", pan=" + pan + ", orgId=" + orgId + ", dpdDays=" + dpdDays + "]";
	}
}
