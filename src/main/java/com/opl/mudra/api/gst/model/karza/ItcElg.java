package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class ItcElg {
	
	@JsonProperty("itc_net")
	private ItcNet itcNet;
	
	@JsonProperty("itc_rev")
	private ItcRev[] itcRev;

	@JsonProperty("itc_inelg")
	private ItcInelg[] itcInelg;

	@JsonProperty("itc_avl")
	private ItcAvl[] itcAvl;

	public ItcNet getItcNet() {
		return itcNet;
	}

	public void setItcNet(ItcNet itcNet) {
		this.itcNet = itcNet;
	}

	public ItcRev[] getItcRev() {
		return itcRev;
	}

	public void setItcRev(ItcRev[] itcRev) {
		this.itcRev = itcRev;
	}

	public ItcInelg[] getItcInelg() {
		return itcInelg;
	}

	public void setItcInelg(ItcInelg[] itcInelg) {
		this.itcInelg = itcInelg;
	}

	public ItcAvl[] getItcAvl() {
		return itcAvl;
	}

	public void setItcAvl(ItcAvl[] itcAvl) {
		this.itcAvl = itcAvl;
	}
	
	
	

}
