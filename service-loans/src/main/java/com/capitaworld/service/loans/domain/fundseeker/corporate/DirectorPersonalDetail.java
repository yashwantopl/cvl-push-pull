package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fs_corporate_director_personal_detail")
public class DirectorPersonalDetail extends AuditActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="marital_status")
    private Integer maritalStatus;

    @Column(name="spouse_detail")
    private Integer spouseDetail;

    @Column(name="no_of_children")
    private Integer noOfChildren;

    @Column(name="owning_house")
    private Integer owningHouse;

    @Column(name="assessed_for_it")
    private Integer assessedForIt;

    @Column(name="have_li_policy")
    private Integer haveLiPolicy;

    @Column(name="spouse_name")
    private String spouseName;
    
    @Column(name="id_proof")
    private Integer idProof;
    
    private Integer dependent;
    
    @Column(name="is_same_add_id_proof")
    private Boolean isSameAddIdProof;
    
    @Column(name="address_years")
    private Integer addressYears;
    
    @Column(name="other_income_source")
    private Integer otherIncomeSource;
    
    @Column(name="ongoing_mudra_loan")
    private Integer ongoingMudraLoan;
    
    @Column(name="certification_course")
    private Integer certificationCourse;
    
    @Column(name="is_workand_residence_sameplace")
    private Integer isWorkAndResidenceSamePlace;
    
    public DirectorPersonalDetail(){
        // Do nothing because of X and Y.
    }

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

}
