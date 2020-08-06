/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Map;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TranCredit {

	 private Map<String, Object> tranCredit;

	/**
	 * @return the tranCredit
	 */
	public Map<String, Object> getTranCredit() {
		return tranCredit;
	}

	/**
	 * @param tranCredit the tranCredit to set
	 */
	public void setTranCredit(Map<String, Object> tranCredit) {
		this.tranCredit = tranCredit;
	}
	 
	 
	 
	
}
