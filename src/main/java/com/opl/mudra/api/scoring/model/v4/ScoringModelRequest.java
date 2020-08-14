package com.opl.mudra.api.scoring.model.v4;

import java.util.Date;
import java.util.List;

public class ScoringModelRequest {

	private Long id;

	private String name;

	private String code;

	private Integer itrTypeId;
	
	private Boolean itrThreeYear;

	private Boolean itrLessThanThreeYear;

	private Boolean itrPresumptive;

	private Boolean isApproved;

	private Date approvalDate;

	private Boolean isCopied;

	private Boolean isEdit;

	private Long jobId;

	private Long orgId;

	private Integer statusId;

	private Float version;

	private Date modifiedDate;

	private Long userRoleId;

	private Long removeJobId;

	private List<ItrTypeMsmeRequest> itrTypeMsmeRequestList;

	private Long userId;

	private Integer status;

	private String message;

	private Long businessTypeId;

	private Long scoringModelId;
	
	private Long copyScoringModelId;
	
	private Boolean isMaker;
	
	private Boolean isActive;
	
	private String requestType;
	
	private String reason;
	
	private String checkerDecision;
	
	private Integer copyFromItrId;
	
	public ScoringModelRequest() {

	}

	public ScoringModelRequest(String message, Integer status) {
		super();
		this.status = status;
		this.message = message;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getItrThreeYear() {
		return itrThreeYear;
	}

	public void setItrThreeYear(Boolean itrThreeYear) {
		this.itrThreeYear = itrThreeYear;
	}

	public Boolean getItrLessThanThreeYear() {
		return itrLessThanThreeYear;
	}

	public void setItrLessThanThreeYear(Boolean itrLessThanThreeYear) {
		this.itrLessThanThreeYear = itrLessThanThreeYear;
	}

	public Boolean getItrPresumptive() {
		return itrPresumptive;
	}

	public void setItrPresumptive(Boolean itrPresumptive) {
		this.itrPresumptive = itrPresumptive;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Boolean getIsCopied() {
		return isCopied;
	}

	public void setIsCopied(Boolean isCopied) {
		this.isCopied = isCopied;
	}

	public Boolean getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getRemoveJobId() {
		return removeJobId;
	}

	public void setRemoveJobId(Long removeJobId) {
		this.removeJobId = removeJobId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Integer getItrTypeId() {
		return itrTypeId;
	}

	public void setItrTypeId(Integer itrTypeId) {
		this.itrTypeId = itrTypeId;
	}
	public Long getScoringModelId() {
		return scoringModelId;
	}

	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}

	public Boolean getIsMaker() {
		return isMaker;
	}

	public void setIsMaker(Boolean isMaker) {
		this.isMaker = isMaker;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<ItrTypeMsmeRequest> getItrTypeMsmeRequestList() {
		return itrTypeMsmeRequestList;
	}

	public void setItrTypeMsmeRequestList(List<ItrTypeMsmeRequest> itrTypeMsmeRequestList) {
		this.itrTypeMsmeRequestList = itrTypeMsmeRequestList;
	}

	public Long getCopyScoringModelId() {
		return copyScoringModelId;
	}

	public void setCopyScoringModelId(Long copyScoringModelId) {
		this.copyScoringModelId = copyScoringModelId;
	}
	
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

	public String getCheckerDecision() {
		return checkerDecision;
	}

	public void setCheckerDecision(String checkerDecision) {
		this.checkerDecision = checkerDecision;
	}
	

	public Integer getCopyFromItrId() {
		return copyFromItrId;
	}

	public void setCopyFromItrId(Integer copyFromItrId) {
		this.copyFromItrId = copyFromItrId;
	}

	@Override
	public String toString() {
		return "ScoringModelRequest [id=" + id + ", name=" + name + ", code=" + code + ", itrTypeId=" + itrTypeId
				+ ", itrThreeYear=" + itrThreeYear + ", itrLessThanThreeYear=" + itrLessThanThreeYear
				+ ", itrPresumptive=" + itrPresumptive + ", isApproved=" + isApproved + ", approvalDate=" + approvalDate
				+ ", isCopied=" + isCopied + ", isEdit=" + isEdit + ", jobId=" + jobId + ", orgId=" + orgId
				+ ", statusId=" + statusId + ", version=" + version + ", modifiedDate=" + modifiedDate + ", userRoleId="
				+ userRoleId + ", removeJobId=" + removeJobId + ", itrTypeMsmeRequestList=" + itrTypeMsmeRequestList
				+ ", userId=" + userId + ", status=" + status + ", message=" + message + ", businessTypeId="
				+ businessTypeId + ", scoringModelId=" + scoringModelId + ", copyScoringModelId=" + copyScoringModelId
				+ ", isMaker=" + isMaker + ", isActive=" + isActive + ", requestType=" + requestType + ", reason="
				+ reason + ", checkerDecision=" + checkerDecision + ", copyFromItrId=" + copyFromItrId + "]";
	}

	

}
