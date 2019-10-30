package com.capitaworld.service.loans.domain.colending;

import javax.persistence.*;

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
}
