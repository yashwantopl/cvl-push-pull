
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "AddressCategory",
    "DateReported",
    "AddressSegmentTag",
    "StateCode",
    "SegmentTag",
    "AddressLine2FieldLength",
    "PinCodeFieldLength",
    "PinCode",
    "AddressLine3",
    "EnrichedThroughEnquiry",
    "AddressLine2",
    "AddressLine1",
    "Length",
    "AddressLine3FieldLength",
    "ResidenceCode",
    "AddressLine1FieldLength"
})
public class Address implements Serializable
{

    @JsonProperty("AddressCategory")
    private String addressCategory;
    @JsonProperty("DateReported")
    private String dateReported;
    @JsonProperty("AddressSegmentTag")
    private String addressSegmentTag;
    @JsonProperty("StateCode")
    private String stateCode;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("AddressLine2FieldLength")
    private String addressLine2FieldLength;
    @JsonProperty("PinCodeFieldLength")
    private String pinCodeFieldLength;
    @JsonProperty("PinCode")
    private String pinCode;
    @JsonProperty("AddressLine3")
    private String addressLine3;
    @JsonProperty("EnrichedThroughEnquiry")
    private String enrichedThroughEnquiry;
    @JsonProperty("AddressLine2")
    private String addressLine2;
    @JsonProperty("AddressLine1")
    private String addressLine1;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("AddressLine3FieldLength")
    private String addressLine3FieldLength;
    @JsonProperty("ResidenceCode")
    private String residenceCode;
    @JsonProperty("AddressLine1FieldLength")
    private Integer addressLine1FieldLength;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4461748522945560491L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Address() {
    }

    /**
     * 
     * @param stateCode
     * @param residenceCode
     * @param addressCategory
     * @param addressLine3
     * @param addressLine2
     * @param addressLine1
     * @param addressLine1FieldLength
     * @param addressLine2FieldLength
     * @param pinCode
     * @param addressLine3FieldLength
     * @param enrichedThroughEnquiry
     * @param dateReported
     * @param addressSegmentTag
     * @param length
     * @param pinCodeFieldLength
     * @param segmentTag
     */
    public Address(String addressCategory, String dateReported, String addressSegmentTag, String stateCode, String segmentTag, String addressLine2FieldLength, String pinCodeFieldLength, String pinCode, String addressLine3, String enrichedThroughEnquiry, String addressLine2, String addressLine1, String length, String addressLine3FieldLength, String residenceCode, Integer addressLine1FieldLength) {
        super();
        this.addressCategory = addressCategory;
        this.dateReported = dateReported;
        this.addressSegmentTag = addressSegmentTag;
        this.stateCode = stateCode;
        this.segmentTag = segmentTag;
        this.addressLine2FieldLength = addressLine2FieldLength;
        this.pinCodeFieldLength = pinCodeFieldLength;
        this.pinCode = pinCode;
        this.addressLine3 = addressLine3;
        this.enrichedThroughEnquiry = enrichedThroughEnquiry;
        this.addressLine2 = addressLine2;
        this.addressLine1 = addressLine1;
        this.length = length;
        this.addressLine3FieldLength = addressLine3FieldLength;
        this.residenceCode = residenceCode;
        this.addressLine1FieldLength = addressLine1FieldLength;
    }

    @JsonProperty("AddressCategory")
    public String getAddressCategory() {
        return addressCategory;
    }

    @JsonProperty("AddressCategory")
    public void setAddressCategory(String addressCategory) {
        this.addressCategory = addressCategory;
    }

    @JsonProperty("DateReported")
    public String getDateReported() {
        return dateReported;
    }

    @JsonProperty("DateReported")
    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    @JsonProperty("AddressSegmentTag")
    public String getAddressSegmentTag() {
        return addressSegmentTag;
    }

    @JsonProperty("AddressSegmentTag")
    public void setAddressSegmentTag(String addressSegmentTag) {
        this.addressSegmentTag = addressSegmentTag;
    }

    @JsonProperty("StateCode")
    public String getStateCode() {
        return stateCode;
    }

    @JsonProperty("StateCode")
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("AddressLine2FieldLength")
    public String getAddressLine2FieldLength() {
        return addressLine2FieldLength;
    }

    @JsonProperty("AddressLine2FieldLength")
    public void setAddressLine2FieldLength(String addressLine2FieldLength) {
        this.addressLine2FieldLength = addressLine2FieldLength;
    }

    @JsonProperty("PinCodeFieldLength")
    public String getPinCodeFieldLength() {
        return pinCodeFieldLength;
    }

    @JsonProperty("PinCodeFieldLength")
    public void setPinCodeFieldLength(String pinCodeFieldLength) {
        this.pinCodeFieldLength = pinCodeFieldLength;
    }

    @JsonProperty("PinCode")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("PinCode")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @JsonProperty("AddressLine3")
    public String getAddressLine3() {
        return addressLine3;
    }

    @JsonProperty("AddressLine3")
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    @JsonProperty("EnrichedThroughEnquiry")
    public String getEnrichedThroughEnquiry() {
        return enrichedThroughEnquiry;
    }

    @JsonProperty("EnrichedThroughEnquiry")
    public void setEnrichedThroughEnquiry(String enrichedThroughEnquiry) {
        this.enrichedThroughEnquiry = enrichedThroughEnquiry;
    }

    @JsonProperty("AddressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("AddressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("AddressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("AddressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("AddressLine3FieldLength")
    public String getAddressLine3FieldLength() {
        return addressLine3FieldLength;
    }

    @JsonProperty("AddressLine3FieldLength")
    public void setAddressLine3FieldLength(String addressLine3FieldLength) {
        this.addressLine3FieldLength = addressLine3FieldLength;
    }

    @JsonProperty("ResidenceCode")
    public String getResidenceCode() {
        return residenceCode;
    }

    @JsonProperty("ResidenceCode")
    public void setResidenceCode(String residenceCode) {
        this.residenceCode = residenceCode;
    }

    @JsonProperty("AddressLine1FieldLength")
    public Integer getAddressLine1FieldLength() {
        return addressLine1FieldLength;
    }

    @JsonProperty("AddressLine1FieldLength")
    public void setAddressLine1FieldLength(Integer addressLine1FieldLength) {
        this.addressLine1FieldLength = addressLine1FieldLength;
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
