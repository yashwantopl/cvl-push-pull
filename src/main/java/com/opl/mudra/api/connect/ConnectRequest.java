package com.opl.mudra.api.connect;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectRequest implements Serializable {

	private static final long serialVersionUID = 3408967653526547573L;

	private Long id;
	private Long userId;
	private Long profileId;
	private Long profileVerMapId;
	private String productType;
	private Long applicationId;
	private Integer stageId;
	private Integer stepId;
	private Integer status;
	private String gstIn;
	private String gstnUserName;
	private String pan;
	private Integer businessTypeId;
	private Long directorId;
	private Integer lastYear;
	private Long branchId;
	private Long orgId;
	private Integer wcRenewalStatus;
	private Long proposalId;
	private Date createdDate;
	private Date modifiedDate;
	private Integer msmeType;
	private Date inPrincipleDate;
	private CommonMCQ commonMCQ;
	private Boolean isCoAppStage;
	private List<ConnectCoAppRequest> coAppList;

	private Boolean isOldUser;
	private Boolean isFillAdditionalForm;

	private Long coAppId;
	private String campaignCode;
	private Long loanTypeId;
	private Boolean isItrReturnFilled;
	private Boolean isNbfcUser;
	private Object dataInput;
	private Integer newStageId; // Stage id to be used for updating stage
	


	public Date getInPrincipleDate() {
		return inPrincipleDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInPrincipleDate(Date inPrincipleDate) {
		this.inPrincipleDate = inPrincipleDate;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGstIn() {
		return gstIn;
	}

	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public String getGstnUserName() {
		return gstnUserName;
	}

	public void setGstnUserName(String gstnUserName) {
		this.gstnUserName = gstnUserName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public Integer getLastYear() {
		return lastYear;
	}

	public void setLastYear(Integer lastYear) {
		this.lastYear = lastYear;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Integer getMsmeType() {
		return msmeType;
	}

	public void setMsmeType(Integer msmeType) {
		this.msmeType = msmeType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public CommonMCQ getCommonMCQ() {
		return commonMCQ;
	}

	public void setCommonMCQ(CommonMCQ commonMCQ) {
		this.commonMCQ = commonMCQ;
	}

	public Boolean getIsCoAppStage() {
		return isCoAppStage;
	}

	public void setIsCoAppStage(Boolean isCoAppStage) {
		this.isCoAppStage = isCoAppStage;
	}
	
	public List<ConnectCoAppRequest> getCoAppList() {
		return coAppList;
	}

	public void setCoAppList(List<ConnectCoAppRequest> coAppList) {
		this.coAppList = coAppList;
	}

	public Long getCoAppId() {
		return coAppId;
	}

	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}

	public Long getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Boolean getIsItrReturnFilled() {
		return isItrReturnFilled;
	}

	public void setIsItrReturnFilled(Boolean isItrReturnFilled) {
		this.isItrReturnFilled = isItrReturnFilled;
	}

	public Boolean getIsOldUser() {
		return isOldUser;
	}

	public void setIsOldUser(Boolean oldUser) {
		isOldUser = oldUser;
	}

	public Boolean getIsFillAdditionalForm() {
		return isFillAdditionalForm;
	}

	public void setIsFillAdditionalForm(Boolean fillAdditionalForm) {
		isFillAdditionalForm = fillAdditionalForm;
	}

	public Boolean getIsNbfcUser() {
		return isNbfcUser;
	}

	public void setIsNbfcUser(Boolean nbfcUser) {
		isNbfcUser = nbfcUser;
	}

	public Object getDataInput() {
		return dataInput;
	}

	public void setDataInput(Object dataInput) {
		this.dataInput = dataInput;
	}
	
	public Integer getNewStageId() {
		return newStageId;
	}

	public void setNewStageId(Integer newStageId) {
		this.newStageId = newStageId;
	}
	
	

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getProfileVerMapId() {
		return profileVerMapId;
	}

	public void setProfileVerMapId(Long profileVerMapId) {
		this.profileVerMapId = profileVerMapId;
	}
	
	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	@Override
	public String toString() {
		return "ConnectRequest [userId=" + userId + ", productType=" + productType + ", applicationId=" + applicationId
				+ ", stageId=" + stageId + ", stepId=" + stepId + ", status=" + status + ", gstIn=" + gstIn
				+ ", gstnUserName=" + gstnUserName + ", pan=" + pan + ", businessTypeId=" + businessTypeId
				+ ", directorId=" + directorId + ", lastYear=" + lastYear + ", branchId=" + branchId + ", orgId="
				+ orgId + ", wcRenewalStatus=" + wcRenewalStatus + ", proposalId=" + proposalId + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", msmeType=" + msmeType + ", inPrincipleDate="
				+ inPrincipleDate + ", commonMCQ=" + commonMCQ + ", isCoAppStage=" + isCoAppStage + "]";
	}

	


}
