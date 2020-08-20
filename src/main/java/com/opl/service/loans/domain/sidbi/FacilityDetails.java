package com.opl.service.loans.domain.sidbi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Entity
@Table(name="fs_sidbi_facility_details")
public class FacilityDetails implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="rupee_term_loan")
    private Double rupeeTermLoan;

	@Column(name="foreign_currency")
    private Double foreignCurrency;

	@Column(name="inr_currency")
	private Double inrCurrency;

	@Column(name="currency_type")
	private String currencyType;

    @Column(name="working_capital_fund")
    private Double workingCapitalFund;

    @Column(name="working_capital_non_fund")
    private Double workingCapitalNonFund;

    @Column(name="total")
    private Double total;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Double getRupeeTermLoan() {
        return rupeeTermLoan;
    }

    public void setRupeeTermLoan(Double rupeeTermLoan) {
        this.rupeeTermLoan = rupeeTermLoan;
    }

    public Double getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(Double foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public Double getWorkingCapitalFund() {
        return workingCapitalFund;
    }

    public void setWorkingCapitalFund(Double workingCapitalFund) {
        this.workingCapitalFund = workingCapitalFund;
    }

    public Double getWorkingCapitalNonFund() {
        return workingCapitalNonFund;
    }

    public void setWorkingCapitalNonFund(Double workingCapitalNonFund) {
        this.workingCapitalNonFund = workingCapitalNonFund;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

	public Double getInrCurrency() {
		return inrCurrency;
	}

	public void setInrCurrency(Double inrCurrency) {
		this.inrCurrency = inrCurrency;
	}
    
}
