/**
 * 
 */
package com.opl.mudra.api.gst.model;

/**
 * @author sanket
 *
 */
public abstract class CommonRequest {
	
	private Long userId;
	private Long userType;
	private Long userOrgId;
	private Long clientId;
	
	
	
	
	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * @return the userType
	 */
	public Long getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Long userType) {
		this.userType = userType;
	}
	/**
	 * @return the userOrgId
	 */
	public Long getUserOrgId() {
		return userOrgId;
	}
	/**
	 * @param userOrgId the userOrgId to set
	 */
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	


	
	

}
