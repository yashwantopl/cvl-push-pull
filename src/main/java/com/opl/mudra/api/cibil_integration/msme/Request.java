
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "request" })
public class Request {

	/**
	 * 
	 */
	@JsonProperty("request")
	private Request_ request;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	@JsonProperty("request")
	public Request_ getRequest() {
		return request;
	}

	@JsonProperty("request")
	public void setRequest(Request_ request) {
		this.request = request;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	@Override
	public String toString() {
		return "Request [request=" + request + ", additionalProperties=" + additionalProperties + "]";
	}

}
