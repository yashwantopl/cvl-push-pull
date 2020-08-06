
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Account_Summary_Segment_Fields",
    "Length",
    "SegmentTag",
    "Account_NonSummary_Segment_Fields"
})
public class Account implements Serializable
{

    @JsonProperty("Account_Summary_Segment_Fields")
    private AccountSummarySegmentFields accountSummarySegmentFields;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("Account_NonSummary_Segment_Fields")
    private AccountNonSummarySegmentFields accountNonSummarySegmentFields;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8157465776216938805L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Account() {
    }

    /**
     * 
     * @param length
     * @param accountSummarySegmentFields
     * @param accountNonSummarySegmentFields
     * @param segmentTag
     */
    public Account(AccountSummarySegmentFields accountSummarySegmentFields, String length, String segmentTag, AccountNonSummarySegmentFields accountNonSummarySegmentFields) {
        super();
        this.accountSummarySegmentFields = accountSummarySegmentFields;
        this.length = length;
        this.segmentTag = segmentTag;
        this.accountNonSummarySegmentFields = accountNonSummarySegmentFields;
    }

    @JsonProperty("Account_Summary_Segment_Fields")
    public AccountSummarySegmentFields getAccountSummarySegmentFields() {
        return accountSummarySegmentFields;
    }

    @JsonProperty("Account_Summary_Segment_Fields")
    public void setAccountSummarySegmentFields(AccountSummarySegmentFields accountSummarySegmentFields) {
        this.accountSummarySegmentFields = accountSummarySegmentFields;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("Account_NonSummary_Segment_Fields")
    public AccountNonSummarySegmentFields getAccountNonSummarySegmentFields() {
        return accountNonSummarySegmentFields;
    }

    @JsonProperty("Account_NonSummary_Segment_Fields")
    public void setAccountNonSummarySegmentFields(AccountNonSummarySegmentFields accountNonSummarySegmentFields) {
        this.accountNonSummarySegmentFields = accountNonSummarySegmentFields;
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
