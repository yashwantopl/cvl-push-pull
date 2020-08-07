
package com.opl.mudra.api.cibil_integration.msme.request.sbi.individual;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "emailCategory",
    "emailId"
})
public class EmailDetail implements Serializable
{

    @JsonProperty("emailCategory")
    private String emailCategory;
    @JsonProperty("emailId")
    private String emailId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1403374261264687367L;

    @JsonProperty("emailCategory")
    public String getEmailCategory() {
        return emailCategory;
    }

    @JsonProperty("emailCategory")
    public void setEmailCategory(String emailCategory) {
        this.emailCategory = emailCategory;
    }

    @JsonProperty("emailId")
    public String getEmailId() {
        return emailId;
    }

    @JsonProperty("emailId")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
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
