package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 08-Jul-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRDirectorResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long applicationId;
	
	private Long directorId;
	
	private String directorName;
	
	private String directorPan;
	
	private Boolean isSuccess;
	
	private Boolean isUpload;

	/**
	 * @return the applicationId
	 */
	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the directorId
	 */
	public Long getDirectorId() {
		return directorId;
	}

	/**
	 * @param directorId the directorId to set
	 */
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	/**
	 * @return the directorName
	 */
	public String getDirectorName() {
		return directorName;
	}

	/**
	 * @param directorName the directorName to set
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	/**
	 * @return the isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the isUpload
	 */
	public Boolean getIsUpload() {
		return isUpload;
	}

	/**
	 * @param isUpload the isUpload to set
	 */
	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
	}

	/**
	 * @return the directorPan
	 */
	public String getDirectorPan() {
		return directorPan;
	}

	/**
	 * @param directorPan the directorPan to set
	 */
	public void setDirectorPan(String directorPan) {
		this.directorPan = directorPan;
	}
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ITRDirectorResponse [id=" + id + "applicationId=" + applicationId + ", directorId=" + directorId + ", directorName="
				+ directorName + ", directorPan=" + directorPan + ", isSuccess=" + isSuccess + ", isUpload=" + isUpload
				+ "]";
	}
	
	
	
	
}
