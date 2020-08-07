
package com.opl.mudra.api.analyzer.model.finbit;

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
    "average",
    "monthlyDetails",
    "total",
    "totalValue"
})
public class PeakUtilization {

    @JsonProperty("average")
    private Double average;
    @JsonProperty("monthlyDetails")
    private List<MonthlyDetail> monthlyDetails = null;
    @JsonProperty("total")
    private Double total;
    @JsonProperty("totalValue")
    private Double totalValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("average")
    public Double getAverage() {
        return average;
    }

    @JsonProperty("average")
    public void setAverage(Double average) {
        this.average = average;
    }

    @JsonProperty("monthlyDetails")
    public List<MonthlyDetail> getMonthlyDetails() {
        return monthlyDetails;
    }

    @JsonProperty("monthlyDetails")
    public void setMonthlyDetails(List<MonthlyDetail> monthlyDetails) {
        this.monthlyDetails = monthlyDetails;
    }

    @JsonProperty("total")
    public Double getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Double total) {
        this.total = total;
    }

    @JsonProperty("totalValue")
    public Double getTotalValue() {
        return totalValue;
    }

    @JsonProperty("totalValue")
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
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
