package com.opl.mudra.api.analyzer.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Statement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MultipartFile> multipartFiles;
	
	private String password;
	
	private String id;
	
	private Long applicationId;
	
	private List<String> passList;
	
	private Long existingReportId;

	
	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public List<String> getPassList() {
		return passList;
	}

	public void setPassList(List<String> passList) {
		this.passList = passList;
	}

	public Long getExistingReportId() {
		return existingReportId;
	}

	public void setExistingReportId(Long existingReportId) {
		this.existingReportId = existingReportId;
	}
	
	


	
	
	
}
