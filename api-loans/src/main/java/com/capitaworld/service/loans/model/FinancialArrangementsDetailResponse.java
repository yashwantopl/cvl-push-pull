package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialArrangementsDetailResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private Double amount;

    private String facilityNature;

    private String financialInstitutionName;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFacilityNature() {
        return facilityNature;
    }

    public void setFacilityNature(String facilityNature) {
        this.facilityNature = facilityNature;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }
}
