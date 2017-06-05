package com.capitaworld.service.loans.model;

import java.util.List;

public class FpProductDetails {
	
	List<String> geographicalFocus;
	
	String typeOfInvestment;
	
	Object fpDashboard;

	public List<String> getGeographicalFocus() {
		return geographicalFocus;
	}

	public void setGeographicalFocus(List<String> geographicalFocus) {
		this.geographicalFocus = geographicalFocus;
	}

	public String getTypeOfInvestment() {
		return typeOfInvestment;
	}

	public void setTypeOfInvestment(String typeOfInvestment) {
		this.typeOfInvestment = typeOfInvestment;
	}

	public Object getFpDashboard() {
		return fpDashboard;
	}

	public void setFpDashboard(Object fpDashboard) {
		this.fpDashboard = fpDashboard;
	}
	
	

}
