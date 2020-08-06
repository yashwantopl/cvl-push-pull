/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class IntrDetails {

	private Double camt;

	private Double csamt;

	private Double iamt;

	private Double samt;

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
	
	

}
