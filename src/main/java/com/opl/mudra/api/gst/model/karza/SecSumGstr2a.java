package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SecSumGstr2a {

	@JsonProperty("cpty_sum")
	private CptySumGstr2a[] cptySum;

	@JsonProperty("sec_nm")
	private String secNm;

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

	public CptySumGstr2a[] getCptySum() {
		return cptySum;
	}

	public void setCptySum(CptySumGstr2a[] cptySum) {
		this.cptySum = cptySum;
	}

	public String getSecNm() {
		return secNm;
	}

	public void setSecNm(String secNm) {
		this.secNm = secNm;
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
		return "SecSumGstr2a [cptySum=" + Arrays.toString(cptySum) + ", secNm=" + secNm + ", ttlCess=" + ttlCess
				+ ", ttlCgst=" + ttlCgst + ", ttlIgst=" + ttlIgst + ", ttlRec=" + ttlRec + ", ttlSgst=" + ttlSgst
				+ ", ttlTax=" + ttlTax + ", ttlVal=" + ttlVal + "]";
	}
	
	
}
