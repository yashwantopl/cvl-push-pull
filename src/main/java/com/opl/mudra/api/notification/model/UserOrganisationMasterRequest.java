package com.opl.mudra.api.notification.model;

import java.io.Serializable;

/**
 * @author maaz.shaikh
 *
 */
public class UserOrganisationMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userOrgId;

	private String organisationName;

	private String organisationCode;

	private boolean isActive;

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserOrganisationMaster [userOrgId=" + userOrgId + ", organisationName=" + organisationName
				+ ", organisationCode=" + organisationCode + ", isActive=" + isActive + "]";
	}
	 

	

}