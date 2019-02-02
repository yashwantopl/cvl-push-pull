package com.capitaworld.service.loans.domain.fundseeker;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * created on 01-02-2019
 * 
 * @author jaimin.darji
 *
 */
@Entity
@Table(name = "ineligible_proposal_transfer_history")
public class IneligibleProposalTransferHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="ineligible_proposal_id")
    private Long ineligibleProposalid;

	@Column(name="application_id")
	private Long applicationId;
	
	@Column(name="new_branch_id")
    private Long newBranchId;
	
	@Column(name="old_branch_id")
    private Long oldBranchId;
	
	@Column(name="reason")
    private String reason;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;
    
    @Column(name="created_by")
    private Long createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIneligibleProposalid() {
		return ineligibleProposalid;
	}

	public void setIneligibleProposalid(Long ineligibleProposalid) {
		this.ineligibleProposalid = ineligibleProposalid;
	}

	public Long getNewBranchId() {
		return newBranchId;
	}

	public void setNewBranchId(Long newBranchId) {
		this.newBranchId = newBranchId;
	}

	public Long getOldBranchId() {
		return oldBranchId;
	}

	public void setOldBranchId(Long oldBranchId) {
		this.oldBranchId = oldBranchId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "IneligibleProposalTransferHistory [id=" + id + ", ineligibleProposalid=" + ineligibleProposalid
				+ ", newBranchId=" + newBranchId + ", oldBranchId=" + oldBranchId + ", reason=" + reason
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + "]";
	}
	
}
