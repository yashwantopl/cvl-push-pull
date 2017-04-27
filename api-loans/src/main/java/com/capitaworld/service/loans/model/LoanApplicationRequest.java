package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * The persistent class for the fs_loan_application_master database table.
 * 
 */
public class LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private String categoryCode;

	private String name;

	private Long productId;

	private int tenure;

	private Long userId;

	private Integer currencyId;

	private Integer denominationId;

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

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getTenure() {
		return this.tenure;
	}

	public void setTenure(int tenure) {
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
}