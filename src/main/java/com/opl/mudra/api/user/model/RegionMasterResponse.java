package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegionMasterResponse implements Serializable{

    private Long id;
    private String regionName;
    private String regionCode;

    //constructor
    public RegionMasterResponse() {
        // Do nothing because of X and Y.
    }

    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public String toString() {
        return "RegionMasterResponse{" +
                "id=" + id +
                ", regionName='" + regionName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                '}';
    }
}
