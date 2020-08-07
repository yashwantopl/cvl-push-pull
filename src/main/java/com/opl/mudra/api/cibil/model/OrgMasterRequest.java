package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author harshit
 */
public class OrgMasterRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String organisationName;

	private String code;
	
	private String msmeApiUrl;
	
	private String username;

	private String password;
	
	private Boolean isMsmeApiActive;
	
	private Date modifiedDate;
	
	private String token;
	
    private String msmeConfig;
    
 // Report data related configurations
    private Boolean isScoreEnable;
    private Boolean isCmrEnable;
    private Boolean isMainDirScoreEnable;
    private Boolean isLoansEnable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsmeApiUrl() {
		return msmeApiUrl;
	}

	public void setMsmeApiUrl(String msmeApiUrl) {
		this.msmeApiUrl = msmeApiUrl;
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

	public Boolean getIsMsmeApiActive() {
		return isMsmeApiActive;
	}

	public void setIsMsmeApiActive(Boolean isMsmeApiActive) {
		this.isMsmeApiActive = isMsmeApiActive;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMsmeConfig() {
		return msmeConfig;
	}

	public void setMsmeConfig(String msmeConfig) {
		this.msmeConfig = msmeConfig;
	}

	public Boolean getIsScoreEnable() {
		return isScoreEnable;
	}

	public void setIsScoreEnable(Boolean isScoreEnable) {
		this.isScoreEnable = isScoreEnable;
	}

	public Boolean getIsCmrEnable() {
		return isCmrEnable;
	}

	public void setIsCmrEnable(Boolean isCmrEnable) {
		this.isCmrEnable = isCmrEnable;
	}

	public Boolean getIsMainDirScoreEnable() {
		return isMainDirScoreEnable;
	}

	public void setIsMainDirScoreEnable(Boolean isMainDirScoreEnable) {
		this.isMainDirScoreEnable = isMainDirScoreEnable;
	}

	public Boolean getIsLoansEnable() {
		return isLoansEnable;
	}

	public void setIsLoansEnable(Boolean isLoansEnable) {
		this.isLoansEnable = isLoansEnable;
	}
	
}
