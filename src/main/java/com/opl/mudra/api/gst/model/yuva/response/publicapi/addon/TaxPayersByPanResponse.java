
package com.opl.mudra.api.gst.model.yuva.response.publicapi.addon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TaxPayersByPanResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @JsonProperty("stjCd")
    private String stjCd;
    @JsonProperty("lgnm")
    private String lgnm;
    @JsonProperty("stj")
    private String stj;
    @JsonProperty("dty")
    private String dty;
    @JsonProperty("cxdt")
    private String cxdt;
    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("nba")
    private List<String> nba = new ArrayList<String>();
    @JsonProperty("lstupdt")
    private String lstupdt;
    @JsonProperty("rgdt")
    private String rgdt;
    @JsonProperty("ctb")
    private String ctb;
    @JsonProperty("sts")
    private String sts;
    @JsonProperty("ctjCd")
    private String ctjCd;
    @JsonProperty("ctj")
    private String ctj;
    @JsonProperty("tradeNam")
    private String tradeNam;
    @JsonProperty("adadr")
    private List<Adadr> adadr = new ArrayList<Adadr>();
    @JsonProperty("pradr")
    private Adadr pradr;

    @JsonProperty("stjCd")
    public String getStjCd() {
        return stjCd;
    }

    @JsonProperty("stjCd")
    public void setStjCd(String stjCd) {
        this.stjCd = stjCd;
    }

    @JsonProperty("lgnm")
    public String getLgnm() {
        return lgnm;
    }

    @JsonProperty("lgnm")
    public void setLgnm(String lgnm) {
        this.lgnm = lgnm;
    }

    @JsonProperty("stj")
    public String getStj() {
        return stj;
    }

    @JsonProperty("stj")
    public void setStj(String stj) {
        this.stj = stj;
    }

    @JsonProperty("dty")
    public String getDty() {
        return dty;
    }

    @JsonProperty("dty")
    public void setDty(String dty) {
        this.dty = dty;
    }

    @JsonProperty("cxdt")
    public String getCxdt() {
        return cxdt;
    }

    @JsonProperty("cxdt")
    public void setCxdt(String cxdt) {
        this.cxdt = cxdt;
    }

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("nba")
    public List<String> getNba() {
        return nba;
    }

    @JsonProperty("nba")
    public void setNba(List<String> nba) {
        this.nba = nba;
    }

    @JsonProperty("lstupdt")
    public String getLstupdt() {
        return lstupdt;
    }

    @JsonProperty("lstupdt")
    public void setLstupdt(String lstupdt) {
        this.lstupdt = lstupdt;
    }

    @JsonProperty("rgdt")
    public String getRgdt() {
        return rgdt;
    }

    @JsonProperty("rgdt")
    public void setRgdt(String rgdt) {
        this.rgdt = rgdt;
    }

    @JsonProperty("ctb")
    public String getCtb() {
        return ctb;
    }

    @JsonProperty("ctb")
    public void setCtb(String ctb) {
        this.ctb = ctb;
    }

    @JsonProperty("sts")
    public String getSts() {
        return sts;
    }

    @JsonProperty("sts")
    public void setSts(String sts) {
        this.sts = sts;
    }

    @JsonProperty("ctjCd")
    public String getCtjCd() {
        return ctjCd;
    }

    @JsonProperty("ctjCd")
    public void setCtjCd(String ctjCd) {
        this.ctjCd = ctjCd;
    }

    @JsonProperty("ctj")
    public String getCtj() {
        return ctj;
    }

    @JsonProperty("ctj")
    public void setCtj(String ctj) {
        this.ctj = ctj;
    }

    @JsonProperty("tradeNam")
    public String getTradeNam() {
        return tradeNam;
    }

    @JsonProperty("tradeNam")
    public void setTradeNam(String tradeNam) {
        this.tradeNam = tradeNam;
    }

    @JsonProperty("adadr")
    public List<Adadr> getAdadr() {
        return adadr;
    }

    @JsonProperty("adadr")
    public void setAdadr(List<Adadr> adadr) {
        this.adadr = adadr;
    }

    @JsonProperty("pradr")
    public Adadr getPradr() {
        return pradr;
    }

    @JsonProperty("pradr")
    public void setPradr(Adadr pradr) {
        this.pradr = pradr;
    }

}
