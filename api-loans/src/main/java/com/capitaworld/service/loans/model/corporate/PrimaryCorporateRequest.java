package com.capitaworld.service.loans.model.corporate;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryCorporateRequest extends LoanApplicationRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double loanAmount;
    private Double businessAssetAmount;
    private Double wcAmount;
    private Double otherAmt;
    private Boolean  haveCollateralSecurity;
    private Double collateralSecurityAmount;

    /*private Boolean isBusinessAssetChecked;
    private Boolean isWorkingCapitalChecked;
    private Boolean isOtherGeneralChecked;*/
    private Integer purposeOfLoanId;


    public PrimaryCorporateRequest() {
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
