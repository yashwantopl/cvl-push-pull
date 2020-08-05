package com.opl.mudra.api.scoring;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRateReqRes implements Serializable {

    private static final long serialVersionUID = -93321449016462267L;

    private   Long id;

    private  Double eblr;
    
    private Double mclr;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private  Date effectiveFromDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private  Date effectiveTillDate;

    private  Integer status;

    private Long jobId;

    private  Long orgId;

    private  Object workFlowData;

    private List<Long> roleIds;

    private  Long userId;

    private Long createdBy;

    private Long modifiedBy;

    private Boolean isApproved;

    private Boolean isEdit;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date approvedDate;

    private Integer businessTypeId;
    
    private Integer scoringModelBasedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEffectiveFromDate() {
        return effectiveFromDate;
    }

    public void setEffectiveFromDate(Date effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    public Date getEffectiveTillDate() {
        return effectiveTillDate;
    }

    public void setEffectiveTillDate(Date effectiveTillDate) {
        this.effectiveTillDate = effectiveTillDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Object getWorkFlowData() {
        return workFlowData;
    }

    public void setWorkFlowData(Object workFlowData) {
        this.workFlowData = workFlowData;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean approved) {
        isApproved = approved;
    }

    public Boolean getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(Boolean edit) {
        isEdit = edit;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Integer getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Integer businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

	public Double getEblr() {
		return eblr;
	}

	public void setEblr(Double eblr) {
		this.eblr = eblr;
	}

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}

	public Double getMclr() {
		return mclr;
	}

	public void setMclr(Double mclr) {
		this.mclr = mclr;
	}

	@Override
	public String toString() {
		return "BaseRateReqRes [id=" + id + ", eblr=" + eblr + ", mclr=" + mclr + ", effectiveFromDate="
				+ effectiveFromDate + ", effectiveTillDate=" + effectiveTillDate + ", status=" + status + ", jobId="
				+ jobId + ", orgId=" + orgId + ", workFlowData=" + workFlowData + ", roleIds=" + roleIds + ", userId="
				+ userId + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", isApproved=" + isApproved
				+ ", isEdit=" + isEdit + ", approvedDate=" + approvedDate + ", businessTypeId=" + businessTypeId
				+ ", scoringModelBasedOn=" + scoringModelBasedOn + "]";
	}
	
	
}
