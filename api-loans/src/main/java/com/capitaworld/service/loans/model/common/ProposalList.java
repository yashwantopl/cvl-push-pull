package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.List;

public class ProposalList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationId;
	
	private List<Long> suggetionIds;
	
	private Long userId;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public List<Long> getSuggetionIds() {
		return suggetionIds;
	}

	public void setSuggetionIds(List<Long> suggetionIds) {
		this.suggetionIds = suggetionIds;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	
}
