package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Top3Sup {

	@JsonProperty("name")
	private String name;

	@JsonProperty("pan")
	private String pan;

	@JsonProperty("share")
	private Double share;

	@JsonProperty("ttl_rec")
	private Double ttlRec;

	@JsonProperty("ttl_tax")
	private Double ttlTec;

	@JsonProperty("ttl_val")
	private Double ttlVal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Double getShare() {
		return share;
	}

	public void setShare(Double share) {
		this.share = share;
	}

	public Double getTtlRec() {
		return ttlRec;
	}

	public void setTtlRec(Double ttlRec) {
		this.ttlRec = ttlRec;
	}

	public Double getTtlTec() {
		return ttlTec;
	}

	public void setTtlTec(Double ttlTec) {
		this.ttlTec = ttlTec;
	}

	public Double getTtlVal() {
		return ttlVal;
	}

	public void setTtlVal(Double ttlVal) {
		this.ttlVal = ttlVal;
	}

	@Override
	public String toString() {
		return "Top3Sup [name=" + name + ", pan=" + pan + ", share=" + share + ", ttlRec=" + ttlRec + ", ttlTec="
				+ ttlTec + ", ttlVal=" + ttlVal + "]";
	}
	
	

}
