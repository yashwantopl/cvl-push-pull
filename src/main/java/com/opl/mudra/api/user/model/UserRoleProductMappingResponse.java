package com.opl.mudra.api.user.model;

/**
 * Created by dhaval.panchal on 09-May-19.
 */
public class UserRoleProductMappingResponse {
    private Long id;

    private Long userId;

    private Long roleId;

    private Long businessTypeId;
    
    private Long loanTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }
    

    public Long getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(Long loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	@Override
    public String toString() {
        return "UserRoleProductMappingResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", businessTypeId=" + businessTypeId +
                '}';
    }
}
