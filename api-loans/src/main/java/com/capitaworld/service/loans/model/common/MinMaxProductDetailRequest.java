package com.capitaworld.service.loans.model.common;

public class MinMaxProductDetailRequest {

    private Long id;

    private Double minRoi;

    private Double maxRoi;

    private Double minTenure;

    private Double maxTenure;

    private Double minLoanAmount;

    private Double maxLoanAmount;

    private Double orgId;

    private Long loanTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMinRoi() {
        return minRoi;
    }

    public void setMinRoi(Double minRoi) {
        this.minRoi = minRoi;
    }

    public Double getMaxRoi() {
        return maxRoi;
    }

    public void setMaxRoi(Double maxRoi) {
        this.maxRoi = maxRoi;
    }

    public Double getMinTenure() {
        return minTenure;
    }

    public void setMinTenure(Double minTenure) {
        this.minTenure = minTenure;
    }

    public Double getMaxTenure() {
        return maxTenure;
    }

    public void setMaxTenure(Double maxTenure) {
        this.maxTenure = maxTenure;
    }

    public Double getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(Double minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public Double getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(Double maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public Double getOrgId() {
        return orgId;
    }

    public void setOrgId(Double orgId) {
        this.orgId = orgId;
    }

    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }
}
