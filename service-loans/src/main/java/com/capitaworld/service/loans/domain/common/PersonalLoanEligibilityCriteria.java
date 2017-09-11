package com.capitaworld.service.loans.domain.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personal_loan_criteria")
public class PersonalLoanEligibilityCriteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "type")
	private Integer type;

	@Column(name = "bank_id")
	private Integer bankId;

	private Long min;
	private Long max;

	@Column(name = "foir")
	private Float foir;

	@Column(name = "roi_low")
	private Float roiLow;

	@Column(name = "roi_high")
	private Float roiHigh;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updateDate;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updateBy;

	public PersonalLoanEligibilityCriteria() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Float getFoir() {
		return foir;
	}

	public void setFoir(Float foir) {
		this.foir = foir;
	}

	public Float getRoiLow() {
		return roiLow;
	}

	public void setRoiLow(Float roiLow) {
		this.roiLow = roiLow;
	}

	public Float getRoiHigh() {
		return roiHigh;
	}

	public void setRoiHigh(Float roiHigh) {
		this.roiHigh = roiHigh;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public String toString() {
		return "PersonalLoanEligibilityCriteria [id=" + id + ", type=" + type + ", bankId=" + bankId + ", min=" + min
				+ ", max=" + max + ", foir=" + foir + ", roiLow=" + roiLow + ", roiHigh=" + roiHigh + ", getId()="
				+ getId() + ", getType()=" + getType() + ", getBankId()=" + getBankId() + ", getMin()=" + getMin()
				+ ", getMax()=" + getMax() + ", getFoir()=" + getFoir() + ", getRoiLow()=" + getRoiLow()
				+ ", getRoiHigh()=" + getRoiHigh() + ", getIsActive()=" + getIsActive() + ", getCreatedDate()="
				+ getCreatedDate() + ", getUpdateDate()=" + getUpdateDate() + ", getCreatedBy()=" + getCreatedBy()
				+ ", getUpdateBy()=" + getUpdateBy() + "]";
	}

	
}
