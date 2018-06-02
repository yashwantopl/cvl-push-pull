package com.capitaworld.service.loans.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmploymentDetailRequest  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long typeOfEmployment;

    private Long employmentWith;

    private Long employmentStatus;

    private Long totalExperience;

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

    @Override
    public String toString() {
        return "EmploymentDetailRequest{" +
                "id=" + id +
                ", typeOfEmployment=" + typeOfEmployment +
                ", employmentWith=" + employmentWith +
                ", employmentStatus=" + employmentStatus +
                ", totalExperience=" + totalExperience +
                '}';
    }
}
