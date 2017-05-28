package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 26-May-17.
 */
public class DriverForFutureGrowthResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private String firstString;

    private String secondString;

    private String thirdString;

    private String forthString;

    public DriverForFutureGrowthResponse(String firstString, String secondString, String thirdString, String forthString) {
        this.firstString = firstString;
        this.secondString = secondString;
        this.thirdString = thirdString;
        this.forthString = forthString;
    }

    public DriverForFutureGrowthResponse() {
    }

    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public String getThirdString() {
        return thirdString;
    }

    public void setThirdString(String thirdString) {
        this.thirdString = thirdString;
    }

    public String getForthString() {
        return forthString;
    }

    public void setForthString(String forthString) {
        this.forthString = forthString;
    }
}
