package com.capitaworld.service.loans.model;

public class LoanApplicationDetailsForSp {

	private Long id;
	
	private Long productId;
	
	private Double amount;
	
	private Integer denominationId;
	
	

	public LoanApplicationDetailsForSp(Long id, Long productId, Double amount, Integer denominationId) {
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
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
