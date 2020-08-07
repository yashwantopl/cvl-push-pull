package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DirectorsDetails {

	private DirectorsData data;

	public DirectorsData getData() {
		return data;
	}

	public void setData(DirectorsData data) {
		this.data = data;
	}
	
	
}
