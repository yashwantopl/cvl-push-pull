package com.opl.mudra.api.user.model;

import java.io.Serializable;

public class SpOrgSpecialityMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Long spUserId;

	private Long orgSpecialityMasterId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getSpUserId() {
		return spUserId;
	}

	public void setSpUserId(Long spUserId) {
		this.spUserId = spUserId;
	}

	public Long getOrgSpecialityMasterId() {
		return orgSpecialityMasterId;
	}

	public void setOrgSpecialityMasterId(Long orgSpecialityMasterId) {
		this.orgSpecialityMasterId = orgSpecialityMasterId;
	}

	public SpOrgSpecialityMapping() {
		super();
		
	}
	
}
