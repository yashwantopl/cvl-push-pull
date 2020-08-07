package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ItemDetGstr2a {

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("rt")
	private Double rt;

	@JsonProperty("samt")
	private Double samt;
	
	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("txval")
	private Double txVal;

	@JsonProperty("iamt")
	private Double iamt;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public Double getRt() {
		return rt;
	}

	public void setRt(Double rt) {
		this.rt = rt;
	}

	public Double getSamt() {
		return samt;
	}

	public void setSamt(Double samt) {
		this.samt = samt;
	}

	public Double getTxVal() {
		return txVal;
	}

	public void setTxVal(Double txVal) {
		this.txVal = txVal;
	}

	
	/**
	 * @return the csamt
	 */
	public Double getCsamt() {
		return csamt;
	}

	/**
	 * @param csamt the csamt to set
	 */
	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}

	public Double getIamt() {
		return iamt;
	}

	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	@Override
	public String toString() {
		return "ItemDetGstr2a [camt=" + camt + ", rt=" + rt + ", samt=" + samt + ", txVal=" + txVal + "]";
	}
	
}
