package com.opl.mudra.api.analyzer.model.finbit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	@JsonProperty("category")
	private String category;
	@JsonProperty("rule")
	private String rule;
	@JsonProperty("ruleId")
	private String ruleId;
	@JsonProperty("ruleName")
	private String ruleName;
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("rule")
    public String getRule() {
        return rule;
    }

    @JsonProperty("rule")
    public void setRule(String rule) {
        this.rule = rule;
    }

    @JsonProperty("ruleId")
    public String getRuleId() {
        return ruleId;
    }

    @JsonProperty("ruleId")
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @JsonProperty("ruleName")
    public String getRuleName() {
        return ruleName;
    }

    @JsonProperty("ruleName")
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }
}
