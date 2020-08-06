package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InquiryRequest {

	@JsonProperty("RequestHeader")
	private RequestHeader requestHeader;

	@JsonProperty("RequestBody")
	private RequestBody requestBody;

	@JsonProperty("RequestBodyCommercial")
	private RequestBodyCommercial requestBodyCommercial;

	@JsonProperty("RequestBodyStr")
	private String requestBodyStr;

	@JsonProperty("Score")
	private List<ScoreType> score;

	@JsonProperty("InternalConfig")
	private InternalConfig internalConfig;
	

	public InternalConfig getInternalConfig() {
		return internalConfig;
	}

	public void setInternalConfig(InternalConfig internalConfig) {
		this.internalConfig = internalConfig;
	}

	public List<ScoreType> getScore() {
		return score;
	}

	public void setScore(List<ScoreType> score) {
		this.score = score;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RequestBody getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(RequestBody requestBody) {
		this.requestBody = requestBody;
	}

	public String getRequestBodyStr() {
		return requestBodyStr;
	}

	public void setRequestBodyStr(String requestBodyStr) {
		this.requestBodyStr = requestBodyStr;
	}

	public RequestBodyCommercial getRequestBodyCommercial() {
		return requestBodyCommercial;
	}

	public void setRequestBodyCommercial(RequestBodyCommercial requestBodyCommercial) {
		this.requestBodyCommercial = requestBodyCommercial;
	}

}
