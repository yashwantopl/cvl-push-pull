package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private Date updatedDate;
	private Boolean isActive;
	private String pan;

	
	

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
}
