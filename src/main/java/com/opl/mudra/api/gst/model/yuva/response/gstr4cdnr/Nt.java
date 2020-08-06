
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "chksum",
    "ntty",
    "nt_num",
    "nt_dt",
    "inum",
    "rchrg",
    "idt",
    "sply_ty",
    "updby",
    "opd",
    "cflag",
    "val",
    "itms"
})
public class Nt {

    @JsonProperty("chksum")
    private String chksum;
    @JsonProperty("ntty")
    private String ntty;
    @JsonProperty("nt_num")
    private String ntNum;
    @JsonProperty("nt_dt")
    private String ntDt;
    @JsonProperty("inum")
    private String inum;
    @JsonProperty("rchrg")
    private String rchrg;
    @JsonProperty("idt")
    private String idt;
    @JsonProperty("sply_ty")
    private String splyTy;
    @JsonProperty("updby")
    private String updby;
    @JsonProperty("opd")
    private String opd;
    @JsonProperty("cflag")
    private String cflag;
    @JsonProperty("val")
    private Double val;
    
    @JsonProperty("p_gst")
    private String pGst;
    @JsonProperty("itms")
    private Itm[] itms;


    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("ntty")
    public String getNtty() {
        return ntty;
    }

    @JsonProperty("ntty")
    public void setNtty(String ntty) {
        this.ntty = ntty;
    }

    @JsonProperty("nt_num")
    public String getNtNum() {
        return ntNum;
    }

    @JsonProperty("nt_num")
    public void setNtNum(String ntNum) {
        this.ntNum = ntNum;
    }

    @JsonProperty("nt_dt")
    public String getNtDt() {
        return ntDt;
    }

    @JsonProperty("nt_dt")
    public void setNtDt(String ntDt) {
        this.ntDt = ntDt;
    }

    @JsonProperty("inum")
    public String getInum() {
        return inum;
    }

    @JsonProperty("inum")
    public void setInum(String inum) {
        this.inum = inum;
    }

    @JsonProperty("rchrg")
    public String getRchrg() {
        return rchrg;
    }

    @JsonProperty("rchrg")
    public void setRchrg(String rchrg) {
        this.rchrg = rchrg;
    }

    @JsonProperty("idt")
    public String getIdt() {
        return idt;
    }

    @JsonProperty("idt")
    public void setIdt(String idt) {
        this.idt = idt;
    }

    @JsonProperty("sply_ty")
    public String getSplyTy() {
        return splyTy;
    }

    @JsonProperty("sply_ty")
    public void setSplyTy(String splyTy) {
        this.splyTy = splyTy;
    }

    @JsonProperty("updby")
    public String getUpdby() {
        return updby;
    }

    @JsonProperty("updby")
    public void setUpdby(String updby) {
        this.updby = updby;
    }

    @JsonProperty("opd")
    public String getOpd() {
        return opd;
    }

    @JsonProperty("opd")
    public void setOpd(String opd) {
        this.opd = opd;
    }

    @JsonProperty("cflag")
    public String getCflag() {
        return cflag;
    }

    @JsonProperty("cflag")
    public void setCflag(String cflag) {
        this.cflag = cflag;
    }

    @JsonProperty("val")
    public Double getVal() {
        return val;
    }

    @JsonProperty("val")
    public void setVal(Double val) {
        this.val = val;
    }

	public Itm[] getItms() {
		return itms;
	}

	public void setItms(Itm[] itms) {
		this.itms = itms;
	}

	public String getpGst() {
		return pGst;
	}

	public void setpGst(String pGst) {
		this.pGst = pGst;
	}
    
}
