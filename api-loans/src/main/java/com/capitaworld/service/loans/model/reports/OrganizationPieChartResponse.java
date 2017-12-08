package com.capitaworld.service.loans.model.reports;

/**
 * Created by dhaval on 02-Dec-17.
 */
public class OrganizationPieChartResponse {
    String approvedFiles;
    String underProcessFiles;
    String approvedAmount;
    String underProcessAmount;
    String branchPieResponce;

    public String getApprovedFiles() {
        return approvedFiles;
    }

    public void setApprovedFiles(String approvedFiles) {
        this.approvedFiles = approvedFiles;
    }

    public String getUnderProcessFiles() {
        return underProcessFiles;
    }

    public void setUnderProcessFiles(String underProcessFiles) {
        this.underProcessFiles = underProcessFiles;
    }

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getUnderProcessAmount() {
        return underProcessAmount;
    }

    public void setUnderProcessAmount(String underProcessAmount) {
        this.underProcessAmount = underProcessAmount;
    }

    public String getBranchPieResponce() {
        return branchPieResponce;
    }

    public void setBranchPieResponce(String branchPieResponce) {
        this.branchPieResponce = branchPieResponce;
    }

    @Override
    public String toString() {
        return "OrganizationPieChartResponse{" +
                "approvedFiles='" + approvedFiles + '\'' +
                ", underProcessFiles='" + underProcessFiles + '\'' +
                ", approvedAmount='" + approvedAmount + '\'' +
                ", underProcessAmount='" + underProcessAmount + '\'' +
                ", branchPieResponce='" + branchPieResponce + '\'' +
                '}';
    }
}
