
package com.opl.mudra.api.gst.model.yuva.response.gstr2hsnsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author vijay.chauhan
 * Jan 28, 2020 2:58:26 PM
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "num",
    "hsn_sc",
    "desc",
    "uqc",
    "qty",
    "val",
    "txval",
    "iamt",
    "camt",
    "samt",
    "csamt"
})
public class Det {

    @JsonProperty("num")
    private Integer num;
    @JsonProperty("hsn_sc")
    private String hsnSc;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("uqc")
    private String uqc;
    @JsonProperty("qty")
    private Integer qty;
    @JsonProperty("val")
    private Double val;
    @JsonProperty("txval")
    private Double txval;
    @JsonProperty("iamt")
    private Integer iamt;
    @JsonProperty("camt")
    private Double camt;
    @JsonProperty("samt")
    private Double samt;
    @JsonProperty("csamt")
    private Integer csamt;

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("hsn_sc")
    public String getHsnSc() {
        return hsnSc;
    }

    @JsonProperty("hsn_sc")
    public void setHsnSc(String hsnSc) {
        this.hsnSc = hsnSc;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("uqc")
    public String getUqc() {
        return uqc;
    }

    @JsonProperty("uqc")
    public void setUqc(String uqc) {
        this.uqc = uqc;
    }

    @JsonProperty("qty")
    public Integer getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @JsonProperty("val")
    public Double getVal() {
        return val;
    }

    @JsonProperty("val")
    public void setVal(Double val) {
        this.val = val;
    }

    @JsonProperty("txval")
    public Double getTxval() {
        return txval;
    }

    @JsonProperty("txval")
    public void setTxval(Double txval) {
        this.txval = txval;
    }

    @JsonProperty("iamt")
    public Integer getIamt() {
        return iamt;
    }

    @JsonProperty("iamt")
    public void setIamt(Integer iamt) {
        this.iamt = iamt;
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

    @JsonProperty("csamt")
    public Integer getCsamt() {
        return csamt;
    }

    @JsonProperty("csamt")
    public void setCsamt(Integer csamt) {
        this.csamt = csamt;
    }

}
