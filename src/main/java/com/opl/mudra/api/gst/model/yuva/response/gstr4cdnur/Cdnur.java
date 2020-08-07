
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnur;

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
    "typ",
    "inum",
    "idt",
    "sply_ty",
    "val",
    "itms"
})
public class Cdnur {

    @JsonProperty("chksum")
    private String chksum;
    @JsonProperty("ntty")
    private String ntty;
    @JsonProperty("nt_num")
    private String ntNum;
    @JsonProperty("nt_dt")
    private String ntDt;
    @JsonProperty("typ")
    private String typ;
    @JsonProperty("inum")
    private String inum;
    @JsonProperty("idt")
    private String idt;
    @JsonProperty("sply_ty")
    private String splyTy;
    @JsonProperty("val")
    private Double val;
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

    @JsonProperty("typ")
    public String getTyp() {
        return typ;
    }

    @JsonProperty("typ")
    public void setTyp(String typ) {
        this.typ = typ;
    }

    @JsonProperty("inum")
    public String getInum() {
        return inum;
    }

    @JsonProperty("inum")
    public void setInum(String inum) {
        this.inum = inum;
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

}
