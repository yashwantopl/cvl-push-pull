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
public class McaFinancialFileData {
	
	
	@JsonProperty("reference_id")
	private String referenceId;
	private String status;
	private String type;
	@JsonProperty("metadata")
	private List<FileMetaData> metaData;
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<FileMetaData> getMetaData() {
		return metaData;
	}
	public void setMetaData(List<FileMetaData> metaData) {
		this.metaData = metaData;
	}
	
	
}
