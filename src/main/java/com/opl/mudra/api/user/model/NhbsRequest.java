package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.List;

public class NhbsRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userRoleId;
	
	private String userRoleIdString;
	
	private String nameString;

	private Long userOrgId;

	private Long branchId;

	private List<Long> roleIdList;

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getUserRoleIdString() {
		return userRoleIdString;
	}

	public void setUserRoleIdString(String userRoleIdString) {
		this.userRoleIdString = userRoleIdString;
	}
}
