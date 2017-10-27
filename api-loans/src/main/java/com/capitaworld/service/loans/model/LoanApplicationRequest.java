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
	private Long clientId;

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

	private String currencyValue;
	
	private Boolean isApplicantPrimaryFilled;
	
	private String applicationCode;
	
	private Integer status;
	
	private String primaryFilledCount;
	
	private Boolean profilePrimaryLocked;
	
	private Boolean finalLocked;
	
	private String mcaCompanyId;
	
	private Boolean isMca;
	
	
	
	

	public Boolean getIsMca() {
		return isMca;
	}

	public void setIsMca(Boolean isMca) {
		this.isMca = isMca;
	}

	public String getMcaCompanyId() {
		return mcaCompanyId;
	}

	public void setMcaCompanyId(String mcaCompanyId) {
		this.mcaCompanyId = mcaCompanyId;
	}

	public LoanApplicationRequest() {
	}

	public LoanApplicationRequest(Long id) {
		this.id = id;
	}

	public LoanApplicationRequest(Long id, Integer productId) {
		super();
		this.id = id;
		this.productId = productId;
	}
	
	public LoanApplicationRequest(Long id,Long userId) {
		super();
		this.id = id;
		this.userId = userId;
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Boolean getIsApplicantPrimaryFilled() {
		return isApplicantPrimaryFilled;
	}

	public void setIsApplicantPrimaryFilled(Boolean isApplicantPrimaryFilled) {
		this.isApplicantPrimaryFilled = isApplicantPrimaryFilled;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrimaryFilledCount() {
		return primaryFilledCount;
	}

	public void setPrimaryFilledCount(String primaryFilledCount) {
		this.primaryFilledCount = primaryFilledCount;
	}

	public Boolean getProfilePrimaryLocked() {
		return profilePrimaryLocked;
	}

	public void setProfilePrimaryLocked(Boolean profilePrimaryLocked) {
		this.profilePrimaryLocked = profilePrimaryLocked;
	}

	public Boolean getFinalLocked() {
		return finalLocked;
	}

	public void setFinalLocked(Boolean finalLocked) {
		this.finalLocked = finalLocked;
	}
}