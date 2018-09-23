package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * Created by KushalCW on 22-09-2018.
 */
public class InEligibleProposalDetailsRequest implements Serializable {


    private Long applicationId;

    private Double loanAmount;

    private Long userOrgId;

    private Long branchId;

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
}
