
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "tx",
    "intr",
    "pen",
    "fee",
    "oth",
    "tot"
})
public class Cgst {

    @JsonProperty("tx")
    private Integer tx;
    @JsonProperty("intr")
    private Integer intr;
    @JsonProperty("pen")
    private Integer pen;
    @JsonProperty("fee")
    private Integer fee;
    @JsonProperty("oth")
    private Integer oth;
    @JsonProperty("tot")
    private Integer tot;
    

    @JsonProperty("tx")
    public Integer getTx() {
        return tx;
    }

    @JsonProperty("tx")
    public void setTx(Integer tx) {
        this.tx = tx;
    }

    @JsonProperty("intr")
    public Integer getIntr() {
        return intr;
    }

    @JsonProperty("intr")
    public void setIntr(Integer intr) {
        this.intr = intr;
    }

    @JsonProperty("pen")
    public Integer getPen() {
        return pen;
    }

    @JsonProperty("pen")
    public void setPen(Integer pen) {
        this.pen = pen;
    }

    @JsonProperty("fee")
    public Integer getFee() {
        return fee;
    }

    @JsonProperty("fee")
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @JsonProperty("oth")
    public Integer getOth() {
        return oth;
    }

    @JsonProperty("oth")
    public void setOth(Integer oth) {
        this.oth = oth;
    }

    @JsonProperty("tot")
    public Integer getTot() {
        return tot;
    }

    @JsonProperty("tot")
    public void setTot(Integer tot) {
        this.tot = tot;
    }
}
