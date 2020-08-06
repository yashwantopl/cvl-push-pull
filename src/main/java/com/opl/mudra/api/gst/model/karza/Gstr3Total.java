package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Gstr3Total {
	
	@JsonProperty("sup_details")
	private SupDetails supDetails;
	
	@JsonProperty("tt_val")
	private TtVal ttVal;
	
	@JsonProperty("intr_itfee")
	private IntrItfee intrItfee;
	
	@JsonProperty("inter_sup")
	private InterSup interSup;
	
	@JsonProperty("itc_elg")
	private ItcElg itcElg;

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

	public IntrItfee getIntrItfee() {
		return intrItfee;
	}

	public void setIntrItfee(IntrItfee intrItfee) {
		this.intrItfee = intrItfee;
	}

	public InterSup getInterSup() {
		return interSup;
	}

	public void setInterSup(InterSup interSup) {
		this.interSup = interSup;
	}

	public ItcElg getItcElg() {
		return itcElg;
	}

	public void setItcElg(ItcElg itcElg) {
		this.itcElg = itcElg;
	}
	
	
	
	

}
