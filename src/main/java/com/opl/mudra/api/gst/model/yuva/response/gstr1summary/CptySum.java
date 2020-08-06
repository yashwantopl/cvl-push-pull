/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr1summary;

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
public class CptySum {
	
private String chksum;
	

	private String ctin;
	
	@JsonProperty("ttl_cess")
	private Double ttlCess;
	
	@JsonProperty("ttl_cgst")
	private Double ttlCgst;
	
	@JsonProperty("ttl_igst")
	private Double ttlIgst;
	
	@JsonProperty("ttl_rec")
	private Double ttlRec;
	
	@JsonProperty("ttl_sgst")
	private Double ttlSgst;
	
	@JsonProperty("ttl_tax")
	private Double ttlTax;
	
	@JsonProperty("ttl_val")
	private Double ttlVal;

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	public String getCtin() {
		return ctin;
	}

	public void setCtin(String ctin) {
		this.ctin = ctin;
	}

	public Double getTtlCess() {
		return ttlCess;
	}

	public void setTtlCess(Double ttlCess) {
		this.ttlCess = ttlCess;
	}

	public Double getTtlCgst() {
		return ttlCgst;
	}

	public void setTtlCgst(Double ttlCgst) {
		this.ttlCgst = ttlCgst;
	}

	public Double getTtlIgst() {
		return ttlIgst;
	}

	public void setTtlIgst(Double ttlIgst) {
		this.ttlIgst = ttlIgst;
	}

	public Double getTtlRec() {
		return ttlRec;
	}

	public void setTtlRec(Double ttlRec) {
		this.ttlRec = ttlRec;
	}

	public Double getTtlSgst() {
		return ttlSgst;
	}

	public void setTtlSgst(Double ttlSgst) {
		this.ttlSgst = ttlSgst;
	}

	public Double getTtlTax() {
		return ttlTax;
	}

	public void setTtlTax(Double ttlTax) {
		this.ttlTax = ttlTax;
	}

	public Double getTtlVal() {
		return ttlVal;
	}

	public void setTtlVal(Double ttlVal) {
		this.ttlVal = ttlVal;
	}


	public CptySum() {
		this.chksum = "";
		this.ctin = "";
		this.ttlCess = 0.0;
		this.ttlCgst = 0.0;
		this.ttlIgst = 0.0;
		this.ttlRec = 0.0;
		this.ttlSgst = 0.0;
		this.ttlTax = 0.0;
		this.ttlVal = 0.0;
	}
}
