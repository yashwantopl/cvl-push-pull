package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InquiryResponseHeader {

	@JsonProperty("CustomerCode")
	private String customerCode;

	@JsonProperty("ClientID")
	private String clientID;

	@JsonProperty("CustRefField")
	private String custRefField;

	@JsonProperty("ReportOrderNO")
	private String reportOrderNO;

	@JsonProperty("TranID")
	private String ccRReportOrderNO;

	@JsonProperty("ProductCode")
	private List<String> productCode;

	@JsonProperty("SuccessCode")
	private String successCode;

	@JsonProperty("Date")
	private String date;

	@JsonProperty("Time")
	private String time;

	@JsonProperty("HitCode")
	private String hitCode;

	@JsonProperty("BureauResponseId")
	private String bureauResponseId;

	@JsonProperty("CustomerName")
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBureauResponseId() {
		return bureauResponseId;
	}

	public void setBureauResponseId(String bureauResponseId) {
		this.bureauResponseId = bureauResponseId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getCustRefField() {
		return custRefField;
	}

	public void setCustRefField(String custRefField) {
		this.custRefField = custRefField;
	}

	public String getReportOrderNO() {
		return reportOrderNO;
	}

	public void setReportOrderNO(String reportOrderNO) {
		this.reportOrderNO = reportOrderNO;
	}

	public String getCcRReportOrderNO() {
		return ccRReportOrderNO;
	}

	public void setCcRReportOrderNO(String ccRReportOrderNO) {
		this.ccRReportOrderNO = ccRReportOrderNO;
	}

	public List<String> getProductCode() {
		return productCode;
	}

	public void setProductCode(List<String> productCode) {
		this.productCode = productCode;
	}

	public String getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHitCode() {
		return hitCode;
	}

	public void setHitCode(String hitCode) {
		this.hitCode = hitCode;
	}

}
