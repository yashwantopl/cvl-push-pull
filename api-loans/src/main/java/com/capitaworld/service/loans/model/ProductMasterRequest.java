package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fp_product_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Integer productId;

	private Date startDate;
	
	private Date endDate;

	private Long userId;
	
	private Long userOrgId;

	private String productCode;
	
	private Boolean isMatched;
	
	private Date createdDate;
	
	private String fpName;
	
	private Boolean isActive;
	
	private Long jobId;
	
	private Integer appstage;

	private Long scoreModelId;

	private Long businessTypeId;
	
	private Boolean isParameterFilled;

	private Integer wcRenewalStatus;
	
	private Integer finId;
	
	private Integer campaignCode;

	private Long proposalCount;
	
	private Long activeInactiveJobId;
	
	private String actionFor;
	
	
	/**
	 * @return the jobId
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public ProductMasterRequest() {
		// Do nothing because of X and Y.
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Boolean getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(Boolean isMatched) {
		this.isMatched = isMatched;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getAppstage() {
		return appstage;
	}

	public void setAppstage(Integer appstage) {
		this.appstage = appstage;
	}

	public Long getScoreModelId() {
		return scoreModelId;
	}

	public void setScoreModelId(Long scoreModelId) {
		this.scoreModelId = scoreModelId;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Boolean getIsParameterFilled() {
		return isParameterFilled;
	}

	public void setIsParameterFilled(Boolean isParameterFilled) {
		this.isParameterFilled = isParameterFilled;
	}

	public Integer getWcRenewalStatus() {
		return wcRenewalStatus;
	}

	public void setWcRenewalStatus(Integer wcRenewalStatus) {
		this.wcRenewalStatus = wcRenewalStatus;
	}

	public Integer getFinId() {
		return finId;
	}

	public void setFinId(Integer finId) {
		this.finId = finId;
	}

	public Integer getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(Integer campaignCode) {
		this.campaignCode = campaignCode;
	}

	public Long getProposalCount() {
		return proposalCount;
	}

	public void setProposalCount(Long proposalCount) {
		this.proposalCount = proposalCount;
	}

	public Long getActiveInactiveJobId() {
		return activeInactiveJobId;
	}

	public String getActionFor() {
		return actionFor;
	}

	public void setActiveInactiveJobId(Long activeInactiveJobId) {
		this.activeInactiveJobId = activeInactiveJobId;
	}

	public void setActionFor(String actionFor) {
		this.actionFor = actionFor;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
}