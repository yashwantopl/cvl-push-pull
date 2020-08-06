/**
 * 
 */
package com.opl.mudra.api.dms.model;

/**
 * @author nilay.darji
 *
 */
public class DmsSftpFileMoveRequest { // add req param as per needed for any service
	
	private Long applicationId;
	private String fileName;

	
	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
