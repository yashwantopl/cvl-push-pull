package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.cibil.utils.CibilUtils.IdentityTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityTypeRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3470045296913007764L;

	private IdentityTypeEnum typeEnum;
	private String value;

	public IdentityTypeEnum getTypeEnum() {
		return typeEnum;
	}

	public void setTypeEnum(IdentityTypeEnum typeEnum) {
		this.typeEnum = typeEnum;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
