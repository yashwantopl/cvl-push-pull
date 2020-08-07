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
public class MonthWiseSummary {
	
	
	@JsonProperty("gstr1")
	private Gstr1MWD gstr1mwd;
	
	@JsonProperty("gstr2a")
	private Gstr2aMWD gstr2amwd;

	@JsonProperty("gstr3b")
	private Gstr3bMWD gstr3bmwd;

	@JsonProperty("ret_period")
	private String retPeriod;

	/**
	 * @return the gstr1mwd
	 */
	public Gstr1MWD getGstr1mwd() {
		return gstr1mwd;
	}

	/**
	 * @param gstr1mwd the gstr1mwd to set
	 */
	public void setGstr1mwd(Gstr1MWD gstr1mwd) {
		this.gstr1mwd = gstr1mwd;
	}

	/**
	 * @return the gstr2amwd
	 */
	public Gstr2aMWD getGstr2amwd() {
		return gstr2amwd;
	}

	/**
	 * @param gstr2amwd the gstr2amwd to set
	 */
	public void setGstr2amwd(Gstr2aMWD gstr2amwd) {
		this.gstr2amwd = gstr2amwd;
	}

	/**
	 * @return the gstr3bmwd
	 */
	public Gstr3bMWD getGstr3bmwd() {
		return gstr3bmwd;
	}

	/**
	 * @param gstr3bmwd the gstr3bmwd to set
	 */
	public void setGstr3bmwd(Gstr3bMWD gstr3bmwd) {
		this.gstr3bmwd = gstr3bmwd;
	}

	/**
	 * @return the retPeriod
	 */
	public String getRetPeriod() {
		return retPeriod;
	}

	/**
	 * @param retPeriod the retPeriod to set
	 */
	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}
	
	
	
}
