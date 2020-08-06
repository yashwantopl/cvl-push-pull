
package com.opl.mudra.api.cibil.model.msme;

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
    "enquiry_amount",
    "enquiry_purpose",
    "enquiry_type",
    "type_of_entity",
    "class_of_activity",
    "date_of_registration",
    "cmr_flag"
})
public class GeneralFields {

    @JsonProperty("enquiry_amount")
    private String enquiryAmount;
    @JsonProperty("enquiry_purpose")
    private String enquiryPurpose;
    @JsonProperty("enquiry_type")
    private String enquiryType;
    @JsonProperty("type_of_entity")
    private String typeOfEntity;
    @JsonProperty("class_of_activity")
    private String classOfActivity;
    @JsonProperty("date_of_registration")
    private String dateOfRegistration;
    @JsonProperty("cmr_flag")
    private String cmrFlag;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("enquiry_amount")
    public String getEnquiryAmount() {
        return enquiryAmount;
    }

    @JsonProperty("enquiry_amount")
    public void setEnquiryAmount(String enquiryAmount) {
        this.enquiryAmount = enquiryAmount;
    }

    @JsonProperty("enquiry_purpose")
    public String getEnquiryPurpose() {
        return enquiryPurpose;
    }

    @JsonProperty("enquiry_purpose")
    public void setEnquiryPurpose(String enquiryPurpose) {
        this.enquiryPurpose = enquiryPurpose;
    }

    @JsonProperty("enquiry_type")
    public String getEnquiryType() {
        return enquiryType;
    }

    @JsonProperty("enquiry_type")
    public void setEnquiryType(String enquiryType) {
        this.enquiryType = enquiryType;
    }

    @JsonProperty("type_of_entity")
    public String getTypeOfEntity() {
        return typeOfEntity;
    }

    @JsonProperty("type_of_entity")
    public void setTypeOfEntity(String typeOfEntity) {
        this.typeOfEntity = typeOfEntity;
    }

    @JsonProperty("class_of_activity")
    public String getClassOfActivity() {
        return classOfActivity;
    }

    @JsonProperty("class_of_activity")
    public void setClassOfActivity(String classOfActivity) {
        this.classOfActivity = classOfActivity;
    }

    @JsonProperty("date_of_registration")
    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    @JsonProperty("date_of_registration")
    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    @JsonProperty("cmr_flag")
    public String getCmrFlag() {
        return cmrFlag;
    }

    @JsonProperty("cmr_flag")
    public void setCmrFlag(String cmrFlag) {
        this.cmrFlag = cmrFlag;
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
