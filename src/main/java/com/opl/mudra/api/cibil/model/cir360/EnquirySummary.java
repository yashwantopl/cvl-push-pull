package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnquirySummary {
	@JsonProperty("Purpose")
	private String purpose;

	@JsonProperty("Total")
	private String total;

	@JsonProperty("Past30Days")
	private String past30Days;

	@JsonProperty("Past12Months")
	private String past12Months;

	@JsonProperty("Past24Months")
	private String past24Months;

	@JsonProperty("Recent")
	private String recent;

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPast30Days() {
		return past30Days;
	}

	public void setPast30Days(String past30Days) {
		this.past30Days = past30Days;
	}

	public String getPast12Months() {
		return past12Months;
	}

	public void setPast12Months(String past12Months) {
		this.past12Months = past12Months;
	}

	public String getPast24Months() {
		return past24Months;
	}

	public void setPast24Months(String past24Months) {
		this.past24Months = past24Months;
	}

	public String getRecent() {
		return recent;
	}

	public void setRecent(String recent) {
		this.recent = recent;
	}

}
