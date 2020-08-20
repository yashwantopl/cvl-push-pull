package com.opl.mudra.api.user.model;

public class StageEclgsReq {
	
	private Long id;
	private Integer stage;
	private Integer status;
	private Integer flowType;
	private String reason;
	private Boolean isGstSalesMatched;
	private Boolean isDpdMatched;
	private Boolean isOsAmtMatched;
	private Double osAmt;
	private Long dpds;
	private Double loanAmt;
	
	
	
	public StageEclgsReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StageEclgsReq(Long id, Integer stage, Integer status, String reason) {
		this.id = id;
		this.stage = stage;
		this.status = status;
		this.reason = reason;
	}
	public StageEclgsReq(Long id, Integer stage, Integer status) {
		this.id = id;
		this.stage = stage;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFlowType() {
		return flowType;
	}
	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Boolean getIsGstSalesMatched() {
		return isGstSalesMatched;
	}
	public void setIsGstSalesMatched(Boolean isGstSalesMatched) {
		this.isGstSalesMatched = isGstSalesMatched;
	}
	public Boolean getIsDpdMatched() {
		return isDpdMatched;
	}
	public void setIsDpdMatched(Boolean isDpdMatched) {
		this.isDpdMatched = isDpdMatched;
	}
	public Boolean getIsOsAmtMatched() {
		return isOsAmtMatched;
	}
	public void setIsOsAmtMatched(Boolean isOsAmtMatched) {
		this.isOsAmtMatched = isOsAmtMatched;
	}
	public Double getOsAmt() {
		return osAmt;
	}
	public void setOsAmt(Double osAmt) {
		this.osAmt = osAmt;
	}
	public Long getDpds() {
		return dpds;
	}
	public void setDpds(Long dpds) {
		this.dpds = dpds;
	}
	public Double getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(Double loanAmt) {
		this.loanAmt = loanAmt;
	}
	
}
