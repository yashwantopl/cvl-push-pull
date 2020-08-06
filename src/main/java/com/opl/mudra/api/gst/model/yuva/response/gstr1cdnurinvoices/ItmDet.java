
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnurinvoices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author vijay.chauhan
 *
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class ItmDet {

    @JsonProperty("rt")
    private Integer rt;
    @JsonProperty("txval")
    private Double txval;
    @JsonProperty("iamt")
    private Double iamt;
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

    @JsonProperty("iamt")
    public Double getIamt() {
        return iamt;
    }

    @JsonProperty("iamt")
    public void setIamt(Double iamt) {
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
