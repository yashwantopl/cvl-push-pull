package com.capitaworld.service.loans.domain.fundprovider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
/**
 * The persistent class for the proposal_details database table.
 *
 */
@Entity
@Table(name="proposal_details")
@NamedQuery(name="ProposalDetails.findAll", query="SELECT p FROM ProposalDetails p")
public class ProposalDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="fp_product_id")
    private Long fpProductId;

    @Column(name="application_id")
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name="proposal_status_id")
    private ProposalStatusMaster proposalStatusId;

    @Column(name="is_proposal_auto")
    private Boolean isProposalAuto;

    @Column(name="proposal_stage")
    private Long proposalStageId;

    @Column(name="initiated_by")
    private Long initiatedBy;

    @Column(name="last_action_performed_by")
    private Long lastActionPerformedBy;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pending_proposal_update_date")
    private Date pendingProposalUpdateDate;

    @Column(name="user_score")
    private Double userScore;

    @Column(name="user_org_id")
    private Long userOrgId;

    @Column(name="branch_id")
    private Long branchId;

    @Column(name="el_amount")
    private Double elAmount;

    @Column(name="el_tenure")
    private Double elTenure;

    @Column(name="el_roi")
    private Double elRoi;

    @Column(name="assign_by")
    private Long assignBy;

    @Column(name="assign_branch_to")
    private Long assignBranchTo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "assign_date")
    private Date assignDate;

    @Column(name="emi")
    private Double emi;

    @Column(name="processing_fee")
    private Double processingFee;

    public Double getEmi() {
        return emi;
    }

    public void setEmi(Double emi) {
        this.emi = emi;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getElAmount() {
        return elAmount;
    }


    public void setElAmount(Double elAmount) {
        this.elAmount = elAmount;
    }


    public Double getElTenure() {
        return elTenure;
    }


    public void setElTenure(Double elTenure) {
        this.elTenure = elTenure;
    }


    public Double getElRoi() {
        return elRoi;
    }


    public void setElRoi(Double elRoi) {
        this.elRoi = elRoi;
    }


    public Long getId() {
        return id;
    }


    public Long getProposalStageId() {
        return proposalStageId;
    }

    public void setProposalStageId(Long proposalStageId) {
        this.proposalStageId = proposalStageId;
    }

    public Long getInitiatedBy() {
        return initiatedBy;
    }


    public void setInitiatedBy(Long initiatedBy) {
        this.initiatedBy = initiatedBy;
    }


    public Long getLastActionPerformedBy() {
        return lastActionPerformedBy;
    }


    public void setLastActionPerformedBy(Long lastActionPerformedBy) {
        this.lastActionPerformedBy = lastActionPerformedBy;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public ProposalStatusMaster getProposalStatusId() {
        return proposalStatusId;
    }

    public void setProposalStatusId(ProposalStatusMaster proposalStatusId) {
        this.proposalStatusId = proposalStatusId;
    }

    public Boolean getIsProposalAuto() {
        return isProposalAuto;
    }

    public void setIsProposalAuto(Boolean isProposalAuto) {
        this.isProposalAuto = isProposalAuto;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public Date getPendingProposalUpdateDate() {
        return pendingProposalUpdateDate;
    }


    public void setPendingProposalUpdateDate(Date pendingProposalUpdateDate) {
        this.pendingProposalUpdateDate = pendingProposalUpdateDate;
    }


    public Long getUserOrgId() {
        return userOrgId;
    }


    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
    }


    public Double getUserScore() {
        return userScore;
    }


    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }


    public Long getAssignBy() {
        return assignBy;
    }


    public void setAssignBy(Long assignBy) {
        this.assignBy = assignBy;
    }


    public Long getAssignBranchTo() {
        return assignBranchTo;
    }


    public void setAssignBranchTo(Long assignBranchTo) {
        this.assignBranchTo = assignBranchTo;
    }


    public Date getAssignDate() {
        return assignDate;
    }


    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }







}