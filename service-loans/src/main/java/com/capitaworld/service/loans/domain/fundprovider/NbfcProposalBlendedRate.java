package com.capitaworld.service.loans.domain.fundprovider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The persistent class for the proposal_details database table.
 *
 */
@Entity
@Table(name="nbfc_proposal_blended_rate")
@NamedQuery(name="NbfcProposalBlendedRate.findAll", query="SELECT p FROM NbfcProposalBlendedRate p")
public class NbfcProposalBlendedRate implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="nbfc_org_id")
    private Long nbfcOrgId;

    @Column(name="bank_org_id")
    private Long bankOrgId;

    @Column(name="bl_exisiting_amount")
    private Double existingLoanAmount;

    @Column(name="bl_additional_amount")
    private Double additionalLoanAmount;

    @Column(name="bl_amount")
    private Double elAmount;

    @Column(name="bl_tenure")
    private Double elTenure;

    @Column(name="bl_roi")
    private Double elRoi;

    @Column(name="bl_emi")
    private Double emi;
    
    @Column(name="bl_processing_fee")
    private Double processingFee;

    public Double getEmi() {
        return emi;
    }

    public void setEmi(Double emi) {
        this.emi = emi;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getElAmount() {
        return elAmount;
    }


    public void setElAmount(Double elAmount) {
        this.elAmount = elAmount;
    }


    public Double getElTenure() {
        return elTenure;
    }


    public void setElTenure(Double elTenure) {
        this.elTenure = elTenure;
    }


    public Double getElRoi() {
        return elRoi;
    }


    public void setElRoi(Double elRoi) {
        this.elRoi = elRoi;
    }


    public Long getId() {
        return id;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Double getExistingLoanAmount() {
        return existingLoanAmount;
    }

    public void setExistingLoanAmount(Double existingLoanAmount) {
        this.existingLoanAmount = existingLoanAmount;
    }

    public Double getAdditionalLoanAmount() {
        return additionalLoanAmount;
    }

    public void setAdditionalLoanAmount(Double additionalLoanAmount) {
        this.additionalLoanAmount = additionalLoanAmount;
    }

    public Long getNbfcOrgId() {
        return nbfcOrgId;
    }

    public void setNbfcOrgId(Long nbfcOrgId) {
        this.nbfcOrgId = nbfcOrgId;
    }

    public Long getBankOrgId() {
        return bankOrgId;
    }

    public void setBankOrgId(Long bankOrgId) {
        this.bankOrgId = bankOrgId;
    }
}