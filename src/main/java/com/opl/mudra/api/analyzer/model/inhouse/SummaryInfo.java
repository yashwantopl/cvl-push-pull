
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
    "fullMonthCount",
    "total",
    "avg"
})
public class SummaryInfo {

    @JsonProperty("fullMonthCount")
    private Integer fullMonthCount;
    @JsonProperty("total")
    private Total total;
    @JsonProperty("avg")
    private Avg avg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fullMonthCount")
    public Integer getFullMonthCount() {
        return fullMonthCount;
    }

    @JsonProperty("fullMonthCount")
    public void setFullMonthCount(Integer fullMonthCount) {
        this.fullMonthCount = fullMonthCount;
    }

    @JsonProperty("total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonProperty("avg")
    public Avg getAvg() {
        return avg;
    }

    @JsonProperty("avg")
    public void setAvg(Avg avg) {
        this.avg = avg;
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
