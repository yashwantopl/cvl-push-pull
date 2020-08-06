package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Total {

	@JsonProperty("state_wise")
	private StateWise[] stateWise;

	@JsonProperty("cpty_sum")
	private CptySum[] cptySum;

	@JsonProperty("hsn_wise")
	private HsnWise[] hsnWise;

	@JsonProperty("sec_sum")
	private SecSum[] secSum;

	public StateWise[] getStateWise() {
		return stateWise;
	}

	public void setStateWise(StateWise[] stateWise) {
		this.stateWise = stateWise;
	}

	public CptySum[] getCptySum() {
		return cptySum;
	}

	public void setCptySum(CptySum[] cptySum) {
		this.cptySum = cptySum;
	}

	public HsnWise[] getHsnWise() {
		return hsnWise;
	}

	public void setHsnWise(HsnWise[] hsnWise) {
		this.hsnWise = hsnWise;
	}

	public SecSum[] getSecSum() {
		return secSum;
	}

	public void setSecSum(SecSum[] secSum) {
		this.secSum = secSum;
	}

	
	
}
