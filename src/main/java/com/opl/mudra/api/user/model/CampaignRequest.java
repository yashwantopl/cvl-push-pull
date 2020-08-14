package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CampaignRequest implements Serializable {

	private static final long serialVersionUID = 6232712419796541868L;
	private Long userId;// Required
	private String code;// Required
	private String type;
	private String name;
	private String mobile;
	private String email;
	private String organisationName;
	private Date createdDate;
	private Long applicationId;
	private Long branchId;

	
	

	/**
	 * @param userId
	 * @param code
	 * @param type
	 * @param name
	 * @param mobile
	 * @param email
	 */
	public CampaignRequest(Long userId, String code, String type, String name, String mobile, String email) {
		super();
		this.userId = userId;
		this.code = code;
		this.type = type;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}
	
	public CampaignRequest(Long userId, String code, String type, String name, String mobile, String email, String orgName, Date createdDate) {
		super();
		this.userId = userId;
		this.code = code;
		this.type = type;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.organisationName = orgName;
		this.createdDate = createdDate;
//		this.applicationId = appId;
	}

	public CampaignRequest() {

	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

}
