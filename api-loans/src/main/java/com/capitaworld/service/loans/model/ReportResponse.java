package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * Created by dhaval on 21-Dec-17.
 */
public class ReportResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long applicationId;
    private Long userId;
    private Long fpProductId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }
}
