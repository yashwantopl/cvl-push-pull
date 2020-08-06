package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RulesDecision {

	@JsonProperty("RuleID")
	private String ruleID;

	@JsonProperty("RuleDesc")
	private String ruleDesc;

	@JsonProperty("Decision")
	private String decision;

	@JsonProperty("Value")
	private DecisionValueType decisionValue;

	@JsonProperty("Error")
	private ErrorDetails error;

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getRuleID() {
		return ruleID;
	}

	public void setRuleID(String ruleID) {
		this.ruleID = ruleID;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public DecisionValueType getDecisionValue() {
		return decisionValue;
	}

	public void setDecisionValue(DecisionValueType decisionValue) {
		this.decisionValue = decisionValue;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

}
