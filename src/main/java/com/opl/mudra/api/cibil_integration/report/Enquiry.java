
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "EnquiryPurpose",
    "EnquiryAmountFieldLength",
    "Length",
    "EnquiringMemberShortNameFieldLength",
    "EnquiringMemberShortName",
    "DateOfEnquiryFields",
    "EnquiryAmount",
    "SegmentTag"
})
public class Enquiry implements Serializable
{

    @JsonProperty("EnquiryPurpose")
    private String enquiryPurpose;
    @JsonProperty("EnquiryAmountFieldLength")
    private String enquiryAmountFieldLength;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("EnquiringMemberShortNameFieldLength")
    private String enquiringMemberShortNameFieldLength;
    @JsonProperty("EnquiringMemberShortName")
    private String enquiringMemberShortName;
    @JsonProperty("DateOfEnquiryFields")
    private String dateOfEnquiryFields;
    @JsonProperty("EnquiryAmount")
    private Integer enquiryAmount;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5876099741250561275L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Enquiry() {
    }

    /**
     * 
     * @param enquiryAmountFieldLength
     * @param enquiringMemberShortNameFieldLength
     * @param length
     * @param enquiringMemberShortName
     * @param enquiryPurpose
     * @param enquiryAmount
     * @param segmentTag
     * @param dateOfEnquiryFields
     */
    public Enquiry(String enquiryPurpose, String enquiryAmountFieldLength, String length, String enquiringMemberShortNameFieldLength, String enquiringMemberShortName, String dateOfEnquiryFields, Integer enquiryAmount, String segmentTag) {
        super();
        this.enquiryPurpose = enquiryPurpose;
        this.enquiryAmountFieldLength = enquiryAmountFieldLength;
        this.length = length;
        this.enquiringMemberShortNameFieldLength = enquiringMemberShortNameFieldLength;
        this.enquiringMemberShortName = enquiringMemberShortName;
        this.dateOfEnquiryFields = dateOfEnquiryFields;
        this.enquiryAmount = enquiryAmount;
        this.segmentTag = segmentTag;
    }

    @JsonProperty("EnquiryPurpose")
    public String getEnquiryPurpose() {
        return enquiryPurpose;
    }

    @JsonProperty("EnquiryPurpose")
    public void setEnquiryPurpose(String enquiryPurpose) {
        this.enquiryPurpose = enquiryPurpose;
    }

    @JsonProperty("EnquiryAmountFieldLength")
    public String getEnquiryAmountFieldLength() {
        return enquiryAmountFieldLength;
    }

    @JsonProperty("EnquiryAmountFieldLength")
    public void setEnquiryAmountFieldLength(String enquiryAmountFieldLength) {
        this.enquiryAmountFieldLength = enquiryAmountFieldLength;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("EnquiringMemberShortNameFieldLength")
    public String getEnquiringMemberShortNameFieldLength() {
        return enquiringMemberShortNameFieldLength;
    }

    @JsonProperty("EnquiringMemberShortNameFieldLength")
    public void setEnquiringMemberShortNameFieldLength(String enquiringMemberShortNameFieldLength) {
        this.enquiringMemberShortNameFieldLength = enquiringMemberShortNameFieldLength;
    }

    @JsonProperty("EnquiringMemberShortName")
    public String getEnquiringMemberShortName() {
        return enquiringMemberShortName;
    }

    @JsonProperty("EnquiringMemberShortName")
    public void setEnquiringMemberShortName(String enquiringMemberShortName) {
        this.enquiringMemberShortName = enquiringMemberShortName;
    }

    @JsonProperty("DateOfEnquiryFields")
    public String getDateOfEnquiryFields() {
        return dateOfEnquiryFields;
    }

    @JsonProperty("DateOfEnquiryFields")
    public void setDateOfEnquiryFields(String dateOfEnquiryFields) {
        this.dateOfEnquiryFields = dateOfEnquiryFields;
    }

    @JsonProperty("EnquiryAmount")
    public Integer getEnquiryAmount() {
        return enquiryAmount;
    }

    @JsonProperty("EnquiryAmount")
    public void setEnquiryAmount(Integer enquiryAmount) {
        this.enquiryAmount = enquiryAmount;
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
