
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ReportingMemberShortNameFieldLength"
})
public class AccountSummarySegmentFields implements Serializable
{

    @JsonProperty("ReportingMemberShortNameFieldLength")
    private Integer reportingMemberShortNameFieldLength;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4051724565779129173L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccountSummarySegmentFields() {
    }

    /**
     * 
     * @param reportingMemberShortNameFieldLength
     */
    public AccountSummarySegmentFields(Integer reportingMemberShortNameFieldLength) {
        super();
        this.reportingMemberShortNameFieldLength = reportingMemberShortNameFieldLength;
    }

    @JsonProperty("ReportingMemberShortNameFieldLength")
    public Integer getReportingMemberShortNameFieldLength() {
        return reportingMemberShortNameFieldLength;
    }

    @JsonProperty("ReportingMemberShortNameFieldLength")
    public void setReportingMemberShortNameFieldLength(Integer reportingMemberShortNameFieldLength) {
        this.reportingMemberShortNameFieldLength = reportingMemberShortNameFieldLength;
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
