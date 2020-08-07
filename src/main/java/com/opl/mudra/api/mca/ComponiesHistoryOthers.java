package com.opl.mudra.api.mca;

import java.util.HashMap;
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
public class ComponiesHistoryOthers {
	
	 @JsonProperty("company-id")
	    private String companyId;
	    @JsonProperty("company-name")
	    private String companyName;
	    @JsonProperty("cin-or-fcrn")
	    private String cinOrFcrn;
	    @JsonProperty("%-shares-held")
	    private String sharesHeld;
	    @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
		public String getCompanyId() {
			return companyId;
		}
		public String getCompanyName() {
			return companyName;
		}
		public String getCinOrFcrn() {
			return cinOrFcrn;
		}
		public String getSharesHeld() {
			return sharesHeld;
		}
		public Map<String, Object> getAdditionalProperties() {
			return additionalProperties;
		}
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public void setCinOrFcrn(String cinOrFcrn) {
			this.cinOrFcrn = cinOrFcrn;
		}
		public void setSharesHeld(String sharesHeld) {
			this.sharesHeld = sharesHeld;
		}
		public void setAdditionalProperties(Map<String, Object> additionalProperties) {
			this.additionalProperties = additionalProperties;
		}
	    
	    


}
