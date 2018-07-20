package com.capitaworld.service.loans.model;

public class WorkflowData {
	private Long nextworkflowStep;
	private Long workflowStep;
	private Long actionId;
	private Long fpProductId;
	private Long jobId;
	private Long userId;
	public Long getNextworkflowStep() {
		return nextworkflowStep;
	}
	public void setNextworkflowStep(Long nextworkflowStep) {
		this.nextworkflowStep = nextworkflowStep;
	}
	public Long getWorkflowStep() {
		return workflowStep;
	}
	public void setWorkflowStep(Long workflowStep) {
		this.workflowStep = workflowStep;
	}
	public Long getActionId() {
		return actionId;
	}
	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
