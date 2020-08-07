/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Gstr3bMWD {

	

	@JsonProperty("ttl_tax")
	private Double ttlTax;
	

	@JsonProperty("ttl_val")
	private Double ttlVal;


	/**
	 * @return the ttlTax
	 */
	public Double getTtlTax() {
		return ttlTax;
	}


	/**
	 * @param ttlTax the ttlTax to set
	 */
	public void setTtlTax(Double ttlTax) {
		this.ttlTax = ttlTax;
	}


	/**
	 * @return the ttlVal
	 */
	public Double getTtlVal() {
		return ttlVal;
	}


	/**
	 * @param ttlVal the ttlVal to set
	 */
	public void setTtlVal(Double ttlVal) {
		this.ttlVal = ttlVal;
	}
	
	
}
