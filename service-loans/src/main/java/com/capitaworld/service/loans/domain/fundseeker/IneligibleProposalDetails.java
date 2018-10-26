package com.capitaworld.service.loans.domain.fundseeker;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by KushalCW on 22-09-2018.
 */
@Entity
@Table(name="ineligible_proposal_details")
@NamedQuery(name="IneligibleProposalDetails.findAll", query="SELECT p FROM IneligibleProposalDetails p")
public class IneligibleProposalDetails implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="loan_amount")
    private Double loanAmount;

    @Column(name="user_org_id")
    private Long userOrgId;

    @Column(name="branch_id")
    private Long branchId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;
    
    @Column(name="is_sanctioned")
    private Boolean isSanctioned;
    
    @Column(name="is_Disbursed")
    private Boolean isDisbursed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Long getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
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

    public void setIsActive(Boolean active) {
        isActive = active;
    }

	public Boolean getIsSanctioned() {
		return isSanctioned;
	}

	public void setIsSanctioned(Boolean isSanctioned) {
		this.isSanctioned = isSanctioned;
	}

	public Boolean getIsDisbursed() {
		return isDisbursed;
	}

	public void setIsDisbursed(Boolean isDisbursed) {
		this.isDisbursed = isDisbursed;
	}
    
}