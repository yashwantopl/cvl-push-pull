package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_loan_application_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private String categoryCode;

	private String name;

	private Integer productId;

	private Double tenure;

	private Long userId;

	private Integer currencyId;

	private Integer denominationId;

	private Date createdDate;

	private boolean hasAlreadyApplied;

	private String loanTypeMain;

	private String loanTypeSub;

	public LoanApplicationRequest() {
	}

	public LoanApplicationRequest(Long id) {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getTenure() {
		return this.tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isHasAlreadyApplied() {
		return hasAlreadyApplied;
	}

	public void setHasAlreadyApplied(boolean hasAlreadyApplied) {
		this.hasAlreadyApplied = hasAlreadyApplied;
	}

	public String getLoanTypeMain() {
		return loanTypeMain;
	}

	public void setLoanTypeMain(String loanTypeMain) {
		this.loanTypeMain = loanTypeMain;
	}

	public String getLoanTypeSub() {
		return loanTypeSub;
	}

	public void setLoanTypeSub(String loanTypeSub) {
		this.loanTypeSub = loanTypeSub;
	}

}