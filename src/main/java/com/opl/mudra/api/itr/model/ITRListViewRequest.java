package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author jaimin.darji
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRListViewRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long masterId;
    private Long profileId;
    private Long xmlMstrId;
    private String companyName;
    private String year;
    private String fileName;
    private Double totalTurnover;
    private Double totalProfit;

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getXmlMstrId() {
        return xmlMstrId;
    }

    public void setXmlMstrId(Long xmlMstrId) {
        this.xmlMstrId = xmlMstrId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getTotalTurnover() {
        return totalTurnover;
    }

    public void setTotalTurnover(Double totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }
}
