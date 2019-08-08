package com.capitaworld.service.loans.model;

import java.util.Date;

public class TutorialsViewAudits {

    private Long id;
    private Long userId;
    private String userName;
    private Long roleId;
    private Long tutorialId;
    private Integer loanType;
    private Date viewDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getTutorialId() {
        return tutorialId;
    }

    public void setTutorialId(Long tutorialId) {
        this.tutorialId = tutorialId;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TutorialsViewAudits{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", tutorialId=" + tutorialId +
                ", loanType=" + loanType +
                ", viewDate=" + viewDate +
                '}';
    }
}
