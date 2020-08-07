package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Nt {

	@JsonProperty("camt")
	private Double camt;

	@JsonProperty("chksum")
	private String chkSum;

	@JsonProperty("csamt")
	private Double csamt;

	@JsonProperty("iamt")
	private Double iamt;

	@JsonProperty("idt")
	private String idt;

	@JsonProperty("inum")
	private String inum;

	@JsonProperty("nt_dt")
	private String ntDt;

	@JsonProperty("nt_num")
	private String ntNum;

	@JsonProperty("ntty")
	private String ntty;

	@JsonProperty("p_gst")
	private String pGst;

	@JsonProperty("samt")
	private Double samt;

	@JsonProperty("txval")
	private Double txval;

	@JsonProperty("val")
	private Double val;

	@JsonProperty("itms")
	private ItemsGstr2a[] itms;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public String getChkSum() {
		return chkSum;
	}

	public void setChkSum(String chkSum) {
		this.chkSum = chkSum;
	}

	public Double getCsamt() {
		return csamt;
	}

	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}

	public Double getIamt() {
		return iamt;
	}

	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	public String getIdt() {
		return idt;
	}

	public void setIdt(String idt) {
		this.idt = idt;
	}

	public String getInum() {
		return inum;
	}

	public void setInum(String inum) {
		this.inum = inum;
	}

	public String getNtDt() {
		return ntDt;
	}

	public void setNtDt(String ntDt) {
		this.ntDt = ntDt;
	}

	public String getNtNum() {
		return ntNum;
	}

	public void setNtNum(String ntNum) {
		this.ntNum = ntNum;
	}

	public String getNtty() {
		return ntty;
	}

	public void setNtty(String ntty) {
		this.ntty = ntty;
	}

	public String getpGst() {
		return pGst;
	}

	public void setpGst(String pGst) {
		this.pGst = pGst;
	}

	public Double getSamt() {
		return samt;
	}

	public void setSamt(Double samt) {
		this.samt = samt;
	}

	public Double getTxval() {
		return txval;
	}

	public void setTxval(Double txval) {
		this.txval = txval;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public ItemsGstr2a[] getItms() {
		return itms;
	}

	public void setItms(ItemsGstr2a[] itms) {
		this.itms = itms;
	}

	@Override
	public String toString() {
		return "Nt [camt=" + camt + ", chkSum=" + chkSum + ", csamt=" + csamt + ", iamt=" + iamt + ", idt=" + idt
				+ ", inum=" + inum + ", ntDt=" + ntDt + ", ntNum=" + ntNum + ", ntty=" + ntty + ", pGst=" + pGst
				+ ", samt=" + samt + ", txval=" + txval + ", val=" + val + ", itms=" + Arrays.toString(itms) + "]";
	}
	
	

}
