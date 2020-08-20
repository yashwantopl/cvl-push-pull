package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * Created by Kushal on 11-07-2018.
 */
public class BranchLoanSystemMasterRequest implements Serializable{

    private Long id;

    private String loanSystemName;

    private Double minSanctionAmt;

    private Double maxSanctionAmt;

    private Boolean isActive;

    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoanSystemName() {
        return loanSystemName;
    }

    public void setLoanSystemName(String loanSystemName) {
        this.loanSystemName = loanSystemName;
    }

    public Double getMinSanctionAmt() {
        return minSanctionAmt;
    }

    public void setMinSanctionAmt(Double minSanctionAmt) {
        this.minSanctionAmt = minSanctionAmt;
    }

    public Double getMaxSanctionAmt() {
        return maxSanctionAmt;
    }

    public void setMaxSanctionAmt(Double maxSanctionAmt) {
        this.maxSanctionAmt = maxSanctionAmt;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
}
