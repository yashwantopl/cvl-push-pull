
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pan",
    "cin",
    "crn",
    "tin"
})
public class Id {

    @JsonProperty("pan")
    private String pan;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("crn")
    private String crn;
    @JsonProperty("tin")
    private String tin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pan")
    public String getPan() {
        return pan;
    }

    @JsonProperty("pan")
    public void setPan(String pan) {
        this.pan = pan;
    }

    @JsonProperty("cin")
    public String getCin() {
        return cin;
    }

    @JsonProperty("cin")
    public void setCin(String cin) {
        this.cin = cin;
    }

    @JsonProperty("crn")
    public String getCrn() {
        return crn;
    }

    @JsonProperty("crn")
    public void setCrn(String crn) {
        this.crn = crn;
    }

    @JsonProperty("tin")
    public String getTin() {
        return tin;
    }

    @JsonProperty("tin")
    public void setTin(String tin) {
        this.tin = tin;
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
