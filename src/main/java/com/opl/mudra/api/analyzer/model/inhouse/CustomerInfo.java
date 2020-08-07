
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
    "name",
    "accno",
    "ifsc",
    "micr",
    "acctyp",
    "branch",
    "date",
    "valid",
    "grid"
})
public class CustomerInfo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("accno")
    private String accno;
    @JsonProperty("ifsc")
    private String ifsc;
    @JsonProperty("micr")
    private String micr;
    @JsonProperty("acctyp")
    private String acctyp;
    @JsonProperty("branch")
    private String branch;
    @JsonProperty("date")
    private String date;
    @JsonProperty("valid")
    private Boolean valid;
    @JsonProperty("grid")
    private Boolean grid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("accno")
    public String getAccno() {
        return accno;
    }

    @JsonProperty("accno")
    public void setAccno(String accno) {
        this.accno = accno;
    }

    @JsonProperty("ifsc")
    public String getIfsc() {
        return ifsc;
    }

    @JsonProperty("ifsc")
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    @JsonProperty("micr")
    public String getMicr() {
        return micr;
    }

    @JsonProperty("micr")
    public void setMicr(String micr) {
        this.micr = micr;
    }

    @JsonProperty("acctyp")
    public String getAcctyp() {
        return acctyp;
    }

    @JsonProperty("acctyp")
    public void setAcctyp(String acctyp) {
        this.acctyp = acctyp;
    }

    @JsonProperty("branch")
    public String getBranch() {
        return branch;
    }

    @JsonProperty("branch")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("valid")
    public Boolean getValid() {
        return valid;
    }

    @JsonProperty("valid")
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @JsonProperty("grid")
    public Boolean getGrid() {
        return grid;
    }

    @JsonProperty("grid")
    public void setGrid(Boolean grid) {
        this.grid = grid;
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
