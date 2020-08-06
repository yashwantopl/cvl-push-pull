package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SecSum {
	
	
	private String chksum;
	
	@JsonProperty("sec_nm")
	private String SecNm;
	
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
	
	@JsonProperty("ttl_doc_issued")
	private Double ttlDocIssued;
	
	@JsonProperty("net_doc_issued")
	private Double netDocIssued;
	
	@JsonProperty("ttl_doc_cancelled")
	private Double ttlDocCancelled;
	
	@JsonProperty("ttl_ngsup_amt")
	private Double ttlNgsupAmt;
	
	@JsonProperty("ttl_expt_amt")
	private Double ttlExptAmt;
	
	@JsonProperty("ttl_nilsup_amt")
	private Double ttlNilsupAmt;
	
	@JsonProperty("cpty_sum")
	private CptySum[] cptySum;
	
	

	/**
	 * @return the cptySum
	 */
	public CptySum[] getCptySum() {
		return cptySum;
	}

	/**
	 * @param cptySum the cptySum to set
	 */
	public void setCptySum(CptySum[] cptySum) {
		this.cptySum = cptySum;
	}

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	public String getSecNm() {
		return SecNm;
	}

	public void setSecNm(String secNm) {
		SecNm = secNm;
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

	public Double getTtlDocIssued() {
		return ttlDocIssued;
	}

	public void setTtlDocIssued(Double ttlDocIssued) {
		this.ttlDocIssued = ttlDocIssued;
	}

	public Double getNetDocIssued() {
		return netDocIssued;
	}

	public void setNetDocIssued(Double netDocIssued) {
		this.netDocIssued = netDocIssued;
	}

	public Double getTtlDocCancelled() {
		return ttlDocCancelled;
	}

	public void setTtlDocCancelled(Double ttlDocCancelled) {
		this.ttlDocCancelled = ttlDocCancelled;
	}

	public Double getTtlNgsupAmt() {
		return ttlNgsupAmt;
	}

	public void setTtlNgsupAmt(Double ttlNgsupAmt) {
		this.ttlNgsupAmt = ttlNgsupAmt;
	}

	public Double getTtlExptAmt() {
		return ttlExptAmt;
	}

	public void setTtlExptAmt(Double ttlExptAmt) {
		this.ttlExptAmt = ttlExptAmt;
	}

	public Double getTtlNilsupAmt() {
		return ttlNilsupAmt;
	}

	public void setTtlNilsupAmt(Double ttlNilsupAmt) {
		this.ttlNilsupAmt = ttlNilsupAmt;
	}
	
	
	
}
