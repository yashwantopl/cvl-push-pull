
package com.opl.mudra.api.mca.verifyApi;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "reference-id",
    "dinname",
    "pannumber"
})
public class SuccessStatusDetail {

    @JsonProperty("reference-id")
    private Long referenceId;
    @JsonProperty("dinname")
    private String dinname;
    @JsonProperty("pannumber")
    private String pannumber;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("reference-id")
    public Long getReferenceId() {
        return referenceId;
    }

    @JsonProperty("reference-id")
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    @JsonProperty("dinname")
    public String getDinname() {
        return dinname;
    }

    @JsonProperty("dinname")
    public void setDinname(String dinname) {
        this.dinname = dinname;
    }

    @JsonProperty("pannumber")
    public String getPannumber() {
        return pannumber;
    }

    @JsonProperty("pannumber")
    public void setPannumber(String pannumber) {
        this.pannumber = pannumber;
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
