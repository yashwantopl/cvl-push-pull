package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditRatingOrganizationDetailResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double amount;

    private String creditRatingFund;

    private String creditRatingOption;

    private String creditRatingTerm;

    private String facilityName;

    private String ratingAgency;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCreditRatingFund() {
        return creditRatingFund;
    }

    public void setCreditRatingFund(String creditRatingFund) {
        this.creditRatingFund = creditRatingFund;
    }

    public String getCreditRatingOption() {
        return creditRatingOption;
    }

    public void setCreditRatingOption(String creditRatingOption) {
        this.creditRatingOption = creditRatingOption;
    }

    public String getCreditRatingTerm() {
        return creditRatingTerm;
    }

    public void setCreditRatingTerm(String creditRatingTerm) {
        this.creditRatingTerm = creditRatingTerm;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getRatingAgency() {
        return ratingAgency;
    }

    public void setRatingAgency(String ratingAgency) {
        this.ratingAgency = ratingAgency;
    }
}
