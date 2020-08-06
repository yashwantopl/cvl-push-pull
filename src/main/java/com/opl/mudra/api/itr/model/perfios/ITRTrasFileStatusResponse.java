/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.itr.model.perfios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRTrasFileStatusResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String fileName;
	private String status;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ITRTrasFileStatusResponse [fileName=" + fileName + ", status=" + status + "]";
	}
	
	
	
	

}
