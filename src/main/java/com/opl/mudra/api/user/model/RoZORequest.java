package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RoZORequest  implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long userOrgId;
	private Long roleId;
	private Long businessTypeId;
	private String email;
	private String mobile;
	private String firstName;	
	private String lastName;	
	private RoRequest roRequest;
	private ZoRequest zoRequest;
	private List<Map<String,Object>> branchMappingList;
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public RoRequest getRoRequest() {
		return roRequest;
	}
	public void setRoRequest(RoRequest roRequest) {
		this.roRequest = roRequest;
	}
	public ZoRequest getZoRequest() {
		return zoRequest;
	}
	public void setZoRequest(ZoRequest zoRequest) {
		this.zoRequest = zoRequest;
	}
	public List<Map<String, Object>> getBranchMappingList() {
		return branchMappingList;
	}
	public void setBranchMappingList(List<Map<String, Object>> branchMappingList) {
		this.branchMappingList = branchMappingList;
	}
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
}
