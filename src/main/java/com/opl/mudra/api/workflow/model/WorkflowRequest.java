package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * The persistent class for the action_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowRequest extends AuditMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long currentStep;

	private Long toStep;

	private Long jobId;

	private Long applicationId;
	
	private Long proposalId;

	private String remarks;

	private Long actionId;

	private List<Long> roleIds;

	private Long workflowId;

	public WorkflowRequest() {
		super();
		roleIds = Collections.emptyList();
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

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}
	
	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	@Override
	public String toString() {
		return "WorkflowRequest [currentStep=" + currentStep + ", toStep=" + toStep + ", jobId=" + jobId
				+ ", applicationId=" + applicationId + ", remarks=" + remarks + ", actionId=" + actionId + ", roleIds="
				+ roleIds + ", workflowId=" + workflowId + "]";
	}

}