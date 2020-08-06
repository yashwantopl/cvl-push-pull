/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Tr {
	
	private Double cessTaxAmt;
	
	private Double cessTaxBal;
	
	private Double cgstTaxAmt;
	
	private Double cgstTaxBal;
	
	private String desc;

	private String dt;
	
	private Double igstTaxAmt;

	private Double igstTaxBal;
	
	@JsonProperty("ref_no")
	private String refNo;
	
	@JsonProperty("ret_period")
	private String retPeriod;
	

	private Double sgstTaxAmt;
	
	private Double sgstTaxBal;
	
	@JsonProperty("tot_rng_bal")
	private Double totRngBal;
	
	@JsonProperty("tot_tr_amt")
	private Double totTrAmt;

	/**
	 * @return the cessTaxAmt
	 */
	public Double getCessTaxAmt() {
		return cessTaxAmt;
	}

	/**
	 * @param cessTaxAmt the cessTaxAmt to set
	 */
	public void setCessTaxAmt(Double cessTaxAmt) {
		this.cessTaxAmt = cessTaxAmt;
	}

	/**
	 * @return the cessTaxBal
	 */
	public Double getCessTaxBal() {
		return cessTaxBal;
	}

	/**
	 * @param cessTaxBal the cessTaxBal to set
	 */
	public void setCessTaxBal(Double cessTaxBal) {
		this.cessTaxBal = cessTaxBal;
	}

	/**
	 * @return the cgstTaxAmt
	 */
	public Double getCgstTaxAmt() {
		return cgstTaxAmt;
	}

	/**
	 * @param cgstTaxAmt the cgstTaxAmt to set
	 */
	public void setCgstTaxAmt(Double cgstTaxAmt) {
		this.cgstTaxAmt = cgstTaxAmt;
	}

	/**
	 * @return the cgstTaxBal
	 */
	public Double getCgstTaxBal() {
		return cgstTaxBal;
	}

	/**
	 * @param cgstTaxBal the cgstTaxBal to set
	 */
	public void setCgstTaxBal(Double cgstTaxBal) {
		this.cgstTaxBal = cgstTaxBal;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the dt
	 */
	public String getDt() {
		return dt;
	}

	/**
	 * @param dt the dt to set
	 */
	public void setDt(String dt) {
		this.dt = dt;
	}

	/**
	 * @return the igstTaxAmt
	 */
	public Double getIgstTaxAmt() {
		return igstTaxAmt;
	}

	/**
	 * @param igstTaxAmt the igstTaxAmt to set
	 */
	public void setIgstTaxAmt(Double igstTaxAmt) {
		this.igstTaxAmt = igstTaxAmt;
	}

	/**
	 * @return the igstTaxBal
	 */
	public Double getIgstTaxBal() {
		return igstTaxBal;
	}

	/**
	 * @param igstTaxBal the igstTaxBal to set
	 */
	public void setIgstTaxBal(Double igstTaxBal) {
		this.igstTaxBal = igstTaxBal;
	}

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
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
	 * @return the sgstTaxAmt
	 */
	public Double getSgstTaxAmt() {
		return sgstTaxAmt;
	}

	/**
	 * @param sgstTaxAmt the sgstTaxAmt to set
	 */
	public void setSgstTaxAmt(Double sgstTaxAmt) {
		this.sgstTaxAmt = sgstTaxAmt;
	}

	/**
	 * @return the sgstTaxBal
	 */
	public Double getSgstTaxBal() {
		return sgstTaxBal;
	}

	/**
	 * @param sgstTaxBal the sgstTaxBal to set
	 */
	public void setSgstTaxBal(Double sgstTaxBal) {
		this.sgstTaxBal = sgstTaxBal;
	}

	/**
	 * @return the totRngBal
	 */
	public Double getTotRngBal() {
		return totRngBal;
	}

	/**
	 * @param totRngBal the totRngBal to set
	 */
	public void setTotRngBal(Double totRngBal) {
		this.totRngBal = totRngBal;
	}

	/**
	 * @return the totTrAmt
	 */
	public Double getTotTrAmt() {
		return totTrAmt;
	}

	/**
	 * @param totTrAmt the totTrAmt to set
	 */
	public void setTotTrAmt(Double totTrAmt) {
		this.totTrAmt = totTrAmt;
	}
	
	
	


}

