package com.capitaworld.service.loans.domain.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_emp_self_employed_type")
public class EmpSelfEmployedType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "application_id")
    private LoanApplicationMaster applicationId;

    @OneToOne
    @JoinColumn(name = "proposal_mapping_id")
    private ApplicationProposalMapping proposalId;

    @Column(name="type_of_ownership")
    private Integer typeOfOwnership;

    @Column(name="number_of_dir_partner")
    private Integer numberOfDirPartner;

    @Column(name="share_hodling")
    private Integer shareHolding;

    @Column(name="name_of_dir_partner")
    private String nameOfDirPartner;

    @Column(name="trade_license_no")
    private String tradeLicenseNo;

    @Column(name="name_of_entity")
    private String nameOfEntity;

    @Column(name="trade_license_exp_date")
    private Date tradeLicenseExpDate;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="name_of_poa_holder")
    private String nameOfPOAHolder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoanApplicationMaster getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(LoanApplicationMaster applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getTypeOfOwnership() {
        return typeOfOwnership;
    }

    public void setTypeOfOwnership(Integer typeOfOwnership) {
        this.typeOfOwnership = typeOfOwnership;
    }

    public Integer getNumberOfDirPartner() {
        return numberOfDirPartner;
    }

    public void setNumberOfDirPartner(Integer numberOfDirPartner) {
        this.numberOfDirPartner = numberOfDirPartner;
    }

    public Integer getShareHolding() {
        return shareHolding;
    }

    public void setShareHolding(Integer shareHolding) {
        this.shareHolding = shareHolding;
    }

    public String getNameOfDirPartner() {
        return nameOfDirPartner;
    }

    public void setNameOfDirPartner(String nameOfDirPartner) {
        this.nameOfDirPartner = nameOfDirPartner;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(String tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public void setNameOfEntity(String nameOfEntity) {
        this.nameOfEntity = nameOfEntity;
    }

    public Date getTradeLicenseExpDate() {
        return tradeLicenseExpDate;
    }

    public void setTradeLicenseExpDate(Date tradeLicenseExpDate) {
        this.tradeLicenseExpDate = tradeLicenseExpDate;
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

    public ApplicationProposalMapping getProposalId() {
        return proposalId;
    }

    public void setProposalId(ApplicationProposalMapping proposalId) {
        this.proposalId = proposalId;
    }

    public String getNameOfPOAHolder() {
        return nameOfPOAHolder;
    }

    public void setNameOfPOAHolder(String nameOfPOAHolder) {
        this.nameOfPOAHolder = nameOfPOAHolder;
    }
}

