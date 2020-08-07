/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay.darji
 *
 */
public class FileMetaData {
	
	@JsonProperty("file-size")
	private String fileSize;
	@JsonProperty("file-name")
	private String fileName;
	@JsonProperty("file-path")
	private String filePath;
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
