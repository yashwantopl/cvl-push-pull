package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * Created by dhaval.panchal on 23-Oct-18.
 */
public class BankFPResponse implements Serializable {
    private String email;
    private String mobile;
    private Integer roleId;
    private String branchName;
    private String branchCode;
    private String ifscCode;
    private String brContactPerson;
    private String brContactEmail;
    private String brTelNo;
    private String locationName;
    private String locationCode;
    private String address;
    private String state;
    private String city;
    private String pincode;
    private String status;

    public BankFPResponse(String email, String mobile, Integer roleId, String branchName, String branchCode, String ifscCode, String brContactPerson, String brContactEmail, String brTelNo, String locationName, String locationCode, String address, String state, String city, String pincode, String status) {
        this.email = email;
        this.mobile = mobile;
        this.roleId = roleId;
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.ifscCode = ifscCode;
        this.brContactPerson = brContactPerson;
        this.brContactEmail = brContactEmail;
        this.brTelNo = brTelNo;
        this.locationName = locationName;
        this.locationCode = locationCode;
        this.address = address;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBrContactPerson() {
        return brContactPerson;
    }

    public void setBrContactPerson(String brContactPerson) {
        this.brContactPerson = brContactPerson;
    }

    public String getBrContactEmail() {
        return brContactEmail;
    }

    public void setBrContactEmail(String brContactEmail) {
        this.brContactEmail = brContactEmail;
    }

    public String getBrTelNo() {
        return brTelNo;
    }

    public void setBrTelNo(String brTelNo) {
        this.brTelNo = brTelNo;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
