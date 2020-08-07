/**
 * 
 */
package com.opl.mudra.api.mca;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Details {
	
	@JsonProperty("details")
	private Map<String, Object> details;

	/**
	 * @return the details
	 */
	public Map<String, Object> getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	
}
