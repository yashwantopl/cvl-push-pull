package com.opl.mudra.api.mca;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryProductServiceOriginal {
	
	   @JsonProperty("description-of-highest-turnover-contributing-product-or-services")
	    private String descriptionOfHighestTurnoverContributingProductOrServices;
	    @JsonProperty("as-on-date")
	    private String asOnDate;
	    @JsonProperty("financial-year")
	    private String financialYear;
	    @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
		public String getDescriptionOfHighestTurnoverContributingProductOrServices() {
			return descriptionOfHighestTurnoverContributingProductOrServices;
		}
		public String getAsOnDate() {
			return asOnDate;
		}
		public String getFinancialYear() {
			return financialYear;
		}
		public Map<String, Object> getAdditionalProperties() {
			return additionalProperties;
		}
		public void setDescriptionOfHighestTurnoverContributingProductOrServices(
				String descriptionOfHighestTurnoverContributingProductOrServices) {
			this.descriptionOfHighestTurnoverContributingProductOrServices = descriptionOfHighestTurnoverContributingProductOrServices;
		}
		public void setAsOnDate(String asOnDate) {
			this.asOnDate = asOnDate;
		}
		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
		public void setAdditionalProperties(Map<String, Object> additionalProperties) {
			this.additionalProperties = additionalProperties;
		}


	    
}
