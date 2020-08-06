
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Response"})
public class ConsumerReportServerErrorResponse implements Serializable {

	
	@JsonProperty("Response")
	private List<ErrorRequest> response;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -938166661831588172L;

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public List<ErrorRequest> getResponse() {
		return response;
	}

	public void setResponse(List<ErrorRequest> response) {
		this.response = response;
	}

}