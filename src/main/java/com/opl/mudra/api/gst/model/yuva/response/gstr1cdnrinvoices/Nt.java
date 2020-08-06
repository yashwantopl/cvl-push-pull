
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices;

import java.util.ArrayList;
import java.util.List;

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
public class Nt {

    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("cflag")
    private String cflag;
    
    @JsonProperty("updby")
    private String updby;
    
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
    
    @JsonProperty("idt")
    private String idt;
    
    @JsonProperty("val")
    private Double val;
    
    @JsonProperty("opd")
    private String opd;
    
    @JsonProperty("flag")
    private String flag;
    
    @JsonProperty("diff_percent")
    private String diffPercent;
    
    @JsonProperty("itms")
    private List<Itm> itms = new ArrayList<Itm>();

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("cflag")
    public String getCflag() {
        return cflag;
    }

    @JsonProperty("cflag")
    public void setCflag(String cflag) {
        this.cflag = cflag;
    }

    @JsonProperty("updby")
    public String getUpdby() {
        return updby;
    }

    @JsonProperty("updby")
    public void setUpdby(String updby) {
        this.updby = updby;
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

    @JsonProperty("idt")
    public String getIdt() {
        return idt;
    }

    @JsonProperty("idt")
    public void setIdt(String idt) {
        this.idt = idt;
    }

    @JsonProperty("val")
    public Double getVal() {
        return val;
    }

    @JsonProperty("val")
    public void setVal(Double val) {
        this.val = val;
    }

    @JsonProperty("opd")
    public String getOpd() {
        return opd;
    }

    @JsonProperty("opd")
    public void setOpd(String opd) {
        this.opd = opd;
    }

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @JsonProperty("itms")
    public List<Itm> getItms() {
        return itms;
    }

    @JsonProperty("itms")
    public void setItms(List<Itm> itms) {
        this.itms = itms;
    }


	@JsonProperty("diff_percent")
	public String getDiffPercent() {
		return diffPercent;
	}

	@JsonProperty("diff_percent")
	public void setDiffPercent(String diffPercent) {
		this.diffPercent = diffPercent;
	}


}
