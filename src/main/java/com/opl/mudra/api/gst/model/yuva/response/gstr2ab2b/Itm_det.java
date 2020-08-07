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
public class Itm_det {
	
	private Double camt;
	private Double csamt;
	private Double rt;
	private Double samt;
	private Double txval;
	private Double iamt;
	
	
	
	
	/**
	 * @return the iamt
	 */
	public Double getIamt() {
		return iamt != null ? iamt : 0.0;
	}
	/**
	 * @param iamt the iamt to set
	 */
	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}
	/**
	 * @return the camt
	 */
	public Double getCamt() {
		return camt != null ? camt : 0.0;
	}
	/**
	 * @param camt the camt to set
	 */
	public void setCamt(Double camt) {
		this.camt = camt;
	}
	/**
	 * @return the csamt
	 */
	public Double getCsamt() {
		return csamt != null ? csamt : 0.0;
	}
	/**
	 * @param csamt the csamt to set
	 */
	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}
	/**
	 * @return the rt
	 */
	public Double getRt() {
		return rt;
	}
	/**
	 * @param rt the rt to set
	 */
	public void setRt(Double rt) {
		this.rt = rt;
	}
	/**
	 * @return the samt
	 */
	public Double getSamt() {
		return samt != null ? samt : 0.0;
	}
	/**
	 * @param samt the samt to set
	 */
	public void setSamt(Double samt) {
		this.samt = samt;
	}
	/**
	 * @return the txval
	 */
	public Double getTxval() {
		return txval != null ? txval : 0.0;
	}
	/**
	 * @param txval the txval to set
	 */
	public void setTxval(Double txval) {
		this.txval = txval;
	}

	public Itm_det() {
		this.camt = 0.0;
		this.csamt = 0.0;
		this.rt = 0.0;
		this.samt = 0.0;
		this.txval = 0.0;
		this.iamt = 0.0;
	}
}
