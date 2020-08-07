
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
    "CreditReport"
})
public class BureauResponseXml_ implements Serializable
{

    @JsonProperty("CreditReport")
    private CreditReport creditReport;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5953616204805748784L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BureauResponseXml_() {
    }

    /**
     * 
     * @param creditReport
     */
    public BureauResponseXml_(CreditReport creditReport) {
        super();
        this.creditReport = creditReport;
    }

    @JsonProperty("CreditReport")
    public CreditReport getCreditReport() {
        return creditReport;
    }

    @JsonProperty("CreditReport")
    public void setCreditReport(CreditReport creditReport) {
        this.creditReport = creditReport;
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
