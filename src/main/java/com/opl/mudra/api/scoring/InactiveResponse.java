package com.opl.mudra.api.scoring;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InactiveResponse implements Serializable {
	
	private Long id;
	private String scoringName;
	private Date createdDate;
	private Integer Status;
	private Integer typeOfEmp;
	private Date inactiveDate;
	private Long scoringId;
	private Long empWith;
	public String getScoringName() {
		return scoringName;
	}
	public void setScoringName(String scoringName) {
		this.scoringName = scoringName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	
	public Integer getTypeOfEmp() {
		return typeOfEmp;
	}
	public void setTypeOfEmp(Integer typeOfEmp) {
		this.typeOfEmp = typeOfEmp;
	}
	public Date getInactiveDate() {
		return inactiveDate;
	}
	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	public Long getScoringId() {
		return scoringId;
	}
	public void setScoringId(Long scoringId) {
		this.scoringId = scoringId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEmpWith() {
		return empWith;
	}
	public void setEmpWith(Long empWith) {
		this.empWith = empWith;
	}
	
	
	
	
	
	
	
	
	

}
