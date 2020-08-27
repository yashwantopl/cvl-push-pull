package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fp_product_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMasterRequestNewStructure implements Serializable {
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
	
	private Long scoreModelIdOthThnSal;
	
	private Long scoreModelIdCoAppIdOthThnSal;
	
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
	
	private Integer productType;
	
	private BigDecimal minInterestCoverageWithDepreciation;

	private BigDecimal maxInterestCoverageWithDepreciation;
	
	private Boolean isInterestCoverageWithDepreciationDisplay = false;
	
	private Boolean isInterestCoverageWithDepreciationMandatory= false;

	private BigDecimal individualBureauMaximumThreeMonthDPDs;

	private Boolean isIndividualBureauMaximumThreeMonthDPDsDisplay = false;
	
	private Boolean isIndividualBureauMaximumThreeMonthDPDsMandatory= false;
	
	private BigDecimal individualBureauMaximumSixMonthDPDs;
	
	private Boolean isIndividualBureauMaximumSixMonthDPDsDisplay = false;
	
	private Boolean isIndividualBureauMaximumSixMonthDPDsMandatory= false;
	
//	Do You want to consider EMI of Borrower who is acting as a Guarantor/ Authorized User for Loan Eligibility calculation?
	private Boolean isEmiForEligibilityCalc = false;
	
	private Integer productTab;
	
	private Date modifiedDate;
	
	private Map<String, Object> getData;
	
	private Integer nextPage;
	
	private Integer pageIndex;
	   
    private Integer size;
    
    private Long version;
    
    private Long mstId;
    
    private List<Integer> typeOfFinancials;
    
    private Long roleId;
    
    private List<DataRequest> versionList;

	
	public ProductMasterRequestNewStructure() {
		// Do nothing because of X and Y.
	}

	public ProductMasterRequestNewStructure(Long id) {
		this.id = id;
	}
	
	


	public ProductMasterRequestNewStructure(Long id, String name, Integer productId, Date createdDate, Date modifiedDate,Long version) {
		super();
		this.id = id;
		this.name = name;
		this.productId = productId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.version=version;
	}
	
	
	public ProductMasterRequestNewStructure(Long id, String name, Integer productId, Date createdDate, Date modifiedDate) {
		super();
		this.id = id;
		this.name = name;
		this.productId = productId;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	
	public ProductMasterRequestNewStructure(Long id, String name, Integer productId, Integer wcRenewalStatus, Integer campaignCode) {
		super();
		this.id = id;
		this.name = name;
		this.productId = productId;
		this.wcRenewalStatus = wcRenewalStatus;
		this.campaignCode = campaignCode;
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

	public Long getScoreModelIdOthThnSal() {
		return scoreModelIdOthThnSal;
	}

	public void setScoreModelIdOthThnSal(Long scoreModelIdOthThnSal) {
		this.scoreModelIdOthThnSal = scoreModelIdOthThnSal;
	}

	public Long getScoreModelIdCoAppIdOthThnSal() {
		return scoreModelIdCoAppIdOthThnSal;
	}

	public void setScoreModelIdCoAppIdOthThnSal(Long scoreModelIdCoAppIdOthThnSal) {
		this.scoreModelIdCoAppIdOthThnSal = scoreModelIdCoAppIdOthThnSal;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public BigDecimal getMinInterestCoverageWithDepreciation() {
		return minInterestCoverageWithDepreciation;
	}

	public void setMinInterestCoverageWithDepreciation(BigDecimal minInterestCoverageWithDepreciation) {
		this.minInterestCoverageWithDepreciation = minInterestCoverageWithDepreciation;
	}

	public BigDecimal getMaxInterestCoverageWithDepreciation() {
		return maxInterestCoverageWithDepreciation;
	}

	public void setMaxInterestCoverageWithDepreciation(BigDecimal maxInterestCoverageWithDepreciation) {
		this.maxInterestCoverageWithDepreciation = maxInterestCoverageWithDepreciation;
	}

	public Boolean getIsInterestCoverageWithDepreciationDisplay() {
		return isInterestCoverageWithDepreciationDisplay;
	}

	public void setIsInterestCoverageWithDepreciationDisplay(Boolean isInterestCoverageWithDepreciationDisplay) {
		this.isInterestCoverageWithDepreciationDisplay = isInterestCoverageWithDepreciationDisplay;
	}

	public Boolean getIsInterestCoverageWithDepreciationMandatory() {
		return isInterestCoverageWithDepreciationMandatory;
	}

	public void setIsInterestCoverageWithDepreciationMandatory(Boolean isInterestCoverageWithDepreciationMandatory) {
		this.isInterestCoverageWithDepreciationMandatory = isInterestCoverageWithDepreciationMandatory;
	}

	public BigDecimal getIndividualBureauMaximumSixMonthDPDs() {
		return individualBureauMaximumSixMonthDPDs;
	}

	public void setIndividualBureauMaximumSixMonthDPDs(BigDecimal individualBureauMaximumSixMonthDPDs) {
		this.individualBureauMaximumSixMonthDPDs = individualBureauMaximumSixMonthDPDs;
	}

	public Boolean getIsIndividualBureauMaximumSixMonthDPDsDisplay() {
		return isIndividualBureauMaximumSixMonthDPDsDisplay;
	}

	public void setIsIndividualBureauMaximumSixMonthDPDsDisplay(Boolean isIndividualBureauMaximumSixMonthDPDsDisplay) {
		this.isIndividualBureauMaximumSixMonthDPDsDisplay = isIndividualBureauMaximumSixMonthDPDsDisplay;
	}

	public Boolean getIsIndividualBureauMaximumSixMonthDPDsMandatory() {
		return isIndividualBureauMaximumSixMonthDPDsMandatory;
	}

	public void setIsIndividualBureauMaximumSixMonthDPDsMandatory(Boolean isIndividualBureauMaximumSixMonthDPDsMandatory) {
		this.isIndividualBureauMaximumSixMonthDPDsMandatory = isIndividualBureauMaximumSixMonthDPDsMandatory;
	}

	public Boolean getIsIndividualBureauMaximumThreeMonthDPDsDisplay() {
		return isIndividualBureauMaximumThreeMonthDPDsDisplay;
	}

	public void setIsIndividualBureauMaximumThreeMonthDPDsDisplay(Boolean isIndividualBureauMaximumThreeMonthDPDsDisplay) {
		this.isIndividualBureauMaximumThreeMonthDPDsDisplay = isIndividualBureauMaximumThreeMonthDPDsDisplay;
	}

	public Boolean getIsIndividualBureauMaximumThreeMonthDPDsMandatory() {
		return isIndividualBureauMaximumThreeMonthDPDsMandatory;
	}

	public void setIsIndividualBureauMaximumThreeMonthDPDsMandatory(
			Boolean isIndividualBureauMaximumThreeMonthDPDsMandatory) {
		this.isIndividualBureauMaximumThreeMonthDPDsMandatory = isIndividualBureauMaximumThreeMonthDPDsMandatory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getIsEmiForEligibilityCalc() {
		return isEmiForEligibilityCalc;
	}

	public void setIsEmiForEligibilityCalc(Boolean isEmiForEligibilityCalc) {
		this.isEmiForEligibilityCalc = isEmiForEligibilityCalc;
	}

	public Integer getProductTab() {
		return productTab;
	}

	public void setProductTab(Integer productTab) {
		this.productTab = productTab;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public BigDecimal getIndividualBureauMaximumThreeMonthDPDs() {
		return individualBureauMaximumThreeMonthDPDs;
	}

	public void setIndividualBureauMaximumThreeMonthDPDs(BigDecimal individualBureauMaximumThreeMonthDPDs) {
		this.individualBureauMaximumThreeMonthDPDs = individualBureauMaximumThreeMonthDPDs;
	}

	public Map<String, Object> getGetData() {
		return getData;
	}

	public void setGetData(Map<String, Object> getData) {
		this.getData = getData;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<Integer> getTypeOfFinancials() {
		return typeOfFinancials;
	}

	public void setTypeOfFinancials(List<Integer> typeOfFinancials) {
		this.typeOfFinancials = typeOfFinancials;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMstId() {
		return mstId;
	}

	public void setMstId(Long mstId) {
		this.mstId = mstId;
	}

	public List<DataRequest> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<DataRequest> versionList) {
		this.versionList = versionList;
	}

	
	
	
}