package com.opl.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the fp_product_master database table.
 *
 */
@Entity
@Table(name = "fp_product_master_temp")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProductMasterTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "fp_product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "fp_name")
	private String fpName;

	@Column(name = "product_code")
	private String productCode;

	@Column(name = "product_id")
	private Integer productId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	@Column(name = "is_parameter_filled")
	private Boolean isParameterFilled = false;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "is_matched")
	private Boolean isMatched = false;

	@Column(name = "user_org_id")
	private Long userOrgId;


	@Column(name = "score_model_id")
	private Long scoreModelId;

	@Column(name = "purpose_loan_model_id")
	private Long purposeLoanModelId;

	@Column(name = "score_model_id_coapp_id")
	private Long scoreModelIdCoAppId;
	
	@Column(name = "score_model_id_oth_thn_sal")
	private Long scoreModelIdOthThnSal;
	
	@Column(name = "score_model_id_coApp_id_oth_thn_sal")
	private Long scoreModelIdCoAppIdOthThnSal;
	

	@Column(name = "fp_product_mapping_id")
	private Long fpProductMappingId;

	@Column(name="is_approved")
    private Boolean isApproved;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="is_copied")
    private Boolean isCopied;

    @Column(name="is_edit")
    private Boolean isEdit;

    @Column(name="status_id")
    private Integer statusId;

    @Column(name="job_id")
    private Long jobId;

    @Column(name="approval_date")
    private Date approvalDate;

	@Column(name="org_id")
	private Long orgId;

	@Column(name = "business_type_id")
	private Long businessTypeId;

	@Column(name = "wc_renewal_status")
	private Integer wcRenewalStatus;

	@Column(name="fin_type_id")
	private Integer finId;

	@Column(name="campaign_type")
	private Integer campaignCode;

	@Column(name="active_inactive_job_id")
    private Long activeInactiveJobId;

	@Column(name="action_for")
	private String actionFor;

	@Column(name = "import_from_id")
	private Long importFromId;

	@Column(name = "is_gst")
	private Boolean isGst;

	@Column(name = "is_itr")
	private Boolean isItr;

	@Column(name = "is_bank_statement")
	private Boolean isBankStatement;

	@Column(name = "is_mca")
	private Boolean isMca;

	@Column(name = "is_bureu_personal")
	private Boolean isBureuPersonal;

	@Column(name = "is_bureu_commercial")
	private Boolean isBureuCommercial;

	@Column(name = "is_manual_fill")
	private Boolean isManualFill;
	
	@Column(name = "nbfc_product_type")
	private Integer productType;
	
	@Column(name = "bureau_version")
	private Integer bureauVersion;
	
//	@Column(name = "bank_statement_option")
//	private Integer bankStatementOption;


	public ProductMasterTemp() {
		// Do nothing because of X and Y.
	}
	public ProductMasterTemp(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public String getFpName() {
		return fpName;
	}

	public void setFpName(String fpName) {
		this.fpName = fpName;
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

	public Boolean getIsParameterFilled() {
		return isParameterFilled;
	}

	public void setIsParameterFilled(Boolean isParameterFilled) {
		this.isParameterFilled = isParameterFilled;
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

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Long getScoreModelId() {
		return scoreModelId;
	}

	public void setScoreModelId(Long scoreModelId) {
		this.scoreModelId = scoreModelId;
	}

	public Long getFpProductMappingId() {
		return fpProductMappingId;
	}

	public void setFpProductMappingId(Long fpProductMappingId) {
		this.fpProductMappingId = fpProductMappingId;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsCopied() {
		return isCopied;
	}

	public void setIsCopied(Boolean isCopied) {
		this.isCopied = isCopied;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
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

	public Long getActiveInactiveJobId() {
		return activeInactiveJobId;
	}

	public void setActiveInactiveJobId(Long activeInactiveJobId) {
		this.activeInactiveJobId = activeInactiveJobId;
	}

	public String getActionFor() {
		return actionFor;
	}

	public void setActionFor(String actionFor) {
		this.actionFor = actionFor;
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
	public Integer getBureauVersion() {
		return bureauVersion;
	}
	public void setBureauVersion(Integer bureauVersion) {
		this.bureauVersion = bureauVersion;
	}
//	public Integer getBankStatementOption() {
//		return bankStatementOption;
//	}
//	public void setBankStatementOption(Integer bankStatementOption) {
//		this.bankStatementOption = bankStatementOption;
//	}
}