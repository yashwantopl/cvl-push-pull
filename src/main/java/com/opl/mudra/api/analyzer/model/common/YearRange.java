package com.opl.mudra.api.analyzer.model.common;

public class YearRange {

	private String fromYear;
	private String toYear;
	private String fromMonth;
	private String toMonth;

	public YearRange(String toYear, String fromYear, String fromMonth, String toMonth) {
		
		
		this.fromYear = fromYear;
		this.toYear = toYear;
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
	}

	public YearRange() {
		
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	public String getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}

	public String getToMonth() {
		return toMonth;
	}

	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}

}
