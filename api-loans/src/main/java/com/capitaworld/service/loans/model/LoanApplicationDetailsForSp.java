package com.capitaworld.service.loans.model;

import java.util.List;

import com.capitaworld.service.loans.model.common.RecentProfileViewResponse;

public class LoanApplicationDetailsForSp {

	private Long id;
	private Integer applicationType;
	private Integer productId;
	private String productName;
	private Double amount;
	private Integer currencyId;
	private String currencyValue;
	private Integer denominationId;
	private String denominationValue;
	
	private boolean hasAlreadyApplied;
	
	private Integer notificationCount;
	
	private List<SpSysNotifyResponse> sysNotifyResponse;
		
	public List<SpSysNotifyResponse> getSysNotifyResponse() {
		return sysNotifyResponse;
	}

	public void setSysNotifyResponse(List<SpSysNotifyResponse> sysNotifyResponse) {
		this.sysNotifyResponse = sysNotifyResponse;
	}
	
	public SpSysNotifyResponse addCompanyHistoryAssociateEntitiesOther(SpSysNotifyResponse sysNotifyResponse) {
		getSysNotifyResponse().add(sysNotifyResponse);
		return sysNotifyResponse;
	}

	
	public Integer getNotificationCount() {
		return notificationCount;
	}

	public void setNotificationCount(Integer notificationCount) {
		this.notificationCount = notificationCount;
	}

	private List<RecentProfileViewResponse> recentProfileViewList;
	
	public List<RecentProfileViewResponse> getRecentProfileViewList() {
		return recentProfileViewList;
	}

	public void setRecentProfileViewList(List<RecentProfileViewResponse> recentProfileViewList) {
		this.recentProfileViewList = recentProfileViewList;
	}

	public Integer getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}

	public LoanApplicationDetailsForSp() {
		super();
	}

	public LoanApplicationDetailsForSp(Long id, Integer productId, Double amount, Integer currencyId,Integer denominationId) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.currencyId = currencyId;
		this.denominationId = denominationId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDenominationValue() {
		return denominationValue;
	}

	public void setDenominationValue(String denominationValue) {
		this.denominationValue = denominationValue;
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

	public boolean isHasAlreadyApplied() {
		return hasAlreadyApplied;
	}

	public void setHasAlreadyApplied(boolean hasAlreadyApplied) {
		this.hasAlreadyApplied = hasAlreadyApplied;
	}
	
	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}
	
}
