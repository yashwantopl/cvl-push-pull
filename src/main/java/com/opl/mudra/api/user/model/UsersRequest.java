package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public String getProductIdString() {
		return productIdString;
	}

	public void setProductIdString(String productIdString) {
		this.productIdString = productIdString;
	}

	public String getApplicationIdString() {
		return applicationIdString;
	}

	public void setApplicationIdString(String applicationIdString) {
		this.applicationIdString = applicationIdString;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getOtpVerificationType() {
		return otpVerificationType;
	}

	public void setOtpVerificationType(Integer otpVerificationType) {
		this.otpVerificationType = otpVerificationType;
	}

	private Long userRoleId;

	private String userRoleIdString;

	private List<GenericChecker> genericCheckers;

	public String getUserRoleIdString() {
		return userRoleIdString;
	}

	public void setUserRoleIdString(String userRoleIdString) {
		this.userRoleIdString = userRoleIdString;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public UsersRequest(Long id) {
		this.id = id;
	}

	public Long getProdId() {
		return prodId;
	}

	public void setProdId(Long prodId) {
		this.prodId = prodId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getLastAccessApplicantId() {
		return lastAccessApplicantId;
	}

	public void setLastAccessApplicantId(Long lastAccessApplicantId) {
		this.lastAccessApplicantId = lastAccessApplicantId;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public boolean isEmailVarified() {
		return emailVarified;
	}

	public void setEmailVarified(boolean emailVarified) {
		this.emailVarified = emailVarified;
	}

	public boolean isOtpVarified() {
		return otpVarified;
	}

	public void setOtpVarified(boolean otpVarified) {
		this.otpVarified = otpVarified;
	}

	public String getVerificationUrl() {
		return verificationUrl;
	}

	public void setVerificationUrl(String verificationUrl) {
		this.verificationUrl = verificationUrl;
	}

	public UsersRequest() {
	}

	public UsersRequest(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public boolean isTermsAccepted() {
		return termsAccepted;
	}

	public void setTermsAccepted(boolean termsAccepted) {
		this.termsAccepted = termsAccepted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public UserTypeRequest getUserType() {
		return userType;
	}

	public void setUserType(UserTypeRequest userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public SignupTypeRequest getSignUpType() {
		return signUpType;
	}

	public void setSignUpType(SignupTypeRequest signUpType) {
		this.signUpType = signUpType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Boolean getIsMsmeUser() {
		return isMsmeUser;
	}

	public void setIsMsmeUser(Boolean isMsmeUser) {
		this.isMsmeUser = isMsmeUser;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
	public Boolean getSkipLogin() {
		return skipLogin;
	}

	public void setSkipLogin(Boolean skipLogin) {
		this.skipLogin = skipLogin;
	}

	public Integer getDataReqType() {
		return dataReqType;
	}

	public void setDataReqType(Integer dataReqType) {
		this.dataReqType = dataReqType;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	private List<Long> roleId;

	public List<Long> getRoleId() {
		return roleId;
	}

	public void setRoleId(List<Long> roleId) {
		this.roleId = roleId;
	}

	public List<GenericChecker> getGenericCheckers() {
		return genericCheckers;
	}

	public void setGenericCheckers(List<GenericChecker> genericCheckers) {
		this.genericCheckers = genericCheckers;
	}

	public Integer getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(Integer otpStatus) {
		this.otpStatus = otpStatus;
	}

	@Override
	public String toString() {
		return "UsersRequest [id=" + id + ", idString=" + idString + ", clientId=" + clientId + ", email=" + email
				+ ", mobile=" + mobile + ", userType=" + userType + ", username=" + username + ", name=" + name
				+ ", signUpType=" + signUpType + ", termsAccepted=" + termsAccepted + ", isActive=" + isActive
				+ ", otp=" + otp + ", verificationUrl=" + verificationUrl + ", otpVarified=" + otpVarified
				+ ", emailVarified=" + emailVarified + ", otpStatus=" + otpStatus + ", cityId=" + cityId + ", stateId="
				+ stateId + ", countryId=" + countryId + ", modifiedDate=" + modifiedDate + ", signUpDate=" + signUpDate
				+ ", browserName=" + browserName + ", campaignCode=" + campaignCode + ", campaignType=" + campaignType
				+ ", lastAccessApplicantId=" + lastAccessApplicantId + ", prodId=" + prodId + ", isMsmeUser="
				+ isMsmeUser + ", pageIndex=" + pageIndex + ", size=" + size + ", pageSize=" + pageSize + ", userOrgId="
				+ userOrgId + ", pincode=" + pincode + ", city=" + city + ", ids=" + ids + ", otpVerificationType="
				+ otpVerificationType + ", skipLogin=" + skipLogin + ", loanAmount=" + loanAmount + ", productId="
				+ productId + ", applicationId=" + applicationId + ", productIdString=" + productIdString
				+ ", applicationIdString=" + applicationIdString + ", dataReqType=" + dataReqType + ", branchId="
				+ branchId + ", userRoleId=" + userRoleId + ", userRoleIdString=" + userRoleIdString
				+ ", genericCheckers=" + genericCheckers + ", roleId=" + roleId + "]";
	}

}
