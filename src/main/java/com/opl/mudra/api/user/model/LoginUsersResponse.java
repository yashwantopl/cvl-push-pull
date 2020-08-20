package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUsersResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9049312792155578603L;
	private String access_token;
	private String token_type;
	private String expires_in;
	private String refresh_token;
	private String scope;
	private Long userType;
	private String message;
	private Integer status;
	private String email;
	private List<String> campaignCode;
	private Integer loginToken;
	private Long applicationId;
	private Boolean isFpUserFillProfile;
	private Boolean isSpUserFillProfile;
	private Boolean isNpUserFillProfile;
	private Long userOrgId;
	private Long isEmailVerified;
	private Long userRoleId;
	private Boolean isPasswordChanged;
	private Integer loginCounter ; 
	private Long userBranchId;
	private Integer userUrlType;
	private Integer businessType;
	private Boolean coOriginationUser;
	private String mobile;

}
