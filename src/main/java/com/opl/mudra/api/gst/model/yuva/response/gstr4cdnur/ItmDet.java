
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnur;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "rt",
    "txval",
    "camt",
    "samt",
    "iamt",
    "csamt"
})
public class ItmDet {

    @JsonProperty("rt")
    private Integer rt;
    @JsonProperty("txval")
    private Double txval;
    @JsonProperty("camt")
    private Double camt;
    @JsonProperty("samt")
    private Double samt;
    @JsonProperty("iamt")
    private Integer iamt;
    @JsonProperty("csamt")
    private Double csamt;

    @JsonProperty("rt")
    public Integer getRt() {
        return rt;
    }

    @JsonProperty("rt")
    public void setRt(Integer rt) {
        this.rt = rt;
    }

    @JsonProperty("txval")
    public Double getTxval() {
        return txval;
    }

    @JsonProperty("txval")
    public void setTxval(Double txval) {
        this.txval = txval;
    }

    @JsonProperty("camt")
    public Double getCamt() {
        return camt;
    }

    @JsonProperty("camt")
    public void setCamt(Double camt) {
        this.camt = camt;
    }

    @JsonProperty("samt")
    public Double getSamt() {
        return samt;
    }

    @JsonProperty("samt")
    public void setSamt(Double samt) {
        this.samt = samt;
    }

    @JsonProperty("iamt")
    public Integer getIamt() {
        return iamt;
    }

    @JsonProperty("iamt")
    public void setIamt(Integer iamt) {
        this.iamt = iamt;
    }

    @JsonProperty("csamt")
    public Double getCsamt() {
        return csamt;
    }

    @JsonProperty("csamt")
    public void setCsamt(Double csamt) {
        this.csamt = csamt;
    }
}
