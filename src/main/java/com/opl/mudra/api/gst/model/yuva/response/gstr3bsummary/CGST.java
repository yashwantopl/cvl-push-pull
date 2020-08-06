/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CGST {
	
private Double tx;
	
	private Double intr;
	
	private Double fee;

	/**
	 * @return the tx
	 */
	public Double getTx() {
		return tx;
	}

	/**
	 * @param tx the tx to set
	 */
	public void setTx(Double tx) {
		this.tx = tx;
	}

	/**
	 * @return the intr
	 */
	public Double getIntr() {
		return intr;
	}

	/**
	 * @param intr the intr to set
	 */
	public void setIntr(Double intr) {
		this.intr = intr;
	}

	/**
	 * @return the fee
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * @param fee the fee to set
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	public CGST() {
		this.tx = 0.0;
		this.intr = 0.0;
		this.fee = 0.0;
	}
}
