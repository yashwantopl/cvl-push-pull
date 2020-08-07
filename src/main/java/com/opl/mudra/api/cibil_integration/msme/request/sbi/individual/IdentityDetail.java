
package com.opl.mudra.api.cibil_integration.msme.request.sbi.individual;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idType",
    "idValue"
})
public class IdentityDetail implements Serializable
{

    @JsonProperty("idType")
    private String idType;
    @JsonProperty("idValue")
    private String idValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1514364034328361219L;

    @JsonProperty("idType")
    public String getIdType() {
        return idType;
    }

    @JsonProperty("idType")
    public void setIdType(String idType) {
        this.idType = idType;
    }

    @JsonProperty("idValue")
    public String getIdValue() {
        return idValue;
    }

    @JsonProperty("idValue")
    public void setIdValue(String idValue) {
        this.idValue = idValue;
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
