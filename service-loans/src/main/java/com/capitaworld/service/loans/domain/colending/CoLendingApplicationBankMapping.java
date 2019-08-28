package com.capitaworld.service.loans.domain.colending;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by dhaval.panchal on 27-Aug-19.
 */
@Entity
@Table(name="fs_co_lending_application_bank_mapping")
public class CoLendingApplicationBankMapping implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="bank_org_id")
    private Integer bankOrgId;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_date")
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getBankOrgId() {
        return bankOrgId;
    }

    public void setBankOrgId(Integer bankOrgId) {
        this.bankOrgId = bankOrgId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
