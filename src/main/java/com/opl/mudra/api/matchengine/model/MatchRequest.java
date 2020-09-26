package com.opl.mudra.api.matchengine.model;

import java.io.Serializable;

import com.opl.mudra.api.matchengine.utils.CommonUtils.MatchesStages;


public class MatchRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long productId;

	private Long proposalId;
	
	private Long applicationId;
	private Long gstId;
	
	private Long bsId;
	
	private Long itrId;

	private Long userId;

	private Long userType;

	private String campaignCode;

	private MatchesStages stage;

	private Long stageId;

	private Integer businessTypeId;
	
	private Integer lastYear;

	private Boolean multiBankFlow;
	
	private Double roi;
	
	private Integer version;
	
	private Double processingFee;

	private Double fsDSCRVal;
	
	private Long loanTypeId;

	private Boolean isNbfcFlow;
	
	private Boolean isBSManual;

	public MatchRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MatchRequest(Long applicationId, Long userId, MatchesStages stage) {
		super();
		this.applicationId = applicationId;
		this.userId = userId;
		this.stage = stage;
	}

	public MatchRequest(Long applicationId, MatchesStages stage) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
	}

	public MatchRequest(Long applicationId, MatchesStages stage,int businessTypeId) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
		this.businessTypeId = businessTypeId;
	}
	
	public MatchRequest(Long applicationId, MatchesStages stage,int businessTypeId,Boolean multiBankFlow) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
		this.businessTypeId = businessTypeId;
		this.multiBankFlow = multiBankFlow;
	}

	public MatchRequest(Long applicationId, MatchesStages stage,int businessTypeId,Boolean multiBankFlow, Long loanTypeId) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
		this.businessTypeId = businessTypeId;
		this.multiBankFlow = multiBankFlow;
		this.loanTypeId = loanTypeId; 
	}
	
	public MatchRequest(Long applicationId, MatchesStages stage,int businessTypeId,Integer lastYear) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
		this.businessTypeId = businessTypeId;
		this.lastYear = lastYear;
	}

	public MatchRequest(Long applicationId, MatchesStages stage,int businessTypeId,Boolean multiBankFlow,Boolean isNbfcFlow) {
		super();
		this.applicationId = applicationId;
		this.stage = stage;
		this.businessTypeId = businessTypeId;
		this.multiBankFlow = multiBankFlow;
		this.isNbfcFlow = isNbfcFlow;
	}
	public Boolean getIsMultiBankFlow() {
		return multiBankFlow;
	}

	public void setIsMultiBankFlow(Boolean multiBankFlow) {
		this.multiBankFlow = multiBankFlow;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

	public MatchesStages getStage() {
		return stage;
	}

	public void setStage(MatchesStages stage) {
		this.stage = stage;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}
	
	
	public Integer getLastYear() {
		return lastYear;
	}

	public void setLastYear(Integer lastYear) {
		this.lastYear = lastYear;
	}
	
	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}
	
	public Double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Double getFsDSCRVal() {
		return fsDSCRVal;
	}

	public void setFsDSCRVal(Double fsDSCRVal) {
		this.fsDSCRVal = fsDSCRVal;
	}

	public Boolean getMultiBankFlow() {
		return multiBankFlow;
	}

	public void setMultiBankFlow(Boolean multiBankFlow) {
		this.multiBankFlow = multiBankFlow;
	}

	public Long getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public Boolean getIsNbfcFlow() {
		return isNbfcFlow;
	}

	public void setIsNbfcFlow(Boolean nbfcFlow) {
		isNbfcFlow = nbfcFlow;
	}

	public Boolean getIsBSManual() {
		return isBSManual;
	}

	public void setIsBSManual(Boolean isBSManual) {
		this.isBSManual = isBSManual;
	}

	public Long getGstId() {
		return gstId;
	}

	public void setGstId(Long gstId) {
		this.gstId = gstId;
	}

	public Long getBsId() {
		return bsId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	public Long getItrId() {
		return itrId;
	}

	public void setItrId(Long itrId) {
		this.itrId = itrId;
	}
}
