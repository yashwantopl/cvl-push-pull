
package com.opl.mudra.api.cibil.model.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "EnrichedThroughEnquiry",
    "TelephoneNumberFieldLength",
    "Length",
    "SegmentTag",
    "TelephoneType",
    "TelephoneNumber",
    "TelephoneExtensionFieldLength",
    "TelephoneExtension"
})
public class TelephoneSegment implements Serializable
{

    @JsonProperty("EnrichedThroughEnquiry")
    private String enrichedThroughEnquiry;
    @JsonProperty("TelephoneNumberFieldLength")
    private Integer telephoneNumberFieldLength;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("TelephoneType")
    private String telephoneType;
    @JsonProperty("TelephoneNumber")
    private String telephoneNumber;
    @JsonProperty("TelephoneExtensionFieldLength")
    private String telephoneExtensionFieldLength;
    @JsonProperty("TelephoneExtension")
    private String telephoneExtension;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 2314856904530158775L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TelephoneSegment() {
    }

    /**
     * 
     * @param telephoneExtension
     * @param enrichedThroughEnquiry
     * @param telephoneNumber
     * @param telephoneNumberFieldLength
     * @param length
     * @param telephoneType
     * @param telephoneExtensionFieldLength
     * @param segmentTag
     */
    public TelephoneSegment(String enrichedThroughEnquiry, Integer telephoneNumberFieldLength, String length, String segmentTag, String telephoneType, String telephoneNumber, String telephoneExtensionFieldLength, String telephoneExtension) {
        super();
        this.enrichedThroughEnquiry = enrichedThroughEnquiry;
        this.telephoneNumberFieldLength = telephoneNumberFieldLength;
        this.length = length;
        this.segmentTag = segmentTag;
        this.telephoneType = telephoneType;
        this.telephoneNumber = telephoneNumber;
        this.telephoneExtensionFieldLength = telephoneExtensionFieldLength;
        this.telephoneExtension = telephoneExtension;
    }

    @JsonProperty("EnrichedThroughEnquiry")
    public String getEnrichedThroughEnquiry() {
        return enrichedThroughEnquiry;
    }

    @JsonProperty("EnrichedThroughEnquiry")
    public void setEnrichedThroughEnquiry(String enrichedThroughEnquiry) {
        this.enrichedThroughEnquiry = enrichedThroughEnquiry;
    }

    @JsonProperty("TelephoneNumberFieldLength")
    public Integer getTelephoneNumberFieldLength() {
        return telephoneNumberFieldLength;
    }

    @JsonProperty("TelephoneNumberFieldLength")
    public void setTelephoneNumberFieldLength(Integer telephoneNumberFieldLength) {
        this.telephoneNumberFieldLength = telephoneNumberFieldLength;
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

    @JsonProperty("TelephoneType")
    public String getTelephoneType() {
        return telephoneType;
    }

    @JsonProperty("TelephoneType")
    public void setTelephoneType(String telephoneType) {
        this.telephoneType = telephoneType;
    }

    @JsonProperty("TelephoneNumber")
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @JsonProperty("TelephoneNumber")
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @JsonProperty("TelephoneExtensionFieldLength")
    public String getTelephoneExtensionFieldLength() {
        return telephoneExtensionFieldLength;
    }

    @JsonProperty("TelephoneExtensionFieldLength")
    public void setTelephoneExtensionFieldLength(String telephoneExtensionFieldLength) {
        this.telephoneExtensionFieldLength = telephoneExtensionFieldLength;
    }

    @JsonProperty("TelephoneExtension")
    public String getTelephoneExtension() {
        return telephoneExtension;
    }

    @JsonProperty("TelephoneExtension")
    public void setTelephoneExtension(String telephoneExtension) {
        this.telephoneExtension = telephoneExtension;
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
