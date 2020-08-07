
package com.opl.mudra.api.mca.verifyApi.directorDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "director_name",
    "din",
    "address",
    "current_company",
    "past_company",
    "current_llp",
    "past_llp"
})
public class Data {

    @JsonProperty("director_name")
    private String directorName;
    @JsonProperty("din")
    private Integer din;
    @JsonProperty("address")
    private String address;
    @JsonProperty("current_company")
    private List<CurrentCompany> currentCompany = new ArrayList<CurrentCompany>();
    @JsonProperty("past_company")
    private List<PastCompany> pastCompany = new ArrayList<PastCompany>();
    @JsonProperty("current_llp")
    private List<CurrentLlp> currentLlp = new ArrayList<CurrentLlp>();
    @JsonProperty("past_llp")
    private List<Object> pastLlp = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("director_name")
    public String getDirectorName() {
        return directorName;
    }

    @JsonProperty("director_name")
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    @JsonProperty("din")
    public Integer getDin() {
        return din;
    }

    @JsonProperty("din")
    public void setDin(Integer din) {
        this.din = din;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("current_company")
    public List<CurrentCompany> getCurrentCompany() {
        return currentCompany;
    }

    @JsonProperty("current_company")
    public void setCurrentCompany(List<CurrentCompany> currentCompany) {
        this.currentCompany = currentCompany;
    }

    @JsonProperty("past_company")
    public List<PastCompany> getPastCompany() {
        return pastCompany;
    }

    @JsonProperty("past_company")
    public void setPastCompany(List<PastCompany> pastCompany) {
        this.pastCompany = pastCompany;
    }

    @JsonProperty("current_llp")
    public List<CurrentLlp> getCurrentLlp() {
        return currentLlp;
    }

    @JsonProperty("current_llp")
    public void setCurrentLlp(List<CurrentLlp> currentLlp) {
        this.currentLlp = currentLlp;
    }

    @JsonProperty("past_llp")
    public List<Object> getPastLlp() {
        return pastLlp;
    }

    @JsonProperty("past_llp")
    public void setPastLlp(List<Object> pastLlp) {
        this.pastLlp = pastLlp;
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
