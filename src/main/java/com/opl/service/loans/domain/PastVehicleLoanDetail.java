package com.opl.service.loans.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */
@Entity
@Table(name="past_vehicle_loan_detail")
public class PastVehicleLoanDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="lender_name")
    private String lenderName;

    @Column(name="sanction_amt")
    private Double sanctionAmt;

    @Column(name="outstanding_amt")
    private Double outstandingAmt;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="is_active")
    private Boolean isActive;

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

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public Double getSanctionAmt() {
        return sanctionAmt;
    }

    public void setSanctionAmt(Double sanctionAmt) {
        this.sanctionAmt = sanctionAmt;
    }

    public Double getOutstandingAmt() {
        return outstandingAmt;
    }

    public void setOutstandingAmt(Double outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
