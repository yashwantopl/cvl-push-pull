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
public class PdCash {
	
	private Double cpd;
	
	private Double cspd;
	
	@JsonProperty("cs_intrpd")
	private Double csIntrpd;
	
	@JsonProperty("cs_lfeepd")
	private Double csLfeepd;
	
	@JsonProperty("c_intrpd")
	private Double cIntrpd;
	
	@JsonProperty("c_lfeepd")
	private Double cLfeepd;
	
	private Double ipd;
	
	@JsonProperty("i_intrpd")
	private Double iIntrpd;
	
	@JsonProperty("i_lfeepd")
	private Double iLfeepd;
	
	@JsonProperty("liab_ldg_id")
	private Double liabLdgId;
	
	private Double spd;
	
	@JsonProperty("s_intrpd")
	private Double sIntrpd;
	
	@JsonProperty("s_lfeepd")
	private Double sLfeepd;
	
	@JsonProperty("trans_typ")
	private Double transTyp;

	/**
	 * @return the cpd
	 */
	public Double getCpd() {
		return cpd;
	}

	/**
	 * @param cpd the cpd to set
	 */
	public void setCpd(Double cpd) {
		this.cpd = cpd;
	}

	/**
	 * @return the cspd
	 */
	public Double getCspd() {
		return cspd;
	}

	/**
	 * @param cspd the cspd to set
	 */
	public void setCspd(Double cspd) {
		this.cspd = cspd;
	}

	/**
	 * @return the csIntrpd
	 */
	public Double getCsIntrpd() {
		return csIntrpd;
	}

	/**
	 * @param csIntrpd the csIntrpd to set
	 */
	public void setCsIntrpd(Double csIntrpd) {
		this.csIntrpd = csIntrpd;
	}

	/**
	 * @return the csLfeepd
	 */
	public Double getCsLfeepd() {
		return csLfeepd;
	}

	/**
	 * @param csLfeepd the csLfeepd to set
	 */
	public void setCsLfeepd(Double csLfeepd) {
		this.csLfeepd = csLfeepd;
	}

	/**
	 * @return the cIntrpd
	 */
	public Double getcIntrpd() {
		return cIntrpd;
	}

	/**
	 * @param cIntrpd the cIntrpd to set
	 */
	public void setcIntrpd(Double cIntrpd) {
		this.cIntrpd = cIntrpd;
	}

	/**
	 * @return the cLfeepd
	 */
	public Double getcLfeepd() {
		return cLfeepd;
	}

	/**
	 * @param cLfeepd the cLfeepd to set
	 */
	public void setcLfeepd(Double cLfeepd) {
		this.cLfeepd = cLfeepd;
	}

	/**
	 * @return the ipd
	 */
	public Double getIpd() {
		return ipd;
	}

	/**
	 * @param ipd the ipd to set
	 */
	public void setIpd(Double ipd) {
		this.ipd = ipd;
	}

	/**
	 * @return the iIntrpd
	 */
	public Double getiIntrpd() {
		return iIntrpd;
	}

	/**
	 * @param iIntrpd the iIntrpd to set
	 */
	public void setiIntrpd(Double iIntrpd) {
		this.iIntrpd = iIntrpd;
	}

	/**
	 * @return the iLfeepd
	 */
	public Double getiLfeepd() {
		return iLfeepd;
	}

	/**
	 * @param iLfeepd the iLfeepd to set
	 */
	public void setiLfeepd(Double iLfeepd) {
		this.iLfeepd = iLfeepd;
	}

	/**
	 * @return the liabLdgId
	 */
	public Double getLiabLdgId() {
		return liabLdgId;
	}

	/**
	 * @param liabLdgId the liabLdgId to set
	 */
	public void setLiabLdgId(Double liabLdgId) {
		this.liabLdgId = liabLdgId;
	}

	/**
	 * @return the spd
	 */
	public Double getSpd() {
		return spd;
	}

	/**
	 * @param spd the spd to set
	 */
	public void setSpd(Double spd) {
		this.spd = spd;
	}

	/**
	 * @return the sIntrpd
	 */
	public Double getsIntrpd() {
		return sIntrpd;
	}

	/**
	 * @param sIntrpd the sIntrpd to set
	 */
	public void setsIntrpd(Double sIntrpd) {
		this.sIntrpd = sIntrpd;
	}

	/**
	 * @return the sLfeepd
	 */
	public Double getsLfeepd() {
		return sLfeepd;
	}

	/**
	 * @param sLfeepd the sLfeepd to set
	 */
	public void setsLfeepd(Double sLfeepd) {
		this.sLfeepd = sLfeepd;
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


	public PdCash() {
		this.cpd = 0.0;
		this.cspd = 0.0;
		this.csIntrpd = 0.0;
		this.csLfeepd = 0.0;
		this.cIntrpd = 0.0;
		this.cLfeepd = 0.0;
		this.ipd = 0.0;
		this.iIntrpd = 0.0;
		this.iLfeepd = 0.0;
		this.liabLdgId = 0.0;
		this.spd = 0.0;
		this.sIntrpd = 0.0;
		this.sLfeepd = 0.0;
		this.transTyp = 0.0;
	}
}
