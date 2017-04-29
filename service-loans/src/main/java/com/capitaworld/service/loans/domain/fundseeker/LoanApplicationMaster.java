package com.capitaworld.service.loans.domain.fundseeker;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the fs_loan_application_master database table.
 * 
 */
@Entity
@Table(name = "fs_loan_application_master")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "LoanApplicationMaster.findAll", query = "SELECT f FROM LoanApplicationMaster f")
public class LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double amount;

	@Column(name = "category_code")
	private String categoryCode;

	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_date")
	private Date modifiedDate;

	private String name;

	@Column(name = "product_id")
	private Long productId;

	private Integer tenure;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "currency_id")
	private Integer currencyId;
	
	@Column(name = "denomination_id")
	private Integer denominationId;

	// bi-directional many-to-one association to ApplicationStatusMaster
	@ManyToOne
	@JoinColumn(name = "status")
	private ApplicationStatusMaster applicationStatusMaster;

	public LoanApplicationMaster() {
	}

	public LoanApplicationMaster(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCategoryCode() {
		return this.categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getTenure() {
		return tenure;
	}

	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ApplicationStatusMaster getApplicationStatusMaster() {
		return this.applicationStatusMaster;
	}

	public void setApplicationStatusMaster(ApplicationStatusMaster applicationStatusMaster) {
		this.applicationStatusMaster = applicationStatusMaster;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
	}
	
}