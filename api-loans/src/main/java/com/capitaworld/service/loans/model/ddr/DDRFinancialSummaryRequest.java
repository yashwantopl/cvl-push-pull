package com.capitaworld.service.loans.model.ddr;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFinancialSummaryRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long ddrFormId;
	
	private String percOfSalesOfAnchorPro;
	
	private String salesOfAnchorPro;
	
	private String financialYear;
	
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

	public String getPercOfSalesOfAnchorPro() {
		return percOfSalesOfAnchorPro;
	}

	public void setPercOfSalesOfAnchorPro(String percOfSalesOfAnchorPro) {
		this.percOfSalesOfAnchorPro = percOfSalesOfAnchorPro;
	}

	public String getSalesOfAnchorPro() {
		return salesOfAnchorPro;
	}

	public void setSalesOfAnchorPro(String salesOfAnchorPro) {
		this.salesOfAnchorPro = salesOfAnchorPro;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
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

	@Override
	public String toString() {
		return "DDRFinancialSummaryRequest [id=" + id + ", ddrFormId=" + ddrFormId + ", percOfSalesOfAnchorPro="
				+ percOfSalesOfAnchorPro + ", salesOfAnchorPro=" + salesOfAnchorPro + ", financialYear=" + financialYear
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + ", isActive=" + isActive + "]";
	}
	
	
	

}
