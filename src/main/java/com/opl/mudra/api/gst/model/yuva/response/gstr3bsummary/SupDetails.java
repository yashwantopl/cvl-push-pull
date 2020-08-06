/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SupDetails {
	
	@JsonProperty("osup_det")
	private OsupDet osupDet;

	@JsonProperty("osup_zero")
	private OsupZero osupZero;

	@JsonProperty("osup_nil_exmp")
	private OsupNilExmp osupNilExmp;

	@JsonProperty("isup_rev")
	private IsupRev isupRev;

	@JsonProperty("osup_nongst")
	private OsupNongst osupNongst;


	/**
	 * @return the isupRev
	 */
	public IsupRev getIsupRev() {
		return isupRev;
	}

	/**
	 * @param isupRev the isupRev to set
	 */
	public void setIsupRev(IsupRev isupRev) {
		this.isupRev = isupRev;
	}

	/**
	 * @return the osupNongst
	 */
	public OsupNongst getOsupNongst() {
		return osupNongst;
	}

	/**
	 * @param osupNongst the osupNongst to set
	 */
	public void setOsupNongst(OsupNongst osupNongst) {
		this.osupNongst = osupNongst;
	}

	/**
	 * @return the osupDet
	 */
	public OsupDet getOsupDet() {
		return osupDet;
	}

	/**
	 * @param osupDet the osupDet to set
	 */
	public void setOsupDet(OsupDet osupDet) {
		this.osupDet = osupDet;
	}

	/**
	 * @return the osupZero
	 */
	public OsupZero getOsupZero() {
		return osupZero;
	}

	/**
	 * @param osupZero the osupZero to set
	 */
	public void setOsupZero(OsupZero osupZero) {
		this.osupZero = osupZero;
	}

	/**
	 * @return the osupNilExmp
	 */
	public OsupNilExmp getOsupNilExmp() {
		return osupNilExmp;
	}

	/**
	 * @param osupNilExmp the osupNilExmp to set
	 */
	public void setOsupNilExmp(OsupNilExmp osupNilExmp) {
		this.osupNilExmp = osupNilExmp;
	}


	public SupDetails() {
		this.osupDet = new OsupDet();
		this.osupZero = new OsupZero();
		this.osupNilExmp = new OsupNilExmp();
		this.isupRev = new IsupRev();
		this.osupNongst = new OsupNongst();
	}
}
