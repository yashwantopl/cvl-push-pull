package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the user_organisation_master database table.
 * 
 */
public class UserOrganisationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userOrgId;

	private String organisationName;

	private String organisationCode;

	private String username;

	private String password;

	private String uatUrl;

	private String productionUrl;

	private Boolean isReverseApiActivated;

	private Integer codeLanguage;

	private String config;
	
	private String generalConfig;
	
	private String controlBlockMsme;
	private String controlBlockNtb;
	
	private String generalFields;

	private Long modifiedBy;

	private Timestamp modifiedDate;

	private Long campaignType;

	public UserOrganisationRequest() {
		// Do nothing because of X and Y.
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getOrganisationCode() {
		return organisationCode;
	}

	public void setOrganisationCode(String organisationCode) {
		this.organisationCode = organisationCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUatUrl() {
		return uatUrl;
	}

	public void setUatUrl(String uatUrl) {
		this.uatUrl = uatUrl;
	}

	public String getProductionUrl() {
		return productionUrl;
	}

	public void setProductionUrl(String productionUrl) {
		this.productionUrl = productionUrl;
	}

	public Boolean getIsReverseApiActivated() {
		return isReverseApiActivated;
	}

	public void setIsReverseApiActivated(Boolean isReverseApiActivated) {
		this.isReverseApiActivated = isReverseApiActivated;
	}

	public Integer getCodeLanguage() {
		return codeLanguage;
	}

	public void setCodeLanguage(Integer codeLanguage) {
		this.codeLanguage = codeLanguage;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	

	public String getGeneralFields() {
		return generalFields;
	}

	public void setGeneralFields(String generalFields) {
		this.generalFields = generalFields;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public String getGeneralConfig() {
		return generalConfig;
	}

	public void setGeneralConfig(String generalConfig) {
		this.generalConfig = generalConfig;
	}

	public String getControlBlockMsme() {
		return controlBlockMsme;
	}

	public void setControlBlockMsme(String controlBlockMsme) {
		this.controlBlockMsme = controlBlockMsme;
	}

	public String getControlBlockNtb() {
		return controlBlockNtb;
	}

	public void setControlBlockNtb(String controlBlockNtb) {
		this.controlBlockNtb = controlBlockNtb;
	}

	public Long getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(Long campaignType) {
		this.campaignType = campaignType;
	}

	@Override
	public String toString() {
		return "UserOrganisationRequest [userOrgId=" + userOrgId + ", organisationName=" + organisationName
				+ ", organisationCode=" + organisationCode + ", username=" + username + ", password=" + password
				+ ", uatUrl=" + uatUrl + ", productionUrl=" + productionUrl + ", isReverseApiActivated="
				+ isReverseApiActivated + ", codeLanguage=" + codeLanguage + ", config=" + config + ", generalFields="
				+ generalFields + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + "]";
	}

}