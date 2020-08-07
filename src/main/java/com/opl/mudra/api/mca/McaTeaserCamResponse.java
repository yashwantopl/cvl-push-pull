/**
 * 
 */
package com.opl.mudra.api.mca;

import java.io.Serializable;
import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class McaTeaserCamResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<McaFinancialCalculationResponse> mcaFinancialCalculationResponse;

	public List<McaFinancialCalculationResponse> getMcaFinancialCalculationResponse() {
		return mcaFinancialCalculationResponse;
	}

	public void setMcaFinancialCalculationResponse(List<McaFinancialCalculationResponse> mcaFinancialCalculationResponse) {
		this.mcaFinancialCalculationResponse = mcaFinancialCalculationResponse;
	}
	
	
	

}
