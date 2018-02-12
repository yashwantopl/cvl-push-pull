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
	
	@Column(name = "perticular_id")
	private Integer perticularId;
	
	@Column(name = "perticular_name")
	private String perticularName;
	
	@Column(name = "provisional_year")
	private Double provisionalYear;
	
	@Column(name = "last_year")
	private Double lastYear;
	
	@Column(name = "last_to_last_year")
	private Double lastToLastYear;
	
	@Column(name = "diff_of_prvsnl_and_last_year")
	private Double diffPfPrvsnlAndLastYear;
	
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

	
	
	
	

}
