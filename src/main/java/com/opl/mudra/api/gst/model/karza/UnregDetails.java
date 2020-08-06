package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class UnregDetails {

	private Double iamt;
	
	private Double txval;
	
	private String pos;

	/**
	 * @return the iamt
	 */
	public Double getIamt() {
		return iamt;
	}

	/**
	 * @param iamt the iamt to set
	 */
	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	/**
	 * @return the pos
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}

	/**
	 * @return the txval
	 */
	public Double getTxval() {
		return txval;
	}

	/**
	 * @param txval the txval to set
	 */
	public void setTxval(Double txval) {
		this.txval = txval;
	}
	
	

}
