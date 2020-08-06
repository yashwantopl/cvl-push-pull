/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.cibil.model;

/**
 * @author harshit
 *
 */
public class GenerateTokenRequest {

	private static final long serialVersionUID = -8358683835700479809L;
	private Long applicationId;

	private String token;
	private String bankToken;
	private String userName;
	private String password;
	private transient String url;

	private String hdrUserName;
	private String hdrPassword;
	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the bankToken
	 */
	public String getBankToken() {
		return bankToken;
	}
	/**
	 * @param bankToken the bankToken to set
	 */
	public void setBankToken(String bankToken) {
		this.bankToken = bankToken;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the hdrUserName
	 */
	public String getHdrUserName() {
		return hdrUserName;
	}
	/**
	 * @param hdrUserName the hdrUserName to set
	 */
	public void setHdrUserName(String hdrUserName) {
		this.hdrUserName = hdrUserName;
	}
	/**
	 * @return the hdrPassword
	 */
	public String getHdrPassword() {
		return hdrPassword;
	}
	/**
	 * @param hdrPassword the hdrPassword to set
	 */
	public void setHdrPassword(String hdrPassword) {
		this.hdrPassword = hdrPassword;
	}
	
	

}
