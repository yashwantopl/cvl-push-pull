
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "igst",
    "cgst",
    "sgst",
    "cess",
    "tran_cd"
})
public class Rev {

    @JsonProperty("igst")
    private Igst igst;
    @JsonProperty("cgst")
    private Cgst cgst;
    @JsonProperty("sgst")
    private Sgst sgst;
    @JsonProperty("cess")
    private Cess cess;
    @JsonProperty("tran_cd")
    private Integer tranCd;
    

    @JsonProperty("igst")
    public Igst getIgst() {
        return igst;
    }

    @JsonProperty("igst")
    public void setIgst(Igst igst) {
        this.igst = igst;
    }

    @JsonProperty("cgst")
    public Cgst getCgst() {
        return cgst;
    }

    @JsonProperty("cgst")
    public void setCgst(Cgst cgst) {
        this.cgst = cgst;
    }

    @JsonProperty("sgst")
    public Sgst getSgst() {
        return sgst;
    }

    @JsonProperty("sgst")
    public void setSgst(Sgst sgst) {
        this.sgst = sgst;
    }

    @JsonProperty("cess")
    public Cess getCess() {
        return cess;
    }

    @JsonProperty("cess")
    public void setCess(Cess cess) {
        this.cess = cess;
    }

    @JsonProperty("tran_cd")
    public Integer getTranCd() {
        return tranCd;
    }

    @JsonProperty("tran_cd")
    public void setTranCd(Integer tranCd) {
        this.tranCd = tranCd;
    }
}
