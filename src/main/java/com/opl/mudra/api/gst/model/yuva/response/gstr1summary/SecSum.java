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
public class SecSum {
	
	private String chksum;
	
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
	
	@JsonProperty("ttl_expt_amt")
	private Double ttlExptAmt;
	
	@JsonProperty("ttl_ngsup_amt")
	private Double ttlNgsupAmt;
	
	@JsonProperty("ttl_nilsup_amt")
	private Double ttlNilSupAmt;

	
	
	
	/**
	 * @return the ttlExptAmt
	 */
	public Double getTtlExptAmt() {
		return ttlExptAmt;
	}

	/**
	 * @param ttlExptAmt the ttlExptAmt to set
	 */
	public void setTtlExptAmt(Double ttlExptAmt) {
		this.ttlExptAmt = ttlExptAmt;
	}

	/**
	 * @return the ttlNgsupAmt
	 */
	public Double getTtlNgsupAmt() {
		return ttlNgsupAmt;
	}

	/**
	 * @param ttlNgsupAmt the ttlNgsupAmt to set
	 */
	public void setTtlNgsupAmt(Double ttlNgsupAmt) {
		this.ttlNgsupAmt = ttlNgsupAmt;
	}

	/**
	 * @return the ttlNilSupAmt
	 */
	public Double getTtlNilSupAmt() {
		return ttlNilSupAmt;
	}

	/**
	 * @param ttlNilSupAmt the ttlNilSupAmt to set
	 */
	public void setTtlNilSupAmt(Double ttlNilSupAmt) {
		this.ttlNilSupAmt = ttlNilSupAmt;
	}

	@JsonProperty("cpty_sum")
	private CptySum[] cptySum;

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
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

	public CptySum[] getCptySum() {
		return cptySum;
	}

	public void setCptySum(CptySum[] cptySum) {
		this.cptySum = cptySum;
	}

	public SecSum() {

		this.chksum = "";
		this.secNm = "";
		this.ttlCess = 0.0;
		this.ttlCgst = 0.0;
		this.ttlIgst = 0.0;
		this.ttlRec = 0.0;
		this.ttlSgst = 0.0;
		this.ttlTax = 0.0;
		this.ttlVal = 0.0;
		this.ttlExptAmt = 0.0;
		this.ttlNgsupAmt = 0.0;
		this.ttlNilSupAmt = 0.0;
		this.cptySum = new CptySum[0];
	}
}
