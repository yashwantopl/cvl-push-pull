package com.capitaworld.service.loans.domain.fundseeker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "application_proposal_mapping")
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationProposalMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "proposal_id")
    private Long proposalId;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "fp_maker_id")
    private Long fpMakerId;

    @Column(name = "np_user_id")
    private Long npUserId;

    @Column(name = "np_assignee_id")
    private Long npAssigneeId;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "date default sysdate")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "approved_date")
	private Date approvedDate;

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_final_locked")
    private Boolean isFinalLocked ;

    @Column(name = "is_primary_locked")
    private Boolean isPrimaryLocked = true;

    @ManyToOne
    @JoinColumn(name = "status")
    private ApplicationStatusMaster applicationStatusMaster;

    @Column(name = "loan_amount")
    private Double loanAmount;
    
    @Column(name = "ddr_status_id")
	private Long ddrStatusId;
    

    // Upload Fields
    @Column(name = "is_primary_upload_filled")
    private Boolean isPrimaryUploadFilled;

    @Column(name = "is_final_dpr_upload_filled")
    private Boolean isFinalDprUploadFilled;

    @Column(name = "is_final_upload_filled")
    private Boolean isFinalUploadFilled;

    @Column(name = "is_final_mcq_filled")
    private Boolean isFinalMcqFilled;

    @Column(name = "tenure")
    private Double tenure;

    @Column(name = "details_filled_count")
    private String detailsFilledCount;

    @Column(name = "primary_filled_count")
    private String primaryFilledCount;

    @Column(name = "product_id")
    private Integer productId;

    // Common Fields
    @Column(name = "is_applicant_details_filled")
    private Boolean isApplicantDetailsFilled;

    @Column(name = "is_applicant_primary_filled")
    private Boolean isApplicantPrimaryFilled;

    @Column(name = "is_applicant_final_filled")
    private Boolean isApplicantFinalFilled;

    @Column(name = "application_code")
    private String applicationCode;

    @Column(name = "payment_status")
    private String paymentStatus;
    
    @Column(name = "business_type_id")
    private Integer businessTypeId;
    
    @Column(name = "is_mcq_skipped")
	private Boolean isMcqSkipped;
    
    @Column(name="denomination_id")
    private Long denominationId;

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ApplicationProposalMapping() {
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getFpMakerId() {
        return fpMakerId;
    }

    public void setFpMakerId(Long fpMakerId) {
        this.fpMakerId = fpMakerId;
    }

    public Long getNpUserId() {
        return npUserId;
    }

    public void setNpUserId(Long npUserId) {
        this.npUserId = npUserId;
    }

    public Long getNpAssigneeId() {
        return npAssigneeId;
    }

    public void setNpAssigneeId(Long npAssigneeId) {
        this.npAssigneeId = npAssigneeId;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public ApplicationStatusMaster getApplicationStatusMaster() {
        return applicationStatusMaster;
    }

    public void setApplicationStatusMaster(ApplicationStatusMaster applicationStatusMaster) {
        this.applicationStatusMaster = applicationStatusMaster;
    }

    public Boolean getIsFinalLocked() {
        return isFinalLocked;
    }

    public void setIsFinalLocked(Boolean finalLocked) {
        isFinalLocked = finalLocked;
    }

    public Boolean getIsPrimaryLocked() {
        return isPrimaryLocked;
    }

    public void setIsPrimaryLocked(Boolean primaryLocked) {
        isPrimaryLocked = primaryLocked;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

	public Long getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(Long ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

    public ApplicationProposalMapping(Long proposalId) {
        this.proposalId = proposalId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getFinalLocked() {
        return isFinalLocked;
    }

    public void setFinalLocked(Boolean finalLocked) {
        isFinalLocked = finalLocked;
    }

    public Boolean getPrimaryLocked() {
        return isPrimaryLocked;
    }

    public void setPrimaryLocked(Boolean primaryLocked) {
        isPrimaryLocked = primaryLocked;
    }

    public Boolean getIsPrimaryUploadFilled() {
        return isPrimaryUploadFilled;
    }

    public void setIsPrimaryUploadFilled(Boolean primaryUploadFilled) {
        isPrimaryUploadFilled = primaryUploadFilled;
    }

    public Boolean getIsFinalDprUploadFilled() {
        return isFinalDprUploadFilled;
    }

    public void setIsFinalDprUploadFilled(Boolean finalDprUploadFilled) {
        isFinalDprUploadFilled = finalDprUploadFilled;
    }

    public Boolean getIsFinalUploadFilled() {
        return isFinalUploadFilled;
    }

    public void setIsFinalUploadFilled(Boolean finalUploadFilled) {
        isFinalUploadFilled = finalUploadFilled;
    }

    public Boolean getIsFinalMcqFilled() {
        return isFinalMcqFilled;
    }

    public void setIsFinalMcqFilled(Boolean finalMcqFilled) {
        isFinalMcqFilled = finalMcqFilled;
    }


    public Double getTenure() {
        return tenure;
    }

    public void setTenure(Double tenure) {
        this.tenure = tenure;
    }

    public String getDetailsFilledCount() {
        return detailsFilledCount;
    }

    public void setDetailsFilledCount(String detailsFilledCount) {
        this.detailsFilledCount = detailsFilledCount;
    }

    public String getPrimaryFilledCount() {
        return primaryFilledCount;
    }

    public void setPrimaryFilledCount(String primaryFilledCount) {
        this.primaryFilledCount = primaryFilledCount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Boolean getIsApplicantDetailsFilled() {
        return isApplicantDetailsFilled;
    }

    public void setIsApplicantDetailsFilled(Boolean applicantDetailsFilled) {
        isApplicantDetailsFilled = applicantDetailsFilled;
    }

    public Boolean getIsApplicantPrimaryFilled() {
        return isApplicantPrimaryFilled;
    }

    public void setIsApplicantPrimaryFilled(Boolean applicantPrimaryFilled) {
        isApplicantPrimaryFilled = applicantPrimaryFilled;
    }

    public Boolean getIsApplicantFinalFilled() {
        return isApplicantFinalFilled;
    }

    public void setIsApplicantFinalFilled(Boolean applicantFinalFilled) {
        isApplicantFinalFilled = applicantFinalFilled;
    }

    public String getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

	public Boolean getIsMcqSkipped() {
		return isMcqSkipped;
	}

	public void setIsMcqSkipped(Boolean isMcqSkipped) {
		this.isMcqSkipped = isMcqSkipped;
	}

    public Long getDenominationId() {
        return denominationId;
    }

    public void setDenominationId(Long denominationId) {
        this.denominationId = denominationId;
    }
}
