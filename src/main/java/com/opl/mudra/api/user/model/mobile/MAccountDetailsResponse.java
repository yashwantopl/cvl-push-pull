package com.opl.mudra.api.user.model.mobile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MAccountDetailsResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String mobile;
	private String name;
	private String password;
	private String userType;
	private Long userTypeId;
	private String imagePath;
	private String orgName;
	private String address;
	private String userRole;
	private Map<String,Object> userProductRole;
	
	
	public MAccountDetailsResponse() {
		super();
		this.userProductRole=new HashMap<String, Object>();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Map<String, Object> getUserProductRole() {
		return userProductRole;
	}

	public void setUserProductRole(Map<String, Object> userProductRole) {
		this.userProductRole = userProductRole;
	}
	
}
