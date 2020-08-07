/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DetailsCurrunt {
	
	@JsonProperty("fr_dt")
	private String 	frDt;
	
	@JsonProperty("gstin")
	private String 	gstin;
	
	@JsonProperty("to_dt")
	private String 	toDt;
	
	@JsonProperty("tr")
	private Tr[] tr;

	/**
	 * @return the frDt
	 */
	public String getFrDt() {
		return frDt;
	}

	/**
	 * @param frDt the frDt to set
	 */
	public void setFrDt(String frDt) {
		this.frDt = frDt;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	/**
	 * @return the toDt
	 */
	public String getToDt() {
		return toDt;
	}

	/**
	 * @param toDt the toDt to set
	 */
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}

	/**
	 * @return the tr
	 */
	public Tr[] getTr() {
		return tr;
	}

	/**
	 * @param tr the tr to set
	 */
	public void setTr(Tr[] tr) {
		this.tr = tr;
	}
	
	
	

}
