package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RetailAccountDetailsType extends AccountDetailsType {

	@JsonProperty("History48Months")
	private List<MonthlyDetailType> history48Months;

	public List<MonthlyDetailType> getHistory48Months() {
		return history48Months;
	}

	public void setHistory48Months(List<MonthlyDetailType> history48Months) {
		this.history48Months = history48Months;
	}
	
	

}
