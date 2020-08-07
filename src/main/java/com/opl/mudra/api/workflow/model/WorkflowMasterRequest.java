package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the workflow_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowMasterRequest extends AuditMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Long parentWorkflow;

	private Integer version;

	public WorkflowMasterRequest() {
		super();
	}

	public WorkflowMasterRequest(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentWorkflow() {
		return this.parentWorkflow;
	}

	public void setParentWorkflow(Long parentWorkflow) {
		this.parentWorkflow = parentWorkflow;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}