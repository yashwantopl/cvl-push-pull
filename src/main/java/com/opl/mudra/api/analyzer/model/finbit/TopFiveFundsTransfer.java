package com.opl.mudra.api.analyzer.model.finbit;/*
package com.example;

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
    "Dec 2017",
    "Jan 2018",
    "Feb 2018",
    "Mar 2018"
})
public class TopFiveFundsTransfer {

    @JsonProperty("Dec 2017")
    private List<Dec2017> dec2017 = null;
    @JsonProperty("Jan 2018")
    private List<Jan2018> jan2018 = null;
    @JsonProperty("Feb 2018")
    private List<Feb2018> feb2018 = null;
    @JsonProperty("Mar 2018")
    private List<Mar2018> mar2018 = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Dec 2017")
    public List<Dec2017> getDec2017() {
        return dec2017;
    }

    @JsonProperty("Dec 2017")
    public void setDec2017(List<Dec2017> dec2017) {
        this.dec2017 = dec2017;
    }

    @JsonProperty("Jan 2018")
    public List<Jan2018> getJan2018() {
        return jan2018;
    }

    @JsonProperty("Jan 2018")
    public void setJan2018(List<Jan2018> jan2018) {
        this.jan2018 = jan2018;
    }

    @JsonProperty("Feb 2018")
    public List<Feb2018> getFeb2018() {
        return feb2018;
    }

    @JsonProperty("Feb 2018")
    public void setFeb2018(List<Feb2018> feb2018) {
        this.feb2018 = feb2018;
    }

    @JsonProperty("Mar 2018")
    public List<Mar2018> getMar2018() {
        return mar2018;
    }

    @JsonProperty("Mar 2018")
    public void setMar2018(List<Mar2018> mar2018) {
        this.mar2018 = mar2018;
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
*/