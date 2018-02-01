package com.capitaworld.service.loans.domain.fundseeker.ddr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by : harshit
 * The persistent class for the fs_ddr_financial_summary database table.
 * 
 */
@Entity
@Table(name="fs_ddr_financial_summary")
@NamedQuery(name="DDRFinancialSummary.findAll", query="SELECT a FROM DDRFinancialSummary a")
public class DDRFinancialSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fs_ddr_form_id")
	private Long ddrFormId;
	
	@Column(name = "perc_of_sales_of_anchor_pro")
	private String percOfSalesOfAnchorPro;
	
	@Column(name = "sales_of_anchor_pro")
	private String salesOfAnchorPro;
	
	@Column(name = "financial_year")
	private String financialYear;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name = "modify_by")
	private Long modifyBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	
	@Column(name = "is_active")
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
		return "DDRFinancialSummary [id=" + id + ", ddrFormId=" + ddrFormId + ", percOfSalesOfAnchorPro="
				+ percOfSalesOfAnchorPro + ", salesOfAnchorPro=" + salesOfAnchorPro + ", financialYear=" + financialYear
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifyBy=" + modifyBy
				+ ", modifyDate=" + modifyDate + ", isActive=" + isActive + "]";
	}
	
	
	

}
