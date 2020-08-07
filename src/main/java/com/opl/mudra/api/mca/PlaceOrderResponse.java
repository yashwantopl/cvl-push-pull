package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class PlaceOrderResponse {
	
	private String status;
	
	private String message;
	
	@JsonProperty("work-order-details")
	private PlaceOrderWorkOrderDetailsResponse[] workOrderDetails;
	

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PlaceOrderWorkOrderDetailsResponse[] getWorkOrderDetails() {
		return workOrderDetails;
	}

	public void setWorkOrderDetails(PlaceOrderWorkOrderDetailsResponse[] workOrderDetails) {
		this.workOrderDetails = workOrderDetails;
	}

	
	

}
