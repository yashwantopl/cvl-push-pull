package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * The persistent class for the workflow_steps database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowStepRequest extends AuditMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Boolean isRequired;

	private String name;

	private Integer seqNo;

	private String taskName;

	private Boolean isLast;

	private Boolean isParallel;

	private List<StepActionRequest> stepActions;

	private List<RoleMasterRequest> roles;

	private WorkflowMasterRequest workflow;

	public WorkflowStepRequest() {
		super();
		stepActions = Collections.emptyList();
	}

	public WorkflowStepRequest(Long id) {
		super();
		this.id = id;
		stepActions = Collections.emptyList();
		roles = Collections.emptyList();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsRequired() {
		return this.isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeqNo() {
		return this.seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public List<StepActionRequest> getStepActions() {
		return stepActions;
	}

	public void setStepActions(List<StepActionRequest> stepActions) {
		this.stepActions = stepActions;
	}

	public WorkflowMasterRequest getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkflowMasterRequest workflow) {
		this.workflow = workflow;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public List<RoleMasterRequest> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleMasterRequest> roles) {
		this.roles = roles;
	}

	public Boolean getIsParallel() {
		return isParallel;
	}

	public void setIsParallel(Boolean isParallel) {
		this.isParallel = isParallel;
	}
}