package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationRequest implements Serializable {
    private Long id;
    private Integer pageIndex;
    private Integer pageSize;

    private Long userOrgId;
    private String locationName;
    private String locationCode;
    private Long cityId;
    private Long stateId;
    private Long regionId;
    private Long districtId;
    private Long subDistrictId;
    private String pincode;
    private String remark;
    private Boolean isActive;
    private Boolean isEdit;
    private Long userId;
    private Long jobId;
    private List<GenericChecker> genericCheckers;
    private List<Long> roleId;
    private Integer dataReqType;
    private Boolean isOverwrite;
    private Date createdDate;
    //constructor
    public LocationRequest() {
        // Do nothing because of X and Y.
    }

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

    public Long getUserOrgId() {
        return userOrgId;
    }

    public void setUserOrgId(Long userOrgId) {
        this.userOrgId = userOrgId;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDataReqType() {
        return dataReqType;
    }

    public void setDataReqType(Integer dataReqType) {
        this.dataReqType = dataReqType;
    }

    public Boolean getIsOverwrite() {
        return isOverwrite;
    }

    public void setIsOverwrite(Boolean overwrite) {
        isOverwrite = overwrite;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<GenericChecker> getGenericCheckers() {
        return genericCheckers;
    }

    public void setGenericCheckers(List<GenericChecker> genericCheckers) {
        this.genericCheckers = genericCheckers;
    }

    public List<Long> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Long> roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean edit) {
        isEdit = edit;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public void setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "LocationRequest{" +
                "id=" + id +
                ", pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", userOrgId=" + userOrgId +
                ", cityId=" + cityId +
                ", stateId=" + stateId +
                ", regionId=" + regionId +
                ", districtId=" + districtId +
                ", subDistrictId=" + subDistrictId +
                ", pincode='" + pincode +
                ", dataReqType=" + dataReqType +
                ", isOverwrite=" + isOverwrite +
                ", createdDate=" + createdDate +
                '}';
    }
}
