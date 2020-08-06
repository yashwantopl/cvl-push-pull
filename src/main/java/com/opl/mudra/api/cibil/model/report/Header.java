
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
    "EnquiryControlNumber",
    "Version",
    "ReferenceNumber",
    "TimeProcessed",
    "DateProcessed",
    "SegmentTag",
    "SubjectReturnCode",
    "MemberCode"
})
public class Header implements Serializable
{

    @JsonProperty("EnquiryControlNumber")
    private String enquiryControlNumber;
    @JsonProperty("Version")
    private Integer version;
    @JsonProperty("ReferenceNumber")
    private Integer referenceNumber;
    @JsonProperty("TimeProcessed")
    private Integer timeProcessed;
    @JsonProperty("DateProcessed")
    private String dateProcessed;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("SubjectReturnCode")
    private Integer subjectReturnCode;
    @JsonProperty("MemberCode")
    private String memberCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2842217890393280917L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Header() {
    }

    /**
     * 
     * @param subjectReturnCode
     * @param timeProcessed
     * @param enquiryControlNumber
     * @param referenceNumber
     * @param dateProcessed
     * @param memberCode
     * @param segmentTag
     * @param version
     */
    public Header(String enquiryControlNumber, Integer version, Integer referenceNumber, Integer timeProcessed, String dateProcessed, String segmentTag, Integer subjectReturnCode, String memberCode) {
        super();
        this.enquiryControlNumber = enquiryControlNumber;
        this.version = version;
        this.referenceNumber = referenceNumber;
        this.timeProcessed = timeProcessed;
        this.dateProcessed = dateProcessed;
        this.segmentTag = segmentTag;
        this.subjectReturnCode = subjectReturnCode;
        this.memberCode = memberCode;
    }

    @JsonProperty("EnquiryControlNumber")
    public String getEnquiryControlNumber() {
        return enquiryControlNumber;
    }

    @JsonProperty("EnquiryControlNumber")
    public void setEnquiryControlNumber(String enquiryControlNumber) {
        this.enquiryControlNumber = enquiryControlNumber;
    }

    @JsonProperty("Version")
    public Integer getVersion() {
        return version;
    }

    @JsonProperty("Version")
    public void setVersion(Integer version) {
        this.version = version;
    }

    @JsonProperty("ReferenceNumber")
    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    @JsonProperty("ReferenceNumber")
    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @JsonProperty("TimeProcessed")
    public Integer getTimeProcessed() {
        return timeProcessed;
    }

    @JsonProperty("TimeProcessed")
    public void setTimeProcessed(Integer timeProcessed) {
        this.timeProcessed = timeProcessed;
    }

    @JsonProperty("DateProcessed")
    public String getDateProcessed() {
        return dateProcessed;
    }

    @JsonProperty("DateProcessed")
    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("SubjectReturnCode")
    public Integer getSubjectReturnCode() {
        return subjectReturnCode;
    }

    @JsonProperty("SubjectReturnCode")
    public void setSubjectReturnCode(Integer subjectReturnCode) {
        this.subjectReturnCode = subjectReturnCode;
    }

    @JsonProperty("MemberCode")
    public String getMemberCode() {
        return memberCode;
    }

    @JsonProperty("MemberCode")
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
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
