package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the workflow_jobs database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowJobRequest extends AuditMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private String description;

	private Boolean isCompleted;

	private WorkflowMasterRequest workflow;

	private List<WorkflowJobsTrackerRequest> workflowJobsTrackers;

	public WorkflowJobRequest() {
		super();
	}

	public WorkflowJobRequest(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsCompleted() {
		return this.isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public WorkflowMasterRequest getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkflowMasterRequest workflow) {
		this.workflow = workflow;
	}

	public List<WorkflowJobsTrackerRequest> getWorkflowJobsTrackers() {
		return workflowJobsTrackers;
	}

	public void setWorkflowJobsTrackers(List<WorkflowJobsTrackerRequest> workflowJobsTrackers) {
		this.workflowJobsTrackers = workflowJobsTrackers;
	}

}