package com.capitaworld.service.loans.domain.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fs_emp_salaried_type")
public class EmpSalariedType implements Serializable{
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

    @Column(name="bonus_per_annum")
    private Integer bonusPerAnnum;

    @Column(name="incentive_per_annum")
    private Integer incentivePerAnnum;

    @Column(name="tax_paid_last_year")
    private Integer taxPaidLastYear;

    @Column(name="created_date")
    private Date createdDate;

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

    public LoanApplicationMaster getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(LoanApplicationMaster applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getBonusPerAnnum() {
        return bonusPerAnnum;
    }

    public void setBonusPerAnnum(Integer bonusPerAnnum) {
        this.bonusPerAnnum = bonusPerAnnum;
    }

    public Integer getIncentivePerAnnum() {
        return incentivePerAnnum;
    }

    public void setIncentivePerAnnum(Integer incentivePerAnnum) {
        this.incentivePerAnnum = incentivePerAnnum;
    }

    public Integer getTaxPaidLastYear() {
        return taxPaidLastYear;
    }

    public void setTaxPaidLastYear(Integer taxPaidLastYear) {
        this.taxPaidLastYear = taxPaidLastYear;
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
}
