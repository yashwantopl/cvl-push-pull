package com.opl.mudra.api.user.model;

import java.io.Serializable;

public class SpClientResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long clientId;
	private String clientEmail;
	private String clientName;
	private Integer clientCountry;
	private Integer clientState;
	private Integer clientCity;
	private String clientImagePath;
	private Long lastAccessId;
	
	public Long getLastAccessId() {
		return lastAccessId;
	}
	public void setLastAccessId(Long lastAccessId) {
		this.lastAccessId = lastAccessId;
	}
	public String getClientImagePath() {
		return clientImagePath;
	}
	public void setClientImagePath(String clientImagePath) {
		this.clientImagePath = clientImagePath;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Integer getClientCountry() {
		return clientCountry;
	}
	public void setClientCountry(Integer clientCountry) {
		this.clientCountry = clientCountry;
	}
	public Integer getClientState() {
		return clientState;
	}
	public void setClientState(Integer clientState) {
		this.clientState = clientState;
	}
	public Integer getClientCity() {
		return clientCity;
	}
	public void setClientCity(Integer clientCity) {
		this.clientCity = clientCity;
	}
	public SpClientResponse() {

		// Do nothing because of X and Y.
	}
		
	
}
