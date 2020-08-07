
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
public class Cdnur {

    @JsonProperty("flag")
    private String flag;
    @JsonProperty("chksum")
    private String chksum;
    @JsonProperty("typ")
    private String typ;
    @JsonProperty("ntty")
    private String ntty;
    @JsonProperty("nt_num")
    private String ntNum;
    @JsonProperty("nt_dt")
    private String ntDt;
    @JsonProperty("p_gst")
    private String pGst;
    @JsonProperty("inum")
    private String inum;
    @JsonProperty("val")
    private Double val;
    @JsonProperty("idt")
    private String idt;
    @JsonProperty("itms")
    private Itm[] itms;

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("typ")
    public String getTyp() {
        return typ;
    }

    @JsonProperty("typ")
    public void setTyp(String typ) {
        this.typ = typ;
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

    @JsonProperty("p_gst")
    public String getPGst() {
        return pGst;
    }

    @JsonProperty("p_gst")
    public void setPGst(String pGst) {
        this.pGst = pGst;
    }

    @JsonProperty("inum")
    public String getInum() {
        return inum;
    }

    @JsonProperty("inum")
    public void setInum(String inum) {
        this.inum = inum;
    }

    @JsonProperty("val")
    public Double getVal() {
        return val;
    }

    @JsonProperty("val")
    public void setVal(Double val) {
        this.val = val;
    }

    @JsonProperty("idt")
    public String getIdt() {
        return idt;
    }

    @JsonProperty("idt")
    public void setIdt(String idt) {
        this.idt = idt;
    }

    @JsonProperty("itms")
    public Itm[] getItms() {
        return itms;
    }

    @JsonProperty("itms")
    public void setItms(Itm[] itms) {
        this.itms = itms;
    }


}
