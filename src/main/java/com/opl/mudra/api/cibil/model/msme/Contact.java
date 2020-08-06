
package com.opl.mudra.api.cibil.model.msme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "address",
    "telephone"
})
public class Contact {

    @JsonProperty("address")
    private List<Address> address = null;
    @JsonProperty("telephone")
    private List<Telephone> telephone = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address")
    public List<Address> getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @JsonProperty("telephone")
    public List<Telephone> getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(List<Telephone> telephone) {
        this.telephone = telephone;
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
