package com.capitaworld.service.loans.model.common;


public class LogDetailsModel {
	private Integer dateTypeMasterId;

	private Long loanApplicationMasterId;

	private Long productMappingId;

	public Integer getDateTypeMasterId() {
		return dateTypeMasterId;
	}

	public void setDateTypeMasterId(Integer dateTypeMasterId) {
		this.dateTypeMasterId = dateTypeMasterId;
	}

	public Long getLoanApplicationMasterId() {
		return loanApplicationMasterId;
	}

	public void setLoanApplicationMasterId(Long loanApplicationMasterId) {
		this.loanApplicationMasterId = loanApplicationMasterId;
	}

	public Long getProductMappingId() {
		return productMappingId;
	}

	public void setProductMappingId(Long productMappingId) {
		this.productMappingId = productMappingId;
	}
	
	
}
