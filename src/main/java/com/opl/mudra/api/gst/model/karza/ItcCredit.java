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
public class ItcCredit {
	
	
	@JsonProperty("detail")
	private DetailsCurrunt[] detailsCurrunt;
	
	@JsonProperty("tran_credit")
	private TranCredit tranCredit;

	/**
	 * @return the detailsCurrunt
	 */
	public DetailsCurrunt[] getDetailsCurrunt() {
		return detailsCurrunt;
	}

	/**
	 * @param detailsCurrunt the detailsCurrunt to set
	 */
	public void setDetailsCurrunt(DetailsCurrunt[] detailsCurrunt) {
		this.detailsCurrunt = detailsCurrunt;
	}

	/**
	 * @return the tranCredit
	 */
	public TranCredit getTranCredit() {
		return tranCredit;
	}

	/**
	 * @param tranCredit the tranCredit to set
	 */
	public void setTranCredit(TranCredit tranCredit) {
		this.tranCredit = tranCredit;
	}
	
	
	

}
