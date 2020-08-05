package com.opl.mudra.api.loans.model;

import java.io.Serializable;

public class NhbsApplicationRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long applicationStatusId;

	private Long userId;
	
	private Long userRoleId;
	
	private String userRoleIdString;

	private Long assignedUserId;
	
	private Long applicationId;

	private Long proposalMappingId;

	private Long ddrStatusId;

	private int pageIndex;

	private int size;

	private Long fpMakerId;

	private Long fpProductId;

	private Long businessTypeId;

	private Long productTypeId;

	//added by Dhaval
	private Long npUserId;

	private Long userOrgId;

	public Long getFpMakerId() {
		return fpMakerId;
	}

	public void setFpMakerId(Long fpMakerId) {
		this.fpMakerId = fpMakerId;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(Long ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	public Long getAssignedUserId() {
		return assignedUserId;
	}

	public void setAssignedUserId(Long assignedUserId) {
		this.assignedUserId = assignedUserId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleIdString() {
		return userRoleIdString;
	}

	public void setUserRoleIdString(String userRoleIdString) {
		this.userRoleIdString = userRoleIdString;
	}

	public Long getNpUserId() {
		return npUserId;
	}

	public void setNpUserId(Long npUserId) {
		this.npUserId = npUserId;
	}

    public Long getProposalMappingId() {
        return proposalMappingId;
    }

    public void setProposalMappingId(Long proposalMappingId) {
        this.proposalMappingId = proposalMappingId;
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

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
}
