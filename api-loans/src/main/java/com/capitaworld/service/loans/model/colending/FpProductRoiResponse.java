package com.capitaworld.service.loans.model.colending;

/**
 * Created by dhaval.panchal on 12-Nov-19.
 */
public class FpProductRoiResponse {

    private Long minRoi;

    private Long maxRoi;


    public Long getMinRoi() {
        return minRoi;
    }

    public void setMinRoi(Long minRoi) {
        this.minRoi = minRoi;
    }

    public Long getMaxRoi() {
        return maxRoi;
    }

    public void setMaxRoi(Long maxRoi) {
        this.maxRoi = maxRoi;
    }
}
