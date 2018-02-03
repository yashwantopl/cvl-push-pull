package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFinancialSummaryRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;
	
	private Integer perticularId;
	
	private String perticularName;
	
	private Double provisionalYear;
	
	private Double lastYear;
	
	private Double lastToLastYear;
	
	private Double diffPfPrvsnlAndLastYear;
	
	private Long createdBy;
	
	private Date createdDate;

	private Long modifyBy;
	
	private Date modifyDate;
	
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



	public String getPerticularName() {
		return perticularName;
	}

	public void setPerticularName(String perticularName) {
		this.perticularName = perticularName;
	}

	public Double getProvisionalYear() {
		return provisionalYear;
	}

	public void setProvisionalYear(Double provisionalYear) {
		this.provisionalYear = provisionalYear;
	}

	public Double getLastYear() {
		return lastYear;
	}

	public void setLastYear(Double lastYear) {
		this.lastYear = lastYear;
	}

	public Double getLastToLastYear() {
		return lastToLastYear;
	}

	public void setLastToLastYear(Double lastToLastYear) {
		this.lastToLastYear = lastToLastYear;
	}

	public Double getDiffPfPrvsnlAndLastYear() {
		return diffPfPrvsnlAndLastYear;
	}

	public void setDiffPfPrvsnlAndLastYear(Double diffPfPrvsnlAndLastYear) {
		this.diffPfPrvsnlAndLastYear = diffPfPrvsnlAndLastYear;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	

	public Integer getPerticularId() {
		return perticularId;
	}

	public void setPerticularId(Integer perticularId) {
		this.perticularId = perticularId;
	}

	@Override
	public String toString() {
		return "DDRFinancialSummaryRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", perticularId=" + perticularId + ", perticularName="
				+ perticularName + ", provisionalYear=" + provisionalYear + ", lastYear=" + lastYear
				+ ", lastToLastYear=" + lastToLastYear + ", diffPfPrvsnlAndLastYear=" + diffPfPrvsnlAndLastYear
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + ", isActive=" + isActive + "]";
	}

	
	
	
	

}
