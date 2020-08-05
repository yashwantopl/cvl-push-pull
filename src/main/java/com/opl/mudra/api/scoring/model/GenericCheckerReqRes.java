package com.opl.mudra.api.scoring.model;

import java.io.Serializable;

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
	@Override
	public String toString() {
		return "GenericCheckerReqRes [id=" + id + ", jobId=" + jobId + ", currentStep=" + currentStep + ", toStep="
				+ toStep + ", actionId=" + actionId + ", code=" + code + ", actionFlag=" + actionFlag + ", isEdit="
				+ isEdit + "]";
	}
}
