package com.opl.mudra.api.user.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganisationConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3181768987400849818L;
	private String keyStore;
	private String keyStorePassword;
	private Boolean isSSL;
	private String keyStoreType;
	private Long applicationId;
	private Boolean isLogic;
	private CibilCommercialConfig commercialConfig;
	private CibilIndividualConfig individualConfig;

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public Boolean getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(Boolean isSSL) {
		this.isSSL = isSSL;
	}

	public String getKeyStoreType() {
		return keyStoreType;
	}

	public void setKeyStoreType(String keyStoreType) {
		this.keyStoreType = keyStoreType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getIsLogic() {
		return isLogic;
	}

	public void setIsLogic(Boolean isLogic) {
		this.isLogic = isLogic;
	}

	public CibilCommercialConfig getCommercialConfig() {
		return commercialConfig;
	}

	public void setCommercialConfig(CibilCommercialConfig commercialConfig) {
		this.commercialConfig = commercialConfig;
	}

	public CibilIndividualConfig getIndividualConfig() {
		return individualConfig;
	}

	public void setIndividualConfig(CibilIndividualConfig individualConfig) {
		this.individualConfig = individualConfig;
	}

	@Override
	public String toString() {
		return "OrganisationConfiguration [keyStore=" + keyStore + ", isSSL="
				+ isSSL + ", keyStoreType=" + keyStoreType + ", applicationId=" + applicationId + ", isLogic=" + isLogic
				+ ", commercialConfig=" + commercialConfig + ", individualConfig=" + individualConfig + "]";
	}

	public static class CibilCommercialConfig {
		private String userName;
		private String password;
		private String url;
		private String memberReferenceNo;
		private String kob;
		private String memberCode;
		private String cmrFlag;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getMemberReferenceNo() {
			return memberReferenceNo;
		}

		public void setMemberReferenceNo(String memberReferenceNo) {
			this.memberReferenceNo = memberReferenceNo;
		}

		public String getKob() {
			return kob;
		}

		public void setKob(String kob) {
			this.kob = kob;
		}

		public String getMemberCode() {
			return memberCode;
		}

		public void setMemberCode(String memberCode) {
			this.memberCode = memberCode;
		}

		public String getCmrFlag() {
			return cmrFlag;
		}

		public void setCmrFlag(String cmrFlag) {
			this.cmrFlag = cmrFlag;
		}

		@Override
		public String toString() {
			return "CibilCommercialConfig [userName=" + userName + ", url=" + url
					+ ", memberReferenceNo=" + memberReferenceNo + ", kob=" + kob + ", memberCode=" + memberCode
					+ ", cmrFlag=" + cmrFlag + "]";
		}
	}

	public static class CibilIndividualConfig {
		private String userName;
		private String password;
		private String url;
		private String executionMode;
		private String solutionSetId;
		private String solutionSetVersion;
		private String memberCode;
		private String memberPassword;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getExecutionMode() {
			return executionMode;
		}

		public void setExecutionMode(String executionMode) {
			this.executionMode = executionMode;
		}

		public String getSolutionSetId() {
			return solutionSetId;
		}

		public void setSolutionSetId(String solutionSetId) {
			this.solutionSetId = solutionSetId;
		}

		public String getSolutionSetVersion() {
			return solutionSetVersion;
		}

		public void setSolutionSetVersion(String solutionSetVersion) {
			this.solutionSetVersion = solutionSetVersion;
		}

		public String getMemberCode() {
			return memberCode;
		}

		public void setMemberCode(String memberCode) {
			this.memberCode = memberCode;
		}

		public String getMemberPassword() {
			return memberPassword;
		}

		public void setMemberPassword(String memberPassword) {
			this.memberPassword = memberPassword;
		}

		@Override
		public String toString() {
			return "CibilIndividualConfig [userName=" + userName + ", url=" + url
					+ ", executionMode=" + executionMode + ", solutionSetId=" + solutionSetId + ", solutionSetVersion="
					+ solutionSetVersion + ", memberCode=" + memberCode + "]";
		}
	}
}
