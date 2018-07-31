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
    private Boolean owningHouse;
    private Boolean assessedForIt;
    private Boolean haveLiPolicy;

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

    public Boolean getOwningHouse() {
        return owningHouse;
    }

    public void setOwningHouse(Boolean owningHouse) {
        this.owningHouse = owningHouse;
    }

    public Boolean getAssessedForIt() {
        return assessedForIt;
    }

    public void setAssessedForIt(Boolean assessedForIt) {
        this.assessedForIt = assessedForIt;
    }

    public Boolean getHaveLiPolicy() {
        return haveLiPolicy;
    }

    public void setHaveLiPolicy(Boolean haveLiPolicy) {
        this.haveLiPolicy = haveLiPolicy;
    }
}
