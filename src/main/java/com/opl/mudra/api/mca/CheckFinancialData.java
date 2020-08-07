/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket.devare
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CheckFinancialData {
	
	private String request;
	
	private CheckFinancialDataPara para;

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

	/**
	 * @return the para
	 */
	public CheckFinancialDataPara getPara() {
		return para;
	}

	/**
	 * @param para the para to set
	 */
	public void setPara(CheckFinancialDataPara para) {
		this.para = para;
	}
	
	
	

}
