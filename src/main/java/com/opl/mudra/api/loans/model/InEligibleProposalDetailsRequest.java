package com.opl.mudra.api.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by KushalCW on 22-09-2018.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InEligibleProposalDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
    private Long applicationId;

    private Double loanAmount;

    private Long userOrgId;

    private Long branchId;
    
    private Boolean isSanctioned;
    
    private Boolean isDisbursed;

    private Long userId;

    private Integer status;
    
    private Integer sanctionStatus;

    private String reason;

    private String reOpenReason;

    private Integer businessTypeId;

    private Long ineligibleProposalId;
    
	private String addiFields;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}


	public Long getIneligibleProposalId() {
		return ineligibleProposalId;
	}

	public void setIneligibleProposalId(Long ineligibleProposalId) {
		this.ineligibleProposalId = ineligibleProposalId;
	}

	public String getReOpenReason() {
		return reOpenReason;
	}

	public void setReOpenReason(String reOpenReason) {
		this.reOpenReason = reOpenReason;
	}
	

	public String getAddiFields() {
		return addiFields;
	}

	public void setAddiFields(String addiFields) {
		this.addiFields = addiFields;
	}

	public Integer getSanctionStatus() {
		return sanctionStatus;
	}

	public void setSanctionStatus(Integer sanctionStatus) {
		this.sanctionStatus = sanctionStatus;
	}

	@Override
	public String toString() {
		return "InEligibleProposalDetailsRequest [applicationId=" + applicationId + ", loanAmount=" + loanAmount
				+ ", userOrgId=" + userOrgId + ", branchId=" + branchId + ", isSanctioned=" + isSanctioned
				+ ", isDisbursed=" + isDisbursed + ", userId=" + userId + ", status=" + status + ", reason=" + reason
				+ ", businessTypeId=" + businessTypeId + ", ineligibleProposalId=" + ineligibleProposalId + "]";
	}

}
