package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SearchDirectorsSummary {

	@JsonProperty("total-count")
	private String totalCount;
	
	@JsonProperty("offset-start")
	private String offsetStart; 
	
	@JsonProperty("offset-end")
	private String offsetEnd;

	public String getTotalCount() {
		return totalCount;
	}

	public String getOffsetStart() {
		return offsetStart;
	}

	public String getOffsetEnd() {
		return offsetEnd;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public void setOffsetStart(String offsetStart) {
		this.offsetStart = offsetStart;
	}

	public void setOffsetEnd(String offsetEnd) {
		this.offsetEnd = offsetEnd;
	}
	
	
}
