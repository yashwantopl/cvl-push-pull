/**
 * 
 */
package com.opl.mudra.api.loans.model;

/**
 * @author nilay.darji
 *
 */
public class GeneralConfigData {
	
	private String userName;
	private String password;
	private Boolean isHunterActive;
	private String url;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsHunterActive() {
		return isHunterActive;
	}
	public void setIsHunterActive(Boolean isHunterActive) {
		this.isHunterActive = isHunterActive;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
