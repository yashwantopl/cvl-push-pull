package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialBureauResponse {

	@JsonProperty("Status")
	private String status;

	@JsonProperty("hit_as_borrower")
	private String no_hit;

	@JsonProperty("hit_as_guarantor")
	private String no_hit_as_guarantor;

	@JsonProperty("Error")
	private ErrorDetails error;

	@JsonProperty("InquiryResponseHeader")
	private InquiryResponseHeader inquiryResponseHeader;

	@JsonProperty("InquiryRequestInfo")
	private RequestBody inquiryRequestInfo;

	@JsonProperty("InquiryRequestInfoCommercial")
	private RequestBodyCommercial inquiryRequestInfoCommercial;

	@JsonProperty("CommercialBureauResponseDetails")
	private CommercialBureauResponseDetails commercialBureauResponseDetails;

	@JsonProperty("CCRHitDetailsLst")
	private List<CCRHitDetails> cCRHitDetailsLst;

	public List<CCRHitDetails> getcCRHitDetailsLst() {
		return cCRHitDetailsLst;
	}

	public void setcCRHitDetailsLst(List<CCRHitDetails> cCRHitDetailsLst) {
		this.cCRHitDetailsLst = cCRHitDetailsLst;
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

	public CommercialBureauResponseDetails getCommercialBureauResponseDetails() {
		return commercialBureauResponseDetails;
	}

	public void setCommercialBureauResponseDetails(CommercialBureauResponseDetails commercialBureauResponseDetails) {
		this.commercialBureauResponseDetails = commercialBureauResponseDetails;
	}

	public String getNo_hit() {
		return no_hit;
	}

	public void setNo_hit(String no_hit) {
		this.no_hit = no_hit;
	}

	public String getNo_hit_as_guarantor() {
		return no_hit_as_guarantor;
	}

	public void setNo_hit_as_guarantor(String no_hit_as_guarantor) {
		this.no_hit_as_guarantor = no_hit_as_guarantor;
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

	public RequestBodyCommercial getInquiryRequestInfoCommercial() {
		return inquiryRequestInfoCommercial;
	}

	public void setInquiryRequestInfoCommercial(RequestBodyCommercial inquiryRequestInfoCommercial) {
		this.inquiryRequestInfoCommercial = inquiryRequestInfoCommercial;
	}

}
