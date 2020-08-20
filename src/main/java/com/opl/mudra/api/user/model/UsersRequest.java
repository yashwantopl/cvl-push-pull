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

	private Long proposalMappingId;

	private Long applicationId;

	private String productIdString;

	private String applicationIdString;

	private Integer dataReqType;

	private Long branchId;
	
	private String branchName;
	
	private String branchCode;
	
	private String branchEmailId;
	
	private String roleName;
	
	private Boolean isLocked;
	
	private Boolean isLockedByAdmin;
	
	private Long userId;
	
	private Long userRoleId;
	
	private String userRoleIdString;
	
	private String loanTypeIdString;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;

	private Long lastAccessBusinessTypeId;
	
	private Integer otpOn;

	private Long businessTypeId;
	private String domain;
	private Long domainId;
	
	private List<Long> roleId;

	private String modelNo;

	private String imeiNo;

	private String mobileOs;

	private String osVersion;

	private String appVersion;

	private Integer orgType;

	List<OrgResponse> orgResponseList;

	List<Long> orgIdList;
	
	private String orgName;

	private String platform;

	private String pan;
	
	private List<String> pincodeList;
	
	private List<GenericChecker> genericCheckers;


	private Long fpUserId;
    private Long fsUserId;
    private Long fileId;
    private Long orgId;
    private Boolean isGstDataRetrieved;
    private Boolean isBsDataRetrived;
    
    // FOR GRIEVANCE DETAILS RELATED 
    private String remoteAddr;
    private String roportTo;
    private String applicationIdd;
	private String customerId;
	private String requestId;
	private String queryType;
	private String modeOfInteraction;
	private String teamAllocatedTo;
	private String pointOfContact;
	private Date dateOfRaising;
	private String status;
	
	// for servicing details
	
    private String dayMonthsYear;
	private String totalDisbursementAmount;
	private String serviceFeePayable;
	private String serviceFeePayableSecond;
	private String cGST;
	private String sGST;
	private String iGST;
	private String tDS;
	private String netServiceFreePayble;
	private String serviceFessCarriedOver;
	private String action;

	private Boolean nbfcUser;
	
	// For Api by Maaz 
	private Long apiNbfcMasterId;
	private Long apiNbfcAssistedUserId;
    
	private Boolean isExistingUser;
	private String fileType;
	
	
	private String companyId;
	
	private String cin;
	
	private String CompanyName;
	
    private String addressLine1;
    
    private String addressLine2;
   
    
    private String addressLine3;
    private Integer stateCode;
   
    private Integer cityCode;
   
    private Integer pinCode;

	private Integer industryId;
	
	private Integer otherIndustryId;
	
	private Double totalSales;
	
	private Boolean isNoGst;
	
	private Boolean isGstSalesMatched;
	
	private Integer constitutionId;
	
	private Double outStandingAmtMLI;
	private Long districtId;
	private String udhyogAadharNo;
	private Integer industryOneform;
	private Integer subIndustryOneform;
	private Long noOfEmployee;
	private Integer chiefPromoterGender;
	private Integer typeOfEntity;
	private Integer typeOfActivity;
	private Integer borrowerGstEx;
	private Integer mudraCover;
	private String gstin;
	private Integer flowType;
	
	private Double totalOs;
	private Double totalWcOs;
	private Double totalTlOs;
	private String isMudraCover;
	
	
	private String industryReason;
	
	public  UsersRequest(Long userId) {
		this.userId = userId;
	}
}
