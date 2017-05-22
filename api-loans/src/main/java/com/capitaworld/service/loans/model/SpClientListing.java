package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class SpClientListing implements Serializable {

	private Long clientId;
	private String clientEmail;
	private String clientName;
	private String clientCountry;
	private String clientState;
	private String clientCity;
	private List<?> listData = Collections.emptyList();
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
	
	public String getClientCountry() {
		return clientCountry;
	}
	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}
	public String getClientState() {
		return clientState;
	}
	public void setClientState(String clientState) {
		this.clientState = clientState;
	}
	public String getClientCity() {
		return clientCity;
	}
	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}
	public List<?> getListData() {
		return listData;
	}
	public void setListData(List<?> listData) {
		this.listData = listData;
	}
	public SpClientListing() {
	
		// TODO Auto-generated constructor stub
	}
	
	
}
