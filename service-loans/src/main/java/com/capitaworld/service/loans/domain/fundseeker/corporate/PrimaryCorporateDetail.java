package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fs_corporate_primary_details")
@NamedQuery(name = "PrimaryCorporateDetail.findAll", query = "SELECT p FROM PrimaryCorporateDetail p")
public class PrimaryCorporateDetail extends LoanApplicationMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "application_id")
    private LoanApplicationMaster applicationId;

    @Column(name = "loan_amount")
    private Double loanAmount;

    @Column(name = "business_asset_amt")
    private Double businessAssetAmount;

    @Column(name = "wc_amt")
    private Double wcAmount;

    @Column(name = "other_amt")
    private Double otherAmt;

    @Column(name = "have_collateral_security")
    private Boolean haveCollateralSecurity;

    @Column(name = "collateral_security_amt")
    private Double collateralSecurityAmount;

    /*@Column(name = "is_business_asset_checked")
    private Boolean isBusinessAssetChecked;

    @Column(name = "is_working_capital_checked")
    private Boolean isWorkingCapitalChecked;

    @Column(name = "is_other_general_checked")
    private Boolean isOtherGeneralChecked;*/

    @Column(name = "purpose_of_loan_id")
    private Integer purposeOfLoanId;


    public PrimaryCorporateDetail() {
    }

    public LoanApplicationMaster getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(LoanApplicationMaster applicationId) {
        this.applicationId = applicationId;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getBusinessAssetAmount() {
        return businessAssetAmount;
    }

    public void setBusinessAssetAmount(Double businessAssetAmount) {
        this.businessAssetAmount = businessAssetAmount;
    }

    public Double getWcAmount() {
        return wcAmount;
    }

    public void setWcAmount(Double wcAmount) {
        this.wcAmount = wcAmount;
    }

    public Double getOtherAmt() {
        return otherAmt;
    }

    public void setOtherAmt(Double otherAmt) {
        this.otherAmt = otherAmt;
    }


    public Boolean getHaveCollateralSecurity() {
        return haveCollateralSecurity;
    }

    public void setHaveCollateralSecurity(Boolean haveCollateralSecurity) {
        this.haveCollateralSecurity = haveCollateralSecurity;
    }

    public Double getCollateralSecurityAmount() {
        return collateralSecurityAmount;
    }

    public void setCollateralSecurityAmount(Double collateralSecurityAmount) {
        this.collateralSecurityAmount = collateralSecurityAmount;
    }

    public Integer getPurposeOfLoanId() {
        return purposeOfLoanId;
    }

    public void setPurposeOfLoanId(Integer purposeOfLoanId) {
        this.purposeOfLoanId = purposeOfLoanId;
    }
}
