/**
 * 
 */
package com.opl.mudra.api.mca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryAssociateEntitiesTwo {
	
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
	    
	    @JsonProperty("common-directors")
	    private ComponiesHistoryCommonDirectors[] componiesHistoryCommonDirectors = null;
	    
		/**
		 * @return the componiesHistoryCommonDirectors
		 */
		public ComponiesHistoryCommonDirectors[] getComponiesHistoryCommonDirectors() {
			return componiesHistoryCommonDirectors;
		}
		/**
		 * @param componiesHistoryCommonDirectors the componiesHistoryCommonDirectors to set
		 */
		public void setComponiesHistoryCommonDirectors(ComponiesHistoryCommonDirectors[] componiesHistoryCommonDirectors) {
			this.componiesHistoryCommonDirectors = componiesHistoryCommonDirectors;
		}
		/**
		 * @return the asOnDate
		 */
		public String getAsOnDate() {
			return asOnDate;
		}
		/**
		 * @param asOnDate the asOnDate to set
		 */
		public void setAsOnDate(String asOnDate) {
			this.asOnDate = asOnDate;
		}
		/**
		 * @return the financialYear
		 */
		public String getFinancialYear() {
			return financialYear;
		}
		/**
		 * @param financialYear the financialYear to set
		 */
		public void setFinancialYear(String financialYear) {
			this.financialYear = financialYear;
		}
		/**
		 * @return the holdings
		 */
		public List<Object> getHoldings() {
			return holdings;
		}
		/**
		 * @param holdings the holdings to set
		 */
		public void setHoldings(List<Object> holdings) {
			this.holdings = holdings;
		}
		/**
		 * @return the subsidiaries
		 */
		public List<Object> getSubsidiaries() {
			return subsidiaries;
		}
		/**
		 * @param subsidiaries the subsidiaries to set
		 */
		public void setSubsidiaries(List<Object> subsidiaries) {
			this.subsidiaries = subsidiaries;
		}
		/**
		 * @return the jointVentures
		 */
		public List<Object> getJointVentures() {
			return jointVentures;
		}
		/**
		 * @param jointVentures the jointVentures to set
		 */
		public void setJointVentures(List<Object> jointVentures) {
			this.jointVentures = jointVentures;
		}
		/**
		 * @return the associates
		 */
		public List<Object> getAssociates() {
			return associates;
		}
		/**
		 * @param associates the associates to set
		 */
		public void setAssociates(List<Object> associates) {
			this.associates = associates;
		}
		/**
		 * @return the companyHistoryAssociateEntitiesOthers
		 */
		public List<ComponiesHistoryOthers> getCompanyHistoryAssociateEntitiesOthers() {
			return companyHistoryAssociateEntitiesOthers;
		}
		/**
		 * @param companyHistoryAssociateEntitiesOthers the companyHistoryAssociateEntitiesOthers to set
		 */
		public void setCompanyHistoryAssociateEntitiesOthers(
				List<ComponiesHistoryOthers> companyHistoryAssociateEntitiesOthers) {
			this.companyHistoryAssociateEntitiesOthers = companyHistoryAssociateEntitiesOthers;
		}
	    
	    
	    

}
