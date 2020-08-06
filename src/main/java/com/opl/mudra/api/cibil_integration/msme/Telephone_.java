
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "telephoneType",
    "telephone_num",
    "contact_area",
    "contact_prefix"
})
public class Telephone_ {

    @JsonProperty("telephoneType")
    private String telephoneType;
    @JsonProperty("telephone_num")
    private String telephoneNum;
    @JsonProperty("contact_area")
    private String contactArea;
    @JsonProperty("contact_prefix")
    private String contactPrefix;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("telephoneType")
    public String getTelephoneType() {
        return telephoneType;
    }

    @JsonProperty("telephoneType")
    public void setTelephoneType(String telephoneType) {
        this.telephoneType = telephoneType;
    }

    @JsonProperty("telephone_num")
    public String getTelephoneNum() {
        return telephoneNum;
    }

    @JsonProperty("telephone_num")
    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    @JsonProperty("contact_area")
    public String getContactArea() {
        return contactArea;
    }

    @JsonProperty("contact_area")
    public void setContactArea(String contactArea) {
        this.contactArea = contactArea;
    }

    @JsonProperty("contact_prefix")
    public String getContactPrefix() {
        return contactPrefix;
    }

    @JsonProperty("contact_prefix")
    public void setContactPrefix(String contactPrefix) {
        this.contactPrefix = contactPrefix;
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
