package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_future_financial_estimates_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_future_financial_estimates_details")
public class FutureFinancialEstimatesDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="current_assets")
	private Double currentAssets;

	@Column(name="current_liabilities")
	private Double currentLiabilities;

	private Double ebitda;

	@Column(name="financial_year")
	private String financialYear;

	@Column(name="fixed_assets")
	private Double fixedAssets;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="long_term_debt")
	private Double longTermDebt;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="net_worth")
	private Double netWorth;

	private Double pat;

	private Double sales;

	public FutureFinancialEstimatesDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getCurrentAssets() {
		return this.currentAssets;
	}

	public void setCurrentAssets(Double currentAssets) {
		this.currentAssets = currentAssets;
	}

	public Double getCurrentLiabilities() {
		return this.currentLiabilities;
	}

	public void setCurrentLiabilities(Double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public Double getEbitda() {
		return this.ebitda;
	}

	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}

	public String getFinancialYear() {
		return this.financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Double getFixedAssets() {
		return this.fixedAssets;
	}

	public void setFixedAssets(Double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLongTermDebt() {
		return this.longTermDebt;
	}

	public void setLongTermDebt(Double longTermDebt) {
		this.longTermDebt = longTermDebt;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getNetWorth() {
		return this.netWorth;
	}

	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}

	public Double getPat() {
		return this.pat;
	}

	public void setPat(Double pat) {
		this.pat = pat;
	}

	public Double getSales() {
		return this.sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	
}