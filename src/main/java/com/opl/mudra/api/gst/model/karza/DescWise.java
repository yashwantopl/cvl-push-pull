package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DescWise {

	private String desc;
	
	@JsonProperty("transactions")
	private HsnWiseTransactions transactions;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public HsnWiseTransactions getTransactions() {
		return transactions;
	}

	public void setTransactions(HsnWiseTransactions transactions) {
		this.transactions = transactions;
	}
	
	
}
