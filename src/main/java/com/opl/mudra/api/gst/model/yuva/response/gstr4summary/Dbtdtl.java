/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay.darji
 *
 */
public class Dbtdtl {

	@JsonProperty("trandate")
	private String tranDate;

	@JsonProperty("trancd")
	private Integer tranCd;
	
	@JsonProperty("debit_id")
	private String debitId;
	
	@JsonProperty("liab_id")
	private Integer liabId;
	
	private Sgst sgst;
	private Cgst cgst;
	private Cess cess;
	private Igst igst;
	public String getTranDate() {
		return tranDate;
	}
	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getDebitId() {
		return debitId;
	}
	public void setDebitId(String debitId) {
		this.debitId = debitId;
	}

	public Sgst getSgst() {
		return sgst;
	}
	public void setSgst(Sgst sgst) {
		this.sgst = sgst;
	}
	public Cgst getCgst() {
		return cgst;
	}
	public void setCgst(Cgst cgst) {
		this.cgst = cgst;
	}
	public Cess getCess() {
		return cess;
	}
	public void setCess(Cess cess) {
		this.cess = cess;
	}
	public Igst getIgst() {
		return igst;
	}
	public void setIgst(Igst igst) {
		this.igst = igst;
	}
	public Integer getTranCd() {
		return tranCd;
	}
	public void setTranCd(Integer tranCd) {
		this.tranCd = tranCd;
	}
	public Integer getLiabId() {
		return liabId;
	}
	public void setLiabId(Integer liabId) {
		this.liabId = liabId;
	}
	
}
