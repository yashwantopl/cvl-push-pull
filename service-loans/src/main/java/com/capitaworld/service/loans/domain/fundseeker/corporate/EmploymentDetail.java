package com.capitaworld.service.loans.domain.fundseeker.corporate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="fs_corporate_employment_details")
public class EmploymentDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_of_employment")
    private Long typeOfEmployment;

    @Column(name="employment_with")
    private Long employmentWith;

    @Column(name="employment_status")
    private Long employmentStatus;

    @Column(name="total_experience")
    private Long totalExperience;

    @Column(name="created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeOfEmployment() {
        return typeOfEmployment;
    }

    public void setTypeOfEmployment(Long typeOfEmployment) {
        this.typeOfEmployment = typeOfEmployment;
    }

    public Long getEmploymentWith() {
        return employmentWith;
    }

    public void setEmploymentWith(Long employmentWith) {
        this.employmentWith = employmentWith;
    }

    public Long getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(Long employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Long getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Long totalExperience) {
        this.totalExperience = totalExperience;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
