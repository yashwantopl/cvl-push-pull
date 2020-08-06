
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices;

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
public class Cdnr {

    @JsonProperty("ctin")
    private String ctin;
    @JsonProperty("cfs")
    private String cfs;
    @JsonProperty("nt")
    private Nt[] nt;

    @JsonProperty("ctin")
    public String getCtin() {
        return ctin;
    }

    @JsonProperty("ctin")
    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    @JsonProperty("cfs")
    public String getCfs() {
        return cfs;
    }

    @JsonProperty("cfs")
    public void setCfs(String cfs) {
        this.cfs = cfs;
    }

    @JsonProperty("nt")
    public Nt[] getNt() {
        return nt;
    }

    @JsonProperty("nt")
    public void setNt(Nt[] nt) {
        this.nt = nt;
    }


}
