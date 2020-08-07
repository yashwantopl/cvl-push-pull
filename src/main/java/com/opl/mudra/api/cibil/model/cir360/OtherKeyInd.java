package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OtherKeyInd {
	@JsonProperty("AgeOfOldestTrade")
	private String ageOfOldestTrade;
	
	@JsonProperty("NumberOfOpenTrades")
	private String numberOfOpenTrades;
	
	@JsonProperty("AllLinesEVERWritten")
	private String allLinesEVERWritten;
	
	@JsonProperty("AllLinesEVERWrittenIn9Months")
	private String allLinesEVERWrittenIn9Months;
	
	@JsonProperty("AllLinesEVERWrittenIn6Months")
	private String allLinesEVERWrittenIn6Months;

	public String getAgeOfOldestTrade() {
		return ageOfOldestTrade;
	}

	public void setAgeOfOldestTrade(String ageOfOldestTrade) {
		this.ageOfOldestTrade = ageOfOldestTrade;
	}

	public String getNumberOfOpenTrades() {
		return numberOfOpenTrades;
	}

	public void setNumberOfOpenTrades(String numberOfOpenTrades) {
		this.numberOfOpenTrades = numberOfOpenTrades;
	}

	public String getAllLinesEVERWritten() {
		return allLinesEVERWritten;
	}

	public void setAllLinesEVERWritten(String allLinesEVERWritten) {
		this.allLinesEVERWritten = allLinesEVERWritten;
	}

	public String getAllLinesEVERWrittenIn9Months() {
		return allLinesEVERWrittenIn9Months;
	}

	public void setAllLinesEVERWrittenIn9Months(String allLinesEVERWrittenIn9Months) {
		this.allLinesEVERWrittenIn9Months = allLinesEVERWrittenIn9Months;
	}

	public String getAllLinesEVERWrittenIn6Months() {
		return allLinesEVERWrittenIn6Months;
	}

	public void setAllLinesEVERWrittenIn6Months(String allLinesEVERWrittenIn6Months) {
		this.allLinesEVERWrittenIn6Months = allLinesEVERWrittenIn6Months;
	}
	
	

}
