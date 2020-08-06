package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class InvGstr2A {

	@JsonProperty("camt")
	private Double camt;
	
	@JsonProperty("chksum")
	private String chksum;
	
	@JsonProperty("csamt")
	private Double csamt;
	
	@JsonProperty("iamt")
	private Double iamt;
	
	@JsonProperty("idt")
	private String idt;
	
	@JsonProperty("inum")
	private String inum;
	
	@JsonProperty("inv_typ")
	private String invTyp;
	
	@JsonProperty("itms")
	private ItemsGstr2a[] itms;
	
	@JsonProperty("pos")
	private String pos;
	
	@JsonProperty("rchrg")
	private String rchrg;
	
	@JsonProperty("samt")
	private Double samt;
	
	@JsonProperty("txval")
	private Double txval;
	
	@JsonProperty("val")
	private Double val;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
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

	public String getInvTyp() {
		return invTyp;
	}

	public void setInvTyp(String invTyp) {
		this.invTyp = invTyp;
	}

	 

	public ItemsGstr2a[] getItms() {
		return itms;
	}

	public void setItms(ItemsGstr2a[] itms) {
		this.itms = itms;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getRchrg() {
		return rchrg;
	}

	public void setRchrg(String rchrg) {
		this.rchrg = rchrg;
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

	@Override
	public String toString() {
		return "InvGstr2A [camt=" + camt + ", chksum=" + chksum + ", csamt=" + csamt + ", iamt=" + iamt + ", idt=" + idt
				+ ", inum=" + inum + ", invTyp=" + invTyp + ", itms=" + itms + ", pos=" + pos + ", rchrg=" + rchrg
				+ ", samt=" + samt + ", txval=" + txval + ", val=" + val + "]";
	}
	 
	



}
