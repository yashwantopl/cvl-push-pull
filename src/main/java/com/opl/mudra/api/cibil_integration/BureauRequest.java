package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauRequest extends AuthRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private Long userId;
	private Long id;
	private Long clientId;
	private String pan;
	private Long orgId;
	private Integer dpdDays;
	private Integer provider;
	private Integer businessTypeId;
	private Long coApplicantId;
	private String xmlResponseExternal;
	private Object dataInput;
	private boolean isNbfcUser;
	private Date loanApplicationCreatedDate;
	private CorporateApplicantRequest applicantRequest;

	// Report data related configurations
    private Boolean isLoansEnable;

	public BureauRequest() {// Do nothing because of X and Y.

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

	/*public Boolean getIsBankSpecificRequest() {
		return isBankSpecificRequest;
	}

	public void setIsBankSpecificRequest(Boolean isBankSpecificRequest) {
		this.isBankSpecificRequest = isBankSpecificRequest;
	}*/

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

	public Date getLoanApplicationCreatedDate() {
		return loanApplicationCreatedDate;
	}

	public void setLoanApplicationCreatedDate(Date loanApplicationCreatedDate) {
		this.loanApplicationCreatedDate = loanApplicationCreatedDate;
	}

	public void setNbfcUser(boolean isNbfcUser) {
		this.isNbfcUser = isNbfcUser;
	}

	public CorporateApplicantRequest getApplicantRequest() {
		return applicantRequest;
	}

	public void setApplicantRequest(CorporateApplicantRequest applicantRequest) {
		this.applicantRequest = applicantRequest;
	}

	public Boolean getIsLoansEnable() {
		return isLoansEnable;
	}

	public void setIsLoansEnable(Boolean isLoansEnable) {
		this.isLoansEnable = isLoansEnable;
	}

}
