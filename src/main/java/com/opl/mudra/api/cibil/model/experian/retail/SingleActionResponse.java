package com.opl.mudra.api.cibil.model.experian.retail;

public class SingleActionResponse {

	private String errorString;
	private String showHtmlReportForCreditReport;
	private String subscriptionMsg;

	public SingleActionResponse() {
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public String getShowHtmlReportForCreditReport() {
		return showHtmlReportForCreditReport;
	}

	public void setShowHtmlReportForCreditReport(String showHtmlReportForCreditReport) {
		this.showHtmlReportForCreditReport = showHtmlReportForCreditReport;
	}

	public String getSubscriptionMsg() {
		return subscriptionMsg;
	}

	public void setSubscriptionMsg(String subscriptionMsg) {
		this.subscriptionMsg = subscriptionMsg;
	}

}
