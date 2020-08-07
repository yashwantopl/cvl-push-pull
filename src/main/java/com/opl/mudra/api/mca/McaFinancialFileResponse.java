/**
 * 
 */
package com.opl.mudra.api.mca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay.darji
 *
 */
public class McaFinancialFileResponse {
	
	private Integer status;
	private String message;
	@JsonProperty("data")
	private List<McaFinancialFileData> data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<McaFinancialFileData> getData() {
		return data;
	}
	public void setData(List<McaFinancialFileData> data) {
		this.data = data;
	}
	
}
