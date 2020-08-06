
package com.opl.mudra.api.cibil.model.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "BureauResponseXml"
})
public class BureauResponseXml implements Serializable
{

    @JsonProperty("BureauResponseXml")
    private BureauResponseXml_ bureauResponseXml;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -6088393117360583384L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BureauResponseXml() {
    }

    /**
     * 
     * @param bureauResponseXml
     */
    public BureauResponseXml(BureauResponseXml_ bureauResponseXml) {
        super();
        this.bureauResponseXml = bureauResponseXml;
    }

    @JsonProperty("BureauResponseXml")
    public BureauResponseXml_ getBureauResponseXml() {
        return bureauResponseXml;
    }

    @JsonProperty("BureauResponseXml")
    public void setBureauResponseXml(BureauResponseXml_ bureauResponseXml) {
        this.bureauResponseXml = bureauResponseXml;
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
