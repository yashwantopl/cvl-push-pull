
package com.opl.mudra.api.gst.model.yuva.response.publicapi.addon;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)

public class Addr  implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @JsonProperty("bnm")
    private String bnm;
    @JsonProperty("st")
    private String st;
    @JsonProperty("loc")
    private String loc;
    @JsonProperty("bno")
    private String bno;
    @JsonProperty("stcd")
    private String stcd;
    @JsonProperty("flno")
    private String flno;
    @JsonProperty("lt")
    private String lt;
    @JsonProperty("lg")
    private String lg;
    @JsonProperty("pncd")
    private String pncd;

    @JsonProperty("bnm")
    public String getBnm() {
        return bnm;
    }

    @JsonProperty("bnm")
    public void setBnm(String bnm) {
        this.bnm = bnm;
    }

    @JsonProperty("st")
    public String getSt() {
        return st;
    }

    @JsonProperty("st")
    public void setSt(String st) {
        this.st = st;
    }

    @JsonProperty("loc")
    public String getLoc() {
        return loc;
    }

    @JsonProperty("loc")
    public void setLoc(String loc) {
        this.loc = loc;
    }

    @JsonProperty("bno")
    public String getBno() {
        return bno;
    }

    @JsonProperty("bno")
    public void setBno(String bno) {
        this.bno = bno;
    }

    @JsonProperty("stcd")
    public String getStcd() {
        return stcd;
    }

    @JsonProperty("stcd")
    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    @JsonProperty("flno")
    public String getFlno() {
        return flno;
    }

    @JsonProperty("flno")
    public void setFlno(String flno) {
        this.flno = flno;
    }

    @JsonProperty("lt")
    public String getLt() {
        return lt;
    }

    @JsonProperty("lt")
    public void setLt(String lt) {
        this.lt = lt;
    }

    @JsonProperty("lg")
    public String getLg() {
        return lg;
    }

    @JsonProperty("lg")
    public void setLg(String lg) {
        this.lg = lg;
    }

    @JsonProperty("pncd")
    public String getPncd() {
        return pncd;
    }

    @JsonProperty("pncd")
    public void setPncd(String pncd) {
        this.pncd = pncd;
    }


}
