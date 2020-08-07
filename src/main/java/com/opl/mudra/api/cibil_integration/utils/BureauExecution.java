package com.opl.mudra.api.cibil_integration.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauExecution implements Serializable {
	private static final long serialVersionUID = -4363151292856116804L;

	private Long id;
	private String priority; // To be used in future
	private Integer primary;
	private BureauList bureauList;
	private List<Integer> noInfoAvailableList;
	private List<Integer> failureList;
	private Boolean isSingleBureauConfigured;
	private Long updatedDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}



	public static class BureauList implements Serializable {
		private static final long serialVersionUID = -8817726145678369662L;

		private ExperianConfig experianConfig;
		private TransunionConfig transunionConfig;
		private CrifHighmarkConfig crifHighmarkConfig;
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
		public CrifHighmarkConfig getCrifHighmarkConfig() {
			return crifHighmarkConfig;
		}
		public void setCrifHighmarkConfig(CrifHighmarkConfig crifHighmarkConfig) {
			this.crifHighmarkConfig = crifHighmarkConfig;
		}
	}

	// Experian Class ----------------------------------------------------------->
	public static class ExperianConfig implements Serializable {
		private static final long serialVersionUID = 8893143389219995253L;

		private Configuration commercial;
		private Configuration individual;
		private Boolean isChecked;

		public Configuration getCommercial() {
			return commercial;	
		}

		public void setCommercial(Configuration commercial) {
			this.commercial = commercial;
		}

		public Configuration getIndividual() {
			return individual;
		}

		public void setIndividual(Configuration individual) {
			this.individual = individual;
		}

		public Boolean getIsChecked() {
			return isChecked;
		}

		public void setIsChecked(Boolean isChecked) {
			this.isChecked = isChecked;
		}


		public static class Configuration implements Serializable {
			private static final long serialVersionUID = 8000318674041022659L;

			private String userName;
			private String password;
			private String bureauMemberId;
			private Boolean isActive;

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
			public String getBureauMemberId() {
				return bureauMemberId;
			}
			public void setBureauMemberId(String menberId) {
				this.bureauMemberId = menberId;
			}
			public Boolean getIsActive() {
				return isActive;
			}
			public void setIsActive(Boolean isActive) {
				this.isActive = isActive;
			}
		}
	}

	// Transunion Class ----------------------------------------------------------->
	public static class TransunionConfig implements Serializable {
		private static final long serialVersionUID = -2632453187796350426L;

		private Commercial commercial;
		private Individual individual;
		private Boolean isChecked;

		public Commercial getCommercial() {
			return commercial;
		}

		public void setCommercial(Commercial commercial) {
			this.commercial = commercial;
		}

		public Individual getIndividual() {
			return individual;
		}

		public void setIndividual(Individual individual) {
			this.individual = individual;
		}

		public Boolean getIsChecked() {
			return isChecked;
		}

		public void setIsChecked(Boolean isChecked) {
			this.isChecked = isChecked;
		}


		public static class Commercial implements Serializable {
			private static final long serialVersionUID = -6793865326843624941L;
			private String userName;
			private String password;
			private String url;
			private String memberReferenceNo;
			private String kob;
			private String memberCode;
			private String cmrFlag;
			private Boolean isActive;

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

			public Boolean getIsActive() {
				return isActive;
			}

			public void setIsActive(Boolean isActive) {
				this.isActive = isActive;
			}

		}

		public static class Individual implements Serializable {
			private static final long serialVersionUID = -5804286456694127461L;

			private String userName;
			private String password;
			private String url;
			private String executionMode;
			private String solutionSetId;
			private String executeLatestVersion;
			private String solutionSetVersion;
			private String scoreType;
			private String environment;
			private String memberCode;
			private String memberPassword;
			private String referenceNumber;
			private String nTCProductType;
			private String iDVMemberCode;
			private String consumerConsentForUIDAIAuthentication;
			private String mFIMemberCode;
			private String centerReferenceNo;
			private String branchReferenceNo;
			private String cibilBureauFlag;
			private String dSTuNtcFlag;
			private String iDVerificationFlag;
			private String mFIBureauFlag;
			private String cIBILPDFReport;
			private String mFIPDFReport;
			private String iDVPDFReport;
			private String formattedReport;
			private Boolean isActive;

			public String getFormattedReport() {
				return formattedReport;
			}

			public void setFormattedReport(String formattedReport) {
				this.formattedReport = formattedReport;
			}

			public String getScoreType() {
				return scoreType;
			}

			public void setScoreType(String scoreType) {
				this.scoreType = scoreType;
			}

			public String getEnvironment() {
				return environment;
			}

			public void setEnvironment(String environment) {
				this.environment = environment;
			}

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

			public String getExecuteLatestVersion() {
				return executeLatestVersion;
			}

			public void setExecuteLatestVersion(String executeLatestVersion) {
				this.executeLatestVersion = executeLatestVersion;
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

			public Boolean getIsActive() {
				return isActive;
			}

			public void setIsActive(Boolean isActive) {
				this.isActive = isActive;
			}

			public String getReferenceNumber() {
				return referenceNumber;
			}

			public void setReferenceNumber(String referenceNumber) {
				this.referenceNumber = referenceNumber;
			}

			public String getnTCProductType() {
				return nTCProductType;
			}

			public void setnTCProductType(String nTCProductType) {
				this.nTCProductType = nTCProductType;
			}

			public String getiDVMemberCode() {
				return iDVMemberCode;
			}

			public void setiDVMemberCode(String iDVMemberCode) {
				this.iDVMemberCode = iDVMemberCode;
			}

			public String getConsumerConsentForUIDAIAuthentication() {
				return consumerConsentForUIDAIAuthentication;
			}

			public void setConsumerConsentForUIDAIAuthentication(String consumerConsentForUIDAIAuthentication) {
				this.consumerConsentForUIDAIAuthentication = consumerConsentForUIDAIAuthentication;
			}

			public String getmFIMemberCode() {
				return mFIMemberCode;
			}

			public void setmFIMemberCode(String mFIMemberCode) {
				this.mFIMemberCode = mFIMemberCode;
			}

			public String getCenterReferenceNo() {
				return centerReferenceNo;
			}

			public void setCenterReferenceNo(String centerReferenceNo) {
				this.centerReferenceNo = centerReferenceNo;
			}

			public String getBranchReferenceNo() {
				return branchReferenceNo;
			}

			public void setBranchReferenceNo(String branchReferenceNo) {
				this.branchReferenceNo = branchReferenceNo;
			}

			public String getCibilBureauFlag() {
				return cibilBureauFlag;
			}

			public void setCibilBureauFlag(String cibilBureauFlag) {
				this.cibilBureauFlag = cibilBureauFlag;
			}


			public String getdSTuNtcFlag() {
				return dSTuNtcFlag;
			}

			public void setdSTuNtcFlag(String dSTuNtcFlag) {
				this.dSTuNtcFlag = dSTuNtcFlag;
			}

			public String getiDVerificationFlag() {
				return iDVerificationFlag;
			}

			public void setiDVerificationFlag(String iDVerificationFlag) {
				this.iDVerificationFlag = iDVerificationFlag;
			}

			public String getmFIBureauFlag() {
				return mFIBureauFlag;
			}

			public void setmFIBureauFlag(String mFIBureauFlag) {
				this.mFIBureauFlag = mFIBureauFlag;
			}

			public String getcIBILPDFReport() {
				return cIBILPDFReport;
			}

			public void setcIBILPDFReport(String cIBILPDFReport) {
				this.cIBILPDFReport = cIBILPDFReport;
			}

			public String getmFIPDFReport() {
				return mFIPDFReport;
			}

			public void setmFIPDFReport(String mFIPDFReport) {
				this.mFIPDFReport = mFIPDFReport;
			}

			public String getiDVPDFReport() {
				return iDVPDFReport;
			}

			public void setiDVPDFReport(String iDVPDFReport) {
				this.iDVPDFReport = iDVPDFReport;
			}
		}
	}

	// CrifHighmarkConfig Class ----------------------------------------------------------->
	public static class CrifHighmarkConfig implements Serializable {

		private static final long serialVersionUID = 1L;
		private Configuration commercial;
		private Configuration individual;
		private Boolean isChecked;

		public Configuration getCommercial() {
			return commercial;	
		}

		public void setCommercial(Configuration commercial) {
			this.commercial = commercial;
		}

		public Configuration getIndividual() {
			return individual;
		}

		public void setIndividual(Configuration individual) {
			this.individual = individual;
		}

		public Boolean getIsChecked() {
			return isChecked;
		}

		public void setIsChecked(Boolean isChecked) {
			this.isChecked = isChecked;
		}


		public static class Configuration implements Serializable {

			private static final long serialVersionUID = -1216258035478336782L;
			
			private String userName;
			private String password;
			private String memberId;
			private String subMemberId;
			private Boolean isActive;
			
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
			public String getMemberId() {
				return memberId;
			}
			public void setMemberId(String memberId) {
				this.memberId = memberId;
			}
			public String getSubMemberId() {
				return subMemberId;
			}
			public void setSubMemberId(String subMemberId) {
				this.subMemberId = subMemberId;
			}
			public Boolean getIsActive() {
				return isActive;
			}
			public void setIsActive(Boolean isActive) {
				this.isActive = isActive;
			}
		}
	}
	
}


