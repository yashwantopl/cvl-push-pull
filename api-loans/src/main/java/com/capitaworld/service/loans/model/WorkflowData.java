package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6453935998292244328L;
	private Long id;
	private Long nextworkflowStep;
	private Long workflowStep;
	private Long actionId;
	private Long fpProductId;
	private Long jobId;
	private Long userId;
	private String actionFor;
	private Integer stage;  // 1 = Pending, 2 = Approved
	
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
	public String getActionFor() {
		return actionFor;
	}
	public void setActionFor(String actionFor) {
		this.actionFor = actionFor;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
