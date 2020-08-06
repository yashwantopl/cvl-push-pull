package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CptySum {

	private String name;
	
	private String pan;
	
	@JsonProperty("transactions")
	private Transactions transactions;

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


	private String ctin;




	/**
	 * @return the ttlCess
	 */
	public Double getTtlCess() {
		return ttlCess;
	}

	/**
	 * @param ttlCess the ttlCess to set
	 */
	public void setTtlCess(Double ttlCess) {
		this.ttlCess = ttlCess;
	}

	/**
	 * @return the ttlCgst
	 */
	public Double getTtlCgst() {
		return ttlCgst;
	}

	/**
	 * @param ttlCgst the ttlCgst to set
	 */
	public void setTtlCgst(Double ttlCgst) {
		this.ttlCgst = ttlCgst;
	}

	/**
	 * @return the ttlIgst
	 */
	public Double getTtlIgst() {
		return ttlIgst;
	}

	/**
	 * @param ttlIgst the ttlIgst to set
	 */
	public void setTtlIgst(Double ttlIgst) {
		this.ttlIgst = ttlIgst;
	}

	/**
	 * @return the ttlRec
	 */
	public Double getTtlRec() {
		return ttlRec;
	}

	/**
	 * @param ttlRec the ttlRec to set
	 */
	public void setTtlRec(Double ttlRec) {
		this.ttlRec = ttlRec;
	}

	/**
	 * @return the ttlSgst
	 */
	public Double getTtlSgst() {
		return ttlSgst;
	}

	/**
	 * @param ttlSgst the ttlSgst to set
	 */
	public void setTtlSgst(Double ttlSgst) {
		this.ttlSgst = ttlSgst;
	}

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

	/**
	 * @return the ctin
	 */
	public String getCtin() {
		return ctin;
	}

	/**
	 * @param ctin the ctin to set
	 */
	public void setCtin(String ctin) {
		this.ctin = ctin;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

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
	
	
}
