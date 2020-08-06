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

/**
 * @author sanket.devare
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class OsupZero {

	private Double txval;
	private Double iamt;
	private Double csamt;
	private Double camt;
	private Double samt;
	
	
	
	
	public Double getCamt() {
		return camt;
	}
	public void setCamt(Double camt) {
		this.camt = camt;
	}
	public Double getSamt() {
		return samt;
	}
	public void setSamt(Double samt) {
		this.samt = samt;
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

	public OsupZero() {
		this.txval = 0.0;
		this.iamt = 0.0;
		this.csamt = 0.0;
	}
}
