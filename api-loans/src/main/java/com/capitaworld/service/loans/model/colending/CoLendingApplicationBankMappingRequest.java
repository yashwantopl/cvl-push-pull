package com.capitaworld.service.loans.model.colending;

import java.util.List;

/**
 * Created by dhaval.panchal on 27-Aug-19.
 */
public class CoLendingApplicationBankMappingRequest {

    private Long applicationId;

    private List<Integer> bankOrgIdList;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public List<Integer> getBankOrgIdList() {
        return bankOrgIdList;
    }

    public void setBankOrgIdList(List<Integer> bankOrgIdList) {
        this.bankOrgIdList = bankOrgIdList;
    }

    @Override
    public String toString() {
        return "CoLendingApplicationBankMappingRequest{" +
                "applicationId=" + applicationId +
                ", bankOrgIdList=" + bankOrgIdList +
                '}';
    }
}
