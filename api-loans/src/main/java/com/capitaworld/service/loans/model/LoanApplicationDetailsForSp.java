package com.capitaworld.service.loans.model;

public class LoanApplicationDetailsForSp {

	private Long id;
	
	private Integer productId;
	
	private Double amount;
	
	private Integer denominationId;
	
	

	public LoanApplicationDetailsForSp() {
		super();
	}

	public LoanApplicationDetailsForSp(Long id, Integer productId, Double amount, Integer denominationId) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.denominationId = denominationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
	}
	
	
	
	
}
