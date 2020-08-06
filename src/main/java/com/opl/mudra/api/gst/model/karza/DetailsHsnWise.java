package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DetailsHsnWise {
	
	private Double camt;
	
	private Double csamt;
	
	private String desc;
	
	@JsonProperty("hsn_sc")
	private String hsnSc;
	
	private Double iamt;
	
	private Double num;
	
	private Double qty;
	
	private Double samt;
	
	private Double txval;
	
	private Double val;
	
	// field added as per new response
	
	private String uqc;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public Double getCsamt() {
		return csamt;
	}

	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getHsnSc() {
		return hsnSc;
	}

	public void setHsnSc(String hsnSc) {
		this.hsnSc = hsnSc;
	}

	public Double getIamt() {
		return iamt;
	}

	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getSamt() {
		return samt;
	}

	public void setSamt(Double samt) {
		this.samt = samt;
	}

	public Double getTxval() {
		return txval;
	}

	public void setTxval(Double txval) {
		this.txval = txval;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	/**
	 * @return the uqc
	 */
	public String getUqc() {
		return uqc;
	}

	/**
	 * @param uqc the uqc to set
	 */
	public void setUqc(String uqc) {
		this.uqc = uqc;
	}

	
}
