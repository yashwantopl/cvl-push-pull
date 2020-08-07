package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the workflow_jobs_tracker database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowJobsTrackerRequest extends AuditMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String remark;

	private Boolean currentActive;
	
	private WorkflowJobRequest job;

	private WorkflowStepRequest step;

	public WorkflowJobsTrackerRequest() {
		super();
	}

	public WorkflowJobsTrackerRequest(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public WorkflowJobRequest getJob() {
		return job;
	}

	public void setJob(WorkflowJobRequest job) {
		this.job = job;
	}

	public WorkflowStepRequest getStep() {
		return step;
	}

	public void setStep(WorkflowStepRequest step) {
		this.step = step;
	}

	public Boolean getCurrentActive() {
		return currentActive;
	}

	public void setCurrentActive(Boolean currentActive) {
		this.currentActive = currentActive;
	}
	
}