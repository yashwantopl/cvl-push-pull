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
public class Pditc {
	
	@JsonProperty("cs_pdcs")
	private Double csPdcs;
	
	@JsonProperty("c_pdc")
	private Double cPdc;
	
	@JsonProperty("c_pdi")
	private Double cPdi;
	
	@JsonProperty("i_pdc")
	private Double iPdc;
	
	@JsonProperty("i_pdi")
	private Double iPdi;
	
	@JsonProperty("i_pds")
	private Double iPds;
	
	@JsonProperty("liab_ldg_id")
	private Double liabLdgId;
	
	@JsonProperty("s_pdi")
	private Double sPdi;
	
	@JsonProperty("s_pds")
	private Double sPds;
	
	@JsonProperty("trans_typ")
	private Double transTyp;

	/**
	 * @return the csPdcs
	 */
	public Double getCsPdcs() {
		return csPdcs;
	}

	/**
	 * @param csPdcs the csPdcs to set
	 */
	public void setCsPdcs(Double csPdcs) {
		this.csPdcs = csPdcs;
	}

	/**
	 * @return the cPdc
	 */
	public Double getcPdc() {
		return cPdc;
	}

	/**
	 * @param cPdc the cPdc to set
	 */
	public void setcPdc(Double cPdc) {
		this.cPdc = cPdc;
	}

	/**
	 * @return the cPdi
	 */
	public Double getcPdi() {
		return cPdi;
	}

	/**
	 * @param cPdi the cPdi to set
	 */
	public void setcPdi(Double cPdi) {
		this.cPdi = cPdi;
	}

	/**
	 * @return the iPdc
	 */
	public Double getiPdc() {
		return iPdc;
	}

	/**
	 * @param iPdc the iPdc to set
	 */
	public void setiPdc(Double iPdc) {
		this.iPdc = iPdc;
	}

	/**
	 * @return the iPdi
	 */
	public Double getiPdi() {
		return iPdi;
	}

	/**
	 * @param iPdi the iPdi to set
	 */
	public void setiPdi(Double iPdi) {
		this.iPdi = iPdi;
	}

	/**
	 * @return the iPds
	 */
	public Double getiPds() {
		return iPds;
	}

	/**
	 * @param iPds the iPds to set
	 */
	public void setiPds(Double iPds) {
		this.iPds = iPds;
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
	 * @return the sPdi
	 */
	public Double getsPdi() {
		return sPdi;
	}

	/**
	 * @param sPdi the sPdi to set
	 */
	public void setsPdi(Double sPdi) {
		this.sPdi = sPdi;
	}

	/**
	 * @return the sPds
	 */
	public Double getsPds() {
		return sPds;
	}

	/**
	 * @param sPds the sPds to set
	 */
	public void setsPds(Double sPds) {
		this.sPds = sPds;
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


	public Pditc() {
		this.csPdcs = 0.0;
		this.cPdc = 0.0;
		this.cPdi = 0.0;
		this.iPdc = 0.0;
		this.iPdi = 0.0;
		this.iPds = 0.0;
		this.liabLdgId = 0.0;
		this.sPdi = 0.0;
		this.sPds = 0.0;
		this.transTyp = 0.0;
	}
}
