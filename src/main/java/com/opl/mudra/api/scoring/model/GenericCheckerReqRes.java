package com.opl.mudra.api.scoring.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericCheckerReqRes implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -93321449016462267L;
	private Long id;
    private Long jobId;
    private Long currentStep;
    private Long toStep;
    private Long actionId;
    private String code;
    private Boolean actionFlag;
    private Boolean isEdit;
    private String remark;
    private Integer statusId;
    private Long userId;
    private Long businessTypeId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(Long currentStep) {
		this.currentStep = currentStep;
	}
	public Long getToStep() {
		return toStep;
	}
	public void setToStep(Long toStep) {
		this.toStep = toStep;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getActionFlag() {
		return actionFlag;
	}
	public void setActionFlag(Boolean actionFlag) {
		this.actionFlag = actionFlag;
	}
	public Boolean getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(Boolean isEdit) {
		this.isEdit = isEdit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
    
    
}
