/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TxPy {
	
	@JsonProperty("tran_desc")
	private String tranDesc;
	
	@JsonProperty("liab_ldg_id")
	private Double liabldgId;
	
	@JsonProperty("trans_typ")
	private Double transTyp;
	
	
	private IGST igst;

	private CGST cgst;

	private SGST sgst;

	private Cess cess;

	/**
	 * @return the tranDesc
	 */
	public String getTranDesc() {
		return tranDesc;
	}

	/**
	 * @param tranDesc the tranDesc to set
	 */
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}

	/**
	 * @return the liabldgId
	 */
	public Double getLiabldgId() {
		return liabldgId;
	}

	/**
	 * @param liabldgId the liabldgId to set
	 */
	public void setLiabldgId(Double liabldgId) {
		this.liabldgId = liabldgId;
	}

	/**
	 * @return the transTyp
	 */
	public Double getTransTyp() {
		return transTyp;
	}

	/**
	 * @param transTyp the transTyp to set
	 */
	public void setTransTyp(Double transTyp) {
		this.transTyp = transTyp;
	}

	/**
	 * @return the igst
	 */
	public IGST getIgst() {
		return igst;
	}

	/**
	 * @param igst the igst to set
	 */
	public void setIgst(IGST igst) {
		this.igst = igst;
	}

	/**
	 * @return the cgst
	 */
	public CGST getCgst() {
		return cgst;
	}

	/**
	 * @param cgst the cgst to set
	 */
	public void setCgst(CGST cgst) {
		this.cgst = cgst;
	}

	/**
	 * @return the sgst
	 */
	public SGST getSgst() {
		return sgst;
	}

	/**
	 * @param sgst the sgst to set
	 */
	public void setSgst(SGST sgst) {
		this.sgst = sgst;
	}

	/**
	 * @return the cess
	 */
	public Cess getCess() {
		return cess;
	}

	/**
	 * @param cess the cess to set
	 */
	public void setCess(Cess cess) {
		this.cess = cess;
	}


	public TxPy() {
		this.tranDesc = "";
		this.liabldgId = 0.0;
		this.transTyp = 0.0;
		this.igst = new IGST();
		this.cgst = new CGST();
		this.sgst = new SGST();
		this.cess = new Cess();
	}
}
