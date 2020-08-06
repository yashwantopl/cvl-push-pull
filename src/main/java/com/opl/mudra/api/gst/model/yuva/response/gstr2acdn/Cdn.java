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
public class Cdn implements Serializable{
	
	private String cfs;
	
	private String ctin;
	
	private Nt[] nt;

	/**
	 * @return the cfs
	 */
	public String getCfs() {
		return cfs;
	}

	/**
	 * @param cfs the cfs to set
	 */
	public void setCfs(String cfs) {
		this.cfs = cfs;
	}

	/**
	 * @return the ctin
	 */
	public String getCtin() {
		return ctin;
	}

	/**
	 * @param ctin the ctin to set
	 */
	public void setCtin(String ctin) {
		this.ctin = ctin;
	}

	/**
	 * @return the nt
	 */
	public Nt[] getNt() {
		return nt;
	}

	/**
	 * @param nt the nt to set
	 */
	public void setNt(Nt[] nt) {
		this.nt = nt;
	}


	public Cdn() {
		this.cfs ="";
		this.ctin = "";
		this.nt = new Nt[0];
	}
}
