package com.opl.mudra.api.workflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * The persistent class for the action_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String buttonText;

	private String json;

	private String name;

	public ActionMasterRequest() {
	}

	public ActionMasterRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getButtonText() {
		return this.buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getJson() {
		return this.json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}