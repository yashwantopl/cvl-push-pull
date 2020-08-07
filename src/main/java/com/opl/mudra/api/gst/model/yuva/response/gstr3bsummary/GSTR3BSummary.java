/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.GSTDataResponse;

/**
 * @author sanket
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GSTR3BSummary extends GSTDataResponse {
	
	private String gstin;
	
	@JsonProperty("ret_period")
	private String retPeriod;
	
	@JsonProperty("sup_details")
	private SupDetails supDetails;
	
	@JsonProperty("tx_pmt")
	private TxPmt txpmt;

	@JsonProperty("intr_ltfee")
	private IntrLtFee intrLtfee;
	
	@JsonProperty("inter_sup")
	private InterSup interSup;

	@JsonProperty("inward_sup")
	private InwardSup inwardSup;
	
	@JsonProperty("itc_elg")
	private ItcElg itcElg;
	
	
	
	
	
	/**
	 * @return the itcElg
	 */
	public ItcElg getItcElg() {
		return itcElg;
	}

	/**
	 * @param itcElg the itcElg to set
	 */
	public void setItcElg(ItcElg itcElg) {
		this.itcElg = itcElg;
	}

	/**
	 * @return the inwardSup
	 */
	public InwardSup getInwardSup() {
		return inwardSup;
	}

	/**
	 * @param inwardSup the inwardSup to set
	 */
	public void setInwardSup(InwardSup inwardSup) {
		this.inwardSup = inwardSup;
	}

	/**
	 * @return the interSup
	 */
	public InterSup getInterSup() {
		return interSup;
	}

	/**
	 * @param interSup the interSup to set
	 */
	public void setInterSup(InterSup interSup) {
		this.interSup = interSup;
	}

	/**
	 * @return the txpmt
	 */
	public TxPmt getTxpmt() {
		return txpmt;
	}

	/**
	 * @param txpmt the txpmt to set
	 */
	public void setTxpmt(TxPmt txpmt) {
		this.txpmt = txpmt;
	}

	/**
	 * @return the intrLtfee
	 */
	public IntrLtFee getIntrLtfee() {
		return intrLtfee;
	}

	/**
	 * @param intrLtfee the intrLtfee to set
	 */
	public void setIntrLtfee(IntrLtFee intrLtfee) {
		this.intrLtfee = intrLtfee;
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
	 * @return the retPeriod
	 */
	public String getRetPeriod() {
		return retPeriod;
	}

	/**
	 * @param retPeriod the retPeriod to set
	 */
	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	/**
	 * @return the supDetails
	 */
	public SupDetails getSupDetails() {
		return supDetails;
	}

	/**
	 * @param supDetails the supDetails to set
	 */
	public void setSupDetails(SupDetails supDetails) {
		this.supDetails = supDetails;
	}

	public GSTR3BSummary() {
		this.gstin = "";
		this.retPeriod = null;
		this.supDetails = new SupDetails();
		this.txpmt = new TxPmt();
		this.intrLtfee = new IntrLtFee();
		this.interSup = new InterSup();
		this.inwardSup = new InwardSup();
		this.itcElg = new ItcElg();
	}
}
