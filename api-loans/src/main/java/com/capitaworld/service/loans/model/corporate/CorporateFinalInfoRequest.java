package com.capitaworld.service.loans.model.corporate;

import com.capitaworld.service.loans.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateFinalInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long clientId;

    private Long applicationId;

    private Address firstAddress;

    private Address secondAddress;

    private Boolean sameAs;

    private Boolean isFinalDetailsFilled;

    private String aadhar;

    private Integer creditRatingId;

    private Double contLiabilityFyAmt;
    private Double contLiabilitySyAmt;
    private Double contLiabilityTyAmt;
    private Integer contLiabilityYear;

    private Boolean notApplicable;

    private String aboutUs;

    private Double totalCostOfEstimate;
    private Double totalMeansOfFinance;
    private Double sharePriceFace;
    private Double sharePriceMarket;
    private Double totalCollateralDetails;

    public Boolean getSameAs() {
        return sameAs;
    }

    public void setSameAs(Boolean sameAs) {
        this.sameAs = sameAs;
    }

    public Address getFirstAddress() {
        return firstAddress;
    }

    public void setFirstAddress(Address firstAddress) {
        this.firstAddress = firstAddress;
    }

    public Address getSecondAddress() {
        return secondAddress;
    }

    public void setSecondAddress(Address secondAddress) {
        this.secondAddress = secondAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Boolean getFinalDetailsFilled() {
        return isFinalDetailsFilled;
    }

    public void setFinalDetailsFilled(Boolean finalDetailsFilled) {
        isFinalDetailsFilled = finalDetailsFilled;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public Integer getCreditRatingId() {
        return creditRatingId;
    }

    public void setCreditRatingId(Integer creditRatingId) {
        this.creditRatingId = creditRatingId;
    }

    public Double getContLiabilityFyAmt() {
        return contLiabilityFyAmt;
    }

    public void setContLiabilityFyAmt(Double contLiabilityFyAmt) {
        this.contLiabilityFyAmt = contLiabilityFyAmt;
    }

    public Double getContLiabilitySyAmt() {
        return contLiabilitySyAmt;
    }

    public void setContLiabilitySyAmt(Double contLiabilitySyAmt) {
        this.contLiabilitySyAmt = contLiabilitySyAmt;
    }

    public Double getContLiabilityTyAmt() {
        return contLiabilityTyAmt;
    }

    public void setContLiabilityTyAmt(Double contLiabilityTyAmt) {
        this.contLiabilityTyAmt = contLiabilityTyAmt;
    }

    public Integer getContLiabilityYear() {
        return contLiabilityYear;
    }

    public void setContLiabilityYear(Integer contLiabilityYear) {
        this.contLiabilityYear = contLiabilityYear;
    }


    public Boolean getNotApplicable() {
        return notApplicable;
    }

    public void setNotApplicable(Boolean notApplicable) {
        this.notApplicable = notApplicable;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public Double getTotalCostOfEstimate() {
        return totalCostOfEstimate;
    }

    public void setTotalCostOfEstimate(Double totalCostOfEstimate) {
        this.totalCostOfEstimate = totalCostOfEstimate;
    }

    public Double getTotalMeansOfFinance() {
        return totalMeansOfFinance;
    }

    public void setTotalMeansOfFinance(Double totalMeansOfFinance) {
        this.totalMeansOfFinance = totalMeansOfFinance;
    }

    public Double getSharePriceFace() {
        return sharePriceFace;
    }

    public void setSharePriceFace(Double sharePriceFace) {
        this.sharePriceFace = sharePriceFace;
    }

    public Double getSharePriceMarket() {
        return sharePriceMarket;
    }

    public void setSharePriceMarket(Double sharePriceMarket) {
        this.sharePriceMarket = sharePriceMarket;
    }

    public Double getTotalCollateralDetails() {
        return totalCollateralDetails;
    }

    public void setTotalCollateralDetails(Double totalCollateralDetails) {
        this.totalCollateralDetails = totalCollateralDetails;
    }
}
