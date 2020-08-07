package com.opl.mudra.api.cibil.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailBureauConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer primary;
	private BureauList bureauList;
	private List<Integer> noInfoAvailableList;
	private List<Integer> failureList;
	private Boolean isSingleBureauConfigured;
	private Boolean isSystemDown;
	private String systemDownMsg;
	private Long modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrimary() {
		return primary;
	}

	public void setPrimary(Integer primary) {
		this.primary = primary;
	}

	public BureauList getBureauList() {
		return bureauList;
	}

	public void setBureauList(BureauList bureauList) {
		this.bureauList = bureauList;
	}

	public List<Integer> getNoInfoAvailableList() {
		return noInfoAvailableList;
	}

	public void setNoInfoAvailableList(List<Integer> noInfoAvailableList) {
		this.noInfoAvailableList = noInfoAvailableList;
	}

	public List<Integer> getFailureList() {
		return failureList;
	}

	public void setFailureList(List<Integer> failureList) {
		this.failureList = failureList;
	}

	public Boolean getIsSingleBureauConfigured() {
		return isSingleBureauConfigured;
	}

	public void setIsSingleBureauConfigured(Boolean isSingleBureauConfigured) {
		this.isSingleBureauConfigured = isSingleBureauConfigured;
	}

	public Boolean getIsSystemDown() {
		return isSystemDown;
	}

	public void setIsSystemDown(Boolean isSystemDown) {
		this.isSystemDown = isSystemDown;
	}
	
	public String getSystemDownMsg() {
		return systemDownMsg;
	}

	public void setSystemDownMsg(String systemDownMsg) {
		this.systemDownMsg = systemDownMsg;
	}

	public Long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	public static class BureauList implements Serializable {
		private static final long serialVersionUID = -8817726145678369662L;

		private ExperianConfig experianConfig;
		private TransunionConfig transunionConfig;
		private EquifaxConfig equifaxConfig;
		private HighmarkConfig highmarkConfig;
		
		public ExperianConfig getExperianConfig() {
			return experianConfig;
		}
		
		public void setExperianConfig(ExperianConfig experianConfig) {
			this.experianConfig = experianConfig;
		}
		
		public TransunionConfig getTransunionConfig() {
			return transunionConfig;
		}
		
		public void setTransunionConfig(TransunionConfig transunionConfig) {
			this.transunionConfig = transunionConfig;
		}
		
		public EquifaxConfig getEquifaxConfig() {
			return equifaxConfig;
		}
		
		public void setEquifaxConfig(EquifaxConfig equifaxConfig) {
			this.equifaxConfig = equifaxConfig;
		}

		public HighmarkConfig getHighmarkConfig() {
			return highmarkConfig;
		}

		public void setHighmarkConfig(HighmarkConfig highmarkConfig) {
			this.highmarkConfig = highmarkConfig;
		}
	}

	// Experian Class ----------------------------------------------------------->
	public static class ExperianConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		private String clientName;
		private String voucherCode;
		private Boolean isActive;

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}

		public String getVoucherCode() {
			return voucherCode;
		}

		public void setVoucherCode(String voucherCode) {
			this.voucherCode = voucherCode;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
	}

	// Transunion Class ----------------------------------------------------------->
	public static class TransunionConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		private String clientName;
		private String voucherCode;
		private Boolean isActive;

		public String getClientName() {
			return clientName;
		}

		public void setClientName(String clientName) {
			this.clientName = clientName;
		}

		public String getVoucherCode() {
			return voucherCode;
		}

		public void setVoucherCode(String voucherCode) {
			this.voucherCode = voucherCode;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
	}

	// Equifax Class ----------------------------------------------------------->
	public static class EquifaxConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		private String customerId;
		private String userId;
		private String password;
		private String memberNumber;
		private String securityCode;
		private Boolean isActive;

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMemberNumber() {
			return memberNumber;
		}

		public void setMemberNumber(String memberNumber) {
			this.memberNumber = memberNumber;
		}

		public String getSecurityCode() {
			return securityCode;
		}

		public void setSecurityCode(String securityCode) {
			this.securityCode = securityCode;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
	}
	// Highmark Class ----------------------------------------------------------->
	public static class HighmarkConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		private String initiateBureauInquiryUrl;
		private String authenticationUrl;
		private String customerAssetUrl;
		private String customerId;
		private String userId;
		private String password;
		private String productCode;
		private String appId;
		private String redirectUrl;
		private Boolean isActive;

		public String getInitiateBureauInquiryUrl() {
			return initiateBureauInquiryUrl;
		}

		public void setInitiateBureauInquiryUrl(String initiateBureauInquiryUrl) {
			this.initiateBureauInquiryUrl = initiateBureauInquiryUrl;
		}

		public String getAuthenticationUrl() {
			return authenticationUrl;
		}

		public void setAuthenticationUrl(String authenticationUrl) {
			this.authenticationUrl = authenticationUrl;
		}

		public String getCustomerAssetUrl() {
			return customerAssetUrl;
		}

		public void setCustomerAssetUrl(String customerAssetUrl) {
			this.customerAssetUrl = customerAssetUrl;
		}

		public String getCustomerId() {
			return customerId;
		}
		
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		
		public String getUserId() {
			return userId;
		}
		
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getProductCode() {
			return productCode;
		}
		
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		
		public Boolean getIsActive() {
			return isActive;
		}
		
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}

		public String getAppId() {
			return appId;
		}

		public void setAppId(String appId) {
			this.appId = appId;
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public void setRedirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
		}
	}

}
