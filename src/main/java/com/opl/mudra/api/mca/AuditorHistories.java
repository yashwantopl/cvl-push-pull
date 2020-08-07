/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class AuditorHistories {
	
	@JsonProperty("as-on-date")
	private String asOnDate;
	
	@JsonProperty("file-id")
	private String fileId;
	
	@JsonProperty("financial-year")
	private String financialYear;
	
	@JsonProperty("whether-caro-applicable")
	private String whetherCaroApplicable;
	
	@JsonProperty("whether-financial-statement-qualified")
	private String whetherFinancialStatementQual;
	
	@JsonProperty("auditor-details")
	private AuditorDetails[] auditorsDetails;

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
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	 * @return the whetherCaroApplicable
	 */
	public String getWhetherCaroApplicable() {
		return whetherCaroApplicable;
	}

	/**
	 * @param whetherCaroApplicable the whetherCaroApplicable to set
	 */
	public void setWhetherCaroApplicable(String whetherCaroApplicable) {
		this.whetherCaroApplicable = whetherCaroApplicable;
	}

	/**
	 * @return the whetherFinancialStatementQual
	 */
	public String getWhetherFinancialStatementQual() {
		return whetherFinancialStatementQual;
	}

	/**
	 * @param whetherFinancialStatementQual the whetherFinancialStatementQual to set
	 */
	public void setWhetherFinancialStatementQual(String whetherFinancialStatementQual) {
		this.whetherFinancialStatementQual = whetherFinancialStatementQual;
	}

	/**
	 * @return the auditorsDetails
	 */
	public AuditorDetails[] getAuditorsDetails() {
		return auditorsDetails;
	}

	/**
	 * @param auditorsDetails the auditorsDetails to set
	 */
	public void setAuditorsDetails(AuditorDetails[] auditorsDetails) {
		this.auditorsDetails = auditorsDetails;
	}
	
	

}
