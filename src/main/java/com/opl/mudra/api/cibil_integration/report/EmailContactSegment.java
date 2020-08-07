
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "EmailID",
    "Length",
    "EmailIDFieldLength",
    "SegmentTag"
})
public class EmailContactSegment implements Serializable
{

    @JsonProperty("EmailID")
    private String emailID;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("EmailIDFieldLength")
    private String emailIDFieldLength;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -7420173975485885992L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EmailContactSegment() {
    }

    /**
     * 
     * @param emailID
     * @param length
     * @param emailIDFieldLength
     * @param segmentTag
     */
    public EmailContactSegment(String emailID, String length, String emailIDFieldLength, String segmentTag) {
        super();
        this.emailID = emailID;
        this.length = length;
        this.emailIDFieldLength = emailIDFieldLength;
        this.segmentTag = segmentTag;
    }

    @JsonProperty("EmailID")
    public String getEmailID() {
        return emailID;
    }

    @JsonProperty("EmailID")
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("EmailIDFieldLength")
    public String getEmailIDFieldLength() {
        return emailIDFieldLength;
    }

    @JsonProperty("EmailIDFieldLength")
    public void setEmailIDFieldLength(String emailIDFieldLength) {
        this.emailIDFieldLength = emailIDFieldLength;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
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
