/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.publicapi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class TaxPayersResposne  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ctb;
	private String ctj;
	private String cxdt;
	private String dty;
	private String gstin;
	private String lgnm;
	private String rgdt;
	private String stj;
	private String sts;
	private String tradeNam;
	
	private List<String> nba;
	
	private List<AdAddress> adadr;

	private AdAddress pradr;
	private String stjCd;
	private String lstupdt;
	private String ctjCd;





	/**
	 * @return the nba
	 */
	public List<String> getNba() {
		return nba;
	}
	/**
	 * @param nba the nba to set
	 */
	public void setNba(List<String> nba) {
		this.nba = nba;
	}
	/**
	 * @return the adadr
	 */
	public List<AdAddress> getAdadr() {
		return adadr;
	}
	/**
	 * @param adadr the adadr to set
	 */
	public void setAdadr(List<AdAddress> adadr) {
		this.adadr = adadr;
	}
	/**
	 * @return the pradr
	 */
	public AdAddress getPradr() {
		return pradr;
	}
	/**
	 * @param pradr the pradr to set
	 */
	public void setPradr(AdAddress pradr) {
		this.pradr = pradr;
	}
	public String getTradeNam() {
		return tradeNam;
	}
	public void setTradeNam(String tradeNam) {
		this.tradeNam = tradeNam;
	}
	/**
	 * @return the ctb
	 */
	public String getCtb() {
		return ctb;
	}
	/**
	 * @param ctb the ctb to set
	 */
	public void setCtb(String ctb) {
		this.ctb = ctb;
	}
	/**
	 * @return the ctj
	 */
	public String getCtj() {
		return ctj;
	}
	/**
	 * @param ctj the ctj to set
	 */
	public void setCtj(String ctj) {
		this.ctj = ctj;
	}
	/**
	 * @return the cxdt
	 */
	public String getCxdt() {
		return cxdt;
	}
	/**
	 * @param cxdt the cxdt to set
	 */
	public void setCxdt(String cxdt) {
		this.cxdt = cxdt;
	}
	/**
	 * @return the dty
	 */
	public String getDty() {
		return dty;
	}
	/**
	 * @param dty the dty to set
	 */
	public void setDty(String dty) {
		this.dty = dty;
	}
	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}
	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	/**
	 * @return the lgnm
	 */
	public String getLgnm() {
		return lgnm;
	}
	/**
	 * @param lgnm the lgnm to set
	 */
	public void setLgnm(String lgnm) {
		this.lgnm = lgnm;
	}
	/**
	 * @return the rgdt
	 */
	public String getRgdt() {
		return rgdt;
	}
	/**
	 * @param rgdt the rgdt to set
	 */
	public void setRgdt(String rgdt) {
		this.rgdt = rgdt;
	}
	/**
	 * @return the stj
	 */
	public String getStj() {
		return stj;
	}
	/**
	 * @param stj the stj to set
	 */
	public void setStj(String stj) {
		this.stj = stj;
	}
	/**
	 * @return the sts
	 */
	public String getSts() {
		return sts;
	}
	/**
	 * @param sts the sts to set
	 */
	public void setSts(String sts) {
		this.sts = sts;
	}

	

	public String getStjCd() {
		return stjCd;
	}
	public void setStjCd(String stjCd) {
		this.stjCd = stjCd;
	}
	public String getLstupdt() {
		return lstupdt;
	}
	public void setLstupdt(String lstupdt) {
		this.lstupdt = lstupdt;
	}
	public String getCtjCd() {
		return ctjCd;
	}
	public void setCtjCd(String ctjCd) {
		this.ctjCd = ctjCd;
	}
	public TaxPayersResposne() {
		this.ctb = "";
		this.ctj = "";
		this.cxdt = "";
		this.dty = "";
		this.gstin = "";
		this.lgnm = "";
		this.rgdt = "";
		this.stj = "";
		this.sts = "";
		this.tradeNam = "";
		this.nba = new ArrayList<>();
		this.adadr = new ArrayList<>();
		this.pradr = new AdAddress();
		this.tradeNam = "";
		this.tradeNam = "";
		this.tradeNam = "";
	}
}
