
package com.opl.mudra.api.mca.verifyApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opl.mudra.api.mca.McaCommonUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "status",
    "message",
    "status-summary",
    "success-status-detail",
    "failed-status-detail"
})
public class VerifyAPIDIRPANResponse {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("status-summary")
    private StatusSummary statusSummary;
    @JsonProperty("success-status-detail")
    private List<SuccessStatusDetail> successStatusDetail = new ArrayList<SuccessStatusDetail>();
    @JsonProperty("failed-status-detail")
    private List<FailedStatusDetail> failedStatusDetail = new ArrayList<FailedStatusDetail>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("status-summary")
    public StatusSummary getStatusSummary() {
        return statusSummary;
    }

    @JsonProperty("status-summary")
    public void setStatusSummary(StatusSummary statusSummary) {
        this.statusSummary = statusSummary;
    }

    @JsonProperty("success-status-detail")
    public List<SuccessStatusDetail> getSuccessStatusDetail() {
        return successStatusDetail;
    }

    @JsonProperty("success-status-detail")
    public void setSuccessStatusDetail(List<SuccessStatusDetail> successStatusDetail) {
        this.successStatusDetail = successStatusDetail;
    }

    @JsonProperty("failed-status-detail")
    public List<FailedStatusDetail> getFailedStatusDetail() {
        return failedStatusDetail;
    }

    @JsonProperty("failed-status-detail")
    public void setFailedStatusDetail(List<FailedStatusDetail> failedStatusDetail) {
        this.failedStatusDetail = failedStatusDetail;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return McaCommonUtils.convertObjectToString(this);
	}

}
