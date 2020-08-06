
package com.opl.mudra.api.analyzer.model.inhouse;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "fileExists",
    "encrypted",
    "scanned",
    "customerInfo"
})
public class File {

    @JsonProperty("fileExists")
    private Boolean fileExists;
    @JsonProperty("encrypted")
    private Boolean encrypted;
    @JsonProperty("scanned")
    private Boolean scanned;
    @JsonProperty("customerInfo")
    private CustomerInfo customerInfo;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fileExists")
    public Boolean getFileExists() {
        return fileExists;
    }

    @JsonProperty("fileExists")
    public void setFileExists(Boolean fileExists) {
        this.fileExists = fileExists;
    }

    @JsonProperty("encrypted")
    public Boolean getEncrypted() {
        return encrypted;
    }

    @JsonProperty("encrypted")
    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    @JsonProperty("scanned")
    public Boolean getScanned() {
        return scanned;
    }

    @JsonProperty("scanned")
    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }

    @JsonProperty("customerInfo")
    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    @JsonProperty("customerInfo")
    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
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
