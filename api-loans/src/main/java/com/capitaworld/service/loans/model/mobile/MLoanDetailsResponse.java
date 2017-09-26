package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MLoanDetailsResponse  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String loan;
	private String loanType;
	private String applicationCode;
	private Double amount;
	private String currency;
	private Date createdDate;
	private Integer productId;
	
	public MLoanDetailsResponse() {
		super();
	}
	
	public MLoanDetailsResponse(Long id, String loan, String applicationCode, Date createdDate) {
		super();
		this.id = id;
		this.loan = loan;
		this.applicationCode = applicationCode;
		this.createdDate = createdDate;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
	
	
}
