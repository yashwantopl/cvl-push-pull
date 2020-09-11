/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr1summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.gst.model.yuva.response.authentication.Error;


/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Gstr1SummaryResponse {
	
	@JsonProperty("status_cd")
	private String statusCd;
	
	private Error error;
	
	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Error error) {
		this.error = error;
	}

	private String chksum;
	
	private String gstin;
	
	@JsonProperty("ret_period")
	private String ratePeriod;
	
	@JsonProperty("summ_typ")
	private String summTyp;
	
	@JsonProperty("sec_sum")
	private SecSum[] secSum;

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getRatePeriod() {
		return ratePeriod;
	}

	public void setRatePeriod(String ratePeriod) {
		this.ratePeriod = ratePeriod;
	}

	public String getSummTyp() {
		return summTyp;
	}

	public void setSummTyp(String summTyp) {
		this.summTyp = summTyp;
	}

	public SecSum[] getSecSum() {
		return secSum;
	}

	public void setSecSum(SecSum[] secSum) {
		this.secSum = secSum;
	}


	public Gstr1SummaryResponse() {
		this.statusCd = "";
		this.error = new Error();
		this.chksum = "";
		this.gstin = "";
		this.ratePeriod = "";
		this.summTyp = "";
		this.secSum = new SecSum[0];
	}
}
