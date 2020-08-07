/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Itc {
	
	private String elg;
	private Double tx_c;
	private Double tx_cs;
	private Double tx_s;
	private Double tx_i;
	
	
	
	
	/**
	 * @return the tx_i
	 */
	public Double getTx_i() {
		return tx_i;
	}
	/**
	 * @param tx_i the tx_i to set
	 */
	public void setTx_i(Double tx_i) {
		this.tx_i = tx_i;
	}
	/**
	 * @return the elg
	 */
	public String getElg() {
		return elg;
	}
	/**
	 * @param elg the elg to set
	 */
	public void setElg(String elg) {
		this.elg = elg;
	}
	/**
	 * @return the tx_c
	 */
	public Double getTx_c() {
		return tx_c;
	}
	/**
	 * @param tx_c the tx_c to set
	 */
	public void setTx_c(Double tx_c) {
		this.tx_c = tx_c;
	}
	/**
	 * @return the tx_cs
	 */
	public Double getTx_cs() {
		return tx_cs;
	}
	/**
	 * @param tx_cs the tx_cs to set
	 */
	public void setTx_cs(Double tx_cs) {
		this.tx_cs = tx_cs;
	}
	/**
	 * @return the tx_s
	 */
	public Double getTx_s() {
		return tx_s;
	}
	/**
	 * @param tx_s the tx_s to set
	 */
	public void setTx_s(Double tx_s) {
		this.tx_s = tx_s;
	}

	public Itc() {
		this.elg = "";
		this.tx_c = 0.0;
		this.tx_cs = 0.0;
		this.tx_s = 0.0;
		this.tx_i = 0.0;
	}
}
