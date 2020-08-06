/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr1summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GSTDataResponse {
	
	@JsonProperty("status_cd")
	private String statusCd;
	
	private String data;
	
	private String rek;
	
	private String hmac;
	
	private Error error;

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
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the rek
	 */
	public String getRek() {
		return rek;
	}

	/**
	 * @param rek the rek to set
	 */
	public void setRek(String rek) {
		this.rek = rek;
	}

	/**
	 * @return the hmac
	 */
	public String getHmac() {
		return hmac;
	}

	/**
	 * @param hmac the hmac to set
	 */
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GSTDataResponse [statusCd=" + statusCd + ", data=" + data + ", rek=" + rek + ", hmac=" + hmac
				+ ", error=" + error + "]";
	}


	public GSTDataResponse() {
		this.statusCd = "";
		this.data = "";
		this.rek = "";
		this.hmac = "";
		this.error = new Error();
	}
}
