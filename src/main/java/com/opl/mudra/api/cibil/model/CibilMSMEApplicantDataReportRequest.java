package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilMSMEApplicantDataReportRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private String formattedReport;
	private String formattedReportType;
	private String rawResponse;
	private String uploadtoSFTP;

	public String getFormattedReport() {
		return formattedReport;
	}

	public CibilMSMEApplicantDataReportRequest() {
		super();
	}

	public void setFormattedReport(String formattedReport) {
		this.formattedReport = formattedReport;
	}

	public String getFormattedReportType() {
		return formattedReportType;
	}

	public void setFormattedReportType(String formattedReportType) {
		this.formattedReportType = formattedReportType;
	}

	public String getRawResponse() {
		return rawResponse;
	}

	public void setRawResponse(String rawResponse) {
		this.rawResponse = rawResponse;
	}

	public String getUploadtoSFTP() {
		return uploadtoSFTP;
	}

	public void setUploadtoSFTP(String uploadtoSFTP) {
		this.uploadtoSFTP = uploadtoSFTP;
	}

	@Override
	public String toString() {
		return "CibilMSMEApplicantDataReportRequest [formattedReport=" + formattedReport + ", formattedReportType="
				+ formattedReportType + ", rawResponse=" + rawResponse + ", uploadtoSFTP=" + uploadtoSFTP + "]";
	}
}
