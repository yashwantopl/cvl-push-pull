/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PreferredAuditorHistories {

	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("financial-year")
	private String financialYear;

	@JsonProperty("whether-financial-statement-qualified")
	private String whetherFinancialStatementQualified;

	@JsonProperty("whether-caro-applicable")
	private String whetherCaroApplicable;

	@JsonProperty("auditor-details")
	private AuditorDetails[] auditorsDetails;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
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
	 * @param financialYear
	 *            the financialYear to set
	 */
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	/**
	 * @return the whetherFinancialStatementQualified
	 */
	public String getWhetherFinancialStatementQualified() {
		return whetherFinancialStatementQualified;
	}

	/**
	 * @param whetherFinancialStatementQualified
	 *            the whetherFinancialStatementQualified to set
	 */
	public void setWhetherFinancialStatementQualified(String whetherFinancialStatementQualified) {
		this.whetherFinancialStatementQualified = whetherFinancialStatementQualified;
	}

	/**
	 * @return the whetherCaroApplicable
	 */
	public String getWhetherCaroApplicable() {
		return whetherCaroApplicable;
	}

	/**
	 * @param whetherCaroApplicable
	 *            the whetherCaroApplicable to set
	 */
	public void setWhetherCaroApplicable(String whetherCaroApplicable) {
		this.whetherCaroApplicable = whetherCaroApplicable;
	}

	/**
	 * @return the auditorsDetails
	 */
	public AuditorDetails[] getAuditorsDetails() {
		return auditorsDetails;
	}

	/**
	 * @param auditorsDetails
	 *            the auditorsDetails to set
	 */
	public void setAuditorsDetails(AuditorDetails[] auditorsDetails) {
		this.auditorsDetails = auditorsDetails;
	}

}
