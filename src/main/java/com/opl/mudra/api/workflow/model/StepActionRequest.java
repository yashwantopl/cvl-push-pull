package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the step_actions database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StepActionRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long nextworkflowStep;

	private Long workflowStep;

	private ActionMasterRequest action;

	public StepActionRequest() {
	}

	public StepActionRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ActionMasterRequest getAction() {
		return action;
	}

	public void setAction(ActionMasterRequest action) {
		this.action = action;
	}
}