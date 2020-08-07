
package com.opl.mudra.api.cibil_integration.msme.request.sbi.individual;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phoneType",
    "phoneNumber"
})
public class PhoneNumberDetail implements Serializable
{

    @JsonProperty("phoneType")
    private String phoneType;
    @JsonProperty("phoneNumber")
    private Long phoneNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6712398805092247424L;

    public PhoneNumberDetail(){
    	
    }
    
    public PhoneNumberDetail(String phoneType,Long phoneNumber){
    	this.phoneType = phoneType;
    	this.phoneNumber = phoneNumber;
    }
    
    @JsonProperty("phoneType")
    public String getPhoneType() {
        return phoneType;
    }

    @JsonProperty("phoneType")
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @JsonProperty("phoneNumber")
    public Long getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
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
