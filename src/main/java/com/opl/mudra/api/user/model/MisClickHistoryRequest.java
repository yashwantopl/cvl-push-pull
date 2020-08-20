package com.opl.mudra.api.user.model;

/**
 * Created by dhaval.panchal on 20-Apr-19.
 */
public class MisClickHistoryRequest {

    private String roleId;

    private String roleTrnId;

    private String urlFormed;

    private String orgId;

    private String userId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleTrnId() {
        return roleTrnId;
    }

    public void setRoleTrnId(String roleTrnId) {
        this.roleTrnId = roleTrnId;
    }

    public String getUrlFormed() {
        return urlFormed;
    }

    public void setUrlFormed(String urlFormed) {
        this.urlFormed = urlFormed;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MisClickHistoryRequest{" +
                "roleId='" + roleId + '\'' +
                ", roleTrnId='" + roleTrnId + '\'' +
                ", urlFormed='" + urlFormed + '\'' +
                ", orgId='" + orgId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
