/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr2acdn;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Nt implements Serializable{

	
	private String chksum;
	private Double diff_percent;
	private String idt;
	private String inum;
	private String ntty;
	private String nt_dt;
	private String nt_num;
	private String p_gst;
	private Double val;
	
	private Itms[] itms;

	/**
	 * @return the chksum
	 */
	public String getChksum() {
		return chksum;
	}

	/**
	 * @param chksum the chksum to set
	 */
	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	/**
	 * @return the diff_percent
	 */
	public Double getDiff_percent() {
		return diff_percent;
	}

	/**
	 * @param diff_percent the diff_percent to set
	 */
	public void setDiff_percent(Double diff_percent) {
		this.diff_percent = diff_percent;
	}

	/**
	 * @return the idt
	 */
	public String getIdt() {
		return idt;
	}

	/**
	 * @param idt the idt to set
	 */
	public void setIdt(String idt) {
		this.idt = idt;
	}

	/**
	 * @return the inum
	 */
	public String getInum() {
		return inum;
	}

	/**
	 * @param inum the inum to set
	 */
	public void setInum(String inum) {
		this.inum = inum;
	}

	/**
	 * @return the ntty
	 */
	public String getNtty() {
		return ntty;
	}

	/**
	 * @param ntty the ntty to set
	 */
	public void setNtty(String ntty) {
		this.ntty = ntty;
	}

	/**
	 * @return the nt_dt
	 */
	public String getNt_dt() {
		return nt_dt;
	}

	/**
	 * @param nt_dt the nt_dt to set
	 */
	public void setNt_dt(String nt_dt) {
		this.nt_dt = nt_dt;
	}

	/**
	 * @return the nt_num
	 */
	public String getNt_num() {
		return nt_num;
	}

	/**
	 * @param nt_num the nt_num to set
	 */
	public void setNt_num(String nt_num) {
		this.nt_num = nt_num;
	}

	/**
	 * @return the p_gst
	 */
	public String getP_gst() {
		return p_gst;
	}

	/**
	 * @param p_gst the p_gst to set
	 */
	public void setP_gst(String p_gst) {
		this.p_gst = p_gst;
	}

	/**
	 * @return the val
	 */
	public Double getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(Double val) {
		this.val = val;
	}

	/**
	 * @return the itms
	 */
	public Itms[] getItms() {
		return itms;
	}

	/**
	 * @param itms the itms to set
	 */
	public void setItms(Itms[] itms) {
		this.itms = itms;
	}

	public Nt() {
		this.chksum = "";
		this.diff_percent = 0.0;
		this.idt = "";
		this.inum = "";
		this.ntty = "";
		this.nt_dt = "";
		this.nt_num ="";
		this.p_gst = "";
		this.val = 0.0;
		this.itms = new Itms[0];
	}
}
