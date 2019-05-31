package com.capitaworld.service.loans.domain.common;


import javax.persistence.*;

@Entity
@Table(name="min_max_product_detail")
public class MinMaxProductDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="min_roi")
    private Double minRoi;

    @Column(name="max_roi")
    private Double maxRoi;

    @Column(name="min_tenure")
    private Double minTenure;

    @Column(name="max_tenure")
    private Double maxTenure;

    @Column(name="min_loan_amount")
    private Double minLoanAmount;

    @Column(name="max_loan_amount")
    private Double maxLoanAmount;

    @Column(name="org_id")
    private Long orgId;

    @Column(name="loan_type_id")
    private Long loanTypeId;

    @Column(name="is_active")
    private Boolean isActive;


    public Long getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}

