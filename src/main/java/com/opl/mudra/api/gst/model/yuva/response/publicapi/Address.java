/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.publicapi;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bnm;
	
	private String st;
	
	private String loc;
	
	private String bno;
	
	private String stcd;
	
	private String flno;
	
	private String lt;
	
	private String lg;

	private String pncd;

	/**
	 * @return the bnm
	 */
	public String getBnm() {
		return bnm;
	}

	/**
	 * @param bnm the bnm to set
	 */
	public void setBnm(String bnm) {
		this.bnm = bnm;
	}

	/**
	 * @return the st
	 */
	public String getSt() {
		return st;
	}

	/**
	 * @param st the st to set
	 */
	public void setSt(String st) {
		this.st = st;
	}

	/**
	 * @return the loc
	 */
	public String getLoc() {
		return loc;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(String loc) {
		this.loc = loc;
	}

	/**
	 * @return the bno
	 */
	public String getBno() {
		return bno;
	}

	/**
	 * @param bno the bno to set
	 */
	public void setBno(String bno) {
		this.bno = bno;
	}

	/**
	 * @return the stcd
	 */
	public String getStcd() {
		return stcd;
	}

	/**
	 * @param stcd the stcd to set
	 */
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	/**
	 * @return the flno
	 */
	public String getFlno() {
		return flno;
	}

	/**
	 * @param flno the flno to set
	 */
	public void setFlno(String flno) {
		this.flno = flno;
	}

	/**
	 * @return the lt
	 */
	public String getLt() {
		return lt;
	}

	/**
	 * @param lt the lt to set
	 */
	public void setLt(String lt) {
		this.lt = lt;
	}

	/**
	 * @return the lg
	 */
	public String getLg() {
		return lg;
	}

	/**
	 * @param lg the lg to set
	 */
	public void setLg(String lg) {
		this.lg = lg;
	}

	/**
	 * @return the pncd
	 */
	public String getPncd() {
		return pncd;
	}

	/**
	 * @param pncd the pncd to set
	 */
	public void setPncd(String pncd) {
		this.pncd = pncd;
	}


	public Address() {
		this.bnm = "";
		this.st = "";
		this.loc = "";
		this.bno = "";
		this.stcd = "";
		this.flno ="";
		this.lt = "";
		this.lg = "";
		this.pncd ="";
	}
}
