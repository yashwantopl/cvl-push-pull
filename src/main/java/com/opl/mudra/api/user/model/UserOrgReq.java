package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the user_organisation_master database table.
 * 
 */
public class UserOrgReq implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userOrgId;

	private String organisationCode;

	private Long campaignType;

	public UserOrgReq() {
		// Do nothing because of X and Y.
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getOrganisationCode() {
		return organisationCode;
	}

	public void setOrganisationCode(String organisationCode) {
		this.organisationCode = organisationCode;
	}

	public Long getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(Long campaignType) {
		this.campaignType = campaignType;
	}

}