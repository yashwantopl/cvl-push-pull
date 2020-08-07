package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Top3Cus {
	
	private String pan;
	
	private Double share;
	
	@JsonProperty("ttl_rec")
	private Double ttlRec;
	
	@JsonProperty("ttl_tax")
	private Double ttlTax;
	
	private String name;
	
	@JsonProperty("ttl_val")
	
	
	
	private Double ttlVal;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getShare() {
		return share;
	}

	public void setShare(Double share) {
		this.share = share;
	}

	public Double getTtlRec() {
		return ttlRec;
	}

	public void setTtlRec(Double ttlRec) {
		this.ttlRec = ttlRec;
	}

	public Double getTtlTax() {
		return ttlTax;
	}

	public void setTtlTax(Double ttlTax) {
		this.ttlTax = ttlTax;
	}
	
	

}
