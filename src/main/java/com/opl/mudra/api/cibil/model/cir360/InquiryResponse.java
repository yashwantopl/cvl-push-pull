package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"InquiryResponseHeader",
	"InquiryRequestInfo","CommercialRequestInfo",
	"Score","CommercialBureauResponse","CIRReportData",
	"PIIVerificationResponse","Error"})
public class InquiryResponse {

	@JsonProperty("InquiryResponseHeader")
	private InquiryResponseHeader inquiryResponseHeader;

	@JsonProperty("InquiryRequestInfo")
	private RequestBody inquiryRequestInfo;

	@JsonProperty("CommercialRequestInfo")
	private RequestBodyCommercial commercialRequestInfo;

	@JsonProperty("CommercialBureauResponse")
	private CommercialBureauResponse commercialBureauResponse;

	@JsonProperty("InquiryRequestInfoStr~")
	private String inquiryRequestInfoStr;

	@JsonProperty("CIRReportData")
	private CIRReportData cirReportData;

	@JsonProperty("PIIVerificationResponse")
	private PIIVerificationResponse piiVerificationResponse;

	@JsonProperty("Error")
	private ErrorDetails error;

	@JsonProperty("CCRResponse")
	private CCRResponse cCRResponse;
	
	@JsonProperty("Score")
	private List<ScoreType> score;	

	public List<ScoreType> getScore() {
		return score;
	}

	public void setScore(List<ScoreType> score) {
		this.score = score;
	}

	public CCRResponse getcCRResponse() {
		return cCRResponse;
	}

	public void setcCRResponse(CCRResponse cCRResponse) {
		this.cCRResponse = cCRResponse;
	}

	public CommercialBureauResponse getCommercialBureauResponse() {
		return commercialBureauResponse;
	}

	public void setCommercialBureauResponse(CommercialBureauResponse commercialBureauResponse) {
		this.commercialBureauResponse = commercialBureauResponse;
	}

	public RequestBodyCommercial getCommercialRequestInfo() {
		return commercialRequestInfo;
	}

	public void setCommercialRequestInfo(RequestBodyCommercial commercialRequestInfo) {
		this.commercialRequestInfo = commercialRequestInfo;
	}

	public InquiryResponseHeader getInquiryResponseHeader() {
		return inquiryResponseHeader;
	}

	public void setInquiryResponseHeader(InquiryResponseHeader inquiryResponseHeader) {
		this.inquiryResponseHeader = inquiryResponseHeader;
	}

	public RequestBody getInquiryRequestInfo() {
		return inquiryRequestInfo;
	}

	public void setInquiryRequestInfo(RequestBody inquiryRequestInfo) {
		this.inquiryRequestInfo = inquiryRequestInfo;
	}

	public CIRReportData getCirReportData() {
		return cirReportData;
	}

	public void setCirReportData(CIRReportData cirReportData) {
		this.cirReportData = cirReportData;
	}

	public PIIVerificationResponse getPiiVerificationResponse() {
		return piiVerificationResponse;
	}

	public void setPiiVerificationResponse(PIIVerificationResponse piiVerificationResponse) {
		this.piiVerificationResponse = piiVerificationResponse;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	public String getInquiryRequestInfoStr() {
		return inquiryRequestInfoStr;
	}

	public void setInquiryRequestInfoStr(String inquiryRequestInfoStr) {
		this.inquiryRequestInfoStr = inquiryRequestInfoStr;
	}

}
