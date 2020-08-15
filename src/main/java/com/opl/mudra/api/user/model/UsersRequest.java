package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String idString;

	private Long clientId;

	private String email;

	private String password;

	private String confirmPassword;

	private String oldPassword;

	private String mobile;

	private UserTypeRequest userType;

	private String username;

	private String name;

	private SignupTypeRequest signUpType;

	private boolean termsAccepted;

	private boolean isActive;

	private String otp;

	private String verificationUrl;

	private boolean otpVarified;

	private boolean emailVarified;

	private Integer otpStatus;

	private Integer cityId;

	private Integer stateId;

	private Integer countryId;

	private Date modifiedDate;

	private Date signUpDate;

	private String browserName;

	private String campaignCode;

	private String campaignType;

	private Long lastAccessApplicantId;

	private Long prodId;

	private Boolean isMsmeUser;

	private Integer pageIndex;

	private Integer size;

	private Integer pageSize;

	private Long userOrgId;

	private String pincode;

	private String city;

	private List<Long> ids;

	private Integer otpVerificationType;

	private Boolean skipLogin;

	private Double loanAmount;

	private Long productId;

	private Long applicationId;

	private String productIdString;

	private String applicationIdString;

	private Integer dataReqType;

	private Long branchId;

	private Long userRoleId;

	private String firstName;

	private String middleName;

	private String lastName;
	
	private Long userId;

	public UsersRequest(Long userId) {
		this.id = userId;
		this.userId = userId;
	}
}
