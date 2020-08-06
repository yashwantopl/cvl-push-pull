package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CCRHitDetails {

	@JsonProperty("Name")
	private String name;

	@JsonProperty("RTL_Hit")
	private String rtl_Hit;

	@JsonProperty("MFI_Hit")
	private String mfi_Hit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRtl_Hit() {
		return rtl_Hit;
	}

	public void setRtl_Hit(String rtl_Hit) {
		this.rtl_Hit = rtl_Hit;
	}

	public String getMfi_Hit() {
		return mfi_Hit;
	}

	public void setMfi_Hit(String mfi_Hit) {
		this.mfi_Hit = mfi_Hit;
	}

}
