/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.itr.model;

/**
 * @author harshit
 *
 */
public class ITRProperties {

	private String organizationName;
	private String startTransactionAPIUrl;
	private String uploadStatementAPIUrl;
	private String processTransactionAPIUrl;
	private String transactionStatusAPIUrl;
	private String retrieveReportAPIUrl;
	
	
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getStartTransactionAPIUrl() {
		return startTransactionAPIUrl;
	}
	public void setStartTransactionAPIUrl(String startTransactionAPIUrl) {
		this.startTransactionAPIUrl = startTransactionAPIUrl;
	}
	public String getUploadStatementAPIUrl() {
		return uploadStatementAPIUrl;
	}
	public void setUploadStatementAPIUrl(String uploadStatementAPIUrl) {
		this.uploadStatementAPIUrl = uploadStatementAPIUrl;
	}
	public String getProcessTransactionAPIUrl() {
		return processTransactionAPIUrl;
	}
	public void setProcessTransactionAPIUrl(String processTransactionAPIUrl) {
		this.processTransactionAPIUrl = processTransactionAPIUrl;
	}
	public String getTransactionStatusAPIUrl() {
		return transactionStatusAPIUrl;
	}
	public void setTransactionStatusAPIUrl(String transactionStatusAPIUrl) {
		this.transactionStatusAPIUrl = transactionStatusAPIUrl;
	}
	public String getRetrieveReportAPIUrl() {
		return retrieveReportAPIUrl;
	}
	public void setRetrieveReportAPIUrl(String retrieveReportAPIUrl) {
		this.retrieveReportAPIUrl = retrieveReportAPIUrl;
	}
	
	
}
