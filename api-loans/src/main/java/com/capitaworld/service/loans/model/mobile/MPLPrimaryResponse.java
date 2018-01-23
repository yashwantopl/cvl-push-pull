package com.capitaworld.service.loans.model.mobile;

public class MPLPrimaryResponse{
	private static final long serialVersionUID = 1L;
	
	private Long loanPurpose;
	
	private Double amount;
	
	private Double tenure;

	public Long getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Long loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}
	
	
}
