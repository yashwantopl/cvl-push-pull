package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class ItfreeDetails {
	
	private Double camt;
	
	private Double csamt;
	
	private Double iamt;
	
	private Double samt;

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public Double getCsamt() {
		return csamt;
	}

	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}

	public Double getIamt() {
		return iamt;
	}

	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	public Double getSamt() {
		return samt;
	}

	public void setSamt(Double samt) {
		this.samt = samt;
	}
	
	

}
