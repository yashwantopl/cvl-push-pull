package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CCRResponse {

	@JsonProperty("Status")
	private String status;

	@JsonProperty("Error")
	private ErrorDetails error;

	@JsonProperty("CommercialBureauResponse")
	private CommercialBureauResponse commercialBureauResponse;

	@JsonProperty("CIRReportDataLst")
	private List<InquiryResponse> inquiryResponseLst;

	public List<InquiryResponse> getInquiryResponseLst() {
		return inquiryResponseLst;
	}

	public void setInquiryResponseLst(List<InquiryResponse> inquiryResponseLst) {
		this.inquiryResponseLst = inquiryResponseLst;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	public CommercialBureauResponse getCommercialBureauResponse() {
		return commercialBureauResponse;
	}

	public void setCommercialBureauResponse(CommercialBureauResponse commercialBureauResponse) {
		this.commercialBureauResponse = commercialBureauResponse;
	}

}
