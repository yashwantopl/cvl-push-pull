package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class HsnWiseTransactions {
	
	private Double camt;
	
	private Double iamt;
	
	private Double num;
	
	private Double qty;
	
	private Double samt;
	
	private Double txval;
	
	private Double val;
	
	// new fields added as per new response
	private String uqc;
	
	private Double avgrate;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
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

	/**
	 * @return the avgrate
	 */
	public Double getAvgrate() {
		return avgrate;
	}

	/**
	 * @param avgrate the avgrate to set
	 */
	public void setAvgrate(Double avgrate) {
		this.avgrate = avgrate;
	}
	
	

}
