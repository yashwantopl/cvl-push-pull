package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestHeader {

	@JsonProperty("CustomerId")
	private String customerId;

	@JsonProperty("UserId")
	private String userId;

	@JsonProperty("Password")
	private String password;

	@JsonProperty("MemberNumber")
	private String memberNumber;

	@JsonProperty("SecurityCode")
	private String securityCode;

	@JsonProperty("ProductCode")
	private List<String> productCode;

	@JsonProperty("CustRefField")
	private String custRefField;

	@JsonProperty("ReportOrderNo")
	private String reportOrderNo;

	@JsonProperty("gds_tranid")
	private String gds_tranid;

	@JsonProperty("Env")
	private String env;

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getReportOrderNo() {
		return reportOrderNo;
	}

	public void setReportOrderNo(String reportOrderNo) {
		this.reportOrderNo = reportOrderNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public List<String> getProductCode() {
		return productCode;
	}

	public void setProductCode(List<String> productCode) {
		this.productCode = productCode;
	}

	public String getCustRefField() {
		return custRefField;
	}

	public void setCustRefField(String custRefField) {
		this.custRefField = custRefField;
	}

	public String getGds_tranid() {
		return gds_tranid;
	}

	public void setGds_tranid(String gds_tranid) {
		this.gds_tranid = gds_tranid;
	}

}
