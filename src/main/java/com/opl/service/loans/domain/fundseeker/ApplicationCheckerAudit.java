package com.opl.service.loans.domain.fundseeker;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_checker_audit")
public class ApplicationCheckerAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "np_user_id")
    private Long npUserId;

    @Column(name = "np_assignee_id")
    private Long npAssigneeId;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "action_date")
    private Date actionDate;

    //Constructor
    public ApplicationCheckerAudit() {
        // Do nothing because of X and Y.
    }

    //getter setter
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

    public Long getNpUserId() {
        return npUserId;
    }

    public void setNpUserId(Long npUserId) {
        this.npUserId = npUserId;
    }

    public Long getNpAssigneeId() {
        return npAssigneeId;
    }

    public void setNpAssigneeId(Long npAssigneeId) {
        this.npAssigneeId = npAssigneeId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }
}
