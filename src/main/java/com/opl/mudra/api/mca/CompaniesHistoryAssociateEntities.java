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
public class CompaniesHistoryAssociateEntities {
	
	  @JsonProperty("as-on-date")
	    private String asOnDate;
	    @JsonProperty("financial-year")
	    private String financialYear;
	    @JsonProperty("holdings")
	    private List<Object> holdings = null;
	    @JsonProperty("subsidiaries")
	    private List<Object> subsidiaries = null;
	    @JsonProperty("joint-ventures")
	    private List<Object> jointVentures = null;
	    @JsonProperty("associates")
	    private List<Object> associates = null;
	    @JsonProperty("others")
	    private List<ComponiesHistoryOthers> companyHistoryAssociateEntitiesOthers = null;
	    @JsonIgnore
	    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
		public String getAsOnDate() {
			return asOnDate;
		}
		public String getFinancialYear() {
			return financialYear;
		}
		public List<Object> getHoldings() {
			return holdings;
		}
		public List<Object> getSubsidiaries() {
			return subsidiaries;
		}
		public List<Object> getJointVentures() {
			return jointVentures;
		}
		public List<Object> getAssociates() {
			return associates;
		}
		public List<ComponiesHistoryOthers> getOthers() {
			return companyHistoryAssociateEntitiesOthers;
		}
		public Map<String, Object> getAdditionalProperties() {
			return additionalProperties;
		}
		public void setAsOnDate(String asOnDate) {
			this.asOnDate = asOnDate;
		}
		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
		public void setHoldings(List<Object> holdings) {
			this.holdings = holdings;
		}
		public void setSubsidiaries(List<Object> subsidiaries) {
			this.subsidiaries = subsidiaries;
		}
		public void setJointVentures(List<Object> jointVentures) {
			this.jointVentures = jointVentures;
		}
		public void setAssociates(List<Object> associates) {
			this.associates = associates;
		}
		public void setOthers(List<ComponiesHistoryOthers> others) {
			this.companyHistoryAssociateEntitiesOthers = others;
		}
		public void setAdditionalProperties(Map<String, Object> additionalProperties) {
			this.additionalProperties = additionalProperties;
		}

	    public ComponiesHistoryOthers addComponiesHistoryOthers(ComponiesHistoryOthers other){
	    	 getOthers().add(other);
	    	 return other;
	    }
	    

}
