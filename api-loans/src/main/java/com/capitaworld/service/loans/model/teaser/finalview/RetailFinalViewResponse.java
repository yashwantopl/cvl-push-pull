package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;
import java.util.List;

import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;

public class RetailFinalViewResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RetailFinalViewCommonResponse applicantCommonDetails;
	private List<RetailFinalViewCommonResponse> coApplicantCommonDetails;
	private List<RetailFinalViewCommonResponse> guarantorCommonDetails;
	
	public RetailFinalViewCommonResponse getApplicantCommonDetails() {
		return applicantCommonDetails;
	}

	public void setApplicantCommonDetails(RetailFinalViewCommonResponse applicantCommonDetails) {
		this.applicantCommonDetails = applicantCommonDetails;
	}

	public List<RetailFinalViewCommonResponse> getCoApplicantCommonDetails() {
		return coApplicantCommonDetails;
	}

	public void setCoApplicantCommonDetails(List<RetailFinalViewCommonResponse> coApplicantCommonDetails) {
		this.coApplicantCommonDetails = coApplicantCommonDetails;
	}

	public List<RetailFinalViewCommonResponse> getGuarantorCommonDetails() {
		return guarantorCommonDetails;
	}

	public void setGuarantorCommonDetails(List<RetailFinalViewCommonResponse> guarantorCommonDetails) {
		this.guarantorCommonDetails = guarantorCommonDetails;
	}
	
	
}
