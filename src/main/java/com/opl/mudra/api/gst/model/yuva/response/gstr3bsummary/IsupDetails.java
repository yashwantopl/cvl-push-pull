package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class IsupDetails {

	@JsonProperty("inter")
	private Double inter;

	@JsonProperty("intra")
	private Double intra;

	
	@JsonProperty("ty")
	private String ty;


	public Double getInter() {
		return inter;
	}


	public void setInter(Double inter) {
		this.inter = inter;
	}


	public Double getIntra() {
		return intra;
	}


	public void setIntra(Double intra) {
		this.intra = intra;
	}


	public String getTy() {
		return ty;
	}


	public void setTy(String ty) {
		this.ty = ty;
	}


	public IsupDetails() {
		this.inter = 0.0;
		this.intra = 0.0;
		this.ty = "";
	}
}
