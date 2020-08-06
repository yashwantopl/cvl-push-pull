package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the workflow_steps_roles database table.
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowStepsRoleRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long roleId;

	private WorkflowStepRequest workflowStepRequest;

	public WorkflowStepsRoleRequest() {
	}

	public WorkflowStepsRoleRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public WorkflowStepRequest getWorkflowStepRequest() {
		return workflowStepRequest;
	}

	public void setWorkflowStepRequest(WorkflowStepRequest workflowStepRequest) {
		this.workflowStepRequest = workflowStepRequest;
	}
}