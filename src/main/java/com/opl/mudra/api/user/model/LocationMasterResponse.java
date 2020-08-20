package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationMasterResponse implements Serializable {
	private Long id;
	private Long tempId;
	private String createdDate;
	private String pincode;
	private String locationName;
	private GenericLocation city;
	private GenericLocation state;
	private GenericLocation region;
	private GenericLocation district;
	private GenericLocation subDistrict;
	private Boolean isActive;
	private Long jobId;
	private Boolean isDeleted;
	private Boolean isEdit;
	private Long districtId;
	private Long subDistrictId;
	// view or edit
	private String locationCode;
	private String remark;

	private Boolean recordExist;
	private Boolean pincodeExist;
	private String oldLocationName;
	private GenericLocation oldCity;
	private GenericLocation oldState;
	private GenericLocation oldRegion;
	private Object workflowData;
	private Integer statusId;

	private String message;
	private Integer status;

	// constructor
	public LocationMasterResponse() {
	}

	public LocationMasterResponse(String message, Integer status) {
		this.message = message;
		this.status = status;
	}
 

	// getter setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	// view or edit
	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getRecordExist() {
		return recordExist;
	}

	public void setRecordExist(Boolean recordExist) {
		this.recordExist = recordExist;
	}

	public Boolean getPincodeExist() {
		return pincodeExist;
	}

	public void setPincodeExist(Boolean pincodeExist) {
		this.pincodeExist = pincodeExist;
	}

	public Long getTempId() {
		return tempId;
	}

	public void setTempId(Long tempId) {
		this.tempId = tempId;
	}

	public GenericLocation getCity() {
		return city;
	}

	public void setCity(GenericLocation city) {
		this.city = city;
	}

	public GenericLocation getState() {
		return state;
	}

	public void setState(GenericLocation state) {
		this.state = state;
	}

	public GenericLocation getOldCity() {
		return oldCity;
	}

	public void setOldCity(GenericLocation oldCity) {
		this.oldCity = oldCity;
	}

	public GenericLocation getOldState() {
		return oldState;
	}

	public void setOldState(GenericLocation oldState) {
		this.oldState = oldState;
	}

	public GenericLocation getRegion() {
		return region;
	}

	public void setRegion(GenericLocation region) {
		this.region = region;
	}

	public String getOldLocationName() {
		return oldLocationName;
	}

	public void setOldLocationName(String oldLocationName) {
		this.oldLocationName = oldLocationName;
	}

	public GenericLocation getOldRegion() {
		return oldRegion;
	}

	public void setOldRegion(GenericLocation oldRegion) {
		this.oldRegion = oldRegion;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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

	public GenericLocation getDistrict() {
		return district;
	}

	public void setDistrict(GenericLocation district) {
		this.district = district;
	}

	public GenericLocation getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(GenericLocation subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}
}
