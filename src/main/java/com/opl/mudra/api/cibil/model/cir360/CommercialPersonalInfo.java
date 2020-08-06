package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialPersonalInfo {

	// JsonProperty("CommercialName")
	// private String commercialName;

	@JsonProperty("BusinessName")
	private String businessName;

	@JsonProperty("BusinessShortName")
	private String businessShortName;

	@JsonProperty("BusinessLegalConstitution")
	private String businessLegalConstitution;

	@JsonProperty("BusinessCategory")
	private String businessCategory;

	@JsonProperty("BusinessIndustryType")
	private String businessIndustryType;

	@JsonProperty("DateIncorporation")
	private String dateincorporation;

	@JsonProperty("SalesFigure")
	private String salesfigure;

	@JsonProperty("ClassActivity")
	private String classactivity;

	@JsonProperty("EmployeeCount")
	private String employeecount;

	public String getDateincorporation() {
		return dateincorporation;
	}

	public void setDateincorporation(String dateincorporation) {
		this.dateincorporation = dateincorporation;
	}

	public String getSalesfigure() {
		return salesfigure;
	}

	public void setSalesfigure(String salesfigure) {
		this.salesfigure = salesfigure;
	}

	public String getClassactivity() {
		return classactivity;
	}

	public void setClassactivity(String classactivity) {
		this.classactivity = classactivity;
	}

	public String getEmployeecount() {
		return employeecount;
	}

	public void setEmployeecount(String employeecount) {
		this.employeecount = employeecount;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getBusinessShortName() {
		return businessShortName;
	}

	public void setBusinessShortName(String businessShortName) {
		this.businessShortName = businessShortName;
	}

	public String getBusinessLegalConstitution() {
		return businessLegalConstitution;
	}

	public void setBusinessLegalConstitution(String businessLegalConstitution) {
		this.businessLegalConstitution = businessLegalConstitution;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getBusinessIndustryType() {
		return businessIndustryType;
	}

	public void setBusinessIndustryType(String businessIndustryType) {
		this.businessIndustryType = businessIndustryType;
	}

}
