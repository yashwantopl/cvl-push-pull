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
public class RoleMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String roleName;

	private List<String> permissions;

	public RoleMasterRequest() {
		permissions = Collections.emptyList();
	}

	public RoleMasterRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
}