/**
 * 
 */
package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay.darji
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GeneralConfigData {
	
	private String userName;
	private String password;
	private Boolean isHunterActive;
	private String url;
	private Integer eclgsFlowType;
	
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
	public Integer getEclgsFlowType() {
		return eclgsFlowType;
	}
	public void setEclgsFlowType(Integer eclgsFlowType) {
		this.eclgsFlowType = eclgsFlowType;
	}
}
