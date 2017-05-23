package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OwnershipDetailResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String remarks;

    private String shareHoldingCategory;

    private Double stackPercentage;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShareHoldingCategory() {
        return shareHoldingCategory;
    }

    public void setShareHoldingCategory(String shareHoldingCategory) {
        this.shareHoldingCategory = shareHoldingCategory;
    }

    public Double getStackPercentage() {
        return stackPercentage;
    }

    public void setStackPercentage(Double stackPercentage) {
        this.stackPercentage = stackPercentage;
    }
}
