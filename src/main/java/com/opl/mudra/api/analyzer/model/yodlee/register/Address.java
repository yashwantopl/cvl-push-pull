package com.opl.mudra.api.analyzer.model.yodlee.register;

public class Address {
	private String address1;
	private String state;
	private String city;
	private String zip;
	private String country;
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Address(String address1, String state, String city, String zip, String country) {
		super();
		this.address1 = address1;
		this.state = state;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	
	public Address()
	{
		
	}
	
	
}
