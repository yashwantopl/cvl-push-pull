package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class HsnWise {
	
	@JsonProperty("hsn_sc")
	private String hsnSc;
	
	@JsonProperty("hsn_desc")
	private Double hsnDesc;
	
	@JsonProperty("desc_wise")
	private DescWise[] descWise;
	
	@JsonProperty("transactions")
	private HsnWiseTransactions hsnWiseTransactons;

	public String getHsnSc() {
		return hsnSc;
	}

	public void setHsnSc(String hsnSc) {
		this.hsnSc = hsnSc;
	}

	public Double getHsnDesc() {
		return hsnDesc;
	}

	public void setHsnDesc(Double hsnDesc) {
		this.hsnDesc = hsnDesc;
	}

	public DescWise[] getDescWise() {
		return descWise;
	}

	public void setDescWise(DescWise[] descWise) {
		this.descWise = descWise;
	}

	public HsnWiseTransactions getHsnWiseTransactons() {
		return hsnWiseTransactons;
	}

	public void setHsnWiseTransactons(HsnWiseTransactions hsnWiseTransactons) {
		this.hsnWiseTransactons = hsnWiseTransactons;
	}
	
	

}
