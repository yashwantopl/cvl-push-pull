package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

	private Long scoreModelIdCoAppId;
	
	private Long purposeLoanModelId;

	private Long businessTypeId;
	
	private Boolean isParameterFilled;

	private Integer wcRenewalStatus;
	
	private Integer finId;
	
	private Integer campaignCode;

	private Long proposalCount;
	
	private Long activeInactiveJobId;
	
	private String actionFor;

	private Long importFromId;

	private Boolean isGst;

	private Boolean isItr;

	private Boolean isBankStatement;

	private Boolean isMca;

	private Boolean isBureuPersonal;

	private Boolean isBureuCommercial;

	private Boolean isManualFill;
	
	
	//total limits
	private List<Integer> gstType;
	

	public ProductMasterRequest() {
		// Do nothing because of X and Y.
	}

	public ProductMasterRequest(Long id) {
		this.id = id;
	}


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

	public List<Integer> getGstType() {
		return gstType;
	}

	public void setGstType(List<Integer> gstType) {
		this.gstType = gstType;
	}

	public Long getScoreModelIdCoAppId() {
		return scoreModelIdCoAppId;
	}

	public void setScoreModelIdCoAppId(Long scoreModelIdCoAppId) {
		this.scoreModelIdCoAppId = scoreModelIdCoAppId;
	}

	public Long getPurposeLoanModelId() {
		return purposeLoanModelId;
	}

	public void setPurposeLoanModelId(Long purposeLoanModelId) {
		this.purposeLoanModelId = purposeLoanModelId;
	}

	public Long getImportFromId() {
		return importFromId;
	}

	public void setImportFromId(Long importFromId) {
		this.importFromId = importFromId;
	}

	public Boolean getIsGst() {
		return isGst;
	}

	public void setIsGst(Boolean isGst) {
		this.isGst = isGst;
	}

	public Boolean getIsItr() {
		return isItr;
	}

	public void setIsItr(Boolean isItr) {
		this.isItr = isItr;
	}

	public Boolean getIsBankStatement() {
		return isBankStatement;
	}

	public void setIsBankStatement(Boolean isBankStatement) {
		this.isBankStatement = isBankStatement;
	}

	public Boolean getIsMca() {
		return isMca;
	}

	public void setIsMca(Boolean isMca) {
		this.isMca = isMca;
	}

	public Boolean getIsBureuPersonal() {
		return isBureuPersonal;
	}

	public void setIsBureuPersonal(Boolean isBureuPersonal) {
		this.isBureuPersonal = isBureuPersonal;
	}

	public Boolean getIsBureuCommercial() {
		return isBureuCommercial;
	}

	public void setIsBureuCommercial(Boolean isBureuCommercial) {
		this.isBureuCommercial = isBureuCommercial;
	}

	public Boolean getIsManualFill() {
		return isManualFill;
	}

	public void setIsManualFill(Boolean isManualFill) {
		this.isManualFill = isManualFill;
	}


	
}