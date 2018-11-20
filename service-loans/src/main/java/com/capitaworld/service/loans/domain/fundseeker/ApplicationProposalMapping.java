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
    @Column(name = "proposal_mapping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proposalId;

    @Column(name = "application_id")
    private Long applicationId;

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

    @Column(name = "modified_by")
    private Long modifiedBy;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "status")
    private ApplicationStatusMaster applicationStatusMaster;

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
}
