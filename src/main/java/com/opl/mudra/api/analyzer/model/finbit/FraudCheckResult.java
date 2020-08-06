package com.opl.mudra.api.analyzer.model.finbit;

public class FraudCheckResult {

	private String message;
	private Boolean result;
	private String rule;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public FraudCheckResult(String message, Boolean result) {
		super();
		this.message = message;
		this.result = result;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
	
	
	
}
