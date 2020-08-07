package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SupDetails {

	@JsonProperty("osup_det")
	private OsupDet osupDet;

	@JsonProperty("osup_nongst")
	private OsupNongst osupNongst;

	@JsonProperty("osup_nil_exmp")
	private OsupNilExmp osupNilExmp;

	@JsonProperty("isup_rev")
	private IsupRev isupRev;

	@JsonProperty("osup_zero")
	private OsupZero osupZero;

	public OsupDet getOsupDet() {
		return osupDet;
	}

	public void setOsupDet(OsupDet osupDet) {
		this.osupDet = osupDet;
	}

	public OsupNongst getOsupNongst() {
		return osupNongst;
	}

	public void setOsupNongst(OsupNongst osupNongst) {
		this.osupNongst = osupNongst;
	}

	public OsupNilExmp getOsupNilExmp() {
		return osupNilExmp;
	}

	public void setOsupNilExmp(OsupNilExmp osupNilExmp) {
		this.osupNilExmp = osupNilExmp;
	}

	public IsupRev getIsupRev() {
		return isupRev;
	}

	public void setIsupRev(IsupRev isupRev) {
		this.isupRev = isupRev;
	}

	public OsupZero getOsupZero() {
		return osupZero;
	}

	public void setOsupZero(OsupZero osupZero) {
		this.osupZero = osupZero;
	}

}
