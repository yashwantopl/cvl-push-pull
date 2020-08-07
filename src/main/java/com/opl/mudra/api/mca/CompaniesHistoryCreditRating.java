package com.opl.mudra.api.mca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryCreditRating {
	
	  @JsonProperty("as-on-date")
	    private String asOnDate;
	    @JsonProperty("details")
	    private List<Object> details = null;
	    @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
		public String getAsOnDate() {
			return asOnDate;
		}
		public List<Object> getDetails() {
			return details;
		}
		public Map<String, Object> getAdditionalProperties() {
			return additionalProperties;
		}
		public void setAsOnDate(String asOnDate) {
			this.asOnDate = asOnDate;
		}
		public void setDetails(List<Object> details) {
			this.details = details;
		}
		public void setAdditionalProperties(Map<String, Object> additionalProperties) {
			this.additionalProperties = additionalProperties;
		}
	    
	    
	    


}
