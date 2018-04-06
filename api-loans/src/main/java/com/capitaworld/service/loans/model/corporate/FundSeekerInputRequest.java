package com.capitaworld.service.loans.model.corporate;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;

import java.util.List;

public class FundSeekerInputRequest {

    private Long applicationId;

    private Long keyVericalFunding;

    private Long keyVerticalSector;

    private Long keyVerticalSubsector;

    ////////////////////

    private Double loanAmount;


    private Boolean isBusinessAssetChecked;

    private Double businessAssetAmount;

    private Boolean isWorkingCapitalChecked;

    private Double wcAmount;

    private Boolean isOtherGeneralChecked;

    private Double otherAmt;



    private Boolean haveCollateralSecurity;

    private Double collateralSecurityAmount;

    ///////////

    private List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList;
    private List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestsList;


    ///////////


    public Long getKeyVericalFunding() {
        return keyVericalFunding;
    }

    public void setKeyVericalFunding(Long keyVericalFunding) {
        this.keyVericalFunding = keyVericalFunding;
    }

    public Long getKeyVerticalSector() {
        return keyVerticalSector;
    }

    public void setKeyVerticalSector(Long keyVerticalSector) {
        this.keyVerticalSector = keyVerticalSector;
    }

    public Long getKeyVerticalSubsector() {
        return keyVerticalSubsector;
    }

    public void setKeyVerticalSubsector(Long keyVerticalSubsector) {
        this.keyVerticalSubsector = keyVerticalSubsector;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Boolean getBusinessAssetChecked() {
        return isBusinessAssetChecked;
    }

    public void setBusinessAssetChecked(Boolean businessAssetChecked) {
        isBusinessAssetChecked = businessAssetChecked;
    }

    public Double getBusinessAssetAmount() {
        return businessAssetAmount;
    }

    public void setBusinessAssetAmount(Double businessAssetAmount) {
        this.businessAssetAmount = businessAssetAmount;
    }

    public Boolean getWorkingCapitalChecked() {
        return isWorkingCapitalChecked;
    }

    public void setWorkingCapitalChecked(Boolean workingCapitalChecked) {
        isWorkingCapitalChecked = workingCapitalChecked;
    }

    public Double getWcAmount() {
        return wcAmount;
    }

    public void setWcAmount(Double wcAmount) {
        this.wcAmount = wcAmount;
    }

    public Boolean getOtherGeneralChecked() {
        return isOtherGeneralChecked;
    }

    public void setOtherGeneralChecked(Boolean otherGeneralChecked) {
        isOtherGeneralChecked = otherGeneralChecked;
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

    public List<FinancialArrangementsDetailRequest> getFinancialArrangementsDetailRequestsList() {
        return financialArrangementsDetailRequestsList;
    }

    public void setFinancialArrangementsDetailRequestsList(List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList) {
        this.financialArrangementsDetailRequestsList = financialArrangementsDetailRequestsList;
    }

    public List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetailRequestsList() {
        return directorBackgroundDetailRequestsList;
    }

    public void setDirectorBackgroundDetailRequestsList(List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestsList) {
        this.directorBackgroundDetailRequestsList = directorBackgroundDetailRequestsList;
    }
}
