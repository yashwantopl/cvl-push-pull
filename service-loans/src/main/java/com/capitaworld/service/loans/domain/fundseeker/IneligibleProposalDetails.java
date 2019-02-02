package com.capitaworld.service.loans.domain.fundseeker;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    @Column(name="created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;
    
    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="is_active")
    private Boolean isActive;
    
    @Column(name="is_sanctioned")
    private Boolean isSanctioned;
    
    @Column(name="is_Disbursed")
    private Boolean isDisbursed;
    
    @Column(name="status")
    private Integer status;
    
    @Column(name="reason")
    private String reason;
    
    @Column(name="gstin")
    private String gstin;
    
    @Column(name = "business_type_id")
	private Integer businessTypeId;
    

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
}