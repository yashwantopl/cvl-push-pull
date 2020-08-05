/**
 * 
 */
package com.opl.mudra.api.connect;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author vijay.chauhan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonMCQ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean gstRegistered;
	
	private Boolean gstRegularFilling;
	
	private Boolean gstNaProduct;
	
	private Boolean gstNaState;
	
	private Boolean gstSalesUnderLimit;
	
	private Boolean gstOther;

	private String gstOtherReason;

	private Boolean isMsmeTermsAccept;
	
	private Boolean isSixMonthBS;

	public Boolean getGstRegistered() {
		return gstRegistered;
	}

	public void setGstRegistered(Boolean gstRegistered) {
		this.gstRegistered = gstRegistered;
	}

	public Boolean getGstRegularFilling() {
		return gstRegularFilling;
	}

	public void setGstRegularFilling(Boolean gstRegularFilling) {
		this.gstRegularFilling = gstRegularFilling;
	}

	public Boolean getGstNaProduct() {
		return gstNaProduct;
	}

	public void setGstNaProduct(Boolean gstNaProduct) {
		this.gstNaProduct = gstNaProduct;
	}

	public Boolean getGstNaState() {
		return gstNaState;
	}

	public void setGstNaState(Boolean gstNaState) {
		this.gstNaState = gstNaState;
	}

	public Boolean getGstSalesUnderLimit() {
		return gstSalesUnderLimit;
	}

	public void setGstSalesUnderLimit(Boolean gstSalesUnderLimit) {
		this.gstSalesUnderLimit = gstSalesUnderLimit;
	}

	public Boolean getGstOther() {
		return gstOther;
	}

	public void setGstOther(Boolean gstOther) {
		this.gstOther = gstOther;
	}

	public String getGstOtherReason() {
		return gstOtherReason;
	}

	public void setGstOtherReason(String gstOtherReason) {
		this.gstOtherReason = gstOtherReason;
	}

	public Boolean getIsMsmeTermsAccept() {
		return isMsmeTermsAccept;
	}

	public void setIsMsmeTermsAccept(Boolean isMsmeTermsAccept) {
		this.isMsmeTermsAccept = isMsmeTermsAccept;
	}

	public Boolean getIsSixMonthBS() {
		return isSixMonthBS;
	}

	public void setIsSixMonthBS(Boolean isSixMonthBS) {
		this.isSixMonthBS = isSixMonthBS;
	}
	 
}
