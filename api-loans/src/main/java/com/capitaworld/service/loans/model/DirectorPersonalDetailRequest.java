package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorPersonalDetailRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer maritalStatus;
    private Integer spouseDetail;
    private Integer noOfChildren;
    private Integer owningHouse;
    private Integer assessedForIt;
    private Integer haveLiPolicy;
    private String spouseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getSpouseDetail() {
        return spouseDetail;
    }

    public void setSpouseDetail(Integer spouseDetail) {
        this.spouseDetail = spouseDetail;
    }

    public Integer getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(Integer noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public Integer getOwningHouse() {
        return owningHouse;
    }

    public void setOwningHouse(Integer owningHouse) {
        this.owningHouse = owningHouse;
    }

    public Integer getAssessedForIt() {
        return assessedForIt;
    }

    public void setAssessedForIt(Integer assessedForIt) {
        this.assessedForIt = assessedForIt;
    }

    public Integer getHaveLiPolicy() {
        return haveLiPolicy;
    }

    public void setHaveLiPolicy(Integer haveLiPolicy) {
        this.haveLiPolicy = haveLiPolicy;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
}
