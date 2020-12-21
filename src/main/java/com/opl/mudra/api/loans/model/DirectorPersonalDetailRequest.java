package com.opl.mudra.api.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Integer idProof;
    private Integer dependent;
    private Boolean isSameAddIdProof;
    private Integer addressYears;
    private Integer otherIncomeSource;
    private Integer certificationCourse;
    private Integer ongoingMudraLoan;
    private Integer isWorkAndResidenceSamePlace;
    private Boolean haveOwnCommercialProperty;
    
    
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

	public Integer getIdProof() {
		return idProof;
	}

	public void setIdProof(Integer idProof) {
		this.idProof = idProof;
	}

	public Integer getDependent() {
		return dependent;
	}

	public void setDependent(Integer dependent) {
		this.dependent = dependent;
	}


	public Boolean getIsSameAddIdProof() {
		return isSameAddIdProof;
	}

	public void setIsSameAddIdProof(Boolean isSameAddIdProof) {
		this.isSameAddIdProof = isSameAddIdProof;
	}

	public Integer getAddressYears() {
		return addressYears;
	}

	public void setAddressYears(Integer addressYears) {
		this.addressYears = addressYears;
	}

	public Integer getOtherIncomeSource() {
		return otherIncomeSource;
	}

	public void setOtherIncomeSource(Integer otherIncomeSource) {
		this.otherIncomeSource = otherIncomeSource;
	}

	public Integer getOngoingMudraLoan() {
		return ongoingMudraLoan;
	}

	public void setOngoingMudraLoan(Integer ongoingMudraLoan) {
		this.ongoingMudraLoan = ongoingMudraLoan;
	}

	public Integer getCertificationCourse() {
		return certificationCourse;
	}

	public void setCertificationCourse(Integer certificationCourse) {
		this.certificationCourse = certificationCourse;
	}

	public Integer getIsWorkAndResidenceSamePlace() {
		return isWorkAndResidenceSamePlace;
	}

	public void setIsWorkAndResidenceSamePlace(Integer isWorkAndResidenceSamePlace) {
		this.isWorkAndResidenceSamePlace = isWorkAndResidenceSamePlace;
	}

	public Boolean getHaveOwnCommercialProperty() {
		return haveOwnCommercialProperty;
	}

	public void setHaveOwnCommercialProperty(Boolean haveOwnCommercialProperty) {
		this.haveOwnCommercialProperty = haveOwnCommercialProperty;
	}
		
}
