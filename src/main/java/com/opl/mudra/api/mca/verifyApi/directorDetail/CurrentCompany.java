
package com.opl.mudra.api.mca.verifyApi.directorDetail;

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
    "company_name",
    "cin",
    "status",
    "city",
    "designation",
    "shell_company",
    "default_company",
    "litigation",
    "bifr",
    "disqualify"
})
public class CurrentCompany {

    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("status")
    private String status;
    @JsonProperty("city")
    private Object city;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("shell_company")
    private Boolean shellCompany;
    @JsonProperty("default_company")
    private Boolean defaultCompany;
    @JsonProperty("litigation")
    private Boolean litigation;
    @JsonProperty("bifr")
    private Boolean bifr;
    @JsonProperty("disqualify")
    private Boolean disqualify;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("company_name")
    public String getCompanyName() {
        return companyName;
    }

    @JsonProperty("company_name")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("cin")
    public String getCin() {
        return cin;
    }

    @JsonProperty("cin")
    public void setCin(String cin) {
        this.cin = cin;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("city")
    public Object getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(Object city) {
        this.city = city;
    }

    @JsonProperty("designation")
    public String getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @JsonProperty("shell_company")
    public Boolean getShellCompany() {
        return shellCompany;
    }

    @JsonProperty("shell_company")
    public void setShellCompany(Boolean shellCompany) {
        this.shellCompany = shellCompany;
    }

    @JsonProperty("default_company")
    public Boolean getDefaultCompany() {
        return defaultCompany;
    }

    @JsonProperty("default_company")
    public void setDefaultCompany(Boolean defaultCompany) {
        this.defaultCompany = defaultCompany;
    }

    @JsonProperty("litigation")
    public Boolean getLitigation() {
        return litigation;
    }

    @JsonProperty("litigation")
    public void setLitigation(Boolean litigation) {
        this.litigation = litigation;
    }

    @JsonProperty("bifr")
    public Boolean getBifr() {
        return bifr;
    }

    @JsonProperty("bifr")
    public void setBifr(Boolean bifr) {
        this.bifr = bifr;
    }

    @JsonProperty("disqualify")
    public Boolean getDisqualify() {
        return disqualify;
    }

    @JsonProperty("disqualify")
    public void setDisqualify(Boolean disqualify) {
        this.disqualify = disqualify;
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
