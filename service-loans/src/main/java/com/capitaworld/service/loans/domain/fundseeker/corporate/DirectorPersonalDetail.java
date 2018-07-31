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
    private Boolean owningHouse;

    @Column(name="assessed_for_it")
    private Boolean assessedForIt;

    @Column(name="have_li_policy")
    private Boolean haveLiPolicy;

    public DirectorPersonalDetail(){
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
