package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author usertwelve
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Gstr3Details {

	@JsonProperty("ret_period")
	private String retPeriod;

	@JsonProperty("intr_ltfee")
	private IntrItfee intrItfee;

	@JsonProperty("inward_sup")
	private InwardSup inwardSup;

	@JsonProperty("inter_sup")
	private InterSup interSup;

	@JsonProperty("itc_elg")
	private ItcElg itcElg;

	@JsonProperty("sup_details")
	private SupDetails supDetails;

	@JsonProperty("tt_val")
	private TtVal ttVal;



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

	private Qn qn;

	public InwardSup getInwardSup() {
		return inwardSup;
	}

	public void setInwardSup(InwardSup inwardSup) {
		this.inwardSup = inwardSup;
	}

	public Qn getQn() {
		return qn;
	}

	public void setQn(Qn qn) {
		this.qn = qn;
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public IntrItfee getIntrItfee() {
		return intrItfee;
	}

	public void setIntrItfee(IntrItfee intrItfee) {
		this.intrItfee = intrItfee;
	}

	public ItcElg getItcElg() {
		return itcElg;
	}

	public void setItcElg(ItcElg itcElg) {
		this.itcElg = itcElg;
	}

	public SupDetails getSupDetails() {
		return supDetails;
	}

	public void setSupDetails(SupDetails supDetails) {
		this.supDetails = supDetails;
	}

	public TtVal getTtVal() {
		return ttVal;
	}

	public void setTtVal(TtVal ttVal) {
		this.ttVal = ttVal;
	}

}
