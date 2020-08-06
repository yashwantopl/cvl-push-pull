
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Reason"
})
public class ConsumerReportErrorResponse implements Serializable
{

    @JsonProperty("Reason")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    public List<Reason> reason = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2589943816762490175L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public List<Reason> getReason() {
		return reason;
	}

	public void setReason(List<Reason> reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ConsumerReportErrorResponse [reason=" + (reason != null ? reason.toString() : reason) + ", additionalProperties=" + additionalProperties + "]";
	}
}
