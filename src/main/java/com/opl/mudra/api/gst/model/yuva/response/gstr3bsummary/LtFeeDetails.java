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
public class LtFeeDetails {
	
	private Double iamt;
	
	private Double camt;
	
	private Double samt;
	
	private Double csamt;

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
	 * @return the camt
	 */
	public Double getCamt() {
		return camt;
	}

	/**
	 * @param camt the camt to set
	 */
	public void setCamt(Double camt) {
		this.camt = camt;
	}

	/**
	 * @return the samt
	 */
	public Double getSamt() {
		return samt;
	}

	/**
	 * @param samt the samt to set
	 */
	public void setSamt(Double samt) {
		this.samt = samt;
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


	public LtFeeDetails() {
		this.iamt = 0.0;
		this.camt = 0.0;
		this.samt = 0.0;
		this.csamt = 0.0;
	}
}
