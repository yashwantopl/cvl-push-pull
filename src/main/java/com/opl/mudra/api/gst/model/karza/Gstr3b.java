package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Gstr3b {
	
	@JsonProperty("total")
	private Gstr3Total gstr3Total;

	@JsonProperty("details")
	private Gstr3Details[] gstr3details;

	public Gstr3Total getGstr3Total() {
		return gstr3Total;
	}

	public void setGstr3Total(Gstr3Total gstr3Total) {
		this.gstr3Total = gstr3Total;
	}

	public Gstr3Details[] getGstr3details() {
		return gstr3details;
	}

	public void setGstr3details(Gstr3Details[] gstr3details) {
		this.gstr3details = gstr3details;
	}


	
	

}
