package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * @author usertwelve
 *
 */

/**
 * @author usertwelve
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CptySumGstr2a {

	@JsonProperty("cfs")
	private String cfs;

	@JsonProperty("cname")
	private String cName;

	@JsonProperty("ctin")
	private String ctin;

	@JsonProperty("nt")
	private Nt[] nt;

	@JsonProperty("inv")
	private InvGstr2A[] inv;

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

	public InvGstr2A[] getInv() {
		return inv;
	}

	public void setInv(InvGstr2A[] inv) {
		this.inv = inv;
	}

	public String getCfs() {
		return cfs;
	}

	public void setCfs(String cfs) {
		this.cfs = cfs;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getCtin() {
		return ctin;
	}

	public void setCtin(String ctin) {
		this.ctin = ctin;
	}

	public Nt[] getNt() {
		return nt;
	}

	public void setNt(Nt[] nt) {
		this.nt = nt;
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

	
	
	@Override
	public String toString() {
		return "CptySumGstr2a [cfs=" + cfs + ", cName=" + cName + ", ctin=" + ctin + ", nt=" + Arrays.toString(nt)
				+ ", ttlCess=" + ttlCess + ", ttlCgst=" + ttlCgst + ", ttlIgst=" + ttlIgst + ", ttlRec=" + ttlRec
				+ ", ttlSgst=" + ttlSgst + ", ttlTax=" + ttlTax + ", ttlVal=" + ttlVal + "]";
	}
	
	

}
