
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Length",
    "IDNumberFieldLength",
    "SegmentTag",
    "IDType",
    "IDNumber"
})
public class IDSegment implements Serializable
{

    @JsonProperty("Length")
    private String length;
    @JsonProperty("IDNumberFieldLength")
    private Integer iDNumberFieldLength;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("IDType")
    private String iDType;
    @JsonProperty("IDNumber")
    private String iDNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4366061339520792289L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public IDSegment() {
    }

    /**
     * 
     * @param length
     * @param iDType
     * @param iDNumber
     * @param iDNumberFieldLength
     * @param segmentTag
     */
    public IDSegment(String length, Integer iDNumberFieldLength, String segmentTag, String iDType, String iDNumber) {
        super();
        this.length = length;
        this.iDNumberFieldLength = iDNumberFieldLength;
        this.segmentTag = segmentTag;
        this.iDType = iDType;
        this.iDNumber = iDNumber;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("IDNumberFieldLength")
    public Integer getIDNumberFieldLength() {
        return iDNumberFieldLength;
    }

    @JsonProperty("IDNumberFieldLength")
    public void setIDNumberFieldLength(Integer iDNumberFieldLength) {
        this.iDNumberFieldLength = iDNumberFieldLength;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("IDType")
    public String getIDType() {
        return iDType;
    }

    @JsonProperty("IDType")
    public void setIDType(String iDType) {
        this.iDType = iDType;
    }

    @JsonProperty("IDNumber")
    public String getIDNumber() {
        return iDNumber;
    }

    @JsonProperty("IDNumber")
    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
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
