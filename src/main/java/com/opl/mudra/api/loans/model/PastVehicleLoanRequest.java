package com.opl.mudra.api.loans.model;

import java.io.Serializable;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class PastVehicleLoanRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private Long applicationId;
    private String lenderName;
    private Double sanctionAmt;
    private Double outstandingAmt;
    

    public Long getId() {
        return id;
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

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public Double getSanctionAmt() {
        return sanctionAmt;
    }

    public void setSanctionAmt(Double sanctionAmt) {
        this.sanctionAmt = sanctionAmt;
    }

    public Double getOutstandingAmt() {
        return outstandingAmt;
    }

    public void setOutstandingAmt(Double outstandingAmt) {
        this.outstandingAmt = outstandingAmt;
    }

    
}
