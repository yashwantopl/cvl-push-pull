package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequest implements Serializable {

    private Long id;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer dataReqType;
    private String employeeName;
    private String employeeCode;
    private String userClassification;
    private Long userId;
    private Long currentUserId;
    private Long departmentId;
    private String username;
    private String mobile;
    private String email;
    private String remarks;
    private Long branchId;
    private Long userOrgId;
    private double minSanctionLimit;
    private double maxSanctionLimit;
    private List<GenericChecker> genericCheckers;
    private List<Long> roleId;
    private Long userRoleId;
    private Long employeeId;


    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
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

    public List<GenericChecker> getGenericCheckers() {
        return genericCheckers;
    }

    public void setGenericCheckers(List<GenericChecker> genericCheckers) {
        this.genericCheckers = genericCheckers;
    }

    public Integer getDataReqType() {
        return dataReqType;
    }

    public void setDataReqType(Integer dataReqType) {
        this.dataReqType = dataReqType;
    }

    public List<Long> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Long> roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", employeeName='" + employeeName +
                ", employeeCode='" + employeeCode +
                ", userClassification='" + userClassification +
                ", userId='" + userId +
                ", mobile='" + mobile +
                ", email='" + email +
                ", remark='" + remarks +
                ", branchId=" + branchId +
                ", maxSanctionLimit=" + maxSanctionLimit +
                ", minSanctionLimit=" + minSanctionLimit +
                '}';
    }
}
