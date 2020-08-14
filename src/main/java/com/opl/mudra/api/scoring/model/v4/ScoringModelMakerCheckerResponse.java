package com.opl.mudra.api.scoring.model.v4;

import java.util.Date;

public class ScoringModelMakerCheckerResponse {

	private Long id;
	private String name;
	private String code;
	private Boolean itrLessThanThreeYear;
	private Boolean itrThreeYear;
	private Boolean itrPresumptive;
	private Boolean isApproved;
	private Date approvalDate;
	private Boolean isCopied;
	private Long scoringModelCheckerId;
	private Boolean isEdit;
	private Long jobId;
	private Long orgId;
	private Integer statusId;
	private String reason;
	private Float version;
	private Date createdDate;
	private Date modifiedDate;
	private Long createdBy;
	private Long modifiedBy;
	private Boolean isActive;
	private Boolean isMaker;

	
	public ScoringModelMakerCheckerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScoringModelMakerCheckerResponse(Long id, String name, String code, Boolean itrLessThanThreeYear,
			Boolean itrThreeYear, Boolean itrPresumptive, Boolean isApproved, Date approvalDate, Boolean isCopied,
			Long scoringModelCheckerId, Boolean isEdit, Long jobId, Long orgId, Integer statusId, String reason,
			Float version, Date createdDate, Date modifiedDate, Long createdBy, Long modifiedBy, Boolean isActive,
			Boolean isMaker) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.itrLessThanThreeYear = itrLessThanThreeYear;
		this.itrThreeYear = itrThreeYear;
		this.itrPresumptive = itrPresumptive;
		this.isApproved = isApproved;
		this.approvalDate = approvalDate;
		this.isCopied = isCopied;
		this.scoringModelCheckerId = scoringModelCheckerId;
		this.isEdit = isEdit;
		this.jobId = jobId;
		this.orgId = orgId;
		this.statusId = statusId;
		this.reason = reason;
		this.version = version;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
		this.isMaker = isMaker;
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

	public Boolean getItrLessThanThreeYear() {
		return itrLessThanThreeYear;
	}

	public void setItrLessThanThreeYear(Boolean itrLessThanThreeYear) {
		this.itrLessThanThreeYear = itrLessThanThreeYear;
	}

	public Boolean getItrThreeYear() {
		return itrThreeYear;
	}

	public void setItrThreeYear(Boolean itrThreeYear) {
		this.itrThreeYear = itrThreeYear;
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

	public Long getScoringModelCheckerId() {
		return scoringModelCheckerId;
	}

	public void setScoringModelCheckerId(Long scoringModelCheckerId) {
		this.scoringModelCheckerId = scoringModelCheckerId;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Float getVersion() {
		return version;
	}

	public void setVersion(Float version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsMaker() {
		return isMaker;
	}

	public void setIsMaker(Boolean isMaker) {
		this.isMaker = isMaker;
	}

	@Override
	public String toString() {
		return "ScoringModelMakerCheckerResponse [id=" + id + ", name=" + name + ", code=" + code
				+ ", itrLessThanThreeYear=" + itrLessThanThreeYear + ", itrThreeYear=" + itrThreeYear
				+ ", itrPresumptive=" + itrPresumptive + ", isApproved=" + isApproved + ", approvalDate=" + approvalDate
				+ ", isCopied=" + isCopied + ", scoringModelCheckerId=" + scoringModelCheckerId + ", isEdit=" + isEdit
				+ ", jobId=" + jobId + ", orgId=" + orgId + ", statusId=" + statusId + ", reason=" + reason
				+ ", version=" + version + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", isActive=" + isActive + ", isMaker="
				+ isMaker + "]";
	}

}
