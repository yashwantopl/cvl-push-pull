
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
    "DateOfBirth",
    "ConsumerName1",
    "Length",
    "ConsumerName5",
    "GenderFieldLength",
    "SegmentTag",
    "DateOfBirthFieldLength",
    "ConsumerName1FieldLength",
    "Gender",
    "ConsumerName5FieldLength"
})
public class NameSegment implements Serializable
{

    @JsonProperty("DateOfBirth")
    private String dateOfBirth;
    @JsonProperty("ConsumerName1")
    private String consumerName1;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("ConsumerName5")
    private String consumerName5;
    @JsonProperty("GenderFieldLength")
    private String genderFieldLength;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("DateOfBirthFieldLength")
    private String dateOfBirthFieldLength;
    @JsonProperty("ConsumerName1FieldLength")
    private Integer consumerName1FieldLength;
    @JsonProperty("Gender")
    private String gender;
    @JsonProperty("ConsumerName5FieldLength")
    private Integer consumerName5FieldLength;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 7737189475931485837L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public NameSegment() {
    }

    /**
     * 
     * @param dateOfBirth
     * @param genderFieldLength
     * @param consumerName5
     * @param consumerName1
     * @param length
     * @param gender
     * @param consumerName1FieldLength
     * @param consumerName5FieldLength
     * @param dateOfBirthFieldLength
     * @param segmentTag
     */
    public NameSegment(String dateOfBirth, String consumerName1, String length, String consumerName5, String genderFieldLength, String segmentTag, String dateOfBirthFieldLength, Integer consumerName1FieldLength, String gender, Integer consumerName5FieldLength) {
        super();
        this.dateOfBirth = dateOfBirth;
        this.consumerName1 = consumerName1;
        this.length = length;
        this.consumerName5 = consumerName5;
        this.genderFieldLength = genderFieldLength;
        this.segmentTag = segmentTag;
        this.dateOfBirthFieldLength = dateOfBirthFieldLength;
        this.consumerName1FieldLength = consumerName1FieldLength;
        this.gender = gender;
        this.consumerName5FieldLength = consumerName5FieldLength;
    }

    @JsonProperty("DateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("DateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("ConsumerName1")
    public String getConsumerName1() {
        return consumerName1;
    }

    @JsonProperty("ConsumerName1")
    public void setConsumerName1(String consumerName1) {
        this.consumerName1 = consumerName1;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("ConsumerName5")
    public String getConsumerName5() {
        return consumerName5;
    }

    @JsonProperty("ConsumerName5")
    public void setConsumerName5(String consumerName5) {
        this.consumerName5 = consumerName5;
    }

    @JsonProperty("GenderFieldLength")
    public String getGenderFieldLength() {
        return genderFieldLength;
    }

    @JsonProperty("GenderFieldLength")
    public void setGenderFieldLength(String genderFieldLength) {
        this.genderFieldLength = genderFieldLength;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("DateOfBirthFieldLength")
    public String getDateOfBirthFieldLength() {
        return dateOfBirthFieldLength;
    }

    @JsonProperty("DateOfBirthFieldLength")
    public void setDateOfBirthFieldLength(String dateOfBirthFieldLength) {
        this.dateOfBirthFieldLength = dateOfBirthFieldLength;
    }

    @JsonProperty("ConsumerName1FieldLength")
    public Integer getConsumerName1FieldLength() {
        return consumerName1FieldLength;
    }

    @JsonProperty("ConsumerName1FieldLength")
    public void setConsumerName1FieldLength(Integer consumerName1FieldLength) {
        this.consumerName1FieldLength = consumerName1FieldLength;
    }

    @JsonProperty("Gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("Gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("ConsumerName5FieldLength")
    public Integer getConsumerName5FieldLength() {
        return consumerName5FieldLength;
    }

    @JsonProperty("ConsumerName5FieldLength")
    public void setConsumerName5FieldLength(Integer consumerName5FieldLength) {
        this.consumerName5FieldLength = consumerName5FieldLength;
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
