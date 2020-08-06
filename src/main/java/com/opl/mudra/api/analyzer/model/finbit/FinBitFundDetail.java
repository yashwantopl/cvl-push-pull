package com.opl.mudra.api.analyzer.model.finbit;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FinBitFundDetail {

	 @JsonProperty("amount")
	    private Double amount;
	    @JsonProperty("description")
	    private String description;
	    @JsonProperty("no_of_transactions")
	    private Double noOfTransactions;
	    @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	    @JsonProperty("amount")
	    public Double getAmount() {
	        return amount;
	    }

	    @JsonProperty("amount")
	    public void setAmount(Double amount) {
	        this.amount = amount;
	    }

	    @JsonProperty("description")
	    public String getDescription() {
	        return description;
	    }

	    @JsonProperty("description")
	    public void setDescription(String description) {
	        this.description = description;
	    }

	    @JsonProperty("no_of_transactions")
	    public Double getNoOfTransactions() {
	        return noOfTransactions;
	    }

	    @JsonProperty("no_of_transactions")
	    public void setNoOfTransactions(Double noOfTransactions) {
	        this.noOfTransactions = noOfTransactions;
	    }

	    @JsonAnyGetter
	    public Map<String, Object> getAdditionalProperties() {
	        return this.additionalProperties;
	    }

	    @JsonAnySetter
	    public void setAdditionalProperty(String name, Object value) {
	        this.additionalProperties.put(name, value);
	    }
}
