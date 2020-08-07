package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SecuritySgmnt {

	private String value_of_security;
	private String currency_type;
	private String type_of_security;
	private String security_classification;
	private String date_of_valuation;

	public String getValue_of_security() {
		return value_of_security;
	}

	public void setValue_of_security(String value_of_security) {
		this.value_of_security = value_of_security;
	}

	public String getCurrency_type() {
		return currency_type;
	}

	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}

	public String getType_of_security() {
		return type_of_security;
	}

	public void setType_of_security(String type_of_security) {
		this.type_of_security = type_of_security;
	}

	public String getSecurity_classification() {
		return security_classification;
	}

	public void setSecurity_classification(String security_classification) {
		this.security_classification = security_classification;
	}

	public String getDate_of_valuation() {
		return date_of_valuation;
	}

	public void setDate_of_valuation(String date_of_valuation) {
		this.date_of_valuation = date_of_valuation;
	}

}
