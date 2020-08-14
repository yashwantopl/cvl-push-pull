package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BranchBasicDetailsRequest implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Long roleId;

	private Integer cityId;

	private String code;

	private String contactPersonEmail;

	private String contactPersonName;

	private String contactPersonNumber;

	private Integer countryId;

	private String faxNo;

	private Boolean isHo;

	private String landMark;

	private Long orgId;

	private Integer parentBranchId;

	private int pincode;

	private String premisesNo;

	private String remarks;

	private Integer stateId;

	private String streetName;

	private String telephoneNo;

	private String ifscCode;

	private Long locationId;

	private int pageIndex;

	private int size;

	private Long userId;

	private Long jobId;

	private Integer status;

	private Date approvedDate;

	private Boolean isCopied;

	private Integer dataReqType;

	private Long branchId;

	private Boolean isActive;

	private List<BranchCategoryRequest> branchCategoryRequestList;

	private List<DepartmentRequest> departmentRequestList;

	private Boolean isDeleted;

	private Boolean isEdit;

	private LocationMasterResponse locationMasterResponse;

	private Object workflowData;

	private Long loanSystemId;
	
	private String locationCode;

	private String smecCode;

	private String smecName;
	
	private String smecEmail;
	
	private String smecMobile;

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long long1) {
		this.roleId = long1;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContactPersonEmail() {
		return contactPersonEmail;
	}

	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public Boolean getIsHo() {
		return isHo;
	}

	public void setIsHo(Boolean ho) {
		isHo = ho;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getParentBranchId() {
		return parentBranchId;
	}

	public void setParentBranchId(Integer parentBranchId) {
		this.parentBranchId = parentBranchId;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getPremisesNo() {
		return premisesNo;
	}

	public void setPremisesNo(String premisesNo) {
		this.premisesNo = premisesNo;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public BranchBasicDetailsRequest(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public BranchBasicDetailsRequest(Long id, int stateId) {
		super();
		this.id = id;
		this.stateId = stateId;
	}

	public BranchBasicDetailsRequest() {
		super();
	}


	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Boolean getIsCopied() {
		return isCopied;
	}

	public void setIsCopied(Boolean copied) {
		isCopied = copied;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDataReqType() {
		return dataReqType;
	}

	public void setDataReqType(Integer dataReqType) {
		this.dataReqType = dataReqType;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	public List<BranchCategoryRequest> getBranchCategoryRequestList() {
		return branchCategoryRequestList;
	}

	public void setBranchCategoryRequestList(List<BranchCategoryRequest> branchCategoryRequestList) {
		this.branchCategoryRequestList = branchCategoryRequestList;
	}

	public List<DepartmentRequest> getDepartmentRequestList() {
		return departmentRequestList;
	}

	public void setDepartmentRequestList(List<DepartmentRequest> departmentRequestList) {
		this.departmentRequestList = departmentRequestList;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public LocationMasterResponse getLocationMasterResponse() {
		return locationMasterResponse;
	}

	public void setLocationMasterResponse(LocationMasterResponse locationMasterResponse) {
		this.locationMasterResponse = locationMasterResponse;
	}

	public Boolean getEdit() {
		return isEdit;
	}

	public void setEdit(Boolean edit) {
		isEdit = edit;
	}

	public String getContactPersonNumber() {
		return contactPersonNumber;
	}

	public void setContactPersonNumber(String contactPersonNumber) {
		this.contactPersonNumber = contactPersonNumber;
	}

	public Long getLoanSystemId() {
		return loanSystemId;
	}

	public void setLoanSystemId(Long loanSystemId) {
		this.loanSystemId = loanSystemId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getSmecCode() {
		return smecCode;
	}

	public void setSmecCode(String smecCode) {
		this.smecCode = smecCode;
	}

	public String getSmecName() {
		return smecName;
	}

	public void setSmecName(String smecName) {
		this.smecName = smecName;
	}

	public String getSmecEmail() {
		return smecEmail;
	}

	public void setSmecEmail(String smecEmail) {
		this.smecEmail = smecEmail;
	}

	public String getSmecMobile() {
		return smecMobile;
	}

	public void setSmecMobile(String smecMobile) {
		this.smecMobile = smecMobile;
	}
	
}
