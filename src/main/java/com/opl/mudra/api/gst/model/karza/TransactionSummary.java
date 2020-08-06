package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TransactionSummary {
	
	@JsonProperty("cur_gt")
	private Double curGt;
	
	private Double gt;
	
	private Double payable;
	
	@JsonProperty("period_from")
	private Double periodFrom;
	
	@JsonProperty("period_to")
	private Double periodTo;
	
	private Turnover turnover;

	private Payment payment;

	@JsonProperty("total_tax_liability")
	private TotalTaxLiability totalTaxLiability;

	public Double getCurGt() {
		return curGt;
	}

	public void setCurGt(Double curGt) {
		this.curGt = curGt;
	}

	public Double getGt() {
		return gt;
	}

	public void setGt(Double gt) {
		this.gt = gt;
	}

	public Double getPayable() {
		return payable;
	}

	public void setPayable(Double payable) {
		this.payable = payable;
	}

	public Double getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Double periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Double getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Double periodTo) {
		this.periodTo = periodTo;
	}

	public Turnover getTurnover() {
		return turnover;
	}

	public void setTurnover(Turnover turnover) {
		this.turnover = turnover;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public TotalTaxLiability getTotalTaxLiability() {
		return totalTaxLiability;
	}

	public void setTotalTaxLiability(TotalTaxLiability totalTaxLiability) {
		this.totalTaxLiability = totalTaxLiability;
	}
	
	
	
}
