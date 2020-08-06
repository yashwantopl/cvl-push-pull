/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Inv {

	private String cflag;
	private String chksum;
	private String flag;
	private String idt;
	private String inum;
	private String inv_typ;
	private String pos;
	private String rchrg;
	private String updby;
	private Double val;
	
	private Itms[] itms;

	/**
	 * @return the cflag
	 */
	public String getCflag() {
		return cflag;
	}

	/**
	 * @param cflag the cflag to set
	 */
	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

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
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
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
	 * @return the inv_typ
	 */
	public String getInv_typ() {
		return inv_typ;
	}

	/**
	 * @param inv_typ the inv_typ to set
	 */
	public void setInv_typ(String inv_typ) {
		this.inv_typ = inv_typ;
	}

	/**
	 * @return the pos
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}

	/**
	 * @return the rchrg
	 */
	public String getRchrg() {
		return rchrg;
	}

	/**
	 * @param rchrg the rchrg to set
	 */
	public void setRchrg(String rchrg) {
		this.rchrg = rchrg;
	}

	/**
	 * @return the updby
	 */
	public String getUpdby() {
		return updby;
	}

	/**
	 * @param updby the updby to set
	 */
	public void setUpdby(String updby) {
		this.updby = updby;
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

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public Inv() {
		this.cflag = "";
		this.chksum = "";
		this.flag = "";
		this.idt = "";
		this.inum = "";
		this.inv_typ = "";
		this.pos = "";
		this.rchrg = "";
		this.updby = "";
		this.val = 0.0;
		this.itms = new Itms[0];
	}
}
