package com.capitaworld.service.loans.domain.colending;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dhaval.panchal on 10-Sep-19.
 */

@Entity
@Table(name="recommend_detail")
public class RecommendDetail {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="value")
    private Double value;

    @Column(name="tenure")
    private Double tenure;

    @Column(name="roi")
    private Double roi;

    @Column(name="processing_fee")
    private Double processingFee;

    @Column(name="remark")
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getTenure() {
        return tenure;
    }

    public void setTenure(Double tenure) {
        this.tenure = tenure;
    }

    public Double getRoi() {
        return roi;
    }

    public void setRoi(Double roi) {
        this.roi = roi;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
