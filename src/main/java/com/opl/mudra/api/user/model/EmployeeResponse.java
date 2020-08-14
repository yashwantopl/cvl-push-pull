package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeResponse implements Serializable {
    private String employeeName;
    private String employeeCode;
    private String userClassification;
    private String username;
    private String mobile;
    private String email;
    private String remarks;
    private GenericLocation branch;
    private double minSanctionLimit;
    private double maxSanctionLimit;
    private Object workflowData;
    private Boolean emailAlreadyExists;
    private String locationCode;
    private String branchCode;
    private Long employeeId;
    private Long id;
    private Long jobId;
    private Boolean isDeleted;
    private Boolean isEdit;
    private Long userRoleId;
    private Long departmentId;

    //getter setter
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getUserClassification() {
        return userClassification;
    }

    public void setUserClassification(String userClassification) {
        this.userClassification = userClassification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public GenericLocation getBranch() {
        return branch;
    }

    public void setBranch(GenericLocation branch) {
        this.branch = branch;
    }

    public double getMinSanctionLimit() {
        return minSanctionLimit;
    }

    public void setMinSanctionLimit(double minSanctionLimit) {
        this.minSanctionLimit = minSanctionLimit;
    }

    public double getMaxSanctionLimit() {
        return maxSanctionLimit;
    }

    public void setMaxSanctionLimit(double maxSanctionLimit) {
        this.maxSanctionLimit = maxSanctionLimit;
    }

    public Object getWorkflowData() {
        return workflowData;
    }

    public void setWorkflowData(Object workflowData) {
        this.workflowData = workflowData;
    }

    public Boolean getEmailAlreadyExists() {
        return emailAlreadyExists;
    }

    public void setEmailAlreadyExists(Boolean emailAlreadyExists) {
        this.emailAlreadyExists = emailAlreadyExists;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean edit) {
        isEdit = edit;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
