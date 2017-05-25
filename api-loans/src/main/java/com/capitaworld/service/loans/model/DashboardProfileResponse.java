package com.capitaworld.service.loans.model;

import java.io.Serializable;

import com.capitaworld.service.loans.utils.CommonUtils;

public class DashboardProfileResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer productId;
	private Long applicationId;
	private String name;
	private String city;
	private String state;
	private String country;
	private String address;
	private String about;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getAddress() {
		return address;
	}
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public void setAddress() {
		String finalAddress = CommonUtils.isObjectNullOrEmpty(this.city) ? "NA ," : this.city +" ,"; 
		finalAddress = finalAddress.concat(CommonUtils.isObjectNullOrEmpty(this.state) ? "NA ," : this.state +" ,");
		finalAddress = finalAddress.concat(CommonUtils.isObjectNullOrEmpty(this.country) ? "NA"  : this.country);
		this.address = finalAddress;
	}
	
}
