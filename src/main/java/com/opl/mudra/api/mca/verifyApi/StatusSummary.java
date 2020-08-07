
package com.opl.mudra.api.mca.verifyApi;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "success-count",
    "failed-count",
    "total-count"
})
public class StatusSummary {

    @JsonProperty("success-count")
    private Integer successCount;
    @JsonProperty("failed-count")
    private Integer failedCount;
    @JsonProperty("total-count")
    private Integer totalCount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("success-count")
    public Integer getSuccessCount() {
        return successCount;
    }

    @JsonProperty("success-count")
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    @JsonProperty("failed-count")
    public Integer getFailedCount() {
        return failedCount;
    }

    @JsonProperty("failed-count")
    public void setFailedCount(Integer failedCount) {
        this.failedCount = failedCount;
    }

    @JsonProperty("total-count")
    public Integer getTotalCount() {
        return totalCount;
    }

    @JsonProperty("total-count")
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
