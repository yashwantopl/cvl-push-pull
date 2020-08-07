package com.opl.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opl.service.loans.domain.fundseeker.AuditActivity;

@Entity
@Table(name="fs_corporate_employment_details")
public class EmploymentDetail extends AuditActivity implements Serializable {
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

    @Column(name="name_of_employer")
    private String nameOfEmployer;

    private Double salary;

    public EmploymentDetail(){
    }

    public EmploymentDetail(Long id) {
        this.id = id;
    }

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

    public String getNameOfEmployer() {
        return nameOfEmployer;
    }

    public void setNameOfEmployer(String nameOfEmployer) {
        this.nameOfEmployer = nameOfEmployer;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
