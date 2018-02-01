package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRAuthorizedSignDetailsRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;

	private String name;
	
	private String designation;

	private String documentObtained;

	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDdrFormId() {
		return ddrFormId;
	}

	public void setDdrFormId(Long ddrFormId) {
		this.ddrFormId = ddrFormId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDocumentObtained() {
		return documentObtained;
	}

	public void setDocumentObtained(String documentObtained) {
		this.documentObtained = documentObtained;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "DDRAuthorizedSignDetailsRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", name=" + name
				+ ", designation=" + designation + ", documentObtained=" + documentObtained + ", isActive=" + isActive
				+ "]";
	}
	
	

}
